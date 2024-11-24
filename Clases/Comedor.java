import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Comedor {
    
    private CyclicBarrier[] mesa;
    private boolean[] mesaLlena;
    private int[] sentados;
    private int cantMesas;
    private int capacidad;
    private int cantVisitantes = 0;


    public Comedor(int cant){
        cantMesas = cant;
        capacidad = cantMesas*4;

        for(int i=0; i < cantMesas; i++){
            mesa[i] = new CyclicBarrier(4);
        }

        for(int i=0; i < cantMesas; i++){
            mesaLlena[i] = false;
        }
    }


    public synchronized int llegaVisitante(int id) throws InterruptedException, BrokenBarrierException{
        
        int i=0;

        if(cantVisitantes == capacidad){
            this.wait();
        }

        cantVisitantes++;

        System.out.println("Llega visitante "+id);

        //Busca mesa con espacio
        while (mesaLlena[i]==true) {
            i++;
        }
        
        //Encontro mesa con espacio
        System.out.println("Visitante " +id+ " se sienta en mesa"+i);

        mesa[i].await();
        System.out.println("[CLASE COMEDOR]");
        mesaLlena[i] = true;
        sentados[i]++;

        System.out.println("Visitante " +id+ " come en mesa"+i);

        return i;
    }


    public void dejaMesa(int i, int id){

        sentados[i]--;
        if(sentados[i]==0){
            mesaLlena[i] = false;
        }
        
        cantVisitantes--;

        System.out.println("Sale visitante"+id);
    }



}
