/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kasirapotek.model;

public class Apoteker extends TenagaMedis {
    public Apoteker(String id, String nama) { super(id, nama); }
    @Override public String getJabatan() { return "Apoteker"; }
}

