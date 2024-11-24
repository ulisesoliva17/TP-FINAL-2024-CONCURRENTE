import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Comedor {
    //asd
    private CyclicBarrier[] mesa;
    private boolean[] mesaLlena;
    private int[] sentados;
    private int cantMesas;
    private int capacidad;
    private int cantVisitantes = 0;


    public Comedor(int cant){
        cantMesas = cant;
        capacidad = cantMesas*4;
        mesa = new CyclicBarrier[cantMesas];
        mesaLlena = new boolean[cantMesas];
        sentados = new int[cantMesas];
        
        for(int i=0; i < cantMesas; i++){
            mesa[i] = new CyclicBarrier(4);
        }

        for(int i=0; i < cantMesas; i++){
            mesaLlena[i] = false;
        }
    }


    public int llegaVisitante(int id) throws InterruptedException, BrokenBarrierException{
        
        int i=0;

        synchronized(this){

            if(cantVisitantes == capacidad){
                this.wait();
            }

            cantVisitantes++;

            System.out.println("Llega visitante "+id);

            //Busca mesa con espacio
            while (mesaLlena[i]==true && i<cantMesas) {
                i++;

                if(i==cantMesas){
                    i=0;
                    //System.out.println("ENTRE !!!");
                }
            }
            
            sentados[i]++;

            if(sentados[i]==4){
                mesaLlena[i] = true; 
            }
            //Encontro mesa con espacio
            System.out.println("Visitante " +id+ " se sienta en mesa"+i);
        }
    
        
            mesa[i].await();

        synchronized(this){
            System.out.println("Visitante " +id+ " come en mesa"+i);
        }
        

        return i;
    }


    public synchronized void dejaMesa(int i, int id){
        System.out.println("Sale visitante"+id);
        
        sentados[i]--;
        if(sentados[i]==0){
            mesaLlena[i] = false;
        }

        cantVisitantes--;

        this.notify();

        
    }



}


