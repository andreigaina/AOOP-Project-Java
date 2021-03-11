package PaooGame.States;

import PaooGame.Game;
import PaooGame.Graphics.Assets;
import PaooGame.RefLinks;
import PaooGame.Maps.Map;
import PaooGame.UI.UIImageButton;
import PaooGame.UI.UIManager;
import java.awt.*;

/*! \class public class PlayState extends State
    \brief Implementeaza/controleaza jocul.
 */
public class PlayState extends State
{
    private Map map;                /*!< Referinta catre harta curenta.*/
    private UIManager uiManager;    /*!< Referinta catre managerul de interfata cu utilizatorul.*/
    public static int diamonds = 0; /*!< Referinta statica catre numarul de diamante, initializat cu 0.*/
    public static int lifes = 3;    /*!< Referinta statica catre numarul de vieti, initializat cu 3.*/
    public static int score = 0;    /*!< Referinta statica catre numarul de puncte.*/
    public static int key = 0;      /*!< Referinta statica catre numarul de chei, initializat cu 0.*/

    /*! \fn public PlayState(RefLinks refLink)
        \brief Constructorul de initializare al clasei

        \param refLink O referinta catre un obiect "shortcut", obiect ce contine o serie de referinte utile in program.
     */
    public PlayState(RefLinks refLink)
    {
            ///Apel al constructorului clasei de baza
        super(refLink);
            ///Construieste harta jocului
        map = new Map(refLink);
            ///Referinta catre harta construita este setata si in obiectul shortcut pentru a fi accesibila si in alte clase ale programului.
        refLink.SetMap(map);
        uiManager=new UIManager(refLink);
        uiManager.addObject(new UIImageButton(890, 2, 40, 40, Assets.pause, () -> {
            if(State.GetState()==refLink.GetGame().getPlayState()) {
                State.SetState(refLink.GetGame().getPauseState());
                refLink.GetMouseManager().setUIManager(refLink.GetGame().getPauseState().GetUIManager());
            }
        }));
    }

    /*! \fn public void Update()
        \brief Actualizeaza starea curenta a jocului.
     */
    @Override
    public void Update()
    {
        map.Update();
        uiManager.Update();
    }

    /*! \fn public void Draw(Graphics g)
        \brief Deseneaza (randeaza) pe ecran starea curenta a jocului.

        \param g Contextul grafic in care trebuie sa deseneze starea jocului pe ecran.
     */
    @Override
    public void Draw(Graphics g)
    {
        map.Draw(g);

        g.setColor(Color.CYAN);
        g.fillRect(0, 0, 960, 42);
        g.setColor(Color.red);
        g.setFont(new Font("TimesRoman", Font.BOLD, 20));
        g.drawString("Lives: " + lifes , 30, 30);
        if(PlayState.lifes>=2)
            g.drawImage(Assets.life[0], 100, 0, 48, 48, null);
        else
            g.drawImage(Assets.life[1], 100, 0, 48, 48, null);
        g.setColor(Color.blue);
        g.setFont(new Font("Script", Font.BOLD, 20));
        g.drawString("Diamonds: "  + diamonds , 150, 30);
        if(PlayState.key!=0)
            g.drawImage(Assets.key,270,0,48,48,null);
        g.setColor(Color.orange);
        g.drawString("Score -> "  + score +" PTS", 580, 30);
        g.setColor(Color.black);
        g.setFont(new Font("Script", Font.BOLD, 20));
        g.drawString("L E V E L: " + Game.level , 770, 30);
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
