/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PlaylistMusik.Bab3;

/**
 *
 * @author Berlian
 */
// Subclass PlaylistFavorite yang merupakan turunan dari PlaylistMusik
public class PlaylistFavorite extends PlaylistMusik {
    boolean isFavorite; // Menyimpan status favorit, tidak menggunakan private (sebaiknya diubah untuk enkapsulasi)

    // Konstruktor untuk menginisialisasi objek PlaylistFavorite
    public PlaylistFavorite(String nama, String email, String judulLagu, String artis, String genre, double rating, boolean isFavorite) {
        super(nama, email, judulLagu, artis, genre, rating); // Memanggil konstruktor dari superclass PlaylistMusik
        this.isFavorite = isFavorite; // Menetapkan status favorit berdasarkan parameter yang diberikan
    }

    // Override method tampilkanPlaylist() untuk menambahkan informasi status favorit
    @Override
    public void tampilkanPlaylist() {
        super.tampilkanPlaylist(); // Memanggil method tampilkanPlaylist() dari superclass
        System.out.println("Status Favorite: " + (isFavorite ? " Berhasil ditambahkan ke Favorit" : " Tidak Ditambahkan ke favorit"));
        // Menampilkan status apakah lagu masuk ke favorit atau tidak dengan ternary operator
    }
}
