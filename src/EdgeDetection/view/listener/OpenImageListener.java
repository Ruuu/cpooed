package view.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.BlockingQueue;

import controller.event.BrokerActionEvent;
import controller.event.OpenImageEvent;

import view.dialog.ErrorDialog;

/**
 * Nasluchiwacz dla przycisku ladujacego nowy obraz z pliku
 * @author Piotr Róż
 */
public class OpenImageListener implements ActionListener
{
    /** kolejka blokujaca */
    private final BlockingQueue<BrokerActionEvent> blockingQueue;
    /** dialog informujacy użytkownika o wystapieniu bledu w dzialaniu aplikacji */
    private final ErrorDialog errorDialog;

    /**
     * Konstruktor
     * 
     * @param blockingQueue
     *            kolejka blokujaca
     */
    public OpenImageListener(final BlockingQueue<BrokerActionEvent> blockingQueue)
    {
        this.blockingQueue = blockingQueue;
        errorDialog = ErrorDialog.getErrorDialog();
    }

    @Override
    public void actionPerformed(final ActionEvent event)
    {
        try
        {
        	// wrzucenie zdarzenia do kolejki
            blockingQueue.put(new OpenImageEvent());
        }
        catch(final InterruptedException e)
        {
            errorDialog.showErrorDialog("Blad podczas wstawiania do kolejki!");
            System.exit(0);
        }
    }
}
