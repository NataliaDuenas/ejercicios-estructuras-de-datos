import java.util.Scanner;

import javax.sound.sampled.Line;

public class Main {
    ListaDoblementeEnlazada TextoMalo;
    ListaDoblementeEnlazada TextoTemporal;
    String Linea;

    Main(String Linea) {
        TextoMalo = new ListaDoblementeEnlazada();
        TextoTemporal = new ListaDoblementeEnlazada();
        TextoMalo.insertarAlInicio(TextoTemporal);
        char accionprevia=']';
        char home='[';
        char back=']';

        for (int i = 0; i < Linea.length(); i++) {
            char caracter = Linea.charAt(i);

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
    }
    String salida(){
        return TextoMalo.toString();
    }

    public static void main(String[] args) {
       Scanner sc = new Scanner(System.in);
       String Linea;
       Linea = sc.nextLine();
       Main m1 = new Main(Linea);
       Linea = sc.nextLine();
       Main m2 = new Main(Linea);
       sc.close();
       System.out.println(m1.salida());
       System.out.println(m2.salida());
       /* String Linea;
       Textos Main[];
       while (sc.hasNext() ) {
            Linea = sc.nextLine();
            new Main(Linea);
            
       
       } */
       //
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
        
        public String toString(){
            Nodo nodo = cabeza;
            String texto="";
            while (nodo!= null) {
                texto += nodo.dato;
                nodo = nodo.siguiente;
            }
            return texto;
        }
    }
}
