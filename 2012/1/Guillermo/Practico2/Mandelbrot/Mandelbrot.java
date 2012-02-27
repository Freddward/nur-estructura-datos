/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Mandelbrot;

/**
 *
 * @author G y M
 */

/*<applet code="Mandel6.class" ancho=297 alto=297></applet>*/
import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public final class Mandelbrot extends Applet implements MouseListener, MouseMotionListener, KeyListener, Runnable {
  private int MaxNumero = 192; // numero maximo de interacciones
  private int fractal = 0; // fractal actual
  private boolean suavisar = false; // suavisar el estado
  private boolean antialias = false; // estado antialias o sea para tener una imagen de mejor calidad o de alta resolucion
  private boolean toDrag = false; // arrastrar mouse
  private boolean recta = true; // Zoom o cuando se mueve el modo de arrastre del raton
  private Color[][] colores; // paletas o los colores de las paletas
  private int paleta = 0; // paleta actual o colores actuales
  // variables del fractal2(otro tipo de fractal)
  private boolean fractal2 = false;
  private double fractal2X = 0.56667, fractal2Y = -0.5;
  private boolean vistaPrevia = false;
  private double vistaPreviaX, viataPreviaY;
  private boolean dibujarTodo = true;
  private boolean dibujarVistaPrevia = false;
  // dimensiones relativas actualmente visibles para la ventana
  private double vistaX = 0.0;
  private double vistaY = 0.0;
  private double zoom = 1.0;

  private Image image; // imagen fuera de pantalla para el doble búfer
  private Graphics graphics; // fuera de la pantalla de gráficos para la imagen fuera de pantalla
  private int ancho, alto; // ancho de la pantalla y la altura
  private Image vistaPreviaImagen;
  private Graphics vistaPreviaPintado;
  private Image bufferImagen;
  private Graphics bufferGraphics;

  private Thread thread = null;

  private int mouseX, mouseY; // posición del ratón cuando se presionó el botón
  private int arrastrarX, arrastrarY; // posición actual del ratón durante el arrastre
  
  private static Logger logger = Logger.getLogger(Mandelbrot.class);
  
  private static final int[][][] colorPaleta = { // Colores de la paleta
    { {12, 0, 10, 20}, {12, 50, 100, 240}, {12, 20, 3, 26}, {12, 230, 60, 20},
      {12, 25, 10, 9}, {12, 230, 170, 0}, {12, 20, 40, 10}, {12, 0, 100, 0},
      {12, 5, 10, 10}, {12, 210, 70, 30}, {12, 90, 0, 50}, {12, 180, 90, 120},
      {12, 0, 20, 40}, {12, 30, 70, 200} },
    { {10, 70, 0, 20}, {10, 100, 0, 100}, {14, 255, 0, 0}, {10, 255, 200, 0} },
    { {8, 40, 70, 10}, {9, 40, 170, 10}, {6, 100, 255, 70}, {8, 255, 255, 255} },
    { {12, 0, 0, 64}, {12, 0, 0, 255}, {10, 0, 255, 255}, {12, 128, 255, 255}, {14, 64, 128, 255} },
    { {16, 0, 0, 0}, {32, 255, 255, 255} },
  };

  public void init() {
       // System.out.println(image);
    addMouseListener(this);
    addMouseMotionListener(this);
    addKeyListener(this);
    // inicializar el color de las paletas
    
    logger.debug("inicializando el color de las paletas "+image);
    
    colores = new Color[colorPaleta.length][];
    
    logger.debug("obteniendo el numero de todos los colores");
    for (int p = 0; p < colorPaleta.length; p++) { // procesando todas las paletas
      int n = 0;
      for (int i = 0; i < colorPaleta[p].length; i++) // obtiene el numero de todos los colores
        n += colorPaleta[p][i][0];
      colores[p] = new Color[n]; // asignacion de la paleta
      n = 0;
      for (int i = 0; i < colorPaleta[p].length; i++) { // interpolarizar todos los colores
        int[] c1 = colorPaleta[p][i]; // primera referencia de color
        int[] c2 = colorPaleta[p][(i + 1) % colorPaleta[p].length]; // segunda referencia de color
        for (int j = 0; j < c1[0]; j++) // linea de interpolarizado de todos los valores de los colores
          colores[p][n + j] = new Color(
              (c1[1] * (c1[0] - 1 - j) + c2[1] * j) / (c1[0] - 1),
              (c1[2] * (c1[0] - 1 - j) + c2[2] * j) / (c1[0] - 1),
              (c1[3] * (c1[0] - 1 - j) + c2[3] * j) / (c1[0] - 1));
        n += c1[0];
      }
    }
    thread = null;
  }

  public void start() {
    redraw(false);
  }

  public void destroy() {
    Thread t = thread;
    thread = null;
    t.interrupt();
  }

  public void run() {
    while (thread != null) {
      while (draw() || Thread.interrupted());
      synchronized (this) {
        try {
          wait();
        }
        catch (InterruptedException e) {}
      }
    }
  }

  private void redraw(boolean preview) {
    markDraw(preview);
    if (thread != null && thread.isAlive()) {
      thread.interrupt();
    }
    else {
      thread = new Thread(this);
      thread.setPriority(Thread.MIN_PRIORITY);
      thread.start();
    }
  }

  private synchronized boolean toDraw(boolean vistaPrevia) {
    boolean f = vistaPrevia ? dibujarVistaPrevia : dibujarTodo;
    if (vistaPrevia)
      dibujarVistaPrevia = false;
    else
      dibujarTodo = false;
    return f;
  }

  private synchronized void markDraw(boolean preview) {
    if (preview)
      dibujarVistaPrevia = true;
    else
      dibujarTodo = true;
  }

  private synchronized Color getColor(int i) {
    int palSize = colores[paleta].length;
    if (i + palSize < 0)
      i = Math.max(0, i + palSize);
    return colores[paleta][(i + palSize) % palSize];
  }

  private synchronized void nextPalette() {
    paleta = (paleta + 1) % colores.length;
  }

  private static final int[][] rows = {
    {0, 16, 8}, {8, 16, 8}, {4, 16, 4}, {12, 16, 4},
    {2, 16, 2}, {10, 16, 2}, {6, 16, 2}, {14, 16, 2},
    {1, 16, 1}, {9, 16, 1}, {5, 16, 1}, {13, 16, 1},
    {3, 16, 1}, {11, 16, 1}, {7, 16, 1}, {15, 16, 1},
  };

  private boolean draw() {
    if (dibujoPrevio())
      return true;
    if (!toDraw(false))
      return false;
    Dimension size = getSize();
    // crear buffer fuera de pantalla para el doble búfer
    if (image == null || size.width != ancho || size.height != alto) {
      ancho = size.width;
      logger.debug("obteniendo el ancho "+ancho);
      alto = size.height;
      logger.debug("obteniendo el alto "+alto);
      image = createImage(ancho, alto);
      logger.debug("creando la imagen "+image);
      graphics = image.getGraphics();
      vistaPreviaImagen = createImage(ancho / 4, alto / 4);
      vistaPreviaPintado = vistaPreviaImagen.getGraphics();
      bufferImagen = createImage(ancho, alto);
      bufferGraphics = bufferImagen.getGraphics();
    }
    // fractal de imágenes pre-dibujados
    double r = zoom / Math.min(ancho, alto);
    double sx = ancho > alto ? 2.0 * r * (ancho - alto) : 0.0;
    double sy = alto > ancho ? 2.0 * r * (alto - ancho) : 0.0;
    for (int y = 0; y < alto + 4; y += 8) {
      if ((dibujoPrevio() || Thread.interrupted()) && dibujarTodo)
        return true;
      for (int x = 0; x < ancho + 4; x += 8) {
        double dx = 4.0 * (x * r + vistaX) - 2.0 - sx;
        double dy = -4.0 * (y * r + vistaY) + 2.0 + sy;
        Color color = color(dx, dy);
        graphics.setColor(color);
        graphics.fillRect(x - 4, y - 4, 8, 8);
      }
    }
    repaint();
    // dibujo de la imagen fractal
    logger.debug("Dibujo de la imagen fractal ");
    for (int row = 0; row < rows.length; row++) {
      for (int y = rows[row][0]; y < alto; y += rows[row][1]) {
        if ((dibujoPrevio() || Thread.interrupted()) && dibujarTodo)
          return true;
        for (int x = 0; x < ancho; x++) {
          double dx = 4.0 * (x * r + vistaX) - 2.0 - sx;
          double dy = -4.0 * (y * r + vistaY) + 2.0 + sy;
          Color color = color(dx, dy);
          // cómputo de color medio para el anti-aliasing
          if (antialias) {
            Color c1 = color(dx - 0.25 * r, dy - 0.25 * r);
            Color c2 = color(dx + 0.25 * r, dy - 0.25 * r);
            Color c3 = color(dx + 0.25 * r, dy + 0.25 * r);
            Color c4 = color(dx - 0.25 * r, dy + 0.25 * r);
            int red = (color.getRed() + c1.getRed() + c2.getRed() + c3.getRed() + c4.getRed()) / 5;
            int green = (color.getGreen() + c1.getGreen() + c2.getGreen() + c3.getGreen() + c4.getGreen()) / 5;
            int blue = (color.getBlue() + c1.getBlue() + c2.getBlue() + c3.getBlue() + c4.getBlue()) / 5;
            color = new Color(red, green, blue);
          }
          graphics.setColor(color);
          graphics.fillRect(x, y - rows[row][2] / 2, 1, rows[row][2]);
        }
      }
      repaint();
    }
    return toDraw(false);
  }

  private boolean dibujoPrevio() {
    if (fractal2 || !vistaPrevia || !toDraw(true))
      return false;
    boolean interrupted = false;
    // fractal de imágenes pre-dibujadas
    int width = this.ancho / 3;
    int height = this.alto / 3;
    double r = 1.0 / Math.min(width, height);
    double sx = width > height ? 2.0 * r * (width - height) : 0.0;
    double sy = height > width ? 2.0 * r * (height - width) : 0.0;
    for (int y = 0; y < height + 2; y += 4) {
      if (Thread.interrupted()) {
        if (dibujarVistaPrevia)
          return true;
        interrupted = true;
      }
      for (int x = 0; x < width + 2; x += 4) {
        double dx = 4.0 * x * r - 2.0 - sx;
        double dy = -4.0 * y * r + 2.0 + sy;
        Color color = getColor(zfun(dx, dy, vistaPreviaX, viataPreviaY) / 256);
        vistaPreviaPintado.setColor(color);
        vistaPreviaPintado.fillRect(x - 2, y - 2, 4, 4);
      }
    }
    repaint();
    if ((interrupted || Thread.interrupted()) && (dibujarVistaPrevia || dibujarTodo))
      return true;
    // dibujo de la imagen fractal
    logger.debug("Dibujo de la imagen fractal ");
    for (int y = 0; y < height; y++) {
      if (Thread.interrupted()) {
        if (dibujarVistaPrevia)
          return true;
        interrupted = true;
      }
      for (int x = 0; x < width; x++) {
        double dx = 4.0 * x * r - 2.0 - sx;
        double dy = -4.0 * y * r + 2.0 + sy;
        Color color = getColor(zfun(dx, dy, vistaPreviaX, viataPreviaY) / 256);
        vistaPreviaPintado.setColor(color);
        vistaPreviaPintado.fillRect(x, y, 1, 1);
      }
    }
    repaint();
    return interrupted;
  }

  // Calcula el color de un punto dado
  
  private Color color(double x, double y) {
    int count = fractal2 ? zfun(x, y, fractal2X, fractal2Y): zfun(0.0, 0.0, x, y);
    Color color = getColor(count / 256);
    if (suavisar) {
      Color color2 = getColor(count / 256 - 1);
      int k1 = count % 256;
      int k2 = 255 - k1;
      int red = (k1 * color.getRed() + k2 * color2.getRed()) / 255;
      int green = (k1 * color.getGreen() + k2 * color2.getGreen()) / 255;
      int blue = (k1 * color.getBlue() + k2 * color2.getBlue()) / 255;
      color = new Color(red, green, blue);
    }
    return color;
  }

  private int zfun(double zr, double zi, double cr, double ci) {
    switch (fractal) {
     case 1:
      return phoenix(zr, zi, cr, ci);
     default:
      return mandel(zr, zi, cr, ci);
    }
  }

  // Calcula un valor para un número dado complejo
  
  private int mandel(double zr, double zi, double cr, double ci) {
    double pr = zr*zr, pi = zi*zi;
    double zm = 0.0;
    int count = 0;
    while (pr + pi < 4.0 && count < MaxNumero) {
      zm = pr + pi;
      zi = 2.0*zr*zi + ci;
      zr = pr - pi + cr;
      pr = zr*zr;
      pi = zi*zi;
      count++;
    }
    if (count == 0 || count == MaxNumero)
      return 0;
    zm += 0.000000001;
    return 256 * count + (suavisar ? (int)(255.0 * Math.log(4.0 / zm) / Math.log((pr + pi) / zm)) : 0);
  }

  // Calcula un valor para un número dado complejo
  private int phoenix(double zr, double zi, double cr, double ci) {
    double pr = zr*zr, pi = zi*zi;
    double sr = 0.0, si = 0.0;
    double zm = 0.0;
    int count = 0;
    while (pr + pi < 4.0 && count < MaxNumero) {
      zm = pr + pi;
      pr = pr - pi + ci*sr + cr;
      pi = 2.0*zr*zi + ci*si;
      sr = zr;
      si = zi;
      zr = pr;
      zi = pi;
      pr = zr*zr;
      pi = zi*zi;
      count++;
    }
    if (count == 0 || count == MaxNumero)
      return 0;
    zm += 0.000000001;
    return 256 * count + (suavisar ? (int)(25.0 * Math.log(4.0 / zm) / Math.log((pr + pi) / zm)) : 0);
  }

  // Para evitar compensación de fondo de cada paint()
    @Override
  public void update(Graphics g) {
    paint(g);
  }

  public void paint(Graphics g) {
    if (image == null) // nada que mostrar
      return;
    Dimension size = getSize();
    if (size.width != ancho || size.height != alto) {
      redraw(false);
      return;
    }
    bufferGraphics.drawImage(image, 0, 0, null);
    if (vistaPrevia) {
      if (!fractal2)
        bufferGraphics.drawImage(vistaPreviaImagen, 0, 0, null);
      bufferGraphics.setColor(Color.white);
      bufferGraphics.drawString("x:" + vistaPreviaX + "  y:" + viataPreviaY, 0, alto - 5);
    }
    g.drawImage(bufferImagen, 0, 0, null);
    // selecciona-rectángulo o un dibujo de línea de desplazamiento
    if (toDrag) {
      g.setColor(Color.black);
      g.setXORMode(Color.white);
      if (recta) {
        int x = Math.min(mouseX, arrastrarX);
        int y = Math.min(mouseY, arrastrarY);
        double w = mouseX + arrastrarX - 2 * x;
        double h = mouseY + arrastrarY - 2 * y;
        double r = Math.max(w / ancho, h / alto);
        g.drawRect(x, y, (int)(ancho * r), (int)(alto * r));
      }
      else
        g.drawLine(mouseX, mouseY, arrastrarX, arrastrarY);
    }
  }

  // los métodos de la interfaz MouseListener

    @Override
  public void mousePressed(MouseEvent e) {
    mouseX = arrastrarX = e.getX();
    mouseY = arrastrarY = e.getY();
    toDrag = true;
  }

    @Override
  public void mouseReleased(MouseEvent e) {
    toDrag = false;
    int x = e.getX();
    int y = e.getY();
    if ((e.getModifiers() & InputEvent.BUTTON1_MASK) != 0) { 
      double r = zoom / Math.min(ancho, alto); // tamaño de píxel en relación
      if (vistaPrevia) { // fractal2
        fractal2 = !fractal2;
        double sx = ancho > alto ? ancho - alto : 0.0;
        double sy = alto > ancho ? alto - ancho : 0.0;
        fractal2X = 4.0 * (r * (mouseX - sx / 2.0) + vistaX) - 2.0;
        fractal2Y = -4.0 * (r * (mouseY - sy / 2.0) + vistaY) + 2.0;
        redraw(false); // volver a calcular y volver a pintar
      }
      else if (!recta) { // moved
        vistaX += (mouseX - x) * r;
        vistaY += (mouseY - y) * r;
        redraw(false); // volver a calcular y volver a pintar
      }
      else if (x != mouseX || y != mouseY) { // ampliar
          logger.debug("ampliando... ");
        int mx = Math.min(x, mouseX);
        int my = Math.min(y, mouseY);
        double sx = ancho > alto ? ancho - alto : 0.0;
        double sy = alto > ancho ? alto - ancho : 0.0;
        vistaX += r * (mx - sx / 2.0);
        vistaY += r * (my - sy / 2.0);
        double w = x + mouseX - 2 * mx;
        double h = y + mouseY - 2 * my;
        double zoom0 = zoom;
        zoom *= Math.max(w / ancho, h / alto);
        vistaX += r / zoom0 * zoom * sx / 2.0;
        vistaY += r / zoom0 * zoom * sy / 2.0;
        redraw(false); // volver a calcular y volver a pintar
      }
    }
    else if ((e.getModifiers() & InputEvent.BUTTON3_MASK) != 0) { // RMB
      if (MaxNumero < 1000000000);
      MaxNumero += MaxNumero / 4; // incrementa el numero de interacciones en 1/4
      redraw(false); // volver a calcular y volver a pintar
    }
  }

    @Override
  public void mouseClicked(MouseEvent e) {} // no se utiliza
  public void mouseEntered(MouseEvent e) {} // no se utiliza
  public void mouseExited(MouseEvent e) {} // no se utiliza

  // los métodos de la interfaz MouseMotionListener

    @Override
  public void mouseDragged(MouseEvent e) {
    if ((e.getModifiers() & InputEvent.BUTTON1_MASK) != 0) { 
      arrastrarX = e.getX();
      arrastrarY = e.getY();
      repaint(); // sólo repintar - sin volver a calcular 
    }
  }

  public void mouseMoved(MouseEvent e) {
    if (vistaPrevia) {
      int x = e.getX();
      int y = e.getY();
      double r = zoom / Math.min(ancho, alto); // tamaño de píxel en relación
      double sx = ancho > alto ? ancho - alto : 0.0;
      double sy = alto > ancho ? alto - ancho : 0.0;
      vistaPreviaX = 4.0 * (r * (x - sx / 2.0) + vistaX) - 2.0;
      viataPreviaY = -4.0 * (r * (y - sy / 2.0) + vistaY) + 2.0;
      if (fractal2)
        repaint();
      else
        redraw(true);
    }
  }

  // los métodos de la interfaz KeyListener

    @Override
  public void keyPressed(KeyEvent e) {
        
    if (e.getKeyCode() == KeyEvent.VK_ESCAPE) { // corre el init
      logger.debug("Volviendo al inicio ");
      MaxNumero = 192;
      vistaX = vistaY = 0.0;
      zoom = 1.0;
      redraw(false); // recompute and repaint
    }
    else if (e.getKeyCode() == KeyEvent.VK_SPACE) { // siguiente fractal
        logger.debug("Cambiando al segundo fractal ");
      fractal = (fractal + 1) % 2;
      vistaX = vistaY = 0.0;
      zoom = 1.0;
      fractal2 = false;
      if (fractal == 1) {
        fractal2X = 0.56667;
        fractal2Y = -0.5;
        fractal2 = true;
      }
      redraw(false); // volver a calcular y volver a pintar
    }
    else if (e.getKeyCode() == KeyEvent.VK_I) { // ampliar
        logger.debug("Ampliando la imagen ");
      vistaX += 0.25 * zoom;
      vistaY += 0.25 * zoom;
      zoom *= 0.5;
      redraw(false); // volver a calcular y volver a pintar
    }
    else if (e.getKeyCode() == KeyEvent.VK_O) { // alejar la imagen
        logger.debug("Alejando la imagen ");
      vistaX -= 0.5 * zoom;
      vistaY -= 0.5 * zoom;
      zoom *= 2.0;
      redraw(false); // volver a calcular y volver a pintar
    }
    else if (e.getKeyCode() == KeyEvent.VK_J) { // fractal2
        logger.debug("cambiando al fractal 2 ");
      fractal2 = !fractal2;
      redraw(false); // volver a calcular y volver a pintar
    }
    else if (e.getKeyCode() == KeyEvent.VK_P) { // siguiente paleta
        logger.debug("Cambiando la paleta de colores ");
      nextPalette();
      redraw(false); // volver a calcular y volver a pintar
    }
    else if (e.getKeyCode() == KeyEvent.VK_S) { // suavizar
        logger.debug("Suavizando la imagen ");
      suavisar = !suavisar;
      redraw(false); // volver a calcular y volver a pintar
    }
    else if (e.getKeyCode() == KeyEvent.VK_A) { // antialiasing o mejora de la imagen
      antialias = !antialias;
      redraw(false); // volver a calcular y volver a pintar
    }
    else if (e.getKeyCode() == KeyEvent.VK_SHIFT) { // para mover la imagen
        logger.debug("Mviendo la imagen ");
      recta = false; // desplazamiento de línea (no selección de rectángulo)
      if (toDrag) // repintar solamente cuando se realiza arrastrando
        repaint(); // sólo repintar - sin volver a calcular
    }
    else if (e.getKeyCode() == KeyEvent.VK_CONTROL) { // modo vistaPrevia
      if (!vistaPrevia) {
        vistaPrevia = true;
        redraw(true);
      }
    }
  }

  public void keyReleased(KeyEvent e) {
    if (e.getKeyCode() == KeyEvent.VK_SHIFT) { // modo zoom
      recta = true; // la selección de rectángulo (la línea no compensada)
      if (toDrag) // repintar solamente cuando se realiza arrastrando
        repaint(); // sólo repintar - sin volver a calcular
    }
    else if (e.getKeyCode() == KeyEvent.VK_CONTROL) { // modo vistaPrevia
      vistaPrevia = false;
      repaint();
    }
  }

  public void keyTyped(KeyEvent e) {} // no se usa


}