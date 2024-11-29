public class Parque {
    private boolean estaAbierto;

    public Parque() {
    }

    // Metodos para Pasajero ---------------------------------------------------
    /**
     * método sincroniza el ingreso de los visitantes al Parque y los hace
     * esperar hasta que el reloj notifique que el Parque abrió. Si el
     * Parque está abierto, el visitante se dirige al puesto de atención.
     *
     * @param pasajero pasajero que desea ingresar
     */
    public synchronized void ingresarParque(Visitante visitante,int id) {
        // Se utiliza un lock implicito para el objeto this, esto para que
        // los pasajeros no puedan ingresar al aeropuerto mientras el reloj
        // no notifique que el aeropuerto abrió.
        try {
            while (!estaAbierto) {
                System.out.println(
                        "[CLASE PARQUE].El Parque esta cerrado. Esperare a que se abra para poder ingresar");
                wait();
            }
            System.out.println("[CLASE PARQUE].Ingreso "+id);
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            System.out.println("[CLASE PARQUE].Error en Parque.ingresarParque" + ex.getMessage());
        }
    }

    // Metodos para Reloj ------------------------------------------------------
    /**
     * Metodo sincronizado para que los visitantes ingresen al Parque y
     * esperen a que el reloj los notifique que el Parque abrio
     */
    public synchronized void comenzarHorarioAtencion() {
        // Se liberan los pasajeros que estaban esperando a que el aeropuerto abriera
        System.out.println("[CLASE PARQUE].El parque se abrio, avisemos a todos los visitantes");
        estaAbierto = true;
        notifyAll();
    }

    /**
     * Método sincronizado que notifica a todos los pasajeros que el aeropuerto
     * cerró y cambia el estado del aeropuerto a cerrado.
     */
    public synchronized void terminarHorarioAtencion() {
        estaAbierto = false;
        System.out.println("[CLASE PARQUE] Termino horario de atencion.");
        System.out.println("[CLASE PARQUE] Se fueron todos los visitantes");
    }
}