/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sistema;

import java.io.Serializable;


public class Pedido implements Serializable {

    private int pedido;
    private Object obj;

    public Pedido() {
        pedido = 0;
        obj = null;
    }

    public Pedido(int pedido, Object obj) {
        this.pedido = pedido;
        this.obj = obj;
    }

    /**
     * @return the pedido
     */
    public int getPedido() {
        return pedido;
    }

    /**
     * @param pedido the pedido to set
     */
    public void setPedido(int pedido) {
        this.pedido = pedido;
    }

    /**
     * @return the obj
     */
    public Object getObj() {
        return obj;
    }

    /**
     * @param obj the obj to set
     */
    public void setObj(Object obj) {
        this.obj = obj;
    }
}
