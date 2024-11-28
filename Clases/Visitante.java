import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.TimeoutException;

public class Visitante implements Runnable {
    private int id;
    private String nombre;
    private final Comedor comedor;
    private final AreaJuegos juegos;
    private int puntos;
    private final ControlTren control;

    public Visitante(int idVisitante, Comedor com, AreaJuegos area, ControlTren c) {
        this.id = idVisitante;
        this.comedor = com;
        this.juegos = area;
        this.puntos = 0;
        this.control = c;
    }

    private void comiendo() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void run(){
        int num;
            
            try {
            /*
                comedor.llegaVisitante(id);
                num=comedor.buscaMesa(id);
                
                if(num>=0){
                    this.comiendo();                
                    comedor.dejaMesa(num, id);
                }
                
                comedor.saleVisitante(id);
                
            
            // Intercambiar ficha y jugar
                juegos.intercambiarFicha(id);
            // Obtener puntos del juego
                this.puntos = juegos.jugar(id);
            // Recibir premio basado en los puntos
                juegos.recibirPremio(puntos,id);
            */
            
            control.abordarTren(nombre);

        }
        catch(Exception e){

        }
        /* 
        catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (TimeoutException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        */    
        
    }

    public int getPuntos() {
        return puntos;
    }
}
