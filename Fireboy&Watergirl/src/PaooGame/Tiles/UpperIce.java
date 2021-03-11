package PaooGame.Tiles;

import PaooGame.Graphics.Assets;

/*! \class public class UpperIce extends Tile
    \brief Abstractizeaza notiunea de dala de tip gheata.
 */
public class UpperIce extends Tile
{
    /*! \fn public UpperIce(int id)
       \brief Constructorul de initializare al clasei.

       \param id Id-ul dalei util in desenarea hartii.
     */
    public UpperIce(int id)
    {
        super(Assets.ice[2], id);
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
