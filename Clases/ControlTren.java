import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ControlTren {
    
    private final int capacidadMax;
    private BlockingQueue<Visitante> colaVisitantes = new ArrayBlockingQueue<>(10);
    
    public ControlTren(int cap) {        
        capacidadMax = cap;
    }

}
