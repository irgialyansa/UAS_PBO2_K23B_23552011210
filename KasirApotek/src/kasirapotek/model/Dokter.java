/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kasirapotek.model;

public class Dokter extends TenagaMedis {
    public Dokter(String id, String nama) { super(id, nama); }
    @Override public String getJabatan() { return "Dokter"; }
}

