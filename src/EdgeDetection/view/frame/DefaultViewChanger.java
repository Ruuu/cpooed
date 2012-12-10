package view.frame;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileNameExtensionFilter;

import model.FileIconInFileChooser;
import model.ImagePreviewerInFileChooser;
import model.Model;
import view.dialog.ErrorDialog;

/**
 * Funkcja odpowiedzialna za komunikacje Kontroler --> Widok
 * @author Piotr Róż
 * @since 2012-11-11
 */
public class DefaultViewChanger
{
    /** Glowna ramka programu */
    private final MainApplicationFrame mainApplicationFrame;
    /** dialog informujacy uzytkownika o wystapieniu bledu w dzialaniu aplikacji */
    private final ErrorDialog errorDialog;

    public DefaultViewChanger(final MainApplicationFrame mainApplicationFrame)
    {
        this.mainApplicationFrame = mainApplicationFrame;
        errorDialog = ErrorDialog.getErrorDialog();
    }
    
    /**
     * Funkcja odpowiedzialna za zaladowanie okna dialogowego do otworzenia nowego zdjecia
     * 
     * @return sciezka do wybranego przez uzytkownika obrazka
     */
    public String openNewImageFromFile()
    {
        final String[] extension = ImageIO.getReaderFileSuffixes();
        final FileNameExtensionFilter filter = new FileNameExtensionFilter("Pliki obrazów", extension);
        final JFileChooser openChooser = new JFileChooser();
        openChooser.setFileFilter(filter);
        openChooser.setAccessory(new ImagePreviewerInFileChooser(openChooser));
        openChooser.setFileView(new FileIconInFileChooser(filter, new ImageIcon(Model.class.getResource("otherIcons/imageIcon16.png"))));
        final int result = openChooser.showOpenDialog(this.mainApplicationFrame);
        String imagePath = null;
        if(result == JFileChooser.APPROVE_OPTION)
        {
            imagePath = openChooser.getSelectedFile().getPath();
        }

        return imagePath;
    }
    
    /**
     * 
     */
    public void showMainImage(final String imagePath)
    {
    	SwingUtilities.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                mainApplicationFrame.showMainImage(imagePath);
            }
        });
    }
    
    /**
     * 
     */
    public void showModImage(final String imagePath)
    {
    	SwingUtilities.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                mainApplicationFrame.showModImage(imagePath);
            }
        });
    }

    /**
     * Funkcja wyswietla okno dialogowe z odpowiednia informacja
     * 
     * @param contents
     *            tresc informacji, ktora ma zostac przekazana
     * @param title
     *            tytul okna dialogowego
     * @param messageType
     *            typ wiadomosci
     */
    public void showInformationDialog(final String contents, final String title, final int messageType)
    {
        JOptionPane.showMessageDialog(mainApplicationFrame, contents, title, messageType);
    }
}
