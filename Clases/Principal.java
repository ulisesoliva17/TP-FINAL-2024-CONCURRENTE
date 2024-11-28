import java.util.concurrent.Exchanger;

public class Principal {
    /**
     * @param args
     */
    public static void main(String[] args) {
        Visitante visitante[] = new Visitante[21];
        Thread hiloVisitante[] = new Thread[21];
        Comedor comedor = new Comedor(5);
        Exchanger<String> exchanger = new Exchanger<>();
        AreaJuegos areaJuegos = new AreaJuegos(exchanger);
        ControlTren controlTren = new ControlTren();
        Tren tren = new Tren(controlTren);
        Thread hiloTren = new Thread(tren);
        hiloTren.start();

        for (int i = 0; i < hiloVisitante.length; i++) {
            visitante[i] = new Visitante(i + 1, comedor, areaJuegos, controlTren);
            hiloVisitante[i] = new Thread(visitante[i]);
            hiloVisitante[i].start();
        }
    }
}