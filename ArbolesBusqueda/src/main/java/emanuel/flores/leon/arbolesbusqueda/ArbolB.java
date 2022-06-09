/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package emanuel.flores.leon.arbolesbusqueda;

import emanuel.flores.leon.arbolesbusqueda.Exception.ExceptionClaveNoExiste;
import emanuel.flores.leon.arbolesbusqueda.Exception.ExceptionOrdenInvalido;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 *
 * @author pc
 */
public class ArbolB <K extends Comparable<K>,V> extends ArbolMViasBusqueda<K,V>{
    
    private final int MAXIMO_DE_DATOS;
    private final int MINIMO_DE_DATOS;
    private final int MINIMO_DE_HIJOS; //Maximo de hijos es el orden
    
    public ArbolB(){
        super();
        MAXIMO_DE_DATOS = 2;
        MINIMO_DE_DATOS = 1;
        MINIMO_DE_HIJOS = 2;
    }
    
    public ArbolB(int ordeN) throws ExceptionOrdenInvalido{
        super(ordeN);
        MAXIMO_DE_DATOS = orden-1;
        MINIMO_DE_DATOS = MAXIMO_DE_DATOS / 2;
        MINIMO_DE_HIJOS = MINIMO_DE_DATOS + 1;
        
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
            raiz = new NodoMvias<>(orden + 1, claveInsertar, valorInsertar);
            return;
        }  
        
