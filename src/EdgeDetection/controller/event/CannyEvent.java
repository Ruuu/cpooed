package controller.event;

import main.Visitable;
import main.Visitor;

/**
 * Jedna z klas opakowujacych dla kontrolera. Przekazywane sa tutaj dane z widoku do watku kontrolera.
 * @author Piotr Róż
 */
public class CannyEvent implements BrokerActionEvent, Visitable
{
    public CannyEvent()
    {
    }

    @Override
    public void accept(final Visitor visitor)
    {
        visitor.visit(this);
    }

}
