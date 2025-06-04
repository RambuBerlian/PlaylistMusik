/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PlaylistMusik.Bab4;

/**
 *
 * @author Berlian
 */
public class PlaylistMusik {
    // Private attributes
    private String nama; // Menyimpan nama pengguna
    private String email; // Menyimpan email pengguna
    private String judulLagu; // Menyimpan judul lagu
    private String artis; // Menyimpan nama artis
    private String genre; // Menyimpan genre lagu
    private double rating; // Menyimpan rating lagu (0.0 - 5.0)

    // Konstruktor untuk menginisialisasi objek PlaylistMusik
    public PlaylistMusik(String nama, String email, String judulLagu, String artis, String genre, double rating) {
        this.nama = nama; // Inisialisasi nama pengguna
        this.email = email; // Inisialisasi email pengguna
        this.judulLagu = judulLagu; // Inisialisasi judul lagu
        this.artis = artis; // Inisialisasi nama artis
        this.genre = genre; // Inisialisasi genre lagu
        setRating(rating); // Gunakan setter untuk validasi rating
    }

    // Mengembalikan nilai nama pengguna
    public String getNama() {
        return nama;
    }

    // Mengatur nilai nama pengguna
    public void setNama(String nama) {
        this.nama = nama;
    }

    // Mengembalikan nilai email pengguna
    public String getEmail() {
        return email;
    }

    // Mengatur nilai email pengguna
    public void setEmail(String email) {
        this.email = email;
    }

    // Mengembalikan judul lagu
    public String getJudulLagu() {
        return judulLagu;
    }

    // Mengatur judul lagu
    public void setJudulLagu(String judulLagu) {
        this.judulLagu = judulLagu;
    }

    // Mengembalikan nama artis
    public String getArtis() {
        return artis;
    }

    // Mengatur nama artis
    public void setArtis(String artis) {
        this.artis = artis;
    }

    // Mengembalikan genre lagu
    public String getGenre() {
        return genre;
    }

    // Mengatur genre lagu
    public void setGenre(String genre) {
        this.genre = genre;
    }

    // Mengembalikan nilai rating lagu
    public double getRating() {
        return rating;
    }

    // Mengatur nilai rating lagu dengan validasi (0.0 - 5.0)
    public void setRating(double rating) {
        if (rating < 0.0 || rating > 5.0) {
            System.out.println("Error: Rating harus antara 0.0 - 5.0!");
        } else {
            this.rating = rating;
        }
    }

    // Menampilkan semua informasi playlist ke konsol
    public void tampilkanPlaylist() {
        System.out.println("Nama       : " + nama);
        System.out.println("Email      : " + email);
        System.out.println("Judul Lagu : " + judulLagu);
        System.out.println("Artis      : " + artis);
        System.out.println("Genre      : " + genre);
        System.out.println("Rating     : " + rating);
    }
}
