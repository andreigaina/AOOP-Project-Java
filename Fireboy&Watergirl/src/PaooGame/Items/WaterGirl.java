package PaooGame.Items;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import PaooGame.Game;
import PaooGame.Items.Statics.BlueWater;
import PaooGame.Items.Statics.Key;
import PaooGame.Items.Statics.RedWater;
import PaooGame.RefLinks;
import PaooGame.Graphics.Assets;
import PaooGame.States.PlayState;
import PaooGame.States.State;
import PaooGame.Tiles.Tile;
import PaooGame.UI.Sound;
import javax.swing.*;

/*! \class public class WaterGirl extends Character
    \brief Implementeaza notiunea de erou/player (caracterul controlat de jucator).

    Elementele suplimentare pe care le aduce fata de clasa de baza sunt:
        imaginea (acest atribut poate fi ridicat si in clasa de baza)
        deplasarea
        atacul (nu este implementat momentan)
        dreptunghiul de coliziune
 */
public class WaterGirl extends Character {
    private BufferedImage image;    /*!< Referinta catre imaginea curenta a eroului.*/
    private static int aux = 0;     /*!< Variabila auxiliara care ajuta la desenarea eroului.*/
    private float startY, startX;   /*!< Pozitie inițiala(coordonate x si y)*/

