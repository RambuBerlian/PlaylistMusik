/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PlaylistMusik.Bab2;

/**
 *
 * @author Berlian
 */
public class PlaylistMain {
    public static void main(String[] args) {
        // Membuat objek PlaylistMusik dengan satu lagu
        PlaylistMusik playlist = new PlaylistMusik(
            "Berlian Rambu Ana Maharani",
            "berlianrambu@gmail.com",
            "Heaven",
            "Bryan Adams",
            "Pop, Rock",
            5.0
        );

        // Menampilkan info pengguna
        playlist.tampilkanInfo();

        // Menampilkan lagu dalam playlist
        playlist.tampilkanPlaylist();

        // Mencari lagu dalam playlist
        playlist.cariLagu("Heaven ");

        // Menghapus lagu dari playlist
        playlist.hapusLagu();
    }
}
