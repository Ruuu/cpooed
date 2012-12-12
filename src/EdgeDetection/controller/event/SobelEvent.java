package controller.event;

import main.Visitable;
import main.Visitor;

/**
 * Jedna z klas opakowujacych dla kontrolera. Przekazywane sa tutaj dane z widoku do watku kontrolera.
 * @author Piotr Róż
 */
public class SobelEvent implements BrokerActionEvent, Visitable
{
    public SobelEvent()
    {
    }

    @Override
    public void accept(final Visitor visitor)
    {
        visitor.visit(this);
    }

}
