/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package emanuel.flores.leon.arbolesbusqueda;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author pc
 */
public class NodoMvias <K,V> {
    
    private List<K> listaClaves;  //Datos
    private List<V> listaValores; //Datos
    
    private List<NodoMvias <K,V> > listaHijos; //Hijos
    
    public NodoMvias(int orden){
        listaClaves = new LinkedList<>();
        listaValores = new LinkedList<>();
        
        listaHijos = new LinkedList<>();
        
        for (int i = 0; i < orden-1; i++ ){
            listaClaves.add((K)NodoMvias.datoVacio());
            listaValores.add((V)NodoMvias.datoVacio());
            
            listaHijos.add(NodoMvias.nodoVacio());
        }
        listaHijos.add(NodoMvias.nodoVacio());
    }
    
    public NodoMvias(int orden ,K pclave ,V pvalor ){
        this(orden);
        listaClaves.set(0, pclave);
        listaValores.set(0, pvalor);
        
    }
    
    public static NodoMvias nodoVacio(){
        return null;
    }
    
    public static Object datoVacio(){
        return null;
    }
    
    public static boolean esNodoVacio(NodoMvias nodito){
        return NodoMvias.nodoVacio() == nodito;
    }
    
    /***
     * Retorna una clave de la lista de claves ubicada
     * en tal pocision que entra como parametro
     * precondicion: el parametro cumple con la siguiente condicion:
     * 0<=pocision<orden-1
     * @param pocision
     */
    public K getClave(int pocision){
        return listaClaves.get(pocision);
    }
    public V getValor(int pocision){
        return listaValores.get(pocision);
    }
    
    public void setClave(int pocision, K clave){
        listaClaves.set(pocision, clave);
    }
    public void setValor(int pocision, V valor){
        listaValores.set(pocision, valor);
    }
    
    public boolean esClaveVacio(int pocision){
        return listaClaves.get(pocision) == NodoMvias.datoVacio();
    }
    /**
     * La pocision para los hijos cumple la siguiente condicion:
     * 0<= pocision < orden
     * @param pocision
     * @return 
     */
    public NodoMvias<K,V> getHijo(int pocision){
        return listaHijos.get(pocision);
    }
    
    public void setHijo(int pocision, NodoMvias<K,V> nodoHijo){
        listaHijos.set(pocision, nodoHijo);
    }
    
    public boolean esHijoVacio(int pocision){
        return NodoMvias.esNodoVacio(listaHijos.get(pocision));
    }
    
    public boolean esHoja(){
      
        return  esHoja(listaHijos.size());
    }
    private boolean esHoja(int maximoHijosPosible){
        boolean w = true;
        if (maximoHijosPosible > 0){
            w = esHoja(maximoHijosPosible-1);
            w = w && esHijoVacio(maximoHijosPosible-1);
        }
        return w;
    }
    
    /**
     * Este Proceso esta sujeto a variar de la forma en como se llene los datos 
     * por ejemplo si se insertan un dato en el nodo en la pocision i+1 teniendo como precondicion
     * que el dato en la pocision i no sea vacio esta funcion cumple 
     * @return 
     */
    public boolean estaClavesTodasLlenas(){
        return !esClaveVacio(listaClaves.size()-1);
    }
    
    public int cantidadHijosNoVacios(){
        return cantidadHijosNoVacios(listaHijos.size());
    }
    private int cantidadHijosNoVacios(int maximoHijosPosible){
        int cantidad = 0;
        if (maximoHijosPosible > 0){
            cantidad = cantidadHijosNoVacios(maximoHijosPosible-1);
            if (!esHijoVacio(maximoHijosPosible-1)){
                cantidad++;
            }
        }
        return cantidad;
    }
    
    public int cantidadHijosVacios(){
        return listaHijos.size() - cantidadHijosNoVacios();
    }
    
    public int cantidadClavesNoVacias(){
        return cantidadClavesNoVacias(listaClaves.size());
    }
    private int cantidadClavesNoVacias(int maximoClavesPosible){
        int cantidad = 0;
        if (maximoClavesPosible > 0) {
            cantidad = cantidadClavesNoVacias(maximoClavesPosible-1);
            if (!this.esClaveVacio(maximoClavesPosible-1)){
                cantidad++;
            }
        }
        return cantidad;
    }
    private int cantidadClavesVacias(){
        return listaClaves.size() - cantidadClavesNoVacias();
    }

}

