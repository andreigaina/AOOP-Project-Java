package PaooGame.Maps;

import PaooGame.Game;
import PaooGame.Graphics.Background;
import PaooGame.Items.ItemsManager;
import PaooGame.Items.Statics.*;
import PaooGame.Items.WaterGirl;
import PaooGame.RefLinks;
import PaooGame.States.PlayState;
import PaooGame.Tiles.Tile;

import java.awt.*;

/*! \class public class Map
    \brief Implementeaza notiunea de harta a jocului(cuprinde si entitatile, nu doar dalele).
 */
public class Map {
    private RefLinks refLink;       /*!< O referinte catre un obiect "shortcut", obiect ce contine o serie de referinte utile in program.*/
    private int width;              /*!< Latimea hartii in numar de dale.*/
    private int height;             /*!< Inaltimea hartii in numar de dale.*/
    private int spawnX,spawnY=565;  /*!< Locul unde va fi afisat caracterul pentru inceput.*/
    private int[][] tiles;     /*!< Referinta catre o matrice cu codurile dalelor ce vor construi harta.*/
    private Background backgroundLvl1 = new Background("/textures/background1.jpg"); /*!< Background-ul  pentru lvl. 1.*/
    private Background backgroundLvl2 = new Background("/textures/foc.jpg");         /*!< Background-ul  pentru lvl. 2.*/
    //Entities
    private ItemsManager itemsManagerLvl1;  /*!< Managerul de entitati pentru lvl. 1*/
    private ItemsManager itemsManagerLvl2;  /*!< Managerul de entitati pentru lvl. 2*/



