import java.util.concurrent.Exchanger;

public class AreaJuegos {
    private Exchanger<String> exchanger;

    public AreaJuegos(Exchanger<String> exchanger) {
        this.exchanger = exchanger;
    }

    public void intercambiarFicha() {
        try {
            // Intercambiar ficha con el visitante
            String ficha = exchanger.exchange("Ficha del encargado");
            System.out.println("Encargado recibió: " + ficha);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void recibirPremio(int puntos) {
        try {
            // Determinar premio basado en los puntos
            String premio;
            if (puntos > 80) {
                premio = "Gran premio";
            } else if (puntos > 50) {
                premio = "Premio medio";
            } else {
                premio = "Premio pequeño";
            }
            // Intercambiar premio con el visitante
            exchanger.exchange(premio);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
