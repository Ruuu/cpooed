package controller.event;

import main.Visitable;
import main.Visitor;

/**
 * Jedna z klas opakowujacych dla kontrolera. Przekazywane sa tutaj dane z widoku (dotyczace przycisku wyjscia z programu) do watku kontrolera.
 * @author Piotr Róż
 * @since 2012-11-11
 */
public class ExitEvent implements BrokerActionEvent, Visitable
{
    public ExitEvent()
    {
    }

    @Override
    public void accept(final Visitor visitor)
    {
        visitor.visit(this);
    }

}