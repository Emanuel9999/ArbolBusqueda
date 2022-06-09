/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package emanuel.flores.leon.arbolesbusqueda;

import emanuel.flores.leon.arbolesbusqueda.Exception.ExceptionClaveNoExiste;
import emanuel.flores.leon.arbolesbusqueda.Exception.ExceptionOrdenInvalido;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 *
 * @author pc
 */
public class ArbolMViasBusqueda<K extends Comparable<K>,V> implements IarbolBusqueda<K,V>{
    
    
    protected NodoMvias<K,V> raiz;
    protected int orden;
    protected static final int POSICION_NO_VALIDA = -1;
    protected static final int ORDEN_MINIMO = 3;
    
    public ArbolMViasBusqueda(){
        orden = ArbolMViasBusqueda.ORDEN_MINIMO;
    }
    
    public ArbolMViasBusqueda(int orden) throws ExceptionOrdenInvalido{
        if (orden < ORDEN_MINIMO){
            throw new ExceptionOrdenInvalido();
        }
        this.orden = orden;
    }
    
    @Override
    public void insertar(K claveInsertar, V valorInsertar) throws NullPointerException {
        if (claveInsertar == null){
            throw new NullPointerException("Clave a insertar no puede ser Nula");
        }
        if (valorInsertar == null){
            throw new NullPointerException("Valor a insertar no puede ser Nula");
        }
        
        if (esArbolVacio()){
            raiz = new NodoMvias<>(orden, claveInsertar, valorInsertar);
            return;
        }  
        
        NodoMvias<K,V> nodoActual = raiz;
        while (!NodoMvias.esNodoVacio(nodoActual)){
            int posicionClaveInsertar = obtenerPocisionDeClave(nodoActual, claveInsertar); //Realizar el proceso
            if (posicionClaveInsertar != this.POSICION_NO_VALIDA){ //Por verdadero actualizamos el valor
                nodoActual.setValor(posicionClaveInsertar, valorInsertar);
                nodoActual = NodoMvias.nodoVacio();
            }else if (nodoActual.esHoja()){
                if (!nodoActual.estaClavesTodasLlenas()){
                    insercionClaveValorEnNodo(nodoActual ,claveInsertar ,valorInsertar ); //Realizar el proceso
                }else{
                    NodoMvias<K,V> nuevoHijo = new NodoMvias<>(orden ,claveInsertar ,valorInsertar);
                    int posicion_a_bajar = obtenerPocisionPorDondeBajar(nodoActual, claveInsertar); //Realizar el proceso
                    nodoActual.setHijo(posicion_a_bajar, nuevoHijo);
                }
                nodoActual = NodoMvias.nodoVacio();
            }else{
                int posicion_a_bajar = obtenerPocisionPorDondeBajar(nodoActual, claveInsertar); //Realizar el proceso
                if (nodoActual.esHijoVacio(posicion_a_bajar)){
                    NodoMvias<K,V> nuevoHijo = new NodoMvias<>(orden ,claveInsertar ,valorInsertar);
                    nodoActual.setHijo(posicion_a_bajar, nuevoHijo);
                    nodoActual = NodoMvias.nodoVacio();
                }else{
                    nodoActual = nodoActual.getHijo(posicion_a_bajar);
                }
            }   
        }
    }
    
