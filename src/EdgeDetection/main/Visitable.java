package main;

/**
 * Interfejs, ktory umozliwia odwiedzenie obiektu przez Visitor'a
 * @author Piotr Róż
 * @since 2012-11-11
 */
public interface Visitable
{
    /**
     * Umozliwienie odwiedzenia obiektow przez obiekty implementujace interfejs Visitor'a
     * 
     * @param visitor
     */
    public void accept(Visitor visitor);
}