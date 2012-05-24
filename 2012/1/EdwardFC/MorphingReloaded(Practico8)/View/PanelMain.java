/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.Line;
import Controller.Pile;
import Logic.Morphing;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.*;
import org.apache.log4j.Logger;

/**
 *
 * @author EdwardFC
 */
public class PanelMain extends JPanel implements ActionListener {

    protected JMenuBar bar;
    private JMenu options, help;
    private JMenuItem start, create, sepia, grayscale,efect, restart, credits;
    private Morphing morphing;
    private JButton next;
    private JButton previous;
    private BufferedImage imageInitial;
    private BufferedImage img;
    private BufferedImage imageFinal;
    private BufferedImage fondo;
    private Line<BufferedImage> line;
    private Line<BufferedImage> lineaux;
    private Line<BufferedImage> motion;
    private Pile<BufferedImage> pile;
    private int cont1 = 0;
    private int cont2 = 0;
    private static Logger logger = Logger.getLogger(ScreenMain.class);

    public PanelMain() {
        super();
        setBounds(0, 0, 733, 500);
        setBackground(Color.BLACK);
        morphing = new Morphing();
        line = new Line<BufferedImage>();
        lineaux = new Line<BufferedImage>();
        motion = new Line<BufferedImage>();
        pile = new Pile<BufferedImage>();
        this.imageInitial = new BufferedImage(240, 400, BufferedImage.TYPE_INT_RGB);
        this.imageFinal = new BufferedImage(240, 400, BufferedImage.TYPE_INT_RGB);
        this.img = new BufferedImage(240, 400, BufferedImage.TYPE_INT_RGB);
        this.fondo = new BufferedImage(733, 500, BufferedImage.TYPE_INT_RGB);
        bar = new JMenuBar();
        options = new JMenu("Options");
        help = new JMenu("Help");
        start = new JMenuItem("Start");
        start.addActionListener(this);
        create = new JMenuItem("Create Animation");
        create.addActionListener(this);
        sepia = new JMenuItem("Sepia Effect");
        sepia.addActionListener(this);
        grayscale = new JMenuItem("Gray Scale Effect");
        grayscale.addActionListener(this);
        efect=new JMenuItem("No Effect");
        efect.addActionListener(this);
        restart = new JMenuItem("Restart");
        restart.addActionListener(this);
        credits = new JMenuItem("Credits");
        credits.addActionListener(this);
        previous = new JButton("Back");
        previous.addActionListener(this);
        previous.setBounds(210, 0, 50, 20);
        next = new JButton("Next");
        next.addActionListener(this);
        next.setBounds(480, 0, 50, 20);
        this.add(previous);
        this.add(next);
        options.add(start);
        options.add(create);
        options.add(sepia);
        options.add(grayscale);
        options.add(efect);
        options.add(restart);
        help.add(credits);
        bar.add(options);
        bar.add(help);
        this.add(bar);
        fondo = loadImage("fondo.png");
        create.setVisible(false);
        sepia.setVisible(false);
        grayscale.setVisible(false);
        efect.setVisible(false);

        previous.setEnabled(false);
        next.setEnabled(false);
    }

