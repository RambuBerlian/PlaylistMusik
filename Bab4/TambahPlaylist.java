/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PlaylistMusik.Bab4;

/**
 *
 * @author Berlian
 */
public class TambahPlaylist extends PlaylistMusik{
    /// Atribut private untuk enkapsulasi
    private String namaPlaylist; // Menyimpan nama playlist baru

    // Konstruktor
    public TambahPlaylist(String nama, String email, String namaPlaylist, String judulLagu, String artis, String genre) {
        super(nama, email, judulLagu, artis, genre, 0.0); // Rating diset 0.0 karena tidak digunakan
        this.namaPlaylist = namaPlaylist;
    }

    // Getter nama playlist
    public String getNamaPlaylist() {
        return namaPlaylist;
    }

    // Setter nama playlist
    public void setNamaPlaylist(String namaPlaylist) {
        this.namaPlaylist = namaPlaylist;
    }

    // Override method tampilkanPlaylist
    @Override
    public void tampilkanPlaylist() {
        super.tampilkanPlaylist(); // Tampilkan data dari superclass
        System.out.println("Nama Playlist   : " + namaPlaylist); // Tampilkan nama playlist tambahan
    }
}
