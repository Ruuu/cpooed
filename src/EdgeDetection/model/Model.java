package model;

import view.dialog.ErrorDialog;

/**
 * Glowna klasa modelu przedstawiajaca cala strukture logiczna programu - wszystkie zaleznosci miedzy poszczeglownymi danymi
 * @author Piotr Róż
 * @since 2012-11-11
 */
public class Model 
{
    /** Dialog informujacy uzytkownika o wystapieniu bledu w dzialaniu aplikacji */
    @SuppressWarnings("unused")
	private final ErrorDialog errorDialog;
    
    /**
     * Konstruktor
     */
    public Model()
    {
        errorDialog = ErrorDialog.getErrorDialog();
    }
}
