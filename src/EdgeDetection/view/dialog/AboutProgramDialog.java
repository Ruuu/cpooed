package view.dialog;

 import java.awt.BorderLayout;
 import java.awt.Dimension;
 import java.awt.Graphics;
 import java.awt.GridLayout;
 import java.awt.Image;
 import java.awt.Point;
 import java.awt.Toolkit;
 import java.awt.event.MouseEvent;
 import java.awt.event.MouseListener;

 import javax.swing.ImageIcon;
 import javax.swing.JDialog;
 import javax.swing.JLabel;
 import javax.swing.JPanel;
 import javax.swing.SwingConstants;

 /**
  * Klasa okna dialogowego 'o programie'
 * @author Piotr Róż
 * @since 2012-11-11
  */
 public class AboutProgramDialog extends JDialog
 {
     private static final long serialVersionUID = 1L;

     /**
      * Konstruktor
      */
     public AboutProgramDialog()
     {
         setTitle(new String("O programie"));
         final Toolkit toolkit = Toolkit.getDefaultToolkit();
         final Dimension dim = toolkit.getScreenSize();
         setLocation(new Point((dim.width / 3) - getWidth() / 3, (dim.height / 5) - getHeight() / 3));
         setGUI();
         pack();
         setResizable(false);
         addMouseListener(new MouseListener()
         {
             @Override
             public void mouseReleased(final MouseEvent arg0)
             {
             }

             @Override
             public void mousePressed(final MouseEvent arg0)
             {
             }

             @Override
             public void mouseExited(final MouseEvent arg0)
             {
             }

             @Override
             public void mouseEntered(final MouseEvent arg0)
             {
             }

             @Override
             public void mouseClicked(final MouseEvent arg0)
             {
                 setVisible(false);
             }
         });
     }

     /**
      * Funkcja wyswietla zawartosc okna 'o programie'
      */
     private void setGUI()
     {
         /** panel gorny ze zdjeciem */
         final ImagePanel topPanel = new ImagePanel(new ImageIcon(AboutProgramDialog.class.getResource("icons/infoImage.jpg")).getImage());
         /** panel dolny wraz z opisem */
         final JPanel bottomPanel = new JPanel();
         /** nazwa programu i autor */
         final JLabel infoLabel = new JLabel();
         /** informacje dodatkowe */
         final JLabel additionalInformationLabel = new JLabel();
         /** prawa autorskie */
         final JLabel copyrightLabel = new JLabel();

         setLayout(new GridLayout(2, 1));

         final String infoText = "<HTML><H1><CENTER><I>Edge Detection v1.0</I></CENTER></H1><HR>" + "<H2><CENTER><B>Piotr Roz</B></CENTER></H2></HTML>";
         infoLabel.setText(infoText);
         infoLabel.setHorizontalAlignment(SwingConstants.CENTER);

         final String additionalInformationText = "<HTML><H5><I>Ikony pobrane ze strony <A HREF=\"www.iconfinder.net\">IconFinder</A></I></H5></HTML>";
         additionalInformationLabel.setText(additionalInformationText);
         additionalInformationLabel.setHorizontalAlignment(SwingConstants.CENTER);

         final String copyrightText = "<HTML><H4>Copyright 2012 Piotr Róż " + "Wszelkie prawa zastrzezone.</H4></HTML>";
         copyrightLabel.setText(copyrightText);
         copyrightLabel.setHorizontalAlignment(SwingConstants.CENTER);

         bottomPanel.setLayout(new GridLayout(3, 1));
         bottomPanel.add(infoLabel, BorderLayout.CENTER);
         bottomPanel.add(additionalInformationLabel, BorderLayout.CENTER);
         bottomPanel.add(copyrightLabel, BorderLayout.CENTER);

         add(topPanel);
         add(bottomPanel);
     }

     /**
      * Klasa opisujaca panel z obrazkiem wyswietlany w okienku 'o programie'
      * 
      * @author Dawid Adamczyk
      */
     private class ImagePanel extends JPanel
     {
         private static final long serialVersionUID = 1L;
         private final Image img;

         /**
          * Konstruktor
          * 
          * @param img
          *            sciezka do obrazka, ktory ma zostac wyswietlany
          */
         @SuppressWarnings("unused")
         public ImagePanel(final String img)
         {
             this(new ImageIcon(img).getImage());
         }

         /**
          * Konstruktor
          * 
          * @param img
          *            obrazek, ktory ma byc wyswietlany
          */
         public ImagePanel(final Image img)
         {
             this.img = img;
             setLayout(null);
         }

         @Override
         public void paintComponent(final Graphics g)
         {
             g.drawImage(img, 0, 0, null);
         }
     }
 }
