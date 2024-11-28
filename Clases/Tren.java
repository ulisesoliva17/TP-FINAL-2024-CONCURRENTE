class Tren implements Runnable {
    private ControlTren control;

    public Tren(ControlTren c) {
        control = c;
    }

    @Override
    public void run() {
        while (true) {
            try {
                // Lista para almacenar los pasajeros que abordarán
                System.out.println("Tren esperando visitantes...");

                /*
                 * // Temporizador para medir el tiempo de espera
                 * long inicio = System.currentTimeMillis();
                 * long tiempoEspera = 5 * 60 * 1000; // 5 minutos en milisegundos
                 * int pasajeros = 0;
                 * 
                 * 
                 * while (pasajeros < 10 && System.currentTimeMillis() - inicio < tiempoEspera)
                 * {
                 * // Esperar a que llegue un visitante o se cumpla el tiempo
                 * Visitante visitante = colaTren.poll(tiempoEspera -
                 * (System.currentTimeMillis() - inicio), TimeUnit.MILLISECONDS);
                 * 
                 * if (visitante != null) {
                 * pasajeros++;
                 * } else {
                 * break; // Tiempo de espera agotado
                 * }
                 * }
                 * 
                 * System.out.println("Tren partiendo con " + pasajeros + " pasajeros.");
                 */

                control.partirTren();

                // Simular recorrido del tren
                Thread.sleep(3000); // Tiempo que tarda el tren en hacer el recorrido

                System.out.println("Tren regresó y está listo para más pasajeros.");

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Tren interrumpido.");
            }
        }
    }
}