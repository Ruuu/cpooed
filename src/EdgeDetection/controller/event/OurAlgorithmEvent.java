package controller.event;

import main.Visitable;
import main.Visitor;

/**
 * Jedna z klas opakowujacych dla kontrolera. Przekazywane sa tutaj dane z widoku (dotyczace przycisku ladujacego nowe zdjecie z folderu) do watku kontrolera.
 * @author mxw
 */
public class OurAlgorithmEvent implements BrokerActionEvent, Visitable
{
    public OurAlgorithmEvent()
    {
    }

    @Override
    public void accept(final Visitor visitor)
    {
        visitor.visit(this);
    }

}
