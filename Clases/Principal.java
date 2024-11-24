import java.util.concurrent.Exchanger;
public class Principal {
    public static void main(String[] args) {
        Visitante visitante[] = new Visitante[20];
        Thread  hiloVisitante[] = new Thread[20];
        Comedor comedor= new Comedor(5);
        Exchanger<String> exchanger = new Exchanger<>();
        
        

        for (int i = 0; i < hiloVisitante.length; i++) {
            visitante[i] = new Visitante(i+1,comedor,exchanger);
            hiloVisitante[i] = new Thread(visitante[i]);
            hiloVisitante[i].start();
        }
     }
}

