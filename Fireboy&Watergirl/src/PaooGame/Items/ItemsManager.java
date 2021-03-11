package PaooGame.Items;

import PaooGame.RefLinks;
import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;

    /*! \class public ItemsManager
        \brief Defineste notiunea de manager de entitati in joc.
    */
public class ItemsManager {
    private RefLinks handler;          /*!< Referinta catre un obiect a carui sarcina este doar de a retine diverse referinte pentru a fi usor accesibile.*/
    private WaterGirl waterGirl;       /*!< Player de tip WaterGirl.*/
    private ArrayList<Item> entities;  /*!< Array de entitati din joc specifice fiecarui nivel.*/
    private Comparator<Item> comparator = (e1, e2) -> {
        if (e1.GetWidth() < e2.GetWidth())
            return -1;
        return 1;
    }; /*!< Variabila de tip Comparator.*/

    /*! \fn public ItemsManager(RefLinks reflink,WaterGirl waterGirl)
        \brief Constructor de initializare al clasei ItemsManager.

        \param reflink Referinte "shortcut" catre alte referinte.
        \param waterGirl Entitate de tip personaj principal.

     */
    public ItemsManager(RefLinks reflink,WaterGirl waterGirl){
        this.handler = reflink;
        this.waterGirl = waterGirl;
        entities = new ArrayList<>();
        addEntity(waterGirl);
    }
    /*! \fn  public void Update()
        \brief Actualizeaza starea entitatilor din joc.
     */
    public void Update(){
        for(int i=0;i<entities.size();++i) {
            Item e =entities.get(i);
            e.Update(); if (!e.isActive()) {
                entities.remove(e);
            }
        }
        entities.sort(comparator);
    }
    /*! \fn public void Draw(Graphics g)
        \brief Deseneaza entitatile grafice in fereastra jocului, coresponzator starilor actualizate ale elementelor.

        \param g Contextul grafic in care se deseneaza.
     */
    public void Draw(Graphics g){
        for (Item e : entities) {
            e.Draw(g);
        }
    }
    /*! \fn public void addEntity(Item e)
        \brief Functie prin care se adauga entitati in joc.
        \param e Entitatea adaugata la array.
     */
    public void addEntity(Item e){
        entities.add(e);
    }


    /*! \fn public  RefLinks getHandler()
        \brief Functie prin care se obtine o referinta la ItemManager.
     */
    public RefLinks getHandler() {
        return handler;
    }
    /*! \fn public  WaterGirl getWaterGirl()
        \brief Functie prin care se obtine o referinta la playerul de tip waterGirl.
     */
    public WaterGirl getWaterGirl() {
        return waterGirl;
    }
    /*! \fn public  ArrayList<Item> getEntities()
        \brief Functie prin care se obtine array-ul de entitati.
     */
    public ArrayList<Item> getEntities() {
        return entities;
    }
}