    public static BufferedImage loadImage(String pathname) {
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File(pathname));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return img;
    }

    public static BufferedImage makeTranslucentImage(BufferedImage bufferedImage, float transparency) {
        BufferedImage bufim = new BufferedImage(bufferedImage.getWidth(), bufferedImage.getHeight(), BufferedImage.TRANSLUCENT);
        Graphics2D g = bufim.createGraphics();
        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, transparency));
        g.drawImage(bufferedImage, null, 0, 0);
        g.dispose();
        return bufim;
    }

    public static void applySepiaFilter(BufferedImage img, int sepiadepth, int sepiaIntensity) {
        // Play around with this. 20 works well and was recommended
        // by another developer. 0 produces black/white image
        int sepiaDepth = sepiadepth;

        int w = img.getWidth();
        int h = img.getHeight();

        WritableRaster raster = img.getRaster();

        // We need 3 integers (for R,G,B color values) per pixel.
        int[] pixels = new int[w * h * 3];
        raster.getPixels(0, 0, w, h, pixels);

        // Process 3 ints at a time for each pixel.
        // Each pixel has 3 RGB colors in array
        for (int i = 0; i < pixels.length; i += 3) {
            int r = pixels[i];
            int g = pixels[i + 1];
            int b = pixels[i + 2];

            int gry = (r + g + b) / 3;
            r = g = b = gry;
            r = r + (sepiaDepth * 2);
            g = g + sepiaDepth;

            if (r > 255) {
                r = 255;
            }
            if (g > 255) {
                g = 255;
            }
            if (b > 255) {
                b = 255;
            }

            // Darken blue color to increase sepia effect
            b -= sepiaIntensity;

            // normalize if out of bounds
            if (b < 0) {
                b = 0;
            }
            if (b > 255) {
                b = 255;
            }

            pixels[i] = r;
            pixels[i + 1] = g;
            pixels[i + 2] = b;
        }
        raster.setPixels(0, 0, w, h, pixels);
    }

    public BufferedImage redimen(BufferedImage bufferedImage) {
        int w = bufferedImage.getWidth();
        int h = bufferedImage.getHeight();
        BufferedImage bufim = new BufferedImage(240, 400, bufferedImage.getType());
        Graphics2D g = bufim.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.drawImage(bufferedImage, 0, 0, 240, 400, 0, 0, w, h, null);
        g.dispose();
        return bufim;
    }

    public String selectImage() {
        String rute = "";
        JFileChooser aFileChooser = new JFileChooser("./");
        int response = aFileChooser.showOpenDialog(aFileChooser);
        if (response != JFileChooser.APPROVE_OPTION) {
            logger.warn("No eligio ningun archivo o algun error se produjo");
        }
        File aFile = aFileChooser.getSelectedFile();
        logger.info("Obtiene el archivo: " + aFile.getName());

        try {
            rute = aFile.toString();
        } catch (Exception err) {
            logger.error("No se pudo cargar la imagen", err);
            JOptionPane.showMessageDialog(null, "Se produjo un error al cargar la imagen");
        }
        return rute;
    }

    public Line<BufferedImage> getMotion() {
        return motion;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponents(g);
        g.drawImage(fondo, 0, 0, this);
        g.drawImage(imageInitial, 0, 40, this);
        g.drawImage(img, 242, 40, this);
        g.drawImage(imageFinal, 484, 40, this);

    }

    public void filler(Line<BufferedImage> charger) {
        while (charger.size() <= morphing.getPhases()) {
            charger.push(morphing.morphingImage(imageInitial, imageFinal, (charger.size())));
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (start.equals(e.getSource())) {
            BufferedImage img1 = loadImage(selectImage());
            imageInitial = redimen(img1);
            repaint();
            BufferedImage img2 = loadImage(selectImage());
            imageFinal = redimen(img2);
            repaint();
            Thread begin = new Thread(new Runnable() {

                @Override
                public void run() {
                    logger.info(
                            "Se dio el numero de nodos para empezar la transformaciÃ³n");
                    String x = JOptionPane.showInputDialog("Ingrese la cantidad de Nodos");
                    int nodo = 0;
                    boolean valid = true;

                    if (x == null) {
                        valid = false;
                    } else {
                        try {
                            nodo = Integer.parseInt(x);
                        } catch (NumberFormatException e) {
                            valid = false;
                            JOptionPane.showMessageDialog(null, "Ingrese un numero valido");
                            logger.info("Se ingreso un caracter no numerico");
                        }

                        if (valid) {
                            morphing.setPhases(nodo);
                        } else {
                            this.run();
                        }
                    }
                    start.setEnabled(false);
                    filler(line);
                    create.setVisible(true);
                    sepia.setVisible(true);
                    grayscale.setVisible(true);
                    efect.setVisible(true);

                }
            });
            begin.start();
        }

        if (next.equals(e.getSource())) {
            create.setVisible(false);
            sepia.setVisible(false);
            grayscale.setVisible(false);
            efect.setVisible(false);
            lineaux = new Line<BufferedImage>();
            filler(lineaux);
            for (int i = 0; i < cont2; i++) {
                pile.push(lineaux.pop());
            }
            if (line.size() > 0) {
                img = line.pop();
                pile.push(img);
                cont1++;
                System.out.println(cont1);
                repaint();
                previous.setEnabled(true);
            } else {
                next.setEnabled(false);

            }

            logger.info("Se llama al metodo pop de la cola");
        }
        if (previous.equals(e.getSource())) {
            lineaux = new Line<BufferedImage>();
            filler(lineaux);
            line = new Line<BufferedImage>();
            for (int i = 0; i < pile.size(); i++) {
                if (lineaux.size() < pile.size()) {
                    lineaux.pop();
                }
            }
            if (lineaux.size() > 0) {
                line = lineaux;
            }
            if (pile.size() > 0) {
                img = pile.pop();
                cont1--;
                cont2 = cont1;
                System.out.println(cont2);
                repaint();

                next.setEnabled(true);
            } else {
                previous.setEnabled(false);

            }
            logger.info("Se llamó al método pop de la Pila");
        }
        if (restart.equals(e.getSource())) {
            Thread reset = new Thread(new Runnable() {

                @Override
                public void run() {
                    img = new BufferedImage(240, 400, BufferedImage.TYPE_INT_RGB);
                    imageInitial = new BufferedImage(240, 400, BufferedImage.TYPE_INT_RGB);
                    imageFinal = new BufferedImage(240, 400, BufferedImage.TYPE_INT_RGB);
                    repaint();
                    start.setEnabled(true);
                    create.setVisible(false);
                    sepia.setVisible(false);
                    grayscale.setVisible(false);
                    previous.setEnabled(false);
                    next.setEnabled(false);
                }
            });
            reset.start();
            logger.info("Se reinicia la aplicación");
        }

        if (credits.equals(e.getSource())) {
            JOptionPane.showMessageDialog(null, "Developed by Eduardo Flores C."
                    + "\n      eduardofc24@gmail.com" + "\n                    May. 2012");
            logger.info("Se llamó a los créditos");
        }
        if (create.equals(e.getSource())) {
            JFrame window = new JFrame("Method Morphing");
            window.setSize(240, 400);
            Line<BufferedImage> xx = new Line<BufferedImage>();
            this.filler(xx);
            PanelMovement panel = new PanelMovement(xx);
            window.add(panel);
            Thread mo = new Thread(panel);
            mo.start();
            window.setVisible(true);
            window.setLocationRelativeTo(null);
            window.setResizable(false);
            window.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        }
        if (sepia.equals(e.getSource())) {
            String x = JOptionPane.showInputDialog("Elija cual imagen modificar"
                    + "\n 1   Imagen Inicial"
                    + "\n 2   Imagen Final");
            x.trim();
            previous.setEnabled(true);
            next.setEnabled(true);
            if (x.equals("1")) {
                this.applySepiaFilter(imageInitial, 20, 10);
                repaint();
            }
            if (x.equals("2")) {
                this.applySepiaFilter(imageFinal, 20, 10);
                repaint();
            }
            line = new Line<BufferedImage>();
            this.filler(line);
        }
        if (grayscale.equals(e.getSource())) {
            String x = JOptionPane.showInputDialog("Elija cual imagen modificar"
                    + "\n 1   Imagen Inicial"
                    + "\n 2   Imagen Final");
            x.trim();
            previous.setEnabled(true);
            next.setEnabled(true);
            if (x.equals("1")) {
                this.applySepiaFilter(imageInitial, 0, 10);
                repaint();
            }
            if (x.equals("2")) {
                this.applySepiaFilter(imageFinal, 0, 10);
                repaint();
            }
            line = new Line<BufferedImage>();
            this.filler(line);
        }
        if(efect.equals(e.getSource())){
            previous.setEnabled(true);
            next.setEnabled(true);
        }

    }
}
