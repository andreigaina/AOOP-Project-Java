package PaooGame.Tiles;

import PaooGame.Graphics.Assets;

import java.awt.*;

/*! \class public class UpperIce2 extends Tile
    \brief Abstractizeaza notiunea de dala de tip gheata.
 */

public class UpperIce2 extends Tile
{

    /*! \fn public UpperIce2(int id)
       \brief Constructorul de initializare al clasei.

       \param id Id-ul dalei util in desenarea hartii.
     */
    public UpperIce2(int id)
    {
        super(Assets.ice[3], id);
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
