package PaooGame.UI;

import PaooGame.RefLinks;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
/*! \class UIManager
    \brief Implementeaza notiunea de manager de interfata cu utilizatorul.
 */
public class UIManager {
    private RefLinks handler;               /*!< Referinta catre un obiect a carui sarcina este doar de a retine diverse referinte pentru a fi usor accesibile.*/
    private ArrayList<UIObject> objects;    /*!< Referinta catre un array de butoane.*/

    /*! \fn public  UIManager(RefLinks handler)
        \brief Constructorul de initializare al clasei.
        \param handler Handler UIManager.
    */
    public UIManager(RefLinks handler)
    {
        this.handler=handler;
        objects=new ArrayList<UIObject>();
    }

    /*! \fn  public  void Update()
        \brief Actualizeaza starea butoanelor.
     */
    public void Update(){
        for(UIObject o: objects)
            o.update();
    }

    /*! \fn  public abstract void Draw(Graphics g)
        \brief  Deseneaza butoanele.
        \param g Contextul grafic in care trebuie sa deseneze enitatea  pe ecran.
     */
    public void draw(Graphics g){
        for(UIObject o:objects) {
            o.draw(g);
        }
    }

    /*! \fn public void onMouseMove(MouseEvent e)
        \brief Verifica daca pozitia cursorului este deasupra unui buton.
        \param e Eveniment de tip mouse.
     */
    public void onMouseMove(MouseEvent e){
        for(UIObject o:objects)
            o.onMouseMove(e);
    }

    /*! \fn public void onMouseRelease(MouseEvent e)
        \brief Verifica daca a fost eliberat butonul de pe mouse.
        \param e Eveniment de tip mouse.
     */
    public void onMouseRelease(MouseEvent e){
        for(UIObject o:objects)
            o.onMouseRelease(e);
    }

    /*! \fn public void addObject(UIObject o)
        \brief Adauga un buton la array.
        \param o  Buton
     */
    public void addObject(UIObject o){
        objects.add(o);
    }

    /*! \fn public void removeObject(UIObject o)
        \brief Sterge un buton din array.
        \param o  Buton
     */
    public void removeObject(UIObject o){
        objects.remove(o);
    }
}
