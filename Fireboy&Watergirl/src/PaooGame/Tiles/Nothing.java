package PaooGame.Tiles;

import PaooGame.Graphics.Assets;

/*! \class public class Nothing extends Tile
    \brief Abstractizeaza notiunea de nimic.
 */
public class Nothing extends Tile
{
    /*! \fn public Nothing(int id)
        \brief Constructorul de initializare al clasei

        \param id Id-ul dalei util in desenarea hartii.
     */
    public Nothing(int id)
    {
            /// Apel al constructorului clasei de baza
        super(Assets.grass, id);
    }
}
