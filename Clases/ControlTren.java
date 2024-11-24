import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ControlTren {

    private final int capacidadMax;
    private boolean trenAndando=false;
    private final BlockingQueue<Visitante> cola;


    public ControlTren(int cap) {        
        capacidadMax = cap;
        cola = new ArrayBlockingQueue<>(cap);
    }


    public void llegaVisitante(Visitante visita) throws InterruptedException{
        cola.add(visita);
    }

    public void arrancaTren(){
        trenAndando=true;
    }
}

