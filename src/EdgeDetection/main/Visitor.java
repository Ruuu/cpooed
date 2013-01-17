package main;

import controller.event.OurAlgorithmEvent;
import controller.event.CannyEvent;
import controller.event.ExitEvent;
import controller.event.OpenImageEvent;
import controller.event.SobelEvent;

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
     * Metoda wizytujaca {@link OurAlgorithmEvent}
     * 
     * @param basicEvent
     *            zdarzenie przycisku do otworzenia obrazu zmodyfikowanego
     */
    public void visit(OurAlgorithmEvent basicEvent);
    
    /**
     * Metoda wizytujaca {@link CannyEvent}
     * 
     * @param cannyEvent
     *            zdarzenie przycisku do otworzenia obrazu zmodyfikowanego
     */
    public void visit(CannyEvent cannyEvent);
    
    /**
     * Metoda wizytujaca {@link SobelEvent}
     * 
     * @param sobelEvent
     *            zdarzenie przycisku do otworzenia obrazu zmodyfikowanego
     */
    public void visit(SobelEvent sobelEvent);
	
    /**
     * Metoda wizytujaca {@link ExitEvent}
     * 
     * @param exitEvent
     *            zdarzenie przycisku do zamkniecia aplikacji
     */
    public void visit(ExitEvent exitEvent);
}
