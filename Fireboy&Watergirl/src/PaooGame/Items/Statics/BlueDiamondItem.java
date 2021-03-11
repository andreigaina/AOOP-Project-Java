package PaooGame.Items.Statics;

import PaooGame.Graphics.Assets;
import PaooGame.RefLinks;
import PaooGame.Tiles.Tile;
import java.awt.*;

    /*! \class public class BlueDiamondItem extends StaticEntity
        \brief Reprezinta un obiect de tipul diamant albastru
         acesta fiind o entitate statica.
     */
public class BlueDiamondItem extends StaticEntity {
    public int id = 1; /*!< Referinta catre id.*/

    /*! \fn public BlueDiamondItem(RefLinks handler, float x, float y)
        \brief Constructor de initializare a unei entitati Statice de tip recompensa.

        \param handler Un obiect de tip Handler.
        \param x Coordonata pe orizontala.
        \param y Coordonata pe verticala.
     */
    public BlueDiamondItem(RefLinks handler, float x, float y) {
        super(handler, x, y, Tile.TILE_WIDTH, Tile.TILE_HEIGHT);
    }

    /*! \fn  public void die()
        \brief Functie ce defineste o actiune atunci cand o entitate statica "moare".
        Momentan aceasta functie nu este utila in program.
     */
    @Override
    public void die() {

    }

    /*! \fn   public void Update()
           \brief  Actualizeaza starea entitatii.
            Momentan aceasta functie nu este utila in program.
     */
    @Override
    public void Update() {

    }

    /*! \fn private void Draw()
       \brief Deseneaza elementele grafice(diamantele) in fereastra coresponzator starilor actualizate ale elementelor.
    */
    @Override
    public void Draw(Graphics g) {
        g.drawImage(Assets.diamonds[0],(int) x,(int) y,width,height,null);
    }

    /*! \fn public int getId()
           \brief  Returneaza id-ul entitatii.
     */
    public int getId() {
        return id;
    }

    /*! \fn public void setId(int id)
            \brief  Seteaza id-ul entitatii.
     */
    public void setId(int id) {
        this.id = id;
    }
}
