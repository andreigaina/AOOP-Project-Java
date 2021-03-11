package PaooGame.Graphics;

import java.awt.image.BufferedImage;

/*! \class public class Assets
    \brief Clasa incarca fiecare element grafic necesar jocului.

    Game assets include tot ce este folosit intr-un joc: imagini, sunete, harti etc.
 */
public class Assets
{
        /// Referinte catre elementele grafice (dale) utilizate in joc.
    public static BufferedImage[] heroLeft;
    public static BufferedImage[] heroRight;
    public static BufferedImage[] heroStand;
    public static BufferedImage heroUp;
    public static BufferedImage heroDown;
    public static BufferedImage[] ice;
    public static BufferedImage grass;
    public static BufferedImage[] play;
    public static BufferedImage[] settings1;
    public static BufferedImage[] about;
    public static BufferedImage[] pause;
    public static BufferedImage[] music;
    public static BufferedImage[] sound;
    public static BufferedImage[] ok;
    public static BufferedImage[] end;
    public static BufferedImage[] resume;
    public static BufferedImage[] retry;
    public static BufferedImage[] settings2;
    public static BufferedImage[] diamonds;
    public static BufferedImage[] obstacle;
    public static BufferedImage cronometer;
    public static BufferedImage[] doors;
    public static BufferedImage[] life;
    public static BufferedImage key;
    /*! \fn public static void Init()
        \brief Functia initializaza referintele catre elementele grafice utilizate.

        Aceasta functie poate fi rescrisa astfel incat elementele grafice incarcate/utilizate
        sa fie parametrizate. Din acest motiv referintele nu sunt finale.
     */
    public static void Init()
    {
            /// Se creaza temporar un obiect SpriteSheet initializat prin intermediul clasei ImageLoader

        SpriteSheet sheet1=new SpriteSheet(ImageLoader.LoadImage("/textures/C2.png"));
        SpriteSheet settings_buttons = new SpriteSheet(ImageLoader.LoadImage("/textures/Settings/icons.png"));
        SpriteSheet dia_cron=new SpriteSheet(ImageLoader.LoadImage("/textures/Settings/cronometer3.png"));

            /// Se obtin subimaginile corespunzatoare elementelor necesare.
        heroLeft = new BufferedImage[8];
        heroRight = new BufferedImage[8];
        heroStand = new BufferedImage[3];
        play=new BufferedImage[2];
        settings1 =new BufferedImage[2];
        about=new BufferedImage[2];
        pause=new BufferedImage[2];
        music=new BufferedImage[2];
        sound=new BufferedImage[2];
        ok=new BufferedImage[2];
        end=new BufferedImage[2];
        resume=new BufferedImage[2];
        retry=new BufferedImage[2];
        settings2=new BufferedImage[2];
        ice=new BufferedImage[5];
        diamonds=new BufferedImage[2];
        obstacle=new BufferedImage[2];
        doors=new BufferedImage[2];
        life =new BufferedImage[2];
        heroStand[0]=sheet1.cropCharacter(0,0);
        heroStand[1]=sheet1.cropCharacter(1,0);
        heroStand[2]=sheet1.cropCharacter(2,0);
        heroUp=sheet1.cropCharacter(3,0);
        heroDown=sheet1.cropCharacter(4,0);
        play[0]=ImageLoader.LoadImage("/textures/play1.png");
        play[1]=ImageLoader.LoadImage("/textures/play.png");
        settings1[0]=ImageLoader.LoadImage("/textures/settings1.png");
        settings1[1]=ImageLoader.LoadImage("/textures/settings.png");
        about[0]=ImageLoader.LoadImage("/textures/about1.png");
        about[1]=ImageLoader.LoadImage("/textures/about.png");
        pause[0]=ImageLoader.LoadImage("/textures/R_Pause.png");
        pause[1]=ImageLoader.LoadImage("/textures/B_Pause.png");
        doors[0]=ImageLoader.LoadImage("/textures/BlueDoor.png");
        sound[0]=settings_buttons.crop(0,0,120,100);
        sound[1]=settings_buttons.crop(120,0,120,100);
        music[1]=settings_buttons.crop(240,0,120,100);
        music[0]=settings_buttons.crop(360,0,120,100);
        ok[0]=settings_buttons.crop(0,100,230,100);
        ok[1]=settings_buttons.crop(1,102,230,100);
        resume[0]=settings_buttons.crop(241,100,240,100);
        resume[1]=settings_buttons.crop(242,102,240,100);
        end[0]=settings_buttons.crop(0,201,230,100);
        end[1]=settings_buttons.crop(1,203,230,100);
        retry[0]=settings_buttons.crop(241,201,240,100);
        retry[1]=settings_buttons.crop(242,203,240,100);
        settings2[0]=settings_buttons.crop(482,0,180,100);
        settings2[1]=settings_buttons.crop(484,1,180,100);
        for(int i=0;i<8;++i)
        {
            heroLeft[i]=sheet1.cropCharacter(i+5,0);
        }
        heroRight[0]=sheet1.cropCharacter(13,0);
        for(int i=1;i<8;++i)
        {
            heroRight[i]=sheet1.cropCharacter(i-1,1);
        }
        ice[0] = sheet1.crop(20, 5+450, 77, 77);
        ice[1]=sheet1.crop(200,5+450,77,77);
        ice[2]=sheet1.rotateImageByDegrees(ice[1],180);
        ice[3]=sheet1.rotateImageByDegrees(ice[1],270);
        ice[4]=sheet1.rotateImageByDegrees(ice[1],90);
        diamonds[0]=dia_cron.crop(0,74,40,37);
        obstacle[0]=dia_cron.crop(4,110,130,23);
        obstacle[1]=dia_cron.crop(4,133,130,23);
        life[0]=ImageLoader.LoadImage("/textures/Heart.png");
        life[1]=ImageLoader.LoadImage("/textures/HalfHeart.png");
        key=ImageLoader.LoadImage("/textures/Key.png");
    }
}
