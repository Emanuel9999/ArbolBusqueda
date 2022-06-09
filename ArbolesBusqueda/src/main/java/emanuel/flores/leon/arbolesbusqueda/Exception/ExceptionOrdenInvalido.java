/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Exception.java to edit this template
 */
package emanuel.flores.leon.arbolesbusqueda.Exception;

/**
 *
 * @author pc
 */
public class ExceptionOrdenInvalido extends Exception{

    /**
     * Creates a new instance of <code>ExceptionOrdenInvalido</code> without
     * detail message.
     */
    public ExceptionOrdenInvalido() {
        this("El orden es Invalido tiene que se mayor a 2");
    }

    /**
     * Constructs an instance of <code>ExceptionOrdenInvalido</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public ExceptionOrdenInvalido(String msg) {
        super(msg);
    }
}
