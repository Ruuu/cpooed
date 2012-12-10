package main;

import controller.event.ExitEvent;
import controller.event.OpenImageEvent;
import controller.event.TestModEvent;

/**
 * Interfejs implementujay interfejs wizytatora
 * @author Piotr Róż
 * @since 2012-11-11
 */
public interface Visitor
{
    /**
     * Metoda wizytujaca {@link OpenImageEvent}
     * 
     * @param openImageEvent
     *            zdarzenie przycisku do otworzenia nowego obrazu
     */
    public void visit(OpenImageEvent openImageEvent);
    
    /**
     * Metoda wizytujaca {@link TestModEvent}
     * 
     * @param testModEvent
     *            zdarzenie przycisku do otworzenia obrazu zmodyfikowanego
     */
    public void visit(TestModEvent testModEvent);
	
    /**
     * Metoda wizytujaca {@link ExitEvent}
     * 
     * @param exitEvent
     *            zdarzenie przycisku do zamkniecia aplikacji
     */
    public void visit(ExitEvent exitEvent);
}
