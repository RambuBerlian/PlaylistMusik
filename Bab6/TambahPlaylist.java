/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PlaylistMusik.Bab6;

import PlaylistMusik.Bab5.*;

/**
 *
 * @author Berlian
 */
// Deklarasi kelas TambahPlaylist yang merupakan subclass dari PlaylistMusik
public class TambahPlaylist extends PlaylistMusik {
   // Atribut tambahan untuk menyimpan nama playlist
    private String namaPlaylist;

    // Konstruktor untuk menginisialisasi data pengguna, lagu, dan nama playlist
    public TambahPlaylist(String nama, String email, String namaPlaylist, String judulLagu, String artis, String genre) {
        super(nama, email, judulLagu, artis, genre, 0.0); // Rating diset default 0.0 saat menambahkan playlist
        this.namaPlaylist = namaPlaylist; // Menyimpan nama playlist
    }

    // Getter untuk atribut namaPlaylist
    public String getNamaPlaylist() {
        return namaPlaylist;
    }

    // Setter untuk atribut namaPlaylist
    public void setNamaPlaylist(String namaPlaylist) {
        this.namaPlaylist = namaPlaylist;
    }

    // Override method tampilkanPlaylist untuk menambahkan informasi nama playlist
    @Override
    public void tampilkanPlaylist() {
        // Menampilkan data
        System.out.println("Nama         : " + getNama());
        System.out.println("Email        : " + getEmail());
        System.out.println("Judul Lagu   : " + getJudulLagu());
        System.out.println("Artis        : " + getArtis());
        System.out.println("Genre        : " + getGenre());
        System.out.println("Rating       : " + getRating());
        System.out.println("Nama Playlist: " + namaPlaylist);
    }

    // Overloading method tampilkanPlaylist dengan parameter string
    public void tampilkanPlaylist(String informasiTambahan) {
        tampilkanPlaylist(); // Memanggil method tampilkanPlaylist versi override
        System.out.println("Informasi Tambahan : " + informasiTambahan); // Menampilkan info tambahan dari parameter
    }
}
