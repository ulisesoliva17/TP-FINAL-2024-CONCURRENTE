/*import java.util.concurrent.Exchanger;
import java.util.concurrent.Semaphore;

public class AreaJuegos {
    private Exchanger<String> exchanger;
    private Semaphore sem = new Semaphore(0);

    public AreaJuegos(Exchanger<String> exchanger) {
        this.exchanger = exchanger;
    }

    public void intercambiarFicha(int id) throws InterruptedException {
        // Intercambiar ficha con el visitante
        sem.release();

        String ficha = exchanger.exchange("Ficha del visitante: " + id);
        System.out.println("Encargado recibió: " + ficha);
    }

    public int jugar(int id) {
        // Lógica del juego para obtener puntos
        int puntos = (int) (Math.random() * 100); // Ejemplo de puntos aleatorios
        System.out.println("El visitante" + id + " obtuvo " + puntos + " puntos.");
        return puntos;
    }

    public void recibirPremio(int puntos, int id) throws InterruptedException {
        // Determinar premio basado en los puntos
        sem.acquire();

        String premio;
        if (puntos > 80) {
            premio = "Gran premio";
        } else if (puntos > 50) {
            premio = "Premio medio";
        } else {
            premio = "Premio pequeño";
        }
        // Intercambiar premio con el visitante
        exchanger.exchange(premio);
        System.out.println("El visitante" + id + " recibio el premio: " + premio);
    }
}*/
import java.util.concurrent.Exchanger;
import java.util.concurrent.Semaphore;

public class AreaJuegos {
    private final Exchanger<String> ficha;
    private final Semaphore semEntrada;

    public AreaJuegos() {
        ficha = new Exchanger<String>();
        semEntrada = new Semaphore(0);// asegura que un visitante notifique a un encargado para cambiar fichas
    }

    public int jugar() throws InterruptedException {
        semEntrada.release(); // notifica al empleado para poder recibir una ficha
        ficha.exchange(Thread.currentThread().getName() + " cambia una ficha");
        int randomSleep = (int) (Math.random() * 10) * 1000;
        Thread.sleep(randomSleep); // simula tiempo de juego
        return randomSleep;
    }

    public void darFicha() throws InterruptedException {
        // metodo ejecutado por los encargados del area
        semEntrada.acquire();
        String pantalla = ficha.exchange("");
        System.out.println(pantalla);
    }
}