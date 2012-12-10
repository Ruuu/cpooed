package controller.event;

import main.Visitable;
import main.Visitor;

/**
 * Jedna z klas opakowujacych dla kontrolera. Przekazywane sa tutaj dane z widoku (dotyczace przycisku ladujacego nowe zdjecie z folderu) do watku kontrolera.
 * @author Piotr Róż
 */
public class OpenImageEvent implements BrokerActionEvent, Visitable
{
    public OpenImageEvent()
    {
    }

    @Override
    public void accept(final Visitor visitor)
    {
        visitor.visit(this);
    }

}
