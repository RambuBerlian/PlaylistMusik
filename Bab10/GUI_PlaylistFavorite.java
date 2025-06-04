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
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Berlian
 */
public class GUI_PlaylistFavorite extends javax.swing.JFrame {
    public Connection conn;
    String judul, artis, genre;
    String judulLama;
    /**
     * Creates new form GUI_PlaylistFavorite
     */
    public GUI_PlaylistFavorite() {
        initComponents();
       tampil();
    }
    
public void batal() {
    txtNama.setText("");
    txtEmail.setText("");
    txtRating.setText("");
    cmbjudulLagu.setSelectedIndex(0);
    cmbArtis.setSelectedIndex(0);
    cmbGenre.setSelectedIndex(0);
    chkFavorite.setSelected(false);
}

public void koneksi() throws SQLException {
    try {
        conn = null;
        Class.forName("com.mysql.cj.jdbc.Driver");
        conn = DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/playlistmusik_2318066?serverTimezone=UTC",
            "root", "");
    } catch (ClassNotFoundException | SQLException e) {
        Logger.getLogger(GUI_PlaylistFavorite.class.getName()).log(Level.SEVERE, null, e);
    }
}

public void tampil() {
    DefaultTableModel model = new DefaultTableModel();
    model.addColumn("Nama");
    model.addColumn("Email");
    model.addColumn("Judul Lagu");
    model.addColumn("Artis");
    model.addColumn("Genre");
    model.addColumn("Rating");
    model.addColumn("Favorite");

    model.setRowCount(0);

    try {
        koneksi();
        String sql = "SELECT * FROM tb_playlistfavorite";
        Statement stat = conn.createStatement();
        ResultSet res = stat.executeQuery(sql);

        while (res.next()) {
            model.addRow(new Object[]{
                res.getString("nama"),
                res.getString("email"),
                res.getString("judul"),
                res.getString("artis"),
                res.getString("genre"),
                res.getString("rating"),
                res.getString("favorite")
            });
        }

        tabel_data_favorite.setModel(model);
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Gagal menampilkan data: " + e.getMessage());
    }
}

public void insert() {
    String nama = txtNama.getText();
    String email = txtEmail.getText();
    String judul = cmbjudulLagu.getSelectedItem().toString();
    String artis = cmbArtis.getSelectedItem().toString();
    String genre = cmbGenre.getSelectedItem().toString();
    String rating = txtRating.getText();
    String favorite = chkFavorite.isSelected() ? "Ya" : "Tidak";

    try {
        koneksi();
        Statement stmt = conn.createStatement();
        stmt.executeUpdate("INSERT INTO tb_playlistfavorite(nama, email, judul, artis, genre, rating, favorite) VALUES ('"
                + nama + "','" + email + "','" + judul + "','" + artis + "','" + genre + "','" + rating + "','" + favorite + "')");
        stmt.close();
        JOptionPane.showMessageDialog(null, "Data berhasil disimpan!");
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Gagal menyimpan data!");
    }
    refresh();
}

public void refresh() {
    new GUI_PlaylistFavorite().setVisible(true);
    this.setVisible(false);
}

public void update() {
    String nama = txtNama.getText();
    String email = txtEmail.getText();
    String judul = cmbjudulLagu.getSelectedItem().toString();
    String artis = cmbArtis.getSelectedItem().toString();
    String genre = cmbGenre.getSelectedItem().toString();
    String rating = txtRating.getText();
    String favorite = chkFavorite.isSelected() ? "Ya" : "Tidak";

    try {
        koneksi();
        String sql = "UPDATE tb_playlistfavorite SET artis=?, genre=?, rating=?, favorite=? " +
                     "WHERE nama=? AND email=? AND judul=?";
        PreparedStatement stmt = conn.prepareStatement(sql);

        // Set parameters sesuai urutan ? di query
        stmt.setString(1, artis);       // artis = ?
        stmt.setString(2, genre);       // genre = ?
        stmt.setString(3, rating);      // rating = ?
        stmt.setString(4, favorite);    // favorite = ?
        stmt.setString(5, nama);        // nama (where)
        stmt.setString(6, email);       // email (where)
        stmt.setString(7, judul);       // judul (where)

        int rowsAffected = stmt.executeUpdate();
        if (rowsAffected > 0) {
            JOptionPane.showMessageDialog(null, "Data berhasil diupdate!");
        } else {
            JOptionPane.showMessageDialog(null, "Data tidak ditemukan untuk diperbarui.");
        }
        refresh();
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Gagal mengupdate data!\n" + e.getMessage());
        e.printStackTrace();
    }
}


