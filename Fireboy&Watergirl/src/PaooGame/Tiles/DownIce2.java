package PaooGame.Tiles;

import PaooGame.Graphics.Assets;

/*! \class public class DownIce extends Tile
    \brief Abstractizeaza notiunea de dala de tip gheata.
 */
public class DownIce2 extends Tile
{

    /*! \fn public DownIce2(int id)
       \brief Constructorul de initializare al clasei.

       \param id Id-ul dalei util in desenarea hartii.
    */
    public DownIce2(int id)
    {
        super(Assets.ice[4], id);
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
