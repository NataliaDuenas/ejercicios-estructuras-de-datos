package print_time;

import java.io.File;
import java.util.Scanner;

import print_time.Main.Cola;
import print_time.Main.Nodo;

public class Main {
    int calls =0;
    private int utilizarCola(int largo, int indice, String linea) {
        this.calls = 0;
        int impresion;
        int objetivo = 9;
        Nodo target = new Nodo(0);
        
        int contador = 0;
        Scanner sc = new Scanner(linea);

        Cola general = new Cola();
        
                for( int i =0; i<largo; i++){
            impresion = sc.nextInt();
            general.append(impresion);
            if(i == indice ){
                target = general.cola;
                general.cola.marker = 'x';

            }
        }
        System.out.println("outside " +general);
        contador = calcularTiempo(target,objetivo, general, 0 );   
            
        return contador ;
        
    }
    private int  calcularTiempo(Nodo target,int objetivo, Cola general, int contador ){
        this.calls ++;
        System.out.println("inception " + this.calls + " target:"+objetivo);
        Cola tempCola = new Cola();
        int impresion;
        
        int nuevo_objetivo = 0;
        Nodo eval;

        while (!general.estaVacia()) {
            eval = general.pop();
            impresion = eval.dato;
            System.out.println(general.toString());
                
            if (impresion == objetivo){
                contador++;
                if(eval.marker == 'x'){
                    return contador;
                }
                System.out.println("Objetivo " + objetivo + " contador: " + contador);
                System.out.println("post: " +general.toString());
                
            }else{
                if(impresion>nuevo_objetivo){
                    nuevo_objetivo = impresion;

                }
                tempCola.append(eval);
                
                
            }

        }
        System.out.println("New Cola: " +tempCola.toString());
        contador = contador +  calcularTiempo(target, nuevo_objetivo, tempCola,contador);
                
        System.out.println("Contador: " + contador +" inception " + this.calls);
        return contador;
    }

    public static void main(String[] args) {
        int tiempo_espera = 0;
        try {
            File file = new File("src/print_time/input.txt");
            Scanner sc = new Scanner(file);
            

            int n_casos;
            int largo;
            int indice;
            int objetivo;
            int impresion;
            int contador = 0;
            String linea;
            
            
            n_casos = sc.nextInt();         // numero de casos
            largo = sc.nextInt();         // numero de casos
            indice = sc.nextInt();         // numero de casos
            linea = sc.nextLine();
            linea = sc.nextLine();
            System.out.println("Start:"+ linea);
            Main ma = new Main();
            tiempo_espera = ma.utilizarCola(largo,indice,linea);
            System.out.println(tiempo_espera);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // Implementación de tu clase Cola aquí
    static class Nodo {
        int dato;
        Nodo siguiente;
        char marker;
        
        public Nodo(int dato) {
            this.dato = dato;
            this.siguiente = null;
            this.marker = 'o';
           
        }
    }
    static class Cola{
        Nodo cabeza;
        Nodo cola;

    
        public Cola() {
            this.cabeza = null;
            this.cola = null; 
           
        }
    
        public boolean estaVacia() {
            return cabeza == null;
        }
        
      
        public void append(int dato) {
            Nodo nuevo = new Nodo(dato);
            if (estaVacia()) {
                cola = nuevo;
                cabeza = nuevo;                 
            } else {
                cola.siguiente = nuevo;
                cola = nuevo;
            }
        }
        public void append(Nodo nuevo) {
            if (estaVacia()) {
                cola = nuevo;
                cabeza = nuevo;                 
            } else {
                cola.siguiente = nuevo;
                cola = nuevo;
            }
        }
        public void append(Cola nuevaCola) {
            
            if (estaVacia()) {
                cabeza = nuevaCola.cabeza; 
                cola = nuevaCola.cola;
                
            } else {
                cola.siguiente = nuevaCola.cabeza;
                cola = nuevaCola.cabeza;
            }
        }
        public Nodo pop() {
            Nodo salida = cabeza;
            cabeza = cabeza.siguiente; 
            if(!this.estaVacia()){
                salida.siguiente = null;
            }
            
            return salida;
        }

        public void vaciar() {
            cabeza = null;
            cola = null;
        }
        
        public String toString(){
            Nodo nodo = cabeza;
            String texto="";
            while (nodo != null) {
                texto += nodo.dato;
                if (nodo.marker =='x') {
                    texto+="x";
                }
                nodo = nodo.siguiente;
            }
            return texto;
        }
    }
}