    /*! \fn public WaterGirl(RefLinks refLink, float x, float y)
        \brief Constructorul de initializare al clasei Hero.

        \param refLink Referinta catre obiectul shortcut (obiect ce retine o serie de referinte din program).
        \param x Pozitia initiala pe axa X a eroului.
        \param y Pozitia initiala pe axa Y a eroului.
     */
    public WaterGirl(RefLinks refLink, float x, float y) {

        ///Apel al constructorului clasei de baza.
        super(refLink, x, y, Character.DEFAULT_CREATURE_WIDTH, Character.DEFAULT_CREATURE_HEIGHT);
        startX = x;
        startY = y;
        ///Seteaza imaginea de start a eroului
        image = Assets.heroStand[0];
        ///Stabilieste pozitia relativa si dimensiunea dreptunghiului de coliziune, starea implicita(normala).
        normalBounds.x = 3;
        normalBounds.y = 2;
        normalBounds.width = 36;
        normalBounds.height = 56;

        ///Stabilieste pozitia relativa si dimensiunea dreptunghiului de coliziune, starea de atac.
        attackBounds.x = 10;
        attackBounds.y = 10;
        attackBounds.width = 38;
        attackBounds.height = 38;
        ///Stabileste inaltimea pamantului.
        floorHeight = y + bounds.height;

    }
    /*! \fn private void checkCollect()
        \brief Prin aceasta functie se colecteaza itemii  din joc(diamante, chei),
        se realizeaza trecerea la urmatorul nivel si se salveaza in baza de date nivelul la care s-a ramas.
     */
        private void checkCollect() {
            Rectangle cb = getCollisionBounds(0, 0);
            Rectangle cr = new Rectangle();
            int crSize = 20;
            cr.width = crSize;
            cr.height = crSize;

            if (refLink.GetKeyManager().space) {
                cr.x = cb.x + cb.width / 2 - crSize / 2;
                cr.y = cb.y + cb.height;
            } else
                return;
            ///Caz pentru nivelul 1.
            switch (Game.level) {
                case 1:
                    for (Item e : refLink.GetMap().getItemsManager().getEntities()) {
                        if (e.equals(this))
                            continue;
                        else if (e.getCollisionBounds(0, 0).intersects(cr)) {
                            if (e.getId() == 1 || e.getId() == 33) {
                                if (Game.music)
                                    Sound.play("Collect");
                                if (e.getId() == 1) {
                                    PlayState.diamonds++;
                                    PlayState.score += 1000;
                                    if (PlayState.diamonds == 4)
                                        refLink.GetMap().addEntity(1, new Key(refLink, 720, 432));
                                } else {
                                    PlayState.key++;
                                    PlayState.score += 2000;
                                }
                                e.hurt(10);

                            } else if (e.getId() == 777 && PlayState.key != 0) {
                                PlayState.key = 0;
                                Game.level++;
                                Connection c = null;
                                Statement stmt = null;
                                try {
                                    Class.forName("org.sqlite.JDBC");
                                    c = DriverManager.getConnection("jdbc:sqlite:game_level.db");
                                    c.setAutoCommit(false);
                                    stmt = c.createStatement();
                                    String sql = "UPDATE GAME_LEVEL set SAVED_LEVEL = " + Game.level + " where ID=1;";
                                    stmt.executeUpdate(sql);
                                    c.commit();
                                    stmt.close();
                                    c.close();
                                } catch (Exception e1) {
                                    System.err.println(e1.getClass().getName() + ": " + e1.getMessage());
                                    System.exit(0);
                                }
                                PlayState.diamonds = 0;
                                if (State.GetState() == refLink.GetGame().getPlayState()) {
                                    State.SetState(refLink.GetGame().newGame());
                                    refLink.GetMouseManager().setUIManager(refLink.GetGame().getPlayState().GetUIManager());
                                }
                                return;
                            }
                            return;
                        }
                    }
                    break;
                    ///Caz pentru nivelul 2.
                case 2:
                    for (Item e : refLink.GetMap().getItemsManager().getEntities()) {
                        if (e.equals(this))
                            continue;
                        else if (e.getCollisionBounds(0, 0).intersects(cr)) {
                            if (e.getId() == 1 || e.getId() == 33) {
                                if (Game.music)
                                    Sound.play("Collect");
                                if (e.getId() == 1) {
                                    PlayState.diamonds++;
                                    PlayState.score += 1000;
                                    if (PlayState.diamonds == 6) {
                                        refLink.GetMap().addEntity(2, new Key(refLink, 24, 192));
                                        refLink.GetMap().addEntity(2, new RedWater(refLink, 576, 428));
                                        refLink.GetMap().addEntity(2, new RedWater(refLink, 719, 620));
                                        refLink.GetMap().addEntity(2, new RedWater(refLink, 576 + 96, 236));
                                        refLink.GetMap().addEntity(2, new RedWater(refLink, 288, 235));
                                        refLink.GetMap().addEntity(2, new BlueWater(refLink, 484, 620));
                                        refLink.GetMap().addEntity(2, new BlueWater(refLink, 550, 620));
                                    }
                                } else {
                                    PlayState.key++;
                                    PlayState.score += 2000;
                                }
                                e.hurt(10);
                            } else if (e.getId() == 777 && PlayState.key != 0) {
                                JOptionPane.showMessageDialog(null, "Well done GIRL! You won both worlds! See you next time!\n\t\tYour FINAL score is: " + PlayState.score);
                                Game.level = 1;
                                Connection c = null;
                                Statement stmt = null;
                                try {
                                    Class.forName("org.sqlite.JDBC");
                                    c = DriverManager.getConnection("jdbc:sqlite:game_level.db");
                                    c.setAutoCommit(false);
                                    stmt = c.createStatement();
                                    String sql = "UPDATE GAME_LEVEL set SAVED_LEVEL = " + Game.level + " where ID=1;";
                                    stmt.executeUpdate(sql);
                                    c.commit();
                                    stmt.close();
                                    c.close();
                                } catch (Exception e1) {
                                    System.err.println(e1.getClass().getName() + ": " + e1.getMessage());
                                    System.exit(0);
                                }
                                System.exit(0);

                            }
                            return;
                        }
                    }
            }
        }

