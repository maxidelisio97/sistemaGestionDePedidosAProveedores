/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
 *
 * @author maxid
 */
public class VistaListaProductos extends javax.swing.JInternalFrame {

    /**
     * Creates new form VistaListaProductos
     */
    public VistaListaProductos() {
       
        initComponents();
         ((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).setNorthPane(null);
        cargarColumnas();
    }
    public void cargarColumnas(){
        
         modeloTablaListaProductos.addColumn("Cantidad");      
        modeloTablaListaProductos.addColumn("Descripción");
        modeloTablaListaProductos.addColumn("Proveedor");
         
       
        TableColumn ColCantidad = tablaListaProducto.getColumn("Cantidad");       
        TableColumn colDescripcion = tablaListaProducto.getColumn("Descripción");
        TableColumn colId2 = tablaListaProducto.getColumn("Proveedor");
       
        
        ColCantidad.setMaxWidth(60);
        ColCantidad.setMinWidth(10);

        colDescripcion.setMaxWidth(620);
        colDescripcion.setMinWidth(50);
        
        colId2.setMaxWidth(140);
        colId2.setMinWidth(10);      
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tablaListaProducto = new javax.swing.JTable();
        btnModificarListaProducto = new javax.swing.JButton();
        btnImprimirPedido = new javax.swing.JButton();
        btnQuitarListaProducto = new javax.swing.JButton();
        btnPedidoRealizado = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        btnCerrarLista = new javax.swing.JButton();
        txtIdProducto = new javax.swing.JTextField();
        txtIdProveedor = new javax.swing.JTextField();

        setBorder(null);

        tablaListaProducto.setModel(modeloTablaListaProductos);
        jScrollPane1.setViewportView(tablaListaProducto);

        btnModificarListaProducto.setText("Modificar cantidad");

        btnImprimirPedido.setText("Imprimir pedido");

        btnQuitarListaProducto.setText("Quitar producto");

        btnPedidoRealizado.setText("Pedido realizado");

        jLabel1.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        jLabel1.setText("Lista de productos");

        btnCerrarLista.setText("Cerrar lista");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(189, 189, 189)
                        .addComponent(btnCerrarLista))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 717, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(1, 1, 1)
                                    .addComponent(btnModificarListaProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(btnQuitarListaProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addComponent(txtIdProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(15, 15, 15)))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(btnPedidoRealizado, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(btnImprimirPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(6, 6, 6)
                                    .addComponent(txtIdProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap(10, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnCerrarLista)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 412, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnModificarListaProducto)
                    .addComponent(btnQuitarListaProducto)
                    .addComponent(btnImprimirPedido)
                    .addComponent(btnPedidoRealizado))
                .addGap(12, 12, 12)
                .addComponent(txtIdProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtIdProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(46, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnCerrarLista;
    public javax.swing.JButton btnImprimirPedido;
    public javax.swing.JButton btnModificarListaProducto;
    public javax.swing.JButton btnPedidoRealizado;
    public javax.swing.JButton btnQuitarListaProducto;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JTable tablaListaProducto;
    public javax.swing.JTextField txtIdProducto;
    public javax.swing.JTextField txtIdProveedor;
    // End of variables declaration//GEN-END:variables
    public DefaultTableModel modeloTablaListaProductos = new DefaultTableModel();
}
