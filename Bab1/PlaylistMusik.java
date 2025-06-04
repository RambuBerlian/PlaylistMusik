/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PlaylistMusik.Bab1;

/**
 *
 * @author Berlian
 */
public class PlaylistMusik { // Mendeklarasi kelas
    // Mendeklarasi variabel instance untuk menyimpan data pengguna
    String nama, email, judulLagu, artis, genre;
    double rating;
    
    // Method untuk menyimpan nama pengguna
    void dataNama(String nama){
        this.nama = nama;   // This untuk membedakan antara variabel instance dan parameter
    }
    
    // Method untuk menyimpan email pengguna
    void dataEmail(String email){
        this.email = email;
    }
    
    // Method untuk menyimpan judul lagu
    void dataJudulLagu(String judulLagu){
        this.judulLagu = judulLagu;
    }
    
    // Method untuk menyimpan nama artis
    void dataArtis(String artis){
        this.artis = artis;
    }
    
    // Method untuk menyimpan genre lagu
    void dataGenre(String genre){
        this.genre = genre;
    }
    
    // Method untuk menyimpan rating lagu
    void dataRating(double rating){
        this.rating = rating;
    }
    
    // Method untuk mengembalikan nama pengguna
    String cetakNama(){
        return nama;
    }
    
    // Method untuk mengembalikan email pengguna
    String cetakEmail(){
        return email;
    }
    
    // Method untuk mengembalikan judul lagu
    String cetakJudulLagu(){
        return judulLagu;
    }
    
    // Method untuk mengembalikan nama artis
    String cetakArtis(){
        return artis;
    }
    
    // Method untuk mengembalikan genre lagu
    String cetakGenre(){
        return genre;
    }
    
    // Method untuk mengembalikan rating lagu
    double cetakRating(){
        return rating;
    }
}
