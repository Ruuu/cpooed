package main;

import controller.event.ExitEvent;

/**
 * Interfejs implementujay interfejs wizytatora
 * @author Piotr Róż
 * @since 2012-11-11
 */
public interface Visitor
{
    /**
     * Metoda wizytujaca {@link ExitEvent}
     * 
     * @param exitEvent
     *            zdarzenie przycisku do zamkniecia aplikacji
     */
    public void visit(ExitEvent exitEvent);
}
