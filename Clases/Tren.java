class Tren implements Runnable {
    private final ControlTren control;

    public Tren(ControlTren c) {
        control = c;
    }

    @Override
    public void run() {
        int i = 0;
        while (true) {
            try {
                control.chequeoSalida();
                // Simular recorrido del tren
                Thread.sleep(3000); // Tiempo que tarda el tren en hacer el recorrido
                control.llegaTren();

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Tren interrumpido.");
            }
            i++;
        }
    }
}
