/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kasirapotek;

import kasirapotek.model.*;
import javax.swing.*;
import javax.swing.table.*;
import java.sql.*;

public class KasirForm extends JFrame {
    // Komponen Swing
    private JComboBox<String> cbPasien, cbObat;
    private JTextField txtJumlah;
    private JButton btnTambah, btnSimpan;
    private JTable tblKeranjang;
    private JLabel lblTotal;
    private JRadioButton optLangsung, optResep;
    private ButtonGroup groupPembelian;
    private DefaultTableModel model;
    private double total = 0;

    public KasirForm() {
        initComponents();
        initForm();
    }

    private void initForm() {
        model = new DefaultTableModel(
            new Object[]{"Nama Obat", "Harga", "Jumlah", "Subtotal"}, 0
        );
        tblKeranjang.setModel(model);
        groupPembelian = new ButtonGroup();
        groupPembelian.add(optLangsung);
        groupPembelian.add(optResep);
        optLangsung.setSelected(true);
        loadPasien();
        loadObat();
    }
    
    private Connection getConnection() {
    try {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/apotek", "root", "");
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(this, "Koneksi gagal: " + ex.getMessage());
        return null;
        
    }
    
}


    private Connection connect() throws SQLException {
        return DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/apotek","root",""
        );
    }

    private void loadPasien() {
        try (Connection conn = connect();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM pasien")) {
            while (rs.next())
                cbPasien.addItem(rs.getInt("id")+" - "+rs.getString("nama"));
        } catch(Exception e){e.printStackTrace();}
    }

    private void loadObat() {
        try (Connection conn = connect();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM obat")) {
            while (rs.next())
                cbObat.addItem(rs.getInt("id")+" - "+rs.getString("nama")
                    +" - "+rs.getDouble("harga"));
        } catch(Exception e){e.printStackTrace();}
    }
    
    

    private void btnTambahActionPerformed() {
        String[] parts = cbObat.getSelectedItem().toString().split(" - ");
        String nama = parts[1];
        double harga = Double.parseDouble(parts[2]);
        int jumlah = Integer.parseInt(txtJumlah.getText());
        double subtotal = harga*jumlah;
        model.addRow(new Object[]{nama, harga, jumlah, subtotal});
        total += subtotal;
        lblTotal.setText("Total: Rp "+total);
    }

    private void btnSimpanActionPerformed() {
        try (Connection conn = connect()) {
            int pasienId = Integer.parseInt(
                cbPasien.getSelectedItem().toString().split(" - ")[0]
            );
            PreparedStatement pstT = conn.prepareStatement(
                "INSERT INTO transaksi(pasien_id, total, tanggal) VALUES (?,?,NOW())",
                Statement.RETURN_GENERATED_KEYS
            );
            pstT.setInt(1, pasienId);
            pstT.setDouble(2, total);
            pstT.executeUpdate();
            ResultSet rsT = pstT.getGeneratedKeys();
            rsT.next();
            int transId = rsT.getInt(1);

            for (int i=0; i<model.getRowCount(); i++){
                String namaObat = model.getValueAt(i,0).toString();
                int jumlah = (int) model.getValueAt(i,2);
                PreparedStatement pstO = conn.prepareStatement(
                  "SELECT id,stok FROM obat WHERE nama=?"
                );
                pstO.setString(1,namaObat);
                ResultSet rsO = pstO.executeQuery();
                rsO.next();
                int obatId=rsO.getInt("id"), stok=rsO.getInt("stok");
                if(jumlah>stok){
                    JOptionPane.showMessageDialog(this,
                      "Stok "+namaObat+" tidak cukup!");
                    return;
                }
                // insert detail_resep
                PreparedStatement pstD = conn.prepareStatement(
                  "INSERT INTO detail_resep(resep_id,obat_id,jumlah) VALUES (?,?,?)"
                );
                pstD.setInt(1,transId);
                pstD.setInt(2,obatId);
                pstD.setInt(3, jumlah);
                pstD.executeUpdate();
                // update stok
                PreparedStatement pstU = conn.prepareStatement(
                  "UPDATE obat SET stok=stok-? WHERE id=?"
                );
                pstU.setInt(1,jumlah);
                pstU.setInt(2, obatId);
                pstU.executeUpdate();
            }
            JOptionPane.showMessageDialog(this,"Transaksi berhasil!");
            model.setRowCount(0);
            total=0;
            lblTotal.setText("Total: Rp 0");
        } catch(Exception e){e.printStackTrace();}
    }

    private void initComponents(){
        setTitle("Kasir Apotek");
        cbPasien = new JComboBox<>();
        cbObat = new JComboBox<>();
        txtJumlah = new JTextField(5);
        btnTambah = new JButton("Tambah");
        btnSimpan = new JButton("Simpan Transaksi");
        tblKeranjang = new JTable();
        lblTotal = new JLabel("Total: Rp 0");
        optLangsung = new JRadioButton("Langsung");
        optResep = new JRadioButton("Resep");
        btnTambah.addActionListener(e->btnTambahActionPerformed());
        btnSimpan.addActionListener(e->btnSimpanActionPerformed());
        JPanel p = new JPanel();
        p.add(new JLabel("Pasien:")); p.add(cbPasien);
        p.add(new JLabel("Metode:")); p.add(optLangsung); p.add(optResep);
        p.add(new JLabel("Obat:")); p.add(cbObat);
        p.add(new JLabel("Jumlah:")); p.add(txtJumlah);
        p.add(btnTambah);
        add(p, "North");
        add(new JScrollPane(tblKeranjang),"Center");
        JPanel bottom = new JPanel();
        bottom.add(lblTotal);
        bottom.add(btnSimpan);
        add(bottom, "South");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack(); setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(()->new KasirForm().setVisible(true));
    }
}

