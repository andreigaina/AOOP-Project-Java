package PaooGame.Input;

import PaooGame.UI.UIManager;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
/*! \class public class MouseManager implements MouseListener, MouseMotionListener
    \brief Gestioneaza intrarea (input-ul) de mouse.

    Clasa citeste daca a fost utilizat mouse-ul si seteaza corespunzator un flag.
    In program trebuie sa se tina cont de flagul aferent tastei de interes a mouse-ului. Daca flagul respectiv este true inseamna
    ca tasta respectiva a fost apasata si false nu a fost apasata.
 */
public class MouseManager implements MouseListener, MouseMotionListener {
    private boolean leftPressed;     /*!< butonul stanga */
    private boolean rightPressed;    /*!< butonul dreapta */
    private int mouseX, mouseY;      /*!< pozitia pe axa Ox respectiv pe axa Oy a cursorului */
    private UIManager uiManager;     /*!< User interface manager */
    /*! \fn Mouse Manager()
        \brief Constructorul clasei
     */
    public MouseManager(){

    }
    /*! \fn public void setUIManager(UIManager uiManager)
            \brief Functie ce seteaza managerul de interfata cu utilizatorul.
     */
    public void setUIManager(UIManager uiManager){
        this.uiManager=uiManager;
    }
    /*! \fn public void mouseClicked(MouseEvent mouseEvent)
            \brief Functie ce verifica daca a fost apasat vreun buton.

            \param mouseEvent obiectul eveniment de mouse.
            Momentan aceasta functie nu este utila in program.
     */
    @Override
    public void mouseClicked(MouseEvent mouseEvent) {

    }
    /*! \fn  public void mousePressed(MouseEvent mouseEvent)
                \brief Functie ce verifica daca este apasat vreun buton.

                \param mouseEvent obiectul eveniment de mouse.
     */
    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        if(mouseEvent.getButton() == MouseEvent.BUTTON1)
            rightPressed=true;
        else if(mouseEvent.getButton() ==MouseEvent.BUTTON3)
            leftPressed=true;
    }
    /*! \fn  public void mouseReleased(MouseEvent mouseEvent)
                   \brief Functie ce verifica daca nu mai este apasat butonul.

                   \param mouseEvent obiectul eveniment de mouse.
     */
    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
        if(mouseEvent.getButton() == MouseEvent.BUTTON1)
            rightPressed=false;
        else if(mouseEvent.getButton() ==MouseEvent.BUTTON3)
            leftPressed=false;
        if(uiManager !=null)
            uiManager.onMouseRelease(mouseEvent);
    }
    /*! \fn public void mouseEntered(MouseEvent mouseEvent)
                \param mouseEvent obiectul eveniment de mouse.
                Momentan aceasta functie nu este utila in program.
     */
    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }
    /*! \fn public void mouseExited(MouseEvent mouseEvent)
                 \param mouseEvent obiectul eveniment de mouse.
                  Momentan aceasta functie nu este utila in program.
     */
    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }
    /*! \fn public void mouseDragged(MouseEvent mouseEvent)
                 \param mouseEvent obiectul eveniment de mouse.
                  Momentan aceasta functie nu este utila in program.
      */
    @Override
    public void mouseDragged(MouseEvent mouseEvent) {

    }
    /*! \fn public void mouseMoved(MouseEvent mouseEvent)
                \brief Functie ce verifica daca a fost miscat cursorul.

                \param mouseEvent obiectul eveniment de mouse.
     */
    @Override
    public void mouseMoved(MouseEvent mouseEvent) {
        ///Se localizeaza pozitia cursorului pe ecran si se verifica
        ///este deasupra unui buton din joc.
        mouseX=mouseEvent.getX();
        mouseY=mouseEvent.getY();
        if(uiManager !=null)
            uiManager.onMouseMove(mouseEvent);
    }
    /*! \fn public boolean isLeftPressed()
               \brief Functie ce verifica daca a fost apasat butonul din stanga
                a mouse-ului.
    */
    public boolean isLeftPressed() {
        return leftPressed;
    }
    /*! \fn  public boolean isRightPressed()
                \brief Functie ce verifica daca a fost apasat butonul din dreapta
                 a mouse-ului.
     */
    public boolean isRightPressed() {
        return rightPressed;
    }
    /*! \fn  public int getMouseX()
                \brief Functie ce returneaza pozitia pe axa Ox
                 a mouse-ului.
     */
    public int getMouseX() {
        return mouseX;
    }
    /*! \fn  public int getMouseX()
                \brief Functie ce returneaza pozitia pe axa Oy
                 a mouse-ului.
     */
    public int getMouseY() {
        return mouseY;
    }
}
