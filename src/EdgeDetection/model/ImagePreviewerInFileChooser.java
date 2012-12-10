package model;

import java.awt.Dimension;
import java.awt.Image;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;

/**
 * Akcesorium wyswietlajace podglad obrazow
 * 
 * @author Piotr Róż
 */
public class ImagePreviewerInFileChooser extends JLabel
{
    private static final long serialVersionUID = 1L;

    /**
     * Tworzy obiekt ImagePreviewerInFileChooser
     * 
     * @param chooser
     *            okno wyboru plikow, ktorego wlasnoss zmienia sie, powoduje zmiane obrazu w tym podgladzie
     */
    public ImagePreviewerInFileChooser(final JFileChooser chooser)
    {
        setPreferredSize(new Dimension(100, 100));
        setBorder(BorderFactory.createEtchedBorder());

        chooser.addPropertyChangeListener(new PropertyChangeListener()
        {
            @Override
            public void propertyChange(final PropertyChangeEvent evt)
            {
                if(evt.getPropertyName().equals(JFileChooser.SELECTED_FILE_CHANGED_PROPERTY))
                {
                    // Uzytkownik wybral inny plik
                    final File file = (File) evt.getNewValue();

                    if(file == null)
                    {
                        setIcon(null);
                        return;
                    }

                    // Wczytanie obrazu jako ikony
                    ImageIcon icon = new ImageIcon(file.getPath());

                    // Skalowanie obrazu, jesli jest zbyt duzy na ikone
                    if(icon.getIconWidth() > getWidth())
                    {
                        icon = new ImageIcon(icon.getImage().getScaledInstance(getWidth(), -1, Image.SCALE_DEFAULT));
                    }

                    setIcon(icon);
                }
            }
        });
    }
}
