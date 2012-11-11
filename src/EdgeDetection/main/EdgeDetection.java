package main;

import java.util.concurrent.ArrayBlockingQueue;

import javax.swing.UIManager;

import view.View;
import view.frame.DefaultViewChanger;

import model.Model;

import controller.Controller;
import controller.event.BrokerActionEvent;


/**
 * Glowna klasa programu tworzaca podstawowe obiekty wchodzace w jego sklad
 * @author Piotr Róż
 * @since 2012-11-11
 */
public class EdgeDetection 
{

	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		int QUEUE_SIZE = 30;
		ArrayBlockingQueue<BrokerActionEvent> blockingEventQueue = new ArrayBlockingQueue<BrokerActionEvent>(QUEUE_SIZE);
		
		try 
		{
		    UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		}
		catch (Exception e) 
		{
		      System.err.println("Błąd podczas ładowania programu!");
		}
		
		Model model = new Model();
		View view = new View(blockingEventQueue);
		DefaultViewChanger defaultViewChanger = new DefaultViewChanger(view.getMainAppFrame());
		new Controller(blockingEventQueue, model, defaultViewChanger).runController();
	}
}