import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Comedor {

    private CyclicBarrier[] mesas;
    private boolean[] mesaLlena;
    private int[] sentados;
    private int cantMesas;
    private int capacidad;
    private int visitantesDentro = 0;

    public Comedor(int cant) {
        cantMesas = cant;
        capacidad = cantMesas * 4;
        mesas = new CyclicBarrier[cantMesas];
        mesaLlena = new boolean[cantMesas];
        sentados = new int[cantMesas];

        for (int i = 0; i < cantMesas; i++) {
            mesas[i] = new CyclicBarrier(4);
        }

        for (int i = 0; i < cantMesas; i++) {
            mesaLlena[i] = false;
        }
    }

    public synchronized void llegaVisitante(int id) throws InterruptedException, BrokenBarrierException {

        System.out.println("Visitante " + id + " entra a wait");
        if (visitantesDentro == capacidad) {
            this.wait();
        }
        visitantesDentro++;
        System.out.println("Visitante " + id + " sale de wait");
        // System.out.println("Entra visitante " + id);

    }

    public int buscaMesa(int id) throws InterruptedException, BrokenBarrierException {

        int mesaAsignada;

        synchronized (this) {

            mesaAsignada = eleccionMesa(id);

            // Encontro mesa con espacio
            // System.out.println("Visitante " + id + " se sienta en mesa" + mesaAsignada);

            sentados[mesaAsignada]++;

            if (sentados[mesaAsignada] == 4) {
                mesaLlena[mesaAsignada] = true;
            }
        }
        System.out.println("Visitante " + id + " entra a await");
        mesas[mesaAsignada].await();
        System.out.println("Visitante " + id + " sale de await");
        // System.out.println("Visitante " + id + " come en mesa" + mesaAsignada);

        return mesaAsignada;
    }

    public int eleccionMesa(int id) {
        int i = 0;
        int mesaAsignada = -1;
        int candidata = -1;

        // Busca mesa con espacio
        System.out.println("Visitante " + id + " entra a while");
        while (mesaAsignada == -1) {
            System.out.println("Visitante" + id + "Bucleando");
            if (!mesaLlena[i]) {
                // Encuentra mesa con espacio
                if (candidata == -1) {
                    candidata = i;
                } else if (candidata != -1 && mesas[i].getNumberWaiting() > mesas[candidata].getNumberWaiting()) {
                    // Si hay una mesa con mas visitantes, la elige como candidata
                    candidata = i;
                }
            }

            // Sigue buscando
            i++;

            if (i == cantMesas) {
                // Chequeo todas las mesas
                
                // Habia al menos una con lugar

                mesaAsignada = candidata; // Elije la mesa mas ocupada

                i = 0;

            }

        }
        System.out.println("Visitante " + id + " sale de while");
        return mesaAsignada;
    }

    public synchronized void dejaMesa(int mesa, int id) {

        sentados[mesa]--;
        //HACER CyclicBarrier salida
        mesaLlena[mesa] = false;
        
    }

    public synchronized void saleVisitante(int id) {

        // System.out.println("Sale visitante " + id);

        visitantesDentro--;

        this.notify();

    }

}