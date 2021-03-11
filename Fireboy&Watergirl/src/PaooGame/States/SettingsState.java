package PaooGame.States;

import PaooGame.Game;
import PaooGame.Graphics.Assets;
import PaooGame.Graphics.Background;
import PaooGame.Graphics.ImageLoader;
import PaooGame.RefLinks;
import PaooGame.UI.UIImageButton;
import PaooGame.UI.UIManager;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/*! \class public class SettingsState extends State
    \brief Implementeaza notiunea de settings pentru joc.

    Aici setarile vor trebui salvate/incarcate intr-un/dintr-un fisier/baza de date sqlite.
 */
public class SettingsState extends State
{

    /*! \fn public SettingsState(RefLinks refLink)
        \brief Constructorul de initializare al clasei.

        \param refLink O referinta catre un obiect "shortcut", obiect ce contine o serie de referinte utile in program.
     */
    private Background background = new Background("/textures/Menu_Background.png");                /*!< Background-ul pentru meniul pauza.*/
    private BufferedImage settings= ImageLoader.LoadImage("/textures/Settings/settings_panel.png"); /*!< Imagine utila pentru meniul pauza.*/
    public UIManager uiManager;                                                                           /*!< Managerul de interfata cu utilizatorul.*/

    /*! \fn public SettingsState(RefLinks refLink)
         \brief Constructorul de initializare al clasei.

         \param refLink O referinta catre un obiect "shortcut", obiect ce contine o serie de referinte utile in program.
      */
    public SettingsState(RefLinks refLink) {
        ///Apel al construcotrului clasei de baza.
        super(refLink);
        uiManager = new UIManager(refLink);
        uiManager.addObject(new UIImageButton(430, 360, 110, 52, Assets.ok, () -> {
            if(State.GetState()==refLink.GetGame().getSettingsState())
            {
                refLink.GetMouseManager().setUIManager(refLink.GetGame().getPreviousState().GetUIManager());
                State.SetState(refLink.GetGame().getPreviousState());
            }
        }));
        uiManager.addObject(new UIImageButton(380, 270, 60, 60, Assets.sound, () -> {
            if(State.GetState()==refLink.GetGame().getSettingsState())
            {
                Game.sound=!Game.sound;
                ///Salvam in baza de date setarile facute
                Connection c = null;
                Statement stmt = null;
                try {
                    Class.forName("org.sqlite.JDBC");
                    c = DriverManager.getConnection("jdbc:sqlite:game_options.db");
                    c.setAutoCommit(false);
                    stmt = c.createStatement();
                    String sql = "UPDATE GAME_OPTIONS set SOUND = "+Game.sound+" where ID=1;";
                    stmt.executeUpdate(sql);
                    c.commit();
                    stmt.close();
                    c.close();
                }
                catch (Exception e1)
                {
                    System.err.println(e1.getClass().getName() + ": " + e1.getMessage());
                    System.exit(0);
                }
            }

        }));
        uiManager.addObject(new UIImageButton(520, 270, 60, 60, Assets.music, () -> {
            if(State.GetState()==refLink.GetGame().getSettingsState())
            {
                Game.music=!Game.music;
                ///Salvam in baza de date setarile facute
                Connection c = null;
                Statement stmt = null;
                try {
                    Class.forName("org.sqlite.JDBC");
                    c = DriverManager.getConnection("jdbc:sqlite:game_options.db");
                    c.setAutoCommit(false);
                    stmt = c.createStatement();
                    String sql = "UPDATE GAME_OPTIONS set MUSIC = "+Game.music+" where ID=1;";
                    stmt.executeUpdate(sql);
                    c.commit();
                    stmt.close();
                    c.close();
                }
                catch (Exception e1)
                {
                    System.err.println(e1.getClass().getName() + ": " + e1.getMessage());
                    System.exit(0);
                }
            }

        }));

    }

    /*! \fn public void Update()
        \brief Actualizeaza starea setarilor.
     */
    @Override
    public void Update()
    {
        uiManager.Update();
    }

    /*! \fn public void Draw(Graphics g)
        \brief Deseneaza (randeaza) pe ecran setarile.

        \param g Contextul grafic in care trebuie sa deseneze starea setarilor pe ecran.
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
