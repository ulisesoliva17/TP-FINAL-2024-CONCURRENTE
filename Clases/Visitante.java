import java.util.concurrent.Exchanger;

public class Visitante implements Runnable {
    private int id;
    private String nombre;
    private Comedor comedor;
    private Exchanger<String> exchanger;
    private int puntos;
    public Visitante (int idVisitante,Comedor com,Exchanger<String> exchanger) {
        id= idVisitante;
        comedor=com;
        this.exchanger = exchanger; this.puntos = 0;
    }
    private void comiendo() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void run(){
        int num=0;
        
            try {
                /*num = comedor.llegaVisitante(id);
                this.comiendo();
                comedor.dejaMesa(num, id);*/
                String ficha = exchanger.exchange("Ficha del visitante"); 
                System.out.println("Visitante " + id + " intercambió una ficha y recibió: " + ficha);
                 // Jugar y obtener puntos 
                 this.puntos = (int) (Math.random() * 100); 
                 // Ejemplo de puntos aleatorios 
                 System.out.println("Visitante " + id + " obtuvo " + puntos + " puntos."); 
                 // Recibir premio basado en los puntos 
                 String premio = exchanger.exchange("Puntos: " + puntos); 
                 System.out.println("Visitante " + id + " recibió: " + premio);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } /*catch (BrokenBarrierException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }*/
            
        
    }

    public String getNombre(){
        return nombre;
    }

}
