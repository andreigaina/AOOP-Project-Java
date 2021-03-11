package PaooGame.Tiles;

import PaooGame.Graphics.Assets;

import java.awt.*;

/*! \class public class SoilTile extends Tile
    \brief Abstractizeaza notiunea de dala de tip sol/pamant.
 */
public class IceTile extends Tile
{

    /*! \fn public SoilTile(int id)
        \brief Constructorul de initializare al clasei

        \param id Id-ul dalei util in desenarea hartii.
     */
    public IceTile(int id)
    {
        super(Assets.ice[0], id);
    }

    @Override
    public boolean IsSolid()
    {
        return true;
    }

}
