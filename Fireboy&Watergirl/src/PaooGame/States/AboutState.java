package PaooGame.States;

import PaooGame.RefLinks;
import PaooGame.UI.UIManager;

import java.awt.*;

/*! \class  AboutState extends State
    \brief Implementeaza notiunea de credentiale (about)
 */
public class AboutState extends State
{
    /*! \fn public AboutState(RefLinks refLink)
        \brief Constructorul de initializare al clasei.

        \param refLink O referinta catre un obiect "shortcut", obiect ce contine o serie de referinte utile in program.
     */
    public AboutState(RefLinks refLink)
    {
            ///Apel al constructorului clasei de baza.
        super(refLink);
    }
    /*! \fn public void Update()
        \brief Actualizeaza starea curenta a meniului about.
     */
    @Override
    public void Update()
    {

    }

    /*! \fn public void Draw(Graphics g)
        \brief Deseneaza (randeaza) pe ecran starea curenta a meniului about.

        \param g Contextul grafic in care trebuie sa se deseneze starea jocului pe ecran.
     */
    @Override
    public void Draw(Graphics g)
    {

    }
    /*! \fn public UIManager GetUIManager()
        \brief Returneaza managerul de interfata cu utilizatorul.
     */
    @Override
    public UIManager GetUIManager()
    {
        return null;
    }

}
