package PaooGame.States;

import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import PaooGame.Game;
import PaooGame.RefLinks;
import PaooGame.UI.Sound;
import PaooGame.UI.UIManager;

/*! \class State
    \brief Implementeaza notiunea abstracta de stare a jocului/programului.

    Un joc odata ce este lansat in executie nu trebuie "sa arunce jucatorul direct in lupta", este nevoie de
    un meniu care sa contine optiuni: New Game, Load Game, Settings, About etc. Toate aceste optiuni nu sunt altceva
    decat stari ale programului (jocului) ce trebuiesc incarcate si afisate functie de starea curenta.
 */
public abstract class State
{
        ///Urmatoarele atribute sunt statice pentru a evita dealocarea spatiului de memorie la trecerea dintr-o stare in alta.
    private static State previousState  = null; /*!< Referinta catre starea anterioara a jocului.*/
    private static State currentState   = null; /*!< Referinta catre starea curenta a jocului: game, meniu, settings, about etc.*/
    private static RefLinks refLink;            /*!< O referinta catre un obiect "shortcut", obiect ce contine o serie de referinte utile in program.*/

    /*! \fn public State(RefLinks refLink)
            \brief Constructorul de initializare al clasei.

            \param refLink O referinta catre un obiect "shortcut", obiect ce contine o serie de referinte utile in program.
     */
    public State(RefLinks refLink)
    {
        State.refLink = refLink;
    }

    /*! \fn public static void SetState(State state)
        \brief Seteaza starea curenta a jocului.

        \param state Noua stare a programului (jocului).
     */
    public static void SetState(State state)
    {
        previousState = currentState;
        currentState = state;
        ///Muzica specifica fiecarei stari
        if(Game.sound) {
            if (state == refLink.GetGame().getMenuState())
                Sound.loop("MenuMusic", 1000, 1000, Sound.getFrames("MenuMusic") - 1000);
            else
                Sound.stop("MenuMusic");

            if (state == refLink.GetGame().getPlayState())
                Sound.loop("PlayMusic", 1000, 1000, Sound.getFrames("PlayMusic") - 1000);
            else
                Sound.stop("PlayMusic");

            if (state == refLink.GetGame().getPauseState() || state == refLink.GetGame().getSettingsState())
                Sound.loop("PauseMusic", 1000, 1000, Sound.getFrames("PauseMusic") - 1000);
            else
                Sound.stop("PauseMusic");
        }
        else
            {
                Sound.stop("MenuMusic");
                Sound.stop("PlayMusic");
                Sound.stop("PauseMusic");
            }
    }
    /*! \fn public static void LoadDB_Level()
        \brief Se incarca din baza de date nivelul salvat.
     */
    public static void LoadDB_Level() {
        Connection c;
        Statement stmt;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:game_level.db");
            c.setAutoCommit(false);
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM GAME_LEVEL;");
            while (rs.next()) {
                Game.level= rs.getInt("SAVED_LEVEL");
            }
            rs.close();
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }
    /*! \fn public abstract UIManager GetUIManager()
        \brief Returneaza managerul de interfata cu utilizatorul.
    */
    public abstract UIManager GetUIManager();

    /*! \fn public static State GetState()
        \brief Returneaza starea curenta.
    */
    public static State GetState()
    {
        return currentState;
    }

    /*! \fn public static State GetPreviousState()
        \brief Returneaza starea anterioara.
    */
    public static State GetPreviousState()
    {
        return previousState;
    }

    /*! \fn public void Update()
        \brief Actualizeaza starea .
         Metoda abstracta destinata actualizarii starii curente
     */
    public abstract void Update();

    /*! \fn public void Draw(Graphics g)
        \brief Deseneaza (randeaza) pe ecran starile.
        Metoda abstracta destinata desenarii starii curente
        \param g Contextul grafic in care trebuie sa deseneze starea setarilor pe ecran.
     */
    public abstract void Draw(Graphics g);
}
