/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Exception.java to edit this template
 */
package emanuel.flores.leon.arbolesbusqueda.Exception;

/**
 *
 * @author pc
 */
public class ExceptionCantidadClaves extends Exception{

    /**
     * Creates a new instance of <code>ExceptionCantidadClaves</code> without
     * detail message.
     */
    public ExceptionCantidadClaves() {
        this("No existe elementos en las lista o la cantidad de elementos de las 4 listas no son iguales");
    }

    /**
     * Constructs an instance of <code>ExceptionCantidadClaves</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public ExceptionCantidadClaves(String msg) {
        super(msg);
    }
}
