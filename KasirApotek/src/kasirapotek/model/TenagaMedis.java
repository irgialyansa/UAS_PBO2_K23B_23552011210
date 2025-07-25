/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kasirapotek.model;

public abstract class TenagaMedis {
    protected String id, nama;
    public TenagaMedis(String id, String nama) {
        this.id = id; this.nama = nama;
    }
    public abstract String getJabatan();
    public String getNama() { return nama; }
}

