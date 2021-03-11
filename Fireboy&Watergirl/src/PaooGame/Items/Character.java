package PaooGame.Items;

import PaooGame.Game;
import PaooGame.RefLinks;
import PaooGame.UI.Sound;


    /*! \class public abstract class Character extends Item
        \brief Defineste notiunea abstracta de caracter/individ/fiinta din joc.

        Notiunea este definita doar de viata, viteza de deplasare si distanta cu care trebuie sa se
        miste/deplaseze in urma calculelor.
     */
public abstract class Character extends Item
{

    public static final float DEFAULT_SPEED         = 3.0f; /*!< Viteza implicita a unui caracter.*/
    public static final int DEFAULT_CREATURE_WIDTH  = 80;   /*!< Latimea implicita a imaginii caracterului.*/
    public static final int DEFAULT_CREATURE_HEIGHT = 80;   /*!< Inaltimea implicita a imaginii caracterului.*/

    protected float speed;          /*!< Retine viteza de deplasare caracterului.*/
    protected float xMove;          /*!< Retine noua pozitie a caracterului pe axa X.*/
    protected float yMove;          /*!< Retine noua pozitie a caracterului pe axa Y.*/
    protected float jumpStrength;   /*!< Retine puterea saltului caracterului.*/

    /*! \fn public Character(RefLinks refLink, float x, float y, int width, int height)
        \brief Constructor de initializare al clasei Character.

        \param refLink Referinta catre obiectul shortcut (care retine alte referinte utile/necesare in joc).
        \param x Pozitia de start pa axa X a caracterului.
        \param y Pozitia de start pa axa Y a caracterului.
        \param width Latimea imaginii caracterului.
        \param height Inaltimea imaginii caracterului.
     */
    public Character(RefLinks refLink, float x, float y, int width, int height)
    {
            ///Apel constructor la clasei de baza
        super(refLink, x,y, width, height);
            //Seteaza pe valorile implicite pentru viata, viteza si distantele de deplasare
        life    = DEFAULT_LIFE;
        speed   = DEFAULT_SPEED;
        xMove   = 0;
        yMove   = 0;
        dy=0;
    }

    /*! \fn public void Move()
        \brief Modifica pozitia caracterului.
     */
    public void Move()
    {
            ///Modifica pozitia caracterului pe axa X.
        MoveX();
            ///Modifica pozitia caracterului pe axa Y.
        MoveY();
            ///Forta gravitationala.
        fall();
    }

    /*! \fn public void MoveX()
        \brief Modifica pozitia caracterului pe axa X.
     */
    public void MoveX()
    {
            ///Aduna la pozitia curenta numarul de pixeli cu care trebuie sa se deplaseze pe axa X.
        if(x+xMove>0) {
            x += xMove;
            if (checkCollisions())
                x -= xMove;
        }
    }

    /*! \fn public void MoveY()
        \brief Modifica pozitia caracterului pe axa Y.
     */
    public void MoveY()
    {
            ///Aduna la pozitia curenta numarul de pixeli cu care trebuie sa se deplaseze pe axa Y.
        if(y+yMove>50) {
            y += yMove;
            if (checkCollisions()) {
                y -= yMove;
            }
        }
    }
    /*! \fn public void jump()
            \brief Modifica pozitia caracterului atunci cand acesta face un salt.
     */
    protected void jump() {
        ///Se face play la sunetul de saritura.
        if(!Sound.isPlaying("Jump") && Game.music)
            Sound.play("Jump");
        ///Se verifica daca se poate sari
        if (isOnFloor()) {
            jumpStrength = 9.5f;
            if (y - jumpStrength > 40) {
                y -= jumpStrength;
                if (isOnTop()) {
                    y += jumpStrength;
                    jumpStrength = 0;
                }
                jumpStrength -= 0.2f;
            }
        }
    }

    /*! \fn public int GetLife()
        \brief Returneaza viata caracterului.
     */
    public int GetLife()
    {
        return life;
    }

    /*! \fn public int GetSpeed()
        \brief Returneaza viteza caracterului.
     */
    public float GetSpeed()
    {
        return speed;
    }

    /*! \fn public void SetLife(int life)
        \brief Seteaza viata caracterului.
        \param life Viata caracterului.
     */
    public void SetLife(int life)
    {
        this.life = life;
    }

    /*! \fn public void SetSpeed(float speed)
        \brief Seteaza viteza caracterului.
        \param speed Viteza caracterului.
     */
    public void SetSpeed(float speed) {
        this.speed = speed;
    }

    /*! \fn public float GetXMove()
        \brief Returneaza distanta in pixeli pe axa X cu care este actualizata pozitia caracterului.
     */
    public float GetXMove()
    {
        return xMove;
    }

    /*! \fn public float GetYMove()
        \brief Returneaza distanta in pixeli pe axa Y cu care este actualizata pozitia caracterului.
     */
    public float GetYMove()
    {
        return yMove;
    }

    /*! \fn public void SetXMove(float xMove)
        \brief Seteaza distanta in pixeli pe axa X cu care va fi actualizata pozitia caracterului.
        \param xMove Distanta in pixeli cu care va fi mutat caracterul pe axa Oy.
     */
    public void SetXMove(float xMove)
    {
        this.xMove = xMove;
    }

    /*! \fn public void SetYMove(float yMove)
        \brief Seteaza distanta in pixeli pe axa Y cu care va fi actualizata pozitia caracterului.
        \param yMove Distanta in pixeli cu care va fi mutat caracterul pe axa Oy.
     */
    public void SetYMove(float yMove)
    {
        this.yMove = yMove;
    }
}

