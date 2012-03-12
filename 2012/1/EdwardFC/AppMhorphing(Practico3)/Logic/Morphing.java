package Logic;

import View.ScreenMain;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.Observable;
import org.apache.log4j.Logger;

/**
 *
 * @author EdwardFC
 */
public class Morphing extends Observable {

    private BufferedImage imageActual;
    private int phases;
    private static Logger logger = Logger.getLogger(ScreenMain.class);

    public Morphing() {
        this.imageActual = new BufferedImage(240, 400, BufferedImage.TYPE_INT_RGB);
        phases = 0;

    }

    public void setImageActual(BufferedImage imageActual) {
        this.imageActual = imageActual;
    }

    public void setPhases(int phases) {
        this.phases = phases;
    }

    public int getPhases() {
        return phases;
    }

    public BufferedImage getImageActual() {
        return imageActual;
    }

    public BufferedImage morphingImage(BufferedImage imageInitial, BufferedImage imageFinal, int k) {
        logger.info("Se resetea la imagenActual para dibujar encima");
        setImageActual(new BufferedImage(240, 400, BufferedImage.TYPE_INT_RGB));
        for (int i = 0; i < imageInitial.getWidth(); i++) {
            for (int j = 0; j < imageFinal.getHeight(); j++) {
                Color img1Color = new Color(imageInitial.getRGB(i, j));
                Color img2Color = new Color(imageFinal.getRGB(i, j));
                int red = img1Color.getRed() + ((img2Color.getRed() - img1Color.getRed()) / phases) * k;
                int green = img1Color.getGreen() + ((img2Color.getGreen() - img1Color.getGreen()) / phases) * k;
                int blue = img1Color.getBlue() + ((img2Color.getBlue() - img1Color.getBlue()) / phases) * k;
                imageActual.setRGB(i, j, new Color(red, green, blue).getRGB());

            }
        }
        setChanged();
        notifyObservers();
        logger.info("Se obtiene el color de cada imagen, componente por componente(RED,GREEN,BLUE)");
        return imageActual;
    }
}
