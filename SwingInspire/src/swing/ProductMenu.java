/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swing;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;

/**
 *
 * @author waruwat
 */
public class ProductMenu extends javax.swing.JPanel {

    /**
     * Creates new form ProductMenu
     */
    public ProductMenu() {
        initComponents();
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "ID", "Photo", "Name", "Qualtity"
                }
        ) {
            @Override
            public Class<?> getColumnClass(int column) {

                if (column == 1) {
                    return ImageIcon.class;
                } else {
                    return Object.class;
                }
            }
        }
        );
        jTable1.getColumn("Qualtity").setCellRenderer(new ProgressCellRenderer());

        //init
        Show_Users_In_JTable();
    }

    public void SetQueryTable(String query) {
        string_query_fix = "SELECT * FROM  `product` " + query;
        string_query = string_query_fix;
        Show_Users_In_JTable();
    }

    public void SetQueryTableSearch(String query) {
        if (string_query_fix.equals("SELECT * FROM  `product` ")) {
            string_query = string_query_fix + "where " + query;
        } else {
            string_query = string_query_fix + " AND " + query;
        }
        System.out.println(string_query);
        Show_Users_In_JTable();
    }

    public ArrayList<Product> getProductList() {
        ArrayList<Product> p_list = new ArrayList<Product>();
        Connection connection = Db_connect.getConnection();

        Statement st;
        ResultSet rs;
        int count = 0;
        try {
            st = connection.createStatement();
            rs = st.executeQuery(string_query);
            Product p;
            while (rs.next()) {
                p = new Product();
                p.setPid(rs.getInt("pid"));
                p.setName(rs.getString("name"));
                p.setImg(rs.getString("img"));
                p.setDes(rs.getString("des"));
                p.setCount(rs.getInt("count"));
                p.setPrice(rs.getInt("price"));
                p.setCid(rs.getInt("cid"));
                p.setSid(rs.getInt("sid"));
                p_list.add(p);
                count++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        jLabel3.setText(count + " Results");
        return p_list;
    }

    public void Show_Users_In_JTable() {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);
        ArrayList<Product> list = getProductList();
        Object[] row = new Object[4];
        for (int i = 0; i < list.size(); i++) {
            row[0] = list.get(i).getPid();
            row[1] = Db_connect.getIcon(list.get(i).getImg(), "product");
            row[2] = list.get(i).getName();
            row[3] = list.get(i).getCount() * 100 / getMax(list.get(i).getCid()); //list.get(i).getIcon();
            model.addRow(row);
        }

    }

    public int getMax(int cid) {
        String query = "SELECT `max` FROM `category` WHERE `cid`=" + cid;
        Connection connection = Db_connect.getConnection();
        Statement st;
        ResultSet rs;
        try {
            st = connection.createStatement();
            rs = st.executeQuery(query);
            rs.next();
            return rs.getInt("max");
        } catch (Exception e) {
            e.printStackTrace();
            return 100;
        }
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
        jTable1 = new javax.swing.JTable();
        jTextField1 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(71, 120, 197));

        jScrollPane1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jTable1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Photo", "Name", "Qualtity"
            }
        ));
        jTable1.setRowHeight(60);
        jTable1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTable1FocusGained(evt);
            }
        });
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTable1MousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jTextField1.setBackground(new java.awt.Color(123, 156, 225));
        jTextField1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Products");

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/swing/images/icons8_Search_18px.png"))); // NOI18N
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel2MousePressed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("83 ผลลัพธ์");

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/swing/images/icons8_Contacts_25px.png"))); // NOI18N
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel4MousePressed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addGap(26, 26, 26))
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 514, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel1)
                                .addComponent(jLabel3)
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(9, 9, 9)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jTable1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTable1FocusGained
        // TODO add your handling code here:
        Show_Users_In_JTable();
    }//GEN-LAST:event_jTable1FocusGained

    private void jTable1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MousePressed
        // TODO add your handling code here:
        int i = jTable1.getSelectedRow();
        Product p = getProductList().get(i);
        new ProductDetail(p);
    }//GEN-LAST:event_jTable1MousePressed

    private void jLabel2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MousePressed
        // TODO add your handling code here:
        SetQueryTableSearch(" `name` LIKE '%" + jTextField1.getText() + "%'");
        // SetQueryTableSearch(" `pid` = "+ jTextField1.getText() + "");
        //SetQueryTableSearch("`name` LIKE '%"+ jTextField1.getText() +"%' OR `pid` =" + jTextField1.getText());
    }//GEN-LAST:event_jLabel2MousePressed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jLabel4MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MousePressed
        // TODO add your handling code here:
        new ProductDetail();
    }//GEN-LAST:event_jLabel4MousePressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables

    private String string_query = "SELECT * FROM  `product` ";
    private String string_query_fix = "SELECT * FROM  `product` ";
}

class ProgressCellRenderer extends javax.swing.JProgressBar implements TableCellRenderer {

    ProgressCellRenderer() {
        // Initialize the progress bar renderer to use a horizontal
        // progress bar.

        //super(JProgressBar.HORIZONTAL);
        // Ensure that the progress bar border is not painted. (The
        // result is ugly when it appears in a table cell.)
        setBorderPainted(false);

        // Ensure that percentage text is painted on the progress bar.
        setStringPainted(true);
    }

    public Component getTableCellRendererComponent(JTable table,
            Object value,
            boolean isSelected,
            boolean hasFocus,
            int row,
            int col) {
        if (value instanceof Integer) {
            // Ensure that the nonselected background portion of a
            // progress bar is assigned the same color as the table's 
            // background color. The resulting progress bar fits more
            // naturally (from a visual perspective) into the overall 
            // table's appearance.

            //setBackground(table.getBackground());
            // Save the current progress bar value for subsequent
            // rendering. That value is converted from [0, 40] to 
            // [0, 100].
            int i = ((Integer) value).intValue();
            setValue(i);
        }

        return this;
    }
}
