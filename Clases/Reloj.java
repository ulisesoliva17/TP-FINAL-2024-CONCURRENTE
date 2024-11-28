import java.util.concurrent.atomic.AtomicInteger;
public class Reloj implements Runnable{
    private static final int hora_apuertura = 9; 
    private static final int hora_cierra = 18;
    private static final int nuevo_dia = 24;

    private final AtomicInteger hora_actual;
    private Parque parque;

    public Reloj(AtomicInteger hora,Parque par){
        hora_actual = hora;
        parque=par;
    }

    public void run() {
        while (true) {
            try {
                Thread.sleep(4000);
                hora_actual.addAndGet(1);
                System.out.println("[CLASE RELOJ]. Hora Actual: "+hora_actual);

                switch (hora_actual.get()) {
                    case hora_apuertura: {
                        parque.comenzarHorarioAtencion();
                        Thread.sleep(1000);
                    }
                    case hora_cierra: {
                        parque.terminarHorarioAtencion();
                        Thread.sleep(1000);
                    }
                    case nuevo_dia: {
                        hora_actual.set(0);
                        System.out.println("[CLASE RELOJ] Comienza un nuevo día");
                    }
                    default :{
                    }
                }
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                System.out.println("Error en Reloj.run: " + ex.getMessage());
            }
        }
    }
}