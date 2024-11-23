import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class Tren implements Runnable {

    private static final long TIEMPO_MAX_ESPERA = 5 * 60 * 1000; // 5 minutos en milisegundos

    public Tren() {
    }

    @Override
    public void run() {
        try {
            while (true) {
                System.out.println("El tren está esperando para llenar su capacidad o 5 minutos.");
                long inicioEspera = System.currentTimeMillis(); // Marca de tiempo inicial
                int pasajeros = 0;

                // Continuar mientras no se llene el tren o no pase el tiempo máximo
                while ((System.currentTimeMillis() - inicioEspera) < TIEMPO_MAX_ESPERA) {
                                        
                }

                // Partir el tren
                System.out.println("El tren parte con " + pasajeros + " pasajeros.");
                Thread.sleep(2000); // Simula el recorrido del tren
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
