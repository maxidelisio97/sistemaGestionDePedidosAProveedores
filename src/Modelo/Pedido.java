/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author maxid
 */
public class Pedido {
    
    private int IdPedido;
    private int numPedido;
    private int cant;
    private int IdProducto;
    private int IdProveedor;
    private String nomProveedor;
    private String descProducto;   

    public Pedido(int IdPedido, int cant) {
        this.IdPedido = IdPedido;
        this.cant = cant;
    }

    public Pedido() {
    }
    

    
    public Pedido(int IdPedido, int cant, int IdProducto, int IdProveedor) {
        this.IdPedido = IdPedido;
        this.cant = cant;
        this.IdProducto = IdProducto;
        this.IdProveedor = IdProveedor;
    }

    public Pedido(int cant, int IdProducto, int IdProveedor) {
        this.cant = cant;
        this.IdProducto = IdProducto;
        this.IdProveedor = IdProveedor;
    }

    public Pedido(int IdPedido, int numPedido, int cant, int IdProducto, int IdProveedor, String nomProveedor) {
        this.IdPedido = IdPedido;
        this.numPedido = numPedido;
        this.cant = cant;
        this.IdProducto = IdProducto;
        this.IdProveedor = IdProveedor;
        this.nomProveedor = nomProveedor;
    }

    Pedido(int idProveedor, String nomProveedor) {
        this.IdProveedor = idProveedor;
        this.nomProveedor = nomProveedor;
    }

    public Pedido(int IdPedido, int numPedido, int cant, int IdProducto, int IdProveedor, String nomProveedor, String descProducto) {
        this.IdPedido = IdPedido;
        this.numPedido = numPedido;
        this.cant = cant;
        this.IdProducto = IdProducto;
        this.IdProveedor = IdProveedor;
        this.nomProveedor = nomProveedor;
        this.descProducto = descProducto;       
       
    }

    
    

    public String getDescProducto() {
        return descProducto;
    }

    public void setDescProducto(String descProducto) {
        this.descProducto = descProducto;
    }
    

    public int getNumPedido() {
        return numPedido;
    }

    public void setNumPedido(int numPedido) {
        this.numPedido = numPedido;
    }

    public String getNomProveedor() {
        return nomProveedor;
    }

    public void setNomProveedor(String nomProveedor) {
        this.nomProveedor = nomProveedor;
    }

    
    
    public int getIdPedido() {
        return IdPedido;
    }

    public void setIdPedido(int IdPedido) {
        this.IdPedido = IdPedido;
    }

    public int getCant() {
        return cant;
    }

    public void setCant(int cant) {
        this.cant = cant;
    }

    public int getIdProducto() {
        return IdProducto;
    }

    public void setIdProducto(int IdProducto) {
        this.IdProducto = IdProducto;
    }

    public int getIdProveedor() {
        return IdProveedor;
    }

    public void setIdProveedor(int IdProveedor) {
        this.IdProveedor = IdProveedor;
    }

    @Override
    public String toString() {
        return nomProveedor;
    }
    
    
    
}
