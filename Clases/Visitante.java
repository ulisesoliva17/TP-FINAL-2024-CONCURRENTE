public class Visitante implements Runnable {
    private int id;
    private String nombre;
    private Comedor comedor;
    private AreaJuegos juegos;
    private int puntos;
    private ControlTren tren;
    private Parque parque;

    public Visitante(int idVisitante,Parque par, Comedor com, AreaJuegos area, ControlTren tr) {
        this.id = idVisitante;
        parque=par;
        this.comedor = com;
        this.juegos = area;
        this.puntos = 0;
        tren = tr;
        nombre = "Visitante" + id;
    }

    private void comiendo() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public int getPuntos() {
        return puntos;
    }

    public void run() {
        int num;

        try {


            //tren.abordarTren(nombre);
            parque.ingresarParque(this,id);
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
         
    
        } catch (Exception e) {
        }
        
}
}
