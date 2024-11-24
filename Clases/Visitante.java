import java.util.concurrent.BrokenBarrierException;

public class Visitante implements Runnable {
    private int id;
    private String nombre;
    private Comedor comedor;

    public Visitante (int idVisitante,Comedor com) {
        id= idVisitante;
        comedor=com;
    }
    private void comiendo() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void run(){
        int num=0;
        while (true) {
            try {
                num = comedor.llegaVisitante(id);
                this.comiendo();
                comedor.dejaMesa(num, id);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            
        }
    }

    public String getNombre(){
        return nombre;
    }

}
