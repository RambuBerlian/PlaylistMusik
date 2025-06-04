/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PlaylistMusik.Bab9;

/**
 *
 * @author Berlian
 */
public class DataPlaylist extends PlaylistMusik{
    public DataPlaylist(String nama, String email, String judulLagu, String artis, String genre, double rating) {
        super(nama, email, judulLagu, artis, genre, rating);
    }

    @Override
    public void tampilkanPlaylist() {
        System.out.println("Playlist oleh: " + getNama());
        tampilkanInfo(); // Panggil dari interface
    }
}
