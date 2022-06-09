/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package emanuel.flores.leon.arbolesbusqueda;

import emanuel.flores.leon.arbolesbusqueda.Exception.ExceptionClaveNoExiste;

/**
 *
 * @author pc
 */
public class AVL  < K extends Comparable<K> ,V> extends ArbolBinarioBusqueda<K,V>{
    
    private static final byte DIFERENCIA_MAX = 1;
    
    @Override
    public void insertar(K claveInsertar, V valorInsertar) throws NullPointerException {
        if (claveInsertar == null){
            throw new NullPointerException("Clave a insertar no puede ser Nula");
        }
        if (valorInsertar == null){
            throw new NullPointerException("Valor a insertar no puede ser Nula");
        }
        this.raiz = insertar(raiz, claveInsertar, valorInsertar);
    }
    private NodoBinario<K,V> insertar(NodoBinario<K,V> nodoActual ,K claveInsertar, V valorInsertar){
        
        if (NodoBinario.esNodoVacio(nodoActual)){
            NodoBinario<K,V> nuevoNodo = new NodoBinario<K,V>(claveInsertar, valorInsertar);                    
            return nuevoNodo;
        }
        
        K claveActual = nodoActual.getClave();        
        if (claveInsertar.compareTo(claveActual) < 0 ){
            NodoBinario<K,V> nuevoHijoIzquierdo = 
                    insertar(nodoActual.getHijo_izquiezdo(), claveInsertar, valorInsertar);
            nodoActual.setHijo_izquiezdo(nuevoHijoIzquierdo);
            return balancear(nodoActual);
        }
        if (claveInsertar.compareTo(claveActual) > 0 ){
            NodoBinario<K,V> nuevoHijoDerecho = 
                    insertar(nodoActual.getHijo_derecho(), claveInsertar, valorInsertar);
            nodoActual.setHijo_derecho(nuevoHijoDerecho);
            return balancear(nodoActual);
        }
        
        nodoActual.setValor(valorInsertar);
        
        return nodoActual;
    }
    
    private NodoBinario<K,V> balancear(NodoBinario<K,V> nodoActual){
        int alturaPorIzquierda = altura(nodoActual.getHijo_izquiezdo());
        int alturaPorDerecha = altura(nodoActual.getHijo_derecho());
        int diferencia= alturaPorIzquierda - alturaPorDerecha;
        
        if (diferencia > DIFERENCIA_MAX ){ // nodo + ->Rotacion a derecha
            
            NodoBinario<K,V> hijoIzquierdo = nodoActual.getHijo_izquiezdo();
            diferencia = altura(hijoIzquierdo.getHijo_izquiezdo())-altura(hijoIzquierdo.getHijo_derecho());
            if (diferencia <= -DIFERENCIA_MAX ){ // nodo - ->Rotacion doble a derecha
                return rotacionDobleDerecha(nodoActual);
            }
            nodoActual = rotacionSimpleDerecha(nodoActual);
            
                        
        } else 
        if (diferencia < -DIFERENCIA_MAX ){ // nodo - ->Rotacion a izquierda   
            
            NodoBinario<K,V> hijoDerecho = nodoActual.getHijo_derecho();
            diferencia = altura(hijoDerecho.getHijo_izquiezdo())-altura(hijoDerecho.getHijo_derecho());
            if (diferencia >= DIFERENCIA_MAX){ // nodo + ->Rotacion doble a izquierda
                return rotacionDobleIzquierda(nodoActual);
            }
            nodoActual = rotacionSimpleIzquierda(nodoActual);
        }
        
        
        return nodoActual;
    }
    
