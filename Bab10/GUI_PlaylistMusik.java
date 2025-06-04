/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package PlaylistMusik.Bab10;
import PlaylistMusik.Bab9.*;
import PlaylistMusik.Bab8.*;
import PlaylistMusik.Bab7.*;
import PlaylistMusik.Bab6.*;
import PlaylistMusik.Bab5.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author Berlian
 */
public class GUI_PlaylistMusik extends javax.swing.JFrame {
    public Connection conn;

    String nama1, email1, judul1, genre1, artis1, rating1;
    String emailLama;
    /**
     * Creates new form GUI_PlaylistMusik
     */
    public GUI_PlaylistMusik() {
        initComponents();
        tampil();
    }
       
    public void batal(){
        txtNama.setText("");
        txtEmail.setText("");
        txtjudulLagu.setText("");
        txtArtis.setText("");
        txtGenre.setText("");
        txtRating.setText("");
    }
    
    
 // Method koneksi ke database
    public void koneksi() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/playlistmusik_2318066?serverTimezone=UTC",
                "root",
                ""
            );
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Koneksi Gagal: " + e.getMessage());
        }
    }
    
    public void tampil() {
    DefaultTableModel tabel = new DefaultTableModel();
    tabel.addColumn("Nama");
    tabel.addColumn("Email");
    tabel.addColumn("Judul Lagu");
    tabel.addColumn("Artis");
    tabel.addColumn("Genre");
    tabel.addColumn("Rating");
    
    try {
        koneksi();
        String sql = "SELECT * FROM tb_playlist";
        Statement stat = conn.createStatement();
        ResultSet res = stat.executeQuery(sql);
        while (res.next()) {
            tabel.addRow(new Object[]{
                res.getString("nama"), res.getString("email"),
                res.getString("judul_lagu"), res.getString("artis"),
                res.getString("genre"), res.getString("rating")
            });
        }
        tabel_data_musik.setModel(tabel);
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Gagal menampilkan data");
    }
}

   public void refresh() {
    new GUI_PlaylistMusik().setVisible(true);
    this.setVisible(false);
}

   public void insert() {
       try {
        Integer.parseInt(txtRating.getText());
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(null, "Rating harus berupa angka!");
        return;
    }
    String nama = txtNama.getText();
    String email = txtEmail.getText();
    String judul = txtjudulLagu.getText();
    String artis = txtArtis.getText();
    String genre = txtGenre.getText();
    String rating = txtRating.getText();

    try {
        koneksi();
        Statement statement = conn.createStatement();
        statement.executeUpdate("INSERT INTO tb_playlist (nama, email, judul_lagu, artis, genre, rating) "
            + "VALUES ('" + nama + "', '" + email + "', '" + judul + "', '" + artis + "', '" + genre + "', '" + rating + "')");
        statement.close();
        JOptionPane.showMessageDialog(null, "Berhasil menambahkan lagu!");
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Terjadi kesalahan input data");
    }
    refresh();
}


public void update() {
    String nama = txtNama.getText();
    String email = txtEmail.getText();
    String judul = txtjudulLagu.getText();
    String artis = txtArtis.getText();
    String genre = txtGenre.getText();
    String rating = txtRating.getText();

    try {
        koneksi();
        Statement stat = conn.createStatement();
        String sql = "UPDATE tb_playlist SET "
                + "nama='" + nama + "',"
                + "judul_lagu='" + judul + "',"
                + "artis='" + artis + "',"
                + "genre='" + genre + "',"
                + "rating='" + rating + "' "
                + "WHERE email='" + emailLama + "'";
        stat.executeUpdate(sql);
        stat.close();
        conn.close();
        JOptionPane.showMessageDialog(null, "Data berhasil di-update!");
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Gagal update data");
    }
    refresh();
}

