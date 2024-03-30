package weird_text;

import java.util.Scanner;

import javax.sound.sampled.Line;

public class Main {
    ListaDoblementeEnlazada TextoMalo;
    ListaDoblementeEnlazada TextoTemporal;
    String Linea;

    Main(String Linea) {

        String[] segmentos = Linea.split( "((?=[\\[\\]])|(?<=[\\[\\]]))",0);
        String home = "[";
        String back = "]";
        String accionprevia = "]";
        String temporal = "";
        
        TextoMalo = new ListaDoblementeEnlazada();

        for(String parte: segmentos){
            if (!parte.equals(home) && !parte.equals(back)) {
                temporal = parte;
                //System.out.println("parte " + (parte ));
                if(accionprevia.equals(home)){
                    TextoMalo.insertarAlInicio(temporal);
                    
                } else if(accionprevia.equals(back)){
                    TextoMalo.insertarAlFinal(temporal);
        
                }
            } else if(parte.equals(home)){
                accionprevia = parte;
                
            } else if(parte.equals(back)){
                accionprevia = parte;

            }
        }    
    }
    String salida(){
        return TextoMalo.toString();
    }

    public static void main(String[] args) {
       Scanner sc = new Scanner(System.in);
       String Linea;
       Main texto;
       String [] salidas = new String [100];
       int contador =0;
       while (sc.hasNext() ) {
            Linea = sc.nextLine();
            texto =new Main(Linea);
            salidas[contador] =texto.salida();
            contador ++;      
        }
       sc.close();
       for(int i=0; i<contador; i++){
        System.out.println(salidas[i]);
       
       }
    }
    
    static class Nodo {
        String dato;
        Nodo siguiente;
        Nodo anterior;
    
        public Nodo(String dato) {
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
        public void insertarAlInicio(String dato) {
            Nodo nuevo = new Nodo(dato);
            if (estaVacia()) {
                cabeza = nuevo;
                cola = nuevo;
            } else {
                cabeza.anterior = nuevo;
                nuevo.siguiente = cabeza;
                cabeza = nuevo;
            }
        }

        public void insertarAlFinal(String dato) {
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
