/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PlaylistMusik.Bab3;

/**
 *
 * @author Berlian
 */
// Superclass PlaylistMusik
public class PlaylistMusik {
    // Deklarasi atribut untuk menyimpan informasi pengguna dan lagu
    String nama, email, JudulLagu, artis, genre;
    double rating; // Rating diubah ke tipe data double untuk mendukung desimal
    
    // Method untuk mengatur nilai rating dengan validasi
    public void setRating(double rating) {
        // Memeriksa apakah rating berada dalam rentang 0.0 - 5.0
        if (rating < 0.0 || rating > 5.0) {
            System.out.println("Error: Rating harus antara 0.0 - 5.0!"); // Menampilkan pesan kesalahan jika rating tidak valid
        } else {
            this.rating = rating; // Menetapkan nilai rating jika valid
        }
    }

    // Konstruktor untuk menginisialisasi objek PlaylistMusik dengan parameter yang diberikan
    public PlaylistMusik(String nama, String email, String judulLagu, String artis, String genre, double rating) {
        this.nama = nama; // Menyimpan nama pengguna
        this.email = email; // Menyimpan email pengguna
        this.JudulLagu = judulLagu; // Menyimpan judul lagu
        this.artis = artis; // Menyimpan nama artis
        this.genre = genre; // Menyimpan genre lagu
        this.rating = rating; // Menyimpan rating lagu
    }

    // Method untuk menampilkan informasi playlist musik
    public void tampilkanPlaylist() {
        System.out.println("Nama       : " + nama);
        System.out.println("Email      : " + email);
        System.out.println("Judul Lagu : " + JudulLagu);
        System.out.println("Artis      : " + artis);
        System.out.println("Genre      : " + genre);
        System.out.println("Rating     : " + rating);
    }
}

