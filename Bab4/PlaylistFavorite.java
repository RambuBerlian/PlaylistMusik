/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PlaylistMusik.Bab4;

/**
 *
 * @author Berlian
 */
public class PlaylistFavorite extends PlaylistMusik{
    // Atribut private untuk menyimpan status apakah lagu favorit atau tidak
    private boolean isFavorite;

    // Konstruktor PlaylistFavorite yang memanggil konstruktor superclass PlaylistMusik
    public PlaylistFavorite(String nama, String email, String judulLagu, String artis, String genre, double rating, boolean isFavorite) {
        super(nama, email, judulLagu, artis, genre, rating); // Memanggil konstruktor dari PlaylistMusik
        this.isFavorite = isFavorite; // Inisialisasi status favorit dari parameter
    }

    // Getter untuk mendapatkan status favorit
    public boolean isFavorite() {
        return isFavorite;
    }

    // Setter untuk mengubah status favorit
    public void setFavorite(boolean isFavorite) {
        this.isFavorite = isFavorite;
    }

    // Override method tampilkanPlaylist() untuk menambahkan informasi status favorit
    @Override
    public void tampilkanPlaylist() {
        super.tampilkanPlaylist(); // Panggil method tampilkanPlaylist() dari superclass PlaylistMusik
        System.out.println("Status Favorite: " + (isFavorite ? " Berhasil ditambahkan ke Favorit" : " Tidak Ditambahkan ke favorit"));
        // Tampilkan status favorit menggunakan ternary operator
    }
}
