public class Principal {
    public static void main(String[] args) {
        Visitante visitante[] = new Visitante[25];
        Thread  hiloVisitante[] = new Thread[25];
        Comedor comedor= new Comedor(10);
        
        

        for (int i = 0; i < hiloVisitante.length; i++) {
            visitante[i] = new Visitante(i+1,comedor);
            hiloVisitante[i] = new Thread(visitante[i]);
            hiloVisitante[i].start();
        }
     }
}
