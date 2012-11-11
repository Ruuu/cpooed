package view.frame;

import javax.swing.JOptionPane;

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
