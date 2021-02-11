/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package surat;
import fungsi.WarnaTable;
import fungsi.batasInput;
import fungsi.koneksiDB;
import fungsi.validasi;
import java.awt.Dimension;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.Timer;
import javax.swing.event.DocumentEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
/**
 *
 * @author root
 */
public class DlgSuratSehat extends javax.swing.JDialog {

    private final DefaultTableModel tabMode;
    private Connection koneksi_john=koneksiDB.condb();
    private ResultSet rs_john;
    private Object WindowAmbilSampel;
    private validasi Valid=new validasi();
    private ResultSet rs;
    
    public DlgSuratSehat(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        
       // WindowAmbilSampel.setSize(530,80);
        tabMode=new DefaultTableModel(null,new Object[]{
            "No.Surat","No.Rawat","No. RM","Pasien","Kd. Dokter","Dokter","Keperluan","Tgl. Surat","Tinggi Badan",
            "Berat Badan","Tensi"
            }){
              @Override public boolean isCellEditable(int rowIndex, int colIndex){return false;}
        };
        tbDataSuratSehat.setModel(tabMode);
        tbDataSuratSehat.setPreferredScrollableViewportSize(new Dimension(800,800));
        tbDataSuratSehat.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        
        int i;
        for (i = 0; i < 10; i++) {
            TableColumn column = tbDataSuratSehat.getColumnModel().getColumn(i);
            if(i==0){
                column.setPreferredWidth(100);
            }else if(i==1){
                column.setPreferredWidth(120);
            }else if(i==2){
                column.setPreferredWidth(90);
            }else if(i==3){
                column.setPreferredWidth(180);
            }else if(i==4){
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }else if(i==5){
                column.setPreferredWidth(180);
            }else if(i==6){
                column.setPreferredWidth(230);
            }else if(i==7){
                column.setPreferredWidth(70);
            }else if(i==8){
                column.setPreferredWidth(70);
            }else if(i==9){
                column.setPreferredWidth(70);
            }else if(i==10){
                column.setPreferredWidth(70);
            }else if(i==11){
                column.setPreferredWidth(70);
            }else if(i==12){
                column.setPreferredWidth(70);
            }    
        }
        tbDataSuratSehat.setDefaultRenderer(Object.class, new WarnaTable());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        internalFrame1 = new widget.InternalFrame();
        jPanel1 = new javax.swing.JPanel();
        FormInput = new widget.PanelBiasa();
        jLabel4 = new widget.Label();
        TTinggiBadan = new widget.TextBox();
        TNoRawat = new widget.TextBox();
        jLabel5 = new widget.Label();
        TNamaPasien1 = new widget.TextBox();
        DTPTglSurat = new widget.Tanggal();
        jLabel6 = new widget.Label();
        jLabel7 = new widget.Label();
        TNoRM1 = new widget.TextBox();
        jLabel8 = new widget.Label();
        jLabel9 = new widget.Label();
        TBeratBadan = new widget.TextBox();
        jLabel10 = new widget.Label();
        jLabel11 = new widget.Label();
        TTensi = new widget.TextBox();
        scrollPane1 = new widget.ScrollPane();
        TKesan = new widget.TextArea();
        jLabel13 = new widget.Label();
        TKdDokter = new widget.TextBox();
        TNamaDokter = new widget.TextBox();
        jLabel14 = new widget.Label();
        jPanel2 = new javax.swing.JPanel();
        panelGlass6 = new widget.panelisi();
        BtnSimpan = new widget.Button();
        BtnBatal = new widget.Button();
        BtnEdit = new widget.Button();
        BtnHapus = new widget.Button();
        BtnPrint = new widget.Button();
        BtnAll = new widget.Button();
        jLabel12 = new widget.Label();
        LCount = new widget.Label();
        BtnKeluar = new widget.Button();
        panelGlass7 = new widget.panelisi();
        jLabel15 = new widget.Label();
        DTPCari1 = new widget.Tanggal();
        jLabel17 = new widget.Label();
        DTPCari2 = new widget.Tanggal();
        jLabel16 = new widget.Label();
        TCari = new widget.TextBox();
        BtnCari = new widget.Button();
        Scroll = new widget.ScrollPane();
        tbDataSuratSehat = new widget.Table();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Data Surat Sehat ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(70, 70, 70))); // NOI18N
        internalFrame1.setFont(new java.awt.Font("Tahoma", 2, 12)); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout());

        jPanel1.setLayout(new java.awt.BorderLayout());

        FormInput.setPreferredSize(new java.awt.Dimension(490, 210));
        FormInput.setLayout(null);

        jLabel4.setText("/ kg");
        jLabel4.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        FormInput.add(jLabel4);
        jLabel4.setBounds(140, 170, 18, 23);

        TTinggiBadan.setBackground(new java.awt.Color(245, 250, 240));
        TTinggiBadan.setHighlighter(null);
        FormInput.add(TTinggiBadan);
        TTinggiBadan.setBounds(80, 140, 50, 23);

        TNoRawat.setBackground(new java.awt.Color(245, 250, 240));
        TNoRawat.setHighlighter(null);
        FormInput.add(TNoRawat);
        TNoRawat.setBounds(80, 10, 152, 23);

        jLabel5.setText("No.Rawat :");
        FormInput.add(jLabel5);
        jLabel5.setBounds(10, 10, 60, 23);

        TNamaPasien1.setBackground(new java.awt.Color(245, 250, 240));
        TNamaPasien1.setHighlighter(null);
        FormInput.add(TNamaPasien1);
        TNamaPasien1.setBounds(320, 10, 270, 23);

        DTPTglSurat.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "14-12-2019" }));
        DTPTglSurat.setDisplayFormat("dd-MM-yyyy");
        DTPTglSurat.setOpaque(false);
        DTPTglSurat.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                DTPTglSuratItemStateChanged(evt);
            }
        });
        DTPTglSurat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DTPTglSuratKeyPressed(evt);
            }
        });
        FormInput.add(DTPTglSurat);
        DTPTglSurat.setBounds(250, 170, 100, 23);

        jLabel6.setText("Keperluan :");
        FormInput.add(jLabel6);
        jLabel6.setBounds(10, 70, 60, 23);

        jLabel7.setText("Tgl. Surat :");
        FormInput.add(jLabel7);
        jLabel7.setBounds(180, 170, 60, 23);

        TNoRM1.setBackground(new java.awt.Color(245, 250, 240));
        TNoRM1.setHighlighter(null);
        FormInput.add(TNoRM1);
        TNoRM1.setBounds(240, 10, 70, 23);

        jLabel8.setText("Tinggi :");
        FormInput.add(jLabel8);
        jLabel8.setBounds(20, 140, 50, 23);

        jLabel9.setText("Berat :");
        FormInput.add(jLabel9);
        jLabel9.setBounds(20, 170, 50, 23);

        TBeratBadan.setBackground(new java.awt.Color(245, 250, 240));
        TBeratBadan.setHighlighter(null);
        FormInput.add(TBeratBadan);
        TBeratBadan.setBounds(80, 170, 50, 23);

        jLabel10.setText("/ mmHg");
        jLabel10.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        FormInput.add(jLabel10);
        jLabel10.setBounds(310, 140, 40, 23);

        jLabel11.setText("Tensi :");
        FormInput.add(jLabel11);
        jLabel11.setBounds(190, 140, 50, 23);

        TTensi.setBackground(new java.awt.Color(245, 250, 240));
        TTensi.setHighlighter(null);
        FormInput.add(TTensi);
        TTensi.setBounds(250, 140, 50, 23);

        scrollPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        TKesan.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        TKesan.setColumns(20);
        TKesan.setRows(5);
        TKesan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TKesanKeyPressed(evt);
            }
        });
        scrollPane1.setViewportView(TKesan);

        FormInput.add(scrollPane1);
        scrollPane1.setBounds(80, 70, 510, 58);

        jLabel13.setText("Dokter :");
        FormInput.add(jLabel13);
        jLabel13.setBounds(10, 40, 60, 23);

        TKdDokter.setBackground(new java.awt.Color(245, 250, 240));
        TKdDokter.setHighlighter(null);
        FormInput.add(TKdDokter);
        TKdDokter.setBounds(80, 40, 70, 23);

        TNamaDokter.setBackground(new java.awt.Color(245, 250, 240));
        TNamaDokter.setHighlighter(null);
        FormInput.add(TNamaDokter);
        TNamaDokter.setBounds(160, 40, 270, 23);

        jLabel14.setText("/ cm");
        jLabel14.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        FormInput.add(jLabel14);
        jLabel14.setBounds(140, 140, 20, 23);

        jPanel1.add(FormInput, java.awt.BorderLayout.CENTER);

        internalFrame1.add(jPanel1, java.awt.BorderLayout.PAGE_START);

        jPanel2.setLayout(new java.awt.BorderLayout());

        panelGlass6.setPreferredSize(new java.awt.Dimension(55, 55));
        panelGlass6.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        BtnSimpan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/save-16x16.png"))); // NOI18N
        BtnSimpan.setMnemonic('S');
        BtnSimpan.setText("Simpan");
        BtnSimpan.setToolTipText("Alt+S");
        BtnSimpan.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSimpanActionPerformed(evt);
            }
        });
        BtnSimpan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnSimpanKeyPressed(evt);
            }
        });
        panelGlass6.add(BtnSimpan);

        BtnBatal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Cancel-2-16x16.png"))); // NOI18N
        BtnBatal.setMnemonic('B');
        BtnBatal.setText("Baru");
        BtnBatal.setToolTipText("Alt+B");
        BtnBatal.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnBatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnBatalActionPerformed(evt);
            }
        });
        BtnBatal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnBatalKeyPressed(evt);
            }
        });
        panelGlass6.add(BtnBatal);

        BtnEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/inventaris.png"))); // NOI18N
        BtnEdit.setMnemonic('G');
        BtnEdit.setText("Ganti");
        BtnEdit.setToolTipText("Alt+G");
        BtnEdit.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnEditActionPerformed(evt);
            }
        });
        BtnEdit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnEditKeyPressed(evt);
            }
        });
        panelGlass6.add(BtnEdit);

        BtnHapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/stop_f2.png"))); // NOI18N
        BtnHapus.setMnemonic('H');
        BtnHapus.setText("Hapus");
        BtnHapus.setToolTipText("Alt+H");
        BtnHapus.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnHapusActionPerformed(evt);
            }
        });
        BtnHapus.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnHapusKeyPressed(evt);
            }
        });
        panelGlass6.add(BtnHapus);

        BtnPrint.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        BtnPrint.setMnemonic('T');
        BtnPrint.setText("Cetak");
        BtnPrint.setToolTipText("Alt+T");
        BtnPrint.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPrintActionPerformed(evt);
            }
        });
        BtnPrint.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnPrintKeyPressed(evt);
            }
        });
        panelGlass6.add(BtnPrint);

        BtnAll.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Search-16x16.png"))); // NOI18N
        BtnAll.setMnemonic('M');
        BtnAll.setText("Semua");
        BtnAll.setToolTipText("Alt+M");
        BtnAll.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAllActionPerformed(evt);
            }
        });
        BtnAll.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnAllKeyPressed(evt);
            }
        });
        panelGlass6.add(BtnAll);

        jLabel12.setText("Record :");
        jLabel12.setPreferredSize(new java.awt.Dimension(70, 30));
        panelGlass6.add(jLabel12);

        LCount.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LCount.setText("0");
        LCount.setPreferredSize(new java.awt.Dimension(72, 30));
        panelGlass6.add(LCount);

        BtnKeluar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/exit.png"))); // NOI18N
        BtnKeluar.setMnemonic('K');
        BtnKeluar.setText("Keluar");
        BtnKeluar.setToolTipText("Alt+K");
        BtnKeluar.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnKeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnKeluarActionPerformed(evt);
            }
        });
        BtnKeluar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnKeluarKeyPressed(evt);
            }
        });
        panelGlass6.add(BtnKeluar);

        jPanel2.add(panelGlass6, java.awt.BorderLayout.PAGE_END);

        panelGlass7.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass7.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        jLabel15.setText("Periode :");
        jLabel15.setPreferredSize(new java.awt.Dimension(60, 23));
        panelGlass7.add(jLabel15);

        DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "14-12-2019" }));
        DTPCari1.setDisplayFormat("dd-MM-yyyy");
        DTPCari1.setOpaque(false);
        DTPCari1.setPreferredSize(new java.awt.Dimension(133, 23));
        panelGlass7.add(DTPCari1);

        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("s.d");
        jLabel17.setPreferredSize(new java.awt.Dimension(24, 23));
        panelGlass7.add(jLabel17);

        DTPCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "14-12-2019" }));
        DTPCari2.setDisplayFormat("dd-MM-yyyy");
        DTPCari2.setOpaque(false);
        DTPCari2.setPreferredSize(new java.awt.Dimension(133, 23));
        panelGlass7.add(DTPCari2);

        jLabel16.setText("Key Word :");
        jLabel16.setPreferredSize(new java.awt.Dimension(158, 23));
        panelGlass7.add(jLabel16);

        TCari.setPreferredSize(new java.awt.Dimension(300, 23));
        TCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCariKeyPressed(evt);
            }
        });
        panelGlass7.add(TCari);

        BtnCari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari.setMnemonic('7');
        BtnCari.setToolTipText("Alt+7");
        BtnCari.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCariActionPerformed(evt);
            }
        });
        BtnCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnCariKeyPressed(evt);
            }
        });
        panelGlass7.add(BtnCari);

        jPanel2.add(panelGlass7, java.awt.BorderLayout.CENTER);

        internalFrame1.add(jPanel2, java.awt.BorderLayout.PAGE_END);

        Scroll.setOpaque(true);
        Scroll.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ScrollMouseClicked(evt);
            }
        });

        tbDataSuratSehat.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbDataSuratSehat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbDataSuratSehatMouseClicked(evt);
            }
        });
        tbDataSuratSehat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbDataSuratSehatKeyPressed(evt);
            }
        });
        Scroll.setViewportView(tbDataSuratSehat);

        internalFrame1.add(Scroll, java.awt.BorderLayout.CENTER);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void DTPTglSuratItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_DTPTglSuratItemStateChanged
       
    }//GEN-LAST:event_DTPTglSuratItemStateChanged

    private void DTPTglSuratKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DTPTglSuratKeyPressed
        
    }//GEN-LAST:event_DTPTglSuratKeyPressed

    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
    
   /*     try {
            //String no_tlp=TNoHp.getText();
            //String no_rm=TNoRM.getText();
            
            String str;
            str = "update pasien set no_tlp = '"+no_tlp+"' where no_rkm_medis ='"+no_rm+"'";
            PreparedStatement pst = koneksiDB.condb().prepareStatement(str);
            pst.execute();
        } catch (Exception e) {
            System.out.println(e);
        } */
    }//GEN-LAST:event_BtnSimpanActionPerformed

    private void BtnSimpanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSimpanKeyPressed
        
    }//GEN-LAST:event_BtnSimpanKeyPressed

    private void BtnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBatalActionPerformed
      
    }//GEN-LAST:event_BtnBatalActionPerformed

    private void BtnBatalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnBatalKeyPressed
       
    }//GEN-LAST:event_BtnBatalKeyPressed

    private void BtnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEditActionPerformed
        
    }//GEN-LAST:event_BtnEditActionPerformed

    private void BtnEditKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnEditKeyPressed
       
    }//GEN-LAST:event_BtnEditKeyPressed

    private void BtnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusActionPerformed

    }//GEN-LAST:event_BtnHapusActionPerformed

    private void BtnHapusKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnHapusKeyPressed
       
    }//GEN-LAST:event_BtnHapusKeyPressed

    private void BtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrintActionPerformed
        
    }//GEN-LAST:event_BtnPrintActionPerformed

    private void BtnPrintKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnPrintKeyPressed
       
    }//GEN-LAST:event_BtnPrintKeyPressed

    private void BtnAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAllActionPerformed
        
    }//GEN-LAST:event_BtnAllActionPerformed

    private void BtnAllKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAllKeyPressed
       
    }//GEN-LAST:event_BtnAllKeyPressed

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        dispose();
    }//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
        
    }//GEN-LAST:event_BtnKeluarKeyPressed

    private void TKesanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TKesanKeyPressed
        
    }//GEN-LAST:event_TKesanKeyPressed

    private void TCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCariKeyPressed
        
    }//GEN-LAST:event_TCariKeyPressed

    private void BtnCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCariActionPerformed
     tampil();
    }//GEN-LAST:event_BtnCariActionPerformed

    private void BtnCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCariKeyPressed
     
    }//GEN-LAST:event_BtnCariKeyPressed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        tampil();
        BtnEdit.setEnabled(false);
        BtnHapus.setEnabled(false);
        BtnSimpan.setEnabled(true);
        BtnPrint.setEnabled(false);
    }//GEN-LAST:event_formWindowActivated

    private void tbDataSuratSehatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbDataSuratSehatKeyPressed
        /* if(tabMode.getRowCount()!=0){
            if(evt.getKeyCode()==KeyEvent.VK_SPACE){
                if((kolom==1)||(kolom==2)||(kolom==0)){
                    catatan.setNoRm(tbBangsal.getValueAt(tbBangsal.getSelectedRow(),0).toString());
                    catatan.setSize(720,330);
                    catatan.setLocationRelativeTo(internalFrame1);
                    catatan.setVisible(true);
                }
            }
        } */
        
        
    }//GEN-LAST:event_tbDataSuratSehatKeyPressed

    private void tbDataSuratSehatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbDataSuratSehatMouseClicked
        ambil_data();
        BtnEdit.setEnabled(true);
        BtnHapus.setEnabled(true);
        BtnSimpan.setEnabled(false);
        BtnPrint.setEnabled(true);
        /*   TNoRawat.setText(tbDataSuratSehat.getValueAt(tbDataSuratSehat.getSelectedRow(),1).toString());
        TTinggiBadan.setText(tbDataSuratSehat.getValueAt(tbDataSuratSehat.getSelectedRow(),2).toString());
        TNamaPasien1.setText(tbDataSuratSehat.getValueAt(tbDataSuratSehat.getSelectedRow(),3).toString());
        TKdDokter.setText(tbDataSuratSehat.getValueAt(tbDataSuratSehat.getSelectedRow(),4).toString());
        TNamaDokter.setText(tbDataSuratSehat.getValueAt(tbDataSuratSehat.getSelectedRow(),5).toString());
        TKesan.setText(tbDataSuratSehat.getValueAt(tbDataSuratSehat.getSelectedRow(),6).toString());
        Valid.SetTgl(DTPTglSurat,tbDataSuratSehat.getValueAt(tbDataSuratSehat.getSelectedRow(),7).toString());
        TTinggiBadan.setText(tbDataSuratSehat.getValueAt(tbDataSuratSehat.getSelectedRow(),8).toString());
        TBeratBadan.setText(tbDataSuratSehat.getValueAt(tbDataSuratSehat.getSelectedRow(),9).toString());
        TTensi.setText(tbDataSuratSehat.getValueAt(tbDataSuratSehat.getSelectedRow(),10).toString());   
        */
    }//GEN-LAST:event_tbDataSuratSehatMouseClicked

    private void ScrollMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ScrollMouseClicked
        //TNoRawat.setText(tbDataSuratSehat.getSelectedRow(),1);
        //TNoRawat.setText(tbDataSuratSehat.getValueAt(tbDataSuratSehat.getSelectedRow(),1).toString());
        //TNoRM.setText(tbDataSuratSehat.getValueAt(tbDataSuratSehat.getSelectedRow(),2).toString());
        //TNamaPasien1.setText(tbDataSuratSehat.getValueAt(tbDataSuratSehat.getSelectedRow(),3).toString());
            
        
    }//GEN-LAST:event_ScrollMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(DlgSuratSehat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DlgSuratSehat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DlgSuratSehat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DlgSuratSehat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                DlgSuratSehat dialog = new DlgSuratSehat(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private widget.Button BtnAll;
    private widget.Button BtnBatal;
    public widget.Button BtnCari;
    private widget.Button BtnEdit;
    private widget.Button BtnHapus;
    private widget.Button BtnKeluar;
    private widget.Button BtnPrint;
    private widget.Button BtnSimpan;
    private widget.Tanggal DTPCari1;
    private widget.Tanggal DTPCari2;
    private widget.Tanggal DTPTglSurat;
    private widget.PanelBiasa FormInput;
    private widget.Label LCount;
    private widget.ScrollPane Scroll;
    private widget.TextBox TBeratBadan;
    private widget.TextBox TCari;
    private widget.TextBox TKdDokter;
    private widget.TextArea TKesan;
    private widget.TextBox TNamaDokter;
    private widget.TextBox TNamaPasien1;
    private widget.TextBox TNoRM1;
    private widget.TextBox TNoRawat;
    private widget.TextBox TTensi;
    private widget.TextBox TTinggiBadan;
    private widget.InternalFrame internalFrame1;
    private widget.Label jLabel10;
    private widget.Label jLabel11;
    private widget.Label jLabel12;
    private widget.Label jLabel13;
    private widget.Label jLabel14;
    private widget.Label jLabel15;
    private widget.Label jLabel16;
    private widget.Label jLabel17;
    private widget.Label jLabel4;
    private widget.Label jLabel5;
    private widget.Label jLabel6;
    private widget.Label jLabel7;
    private widget.Label jLabel8;
    private widget.Label jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private widget.panelisi panelGlass6;
    private widget.panelisi panelGlass7;
    private widget.ScrollPane scrollPane1;
    private widget.Table tbDataSuratSehat;
    // End of variables declaration//GEN-END:variables

