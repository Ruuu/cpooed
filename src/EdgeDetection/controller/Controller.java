package controller;

import java.util.concurrent.BlockingQueue;

import main.Visitable;
import main.Visitor;
import model.Model;
import view.dialog.ErrorDialog;
import view.frame.DefaultViewChanger;
import controller.event.BrokerActionEvent;
import controller.event.ExitEvent;
import controller.event.OpenImageEvent;
import controller.event.TestModEvent;

/**
 * Glowna klasa kontrolera - odbiera informacje z widoku, przekazuje je do modelu, wprowadza zmiany na widoku
 * @author Piotr Róż
 * @since 2012-11-11
 */
public class Controller implements Visitor
{
    /** model */
    private final Model model;
    /** obiekt odpowiadajacy za komunikacje miedzy kontrolerem i widokiem */
    private final DefaultViewChanger defaultViewChanger;
    /** akcja, ktora zostanie obsluzona */
    private BrokerActionEvent eventQueue;
    /** dialog informujacy uzytkownika o wystapieniu bledu w dzialaniu aplikacji */
    private final ErrorDialog errorDialog;
    /** kolejka blokujaca */
    private final BlockingQueue<BrokerActionEvent> blockingQueue;

    /**
     * Konstruktor
     * 
     * @param blockingQueue
     *            kolejka blokujaca
     * @param model
     *            model opisujacy cala strukture logiczna programu
     * @param defaultViewChanger
     *            klasa opakowujaca funkcje do zmiany widoku
     */
    public Controller(final BlockingQueue<BrokerActionEvent> blockingQueue, final Model model, final DefaultViewChanger defaultViewChanger)
    {
        this.model = model;
        this.defaultViewChanger = defaultViewChanger;
        errorDialog = ErrorDialog.getErrorDialog();
        this.blockingQueue = blockingQueue;
    }

    /**
     * Funkcja uruchamiajaca watek kontrolera - wyjmujaca odpowiednie zdarzenia z kolejki blokujacej
     */
    public void runController()
    {
        while(true)
        {
            try
            {
            	/**
            	 * Wyjecie zdarzenia z kolejki
            	 */
                eventQueue = blockingQueue.take();
                ((Visitable) eventQueue).accept(this);
            }
            catch(final InterruptedException e)
            {
                errorDialog.showErrorDialog("B��d podczas wyjmowania z kolejki!");
                System.exit(0);
            }
        }
    }
    
	@Override
	public void visit(OpenImageEvent openImageEvent)
	{
        final String imagePath = defaultViewChanger.openNewImageFromFile();
        
        model.setCurrentImagePath(imagePath);
        
        defaultViewChanger.showMainImage(imagePath);
	}
	
	@Override
	public void visit(TestModEvent testModEvent)
	{
		// Wykonanie operacji algorytmu i zwrocenie sciezki do nowego obrazka
		String imagePath = "C:\\Users\\Ru\\Desktop\\modlinbus.png";
        defaultViewChanger.showModImage(imagePath);
	}

	@Override
	public void visit(ExitEvent exitEvent)
	{
		System.exit(0);
	}
}