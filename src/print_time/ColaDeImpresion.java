package print_time;

public class ColaDeImpresion {

    private static void utilizarCola() {
        int capacidad = 4; // Capacidad de la cola
        int TiempoImpresion=0;
        Cola cola = new Cola(capacidad); // Instancia de tu propia cola

        // Encola las prioridades
        cola.encolar(1);
        cola.encolar(2);
        cola.encolar(3);
        cola.encolar(4);

        /* //Revisa la prioridad
        int primera=0;
        int posicionpri=0;
        for (int i=0;i<capacidad;i++){
        int Revisa = cola.peek(i); 
        primera= Math.max(primera,Revisa);
        if(primera==Revisa){posicionpri=i;}
    }
        System.out.println("Primera: " + primera);//Cual es la mayor prioridad de 0 a 9
        System.out.println("Posición primera: " + posicionpri);// Posicion en el arreglo del trabajo con mayor prioridad
    
        // Desencola y vuelve a encolar los trabajos para que el primero en la cola sea 4 */
        int trabajo=0;
        int prioridad=cola.maximo();
        while (!cola.vacio() && trabajo <  prioridad-1) {
            trabajo = cola.desencolar();
            cola.encolar(trabajo);
            System.out.println("Trabajo desencolado y encolado nuevamente: " + trabajo);
        }

        // Desencola y cuenta el tiempo de impresión
        while (!cola.vacio()) {
            trabajo = cola.desencolar();
            prioridad = trabajo - 1; // Establece la nueva prioridad
            TiempoImpresion++;
            System.out.println("Trabajo desencolado: " + trabajo);
        }        
    }
    

    public static void main(String[] args) {
        utilizarCola();
    }

    // Implementación de tu clase Cola aquí
    static class Cola {
        // Implementación de la cola
        private int[] arregloCola;
        private int frente, trasero, contador, CapCola;

        public Cola(int CapCola) {
            this.CapCola = CapCola;
            arregloCola = new int[CapCola];
            frente = trasero = contador = 0;
        }

        public int maximo() {
            if (vacio()) {
                System.out.println("La cola está vacía");
                return -1;
            }
        
            int maximo = Integer.MIN_VALUE;
            int indice = frente;
            for (int i = 0; i < contador; i++) {
                int elemento = arregloCola[indice];
                maximo = Math.max(maximo, elemento);
                indice = (indice + 1) % CapCola;
            }
            return maximo;
        }

        public void encolar(int item) {
            if (lleno())
            System.out.println("Queue is full: item not enqueued");
        else {
            this.arregloCola[this.trasero] = item;
            this.trasero = (trasero + 1) % CapCola;//% Cola sea circular 
            this.contador++;
        }
    }

        public int desencolar() {
            int item = -1;
        if (vacio())
            System.out.println("Queue is empty: item not dequeued");
        else {
            item = arregloCola[frente];
            frente = (frente + 1) % CapCola; //% Para que la cola sea circular
            contador--;
        }
        return item;
        }
        public int peek(int puesto) {
            if (vacio()) {
                return -1; // Lanzar una excepción
            } else {
                return arregloCola[puesto];
            }
        }
        public boolean lleno() {
            if(contador >= CapCola){
            return true;
            }else{return false;}
        }

        public boolean vacio() {
            if(contador <= 0){
            return true; }
            else {return false;}
        }
    }
}
