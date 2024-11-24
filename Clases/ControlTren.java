import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class ControlTren {

    private static final int CAPACIDAD_TREN = 10; // Capacidad máxima del tren
    private static final int TIEMPO_ESPERA = 5;  // Tiempo máximo de espera en minutos

    private final BlockingQueue<String> filaVisitantes; // Fila de visitantes esperando abordar

    public ControlTren() {
        this.filaVisitantes = new LinkedBlockingQueue<>(CAPACIDAD_TREN);
    }

    // Método para que los visitantes se suban a la fila
    public boolean subirseFila(String visitante) {
        boolean agregado = false;
        try {
            agregado = filaVisitantes.offer(visitante, TIEMPO_ESPERA, TimeUnit.MINUTES);
            if (agregado) {
                System.out.println(visitante + " se unió a la fila.");
            } else {
                System.out.println(visitante + " no pudo unirse a la fila (tiempo o capacidad alcanzados).");
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Interrumpido mientras se unía a la fila: " + visitante);
        }
        return agregado;
    }

    // Método para iniciar un viaje
    public void iniciarViaje() {
        int visitantesAbordados = 0;

        try {
            System.out.println("Preparando el tren...");
            long inicioEspera = System.currentTimeMillis();
            long tiempoMaximoEspera = TimeUnit.MINUTES.toMillis(TIEMPO_ESPERA);

            while (visitantesAbordados < CAPACIDAD_TREN && (System.currentTimeMillis() - inicioEspera) < tiempoMaximoEspera) {
                String visitante = filaVisitantes.poll(tiempoMaximoEspera, TimeUnit.MILLISECONDS);
                if (visitante != null) {
                    System.out.println(visitante + " abordó el tren.");
                    visitantesAbordados++;
                }
            }

            System.out.println("El tren ha salido con " + visitantesAbordados + " visitantes.");
            Thread.sleep(3000); // Simular el recorrido del tren

            terminarViaje();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("El viaje fue interrumpido.");
        }
    }

    // Método para terminar un viaje y vaciar el tren
    private void terminarViaje() {
        System.out.println("El tren ha regresado. Los visitantes han bajado.");
        filaVisitantes.clear(); // Vaciar la fila por seguridad
    }
}

