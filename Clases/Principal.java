public class Principal {
    public static void main(String[] args) {
        Visitante visitante[] = new Visitante[24];
        Thread  hiloVisitante[] = new Thread[24];
        Comedor comedor= new Comedor(5);
        
        

        for (int i = 0; i < hiloVisitante.length; i++) {
            visitante[i] = new Visitante(i+1,comedor);
            hiloVisitante[i] = new Thread(visitante[i]);
            hiloVisitante[i].start();
        }
     }
}
