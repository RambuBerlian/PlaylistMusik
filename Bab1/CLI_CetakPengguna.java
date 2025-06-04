/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PlaylistMusik.Bab1;

/**
 *
 * @author Berlian
 */
public class CLI_CetakPengguna {
    // Main utama program yang akan dijalankan
    public static void main(String[] args){
        // Membuat object baru dengan nama "playlist" dari class PlaylistMusik
        PlaylistMusik playlist = new PlaylistMusik(); 
        
        // Mengatur data pengguna dan lagu dari data yang sudah disimpan
        playlist.dataNama("Berlian Rambu Ana Maharani"); // Menetapkan nama pengguna
        playlist.dataEmail("berlianrambu@gmail.com"); // Menetapkan email pengguna
        playlist.dataJudulLagu("Heaven"); // Menetapkan judul lagu
        playlist.dataArtis("Bryan Adams"); // Menetapkan nama artis
        playlist.dataGenre("Pop, Rock"); // Menetapkan genre lagu
        playlist.dataRating(5.0); // Menetapkan rating lagu
        
        // Mencetak header playlist musik ke dalam CLI
        System.out.println("PLAYLIST MUSIK");
        System.out.println("------------------------------------");
        
        // Mencetak informasi playlist dengan mengambil data yang sudah disimpan
        System.out.println("Nama        : "+ playlist.cetakNama()); 
        System.out.println("Email       : "+ playlist.cetakEmail()); 
        System.out.println("Judul Lagu  : "+ playlist.cetakJudulLagu()); 
        System.out.println("Artis       : "+ playlist.cetakArtis()); 
        System.out.println("Genre       : "+ playlist.cetakGenre()); 
        System.out.println("Rating      : "+ playlist.cetakRating()); 
    }
}