private void tampil() {
        Valid.tabelKosong(tabMode);
        try {
            PreparedStatement ps = koneksi_john.prepareStatement(
            "SELECT a.no_surat, a.no_rawat, b.no_rkm_medis,c.nm_pasien,d.kd_dokter, d.nm_dokter,a.keperluan, a.tgl_surat, "+
            "a.tinggi_badan, a.berat_badan, a.tensi FROM surat_sehat as a,reg_periksa as b, "+
            "pasien as c, dokter as d WHERE a.no_rawat = b.no_rawat AND	b.no_rkm_medis = c.no_rkm_medis "+
            "AND b.kd_dokter = d.kd_dokter " +
            "AND a.tgl_surat between ? AND ? order by a.no_surat DESC" );//OR "+ 
            //"a.tgl_surat between ? AND ? AND c.nm_pasien like ? OR "+
            //"a.tgl_surat between ? AND ? AND b.no_rkm_medis like ? ");
            try {
                ps.setString(1,Valid.SetTgl(DTPCari1.getSelectedItem()+""));
                ps.setString(2,Valid.SetTgl(DTPCari2.getSelectedItem()+""));
              //  ps.setString(3,Valid.SetTgl(DTPCari1.getSelectedItem()+""));
              //  ps.setString(4,Valid.SetTgl(DTPCari2.getSelectedItem()+""));
              //  ps.setString(5,"%"+TCari.getText()+"%");
              //  ps.setString(6,Valid.SetTgl(DTPCari1.getSelectedItem()+""));
              //  ps.setString(7,Valid.SetTgl(DTPCari2.getSelectedItem()+""));
              //  ps.setString(8,"%"+TCari.getText()+"%");
               
                rs = ps.executeQuery();
                while(rs.next()){
                    tabMode.addRow(new String[]{
                        rs.getString("no_surat"),rs.getString("no_rawat"),rs.getString("no_rkm_medis"),
                        rs.getString("nm_pasien"),rs.getString("kd_dokter"),rs.getString("nm_dokter"),rs.getString("keperluan"),
                        rs.getString("tgl_surat"),rs.getString("tinggi_badan"),rs.getString("berat_badan"),
                        rs.getString("tensi")
                    });
                   /* ps2=koneksi.prepareStatement(
                            "select permintaan_pemeriksaan_radiologi.kd_jenis_prw,jns_perawatan_radiologi.nm_perawatan "+
                            "from permintaan_pemeriksaan_radiologi inner join jns_perawatan_radiologi on "+
                            "permintaan_pemeriksaan_radiologi.kd_jenis_prw=jns_perawatan_radiologi.kd_jenis_prw "+
                            "where permintaan_pemeriksaan_radiologi.noorder=?");
                    try {
                        ps2.setString(1,rs.getString("noorder"));
                        rs2=ps2.executeQuery();
                        while(rs2.next()){
                            tabMode.addRow(new Object[]{
                                "","",rs2.getString("nm_perawatan"),"","","","","","","","",""
                            });
                        }
                    } catch (Exception e) {
                        System.out.println("Notif 2 : "+e);
                    } finally{
                        if(rs2!=null){
                            rs2.close();
                        }
                        if(ps2!=null){
                            ps2.close();
                        }
                    } */
                }
                rs.last();
                LCount.setText(""+rs.getRow());
            } catch (Exception e) {
                System.out.println("Notif : "+e);
            } finally{
                if(rs!=null){
                    rs.close();
                }
                if(ps!=null){
                    ps.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notif : "+e);
        }        
    }    
    
public void setPasien(String norm, String norawat, String nama, String nama_dokter, String kd_dokter) {
        //String norawat = null;
        TNoRawat.setText(norawat);
        TNoRM1.setText(norm);
        TNamaPasien1.setText(nama);
        TKdDokter.setText(kd_dokter);
        TNamaDokter.setText(nama_dokter);
        
  }
private void ambil_data(){
        TNoRawat.setText(tbDataSuratSehat.getValueAt(tbDataSuratSehat.getSelectedRow(),1).toString());
        TTinggiBadan.setText(tbDataSuratSehat.getValueAt(tbDataSuratSehat.getSelectedRow(),2).toString());
        TNamaPasien1.setText(tbDataSuratSehat.getValueAt(tbDataSuratSehat.getSelectedRow(),3).toString());
        TKdDokter.setText(tbDataSuratSehat.getValueAt(tbDataSuratSehat.getSelectedRow(),4).toString());
        TNamaDokter.setText(tbDataSuratSehat.getValueAt(tbDataSuratSehat.getSelectedRow(),5).toString());
        TKesan.setText(tbDataSuratSehat.getValueAt(tbDataSuratSehat.getSelectedRow(),6).toString());
        Valid.SetTgl(DTPTglSurat,tbDataSuratSehat.getValueAt(tbDataSuratSehat.getSelectedRow(),7).toString());
        TTinggiBadan.setText(tbDataSuratSehat.getValueAt(tbDataSuratSehat.getSelectedRow(),8).toString());
        TBeratBadan.setText(tbDataSuratSehat.getValueAt(tbDataSuratSehat.getSelectedRow(),9).toString());
        TTensi.setText(tbDataSuratSehat.getValueAt(tbDataSuratSehat.getSelectedRow(),10).toString());  
}

}

