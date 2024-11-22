import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class Tren {
BlockingQueue colaTren = new ArrayBlockingQueue(10);

private long tiempoInicio = System.currentTimeMillis();


public synchronized void abordarTren(int visitante) throws InterruptedException {
    if (colaTren.isEmpty()) {
        // Si la cola está vacía, se registra el tiempo de llegada del primer visitante
        tiempoInicio = System.currentTimeMillis();
    }

    colaTren.put(visitante);
    System.out.println(visitante + " abordó el tren. Personas en la cola: " + colaTren.size());
    System.out.println("TiempoTranscurrido: "+tiempoInicio);
    if (colaTren.size() == 10 || seCumplioTiempo()) {
        partirTren(); // El tren parte cuando está lleno o se cumple el tiempo
    }
}

    private boolean seCumplioTiempo() {
        long tiempoActual = System.currentTimeMillis();
        return (tiempoActual - tiempoInicio) >= TimeUnit.MINUTES.toMillis(5);
    }
    
    private synchronized void partirTren() {
    System.out.println("El tren parte con " + colaTren.size());

    colaTren.clear(); // Vacía el tren
    tiempoInicio = System.currentTimeMillis(); // Reinicia el temporizador
    notifyAll(); // Notifica a cualquier hilo en espera (opcional)
}

}
