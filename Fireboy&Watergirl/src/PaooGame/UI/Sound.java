package PaooGame.UI;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.util.HashMap;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;


/*! \class Sound
    \brief Implementeaza notiunea de sunet.
 */
public class Sound {

    private static HashMap<String, Clip> clips;
    private static int gap;

    /*! \fn public static void init()
        \brief Cream HashMap-ul in care stocam melodiile.
     */
    public static void init() {
        clips = new HashMap<>();
        gap = 0;
    }
    /*! \fn public static void load(String s, String n)
        \brief Incarca sirul s in locatia cu cheia n a hashmapului
        \param s Calea melodiei.
        \param n Cheia melodiei.
     */
    public static void load(String s, String n) {
        if(clips.get(n) != null) return;
        Clip clip;
        try {
            InputStream in = Sound.class.getResourceAsStream(s);
            InputStream bin = new BufferedInputStream(in);
            AudioInputStream ais = AudioSystem.getAudioInputStream(bin);
            AudioFormat baseFormat = ais.getFormat();
            AudioFormat decodeFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, baseFormat.getSampleRate(), 16, baseFormat.getChannels(), baseFormat.getChannels() * 2, baseFormat.getSampleRate(), false);
            AudioInputStream dais = AudioSystem.getAudioInputStream(decodeFormat, ais);
            clip = AudioSystem.getClip();
            clip.open(dais);
            clips.put(n, clip);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
    /*! \fn public static void  play(String s)
            \brief Play la melodie.
            \param s Cheia melodiei.
     */
    public static void play(String s) {
        play(s, gap);
    }

    /*! \fn public static void  play(String s, int i)
           \brief Play la melodie.
           \param s Cheia melodiei.
           \param i Pasul de play.
    */
    public static void play(String s, int i) {
        Clip c = clips.get(s);
        if(c == null)
            return;
        if(c.isRunning())
            c.stop();
        c.setFramePosition(i);
        while(!c.isRunning())
            c.start();
    }
    /*! \fn public static void  stop(String s)
                \brief Stop la melodie.
                \param s Cheia melodiei.
     */
    public static void stop(String s) {
        if(clips.get(s) == null)
            return;
        if(clips.get(s).isRunning())
            clips.get(s).stop();
    }

    /*! \fn public static void  resumeLoop(String s)
                \brief Replay la bucla.
                \param s Cheia melodiei.
     */
    public static void resumeLoop(String s) {
        Clip c = clips.get(s);
        if(c == null)
            return;
        c.loop(Clip.LOOP_CONTINUOUSLY);
    }

    /*! \fn public static void  loop(String s, int frame, int start, int end)
        \brief Bucla la bucla.
        \param s Cheia melodiei.
        \param frame Freme-ul.
        \param start Pozitia de start a melodiei.
        \param stop  Pozitia de stop a melodiei.

     */
    public static void loop(String s, int frame, int start, int end) {
        Clip c = clips.get(s);
        if(c == null)
            return;
        if(c.isRunning())
            c.stop();
        c.setLoopPoints(start, end);
        c.setFramePosition(frame);
        c.loop(Clip.LOOP_CONTINUOUSLY);
    }

    /*! \fn public static  int getFrames(String s)
                \brief Returneaza frame-ul.
                \param s Cheia melodiei.
     */
    public static int getFrames(String s) {
        return clips.get(s).getFrameLength(); }

    /*! \fn public static void setVolume(String s, float f)
        \brief Seteaza volumul.
        \param s Cheia melodiei.
        \param f Volumul.
     */
    public static void setVolume(String s, float f) {
        Clip c = clips.get(s);
        if(c == null)
            return;
        FloatControl vol = (FloatControl) c.getControl(FloatControl.Type.MASTER_GAIN);
        vol.setValue(f);
    }
    /*! \fn public static boolean isPlaying(String s)
        \brief Returneaza o variabila ce indica daca melodia este pornita.
        \param s Cheia melodiei.
     */
    public static boolean isPlaying(String s) {
        Clip c = clips.get(s);
        if(c == null)
            return false;
        return c.isRunning();
    }
}