import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class ControlTren {
    //asd
    private static final int CAPACIDAD_TREN = 10;
    private static final long TIEMPO_ESPERA = 5; // en minutos
    private final BlockingQueue <Integer> cola = new LinkedBlockingQueue<>(CAPACIDAD_TREN);

    private long tiempoInicio = System.currentTimeMillis();

    public synchronized void abordarTren(String visitante) throws InterruptedException {

        //if(andando) { // Usar WHILE y contador 
        //    this.wait(); // Espera a que tren vuelva
        //}

        if (cola.isEmpty()) {
            // Si la cola está vacía, se registra el tiempo de llegada del primer visitante
            tiempoInicio = System.currentTimeMillis();
            notify(); // Despierta tren
        }

        cola.put(visitante);

        System.out.println(visitante + " abordó el tren. Personas en la cola: " + cola.size());

    }

    public synchronized void chequeoSalida() throws InterruptedException{
        
        this.wait(); // Espera a que llegue el primer visitante
        
        while(andando==false){
            if (cola.size() == CAPACIDAD_TREN || seCumplioTiempo()) {
                partirTren(); // El tren parte cuando está lleno o se cumple el tiempo
            }
        }
    }

    public synchronized boolean seCumplioTiempo() {
        long tiempoActual = System.currentTimeMillis();
        return (tiempoActual - tiempoInicio) >= TimeUnit.SECONDS.toMillis(TIEMPO_ESPERA);
    }

    public synchronized void partirTren() {
        andando=true;
        System.out.println("El tren parte con " + cola.size() + " visitantes: " + cola);
        cola.clear(); // Vacía el tren
        //notifyAll(); // Notifica a cualquier hilo en espera (opcional)
    }

    public synchronized void llegaTren() {
        andando=false;
        notifyAll();
    }

}


