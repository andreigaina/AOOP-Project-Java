package PaooGame.Items.Statics;

import PaooGame.Graphics.Assets;
import PaooGame.RefLinks;
import PaooGame.Tiles.Tile;
import java.awt.*;

    /*! \class public class Key extends StaticEntity
        \brief Defineste notiunea de cheie pentru a putea trece la nivelul urmator.
     */
public class Key extends StaticEntity {
    public int id = 33; /*!< Referinta catre id.*/

    /*! \fn public Key(Handler handler, float x, float y)
        \brief Constructor de initializare a unei entitati Statice de tip cheie.

        \param handler Un obiect de tip Handler.
        \param x Coordonata pe orizontala.
        \param y Coordonata pe verticala.
     */
    public Key(RefLinks handler, float x, float y) {
        super(handler, x, y, Tile.TILE_WIDTH, Tile.TILE_HEIGHT);
    }

     /*! \fn  public void die()
         \brief Functie ce defineste o actiune atunci cand o entitate statica "moare".
          Momentan aceasta functie nu este utila in program.
      */
    @Override
    public void die() {

    }

    /*! \fn public void Update()
        \brief Actualizeaza starea entitatii de tip cheie din joc.
         Momentan aceasta functie nu este utila in program.

     */
    @Override
    public void Update() {

    }

    /*! \fn public void Draw(Graphics g)
        \brief  Deseneaza cheia pe ecran.
        \param g Contextul grafic in care trebuie sa deseneze enitatea  pe ecran.
     */
    @Override
    public void Draw(Graphics g) {
        g.drawImage(Assets.key,(int) x,(int) y,width,height,null);
    }

    /*! \fn public int getId()
        \brief  Returneaza id-ul
     */
    public int getId() {
        return id;
    }

    /*! \fn public void setId(int id)
        \brief  Seteaza id-ul.
     */
    public void setId(int id) {
        this.id = id;
    }
}
