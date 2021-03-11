package PaooGame.States;

import PaooGame.Graphics.Assets;
import PaooGame.Graphics.Background;
import PaooGame.Graphics.ImageLoader;
import PaooGame.RefLinks;
import PaooGame.UI.UIImageButton;
import PaooGame.UI.UIManager;

import java.awt.*;
import java.awt.image.BufferedImage;

/*! \class public class PauseState extends State
    \brief Implementeaza notiunea de pauza pentru joc.
 */
public class PauseState extends State
{
    private Background background = new Background("/textures/Menu_Background.png");                /*!< Background-ul pentru meniul pauza.*/
    private BufferedImage settings= ImageLoader.LoadImage("/textures/Settings/pause_panel.png");    /*!< Imagine utila pentru meniul pauza.*/
    public UIManager uiManager;                                                                           /*!< Managerul de interfata cu utilizatorul.*/

    /*! \fn public PauseState(RefLinks refLink)
        \brief Constructorul de initializare al clasei.

        \param refLink O referinta catre un obiect "shortcut", obiect ce contine o serie de referinte utile in program.
     */
    public PauseState(RefLinks refLink) {
        ///Apel al construcotrului clasei de baza.
        super(refLink);
        uiManager = new UIManager(refLink);
        uiManager.addObject(new UIImageButton(430, 360, 110, 52, Assets.resume, () -> {
            if(State.GetState()==refLink.GetGame().getPauseState())
            {
                State.SetState(refLink.GetGame().getPlayState());
                refLink.GetMouseManager().setUIManager(refLink.GetGame().getPlayState().GetUIManager());
            }

        }));
        uiManager.addObject(new UIImageButton(340, 300, 115, 52, Assets.end, () -> {
            if(State.GetState()==refLink.GetGame().getPauseState())
            {
                State.SetState(refLink.GetGame().getMenuState());
                refLink.GetMouseManager().setUIManager(refLink.GetGame().getMenuState().GetUIManager());
            }
        }));
        uiManager.addObject(new UIImageButton(510, 300, 110, 52, Assets.retry, () -> {
            if(State.GetState()==refLink.GetGame().getPauseState())
            {
                State.SetState(refLink.GetGame().newGame());
                refLink.GetMouseManager().setUIManager(refLink.GetGame().getPlayState().GetUIManager());
            }
        }));
        uiManager.addObject(new UIImageButton(630, 190, 110, 60, Assets.settings2, () -> {
            if(State.GetState()==refLink.GetGame().getPauseState())
            {
                State.SetState(refLink.GetGame().getSettingsState());
                refLink.GetMouseManager().setUIManager(refLink.GetGame().getSettingsState().GetUIManager());
            }
        }));

    }

    /*! \fn public void Update()
        \brief Actualizeaza starea meniului.
     */
    @Override
    public void Update()
    {
        uiManager.Update();
    }

    /*! \fn public void Draw(Graphics g)
        \brief Deseneaza (randeaza) pe ecran setarile.

        \param g Contextul grafic in care trebuie sa deseneze starea meniului pe ecran.
     */
    @Override
    public void Draw(Graphics g)
    {
        background.Draw(g);
        g.drawImage(settings,230,150,500,300,null);
        uiManager.draw(g);
    }

    /*! \fn public UIManager GetUIManager()
        \brief Returneaza managerul de interfata cu utilizatorul.
    */
    @Override
    public UIManager GetUIManager()
    {
        return uiManager;
    }
}
