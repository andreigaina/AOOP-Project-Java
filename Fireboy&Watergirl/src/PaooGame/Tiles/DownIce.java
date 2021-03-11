package PaooGame.Tiles;

import PaooGame.Graphics.Assets;

/*! \class public class DownIce extends Tile
    \brief Abstractizeaza notiunea de dala de tip gheata.
 */
public class DownIce extends Tile
{

    /*! \fn public DownIce(int id)
        \brief Constructorul de initializare al clasei.

        \param id Id-ul dalei util in desenarea hartii.
     */
    public DownIce(int id)
    {
        super(Assets.ice[1], id);
    }

    /*! \fn public boolean IsSolid()
        \brief Returneaza proprietatea de dala solida (supusa coliziunilor) sau nu.
     */
    @Override
    public boolean IsSolid()
    {
        return true;
    }

}