public void delete() {
    int ok = JOptionPane.showConfirmDialog(null, "Yakin ingin menghapus data?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
    if (ok == 0) {
        try {
            koneksi();
            String sql = "DELETE FROM tb_playlist WHERE email='" + txtEmail.getText() + "'";
            Statement stat = conn.createStatement();
            stat.executeUpdate(sql);
            JOptionPane.showMessageDialog(null, "Data berhasil dihapus!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Gagal hapus data");
        }
    }
    refresh();
}

public void itempilih() {
    txtNama.setText(nama1);
    txtEmail.setText(email1);
    txtjudulLagu.setText(judul1);
    txtArtis.setText(artis1);
    txtGenre.setText(genre1);
    txtRating.setText(rating1);
}


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtNama = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        txtjudulLagu = new javax.swing.JTextField();
        txtArtis = new javax.swing.JTextField();
        txtGenre = new javax.swing.JTextField();
        txtRating = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabel_data_musik = new javax.swing.JTable();
        btnSimpan = new javax.swing.JButton();
        btnHapus = new javax.swing.JButton();
        btnBatal = new javax.swing.JButton();
        btnClose = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("PLAYLIST MUSIK");

        jLabel2.setText("Nama pengguna");

        jLabel3.setText("Email Pengguna");

        jLabel4.setText("Judul Lagu");

        jLabel5.setText("Artis");

        jLabel6.setText("Genre");

        jLabel7.setText("Rating");

        tabel_data_musik.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Nama", "Email", "Judul Lagu", "Artis", "Genre", "Rating"
            }
        ));
        tabel_data_musik.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabel_data_musikMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabel_data_musik);

        btnSimpan.setText("Simpan");
        btnSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanActionPerformed(evt);
            }
        });

        btnHapus.setText("Hapus");
        btnHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapusActionPerformed(evt);
            }
        });

        btnBatal.setText("Batal");
        btnBatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBatalActionPerformed(evt);
            }
        });

        btnClose.setText("Close");
        btnClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCloseActionPerformed(evt);
            }
        });

        btnUpdate.setText("Update");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(48, 48, 48)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtNama, javax.swing.GroupLayout.DEFAULT_SIZE, 181, Short.MAX_VALUE)
                    .addComponent(txtEmail)
                    .addComponent(txtjudulLagu)
                    .addComponent(txtArtis)
                    .addComponent(txtGenre)
                    .addComponent(txtRating))
                .addGap(48, 48, 48)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(btnSimpan)
                            .addGap(35, 35, 35)
                            .addComponent(btnHapus)
                            .addGap(49, 49, 49)
                            .addComponent(btnBatal)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnUpdate)
                            .addGap(18, 18, 18)
                            .addComponent(btnClose))
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 490, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(15, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtNama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(25, 25, 25)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(23, 23, 23)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtjudulLagu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(24, 24, 24)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txtArtis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txtGenre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtRating, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSimpan)
                    .addComponent(btnHapus)
                    .addComponent(btnBatal)
                    .addComponent(btnClose)
                    .addComponent(btnUpdate))
                .addContainerGap(53, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBatalActionPerformed
        // TODO add your handling code here:
        batal();
    }//GEN-LAST:event_btnBatalActionPerformed

    private void btnCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCloseActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_btnCloseActionPerformed

    private void btnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusActionPerformed
        // TODO add your handling code here:
        delete();
    }//GEN-LAST:event_btnHapusActionPerformed

    private void btnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanActionPerformed
        // TODO add your handling code here:
        insert();
    }//GEN-LAST:event_btnSimpanActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        // TODO add your handling code here:
        update();
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void tabel_data_musikMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabel_data_musikMouseClicked
        // TODO add your handling code here:
        int tabel = tabel_data_musik.getSelectedRow();
        nama1 = tabel_data_musik.getValueAt(tabel, 0).toString();
        email1 = tabel_data_musik.getValueAt(tabel, 1).toString();
        judul1 = tabel_data_musik.getValueAt(tabel, 2).toString();
        artis1 = tabel_data_musik.getValueAt(tabel, 3).toString();
        genre1 = tabel_data_musik.getValueAt(tabel, 4).toString();
        rating1 = tabel_data_musik.getValueAt(tabel, 5).toString();
        emailLama = email1; // untuk update
        itempilih();
    }//GEN-LAST:event_tabel_data_musikMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GUI_PlaylistMusik.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUI_PlaylistMusik.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUI_PlaylistMusik.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUI_PlaylistMusik.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUI_PlaylistMusik().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBatal;
    private javax.swing.JButton btnClose;
    private javax.swing.JButton btnHapus;
    private javax.swing.JButton btnSimpan;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabel_data_musik;
    private javax.swing.JTextField txtArtis;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtGenre;
    private javax.swing.JTextField txtNama;
    private javax.swing.JTextField txtRating;
    private javax.swing.JTextField txtjudulLagu;
    // End of variables declaration//GEN-END:variables
}
