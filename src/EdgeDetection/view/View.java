package view;

import java.util.concurrent.BlockingQueue;
import view.frame.MainApplicationFrame;
import controller.event.BrokerActionEvent;

/**
 * Glowna klasa podstawowego widoku programu
 * @author Piotr Róż
 * @since 2012-11-11
 */
public class View
{
    /** kolejka blokujaca */
    BlockingQueue<BrokerActionEvent> blockingQueue;
    /** glowne okno programu widoczne przez uzytkownika */
    private final MainApplicationFrame applicationFrame;
    
    /**
     * Konstruktor
     * 
     * @param blockingQueue
     *            kolejka blokujaca
     */
    public View(final BlockingQueue<BrokerActionEvent> blockingQueue)
    {
        this.blockingQueue = blockingQueue;
        applicationFrame = new MainApplicationFrame(this);
        applicationFrame.addMultipleListener(blockingQueue);
    }

    /**
     * Zwrocenie glownego okna programu
     * 
     * @return glowne okno programu
     */
    public MainApplicationFrame getMainAppFrame()
    {
        return applicationFrame;
    }
}