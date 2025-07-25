UTS Pemrograman Berorientasi Obyek 2


  Mata Kuliah: Pemrograman Berorientasi Obyek 2   Dosen Pengampu: Muhammad Ikhwan Fathulloh

Profil


  Nama: Irgi Alyansa Perdana
  NIM: 23552011210
  Studi Kasus: Kasir Apotek

Judul Studi Kasus

Kasir Apotek.

Penjelasan Studi Kasus

Deskripsi: Mengelola transaksi pembelian obat, baik dengan resep dokter maupun pembelian langsung.
Proyek Kasir Apotek ini merupakan aplikasi berbasis Java yang mengelola transaksi obat dengan menerapkan konsep Konsep OOP berikut: Inheritance, Encapsulation, Polymorphism, dan Abstraction.


Struktur Project Kasir Apotek


ApotekKasir/
├── src/
│   └── kasirapotek/
│       ├── KasirForm.java
│       ├── model/
│       │   ├── Pasien.java
│       │   ├── Obat.java
│       │   ├── TenagaMedis.java
│       │   ├── Dokter.java
│       │   ├── Apoteker.java
│       │   └── MetodeResep.java
└── lib/
    └── mysql-connector-java.jar 
## Penjelasan 4 Pilar OOP dalam Studi Kasus

1.	Inheritance:

contoh kode :

public class Dokter extends TenagaMedis {
    public Dokter(String id, String nama) {
        super(id, nama);
    }

    @Override
    public String getJabatan() {
        return "Dokter";
    }
}


2.	Polymorphism:

contoh kode :

public class MetodeResep {
    public void verifikasi(TenagaMedis medis) {
        System.out.println("Resep diverifikasi oleh: " + medis.getJabatan() + " " + medis.nama);
    }
}
3.	Encapsulation:

contoh kode :

public class Pasien {
    private int id;
    private String nama;
    private int umur;

    public Pasien(int id, String nama, int umur) {
        this.id = id;
        this.nama = nama;
        this.umur = umur;
    }

    public int getId() { return id; }
    public String getNama() { return nama; }
    public int getUmur() { return umur; }
}
4.	Abstract Class:
contoh kode :
public abstract class TenagaMedis {
    protected String id;
    protected String nama;

    public TenagaMedis(String id, String nama) {
        this.id = id;
        this.nama = nama;
    }

    public abstract String getJabatan();
}return nama; }
}

Fitur aplikasi:
1.	Sistem login
2.	Dashboard menu
3.	Manajemen pasien
4.	Manajemen obat
5.	Transaksi dengan resep dokter
6.	Pembelian langsung
7.	Manajemen transaksi




 
Demo Proyek


Github: https://github.com/irgialyansa/UAS_PBO2_K23B_23552011210.git 
Youtube: https://youtu.be/ZqogOaQnJ_4

