package PaooGame.Graphics;

import java.awt.*;
import java.awt.image.BufferedImage;
/*! \class Background
    \brief Implementeaza notiunea de background a jocului;
 */
public class Background
{
    private BufferedImage background;      /*!< Obiectul background*/
 /*! \fn Background(String path)
            \brief Constructorul cu parametri al clasei Background

            \param path Calea imaginii.
  */
    public Background(String path) {
        background = ImageLoader.LoadImage(path);
    }
/*! \fn public void Draw(Graphics g)
       \brief Deseneaza background-ul in contextul grafic g.
 */
    public void Draw(Graphics g) {
        g.drawImage(background, 0,0, 960, 672, null);
    }
}