    protected int obtenerPocisionDeClave(NodoMvias<K,V> nodoActual, K claveInsertar){
        int cantidadDeClavesNoVacias = nodoActual.cantidadClavesNoVacias();
        int posicion = POSICION_NO_VALIDA;
        K claveActual = (K)NodoMvias.datoVacio();
        short i = 0;
        while ((i < cantidadDeClavesNoVacias) && (posicion == POSICION_NO_VALIDA)){
            claveActual = nodoActual.getClave(i);
            if (claveInsertar.compareTo(claveActual) == 0){
                posicion = i;
            }
            i++;
        }
        return posicion;
    }
    // Esta funcion tiene como precondicion todas las claves llenas
    protected int obtenerPocisionPorDondeBajar(NodoMvias<K,V>nodoActual, K claveInsertar){
        int posicion = nodoActual.cantidadClavesNoVacias();
        K claveActual = (K)NodoMvias.datoVacio();
        short i = 0;
        while ((i < nodoActual.cantidadClavesNoVacias()) &&
               (posicion == nodoActual.cantidadClavesNoVacias())){
            
            claveActual = nodoActual.getClave(i);
            if (claveInsertar.compareTo(claveActual) < 0){
                posicion = i;
            }
            i++;
        }
        return posicion;     
    }
    // Este proceso tiene como precondicion que existe al menos un dato vacio
    protected void insercionClaveValorEnNodo(NodoMvias<K,V>nodoActual ,K claveInsertar ,V valorInsertar ){
        insercionClaveValorEnNodo(nodoActual ,claveInsertar ,valorInsertar,
                nodoActual.cantidadClavesNoVacias());
    }
    private void insercionClaveValorEnNodo(NodoMvias<K,V>nodoActual ,K claveInsertar ,
                                            V valorInsertar, int posicionFinal){
        if (posicionFinal>0){
            K claveActual = nodoActual.getClave(posicionFinal-1);
            if (claveInsertar.compareTo(claveActual) > 0){
                nodoActual.setClave(posicionFinal, claveInsertar);
                nodoActual.setValor(posicionFinal, valorInsertar);
            }else{
                V valorActual = nodoActual.getValor(posicionFinal-1);
                insercionClaveValorEnNodo(nodoActual ,claveInsertar ,valorInsertar ,posicionFinal-1);
                nodoActual.setClave(posicionFinal, claveActual);
                nodoActual.setValor(posicionFinal, valorActual);
            }
        }else{
            nodoActual.setClave(posicionFinal, claveInsertar);
            nodoActual.setValor(posicionFinal, valorInsertar);
        }
    }

    @Override
    public V eliminar(K claveEliminar) throws NullPointerException, ExceptionClaveNoExiste {
        if (claveEliminar == null){
            throw new NullPointerException("La clave no puede ser nula");
        }
        V valorEliminar = this.buscar(claveEliminar);
        if (valorEliminar == null){
            throw new ExceptionClaveNoExiste();
        }
        this.raiz = eliminar(this.raiz, claveEliminar);
        return valorEliminar;
    }
    //Tiene como Precondicion que la clave a eliminar si existe
    protected NodoMvias<K,V> eliminar(NodoMvias<K,V> nodoActual ,K claveEliminar){
        int cantidadClavesNvacias = nodoActual.cantidadClavesNoVacias();
        K claveActual = (K)NodoMvias.datoVacio();
        for (int i = 0; i<cantidadClavesNvacias; i++){
            
            claveActual = nodoActual.getClave(i);
            if (claveEliminar.compareTo(claveActual) == 0){ 
                
                if (nodoActual.esHoja()){ // Caso 1
                    eliminarClaveValorEnNodo(nodoActual, i); //Realizar Proceso
                    if (nodoActual.cantidadClavesNoVacias() == 0){
                        return NodoMvias.nodoVacio();
                    }
                    return nodoActual;
                }
                K claveDeReemplazo;
                if (hayHijosMasAdelante(nodoActual,i)){//Caso 2 ->Realizar funcion
                    if (!nodoActual.esHijoVacio(i+1)){
                        claveDeReemplazo = obtenerClaveSucesoraInOrden(nodoActual.getHijo(i+1));
                    }else{
                        claveDeReemplazo = nodoActual.getClave(i+1);
                    }
                }else{//Caso 3
                    if (!nodoActual.esHijoVacio(i)){
                        claveDeReemplazo = obtenerClavePredecesoraInOrden(nodoActual.getHijo(i));
                    }else{
                        claveDeReemplazo = nodoActual.getClave(i-1);
                    }
                }
                V valorDeReemplazo = this.buscar(claveDeReemplazo);
                
                nodoActual = eliminar(nodoActual ,claveDeReemplazo);
                nodoActual.setClave(i, claveDeReemplazo);
                nodoActual.setValor(i, valorDeReemplazo);
                
                return nodoActual;
            }
            
            if (claveEliminar.compareTo(claveActual) < 0){
                NodoMvias<K,V> nuevoHijo = eliminar(nodoActual.getHijo(i) ,claveEliminar);
                nodoActual.setHijo(i, nuevoHijo);
                return nodoActual;
            }
            
        }
        NodoMvias<K,V> nuevoHijo = eliminar(nodoActual.getHijo(orden-1) ,claveEliminar);
        nodoActual.setHijo(orden-1, nuevoHijo);
        
        return nodoActual;
    }
    //Tiene como precondicion que el nodo es hoja
    protected void eliminarClaveValorEnNodo(NodoMvias<K,V> nodoActual , int posicion){
        int cantidadClaveNvacias = nodoActual.cantidadClavesNoVacias();
        for (int i = posicion; i < cantidadClaveNvacias - 1 ;i++){
            nodoActual.setClave(i, nodoActual.getClave(i+1));
            nodoActual.setValor(i, nodoActual.getValor(i+1));
        }
        nodoActual.setClave(cantidadClaveNvacias-1, (K)NodoMvias.datoVacio());
        nodoActual.setValor(cantidadClaveNvacias-1, (V)NodoMvias.datoVacio());
    }
    //Tiene como precondicion que el nodo no es hoja
    private boolean hayHijosMasAdelante(NodoMvias<K,V> nodoActual ,int posicion){
        boolean existen_hijos = false;
        if (posicion < orden - 1){
            existen_hijos = !nodoActual.esHijoVacio(posicion+1);
            if (!existen_hijos){
                existen_hijos = hayHijosMasAdelante(nodoActual, posicion+1);
            }
        }
        return existen_hijos;
    }
    
