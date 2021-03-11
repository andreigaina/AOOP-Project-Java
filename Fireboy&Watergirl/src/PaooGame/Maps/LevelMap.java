package PaooGame.Maps;

/*! \class public abstract class LevelMap
    \brief Implementeaza notiunea de harta pentru fiecare nivel.
     Aceasta clasa va fi derivata.

 */
public abstract class LevelMap {
    int [][] map=null;  /*!< Obiect de tip harta.*/
    /*! \fn public  int getMap(int x,int y)
        \brief Este returnat codul dalei.
        \param x Coordonata x pe harta(matrice).
        \param y Coordonata y pe harta(matrice).
     */
    public  int getMap(int x,int y) {
        return this.map[x][y];
    }
}
