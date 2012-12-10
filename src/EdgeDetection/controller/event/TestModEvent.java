package controller.event;

import main.Visitable;
import main.Visitor;

/**
 * Jedna z klas opakowujacych dla kontrolera. Przekazywane sa tutaj dane z widoku (dotyczace przycisku ladujacego nowe zdjecie z folderu) do watku kontrolera.
 * @author Piotr Róż
 */
public class TestModEvent implements BrokerActionEvent, Visitable
{
    public TestModEvent()
    {
    }

    @Override
    public void accept(final Visitor visitor)
    {
        visitor.visit(this);
    }

}
