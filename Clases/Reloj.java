import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Handler;

public class Reloj implements Runnable{
    private static final int hora_apuertura = 9; 
    private static final int hora_cierra = 18;
    private static final int nuevo_dia = 24;

    private final AtomicInteger hora_actual;

    public Reloj(AtomicInteger hora){
        hora_actual = hora;
    }

    public void run() {
        

        while (true) {
            try {
                Thread.sleep(4000);
                hora_actual.addAndGet(1);
                //System.out.println(PrintColor.ANSI_YELLOW + "[CLASE RELOJ] Hora actual: " + Handler.formatoHora(hora.get()) + " hs" + PrintColor.ANSI_RESET);

                switch (hora_actual.get()) {
                    case hora_apuertura -> {
                        //aeropuerto.comenzarHorarioAtencion();
                        //Thread.sleep(1000);
                    }
                    case hora_cierra -> {
                        //aeropuerto.terminarHorarioAtencion();
                        //Thread.sleep(1000);
                    }
                    case nuevo_dia -> {
                        hora_actual.set(0);
                        //System.out.println( PrintColor.ANSI_YELLOW + "[CLASE RELOJ] Comienza un nuevo día" + PrintColor.ANSI_RESET);
                    }
                    default -> {
                    }
                }

                //notificarTerminalesHora(terminales);
                //repositor.notificarCambioHora();
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                System.out.println("Error en Reloj.run: " + ex.getMessage());
            }
        }
    }


}