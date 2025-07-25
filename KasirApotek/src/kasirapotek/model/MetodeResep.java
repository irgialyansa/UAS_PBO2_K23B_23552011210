/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kasirapotek.model;

public class MetodeResep {
    public void verifikasi(TenagaMedis medis) {
        System.out.println("Resep diverifikasi oleh: " + medis.getJabatan()
                           + " " + medis.getNama());
    }
}

