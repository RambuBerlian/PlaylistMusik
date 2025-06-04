/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PlaylistMusik.Bab8;
import javax.swing.JOptionPane;
import java.util.Scanner;

/**
 *
 * @author Berlian
 */
public class PlaylistMain {
    public static void main(String[] args) {
        // Membuat objek Scanner untuk membaca input dari pengguna
        Scanner scanner = new Scanner(System.in);

        // Blok try-catch untuk menangani kemungkinan error saat runtime
        try {
            // Menampilkan judul input
            System.out.println("=== INPUT DATA PLAYLIST ===");

            // Mengambil input nama pengguna
            System.out.print("Nama: ");
            String nama = scanner.nextLine();

            // Mengambil input email pengguna
            System.out.print("Email: ");
            String email = scanner.nextLine();

            // Mengambil input judul lagu
            System.out.print("Judul Lagu: ");
            String judul = scanner.nextLine();

            // Mengambil input artis
            System.out.print("Artis: ");
            String artis = scanner.nextLine();

            // Mengambil input genre lagu
            System.out.print("Genre: ");
            String genre = scanner.nextLine();

            // Meminta pengguna memilih jenis entri: Favorite atau Tambah Playlist
            System.out.print("Pilih jenis entri (1 = Favorite, 2 = Tambah Playlist): ");
            int pilihan = Integer.parseInt(scanner.nextLine()); // Mengonversi input string menjadi int

            // Jika pilihan 1, berarti pengguna ingin menambahkan ke Playlist Favorite
            if (pilihan == 1) {
                // Input rating lagu
                System.out.print("Rating (0.0 - 5.0): ");
                double rating = Double.parseDouble(scanner.nextLine());

                // Validasi nilai rating agar berada dalam rentang 0.0 hingga 5.0
                if (rating < 0.0 || rating > 5.0) {
                    throw new IllegalArgumentException("Rating harus dalam rentang 0.0 - 5.0");
                }

                // Menanyakan apakah lagu ini termasuk favorit
                System.out.print("Apakah lagu ini favorit? (true/false): ");
                boolean isFavorite = Boolean.parseBoolean(scanner.nextLine());

                // Membuat objek PlaylistFavorite dengan data yang dimasukkan
                PlaylistFavorite pf = new PlaylistFavorite(nama, email, judul, artis, genre, rating, isFavorite);

                // Menampilkan hasil playlist menggunakan method overloading
                System.out.println("\n=== HASIL PLAYLIST FAVORIT ===");
                pf.tampilkanPlaylist(true);

            // Jika pilihan 2, maka buat playlist baru dengan nama playlist
            } else if (pilihan == 2) {
                System.out.print("Nama Playlist: ");
                String namaPlaylist = scanner.nextLine();

                // Membuat objek TambahPlaylist
                TambahPlaylist tp = new TambahPlaylist(nama, email, namaPlaylist, judul, artis, genre);

                // Menampilkan hasil playlist dengan tambahan keterangan
                System.out.println("\n=== HASIL TAMBAH PLAYLIST ===");
                tp.tampilkanPlaylist("Playlist ditambahkan secara manual oleh pengguna.");

            // Jika input selain 1 dan 2, munculkan pesan peringatan
            } else {
                JOptionPane.showMessageDialog(null, "Pilihan tidak valid! Masukkan 1 atau 2.", "Peringatan", JOptionPane.WARNING_MESSAGE);
            }

        // Penanganan error jika input angka tidak valid (misalnya huruf dimasukkan untuk rating)
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Input angka tidak valid! " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);

        // Penanganan error logika, seperti rating di luar batas
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, "Kesalahan : " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);

        // Penanganan error umum lainnya
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Terjadi kesalahan: " + e.getMessage(), "Kesalahan Umum", JOptionPane.ERROR_MESSAGE);

        // Blok finally akan selalu dijalankan walaupun terjadi error
        } finally {
            // Menampilkan pesan terima kasih setelah program selesai
            JOptionPane.showMessageDialog(null, "Terima kasih telah menggunakan program PlaylistMusik.", "Selesai", JOptionPane.INFORMATION_MESSAGE);

            // Menutup scanner agar tidak terjadi memory leak
            scanner.close();
        }
    }
}
