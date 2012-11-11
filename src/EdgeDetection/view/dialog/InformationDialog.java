package view.dialog;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import view.frame.MainApplicationFrame;

 /**
  * Klasa reprezentujaca okno dialogowe przekazujace uzytkownikowi informacje
 * @author Piotr Róż
 * @since 2012-11-11
  */
 public class InformationDialog extends JDialog
 {
     private static final long serialVersionUID = 1L;
     /** tekst wyswietlony jako informacja */
     private final JLabel informationsLabel;
     /** panel z tekstem */
     private final JPanel topPanel;
     /** panel dolny */
     private final JPanel bottomPanel;

     /**
      * Konstruktor
      * 
      * @param mainApplicationFrame
      *            okno, na ktorym zostanie wyswietlony dialog
      * @param informations
      *            tekst informujacy uzytkownika
      * @param title
      *            tytul dialogu
      */
     public InformationDialog(final MainApplicationFrame mainApplicationFrame, final String informationsToShow, final String title)
     {
         super(mainApplicationFrame, title);
         this.setSize(new Dimension(300, 100));
         final Toolkit toolkit = Toolkit.getDefaultToolkit();
         final Dimension dim = toolkit.getScreenSize();
         setLocation(new Point((dim.width / 2) - getWidth() / 2, (dim.height / 2) - getHeight() / 2));
         this.setModal(true);
         this.setLayout(new GridLayout(2, 1));

         topPanel = new JPanel();
         informationsLabel = new JLabel(informationsToShow, SwingConstants.CENTER);
         topPanel.add(informationsLabel);

         bottomPanel = new JPanel();
         final JLabel exitLabel = new JLabel("Kliknij tutaj, aby zamknąć.", SwingConstants.CENTER);
         exitLabel.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 9));
         bottomPanel.addMouseListener(new MouseListener()
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
         bottomPanel.add(exitLabel);

         add(topPanel);
         add(bottomPanel);
     }

     /**
      * Zmiana tekstu wyswietlanego przez okno dialogowe
      * 
      * @param informationText
      *            nowy tekst wyswietlany przez okno dialogowe
      */
     public void setInformationText(final String informationText)
     {
         this.informationsLabel.setText(informationText);
     }

     /**
      * Uwidocznienie dialogu
      */
     public void showInformationDialog()
     {
         this.setVisible(true);
     }
 }
