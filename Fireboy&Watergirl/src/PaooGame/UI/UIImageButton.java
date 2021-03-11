package PaooGame.UI;

import java.awt.*;
import java.awt.image.BufferedImage;

/*! \class UIImageButton extends UIObject
    \brief Implementeaza notiunea de imagine pentru buton.
 */
public class UIImageButton extends UIObject {
    private ClickListener clicker;   /*!< Click listener.*/
    private BufferedImage[] images;  /*!< Imagine pentru buton.*/

    /*! \fn public  UIImageButton(float x, float y, int width, int height,BufferedImage[] images,ClickListener clicker)
        \brief Constructorul de initializare al clasei.
        \param x Coordonata pe axa Ox.
        \param y Coordonata pe axa Oy.
        \param width Latimea imaginii.
        \param width Inaltimea imaginii.
        \param images Imaginea pentru buton.
        \param clicker Click listener.
    */
    public UIImageButton(float x, float y, int width, int height,BufferedImage[] images,ClickListener clicker) {
        super(x, y, width, height);
        this.images=images;
        this.clicker=clicker;
    }

    /*! \fn  public  void Update()
       \brief Actualizeaza imaginea butoanelor.
     */
    @Override
    public void update() {

    }

    /*! \fn  public void draw(Graphics g)
        \brief  Deseneaza butoanele.
        \param g Contextul grafic in care trebuie sa deseneze enitatea  pe ecran.
     */
    @Override
    public void draw(Graphics g) {
        if (images != null) {
            if (hovering) {
                g.drawImage(images[0], (int) x, (int) y, width, height, null);
            }
            else {
                g.drawImage(images[1], (int) x, (int) y, width, height, null);
            }
            //g.setColor(Color.red);
            //g.fillRect((int)(this.x ), (int)(this.y ), this.width, this.height);
        }
    }

    /*! \fn  public abstract void onClick();
        \brief Se va defini o actiune in momentul in care apasam click.
     */
    @Override
    public void onClick() {
        Sound.play("Selected");
        clicker.onClick();
    }
}
