    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.Date;
/**
 *
 * @author maxid
 */
public class DetallePedido {
    
    private int idDetallePedido;
    private int cantSolicitada;
    private Date fecha;
    private int idProducto;
    private int idProveedor;
    private String nomProveedor;
    private String descripcion;
    

    public String getNomProveedor() {
        return nomProveedor;
    }

    public void setNomProveedor(String nomProveedor) {
        this.nomProveedor = nomProveedor;
    }

    public DetallePedido() {
    }

    public DetallePedido(int idProveedor, String nomProveedor,Date fecha) {
        this.idProveedor = idProveedor;
        this.nomProveedor = nomProveedor;
        this.fecha = fecha;
    }

    public DetallePedido(int idDetallePedido, int cantSolicitada, Date fecha, int idProducto, int idProveedor, String nomProveedor, String descripcion) {
        this.idDetallePedido = idDetallePedido;
        this.cantSolicitada = cantSolicitada;
        this.fecha = fecha;
        this.idProducto = idProducto;
        this.idProveedor = idProveedor;
        this.nomProveedor = nomProveedor;
        this.descripcion = descripcion;
       
    }
    
    

    public DetallePedido(int cantSolicitada, Date fecha, int idProducto, int idProveedor) {
        this.cantSolicitada = cantSolicitada;
        this.fecha = fecha;
        this.idProducto = idProducto;
        this.idProveedor = idProveedor;
    }

    public DetallePedido(int idDetallePedido, int cantSolicitada, Date fecha, int idProducto, int idProveedor,String nomProveedor) {
        this.idDetallePedido = idDetallePedido;
        this.cantSolicitada = cantSolicitada;
        this.fecha = fecha;
        this.idProducto = idProducto;
        this.idProveedor = idProveedor;
        this.nomProveedor = nomProveedor;
    }

  

    public int getIdDetallePedido() {
        return idDetallePedido;
    }

    public void setIdDetallePedido(int idDetallePedido) {
        this.idDetallePedido = idDetallePedido;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public int getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(int idProveedor) {
        this.idProveedor = idProveedor;
    }

    public int getCantSolicitada() {
        return cantSolicitada;
    }

    public void setCantSolicitada(int cantSolicitada) {
        this.cantSolicitada = cantSolicitada;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return nomProveedor;
    }

   
    
    
}
