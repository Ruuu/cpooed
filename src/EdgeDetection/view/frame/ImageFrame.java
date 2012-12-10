package view.frame;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Shape;
import java.awt.Toolkit;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

/**
 * Miejsce, gdzie jest wyswietlany obraz
 * @author Piotr Róż
 */
public class ImageFrame extends JInternalFrame implements Printable
{
    static final long serialVersionUID = 2L;

    /** aktualnie wyswietlany obraz */
    private Image currentImage;
    /** panel, na ktorym wyswietlany jest obraz */
    private final JPanel topImagePanel;
    /** panel z suwakami, na ktorym znajduje sie topImagePanel */
    private final JScrollPane scrollImagePane;
    /** rozmiar obrazu */
    private final Dimension imageDimension;
    /** do wyswietlenia obrazu */
    private final JLabel imageLabel;

    /**
     * Konstruktor
     */
    public ImageFrame()
    {
        final Toolkit toolkit = Toolkit.getDefaultToolkit();
        imageDimension = toolkit.getScreenSize();
        setSize(imageDimension.width - 100, imageDimension.height - 200);
        setBorder(BorderFactory.createEtchedBorder());
        imageLabel = new JLabel();
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        scrollImagePane = new JScrollPane();
        topImagePanel = new JPanel();
        topImagePanel.setLayout(new BorderLayout());
        getContentPane().add(topImagePanel);
        topImagePanel.add(scrollImagePane, BorderLayout.CENTER);
    }

    /**
     * Funkcja rysujaca obraz
     * 
     * @param image
     *            obraz do zaladowania
     */
    public void paintImage(final String imagePath)
    {
        setTitle(imagePath);
        try 
        {
			currentImage = ImageIO.read(new File(imagePath));
			final Icon imageIcon = new ImageIcon(currentImage);
	        imageLabel.setIcon(imageIcon);
	        scrollImagePane.getViewport().add(imageLabel);
	        setVisible(true);
		} 
        catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    /**
     * Funkcja sluzaca wprowadzaniu zmian na obrazie
     * 
     * @param image
     *            nowy obraz
     */
    public void setChanges(final String imagePath)
    {
        setTitle(imagePath);
        try 
        {
			currentImage = ImageIO.read(new File(imagePath));
			final Icon imageIcon = new ImageIcon(currentImage);
	        imageLabel.setIcon(imageIcon);
	        scrollImagePane.getViewport().add(imageLabel);
	        repaint();
		} 
        catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @Override
    public int print(final Graphics graphics, final PageFormat pageFormat, final int pageIndex) throws PrinterException
    {
        if(pageIndex >= 1)
        {
            return Printable.NO_SUCH_PAGE;
        }

        final Graphics2D g2 = (Graphics2D) graphics;
        g2.translate(pageFormat.getImageableX(), pageFormat.getImageableY());
        g2.draw(new Rectangle2D.Double(0, 0, pageFormat.getImageableWidth(), pageFormat.getImageableHeight()));
        drawPage(g2);
        return Printable.PAGE_EXISTS;
    }

    /**
     * Metoda tworzaca obraz strony, wykorzystujaca kontekst graficzny interfejsu ekranu badz drukarki.
     * 
     * @param g2
     *            kontekst graficzny
     */
    public void drawPage(final Graphics2D g2)
    {
        final FontRenderContext context = g2.getFontRenderContext();
        final Font f = new Font("Serif", Font.PLAIN, 72);
        final GeneralPath clipShape = new GeneralPath();

        TextLayout layout = new TextLayout("Hello", f, context);
        AffineTransform transform = AffineTransform.getTranslateInstance(0, 72);
        Shape outline = layout.getOutline(transform);
        clipShape.append(outline, false);

        layout = new TextLayout("World", f, context);
        transform = AffineTransform.getTranslateInstance(0, 144);
        outline = layout.getOutline(transform);
        clipShape.append(outline, false);

        g2.draw(clipShape);
        g2.clip(clipShape);

        final int NLINES = 50;

        final Point2D p = new Point2D.Double(0, 0);
        for(int i = 0; i < NLINES; i++)
        {
            final double x = (2 * getWidth() * i) / NLINES;
            final double y = (2 * getHeight() * (NLINES - 1 - i)) / NLINES;
            final Point2D q = new Point2D.Double(x, y);
            g2.draw(new Line2D.Double(p, q));
        }
    }
}
