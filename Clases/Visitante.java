public class Visitante implements Runnable {
    private int id;
    private String nombre;
    private Comedor comedor;
    private AreaJuegos juegos;
    private int puntos;
    private ControlTren tren;
    private Parque parque;
<<<<<<< HEAD
    private Reloj reloj;

    public Visitante(int idVisitante, Parque par, Comedor com, AreaJuegos area, ControlTren tr,Reloj re) {
=======
    private RealidadVirtual virtual;

    public Visitante(int idVisitante, Parque par, Comedor com, AreaJuegos area, ControlTren tr, RealidadVirtual virt) {
>>>>>>> ce86365c96c55ba9e8350a265b8895eb5e6af2c3
        this.id = idVisitante;
        parque = par;
        this.comedor = com;
        this.juegos = area;
        this.puntos = 0;
        tren = tr;
        virtual = virt;
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
<<<<<<< HEAD
            parque.ingresarParque(this, id);
            
            tren.abordarTren(nombre);
            comedor.llegaVisitante(id);
            num=comedor.buscaMesa(id);
          
          if(num>=0){
          this.comiendo();
          comedor.dejaMesa(num, id);
          }
          
          comedor.saleVisitante(id);
=======

            parque.ingresarParque(this, id);
>>>>>>> ce86365c96c55ba9e8350a265b8895eb5e6af2c3

            // Intercambiar ficha y jugar
            juegos.intercambiarFicha(id);
            // Obtener puntos del juego
            this.puntos = juegos.jugar(id);
            // Recibir premio basado en los puntos
            juegos.recibirPremio(puntos, id);

            comedor.llegaVisitante(id);

            num = comedor.buscaMesa(id);

            if (num >= 0) {
                this.comiendo();
                comedor.dejaMesa(num, id);
            }

            comedor.saleVisitante(id);

            tren.abordarTren(nombre);

            virtual.recibirEquipoCompleto(nombre);

            virtual.devolverEquipo(nombre);

            parque.saleParque();

        } catch (Exception e) {
        }

    }
}