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
public class Productos {
    
    private int IdProducto;
    private String descripcion;
    private int stock;   
    private int IdProveedor;
    private String nomProveedor;
    private int ranking;

    public Productos() {
    }
    
    

    public Productos(int IdProducto, String descripcion,int stock, int IdProveedor,int ranking) {
        this.IdProducto = IdProducto;
        this.descripcion = descripcion;
        this.stock = stock;        
        this.IdProveedor = IdProveedor;
         this.ranking = ranking;
    }

    public Productos(String descripcion,int stock,  int IdProveedor) {
        this.descripcion = descripcion;
        this.stock = stock;       
        this.IdProveedor = IdProveedor;
    }

    public Productos(int IdProducto, String descripcion, int stock, int IdProveedor, String nomProveedor,int ranking) {
        this.IdProducto = IdProducto;
        this.descripcion = descripcion;
        this.stock = stock;       
        this.IdProveedor = IdProveedor;
        this.nomProveedor = nomProveedor;
        this.ranking = ranking;
    }
    
     public Productos(int IdProducto, String descripcion, int stock, int IdProveedor, String nomProveedor) {
        this.IdProducto = IdProducto;
        this.descripcion = descripcion;
        this.stock = stock;       
        this.IdProveedor = IdProveedor;
        this.nomProveedor = nomProveedor;
        
    }

    public Productos(int IdProducto, String descripcion, int stock, String nomProveedor) {
        this.IdProducto = IdProducto;
        this.descripcion = descripcion;
        this.stock = stock;       
        this.nomProveedor = nomProveedor;
       
    }
    
     public Productos(int IdProducto, String descripcion, int stock, int idProveedor) {
        this.IdProducto = IdProducto;
        this.descripcion = descripcion;
        this.stock = stock;       
        this.IdProveedor = idProveedor;
       
    }

    public Productos(int IdProducto, int stock) {
        this.IdProducto = IdProducto;
        this.stock = stock;
    }
    
    

    public String getNomProveedor() {
        return nomProveedor;
    }

    public void setNomProveedor(String nomProveedor) {
        this.nomProveedor = nomProveedor;
    }

    public int getRanking() {
        return ranking;
    }

    public void setRanking(int ranking) {
        this.ranking = ranking;
    }
    
    

    public int getIdProducto() {
        return IdProducto;
    }

    public void setIdProducto(int IdProducto) {
        this.IdProducto = IdProducto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getIdProveedor() {
        return IdProveedor;
    }

    public void setIdProveedor(int IdProveedor) {
        this.IdProveedor = IdProveedor;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
    
    

    @Override
    public String toString() {
        return  descripcion;
    }
    
    
    
}
