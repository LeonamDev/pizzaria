/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pos.java.pizzaria.service;

/**
 *
 * @author leonam
 */
public class ServiceException extends Exception {

    /**
     * Creates a new instance of <code>ServiceException</code> without detail
     * message.
     */
    public ServiceException() {
    }

    /**
     * Constructs an instance of <code>ServiceException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public ServiceException(String msg) {
        super(msg);
    }
}