    private K obtenerClaveSucesoraInOrden(NodoMvias<K,V> nodoActual ){
        if (!nodoActual.esHijoVacio(0)){
            return obtenerClaveSucesoraInOrden(nodoActual.getHijo(0));
        }else{
            return nodoActual.getClave(0);
        }
    }
    private K obtenerClavePredecesoraInOrden(NodoMvias<K,V> nodoActual){
        if (!nodoActual.esHijoVacio(orden-1)){
            return obtenerClavePredecesoraInOrden(nodoActual.getHijo(orden-1));
        }else{
            return nodoActual.getClave(nodoActual.cantidadClavesNoVacias()-1);
        }
    }
    
    @Override
    public V buscar(K claveBuscar) throws NullPointerException {
       if (claveBuscar== null ){
           throw new NullPointerException("Clave a buscar no puede ser Nula");
       } 
       return buscar(claveBuscar, raiz);
    }
    private V buscar(K claveBuscar, NodoMvias<K,V> nodoActual){
        V valor = (V)NodoMvias.datoVacio();
        if (!NodoMvias.esNodoVacio(nodoActual)){
            int i = 0;
            int cantidadClaves = nodoActual.cantidadClavesNoVacias();
            K claveActual = (K)NodoMvias.datoVacio();
            boolean laClaveEsmayorDeTodos ;
            do {
                claveActual = nodoActual.getClave(i);
                laClaveEsmayorDeTodos = claveBuscar.compareTo(claveActual) > 0 ;
                i++;
            }while (laClaveEsmayorDeTodos && i < cantidadClaves);
            if (!laClaveEsmayorDeTodos){
                if (claveBuscar.compareTo(claveActual)==0){
                    valor = nodoActual.getValor(i-1);
                }else{
                    valor = buscar(claveBuscar,nodoActual.getHijo(i-1));
                }
            }else{
                valor = buscar(claveBuscar,nodoActual.getHijo(i));
            }
            
        }
        return valor;
    }

    @Override
    public boolean contiene(K claveBuscar) throws NullPointerException {
        if (claveBuscar== null ){
           throw new NullPointerException("Clave a buscar no puede ser Nula");
       } 
       return buscar(claveBuscar) != (V)NodoMvias.datoVacio();
    }

    @Override
    public int size() {//CantidadNodos
        return size(raiz);
    }
    
    //Funcion tanto para arbol m vias como b
    
    private int size(NodoMvias<K,V> nodoActual){
        int cantidad = 0;
        if (!NodoMvias.esNodoVacio(nodoActual)){
            for (int i=0; i<=nodoActual.cantidadClavesNoVacias(); i++){
                cantidad = cantidad + size(nodoActual.getHijo(i));
            }
            cantidad++;
        }
        return cantidad;
    }
    @Override
    public int altura() {
        return altura(raiz);
    }
    protected int altura(NodoMvias<K,V> nodoActual){
       int alturaMaxima = 0;
       if (!NodoMvias.esNodoVacio(nodoActual)){
           alturaMaxima = altura(nodoActual.getHijo(0));
           int aLtura;
           for (int i=1; i<orden; i++){
               aLtura = altura(nodoActual.getHijo(i));
               if (aLtura > alturaMaxima){
                   alturaMaxima = aLtura;
               }
           }
           alturaMaxima++;
       } 
       return alturaMaxima;
    }
    
    @Override
    public void vaciaArbol() {
        this.raiz = NodoMvias.nodoVacio();
    }

    @Override
    public boolean esArbolVacio() {
        return NodoMvias.esNodoVacio(raiz);
    }