public void delete() {
    int ok = JOptionPane.showConfirmDialog(null, "Hapus data ini?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
    if (ok == JOptionPane.YES_OPTION) {
        try {
            koneksi();
            String sql = "DELETE FROM tb_playlistfavorite WHERE judul=? AND genre=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, cmbjudulLagu.getSelectedItem().toString());
            stmt.setString(2, cmbGenre.getSelectedItem().toString());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data berhasil dihapus!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Gagal menghapus data!\n" + e.getMessage());
        }
    }
    refresh();
}

public void itempilih() {
    int row = tabel_data_favorite.getSelectedRow();

    txtNama.setText(tabel_data_favorite.getValueAt(row, 0).toString());
    txtEmail.setText(tabel_data_favorite.getValueAt(row, 1).toString());
    cmbjudulLagu.setSelectedItem(tabel_data_favorite.getValueAt(row, 2).toString());
    cmbArtis.setSelectedItem(tabel_data_favorite.getValueAt(row, 3).toString());
    cmbGenre.setSelectedItem(tabel_data_favorite.getValueAt(row, 4).toString());
    txtRating.setText(tabel_data_favorite.getValueAt(row, 5).toString());
    
    String favorite = tabel_data_favorite.getValueAt(row, 6).toString();
    chkFavorite.setSelected(favorite.equalsIgnoreCase("Ya"));
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
        cmbjudulLagu = new javax.swing.JComboBox<>();
        cmbArtis = new javax.swing.JComboBox<>();
        cmbGenre = new javax.swing.JComboBox<>();
        txtRating = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabel_data_favorite = new javax.swing.JTable();
        chkFavorite = new javax.swing.JCheckBox();
        btnSimpan = new javax.swing.JButton();
        btnHapus = new javax.swing.JButton();
        btnBatal = new javax.swing.JButton();
        btnClose = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("PLAYLIST FAVORITE");

        jLabel2.setText("Nama Pengguna");

        jLabel3.setText("Email Pengguna");

        jLabel4.setText("Judul Lagu");

        jLabel5.setText("Artis");

        jLabel6.setText("Genre");

        jLabel7.setText("Rating");

        cmbjudulLagu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Heaven", "Always Remember Us This Way", "Perfect Strangers" }));

        cmbArtis.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Jonas Blue, JP Coope", "Lady Gaga", "Bryan Adams" }));

        cmbGenre.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Country Soft Rock", "Pop, Rock", "Country, Pop" }));

        tabel_data_favorite.setModel(new javax.swing.table.DefaultTableModel(
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
        tabel_data_favorite.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabel_data_favoriteMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabel_data_favorite);

        chkFavorite.setText("Tambahkan ke Favorite");
        chkFavorite.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkFavoriteActionPerformed(evt);
            }
        });

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
                .addGap(394, 394, 394)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(46, 46, 46)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtNama)
                    .addComponent(txtEmail)
                    .addComponent(cmbjudulLagu, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cmbArtis, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cmbGenre, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtRating))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 465, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(24, 24, 24))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(chkFavorite)
                            .addGap(185, 185, 185)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnSimpan)
                        .addGap(32, 32, 32)
                        .addComponent(btnHapus)
                        .addGap(35, 35, 35)
                        .addComponent(btnBatal)
                        .addGap(18, 18, 18)
                        .addComponent(btnUpdate)
                        .addGap(18, 18, 18)
                        .addComponent(btnClose)))
                .addGap(30, 30, 30))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtNama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chkFavorite))
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(32, 32, 32)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(cmbjudulLagu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(29, 29, 29)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(cmbArtis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(29, 29, 29)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(cmbGenre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(txtRating, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSimpan)
                    .addComponent(btnHapus)
                    .addComponent(btnBatal)
                    .addComponent(btnClose)
                    .addComponent(btnUpdate))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanActionPerformed
        // TODO add your handling code here:
        insert();
    }//GEN-LAST:event_btnSimpanActionPerformed

    private void btnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusActionPerformed
        // TODO add your handling code here:
        delete();
    }//GEN-LAST:event_btnHapusActionPerformed

    private void btnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBatalActionPerformed
        // TODO add your handling code here:
        batal();
    }//GEN-LAST:event_btnBatalActionPerformed

    private void btnCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCloseActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_btnCloseActionPerformed

    private void tabel_data_favoriteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabel_data_favoriteMouseClicked
        // TODO add your handling code here:
        itempilih();
    }//GEN-LAST:event_tabel_data_favoriteMouseClicked

    private void chkFavoriteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkFavoriteActionPerformed
        // TODO add your handling code here:
        if (chkFavorite.isSelected()) {
        System.out.println("Favorite selected");
        } else {
            System.out.println("Favorite not selected");
        }
    }//GEN-LAST:event_chkFavoriteActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        // TODO add your handling code here:
        update();
    }//GEN-LAST:event_btnUpdateActionPerformed

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
            java.util.logging.Logger.getLogger(GUI_PlaylistFavorite.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUI_PlaylistFavorite.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUI_PlaylistFavorite.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUI_PlaylistFavorite.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new GUI_PlaylistFavorite().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBatal;
    private javax.swing.JButton btnClose;
    private javax.swing.JButton btnHapus;
    private javax.swing.JButton btnSimpan;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JCheckBox chkFavorite;
    private javax.swing.JComboBox<String> cmbArtis;
    private javax.swing.JComboBox<String> cmbGenre;
    private javax.swing.JComboBox<String> cmbjudulLagu;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabel_data_favorite;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtNama;
    private javax.swing.JTextField txtRating;
    // End of variables declaration//GEN-END:variables
}
