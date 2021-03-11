package PaooGame.States;

import PaooGame.Graphics.Assets;
import PaooGame.Graphics.Background;
import PaooGame.RefLinks;
import PaooGame.UI.Sound;
import PaooGame.UI.UIImageButton;
import PaooGame.UI.UIManager;
import java.awt.*;

/*! \class public class MenuState extends State
    \brief Implementeaza notiunea de meniu pentru joc.
 */
public class MenuState extends State
{
    private Background background = new Background("/textures/Menu_Background.png"); /*!< Background-ul pentru meniu.*/
    public UIManager uiManager;  /*!< Managerul de interfata cu utilizatorul.*/
    /*! \fn public MenuState(RefLinks refLink)
        \brief Constructorul de initializare al clasei.

        \param refLink O referinta catre un obiect "shortcut", obiect ce contine o serie de referinte utile in program.
     */
    public MenuState(RefLinks refLink)
    {
            ///Apel al constructorului clasei de baza.
        super(refLink);
            /// Muzica specifica.
        Sound.init();
        Sound.load("/F&W(menu-music).wav","MenuMusic");
        Sound.setVolume("MenuMusic", -10);
        Sound.load("/F&W(play-music).wav","PlayMusic");
        Sound.setVolume("PlayMusic", -10);
        Sound.load("/DeadMove.wav","DeadMove");

        Sound.load("/Pause.wav","PauseMusic");
        Sound.setVolume("PauseMusic", -10);
        Sound.load("/Jump.wav","Jump");
        Sound.load("/collect.wav","Collect");
        Sound.load("/Water.wav","Water");
        Sound.load("/Falling.wav","Falling");
        Sound.setVolume("Falling", -25);
        Sound.load("/Selected.wav","Selected");
        //Sound.setVolume("DeadMove", -10);
        /// User Interface Manager
        uiManager=new UIManager(refLink);
        refLink.GetMouseManager().setUIManager(uiManager);
        uiManager.addObject(new UIImageButton(400, 300, 110, 56, Assets.play, () -> {
        if(State.GetState()==refLink.GetGame().getMenuState())
        {
            //Game.level=1;
            State.SetState(refLink.GetGame().newGame());
            refLink.GetMouseManager().setUIManager(refLink.GetGame().getPlayState().GetUIManager());
        }
        }));
        uiManager.addObject(new UIImageButton(360,370,180,60,Assets.settings1,()->{
            if(State.GetState()==refLink.GetGame().getMenuState())
            {
                State.SetState(refLink.GetGame().getSettingsState());
                refLink.GetMouseManager().setUIManager(refLink.GetGame().getSettingsState().GetUIManager());
            }
        }));
        uiManager.addObject(new UIImageButton(370,440,160,45,Assets.about,()->{
            if(State.GetState()==refLink.GetGame().getMenuState())
            {

            }
        }));
    }
    /*! \fn public void Update()
        \brief Actualizeaza starea curenta a meniului.
     */
    @Override
    public void Update()
    {
        uiManager.Update();
    }

    /*! \fn public void Draw(Graphics g)
        \brief Deseneaza (randeaza) pe ecran starea curenta a meniului.

        \param g Contextul grafic in care trebuie sa deseneze starea jocului pe ecran.
     */
    @Override
    public void Draw(Graphics g)
    {
        background.Draw(g);
        uiManager.draw(g);
    }

    /*! \fn public UIManager GetUIManager()
        \brief Returneaza managerul de interfata cu utilizatorul.
    */
    public UIManager GetUIManager()
    {
        return uiManager;
    }
}
