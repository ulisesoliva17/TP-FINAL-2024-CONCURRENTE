
public class Visitante implements Runnable {
    private int id;
    private String nombre;
    private Comedor comedor;
    private AreaJuegos juegos;
    private int puntos;

    public Visitante(int idVisitante, Comedor com, AreaJuegos area) {
        this.id = idVisitante;
        this.comedor = com;
        this.juegos = area;
        this.puntos = 0;
    }

    private void comiendo() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            /*num = comedor.llegaVisitante(id);
                this.comiendo();
                comedor.dejaMesa(num, id);*/

            // Intercambiar ficha y jugar
            juegos.intercambiarFicha(id);
            // Obtener puntos del juego
            this.puntos = juegos.jugar(id);
            // Recibir premio basado en los puntos
            juegos.recibirPremio(puntos,id);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public int getPuntos() {
        return puntos;
    }
}
