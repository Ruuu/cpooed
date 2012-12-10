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
    
    private String currentImagePath;
    
    /**
     * Konstruktor
     */
    public Model()
    {
        errorDialog = ErrorDialog.getErrorDialog();
    }
    
    public void setCurrentImagePath(String currentImagePath)
    {
    	this.currentImagePath = currentImagePath;
    }
    
    public String getCurrentImagePath()
    {
    	return this.currentImagePath;
    }
}