    private NodoBinario<K,V> rotacionSimpleIzquierda(NodoBinario<K,V> nodito){
       NodoBinario<K,V> nodoRotar = nodito.getHijo_derecho();
       nodito.setHijo_derecho(nodoRotar.getHijo_izquiezdo());
       nodoRotar.setHijo_izquiezdo(nodito);
       return nodoRotar;
    }
    private NodoBinario<K,V> rotacionSimpleDerecha(NodoBinario<K,V> nodito){
        NodoBinario<K,V> nodoRotar = nodito.getHijo_izquiezdo();
        nodito.setHijo_izquiezdo(nodoRotar.getHijo_derecho());
        nodoRotar.setHijo_derecho(nodito);
        return nodoRotar;
        
    }
    private NodoBinario<K,V> rotacionDobleIzquierda(NodoBinario<K,V> nodito){
        NodoBinario<K,V> nuevoHijoDerecho = rotacionSimpleDerecha(nodito.getHijo_derecho());
        nodito.setHijo_derecho(nuevoHijoDerecho);
        return rotacionSimpleIzquierda(nodito);
        
    } 
    private NodoBinario<K,V> rotacionDobleDerecha(NodoBinario<K,V> nodito){
        NodoBinario<K,V> nuevoHijoIzquierdo = rotacionSimpleIzquierda(nodito.getHijo_izquiezdo());
        nodito.setHijo_izquiezdo(nuevoHijoIzquierdo);
        return rotacionSimpleDerecha(nodito);        
    }     

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////    
//    private NodoBinario<K,V> balancear(NodoBinario<K,V> nodoActual){
//        int alturaPorIzquierda = altura(nodoActual.getHijo_izquiezdo());
//        int alturaPorDerecha = altura(nodoActual.getHijo_derecho());
//        int diferencia= alturaPorIzquierda - alturaPorDerecha;
//        
//        if (diferencia > DIFERENCIA_MAX ){ // nodo + ->Rotacion a derecha
//            
//            NodoBinario<K,V> nodoRotar = nodoActual.getHijo_izquiezdo();
//            alturaPorIzquierda = altura(nodoRotar.getHijo_izquiezdo());
//            alturaPorDerecha = altura(nodoRotar.getHijo_derecho());
//            diferencia= alturaPorIzquierda - alturaPorDerecha;
//            if (diferencia <= -DIFERENCIA_MAX){// nodo - ->Rotacion a izquierda
//                nodoRotar = nodoRotar.getHijo_derecho();
//                nodoRotar = rotacionSimpleIzquierda(nodoRotar, nodoActual.getHijo_izquiezdo());
//                nodoActual.setHijo_izquiezdo(nodoRotar);
//            }
//            nodoActual = rotacionSimpleDerecha(nodoRotar, nodoActual); 
//            
//        } else 
//        if (diferencia < -DIFERENCIA_MAX ){ // nodo - ->Rotacion a izquierda
//
//            NodoBinario<K,V> nodoRotar = nodoActual.getHijo_derecho();
//            alturaPorIzquierda = altura(nodoRotar.getHijo_izquiezdo());
//            alturaPorDerecha = altura(nodoRotar.getHijo_derecho());
//            diferencia= alturaPorIzquierda - alturaPorDerecha;
//            if (diferencia >= DIFERENCIA_MAX){// nodo - ->Rotacion a derecha
//                nodoRotar = nodoRotar.getHijo_izquiezdo();
//                nodoRotar = rotacionSimpleDerecha(nodoRotar, nodoActual.getHijo_derecho());
//                nodoActual.setHijo_derecho(nodoRotar);
//            }
//            nodoActual = rotacionSimpleIzquierda(nodoRotar, nodoActual);             
//        }
//        
//        
//        return nodoActual;
//    }
//    private NodoBinario<K,V> rotacionSimpleIzquierda(NodoBinario<K,V> nodoRotar, NodoBinario<K,V> nodoPadre){
//        NodoBinario<K,V> celeste = nodoRotar.getHijo_izquiezdo();
//        nodoRotar.setHijo_izquiezdo(nodoPadre);
//        nodoPadre.setHijo_derecho(celeste);
//          
//        
//        return nodoRotar;
//    }
//    private NodoBinario<K,V> rotacionSimpleDerecha(NodoBinario<K,V> nodoRotar, NodoBinario<K,V> nodoPadre){
//        NodoBinario<K,V> celeste = nodoRotar.getHijo_derecho();
//        nodoRotar.setHijo_derecho(nodoPadre);
//        nodoPadre.setHijo_izquiezdo(celeste);
//          
//        
//        return nodoRotar;
//    }
    
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
            return balancear(nodoActual);
        }
        if (claveEliminar.compareTo(claveActual) < 0){
            NodoBinario<K,V> nuevoHijoIzquierdo = eliminar(nodoActual.getHijo_izquiezdo(), claveEliminar);
            nodoActual.setHijo_izquiezdo(nuevoHijoIzquierdo);
            return balancear(nodoActual);
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
       
       return balancear(nodoActual);
    }
    
}
