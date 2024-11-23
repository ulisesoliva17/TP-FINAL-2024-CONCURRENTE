import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class Tren {
BlockingQueue colaTren = new ArrayBlockingQueue(10);

private long inicioEspera = System.currentTimeMillis();
private final int CAPACIDAD_MAXIMA = 10;
private final long TIEMPO_MAX_ESPERA = 5 * 6 * 1000; //5 representa cant minutos, cada min es representado por 6 segundos 


public synchronized void abordarTren(int visitante) throws InterruptedException {
    if (colaTren.isEmpty()) {
        // Si la cola está vacía, se registra el tiempo de llegada del primer visitante
        inicioEspera = System.currentTimeMillis();
    }

    colaTren.put(visitante);
    System.out.println(visitante + " abordó el tren. Personas en la cola: " + colaTren.size());
    System.out.println("TiempoTranscurrido: "+(System.currentTimeMillis()-inicioEspera));
    if (colaTren.size() == CAPACIDAD_MAXIMA || chequeoTiempo()) {
        partirTren(); // El tren parte cuando está lleno o se cumple el tiempo
    }
}

    private boolean chequeoTiempo() {
        long tiempoActual = System.currentTimeMillis();

        while (colaTren.size() < CAPACIDAD_MAXIMA ||
        (System.currentTimeMillis() - inicioEspera) < TIEMPO_MAX_ESPERA) {
    Visitante visitante = cola.poll(1, TimeUnit.SECONDS); // Espera por un visitante
    if (visitante != null) {
        System.out.println("El visitante " + visitante.getNombre() + " abordó el tren.");
        pasajeros++;
    }
}

        return (tiempoActual - tiempoInicio) >= TimeUnit.MINUTES.toMillis(5);
    }
    
    private synchronized void partirTren() {
    System.out.println("El tren parte con " + colaTren.size());

    colaTren.clear(); // Vacía el tren
    tiempoInicio = System.currentTimeMillis(); // Reinicia el temporizador
    notifyAll(); // Notifica a cualquier hilo en espera (opcional)
}

}
