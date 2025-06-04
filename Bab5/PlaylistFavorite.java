/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PlaylistMusik.Bab5;

/**
 *
 * @author Berlian
 */
// Deklarasi kelas PlaylistFavorite yang merupakan subclass dari PlaylistMusik
public class PlaylistFavorite extends PlaylistMusik {
    // Atribut tambahan untuk menandai apakah lagu ini termasuk favorit
    private boolean isFavorite;

    // Konstruktor untuk menginisialisasi semua atribut, termasuk atribut dari superclass
    public PlaylistFavorite(String nama, String email, String judulLagu, String artis, String genre, double rating, boolean isFavorite) {
        super(nama, email, judulLagu, artis, genre, rating); // Memanggil konstruktor dari PlaylistMusik
        this.isFavorite = isFavorite; // Menetapkan status favorit
    }

    // Getter untuk atribut isFavorite
    public boolean isFavorite() {
        return isFavorite;
    }

    // Setter untuk atribut isFavorite
    public void setFavorite(boolean isFavorite) {
        this.isFavorite = isFavorite;
    }

    // Override method tampilkanPlaylist untuk menambahkan informasi status favorit
    @Override
    public void tampilkanPlaylist() {
        super.tampilkanPlaylist(); // Memanggil versi method dari PlaylistMusik
        // Menampilkan status favorit dengan kondisi ternary
        System.out.println("Status Favorite: " + (isFavorite ? " Berhasil ditambahkan ke Favorit" : " Tidak Ditambahkan ke favorit"));
    }

    // Overloading method tampilkanPlaylist dengan parameter tambahan boolean
    public void tampilkanPlaylist(boolean tampilkanHeader) {
        if (tampilkanHeader) {
            System.out.println("=== Data Playlist Favorit ===");
        }
        tampilkanPlaylist(); // Memanggil method tampilkanPlaylist yang sudah dioverride
    }
}
