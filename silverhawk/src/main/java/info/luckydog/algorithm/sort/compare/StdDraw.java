package info.luckydog.algorithm.sort.compare;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.awt.image.DirectColorModel;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.TreeSet;


public final class StdDraw
        implements ActionListener, MouseListener, MouseMotionListener, KeyListener {
    public static final Color BLACK = Color.BLACK;


    public static final Color BLUE = Color.BLUE;


    public static final Color CYAN = Color.CYAN;


    public static final Color DARK_GRAY = Color.DARK_GRAY;


    public static final Color GRAY = Color.GRAY;


    public static final Color GREEN = Color.GREEN;


    public static final Color LIGHT_GRAY = Color.LIGHT_GRAY;


    public static final Color MAGENTA = Color.MAGENTA;


    public static final Color ORANGE = Color.ORANGE;


    public static final Color PINK = Color.PINK;


    public static final Color RED = Color.RED;


    public static final Color WHITE = Color.WHITE;


    public static final Color YELLOW = Color.YELLOW;


    public static final Color BOOK_BLUE = new Color(9, 90, 166);


    public static final Color BOOK_LIGHT_BLUE = new Color(103, 198, 243);


    public static final Color BOOK_RED = new Color(150, 35, 31);


    public static final Color PRINCETON_ORANGE = new Color(245, 128, 37);


    private static final Color DEFAULT_PEN_COLOR = BLACK;
    private static final Color DEFAULT_CLEAR_COLOR = WHITE;


    private static Color penColor;

    private static final int DEFAULT_SIZE = 512;

    private static int width = 512;
    private static int height = 512;

    private static final double DEFAULT_PEN_RADIUS = 0.002D;

    private static double penRadius;

    private static boolean defer = false;

    private static final double BORDER = 0.0D;

    private static final double DEFAULT_XMIN = 0.0D;

    private static final double DEFAULT_XMAX = 1.0D;

    private static final double DEFAULT_YMIN = 0.0D;

    private static final double DEFAULT_YMAX = 1.0D;
    private static double xmin;
    private static double ymin;
    private static double xmax;
    private static double ymax;
    private static Object mouseLock = new Object();
    private static Object keyLock = new Object();


    private static final Font DEFAULT_FONT = new Font("SansSerif", 1, 16);

    private static Font font;

    private static BufferedImage offscreenImage;

    private static BufferedImage onscreenImage;

    private static Graphics2D offscreen;
    private static Graphics2D onscreen;
    private static StdDraw std = new StdDraw();


    private static JFrame frame;

    private static boolean mousePressed = false;

    private static double mouseX = 0.0D;
    private static double mouseY = 0.0D;


    private static LinkedList<Character> keysTyped = new LinkedList();


    private static TreeSet<Integer> keysDown = new TreeSet();


    static {
        init();
    }


    public static void setCanvasSize() {
        setCanvasSize(512, 512);
    }


    public static void setCanvasSize(int canvasWidth, int canvasHeight) {
        if (canvasWidth <= 0 || canvasHeight <= 0)
            throw new IllegalArgumentException("width and height must be positive");
        width = canvasWidth;
        height = canvasHeight;
        init();
    }


    private static void init() {
        if (frame != null) frame.setVisible(false);
        frame = new JFrame();
        offscreenImage = new BufferedImage(width, height, 2);
        onscreenImage = new BufferedImage(width, height, 2);
        offscreen = offscreenImage.createGraphics();
        onscreen = onscreenImage.createGraphics();
        setXscale();
        setYscale();
        offscreen.setColor(DEFAULT_CLEAR_COLOR);
        offscreen.fillRect(0, 0, width, height);
        setPenColor();
        setPenRadius();
        setFont();
        clear();


        RenderingHints hints = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        hints.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        offscreen.addRenderingHints(hints);


        ImageIcon icon = new ImageIcon(onscreenImage);
        JLabel draw = new JLabel(icon);

        draw.addMouseListener(std);
        draw.addMouseMotionListener(std);

        frame.setContentPane(draw);
        frame.addKeyListener(std);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(3);

        frame.setTitle("Standard Draw");
        frame.setJMenuBar(createMenuBar());
        frame.pack();
        frame.requestFocusInWindow();
        frame.setVisible(true);
    }


    private static JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("File");
        menuBar.add(menu);
        JMenuItem menuItem1 = new JMenuItem(" Save...   ");
        menuItem1.addActionListener(std);
        menuItem1.setAccelerator(KeyStroke.getKeyStroke(83,
                Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        menu.add(menuItem1);
        return menuBar;
    }


    public static void setXscale() {
        setXscale(0.0D, 1.0D);
    }


    public static void setYscale() {
        setYscale(0.0D, 1.0D);
    }


    public static void setScale() {
        setXscale();
        setYscale();
    }


    public static void setXscale(double min, double max) {
        double size = max - min;
        if (size == 0.0D) throw new IllegalArgumentException("the min and max are the same");
        synchronized (mouseLock) {
            xmin = min - 0.0D * size;
            xmax = max + 0.0D * size;
        }
    }


    public static void setYscale(double min, double max) {
        double size = max - min;
        if (size == 0.0D) throw new IllegalArgumentException("the min and max are the same");
        synchronized (mouseLock) {
            ymin = min - 0.0D * size;
            ymax = max + 0.0D * size;
        }
    }


    public static void setScale(double min, double max) {
        double size = max - min;
        if (size == 0.0D) throw new IllegalArgumentException("the min and max are the same");
        synchronized (mouseLock) {
            xmin = min - 0.0D * size;
            xmax = max + 0.0D * size;
            ymin = min - 0.0D * size;
            ymax = max + 0.0D * size;
        }
    }


    private static double scaleX(double x) {
        return width * (x - xmin) / (xmax - xmin);
    }

    private static double scaleY(double y) {
        return height * (ymax - y) / (ymax - ymin);
    }

    private static double factorX(double w) {
        return w * width / Math.abs(xmax - xmin);
    }

    private static double factorY(double h) {
        return h * height / Math.abs(ymax - ymin);
    }

    private static double userX(double x) {
        return xmin + x * (xmax - xmin) / width;
    }

    private static double userY(double y) {
        return ymax - y * (ymax - ymin) / height;
    }


    public static void clear() {
        clear(DEFAULT_CLEAR_COLOR);
    }


    public static void clear(Color color) {
        offscreen.setColor(color);
        offscreen.fillRect(0, 0, width, height);
        offscreen.setColor(penColor);
        draw();
    }


    public static double getPenRadius() {
        return penRadius;
    }


    public static void setPenRadius() {
        setPenRadius(0.002D);
    }


    public static void setPenRadius(double radius) {
        if (radius < 0.0D) throw new IllegalArgumentException("pen radius must be nonnegative");
        penRadius = radius;
        float scaledPenRadius = (float) (radius * 512.0D);
        BasicStroke stroke = new BasicStroke(scaledPenRadius, 1, 1);

        offscreen.setStroke(stroke);
    }


    public static Color getPenColor() {
        return penColor;
    }


    public static void setPenColor() {
        setPenColor(DEFAULT_PEN_COLOR);
    }


    public static void setPenColor(Color color) {
        if (color == null) throw new IllegalArgumentException();
        penColor = color;
        offscreen.setColor(penColor);
    }


    public static void setPenColor(int red, int green, int blue) {
        if (red < 0 || red >= 256) throw new IllegalArgumentException("amount of red must be between 0 and 255");
        if (green < 0 || green >= 256) throw new IllegalArgumentException("amount of green must be between 0 and 255");
        if (blue < 0 || blue >= 256) throw new IllegalArgumentException("amount of blue must be between 0 and 255");
        setPenColor(new Color(red, green, blue));
    }


    public static Font getFont() {
        return font;
    }


    public static void setFont() {
        setFont(DEFAULT_FONT);
    }


    public static void setFont(Font font) {
        if (font == null) throw new IllegalArgumentException();
        StdDraw.font = font;
    }


    public static void line(double x0, double y0, double x1, double y1) {
        offscreen.draw(new Line2D.Double(scaleX(x0), scaleY(y0), scaleX(x1), scaleY(y1)));
        draw();
    }


    private static void pixel(double x, double y) {
        offscreen.fillRect((int) Math.round(scaleX(x)), (int) Math.round(scaleY(y)), 1, 1);
    }


    public static void point(double x, double y) {
        double xs = scaleX(x);
        double ys = scaleY(y);
        double r = penRadius;
        float scaledPenRadius = (float) (r * 512.0D);


        if (scaledPenRadius <= 1.0F) {
            pixel(x, y);
        } else {
            offscreen.fill(new Ellipse2D.Double(xs - (scaledPenRadius / 2.0F), ys - (scaledPenRadius / 2.0F), scaledPenRadius, scaledPenRadius));
        }

        draw();
    }

    /**
     * 画圆
     *
     * @param x      圆心x坐标
     * @param y      圆心y坐标
     * @param radius 半径
     */
    public static void circle(double x, double y, double radius) {
        if (radius < 0.0D) throw new IllegalArgumentException("radius must be nonnegative");
        double xs = scaleX(x);
        double ys = scaleY(y);
        double ws = factorX(2.0D * radius);
        double hs = factorY(2.0D * radius);
        if (ws <= 1.0D && hs <= 1.0D) {
            pixel(x, y);
        } else {
            offscreen.draw(new Ellipse2D.Double(xs - ws / 2.0D, ys - hs / 2.0D, ws, hs));
        }
        draw();
    }


    public static void filledCircle(double x, double y, double radius) {
        if (radius < 0.0D) throw new IllegalArgumentException("radius must be nonnegative");
        double xs = scaleX(x);
        double ys = scaleY(y);
        double ws = factorX(2.0D * radius);
        double hs = factorY(2.0D * radius);
        if (ws <= 1.0D && hs <= 1.0D) {
            pixel(x, y);
        } else {
            offscreen.fill(new Ellipse2D.Double(xs - ws / 2.0D, ys - hs / 2.0D, ws, hs));
        }
        draw();
    }


    public static void ellipse(double x, double y, double semiMajorAxis, double semiMinorAxis) {
        if (semiMajorAxis < 0.0D) throw new IllegalArgumentException("ellipse semimajor axis must be nonnegative");
        if (semiMinorAxis < 0.0D) throw new IllegalArgumentException("ellipse semiminor axis must be nonnegative");
        double xs = scaleX(x);
        double ys = scaleY(y);
        double ws = factorX(2.0D * semiMajorAxis);
        double hs = factorY(2.0D * semiMinorAxis);
        if (ws <= 1.0D && hs <= 1.0D) {
            pixel(x, y);
        } else {
            offscreen.draw(new Ellipse2D.Double(xs - ws / 2.0D, ys - hs / 2.0D, ws, hs));
        }
        draw();
    }


    public static void filledEllipse(double x, double y, double semiMajorAxis, double semiMinorAxis) {
        if (semiMajorAxis < 0.0D) throw new IllegalArgumentException("ellipse semimajor axis must be nonnegative");
        if (semiMinorAxis < 0.0D) throw new IllegalArgumentException("ellipse semiminor axis must be nonnegative");
        double xs = scaleX(x);
        double ys = scaleY(y);
        double ws = factorX(2.0D * semiMajorAxis);
        double hs = factorY(2.0D * semiMinorAxis);
        if (ws <= 1.0D && hs <= 1.0D) {
            pixel(x, y);
        } else {
            offscreen.fill(new Ellipse2D.Double(xs - ws / 2.0D, ys - hs / 2.0D, ws, hs));
        }
        draw();
    }

    /**
     * 画圆弧
     *
     * @param x      圆心x坐标
     * @param y      圆心y坐标
     * @param radius 半径
     * @param angle1 起始角度
     * @param angle2 终止角度
     */
    public static void arc(double x, double y, double radius, double angle1, double angle2) {
        if (radius < 0.0D) throw new IllegalArgumentException("arc radius must be nonnegative");
        for (; angle2 < angle1; angle2 += 360.0D) ;
        double xs = scaleX(x);
        double ys = scaleY(y);
        double ws = factorX(2.0D * radius);
        double hs = factorY(2.0D * radius);
        if (ws <= 1.0D && hs <= 1.0D) {
            pixel(x, y);
        } else {
            offscreen.draw(new Arc2D.Double(xs - ws / 2.0D, ys - hs / 2.0D, ws, hs, angle1, angle2 - angle1, Arc2D.CHORD));
        }
        draw();
    }

    /**
     * 画一个正方形
     *
     * @param x          中心点x坐标
     * @param y          中心点y坐标
     * @param halfLength 边长的一半
     */
    public static void square(double x, double y, double halfLength) {
        if (halfLength < 0.0D) throw new IllegalArgumentException("half length must be nonnegative");
        double xs = scaleX(x);
        double ys = scaleY(y);
        double ws = factorX(2.0D * halfLength);
        double hs = factorY(2.0D * halfLength);
        if (ws <= 1.0D && hs <= 1.0D) {
            pixel(x, y);
        } else {
            offscreen.draw(new Rectangle2D.Double(xs - ws / 2.0D, ys - hs / 2.0D, ws, hs));
        }
        draw();
    }

    /**
     * 画一个正方形并填充
     *
     * @param x          中心点x坐标
     * @param y          中心点y坐标
     * @param halfLength 边长的一半
     */
    public static void filledSquare(double x, double y, double halfLength) {
        if (halfLength < 0.0D) throw new IllegalArgumentException("half length must be nonnegative");
        double xs = scaleX(x);
        double ys = scaleY(y);
        double ws = factorX(2.0D * halfLength);
        double hs = factorY(2.0D * halfLength);
        if (ws <= 1.0D && hs <= 1.0D) {
            pixel(x, y);
        } else {
            offscreen.fill(new Rectangle2D.Double(xs - ws / 2.0D, ys - hs / 2.0D, ws, hs));
        }
        draw();
    }


    public static void rectangle(double x, double y, double halfWidth, double halfHeight) {
        if (halfWidth < 0.0D) throw new IllegalArgumentException("half width must be nonnegative");
        if (halfHeight < 0.0D) throw new IllegalArgumentException("half height must be nonnegative");
        double xs = scaleX(x);
        double ys = scaleY(y);
        double ws = factorX(2.0D * halfWidth);
        double hs = factorY(2.0D * halfHeight);
        if (ws <= 1.0D && hs <= 1.0D) {
            pixel(x, y);
        } else {
            offscreen.draw(new Rectangle2D.Double(xs - ws / 2.0D, ys - hs / 2.0D, ws, hs));
        }
        draw();
    }


    public static void filledRectangle(double x, double y, double halfWidth, double halfHeight) {
        if (halfWidth < 0.0D) throw new IllegalArgumentException("half width must be nonnegative");
        if (halfHeight < 0.0D) throw new IllegalArgumentException("half height must be nonnegative");
        double xs = scaleX(x);
        double ys = scaleY(y);
        double ws = factorX(2.0D * halfWidth);
        double hs = factorY(2.0D * halfHeight);
        if (ws <= 1.0D && hs <= 1.0D) {
            pixel(x, y);
        } else {
            offscreen.fill(new Rectangle2D.Double(xs - ws / 2.0D, ys - hs / 2.0D, ws, hs));
        }
        draw();
    }

    /**
     * 画多边形
     *
     * @param x 各点的x坐标数组
     * @param y 各点的y坐标数组
     */
    public static void polygon(double[] x, double[] y) {
        if (x == null) throw new IllegalArgumentException("x-coordinate array is null");
        if (y == null) throw new IllegalArgumentException("y-coordinate array is null");
        int n1 = x.length;
        int n2 = y.length;
        if (n1 != n2) throw new IllegalArgumentException("arrays must be of the same length");
        int n = n1;
        if (n == 0)
            return;
        GeneralPath path = new GeneralPath();
        path.moveTo((float) scaleX(x[0]), (float) scaleY(y[0]));
        for (int i = 0; i < n; i++)
            path.lineTo((float) scaleX(x[i]), (float) scaleY(y[i]));
        path.closePath();
        offscreen.draw(path);
        draw();
    }

    /**
     * 画多边形并填充
     *
     * @param x 各点的x坐标数组
     * @param y 各点的y坐标数组
     */
    public static void filledPolygon(double[] x, double[] y) {
        if (x == null) throw new IllegalArgumentException("x-coordinate array is null");
        if (y == null) throw new IllegalArgumentException("y-coordinate array is null");
        int n1 = x.length;
        int n2 = y.length;
        if (n1 != n2) throw new IllegalArgumentException("arrays must be of the same length");
        int n = n1;
        if (n == 0)
            return;
        GeneralPath path = new GeneralPath();
        path.moveTo((float) scaleX(x[0]), (float) scaleY(y[0]));
        for (int i = 0; i < n; i++)
            path.lineTo((float) scaleX(x[i]), (float) scaleY(y[i]));
        path.closePath();
        offscreen.fill(path);
        draw();
    }


    private static Image getImage(String filename) {
        if (filename == null) throw new IllegalArgumentException();


        ImageIcon icon = new ImageIcon(filename);


        if (icon == null || icon.getImageLoadStatus() != 8) {
            try {
                URL url = new URL(filename);
                icon = new ImageIcon(url);
            } catch (MalformedURLException malformedURLException) {
            }
        }


        if (icon == null || icon.getImageLoadStatus() != 8) {
            URL url = StdDraw.class.getResource(filename);
            if (url != null) {
                icon = new ImageIcon(url);
            }
        }

        if (icon == null || icon.getImageLoadStatus() != 8) {
            URL url = StdDraw.class.getResource("/" + filename);
            if (url == null) throw new IllegalArgumentException("image " + filename + " not found");
            icon = new ImageIcon(url);
        }

        return icon.getImage();
    }


    public static void picture(double x, double y, String filename) {
        Image image = getImage(filename);
        double xs = scaleX(x);
        double ys = scaleY(y);


        int ws = image.getWidth(null);
        int hs = image.getHeight(null);
        if (ws < 0 || hs < 0) throw new IllegalArgumentException("image " + filename + " is corrupt");

        offscreen.drawImage(image, (int) Math.round(xs - ws / 2.0D), (int) Math.round(ys - hs / 2.0D), null);
        draw();
    }


    public static void picture(double x, double y, String filename, double degrees) {
        Image image = getImage(filename);
        double xs = scaleX(x);
        double ys = scaleY(y);


        int ws = image.getWidth(null);
        int hs = image.getHeight(null);
        if (ws < 0 || hs < 0) throw new IllegalArgumentException("image " + filename + " is corrupt");

        offscreen.rotate(Math.toRadians(-degrees), xs, ys);
        offscreen.drawImage(image, (int) Math.round(xs - ws / 2.0D), (int) Math.round(ys - hs / 2.0D), null);
        offscreen.rotate(Math.toRadians(degrees), xs, ys);

        draw();
    }


    public static void picture(double x, double y, String filename, double scaledWidth, double scaledHeight) {
        Image image = getImage(filename);
        if (scaledWidth < 0.0D) throw new IllegalArgumentException("width  is negative: " + scaledWidth);
        if (scaledHeight < 0.0D) throw new IllegalArgumentException("height is negative: " + scaledHeight);
        double xs = scaleX(x);
        double ys = scaleY(y);
        double ws = factorX(scaledWidth);
        double hs = factorY(scaledHeight);
        if (ws < 0.0D || hs < 0.0D) throw new IllegalArgumentException("image " + filename + " is corrupt");
        if (ws <= 1.0D && hs <= 1.0D) {
            pixel(x, y);
        } else {
            offscreen.drawImage(image, (int) Math.round(xs - ws / 2.0D),
                    (int) Math.round(ys - hs / 2.0D),
                    (int) Math.round(ws),
                    (int) Math.round(hs), null);
        }

        draw();
    }


    public static void picture(double x, double y, String filename, double scaledWidth, double scaledHeight, double degrees) {
        if (scaledWidth < 0.0D) throw new IllegalArgumentException("width is negative: " + scaledWidth);
        if (scaledHeight < 0.0D) throw new IllegalArgumentException("height is negative: " + scaledHeight);
        Image image = getImage(filename);
        double xs = scaleX(x);
        double ys = scaleY(y);
        double ws = factorX(scaledWidth);
        double hs = factorY(scaledHeight);
        if (ws < 0.0D || hs < 0.0D) throw new IllegalArgumentException("image " + filename + " is corrupt");
        if (ws <= 1.0D && hs <= 1.0D) pixel(x, y);

        offscreen.rotate(Math.toRadians(-degrees), xs, ys);
        offscreen.drawImage(image, (int) Math.round(xs - ws / 2.0D),
                (int) Math.round(ys - hs / 2.0D),
                (int) Math.round(ws),
                (int) Math.round(hs), null);
        offscreen.rotate(Math.toRadians(degrees), xs, ys);

        draw();
    }

    /**
     * 写文字
     *
     * @param x    文字中心点x坐标
     * @param y    文字中心点y坐标
     * @param text 文本内容
     */
    public static void text(double x, double y, String text) {
        if (text == null) throw new IllegalArgumentException();
        offscreen.setFont(font);
        FontMetrics metrics = offscreen.getFontMetrics();
        double xs = scaleX(x);
        double ys = scaleY(y);
        int ws = metrics.stringWidth(text);
        int hs = metrics.getDescent();
        offscreen.drawString(text, (float) (xs - ws / 2.0D), (float) (ys + hs));
        draw();
    }


    public static void text(double x, double y, String text, double degrees) {
        if (text == null) throw new IllegalArgumentException();
        double xs = scaleX(x);
        double ys = scaleY(y);
        offscreen.rotate(Math.toRadians(-degrees), xs, ys);
        text(x, y, text);
        offscreen.rotate(Math.toRadians(degrees), xs, ys);
    }


    public static void textLeft(double x, double y, String text) {
        if (text == null) throw new IllegalArgumentException();
        offscreen.setFont(font);
        FontMetrics metrics = offscreen.getFontMetrics();
        double xs = scaleX(x);
        double ys = scaleY(y);
        int hs = metrics.getDescent();
        offscreen.drawString(text, (float) xs, (float) (ys + hs));
        draw();
    }


    public static void textRight(double x, double y, String text) {
        if (text == null) throw new IllegalArgumentException();
        offscreen.setFont(font);
        FontMetrics metrics = offscreen.getFontMetrics();
        double xs = scaleX(x);
        double ys = scaleY(y);
        int ws = metrics.stringWidth(text);
        int hs = metrics.getDescent();
        offscreen.drawString(text, (float) (xs - ws), (float) (ys + hs));
        draw();
    }


    @Deprecated
    public static void show(int t) {
        show();
        pause(t);
        enableDoubleBuffering();
    }


    public static void pause(int t) {
        try {
            Thread.sleep(t);
        } catch (InterruptedException e) {
            System.out.println("Error sleeping");
        }
    }


    public static void show() {
        onscreen.drawImage(offscreenImage, 0, 0, null);
        frame.repaint();
    }


    private static void draw() {
        if (!defer) show();
    }


    public static void enableDoubleBuffering() {
        defer = true;
    }


    public static void disableDoubleBuffering() {
        defer = false;
    }


    public static void save(String filename) {
        if (filename == null) throw new IllegalArgumentException();
        File file = new File(filename);
        String suffix = filename.substring(filename.lastIndexOf('.') + 1);


        if ("png".equalsIgnoreCase(suffix)) {
            try {
                ImageIO.write(onscreenImage, suffix, file);
            } catch (IOException e) {
                e.printStackTrace();

            }


        } else if ("jpg".equalsIgnoreCase(suffix)) {
            WritableRaster raster = onscreenImage.getRaster();

            WritableRaster newRaster = raster.createWritableChild(0, 0, width, height, 0, 0, new int[]{0, 1, 2});
            DirectColorModel cm = (DirectColorModel) onscreenImage.getColorModel();


            DirectColorModel newCM = new DirectColorModel(cm.getPixelSize(), cm.getRedMask(), cm.getGreenMask(), cm.getBlueMask());
            BufferedImage rgbBuffer = new BufferedImage(newCM, newRaster, false, null);
            try {
                ImageIO.write(rgbBuffer, suffix, file);
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {

            System.out.println("Invalid image file type: " + suffix);
        }
    }


    public void actionPerformed(ActionEvent e) {
        FileDialog chooser = new FileDialog(frame, "Use a .png or .jpg extension", 1);
        chooser.setVisible(true);
        String filename = chooser.getFile();
        if (filename != null) {
            save(chooser.getDirectory() + File.separator + chooser.getFile());
        }
    }


    public static boolean mousePressed() {
        synchronized (mouseLock) {
            return mousePressed;
        }
    }


    public static double mouseX() {
        synchronized (mouseLock) {
            return mouseX;
        }
    }


    public static double mouseY() {
        synchronized (mouseLock) {
            return mouseY;
        }
    }


    public void mouseClicked(MouseEvent e) {
    }


    public void mouseEntered(MouseEvent e) {
    }


    public void mouseExited(MouseEvent e) {
    }


    public void mousePressed(MouseEvent e) {
        synchronized (mouseLock) {
            mouseX = userX(e.getX());
            mouseY = userY(e.getY());
            mousePressed = true;
        }
    }


    public void mouseReleased(MouseEvent e) {
        synchronized (mouseLock) {
            mousePressed = false;
        }
    }


    public void mouseDragged(MouseEvent e) {
        synchronized (mouseLock) {
            mouseX = userX(e.getX());
            mouseY = userY(e.getY());
        }
    }


    public void mouseMoved(MouseEvent e) {
        synchronized (mouseLock) {
            mouseX = userX(e.getX());
            mouseY = userY(e.getY());
        }
    }


    public static boolean hasNextKeyTyped() {
        synchronized (keyLock) {
            return !keysTyped.isEmpty();
        }
    }


    public static char nextKeyTyped() {
        synchronized (keyLock) {
            if (keysTyped.isEmpty()) {
                throw new NoSuchElementException("your program has already processed all keystrokes");
            }
            return ((Character) keysTyped.removeLast()).charValue();
        }
    }


    public static boolean isKeyPressed(int keycode) {
        synchronized (keyLock) {
            return keysDown.contains(Integer.valueOf(keycode));
        }
    }


    public void keyTyped(KeyEvent e) {
        synchronized (keyLock) {
            keysTyped.addFirst(Character.valueOf(e.getKeyChar()));
        }
    }


    public void keyPressed(KeyEvent e) {
        synchronized (keyLock) {
            keysDown.add(Integer.valueOf(e.getKeyCode()));
        }
    }


    public void keyReleased(KeyEvent e) {
        synchronized (keyLock) {
            keysDown.remove(Integer.valueOf(e.getKeyCode()));
        }
    }


    public static void main(String[] args) {
        //画一个正方形
//        square(0.2D, 0.8D, 0.1D);
//        //画一个正方形并填充
//        filledSquare(0.8D, 0.8D, 0.2D);
//        //画一个圆
//        circle(0.8D, 0.2D, 0.2D);
//
//        //设置画笔颜色
//        setPenColor(BOOK_RED);
//        //设置画笔线条粗细
//        setPenRadius(0.02D);
//        //画圆弧
//        arc(0.8D, 0.2D, 0.1D, 200.0D, 45.0D);

        //设置画笔线条默认粗细
        setPenRadius();
        //设置画笔颜色
        setPenColor(BOOK_BLUE);
        //画多边形并填充
//        double[] x = {0.1D, 0.2D, 0.3D, 0.2D};
//        double[] y = {0.2D, 0.3D, 0.2D, 0.1D};
//        filledPolygon(x, y);

//        //设置画笔颜色
//        setPenColor(BLACK);
//        //写文字
//        text(0.2D, 0.5D, "black text");
//        //设置画笔颜色
//        setPenColor(WHITE);
//        //写文字
//        text(0.8D, 0.8D, "white text");

        Integer[] a = {9, 8, 7, 6, 5, 4, 3, 2, 1};
        int n = a.length;
        double xPadding = 2D;
        double yPadding = 0.5D;
        double width = 0.5D;
        double height = 1D;
        double[] x = new double[n * 4];
        double[] y = new double[n * 4];
        for (int i = 0; i < n; i++) {
            int j = 4 * i;
            if (i == 0) {
                x[j] = xPadding;
                y[j] = yPadding;
            } else {
                x[j] = x[j - 1];
                y[j] = y[j - 1];
            }

            x[j + 1] = x[j];
            y[j + 1] = y[j] + a[i] * height;
            x[j + 2] = x[j + 1] + width;
            y[j + 2] = y[j + 1];
            x[j + 3] = x[j + 2];
            y[j + 3] = y[j + 2] - a[i] * height;
        }
        for (int i = 0; i < x.length; i++) {
            x[i] = x[i] / 10;
        }
        for (int i = 0; i < y.length; i++) {
            y[i] = y[i] / 10;
        }

        System.out.println(Arrays.toString(x));
        System.out.println(Arrays.toString(y));

        polygon(x, y);
    }
}