        Stack<NodoMvias<K,V>> pilaAncestros = new Stack<>();
        NodoMvias<K,V> nodoActual = raiz;
        int posicionClaveInsertar = 0;
        int posicionAbajar = 0;
        while (!NodoMvias.esNodoVacio(nodoActual)){
            posicionClaveInsertar = obtenerPocisionDeClave(nodoActual, claveInsertar);
            if (posicionClaveInsertar != POSICION_NO_VALIDA){
                nodoActual.setValor(posicionClaveInsertar, valorInsertar);
                nodoActual = NodoMvias.nodoVacio();
            } else if (nodoActual.esHoja()){
                insercionClaveValorEnNodo(nodoActual, claveInsertar, valorInsertar);
                if (nodoActual.cantidadClavesNoVacias() > MAXIMO_DE_DATOS){
                    dividir(nodoActual, pilaAncestros);
                }
                nodoActual = NodoMvias.nodoVacio();
            }else{
                posicionAbajar = obtenerPocisionPorDondeBajar(nodoActual, claveInsertar);
                pilaAncestros.push(nodoActual);
                nodoActual = nodoActual.getHijo(posicionAbajar);
            }
        }
    }
    //el nodo actual es una hoja y rompio las condiciones de Maximo De Datos
    private void dividir(NodoMvias<K,V> nodoActual, Stack<NodoMvias<K,V>> pilaAncestros){
        if (pilaAncestros.empty()){
            NodoMvias<K,V> primerHijo = new NodoMvias<>(orden + 1);
            NodoMvias<K,V> segundoHijo = new NodoMvias<>(orden + 1 );
            
            cortarYpegarSubNodo(nodoActual, segundoHijo, orden/2 + 1);
            
            V valor = nodoActual.getValor(orden/2);
            K clave = nodoActual.getClave(orden/2);
            nodoActual.setValor(orden/2, (V)NodoMvias.datoVacio());
            nodoActual.setClave(orden/2, (K)NodoMvias.datoVacio());
            
            cortarYpegarSubNodo(nodoActual, primerHijo, 0);
            
            insercionClaveValorEnNodo(nodoActual, clave, valor);
            nodoActual.setHijo(0, primerHijo);
            nodoActual.setHijo(1, segundoHijo);
        }else{
            NodoMvias<K,V> nodoPadre = pilaAncestros.pop();
            NodoMvias<K,V> nodoHermano = new NodoMvias<>(orden + 1);
            
            cortarYpegarSubNodo(nodoActual, nodoHermano, orden/2 + 1);
            
            V valor = nodoActual.getValor(orden/2);
            K clave = nodoActual.getClave(orden/2);
            nodoActual.setValor(orden/2, (V)NodoMvias.datoVacio());
            nodoActual.setClave(orden/2, (K)NodoMvias.datoVacio());
            
            insercionClaveValorEnNodo(nodoPadre, clave, valor);
            
            int posicionClave = obtenerPocisionDeClave(nodoPadre, clave);
            
            for (int ultimo = nodoPadre.cantidadClavesNoVacias(); 
                     ultimo > posicionClave + 1; ultimo--){
                nodoPadre.setHijo(ultimo, nodoPadre.getHijo(ultimo - 1));
            }
            nodoPadre.setHijo(posicionClave + 1 , nodoHermano);
            
            if (nodoPadre.cantidadClavesNoVacias() > MAXIMO_DE_DATOS){
                dividir(nodoPadre, pilaAncestros);
            }
        }
    }
    //Corta el nodo a partir de una posicion en adelante
    private void cortarYpegarSubNodo(NodoMvias<K,V> nodoActual, NodoMvias<K,V> subNodo, int posicion){
        int cantidadClaves = nodoActual.cantidadClavesNoVacias();
        
        for (int i = posicion; i<cantidadClaves; i++){
            
            subNodo.setHijo(i-posicion, nodoActual.getHijo(i));
            insercionClaveValorEnNodo(subNodo, nodoActual.getClave(i), nodoActual.getValor(i));
            
            nodoActual.setHijo(i, NodoMvias.nodoVacio());
            nodoActual.setClave(i, (K)NodoMvias.datoVacio());
            nodoActual.setValor(i, (V)NodoMvias.datoVacio());
            
        } 
        subNodo.setHijo(cantidadClaves - posicion, nodoActual.getHijo(cantidadClaves));
        nodoActual.setHijo(cantidadClaves, NodoMvias.nodoVacio());
      
    }
    
    
    @Override
    public V eliminar(K claveEliminar) throws NullPointerException, ExceptionClaveNoExiste {
        if (claveEliminar == null){
            throw new NullPointerException("La clave no puede ser nula");
        }
        Stack<NodoMvias<K,V>> pilaAncestros = new Stack<>();
        NodoMvias<K,V> nodoActual = buscarNodoDeLaClave(claveEliminar ,pilaAncestros); //Realizar proceso
        if (NodoMvias.esNodoVacio(nodoActual)){
            throw new ExceptionClaveNoExiste();
        }
        int posicionClaveEliminar = this.obtenerPocisionDeClave(nodoActual, claveEliminar);
        V valorAretornar = nodoActual.getValor(posicionClaveEliminar);
        
        if (nodoActual.esHoja()){
            eliminarClaveValorEnNodo(nodoActual, posicionClaveEliminar);
            if (nodoActual.cantidadClavesNoVacias() < MINIMO_DE_DATOS){
                if (pilaAncestros.empty()){
                    if (nodoActual.cantidadClavesNoVacias() == 0){
                        vaciaArbol();
                    }
                }else{
                    prestar_o_fusionarse(nodoActual ,pilaAncestros); //Realizar proceso
                }
            }
        }else{
            pilaAncestros.push(nodoActual);
            NodoMvias<K,V> nodoPredecesor = obtenerNodoClavePredecesora(nodoActual.getHijo(posicionClaveEliminar)
                                            ,pilaAncestros);
            int posicionClavePredecesor = nodoPredecesor.cantidadClavesNoVacias();
            K clavePredecesor = nodoPredecesor.getClave(posicionClavePredecesor-1);
            V valorPredecesor = nodoPredecesor.getValor(posicionClavePredecesor-1);
            
            eliminarClaveValorEnNodo(nodoPredecesor, posicionClavePredecesor);
            
            nodoActual.setClave(posicionClaveEliminar, clavePredecesor);
            nodoActual.setValor(posicionClaveEliminar, valorPredecesor);
            
            if (nodoPredecesor.cantidadClavesNoVacias() < MINIMO_DE_DATOS){
                prestar_o_fusionarse(nodoPredecesor ,pilaAncestros); 
            }
        }
        return valorAretornar;
    }
    
    private NodoMvias<K,V> buscarNodoDeLaClave(K claveEliminar ,Stack<NodoMvias<K,V>> pilaAncestros){
        return null;
    }
    
    private void prestar_o_fusionarse(NodoMvias<K,V> nodoActual ,Stack<NodoMvias<K,V>> pilaAncestros){
        
    }
    private NodoMvias<K,V> obtenerNodoClavePredecesora(NodoMvias<K,V> nodoActual ,Stack<NodoMvias<K,V>> pilaAncestros){
        return null;
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
            recorridoPreOrden(nodoActual.getHijo(cantidadClavesNvacias), recorrido);
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
            recorridoInOrden(nodoActual.getHijo(cantidadClavesNvacias), recorrido);
        }
    }
}
