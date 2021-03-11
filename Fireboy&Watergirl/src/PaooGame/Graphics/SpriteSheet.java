package PaooGame.Graphics;

import javafx.scene.shape.TriangleMesh;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

/*! \class public class SpriteSheet
    \brief Clasa retine o referinta catre o imagine formata din dale (sprite sheet)

    Metoda crop() returneaza o dala de dimensiuni fixe (o subimagine) din sprite sheet
    de la adresa (x * latimeDala, y * inaltimeDala)
 */
public class SpriteSheet
{
    private BufferedImage       spriteSheet;        /*!< Referinta catre obiectul BufferedImage ce contine sprite sheet-ul.*/
    private static final int    tileWidth   = 48;   /*!< Latimea unei dale din sprite sheet.*/
    private static final int    tileHeight  = 48;   /*!< Inaltime unei dale din sprite sheet.*/

    /*! \fn public SpriteSheet(BufferedImage sheet)
        \brief Constructor, initializeaza spriteSheet.

        \param buffImg Un obiect BufferedImage valid.
     */
    public SpriteSheet(BufferedImage buffImg)
    {
            /// Retine referinta catre BufferedImage object.
        spriteSheet = buffImg;
    }

    /*! \fn public BufferedImage cropCharacter(int x, int y)
        \brief Returneaza un obiect BufferedImage ce contine o subimagine (dala).

        Subimaginea este localizata avand ca referinta punctul din stanga sus.

        \param x numarul dalei din sprite sheet pe axa x.
        \param y numarul dalei din sprite sheet pe axa y.

     */

    public BufferedImage cropCharacter(int x, int y)
    {
        /// Subimaginea (dala) este regasita in sprite sheet specificad coltul stanga sus
        /// al imaginii si apoi latimea si inaltimea (totul in pixeli). Coltul din stanga sus al imaginii
        /// se obtine inmultind numarul de ordine al dalei cu dimensiunea in pixeli a unei dale.
        return spriteSheet.getSubimage(x*151, y*154, 151, 154);
    }
    /*! \fn public BufferedImage crop(int x, int y, int width, int height )
            \brief Returneaza un obiect BufferedImage ce contine o subimagine (dala).

            \param x coltul stanga sus al dalei din sprite sheet pe axa x.
            \param y coltul stanga sus al dalei din sprite sheet pe axa y.
            \param width latimea dalei din sprite sheet.
            \param y height inaltimea dalei din sprite sheet.
         */
    public BufferedImage crop(int x, int y, int width, int height)
    {
        /// Subimaginea (dala) este regasita in sprite sheet specificad coltul stanga sus
        /// al imaginii si apoi latimea si inaltimea (totul in pixeli).
        return spriteSheet.getSubimage(x,y, width,height);
    }
    /*! \fn public BufferedImage rotateImageByDegrees(BufferedImage img, double angle)
            \brief Returneaza un obiect BufferedImage ce contine imaginea
            \brief primita ca parametru dar rotita cu un anumit numar de grade.

            \param img Imaginea primita ca parametru.
            \param angle Unghiul de rotire.
         */
    public BufferedImage rotateImageByDegrees(BufferedImage img, double angle) {
        /// Imaginea este rotita cu ajutorul unor formule matematice deduse din calcule.
        ///Aceasta clasa este inspirata si din surse externe.
        double rads = Math.toRadians(angle);
        double sin = Math.abs(Math.sin(rads)), cos = Math.abs(Math.cos(rads));
        int w = img.getWidth();
        int h = img.getHeight();
        int newWidth = (int) Math.floor(w * cos + h * sin);
        int newHeight = (int) Math.floor(h * cos + w * sin);
        BufferedImage rotated = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = rotated.createGraphics();
        AffineTransform at = new AffineTransform();
        at.translate((newWidth - w) / 2, (newHeight - h) / 2);
        int x = w / 2;
        int y = h / 2;
        at.rotate(rads, x, y);
        g2d.setTransform(at);
        g2d.drawImage(img, 0, 0, null);
        g2d.setColor(Color.RED);
        //g2d.drawRect(0, 0, newWidth - 1, newHeight - 1);
        g2d.dispose();
        return rotated;
    }

}
