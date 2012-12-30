package controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Savepoint;
import java.util.concurrent.BlockingQueue;

import javax.imageio.ImageIO;

import Sobel.Sobel;

import Canny.Canny;

import main.Visitable;
import main.Visitor;
import model.Model;
import view.dialog.ErrorDialog;
import view.frame.DefaultViewChanger;
import controller.event.BrokerActionEvent;
import controller.event.CannyEvent;
import controller.event.ExitEvent;
import controller.event.OpenImageEvent;
import controller.event.SobelEvent;
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
        
        if(imagePath != null)
        {
	        model.setCurrentImagePath(imagePath);
	        
	        defaultViewChanger.showMainImage(imagePath);
        }
	}
	
	@Override
	public void visit(TestModEvent testModEvent)
	{
		// Wykonanie operacji algorytmu i zwrocenie sciezki do nowego obrazka
		String imagePath = "C:\\Users\\Ru\\Desktop\\modlinbus.png";
        defaultViewChanger.showModImage(imagePath);
	}
	
	@Override
	public void visit(CannyEvent cannyEvent)
	{
		String low_treshold_str = defaultViewChanger.showCannyParametersDialog(true);
		Float low_treshold = 0.0f;
		try 
		{
			if(low_treshold_str == null)
				throw new NumberFormatException();
			low_treshold = new Float(low_treshold_str);
		} 
		catch (NumberFormatException e) 
		{
			System.out.println("Przyjmuje wartość domyślną low_treshold: " + 0.5f);
			errorDialog.showErrorDialog("Błędnie wpisania wartość! Przyjmuje wartość domyślną low_treshold: " + 0.5f);
			low_treshold = 0.5f;
		}
	
		String high_treshold_str = defaultViewChanger.showCannyParametersDialog(false);
		Float high_treshold = 1.0f;
		try 
		{
			if(high_treshold_str == null)
				throw new NumberFormatException();
			high_treshold = new Float(high_treshold_str);
		} 
		catch (NumberFormatException e) 
		{
			System.out.println("Przyjmuje wartość domyślną high_treshold: " + 1.0f);
			errorDialog.showErrorDialog("Błędnie wpisania wartość! Przyjmuje wartość domyślną high_treshold: " + 1.0f);
			high_treshold = 1.0f;
		}
		
		Canny canny = new Canny(low_treshold, high_treshold);
		BufferedImage source;
		try 
		{
			System.out.println("Wczytuje plik źródłowy...");
			source = ImageIO.read(new File(model.getCurrentImagePath()));
			
			System.out.println("Uruchamiam algorytm...");
			BufferedImage canny_image = canny.process(source);
			
			String output_name = "output/canny.png";
			saveImageToFile(output_name, canny_image);
		    
		    System.out.println("Wyświetlam zdjęcie...");
		    defaultViewChanger.showModImage(output_name);
		} 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	@Override
	public void visit(SobelEvent sobelEvent)
	{
		Sobel sobel = new Sobel();
		BufferedImage source;
		try 
		{
			System.out.println("Wczytuje plik źródłowy...");
			source = ImageIO.read(new File(model.getCurrentImagePath()));
			
			System.out.println("Uruchamiam algorytm...");
			BufferedImage canny_image = sobel.process(source);
			
			String output_name = "output/sobel.png";
			saveImageToFile(output_name, canny_image);
		    
		    System.out.println("Wyświetlam zdjęcie...");
		    defaultViewChanger.showModImage(output_name);
		} 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}

	@Override
	public void visit(ExitEvent exitEvent)
	{
		System.exit(0);
	}
	
	private void saveImageToFile(String image_path, BufferedImage image) throws IOException
	{
		System.out.println("Zapisuje plik wynikowy do pliku...");
		File outputfile = new File(image_path);
	    ImageIO.write(image, "png", outputfile);
	}
}