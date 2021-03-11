package PaooGame.UI;

import java.awt.*;
import java.awt.event.MouseEvent;
/*! \class UIObject
    \brief Implementeaza notiunea user interface object.
 */
public abstract class UIObject {
    protected float x,y;                /*!< Pozitia pe ecran a butonului.*/
    protected int width,height;         /*!< Inaltimea si latimea butonului.*/
    protected Rectangle bounds;         /*!< Dreptunghiul de coliziune a butonului.*/
    protected boolean hovering =false;  /*!< Variabila de verificare daca se afla cursorul pe buton.*/

    /*! \fn public  UIObject(float x, float y, int width, int height)
        \brief Constructorul de initializare al clasei.
        \param x Coordonata pe axa Ox.
        \param y Coordonata pe axa Oy.
        \param width Latimea butonului.
        \param height Inaltimea obiectului.
    */
    public UIObject(float x, float y, int width, int height){
        this.x=x;
        this.y=y;
        this.width=width;
        this.height=height;
        bounds =new Rectangle((int) x,(int) y,width,height);
    }
    /*! \fn  public abstract void Update()
        \brief Actualizeaza starea butoanelor.
         Urmeaza sa fie implemetata de clasele copil.
     */
    public abstract void update();
    /*! \fn  public abstract void Draw(Graphics g)
        \brief  Deseneaza butoanele.
         Urmeaza sa fie implemetata de clasele copil.

        \param g Contextul grafic in care trebuie sa deseneze enitatea  pe ecran.
     */
    public abstract void draw(Graphics g);

    /*! \fn  public abstract void onClick();
        \brief Se va defini o actiune in momentul in care apasam click.
     */
    public abstract void onClick();

    /*! \fn public void onMouseMove(MouseEvent e)
        \brief Verifica daca pozitia cursorului este deasupra unui buton.
        \param e Eveniment de tip mouse.
     */
    public void onMouseMove(MouseEvent e){
        hovering= bounds.contains(e.getX(), e.getY());
    }

    /*! \fn public void onMouseRelease(MouseEvent e)
        \brief Verifica daca a fost eliberat butonul de pe mouse.
        \param e Eveniment de tip mouse.
     */
    public void onMouseRelease(MouseEvent e){
        if(hovering)
            onClick();
    }
    /*! \fn public float getX()
        \brief Returneaza coordonata x.
     */
    public float getX() {
        return x;
    }
    /*! \fn public float getY()
        \brief Returneaza coordonata y.
     */
    public float getY() {
        return y;
    }
    /*! \fn public int getWidth()
        \brief Returneaza latimea.
     */
    public int getWidth() {
        return width;
    }
    /*! \fn public int getHeight()
        \brief Returneaza inaltimea.
     */
    public int getHeight() {
        return height;
    }

    /*! \fn public void setX(float x)
        \brief Seteaza coordonata x.
        \param Coordonata x.
     */
    public void setX(float x) {
        this.x = x;
    }

    /*! \fn public void setY(float y)
        \brief Seteaza coordonata y.
        \param y Coordonata y.
     */
    public void setY(float y) {
        this.y = y;
    }

    /*! \fn public void setWidth(int width)
        \brief Seteaza latimea.
        \param width Latimea.
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /*! \fn public void setHeight(int height)
        \brief Seteaza inaltimea.
        \param height Inaltimea.
     */
    public void setHeight(int height) {
        this.height = height;
    }
}
