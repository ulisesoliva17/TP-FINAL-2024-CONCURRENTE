import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class ControlTren {
    //asd
    //nv
    private static final int CAPACIDAD_TREN = 10;
    private static final long TIEMPO_ESPERA = 5; // en minutos
    private final BlockingQueue <String> cola = new LinkedBlockingQueue<>(CAPACIDAD_TREN);

    private long tiempoInicio = System.currentTimeMillis();

    public synchronized void abordarTren(String visitante) throws InterruptedException {
        tiempoInicio = System.currentTimeMillis();

        cola.put(visitante);
        System.out.println(visitante + " abordó el tren. Personas en la cola: " + cola.size());
        System.out.println("TiempoInicio: "+tiempoInicio);
        if (cola.size() == CAPACIDAD_TREN || seCumplioTiempo()) {
            partirTren(); // El tren parte cuando está lleno o se cumple el tiempo
        }
    }

    private synchronized void partirTren() {
        System.out.println("El tren parte con " + cola.size() + " visitantes: " + cola);
        cola.clear(); // Vacía el tren
        tiempoInicio = System.currentTimeMillis(); // Reinicia el temporizador
        //notifyAll(); // Notifica a cualquier hilo en espera (opcional)
    }

    private boolean seCumplioTiempo() {
        long tiempoActual = System.currentTimeMillis();
        return (tiempoActual - tiempoInicio) >= TimeUnit.MINUTES.toMillis(TIEMPO_ESPERA);
    }
}


