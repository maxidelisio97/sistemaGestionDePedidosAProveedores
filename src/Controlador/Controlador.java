/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.BaseDatos;
import Modelo.Conexion;
import Modelo.DetallePedido;
import Modelo.Pedido;
import Modelo.Productos;
import Modelo.Proveedor;
import Modelo.Usuario;
import Vista.*;
import java.sql.Connection;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.time.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JInternalFrame;
import javax.swing.JTable;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.table.DefaultTableCellRenderer;
import modelo.Hash;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author maxid
 */
public class Controlador {

    private VistaConstruirPedido vConstruirPedido;
    private VistaGestionarPedido vGestionarPedido;
    private VistaRecepcionPedido vRecepcionPedido;
    private VistaGestionarProveedor vGestionarProveedor;
    private VistaGestionarProducto vGestionarProducto;
    private VistaListaProductos vListaProducto;
    private VistaInicio v;
    private VistaVentas vistaVentas;
    private VistaListaRecepcionPedido vListaRecepcionPedido;
    private VistaLogin vistaLogin;

    private BaseDatos base;
    private DefaultTableModel modeloTablaProductos;
    private DefaultTableModel modeloTablaConstruirPedido;
    private DefaultTableModel modeloTablaProveedor;
    private DefaultTableModel modeloTablaPedido;
    private DefaultTableModel modeloTablaRecepcionPedido;
    private DefaultTableModel modeloTablaListaProductos;
    private DefaultTableModel modeloTablaListaRecepcionPedido;

    private Connection conn;
    private Conexion Conexion;

    public Controlador() {

    }

