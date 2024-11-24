import java.util.concurrent.Exchanger;

public class Principal {
    public static void main(String[] args) {
<<<<<<< HEAD
        Visitante visitante[] = new Visitante[20];
        Thread hiloVisitante[] = new Thread[20];
        Comedor comedor = new Comedor(5);
=======
        Visitante visitante[] = new Visitante[24];
        Thread  hiloVisitante[] = new Thread[24];
        Comedor comedor= new Comedor(5);
>>>>>>> e5b6bb41809215927dd0aa4abb76dec40535f46e
        Exchanger<String> exchanger = new Exchanger<>();
        AreaJuegos areaJuegos = new AreaJuegos(exchanger);

        for (int i = 0; i < hiloVisitante.length; i++) {
            visitante[i] = new Visitante(i + 1, comedor, areaJuegos);
            hiloVisitante[i] = new Thread(visitante[i]);
            hiloVisitante[i].start();
        }
    }
}