        /*! \fn  public void checkObstacle()
            \brief Aici se sesizeaza coliziunea cu obstacolele care produc damage sau au influenta asupra
             caracterului si se realizeaza diferite operatii.
         */
       public void checkObstacle(){
           if(checkEntityCollisions(xMove,yMove)) {
               if (actualCollision == 14) {
                   if (PlayState.lifes != 1) {
                       --PlayState.lifes;
                   } else {
                       die();
                   }
                   x = startX;
                   y = startY;
               }
               if(actualCollision == 13 && !Sound.isPlaying("Water") && xMove!=0 && Game.music)
               {
                   Sound.loop("Water", 1000, 1000, Sound.getFrames("Water") - 1000);
               }
           }
           if(actualCollision!=13 || xMove==0)
           {
               Sound.stop("Water");
           }
       }
       /*! \fn  public void die()
           \brief Prin aceasta functie se realizeaza diferite operatii atunci cand caracterul moare.
        */
       @Override
       public void die(){
           JOptionPane.showMessageDialog(null, "You lost! Try harder next time!\n\t\tSCORE:" + PlayState.score);
           if (State.GetState() == refLink.GetGame().getPlayState() ) {
               State.SetState(refLink.GetGame().getMenuState());
               refLink.GetMouseManager().setUIManager(refLink.GetGame().getMenuState().GetUIManager());
           }


       }
       /*! \fn public void Update()
           \brief Actualizeaza pozitia si imaginea eroului.
        */
    @Override
    public void Update() {
        ///Verifica daca a fost apasata o tasta
        GetInput();
        ///Actualizeaza pozitia
        Move();
        ///Actualizeaza imaginea
        jumpUpdate();
        if (refLink.GetKeyManager().left && (!refLink.GetKeyManager().right)) {
            if (aux >= 7) {
                aux = 0;
            }
            image = Assets.heroLeft[aux];
            ++aux;
        } else {
            if (refLink.GetKeyManager().right && (!refLink.GetKeyManager().left)) {
                if (aux >= 7) {
                    aux = 0;
                }
                image = Assets.heroRight[aux];
                ++aux;
            } else {
                if (refLink.GetKeyManager().up)
                    image = Assets.heroUp;
                else {
                    if (falling) {
                        image = Assets.heroDown;
                       // if (!Sound.isPlaying("Falling"))
                        //     Sound.play("Falling");
                    }
                    else {
                      //  Sound.stop("Falling");
                        if ((refLink.GetKeyManager().left && refLink.GetKeyManager().right) ||
                                ((!refLink.GetKeyManager().left) && (!refLink.GetKeyManager().right)))
                            image = Assets.heroStand[0];
                    }
                }
            }
        }
        checkObstacle();
        checkCollect();

    }

    /*! \fn private void GetInput()
        \brief Verifica daca a fost apasata o tasta din cele stabilite pentru controlul eroului.
     */
    private void GetInput()
    {
            ///Implicit eroul nu trebuie sa se deplaseze daca nu este apasata o tasta
        xMove = 0;
        yMove = 0;
            ///Verificare apasare tasta "sus"
        if(refLink.GetKeyManager().up)
        {
            jump();
        }
            ///Verificare apasare tasta "left"
        if(refLink.GetKeyManager().left&&(!refLink.GetKeyManager().right))
        {
            xMove = -speed;
        }
            ///Verificare apasare tasta "dreapta"
        if(refLink.GetKeyManager().right&&(!refLink.GetKeyManager().left))
        {
            xMove = speed;
        }
    }

    /*! \fn public void Draw(Graphics g)
        \brief Randeaza/deseneaza eroul in noua pozitie.

        \brief g Contextul grafic in care trebuie efectuata desenarea eroului.
     */
    @Override
    public void Draw(Graphics g)
    {

            if(refLink.GetKeyManager().right&&(!refLink.GetKeyManager().left))
                g.drawImage(image, (int) x-40, (int) y, width, height, null);
            else
                if(refLink.GetKeyManager().left&&(!refLink.GetKeyManager().right))
                    g.drawImage(image, (int) x, (int) y, width, height, null);
                else
                        g.drawImage(image, (int) x, (int) y, width, height, null);

            ///doar pentru debug daca se doreste vizualizarea dreptunghiului de coliziune altfel se vor comenta urmatoarele doua linii
        //g.setColor(Color.red);
        //g.fillRect((int)(x + bounds.x), (int)(y + bounds.y), bounds.width, bounds.height);
    }
    /*! \fn public int getId()
        \brief Returneaza id-ul caracterului.
     */
    @Override
    public int getId() {
        return 0;
    }
    /*! \fn public void jumpUpdate()
        \brief Functie de update a sariturii caracterului.
     */
    protected  void jumpUpdate() {
            if (!isOnFloor()) {

                if (y - jumpStrength > 5) {
                    y -= jumpStrength;
                }
                    if (isOnTop()) {
                        y += jumpStrength;
                        //y=floorHeight-Tile.TILE_HEIGHT*2;
                        jumpStrength = 0.217f;
                    }
                    if (isOnFloor())
                        y += jumpStrength;
                    else {
                        jumpStrength -= 0.217f;//0.215->0.3
                        if (jumpStrength < 0)
                            jumpStrength -= 0.3f;
                    }
                }

        if(!falling)
        {
            if(isOnFloor()) {
                y = (int) ((y + bounds.height) / Tile.TILE_HEIGHT) * Tile.TILE_HEIGHT + 45 - bounds.height;
                floorHeight = y + bounds.height;
            }
        }
    }
}
