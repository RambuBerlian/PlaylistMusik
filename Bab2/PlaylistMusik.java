/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PlaylistMusik.Bab2;

/**
 *
 * @author Berlian
 */
public class PlaylistMusik {
    // Atribut
    String nama, email, judulLagu, artis, genre;
    double rating;

    // Konstruktor
    public PlaylistMusik(String nama, String email, String judulLagu, String artis, String genre, double rating) {
        this.nama = nama;
        this.email = email;
        this.judulLagu = judulLagu;
        this.artis = artis;
        this.genre = genre;
        this.rating = rating;
    }

    // Menampilkan informasi pengguna
    public void tampilkanInfo() {
        System.out.println("Nama Pengguna : " + nama);
        System.out.println("Email         : " + email);
        System.out.println("--------------------------------------");
        System.out.println("Method Playlist :");
    }

    // Menampilkan lagu dalam playlist
    public void tampilkanPlaylist() {
        System.out.println("Menampilkan lagu dalam Playlist:");
        System.out.println("Judul  : " + judulLagu);
        System.out.println("Artis  : " + artis);
        System.out.println("Genre  : " + genre);
        System.out.println("Rating : " + rating);
        System.out.println("--------------------------------------");
    }

    // Mencari lagu dalam playlist
    public void cariLagu(String lagu) {
        System.out.println("Mencari lagu : " + lagu);
        System.out.println("Lagu ditemukan dalam playlist.");
    }

    // Menghapus lagu dari playlist
    public void hapusLagu() {
        System.out.println("Menghapus lagu : " + judulLagu + " berhasil di hapus dari playlist");
    }
}
