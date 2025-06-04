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
public class GUI_TambahPlaylist extends javax.swing.JFrame {
    public Connection conn;
    /**
     * Creates new form NewJFrame
     */
    public GUI_TambahPlaylist() {
        initComponents();
        koneksi();
        tampil();
    }
    
    // Koneksi ke database
    public void koneksi() {
    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        conn = DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/playlistmusik_2318066?serverTimezone=UTC",
            "root", "");
    } catch (ClassNotFoundException | SQLException e) {
        JOptionPane.showMessageDialog(null, "Gagal koneksi ke database: " + e.getMessage());
    }
}
    
    // Tampilkan data ke tabel
    public void tampil() {
    // Create a new DefaultTableModel
    DefaultTableModel model = new DefaultTableModel();

    // Add columns to the table model
    model.addColumn("Nama");
    model.addColumn("Email");
    model.addColumn("Nama Playlist");
    model.addColumn("Judul Lagu");
    model.addColumn("Artis");
    model.addColumn("Genre");

    // Set the table model
    tabel_data_playlist.setModel(model);
    model.setRowCount(0);

    try {
        // Execute SQL query to fetch data
        String sql = "SELECT * FROM tb_tambahplaylist";
        Statement stat = conn.createStatement();
        ResultSet res = stat.executeQuery(sql);

        // Populate the table with data
        while (res.next()) {
            model.addRow(new Object[]{
                res.getString("nama"),
                res.getString("email"),
                res.getString("nama_playlist"),
                res.getString("judul_lagu"),
                res.getString("artis"),
                res.getString("genre")
            });
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Gagal menampilkan data: " + e.getMessage());
    }
}
    
    // Simpan data
    public void insert() {
        String nama = txtNama.getText();
        String email = txtEmail.getText();
        String namaPlaylist = txtNamaPlaylist.getText();
        String judulLagu = cmbjudulLagu.getSelectedItem().toString();
        String artis = cmbArtis.getSelectedItem().toString();
        String genre = cmbGenre.getSelectedItem().toString();

        try {
            String sql = "INSERT INTO tb_tambahplaylist(nama, email, nama_playlist, judul_lagu, artis, genre) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, nama);
            stmt.setString(2, email);
            stmt.setString(3, namaPlaylist);
            stmt.setString(4, judulLagu);
            stmt.setString(5, artis);
            stmt.setString(6, genre);
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data berhasil disimpan!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Gagal menyimpan data: " + e.getMessage());
        }
        refresh();
    }
    
    public void update() {
    String nama = txtNama.getText();
    String email = txtEmail.getText();
    String namaPlaylist = txtNamaPlaylist.getText();
    String judulLagu = cmbjudulLagu.getSelectedItem().toString();
    String artis = cmbArtis.getSelectedItem().toString();
    String genre = cmbGenre.getSelectedItem().toString();

    try {
        // Use the correct table name: tb_tambahplaylist
        String sql = "UPDATE tb_tambahplaylist SET nama_playlist=?, judul_lagu=?, artis=?, genre=? " +
                     "WHERE nama=? AND email=?";
        PreparedStatement stmt = conn.prepareStatement(sql);

        // Set parameters for the PreparedStatement
        stmt.setString(1, namaPlaylist);  // nama_playlist
        stmt.setString(2, judulLagu);     // judul_lagu
        stmt.setString(3, artis);         // artis
        stmt.setString(4, genre);         // genre
        stmt.setString(5, nama);          // nama (WHERE condition)
        stmt.setString(6, email);         // email (WHERE condition)

        // Execute the update query
        int rowsAffected = stmt.executeUpdate();

        if (rowsAffected > 0) {
            JOptionPane.showMessageDialog(null, "Data berhasil diupdate!");
        } else {
            JOptionPane.showMessageDialog(null, "Data tidak ditemukan untuk diperbarui.");
        }

        refresh(); // Refresh the GUI to display updated data

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Gagal mengupdate data: " + e.getMessage());
        e.printStackTrace(); // Optional: Log the full stack trace for debugging
    }
}
    
     // Hapus data
    public void delete() {
        int ok = JOptionPane.showConfirmDialog(null, "Hapus data ini?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
        if (ok == JOptionPane.YES_OPTION) {
            try {
                String sql = "DELETE FROM tb_tambahplaylist WHERE nama=? AND email=?";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, txtNama.getText());
                stmt.setString(2, txtEmail.getText());
                stmt.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data berhasil dihapus!");
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Gagal menghapus data: " + e.getMessage());
            }
        }
        refresh();
    }
    // Kosongkan form input
    public void batal() {
        txtNama.setText("");
        txtEmail.setText("");
        txtNamaPlaylist.setText("");
        cmbjudulLagu.setSelectedIndex(0);
        cmbArtis.setSelectedIndex(0);
        cmbGenre.setSelectedIndex(0);
    }

    // Pilih baris di tabel
    public void itempilih() {
        int row = tabel_data_playlist.getSelectedRow();
        txtNama.setText(tabel_data_playlist.getValueAt(row, 0).toString());
        txtEmail.setText(tabel_data_playlist.getValueAt(row, 1).toString());
        txtNamaPlaylist.setText(tabel_data_playlist.getValueAt(row, 2).toString());
        cmbjudulLagu.setSelectedItem(tabel_data_playlist.getValueAt(row, 3).toString());
        cmbArtis.setSelectedItem(tabel_data_playlist.getValueAt(row, 4).toString());
        cmbGenre.setSelectedItem(tabel_data_playlist.getValueAt(row, 5).toString());
    }
    // Refresh GUI
    public void refresh() {
        new GUI_TambahPlaylist().setVisible(true);
        this.setVisible(false);
    }
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField4 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtNama = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        txtNamaPlaylist = new javax.swing.JTextField();
        cmbjudulLagu = new javax.swing.JComboBox<>();
        cmbArtis = new javax.swing.JComboBox<>();
        cmbGenre = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabel_data_playlist = new javax.swing.JTable();
        chkBuatPlaylist = new javax.swing.JCheckBox();
        btnSimpan = new javax.swing.JButton();
        btnHapus = new javax.swing.JButton();
        btnBatal = new javax.swing.JButton();
        btnClose = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();

        jTextField4.setText("jTextField4");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("BUAT PLAYLIST");

        jLabel2.setText("Nama pengguna");

        jLabel3.setText("Email Pengguna");

        jLabel4.setText("Nama Playlist");

        jLabel5.setText("Judul Lagu");

        jLabel6.setText("Artis");

        jLabel7.setText("Genre");

        cmbjudulLagu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Heaven", "Always Remember Us This Way", "Perfect Strangers" }));
        cmbjudulLagu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbjudulLaguActionPerformed(evt);
            }
        });

        cmbArtis.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Jonas Blue, JP Coope", "Lady Gaga", "Bryan Adams" }));

        cmbGenre.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Country Soft Rock", "Pop, Rock", "Country, Pop" }));
        cmbGenre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbGenreActionPerformed(evt);
            }
        });

        tabel_data_playlist.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Nama", "Email", "Nama Playlist", "Judul Lagu", "Artis", "Genre"
            }
        ));
        tabel_data_playlist.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabel_data_playlistMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabel_data_playlist);

        chkBuatPlaylist.setText("Buat Playlist");

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
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(43, 43, 43)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(txtNama, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtEmail, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtNamaPlaylist, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmbjudulLagu, javax.swing.GroupLayout.Alignment.LEADING, 0, 1, Short.MAX_VALUE)
                    .addComponent(cmbArtis, javax.swing.GroupLayout.Alignment.LEADING, 0, 184, Short.MAX_VALUE)
                    .addComponent(cmbGenre, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(65, 65, 65)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(btnSimpan)
                                    .addGap(34, 34, 34)
                                    .addComponent(btnHapus)
                                    .addGap(33, 33, 33)
                                    .addComponent(btnBatal)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnUpdate)
                                    .addGap(18, 18, 18)
                                    .addComponent(btnClose))
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 479, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(26, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(chkBuatPlaylist)
                        .addGap(219, 219, 219))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel1)
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtNama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chkBuatPlaylist))
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(35, 35, 35)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtNamaPlaylist, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(33, 33, 33)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(cmbjudulLagu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(cmbArtis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(cmbGenre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnSimpan)
                    .addComponent(btnHapus)
                    .addComponent(btnClose)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnBatal)
                        .addComponent(btnUpdate)))
                .addContainerGap(32, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmbjudulLaguActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbjudulLaguActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbjudulLaguActionPerformed

    private void cmbGenreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbGenreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbGenreActionPerformed

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

    private void tabel_data_playlistMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabel_data_playlistMouseClicked
        // TODO add your handling code here:
        itempilih();
    }//GEN-LAST:event_tabel_data_playlistMouseClicked

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
            java.util.logging.Logger.getLogger(GUI_TambahPlaylist.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUI_TambahPlaylist.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUI_TambahPlaylist.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUI_TambahPlaylist.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new GUI_TambahPlaylist().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBatal;
    private javax.swing.JButton btnClose;
    private javax.swing.JButton btnHapus;
    private javax.swing.JButton btnSimpan;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JCheckBox chkBuatPlaylist;
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
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTable tabel_data_playlist;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtNama;
    private javax.swing.JTextField txtNamaPlaylist;
    // End of variables declaration//GEN-END:variables
}
