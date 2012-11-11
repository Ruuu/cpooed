package view.dialog;

/**
 * Klasa, sluzaca do informowania uzytkownika o bledach w dzialaniu aplikacji korzystajaca z wzorca Singleton.
 * @author Piotr Róż
 * @since 2012-11-11
 */
public class ErrorDialog
{
    /** Statyczny obiekt tej klasy */
    private static ErrorDialog errorDialog;
    /** Dialog wyswietlajacy informacje */
    private final InformationDialog informationDialog;

    /**
     * Konstruktor prywatny
     */
    private ErrorDialog()
    {
        informationDialog = new InformationDialog(null, "", "Błąd wykonania programu!");
    }

    /**
     * Funkcja zwraca statyczna instancje obiektu tej klasy
     * 
     * @return dialog informujacy o bledzie
     */
    public static ErrorDialog getErrorDialog()
    {
        if(errorDialog == null)
        {
            errorDialog = new ErrorDialog();
        }
        return errorDialog;
    }

    /**
     * Funkcja uwidacznia okno dialogowe wyswietlajace informacje o bledzie
     * 
     * @param informationToShow
     */
    public void showErrorDialog(final String informationToShow)
    {
        this.informationDialog.setInformationText(informationToShow);
        this.informationDialog.showInformationDialog();
    }
}