    @Override
    public List<K> recorridoPorNiveles() {
        List<K> recorrido = new ArrayList<>(); 
        
        if (!esArbolVacio()){
            Queue<NodoMvias<K,V>> colaNodos = new LinkedList<>();
            colaNodos.offer(raiz); 
            recorridoPorNiveles(recorrido,colaNodos);
        }

//        if (!esArbolVacio()){
//            int cantidadClavesNoVacia = 0;
//            Queue<NodoMvias<K,V>> colaNodos = new LinkedList<>();
//            colaNodos.offer(raiz);
//            NodoMvias<K,V> nodoActual = NodoMvias.nodoVacio();
//            while (!colaNodos.isEmpty()){
//                nodoActual = colaNodos.poll();
//                cantidadClavesNoVacia = nodoActual.cantidadClavesNoVacias();
//                for (int i=0; i < cantidadClavesNoVacia; i++){
//                    recorrido.add(nodoActual.getClave(i));
//                    if (!nodoActual.esHijoVacio(i)){
//                        colaNodos.offer(nodoActual.getHijo(i));
//                    }
//                }
//                if (!nodoActual.esHijoVacio(orden-1)){
//                    colaNodos.offer(nodoActual.getHijo(orden-1));
//                }
//            }
//        }
        return recorrido;
    }
    private void recorridoPorNiveles(List<K> recorrido ,Queue<NodoMvias<K,V>> colaNodos){ 
        if (!colaNodos.isEmpty()){
            NodoMvias<K,V> nodoActual = colaNodos.poll();
            int cantidadClavesNoVacia = nodoActual.cantidadClavesNoVacias();
            for (int i=0; i < cantidadClavesNoVacia; i++){
                recorrido.add(nodoActual.getClave(i));
                if (!nodoActual.esHijoVacio(i)){
                    colaNodos.offer(nodoActual.getHijo(i));
                }
            }
            if (!nodoActual.esHijoVacio(cantidadClavesNoVacia)){
                colaNodos.offer(nodoActual.getHijo(cantidadClavesNoVacia));
            }
            recorridoPorNiveles(recorrido, colaNodos);
            
        }
    }
    
    @Override
    public List<K> recorridoPreOrden() {
        List<K> recorrido = new ArrayList<>();
        recorridoPreOrden(raiz, recorrido);
        return recorrido;
    }
    
    private void recorridoPreOrden(NodoMvias<K,V> nodoActual, List<K> recorrido){
        if (!NodoMvias.esNodoVacio(nodoActual)){
            int cantidadClavesNvacias = nodoActual.cantidadClavesNoVacias();
            for (short i = 0; i < cantidadClavesNvacias ;i++ ){
                recorrido.add(nodoActual.getClave(i));
                recorridoPreOrden(nodoActual.getHijo(i), recorrido);
            }
            recorridoPreOrden(nodoActual.getHijo(orden-1), recorrido);
        }        
    }

    @Override
    public List<K> recorridoInOrden() {
        List<K> recorrido = new ArrayList<>();
        recorridoInOrden(raiz, recorrido);
        return recorrido;
    }
    private void recorridoInOrden(NodoMvias<K,V> nodoActual, List<K> recorrido){
        if (!NodoMvias.esNodoVacio(nodoActual)){
            int cantidadClavesNvacias = nodoActual.cantidadClavesNoVacias();
            for (short i = 0; i < cantidadClavesNvacias ;i++ ){
                recorridoInOrden(nodoActual.getHijo(i), recorrido);
                recorrido.add(nodoActual.getClave(i));
            }
            recorridoInOrden(nodoActual.getHijo(orden-1), recorrido);
        }
    }

    @Override
    public List<K> recorridoPostOrden() {
        List<K> recorrido = new ArrayList<>();
        recorridoPostOrden(raiz,recorrido);
        return recorrido;
    }
    protected void recorridoPostOrden(NodoMvias<K,V> nodoActual ,List<K> recorrido){
        if (!NodoMvias.esNodoVacio(nodoActual)){
            int cantidadClavesNvacias = nodoActual.cantidadClavesNoVacias();
            recorridoPostOrden(nodoActual.getHijo(0) ,recorrido);
            for (short i = 0; i<cantidadClavesNvacias; i++){
                recorridoPostOrden(nodoActual.getHijo(i+1), recorrido);
                recorrido.add(nodoActual.getClave(i));
            }
        }
    }
}
