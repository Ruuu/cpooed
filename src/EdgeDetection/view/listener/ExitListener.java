package view.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.BlockingQueue;

import view.dialog.ErrorDialog;

import controller.event.BrokerActionEvent;
import controller.event.ExitEvent;

/**
 * Nasluchiwacz dla przycisku do zamykania aplikacji
 * @author Piotr Róż
 * @since 2012-11-11
 */
public class ExitListener implements ActionListener
{
    /** kolejka blokujaca */
    private final BlockingQueue<BrokerActionEvent> blockingQueue;
    /** dialog informujacy uzytkownika o wystapieniu bledu w dzialaniu aplikacji */
    private final ErrorDialog errorDialog;

    /**
     * Konstruktor
     * 
     * @param blockingQueue
     *            kolejka blokujaca
     */
    public ExitListener(final BlockingQueue<BrokerActionEvent> blockingQueue)
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
            blockingQueue.put(new ExitEvent());
        }
        catch(final InterruptedException e)
        {
            errorDialog.showErrorDialog("B��d podczas wstawiania do kolejki!");
            System.exit(0);
        }
    }
}
