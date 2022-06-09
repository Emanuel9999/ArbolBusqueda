/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package emanuel.flores.leon.arbolesbusqueda;

import emanuel.flores.leon.arbolesbusqueda.Exception.ExceptionCantidadClaves;
import emanuel.flores.leon.arbolesbusqueda.Exception.ExceptionClaveNoExiste;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

/**
 *
 * @author pc
 */
public class ArbolBinarioBusqueda  <K extends Comparable<K> ,V> implements IarbolBusqueda<K,V>{
    
    NodoBinario<K,V> raiz;
    
    @Override
    public void insertar(K claveInsertar, V valorInsertar) throws NullPointerException {
        if (claveInsertar == null){
            throw new NullPointerException("Clave a insertar no puede ser Nula");
        }
        if (valorInsertar == null){
            throw new NullPointerException("Valor a insertar no puede ser Nula");
        }
        
        if (esArbolVacio()){
            raiz = new NodoBinario<>(claveInsertar, valorInsertar);
            return;
        }
        
        NodoBinario<K,V> nodoAnterior = NodoBinario.nodoVacio();
        NodoBinario<K,V> nodoActual = raiz;
        
        while (!NodoBinario.esNodoVacio(nodoActual)){
            K claveNodoActual = nodoActual.getClave();
            nodoAnterior = nodoActual;
            if (claveInsertar.compareTo(claveNodoActual)>0){
                //Caso en el que clave a insertar es mayor a nodo actual
                nodoActual = nodoActual.getHijo_derecho();
            }else if (claveInsertar.compareTo(claveNodoActual)<0){
                //Caso en el que clave a insertar en menor a nodo actual
                nodoActual = nodoActual.getHijo_izquiezdo();
            }else{
                //Claver iguales
                nodoActual.setValor(valorInsertar);
                return;
            }
        }
                    
        nodoActual = new NodoBinario<>(claveInsertar,valorInsertar);
        if (claveInsertar.compareTo(nodoAnterior.getClave())>0){
                nodoAnterior.setHijo_derecho(nodoActual);
        }else{
                nodoAnterior.setHijo_izquiezdo(nodoActual);
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
    private NodoBinario<K,V> eliminar(NodoBinario<K,V> nodoActual, K claveEliminar){
        K claveActual = nodoActual.getClave();
        if (claveEliminar.compareTo(claveActual) > 0){
            NodoBinario<K,V> nuevoHijoDerecho = eliminar(nodoActual.getHijo_derecho(), claveEliminar);
            nodoActual.setHijo_derecho(nuevoHijoDerecho);
            return nodoActual;
        }
        if (claveEliminar.compareTo(claveActual) < 0){
            NodoBinario<K,V> nuevoHijoIzquierdo = eliminar(nodoActual.getHijo_izquiezdo(), claveEliminar);
            nodoActual.setHijo_izquiezdo(nuevoHijoIzquierdo);
            return nodoActual;
        }

        // caso1
        if (nodoActual.esHoja()){
            return NodoBinario.nodoVacio();
        }
        //  caso2
        if (nodoActual.hijoDerechoEsvacio() && !nodoActual.hijoIzquierdoEsvacio()){
            return nodoActual.getHijo_izquiezdo();
        }
        if (!nodoActual.hijoDerechoEsvacio() && nodoActual.hijoIzquierdoEsvacio()){
            return nodoActual.getHijo_derecho();
        }
        // caso 3
       NodoBinario<K,V> nododelSucesor = nodoSucesor(nodoActual.getHijo_derecho());
       NodoBinario<K,V> nuevoNodoDerecho = eliminar(nodoActual.getHijo_derecho(), nododelSucesor.getClave());
       
       nodoActual.setHijo_derecho(nuevoNodoDerecho);
       nodoActual.setClave(nododelSucesor.getClave());
       nodoActual.setValor(nododelSucesor.getValor());
       
       return nodoActual;
    }
    protected NodoBinario<K,V> nodoSucesor(NodoBinario<K,V> hijoDerecho){
        if (!hijoDerecho.hijoIzquierdoEsvacio()){
            return nodoSucesor(hijoDerecho.getHijo_izquiezdo());
        }else{
            return hijoDerecho;
        }
    }
    

    @Override
    public V buscar(K claveBuscar) throws NullPointerException {
        V valor = null;
       if (claveBuscar==null){
           throw new NullPointerException("Clave a buscar no puede ser Nula");
       }
       
       NodoBinario<K,V> nodoActual = raiz;
       
       while (!NodoBinario.esNodoVacio(nodoActual)){
           if (claveBuscar.compareTo(nodoActual.getClave())>0){
               nodoActual = nodoActual.getHijo_derecho();
           }else if (claveBuscar.compareTo(nodoActual.getClave())<0){
               nodoActual = nodoActual.getHijo_izquiezdo();
           }else{
               valor = nodoActual.getValor();
               nodoActual = NodoBinario.nodoVacio();
           }
       }
       return valor;
    }

    @Override
    public boolean contiene(K claveBuscar) throws NullPointerException {
        return buscar(claveBuscar)!=null;
    }

    @Override
    public int size() {
        return size(raiz);
    }
    private int size(NodoBinario<K,V> nodoActual){
        if (!NodoBinario.esNodoVacio(nodoActual)){
            int sizeIzq = size(nodoActual.getHijo_izquiezdo());
            int sizeDer = size(nodoActual.getHijo_derecho());
            return sizeIzq + sizeDer + 1;
        }
        return 0;
    }

    @Override
    public int altura() {
        return altura(raiz);
    }
    protected int altura(NodoBinario nodoActual){
        int alturaArb=0;
        
        if (!NodoBinario.esNodoVacio(nodoActual)){
            int alturaIzq = altura(nodoActual.getHijo_izquiezdo());
            int alturaDer = altura(nodoActual.getHijo_derecho());
            
            if (alturaIzq > alturaDer){
                alturaArb = alturaIzq + 1;
            }else{
                alturaArb = alturaDer + 1;
            }
        }
        return alturaArb;
    }

    @Override
    public void vaciaArbol() {
         raiz=NodoBinario.nodoVacio();
    }

    @Override
    public boolean esArbolVacio() {
        return NodoBinario.esNodoVacio(raiz);
    }

    @Override
    public List<K> recorridoPorNiveles() {
        List<K> recorrido = new ArrayList<>();
        if (esArbolVacio()){
            return recorrido;
        }
        
        Queue< NodoBinario<K,V> > colaNodos = new LinkedList<>();
        colaNodos.offer(raiz);
        NodoBinario<K,V> nodoActual=NodoBinario.nodoVacio();
        
        while (!colaNodos.isEmpty()){
            nodoActual = colaNodos.poll();
            recorrido.add(nodoActual.getClave());
                
            if (!nodoActual.hijoIzquierdoEsvacio()){
                colaNodos.offer(nodoActual.getHijo_izquiezdo());
            }
            
            if (!nodoActual.hijoDerechoEsvacio()){
                colaNodos.offer(nodoActual.getHijo_derecho());
            }
            
        }
        
        return recorrido;
    }

    @Override
    public List<K> recorridoPreOrden() {
        List<K> recorrido = new ArrayList<>();
        
        if (!esArbolVacio()){
            Stack< NodoBinario<K,V> > pilaNodos= new Stack<>();
            pilaNodos.push(raiz);
            NodoBinario<K,V> nodoActual = NodoBinario.nodoVacio();
            
            while (!pilaNodos.isEmpty()){
                nodoActual = pilaNodos.pop();
                recorrido.add(nodoActual.getClave());
                
                if (!nodoActual.hijoDerechoEsvacio()){
                    pilaNodos.push(nodoActual.getHijo_derecho());
                }
                
                if (!nodoActual.hijoIzquierdoEsvacio()){
                    pilaNodos.push(nodoActual.getHijo_izquiezdo());
                }
                
            }
        }
        return recorrido;
    }

    @Override
    public List<K> recorridoInOrden() {
        List<K> recorrido = new ArrayList<>();
        recorridoInOrden(raiz, recorrido);
        return recorrido;
    }
    
    private void recorridoInOrden(NodoBinario<K,V> nodoActual, List<K> recorrido){
        if (!NodoBinario.esNodoVacio(nodoActual)){
            recorridoInOrden(nodoActual.getHijo_izquiezdo(), recorrido);
            recorrido.add(nodoActual.getClave());
            recorridoInOrden(nodoActual.getHijo_derecho(), recorrido);
        }
    }


    @Override
    public List<K> recorridoPostOrden() {
        List<K> recorrido = new ArrayList<>();
        recorridoPostOrden(raiz, recorrido);
        return recorrido;
    }
    
    private void recorridoPostOrden(NodoBinario<K,V> nodoActual, List recorrido){
        if (!NodoBinario.esNodoVacio(nodoActual)){
            recorridoPostOrden(nodoActual.getHijo_izquiezdo(), recorrido);
            recorridoPostOrden(nodoActual.getHijo_derecho(), recorrido);
            recorrido.add(nodoActual.getClave());
        }
    }
    //respuesta2 Recorrido postOrden iterativo
    public List<K> recorridoPostOrdenIterativo(){
        List<K> recorrido = new ArrayList<>();
        Stack<NodoBinario<K,V>> pilaNodos = new Stack<>();
        NodoBinario<K,V> nodoActual = NodoBinario.nodoVacio();
        pilaNodos.push(raiz);
        K ultimaClave = raiz.getClave();
        while (!pilaNodos.isEmpty()){
            nodoActual = pilaNodos.pop();
            if (!nodoActual.esHoja()){
                if (!nodoActual.hijoDerechoEsvacio() &&
                    ultimaClave.compareTo(nodoActual.getHijo_derecho().getClave()) == 0){
                    
                    ultimaClave = nodoActual.getClave();
                    recorrido.add(ultimaClave);
                }else if (!nodoActual.hijoIzquierdoEsvacio() &&
                    ultimaClave.compareTo(nodoActual.getHijo_izquiezdo().getClave()) == 0){
                    
                    ultimaClave = nodoActual.getClave();
                    recorrido.add(ultimaClave);
                }else{
                    pilaNodos.push(nodoActual);
                    if (!nodoActual.hijoDerechoEsvacio()){
                        pilaNodos.push(nodoActual.getHijo_derecho());
                    }
                    if (!nodoActual.hijoIzquierdoEsvacio()){
                        pilaNodos.push(nodoActual.getHijo_izquiezdo());
                    }
                }
            }else{
                ultimaClave = nodoActual.getClave();
                recorrido.add(ultimaClave);
            }
        }
        return recorrido;        
    }
    //respuesta 1 //Reconstruccion de arbol
    public void reconstruirArbol(List<K> clavesInOrden , List<K> clavesPostOrden
                                 ,List<V> valoresInOrden, List<V> valoresPostOrden)throws ExceptionCantidadClaves{
        
        int n = clavesInOrden.size();
        if (n != clavesPostOrden.size() || n != valoresInOrden.size() || 
            n !=  valoresPostOrden.size() || n == 0 ){
            
            throw new ExceptionCantidadClaves();
        }
        K claveAbuscar = clavesPostOrden.get(n - 1);
        final int MITAD = posicionDeClaveEnLista(clavesInOrden, claveAbuscar);
        
        raiz = new NodoBinario<>(clavesInOrden.get(MITAD) ,valoresInOrden.get(MITAD));
        List<NodoBinario<K,V>> listaNodos = new ArrayList<>();
        for (int i = 0; i < n; i++ ){
            listaNodos.add(NodoBinario.nodoVacio());
        } 
        listaNodos.set(MITAD, raiz);
        int posicion;
        for (int i = n - 1; i > 0; i--){
            claveAbuscar = clavesPostOrden.get(i - 1);
            posicion = posicionDeClaveEnLista(clavesInOrden, claveAbuscar);
            NodoBinario<K,V> nuevoNodo = new NodoBinario<>(clavesInOrden.get(posicion) ,valoresInOrden.get(posicion));
            listaNodos.set(posicion, nuevoNodo);
            if (posicion > MITAD){
                enlazarIzquierda(listaNodos,nuevoNodo,posicion);
            }else{
                enlazarDerecha(listaNodos,nuevoNodo,posicion);
            }
        }
    }
    private int posicionDeClaveEnLista(List<K> clavesInOrden, K clavePosOrden){
        int n = clavesInOrden.size();
        int posicion = 0;
        while (posicion < n){
            if (clavePosOrden.compareTo(clavesInOrden.get(posicion)) == 0){
                return posicion;
            }
            posicion++;
        }
        return -1;
    }  
    private void enlazarIzquierda(List<NodoBinario<K,V>> listaNodos ,NodoBinario<K,V> nuevoNodo ,int posicion){
        NodoBinario<K,V> nodoPadre = this.buscarAizquierda(listaNodos, posicion);
        if (NodoBinario.esNodoVacio(nodoPadre.getHijo_derecho())){
            nodoPadre.setHijo_derecho(nuevoNodo);
        }else{
            nodoPadre = this.buscarAderecha(listaNodos, posicion);
            nodoPadre.setHijo_izquiezdo(nuevoNodo);
        }
    }
    private void enlazarDerecha(List<NodoBinario<K,V>> listaNodos ,NodoBinario<K,V> nuevoNodo ,int posicion){
        NodoBinario<K,V> nodoPadre = this.buscarAderecha(listaNodos, posicion);
        if (NodoBinario.esNodoVacio(nodoPadre.getHijo_izquiezdo())){
            nodoPadre.setHijo_izquiezdo(nuevoNodo);
        }else{
            nodoPadre = this.buscarAizquierda(listaNodos, posicion);
            nodoPadre.setHijo_derecho(nuevoNodo);
        }
    }
    private NodoBinario<K,V> buscarAizquierda(List<NodoBinario<K,V>> listaNodos ,int posicion){
        NodoBinario<K,V> nodito = NodoBinario.nodoVacio();
        
        while (posicion > 0){
            posicion--;
            if (!NodoBinario.esNodoVacio(listaNodos.get(posicion))){
                nodito = listaNodos.get(posicion);
                posicion = 0;
            }
        }
        return nodito;
        
    }
    private NodoBinario<K,V> buscarAderecha(List<NodoBinario<K,V>> listaNodos ,int posicion){
        NodoBinario<K,V> nodito = NodoBinario.nodoVacio();
        int n = listaNodos.size();
        while (posicion < n){
            posicion++;
            if (!NodoBinario.esNodoVacio(listaNodos.get(posicion))){
                nodito = listaNodos.get(posicion);
                posicion = n;
            }
        }
        return nodito;
    }
}

   
