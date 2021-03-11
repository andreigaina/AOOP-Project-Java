package PaooGame.Items.Statics;


import PaooGame.Items.Item;
import PaooGame.RefLinks;

    /*! \class StaticEntity extends Entity
        \brief Defineste notiunea abstracta de cheie/usa/apa/diamant din joc.
     */
public abstract class StaticEntity extends Item {
    /*! \fn public StaticEntity(Handler handler, float x, float y, int width, int height)
        \brief Constructor de initializare a unei entitati Statice(care nu se misca).

        \param handler Un obiect de tip Handler.
        \param x Coordonata pe orizontala.
        \param y Coordonata pe verticala.
        \param width Latimea entitatii.
        \param height Inaltimea entitatii.
     */
    public StaticEntity(RefLinks handler, float x, float y, int width, int height){
        super(handler, x, y, width, height);
    }
}
