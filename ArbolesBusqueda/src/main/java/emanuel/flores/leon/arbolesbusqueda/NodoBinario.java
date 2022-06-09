/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package emanuel.flores.leon.arbolesbusqueda;

/**
 *
 * @author pc
 */
public class NodoBinario<K,V>{
    private K clave;
    private V valor;
    
    private NodoBinario<K,V> hijo_izquiezdo;
    private NodoBinario<K,V> hijo_derecho;
    
    public NodoBinario(){
        
    }
    public NodoBinario(K clave, V valor){
        this.clave = clave;
        this.valor = valor;
    }

    
    public void setClave(K clave) {
        this.clave = clave;
    }

    public void setValor(V valor) {
        this.valor = valor;
    }

    public void setHijo_izquiezdo(NodoBinario<K, V> hijo_izquiezdo) {
        this.hijo_izquiezdo = hijo_izquiezdo;
    }

    public void setHijo_derecho(NodoBinario<K, V> hijo_derecho) {
        this.hijo_derecho = hijo_derecho;
    }

    
    public K getClave() {
        return clave;
    }

    public V getValor() {
        return valor;
    }

    public NodoBinario<K, V> getHijo_izquiezdo() {
        return hijo_izquiezdo;
    }

    public NodoBinario<K, V> getHijo_derecho() {
        return hijo_derecho;
    }
    
    public static NodoBinario nodoVacio(){
        return null;
    }
    
    public static boolean esNodoVacio(NodoBinario nodito){
        return nodito == nodoVacio(); // NodoBinario.nodoVacio
    }
    
    
    public boolean hijoIzquierdoEsvacio(){
        return esNodoVacio(getHijo_izquiezdo());
    }
    
    public boolean hijoDerechoEsvacio(){
        return esNodoVacio(getHijo_derecho());
    }
    
    public boolean esHoja(){
        return hijoIzquierdoEsvacio() && hijoDerechoEsvacio();
    }
    
    
    
}