    /*! \fn public Map(RefLinks refLink)
            \brief Constructorul de initializare al clasei.

            \param refLink O referinta catre un obiect "shortcut", obiect ce contine o serie de referinte utile in program.
     */
    public Map(RefLinks refLink) {
        /// Retine referinta "shortcut"
        this.refLink = refLink;
        ///Managerul de entitati pentru lvl. 1
        itemsManagerLvl1 = new ItemsManager(refLink,new WaterGirl(refLink,0, 469));
        itemsManagerLvl1.addEntity(new RedWater(refLink,0,621));
        itemsManagerLvl1.addEntity(new RedWater(refLink,192,621));
        itemsManagerLvl1.addEntity(new RedWater(refLink,288,621));
        itemsManagerLvl1.addEntity(new RedWater(refLink,192+48,621));
        itemsManagerLvl1.addEntity(new RedWater(refLink,528,285));

        itemsManagerLvl1.addEntity(new BlueWater(refLink,384,571));
        itemsManagerLvl1.addEntity(new BlueWater(refLink,820,618));
        itemsManagerLvl1.addEntity(new BlueWater(refLink,700,618));
        itemsManagerLvl1.addEntity(new BlueWater(refLink,760,618));
        itemsManagerLvl1.addEntity(new BlueWater(refLink,480+97,618));
        itemsManagerLvl1.addEntity(new BlueWater(refLink,637,618));
        itemsManagerLvl1.addEntity(new BlueWater(refLink,420+57,618));
        itemsManagerLvl1.addEntity(new BlueWater(refLink,420+97,618));

        itemsManagerLvl1.addEntity(new BlueDoor(refLink,634,438));

        itemsManagerLvl1.addEntity(new BlueDiamondItem(refLink,408,528));
        itemsManagerLvl1.addEntity(new BlueDiamondItem(refLink,0,390));
        itemsManagerLvl1.addEntity(new BlueDiamondItem(refLink,48,294));
        itemsManagerLvl1.addEntity(new BlueDiamondItem(refLink,264,96));

        ///Managerul de entitati pentru lvl. 2
        itemsManagerLvl2 = new ItemsManager(refLink,new WaterGirl(refLink,100, 565));

        itemsManagerLvl2.addEntity(new BlueDiamondItem(refLink,384,531));
        itemsManagerLvl2.addEntity(new BlueDiamondItem(refLink,456,367));
        itemsManagerLvl2.addEntity(new BlueDiamondItem(refLink,168,53));
        itemsManagerLvl2.addEntity(new BlueDiamondItem(refLink,576,147));
        itemsManagerLvl2.addEntity(new BlueDiamondItem(refLink,888,243));
        itemsManagerLvl2.addEntity(new BlueDiamondItem(refLink,888,559));

        itemsManagerLvl2.addEntity(new BlueWater(refLink,192,235));
        itemsManagerLvl2.addEntity(new BlueWater(refLink,384,235));
        itemsManagerLvl2.addEntity(new BlueWater(refLink,240,620));
        itemsManagerLvl2.addEntity(new BlueWater(refLink,816,618));
        itemsManagerLvl2.addEntity(new BlueWater(refLink,168+30,476));
        itemsManagerLvl2.addEntity(new BlueWater(refLink,168-29,476));
        itemsManagerLvl2.addEntity(new BlueWater(refLink,0,235));

        itemsManagerLvl2.addEntity(new RedWater(refLink,432,476));
        itemsManagerLvl2.addEntity(new RedWater(refLink,653 ,621));
        itemsManagerLvl2.addEntity(new RedWater(refLink,144,140));
        itemsManagerLvl2.addEntity(new RedWater(refLink,792,283));
        itemsManagerLvl2.addEntity(new RedWater(refLink,-46,476));

        itemsManagerLvl2.addEntity(new BlueDoor(refLink,872,390));

        ///incarca harta de start. Functia primeste ca argument id-ul hartii ce poate fi incarcat.
        LoadWorld(Game.level);
        switch (Game.level){
            case 1:
                itemsManagerLvl1.getWaterGirl().SetX(spawnX=0);
                itemsManagerLvl1.getWaterGirl().SetY(spawnY=469);
                break;
            case 2:
                itemsManagerLvl2.getWaterGirl().SetX(spawnX=100);
                itemsManagerLvl2.getWaterGirl().SetY(spawnY=565);

        }
    }
    /*! \fn public  void addEntity(int id, StaticEntity e)
        \brief Functie pentru adaugarea entitatilor necesare construirii nivelului.
        \param id ID-ul entitatii.
        \param e Entitatea adaugata.
     */
    public void addEntity(int id, StaticEntity e)
    {
        switch(id) {
            case 1:
                    itemsManagerLvl1.addEntity(e);
                break;
            case 2:
                    itemsManagerLvl2.addEntity(e);
                break;

        }
    }
    /*! \fn public  void Update()
        \brief Actualizarea hartii in functie de evenimente (un copac a fost taiat)
     */
    public void Update() {
        switch(Game.level) {
            case 1:
                itemsManagerLvl1.Update();
                break;
            case 2:
                itemsManagerLvl2.Update();
                break;
            default:
                itemsManagerLvl1.Update();
        }

    }

    /*! \fn public void Draw(Graphics g)
        \brief Functia de desenare a hartii.

        \param g Contextul grafic in care se realizeaza desenarea.
     */
    public void Draw(Graphics g) {
        switch(Game.level){
            case 1:
                backgroundLvl1.Draw(g);
                break;
            case 2:
                backgroundLvl2.Draw((g));
                break;
            default:
                backgroundLvl1.Draw(g);
        }
        ///Se parcurge matricea de dale (codurile aferente) si se deseneaza harta respectiva
        for (int y = 0; y <= refLink.GetGame().GetHeight() / Tile.TILE_HEIGHT; y++) {
            for (int x = 0; x < refLink.GetGame().GetWidth() / Tile.TILE_WIDTH; x++) {
                GetTile(x, y).Draw(g, x * Tile.TILE_HEIGHT, y * Tile.TILE_WIDTH);
//verificare afisare triunghi
 /*
                if (GetTile(x, y) == Tile.downIce) {
                    int[] a = {x * Tile.TILE_WIDTH, x * Tile.TILE_WIDTH + Tile.TILE_WIDTH, x * Tile.TILE_WIDTH + Tile.TILE_WIDTH};
                    int[] b = {y * Tile.TILE_HEIGHT + Tile.TILE_HEIGHT, y * Tile.TILE_HEIGHT, y * Tile.TILE_HEIGHT + Tile.TILE_HEIGHT};
                    g.fillPolygon(a, b, a.length);
                } else if (GetTile(x, y) == Tile.upperIce) {
                    int[] a = {x * Tile.TILE_WIDTH, x * Tile.TILE_WIDTH + Tile.TILE_WIDTH, x * Tile.TILE_WIDTH};
                    int[] b = {y * Tile.TILE_HEIGHT, y * Tile.TILE_HEIGHT, y * Tile.TILE_HEIGHT + Tile.TILE_HEIGHT};
                    g.fillPolygon(a, b, a.length);
                }
*/

            }
        }
        switch(Game.level){
            case 1:
                itemsManagerLvl1.Draw(g);
                break;
            case 2:
                itemsManagerLvl2.Draw(g);
                break;
            default:
                itemsManagerLvl1.Draw(g);
        }

    }

