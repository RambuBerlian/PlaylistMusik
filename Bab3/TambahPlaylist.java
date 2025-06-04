/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PlaylistMusik.Bab3;

/**
 *
 * @author Berlian
 */
// Subclass TambahPlaylist yang merupakan turunan dari PlaylistMusik
public class TambahPlaylist extends PlaylistMusik {
    boolean isPremiumUser; // Menyimpan status pengguna premium
    String NamaPlaylist; // Menyimpan nama playlist, menggantikan atribut rating

    // Konstruktor untuk menginisialisasi objek TambahPlaylist
    public TambahPlaylist(String nama, String email, String NamaPlaylist, String judulLagu, String artis, String genre, boolean isPremiumUser) {
        super(nama, email, judulLagu, artis, genre, 0.0); // Memanggil konstruktor superclass, rating diatur ke 0.0 karena tidak digunakan
        this.NamaPlaylist = NamaPlaylist; // Menyimpan nama playlist
        this.isPremiumUser = isPremiumUser; // Menyimpan status pengguna premium
    }

    // Override method tampilkanPlaylist() untuk menampilkan informasi tambahan
    @Override
    public void tampilkanPlaylist() {
        super.tampilkanPlaylist(); // Memanggil method tampilkanPlaylist() dari superclass
        System.out.println("Nama Playlist: " + NamaPlaylist); // Menampilkan nama playlist
    }
}