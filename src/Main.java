import java.util.Scanner;

public class Main {
    ListaDoblementeEnlazada TextoMalo;
    ListaDoblementeEnlazada TextoTemporal;
    String Linea;

    Main(String Linea) {
        TextoMalo = new ListaDoblementeEnlazada();
        TextoTemporal = new ListaDoblementeEnlazada();
        TextoMalo.insertarAlInicio(TextoTemporal);
        char accionprevia=']';
        for (int i = 0; i < Linea.length(); i++) {
            char caracter = Linea.charAt(i);
            char home='[';
            char back=']';
            if ((caracter != home && caracter != back)) {
                TextoTemporal.insertarAlFinal(caracter);
            } else if(accionprevia==home){
                TextoMalo.insertarAlInicio(TextoTemporal);
                TextoTemporal.vaciar();
                accionprevia=caracter;
            } else if(accionprevia==back){
                TextoMalo.insertarAlFinal(TextoTemporal);
                TextoTemporal.vaciar();
                accionprevia=caracter;
            }
            if (i == Linea.length() - 1) {
                TextoMalo.insertarAlFinal(TextoTemporal);
            }            
        }
        System.out.println(TextoMalo.toString());
    }

    public static void main(String[] args) {
       Scanner sc = new Scanner(System.in);
       String Linea1 = sc.nextLine();
       String Linea2 = sc.nextLine();
       new Main(Linea1); 
       new Main(Linea2);
       sc.close();
    }
    
    static class Nodo {
        char dato;
        Nodo siguiente;
        Nodo anterior;
    
        public Nodo(char dato) {
            this.dato = dato;
            this.siguiente = null;
            this.anterior = null;
        }
    }
    
    static class ListaDoblementeEnlazada {
        Nodo cabeza;
        Nodo cola;
    
        public ListaDoblementeEnlazada() {
            this.cabeza = null;
            this.cola = null;
        }
    
        public boolean estaVacia() {
            return cabeza == null;
        }
        
        public void insertarAlInicio(ListaDoblementeEnlazada nuevaLista) {
            if (nuevaLista.estaVacia()) {
                return;
            }
            if (estaVacia()) {
                cabeza = nuevaLista.cabeza;
                cola = nuevaLista.cola;
            } else {
                nuevaLista.cola.siguiente = cabeza;
                cabeza.anterior = nuevaLista.cola;
                cabeza = nuevaLista.cabeza;
            }
        }
        
        public void insertarAlFinal(ListaDoblementeEnlazada nuevaLista) {
            if (nuevaLista.estaVacia()) {
                return;
            }
            if (estaVacia()) {
                cabeza = nuevaLista.cabeza;
                cola = nuevaLista.cola;
            } else {
                cola.siguiente = nuevaLista.cabeza;
                nuevaLista.cabeza.anterior = cola;
                cola = nuevaLista.cola;
            }
        }

        public void insertarAlFinal(char dato) {
            Nodo nuevo = new Nodo(dato);
            if (estaVacia()) {
                cabeza = nuevo;
                cola = nuevo;
            } else {
                cola.siguiente = nuevo;
                nuevo.anterior = cola;
                cola = nuevo;
            }
        }

        public void vaciar() {
            cabeza = null;
            cola = null;
        }
        
        public String toString() {
            StringBuilder sb = new StringBuilder();
            Nodo actual = cabeza;
            while (actual != null) {
                sb.append(actual.dato);
                actual = actual.siguiente;
            }
            return sb.toString();
        }
    }
}




