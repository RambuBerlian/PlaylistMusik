/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PlaylistMusik.Bab9;

import PlaylistMusik.Bab8.*;

/**
 *
 * @author Berlian
 */
public abstract class PlaylistMusik implements InfoLagu{
    // Atribut privat untuk menyimpan data pengguna dan lagu
    private String nama;
    private String email;
    private String judulLagu;
    private String artis;
    private String genre;
    private double rating;

    // Konstruktor untuk menginisialisasi semua atribut saat objek dibuat
     public PlaylistMusik(String nama, String email, String judulLagu, String artis, String genre, double rating) {
        this.nama = nama;
        this.email = email;
        this.judulLagu = judulLagu;
        this.artis = artis;
        this.genre = genre;
        setRating(rating);
    }

    // Getter untuk atribut nama
    public String getNama() { 
        return nama; 
    }

    // Setter untuk atribut nama
    public void setNama(String nama) { 
        this.nama = nama; 
    }

    // Getter untuk atribut email
    public String getEmail() { 
        return email; 
    }

    // Setter untuk atribut email
    public void setEmail(String email) { 
        this.email = email; 
    }

    // Getter untuk atribut judulLagu
    public String getJudulLagu() { 
        return judulLagu; 
    }

    // Setter untuk atribut judulLagu
    public void setJudulLagu(String judulLagu) { 
        this.judulLagu = judulLagu; 
    }

    // Getter untuk atribut artis
    public String getArtis() { 
        return artis; 
    }

    // Setter untuk atribut artis
    public void setArtis(String artis) { 
        this.artis = artis; 
    }

    // Getter untuk atribut genre
    public String getGenre() { 
        return genre; 
    }

    // Setter untuk atribut genre
    public void setGenre(String genre) { 
        this.genre = genre; 
    }

    // Getter untuk atribut rating
    public double getRating() { 
        return rating; 
    }

    // Setter untuk atribut rating dengan rentang nilai harus antara 0.0 sampai 5.0
    public void setRating(double rating) {
        if (rating < 0.0 || rating > 5.0) {
            System.out.println("Error: Rating harus antara 0.0 - 5.0!");
        } else {
            this.rating = rating;
        }
    }
    
    public void tampilkanInfo() {
        System.out.println("Judul: " + judulLagu);
        System.out.println("Artis: " + artis);
        System.out.println("Genre: " + genre);
        System.out.println("Rating: " + rating);
    }

    // Abstract method yang harus dioverride di subclass
    public abstract void tampilkanPlaylist();

    // Overloading method setRating dengan parameter tipe int
    public void setRating(int nilaiRating) {
        setRating((double) nilaiRating); // Mengkonversi int ke double dan memanggil versi double
    }
}
