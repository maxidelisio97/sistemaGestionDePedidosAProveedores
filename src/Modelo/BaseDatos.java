 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author maxid
 */
public class BaseDatos {

    private Conexion conn;
    private Connection Conexion = null;
    private Statement st = null;
    private PreparedStatement prep = null;
    private ResultSet rs = null;

    public BaseDatos() {

        conn = new Conexion();
        Conexion = conn.getConexion();

    }

    public void insertarProducto(Productos p) {
        try {

            String sql = "INSERT INTO productos ( DESC_PRODUCTO,STOCK, ID_PROVEEDOR) "
                    + "VALUES(?,?,?)";

            prep = Conexion.prepareStatement(sql);
            prep.setString(1, p.getDescripcion());
            prep.setInt(2, p.getStock());           
            prep.setInt(3, p.getIdProveedor());

            prep.execute();

            
        } catch (SQLIntegrityConstraintViolationException ex1) {
            JOptionPane.showMessageDialog(null, "El producto ya esta registrado", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException ex) {
            Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void insertarProveedor(Proveedor p) {
        try {

            String sql = "INSERT INTO proveedor (NOM_PROVEEDOR,DIR_PROVEEDOR,TEL_PROVEEDOR,EMAIL_PROVEEDOR) VALUES (?,?,?,?)";

            prep = Conexion.prepareStatement(sql);
            prep.setString(1, p.getNomProveedor());
            prep.setString(2, p.getDirProveedor());
            prep.setString(3, p.getTelProveedor());
            prep.setString(4, p.getEmailProveedor());

            prep.execute();
            JOptionPane.showMessageDialog(null, "Se ha registrado a un nuevo proveedor", "Nuevo proveedor", 1);

        } catch (SQLIntegrityConstraintViolationException ex1) {
            JOptionPane.showMessageDialog(null, "El proveedor ya esta registrado", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException ex) {
            Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void insertarPedido(Pedido p) {
        try {

            String sql = "INSERT INTO pedido (CANT_SOLICITADA,ID_PRODUCTO, ID_PROVEEDOR) "
                    + "VALUES(?,?,?)";

            prep = Conexion.prepareStatement(sql);
            prep.setInt(1, p.getCant());
            prep.setInt(2, p.getIdProducto());
            prep.setInt(3, p.getIdProveedor());

            prep.execute();

            
        } catch (SQLException ex) {
            Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
     public void insertarDetallePedido(DetallePedido detalle) {
           try {

            String sql = "INSERT INTO detalle_pedido (CANT_SOLICITADA,FECHA_PEDIDO,ID_PRODUCTO, ID_PROVEEDOR)"
                    + "VALUES(?,?,?,?)";

            prep = Conexion.prepareStatement(sql);
            prep.setInt(1, detalle.getCantSolicitada());
            prep.setDate(2, detalle.getFecha());
            prep.setInt(3, detalle.getIdProducto());
            prep.setInt(4,detalle.getIdProveedor());

            prep.execute();

            
        } catch (SQLException ex) {
            Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<Proveedor> getProveedores() {
        ArrayList<Proveedor> listaProveedores = new ArrayList<Proveedor>();

        try {

            st = Conexion.createStatement();

            rs = st.executeQuery("SELECT * FROM proveedor");

            while (rs.next()) {
                int idProveedor = rs.getInt("ID_PROVEEDOR");
                String nomProveedor = rs.getString("NOM_PROVEEDOR");
                String direccion = rs.getString("DIR_PROVEEDOR");
                String telefono = rs.getString("TEL_PROVEEDOR");
                String emailProveedor = rs.getString("EMAIL_PROVEEDOR");

                Proveedor proveedor1 = new Proveedor(idProveedor, nomProveedor, direccion, telefono, emailProveedor);

                listaProveedores.add(proveedor1);

            }
        } catch (Exception ex) {
            ex.printStackTrace();

        }
        return listaProveedores;

    }
    

    public ArrayList<Productos> getProductos() {
        ArrayList<Productos> listaProductos = new ArrayList<Productos>();

        try {

            st = Conexion.createStatement();

            rs = st.executeQuery("SELECT * FROM productos ORDER BY STOCK ASC");

            while (rs.next()) {
                int idProducto = rs.getInt("ID_PRODUCTO");
                String nomProducto = rs.getString("DESC_PRODUCTO");
                int stock = rs.getInt("STOCK");               
                int idProveedor = rs.getInt("ID_PROVEEDOR");

                Productos p = new Productos(idProducto, nomProducto, stock,  idProveedor);

                listaProductos.add(p);

            }
        } catch (Exception ex) {
            ex.printStackTrace();

        }
        return listaProductos;

    }

    public ArrayList<Pedido> getProveedorConPedido() {
        ArrayList<Pedido> listaPedidos = new ArrayList<Pedido>();

        try {

            st = Conexion.createStatement();

            rs = st.executeQuery("SELECT DISTINCT p.ID_PROVEEDOR,p.NOM_PROVEEDOR FROM pedido v INNER JOIN proveedor p ON v.ID_PROVEEDOR = p.ID_PROVEEDOR");

            while (rs.next()) {              
                int idProveedor = rs.getInt("ID_PROVEEDOR");
                String nomProveedor = rs.getString("NOM_PROVEEDOR");

                Pedido p = new Pedido(idProveedor,nomProveedor);

                listaPedidos.add(p);

            }
        } catch (Exception ex) {
            ex.printStackTrace();

        }
        return listaPedidos;

    }
    
    
    public ArrayList<Pedido> getProveedorConPedidoInneJoin(int id) {
        ArrayList<Pedido> listaPedidos = new ArrayList<Pedido>();

        try {

            st = Conexion.createStatement();

            rs = st.executeQuery("SELECT DISTINCT p.ID_PROVEEDOR,p.NOM_PROVEEDOR FROM pedido v INNER JOIN proveedor p ON v.ID_PROVEEDOR = p.ID_PROVEEDOR WHERE v.ID_PROVEEDOR = " + id);

            while (rs.next()) {              
                int idProveedor = rs.getInt("ID_PROVEEDOR");
                String nomProveedor = rs.getString("NOM_PROVEEDOR");

                Pedido p = new Pedido(idProveedor,nomProveedor);

                listaPedidos.add(p);

            }
        } catch (Exception ex) {
            ex.printStackTrace();

        }
        return listaPedidos;

    }
    
    public ArrayList<Pedido> getPedidosInnerJoin() {
        ArrayList<Pedido> listaPedidos = new ArrayList<Pedido>();

        try {

            st = Conexion.createStatement();

            rs = st.executeQuery("SELECT v.ID_PEDIDO,v.NUM_PEDIDO,v.CANT_SOLICITADA,v.ID_PRODUCTO, p.ID_PROVEEDOR,p.NOM_PROVEEDOR,l.DESC_PRODUCTO FROM pedido v INNER JOIN proveedor p ON v.ID_PROVEEDOR = p.ID_PROVEEDOR INNER JOIN productos l ON v.ID_PRODUCTO=l.ID_PRODUCTO");

            while (rs.next()) {
                int idPedido = rs.getInt("ID_PEDIDO");
                int numPedido = rs.getInt("NUM_PEDIDO");
                int cantSolicitada = rs.getInt("CANT_SOLICITADA");
                int idProducto = rs.getInt("ID_PRODUCTO");
                int idProveedor = rs.getInt("ID_PROVEEDOR");
                String nomProveedor = rs.getString("NOM_PROVEEDOR");
                String descProducto = rs.getString("DESC_PRODUCTO");             
                

                Pedido p = new Pedido(idPedido,numPedido,cantSolicitada,idProducto,idProveedor,nomProveedor,descProducto);

                listaPedidos.add(p);

            }
        } catch (Exception ex) {
            ex.printStackTrace();

        }
        return listaPedidos;

    }
    
    public ArrayList<Pedido> getPedidosInnerJoin2(int id) {
        ArrayList<Pedido> listaPedidos = new ArrayList<Pedido>();

        try {

            st = Conexion.createStatement();

            rs = st.executeQuery("SELECT v.ID_PEDIDO,v.NUM_PEDIDO,v.CANT_SOLICITADA,v.ID_PRODUCTO, p.ID_PROVEEDOR,p.NOM_PROVEEDOR,l.DESC_PRODUCTO FROM pedido v INNER JOIN proveedor p ON v.ID_PROVEEDOR = p.ID_PROVEEDOR INNER JOIN productos l ON v.ID_PRODUCTO=l.ID_PRODUCTO WHERE v.ID_PROVEEDOR = " +id);

            while (rs.next()) {
                int idPedido = rs.getInt("ID_PEDIDO");
                int numPedido = rs.getInt("NUM_PEDIDO");
                int cantSolicitada = rs.getInt("CANT_SOLICITADA");
                int idProducto = rs.getInt("ID_PRODUCTO");
                int idProveedor = rs.getInt("ID_PROVEEDOR");
                String nomProveedor = rs.getString("NOM_PROVEEDOR");
                String descProducto = rs.getString("DESC_PRODUCTO");
                

                Pedido p = new Pedido(idPedido,numPedido,cantSolicitada,idProducto,idProveedor,nomProveedor,descProducto);

                listaPedidos.add(p);

            }
        } catch (Exception ex) {
            ex.printStackTrace();

        }
        return listaPedidos;

    }

    public ArrayList<Productos> getProductosInnerJoin() {
        ArrayList<Productos> listaProductos = new ArrayList<Productos>();

        try {

            st = Conexion.createStatement();

            rs = st.executeQuery("SELECT v.ID_PRODUCTO, v.DESC_PRODUCTO, v.STOCK,v.RANKING,p.NOM_PROVEEDOR,p.ID_PROVEEDOR FROM productos v INNER JOIN proveedor p ON v.ID_PROVEEDOR = p.ID_PROVEEDOR ORDER BY RANKING DESC");

            while (rs.next()) {
                int idProducto = rs.getInt("ID_PRODUCTO");
                String nomProducto = rs.getString("DESC_PRODUCTO");
                int stock = rs.getInt("STOCK");               
                String nomProveedor = rs.getString("NOM_PROVEEDOR");
                int idProveedor = rs.getInt("ID_PROVEEDOR");
                int ranking = rs.getInt("RANKING");

                Productos p = new Productos(idProducto, nomProducto, stock, idProveedor, nomProveedor,ranking);

                listaProductos.add(p);

            }
        } catch (Exception ex) {
            ex.printStackTrace();

        }
        return listaProductos;

    }
    
    public ArrayList<Productos> getProductosConstruirPedidoInnerJoin() {
        ArrayList<Productos> listaProductos = new ArrayList<Productos>();

        try {

            st = Conexion.createStatement();

            rs = st.executeQuery("SELECT v.ID_PRODUCTO, v.DESC_PRODUCTO, v.STOCK,p.NOM_PROVEEDOR,p.ID_PROVEEDOR FROM productos v INNER JOIN proveedor p ON v.ID_PROVEEDOR = p.ID_PROVEEDOR ORDER BY STOCK ASC");

            while (rs.next()) {
                int idProducto = rs.getInt("ID_PRODUCTO");
                String nomProducto = rs.getString("DESC_PRODUCTO");
                int stock = rs.getInt("STOCK");               
                String nomProveedor = rs.getString("NOM_PROVEEDOR");
                int idProveedor = rs.getInt("ID_PROVEEDOR");
               

                Productos p = new Productos(idProducto, nomProducto, stock, idProveedor, nomProveedor);

                listaProductos.add(p);

            }
        } catch (Exception ex) {
            ex.printStackTrace();

        }
        return listaProductos;

    }

    public ArrayList<Productos> getProductosInnerJoinConCriterio(String cadena) {
        ArrayList<Productos> listaProductos = new ArrayList<>();

        try {

            st = Conexion.createStatement();

            rs = st.executeQuery("SELECT ID_PRODUCTO,DESC_PRODUCTO,STOCK,NOM_PROVEEDOR,RANKING FROM productos INNER JOIN proveedor ON productos.ID_PROVEEDOR=proveedor.ID_PROVEEDOR WHERE DESCRIPCION_PROD LIKE '%" + cadena + "%'  ORDER BY RANKING DESC");

            while (rs.next()) {
                int idProducto = rs.getInt("ID_PRODUCTO");
                String nomProducto = rs.getString("DESC_PRODUCTO");
                int stock = rs.getInt("STOCK");               
                int idProveedor = rs.getInt("ID_PROVEEDOR");
                int ranking = rs.getInt("RANKING");

                Productos p = new Productos(idProducto, nomProducto, stock, idProveedor,ranking);

                listaProductos.add(p);

            }
        } catch (Exception ex) {
            ex.printStackTrace();

        }
        return listaProductos;

    }
    
     public ArrayList<Productos> getProductosConstruirPedidoInnerJoinConCriterio(String cadena) {
        ArrayList<Productos> listaProductos = new ArrayList<Productos>();

        try {

            st = Conexion.createStatement();

            rs = st.executeQuery("SELECT ID_PRODUCTO,DESC_PRODUCTO,STOCK,NOM_PROVEEDOR FROM productos INNER JOIN proveedor ON productos.ID_PROVEEDOR=proveedor.ID_PROVEEDOR WHERE DESCRIPCION_PROD LIKE '%" + cadena + "%'  ORDER BY STOCK ASC");

            while (rs.next()) {
                int idProducto = rs.getInt("ID_PRODUCTO");
                String nomProducto = rs.getString("DESC_PRODUCTO");
                int stock = rs.getInt("STOCK");               
                int idProveedor = rs.getInt("ID_PROVEEDOR");

                Productos p = new Productos(idProducto, nomProducto, stock, idProveedor);

                listaProductos.add(p);

            }
        } catch (Exception ex) {
            ex.printStackTrace();

        }
        return listaProductos;

    }
    
     public ArrayList<DetallePedido> getDetallePedido() {
        ArrayList<DetallePedido> listaDetalle = new ArrayList<DetallePedido>();

        try {

            st = Conexion.createStatement();

            rs = st.executeQuery("SELECT DISTINCT p.NOM_PROVEEDOR,p.ID_PROVEEDOR,d.FECHA_PEDIDO FROM detalle_pedido d INNER JOIN proveedor p ON d.ID_PROVEEDOR = p.ID_PROVEEDOR");

            while (rs.next()) {              
                int idProveedor = rs.getInt("ID_PROVEEDOR");
                String nomProveedor = rs.getString("NOM_PROVEEDOR");
                Date fecha = rs.getDate("FECHA_PEDIDO");

                DetallePedido detalle = new DetallePedido(idProveedor,nomProveedor,fecha);

                listaDetalle.add(detalle);

            }
        } catch (Exception ex) {
            ex.printStackTrace();

        }
        return listaDetalle;

    }
     
     public ArrayList<DetallePedido> getDetallesPedidos(int id) {
        ArrayList<DetallePedido> listaDetalle = new ArrayList<DetallePedido>();

        try {

            st = Conexion.createStatement();

            rs = st.executeQuery("SELECT d.ID_DETALLE_PEDIDO,d.CANT_SOLICITADA,d.FECHA_PEDIDO,d.ID_PRODUCTO, d.ID_PROVEEDOR,p.NOM_PROVEEDOR,l.DESC_PRODUCTO FROM detalle_pedido d INNER JOIN proveedor p ON d.ID_PROVEEDOR = p.ID_PROVEEDOR INNER JOIN productos l ON d.ID_PRODUCTO=l.ID_PRODUCTO WHERE d.ID_PROVEEDOR = " +id);

            while (rs.next()) {
                int idDetalle = rs.getInt("ID_DETALLE_PEDIDO");
                int cant = rs.getInt("CANT_SOLICITADA");
                Date date = rs.getDate("FECHA_PEDIDO");
                int idProducto = rs.getInt("ID_PRODUCTO");
                int idProveedor = rs.getInt("ID_PROVEEDOR");
                String nomProveedor = rs.getString("NOM_PROVEEDOR");
                String desc = rs.getString("DESC_PRODUCTO");               

                DetallePedido detalle = new DetallePedido(idDetalle,cant,date,idProducto,idProveedor,nomProveedor,desc);

                listaDetalle.add(detalle);

            }
        } catch (Exception ex) {
            ex.printStackTrace();

        }
        return listaDetalle;

    }
     
      public ArrayList<DetallePedido> getDetallePedidoPorId(int id) {
        ArrayList<DetallePedido> listaDetalle = new ArrayList<DetallePedido>();

        try {

            st = Conexion.createStatement();

            rs = st.executeQuery("SELECT DISTINCT p.NOM_PROVEEDOR,p.ID_PROVEEDOR,d.FECHA_PEDIDO FROM detalle_pedido d INNER JOIN proveedor p ON d.ID_PROVEEDOR = p.ID_PROVEEDOR WHERE d.ID_PROVEEDOR= " + id);

            while (rs.next()) {              
                int idProveedor = rs.getInt("ID_PROVEEDOR");
                String nomProveedor = rs.getString("NOM_PROVEEDOR");
                Date fecha = rs.getDate("FECHA_PEDIDO");

                DetallePedido detalle = new DetallePedido(idProveedor,nomProveedor,fecha);

                listaDetalle.add(detalle);

            }
        } catch (Exception ex) {
            ex.printStackTrace();

        }
        return listaDetalle;

    }

    public void actualizarProveedor(Proveedor proveedor) {

        try {

            prep = Conexion.prepareStatement("UPDATE proveedor SET NOM_PROVEEDOR = ?, DIR_PROVEEDOR=?,TEL_PROVEEDOR=?,EMAIL_PROVEEDOR = ? WHERE ID_PROVEEDOR = ?");

            prep.setString(1, proveedor.getNomProveedor());
            prep.setString(2, proveedor.getDirProveedor());
            prep.setString(3, proveedor.getTelProveedor());
            prep.setString(4, proveedor.getEmailProveedor());
            prep.setInt(5, proveedor.getIdProveedor());

            prep.executeUpdate();
           

        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    public void actualizarPedido(Pedido pedido) {

        try {

            prep = Conexion.prepareStatement("UPDATE pedido SET CANT_SOLICITADA = ? WHERE ID_PEDIDO = ?");

            prep.setInt(1, pedido.getCant());
            prep.setInt(2, pedido.getIdPedido());            

            prep.executeUpdate();
           

        } catch (Exception e) {
            e.printStackTrace();

        }
    }
    
    public void actualizarProductos(Productos p) {

        try {

            prep = Conexion.prepareStatement("UPDATE productos SET DESC_PRODUCTO = ?, STOCK=?,ID_PROVEEDOR = ? WHERE ID_PRODUCTO = ?");

            prep.setString(1, p.getDescripcion());
            prep.setInt(2, p.getStock());          
            prep.setInt(3, p.getIdProveedor());
            prep.setInt(4, p.getIdProducto());

            prep.executeUpdate();
           
        } catch (Exception e) {
            e.printStackTrace();

        }
    }
    
    public void cerrarPedido(Productos p){
        try{
            
            prep = Conexion.prepareStatement("UPDATE productos,detalle_pedido SET productos.STOCK= productos.STOCK + (SELECT detalle_pedido.CANT_SOLICITADA FROM detalle_pedido WHERE detalle_pedido.ID_PRODUCTO=?), productos.RANKING= productos.RANKING + (SELECT detalle_pedido.CANT_SOLICITADA FROM detalle_pedido WHERE detalle_pedido.ID_PRODUCTO=?) WHERE productos.ID_PRODUCTO=?");
            
            prep.setInt(1, p.getIdProducto());
            prep.setInt(2, p.getIdProducto());
            prep.setInt(3, p.getIdProducto());
            
            prep.executeUpdate();
            
        }catch(Exception e){
            e.printStackTrace();
            
        }
    }
    
    public void actualizarStock(Productos producto) {

        try {

            prep = Conexion.prepareStatement("UPDATE productos SET STOCK= ? WHERE ID_PRODUCTO= ?");

            prep.setInt(1, producto.getStock());
            prep.setInt(2, producto.getIdProducto());

            prep.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    public void eliminarProveedor(Proveedor p) {
        try {

            prep = Conexion.prepareStatement("DELETE FROM proveedor WHERE ID_PROVEEDOR=?");

            prep.setInt(1, p.getIdProveedor());

            prep.execute();
             
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void eliminarProducto(Productos p) {
        try {

            prep = Conexion.prepareStatement("DELETE FROM productos WHERE ID_PRODUCTO=?");

            prep.setInt(1, p.getIdProducto());

            prep.execute();
           
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Proveedor> obtenerProveedorPorCadenaTexto(String cadena) {
        ArrayList<Proveedor> listaProveedor = new ArrayList<Proveedor>();
        try {

            String sql = "SELECT * FROM proveedor WHERE NOM_PROVEEDOR LIKE '%" + cadena + "%'  ORDER BY NOM_PROVEEDOR";
            st = Conexion.createStatement();
            rs = st.executeQuery(sql);

            while (rs.next()) {

                int idProveedor = rs.getInt("ID_PROVEEDOR");
                String nombre = rs.getString("NOM_PROVEEDOR");
                String direccion = rs.getString("DIR_PROVEEDOR");
                String telefono = rs.getString("TEL_PROVEEDOR");
                String email = rs.getString("EMAIL_PROVEEDOR");

                Proveedor proveedor = new Proveedor(idProveedor, nombre, direccion, telefono, email);
                listaProveedor.add(proveedor);

            }

        } catch (SQLException e) {
            e.printStackTrace();

        }
        return listaProveedor;
    }

    public ArrayList<Productos> obtenerProductosrPorCadenaTexto(String cadena) {
        ArrayList<Productos> listaProductos = new ArrayList<Productos>();
        try {

            String sql = "SELECT v.ID_PRODUCTO,v.DESC_PRODUCTO,v.STOCK,v.RANKING,p.NOM_PROVEEDOR,p.ID_PROVEEDOR FROM productos v INNER JOIN proveedor p ON v.ID_PROVEEDOR = p.ID_PROVEEDOR WHERE DESC_PRODUCTO LIKE '%" + cadena + "%'  ORDER BY RANKING DESC";
            st = Conexion.createStatement();
            rs = st.executeQuery(sql);

            while (rs.next()) {

                int idProducto = rs.getInt("ID_PRODUCTO");
                String desc = rs.getString("DESC_PRODUCTO");
                int stock = rs.getInt("STOCK");               
                String nomProvedor = rs.getString("NOM_PROVEEDOR");
                int id = rs.getInt("ID_PROVEEDOR");
                int ranking = rs.getInt("RANKING");

                Productos productos = new Productos(idProducto, desc, stock, id, nomProvedor,ranking);
                listaProductos.add(productos);

            }

        } catch (SQLException e) {
            e.printStackTrace();

        }
        return listaProductos;
    }

    public void eliminarPedido(Pedido p) {
       try {

            prep = Conexion.prepareStatement("DELETE FROM pedido WHERE ID_PEDIDO=?");

            prep.setInt(1, p.getIdPedido());

            prep.execute();
             
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void eliminarPedidoCompleto(Pedido p) {
       try {

            prep = Conexion.prepareStatement("DELETE FROM pedido WHERE ID_PROVEEDOR=?");

            prep.setInt(1, p.getIdProveedor());

            prep.execute();
             
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void eliminarDetallePedido(DetallePedido detalle) {
      
         try {

            prep = Conexion.prepareStatement("DELETE FROM detalle_pedido WHERE ID_DETALLE_PEDIDO=?");

            prep.setInt(1, detalle.getIdDetallePedido());

            prep.execute();
             
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }

    public boolean login(Usuario usuario) {

    try {

      String SentenciaSql = "SELECT ID, USUARIO, PASSWORD, NOMBRE, ID_TIPO "
          + " FROM usuario_  WHERE USUARIO =?";

      prep = Conexion.prepareStatement(SentenciaSql);

      prep.setString(1, usuario.getUsuario());      
      
      rs = prep.executeQuery();

      if (rs.next()) {
        if (usuario.getPassword().equals(rs.getString(3))) {// CAMPO DE PASSWORD DE BD
        
          usuario.setId(rs.getInt(1));
          usuario.setNombre(rs.getString(4));
          usuario.setId_tipo(rs.getInt(5));
          

          return true;// CUANDO COINCIDA LA CONTRASEÑA DEVUELVE TRUE

        } else {
          return false;
        }

      }
      
      return false;

    } catch (SQLException e) {
        e.printStackTrace();

      return false;
    }
  }
    
    public boolean registrar(Usuario usr){
        
        try {
            prep = Conexion.prepareStatement("INSERT INTO usuario_ (USUARIO,PASSWORD,NOMBRE,ID_TIPO) VALUES(?,?,?,?)");
            
            prep.setString(1, usr.getUsuario());
            prep.setString(2, usr.getPassword());
            prep.setString(3, usr.getNombre());
            prep.setInt(4, usr.getId_tipo());
            
            prep.execute();
            return true;
        } catch (SQLException ex) {
           ex.printStackTrace();
           return false;
        }
        
    }
   

}