    /*! \fn public Tile GetTile(int x, int y)
        \brief Intoarce o referinta catre dala aferenta codului din matrice de dale.

        In situatia in care dala nu este gasita datorita unei erori ce tine de cod dala, coordonate gresite etc se
        intoarce o dala predefinita (ex. grassTile, mountainTile)
     */
    public Tile GetTile(int x, int y) {
        if (x < 0 || y < 0 || x >= width || y >= height) {

            return Tile.iceTile;
        }
        Tile t = Tile.tiles[tiles[x][y]];
        if (t == null) {
            return Tile.iceTile;
        }
        return t;
    }

    /*! \fn private void LoadWorld()
        \brief Functie de incarcare a hartii jocului.
        Aici se poate genera sau incarca din fisier harta. Momentan este incarcata static.
     */
    private void LoadWorld(int level) {
        //atentie latimea si inaltimea trebuiesc corelate cu dimensiunile ferestrei sau
        //se poate implementa notiunea de camera/cadru de vizualizare al hartii
        ///Se stabileste latimea hartii in numar de dale.
        width = 20;
        ///Se stabileste inaltimea hartii in numar de dale
        height = 14;
        ///Se construieste matricea de coduri de dale
        tiles = new int[width][height];
        //Se incarca matricea cu coduri
        MapFactory mapFactory = new MapFactory();
        LevelMap map1 = mapFactory.getMap(level);
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                tiles[x][y] = map1.getMap(y, x);
            }
        }
    }

    /*! \fn public Polygon getBounds(int x, int y)
        \brief Functie ce returneaza poligonul de coliziune.
        \param x Pozitia pe axa Ox a dalei.
        \param y Pozitia pe axa Oy a dalei.
     */
    public Polygon getBounds(int x, int y) {
        if (GetTile(x, y) == Tile.iceTile) {
            int[] a = {x, x + Tile.TILE_WIDTH, x + Tile.TILE_WIDTH, x};
            int[] b = {y, y, y + Tile.TILE_HEIGHT, y + Tile.TILE_HEIGHT};
            return new Polygon(a, b, a.length);
        } else {
            if (GetTile(x, y) == Tile.downIce) {
                int[] a = {x, x + Tile.TILE_WIDTH, x + Tile.TILE_WIDTH};
                int[] b = {y + Tile.TILE_HEIGHT, y, y + Tile.TILE_HEIGHT};
                return new Polygon(a, b, a.length);

            } else {
                    int[] a = {x, x + Tile.TILE_WIDTH, x + Tile.TILE_WIDTH, x};
                    int[] b = {y, y, y + Tile.TILE_HEIGHT, y + Tile.TILE_HEIGHT};
                    return new Polygon(a, b, a.length);
            }
        }
    }

    /*! \fn public ItemsManager getItemsManager()
        \brief Functie ce returneaza managerul de entitati specific fiecarui nivel.
     */
    public ItemsManager getItemsManager() {
        switch(Game.level){
            case 1:
                return itemsManagerLvl1;
            case 2:
                return itemsManagerLvl2;
            default:
                return itemsManagerLvl1;
        }
    }
}

