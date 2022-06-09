/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package emanuel.flores.leon.arbolesbusqueda;

import emanuel.flores.leon.arbolesbusqueda.Exception.ExceptionClaveNoExiste;
import java.util.List;

/**
 *
 * @author pc
 */
public interface IarbolBusqueda <K extends Comparable<K> ,V>{
    void insertar(K claveInsertar, V valorInsertar) throws NullPointerException;
    V eliminar(K claveEliminar)throws NullPointerException,ExceptionClaveNoExiste;
    V buscar(K claveBuscar) throws NullPointerException;
    boolean contiene(K claveBuscar) throws NullPointerException;
    int size();//Cantidad de nodos
    int altura(); 
    void vaciaArbol();
    boolean esArbolVacio();
    List<K> recorridoPorNiveles();
    List<K> recorridoPreOrden();
    List<K> recorridoInOrden();
    List<K> recorridoPostOrden();
}
    
