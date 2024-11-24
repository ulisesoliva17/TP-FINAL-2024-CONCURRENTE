public class Principal {
    public static void main(String[] args) {
        Visitante visitante[] = new Visitante[20];
        Thread  hiloVisitante[] = new Thread[20];
        Comedor comedor= new Comedor(4);
        
        

        for (int i = 0; i < hiloVisitante.length; i++) {
            visitante[i] = new Visitante(i+1,comedor);
            hiloVisitante[i] = new Thread(visitante[i]);
            hiloVisitante[i].start();
        }
     }
}
