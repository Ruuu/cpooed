package model;

import java.io.File;

import javax.swing.Icon;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileView;

/**
 * Widok plikow wyswietlajacych ikone obok wszystkich plikow zaakceptowanych przez filtr
 * @author Piotr Róż
 */
public class FileIconInFileChooser extends FileView
{
    /** filtr plikow */
    private final FileFilter filter;
    /** ikona plikow zaakceptowanych przez filtr */
    private final Icon icon;

    /**
     * Tworzy obiekt FileIconInFileChooser
     * 
     * @param filter
     *            filtr plikow - wszyskti pliki zaakceptowane przez ten filtr beda mialy ikone
     * @param icon
     *            ikona wyswietlana obok wszystkich zaakceptowanych plikow
     */
    public FileIconInFileChooser(final FileFilter filter, final Icon icon)
    {
        this.filter = filter;
        this.icon = icon;
    }

    /**
     * Sprawdza, czy wyswietlic przy pliku ikone
     * 
     * @param file
     *            plik, ktory jest poddawany sprawdzeniu
     * 
     * @return zwraca ikone, jesli plik jest zaakceptowany przez filtr, null w przeciwnym wypadku
     */
    @Override
    public Icon getIcon(final File file)
    {
        if(!file.isDirectory() && filter.accept(file))
        {
            return icon;
        }
        else
        {
            return null;
        }
    }
}
