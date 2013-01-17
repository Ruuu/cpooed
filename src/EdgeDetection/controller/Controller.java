package controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.BlockingQueue;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

import main.Visitable;
import main.Visitor;
import model.Model;
import view.dialog.ErrorDialog;
import view.frame.DefaultViewChanger;
import Canny.Canny;
import OurAlgorithm.OurAlgorithm;
import Sobel.Sobel;
import controller.event.BrokerActionEvent;
import controller.event.CannyEvent;
import controller.event.ExitEvent;
import controller.event.OpenImageEvent;
import controller.event.OurAlgorithmEvent;
import controller.event.SobelEvent;

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
                errorDialog.showErrorDialog("Błąd podczas wyjmowania z kolejki!");
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
	

	/**
	 * Prosty zaimplementowany wlasnorecznie algorytm
	 */
	@Override
	public void visit(OurAlgorithmEvent basicEvent){
		OurAlgorithm alg = new OurAlgorithm();
		BufferedImage source;
		try {
			if (model.getCurrentImagePath() == null || model.getCurrentImagePath().isEmpty() ){
				throw new Exception("Brak pliku wejściowego!");
			}
			
			//popros o podanie parametru progu wariancji
			String treshold = defaultViewChanger.showOurAlgorithmThresholdParameterDialog(OurAlgorithm.getStdevTreshold());
			try 
			{
				if(treshold == null || treshold.isEmpty() )
					throw new NumberFormatException();
				OurAlgorithm.setStdevTreshold( new Float(treshold) );
			} 
			catch (NumberFormatException e) 
			{
				System.out.println("Przyjmuje wartość domyślną treshold = " + OurAlgorithm.getStdevTreshold() );
				errorDialog.showErrorDialog("Błędnie wpisania wartość! Przyjmuje wartość domyślną treshold = " + OurAlgorithm.getStdevTreshold() );
			}

			// popros o podanie parametru zasiegu sasiedztwa
			String range = defaultViewChanger.showOurAlgorithmRangeParameterDialog(OurAlgorithm.getStdevRange() );
			try 
			{
				if(range == null || range.isEmpty() )
					throw new NumberFormatException();
				OurAlgorithm.setStdevRange( new Integer(range) );
			} 
			catch (NumberFormatException e) 
			{
				System.out.println("Przyjmuje wartość domyślną range = " + OurAlgorithm.getStdevRange() );
				errorDialog.showErrorDialog("Błędnie wpisania wartość! Przyjmuje wartość domyślną range = " + OurAlgorithm.getStdevRange() );
			}
			
			
			
			System.out.println("Wczytuje plik źródłowy...");
			source = ImageIO.read( new File(model.getCurrentImagePath()) );
			
			System.out.println("Uruchamiam algorytm...");
			BufferedImage image = alg.process(source);
			
			String outputName = "output/basic.png";
			saveImageToFile(outputName, image);
		    
		    System.out.println("Wyświetlam zdjęcie...");
		    defaultViewChanger.showModImage(outputName);

		} 
		catch (Exception e) 
		{
			if ( defaultViewChanger != null )
				defaultViewChanger.showInformationDialog(e.getMessage(), "Błąd", JOptionPane.ERROR_MESSAGE );
				
			e.printStackTrace();
		} 
	}
	
	@Override
	public void visit(CannyEvent cannyEvent)
	{
		try 
		{
			if (model.getCurrentImagePath() == null || model.getCurrentImagePath().isEmpty() ){
				throw new Exception("Brak pliku wejściowego!");
			}
			
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

			System.out.println("Wczytuje plik źródłowy...");
			source = ImageIO.read(new File(model.getCurrentImagePath()));
			
			System.out.println("Uruchamiam algorytm...");
			BufferedImage canny_image = canny.process(source);
			
			String output_name = "output/canny.png";
			saveImageToFile(output_name, canny_image);
		    
		    System.out.println("Wyświetlam zdjęcie...");
		    defaultViewChanger.showModImage(output_name);
		} 
		catch (Exception e) 
		{
			if ( defaultViewChanger != null )
				defaultViewChanger.showInformationDialog(e.getMessage(), "Błąd", JOptionPane.ERROR_MESSAGE );
				
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
			if (model.getCurrentImagePath() == null || model.getCurrentImagePath().isEmpty() ){
				throw new Exception("Brak pliku wejściowego!");
			}
			
			System.out.println("Wczytuje plik źródłowy...");
			source = ImageIO.read(new File(model.getCurrentImagePath()));
			
			System.out.println("Uruchamiam algorytm...");
			BufferedImage canny_image = sobel.process(source);
			
			String output_name = "output/sobel.png";
			saveImageToFile(output_name, canny_image);
		    
		    System.out.println("Wyświetlam zdjęcie...");
		    defaultViewChanger.showModImage(output_name);
		} 
		catch (Exception e) 
		{
			if ( defaultViewChanger != null )
				defaultViewChanger.showInformationDialog(e.getMessage(), "Błąd", JOptionPane.ERROR_MESSAGE );
				
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