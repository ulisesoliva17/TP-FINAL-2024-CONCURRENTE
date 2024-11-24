import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Comedor {

    private CyclicBarrier[] mesa;
    private boolean[] mesaLlena;
    private int[] sentados;
    private int cantMesas;
    private int capacidad;
    private int cantVisitantes = 0;

    public Comedor(int cant) {
        cantMesas = cant;
        capacidad = cantMesas * 4;
        mesa = new CyclicBarrier[cantMesas];
        mesaLlena = new boolean[cantMesas];
        sentados = new int[cantMesas];

        for (int i = 0; i < cantMesas; i++) {
            mesa[i] = new CyclicBarrier(4);
        }

        for (int i = 0; i < cantMesas; i++) {
            mesaLlena[i] = false;
        }
    }

    public synchronized void llegaVisitante(int id) throws InterruptedException, BrokenBarrierException {

        while (cantVisitantes >= capacidad) {
            this.wait();
        }

        cantVisitantes++;
    }

    public synchronized int buscaMesa(int id) throws InterruptedException, BrokenBarrierException {

        int i = 0;
        int mesaAsignada=-1;

        System.out.println("Entra visitante " + id);

        // Busca mesa con espacio
        while (mesaAsignada==-1) {
            
            if (i == cantMesas) {
                i = 0;
            }

            if(mesaLlena[i]==false){
                mesaAsignada=i;
            }
            else{
                i++;
            }
        }

        sentados[i]++;

        if (sentados[i] == 4) {
            mesaLlena[i] = true;
        }
        // Encontro mesa con espacio
        System.out.println("Visitante " + id + " se sienta en mesa" + i);

        mesa[i].await();

        synchronized (this) {
            System.out.println("Visitante " + id + " come en mesa" + i);
        }

        return i;
    }

    public synchronized void dejaMesa(int mesa, int id) {

        sentados[mesa]--;

        if (sentados[mesa] == 0) {
            mesaLlena[mesa] = false;
        }
    }

    public synchronized void saleVisitante(int id) {

        System.out.println("Sale visitante" + id);
        
        cantVisitantes--;

        this.notify();

    }

}