    public Controlador(VistaInicio v, BaseDatos base) {
        Conexion = new Conexion();
        conn = Conexion.getConexion();
        vConstruirPedido = new VistaConstruirPedido();
        vGestionarPedido = new VistaGestionarPedido();
        vRecepcionPedido = new VistaRecepcionPedido();
        vGestionarProveedor = new VistaGestionarProveedor();
        vGestionarProducto = new VistaGestionarProducto();
        vListaProducto = new VistaListaProductos();
        vistaVentas = new VistaVentas();
        vListaRecepcionPedido = new VistaListaRecepcionPedido();
        //vistaLogin = new VistaLogin();

        modeloTablaProductos = (DefaultTableModel) vGestionarProducto.tablaProductos.getModel();
        modeloTablaConstruirPedido = (DefaultTableModel) vConstruirPedido.tablaConstruirPedido.getModel();
        modeloTablaProveedor = (DefaultTableModel) vGestionarProveedor.tablaProveedor.getModel();
        modeloTablaPedido = (DefaultTableModel) vGestionarPedido.tablaGestionarPedidos.getModel();
        modeloTablaRecepcionPedido = (DefaultTableModel) vRecepcionPedido.tablaRecepcionPedidos.getModel();
        modeloTablaListaProductos = (DefaultTableModel) vListaProducto.tablaListaProducto.getModel();
        modeloTablaListaRecepcionPedido = (DefaultTableModel) vListaRecepcionPedido.tablaListaRecepcionPedido.getModel();

        this.v = v;
        this.base = base;

        cargarProveedoresEnComboBox();
        cargarModeloTablaProveedores(modeloTablaProveedor);
        cargarModeloTablaProductos(modeloTablaProductos);
        cargarModeloTablaConstruirPedido(modeloTablaConstruirPedido);
        cargarProveedoresConPedidoEnComboBox();
        cargarModeloTablaPedidos(modeloTablaPedido);
        cargarComboRecepcionDePedido();
        cargarModeloTablaArriba();
        cargarModeloTablaRecepcionPedido(modeloTablaRecepcionPedido);

        this.v.btnConstruccionPedido.addActionListener((ActionEvent e) -> {
            abrirFrameConstruirPedido(e);
        });

        this.v.btnGestionPedido.addActionListener((ActionEvent e) -> {
            abrirFrameGestionarPedido(e);
            cargarProveedoresConPedidoEnComboBox();
        });

        this.vConstruirPedido.btnAltaProducto.addActionListener((ActionEvent e) -> {
            abrirFrameGestionarProducto(e);
        });

        this.v.btnRecepcionPedido.addActionListener((ActionEvent e) -> {
            abrirFrameRecepcionPedido(e);
        });

        this.v.btnGestionarProducto.addActionListener((ActionEvent e) -> {
            abrirFrameGestionarProducto(e);
        });

        this.v.btnGestionarProveedor.addActionListener((ActionEvent e) -> {
            abrirFrameGestionarProveedor(e);
        });

        this.v.btnVenta.addActionListener((ActionEvent e) -> {
            abrirFrameVenta(e);
        });

        this.vGestionarProveedor.btnAltaProveedor.addActionListener((ActionEvent e) -> {
            insertarNuevoProveedor(e, modeloTablaProveedor);
        });

        this.vGestionarProducto.btnAltaProducto.addActionListener((ActionEvent e) -> {
            insertarNuevoProducto(e, modeloTablaProductos);
            cargarModeloTablaProductos(modeloTablaProductos);
        });

        this.vConstruirPedido.btnAnadirProducto.addActionListener((ActionEvent e) -> {
            insertarNuevoProductoEnPedido(e, modeloTablaConstruirPedido);
            cargarModeloTablaPedidos(modeloTablaPedido);
        });

        this.vGestionarProveedor.tablaProveedor.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                getSeleccionEnTablaProveedor();

            }

        });

        this.vGestionarProducto.tablaProductos.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                getSeleccionEnTablaProductos(e);

            }

        });

        this.vConstruirPedido.tablaConstruirPedido.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                getSeleccionEnTablaConstruirPedido(e);

            }

        });

        this.vGestionarPedido.tablaGestionarPedidos.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                getSeleccionEnTablaGestionarPedidos(e, modeloTablaListaProductos);
                cargarModeloTablaPedidos(modeloTablaPedido);

            }

        });
        this.vRecepcionPedido.tablaRecepcionPedidos.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                getSeleccionEnTablaRecepcionPedidos(e, modeloTablaListaRecepcionPedido);
                cargarModeloTablaRecepcionPedido(modeloTablaRecepcionPedido);

            }

        });

        this.vListaProducto.tablaListaProducto.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                getSeleccionEnTablaListaProductos(e);

            }

        });

        this.vGestionarProveedor.btnModificarProveedor.addActionListener((ActionEvent e) -> {
            actualizarProveedor(e, modeloTablaProveedor);
        });

        this.vGestionarProducto.btnModificarProducto.addActionListener((ActionEvent e) -> {
            actualizarProductos(e, modeloTablaProductos);
        });

        this.vListaProducto.btnModificarListaProducto.addActionListener((ActionEvent e) -> {
            actualizarPedido(e, modeloTablaListaProductos);
        });

        this.vListaProducto.btnPedidoRealizado.addActionListener((ActionEvent e) -> {
            pedidoRealizado(modeloTablaListaProductos);
        });

        this.vListaRecepcionPedido.btnCerrarPedido.addActionListener((ActionEvent e) -> {
            cerrarPedido(modeloTablaListaRecepcionPedido);
        });

        this.vGestionarProveedor.btnBajaProveedor.addActionListener((ActionEvent e) -> {
            eliminarProveedor(e, modeloTablaProveedor);
        });

        this.vGestionarProducto.btnBajaProducto.addActionListener((ActionEvent e) -> {
            eliminarProducto(e, modeloTablaProductos);
        });

        this.vListaProducto.btnQuitarListaProducto.addActionListener((ActionEvent e) -> {
            eliminarPedido(e, modeloTablaListaProductos, modeloTablaPedido);
        });

        this.vGestionarProveedor.txtBuscarProveedor.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                consultarProveedorConCriterio(e);
            }
        });

        this.vGestionarProducto.txtBuscarProducto.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                consultarProductosConCriterio(e);
            }
        });

        this.vConstruirPedido.txtBuscarProducto.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                consultarProductosConCriterio2(e);
            }
        });

        this.vistaVentas.txtDesc.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                consultarProductosConCriterio3(e);
            }
        });

        this.vGestionarPedido.btnConsultar.addActionListener((ActionEvent e) -> {
            consultarPedido(e);
        });

        this.vRecepcionPedido.btnConsultar.addActionListener((ActionEvent e) -> {
            consultarRecepcionPedido(e);
        });

        this.vistaVentas.btnAnadir.addActionListener((ActionEvent e) -> {
            anadirProductoTablaAbajo(e);
            cargarModeloTablaArriba();
        });

        this.vistaVentas.btnCancelar.addActionListener((ActionEvent e) -> {
            limpiarTablaAbajo();
        });

        this.vistaVentas.btnQuitarProd.addActionListener((ActionEvent e) -> {
            quitarProductoTablaAbajo();
        });

        this.vistaVentas.btnAceptar.addActionListener((ActionEvent e) -> {
            restarStock();
        });

        this.vListaProducto.btnCerrarLista.addActionListener((ActionEvent e) -> {
            cerrarListaProducto(e, vListaProducto);
        });
        this.vListaRecepcionPedido.btnCerrar.addActionListener((ActionEvent e) -> {
            cerrarListaProducto(e, vListaRecepcionPedido);
        });
        this.vListaProducto.btnImprimirPedido.addActionListener((ActionEvent e) -> {
           imprimirReporte();
        });

    }

    public void abrirFrameConstruirPedido(ActionEvent e) {
        v.escritorio.removeAll();
        v.escritorio.repaint();
        v.escritorio.add(BorderLayout.CENTER, this.vConstruirPedido);
        v.escritorio.getDesktopManager().maximizeFrame(vConstruirPedido);
        this.vConstruirPedido.show();
    }

    public void abrirFrameGestionarPedido(ActionEvent e) {
        v.escritorio.removeAll();
        v.escritorio.repaint();
        v.escritorio.add(BorderLayout.CENTER, this.vGestionarPedido);
        v.escritorio.getDesktopManager().maximizeFrame(vGestionarPedido);
        this.vGestionarPedido.show();
    }

    public void abrirFrameRecepcionPedido(ActionEvent e) {
        v.escritorio.removeAll();
        v.escritorio.repaint();
        v.escritorio.add(BorderLayout.CENTER, this.vRecepcionPedido);
        v.escritorio.getDesktopManager().maximizeFrame(vRecepcionPedido);
        this.vRecepcionPedido.show();
    }

    public void abrirFrameGestionarProveedor(ActionEvent e) {
        v.escritorio.removeAll();
        v.escritorio.repaint();
        v.escritorio.add(BorderLayout.CENTER, this.vGestionarProveedor);
        v.escritorio.getDesktopManager().maximizeFrame(vGestionarProveedor);
        this.vGestionarProveedor.show();
    }

    public void abrirFrameGestionarProducto(ActionEvent e) {
        v.escritorio.removeAll();
        v.escritorio.repaint();
        v.escritorio.add(BorderLayout.CENTER, this.vGestionarProducto);
        v.escritorio.getDesktopManager().maximizeFrame(vGestionarProducto);
        this.vGestionarProducto.show();
    }

    private void abrirFrameVenta(ActionEvent e) {
        v.escritorio.removeAll();
        v.escritorio.repaint();
        v.escritorio.add(BorderLayout.CENTER, this.vistaVentas);
        v.escritorio.getDesktopManager().maximizeFrame(vistaVentas);
        this.vistaVentas.show();
    }

    public void crearVistaListaProductos() {

        v.escritorio.removeAll();
        v.escritorio.repaint();
        v.escritorio.add(BorderLayout.CENTER, this.vListaProducto);
        v.escritorio.getDesktopManager().maximizeFrame(vListaProducto);
        this.vListaProducto.show();
    }

    public void crearVistaListaRecepcion() {

        v.escritorio.removeAll();
        v.escritorio.repaint();
        v.escritorio.add(BorderLayout.CENTER, this.vListaRecepcionPedido);
        v.escritorio.getDesktopManager().maximizeFrame(vListaRecepcionPedido);
        this.vListaRecepcionPedido.show();
    }

    private void cargarProveedoresEnComboBox() {
        vGestionarProducto.modeloComboProveedores.removeAllElements();
        ArrayList<Proveedor> listaProveedores = new ArrayList<>();
        listaProveedores = base.getProveedores();

        for (Proveedor c : listaProveedores) {
            vGestionarProducto.modeloComboProveedores.addElement(c);
        }

    }

    private void cargarProveedoresConPedidoEnComboBox() {
        vGestionarPedido.modeloComboProveedorConPedido.removeAllElements();
        ArrayList<Pedido> listaPedido = new ArrayList<>();
        listaPedido = base.getProveedorConPedido();

        for (Pedido c : listaPedido) {
            vGestionarPedido.modeloComboProveedorConPedido.addElement(c);

        }

    }

    private void cargarComboRecepcionDePedido() {
        vRecepcionPedido.modeloComboProveedorConPedido.removeAllElements();
        ArrayList<DetallePedido> listaPedido = new ArrayList<>();
        listaPedido = base.getDetallePedido();

        for (DetallePedido c : listaPedido) {
            vRecepcionPedido.modeloComboProveedorConPedido.addElement(c);

        }

    }

    private void cargarModeloTablaProveedores(DefaultTableModel modeloTabla) {
        limpiarTabla(modeloTabla);
        ArrayList<Proveedor> listaProveedor = base.getProveedores();
        int numeroProveedor = listaProveedor.size();
        modeloTabla.setNumRows(numeroProveedor);

        for (int i = 0; i < numeroProveedor; i++) {

            Proveedor proveedor = listaProveedor.get(i);
            String direccion = proveedor.getDirProveedor();
            String telefono = proveedor.getTelProveedor();
            String email = proveedor.getEmailProveedor();

            modeloTabla.setValueAt(proveedor, i, 0);
            modeloTabla.setValueAt(direccion, i, 1);
            modeloTabla.setValueAt(telefono, i, 2);
            modeloTabla.setValueAt(email, i, 3);

        }

    }

    private void cargarModeloTablaProductos(DefaultTableModel modeloTabla) {
        limpiarTabla(modeloTabla);
        ArrayList<Productos> listaProductos = base.getProductosInnerJoin();
        int numeroProducto = listaProductos.size();
        modeloTabla.setNumRows(numeroProducto);

        for (int i = 0; i < numeroProducto; i++) {

            Productos producto = listaProductos.get(i);
            int stock = producto.getStock();
            String nomProveedor = producto.getNomProveedor();
            int ranking = producto.getRanking();

            modeloTabla.setValueAt(producto, i, 0);
            modeloTabla.setValueAt(stock, i, 1);
            modeloTabla.setValueAt(nomProveedor, i, 2);
            modeloTabla.setValueAt(ranking, i, 3);

        }

    }

    private void cargarModeloTablaConstruirPedido(DefaultTableModel modeloTabla) {
        limpiarTabla(modeloTabla);
        ArrayList<Productos> listaProductos = base.getProductosConstruirPedidoInnerJoin();
        int numeroProducto = listaProductos.size();
        modeloTabla.setNumRows(numeroProducto);

        for (int i = 0; i < numeroProducto; i++) {

            Productos producto = listaProductos.get(i);
            int stock = producto.getStock();
            String nomProveedor = producto.getNomProveedor();

            modeloTabla.setValueAt(producto, i, 0);
            modeloTabla.setValueAt(stock, i, 1);
            modeloTabla.setValueAt(nomProveedor, i, 2);

        }
        ColorearFilas colorear = new ColorearFilas(1);
        vConstruirPedido.tablaConstruirPedido.getColumnModel().getColumn(1).setCellRenderer(colorear);
    }

    private void cargarModeloTablaPedidos(DefaultTableModel modeloTabla) {
        limpiarTabla(modeloTabla);
        ArrayList<Pedido> listaPedido = base.getProveedorConPedido();
        int numeroPedido = listaPedido.size();
        modeloTabla.setNumRows(numeroPedido);

        for (int i = 0; i < numeroPedido; i++) {

            Pedido pedido = listaPedido.get(i);

            modeloTabla.setValueAt(pedido, i, 0);
        }

    }

    private void cargarModeloTablaRecepcionPedido(DefaultTableModel modeloTabla) {
        limpiarTabla(modeloTabla);
        ArrayList<DetallePedido> listaPedido = base.getDetallePedido();
        int numeroPedido = listaPedido.size();
        modeloTabla.setNumRows(numeroPedido);

        for (int i = 0; i < numeroPedido; i++) {

            DetallePedido detallePedido = listaPedido.get(i);
            Date date = detallePedido.getFecha();

            modeloTabla.setValueAt(date, i, 0);
            modeloTabla.setValueAt(detallePedido, i, 1);
        }

    }

    private void cargarModeloTablaArriba() {

        ArrayList<Productos> listaProducto = base.getProductosInnerJoin();
        int numeroProducto = listaProducto.size();
        vistaVentas.modeloTablaProductosArriba.setNumRows(numeroProducto);

        for (int i = 0; i < numeroProducto; i++) {

            Productos producto = listaProducto.get(i);
            int stock = producto.getStock();
            int id = producto.getIdProducto();
            String nomProveedor = producto.getNomProveedor();

            vistaVentas.modeloTablaProductosArriba.setValueAt(id, i, 0);
            vistaVentas.modeloTablaProductosArriba.setValueAt(producto, i, 1);
            vistaVentas.modeloTablaProductosArriba.setValueAt(stock, i, 2);
            vistaVentas.modeloTablaProductosArriba.setValueAt(nomProveedor, i, 3);

        }

    }

    public void getSeleccionEnTablaProveedor() {

        if ((vGestionarProveedor.tablaProveedor.getSelectedRow() >= 0)) {

            Proveedor proveedor = (Proveedor) vGestionarProveedor.modeloTablaProveedores.getValueAt(vGestionarProveedor.tablaProveedor.getSelectedRow(), 0);

            vGestionarProveedor.txtIdProveedor.setText(String.valueOf(proveedor.getIdProveedor()));
            vGestionarProveedor.txtNombreProveedor.setText(proveedor.getNomProveedor());
            vGestionarProveedor.txtDirreccionProveedor.setText(proveedor.getDirProveedor());
            vGestionarProveedor.txtTelefonoProveedor.setText(proveedor.getTelProveedor());
            vGestionarProveedor.txtEmailProveedor.setText(proveedor.getEmailProveedor());

        }

    }

    public void getSeleccionEnTablaProductos(MouseEvent e) {

        if ((vGestionarProducto.tablaProductos.getSelectedRow() >= 0)) {

            Productos producto = (Productos) vGestionarProducto.modeloTablaProductos.getValueAt(vGestionarProducto.tablaProductos.getSelectedRow(), 0);

            vGestionarProducto.txtIdProducto.setText(String.valueOf(producto.getIdProducto()));
            vGestionarProducto.txtDescripcionProducto.setText(producto.getDescripcion());
            vGestionarProducto.txtStock.setText(String.valueOf(producto.getStock()));
            vGestionarProducto.modeloComboProveedores.setSelectedItem(producto.getNomProveedor());
            vGestionarProducto.txtIdProveedor.setText(String.valueOf(producto.getIdProveedor()));

        }
    }

    public void getSeleccionEnTablaConstruirPedido(MouseEvent e) {

        if ((vConstruirPedido.tablaConstruirPedido.getSelectedRow() >= 0)) {

            Productos producto = (Productos) vConstruirPedido.modeloTablaProductos.getValueAt(vConstruirPedido.tablaConstruirPedido.getSelectedRow(), 0);

            vConstruirPedido.txtIdProducto.setText(String.valueOf(producto.getIdProducto()));
            vConstruirPedido.txtIdProveedor.setText(String.valueOf(producto.getIdProveedor()));

        }
    }

    public void getSeleccionEnTablaGestionarPedidos(MouseEvent e, DefaultTableModel modeloTabla) {

        if ((vGestionarPedido.tablaGestionarPedidos.getSelectedRow() >= 0)) {

            Pedido pedido = (Pedido) vGestionarPedido.modeloTablaPedidos.getValueAt(vGestionarPedido.tablaGestionarPedidos.getSelectedRow(), 0);
            crearVistaListaProductos();
            cargarListaProductosPedidos(pedido.getIdProveedor(), modeloTabla);

        }
    }

    public void getSeleccionEnTablaRecepcionPedidos(MouseEvent e, DefaultTableModel modeloTabla) {

        if ((vRecepcionPedido.tablaRecepcionPedidos.getSelectedRow() >= 0)) {

            DetallePedido detalle_pedido = (DetallePedido) vRecepcionPedido.modeloTablaPedidos.getValueAt(vRecepcionPedido.tablaRecepcionPedidos.getSelectedRow(), 1);
            crearVistaListaRecepcion();
            cargarListaRecepcionDePedidos(detalle_pedido.getIdProveedor(), modeloTabla);

        }
    }

    public void getSeleccionEnTablaListaProductos(MouseEvent e) {
        if ((vListaProducto.tablaListaProducto.getSelectedRow() >= 0)) {

            Pedido pedido = (Pedido) vListaProducto.modeloTablaListaProductos.getValueAt(vListaProducto.tablaListaProducto.getSelectedRow(), 2);
            vListaProducto.txtIdProducto.setText(String.valueOf(pedido.getIdPedido()));
            vListaProducto.txtIdProveedor.setText(String.valueOf(pedido.getIdProveedor()));

        }
    }

    public void cargarListaProductosPedidos(int id, DefaultTableModel modeloTabla) {

        limpiarTabla(modeloTabla);
        ArrayList<Pedido> listaPedido = base.getPedidosInnerJoin2(id);
        int numeroPedido = listaPedido.size();
        modeloTabla.setNumRows(numeroPedido);

        for (int i = 0; i < numeroPedido; i++) {

            Pedido pedido = listaPedido.get(i);
            String desc = pedido.getDescProducto();
            int cant = pedido.getCant();

            modeloTabla.setValueAt(cant, i, 0);
            modeloTabla.setValueAt(desc, i, 1);
            modeloTabla.setValueAt(pedido, i, 2);

        }

    }

    public void cargarListaRecepcionDePedidos(int id, DefaultTableModel modeloTabla) {

        limpiarTabla(modeloTabla);
        ArrayList<DetallePedido> listaPedido = base.getDetallesPedidos(id);
        int numeroPedido = listaPedido.size();
        modeloTabla.setNumRows(numeroPedido);

        for (int i = 0; i < numeroPedido; i++) {

            DetallePedido pedido = listaPedido.get(i);
            String desc = pedido.getDescripcion();
            int cant = pedido.getCantSolicitada();

            modeloTabla.setValueAt(cant, i, 0);
            modeloTabla.setValueAt(desc, i, 1);
            modeloTabla.setValueAt(pedido, i, 2);

        }

    }

    private void cerrarListaProducto(ActionEvent e, JInternalFrame frame) {
        frame.setVisible(false);
    }

    private void anadirProductoTablaAbajo(ActionEvent e) {
        String cantidad = vistaVentas.txtCantidad.getText();
        int numFilas = vistaVentas.tablaArriba.getSelectedRow();
        if (cantidad.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe ingresar la cantidad", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (numFilas == -1) {
            JOptionPane.showMessageDialog(vListaProducto, "Debe seleccionar el producto que desea agregar", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            int cant = Integer.parseInt(cantidad);
            int id = (int) vistaVentas.tablaArriba.getValueAt(vistaVentas.tablaArriba.getSelectedRow(), 0);
            String descripcion = String.valueOf(vistaVentas.tablaArriba.getValueAt(vistaVentas.tablaArriba.getSelectedRow(), 1));
            int stock = (int) vistaVentas.tablaArriba.getValueAt(vistaVentas.tablaArriba.getSelectedRow(), 2);
            String nomProveedor = String.valueOf(vistaVentas.tablaArriba.getValueAt(vistaVentas.tablaArriba.getSelectedRow(), 3));

            vistaVentas.modeloTablaProductosAbajo = (DefaultTableModel) vistaVentas.tablaAbajo.getModel();

            Object ListaProductos[] = {id, cant, descripcion, stock, nomProveedor};

            vistaVentas.modeloTablaProductosAbajo.addRow(ListaProductos);

            vistaVentas.txtDesc.setText("");
            vistaVentas.txtCantidad.setText("");
        }
    }

    private void restarStock() {
        int rows = vistaVentas.modeloTablaProductosAbajo.getRowCount();

        if (rows == 0) {
            JOptionPane.showMessageDialog(vistaVentas, "Debe agregar productos primero", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            for (int i = 0; i < rows; i++) {

                int idProd = (int) vistaVentas.modeloTablaProductosAbajo.getValueAt(i, 0);
                int cant = (int) vistaVentas.modeloTablaProductosAbajo.getValueAt(i, 1);
                int stock = (int) vistaVentas.modeloTablaProductosAbajo.getValueAt(i, 3);

                int stockActualizado = stock - cant;
                Productos p = new Productos();
                p.setIdProducto(idProd);
                p.setStock(stockActualizado);
                base.actualizarStock(p);

            }
            cargarModeloTablaArriba();
            cargarModeloTablaProductos(modeloTablaProductos);
            cargarModeloTablaConstruirPedido(modeloTablaConstruirPedido);
            limpiarTablaAbajo();
            JOptionPane.showMessageDialog(null, "Se ha actualizado el stock correctamente", "Stock", 1);
        }

    }

    public void pedidoRealizado(DefaultTableModel modeloTabla) {

        int quitar = JOptionPane.showConfirmDialog(vListaProducto, "El pedido se almacenará para su posterior comparación");

        if (quitar == 0) {
            java.util.Date date = new java.util.Date();
            java.sql.Date date2 = new java.sql.Date(date.getTime());
            Pedido pedido = (Pedido) modeloTabla.getValueAt(0, 2);
            int numFilas = modeloTabla.getRowCount();
            int seleccion = JOptionPane.showConfirmDialog(null,"¿Desea imprimir el pedido?");
            if(seleccion == 0){
                imprimirReporte();
            }

            for (int i = 0; i < numFilas; i++) {
                int cant = (int) modeloTabla.getValueAt(i, 0);
                Pedido idProducto = (Pedido) modeloTabla.getValueAt(i, 2);

                DetallePedido detalle = new DetallePedido(cant, date2, idProducto.getIdProducto(), pedido.getIdProveedor());
                base.insertarDetallePedido(detalle);

            }
            for (int j = 0; j < numFilas; j++) {

                pedido = (Pedido) modeloTabla.getValueAt(j, 2);
                base.eliminarPedido(pedido);
            }
            cargarListaProductosPedidos(pedido.getIdProveedor(), modeloTabla);
            JOptionPane.showMessageDialog(vListaProducto, "Se ha almacenado el pedido", "Ok", JOptionPane.INFORMATION_MESSAGE);

            limpiarTabla(modeloTabla);
            cargarComboRecepcionDePedido();
            cargarModeloTablaRecepcionPedido(modeloTablaRecepcionPedido);
            cargarModeloTablaPedidos(modeloTablaPedido);
        }

    }

    private void cerrarPedido(DefaultTableModel modeloTablaListaRecepcionPedido) {

        int opcion = JOptionPane.showConfirmDialog(vListaRecepcionPedido, "¿Desea cerrar el pedido?, se actualizará el stock y el ranking");
        if (opcion == 0) {
            Productos p;
            DetallePedido detalle = null;
            int numFilas = modeloTablaListaRecepcionPedido.getRowCount();

            for (int i = 0; i < numFilas; i++) {
                detalle = (DetallePedido) modeloTablaListaRecepcionPedido.getValueAt(i, 2);

                p = new Productos(detalle.getIdProducto(), detalle.getCantSolicitada());
                base.cerrarPedido(p);

            }
            for (int j = 0; j < numFilas; j++) {

                detalle = (DetallePedido) modeloTablaListaRecepcionPedido.getValueAt(j, 2);
                base.eliminarDetallePedido(detalle);
            }
            cargarListaRecepcionDePedidos(detalle.getIdProveedor(), modeloTablaListaRecepcionPedido);
            cargarModeloTablaRecepcionPedido(modeloTablaRecepcionPedido);
            limpiarTabla(modeloTablaListaRecepcionPedido);
            cargarComboRecepcionDePedido();
            cargarModeloTablaProductos(modeloTablaProductos);
            cargarModeloTablaConstruirPedido(modeloTablaConstruirPedido);
            cargarModeloTablaArriba();
            JOptionPane.showMessageDialog(vListaProducto, "Operación exitosa", "Ok", JOptionPane.INFORMATION_MESSAGE);

        }

    }

    public void imprimirReporte() {

        try {
            Pedido pedido = (Pedido) modeloTablaListaProductos.getValueAt(0, 2);
            Map parametros = new HashMap();
            parametros.put("idProveedor", pedido.getIdProveedor());
            String path = "C:\\Users\\maxid\\Documents\\NetBeansProjects\\SistemaGestionDePedidosProveedores\\src\\Vista\\pedidos.jasper";           
            JasperReport reporte = (JasperReport) JRLoader.loadObjectFromFile(path);     

            JasperPrint jprint = JasperFillManager.fillReport(reporte, parametros, conn);
            JasperViewer view = new JasperViewer(jprint, false);
            view.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            view.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void limpiarCamposProveedor() {
        vGestionarProveedor.txtNombreProveedor.setText("");
        vGestionarProveedor.txtDirreccionProveedor.setText("");
        vGestionarProveedor.txtTelefonoProveedor.setText("");
        vGestionarProveedor.txtEmailProveedor.setText("");
    }

    public void limpiarCamposProductos() {
        vGestionarProducto.txtDescripcionProducto.setText("");
        vGestionarProducto.txtStock.setText("");
    }

    private void limpiarTabla(DefaultTableModel modeloTabla) {
        int numFilas = modeloTabla.getRowCount();
        if (numFilas > 0) {
            for (int i = numFilas - 1; i < 0; i--) {
                modeloTabla.removeRow(i);
            }
        }
    }

    private void limpiarTablaAbajo() {
        int cantidadFilas = vistaVentas.modeloTablaProductosAbajo.getRowCount();
        if (cantidadFilas > 0) {
            int quitar = JOptionPane.showConfirmDialog(vistaVentas, "¿ Desea finalizar la venta ?");
            if (quitar == 0) {
                for (int i = cantidadFilas - 1; i >= 0; i--) {
                    vistaVentas.modeloTablaProductosAbajo.removeRow(i);

                }
            }

        }
    }

    private void quitarProductoTablaAbajo() {
        int numFilas = vistaVentas.tablaAbajo.getSelectedRow();
        int filaSelec = vistaVentas.modeloTablaProductosAbajo.getRowCount();
        if (filaSelec > 0) {
            int quitar = JOptionPane.showConfirmDialog(vistaVentas, "¿ Desea eliminar el articulo seleccionado ?");
            if (quitar == 0) {
                if (numFilas == -1) {
                    JOptionPane.showMessageDialog(vistaVentas, "Debe seleccionar el articulo que desea quitar", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    vistaVentas.modeloTablaProductosAbajo.removeRow(numFilas);
                }
            }
        }

    }

    // METODOS INSERT,UPDATE,DELETE,SELECT BASE DATOS   
    public void consultarProveedorConCriterio(KeyEvent e) {

        String cadena = vGestionarProveedor.txtBuscarProveedor.getText();

        ArrayList<Proveedor> listaProveedor = base.obtenerProveedorPorCadenaTexto(cadena);

        int numeroProveedor = listaProveedor.size();
        vGestionarProveedor.modeloTablaProveedores.setNumRows(numeroProveedor);
        for (int i = 0; i < numeroProveedor; i++) {

            Proveedor proveedor = listaProveedor.get(i);
            String direccion = proveedor.getDirProveedor();
            String telefono = proveedor.getTelProveedor();
            String email = proveedor.getEmailProveedor();

            vGestionarProveedor.modeloTablaProveedores.setValueAt(proveedor, i, 0);
            vGestionarProveedor.modeloTablaProveedores.setValueAt(direccion, i, 1);
            vGestionarProveedor.modeloTablaProveedores.setValueAt(telefono, i, 2);
            vGestionarProveedor.modeloTablaProveedores.setValueAt(email, i, 3);
        }
    }

    public void consultarProductosConCriterio(KeyEvent e) {

        String cadena = vGestionarProducto.txtBuscarProducto.getText();

        ArrayList<Productos> listaProductos = base.obtenerProductosrPorCadenaTexto(cadena);

        int numeroProducto = listaProductos.size();
        vGestionarProducto.modeloTablaProductos.setNumRows(numeroProducto);
        for (int i = 0; i < numeroProducto; i++) {

            Productos productos = listaProductos.get(i);
            int Stock = productos.getStock();
            String nomProveedor = productos.getNomProveedor();

            vGestionarProducto.modeloTablaProductos.setValueAt(productos, i, 0);
            vGestionarProducto.modeloTablaProductos.setValueAt(Stock, i, 1);
            vGestionarProducto.modeloTablaProductos.setValueAt(nomProveedor, i, 2);

        }

    }

    public void consultarProductosConCriterio2(KeyEvent e) {

        String cadena = vConstruirPedido.txtBuscarProducto.getText();

        ArrayList<Productos> listaProductos = base.obtenerProductosrPorCadenaTexto(cadena);

        int numeroProducto = listaProductos.size();
        vConstruirPedido.modeloTablaProductos.setNumRows(numeroProducto);
        for (int i = 0; i < numeroProducto; i++) {

            Productos productos = listaProductos.get(i);
            int Stock = productos.getStock();
            String nomProveedor = productos.getNomProveedor();

            vConstruirPedido.modeloTablaProductos.setValueAt(productos, i, 0);
            vConstruirPedido.modeloTablaProductos.setValueAt(Stock, i, 1);
            vConstruirPedido.modeloTablaProductos.setValueAt(nomProveedor, i, 2);

        }

    }

    public void consultarProductosConCriterio3(KeyEvent e) {

        String cadena = vistaVentas.txtDesc.getText();

        ArrayList<Productos> listaProductos = base.obtenerProductosrPorCadenaTexto(cadena);

        int numeroProducto = listaProductos.size();
        vistaVentas.modeloTablaProductosArriba.setNumRows(numeroProducto);
        for (int i = 0; i < numeroProducto; i++) {

            Productos productos = listaProductos.get(i);
            int Stock = productos.getStock();
            int id = productos.getIdProducto();
            String nomProveedor = productos.getNomProveedor();

            vistaVentas.modeloTablaProductosArriba.setValueAt(id, i, 0);
            vistaVentas.modeloTablaProductosArriba.setValueAt(productos, i, 1);
            vistaVentas.modeloTablaProductosArriba.setValueAt(Stock, i, 2);
            vistaVentas.modeloTablaProductosArriba.setValueAt(nomProveedor, i, 3);

        }

    }

    public void consultarPedido(ActionEvent e) {
        Pedido p = (Pedido) vGestionarPedido.comboBoxProveedorConPedido.getSelectedItem();

        ArrayList<Pedido> listaPedido = base.getProveedorConPedidoInneJoin(p.getIdProveedor());

        int numeroPedido = listaPedido.size();
        vGestionarPedido.modeloTablaPedidos.setNumRows(numeroPedido);
        for (int i = 0; i < numeroPedido; i++) {

            Pedido pedido = listaPedido.get(i);

            vGestionarPedido.modeloTablaPedidos.setValueAt(pedido, i, 0);
        }

    }

    public void consultarRecepcionPedido(ActionEvent e) {
        DetallePedido p = (DetallePedido) vRecepcionPedido.comboBoxProveedoresConPedido.getSelectedItem();

        ArrayList<DetallePedido> listaPedido = base.getDetallePedidoPorId(p.getIdProveedor());

        int numeroPedido = listaPedido.size();
        vRecepcionPedido.modeloTablaPedidos.setNumRows(numeroPedido);
        for (int i = 0; i < numeroPedido; i++) {

            DetallePedido pedido = listaPedido.get(i);

            vRecepcionPedido.modeloTablaPedidos.setValueAt(pedido, i, 1);
        }

    }

    private void insertarNuevoProveedor(ActionEvent e, DefaultTableModel modeloTabla) {

        String nombre = vGestionarProveedor.txtNombreProveedor.getText();
        String direccion = vGestionarProveedor.txtDirreccionProveedor.getText();
        String telefono = vGestionarProveedor.txtTelefonoProveedor.getText();
        String email = vGestionarProveedor.txtEmailProveedor.getText();

        if (nombre.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe ingresar un nombre", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            Proveedor p = new Proveedor(nombre, direccion, telefono, email);
            base.insertarProveedor(p);
            cargarProveedoresEnComboBox();
            cargarModeloTablaProveedores(modeloTabla);            
            limpiarCamposProveedor();
        }

    }

    private void insertarNuevoProducto(ActionEvent e, DefaultTableModel modeloTabla) {

        String nombre = vGestionarProducto.txtDescripcionProducto.getText();
        int stock = Integer.parseInt(vGestionarProducto.txtStock.getText());
        Proveedor proveedor = (Proveedor) vGestionarProducto.comboBoxProveedores.getSelectedItem();
        int idProveedor = proveedor.getIdProveedor();

        if (nombre.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe ingresar una descripción", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            Productos p = new Productos(nombre, stock, idProveedor);
            base.insertarProducto(p);
            cargarModeloTablaProductos(modeloTabla);
            cargarModeloTablaConstruirPedido(modeloTablaConstruirPedido);
            cargarModeloTablaArriba();
            JOptionPane.showMessageDialog(null, "Se ha registrado a un nuevo producto", "Nuevo producto", 1);
            limpiarCamposProductos();

        }
    }

    private void insertarNuevoProductoEnPedido(ActionEvent e, DefaultTableModel modeloTabla) {
        int numFilas = vConstruirPedido.tablaConstruirPedido.getSelectedRow();

        if (numFilas == -1) {
            JOptionPane.showMessageDialog(vConstruirPedido, "Debe seleccionar el producto", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            int cantSolicitada = (Integer) vConstruirPedido.jSpinner.getValue();
            int idProducto = Integer.parseInt(vConstruirPedido.txtIdProducto.getText());
            int idProveedor = Integer.parseInt(vConstruirPedido.txtIdProveedor.getText());
            if (cantSolicitada <= 0) {
                JOptionPane.showMessageDialog(null, "Cantidad inválida", "Pedido", JOptionPane.ERROR_MESSAGE);
            } else {
                int confirmar = JOptionPane.showConfirmDialog(null,"¿Desea añadir el producto?");
                if(confirmar == 0){
                Pedido p = new Pedido(cantSolicitada, idProducto, idProveedor);
                base.insertarPedido(p);
                cargarModeloTablaConstruirPedido(modeloTabla);
                JOptionPane.showMessageDialog(null, "Se ha agregado el producto correctamente", "Nuevo producto", 1);
                }
            }
        }
    }

    private void actualizarProveedor(ActionEvent e, DefaultTableModel modeloTabla) {

        int idProveedor = Integer.parseInt(vGestionarProveedor.txtIdProveedor.getText());
        String nombre = vGestionarProveedor.txtNombreProveedor.getText();
        String direccion = vGestionarProveedor.txtDirreccionProveedor.getText();
        String telefono = vGestionarProveedor.txtTelefonoProveedor.getText();
        String email = vGestionarProveedor.txtEmailProveedor.getText();

        if (nombre.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe ingresar un nombre", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            Proveedor p = new Proveedor(idProveedor, nombre, direccion, telefono, email);
            base.actualizarProveedor(p);
            cargarProveedoresEnComboBox();
            cargarModeloTablaProveedores(modeloTabla);
            JOptionPane.showMessageDialog(null, "Se ha actualizado el proveedor correctamente", "Proveedor", 1);
            limpiarCamposProveedor();
        }
    }

    private void actualizarProductos(ActionEvent e, DefaultTableModel modeloTabla) {

        int numFilas = vGestionarProducto.tablaProductos.getSelectedRow();
        String nombre = vGestionarProducto.txtDescripcionProducto.getText();

        if (numFilas == -1) {
            JOptionPane.showMessageDialog(null, "Debe seleccionar el producto que desea actualizar", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (nombre.isEmpty()) {
            JOptionPane.showMessageDialog(vGestionarProveedor, "Debe ingresar un nombre", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            int idProducto = Integer.parseInt(vGestionarProducto.txtIdProducto.getText());
            int stock = Integer.parseInt(vGestionarProducto.txtStock.getText());           
            int idProveedor = Integer.parseInt(vGestionarProducto.txtIdProveedor.getText());
            Productos p = new Productos(idProducto, nombre, stock, idProveedor);
            base.actualizarProductos(p);
            cargarModeloTablaProductos(modeloTabla);
            JOptionPane.showMessageDialog(null, "Se ha actualizado el producto correctamente", "Producto", 1);
            limpiarCamposProductos();
        }
    }

    public void actualizarPedido(ActionEvent e, DefaultTableModel modeloTabla) {

        try {

            int cantProductos = Integer.parseInt(JOptionPane.showInputDialog("Actualizar cantidad"));
            int idPedido = Integer.parseInt(vListaProducto.txtIdProducto.getText());
            int idProveedor = Integer.parseInt(vListaProducto.txtIdProveedor.getText());
            Pedido p = new Pedido(idPedido, cantProductos);
            base.actualizarPedido(p);

            cargarListaProductosPedidos(idProveedor, modeloTabla);
            JOptionPane.showMessageDialog(null, "Se ha actualizado el pedido correctamente", "Pedido", 1);
        } catch (ArrayIndexOutOfBoundsException ex) {
            JOptionPane.showMessageDialog(null, "Debe seleccionar el producto que desea actualizar");
        }

    }

    public void eliminarProveedor(ActionEvent e, DefaultTableModel modeloTabla) {
        int numFilas = vGestionarProveedor.tablaProveedor.getSelectedRow();
        int quitar = JOptionPane.showConfirmDialog(vGestionarProveedor, "¿ Desea eliminar el proveedor seleccionado ?");
        if (quitar == 0) {
            if (numFilas == -1) {
                JOptionPane.showMessageDialog(vGestionarProveedor, "Debe seleccionar el proveedor que desea eliminar", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                int id = Integer.parseInt(vGestionarProveedor.txtIdProveedor.getText());
                Proveedor p = new Proveedor();
                p.setIdProveedor(id);
                base.eliminarProveedor(p);
                cargarModeloTablaProveedores(modeloTabla);
                JOptionPane.showMessageDialog(null, "Se ha eliminado un proveedor");
                cargarProveedoresEnComboBox();
                limpiarCamposProveedor();
            }
        }
    }

    public void eliminarProducto(ActionEvent e, DefaultTableModel modeloTabla) {
        int numFilas = vGestionarProducto.tablaProductos.getSelectedRow();
        int quitar = JOptionPane.showConfirmDialog(vGestionarProducto, "¿ Desea eliminar el producto seleccionado ?");
        if (quitar == 0) {
            if (numFilas == -1) {
                JOptionPane.showMessageDialog(vGestionarProducto, "Debe seleccionar el producto que desea eliminar", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                int id = Integer.parseInt(vGestionarProducto.txtIdProducto.getText());
                Productos p = new Productos();
                p.setIdProducto(id);
                base.eliminarProducto(p);
                cargarModeloTablaProductos(modeloTabla);
                JOptionPane.showMessageDialog(null, "Se ha eliminado un producto");
                limpiarCamposProductos();

            }
        }
    }

    public void eliminarPedido(ActionEvent e, DefaultTableModel modeloTablaProductosPedidos, DefaultTableModel modeloTablaPedido) {
        int numFilas = vListaProducto.tablaListaProducto.getSelectedRow();
        int quitar = JOptionPane.showConfirmDialog(vListaProducto, "¿ Desea quitar de la lista al producto seleccionado ?");
        if (quitar == 0) {
            if (numFilas == -1) {
                JOptionPane.showMessageDialog(vListaProducto, "Debe seleccionar el producto que desea quitar", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                int idPedido = Integer.parseInt(vListaProducto.txtIdProducto.getText());
                int idProveedor = Integer.parseInt(vListaProducto.txtIdProveedor.getText());
                Pedido p = new Pedido();
                p.setIdPedido(idPedido);
                base.eliminarPedido(p);
                cargarListaProductosPedidos(idProveedor, modeloTablaProductosPedidos);
                JOptionPane.showMessageDialog(null, "Se ha quitado el producto de la lista");
                cargarModeloTablaPedidos(modeloTablaPedido);
            }
        }
    }

    private class ColorearFilas extends DefaultTableCellRenderer {

        private final int columna_patron;

        public ColorearFilas(int Colpatron) {
            this.columna_patron = Colpatron;
        }

        public Component getTableCellRendererComponent(JTable table, Object value, boolean Selected, boolean hasFocus, int row, int col) {

            int cant = (int) table.getValueAt(row, columna_patron);

            if (cant < 10) {
                setForeground(Color.red);
            } else {
                setForeground(Color.black);

            }
            super.getTableCellRendererComponent(table, value, Selected, hasFocus, row, col);
            return this;
        }
    }

}
