/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * DlgReg.java
 *
 * Created on Jun 8, 2010, choose Tools | Templates
 * and open the template in10:28:56 PM
 */

package simrskhanza;
import permintaan.DlgBookingOperasi;
import kepegawaian.DlgCariDokter;
import kepegawaian.DlgCariDokter2;
import inventory.DlgResepObat;
import inventory.DlgPemberianObat;
import bridging.BPJSDataSEP;
import bridging.DlgSKDPBPJS;
import bridging.InhealthDataSJP;
import bridging.PCareDataPendaftaran;
import bridging.SisruteRujukanKeluar;
import laporan.DlgDiagnosaPenyakit;
import laporan.DlgFrekuensiPenyakitRalan;
import keuangan.DlgBilingRalan;
import fungsi.WarnaTable;
import fungsi.batasInput;
import surat.DlgSuratSehat;
import grafikanalisa.grafikperiksaperagama;
import grafikanalisa.grafikperiksaperbulan;
import grafikanalisa.grafikperiksaperdokter;
import grafikanalisa.grafikperiksaperhari;
import grafikanalisa.grafikperiksaperjk;
import grafikanalisa.grafikperiksaperpekerjaan;
import grafikanalisa.grafikperiksaperpoli;
import grafikanalisa.grafikperiksapertahun;
import grafikanalisa.grafiksql;
import fungsi.koneksiDB;
import fungsi.sekuel;
import fungsi.validasi;
import fungsi.var;
import inventory.DlgPeresepanDokter;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.event.DocumentEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import keuangan.DlgBilingParsialRalan;
import keuangan.DlgLhtPiutang;
import laporan.DlgBerkasRawat;
import laporan.DlgDataInsidenKeselamatan;
import bridging.DlgDataTB;
import org.apache.pdfbox.contentstream.operator.text.SetTextHorizontalScaling;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import permintaan.DlgPermintaanLaboratorium;
import permintaan.DlgPermintaanRadiologi;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
/**
 *
 * @author dosen
 */
public final class DlgReg extends javax.swing.JDialog {
    private final DefaultTableModel tabMode,tabMode2,tabMode3;
    private sekuel Sequel=new sekuel();
    private validasi Valid=new validasi();
    private Connection koneksi=koneksiDB.condb();
    public  DlgPasien pasien=new DlgPasien(null,false);
    public  DlgCariDokter dokter=new DlgCariDokter(null,false);
    public  DlgCariDokter2 dokter2=new DlgCariDokter2(null,false);
    private DlgCariPoli poli=new DlgCariPoli(null,false);
    private DlgCariPoli2 poli2=new DlgCariPoli2(null,false);
    private DlgRujukanPoliInternal dlgrjk=new DlgRujukanPoliInternal(null,false);
    public  DlgRujukMasuk rujukmasuk=new DlgRujukMasuk(null,false);
    private DlgCatatan catatan=new DlgCatatan(null,false);
    private PreparedStatement ps,ps2,ps3,pscaripiutang;
    private Properties prop = new Properties();
    private ResultSet rs;
    private int pilihan=0,i=0,kuota=0,jmlparsial=0;
    private Date cal=new Date();
    private boolean ceksukses=false;
    private String nosisrute="",aktifkanparsial="no",BASENOREG="",
            URUTNOREG="",status="Baru",order="reg_periksa.tgl_registrasi,reg_periksa.jam_reg desc",alamatperujuk="-",aktifjadwal="",IPPRINTERTRACER="",umur="0",sttsumur="Th",
            validasiregistrasi=Sequel.cariIsi("select wajib_closing_kasir from set_validasi_registrasi"),
            validasicatatan=Sequel.cariIsi("select tampilkan_catatan from set_validasi_catatan");
    private SimpleDateFormat dateformat = new SimpleDateFormat("yyyy/MM/dd");
    private char ESC = 27;
    // ganti kertas
    private char[] FORM_FEED = {12};
    // reset setting
    private char[] RESET = {ESC,'@'};
    // huruf tebal diaktifkan
    private char[] BOLD_ON = {ESC,'E'};
    // huruf tebal dimatikan
    private char[] BOLD_OFF = {ESC,'F'};
    // huruf miring diaktifkan
    private char[] ITALIC_ON = {ESC,'4'};
    // huruf miring dimatikan
    private char[] ITALIC_OFF = {ESC,'5'};
    // mode draft diaktifkan
    private char[] MODE_DRAFT = {ESC,'x',0};
    private char[] MODE_NLQ = {ESC,'x',1};
    // font Roman (halaman 47)
    private char[] FONT_ROMAN = {ESC,'k',0};
    // font Sans serif
    private char[] FONT_SANS_SERIF = {ESC,'k',1};
    // font size (halaman 49)
    private char[] SIZE_5_CPI = {ESC,'W','1',ESC,'P'};
    private char[] SIZE_6_CPI = {ESC,'W','1',ESC,'M'};
    private char[] SIZE_10_CPI = {ESC,'P'};
    private char[] SIZE_12_CPI = {ESC,'M'};
    //font height
    private char[] HEIGHT_NORMAL = {ESC,'w', '0'};
    private char[] HEIGHT_DOUBLE = {ESC,'w', '1'};
    // double strike (satu dot dicetak 2 kali)
    private char[] DOUBLE_STRIKE_ON = {ESC,'G'};
    private char[] DOUBLE_STRIKE_OFF = {ESC,'H'};
    // http://www.berklix.com/~jhs/standards/escapes.epson
    // condensed (huruf kurus)
    private char[] CONDENSED_ON = {15};
    private char[] CONDENSED_OFF = {18};
    // condensed (huruf gemuk)
    private char[] ENLARGED_ON = {(char) 14};
    private char[] ENLARGED_OFF = {(char) 20};
    // line spacing
    private char[] SPACING_9_72 = {ESC, '0'};
    private char[] SPACING_7_72 = {ESC, '1'};
    private char[] SPACING_12_72 = {ESC, '2'};
    // set unit for margin setting
    private char[] UNIT_1_360 = {ESC,40, 'U', '1', '0'};
    // move vertical print position
    private char[] VERTICAL_PRINT_POSITION = {ESC, 'J', '1'};
    

    /** Creates new form DlgReg
     * @param parent
     * @param modal */
    public DlgReg(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        this.setLocation(8,1);
        setSize(885,674);

        tabMode=new DefaultTableModel(null,new Object[]{
            "P","No.Reg","No.Rawat","Tanggal","Jam","Kd.Dokter","Dokter Dituju","Nomer RM",
            "Pasien","J.K.","Umur","Poliklinik","Jenis Bayar","Penanggung Jawab","Alamat P.J.","Hubungan P.J.",
            "Biaya Reg","Status","No.Telp","Stts Rawat","Stts Poli","Kode Poli","Kode PJ"
//            "P","No.Reg","No.Rawat","Tanggal","Jam","Dokter Dituju","Nomer RM",
//            "Pasien","J.K.","Umur","Poliklinik","Jenis Bayar","Status","No.Telp","Stts Rawat","Stts Poli","Kode Poli","Kode PJ"
        }){
             @Override public boolean isCellEditable(int rowIndex, int colIndex){
                boolean a = false;
                if (colIndex==0) {
                    a=true;
                }
                return a;
             }
             Class[] types = new Class[] {
                 java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, 
                 java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, 
                 java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, 
                 java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, 
                 java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, 
                 java.lang.Object.class,java.lang.Object.class,java.lang.Object.class
//                 java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, 
//                 java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, 
//                 java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, 
//                 java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, 
//                 java.lang.Object.class, java.lang.Object.class
             };
             @Override
             public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
             }
        };
        tbPetugas.setModel(tabMode);
        tbPetugas.setRowHeight(25);
        tbPetugas.setPreferredScrollableViewportSize(new Dimension(800,800));
        tbPetugas.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tbPetugas.getColumnModel().removeColumn(tbPetugas.getColumnModel().getColumn(5));
        tbPetugas.getColumnModel().removeColumn(tbPetugas.getColumnModel().getColumn(12));
        tbPetugas.getColumnModel().removeColumn(tbPetugas.getColumnModel().getColumn(12));
        tbPetugas.getColumnModel().removeColumn(tbPetugas.getColumnModel().getColumn(12));
        for (i = 0; i < 19; i++) {
            TableColumn column = tbPetugas.getColumnModel().getColumn(i);
            if(i==0){
                column.setPreferredWidth(20);
            }else if(i==1){
                column.setPreferredWidth(50);
            }else if(i==2){
                column.setPreferredWidth(125);
            }else if(i==3){
                column.setPreferredWidth(70);
            }else if(i==4){
                column.setPreferredWidth(60);   
//            }else if(i==5){
//                column.setPreferredWidth(70);
            }else if(i==5){
                column.setPreferredWidth(200);
            }else if(i==6){
                column.setPreferredWidth(70);
            }else if(i==7){
                column.setPreferredWidth(200);
            }else if(i==8){
                column.setPreferredWidth(30);
            }else if(i==9){
                column.setPreferredWidth(50);
            }else if(i==10){
                column.setPreferredWidth(140);
            }else if(i==11){
                column.setPreferredWidth(50);
//            }else if(i==12){
//                column.setPreferredWidth(200);
//            }else if(i==13){
//                column.setPreferredWidth(140);
//            }else if(i==13){
//                column.setPreferredWidth(90);
            }else if(i==12){
                column.setPreferredWidth(50);
            }else if(i==13){
                column.setPreferredWidth(50);
            }else if(i==14){
                column.setPreferredWidth(90);
            }else if(i==15){
                column.setPreferredWidth(70);
            }else if(i==16){
                column.setPreferredWidth(60);
            }else if(i==17){
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }else if(i==18){
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }
        }
        tbPetugas.setDefaultRenderer(Object.class, new WarnaTable());
        //tab mode 3
        tabMode3 = new DefaultTableModel(null,new Object[]{
            "P","No.Reg","No.Rawat","Tanggal","Jam","Kd.Dokter","Dokter Dituju","Nomer RM",
            "Pasien","J.K.","Umur","Poliklinik","Jenis Bayar","Penanggung Jawab","Alamat P.J.","Hubungan P.J.",
            "Biaya Regristrasi","Status","No.Telp","Stts Rawat","Stts Poli","Kode Poli","Kode PJ"
        }){
            @Override public boolean isCellEditable(int rowIndex, int colIndex){
                boolean a = false;
                if (colIndex==0) {
                    a=true;
                }
                return a;
            }
            Class[] types = new Class[] {
                java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class,java.lang.Object.class,java.lang.Object.class
            };
            @Override
            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        };
        tbPetugas1.setModel(tabMode3);

        tbPetugas1.setPreferredScrollableViewportSize(new Dimension(800,800));
        tbPetugas1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 23; i++) {
            TableColumn column = tbPetugas1.getColumnModel().getColumn(i);
            if(i==0){
                column.setPreferredWidth(20);
            }else if(i==1){
                column.setPreferredWidth(50);
            }else if(i==2){
                column.setPreferredWidth(120);
            }else if(i==3){
                column.setPreferredWidth(70);
            }else if(i==4){
                column.setPreferredWidth(50);   
            }else if(i==5){
                column.setPreferredWidth(70);
            }else if(i==6){
                column.setPreferredWidth(200);
            }else if(i==7){
                column.setPreferredWidth(70);
            }else if(i==8){
                column.setPreferredWidth(200);
            }else if(i==9){
                column.setPreferredWidth(30);
            }else if(i==10){
                column.setPreferredWidth(50);
            }else if(i==11){
                column.setPreferredWidth(140);
            }else if(i==12){
                column.setPreferredWidth(140);
            }else if(i==13){
                column.setPreferredWidth(200);
            }else if(i==14){
                column.setPreferredWidth(140);
            }else if(i==15){
                column.setPreferredWidth(90);
            }else if(i==16){
                column.setPreferredWidth(100);
            }else if(i==17){
                column.setPreferredWidth(80);
            }else if(i==18){
                column.setPreferredWidth(80);
            }else if(i==19){
                column.setPreferredWidth(70);
            }else if(i==20){
                column.setPreferredWidth(60);
            }else if(i==21){
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }else if(i==22){
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }
        }
    
        tbPetugas1.setDefaultRenderer(Object.class, new WarnaTable()); 
        //akhir tab mode 3
        tabMode2=new DefaultTableModel(null,new Object[]{
            "P","No.Rawat","Tanggal","Jam","Kd.Dokter","Dokter Rujukan","Nomer RM",
            "Pasien","J.K.","Umur","Poliklinik Rujukan","Poliklinik Perujuk","Jenis Bayar","Penanggung Jawab",
            "Alamat P.J.","Hubungan P.J.",
            "Status","No.Telp","Stts Rawat","Kode Poli","Kode PJ"
        }){
             @Override public boolean isCellEditable(int rowIndex, int colIndex){
                boolean a = false;
                if (colIndex==0) {
                    a=true;
                }
                return a;
             }
             //
             Class[] types = new Class[] {
                 java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class, 
                 java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, 
                 java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, 
                 java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, 
                 java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, 
                 java.lang.Object.class, java.lang.Object.class
             };
             @Override
             public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
             }
        };
        tbPetugas2.setModel(tabMode2);

        tbPetugas2.setPreferredScrollableViewportSize(new Dimension(800,800));
        tbPetugas2.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 20; i++) {
            TableColumn column = tbPetugas2.getColumnModel().getColumn(i);
            if(i==0){
                column.setPreferredWidth(20);
            }else if(i==1){
                column.setPreferredWidth(120);
            }else if(i==2){
                column.setPreferredWidth(70);
            }else if(i==3){
                column.setPreferredWidth(50);   
            }else if(i==4){
                column.setPreferredWidth(70);
            }else if(i==5){
                column.setPreferredWidth(200);
            }else if(i==6){
                column.setPreferredWidth(70);
            }else if(i==7){
                column.setPreferredWidth(200);
            }else if(i==8){
                column.setPreferredWidth(30);
            }else if(i==9){
                column.setPreferredWidth(50);
            }else if(i==10){
                column.setPreferredWidth(140);
            }else if(i==11){
                column.setPreferredWidth(140);
            }else if(i==12){
                column.setPreferredWidth(200);
            }else if(i==13){
                column.setPreferredWidth(140);
            }else if(i==14){
                column.setPreferredWidth(140);
            }else if(i==15){
                column.setPreferredWidth(140);
            }else if(i==16){
                column.setPreferredWidth(90);
            }else if(i==17){
                column.setPreferredWidth(80);
            }else if(i==18){
                column.setPreferredWidth(70);
            }else if(i==19){
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }else if(i==20){
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }
        }
        tbPetugas2.setDefaultRenderer(Object.class, new WarnaTable());
        
        TNoReg.setDocument(new batasInput((byte)8).getKata(TNoReg));
        TNoRw.setDocument(new batasInput((byte)17).getKata(TNoRw));
        TNoRM.setDocument(new batasInput((byte)15).getKata(TNoRM));
        AsalRujukan.setDocument(new batasInput((byte)60).getKata(AsalRujukan));
        kddokter.setDocument(new batasInput((byte)20).getKata(kddokter));
        kdpnj.setDocument(new batasInput((byte)3).getKata(kdpnj));
        TPngJwb.setDocument(new batasInput((byte)30).getKata(TPngJwb));
        kdpoli.setDocument(new batasInput((byte)5).getKata(kdpoli));
        THbngn.setDocument(new batasInput((byte)20).getKata(THbngn));
        TAlmt.setDocument(new batasInput((byte)60).getKata(TAlmt));
        TBiaya.setDocument(new batasInput((byte)13).getOnlyAngka(TBiaya));
        TCari.setDocument(new batasInput((byte)100).getKata(TCari));
        CrPoli.setDocument(new batasInput((byte)100).getKata(CrPoli));
        CrDokter.setDocument(new batasInput((byte)100).getKata(CrDokter));
        if(koneksiDB.cariCepat().equals("aktif")){
            TCari.getDocument().addDocumentListener(new javax.swing.event.DocumentListener(){
                @Override
                public void insertUpdate(DocumentEvent e) {
                    if(TCari.getText().length()>2){
                        tampil();
                    }
                }
                @Override
                public void removeUpdate(DocumentEvent e) {
                    if(TCari.getText().length()>2){
                        tampil();
                    }
                }
                @Override
                public void changedUpdate(DocumentEvent e) {
                    if(TCari.getText().length()>2){
                        tampil();
                    }
                }
            });
        }  
        jam();                

        pasien.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                if(var.getform().equals("DlgReg")){
                    if(pasien.getTable().getSelectedRow()!= -1){  
                        TNoRM.setText(pasien.getTable().getValueAt(pasien.getTable().getSelectedRow(),1).toString());
                        isPas();
                        isNumber();                                                    
                    }  
                    if(pasien.getTable2().getSelectedRow()!= -1){  
                        TNoRM.setText(pasien.getTable2().getValueAt(pasien.getTable2().getSelectedRow(),1).toString());
                        isPas();
                        isNumber();                                                    
                    }  
                    if(pasien.getTable3().getSelectedRow()!= -1){  
                        TNoRM.setText(pasien.getTable3().getValueAt(pasien.getTable3().getSelectedRow(),1).toString());
                        isPas();
                        isNumber();                                                    
                    }  
                    TNoRM.requestFocus();
                }
            }
            @Override
            public void windowIconified(WindowEvent e) {}
            @Override
            public void windowDeiconified(WindowEvent e) {}
            @Override
            public void windowActivated(WindowEvent e) {}
            @Override
            public void windowDeactivated(WindowEvent e) {}
        });
        
        pasien.getTable().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}
            @Override
            public void keyPressed(KeyEvent e) {
                if(var.getform().equals("DlgReg")){
                    if(e.getKeyCode()==KeyEvent.VK_SPACE){
                        pasien.dispose();
                    }
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {}
        });    
        
        pasien.getTable2().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}
            @Override
            public void keyPressed(KeyEvent e) {
                if(var.getform().equals("DlgReg")){
                    if(e.getKeyCode()==KeyEvent.VK_SPACE){
                        pasien.dispose();
                    }
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {}
        }); 
        
        pasien.getTable3().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}
            @Override
            public void keyPressed(KeyEvent e) {
                if(var.getform().equals("DlgReg")){
                    if(e.getKeyCode()==KeyEvent.VK_SPACE){
                        pasien.dispose();
                    }
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {}
        }); 
        
        dokter.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {;}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                if(var.getform().equals("DlgReg")){
                    if(dokter.getTable().getSelectedRow()!= -1){                    
                        if(pilihan==1){
                            kddokter.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(),0).toString());
                            TDokter.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(),1).toString());
                            isNumber();
                            kddokter.requestFocus();
                        }else if(pilihan==2){
                            CrDokter.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(),1).toString());
                            CrDokter.requestFocus();
                            tampil();
                        }else if(pilihan==3){
                            CrDokter3.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(),1).toString());
                            CrDokter3.requestFocus();
                        }
                    }                
                }
            }
            @Override
            public void windowIconified(WindowEvent e) {}
            @Override
            public void windowDeiconified(WindowEvent e) {}
            @Override
            public void windowActivated(WindowEvent e) {}
            @Override
            public void windowDeactivated(WindowEvent e) {}
        });
                
        dokter2.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {;}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                if(var.getform().equals("DlgReg")){
                    if(dokter2.getTable().getSelectedRow()!= -1){                    
                        if(pilihan==1){
                            kddokter.setText(dokter2.getTable().getValueAt(dokter2.getTable().getSelectedRow(),0).toString());
                            TDokter.setText(dokter2.getTable().getValueAt(dokter2.getTable().getSelectedRow(),1).toString());
                            if(aktifjadwal.equals("aktif")){
                                kuota=Integer.parseInt(dokter2.getTable().getValueAt(dokter2.getTable().getSelectedRow(),13).toString());
                            }
                            isNumber();
                            kddokter.requestFocus();
                        }else if(pilihan==2){
                            CrDokter.setText(dokter2.getTable().getValueAt(dokter2.getTable().getSelectedRow(),1).toString());
                            CrDokter.requestFocus();
                            tampil();
                        }else if(pilihan==3){
                            CrDokter3.setText(dokter2.getTable().getValueAt(dokter2.getTable().getSelectedRow(),1).toString());
                            CrDokter3.requestFocus();
                        }
                    }                
                }
            }
            @Override
            public void windowIconified(WindowEvent e) {}
            @Override
            public void windowDeiconified(WindowEvent e) {}
            @Override
            public void windowActivated(WindowEvent e) {}
            @Override
            public void windowDeactivated(WindowEvent e) {}
        });
        
        poli.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                if(var.getform().equals("DlgReg")){
                    if(poli.getTable().getSelectedRow()!= -1){                    
                        if(pilihan==1){
                            kdpoli.setText(poli.getTable().getValueAt(poli.getTable().getSelectedRow(),0).toString());
                            TPoli.setText(poli.getTable().getValueAt(poli.getTable().getSelectedRow(),1).toString());
                            switch (TStatus.getText()) {
                                case "Baru":
                                    TBiaya.setText(poli.getTable().getValueAt(poli.getTable().getSelectedRow(),2).toString());
                                    break;
                                case "Lama":
                                    TBiaya.setText(poli.getTable().getValueAt(poli.getTable().getSelectedRow(),3).toString());
                                    break;
                                default :
                                    TBiaya.setText(poli.getTable().getValueAt(poli.getTable().getSelectedRow(),2).toString());
                                    break;
                            }
                            isNumber();                            
                            kdpoli.requestFocus();
                        }else if(pilihan==2){
                            CrPoli.setText(poli.getTable().getValueAt(poli.getTable().getSelectedRow(),1).toString());
                            CrPoli.requestFocus();
                            tampil();
                        }
                    }                
                }
            }
            @Override
            public void windowIconified(WindowEvent e) {}
            @Override
            public void windowDeiconified(WindowEvent e) {}
            @Override
            public void windowActivated(WindowEvent e) {}
            @Override
            public void windowDeactivated(WindowEvent e) {}
        });        
                
        poli2.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                if(var.getform().equals("DlgReg")){
                    if(poli2.getTable().getSelectedRow()!= -1){                    
                        if(pilihan==1){
                            kdpoli.setText(poli2.getTable().getValueAt(poli2.getTable().getSelectedRow(),0).toString());
                            TPoli.setText(poli2.getTable().getValueAt(poli2.getTable().getSelectedRow(),1).toString());
                            switch (TStatus.getText()) {
                                case "Baru":
                                    TBiaya.setText(poli2.getTable().getValueAt(poli2.getTable().getSelectedRow(),2).toString());
                                    break;
                                case "Lama":
                                    TBiaya.setText(poli2.getTable().getValueAt(poli2.getTable().getSelectedRow(),3).toString());
                                    break;
                                default :
                                    TBiaya.setText(poli2.getTable().getValueAt(poli2.getTable().getSelectedRow(),2).toString());
                                    break;
                            }
                            isNumber();                            
                            kdpoli.requestFocus();
                        }else if(pilihan==2){
                            CrPoli.setText(poli2.getTable().getValueAt(poli2.getTable().getSelectedRow(),1).toString());
                            CrPoli.requestFocus();
                            tampil();
                        }
                    }                
                }
            }
            @Override
            public void windowIconified(WindowEvent e) {}
            @Override
            public void windowDeiconified(WindowEvent e) {}
            @Override
            public void windowActivated(WindowEvent e) {}
            @Override
            public void windowDeactivated(WindowEvent e) {}
        });       
        
        pasien.kab.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                if(var.getform().equals("DlgReg")){
                    if(pasien.kab.getTable().getSelectedRow()!= -1){                   
                        Kabupaten2.setText(pasien.kab.getTable().getValueAt(pasien.kab.getTable().getSelectedRow(),0).toString());
                    }     
                    Kabupaten2.requestFocus();
                }
            }
            @Override
            public void windowIconified(WindowEvent e) {}
            @Override
            public void windowDeiconified(WindowEvent e) {}
            @Override
            public void windowActivated(WindowEvent e) {}
            @Override
            public void windowDeactivated(WindowEvent e) {}
        });
        
        pasien.kec.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                if(var.getform().equals("DlgReg")){
                    if(pasien.kec.getTable().getSelectedRow()!= -1){                   
                        Kecamatan2.setText(pasien.kec.getTable().getValueAt(pasien.kec.getTable().getSelectedRow(),0).toString());
                    }                
                    Kecamatan2.requestFocus();
                }
            }
            @Override
            public void windowIconified(WindowEvent e) {}
            @Override
            public void windowDeiconified(WindowEvent e) {}
            @Override
            public void windowActivated(WindowEvent e) {}
            @Override
            public void windowDeactivated(WindowEvent e) {}
        });
        
        pasien.kel.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                if(var.getform().equals("DlgReg")){
                    if(pasien.kel.getTable().getSelectedRow()!= -1){                   
                        Kelurahan2.setText(pasien.kel.getTable().getValueAt(pasien.kel.getTable().getSelectedRow(),0).toString());
                    }  
                    Kelurahan2.requestFocus();
                }
            }
            @Override
            public void windowIconified(WindowEvent e) {}
            @Override
            public void windowDeiconified(WindowEvent e) {}
            @Override
            public void windowActivated(WindowEvent e) {}
            @Override
            public void windowDeactivated(WindowEvent e) {}
        });
        
        pasien.penjab.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                if(var.getform().equals("DlgReg")){
                    if(pasien.penjab.getTable().getSelectedRow()!= -1){
                        kdpnj.setText(pasien.penjab.getTable().getValueAt(pasien.penjab.getTable().getSelectedRow(),1).toString());
                        nmpnj.setText(pasien.penjab.getTable().getValueAt(pasien.penjab.getTable().getSelectedRow(),2).toString());
                    }    
                    kdpnj.requestFocus();
                }
            }
            @Override
            public void windowIconified(WindowEvent e) {}
            @Override
            public void windowDeiconified(WindowEvent e) {}
            @Override
            public void windowActivated(WindowEvent e) {}
            @Override
            public void windowDeactivated(WindowEvent e) {}
        });
        
        rujukmasuk.WindowPerujuk.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                if(var.getform().equals("DlgReg")){
                    if(rujukmasuk.tbPerujuk.getSelectedRow()!= -1){
                        AsalRujukan.setText(rujukmasuk.tbPerujuk.getValueAt(rujukmasuk.tbPerujuk.getSelectedRow(),0).toString());
                        alamatperujuk=rujukmasuk.tbPerujuk.getValueAt(rujukmasuk.tbPerujuk.getSelectedRow(),1).toString();
                    }    
                    AsalRujukan.requestFocus();
                }
            }
            @Override
            public void windowIconified(WindowEvent e) {}
            @Override
            public void windowDeiconified(WindowEvent e) {}
            @Override
            public void windowActivated(WindowEvent e) {}
            @Override
            public void windowDeactivated(WindowEvent e) {}
        });
        
        pasien.penjab.getTable().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}
            @Override
            public void keyPressed(KeyEvent e) {
                if(var.getform().equals("DlgReg")){
                    if(e.getKeyCode()==KeyEvent.VK_SPACE){
                        pasien.penjab.dispose();
                    }
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {}
        });
        
        DlgCatatan.setSize(595,34);        
                
        try {
            prop.loadFromXML(new FileInputStream("setting/database.xml"));
            aktifjadwal=prop.getProperty("JADWALDOKTERDIREGISTRASI");
            IPPRINTERTRACER=prop.getProperty("IPPRINTERTRACER");
            URUTNOREG=prop.getProperty("URUTNOREG");
            BASENOREG=prop.getProperty("BASENOREG");
            aktifkanparsial=prop.getProperty("AKTIFKANBILLINGPARSIAL");
            try{    
                if(prop.getProperty("MENUTRANSPARAN").equals("yes")){
//                    com.sun.awt.AWTUtilities.setWindowOpacity(DlgCatatan,0.5f);
                }     
            }catch(Exception e){    
            }
        } catch (Exception ex) {
            aktifjadwal="";            
            IPPRINTERTRACER="";
            URUTNOREG="";
            BASENOREG="";
        }
        
        ChkInput.setSelected(false);
        isForm(); 
    }

    
    

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPopupMenu1 = new javax.swing.JPopupMenu();
        MnPermintaan = new javax.swing.JMenu();
        MnJadwalOperasi = new javax.swing.JMenuItem();
        MnSKDPBPJS = new javax.swing.JMenuItem();
        MnPermintaanLab = new javax.swing.JMenuItem();
        MnPermintaanRadiologi = new javax.swing.JMenuItem();
        MnKamarInap = new javax.swing.JMenuItem();
        MnTindakan = new javax.swing.JMenu();
        MnRawatJalan = new javax.swing.JMenuItem();
        MnPeriksaLab = new javax.swing.JMenuItem();
        MnPeriksaRadiologi = new javax.swing.JMenuItem();
        MnOperasi = new javax.swing.JMenuItem();
        MnObat = new javax.swing.JMenu();
        MnPemberianObat = new javax.swing.JMenuItem();
        MnNoResep = new javax.swing.JMenuItem();
        MnResepDOkter = new javax.swing.JMenuItem();
        MnPilihBilling = new javax.swing.JMenu();
        MnBillingParsial = new javax.swing.JMenuItem();
        MnBilling = new javax.swing.JMenuItem();
        jSeparator10 = new javax.swing.JPopupMenu.Separator();
        jMenu1 = new javax.swing.JMenu();
        MnLaporanRekapKunjunganPoli = new javax.swing.JMenuItem();
        MnLaporanRekapKunjunganDokter = new javax.swing.JMenuItem();
        MnLaporanRekapJenisBayar = new javax.swing.JMenuItem();
        MnLaporanRekapRawatDarurat = new javax.swing.JMenuItem();
        MnLaporanRekapKunjunganBulanan = new javax.swing.JMenuItem();
        MnLaporanRekapKunjunganBulananPoli = new javax.swing.JMenuItem();
        MnLaporanRekapPenyakitRalan = new javax.swing.JMenuItem();
        MnLaporanRekapPerujuk = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        ppGrafikPerpoli = new javax.swing.JMenuItem();
        ppGrafikPerpoli1 = new javax.swing.JMenuItem();
        ppGrafikPerpoli2 = new javax.swing.JMenuItem();
        ppGrafikPerdokter = new javax.swing.JMenuItem();
        ppGrafikPerdokter1 = new javax.swing.JMenuItem();
        ppGrafikPerdokter2 = new javax.swing.JMenuItem();
        ppGrafikPerJK = new javax.swing.JMenuItem();
        ppGrafikPerPekerjaan = new javax.swing.JMenuItem();
        ppGrafikPerAgama = new javax.swing.JMenuItem();
        ppGrafikPerTahun = new javax.swing.JMenuItem();
        ppGrafikPerBulan = new javax.swing.JMenuItem();
        ppGrafikPerTanggal = new javax.swing.JMenuItem();
        ppGrafikDemografi = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        MnCetakSuratSehat = new javax.swing.JMenuItem();
        MnCetakSuratSehat1 = new javax.swing.JMenuItem();
        MnCetakBebasNarkoba = new javax.swing.JMenuItem();
        MnCetakSuratSakit = new javax.swing.JMenuItem();
        MnCetakSuratSakit2 = new javax.swing.JMenuItem();
        MnCetakSuratSakit3 = new javax.swing.JMenuItem();
        MnCetakSuratSakit1 = new javax.swing.JMenuItem();
        MnCetakRegister = new javax.swing.JMenuItem();
        MnCetakRegister1 = new javax.swing.JMenuItem();
        MnCetakRegister2 = new javax.swing.JMenuItem();
        MnPersetujuanMedis = new javax.swing.JMenuItem();
        MnBuktiPelayananRalan = new javax.swing.JMenuItem();
        MnLembarCasemix = new javax.swing.JMenuItem();
        MnLembarCasemix1 = new javax.swing.JMenuItem();
        MnLembarCasemix2 = new javax.swing.JMenuItem();
        MnSPBK = new javax.swing.JMenuItem();
        MnSPBK1 = new javax.swing.JMenuItem();
        MnJKRA = new javax.swing.JMenuItem();
        MnLembarRalan = new javax.swing.JMenuItem();
        MnBlangkoResep = new javax.swing.JMenuItem();
        MnDataSuratSehat = new javax.swing.JMenuItem();
        MnCetakSuratSehat2 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        MnCheckList = new javax.swing.JMenuItem();
        MnCheckList1 = new javax.swing.JMenuItem();
        MnCheckList2 = new javax.swing.JMenuItem();
        MnCheckList3 = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        MnCheckList4 = new javax.swing.JMenuItem();
        MnCheckList5 = new javax.swing.JMenuItem();
        MnCheckList6 = new javax.swing.JMenuItem();
        MnCheckList7 = new javax.swing.JMenuItem();
        jMenu6 = new javax.swing.JMenu();
        MnLabelTracker = new javax.swing.JMenuItem();
        MnLabelTracker1 = new javax.swing.JMenuItem();
        MnLabelTracker2 = new javax.swing.JMenuItem();
        MnLabelTracker3 = new javax.swing.JMenuItem();
        MnBarcode = new javax.swing.JMenuItem();
        MnBarcode1 = new javax.swing.JMenuItem();
        MnBarcodeRM9 = new javax.swing.JMenuItem();
        MnGelang1 = new javax.swing.JMenuItem();
        MnGelang2 = new javax.swing.JMenuItem();
        MnGelang3 = new javax.swing.JMenuItem();
        MnGelang4 = new javax.swing.JMenuItem();
        MnGelang5 = new javax.swing.JMenuItem();
        MnGelang6 = new javax.swing.JMenuItem();
        MnGelang7 = new javax.swing.JMenuItem();
        jSeparator11 = new javax.swing.JPopupMenu.Separator();
        MnRujukan = new javax.swing.JMenu();
        MnRujukMasuk = new javax.swing.JMenuItem();
        MnRujuk = new javax.swing.JMenuItem();
        MnPoliInternal = new javax.swing.JMenuItem();
        MnJawabanKonsul = new javax.swing.JMenuItem();
        MnBridging = new javax.swing.JMenu();
        MnSEP = new javax.swing.JMenuItem();
        MnSJP = new javax.swing.JMenuItem();
        MnPCare = new javax.swing.JMenuItem();
        MnRujukSisrute = new javax.swing.JMenuItem();
        MenuInputData = new javax.swing.JMenu();
        MnDiagnosa = new javax.swing.JMenuItem();
        ppCatatanPasien = new javax.swing.JMenuItem();
        ppBerkasDigital = new javax.swing.JMenuItem();
        ppIKP = new javax.swing.JMenuItem();
        MnTeridentifikasiTB = new javax.swing.JMenuItem();
        MnStatus = new javax.swing.JMenu();
        ppBerkas = new javax.swing.JMenuItem();
        MnSudah = new javax.swing.JMenuItem();
        MnBelum = new javax.swing.JMenuItem();
        MnBatal = new javax.swing.JMenuItem();
        MnDirujuk = new javax.swing.JMenuItem();
        MnDIrawat = new javax.swing.JMenuItem();
        MnMeninggal = new javax.swing.JMenuItem();
        MnPulangPaksa = new javax.swing.JMenuItem();
        jMenu7 = new javax.swing.JMenu();
        MnStatusBaru = new javax.swing.JMenuItem();
        MnStatusLama = new javax.swing.JMenuItem();
        MnUrut = new javax.swing.JMenu();
        MnUrutNoRawatDesc = new javax.swing.JMenuItem();
        MnUrutNoRawatAsc = new javax.swing.JMenuItem();
        MnUrutTanggalDesc = new javax.swing.JMenuItem();
        MnUrutTanggalAsc = new javax.swing.JMenuItem();
        MnUrutDokterDesc = new javax.swing.JMenuItem();
        MnUrutDokterAsc = new javax.swing.JMenuItem();
        MnUrutPoliklinikDesc = new javax.swing.JMenuItem();
        MnUrutPoliklinikAsc = new javax.swing.JMenuItem();
        MnUrutPenjabDesc = new javax.swing.JMenuItem();
        MnUrutPenjabAsc = new javax.swing.JMenuItem();
        MnUrutStatusDesc = new javax.swing.JMenuItem();
        MnUrutStatusAsc = new javax.swing.JMenuItem();
        MnUrutRegDesc1 = new javax.swing.JMenuItem();
        MnUrutRegAsc1 = new javax.swing.JMenuItem();
        MnInputHP = new javax.swing.JMenuItem();
        MnInterpretasiEkg = new javax.swing.JMenuItem();
        MnHemodialisa = new javax.swing.JMenuItem();
        ppRiwayat = new javax.swing.JMenuItem();
        MnRiwayatObatRacikan = new javax.swing.JMenuItem();
        MnHapusData = new javax.swing.JMenu();
        MnHapusTagihanOperasi = new javax.swing.JMenuItem();
        MnHapusObatOperasi = new javax.swing.JMenuItem();
        MnMCU = new javax.swing.JMenu();
        MnPaketMCU = new javax.swing.JMenuItem();
        MnPaketNapza = new javax.swing.JMenuItem();
        MnPaketMMPI = new javax.swing.JMenuItem();
        MnPaketHajiLaki = new javax.swing.JMenuItem();
        MnPaketHajiPerempuan = new javax.swing.JMenuItem();
        Kd2 = new widget.TextBox();
        DlgSakit = new javax.swing.JDialog();
        internalFrame3 = new widget.InternalFrame();
        panelBiasa2 = new widget.PanelBiasa();
        TglSakit1 = new widget.Tanggal();
        jLabel31 = new widget.Label();
        BtnPrint2 = new widget.Button();
        BtnKeluar2 = new widget.Button();
        jLabel32 = new widget.Label();
        TglSakit2 = new widget.Tanggal();
        jLabel33 = new widget.Label();
        lmsakit = new widget.TextBox();
        DlgDemografi = new javax.swing.JDialog();
        internalFrame4 = new widget.InternalFrame();
        panelBiasa3 = new widget.PanelBiasa();
        BtnPrint3 = new widget.Button();
        BtnKeluar3 = new widget.Button();
        Kelurahan2 = new widget.TextBox();
        btnKel = new widget.Button();
        Kecamatan2 = new widget.TextBox();
        btnKec = new widget.Button();
        Kabupaten2 = new widget.TextBox();
        btnKab = new widget.Button();
        jLabel34 = new widget.Label();
        jLabel35 = new widget.Label();
        jLabel36 = new widget.Label();
        BtnPrint4 = new widget.Button();
        DlgSakit2 = new javax.swing.JDialog();
        internalFrame5 = new widget.InternalFrame();
        panelBiasa4 = new widget.PanelBiasa();
        jLabel37 = new widget.Label();
        BtnPrint5 = new widget.Button();
        BtnKeluar4 = new widget.Button();
        NomorSurat = new widget.TextBox();
        BtnSeek5 = new widget.Button();
        CrDokter3 = new widget.TextBox();
        jLabel24 = new widget.Label();
        NoBalasan = new widget.TextBox();
        jPopupMenu2 = new javax.swing.JPopupMenu();
        MnPermintaan1 = new javax.swing.JMenu();
        MnJadwalOperasi1 = new javax.swing.JMenuItem();
        MnSKDPBPJS1 = new javax.swing.JMenuItem();
        MnPermintaanLab1 = new javax.swing.JMenuItem();
        MnPermintaanRadiologi1 = new javax.swing.JMenuItem();
        MnKamarInap1 = new javax.swing.JMenuItem();
        MnTindakan1 = new javax.swing.JMenu();
        MnRawatJalan1 = new javax.swing.JMenuItem();
        MnPeriksaLab1 = new javax.swing.JMenuItem();
        MnPeriksaRadiologi1 = new javax.swing.JMenuItem();
        MnOperasi1 = new javax.swing.JMenuItem();
        MnObat1 = new javax.swing.JMenu();
        MnPemberianObat1 = new javax.swing.JMenuItem();
        MnNoResep1 = new javax.swing.JMenuItem();
        MnResepDOkter1 = new javax.swing.JMenuItem();
        MnBilling1 = new javax.swing.JMenuItem();
        jSeparator12 = new javax.swing.JPopupMenu.Separator();
        MenuInputData1 = new javax.swing.JMenu();
        ppBerkasDigital1 = new javax.swing.JMenuItem();
        ppIKP1 = new javax.swing.JMenuItem();
        MnDiagnosa1 = new javax.swing.JMenuItem();
        MnTeridentifikasiTB1 = new javax.swing.JMenuItem();
        MnUrut1 = new javax.swing.JMenu();
        MnUrutNoRawatDesc1 = new javax.swing.JMenuItem();
        MnUrutNoRawatAsc1 = new javax.swing.JMenuItem();
        MnUrutTanggalDesc1 = new javax.swing.JMenuItem();
        MnUrutTanggalAsc1 = new javax.swing.JMenuItem();
        MnUrutDokterDesc1 = new javax.swing.JMenuItem();
        MnUrutDokterAsc1 = new javax.swing.JMenuItem();
        MnUrutPoliklinikDesc1 = new javax.swing.JMenuItem();
        MnUrutPoliklinikAsc1 = new javax.swing.JMenuItem();
        MnUrutPenjabDesc1 = new javax.swing.JMenuItem();
        MnUrutPenjabAsc1 = new javax.swing.JMenuItem();
        MnUrutStatusDesc1 = new javax.swing.JMenuItem();
        MnUrutStatusAsc1 = new javax.swing.JMenuItem();
        ppRiwayat1 = new javax.swing.JMenuItem();
        MnHapusRujukan = new javax.swing.JMenuItem();
        MnInterpretasiEkg2 = new javax.swing.JMenuItem();
        MnJawabKonsul = new javax.swing.JMenuItem();
        DlgCatatan = new javax.swing.JDialog();
        internalFrame6 = new widget.InternalFrame();
        LabelCatatan = new widget.Label();
        DlgInputNoHp = new javax.swing.JDialog();
        internalFrame2 = new widget.InternalFrame();
        TNoRM1 = new widget.TextBox();
        TNoHp = new widget.TextBox();
        jLabel5 = new widget.Label();
        TPasien1 = new widget.TextBox();
        jLabel11 = new widget.Label();
        BtnSimpan1 = new widget.Button();
        BtnKeluar1 = new widget.Button();
        DlgInterpretasiEKG = new javax.swing.JDialog();
        internalFrame7 = new widget.InternalFrame();
        TNoRMint = new widget.TextBox();
        TNoRwint = new widget.TextBox();
        jLabel12 = new widget.Label();
        TPasienint = new widget.TextBox();
        jLabel25 = new widget.Label();
        BtnSimpan2 = new widget.Button();
        BtnKeluar5 = new widget.Button();
        scrollPane1 = new widget.ScrollPane();
        TKesan = new widget.TextArea();
        BtnPrint1 = new widget.Button();
        Tind = new widget.TextBox();
        DlgJawabanKonsul = new javax.swing.JDialog();
        internalFrame8 = new widget.InternalFrame();
        jLabel26 = new widget.Label();
        jLabel27 = new widget.Label();
        LabelNama = new widget.Label();
        jLabel28 = new widget.Label();
        LabelNoRM = new widget.Label();
        jLabel29 = new widget.Label();
        LabelNoRW = new widget.Label();
        jLabel38 = new widget.Label();
        LabelDokter = new widget.Label();
        jLabel39 = new widget.Label();
        LabelPoliTujuan = new widget.Label();
        jLabel40 = new widget.Label();
        jLabel41 = new widget.Label();
        LabelDokterTujuan = new widget.Label();
        jLabel42 = new widget.Label();
        LabelPoliPerujuk = new widget.Label();
        jLabel43 = new widget.Label();
        jLabel44 = new widget.Label();
        jLabel45 = new widget.Label();
        LabelDiagnosa = new widget.Label();
        jLabel46 = new widget.Label();
        TSaranJawab = new widget.TextArea();
        TKonsulJawab = new widget.TextArea();
        TPemeriksaanJawab = new widget.TextArea();
        BtnKeluar6 = new widget.Button();
        internalFrame1 = new widget.InternalFrame();
        jPanel2 = new javax.swing.JPanel();
        panelGlass6 = new widget.panelisi();
        BtnSimpan = new widget.Button();
        BtnBatal = new widget.Button();
        BtnEdit = new widget.Button();
        BtnHapus = new widget.Button();
        BtnPrint = new widget.Button();
        BtnAll = new widget.Button();
        jLabel10 = new widget.Label();
        LCount = new widget.Label();
        BtnKeluar = new widget.Button();
        panelGlass7 = new widget.panelisi();
        jLabel15 = new widget.Label();
        DTPCari1 = new widget.Tanggal();
        jLabel17 = new widget.Label();
        DTPCari2 = new widget.Tanggal();
        jLabel6 = new widget.Label();
        TCari = new widget.TextBox();
        BtnCari = new widget.Button();
        panelGlass8 = new widget.panelisi();
        jLabel14 = new widget.Label();
        CrDokter = new widget.TextBox();
        BtnSeek3 = new widget.Button();
        jLabel16 = new widget.Label();
        CrPoli = new widget.TextBox();
        BtnSeek4 = new widget.Button();
        PanelInput = new javax.swing.JPanel();
        FormInput = new widget.PanelBiasa();
        jLabel3 = new widget.Label();
        jLabel4 = new widget.Label();
        TDokter = new widget.TextBox();
        TNoRw = new widget.TextBox();
        jLabel8 = new widget.Label();
        jLabel13 = new widget.Label();
        jLabel9 = new widget.Label();
        DTPReg = new widget.Tanggal();
        jLabel20 = new widget.Label();
        TPngJwb = new widget.TextBox();
        TNoRM = new widget.TextBox();
        TNoReg = new widget.TextBox();
        jLabel21 = new widget.Label();
        CmbJam = new widget.ComboBox();
        CmbMenit = new widget.ComboBox();
        CmbDetik = new widget.ComboBox();
        jLabel7 = new widget.Label();
        TAlmt = new widget.TextBox();
        BtnPasien = new widget.Button();
        TPasien = new widget.TextBox();
        jLabel22 = new widget.Label();
        THbngn = new widget.TextBox();
        ChkJln = new widget.CekBox();
        jLabel19 = new widget.Label();
        TPoli = new widget.TextBox();
        TBiaya = new widget.TextBox();
        kddokter = new widget.TextBox();
        BtnDokter = new widget.Button();
        kdpoli = new widget.TextBox();
        BtnUnit = new widget.Button();
        jLabel30 = new widget.Label();
        TStatus = new widget.TextBox();
        jLabel18 = new widget.Label();
        kdpnj = new widget.TextBox();
        nmpnj = new widget.TextBox();
        btnPenjab = new widget.Button();
        jLabel23 = new widget.Label();
        AsalRujukan = new widget.TextBox();
        btnPenjab1 = new widget.Button();
        ChkTracker = new widget.CekBox();
        ChkInput = new widget.CekBox();
        TabRawat = new javax.swing.JTabbedPane();
        Scroll = new widget.ScrollPane();
        tbPetugas = new widget.Table();
        Scroll1 = new widget.ScrollPane();
        tbPetugas2 = new widget.Table();
        Scroll2 = new widget.ScrollPane();
        tbPetugas1 = new widget.Table();

        jPopupMenu1.setName("jPopupMenu1"); // NOI18N

        MnPermintaan.setBackground(new java.awt.Color(250, 255, 245));
        MnPermintaan.setForeground(new java.awt.Color(70, 70, 70));
        MnPermintaan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnPermintaan.setText("Permintaan");
        MnPermintaan.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnPermintaan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnPermintaan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnPermintaan.setIconTextGap(5);
        MnPermintaan.setName("MnPermintaan"); // NOI18N
        MnPermintaan.setPreferredSize(new java.awt.Dimension(260, 26));

        MnJadwalOperasi.setBackground(new java.awt.Color(255, 255, 254));
        MnJadwalOperasi.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnJadwalOperasi.setForeground(new java.awt.Color(70, 70, 70));
        MnJadwalOperasi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnJadwalOperasi.setText("Jadwal Operasi");
        MnJadwalOperasi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnJadwalOperasi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnJadwalOperasi.setIconTextGap(5);
        MnJadwalOperasi.setName("MnJadwalOperasi"); // NOI18N
        MnJadwalOperasi.setPreferredSize(new java.awt.Dimension(180, 26));
        MnJadwalOperasi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnJadwalOperasiActionPerformed(evt);
            }
        });
        MnPermintaan.add(MnJadwalOperasi);

        MnSKDPBPJS.setBackground(new java.awt.Color(255, 255, 254));
        MnSKDPBPJS.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnSKDPBPJS.setForeground(new java.awt.Color(70, 70, 70));
        MnSKDPBPJS.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnSKDPBPJS.setText("SKDP BPJS");
        MnSKDPBPJS.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnSKDPBPJS.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnSKDPBPJS.setIconTextGap(5);
        MnSKDPBPJS.setName("MnSKDPBPJS"); // NOI18N
        MnSKDPBPJS.setPreferredSize(new java.awt.Dimension(180, 26));
        MnSKDPBPJS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnSKDPBPJSActionPerformed(evt);
            }
        });
        MnPermintaan.add(MnSKDPBPJS);

        MnPermintaanLab.setBackground(new java.awt.Color(255, 255, 254));
        MnPermintaanLab.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnPermintaanLab.setForeground(new java.awt.Color(70, 70, 70));
        MnPermintaanLab.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnPermintaanLab.setText("Pemeriksaan Lab");
        MnPermintaanLab.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnPermintaanLab.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnPermintaanLab.setIconTextGap(5);
        MnPermintaanLab.setName("MnPermintaanLab"); // NOI18N
        MnPermintaanLab.setPreferredSize(new java.awt.Dimension(180, 26));
        MnPermintaanLab.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnPermintaanLabActionPerformed(evt);
            }
        });
        MnPermintaan.add(MnPermintaanLab);

        MnPermintaanRadiologi.setBackground(new java.awt.Color(255, 255, 254));
        MnPermintaanRadiologi.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnPermintaanRadiologi.setForeground(new java.awt.Color(70, 70, 70));
        MnPermintaanRadiologi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnPermintaanRadiologi.setText("Pemeriksaan Radiologi");
        MnPermintaanRadiologi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnPermintaanRadiologi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnPermintaanRadiologi.setIconTextGap(5);
        MnPermintaanRadiologi.setName("MnPermintaanRadiologi"); // NOI18N
        MnPermintaanRadiologi.setPreferredSize(new java.awt.Dimension(180, 26));
        MnPermintaanRadiologi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnPermintaanRadiologiActionPerformed(evt);
            }
        });
        MnPermintaan.add(MnPermintaanRadiologi);

        jPopupMenu1.add(MnPermintaan);

        MnKamarInap.setBackground(new java.awt.Color(255, 255, 254));
        MnKamarInap.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnKamarInap.setForeground(new java.awt.Color(70, 70, 70));
        MnKamarInap.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnKamarInap.setText("Kamar Inap");
        MnKamarInap.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnKamarInap.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnKamarInap.setIconTextGap(5);
        MnKamarInap.setName("MnKamarInap"); // NOI18N
        MnKamarInap.setPreferredSize(new java.awt.Dimension(260, 26));
        MnKamarInap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnKamarInapActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnKamarInap);

        MnTindakan.setBackground(new java.awt.Color(250, 255, 245));
        MnTindakan.setForeground(new java.awt.Color(70, 70, 70));
        MnTindakan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnTindakan.setText("Tindakan & Pemeriksaan");
        MnTindakan.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnTindakan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnTindakan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnTindakan.setIconTextGap(5);
        MnTindakan.setName("MnTindakan"); // NOI18N
        MnTindakan.setPreferredSize(new java.awt.Dimension(260, 26));

        MnRawatJalan.setBackground(new java.awt.Color(255, 255, 254));
        MnRawatJalan.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnRawatJalan.setForeground(new java.awt.Color(70, 70, 70));
        MnRawatJalan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnRawatJalan.setText("Tagihan/Tindakan Rawat Jalan");
        MnRawatJalan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnRawatJalan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnRawatJalan.setIconTextGap(5);
        MnRawatJalan.setName("MnRawatJalan"); // NOI18N
        MnRawatJalan.setPreferredSize(new java.awt.Dimension(240, 26));
        MnRawatJalan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnRawatJalanActionPerformed(evt);
            }
        });
        MnTindakan.add(MnRawatJalan);

        MnPeriksaLab.setBackground(new java.awt.Color(255, 255, 254));
        MnPeriksaLab.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnPeriksaLab.setForeground(new java.awt.Color(70, 70, 70));
        MnPeriksaLab.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnPeriksaLab.setText("Periksa Laborat");
        MnPeriksaLab.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnPeriksaLab.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnPeriksaLab.setIconTextGap(5);
        MnPeriksaLab.setName("MnPeriksaLab"); // NOI18N
        MnPeriksaLab.setPreferredSize(new java.awt.Dimension(240, 26));
        MnPeriksaLab.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnPeriksaLabActionPerformed(evt);
            }
        });
        MnTindakan.add(MnPeriksaLab);

        MnPeriksaRadiologi.setBackground(new java.awt.Color(255, 255, 254));
        MnPeriksaRadiologi.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnPeriksaRadiologi.setForeground(new java.awt.Color(70, 70, 70));
        MnPeriksaRadiologi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnPeriksaRadiologi.setText("Periksa Radiologi");
        MnPeriksaRadiologi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnPeriksaRadiologi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnPeriksaRadiologi.setIconTextGap(5);
        MnPeriksaRadiologi.setName("MnPeriksaRadiologi"); // NOI18N
        MnPeriksaRadiologi.setPreferredSize(new java.awt.Dimension(240, 26));
        MnPeriksaRadiologi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnPeriksaRadiologiActionPerformed(evt);
            }
        });
        MnTindakan.add(MnPeriksaRadiologi);

        MnOperasi.setBackground(new java.awt.Color(255, 255, 254));
        MnOperasi.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnOperasi.setForeground(new java.awt.Color(70, 70, 70));
        MnOperasi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnOperasi.setText("Tagihan Operasi/VK");
        MnOperasi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnOperasi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnOperasi.setIconTextGap(5);
        MnOperasi.setName("MnOperasi"); // NOI18N
        MnOperasi.setPreferredSize(new java.awt.Dimension(240, 26));
        MnOperasi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnOperasiActionPerformed(evt);
            }
        });
        MnTindakan.add(MnOperasi);

        jPopupMenu1.add(MnTindakan);

        MnObat.setBackground(new java.awt.Color(250, 255, 245));
        MnObat.setForeground(new java.awt.Color(70, 70, 70));
        MnObat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnObat.setText("Obat");
        MnObat.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnObat.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnObat.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnObat.setIconTextGap(5);
        MnObat.setName("MnObat"); // NOI18N
        MnObat.setPreferredSize(new java.awt.Dimension(260, 26));

        MnPemberianObat.setBackground(new java.awt.Color(255, 255, 254));
        MnPemberianObat.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnPemberianObat.setForeground(new java.awt.Color(70, 70, 70));
        MnPemberianObat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnPemberianObat.setText("Pemberian Obat/BHP");
        MnPemberianObat.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnPemberianObat.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnPemberianObat.setIconTextGap(5);
        MnPemberianObat.setName("MnPemberianObat"); // NOI18N
        MnPemberianObat.setPreferredSize(new java.awt.Dimension(160, 26));
        MnPemberianObat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnPemberianObatActionPerformed(evt);
            }
        });
        MnObat.add(MnPemberianObat);

        MnNoResep.setBackground(new java.awt.Color(255, 255, 254));
        MnNoResep.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnNoResep.setForeground(new java.awt.Color(70, 70, 70));
        MnNoResep.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnNoResep.setText("Input No.Resep");
        MnNoResep.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnNoResep.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnNoResep.setIconTextGap(5);
        MnNoResep.setName("MnNoResep"); // NOI18N
        MnNoResep.setPreferredSize(new java.awt.Dimension(160, 26));
        MnNoResep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnNoResepActionPerformed(evt);
            }
        });
        MnObat.add(MnNoResep);

        MnResepDOkter.setBackground(new java.awt.Color(255, 255, 254));
        MnResepDOkter.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnResepDOkter.setForeground(new java.awt.Color(70, 70, 70));
        MnResepDOkter.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnResepDOkter.setText("Input Resep Dokter");
        MnResepDOkter.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnResepDOkter.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnResepDOkter.setIconTextGap(5);
        MnResepDOkter.setName("MnResepDOkter"); // NOI18N
        MnResepDOkter.setPreferredSize(new java.awt.Dimension(160, 26));
        MnResepDOkter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnResepDOkterActionPerformed(evt);
            }
        });
        MnObat.add(MnResepDOkter);

        jPopupMenu1.add(MnObat);

        MnPilihBilling.setBackground(new java.awt.Color(250, 255, 245));
        MnPilihBilling.setForeground(new java.awt.Color(70, 70, 70));
        MnPilihBilling.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnPilihBilling.setText("Billing/Pembayaran Pasien");
        MnPilihBilling.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnPilihBilling.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnPilihBilling.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnPilihBilling.setIconTextGap(5);
        MnPilihBilling.setName("MnPilihBilling"); // NOI18N
        MnPilihBilling.setPreferredSize(new java.awt.Dimension(260, 26));

        MnBillingParsial.setBackground(new java.awt.Color(255, 255, 254));
        MnBillingParsial.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnBillingParsial.setForeground(new java.awt.Color(70, 70, 70));
        MnBillingParsial.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnBillingParsial.setText("Billing Parsial");
        MnBillingParsial.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnBillingParsial.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnBillingParsial.setIconTextGap(5);
        MnBillingParsial.setName("MnBillingParsial"); // NOI18N
        MnBillingParsial.setPreferredSize(new java.awt.Dimension(130, 26));
        MnBillingParsial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnBillingParsialActionPerformed(evt);
            }
        });
        MnPilihBilling.add(MnBillingParsial);

        MnBilling.setBackground(new java.awt.Color(255, 255, 254));
        MnBilling.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnBilling.setForeground(new java.awt.Color(70, 70, 70));
        MnBilling.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnBilling.setText("Billing Total");
        MnBilling.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnBilling.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnBilling.setIconTextGap(5);
        MnBilling.setName("MnBilling"); // NOI18N
        MnBilling.setPreferredSize(new java.awt.Dimension(130, 26));
        MnBilling.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnBillingActionPerformed(evt);
            }
        });
        MnPilihBilling.add(MnBilling);

        jPopupMenu1.add(MnPilihBilling);

        jSeparator10.setBackground(new java.awt.Color(190, 220, 180));
        jSeparator10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(190, 220, 180)));
        jSeparator10.setForeground(new java.awt.Color(190, 220, 180));
        jSeparator10.setName("jSeparator10"); // NOI18N
        jSeparator10.setPreferredSize(new java.awt.Dimension(260, 1));
        jPopupMenu1.add(jSeparator10);

        jMenu1.setBackground(new java.awt.Color(250, 255, 245));
        jMenu1.setForeground(new java.awt.Color(70, 70, 70));
        jMenu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        jMenu1.setText("Laporan");
        jMenu1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jMenu1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jMenu1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jMenu1.setIconTextGap(5);
        jMenu1.setName("jMenu1"); // NOI18N
        jMenu1.setPreferredSize(new java.awt.Dimension(260, 26));

        MnLaporanRekapKunjunganPoli.setBackground(new java.awt.Color(255, 255, 254));
        MnLaporanRekapKunjunganPoli.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnLaporanRekapKunjunganPoli.setForeground(new java.awt.Color(70, 70, 70));
        MnLaporanRekapKunjunganPoli.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnLaporanRekapKunjunganPoli.setText("Laporan Rekap Kunjungan Per Poli");
        MnLaporanRekapKunjunganPoli.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnLaporanRekapKunjunganPoli.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnLaporanRekapKunjunganPoli.setIconTextGap(5);
        MnLaporanRekapKunjunganPoli.setName("MnLaporanRekapKunjunganPoli"); // NOI18N
        MnLaporanRekapKunjunganPoli.setPreferredSize(new java.awt.Dimension(240, 26));
        MnLaporanRekapKunjunganPoli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnLaporanRekapKunjunganPoliActionPerformed(evt);
            }
        });
        jMenu1.add(MnLaporanRekapKunjunganPoli);

        MnLaporanRekapKunjunganDokter.setBackground(new java.awt.Color(255, 255, 254));
        MnLaporanRekapKunjunganDokter.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnLaporanRekapKunjunganDokter.setForeground(new java.awt.Color(70, 70, 70));
        MnLaporanRekapKunjunganDokter.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnLaporanRekapKunjunganDokter.setText("Laporan Rekap Kunjungan Per Dokter");
        MnLaporanRekapKunjunganDokter.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnLaporanRekapKunjunganDokter.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnLaporanRekapKunjunganDokter.setIconTextGap(5);
        MnLaporanRekapKunjunganDokter.setName("MnLaporanRekapKunjunganDokter"); // NOI18N
        MnLaporanRekapKunjunganDokter.setPreferredSize(new java.awt.Dimension(240, 26));
        MnLaporanRekapKunjunganDokter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnLaporanRekapKunjunganDokterActionPerformed(evt);
            }
        });
        jMenu1.add(MnLaporanRekapKunjunganDokter);

        MnLaporanRekapJenisBayar.setBackground(new java.awt.Color(255, 255, 254));
        MnLaporanRekapJenisBayar.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnLaporanRekapJenisBayar.setForeground(new java.awt.Color(70, 70, 70));
        MnLaporanRekapJenisBayar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnLaporanRekapJenisBayar.setText("Laporan RL 315 Cara bayar");
        MnLaporanRekapJenisBayar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnLaporanRekapJenisBayar.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnLaporanRekapJenisBayar.setIconTextGap(5);
        MnLaporanRekapJenisBayar.setName("MnLaporanRekapJenisBayar"); // NOI18N
        MnLaporanRekapJenisBayar.setPreferredSize(new java.awt.Dimension(240, 26));
        MnLaporanRekapJenisBayar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnLaporanRekapJenisBayarActionPerformed(evt);
            }
        });
        jMenu1.add(MnLaporanRekapJenisBayar);

        MnLaporanRekapRawatDarurat.setBackground(new java.awt.Color(255, 255, 254));
        MnLaporanRekapRawatDarurat.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnLaporanRekapRawatDarurat.setForeground(new java.awt.Color(70, 70, 70));
        MnLaporanRekapRawatDarurat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnLaporanRekapRawatDarurat.setText("Laporan RL 32 Rawat Darurat");
        MnLaporanRekapRawatDarurat.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnLaporanRekapRawatDarurat.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnLaporanRekapRawatDarurat.setIconTextGap(5);
        MnLaporanRekapRawatDarurat.setName("MnLaporanRekapRawatDarurat"); // NOI18N
        MnLaporanRekapRawatDarurat.setPreferredSize(new java.awt.Dimension(240, 26));
        MnLaporanRekapRawatDarurat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnLaporanRekapRawatDaruratActionPerformed(evt);
            }
        });
        jMenu1.add(MnLaporanRekapRawatDarurat);

        MnLaporanRekapKunjunganBulanan.setBackground(new java.awt.Color(255, 255, 254));
        MnLaporanRekapKunjunganBulanan.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnLaporanRekapKunjunganBulanan.setForeground(new java.awt.Color(70, 70, 70));
        MnLaporanRekapKunjunganBulanan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnLaporanRekapKunjunganBulanan.setText("Laporan RL 51");
        MnLaporanRekapKunjunganBulanan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnLaporanRekapKunjunganBulanan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnLaporanRekapKunjunganBulanan.setIconTextGap(5);
        MnLaporanRekapKunjunganBulanan.setName("MnLaporanRekapKunjunganBulanan"); // NOI18N
        MnLaporanRekapKunjunganBulanan.setPreferredSize(new java.awt.Dimension(240, 26));
        MnLaporanRekapKunjunganBulanan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnLaporanRekapKunjunganBulananActionPerformed(evt);
            }
        });
        jMenu1.add(MnLaporanRekapKunjunganBulanan);

        MnLaporanRekapKunjunganBulananPoli.setBackground(new java.awt.Color(255, 255, 254));
        MnLaporanRekapKunjunganBulananPoli.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnLaporanRekapKunjunganBulananPoli.setForeground(new java.awt.Color(70, 70, 70));
        MnLaporanRekapKunjunganBulananPoli.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnLaporanRekapKunjunganBulananPoli.setText("Laporan RL 52");
        MnLaporanRekapKunjunganBulananPoli.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnLaporanRekapKunjunganBulananPoli.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnLaporanRekapKunjunganBulananPoli.setIconTextGap(5);
        MnLaporanRekapKunjunganBulananPoli.setName("MnLaporanRekapKunjunganBulananPoli"); // NOI18N
        MnLaporanRekapKunjunganBulananPoli.setPreferredSize(new java.awt.Dimension(240, 26));
        MnLaporanRekapKunjunganBulananPoli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnLaporanRekapKunjunganBulananPoliActionPerformed(evt);
            }
        });
        jMenu1.add(MnLaporanRekapKunjunganBulananPoli);

        MnLaporanRekapPenyakitRalan.setBackground(new java.awt.Color(255, 255, 254));
        MnLaporanRekapPenyakitRalan.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnLaporanRekapPenyakitRalan.setForeground(new java.awt.Color(70, 70, 70));
        MnLaporanRekapPenyakitRalan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnLaporanRekapPenyakitRalan.setText("Laporan Frekuensi Penyakit Ralan");
        MnLaporanRekapPenyakitRalan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnLaporanRekapPenyakitRalan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnLaporanRekapPenyakitRalan.setIconTextGap(5);
        MnLaporanRekapPenyakitRalan.setName("MnLaporanRekapPenyakitRalan"); // NOI18N
        MnLaporanRekapPenyakitRalan.setPreferredSize(new java.awt.Dimension(240, 26));
        MnLaporanRekapPenyakitRalan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnLaporanRekapPenyakitRalanActionPerformed(evt);
            }
        });
        jMenu1.add(MnLaporanRekapPenyakitRalan);

        MnLaporanRekapPerujuk.setBackground(new java.awt.Color(255, 255, 254));
        MnLaporanRekapPerujuk.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnLaporanRekapPerujuk.setForeground(new java.awt.Color(70, 70, 70));
        MnLaporanRekapPerujuk.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnLaporanRekapPerujuk.setText("Laporan Rekap Perperujuk");
        MnLaporanRekapPerujuk.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnLaporanRekapPerujuk.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnLaporanRekapPerujuk.setIconTextGap(5);
        MnLaporanRekapPerujuk.setName("MnLaporanRekapPerujuk"); // NOI18N
        MnLaporanRekapPerujuk.setPreferredSize(new java.awt.Dimension(240, 26));
        MnLaporanRekapPerujuk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnLaporanRekapPerujukActionPerformed(evt);
            }
        });
        jMenu1.add(MnLaporanRekapPerujuk);

        jPopupMenu1.add(jMenu1);

        jMenu2.setBackground(new java.awt.Color(250, 255, 245));
        jMenu2.setForeground(new java.awt.Color(70, 70, 70));
        jMenu2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        jMenu2.setText("Grafik Analisa");
        jMenu2.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jMenu2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jMenu2.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jMenu2.setIconTextGap(5);
        jMenu2.setName("jMenu2"); // NOI18N
        jMenu2.setPreferredSize(new java.awt.Dimension(260, 26));

        ppGrafikPerpoli.setBackground(new java.awt.Color(255, 255, 254));
        ppGrafikPerpoli.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppGrafikPerpoli.setForeground(new java.awt.Color(70, 70, 70));
        ppGrafikPerpoli.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppGrafikPerpoli.setText("Grafik Plot Kunjungan Per Poli");
        ppGrafikPerpoli.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppGrafikPerpoli.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppGrafikPerpoli.setIconTextGap(5);
        ppGrafikPerpoli.setName("ppGrafikPerpoli"); // NOI18N
        ppGrafikPerpoli.setPreferredSize(new java.awt.Dimension(260, 26));
        ppGrafikPerpoli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppGrafikPerpoliActionPerformed(evt);
            }
        });
        jMenu2.add(ppGrafikPerpoli);

        ppGrafikPerpoli1.setBackground(new java.awt.Color(255, 255, 254));
        ppGrafikPerpoli1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppGrafikPerpoli1.setForeground(new java.awt.Color(70, 70, 70));
        ppGrafikPerpoli1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppGrafikPerpoli1.setText("Grafik Pie Kunjungan Per Poli");
        ppGrafikPerpoli1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppGrafikPerpoli1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppGrafikPerpoli1.setIconTextGap(5);
        ppGrafikPerpoli1.setName("ppGrafikPerpoli1"); // NOI18N
        ppGrafikPerpoli1.setPreferredSize(new java.awt.Dimension(260, 26));
        ppGrafikPerpoli1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppGrafikPerpoli1ActionPerformed(evt);
            }
        });
        jMenu2.add(ppGrafikPerpoli1);

        ppGrafikPerpoli2.setBackground(new java.awt.Color(255, 255, 254));
        ppGrafikPerpoli2.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppGrafikPerpoli2.setForeground(new java.awt.Color(70, 70, 70));
        ppGrafikPerpoli2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppGrafikPerpoli2.setText("Grafik Batang Kunjungan Per Poli");
        ppGrafikPerpoli2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppGrafikPerpoli2.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppGrafikPerpoli2.setIconTextGap(5);
        ppGrafikPerpoli2.setName("ppGrafikPerpoli2"); // NOI18N
        ppGrafikPerpoli2.setPreferredSize(new java.awt.Dimension(260, 26));
        ppGrafikPerpoli2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppGrafikPerpoli2ActionPerformed(evt);
            }
        });
        jMenu2.add(ppGrafikPerpoli2);

        ppGrafikPerdokter.setBackground(new java.awt.Color(255, 255, 254));
        ppGrafikPerdokter.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppGrafikPerdokter.setForeground(new java.awt.Color(70, 70, 70));
        ppGrafikPerdokter.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppGrafikPerdokter.setText("Grafik Plot Kunjungan Per Dokter");
        ppGrafikPerdokter.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppGrafikPerdokter.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppGrafikPerdokter.setIconTextGap(5);
        ppGrafikPerdokter.setName("ppGrafikPerdokter"); // NOI18N
        ppGrafikPerdokter.setPreferredSize(new java.awt.Dimension(260, 26));
        ppGrafikPerdokter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppGrafikPerdokterActionPerformed(evt);
            }
        });
        jMenu2.add(ppGrafikPerdokter);

        ppGrafikPerdokter1.setBackground(new java.awt.Color(255, 255, 254));
        ppGrafikPerdokter1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppGrafikPerdokter1.setForeground(new java.awt.Color(70, 70, 70));
        ppGrafikPerdokter1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppGrafikPerdokter1.setText("Grafik Pie Kunjungan Per Dokter");
        ppGrafikPerdokter1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppGrafikPerdokter1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppGrafikPerdokter1.setIconTextGap(5);
        ppGrafikPerdokter1.setName("ppGrafikPerdokter1"); // NOI18N
        ppGrafikPerdokter1.setPreferredSize(new java.awt.Dimension(260, 26));
        ppGrafikPerdokter1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppGrafikPerdokter1ActionPerformed(evt);
            }
        });
        jMenu2.add(ppGrafikPerdokter1);

        ppGrafikPerdokter2.setBackground(new java.awt.Color(255, 255, 254));
        ppGrafikPerdokter2.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppGrafikPerdokter2.setForeground(new java.awt.Color(70, 70, 70));
        ppGrafikPerdokter2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppGrafikPerdokter2.setText("Grafik Batang Kunjungan Per Dokter");
        ppGrafikPerdokter2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppGrafikPerdokter2.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppGrafikPerdokter2.setIconTextGap(5);
        ppGrafikPerdokter2.setName("ppGrafikPerdokter2"); // NOI18N
        ppGrafikPerdokter2.setPreferredSize(new java.awt.Dimension(260, 26));
        ppGrafikPerdokter2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppGrafikPerdokter2ActionPerformed(evt);
            }
        });
        jMenu2.add(ppGrafikPerdokter2);

        ppGrafikPerJK.setBackground(new java.awt.Color(255, 255, 254));
        ppGrafikPerJK.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppGrafikPerJK.setForeground(new java.awt.Color(70, 70, 70));
        ppGrafikPerJK.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppGrafikPerJK.setText("Grafik Plot Kunjungan Per Jenis Kelamin");
        ppGrafikPerJK.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppGrafikPerJK.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppGrafikPerJK.setIconTextGap(5);
        ppGrafikPerJK.setName("ppGrafikPerJK"); // NOI18N
        ppGrafikPerJK.setPreferredSize(new java.awt.Dimension(260, 26));
        ppGrafikPerJK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppGrafikPerJKActionPerformed(evt);
            }
        });
        jMenu2.add(ppGrafikPerJK);

        ppGrafikPerPekerjaan.setBackground(new java.awt.Color(255, 255, 254));
        ppGrafikPerPekerjaan.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppGrafikPerPekerjaan.setForeground(new java.awt.Color(70, 70, 70));
        ppGrafikPerPekerjaan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppGrafikPerPekerjaan.setText("Grafik Plot Kunjungan Per Pekerjaan");
        ppGrafikPerPekerjaan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppGrafikPerPekerjaan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppGrafikPerPekerjaan.setIconTextGap(5);
        ppGrafikPerPekerjaan.setName("ppGrafikPerPekerjaan"); // NOI18N
        ppGrafikPerPekerjaan.setPreferredSize(new java.awt.Dimension(260, 26));
        ppGrafikPerPekerjaan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppGrafikPerPekerjaanActionPerformed(evt);
            }
        });
        jMenu2.add(ppGrafikPerPekerjaan);

        ppGrafikPerAgama.setBackground(new java.awt.Color(255, 255, 254));
        ppGrafikPerAgama.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppGrafikPerAgama.setForeground(new java.awt.Color(70, 70, 70));
        ppGrafikPerAgama.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppGrafikPerAgama.setText("Grafik Plot Kunjungan Per Agama");
        ppGrafikPerAgama.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppGrafikPerAgama.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppGrafikPerAgama.setIconTextGap(5);
        ppGrafikPerAgama.setName("ppGrafikPerAgama"); // NOI18N
        ppGrafikPerAgama.setPreferredSize(new java.awt.Dimension(260, 26));
        ppGrafikPerAgama.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppGrafikPerAgamaActionPerformed(evt);
            }
        });
        jMenu2.add(ppGrafikPerAgama);

        ppGrafikPerTahun.setBackground(new java.awt.Color(255, 255, 254));
        ppGrafikPerTahun.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppGrafikPerTahun.setForeground(new java.awt.Color(70, 70, 70));
        ppGrafikPerTahun.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppGrafikPerTahun.setText("Grafik Plot Kunjungan Per Tahun");
        ppGrafikPerTahun.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppGrafikPerTahun.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppGrafikPerTahun.setIconTextGap(5);
        ppGrafikPerTahun.setName("ppGrafikPerTahun"); // NOI18N
        ppGrafikPerTahun.setPreferredSize(new java.awt.Dimension(260, 26));
        ppGrafikPerTahun.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppGrafikPerTahunActionPerformed(evt);
            }
        });
        jMenu2.add(ppGrafikPerTahun);

        ppGrafikPerBulan.setBackground(new java.awt.Color(255, 255, 254));
        ppGrafikPerBulan.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppGrafikPerBulan.setForeground(new java.awt.Color(70, 70, 70));
        ppGrafikPerBulan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppGrafikPerBulan.setText("Grafik Plot Kunjungan Per Bulan");
        ppGrafikPerBulan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppGrafikPerBulan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppGrafikPerBulan.setIconTextGap(5);
        ppGrafikPerBulan.setName("ppGrafikPerBulan"); // NOI18N
        ppGrafikPerBulan.setPreferredSize(new java.awt.Dimension(260, 26));
        ppGrafikPerBulan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppGrafikPerBulanActionPerformed(evt);
            }
        });
        jMenu2.add(ppGrafikPerBulan);

        ppGrafikPerTanggal.setBackground(new java.awt.Color(255, 255, 254));
        ppGrafikPerTanggal.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppGrafikPerTanggal.setForeground(new java.awt.Color(70, 70, 70));
        ppGrafikPerTanggal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppGrafikPerTanggal.setText("Grafik Kunjungan Per Tanggal");
        ppGrafikPerTanggal.setActionCommand("Grafik Plot Kunjungan Per Tanggal");
        ppGrafikPerTanggal.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppGrafikPerTanggal.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppGrafikPerTanggal.setIconTextGap(5);
        ppGrafikPerTanggal.setName("ppGrafikPerTanggal"); // NOI18N
        ppGrafikPerTanggal.setPreferredSize(new java.awt.Dimension(260, 26));
        ppGrafikPerTanggal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppGrafikPerTanggalActionPerformed(evt);
            }
        });
        jMenu2.add(ppGrafikPerTanggal);

        ppGrafikDemografi.setBackground(new java.awt.Color(255, 255, 254));
        ppGrafikDemografi.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppGrafikDemografi.setForeground(new java.awt.Color(70, 70, 70));
        ppGrafikDemografi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppGrafikDemografi.setText("Demografi Pendaftar");
        ppGrafikDemografi.setActionCommand("Grafik Demografi Pasien");
        ppGrafikDemografi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppGrafikDemografi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppGrafikDemografi.setIconTextGap(5);
        ppGrafikDemografi.setName("ppGrafikDemografi"); // NOI18N
        ppGrafikDemografi.setPreferredSize(new java.awt.Dimension(260, 26));
        ppGrafikDemografi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppGrafikDemografiActionPerformed(evt);
            }
        });
        jMenu2.add(ppGrafikDemografi);

        jPopupMenu1.add(jMenu2);

        jMenu4.setBackground(new java.awt.Color(250, 255, 245));
        jMenu4.setForeground(new java.awt.Color(70, 70, 70));
        jMenu4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        jMenu4.setText("Surat-Surat");
        jMenu4.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jMenu4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jMenu4.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jMenu4.setIconTextGap(5);
        jMenu4.setName("jMenu4"); // NOI18N
        jMenu4.setPreferredSize(new java.awt.Dimension(260, 26));

        MnCetakSuratSehat.setBackground(new java.awt.Color(255, 255, 254));
        MnCetakSuratSehat.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnCetakSuratSehat.setForeground(new java.awt.Color(70, 70, 70));
        MnCetakSuratSehat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnCetakSuratSehat.setText("Surat Keterangan Sehat 1");
        MnCetakSuratSehat.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnCetakSuratSehat.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnCetakSuratSehat.setIconTextGap(5);
        MnCetakSuratSehat.setName("MnCetakSuratSehat"); // NOI18N
        MnCetakSuratSehat.setPreferredSize(new java.awt.Dimension(320, 26));
        MnCetakSuratSehat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnCetakSuratSehatActionPerformed(evt);
            }
        });
        jMenu4.add(MnCetakSuratSehat);

        MnCetakSuratSehat1.setBackground(new java.awt.Color(255, 255, 254));
        MnCetakSuratSehat1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnCetakSuratSehat1.setForeground(new java.awt.Color(70, 70, 70));
        MnCetakSuratSehat1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnCetakSuratSehat1.setText("Surat Keterangan Sehat 2");
        MnCetakSuratSehat1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnCetakSuratSehat1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnCetakSuratSehat1.setIconTextGap(5);
        MnCetakSuratSehat1.setName("MnCetakSuratSehat1"); // NOI18N
        MnCetakSuratSehat1.setPreferredSize(new java.awt.Dimension(320, 26));
        MnCetakSuratSehat1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnCetakSuratSehat1ActionPerformed(evt);
            }
        });
        jMenu4.add(MnCetakSuratSehat1);

        MnCetakBebasNarkoba.setBackground(new java.awt.Color(255, 255, 254));
        MnCetakBebasNarkoba.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnCetakBebasNarkoba.setForeground(new java.awt.Color(70, 70, 70));
        MnCetakBebasNarkoba.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnCetakBebasNarkoba.setText("Surat Keterangan Bebas Narkoba");
        MnCetakBebasNarkoba.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnCetakBebasNarkoba.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnCetakBebasNarkoba.setIconTextGap(5);
        MnCetakBebasNarkoba.setName("MnCetakBebasNarkoba"); // NOI18N
        MnCetakBebasNarkoba.setPreferredSize(new java.awt.Dimension(320, 26));
        MnCetakBebasNarkoba.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnCetakBebasNarkobaActionPerformed(evt);
            }
        });
        jMenu4.add(MnCetakBebasNarkoba);

        MnCetakSuratSakit.setBackground(new java.awt.Color(255, 255, 254));
        MnCetakSuratSakit.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnCetakSuratSakit.setForeground(new java.awt.Color(70, 70, 70));
        MnCetakSuratSakit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnCetakSuratSakit.setText("Surat Cuti Sakit 1");
        MnCetakSuratSakit.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnCetakSuratSakit.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnCetakSuratSakit.setIconTextGap(5);
        MnCetakSuratSakit.setName("MnCetakSuratSakit"); // NOI18N
        MnCetakSuratSakit.setPreferredSize(new java.awt.Dimension(320, 26));
        MnCetakSuratSakit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnCetakSuratSakitActionPerformed(evt);
            }
        });
        jMenu4.add(MnCetakSuratSakit);

        MnCetakSuratSakit2.setBackground(new java.awt.Color(255, 255, 254));
        MnCetakSuratSakit2.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnCetakSuratSakit2.setForeground(new java.awt.Color(70, 70, 70));
        MnCetakSuratSakit2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnCetakSuratSakit2.setText("Surat Cuti Sakit 2");
        MnCetakSuratSakit2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnCetakSuratSakit2.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnCetakSuratSakit2.setIconTextGap(5);
        MnCetakSuratSakit2.setName("MnCetakSuratSakit2"); // NOI18N
        MnCetakSuratSakit2.setPreferredSize(new java.awt.Dimension(320, 26));
        MnCetakSuratSakit2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnCetakSuratSakit2ActionPerformed(evt);
            }
        });
        jMenu4.add(MnCetakSuratSakit2);

        MnCetakSuratSakit3.setBackground(new java.awt.Color(255, 255, 254));
        MnCetakSuratSakit3.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnCetakSuratSakit3.setForeground(new java.awt.Color(70, 70, 70));
        MnCetakSuratSakit3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnCetakSuratSakit3.setText("Surat Cuti Sakit 3");
        MnCetakSuratSakit3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnCetakSuratSakit3.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnCetakSuratSakit3.setIconTextGap(5);
        MnCetakSuratSakit3.setName("MnCetakSuratSakit3"); // NOI18N
        MnCetakSuratSakit3.setPreferredSize(new java.awt.Dimension(320, 26));
        MnCetakSuratSakit3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnCetakSuratSakit3ActionPerformed(evt);
            }
        });
        jMenu4.add(MnCetakSuratSakit3);

        MnCetakSuratSakit1.setBackground(new java.awt.Color(255, 255, 254));
        MnCetakSuratSakit1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnCetakSuratSakit1.setForeground(new java.awt.Color(70, 70, 70));
        MnCetakSuratSakit1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnCetakSuratSakit1.setText("Surat Keterangan Rawat Inap");
        MnCetakSuratSakit1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnCetakSuratSakit1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnCetakSuratSakit1.setIconTextGap(5);
        MnCetakSuratSakit1.setName("MnCetakSuratSakit1"); // NOI18N
        MnCetakSuratSakit1.setPreferredSize(new java.awt.Dimension(320, 26));
        MnCetakSuratSakit1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnCetakSuratSakit1ActionPerformed(evt);
            }
        });
        jMenu4.add(MnCetakSuratSakit1);

        MnCetakRegister.setBackground(new java.awt.Color(255, 255, 254));
        MnCetakRegister.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnCetakRegister.setForeground(new java.awt.Color(70, 70, 70));
        MnCetakRegister.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnCetakRegister.setText("Bukti Register 1");
        MnCetakRegister.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnCetakRegister.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnCetakRegister.setIconTextGap(5);
        MnCetakRegister.setName("MnCetakRegister"); // NOI18N
        MnCetakRegister.setPreferredSize(new java.awt.Dimension(320, 26));
        MnCetakRegister.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnCetakRegisterActionPerformed(evt);
            }
        });
        jMenu4.add(MnCetakRegister);

        MnCetakRegister1.setBackground(new java.awt.Color(255, 255, 254));
        MnCetakRegister1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnCetakRegister1.setForeground(new java.awt.Color(70, 70, 70));
        MnCetakRegister1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnCetakRegister1.setText("Bukti Register 2");
        MnCetakRegister1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnCetakRegister1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnCetakRegister1.setIconTextGap(5);
        MnCetakRegister1.setName("MnCetakRegister1"); // NOI18N
        MnCetakRegister1.setPreferredSize(new java.awt.Dimension(320, 26));
        MnCetakRegister1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnCetakRegister1ActionPerformed(evt);
            }
        });
        jMenu4.add(MnCetakRegister1);

        MnCetakRegister2.setBackground(new java.awt.Color(255, 255, 254));
        MnCetakRegister2.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnCetakRegister2.setForeground(new java.awt.Color(70, 70, 70));
        MnCetakRegister2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnCetakRegister2.setText("Bukti Register 3");
        MnCetakRegister2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnCetakRegister2.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnCetakRegister2.setIconTextGap(5);
        MnCetakRegister2.setName("MnCetakRegister2"); // NOI18N
        MnCetakRegister2.setPreferredSize(new java.awt.Dimension(240, 26));
        MnCetakRegister2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnCetakRegister2ActionPerformed(evt);
            }
        });
        jMenu4.add(MnCetakRegister2);

        MnPersetujuanMedis.setBackground(new java.awt.Color(255, 255, 254));
        MnPersetujuanMedis.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnPersetujuanMedis.setForeground(new java.awt.Color(70, 70, 70));
        MnPersetujuanMedis.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnPersetujuanMedis.setText("Persetujuan Medis");
        MnPersetujuanMedis.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnPersetujuanMedis.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnPersetujuanMedis.setIconTextGap(5);
        MnPersetujuanMedis.setName("MnPersetujuanMedis"); // NOI18N
        MnPersetujuanMedis.setPreferredSize(new java.awt.Dimension(320, 26));
        MnPersetujuanMedis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnPersetujuanMedisActionPerformed(evt);
            }
        });
        jMenu4.add(MnPersetujuanMedis);

        MnBuktiPelayananRalan.setBackground(new java.awt.Color(255, 255, 254));
        MnBuktiPelayananRalan.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnBuktiPelayananRalan.setForeground(new java.awt.Color(70, 70, 70));
        MnBuktiPelayananRalan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnBuktiPelayananRalan.setText("Surat Jaminan & Bukti Pelayanan Ralan");
        MnBuktiPelayananRalan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnBuktiPelayananRalan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnBuktiPelayananRalan.setIconTextGap(5);
        MnBuktiPelayananRalan.setName("MnBuktiPelayananRalan"); // NOI18N
        MnBuktiPelayananRalan.setPreferredSize(new java.awt.Dimension(320, 26));
        MnBuktiPelayananRalan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnBuktiPelayananRalanActionPerformed(evt);
            }
        });
        jMenu4.add(MnBuktiPelayananRalan);

        MnLembarCasemix.setBackground(new java.awt.Color(255, 255, 254));
        MnLembarCasemix.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnLembarCasemix.setForeground(new java.awt.Color(70, 70, 70));
        MnLembarCasemix.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnLembarCasemix.setText("Lembar Casemix 1");
        MnLembarCasemix.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnLembarCasemix.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnLembarCasemix.setIconTextGap(5);
        MnLembarCasemix.setName("MnLembarCasemix"); // NOI18N
        MnLembarCasemix.setPreferredSize(new java.awt.Dimension(320, 26));
        MnLembarCasemix.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnLembarCasemixActionPerformed(evt);
            }
        });
        jMenu4.add(MnLembarCasemix);

        MnLembarCasemix1.setBackground(new java.awt.Color(255, 255, 254));
        MnLembarCasemix1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnLembarCasemix1.setForeground(new java.awt.Color(70, 70, 70));
        MnLembarCasemix1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnLembarCasemix1.setText("Lembar Casemix 2");
        MnLembarCasemix1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnLembarCasemix1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnLembarCasemix1.setIconTextGap(5);
        MnLembarCasemix1.setName("MnLembarCasemix1"); // NOI18N
        MnLembarCasemix1.setPreferredSize(new java.awt.Dimension(320, 26));
        MnLembarCasemix1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnLembarCasemix1ActionPerformed(evt);
            }
        });
        jMenu4.add(MnLembarCasemix1);

        MnLembarCasemix2.setBackground(new java.awt.Color(255, 255, 254));
        MnLembarCasemix2.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnLembarCasemix2.setForeground(new java.awt.Color(70, 70, 70));
        MnLembarCasemix2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnLembarCasemix2.setText("Lembar Casemix 3");
        MnLembarCasemix2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnLembarCasemix2.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnLembarCasemix2.setIconTextGap(5);
        MnLembarCasemix2.setName("MnLembarCasemix2"); // NOI18N
        MnLembarCasemix2.setPreferredSize(new java.awt.Dimension(320, 26));
        MnLembarCasemix2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnLembarCasemix2ActionPerformed(evt);
            }
        });
        jMenu4.add(MnLembarCasemix2);

        MnSPBK.setBackground(new java.awt.Color(255, 255, 254));
        MnSPBK.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnSPBK.setForeground(new java.awt.Color(70, 70, 70));
        MnSPBK.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnSPBK.setText("Surat Bukti Pelayanan Kesehatan (SBPK) 1");
        MnSPBK.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnSPBK.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnSPBK.setIconTextGap(5);
        MnSPBK.setName("MnSPBK"); // NOI18N
        MnSPBK.setPreferredSize(new java.awt.Dimension(320, 26));
        MnSPBK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnSPBKActionPerformed(evt);
            }
        });
        jMenu4.add(MnSPBK);

        MnSPBK1.setBackground(new java.awt.Color(255, 255, 254));
        MnSPBK1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnSPBK1.setForeground(new java.awt.Color(70, 70, 70));
        MnSPBK1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnSPBK1.setMnemonic('z');
        MnSPBK1.setText("Surat Bukti Pelayanan Kesehatan (SBPK) 2");
        MnSPBK1.setToolTipText("Z");
        MnSPBK1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnSPBK1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnSPBK1.setIconTextGap(5);
        MnSPBK1.setName("MnSPBK1"); // NOI18N
        MnSPBK1.setPreferredSize(new java.awt.Dimension(320, 26));
        MnSPBK1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnSPBK1ActionPerformed(evt);
            }
        });
        jMenu4.add(MnSPBK1);

        MnJKRA.setBackground(new java.awt.Color(255, 255, 254));
        MnJKRA.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnJKRA.setForeground(new java.awt.Color(70, 70, 70));
        MnJKRA.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnJKRA.setText("Surat Jaminan Kesehatan Nasional (JKRA) Rawat Jalan");
        MnJKRA.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnJKRA.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnJKRA.setIconTextGap(5);
        MnJKRA.setName("MnJKRA"); // NOI18N
        MnJKRA.setPreferredSize(new java.awt.Dimension(320, 26));
        MnJKRA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnJKRAActionPerformed(evt);
            }
        });
        jMenu4.add(MnJKRA);

        MnLembarRalan.setBackground(new java.awt.Color(255, 255, 254));
        MnLembarRalan.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnLembarRalan.setForeground(new java.awt.Color(70, 70, 70));
        MnLembarRalan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnLembarRalan.setMnemonic('w');
        MnLembarRalan.setText("Lembar Rawat Jalan");
        MnLembarRalan.setToolTipText("W");
        MnLembarRalan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnLembarRalan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnLembarRalan.setIconTextGap(5);
        MnLembarRalan.setName("MnLembarRalan"); // NOI18N
        MnLembarRalan.setPreferredSize(new java.awt.Dimension(320, 26));
        MnLembarRalan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnLembarRalanActionPerformed(evt);
            }
        });
        jMenu4.add(MnLembarRalan);

        MnBlangkoResep.setBackground(new java.awt.Color(255, 255, 254));
        MnBlangkoResep.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnBlangkoResep.setForeground(new java.awt.Color(70, 70, 70));
        MnBlangkoResep.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnBlangkoResep.setMnemonic('w');
        MnBlangkoResep.setText("Blanko Resep");
        MnBlangkoResep.setToolTipText("W");
        MnBlangkoResep.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnBlangkoResep.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnBlangkoResep.setIconTextGap(5);
        MnBlangkoResep.setName("MnBlangkoResep"); // NOI18N
        MnBlangkoResep.setPreferredSize(new java.awt.Dimension(320, 26));
        MnBlangkoResep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnBlangkoResepActionPerformed(evt);
            }
        });
        jMenu4.add(MnBlangkoResep);

        MnDataSuratSehat.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        MnDataSuratSehat.setText("Data Surat Sehat");
        MnDataSuratSehat.setName("MnDataSuratSehat"); // NOI18N
        MnDataSuratSehat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnDataSuratSehatActionPerformed(evt);
            }
        });
        jMenu4.add(MnDataSuratSehat);

        MnCetakSuratSehat2.setBackground(new java.awt.Color(255, 255, 254));
        MnCetakSuratSehat2.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnCetakSuratSehat2.setForeground(new java.awt.Color(70, 70, 70));
        MnCetakSuratSehat2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnCetakSuratSehat2.setText("Data Surat Sehat");
        MnCetakSuratSehat2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnCetakSuratSehat2.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnCetakSuratSehat2.setIconTextGap(5);
        MnCetakSuratSehat2.setName("MnDataSuratSehat1"); // NOI18N
        MnCetakSuratSehat2.setPreferredSize(new java.awt.Dimension(320, 26));
        MnCetakSuratSehat2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnCetakSuratSehat2ActionPerformed(evt);
            }
        });
        jMenu4.add(MnCetakSuratSehat2);

        jPopupMenu1.add(jMenu4);

        jMenu3.setBackground(new java.awt.Color(250, 255, 245));
        jMenu3.setForeground(new java.awt.Color(70, 70, 70));
        jMenu3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        jMenu3.setText("Check List Kelengkapan Pendaftaran");
        jMenu3.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jMenu3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jMenu3.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jMenu3.setIconTextGap(5);
        jMenu3.setName("jMenu3"); // NOI18N
        jMenu3.setPreferredSize(new java.awt.Dimension(260, 26));

        MnCheckList.setBackground(new java.awt.Color(255, 255, 254));
        MnCheckList.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnCheckList.setForeground(new java.awt.Color(70, 70, 70));
        MnCheckList.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnCheckList.setText("Chekc List Kelengkapan Pendaftaran Kanan");
        MnCheckList.setToolTipText("");
        MnCheckList.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnCheckList.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnCheckList.setIconTextGap(5);
        MnCheckList.setName("MnCheckList"); // NOI18N
        MnCheckList.setPreferredSize(new java.awt.Dimension(310, 26));
        MnCheckList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnCheckListActionPerformed(evt);
            }
        });
        jMenu3.add(MnCheckList);

        MnCheckList1.setBackground(new java.awt.Color(255, 255, 254));
        MnCheckList1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnCheckList1.setForeground(new java.awt.Color(70, 70, 70));
        MnCheckList1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnCheckList1.setText("Chekc List Kelengkapan Pendaftaran Kiri");
        MnCheckList1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnCheckList1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnCheckList1.setIconTextGap(5);
        MnCheckList1.setName("MnCheckList1"); // NOI18N
        MnCheckList1.setPreferredSize(new java.awt.Dimension(310, 26));
        MnCheckList1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnCheckList1ActionPerformed(evt);
            }
        });
        jMenu3.add(MnCheckList1);

        MnCheckList2.setBackground(new java.awt.Color(255, 255, 254));
        MnCheckList2.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnCheckList2.setForeground(new java.awt.Color(70, 70, 70));
        MnCheckList2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnCheckList2.setText("Chekc List Kelengkapan Pendaftaran Kanan+Tracker");
        MnCheckList2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnCheckList2.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnCheckList2.setIconTextGap(5);
        MnCheckList2.setName("MnCheckList2"); // NOI18N
        MnCheckList2.setPreferredSize(new java.awt.Dimension(310, 26));
        MnCheckList2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnCheckList2ActionPerformed(evt);
            }
        });
        jMenu3.add(MnCheckList2);

        MnCheckList3.setBackground(new java.awt.Color(255, 255, 254));
        MnCheckList3.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnCheckList3.setForeground(new java.awt.Color(70, 70, 70));
        MnCheckList3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnCheckList3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnCheckList3.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnCheckList3.setIconTextGap(5);
        MnCheckList3.setLabel("Chekc List Kelengkapan Pendaftaran Kiri+Tracker");
        MnCheckList3.setName("MnCheckList3"); // NOI18N
        MnCheckList3.setPreferredSize(new java.awt.Dimension(310, 26));
        MnCheckList3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnCheckList3ActionPerformed(evt);
            }
        });
        jMenu3.add(MnCheckList3);

        jPopupMenu1.add(jMenu3);

        jMenu5.setBackground(new java.awt.Color(250, 255, 245));
        jMenu5.setForeground(new java.awt.Color(70, 70, 70));
        jMenu5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        jMenu5.setText("Lembar Periksa Pasien");
        jMenu5.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jMenu5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jMenu5.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jMenu5.setIconTextGap(5);
        jMenu5.setName("jMenu5"); // NOI18N
        jMenu5.setPreferredSize(new java.awt.Dimension(260, 26));

        MnCheckList4.setBackground(new java.awt.Color(255, 255, 254));
        MnCheckList4.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnCheckList4.setForeground(new java.awt.Color(70, 70, 70));
        MnCheckList4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnCheckList4.setText("Lembar Periksa Pasien Kanan");
        MnCheckList4.setToolTipText("");
        MnCheckList4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnCheckList4.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnCheckList4.setIconTextGap(5);
        MnCheckList4.setName("MnCheckList4"); // NOI18N
        MnCheckList4.setPreferredSize(new java.awt.Dimension(240, 26));
        MnCheckList4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnCheckList4ActionPerformed(evt);
            }
        });
        jMenu5.add(MnCheckList4);

        MnCheckList5.setBackground(new java.awt.Color(255, 255, 254));
        MnCheckList5.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnCheckList5.setForeground(new java.awt.Color(70, 70, 70));
        MnCheckList5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnCheckList5.setText("Lembar Periksa Pasien Kiri");
        MnCheckList5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnCheckList5.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnCheckList5.setIconTextGap(5);
        MnCheckList5.setName("MnCheckList5"); // NOI18N
        MnCheckList5.setPreferredSize(new java.awt.Dimension(240, 26));
        MnCheckList5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnCheckList5ActionPerformed(evt);
            }
        });
        jMenu5.add(MnCheckList5);

        MnCheckList6.setBackground(new java.awt.Color(255, 255, 254));
        MnCheckList6.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnCheckList6.setForeground(new java.awt.Color(70, 70, 70));
        MnCheckList6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnCheckList6.setText("Lembar Periksa Pasien Kanan 2");
        MnCheckList6.setToolTipText("");
        MnCheckList6.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnCheckList6.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnCheckList6.setIconTextGap(5);
        MnCheckList6.setName("MnCheckList6"); // NOI18N
        MnCheckList6.setPreferredSize(new java.awt.Dimension(240, 26));
        MnCheckList6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnCheckList6ActionPerformed(evt);
            }
        });
        jMenu5.add(MnCheckList6);

        MnCheckList7.setBackground(new java.awt.Color(255, 255, 254));
        MnCheckList7.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnCheckList7.setForeground(new java.awt.Color(70, 70, 70));
        MnCheckList7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnCheckList7.setText("Lembar Periksa Pasien Kiri 2");
        MnCheckList7.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnCheckList7.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnCheckList7.setIconTextGap(5);
        MnCheckList7.setName("MnCheckList7"); // NOI18N
        MnCheckList7.setPreferredSize(new java.awt.Dimension(240, 26));
        MnCheckList7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnCheckList7ActionPerformed(evt);
            }
        });
        jMenu5.add(MnCheckList7);

        jPopupMenu1.add(jMenu5);

        jMenu6.setBackground(new java.awt.Color(250, 255, 245));
        jMenu6.setForeground(new java.awt.Color(70, 70, 70));
        jMenu6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        jMenu6.setText("Label & Barcode");
        jMenu6.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jMenu6.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jMenu6.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jMenu6.setIconTextGap(5);
        jMenu6.setName("jMenu6"); // NOI18N
        jMenu6.setPreferredSize(new java.awt.Dimension(260, 26));

        MnLabelTracker.setBackground(new java.awt.Color(255, 255, 254));
        MnLabelTracker.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnLabelTracker.setForeground(new java.awt.Color(70, 70, 70));
        MnLabelTracker.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnLabelTracker.setText("Label Tracker 1");
        MnLabelTracker.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnLabelTracker.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnLabelTracker.setIconTextGap(5);
        MnLabelTracker.setName("MnLabelTracker"); // NOI18N
        MnLabelTracker.setPreferredSize(new java.awt.Dimension(200, 26));
        MnLabelTracker.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnLabelTrackerActionPerformed(evt);
            }
        });
        jMenu6.add(MnLabelTracker);

        MnLabelTracker1.setBackground(new java.awt.Color(255, 255, 254));
        MnLabelTracker1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnLabelTracker1.setForeground(new java.awt.Color(70, 70, 70));
        MnLabelTracker1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnLabelTracker1.setText("Label Tracker 2");
        MnLabelTracker1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnLabelTracker1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnLabelTracker1.setIconTextGap(5);
        MnLabelTracker1.setName("MnLabelTracker1"); // NOI18N
        MnLabelTracker1.setPreferredSize(new java.awt.Dimension(200, 26));
        MnLabelTracker1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnLabelTracker1ActionPerformed(evt);
            }
        });
        jMenu6.add(MnLabelTracker1);

        MnLabelTracker2.setBackground(new java.awt.Color(255, 255, 254));
        MnLabelTracker2.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnLabelTracker2.setForeground(new java.awt.Color(70, 70, 70));
        MnLabelTracker2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnLabelTracker2.setText("Label Tracker 3");
        MnLabelTracker2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnLabelTracker2.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnLabelTracker2.setIconTextGap(5);
        MnLabelTracker2.setName("MnLabelTracker2"); // NOI18N
        MnLabelTracker2.setPreferredSize(new java.awt.Dimension(200, 26));
        MnLabelTracker2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnLabelTracker2ActionPerformed(evt);
            }
        });
        jMenu6.add(MnLabelTracker2);

        MnLabelTracker3.setBackground(new java.awt.Color(255, 255, 254));
        MnLabelTracker3.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnLabelTracker3.setForeground(new java.awt.Color(70, 70, 70));
        MnLabelTracker3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnLabelTracker3.setText("Label Tracker 4");
        MnLabelTracker3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnLabelTracker3.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnLabelTracker3.setIconTextGap(5);
        MnLabelTracker3.setName("MnLabelTracker3"); // NOI18N
        MnLabelTracker3.setPreferredSize(new java.awt.Dimension(200, 26));
        MnLabelTracker3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnLabelTracker3ActionPerformed(evt);
            }
        });
        jMenu6.add(MnLabelTracker3);

        MnBarcode.setBackground(new java.awt.Color(255, 255, 254));
        MnBarcode.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnBarcode.setForeground(new java.awt.Color(70, 70, 70));
        MnBarcode.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnBarcode.setText("Barcode Perawatan 1");
        MnBarcode.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnBarcode.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnBarcode.setIconTextGap(5);
        MnBarcode.setName("MnBarcode"); // NOI18N
        MnBarcode.setPreferredSize(new java.awt.Dimension(200, 26));
        MnBarcode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnBarcodeActionPerformed(evt);
            }
        });
        jMenu6.add(MnBarcode);

        MnBarcode1.setBackground(new java.awt.Color(255, 255, 254));
        MnBarcode1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnBarcode1.setForeground(new java.awt.Color(70, 70, 70));
        MnBarcode1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnBarcode1.setText("Barcode Perawatan 2");
        MnBarcode1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnBarcode1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnBarcode1.setIconTextGap(5);
        MnBarcode1.setName("MnBarcode1"); // NOI18N
        MnBarcode1.setPreferredSize(new java.awt.Dimension(200, 26));
        MnBarcode1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnBarcode1ActionPerformed(evt);
            }
        });
        jMenu6.add(MnBarcode1);

        MnBarcodeRM9.setBackground(new java.awt.Color(255, 255, 254));
        MnBarcodeRM9.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnBarcodeRM9.setForeground(new java.awt.Color(70, 70, 70));
        MnBarcodeRM9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnBarcodeRM9.setMnemonic('L');
        MnBarcodeRM9.setText("Label Rekam Medis 10");
        MnBarcodeRM9.setToolTipText("L");
        MnBarcodeRM9.setIconTextGap(5);
        MnBarcodeRM9.setName("MnBarcodeRM9"); // NOI18N
        MnBarcodeRM9.setPreferredSize(new java.awt.Dimension(200, 26));
        MnBarcodeRM9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnBarcodeRM9ActionPerformed(evt);
            }
        });
        jMenu6.add(MnBarcodeRM9);

        MnGelang1.setBackground(new java.awt.Color(255, 255, 254));
        MnGelang1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnGelang1.setForeground(new java.awt.Color(70, 70, 70));
        MnGelang1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnGelang1.setText("Gelang Pasien 1");
        MnGelang1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnGelang1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnGelang1.setIconTextGap(5);
        MnGelang1.setName("MnGelang1"); // NOI18N
        MnGelang1.setPreferredSize(new java.awt.Dimension(200, 26));
        MnGelang1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnGelang1ActionPerformed(evt);
            }
        });
        jMenu6.add(MnGelang1);

        MnGelang2.setBackground(new java.awt.Color(255, 255, 254));
        MnGelang2.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnGelang2.setForeground(new java.awt.Color(70, 70, 70));
        MnGelang2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnGelang2.setText("Gelang Pasien 2");
        MnGelang2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnGelang2.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnGelang2.setIconTextGap(5);
        MnGelang2.setName("MnGelang2"); // NOI18N
        MnGelang2.setPreferredSize(new java.awt.Dimension(200, 26));
        MnGelang2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnGelang2ActionPerformed(evt);
            }
        });
        jMenu6.add(MnGelang2);

        MnGelang3.setBackground(new java.awt.Color(255, 255, 254));
        MnGelang3.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnGelang3.setForeground(new java.awt.Color(70, 70, 70));
        MnGelang3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnGelang3.setText("Gelang Pasien 3");
        MnGelang3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnGelang3.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnGelang3.setIconTextGap(5);
        MnGelang3.setName("MnGelang3"); // NOI18N
        MnGelang3.setPreferredSize(new java.awt.Dimension(200, 26));
        MnGelang3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnGelang3ActionPerformed(evt);
            }
        });
        jMenu6.add(MnGelang3);

        MnGelang4.setBackground(new java.awt.Color(255, 255, 254));
        MnGelang4.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnGelang4.setForeground(new java.awt.Color(70, 70, 70));
        MnGelang4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnGelang4.setText("Gelang Pasien 4");
        MnGelang4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnGelang4.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnGelang4.setIconTextGap(5);
        MnGelang4.setName("MnGelang4"); // NOI18N
        MnGelang4.setPreferredSize(new java.awt.Dimension(200, 26));
        MnGelang4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnGelang4ActionPerformed(evt);
            }
        });
        jMenu6.add(MnGelang4);

        MnGelang5.setBackground(new java.awt.Color(255, 255, 254));
        MnGelang5.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnGelang5.setForeground(new java.awt.Color(70, 70, 70));
        MnGelang5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnGelang5.setText("Gelang Pasien 5");
        MnGelang5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnGelang5.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnGelang5.setIconTextGap(5);
        MnGelang5.setName("MnGelang5"); // NOI18N
        MnGelang5.setPreferredSize(new java.awt.Dimension(200, 26));
        MnGelang5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnGelang5ActionPerformed(evt);
            }
        });
        jMenu6.add(MnGelang5);

        MnGelang6.setBackground(new java.awt.Color(255, 255, 254));
        MnGelang6.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnGelang6.setForeground(new java.awt.Color(70, 70, 70));
        MnGelang6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnGelang6.setText("Gelang Pasien 6");
        MnGelang6.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnGelang6.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnGelang6.setIconTextGap(5);
        MnGelang6.setName("MnGelang6"); // NOI18N
        MnGelang6.setPreferredSize(new java.awt.Dimension(200, 26));
        MnGelang6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnGelang6ActionPerformed(evt);
            }
        });
        jMenu6.add(MnGelang6);

        MnGelang7.setBackground(new java.awt.Color(255, 255, 254));
        MnGelang7.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnGelang7.setForeground(new java.awt.Color(70, 70, 70));
        MnGelang7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnGelang7.setText("Gelang Pasien 7");
        MnGelang7.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnGelang7.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnGelang7.setIconTextGap(5);
        MnGelang7.setName("MnGelang7"); // NOI18N
        MnGelang7.setPreferredSize(new java.awt.Dimension(200, 26));
        MnGelang7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnGelang7ActionPerformed(evt);
            }
        });
        jMenu6.add(MnGelang7);

        jPopupMenu1.add(jMenu6);

        jSeparator11.setBackground(new java.awt.Color(190, 220, 180));
        jSeparator11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(190, 220, 180)));
        jSeparator11.setForeground(new java.awt.Color(190, 220, 180));
        jSeparator11.setName("jSeparator11"); // NOI18N
        jSeparator11.setPreferredSize(new java.awt.Dimension(260, 1));
        jPopupMenu1.add(jSeparator11);

        MnRujukan.setBackground(new java.awt.Color(250, 255, 245));
        MnRujukan.setForeground(new java.awt.Color(70, 70, 70));
        MnRujukan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnRujukan.setText("Rujukan");
        MnRujukan.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnRujukan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnRujukan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnRujukan.setIconTextGap(5);
        MnRujukan.setName("MnRujukan"); // NOI18N
        MnRujukan.setPreferredSize(new java.awt.Dimension(260, 26));

        MnRujukMasuk.setBackground(new java.awt.Color(255, 255, 254));
        MnRujukMasuk.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnRujukMasuk.setForeground(new java.awt.Color(70, 70, 70));
        MnRujukMasuk.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnRujukMasuk.setText("Masuk");
        MnRujukMasuk.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnRujukMasuk.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnRujukMasuk.setIconTextGap(5);
        MnRujukMasuk.setName("MnRujukMasuk"); // NOI18N
        MnRujukMasuk.setPreferredSize(new java.awt.Dimension(160, 26));
        MnRujukMasuk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnRujukMasukActionPerformed(evt);
            }
        });
        MnRujukan.add(MnRujukMasuk);

        MnRujuk.setBackground(new java.awt.Color(255, 255, 254));
        MnRujuk.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnRujuk.setForeground(new java.awt.Color(70, 70, 70));
        MnRujuk.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnRujuk.setText("Keluar");
        MnRujuk.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnRujuk.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnRujuk.setIconTextGap(5);
        MnRujuk.setName("MnRujuk"); // NOI18N
        MnRujuk.setPreferredSize(new java.awt.Dimension(160, 26));
        MnRujuk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnRujukActionPerformed(evt);
            }
        });
        MnRujukan.add(MnRujuk);

        MnPoliInternal.setBackground(new java.awt.Color(255, 255, 254));
        MnPoliInternal.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnPoliInternal.setForeground(new java.awt.Color(70, 70, 70));
        MnPoliInternal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnPoliInternal.setText("Poli Internal");
        MnPoliInternal.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnPoliInternal.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnPoliInternal.setIconTextGap(5);
        MnPoliInternal.setName("MnPoliInternal"); // NOI18N
        MnPoliInternal.setPreferredSize(new java.awt.Dimension(160, 26));
        MnPoliInternal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnPoliInternalActionPerformed(evt);
            }
        });
        MnRujukan.add(MnPoliInternal);

        MnJawabanKonsul.setBackground(new java.awt.Color(255, 255, 254));
        MnJawabanKonsul.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnJawabanKonsul.setForeground(new java.awt.Color(70, 70, 70));
        MnJawabanKonsul.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnJawabanKonsul.setText("Jawaban Konsul");
        MnJawabanKonsul.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnJawabanKonsul.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnJawabanKonsul.setIconTextGap(5);
        MnJawabanKonsul.setName("MnJawabanKonsul"); // NOI18N
        MnJawabanKonsul.setPreferredSize(new java.awt.Dimension(160, 26));
        MnJawabanKonsul.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnJawabanKonsulActionPerformed(evt);
            }
        });
        MnRujukan.add(MnJawabanKonsul);

        jPopupMenu1.add(MnRujukan);

        MnBridging.setBackground(new java.awt.Color(250, 255, 245));
        MnBridging.setForeground(new java.awt.Color(70, 70, 70));
        MnBridging.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnBridging.setText("Bridging");
        MnBridging.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnBridging.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnBridging.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnBridging.setIconTextGap(5);
        MnBridging.setName("MnBridging"); // NOI18N
        MnBridging.setPreferredSize(new java.awt.Dimension(260, 26));

        MnSEP.setBackground(new java.awt.Color(255, 255, 254));
        MnSEP.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnSEP.setForeground(new java.awt.Color(70, 70, 70));
        MnSEP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnSEP.setText("SEP BPJS");
        MnSEP.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnSEP.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnSEP.setIconTextGap(5);
        MnSEP.setName("MnSEP"); // NOI18N
        MnSEP.setPreferredSize(new java.awt.Dimension(180, 26));
        MnSEP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnSEPActionPerformed(evt);
            }
        });
        MnBridging.add(MnSEP);

        MnSJP.setBackground(new java.awt.Color(255, 255, 254));
        MnSJP.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnSJP.setForeground(new java.awt.Color(70, 70, 70));
        MnSJP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnSJP.setText("SJP Inhealth");
        MnSJP.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnSJP.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnSJP.setIconTextGap(5);
        MnSJP.setName("MnSJP"); // NOI18N
        MnSJP.setPreferredSize(new java.awt.Dimension(180, 26));
        MnSJP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnSJPActionPerformed(evt);
            }
        });
        MnBridging.add(MnSJP);

        MnPCare.setBackground(new java.awt.Color(255, 255, 254));
        MnPCare.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnPCare.setForeground(new java.awt.Color(70, 70, 70));
        MnPCare.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnPCare.setText("Pendaftaran PCare");
        MnPCare.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnPCare.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnPCare.setIconTextGap(5);
        MnPCare.setName("MnPCare"); // NOI18N
        MnPCare.setPreferredSize(new java.awt.Dimension(180, 26));
        MnPCare.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnPCareActionPerformed(evt);
            }
        });
        MnBridging.add(MnPCare);

        MnRujukSisrute.setBackground(new java.awt.Color(255, 255, 254));
        MnRujukSisrute.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnRujukSisrute.setForeground(new java.awt.Color(70, 70, 70));
        MnRujukSisrute.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnRujukSisrute.setText("Rujuk Keluar Via Sisrute");
        MnRujukSisrute.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnRujukSisrute.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnRujukSisrute.setIconTextGap(5);
        MnRujukSisrute.setName("MnRujukSisrute"); // NOI18N
        MnRujukSisrute.setPreferredSize(new java.awt.Dimension(180, 26));
        MnRujukSisrute.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnRujukSisruteActionPerformed(evt);
            }
        });
        MnBridging.add(MnRujukSisrute);

        jPopupMenu1.add(MnBridging);

        MenuInputData.setBackground(new java.awt.Color(250, 255, 245));
        MenuInputData.setForeground(new java.awt.Color(70, 70, 70));
        MenuInputData.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MenuInputData.setText("Input Data");
        MenuInputData.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MenuInputData.setIconTextGap(5);
        MenuInputData.setName("MenuInputData"); // NOI18N
        MenuInputData.setPreferredSize(new java.awt.Dimension(260, 26));

        MnDiagnosa.setBackground(new java.awt.Color(255, 255, 254));
        MnDiagnosa.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnDiagnosa.setForeground(new java.awt.Color(70, 70, 70));
        MnDiagnosa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnDiagnosa.setText("Diagnosa Pasien");
        MnDiagnosa.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnDiagnosa.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnDiagnosa.setIconTextGap(5);
        MnDiagnosa.setName("MnDiagnosa"); // NOI18N
        MnDiagnosa.setPreferredSize(new java.awt.Dimension(200, 26));
        MnDiagnosa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnDiagnosaActionPerformed(evt);
            }
        });
        MenuInputData.add(MnDiagnosa);

        ppCatatanPasien.setBackground(new java.awt.Color(255, 255, 254));
        ppCatatanPasien.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppCatatanPasien.setForeground(new java.awt.Color(70, 70, 70));
        ppCatatanPasien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppCatatanPasien.setText("Catatan Untuk Pasien");
        ppCatatanPasien.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppCatatanPasien.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppCatatanPasien.setIconTextGap(5);
        ppCatatanPasien.setName("ppCatatanPasien"); // NOI18N
        ppCatatanPasien.setPreferredSize(new java.awt.Dimension(200, 26));
        ppCatatanPasien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppCatatanPasienBtnPrintActionPerformed(evt);
            }
        });
        MenuInputData.add(ppCatatanPasien);

        ppBerkasDigital.setBackground(new java.awt.Color(255, 255, 254));
        ppBerkasDigital.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppBerkasDigital.setForeground(new java.awt.Color(70, 70, 70));
        ppBerkasDigital.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppBerkasDigital.setText("Berkas Digital Perawatan");
        ppBerkasDigital.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppBerkasDigital.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppBerkasDigital.setIconTextGap(5);
        ppBerkasDigital.setName("ppBerkasDigital"); // NOI18N
        ppBerkasDigital.setPreferredSize(new java.awt.Dimension(200, 26));
        ppBerkasDigital.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppBerkasDigitalBtnPrintActionPerformed(evt);
            }
        });
        MenuInputData.add(ppBerkasDigital);

        ppIKP.setBackground(new java.awt.Color(255, 255, 254));
        ppIKP.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppIKP.setForeground(new java.awt.Color(70, 70, 70));
        ppIKP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppIKP.setText("Insiden Keselamatan Pasien");
        ppIKP.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppIKP.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppIKP.setIconTextGap(5);
        ppIKP.setName("ppIKP"); // NOI18N
        ppIKP.setPreferredSize(new java.awt.Dimension(200, 26));
        ppIKP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppIKPBtnPrintActionPerformed(evt);
            }
        });
        MenuInputData.add(ppIKP);

        MnTeridentifikasiTB.setBackground(new java.awt.Color(255, 255, 254));
        MnTeridentifikasiTB.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnTeridentifikasiTB.setForeground(new java.awt.Color(70, 70, 70));
        MnTeridentifikasiTB.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnTeridentifikasiTB.setText("Teridentifikasi TB");
        MnTeridentifikasiTB.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnTeridentifikasiTB.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnTeridentifikasiTB.setIconTextGap(5);
        MnTeridentifikasiTB.setName("MnTeridentifikasiTB"); // NOI18N
        MnTeridentifikasiTB.setPreferredSize(new java.awt.Dimension(200, 26));
        MnTeridentifikasiTB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnTeridentifikasiTBActionPerformed(evt);
            }
        });
        MenuInputData.add(MnTeridentifikasiTB);

        jPopupMenu1.add(MenuInputData);

        MnStatus.setBackground(new java.awt.Color(250, 255, 245));
        MnStatus.setForeground(new java.awt.Color(70, 70, 70));
        MnStatus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnStatus.setText("Set Status");
        MnStatus.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnStatus.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnStatus.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnStatus.setIconTextGap(5);
        MnStatus.setName("MnStatus"); // NOI18N
        MnStatus.setPreferredSize(new java.awt.Dimension(260, 26));

        ppBerkas.setBackground(new java.awt.Color(255, 255, 254));
        ppBerkas.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppBerkas.setForeground(new java.awt.Color(70, 70, 70));
        ppBerkas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppBerkas.setText("Berkas R.M. Diterima");
        ppBerkas.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppBerkas.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppBerkas.setIconTextGap(5);
        ppBerkas.setName("ppBerkas"); // NOI18N
        ppBerkas.setPreferredSize(new java.awt.Dimension(180, 26));
        ppBerkas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppBerkasBtnPrintActionPerformed(evt);
            }
        });
        MnStatus.add(ppBerkas);

        MnSudah.setBackground(new java.awt.Color(255, 255, 254));
        MnSudah.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnSudah.setForeground(new java.awt.Color(70, 70, 70));
        MnSudah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnSudah.setText("Sudah Periksa");
        MnSudah.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnSudah.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnSudah.setIconTextGap(5);
        MnSudah.setName("MnSudah"); // NOI18N
        MnSudah.setPreferredSize(new java.awt.Dimension(180, 26));
        MnSudah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnSudahActionPerformed(evt);
            }
        });
        MnStatus.add(MnSudah);

        MnBelum.setBackground(new java.awt.Color(255, 255, 254));
        MnBelum.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnBelum.setForeground(new java.awt.Color(70, 70, 70));
        MnBelum.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnBelum.setText("Belum Periksa");
        MnBelum.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnBelum.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnBelum.setIconTextGap(5);
        MnBelum.setName("MnBelum"); // NOI18N
        MnBelum.setPreferredSize(new java.awt.Dimension(180, 26));
        MnBelum.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnBelumActionPerformed(evt);
            }
        });
        MnStatus.add(MnBelum);

        MnBatal.setBackground(new java.awt.Color(255, 255, 254));
        MnBatal.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnBatal.setForeground(new java.awt.Color(70, 70, 70));
        MnBatal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnBatal.setText("Batal Periksa");
        MnBatal.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnBatal.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnBatal.setIconTextGap(5);
        MnBatal.setName("MnBatal"); // NOI18N
        MnBatal.setPreferredSize(new java.awt.Dimension(180, 26));
        MnBatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnBatalActionPerformed(evt);
            }
        });
        MnStatus.add(MnBatal);

        MnDirujuk.setBackground(new java.awt.Color(255, 255, 254));
        MnDirujuk.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnDirujuk.setForeground(new java.awt.Color(70, 70, 70));
        MnDirujuk.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnDirujuk.setText("Dirujuk");
        MnDirujuk.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnDirujuk.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnDirujuk.setIconTextGap(5);
        MnDirujuk.setName("MnDirujuk"); // NOI18N
        MnDirujuk.setPreferredSize(new java.awt.Dimension(180, 26));
        MnDirujuk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnDirujukActionPerformed(evt);
            }
        });
        MnStatus.add(MnDirujuk);

        MnDIrawat.setBackground(new java.awt.Color(255, 255, 254));
        MnDIrawat.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnDIrawat.setForeground(new java.awt.Color(70, 70, 70));
        MnDIrawat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnDIrawat.setText("Dirawat");
        MnDIrawat.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnDIrawat.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnDIrawat.setIconTextGap(5);
        MnDIrawat.setName("MnDIrawat"); // NOI18N
        MnDIrawat.setPreferredSize(new java.awt.Dimension(180, 26));
        MnDIrawat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnDIrawatActionPerformed(evt);
            }
        });
        MnStatus.add(MnDIrawat);

        MnMeninggal.setBackground(new java.awt.Color(255, 255, 254));
        MnMeninggal.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnMeninggal.setForeground(new java.awt.Color(70, 70, 70));
        MnMeninggal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnMeninggal.setText("Meninggal");
        MnMeninggal.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnMeninggal.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnMeninggal.setIconTextGap(5);
        MnMeninggal.setName("MnMeninggal"); // NOI18N
        MnMeninggal.setPreferredSize(new java.awt.Dimension(180, 26));
        MnMeninggal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnMeninggalActionPerformed(evt);
            }
        });
        MnStatus.add(MnMeninggal);

        MnPulangPaksa.setBackground(new java.awt.Color(255, 255, 254));
        MnPulangPaksa.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnPulangPaksa.setForeground(new java.awt.Color(70, 70, 70));
        MnPulangPaksa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnPulangPaksa.setText("Pulang Paksa");
        MnPulangPaksa.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnPulangPaksa.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnPulangPaksa.setIconTextGap(5);
        MnPulangPaksa.setName("MnPulangPaksa"); // NOI18N
        MnPulangPaksa.setPreferredSize(new java.awt.Dimension(180, 26));
        MnPulangPaksa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnPulangPaksaBtnPrintActionPerformed(evt);
            }
        });
        MnStatus.add(MnPulangPaksa);

        jMenu7.setBackground(new java.awt.Color(255, 255, 254));
        jMenu7.setForeground(new java.awt.Color(70, 70, 70));
        jMenu7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        jMenu7.setText("Status Poli");
        jMenu7.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jMenu7.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jMenu7.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jMenu7.setIconTextGap(5);
        jMenu7.setName("jMenu7"); // NOI18N
        jMenu7.setPreferredSize(new java.awt.Dimension(240, 26));

        MnStatusBaru.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnStatusBaru.setForeground(new java.awt.Color(70, 70, 70));
        MnStatusBaru.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnStatusBaru.setText("Status Poli Baru");
        MnStatusBaru.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnStatusBaru.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnStatusBaru.setIconTextGap(5);
        MnStatusBaru.setName("MnStatusBaru"); // NOI18N
        MnStatusBaru.setPreferredSize(new java.awt.Dimension(170, 26));
        MnStatusBaru.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnStatusBaruActionPerformed(evt);
            }
        });
        jMenu7.add(MnStatusBaru);

        MnStatusLama.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnStatusLama.setForeground(new java.awt.Color(70, 70, 70));
        MnStatusLama.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnStatusLama.setText("Status Poli Lama");
        MnStatusLama.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnStatusLama.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnStatusLama.setIconTextGap(5);
        MnStatusLama.setName("MnStatusLama"); // NOI18N
        MnStatusLama.setPreferredSize(new java.awt.Dimension(170, 26));
        MnStatusLama.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnStatusLamaActionPerformed(evt);
            }
        });
        jMenu7.add(MnStatusLama);

        MnStatus.add(jMenu7);

        jPopupMenu1.add(MnStatus);

        MnUrut.setBackground(new java.awt.Color(250, 255, 245));
        MnUrut.setForeground(new java.awt.Color(70, 70, 70));
        MnUrut.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnUrut.setText("Urutkan Data Berdasar");
        MnUrut.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnUrut.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnUrut.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnUrut.setIconTextGap(5);
        MnUrut.setName("MnUrut"); // NOI18N
        MnUrut.setPreferredSize(new java.awt.Dimension(260, 26));

        MnUrutNoRawatDesc.setBackground(new java.awt.Color(255, 255, 254));
        MnUrutNoRawatDesc.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnUrutNoRawatDesc.setForeground(new java.awt.Color(70, 70, 70));
        MnUrutNoRawatDesc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnUrutNoRawatDesc.setText("No.Rawat Descending");
        MnUrutNoRawatDesc.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnUrutNoRawatDesc.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnUrutNoRawatDesc.setIconTextGap(5);
        MnUrutNoRawatDesc.setName("MnUrutNoRawatDesc"); // NOI18N
        MnUrutNoRawatDesc.setPreferredSize(new java.awt.Dimension(180, 26));
        MnUrutNoRawatDesc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnUrutNoRawatDescActionPerformed(evt);
            }
        });
        MnUrut.add(MnUrutNoRawatDesc);

        MnUrutNoRawatAsc.setBackground(new java.awt.Color(255, 255, 254));
        MnUrutNoRawatAsc.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnUrutNoRawatAsc.setForeground(new java.awt.Color(70, 70, 70));
        MnUrutNoRawatAsc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnUrutNoRawatAsc.setText("No.Rawat Ascending");
        MnUrutNoRawatAsc.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnUrutNoRawatAsc.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnUrutNoRawatAsc.setIconTextGap(5);
        MnUrutNoRawatAsc.setName("MnUrutNoRawatAsc"); // NOI18N
        MnUrutNoRawatAsc.setPreferredSize(new java.awt.Dimension(180, 26));
        MnUrutNoRawatAsc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnUrutNoRawatAscActionPerformed(evt);
            }
        });
        MnUrut.add(MnUrutNoRawatAsc);

        MnUrutTanggalDesc.setBackground(new java.awt.Color(255, 255, 254));
        MnUrutTanggalDesc.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnUrutTanggalDesc.setForeground(new java.awt.Color(70, 70, 70));
        MnUrutTanggalDesc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnUrutTanggalDesc.setText("Tanggal Descending");
        MnUrutTanggalDesc.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnUrutTanggalDesc.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnUrutTanggalDesc.setIconTextGap(5);
        MnUrutTanggalDesc.setName("MnUrutTanggalDesc"); // NOI18N
        MnUrutTanggalDesc.setPreferredSize(new java.awt.Dimension(180, 26));
        MnUrutTanggalDesc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnUrutTanggalDescActionPerformed(evt);
            }
        });
        MnUrut.add(MnUrutTanggalDesc);

        MnUrutTanggalAsc.setBackground(new java.awt.Color(255, 255, 254));
        MnUrutTanggalAsc.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnUrutTanggalAsc.setForeground(new java.awt.Color(70, 70, 70));
        MnUrutTanggalAsc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnUrutTanggalAsc.setText("Tanggal Ascending");
        MnUrutTanggalAsc.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnUrutTanggalAsc.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnUrutTanggalAsc.setIconTextGap(5);
        MnUrutTanggalAsc.setName("MnUrutTanggalAsc"); // NOI18N
        MnUrutTanggalAsc.setPreferredSize(new java.awt.Dimension(180, 26));
        MnUrutTanggalAsc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnUrutTanggalAscActionPerformed(evt);
            }
        });
        MnUrut.add(MnUrutTanggalAsc);

        MnUrutDokterDesc.setBackground(new java.awt.Color(255, 255, 254));
        MnUrutDokterDesc.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnUrutDokterDesc.setForeground(new java.awt.Color(70, 70, 70));
        MnUrutDokterDesc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnUrutDokterDesc.setText("Dokter Descending");
        MnUrutDokterDesc.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnUrutDokterDesc.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnUrutDokterDesc.setIconTextGap(5);
        MnUrutDokterDesc.setName("MnUrutDokterDesc"); // NOI18N
        MnUrutDokterDesc.setPreferredSize(new java.awt.Dimension(180, 26));
        MnUrutDokterDesc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnUrutDokterDescActionPerformed(evt);
            }
        });
        MnUrut.add(MnUrutDokterDesc);

        MnUrutDokterAsc.setBackground(new java.awt.Color(255, 255, 254));
        MnUrutDokterAsc.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnUrutDokterAsc.setForeground(new java.awt.Color(70, 70, 70));
        MnUrutDokterAsc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnUrutDokterAsc.setText("Dokter Ascending");
        MnUrutDokterAsc.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnUrutDokterAsc.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnUrutDokterAsc.setIconTextGap(5);
        MnUrutDokterAsc.setName("MnUrutDokterAsc"); // NOI18N
        MnUrutDokterAsc.setPreferredSize(new java.awt.Dimension(180, 26));
        MnUrutDokterAsc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnUrutDokterAscActionPerformed(evt);
            }
        });
        MnUrut.add(MnUrutDokterAsc);

        MnUrutPoliklinikDesc.setBackground(new java.awt.Color(255, 255, 254));
        MnUrutPoliklinikDesc.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnUrutPoliklinikDesc.setForeground(new java.awt.Color(70, 70, 70));
        MnUrutPoliklinikDesc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnUrutPoliklinikDesc.setText("Poli/Unit Descending");
        MnUrutPoliklinikDesc.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnUrutPoliklinikDesc.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnUrutPoliklinikDesc.setIconTextGap(5);
        MnUrutPoliklinikDesc.setName("MnUrutPoliklinikDesc"); // NOI18N
        MnUrutPoliklinikDesc.setPreferredSize(new java.awt.Dimension(180, 26));
        MnUrutPoliklinikDesc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnUrutPoliklinikDescActionPerformed(evt);
            }
        });
        MnUrut.add(MnUrutPoliklinikDesc);

        MnUrutPoliklinikAsc.setBackground(new java.awt.Color(255, 255, 254));
        MnUrutPoliklinikAsc.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnUrutPoliklinikAsc.setForeground(new java.awt.Color(70, 70, 70));
        MnUrutPoliklinikAsc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnUrutPoliklinikAsc.setText("Poli/Unit Ascending");
        MnUrutPoliklinikAsc.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnUrutPoliklinikAsc.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnUrutPoliklinikAsc.setIconTextGap(5);
        MnUrutPoliklinikAsc.setName("MnUrutPoliklinikAsc"); // NOI18N
        MnUrutPoliklinikAsc.setPreferredSize(new java.awt.Dimension(180, 26));
        MnUrutPoliklinikAsc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnUrutPoliklinikAscActionPerformed(evt);
            }
        });
        MnUrut.add(MnUrutPoliklinikAsc);

        MnUrutPenjabDesc.setBackground(new java.awt.Color(255, 255, 254));
        MnUrutPenjabDesc.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnUrutPenjabDesc.setForeground(new java.awt.Color(70, 70, 70));
        MnUrutPenjabDesc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnUrutPenjabDesc.setText("Cara Bayar Descending");
        MnUrutPenjabDesc.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnUrutPenjabDesc.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnUrutPenjabDesc.setIconTextGap(5);
        MnUrutPenjabDesc.setName("MnUrutPenjabDesc"); // NOI18N
        MnUrutPenjabDesc.setPreferredSize(new java.awt.Dimension(180, 26));
        MnUrutPenjabDesc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnUrutPenjabDescActionPerformed(evt);
            }
        });
        MnUrut.add(MnUrutPenjabDesc);

        MnUrutPenjabAsc.setBackground(new java.awt.Color(255, 255, 254));
        MnUrutPenjabAsc.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnUrutPenjabAsc.setForeground(new java.awt.Color(70, 70, 70));
        MnUrutPenjabAsc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnUrutPenjabAsc.setText("Cara Bayar Ascending");
        MnUrutPenjabAsc.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnUrutPenjabAsc.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnUrutPenjabAsc.setIconTextGap(5);
        MnUrutPenjabAsc.setName("MnUrutPenjabAsc"); // NOI18N
        MnUrutPenjabAsc.setPreferredSize(new java.awt.Dimension(180, 26));
        MnUrutPenjabAsc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnUrutPenjabAscActionPerformed(evt);
            }
        });
        MnUrut.add(MnUrutPenjabAsc);

        MnUrutStatusDesc.setBackground(new java.awt.Color(255, 255, 254));
        MnUrutStatusDesc.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnUrutStatusDesc.setForeground(new java.awt.Color(70, 70, 70));
        MnUrutStatusDesc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnUrutStatusDesc.setText("Status Descending");
        MnUrutStatusDesc.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnUrutStatusDesc.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnUrutStatusDesc.setIconTextGap(5);
        MnUrutStatusDesc.setName("MnUrutStatusDesc"); // NOI18N
        MnUrutStatusDesc.setPreferredSize(new java.awt.Dimension(180, 26));
        MnUrutStatusDesc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnUrutStatusDescActionPerformed(evt);
            }
        });
        MnUrut.add(MnUrutStatusDesc);

        MnUrutStatusAsc.setBackground(new java.awt.Color(255, 255, 254));
        MnUrutStatusAsc.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnUrutStatusAsc.setForeground(new java.awt.Color(70, 70, 70));
        MnUrutStatusAsc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnUrutStatusAsc.setText("Status Ascending");
        MnUrutStatusAsc.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnUrutStatusAsc.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnUrutStatusAsc.setIconTextGap(5);
        MnUrutStatusAsc.setName("MnUrutStatusAsc"); // NOI18N
        MnUrutStatusAsc.setPreferredSize(new java.awt.Dimension(180, 26));
        MnUrutStatusAsc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnUrutStatusAscActionPerformed(evt);
            }
        });
        MnUrut.add(MnUrutStatusAsc);

        MnUrutRegDesc1.setBackground(new java.awt.Color(255, 255, 254));
        MnUrutRegDesc1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnUrutRegDesc1.setForeground(new java.awt.Color(70, 70, 70));
        MnUrutRegDesc1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnUrutRegDesc1.setText("No. Reg. Descending");
        MnUrutRegDesc1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnUrutRegDesc1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnUrutRegDesc1.setIconTextGap(5);
        MnUrutRegDesc1.setName("MnUrutRegDesc1"); // NOI18N
        MnUrutRegDesc1.setPreferredSize(new java.awt.Dimension(180, 26));
        MnUrutRegDesc1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnUrutRegDesc1ActionPerformed(evt);
            }
        });
        MnUrut.add(MnUrutRegDesc1);

        MnUrutRegAsc1.setBackground(new java.awt.Color(255, 255, 254));
        MnUrutRegAsc1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnUrutRegAsc1.setForeground(new java.awt.Color(70, 70, 70));
        MnUrutRegAsc1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnUrutRegAsc1.setText("No. Reg. Ascending");
        MnUrutRegAsc1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnUrutRegAsc1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnUrutRegAsc1.setIconTextGap(5);
        MnUrutRegAsc1.setName("MnUrutRegAsc1"); // NOI18N
        MnUrutRegAsc1.setPreferredSize(new java.awt.Dimension(180, 26));
        MnUrutRegAsc1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnUrutRegAsc1ActionPerformed(evt);
            }
        });
        MnUrut.add(MnUrutRegAsc1);

        jPopupMenu1.add(MnUrut);

        MnInputHP.setBackground(new java.awt.Color(255, 255, 254));
        MnInputHP.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnInputHP.setForeground(new java.awt.Color(70, 70, 70));
        MnInputHP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnInputHP.setText("Input No. Hp Pasien");
        MnInputHP.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnInputHP.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnInputHP.setIconTextGap(5);
        MnInputHP.setName("MnInputHP"); // NOI18N
        MnInputHP.setPreferredSize(new java.awt.Dimension(260, 26));
        MnInputHP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnInputHPBtnPrintActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnInputHP);

        MnInterpretasiEkg.setBackground(new java.awt.Color(255, 255, 254));
        MnInterpretasiEkg.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnInterpretasiEkg.setForeground(new java.awt.Color(70, 70, 70));
        MnInterpretasiEkg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnInterpretasiEkg.setText("Interpretasi EKG");
        MnInterpretasiEkg.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnInterpretasiEkg.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnInterpretasiEkg.setIconTextGap(5);
        MnInterpretasiEkg.setName("MnInterpretasiEkg"); // NOI18N
        MnInterpretasiEkg.setPreferredSize(new java.awt.Dimension(260, 26));
        MnInterpretasiEkg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnInterpretasiEkgBtnPrintActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnInterpretasiEkg);

        MnHemodialisa.setBackground(new java.awt.Color(255, 255, 254));
        MnHemodialisa.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnHemodialisa.setForeground(new java.awt.Color(70, 70, 70));
        MnHemodialisa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnHemodialisa.setText("Hemodialisa");
        MnHemodialisa.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnHemodialisa.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnHemodialisa.setIconTextGap(5);
        MnHemodialisa.setName("MnHemodialisa"); // NOI18N
        MnHemodialisa.setPreferredSize(new java.awt.Dimension(260, 26));
        MnHemodialisa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnHemodialisaActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnHemodialisa);

        ppRiwayat.setBackground(new java.awt.Color(255, 255, 254));
        ppRiwayat.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppRiwayat.setForeground(new java.awt.Color(70, 70, 70));
        ppRiwayat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppRiwayat.setText("Riwayat Perawatan");
        ppRiwayat.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppRiwayat.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppRiwayat.setIconTextGap(5);
        ppRiwayat.setName("ppRiwayat"); // NOI18N
        ppRiwayat.setPreferredSize(new java.awt.Dimension(260, 26));
        ppRiwayat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppRiwayatBtnPrintActionPerformed(evt);
            }
        });
        jPopupMenu1.add(ppRiwayat);

        MnRiwayatObatRacikan.setBackground(new java.awt.Color(255, 255, 254));
        MnRiwayatObatRacikan.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnRiwayatObatRacikan.setForeground(new java.awt.Color(70, 70, 70));
        MnRiwayatObatRacikan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnRiwayatObatRacikan.setText("Riwayat Obat Racikan");
        MnRiwayatObatRacikan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnRiwayatObatRacikan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnRiwayatObatRacikan.setIconTextGap(5);
        MnRiwayatObatRacikan.setName("MnRiwayatObatRacikan"); // NOI18N
        MnRiwayatObatRacikan.setPreferredSize(new java.awt.Dimension(260, 26));
        MnRiwayatObatRacikan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnRiwayatObatRacikanBtnPrintActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnRiwayatObatRacikan);

        MnHapusData.setBackground(new java.awt.Color(250, 255, 245));
        MnHapusData.setForeground(new java.awt.Color(70, 70, 70));
        MnHapusData.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnHapusData.setText("Hapus Data");
        MnHapusData.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnHapusData.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnHapusData.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnHapusData.setIconTextGap(5);
        MnHapusData.setName("MnHapusData"); // NOI18N
        MnHapusData.setPreferredSize(new java.awt.Dimension(260, 26));

        MnHapusTagihanOperasi.setBackground(new java.awt.Color(255, 255, 254));
        MnHapusTagihanOperasi.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnHapusTagihanOperasi.setForeground(new java.awt.Color(70, 70, 70));
        MnHapusTagihanOperasi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnHapusTagihanOperasi.setText("Hapus Tagihan Operasi");
        MnHapusTagihanOperasi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnHapusTagihanOperasi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnHapusTagihanOperasi.setIconTextGap(5);
        MnHapusTagihanOperasi.setName("MnHapusTagihanOperasi"); // NOI18N
        MnHapusTagihanOperasi.setPreferredSize(new java.awt.Dimension(200, 26));
        MnHapusTagihanOperasi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnHapusTagihanOperasiActionPerformed(evt);
            }
        });
        MnHapusData.add(MnHapusTagihanOperasi);

        MnHapusObatOperasi.setBackground(new java.awt.Color(255, 255, 254));
        MnHapusObatOperasi.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnHapusObatOperasi.setForeground(new java.awt.Color(70, 70, 70));
        MnHapusObatOperasi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnHapusObatOperasi.setText("Hapus Obat Operasi");
        MnHapusObatOperasi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnHapusObatOperasi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnHapusObatOperasi.setIconTextGap(5);
        MnHapusObatOperasi.setName("MnHapusObatOperasi"); // NOI18N
        MnHapusObatOperasi.setPreferredSize(new java.awt.Dimension(200, 26));
        MnHapusObatOperasi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnHapusObatOperasiActionPerformed(evt);
            }
        });
        MnHapusData.add(MnHapusObatOperasi);

        jPopupMenu1.add(MnHapusData);

        MnMCU.setBackground(new java.awt.Color(250, 255, 245));
        MnMCU.setForeground(new java.awt.Color(70, 70, 70));
        MnMCU.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnMCU.setText("Menu MCU");
        MnMCU.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnMCU.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnMCU.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnMCU.setIconTextGap(5);
        MnMCU.setName("MnMCU"); // NOI18N
        MnMCU.setPreferredSize(new java.awt.Dimension(260, 26));

        MnPaketMCU.setBackground(new java.awt.Color(255, 255, 254));
        MnPaketMCU.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnPaketMCU.setForeground(new java.awt.Color(70, 70, 70));
        MnPaketMCU.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnPaketMCU.setText("Paket MCU");
        MnPaketMCU.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnPaketMCU.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnPaketMCU.setIconTextGap(5);
        MnPaketMCU.setName("MnPaketMCU"); // NOI18N
        MnPaketMCU.setPreferredSize(new java.awt.Dimension(200, 26));
        MnPaketMCU.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnPaketMCUActionPerformed(evt);
            }
        });
        MnMCU.add(MnPaketMCU);

        MnPaketNapza.setBackground(new java.awt.Color(255, 255, 254));
        MnPaketNapza.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnPaketNapza.setForeground(new java.awt.Color(70, 70, 70));
        MnPaketNapza.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnPaketNapza.setText("Paket Napza");
        MnPaketNapza.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnPaketNapza.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnPaketNapza.setIconTextGap(5);
        MnPaketNapza.setName("MnPaketNapza"); // NOI18N
        MnPaketNapza.setPreferredSize(new java.awt.Dimension(200, 26));
        MnPaketNapza.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnPaketNapzaActionPerformed(evt);
            }
        });
        MnMCU.add(MnPaketNapza);

        MnPaketMMPI.setBackground(new java.awt.Color(255, 255, 254));
        MnPaketMMPI.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnPaketMMPI.setForeground(new java.awt.Color(70, 70, 70));
        MnPaketMMPI.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnPaketMMPI.setText("Paket MMPI");
        MnPaketMMPI.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnPaketMMPI.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnPaketMMPI.setIconTextGap(5);
        MnPaketMMPI.setName("MnPaketMMPI"); // NOI18N
        MnPaketMMPI.setPreferredSize(new java.awt.Dimension(200, 26));
        MnPaketMMPI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnPaketMMPIActionPerformed(evt);
            }
        });
        MnMCU.add(MnPaketMMPI);

        MnPaketHajiLaki.setBackground(new java.awt.Color(255, 255, 254));
        MnPaketHajiLaki.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnPaketHajiLaki.setForeground(new java.awt.Color(70, 70, 70));
        MnPaketHajiLaki.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnPaketHajiLaki.setText("Paket CJH Laki-laki");
        MnPaketHajiLaki.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnPaketHajiLaki.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnPaketHajiLaki.setIconTextGap(5);
        MnPaketHajiLaki.setName("MnPaketHajiLaki"); // NOI18N
        MnPaketHajiLaki.setPreferredSize(new java.awt.Dimension(200, 26));
        MnPaketHajiLaki.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnPaketHajiLakiActionPerformed(evt);
            }
        });
        MnMCU.add(MnPaketHajiLaki);

        MnPaketHajiPerempuan.setBackground(new java.awt.Color(255, 255, 254));
        MnPaketHajiPerempuan.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnPaketHajiPerempuan.setForeground(new java.awt.Color(70, 70, 70));
        MnPaketHajiPerempuan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnPaketHajiPerempuan.setText("Paket CJH Perempuan");
        MnPaketHajiPerempuan.setToolTipText("");
        MnPaketHajiPerempuan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnPaketHajiPerempuan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnPaketHajiPerempuan.setIconTextGap(5);
        MnPaketHajiPerempuan.setName("MnPaketHajiPerempuan"); // NOI18N
        MnPaketHajiPerempuan.setPreferredSize(new java.awt.Dimension(200, 26));
        MnPaketHajiPerempuan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnPaketHajiPerempuanActionPerformed(evt);
            }
        });
        MnMCU.add(MnPaketHajiPerempuan);

        jPopupMenu1.add(MnMCU);

        Kd2.setName("Kd2"); // NOI18N
        Kd2.setPreferredSize(new java.awt.Dimension(207, 23));

        DlgSakit.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        DlgSakit.setName("DlgSakit"); // NOI18N
        DlgSakit.setUndecorated(true);
        DlgSakit.setResizable(false);

        internalFrame3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(230, 235, 225)), "::[ Cetak Surat Cuti Sakit ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Lucida Grande", 0, 13), new java.awt.Color(50, 70, 50))); // NOI18N
        internalFrame3.setName("internalFrame3"); // NOI18N
        internalFrame3.setLayout(new java.awt.BorderLayout(1, 1));

        panelBiasa2.setName("panelBiasa2"); // NOI18N
        panelBiasa2.setLayout(null);

        TglSakit1.setForeground(new java.awt.Color(50, 70, 50));
        TglSakit1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "29-01-2021" }));
        TglSakit1.setDisplayFormat("dd-MM-yyyy");
        TglSakit1.setName("TglSakit1"); // NOI18N
        TglSakit1.setOpaque(false);
        panelBiasa2.add(TglSakit1);
        TglSakit1.setBounds(70, 10, 100, 23);

        jLabel31.setText("Lama Sakit :");
        jLabel31.setName("jLabel31"); // NOI18N
        panelBiasa2.add(jLabel31);
        jLabel31.setBounds(297, 10, 110, 23);

        BtnPrint2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        BtnPrint2.setMnemonic('T');
        BtnPrint2.setText("Cetak");
        BtnPrint2.setToolTipText("Alt+T");
        BtnPrint2.setName("BtnPrint2"); // NOI18N
        BtnPrint2.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnPrint2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPrint2ActionPerformed(evt);
            }
        });
        panelBiasa2.add(BtnPrint2);
        BtnPrint2.setBounds(10, 50, 100, 30);

        BtnKeluar2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/exit.png"))); // NOI18N
        BtnKeluar2.setMnemonic('K');
        BtnKeluar2.setText("Keluar");
        BtnKeluar2.setToolTipText("Alt+K");
        BtnKeluar2.setName("BtnKeluar2"); // NOI18N
        BtnKeluar2.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnKeluar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnKeluar2ActionPerformed(evt);
            }
        });
        panelBiasa2.add(BtnKeluar2);
        BtnKeluar2.setBounds(430, 50, 100, 30);

        jLabel32.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel32.setText("s/d");
        jLabel32.setName("jLabel32"); // NOI18N
        panelBiasa2.add(jLabel32);
        jLabel32.setBounds(176, 10, 20, 23);

        TglSakit2.setForeground(new java.awt.Color(50, 70, 50));
        TglSakit2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "29-01-2021" }));
        TglSakit2.setDisplayFormat("dd-MM-yyyy");
        TglSakit2.setName("TglSakit2"); // NOI18N
        TglSakit2.setOpaque(false);
        panelBiasa2.add(TglSakit2);
        TglSakit2.setBounds(200, 10, 100, 23);

        jLabel33.setText("Tanggal :");
        jLabel33.setName("jLabel33"); // NOI18N
        panelBiasa2.add(jLabel33);
        jLabel33.setBounds(0, 10, 66, 23);

        lmsakit.setHighlighter(null);
        lmsakit.setName("lmsakit"); // NOI18N
        panelBiasa2.add(lmsakit);
        lmsakit.setBounds(410, 10, 110, 23);

        internalFrame3.add(panelBiasa2, java.awt.BorderLayout.CENTER);

        DlgSakit.getContentPane().add(internalFrame3, java.awt.BorderLayout.CENTER);

        DlgDemografi.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        DlgDemografi.setName("DlgDemografi"); // NOI18N
        DlgDemografi.setUndecorated(true);
        DlgDemografi.setResizable(false);

        internalFrame4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(230, 235, 225)), "::[ Demografi Pendaftar ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Lucida Grande", 0, 13), new java.awt.Color(50, 70, 50))); // NOI18N
        internalFrame4.setName("internalFrame4"); // NOI18N
        internalFrame4.setLayout(new java.awt.BorderLayout(1, 1));

        panelBiasa3.setName("panelBiasa3"); // NOI18N
        panelBiasa3.setLayout(null);

        BtnPrint3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        BtnPrint3.setMnemonic('G');
        BtnPrint3.setText("Grafik");
        BtnPrint3.setToolTipText("Alt+G");
        BtnPrint3.setName("BtnPrint3"); // NOI18N
        BtnPrint3.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnPrint3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPrint3ActionPerformed(evt);
            }
        });
        BtnPrint3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnPrint3KeyPressed(evt);
            }
        });
        panelBiasa3.add(BtnPrint3);
        BtnPrint3.setBounds(110, 110, 100, 30);

        BtnKeluar3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/exit.png"))); // NOI18N
        BtnKeluar3.setMnemonic('K');
        BtnKeluar3.setText("Keluar");
        BtnKeluar3.setToolTipText("Alt+K");
        BtnKeluar3.setName("BtnKeluar3"); // NOI18N
        BtnKeluar3.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnKeluar3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnKeluar3ActionPerformed(evt);
            }
        });
        BtnKeluar3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnKeluar3KeyPressed(evt);
            }
        });
        panelBiasa3.add(BtnKeluar3);
        BtnKeluar3.setBounds(430, 110, 100, 30);

        Kelurahan2.setHighlighter(null);
        Kelurahan2.setName("Kelurahan2"); // NOI18N
        Kelurahan2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Kelurahan2KeyPressed(evt);
            }
        });
        panelBiasa3.add(Kelurahan2);
        Kelurahan2.setBounds(105, 70, 350, 23);

        btnKel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnKel.setMnemonic('1');
        btnKel.setToolTipText("ALt+1");
        btnKel.setName("btnKel"); // NOI18N
        btnKel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKelActionPerformed(evt);
            }
        });
        panelBiasa3.add(btnKel);
        btnKel.setBounds(460, 70, 28, 23);

        Kecamatan2.setHighlighter(null);
        Kecamatan2.setName("Kecamatan2"); // NOI18N
        Kecamatan2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Kecamatan2KeyPressed(evt);
            }
        });
        panelBiasa3.add(Kecamatan2);
        Kecamatan2.setBounds(105, 40, 350, 23);

        btnKec.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnKec.setMnemonic('1');
        btnKec.setToolTipText("ALt+1");
        btnKec.setName("btnKec"); // NOI18N
        btnKec.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKecActionPerformed(evt);
            }
        });
        panelBiasa3.add(btnKec);
        btnKec.setBounds(460, 40, 28, 23);

        Kabupaten2.setHighlighter(null);
        Kabupaten2.setName("Kabupaten2"); // NOI18N
        Kabupaten2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Kabupaten2KeyPressed(evt);
            }
        });
        panelBiasa3.add(Kabupaten2);
        Kabupaten2.setBounds(105, 10, 350, 23);

        btnKab.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnKab.setMnemonic('1');
        btnKab.setToolTipText("ALt+1");
        btnKab.setName("btnKab"); // NOI18N
        btnKab.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKabActionPerformed(evt);
            }
        });
        panelBiasa3.add(btnKab);
        btnKab.setBounds(460, 10, 28, 23);

        jLabel34.setText("Kelurahan :");
        jLabel34.setName("jLabel34"); // NOI18N
        panelBiasa3.add(jLabel34);
        jLabel34.setBounds(0, 70, 100, 23);

        jLabel35.setText("Kabupaten :");
        jLabel35.setName("jLabel35"); // NOI18N
        panelBiasa3.add(jLabel35);
        jLabel35.setBounds(0, 10, 100, 23);

        jLabel36.setText("Kecamatan :");
        jLabel36.setName("jLabel36"); // NOI18N
        panelBiasa3.add(jLabel36);
        jLabel36.setBounds(0, 40, 100, 23);

        BtnPrint4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        BtnPrint4.setMnemonic('T');
        BtnPrint4.setText("Cetak");
        BtnPrint4.setToolTipText("Alt+T");
        BtnPrint4.setName("BtnPrint4"); // NOI18N
        BtnPrint4.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnPrint4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPrint4ActionPerformed(evt);
            }
        });
        BtnPrint4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnPrint4KeyPressed(evt);
            }
        });
        panelBiasa3.add(BtnPrint4);
        BtnPrint4.setBounds(10, 110, 100, 30);

        internalFrame4.add(panelBiasa3, java.awt.BorderLayout.CENTER);

        DlgDemografi.getContentPane().add(internalFrame4, java.awt.BorderLayout.CENTER);

        DlgSakit2.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        DlgSakit2.setName("DlgSakit2"); // NOI18N
        DlgSakit2.setUndecorated(true);
        DlgSakit2.setResizable(false);

        internalFrame5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(230, 235, 225)), "::[ Cetak Surat Keterangan Rawat Inap ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Lucida Grande", 0, 13), new java.awt.Color(50, 70, 50))); // NOI18N
        internalFrame5.setName("internalFrame5"); // NOI18N
        internalFrame5.setLayout(new java.awt.BorderLayout(1, 1));

        panelBiasa4.setName("panelBiasa4"); // NOI18N
        panelBiasa4.setLayout(null);

        jLabel37.setText("Nomor Surat Keterangan :");
        jLabel37.setName("jLabel37"); // NOI18N
        panelBiasa4.add(jLabel37);
        jLabel37.setBounds(7, 10, 140, 23);

        BtnPrint5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        BtnPrint5.setMnemonic('T');
        BtnPrint5.setText("Cetak");
        BtnPrint5.setToolTipText("Alt+T");
        BtnPrint5.setName("BtnPrint5"); // NOI18N
        BtnPrint5.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnPrint5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPrint5ActionPerformed(evt);
            }
        });
        panelBiasa4.add(BtnPrint5);
        BtnPrint5.setBounds(10, 80, 100, 30);

        BtnKeluar4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/exit.png"))); // NOI18N
        BtnKeluar4.setMnemonic('K');
        BtnKeluar4.setText("Keluar");
        BtnKeluar4.setToolTipText("Alt+K");
        BtnKeluar4.setName("BtnKeluar4"); // NOI18N
        BtnKeluar4.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnKeluar4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnKeluar4ActionPerformed(evt);
            }
        });
        panelBiasa4.add(BtnKeluar4);
        BtnKeluar4.setBounds(430, 80, 100, 30);

        NomorSurat.setHighlighter(null);
        NomorSurat.setName("NomorSurat"); // NOI18N
        panelBiasa4.add(NomorSurat);
        NomorSurat.setBounds(150, 10, 370, 23);

        BtnSeek5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnSeek5.setMnemonic('6');
        BtnSeek5.setToolTipText("ALt+6");
        BtnSeek5.setName("BtnSeek5"); // NOI18N
        BtnSeek5.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnSeek5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSeek5ActionPerformed(evt);
            }
        });
        panelBiasa4.add(BtnSeek5);
        BtnSeek5.setBounds(492, 40, 28, 23);

        CrDokter3.setEditable(false);
        CrDokter3.setName("CrDokter3"); // NOI18N
        CrDokter3.setPreferredSize(new java.awt.Dimension(300, 23));
        panelBiasa4.add(CrDokter3);
        CrDokter3.setBounds(150, 40, 340, 23);

        jLabel24.setText("Dokter Png. Jawab :");
        jLabel24.setName("jLabel24"); // NOI18N
        jLabel24.setPreferredSize(new java.awt.Dimension(60, 23));
        panelBiasa4.add(jLabel24);
        jLabel24.setBounds(7, 40, 140, 23);

        internalFrame5.add(panelBiasa4, java.awt.BorderLayout.CENTER);

        DlgSakit2.getContentPane().add(internalFrame5, java.awt.BorderLayout.CENTER);

        NoBalasan.setHighlighter(null);
        NoBalasan.setName("NoBalasan"); // NOI18N

        jPopupMenu2.setName("jPopupMenu2"); // NOI18N

        MnPermintaan1.setBackground(new java.awt.Color(250, 255, 245));
        MnPermintaan1.setForeground(new java.awt.Color(70, 70, 70));
        MnPermintaan1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnPermintaan1.setText("Permintaan");
        MnPermintaan1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnPermintaan1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnPermintaan1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnPermintaan1.setIconTextGap(5);
        MnPermintaan1.setName("MnPermintaan1"); // NOI18N
        MnPermintaan1.setPreferredSize(new java.awt.Dimension(210, 26));

        MnJadwalOperasi1.setBackground(new java.awt.Color(255, 255, 254));
        MnJadwalOperasi1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnJadwalOperasi1.setForeground(new java.awt.Color(70, 70, 70));
        MnJadwalOperasi1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnJadwalOperasi1.setText("Jadwal Operasi");
        MnJadwalOperasi1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnJadwalOperasi1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnJadwalOperasi1.setIconTextGap(5);
        MnJadwalOperasi1.setName("MnJadwalOperasi1"); // NOI18N
        MnJadwalOperasi1.setPreferredSize(new java.awt.Dimension(170, 26));
        MnJadwalOperasi1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnJadwalOperasi1ActionPerformed(evt);
            }
        });
        MnPermintaan1.add(MnJadwalOperasi1);

        MnSKDPBPJS1.setBackground(new java.awt.Color(255, 255, 254));
        MnSKDPBPJS1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnSKDPBPJS1.setForeground(new java.awt.Color(70, 70, 70));
        MnSKDPBPJS1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnSKDPBPJS1.setText("SKDP BPJS");
        MnSKDPBPJS1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnSKDPBPJS1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnSKDPBPJS1.setIconTextGap(5);
        MnSKDPBPJS1.setName("MnSKDPBPJS1"); // NOI18N
        MnSKDPBPJS1.setPreferredSize(new java.awt.Dimension(170, 26));
        MnSKDPBPJS1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnSKDPBPJS1ActionPerformed(evt);
            }
        });
        MnPermintaan1.add(MnSKDPBPJS1);

        MnPermintaanLab1.setBackground(new java.awt.Color(255, 255, 254));
        MnPermintaanLab1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnPermintaanLab1.setForeground(new java.awt.Color(70, 70, 70));
        MnPermintaanLab1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnPermintaanLab1.setText("Pemeriksaan Lab");
        MnPermintaanLab1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnPermintaanLab1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnPermintaanLab1.setIconTextGap(5);
        MnPermintaanLab1.setName("MnPermintaanLab1"); // NOI18N
        MnPermintaanLab1.setPreferredSize(new java.awt.Dimension(170, 26));
        MnPermintaanLab1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnPermintaanLab1ActionPerformed(evt);
            }
        });
        MnPermintaan1.add(MnPermintaanLab1);

        MnPermintaanRadiologi1.setBackground(new java.awt.Color(255, 255, 254));
        MnPermintaanRadiologi1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnPermintaanRadiologi1.setForeground(new java.awt.Color(70, 70, 70));
        MnPermintaanRadiologi1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnPermintaanRadiologi1.setText("Pemeriksaan Radiologi");
        MnPermintaanRadiologi1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnPermintaanRadiologi1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnPermintaanRadiologi1.setIconTextGap(5);
        MnPermintaanRadiologi1.setName("MnPermintaanRadiologi1"); // NOI18N
        MnPermintaanRadiologi1.setPreferredSize(new java.awt.Dimension(170, 26));
        MnPermintaanRadiologi1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnPermintaanRadiologi1ActionPerformed(evt);
            }
        });
        MnPermintaan1.add(MnPermintaanRadiologi1);

        jPopupMenu2.add(MnPermintaan1);

        MnKamarInap1.setBackground(new java.awt.Color(255, 255, 254));
        MnKamarInap1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnKamarInap1.setForeground(new java.awt.Color(70, 70, 70));
        MnKamarInap1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnKamarInap1.setText("Kamar Inap");
        MnKamarInap1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnKamarInap1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnKamarInap1.setIconTextGap(5);
        MnKamarInap1.setName("MnKamarInap1"); // NOI18N
        MnKamarInap1.setPreferredSize(new java.awt.Dimension(210, 26));
        MnKamarInap1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnKamarInap1ActionPerformed(evt);
            }
        });
        jPopupMenu2.add(MnKamarInap1);

        MnTindakan1.setBackground(new java.awt.Color(250, 255, 245));
        MnTindakan1.setForeground(new java.awt.Color(70, 70, 70));
        MnTindakan1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnTindakan1.setText("Tindakan & Pemeriksaan");
        MnTindakan1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnTindakan1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnTindakan1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnTindakan1.setIconTextGap(5);
        MnTindakan1.setName("MnTindakan1"); // NOI18N
        MnTindakan1.setPreferredSize(new java.awt.Dimension(210, 26));

        MnRawatJalan1.setBackground(new java.awt.Color(255, 255, 254));
        MnRawatJalan1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnRawatJalan1.setForeground(new java.awt.Color(70, 70, 70));
        MnRawatJalan1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnRawatJalan1.setText("Tagihan/Tindakan Rawat Jalan");
        MnRawatJalan1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnRawatJalan1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnRawatJalan1.setIconTextGap(5);
        MnRawatJalan1.setName("MnRawatJalan1"); // NOI18N
        MnRawatJalan1.setPreferredSize(new java.awt.Dimension(240, 26));
        MnRawatJalan1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnRawatJalan1ActionPerformed(evt);
            }
        });
        MnTindakan1.add(MnRawatJalan1);

        MnPeriksaLab1.setBackground(new java.awt.Color(255, 255, 254));
        MnPeriksaLab1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnPeriksaLab1.setForeground(new java.awt.Color(70, 70, 70));
        MnPeriksaLab1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnPeriksaLab1.setText("Periksa Laborat");
        MnPeriksaLab1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnPeriksaLab1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnPeriksaLab1.setIconTextGap(5);
        MnPeriksaLab1.setName("MnPeriksaLab1"); // NOI18N
        MnPeriksaLab1.setPreferredSize(new java.awt.Dimension(240, 26));
        MnPeriksaLab1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnPeriksaLab1ActionPerformed(evt);
            }
        });
        MnTindakan1.add(MnPeriksaLab1);

        MnPeriksaRadiologi1.setBackground(new java.awt.Color(255, 255, 254));
        MnPeriksaRadiologi1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnPeriksaRadiologi1.setForeground(new java.awt.Color(70, 70, 70));
        MnPeriksaRadiologi1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnPeriksaRadiologi1.setText("Periksa Radiologi");
        MnPeriksaRadiologi1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnPeriksaRadiologi1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnPeriksaRadiologi1.setIconTextGap(5);
        MnPeriksaRadiologi1.setName("MnPeriksaRadiologi1"); // NOI18N
        MnPeriksaRadiologi1.setPreferredSize(new java.awt.Dimension(240, 26));
        MnPeriksaRadiologi1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnPeriksaRadiologi1ActionPerformed(evt);
            }
        });
        MnTindakan1.add(MnPeriksaRadiologi1);

        MnOperasi1.setBackground(new java.awt.Color(255, 255, 254));
        MnOperasi1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnOperasi1.setForeground(new java.awt.Color(70, 70, 70));
        MnOperasi1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnOperasi1.setText("Tagihan Operasi/VK");
        MnOperasi1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnOperasi1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnOperasi1.setIconTextGap(5);
        MnOperasi1.setName("MnOperasi1"); // NOI18N
        MnOperasi1.setPreferredSize(new java.awt.Dimension(240, 26));
        MnOperasi1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnOperasi1ActionPerformed(evt);
            }
        });
        MnTindakan1.add(MnOperasi1);

        jPopupMenu2.add(MnTindakan1);

        MnObat1.setBackground(new java.awt.Color(250, 255, 245));
        MnObat1.setForeground(new java.awt.Color(70, 70, 70));
        MnObat1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnObat1.setText("Obat");
        MnObat1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnObat1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnObat1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnObat1.setIconTextGap(5);
        MnObat1.setName("MnObat1"); // NOI18N
        MnObat1.setPreferredSize(new java.awt.Dimension(210, 26));

        MnPemberianObat1.setBackground(new java.awt.Color(255, 255, 254));
        MnPemberianObat1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnPemberianObat1.setForeground(new java.awt.Color(70, 70, 70));
        MnPemberianObat1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnPemberianObat1.setText("Pemberian Obat/BHP");
        MnPemberianObat1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnPemberianObat1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnPemberianObat1.setIconTextGap(5);
        MnPemberianObat1.setName("MnPemberianObat1"); // NOI18N
        MnPemberianObat1.setPreferredSize(new java.awt.Dimension(160, 26));
        MnPemberianObat1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnPemberianObat1ActionPerformed(evt);
            }
        });
        MnObat1.add(MnPemberianObat1);

        MnNoResep1.setBackground(new java.awt.Color(255, 255, 254));
        MnNoResep1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnNoResep1.setForeground(new java.awt.Color(70, 70, 70));
        MnNoResep1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnNoResep1.setText("Input No.Resep");
        MnNoResep1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnNoResep1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnNoResep1.setIconTextGap(5);
        MnNoResep1.setName("MnNoResep1"); // NOI18N
        MnNoResep1.setPreferredSize(new java.awt.Dimension(160, 26));
        MnNoResep1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnNoResep1ActionPerformed(evt);
            }
        });
        MnObat1.add(MnNoResep1);

        MnResepDOkter1.setBackground(new java.awt.Color(255, 255, 254));
        MnResepDOkter1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnResepDOkter1.setForeground(new java.awt.Color(70, 70, 70));
        MnResepDOkter1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnResepDOkter1.setText("Input Resep Dokter");
        MnResepDOkter1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnResepDOkter1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnResepDOkter1.setIconTextGap(5);
        MnResepDOkter1.setName("MnResepDOkter1"); // NOI18N
        MnResepDOkter1.setPreferredSize(new java.awt.Dimension(160, 26));
        MnResepDOkter1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnResepDOkter1ActionPerformed(evt);
            }
        });
        MnObat1.add(MnResepDOkter1);

        jPopupMenu2.add(MnObat1);

        MnBilling1.setBackground(new java.awt.Color(255, 255, 254));
        MnBilling1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnBilling1.setForeground(new java.awt.Color(70, 70, 70));
        MnBilling1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnBilling1.setText("Billing/Pembayaran Pasien");
        MnBilling1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnBilling1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnBilling1.setIconTextGap(5);
        MnBilling1.setName("MnBilling1"); // NOI18N
        MnBilling1.setPreferredSize(new java.awt.Dimension(210, 26));
        MnBilling1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnBilling1ActionPerformed(evt);
            }
        });
        jPopupMenu2.add(MnBilling1);

        jSeparator12.setBackground(new java.awt.Color(190, 220, 180));
        jSeparator12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(190, 220, 180)));
        jSeparator12.setForeground(new java.awt.Color(190, 220, 180));
        jSeparator12.setName("jSeparator12"); // NOI18N
        jSeparator12.setPreferredSize(new java.awt.Dimension(210, 1));
        jPopupMenu2.add(jSeparator12);

        MenuInputData1.setBackground(new java.awt.Color(250, 255, 245));
        MenuInputData1.setForeground(new java.awt.Color(70, 70, 70));
        MenuInputData1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MenuInputData1.setText("Input Data");
        MenuInputData1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MenuInputData1.setIconTextGap(5);
        MenuInputData1.setName("MenuInputData1"); // NOI18N
        MenuInputData1.setPreferredSize(new java.awt.Dimension(210, 26));

        ppBerkasDigital1.setBackground(new java.awt.Color(255, 255, 254));
        ppBerkasDigital1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppBerkasDigital1.setForeground(new java.awt.Color(70, 70, 70));
        ppBerkasDigital1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppBerkasDigital1.setText("Berkas Digital Perawatan");
        ppBerkasDigital1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppBerkasDigital1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppBerkasDigital1.setIconTextGap(5);
        ppBerkasDigital1.setName("ppBerkasDigital1"); // NOI18N
        ppBerkasDigital1.setPreferredSize(new java.awt.Dimension(200, 26));
        ppBerkasDigital1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppBerkasDigital1BtnPrintActionPerformed(evt);
            }
        });
        MenuInputData1.add(ppBerkasDigital1);

        ppIKP1.setBackground(new java.awt.Color(255, 255, 254));
        ppIKP1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppIKP1.setForeground(new java.awt.Color(70, 70, 70));
        ppIKP1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppIKP1.setText("Insiden Keselamatan Pasien");
        ppIKP1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppIKP1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppIKP1.setIconTextGap(5);
        ppIKP1.setName("ppIKP1"); // NOI18N
        ppIKP1.setPreferredSize(new java.awt.Dimension(200, 26));
        ppIKP1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppIKP1BtnPrintActionPerformed(evt);
            }
        });
        MenuInputData1.add(ppIKP1);

        MnDiagnosa1.setBackground(new java.awt.Color(255, 255, 254));
        MnDiagnosa1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnDiagnosa1.setForeground(new java.awt.Color(70, 70, 70));
        MnDiagnosa1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnDiagnosa1.setText("Diagnosa Pasien");
        MnDiagnosa1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnDiagnosa1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnDiagnosa1.setIconTextGap(5);
        MnDiagnosa1.setName("MnDiagnosa1"); // NOI18N
        MnDiagnosa1.setPreferredSize(new java.awt.Dimension(200, 26));
        MnDiagnosa1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnDiagnosa1ActionPerformed(evt);
            }
        });
        MenuInputData1.add(MnDiagnosa1);

        MnTeridentifikasiTB1.setBackground(new java.awt.Color(255, 255, 254));
        MnTeridentifikasiTB1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnTeridentifikasiTB1.setForeground(new java.awt.Color(70, 70, 70));
        MnTeridentifikasiTB1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnTeridentifikasiTB1.setText("Teridentifikasi TB");
        MnTeridentifikasiTB1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnTeridentifikasiTB1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnTeridentifikasiTB1.setIconTextGap(5);
        MnTeridentifikasiTB1.setName("MnTeridentifikasiTB1"); // NOI18N
        MnTeridentifikasiTB1.setPreferredSize(new java.awt.Dimension(200, 26));
        MnTeridentifikasiTB1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnTeridentifikasiTB1ActionPerformed(evt);
            }
        });
        MenuInputData1.add(MnTeridentifikasiTB1);

        jPopupMenu2.add(MenuInputData1);

        MnUrut1.setBackground(new java.awt.Color(250, 255, 245));
        MnUrut1.setForeground(new java.awt.Color(70, 70, 70));
        MnUrut1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnUrut1.setText("Urutkan Data Berdasar");
        MnUrut1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnUrut1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnUrut1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnUrut1.setIconTextGap(5);
        MnUrut1.setName("MnUrut1"); // NOI18N
        MnUrut1.setPreferredSize(new java.awt.Dimension(210, 26));

        MnUrutNoRawatDesc1.setBackground(new java.awt.Color(255, 255, 254));
        MnUrutNoRawatDesc1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnUrutNoRawatDesc1.setForeground(new java.awt.Color(70, 70, 70));
        MnUrutNoRawatDesc1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnUrutNoRawatDesc1.setText("No.Rawat Descending");
        MnUrutNoRawatDesc1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnUrutNoRawatDesc1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnUrutNoRawatDesc1.setIconTextGap(5);
        MnUrutNoRawatDesc1.setName("MnUrutNoRawatDesc1"); // NOI18N
        MnUrutNoRawatDesc1.setPreferredSize(new java.awt.Dimension(180, 26));
        MnUrutNoRawatDesc1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnUrutNoRawatDesc1ActionPerformed(evt);
            }
        });
        MnUrut1.add(MnUrutNoRawatDesc1);

        MnUrutNoRawatAsc1.setBackground(new java.awt.Color(255, 255, 254));
        MnUrutNoRawatAsc1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnUrutNoRawatAsc1.setForeground(new java.awt.Color(70, 70, 70));
        MnUrutNoRawatAsc1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnUrutNoRawatAsc1.setText("No.Rawat Ascending");
        MnUrutNoRawatAsc1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnUrutNoRawatAsc1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnUrutNoRawatAsc1.setIconTextGap(5);
        MnUrutNoRawatAsc1.setName("MnUrutNoRawatAsc1"); // NOI18N
        MnUrutNoRawatAsc1.setPreferredSize(new java.awt.Dimension(180, 26));
        MnUrutNoRawatAsc1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnUrutNoRawatAsc1ActionPerformed(evt);
            }
        });
        MnUrut1.add(MnUrutNoRawatAsc1);

        MnUrutTanggalDesc1.setBackground(new java.awt.Color(255, 255, 254));
        MnUrutTanggalDesc1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnUrutTanggalDesc1.setForeground(new java.awt.Color(70, 70, 70));
        MnUrutTanggalDesc1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnUrutTanggalDesc1.setText("Tanggal Descending");
        MnUrutTanggalDesc1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnUrutTanggalDesc1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnUrutTanggalDesc1.setIconTextGap(5);
        MnUrutTanggalDesc1.setName("MnUrutTanggalDesc1"); // NOI18N
        MnUrutTanggalDesc1.setPreferredSize(new java.awt.Dimension(180, 26));
        MnUrutTanggalDesc1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnUrutTanggalDesc1ActionPerformed(evt);
            }
        });
        MnUrut1.add(MnUrutTanggalDesc1);

        MnUrutTanggalAsc1.setBackground(new java.awt.Color(255, 255, 254));
        MnUrutTanggalAsc1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnUrutTanggalAsc1.setForeground(new java.awt.Color(70, 70, 70));
        MnUrutTanggalAsc1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnUrutTanggalAsc1.setText("Tanggal Ascending");
        MnUrutTanggalAsc1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnUrutTanggalAsc1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnUrutTanggalAsc1.setIconTextGap(5);
        MnUrutTanggalAsc1.setName("MnUrutTanggalAsc1"); // NOI18N
        MnUrutTanggalAsc1.setPreferredSize(new java.awt.Dimension(180, 26));
        MnUrutTanggalAsc1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnUrutTanggalAsc1ActionPerformed(evt);
            }
        });
        MnUrut1.add(MnUrutTanggalAsc1);

        MnUrutDokterDesc1.setBackground(new java.awt.Color(255, 255, 254));
        MnUrutDokterDesc1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnUrutDokterDesc1.setForeground(new java.awt.Color(70, 70, 70));
        MnUrutDokterDesc1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnUrutDokterDesc1.setText("Dokter Descending");
        MnUrutDokterDesc1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnUrutDokterDesc1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnUrutDokterDesc1.setIconTextGap(5);
        MnUrutDokterDesc1.setName("MnUrutDokterDesc1"); // NOI18N
        MnUrutDokterDesc1.setPreferredSize(new java.awt.Dimension(180, 26));
        MnUrutDokterDesc1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnUrutDokterDesc1ActionPerformed(evt);
            }
        });
        MnUrut1.add(MnUrutDokterDesc1);

        MnUrutDokterAsc1.setBackground(new java.awt.Color(255, 255, 254));
        MnUrutDokterAsc1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnUrutDokterAsc1.setForeground(new java.awt.Color(70, 70, 70));
        MnUrutDokterAsc1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnUrutDokterAsc1.setText("Dokter Ascending");
        MnUrutDokterAsc1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnUrutDokterAsc1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnUrutDokterAsc1.setIconTextGap(5);
        MnUrutDokterAsc1.setName("MnUrutDokterAsc1"); // NOI18N
        MnUrutDokterAsc1.setPreferredSize(new java.awt.Dimension(180, 26));
        MnUrutDokterAsc1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnUrutDokterAsc1ActionPerformed(evt);
            }
        });
        MnUrut1.add(MnUrutDokterAsc1);

        MnUrutPoliklinikDesc1.setBackground(new java.awt.Color(255, 255, 254));
        MnUrutPoliklinikDesc1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnUrutPoliklinikDesc1.setForeground(new java.awt.Color(70, 70, 70));
        MnUrutPoliklinikDesc1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnUrutPoliklinikDesc1.setText("Poli/Unit Descending");
        MnUrutPoliklinikDesc1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnUrutPoliklinikDesc1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnUrutPoliklinikDesc1.setIconTextGap(5);
        MnUrutPoliklinikDesc1.setName("MnUrutPoliklinikDesc1"); // NOI18N
        MnUrutPoliklinikDesc1.setPreferredSize(new java.awt.Dimension(180, 26));
        MnUrutPoliklinikDesc1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnUrutPoliklinikDesc1ActionPerformed(evt);
            }
        });
        MnUrut1.add(MnUrutPoliklinikDesc1);

        MnUrutPoliklinikAsc1.setBackground(new java.awt.Color(255, 255, 254));
        MnUrutPoliklinikAsc1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnUrutPoliklinikAsc1.setForeground(new java.awt.Color(70, 70, 70));
        MnUrutPoliklinikAsc1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnUrutPoliklinikAsc1.setText("Poli/Unit Ascending");
        MnUrutPoliklinikAsc1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnUrutPoliklinikAsc1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnUrutPoliklinikAsc1.setIconTextGap(5);
        MnUrutPoliklinikAsc1.setName("MnUrutPoliklinikAsc1"); // NOI18N
        MnUrutPoliklinikAsc1.setPreferredSize(new java.awt.Dimension(180, 26));
        MnUrutPoliklinikAsc1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnUrutPoliklinikAsc1ActionPerformed(evt);
            }
        });
        MnUrut1.add(MnUrutPoliklinikAsc1);

        MnUrutPenjabDesc1.setBackground(new java.awt.Color(255, 255, 254));
        MnUrutPenjabDesc1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnUrutPenjabDesc1.setForeground(new java.awt.Color(70, 70, 70));
        MnUrutPenjabDesc1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnUrutPenjabDesc1.setText("Cara Bayar Descending");
        MnUrutPenjabDesc1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnUrutPenjabDesc1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnUrutPenjabDesc1.setIconTextGap(5);
        MnUrutPenjabDesc1.setName("MnUrutPenjabDesc1"); // NOI18N
        MnUrutPenjabDesc1.setPreferredSize(new java.awt.Dimension(180, 26));
        MnUrutPenjabDesc1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnUrutPenjabDesc1ActionPerformed(evt);
            }
        });
        MnUrut1.add(MnUrutPenjabDesc1);

        MnUrutPenjabAsc1.setBackground(new java.awt.Color(255, 255, 254));
        MnUrutPenjabAsc1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnUrutPenjabAsc1.setForeground(new java.awt.Color(70, 70, 70));
        MnUrutPenjabAsc1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnUrutPenjabAsc1.setText("Cara Bayar Ascending");
        MnUrutPenjabAsc1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnUrutPenjabAsc1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnUrutPenjabAsc1.setIconTextGap(5);
        MnUrutPenjabAsc1.setName("MnUrutPenjabAsc1"); // NOI18N
        MnUrutPenjabAsc1.setPreferredSize(new java.awt.Dimension(180, 26));
        MnUrutPenjabAsc1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnUrutPenjabAsc1ActionPerformed(evt);
            }
        });
        MnUrut1.add(MnUrutPenjabAsc1);

        MnUrutStatusDesc1.setBackground(new java.awt.Color(255, 255, 254));
        MnUrutStatusDesc1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnUrutStatusDesc1.setForeground(new java.awt.Color(70, 70, 70));
        MnUrutStatusDesc1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnUrutStatusDesc1.setText("Status Descending");
        MnUrutStatusDesc1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnUrutStatusDesc1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnUrutStatusDesc1.setIconTextGap(5);
        MnUrutStatusDesc1.setName("MnUrutStatusDesc1"); // NOI18N
        MnUrutStatusDesc1.setPreferredSize(new java.awt.Dimension(180, 26));
        MnUrutStatusDesc1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnUrutStatusDesc1ActionPerformed(evt);
            }
        });
        MnUrut1.add(MnUrutStatusDesc1);

        MnUrutStatusAsc1.setBackground(new java.awt.Color(255, 255, 254));
        MnUrutStatusAsc1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnUrutStatusAsc1.setForeground(new java.awt.Color(70, 70, 70));
        MnUrutStatusAsc1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnUrutStatusAsc1.setText("Status Ascending");
        MnUrutStatusAsc1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnUrutStatusAsc1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnUrutStatusAsc1.setIconTextGap(5);
        MnUrutStatusAsc1.setName("MnUrutStatusAsc1"); // NOI18N
        MnUrutStatusAsc1.setPreferredSize(new java.awt.Dimension(180, 26));
        MnUrutStatusAsc1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnUrutStatusAsc1ActionPerformed(evt);
            }
        });
        MnUrut1.add(MnUrutStatusAsc1);

        jPopupMenu2.add(MnUrut1);

        ppRiwayat1.setBackground(new java.awt.Color(255, 255, 254));
        ppRiwayat1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppRiwayat1.setForeground(new java.awt.Color(70, 70, 70));
        ppRiwayat1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppRiwayat1.setText("Riwayat Perawatan");
        ppRiwayat1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppRiwayat1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppRiwayat1.setIconTextGap(5);
        ppRiwayat1.setName("ppRiwayat1"); // NOI18N
        ppRiwayat1.setPreferredSize(new java.awt.Dimension(210, 26));
        ppRiwayat1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppRiwayat1BtnPrintActionPerformed(evt);
            }
        });
        jPopupMenu2.add(ppRiwayat1);

        MnHapusRujukan.setBackground(new java.awt.Color(255, 255, 254));
        MnHapusRujukan.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnHapusRujukan.setForeground(new java.awt.Color(70, 70, 70));
        MnHapusRujukan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnHapusRujukan.setText("Hapus Rujukan Poli Internal");
        MnHapusRujukan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnHapusRujukan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnHapusRujukan.setIconTextGap(5);
        MnHapusRujukan.setName("MnHapusRujukan"); // NOI18N
        MnHapusRujukan.setPreferredSize(new java.awt.Dimension(210, 26));
        MnHapusRujukan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnHapusRujukanActionPerformed(evt);
            }
        });
        jPopupMenu2.add(MnHapusRujukan);

        MnInterpretasiEkg2.setBackground(new java.awt.Color(255, 255, 254));
        MnInterpretasiEkg2.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnInterpretasiEkg2.setForeground(new java.awt.Color(70, 70, 70));
        MnInterpretasiEkg2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnInterpretasiEkg2.setText("Interpretasi EKG");
        MnInterpretasiEkg2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnInterpretasiEkg2.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnInterpretasiEkg2.setIconTextGap(5);
        MnInterpretasiEkg2.setName("MnInterpretasiEkg2"); // NOI18N
        MnInterpretasiEkg2.setPreferredSize(new java.awt.Dimension(210, 26));
        MnInterpretasiEkg2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnInterpretasiEkg2ActionPerformed(evt);
            }
        });
        jPopupMenu2.add(MnInterpretasiEkg2);

        MnJawabKonsul.setBackground(new java.awt.Color(255, 255, 254));
        MnJawabKonsul.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnJawabKonsul.setForeground(new java.awt.Color(70, 70, 70));
        MnJawabKonsul.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnJawabKonsul.setText("Jawab Konsul");
        MnJawabKonsul.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnJawabKonsul.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnJawabKonsul.setIconTextGap(5);
        MnJawabKonsul.setName("MnJawabKonsul"); // NOI18N
        MnJawabKonsul.setPreferredSize(new java.awt.Dimension(210, 26));
        MnJawabKonsul.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnJawabKonsulActionPerformed(evt);
            }
        });
        jPopupMenu2.add(MnJawabKonsul);

        DlgCatatan.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        DlgCatatan.setName("DlgCatatan"); // NOI18N
        DlgCatatan.setUndecorated(true);
        DlgCatatan.setResizable(false);

        internalFrame6.setBorder(null);
        internalFrame6.setName("internalFrame6"); // NOI18N
        internalFrame6.setWarnaAtas(new java.awt.Color(100, 100, 10));
        internalFrame6.setWarnaBawah(new java.awt.Color(100, 100, 10));
        internalFrame6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                internalFrame6MouseClicked(evt);
            }
        });
        internalFrame6.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        LabelCatatan.setForeground(new java.awt.Color(255, 255, 255));
        LabelCatatan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LabelCatatan.setText("Catatan");
        LabelCatatan.setName("LabelCatatan"); // NOI18N
        LabelCatatan.setPreferredSize(new java.awt.Dimension(580, 23));
        LabelCatatan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                LabelCatatanMouseClicked(evt);
            }
        });
        internalFrame6.add(LabelCatatan);

        DlgCatatan.getContentPane().add(internalFrame6, java.awt.BorderLayout.CENTER);

        DlgInputNoHp.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        DlgInputNoHp.setName("DlgInputNoHp"); // NOI18N
        DlgInputNoHp.setResizable(false);

        internalFrame2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Input Nomor Handphone Pasien ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(70, 70, 70))); // NOI18N
        internalFrame2.setName("internalFrame2"); // NOI18N

        TNoRM1.setEditable(false);
        TNoRM1.setHighlighter(null);
        TNoRM1.setName("TNoRM1"); // NOI18N

        TNoHp.setHighlighter(null);
        TNoHp.setName("TNoHp"); // NOI18N
        TNoHp.setPreferredSize(new java.awt.Dimension(25, 28));

        jLabel5.setText("Pasien :");
        jLabel5.setName("jLabel5"); // NOI18N

        TPasien1.setEditable(false);
        TPasien1.setHighlighter(null);
        TPasien1.setName("TPasien1"); // NOI18N
        TPasien1.setPreferredSize(new java.awt.Dimension(25, 28));

        jLabel11.setText("No. Hp :");
        jLabel11.setName("jLabel11"); // NOI18N

        BtnSimpan1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/save-16x16.png"))); // NOI18N
        BtnSimpan1.setMnemonic('S');
        BtnSimpan1.setText("Simpan");
        BtnSimpan1.setToolTipText("Alt+S");
        BtnSimpan1.setName("BtnSimpan1"); // NOI18N
        BtnSimpan1.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnSimpan1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSimpan1ActionPerformed(evt);
            }
        });
        BtnSimpan1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnSimpan1KeyPressed(evt);
            }
        });

        BtnKeluar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/cross.png"))); // NOI18N
        BtnKeluar1.setMnemonic('T');
        BtnKeluar1.setText("Tutup");
        BtnKeluar1.setToolTipText("Alt+T");
        BtnKeluar1.setName("BtnKeluar1"); // NOI18N
        BtnKeluar1.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnKeluar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnKeluar1ActionPerformed(evt);
            }
        });
        BtnKeluar1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnKeluar1KeyPressed(evt);
            }
        });

        javax.swing.GroupLayout internalFrame2Layout = new javax.swing.GroupLayout(internalFrame2);
        internalFrame2.setLayout(internalFrame2Layout);
        internalFrame2Layout.setHorizontalGroup(
            internalFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(internalFrame2Layout.createSequentialGroup()
                .addGroup(internalFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(internalFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(internalFrame2Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(BtnSimpan1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(BtnKeluar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(internalFrame2Layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addGroup(internalFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(TNoHp, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(internalFrame2Layout.createSequentialGroup()
                                .addComponent(TNoRM1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(TPasien1, javax.swing.GroupLayout.PREFERRED_SIZE, 371, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(58, Short.MAX_VALUE))))
        );
        internalFrame2Layout.setVerticalGroup(
            internalFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(internalFrame2Layout.createSequentialGroup()
                .addGroup(internalFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(internalFrame2Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, internalFrame2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(internalFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(TNoRM1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TPasien1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(internalFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TNoHp, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(internalFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BtnSimpan1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnKeluar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        DlgInputNoHp.getContentPane().add(internalFrame2, java.awt.BorderLayout.CENTER);

        DlgInterpretasiEKG.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        DlgInterpretasiEKG.setName("DlgInterpretasiEKG"); // NOI18N
        DlgInterpretasiEKG.setUndecorated(true);
        DlgInterpretasiEKG.setResizable(false);
        DlgInterpretasiEKG.setSize(new java.awt.Dimension(1200, 200));

        internalFrame7.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Interpretasi EKG ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(70, 70, 70))); // NOI18N
        internalFrame7.setName("internalFrame7"); // NOI18N

        TNoRMint.setEditable(false);
        TNoRMint.setHighlighter(null);
        TNoRMint.setName("TNoRMint"); // NOI18N

        TNoRwint.setHighlighter(null);
        TNoRwint.setName("TNoRwint"); // NOI18N
        TNoRwint.setPreferredSize(new java.awt.Dimension(25, 28));

        jLabel12.setText("Pasien :");
        jLabel12.setName("jLabel12"); // NOI18N

        TPasienint.setEditable(false);
        TPasienint.setHighlighter(null);
        TPasienint.setName("TPasienint"); // NOI18N
        TPasienint.setPreferredSize(new java.awt.Dimension(25, 28));

        jLabel25.setText("Interpretasi :");
        jLabel25.setName("jLabel25"); // NOI18N

        BtnSimpan2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/save-16x16.png"))); // NOI18N
        BtnSimpan2.setMnemonic('S');
        BtnSimpan2.setText("Simpan");
        BtnSimpan2.setToolTipText("Alt+S");
        BtnSimpan2.setName("BtnSimpan2"); // NOI18N
        BtnSimpan2.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnSimpan2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSimpan2ActionPerformed(evt);
            }
        });
        BtnSimpan2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnSimpan2KeyPressed(evt);
            }
        });

        BtnKeluar5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/cross.png"))); // NOI18N
        BtnKeluar5.setMnemonic('T');
        BtnKeluar5.setText("Tutup");
        BtnKeluar5.setToolTipText("Alt+T");
        BtnKeluar5.setName("BtnKeluar5"); // NOI18N
        BtnKeluar5.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnKeluar5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnKeluar5ActionPerformed(evt);
            }
        });
        BtnKeluar5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnKeluar5KeyPressed(evt);
            }
        });

        scrollPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane1.setName("scrollPane1"); // NOI18N

        TKesan.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        TKesan.setColumns(20);
        TKesan.setRows(5);
        TKesan.setName("TKesan"); // NOI18N
        TKesan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TKesanKeyPressed(evt);
            }
        });
        scrollPane1.setViewportView(TKesan);

        BtnPrint1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        BtnPrint1.setMnemonic('T');
        BtnPrint1.setText("Cetak");
        BtnPrint1.setToolTipText("Alt+T");
        BtnPrint1.setName("BtnPrint1"); // NOI18N
        BtnPrint1.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnPrint1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPrint1ActionPerformed(evt);
            }
        });
        BtnPrint1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnPrint1KeyPressed(evt);
            }
        });

        Tind.setEditable(false);
        Tind.setHighlighter(null);
        Tind.setName("Tind"); // NOI18N

        javax.swing.GroupLayout internalFrame7Layout = new javax.swing.GroupLayout(internalFrame7);
        internalFrame7.setLayout(internalFrame7Layout);
        internalFrame7Layout.setHorizontalGroup(
            internalFrame7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(internalFrame7Layout.createSequentialGroup()
                .addGroup(internalFrame7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel25, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(internalFrame7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(internalFrame7Layout.createSequentialGroup()
                        .addComponent(TNoRwint, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TNoRMint, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TPasienint, javax.swing.GroupLayout.PREFERRED_SIZE, 371, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(382, Short.MAX_VALUE))
                    .addGroup(internalFrame7Layout.createSequentialGroup()
                        .addGroup(internalFrame7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(internalFrame7Layout.createSequentialGroup()
                                .addComponent(BtnSimpan2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(BtnPrint1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(48, 48, 48)
                                .addComponent(BtnKeluar5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(35, 35, 35)
                                .addComponent(Tind, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(scrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 667, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        internalFrame7Layout.setVerticalGroup(
            internalFrame7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(internalFrame7Layout.createSequentialGroup()
                .addGroup(internalFrame7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(internalFrame7Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, internalFrame7Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(internalFrame7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(TNoRwint, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TPasienint, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TNoRMint, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(internalFrame7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(internalFrame7Layout.createSequentialGroup()
                        .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(scrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE))
                .addGap(52, 52, 52)
                .addGroup(internalFrame7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(internalFrame7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(BtnKeluar5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(Tind, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(internalFrame7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(BtnSimpan2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(BtnPrint1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        DlgInterpretasiEKG.getContentPane().add(internalFrame7, java.awt.BorderLayout.CENTER);

        DlgJawabanKonsul.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        DlgJawabanKonsul.setName("DlgJawabanKonsul"); // NOI18N
        DlgJawabanKonsul.setUndecorated(true);
        DlgJawabanKonsul.setResizable(false);

        internalFrame8.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Jawaban Konsul ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(70, 70, 70))); // NOI18N
        internalFrame8.setName("internalFrame8"); // NOI18N

        jLabel26.setText("DETAIL KONSUL / RUJUKAN INTERNAL :");
        jLabel26.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel26.setName("jLabel26"); // NOI18N

        jLabel27.setText("Nama Lengkap :");
        jLabel27.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel27.setName("jLabel27"); // NOI18N

        LabelNama.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LabelNama.setText("Nama");
        LabelNama.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        LabelNama.setName("LabelNama"); // NOI18N

        jLabel28.setText("No. RM :");
        jLabel28.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel28.setName("jLabel28"); // NOI18N

        LabelNoRM.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LabelNoRM.setText("NoRM");
        LabelNoRM.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        LabelNoRM.setName("LabelNoRM"); // NOI18N

        jLabel29.setText("No. Rawat :");
        jLabel29.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel29.setName("jLabel29"); // NOI18N

        LabelNoRW.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LabelNoRW.setText("NoRW");
        LabelNoRW.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        LabelNoRW.setName("LabelNoRW"); // NOI18N

        jLabel38.setText("Dokter Perujuk :");
        jLabel38.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel38.setName("jLabel38"); // NOI18N

        LabelDokter.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LabelDokter.setText("Nama Dokter");
        LabelDokter.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        LabelDokter.setName("LabelDokter"); // NOI18N

        jLabel39.setText("Poli Tujuan :");
        jLabel39.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel39.setName("jLabel39"); // NOI18N

        LabelPoliTujuan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LabelPoliTujuan.setText("NamaPoli");
        LabelPoliTujuan.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        LabelPoliTujuan.setName("LabelPoliTujuan"); // NOI18N

        jLabel40.setText("DETAIL PASIEN :");
        jLabel40.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel40.setName("jLabel40"); // NOI18N

        jLabel41.setText("Dokter Tujuan :");
        jLabel41.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel41.setName("jLabel41"); // NOI18N

        LabelDokterTujuan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LabelDokterTujuan.setText("NamaDokterTujuan");
        LabelDokterTujuan.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        LabelDokterTujuan.setName("LabelDokterTujuan"); // NOI18N

        jLabel42.setText("Poli Perujuk :");
        jLabel42.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel42.setName("jLabel42"); // NOI18N

        LabelPoliPerujuk.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LabelPoliPerujuk.setText("NamaPoli");
        LabelPoliPerujuk.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        LabelPoliPerujuk.setName("LabelPoliPerujuk"); // NOI18N

        jLabel43.setText("Catatan Konsul :");
        jLabel43.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel43.setName("jLabel43"); // NOI18N

        jLabel44.setText("Pemeriksaan :");
        jLabel44.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel44.setName("jLabel44"); // NOI18N

        jLabel45.setText("Diagnosa :");
        jLabel45.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel45.setName("jLabel45"); // NOI18N

        LabelDiagnosa.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LabelDiagnosa.setText("Diagnosa");
        LabelDiagnosa.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        LabelDiagnosa.setName("LabelDiagnosa"); // NOI18N

        jLabel46.setText("Saran :");
        jLabel46.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel46.setName("jLabel46"); // NOI18N

        TSaranJawab.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        TSaranJawab.setColumns(20);
        TSaranJawab.setRows(5);
        TSaranJawab.setText("Saran\n");
        TSaranJawab.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        TSaranJawab.setName("TSaranJawab"); // NOI18N
        TSaranJawab.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TSaranJawabKeyPressed(evt);
            }
        });

        TKonsulJawab.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        TKonsulJawab.setColumns(20);
        TKonsulJawab.setRows(5);
        TKonsulJawab.setText("Konsul\n");
        TKonsulJawab.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        TKonsulJawab.setName("TKonsulJawab"); // NOI18N
        TKonsulJawab.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TKonsulJawabKeyPressed(evt);
            }
        });

        TPemeriksaanJawab.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        TPemeriksaanJawab.setColumns(20);
        TPemeriksaanJawab.setRows(5);
        TPemeriksaanJawab.setText("Pemeriksaan\n");
        TPemeriksaanJawab.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        TPemeriksaanJawab.setName("TPemeriksaanJawab"); // NOI18N
        TPemeriksaanJawab.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TPemeriksaanJawabKeyPressed(evt);
            }
        });

        BtnKeluar6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/cross.png"))); // NOI18N
        BtnKeluar6.setMnemonic('K');
        BtnKeluar6.setText("Tutup");
        BtnKeluar6.setToolTipText("Alt+K");
        BtnKeluar6.setName("BtnKeluar6"); // NOI18N
        BtnKeluar6.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnKeluar6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnKeluar6ActionPerformed(evt);
            }
        });
        BtnKeluar6.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnKeluar6KeyPressed(evt);
            }
        });

        javax.swing.GroupLayout internalFrame8Layout = new javax.swing.GroupLayout(internalFrame8);
        internalFrame8.setLayout(internalFrame8Layout);
        internalFrame8Layout.setHorizontalGroup(
            internalFrame8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(internalFrame8Layout.createSequentialGroup()
                .addGroup(internalFrame8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(internalFrame8Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(internalFrame8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(internalFrame8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(internalFrame8Layout.createSequentialGroup()
                                    .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(LabelNoRM, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(internalFrame8Layout.createSequentialGroup()
                                    .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(LabelNoRW, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(internalFrame8Layout.createSequentialGroup()
                                .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(LabelNama, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(381, 381, 381))
                    .addGroup(internalFrame8Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1269, 1269, 1269))
                    .addGroup(internalFrame8Layout.createSequentialGroup()
                        .addGroup(internalFrame8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(internalFrame8Layout.createSequentialGroup()
                                .addGap(25, 25, 25)
                                .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(internalFrame8Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(internalFrame8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(internalFrame8Layout.createSequentialGroup()
                                        .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(LabelDokter, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(internalFrame8Layout.createSequentialGroup()
                                        .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(LabelPoliPerujuk, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(internalFrame8Layout.createSequentialGroup()
                                        .addGap(12, 12, 12)
                                        .addGroup(internalFrame8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(internalFrame8Layout.createSequentialGroup()
                                                .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(LabelPoliTujuan, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(internalFrame8Layout.createSequentialGroup()
                                                .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(LabelDokterTujuan, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(internalFrame8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addGroup(internalFrame8Layout.createSequentialGroup()
                                                    .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGap(6, 6, 6)
                                                    .addComponent(TSaranJawab, javax.swing.GroupLayout.PREFERRED_SIZE, 391, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGroup(internalFrame8Layout.createSequentialGroup()
                                                    .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGap(6, 6, 6)
                                                    .addGroup(internalFrame8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(TPemeriksaanJawab, javax.swing.GroupLayout.PREFERRED_SIZE, 391, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(TKonsulJawab, javax.swing.GroupLayout.PREFERRED_SIZE, 391, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                            .addGroup(internalFrame8Layout.createSequentialGroup()
                                                .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(LabelDiagnosa, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(internalFrame8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(BtnKeluar6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        internalFrame8Layout.setVerticalGroup(
            internalFrame8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(internalFrame8Layout.createSequentialGroup()
                .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(internalFrame8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LabelNama, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(internalFrame8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LabelNoRM, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(internalFrame8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LabelNoRW, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(internalFrame8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LabelDokter, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(internalFrame8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LabelPoliPerujuk, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(internalFrame8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LabelDokterTujuan, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(internalFrame8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LabelPoliTujuan, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(internalFrame8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(internalFrame8Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(TKonsulJawab, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(internalFrame8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TPemeriksaanJawab, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(internalFrame8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LabelDiagnosa, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(internalFrame8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TSaranJawab, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                .addComponent(BtnKeluar6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout DlgJawabanKonsulLayout = new javax.swing.GroupLayout(DlgJawabanKonsul.getContentPane());
        DlgJawabanKonsul.getContentPane().setLayout(DlgJawabanKonsulLayout);
        DlgJawabanKonsulLayout.setHorizontalGroup(
            DlgJawabanKonsulLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, DlgJawabanKonsulLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(internalFrame8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        DlgJawabanKonsulLayout.setVerticalGroup(
            DlgJawabanKonsulLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DlgJawabanKonsulLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(internalFrame8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Registrasi Periksa Hari Ini ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(70, 70, 70))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        jPanel2.setName("jPanel2"); // NOI18N
        jPanel2.setOpaque(false);
        jPanel2.setLayout(new java.awt.BorderLayout(1, 1));

        panelGlass6.setName("panelGlass6"); // NOI18N
        panelGlass6.setPreferredSize(new java.awt.Dimension(55, 55));
        panelGlass6.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        BtnSimpan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/save-16x16.png"))); // NOI18N
        BtnSimpan.setMnemonic('S');
        BtnSimpan.setText("Simpan");
        BtnSimpan.setToolTipText("Alt+S");
        BtnSimpan.setName("BtnSimpan"); // NOI18N
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
        BtnBatal.setName("BtnBatal"); // NOI18N
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
        BtnEdit.setName("BtnEdit"); // NOI18N
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
        BtnHapus.setName("BtnHapus"); // NOI18N
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
        BtnPrint.setName("BtnPrint"); // NOI18N
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
        BtnAll.setName("BtnAll"); // NOI18N
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

        jLabel10.setText("Record :");
        jLabel10.setName("jLabel10"); // NOI18N
        jLabel10.setPreferredSize(new java.awt.Dimension(70, 30));
        panelGlass6.add(jLabel10);

        LCount.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LCount.setText("0");
        LCount.setName("LCount"); // NOI18N
        LCount.setPreferredSize(new java.awt.Dimension(72, 30));
        panelGlass6.add(LCount);

        BtnKeluar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/exit.png"))); // NOI18N
        BtnKeluar.setMnemonic('K');
        BtnKeluar.setText("Keluar");
        BtnKeluar.setToolTipText("Alt+K");
        BtnKeluar.setName("BtnKeluar"); // NOI18N
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

        panelGlass7.setName("panelGlass7"); // NOI18N
        panelGlass7.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass7.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        jLabel15.setText("Periode :");
        jLabel15.setName("jLabel15"); // NOI18N
        jLabel15.setPreferredSize(new java.awt.Dimension(60, 23));
        panelGlass7.add(jLabel15);

        DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "29-01-2021" }));
        DTPCari1.setDisplayFormat("dd-MM-yyyy");
        DTPCari1.setName("DTPCari1"); // NOI18N
        DTPCari1.setOpaque(false);
        DTPCari1.setPreferredSize(new java.awt.Dimension(133, 23));
        panelGlass7.add(DTPCari1);

        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("s.d");
        jLabel17.setName("jLabel17"); // NOI18N
        jLabel17.setPreferredSize(new java.awt.Dimension(24, 23));
        panelGlass7.add(jLabel17);

        DTPCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "29-01-2021" }));
        DTPCari2.setDisplayFormat("dd-MM-yyyy");
        DTPCari2.setName("DTPCari2"); // NOI18N
        DTPCari2.setOpaque(false);
        DTPCari2.setPreferredSize(new java.awt.Dimension(133, 23));
        panelGlass7.add(DTPCari2);

        jLabel6.setText("Key Word :");
        jLabel6.setName("jLabel6"); // NOI18N
        jLabel6.setPreferredSize(new java.awt.Dimension(158, 23));
        panelGlass7.add(jLabel6);

        TCari.setName("TCari"); // NOI18N
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
        BtnCari.setName("BtnCari"); // NOI18N
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

        panelGlass8.setName("panelGlass8"); // NOI18N
        panelGlass8.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass8.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        jLabel14.setText("Dokter :");
        jLabel14.setName("jLabel14"); // NOI18N
        jLabel14.setPreferredSize(new java.awt.Dimension(60, 23));
        panelGlass8.add(jLabel14);

        CrDokter.setEditable(false);
        CrDokter.setName("CrDokter"); // NOI18N
        CrDokter.setPreferredSize(new java.awt.Dimension(300, 23));
        panelGlass8.add(CrDokter);

        BtnSeek3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnSeek3.setMnemonic('6');
        BtnSeek3.setToolTipText("ALt+6");
        BtnSeek3.setName("BtnSeek3"); // NOI18N
        BtnSeek3.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnSeek3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSeek3ActionPerformed(evt);
            }
        });
        panelGlass8.add(BtnSeek3);

        jLabel16.setText("Unit :");
        jLabel16.setName("jLabel16"); // NOI18N
        jLabel16.setPreferredSize(new java.awt.Dimension(125, 23));
        panelGlass8.add(jLabel16);

        CrPoli.setEditable(false);
        CrPoli.setName("CrPoli"); // NOI18N
        CrPoli.setPreferredSize(new java.awt.Dimension(300, 23));
        panelGlass8.add(CrPoli);

        BtnSeek4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnSeek4.setMnemonic('5');
        BtnSeek4.setToolTipText("ALt+5");
        BtnSeek4.setName("BtnSeek4"); // NOI18N
        BtnSeek4.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnSeek4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSeek4ActionPerformed(evt);
            }
        });
        panelGlass8.add(BtnSeek4);

        jPanel2.add(panelGlass8, java.awt.BorderLayout.PAGE_START);

        internalFrame1.add(jPanel2, java.awt.BorderLayout.PAGE_END);

        PanelInput.setName("PanelInput"); // NOI18N
        PanelInput.setOpaque(false);
        PanelInput.setLayout(new java.awt.BorderLayout(1, 1));

        FormInput.setName("FormInput"); // NOI18N
        FormInput.setPreferredSize(new java.awt.Dimension(490, 167));
        FormInput.setLayout(null);

        jLabel3.setText("No. Reg. :");
        jLabel3.setName("jLabel3"); // NOI18N
        FormInput.add(jLabel3);
        jLabel3.setBounds(0, 12, 70, 23);

        jLabel4.setText("No. Rawat :");
        jLabel4.setName("jLabel4"); // NOI18N
        FormInput.add(jLabel4);
        jLabel4.setBounds(0, 42, 70, 23);

        TDokter.setEditable(false);
        TDokter.setName("TDokter"); // NOI18N
        FormInput.add(TDokter);
        TDokter.setBounds(183, 102, 209, 23);

        TNoRw.setHighlighter(null);
        TNoRw.setName("TNoRw"); // NOI18N
        TNoRw.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TNoRwKeyPressed(evt);
            }
        });
        FormInput.add(TNoRw);
        TNoRw.setBounds(74, 42, 220, 23);

        jLabel8.setText("Tgl. Reg. :");
        jLabel8.setName("jLabel8"); // NOI18N
        FormInput.add(jLabel8);
        jLabel8.setBounds(0, 72, 70, 23);

        jLabel13.setText("Dr Dituju :");
        jLabel13.setName("jLabel13"); // NOI18N
        FormInput.add(jLabel13);
        jLabel13.setBounds(0, 102, 70, 23);

        jLabel9.setText("Jam :");
        jLabel9.setName("jLabel9"); // NOI18N
        FormInput.add(jLabel9);
        jLabel9.setBounds(165, 72, 36, 23);

        DTPReg.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "29-01-2021" }));
        DTPReg.setDisplayFormat("dd-MM-yyyy");
        DTPReg.setName("DTPReg"); // NOI18N
        DTPReg.setOpaque(false);
        DTPReg.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                DTPRegItemStateChanged(evt);
            }
        });
        DTPReg.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DTPRegKeyPressed(evt);
            }
        });
        FormInput.add(DTPReg);
        DTPReg.setBounds(74, 72, 90, 23);

        jLabel20.setText("Png. Jawab :");
        jLabel20.setName("jLabel20"); // NOI18N
        FormInput.add(jLabel20);
        jLabel20.setBounds(416, 42, 100, 23);

        TPngJwb.setHighlighter(null);
        TPngJwb.setName("TPngJwb"); // NOI18N
        TPngJwb.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TPngJwbKeyPressed(evt);
            }
        });
        FormInput.add(TPngJwb);
        TPngJwb.setBounds(520, 42, 150, 23);

        TNoRM.setHighlighter(null);
        TNoRM.setName("TNoRM"); // NOI18N
        TNoRM.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TNoRMKeyPressed(evt);
            }
        });
        FormInput.add(TNoRM);
        TNoRM.setBounds(520, 12, 110, 23);

        TNoReg.setHighlighter(null);
        TNoReg.setName("TNoReg"); // NOI18N
        TNoReg.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TNoRegKeyPressed(evt);
            }
        });
        FormInput.add(TNoReg);
        TNoReg.setBounds(74, 12, 120, 23);

        jLabel21.setText("Almt Png. Jwb :");
        jLabel21.setName("jLabel21"); // NOI18N
        FormInput.add(jLabel21);
        jLabel21.setBounds(426, 72, 90, 23);

        CmbJam.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        CmbJam.setName("CmbJam"); // NOI18N
        CmbJam.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                CmbJamKeyPressed(evt);
            }
        });
        FormInput.add(CmbJam);
        CmbJam.setBounds(205, 72, 62, 23);

        CmbMenit.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        CmbMenit.setName("CmbMenit"); // NOI18N
        CmbMenit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                CmbMenitKeyPressed(evt);
            }
        });
        FormInput.add(CmbMenit);
        CmbMenit.setBounds(270, 72, 62, 23);

        CmbDetik.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        CmbDetik.setName("CmbDetik"); // NOI18N
        CmbDetik.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                CmbDetikKeyPressed(evt);
            }
        });
        FormInput.add(CmbDetik);
        CmbDetik.setBounds(335, 72, 62, 23);

        jLabel7.setText("No.Rekam Medik :");
        jLabel7.setName("jLabel7"); // NOI18N
        FormInput.add(jLabel7);
        jLabel7.setBounds(416, 12, 100, 23);

        TAlmt.setHighlighter(null);
        TAlmt.setName("TAlmt"); // NOI18N
        TAlmt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TAlmtKeyPressed(evt);
            }
        });
        FormInput.add(TAlmt);
        TAlmt.setBounds(520, 72, 170, 23);

        BtnPasien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnPasien.setMnemonic('1');
        BtnPasien.setToolTipText("ALt+1");
        BtnPasien.setName("BtnPasien"); // NOI18N
        BtnPasien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPasienActionPerformed(evt);
            }
        });
        FormInput.add(BtnPasien);
        BtnPasien.setBounds(852, 12, 28, 23);

        TPasien.setEditable(false);
        TPasien.setHighlighter(null);
        TPasien.setName("TPasien"); // NOI18N
        FormInput.add(TPasien);
        TPasien.setBounds(632, 12, 218, 23);

        jLabel22.setText("Hubungan :");
        jLabel22.setName("jLabel22"); // NOI18N
        FormInput.add(jLabel22);
        jLabel22.setBounds(648, 42, 98, 23);

        THbngn.setHighlighter(null);
        THbngn.setName("THbngn"); // NOI18N
        THbngn.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                THbngnKeyPressed(evt);
            }
        });
        FormInput.add(THbngn);
        THbngn.setBounds(750, 42, 130, 23);

        ChkJln.setBorder(null);
        ChkJln.setSelected(true);
        ChkJln.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        ChkJln.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ChkJln.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        ChkJln.setName("ChkJln"); // NOI18N
        FormInput.add(ChkJln);
        ChkJln.setBounds(400, 72, 23, 23);

        jLabel19.setText("Unit :");
        jLabel19.setName("jLabel19"); // NOI18N
        FormInput.add(jLabel19);
        jLabel19.setBounds(0, 132, 70, 23);

        TPoli.setEditable(false);
        TPoli.setName("TPoli"); // NOI18N
        FormInput.add(TPoli);
        TPoli.setBounds(141, 132, 156, 23);

        TBiaya.setName("TBiaya"); // NOI18N
        TBiaya.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TBiayaKeyPressed(evt);
            }
        });
        FormInput.add(TBiaya);
        TBiaya.setBounds(298, 132, 94, 23);

        kddokter.setHighlighter(null);
        kddokter.setName("kddokter"); // NOI18N
        kddokter.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                kddokterKeyPressed(evt);
            }
        });
        FormInput.add(kddokter);
        kddokter.setBounds(74, 102, 107, 23);

        BtnDokter.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnDokter.setMnemonic('3');
        BtnDokter.setToolTipText("ALt+3");
        BtnDokter.setName("BtnDokter"); // NOI18N
        BtnDokter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnDokterActionPerformed(evt);
            }
        });
        FormInput.add(BtnDokter);
        BtnDokter.setBounds(395, 102, 28, 23);

        kdpoli.setHighlighter(null);
        kdpoli.setName("kdpoli"); // NOI18N
        kdpoli.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                kdpoliKeyPressed(evt);
            }
        });
        FormInput.add(kdpoli);
        kdpoli.setBounds(74, 132, 66, 23);

        BtnUnit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnUnit.setMnemonic('4');
        BtnUnit.setToolTipText("ALt+4");
        BtnUnit.setName("BtnUnit"); // NOI18N
        BtnUnit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnUnitActionPerformed(evt);
            }
        });
        FormInput.add(BtnUnit);
        BtnUnit.setBounds(395, 132, 28, 23);

        jLabel30.setText("Status :");
        jLabel30.setName("jLabel30"); // NOI18N
        FormInput.add(jLabel30);
        jLabel30.setBounds(648, 72, 98, 23);

        TStatus.setEditable(false);
        TStatus.setHighlighter(null);
        TStatus.setName("TStatus"); // NOI18N
        FormInput.add(TStatus);
        TStatus.setBounds(750, 72, 130, 23);

        jLabel18.setText("Jenis Bayar :");
        jLabel18.setName("jLabel18"); // NOI18N
        FormInput.add(jLabel18);
        jLabel18.setBounds(426, 102, 90, 23);

        kdpnj.setHighlighter(null);
        kdpnj.setName("kdpnj"); // NOI18N
        kdpnj.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                kdpnjKeyPressed(evt);
            }
        });
        FormInput.add(kdpnj);
        kdpnj.setBounds(520, 102, 70, 23);

        nmpnj.setEditable(false);
        nmpnj.setName("nmpnj"); // NOI18N
        FormInput.add(nmpnj);
        nmpnj.setBounds(592, 102, 259, 23);

        btnPenjab.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnPenjab.setMnemonic('2');
        btnPenjab.setToolTipText("ALt+2");
        btnPenjab.setName("btnPenjab"); // NOI18N
        btnPenjab.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPenjabActionPerformed(evt);
            }
        });
        FormInput.add(btnPenjab);
        btnPenjab.setBounds(852, 102, 28, 23);

        jLabel23.setText("Asal Rujukan :");
        jLabel23.setName("jLabel23"); // NOI18N
        FormInput.add(jLabel23);
        jLabel23.setBounds(426, 132, 90, 23);

        AsalRujukan.setName("AsalRujukan"); // NOI18N
        AsalRujukan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                AsalRujukanKeyPressed(evt);
            }
        });
        FormInput.add(AsalRujukan);
        AsalRujukan.setBounds(520, 132, 331, 23);

        btnPenjab1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnPenjab1.setMnemonic('2');
        btnPenjab1.setToolTipText("ALt+2");
        btnPenjab1.setName("btnPenjab1"); // NOI18N
        btnPenjab1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPenjab1ActionPerformed(evt);
            }
        });
        FormInput.add(btnPenjab1);
        btnPenjab1.setBounds(852, 132, 28, 23);

        ChkTracker.setBorder(null);
        ChkTracker.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        ChkTracker.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ChkTracker.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        ChkTracker.setName("ChkTracker"); // NOI18N
        FormInput.add(ChkTracker);
        ChkTracker.setBounds(196, 12, 23, 23);

        PanelInput.add(FormInput, java.awt.BorderLayout.CENTER);

        ChkInput.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/143.png"))); // NOI18N
        ChkInput.setMnemonic('I');
        ChkInput.setText(".: Input Data");
        ChkInput.setToolTipText("Alt+I");
        ChkInput.setBorderPainted(true);
        ChkInput.setBorderPaintedFlat(true);
        ChkInput.setFocusable(false);
        ChkInput.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkInput.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkInput.setName("ChkInput"); // NOI18N
        ChkInput.setPreferredSize(new java.awt.Dimension(192, 20));
        ChkInput.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/143.png"))); // NOI18N
        ChkInput.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/145.png"))); // NOI18N
        ChkInput.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/145.png"))); // NOI18N
        ChkInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkInputActionPerformed(evt);
            }
        });
        PanelInput.add(ChkInput, java.awt.BorderLayout.PAGE_END);

        internalFrame1.add(PanelInput, java.awt.BorderLayout.PAGE_START);

        TabRawat.setBackground(new java.awt.Color(255, 255, 254));
        TabRawat.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(241, 246, 236)));
        TabRawat.setForeground(new java.awt.Color(70, 70, 70));
        TabRawat.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        TabRawat.setInheritsPopupMenu(true);
        TabRawat.setName("TabRawat"); // NOI18N
        TabRawat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabRawatMouseClicked(evt);
            }
        });

        Scroll.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        Scroll.setToolTipText("Klik data di table, kemudian klik kanan untuk memilih menu yang diinginkan");
        Scroll.setComponentPopupMenu(jPopupMenu1);
        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);

        tbPetugas.setToolTipText("Klik data di table, kemudian klik kanan untuk memilih menu yang diinginkan");
        tbPetugas.setComponentPopupMenu(jPopupMenu1);
        tbPetugas.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tbPetugas.setName("tbPetugas"); // NOI18N
        tbPetugas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbPetugasMouseClicked(evt);
            }
        });
        tbPetugas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbPetugasKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tbPetugasKeyReleased(evt);
            }
        });
        Scroll.setViewportView(tbPetugas);

        TabRawat.addTab("Registrasi Awal", Scroll);

        Scroll1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        Scroll1.setToolTipText("Klik data di table, kemudian klik kanan untuk memilih menu yang diinginkan");
        Scroll1.setComponentPopupMenu(jPopupMenu2);
        Scroll1.setName("Scroll1"); // NOI18N
        Scroll1.setOpaque(true);

        tbPetugas2.setToolTipText("Klik data di table, kemudian klik kanan untuk memilih menu yang diinginkan");
        tbPetugas2.setComponentPopupMenu(jPopupMenu2);
        tbPetugas2.setName("tbPetugas2"); // NOI18N
        tbPetugas2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbPetugas2MouseClicked(evt);
            }
        });
        tbPetugas2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbPetugas2KeyPressed(evt);
            }
        });
        Scroll1.setViewportView(tbPetugas2);

        TabRawat.addTab("Rujukan Internal Poli", Scroll1);

        Scroll2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        Scroll2.setToolTipText("Klik data di table, kemudian klik kanan untuk memilih menu yang diinginkan");
        Scroll2.setComponentPopupMenu(jPopupMenu1);
        Scroll2.setName("Scroll2"); // NOI18N
        Scroll2.setOpaque(true);

        tbPetugas1.setToolTipText("Klik data di table, kemudian klik kanan untuk memilih menu yang diinginkan");
        tbPetugas1.setComponentPopupMenu(jPopupMenu1);
        tbPetugas1.setName("tbPetugas1"); // NOI18N
        tbPetugas1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbPetugas1MouseClicked(evt);
            }
        });
        tbPetugas1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbPetugas1KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tbPetugas1KeyReleased(evt);
            }
        });
        Scroll2.setViewportView(tbPetugas1);

        TabRawat.addTab("Pasien Lanjutan", Scroll2);

        internalFrame1.add(TabRawat, java.awt.BorderLayout.CENTER);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void TNoRwKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TNoRwKeyPressed
        Valid.pindah(evt,TNoReg,DTPReg);
}//GEN-LAST:event_TNoRwKeyPressed

    private void DTPRegKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DTPRegKeyPressed
        Valid.pindah(evt,TNoRw,CmbJam);
}//GEN-LAST:event_DTPRegKeyPressed

    private void TPngJwbKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TPngJwbKeyPressed
        Valid.pindah(evt,TNoRM,THbngn);
}//GEN-LAST:event_TPngJwbKeyPressed

    private void TNoRMKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TNoRMKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_PAGE_DOWN){
            isPas();
        }else if(evt.getKeyCode()==KeyEvent.VK_UP){
            BtnPasienActionPerformed(null);
        }else if(evt.getKeyCode()==KeyEvent.VK_DOWN){
            if(!TPasien.getText().equals("")){
                Map<String, Object> param = new HashMap<>();
                param.put("poli",TPoli.getText());
                param.put("antrian",TNoReg.getText());
                param.put("nama",TPasien.getText());
                param.put("norm",TNoRM.getText());
                param.put("bayar",nmpnj.getText());
                param.put("namars",var.getnamars());
                param.put("alamatrs",var.getalamatrs());
                param.put("kotars",var.getkabupatenrs());
                param.put("propinsirs",var.getpropinsirs());
                param.put("kontakrs",var.getkontakrs());
                param.put("emailrs",var.getemailrs());
                param.put("logo",Sequel.cariGambar("select logo from setting"));
                Valid.MyReport("rptCheckList.jrxml","report","::[ Check List ]::",
                        "select current_date() as sekarang",param); 
            }             
        }else if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            isPas();
            TPngJwb.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_UP){
            kdpoli.requestFocus();
        }
}//GEN-LAST:event_TNoRMKeyPressed

    private void TNoRegKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TNoRegKeyPressed
        Valid.pindah(evt,TCari,TNoRw);
}//GEN-LAST:event_TNoRegKeyPressed

    private void CmbJamKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CmbJamKeyPressed
        Valid.pindah(evt,DTPReg,CmbMenit);
}//GEN-LAST:event_CmbJamKeyPressed

    private void CmbMenitKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CmbMenitKeyPressed
        Valid.pindah(evt,CmbJam,CmbDetik);
}//GEN-LAST:event_CmbMenitKeyPressed

    private void CmbDetikKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CmbDetikKeyPressed
        Valid.pindah(evt,CmbMenit,kddokter);
}//GEN-LAST:event_CmbDetikKeyPressed

    private void TAlmtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TAlmtKeyPressed
        Valid.pindah(evt,THbngn,kdpnj);
}//GEN-LAST:event_TAlmtKeyPressed

    private void BtnPasienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPasienActionPerformed
        var.setform("DlgReg");
        //pasien.penjab.removeWindowListener(null);
        //pasien.penjab.getTable().removeKeyListener(null);
        pasien.emptTeks();
        pasien.isCek();
        pasien.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
        pasien.setLocationRelativeTo(internalFrame1);
        pasien.setVisible(true);
}//GEN-LAST:event_BtnPasienActionPerformed

    private void THbngnKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_THbngnKeyPressed
        Valid.pindah(evt,TPngJwb,TAlmt);
}//GEN-LAST:event_THbngnKeyPressed

    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
        if(TNoReg.getText().trim().equals("")){
            Valid.textKosong(TNoReg,"No.Regristrasi");
        }else if(TNoRw.getText().trim().equals("")){
            Valid.textKosong(TNoRw,"No.Rawat");
        }else if(TDokter.getText().trim().equals("")){
            Valid.textKosong(kddokter,"dokter");
        }else if(TNoRM.getText().trim().equals("")||TPasien.getText().trim().equals("")){
            Valid.textKosong(TNoRM,"pasien");
        }else if(TPoli.getText().trim().equals("")){
            Valid.textKosong(kdpoli,"poliklinik");
        }else if(TBiaya.getText().trim().equals("")){
            Valid.textKosong(TBiaya,"biaya regristrasi");
        }else if(kdpnj.getText().trim().equals("")||nmpnj.getText().trim().equals("")){
            Valid.textKosong(kdpnj,"Jenis Bayar");
        }else if(Sequel.cariInteger("select count(pasien.no_rkm_medis) from pasien inner join reg_periksa inner join kamar_inap "+
                 "on reg_periksa.no_rkm_medis=pasien.no_rkm_medis and reg_periksa.no_rawat=kamar_inap.no_rawat "+
                 "where kamar_inap.stts_pulang='-' and pasien.no_rkm_medis=?",TNoRM.getText())>0){
            JOptionPane.showMessageDialog(null,"Pasien sedang dalam masa perawatan di kamar inap..!!");
            TNoRM.requestFocus();
        }else{
            if(var.getkode().equals("Admin Utama")){
                isRegistrasi();
            }else{
                if(aktifjadwal.equals("aktif")){
                    if(Sequel.cariInteger("select count(no_rawat) from reg_periksa where kd_dokter='"+kddokter.getText()+"' and tgl_registrasi='"+Valid.SetTgl(DTPReg.getSelectedItem()+"")+"' ")>=kuota){
                        JOptionPane.showMessageDialog(null,"Eiiits, Kuota registrasi penuh..!!!");
                        TCari.requestFocus();
                    }else{
                        isRegistrasi();
                    }                    
                }else{
                    isRegistrasi();
                }  
            }                          
        }
}//GEN-LAST:event_BtnSimpanActionPerformed

    private void BtnSimpanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSimpanKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnSimpanActionPerformed(null);
        }else{
            Valid.pindah(evt,kdpnj,BtnBatal);
        }
}//GEN-LAST:event_BtnSimpanKeyPressed

    private void BtnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBatalActionPerformed
        ChkInput.setSelected(true);
        isForm(); 
        emptTeks();        
}//GEN-LAST:event_BtnBatalActionPerformed

    private void BtnBatalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnBatalKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            emptTeks();
        }else{Valid.pindah(evt, BtnSimpan, BtnHapus);}
}//GEN-LAST:event_BtnBatalKeyPressed

    private void BtnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusActionPerformed
        
        for(i=0;i<tbPetugas.getRowCount();i++){ 
            if(tbPetugas.getValueAt(i,0).toString().equals("true")){
                Sequel.meghapus("reg_periksa","no_rawat",tbPetugas.getValueAt(i,2).toString());
                if(var.getkode().equals("Admin Utama")){
                    Sequel.meghapus("nota_inap","no_rawat",tbPetugas.getValueAt(i,2).toString());
                    Sequel.meghapus("nota_jalan","no_rawat",tbPetugas.getValueAt(i,2).toString());
                }
            }
        } 
        
        tampil();
        emptTeks();
}//GEN-LAST:event_BtnHapusActionPerformed

    private void BtnHapusKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnHapusKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnHapusActionPerformed(null);
        }else{
            Valid.pindah(evt, BtnBatal, BtnEdit);
        }
}//GEN-LAST:event_BtnHapusKeyPressed

    private void BtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrintActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        if(tabMode.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Maaf, data sudah habis. Tidak ada data yang bisa anda print...!!!!");
            BtnBatal.requestFocus();
        }else if(tabMode.getRowCount()!=0){
            Map<String, Object> param = new HashMap<>();                 
            param.put("namars",var.getnamars());
            param.put("alamatrs",var.getalamatrs());
            param.put("kotars",var.getkabupatenrs());
            param.put("propinsirs",var.getpropinsirs());
            param.put("kontakrs",var.getkontakrs());
            param.put("emailrs",var.getemailrs());   
            param.put("logo",Sequel.cariGambar("select logo from setting")); 
               Valid.MyReport("rptReg.jrxml","report","::[ Data Registrasi Periksa ]::","select reg_periksa.no_reg,reg_periksa.no_rawat,reg_periksa.tgl_registrasi,reg_periksa.jam_reg,"+
                   "reg_periksa.kd_dokter,dokter.nm_dokter,reg_periksa.no_rkm_medis,pasien.nm_pasien,pasien.jk,concat(reg_periksa.umurdaftar,' ',reg_periksa.sttsumur)as umur,poliklinik.nm_poli,ifnull((select perujuk from rujuk_masuk where rujuk_masuk.no_rawat=reg_periksa.no_rawat),'') as perujuk,"+
                   "reg_periksa.p_jawab,reg_periksa.almt_pj,reg_periksa.hubunganpj,reg_periksa.biaya_reg,reg_periksa.stts_daftar,penjab.png_jawab,pasien.no_tlp "+
                   "from reg_periksa inner join dokter inner join pasien inner join poliklinik inner join penjab  "+
                   "on reg_periksa.kd_dokter=dokter.kd_dokter and reg_periksa.kd_pj=penjab.kd_pj "+
                   "and reg_periksa.no_rkm_medis=pasien.no_rkm_medis and reg_periksa.kd_poli=poliklinik.kd_poli  "+
                   "where poliklinik.kd_poli<>'IGDK' and dokter.nm_dokter like '%"+CrDokter.getText()+"%' and poliklinik.nm_poli like '%"+CrPoli.getText()+"%' and tgl_registrasi between '"+Valid.SetTgl(DTPCari1.getSelectedItem()+"")+"' and '"+Valid.SetTgl(DTPCari2.getSelectedItem()+"")+"' and reg_periksa.no_reg like '%"+TCari.getText().trim()+"%' or "+
                   "poliklinik.kd_poli<>'IGDK' and dokter.nm_dokter like '%"+CrDokter.getText()+"%' and poliklinik.nm_poli like '%"+CrPoli.getText()+"%' and tgl_registrasi between '"+Valid.SetTgl(DTPCari1.getSelectedItem()+"")+"' and '"+Valid.SetTgl(DTPCari2.getSelectedItem()+"")+"' and reg_periksa.no_rawat like '%"+TCari.getText().trim()+"%' or "+
                   "poliklinik.kd_poli<>'IGDK' and dokter.nm_dokter like '%"+CrDokter.getText()+"%' and poliklinik.nm_poli like '%"+CrPoli.getText()+"%' and tgl_registrasi between '"+Valid.SetTgl(DTPCari1.getSelectedItem()+"")+"' and '"+Valid.SetTgl(DTPCari2.getSelectedItem()+"")+"' and reg_periksa.tgl_registrasi like '%"+TCari.getText().trim()+"%' or "+
                   "poliklinik.kd_poli<>'IGDK' and dokter.nm_dokter like '%"+CrDokter.getText()+"%' and poliklinik.nm_poli like '%"+CrPoli.getText()+"%' and tgl_registrasi between '"+Valid.SetTgl(DTPCari1.getSelectedItem()+"")+"' and '"+Valid.SetTgl(DTPCari2.getSelectedItem()+"")+"' and reg_periksa.kd_dokter like '%"+TCari.getText().trim()+"%' or "+
                   "poliklinik.kd_poli<>'IGDK' and dokter.nm_dokter like '%"+CrDokter.getText()+"%' and poliklinik.nm_poli like '%"+CrPoli.getText()+"%' and tgl_registrasi between '"+Valid.SetTgl(DTPCari1.getSelectedItem()+"")+"' and '"+Valid.SetTgl(DTPCari2.getSelectedItem()+"")+"' and dokter.nm_dokter like '%"+TCari.getText().trim()+"%' or "+
                   "poliklinik.kd_poli<>'IGDK' and dokter.nm_dokter like '%"+CrDokter.getText()+"%' and poliklinik.nm_poli like '%"+CrPoli.getText()+"%' and tgl_registrasi between '"+Valid.SetTgl(DTPCari1.getSelectedItem()+"")+"' and '"+Valid.SetTgl(DTPCari2.getSelectedItem()+"")+"' and reg_periksa.no_rkm_medis like '%"+TCari.getText().trim()+"%' or "+
                   "poliklinik.kd_poli<>'IGDK' and dokter.nm_dokter like '%"+CrDokter.getText()+"%' and poliklinik.nm_poli like '%"+CrPoli.getText()+"%' and tgl_registrasi between '"+Valid.SetTgl(DTPCari1.getSelectedItem()+"")+"' and '"+Valid.SetTgl(DTPCari2.getSelectedItem()+"")+"' and reg_periksa.stts_daftar like '%"+TCari.getText().trim()+"%' or "+
                   "poliklinik.kd_poli<>'IGDK' and dokter.nm_dokter like '%"+CrDokter.getText()+"%' and poliklinik.nm_poli like '%"+CrPoli.getText()+"%' and tgl_registrasi between '"+Valid.SetTgl(DTPCari1.getSelectedItem()+"")+"' and '"+Valid.SetTgl(DTPCari2.getSelectedItem()+"")+"' and pasien.nm_pasien like '%"+TCari.getText().trim()+"%' or "+
                   "poliklinik.kd_poli<>'IGDK' and dokter.nm_dokter like '%"+CrDokter.getText()+"%' and poliklinik.nm_poli like '%"+CrPoli.getText()+"%' and tgl_registrasi between '"+Valid.SetTgl(DTPCari1.getSelectedItem()+"")+"' and '"+Valid.SetTgl(DTPCari2.getSelectedItem()+"")+"' and poliklinik.nm_poli like '%"+TCari.getText().trim()+"%' or "+
                   "poliklinik.kd_poli<>'IGDK' and dokter.nm_dokter like '%"+CrDokter.getText()+"%' and poliklinik.nm_poli like '%"+CrPoli.getText()+"%' and tgl_registrasi between '"+Valid.SetTgl(DTPCari1.getSelectedItem()+"")+"' and '"+Valid.SetTgl(DTPCari2.getSelectedItem()+"")+"' and reg_periksa.p_jawab like '%"+TCari.getText().trim()+"%' or "+
                   "poliklinik.kd_poli<>'IGDK' and dokter.nm_dokter like '%"+CrDokter.getText()+"%' and poliklinik.nm_poli like '%"+CrPoli.getText()+"%' and tgl_registrasi between '"+Valid.SetTgl(DTPCari1.getSelectedItem()+"")+"' and '"+Valid.SetTgl(DTPCari2.getSelectedItem()+"")+"' and reg_periksa.almt_pj like '%"+TCari.getText().trim()+"%' or "+
                   "poliklinik.kd_poli<>'IGDK' and dokter.nm_dokter like '%"+CrDokter.getText()+"%' and poliklinik.nm_poli like '%"+CrPoli.getText()+"%' and tgl_registrasi between '"+Valid.SetTgl(DTPCari1.getSelectedItem()+"")+"' and '"+Valid.SetTgl(DTPCari2.getSelectedItem()+"")+"' and penjab.png_jawab like '%"+TCari.getText().trim()+"%' or "+
                   "poliklinik.kd_poli<>'IGDK' and dokter.nm_dokter like '%"+CrDokter.getText()+"%' and poliklinik.nm_poli like '%"+CrPoli.getText()+"%' and tgl_registrasi between '"+Valid.SetTgl(DTPCari1.getSelectedItem()+"")+"' and '"+Valid.SetTgl(DTPCari2.getSelectedItem()+"")+"' and reg_periksa.hubunganpj like '%"+TCari.getText().trim()+"%' order by reg_periksa.tgl_registrasi,reg_periksa.jam_reg desc",param);   
        }
        this.setCursor(Cursor.getDefaultCursor());
}//GEN-LAST:event_BtnPrintActionPerformed

    private void BtnPrintKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnPrintKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnPrintActionPerformed(null);
        }else{
            Valid.pindah(evt, BtnHapus, BtnAll);
        }
}//GEN-LAST:event_BtnPrintKeyPressed

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        pasien.dispose();
        pasien.bahasa.dispose();
        pasien.cacat.dispose();
        pasien.golonganpolri.dispose();
        pasien.golongantni.dispose();
        pasien.jabatanpolri.dispose();
        pasien.jabatantni.dispose();
        pasien.kab.dispose();
        pasien.kec.dispose();
        pasien.kel.dispose();
        pasien.pangkatpolri.dispose();
        pasien.pangkattni.dispose();
        pasien.penjab.dispose();
        pasien.perusahaan.dispose();
        pasien.prop.dispose();
        pasien.satuanpolri.dispose();
        pasien.satuantni.dispose();
        pasien.suku.dispose();
        dokter.dispose();
        dokter.dokter.dispose();
        dokter2.dokter.dispose();
        poli.dispose();
        poli2.dispose();
        dlgrjk.dispose();
        rujukmasuk.WindowPerujuk.dispose();   
        DlgSakit.dispose();
        DlgSakit2.dispose();
        DlgDemografi.dispose();
        DlgCatatan.dispose();
        var.setAktif(false);
        dispose();
}//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnKeluarActionPerformed(null);
        }else{Valid.pindah(evt,BtnPrint,TCari);}
}//GEN-LAST:event_BtnKeluarKeyPressed

    private void BtnAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAllActionPerformed
        CrPoli.setText("");
        CrDokter.setText("");
        TCari.setText("");
        if(TabRawat.getSelectedIndex()==0){
            tampil();
        }else if(TabRawat.getSelectedIndex()==1){
            tampil2();
        }
}//GEN-LAST:event_BtnAllActionPerformed

    private void BtnAllKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAllKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            tampil();
            TCari.setText("");
        }else{
            Valid.pindah(evt, BtnPrint, BtnKeluar);
        }
}//GEN-LAST:event_BtnAllKeyPressed

    private void BtnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEditActionPerformed
        if(TNoReg.getText().trim().equals("")){
            Valid.textKosong(TNoReg,"No.Regristrasi");
        }else if(TNoRw.getText().trim().equals("")){
            Valid.textKosong(TNoRw,"No.Rawat");
        }else if(TDokter.getText().trim().equals("")){
            Valid.textKosong(kddokter,"dokter");
        }else if(TNoRM.getText().trim().equals("")||TPasien.getText().trim().equals("")){
            Valid.textKosong(TNoRM,"pasien");
        }else if(TPoli.getText().trim().equals("")){
            Valid.textKosong(kdpoli,"poliklinik");
        }else if(TBiaya.getText().trim().equals("")){
            Valid.textKosong(TBiaya,"biaya regristrasi");
        }else{
            if(tbPetugas.getSelectedRow()>-1){
                if(var.getedit_registrasi()==true){
                    Sequel.queryu2("update reg_periksa set no_rawat=?,no_reg=?,tgl_registrasi=?,jam_reg=?,kd_dokter=?,no_rkm_medis=?,kd_poli=?,"+
                        "p_jawab=?,almt_pj=?,biaya_reg=?,hubunganpj=?,stts_daftar=?,kd_pj=?,umurdaftar=?,sttsumur=? where no_rawat=?",16,
                        new String[]{TNoRw.getText(),TNoReg.getText(),Valid.SetTgl(DTPReg.getSelectedItem()+""),CmbJam.getSelectedItem()+":"+CmbMenit.getSelectedItem()+":"+CmbDetik.getSelectedItem(),
                            kddokter.getText(),TNoRM.getText(),kdpoli.getText(),TPngJwb.getText(),TAlmt.getText(),TBiaya.getText(),THbngn.getText(),
                            TStatus.getText(),kdpnj.getText(),umur,sttsumur,tbPetugas.getValueAt(tbPetugas.getSelectedRow(),2).toString()
                        });
                }else{
                    if((Sequel.cariInteger("select count(no_rawat) from rawat_jl_dr where no_rawat=?",TNoRw.getText())>0)||
                            (Sequel.cariInteger("select count(no_rawat) from rawat_jl_pr where no_rawat=?",TNoRw.getText())>0)||
                            (Sequel.cariInteger("select count(no_rawat) from rawat_jl_drpr where no_rawat=?",TNoRw.getText())>0)||
                            (Sequel.cariInteger("select count(no_rawat) from periksa_lab where no_rawat=?",TNoRw.getText())>0)||
                            (Sequel.cariInteger("select count(no_rawat) from kamar_inap where no_rawat=?",TNoRw.getText())>0)){
                        JOptionPane.showMessageDialog(rootPane,"Maaf pasien sudah ada transaksi sebelumnya & tidak bisa diedit..!!! ");
                        TCari.requestFocus();
                    }else{
                        Sequel.queryu2("update reg_periksa set no_rawat=?,no_reg=?,tgl_registrasi=?,jam_reg=?,kd_dokter=?,no_rkm_medis=?,kd_poli=?,"+
                            "p_jawab=?,almt_pj=?,biaya_reg=?,hubunganpj=?,stts_daftar=?,kd_pj=?,umurdaftar=?,sttsumur=? where no_rawat=?",16,
                            new String[]{TNoRw.getText(),TNoReg.getText(),Valid.SetTgl(DTPReg.getSelectedItem()+""),CmbJam.getSelectedItem()+":"+CmbMenit.getSelectedItem()+":"+CmbDetik.getSelectedItem(),
                                kddokter.getText(),TNoRM.getText(),kdpoli.getText(),TPngJwb.getText(),TAlmt.getText(),TBiaya.getText(),THbngn.getText(),
                                TStatus.getText(),kdpnj.getText(),umur,sttsumur,tbPetugas.getValueAt(tbPetugas.getSelectedRow(),2).toString()
                        });
                    }
                }

                tampil();
                emptTeks();
            }                
        }
}//GEN-LAST:event_BtnEditActionPerformed

    private void BtnEditKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnEditKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            BtnEditActionPerformed(null);
        }else{
            Valid.pindah(evt, BtnHapus, BtnPrint);
        }
}//GEN-LAST:event_BtnEditKeyPressed

    private void TCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCariKeyPressed
         if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            BtnCariActionPerformed(null);
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_DOWN){
            BtnCari.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_UP){
            BtnKeluar.requestFocus();
        }
}//GEN-LAST:event_TCariKeyPressed

    private void BtnCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCariActionPerformed
        if(TabRawat.getSelectedIndex()==0){
            tampil();
        }else if(TabRawat.getSelectedIndex()==1){
            tampil2();
        }
}//GEN-LAST:event_BtnCariActionPerformed

    private void BtnCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCariKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnCariActionPerformed(null);
        }else{
            Valid.pindah(evt, TCari, BtnAll);
        }
}//GEN-LAST:event_BtnCariKeyPressed

    private void TBiayaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TBiayaKeyPressed
        Valid.pindah(evt,kdpoli,BtnSimpan);
}//GEN-LAST:event_TBiayaKeyPressed

    private void tbPetugasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbPetugasMouseClicked
        if(tabMode.getRowCount()!=0){
            try {
                getData();
            } catch (java.lang.NullPointerException e) {
            }
            if(evt.getClickCount()==1){
                i=tbPetugas.getSelectedColumn();
                if(i==8){
                    if(validasicatatan.equals("Yes")){
                        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                        LabelCatatan.setText(Sequel.cariIsi("select catatan from catatan_pasien where no_rkm_medis=?",TNoRM.getText()));
                        if(!LabelCatatan.getText().equals("")){
                            DlgCatatan.setLocationRelativeTo(TabRawat);
                            DlgCatatan.setVisible(true);
                        }else{
                            DlgCatatan.setVisible(false);
                        }                            
                        this.setCursor(Cursor.getDefaultCursor());
                    }  
                }                
            }else if(evt.getClickCount()==2){
                i=tbPetugas.getSelectedColumn();
                if(i==1){
                    if(MnKamarInap.isEnabled()==true){
                        MnKamarInapActionPerformed(null);
                    }                    
                }else if(i==2){
                    if(var.gettindakan_ralan()==true){
                        MnRawatJalanActionPerformed(null);
                    }                    
                }else if(i==3){
                    if(var.getberi_obat()==true){
                        MnPemberianObatActionPerformed(null);
                    }                   
                }else if(i==4){
                    if(var.getperiksa_lab()==true){
                        MnPeriksaLabActionPerformed(null);
                    }                    
                }else if(i==5){
                    if(var.getrujukan_masuk()==true){
                        MnRujukMasukActionPerformed(null);
                    }                    
                }else if(i==6){
                    MnCetakRegisterActionPerformed(null);
                }
            }
            
        }
        
}//GEN-LAST:event_tbPetugasMouseClicked

    private void tbPetugasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbPetugasKeyPressed
        if(tabMode.getRowCount()!=0){
            if(evt.getKeyCode()==KeyEvent.VK_SPACE){
                try {
                    getData();
                } catch (java.lang.NullPointerException e) {
                }
                i=tbPetugas.getSelectedColumn();
                if(i==1){
                    if(MnKamarInap.isEnabled()==true){
                        MnKamarInapActionPerformed(null);
                    }                    
                }else if(i==2){
                    if(var.gettindakan_ralan()==true){
                        MnRawatJalanActionPerformed(null);
                    }                    
                }else if(i==3){
                    if(var.getberi_obat()==true){
                        MnPemberianObatActionPerformed(null);
                    }                   
                }else if(i==4){
                    if(var.getperiksa_lab()==true){
                        MnPeriksaLabActionPerformed(null);
                    }                    
                }else if(i==5){
                    if(var.getrujukan_masuk()==true){
                        MnRujukMasukActionPerformed(null);
                    }                    
                }else if(i==6){
                    MnCetakRegisterActionPerformed(null);
                }else if(i==7){
                    MnBuktiPelayananRalanActionPerformed(null);
                }
            }else if(evt.getKeyCode()==KeyEvent.VK_A){                
                for(i=0;i<tbPetugas.getRowCount();i++){ 
                    tbPetugas.setValueAt(true,i,0);
                }
            }else if(evt.getKeyCode()==KeyEvent.VK_L){
                MnBarcodeRM9ActionPerformed(null);
            }else if(evt.getKeyCode()==KeyEvent.VK_Z){
                MnSPBK1ActionPerformed(null);
            }else if(evt.getKeyCode()==KeyEvent.VK_W){
                MnLembarRalanActionPerformed(null);
            }
        }
}//GEN-LAST:event_tbPetugasKeyPressed

private void kddokterKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kddokterKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_PAGE_DOWN){
            isNumber();
            Sequel.cariIsi("select nm_dokter from dokter where kd_dokter=?",TDokter,kddokter.getText());
        }else if(evt.getKeyCode()==KeyEvent.VK_UP){
            BtnDokterActionPerformed(null);
        }else{
            Valid.pindah(evt,CmbDetik,kdpoli);
        }
}//GEN-LAST:event_kddokterKeyPressed

private void BtnDokterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDokterActionPerformed
        pilihan=1;
        var.setform("DlgReg");
        if(aktifjadwal.equals("aktif")){
            if(var.getkode().equals("Admin Utama")){
                dokter.isCek();        
                dokter.TCari.requestFocus();
                dokter.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
                dokter.setLocationRelativeTo(internalFrame1);
                dokter.setVisible(true);
            }else{
                dokter2.setPoli(TPoli.getText());
                dokter2.isCek();                 
                dokter2.SetHari(DTPReg.getDate());
                dokter2.tampil();
                dokter2.TCari.requestFocus();
                dokter2.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
                dokter2.setLocationRelativeTo(internalFrame1);
                dokter2.setVisible(true);
            }                
        }else{
            dokter.isCek();        
            dokter.TCari.requestFocus();
            dokter.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
            dokter.setLocationRelativeTo(internalFrame1);
            dokter.setVisible(true);
        }
}//GEN-LAST:event_BtnDokterActionPerformed

private void kdpoliKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kdpoliKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_PAGE_DOWN){
            Sequel.cariIsi("select nm_poli from poliklinik where kd_poli=?",TPoli,kdpoli.getText());
        }else if(evt.getKeyCode()==KeyEvent.VK_UP){
            BtnUnitActionPerformed(null);
        }else{
            Valid.pindah(evt,kddokter,TNoRM);
        }
}//GEN-LAST:event_kdpoliKeyPressed

private void BtnUnitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnUnitActionPerformed
        var.setform("DlgReg");
        pilihan=1;
        
        if(aktifjadwal.equals("aktif")){
            if(var.getkode().equals("Admin Utama")){
                poli.isCek();        
                poli.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
                poli.setLocationRelativeTo(internalFrame1);
                poli.setVisible(true);
            }else{
                poli2.isCek();  
                poli2.SetHari(DTPReg.getDate());
                poli2.tampil(); 
                poli2.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
                poli2.setLocationRelativeTo(internalFrame1);
                poli2.setVisible(true);
            }                
        }else{
            poli.isCek();        
            poli.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
            poli.setLocationRelativeTo(internalFrame1);
            poli.setVisible(true);
        }
}//GEN-LAST:event_BtnUnitActionPerformed

private void BtnSeek3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSeek3ActionPerformed
        var.setform("DlgReg");
        pilihan=2;
        dokter.isCek();
        dokter.TCari.requestFocus();
        dokter.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
        dokter.setLocationRelativeTo(internalFrame1);
        dokter.setVisible(true);
}//GEN-LAST:event_BtnSeek3ActionPerformed

private void BtnSeek4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSeek4ActionPerformed
        var.setform("DlgReg");
        pilihan=2;
        poli.isCek();
        poli.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
        poli.setLocationRelativeTo(internalFrame1);
        poli.setVisible(true);
}//GEN-LAST:event_BtnSeek4ActionPerformed

private void MnKamarInapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnKamarInapActionPerformed
        if(tabMode.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Maaf, table masih kosong...!!!!");
            TNoReg.requestFocus();
        }else if(TPasien.getText().trim().equals("")){
            JOptionPane.showMessageDialog(null,"Maaf, Silahkan anda pilih dulu dengan menklik data pada table...!!!");
            tbPetugas.requestFocus();
        }else{
            if(Sequel.cariRegistrasi(TNoRw.getText())>0){
                JOptionPane.showMessageDialog(rootPane,"Data billing sudah terverifikasi..!!");
            }else{
                this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                var.setstatus(true);
                DlgKamarInap dlgki=new DlgKamarInap(null,false);
                dlgki.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
                dlgki.setLocationRelativeTo(internalFrame1);
                dlgki.emptTeks();
                dlgki.isCek();
                dlgki.setNoRm(TNoRw.getText());   
                dlgki.setVisible(true);
                this.setCursor(Cursor.getDefaultCursor());
            } 
                
        }        
}//GEN-LAST:event_MnKamarInapActionPerformed

private void MnRawatJalanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnRawatJalanActionPerformed
       if(tabMode.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Maaf, table masih kosong...!!!!");
            TNoReg.requestFocus();
        }else if(TPasien.getText().trim().equals("")){
            JOptionPane.showMessageDialog(null,"Maaf, Silahkan anda pilih dulu dengan menklik data pada table...!!!");
            tbPetugas.requestFocus();
        }else{
            if(tbPetugas.getSelectedRow()!= -1){
                if(Sequel.cariInteger("select count(no_rawat) from kamar_inap where no_rawat=?",TNoRw.getText())>0){
                            JOptionPane.showMessageDialog(null,"Maaf, Pasien sudah masuk Kamar Inap. Gunakan billing Ranap..!!!");
                }else {
                    this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                    DlgRawatJalan dlgrwjl=new DlgRawatJalan(null,false);
                    dlgrwjl.isCek();
                    dlgrwjl.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
                    dlgrwjl.setLocationRelativeTo(internalFrame1);
                    dlgrwjl.SetPoli(tbPetugas.getValueAt(tbPetugas.getSelectedRow(),21).toString());
                    dlgrwjl.SetPj(tbPetugas.getValueAt(tbPetugas.getSelectedRow(),22).toString());
                    dlgrwjl.setNoRm(TNoRw.getText(),DTPCari1.getDate(),DTPCari2.getDate());    
                    dlgrwjl.setVisible(true);
                    this.setCursor(Cursor.getDefaultCursor());
                }  
            }                              
        }
}//GEN-LAST:event_MnRawatJalanActionPerformed

private void MnRujukActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnRujukActionPerformed
        if(tabMode.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Maaf, table masih kosong...!!!!");
            TNoReg.requestFocus();
        }else if(TPasien.getText().trim().equals("")){
            JOptionPane.showMessageDialog(null,"Maaf, Silahkan anda pilih dulu dengan menklik data pada table...!!!");
            tbPetugas.requestFocus();
        }else{            
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            DlgRujuk form=new DlgRujuk(null,false);
            form.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
            form.setLocationRelativeTo(internalFrame1);
            form.emptTeks();
            form.isCek();
            form.setNoRm(TNoRw.getText(),DTPCari1.getDate(),DTPCari2.getDate()); 
            form.tampil();
            form.setVisible(true);
            this.setCursor(Cursor.getDefaultCursor());
        }
}//GEN-LAST:event_MnRujukActionPerformed

private void MnPemberianObatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnPemberianObatActionPerformed
        if(tabMode.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Maaf, table masih kosong...!!!!");
            TNoReg.requestFocus();
        }else if(TPasien.getText().trim().equals("")){
            JOptionPane.showMessageDialog(null,"Maaf, Silahkan anda pilih dulu dengan menklik data pada table...!!!");
            tbPetugas.requestFocus();
        }else{
            if(Sequel.cariInteger("select count(no_rawat) from kamar_inap where no_rawat=?",TNoRw.getText())>0){
                        JOptionPane.showMessageDialog(null,"Maaf, Pasien sudah masuk Kamar Inap. Gunakan billing Ranap..!!!");
            }else {
                this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                DlgPemberianObat dlgrwinap=new DlgPemberianObat(null,false);
                dlgrwinap.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
                dlgrwinap.setLocationRelativeTo(internalFrame1);
                dlgrwinap.isCek();
                dlgrwinap.setNoRm(TNoRw.getText(),DTPCari1.getDate(),DTPCari2.getDate(),"ralan");
                dlgrwinap.tampilPO();
                dlgrwinap.setVisible(true);
                this.setCursor(Cursor.getDefaultCursor());
            }                
        }
}//GEN-LAST:event_MnPemberianObatActionPerformed

private void MnBillingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnBillingActionPerformed
        if(tabMode.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Maaf, table masih kosong...!!!!");
            TNoReg.requestFocus();
        }else if(TPasien.getText().trim().equals("")){
            JOptionPane.showMessageDialog(null,"Maaf, Silahkan anda pilih dulu dengan menklik data pada table...!!!");
            tbPetugas.requestFocus();
        }else{
            if(Sequel.cariInteger("select count(no_rawat) from kamar_inap where no_rawat=?",TNoRw.getText())>0){
                JOptionPane.showMessageDialog(null,"Maaf, Pasien sudah masuk Kamar Inap. Gunakan billing Ranap..!!!");
            }else {                
                try {
                    pscaripiutang=koneksi.prepareStatement("select tgl_piutang from piutang_pasien where no_rkm_medis=? and status='Belum Lunas' order by tgl_piutang asc limit 1");
                    try {
                        pscaripiutang.setString(1,TNoRM.getText());
                        rs=pscaripiutang.executeQuery();
                        if(rs.next()){
                            i=JOptionPane.showConfirmDialog(null, "Masih ada tunggakan pembayaran, apa mau bayar sekarang ?","Konfirmasi",JOptionPane.YES_NO_OPTION);
                            if(i==JOptionPane.YES_OPTION){
                                this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                                 DlgLhtPiutang piutang=new DlgLhtPiutang(null,false);
                                 piutang.setNoRm(TNoRM.getText(),rs.getDate(1));
                                 piutang.tampil();
                                 piutang.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
                                 piutang.setLocationRelativeTo(internalFrame1);
                                 piutang.setVisible(true);
                                 this.setCursor(Cursor.getDefaultCursor());
                            }else{
                                this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                                DlgBilingRalan dlgbil=new DlgBilingRalan(null,false);
                                dlgbil.TNoRw.setText(TNoRw.getText());
                                dlgbil.isCek();
                                dlgbil.isRawat(); 
                                dlgbil.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
                                dlgbil.setLocationRelativeTo(internalFrame1);
                                dlgbil.setVisible(true);
                                this.setCursor(Cursor.getDefaultCursor());
                            }
                        }else{ 
                            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                            DlgBilingRalan dlgbil=new DlgBilingRalan(null,false);
                            dlgbil.TNoRw.setText(TNoRw.getText()); 
                            dlgbil.isCek();
                            dlgbil.isRawat(); 
                            dlgbil.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
                            dlgbil.setLocationRelativeTo(internalFrame1);
                            dlgbil.setVisible(true);
                            this.setCursor(Cursor.getDefaultCursor());
                        }
                    } catch (Exception e) {
                        System.out.println(e);
                    }finally{
                        if( rs != null){
                            rs.close();
                        }

                        if( pscaripiutang != null){
                            pscaripiutang.close();
                        }
                    }
                } catch (Exception e) {
                    System.out.println(e);
                }                  
            }              
        }
}//GEN-LAST:event_MnBillingActionPerformed

private void ChkInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkInputActionPerformed
  isForm();                
}//GEN-LAST:event_ChkInputActionPerformed

private void ppGrafikPerpoliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppGrafikPerpoliActionPerformed
       tampil();
       grafikperiksaperpoli kas=new grafikperiksaperpoli("Grafik Periksa Per Unit/Poli Tanggal "+Valid.SetTgl(DTPCari1.getSelectedItem()+"")+" S.D. "+Valid.SetTgl(DTPCari2.getSelectedItem()+""),"where tgl_registrasi between '"+Valid.SetTgl(DTPCari1.getSelectedItem()+"")+"' and '"+Valid.SetTgl(DTPCari2.getSelectedItem()+"")+"' ");
       kas.setSize(this.getWidth(), this.getHeight());        
       kas.setLocationRelativeTo(this);
       kas.setVisible(true);
}//GEN-LAST:event_ppGrafikPerpoliActionPerformed

private void ppGrafikPerdokterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppGrafikPerdokterActionPerformed
      tampil();
       grafikperiksaperdokter kas=new grafikperiksaperdokter("Grafik Periksa Per Dokter Tanggal "+Valid.SetTgl(DTPCari1.getSelectedItem()+"")+" S.D. "+Valid.SetTgl(DTPCari2.getSelectedItem()+""),"where tgl_registrasi between '"+Valid.SetTgl(DTPCari1.getSelectedItem()+"")+"' and '"+Valid.SetTgl(DTPCari2.getSelectedItem()+"")+"' ");
       kas.setSize(this.getWidth(), this.getHeight());        
       kas.setLocationRelativeTo(this);
       kas.setVisible(true);
}//GEN-LAST:event_ppGrafikPerdokterActionPerformed

private void ppGrafikPerJKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppGrafikPerJKActionPerformed
      tampil();
       grafikperiksaperjk kas=new grafikperiksaperjk("Grafik Periksa Per Jenis Kelamin "+Valid.SetTgl(DTPCari1.getSelectedItem()+"")+" S.D. "+Valid.SetTgl(DTPCari2.getSelectedItem()+""),"where tgl_registrasi between '"+Valid.SetTgl(DTPCari1.getSelectedItem()+"")+"' and '"+Valid.SetTgl(DTPCari2.getSelectedItem()+"")+"' ");
       kas.setSize(this.getWidth(), this.getHeight());        
       kas.setLocationRelativeTo(this);
       kas.setVisible(true);
}//GEN-LAST:event_ppGrafikPerJKActionPerformed

private void ppGrafikPerPekerjaanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppGrafikPerPekerjaanActionPerformed
       tampil();
       grafikperiksaperpekerjaan kas=new grafikperiksaperpekerjaan("Grafik Periksa Per Pekerjaan "+Valid.SetTgl(DTPCari1.getSelectedItem()+"")+" S.D. "+Valid.SetTgl(DTPCari2.getSelectedItem()+""),"where tgl_registrasi between '"+Valid.SetTgl(DTPCari1.getSelectedItem()+"")+"' and '"+Valid.SetTgl(DTPCari2.getSelectedItem()+"")+"' ");
       kas.setSize(this.getWidth(), this.getHeight());        
       kas.setLocationRelativeTo(this);
       kas.setVisible(true);
}//GEN-LAST:event_ppGrafikPerPekerjaanActionPerformed

private void ppGrafikPerAgamaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppGrafikPerAgamaActionPerformed
     tampil();
       grafikperiksaperagama kas=new grafikperiksaperagama("Grafik Periksa Per Agama "+Valid.SetTgl(DTPCari1.getSelectedItem()+"")+" S.D. "+Valid.SetTgl(DTPCari2.getSelectedItem()+""),"where tgl_registrasi between '"+Valid.SetTgl(DTPCari1.getSelectedItem()+"")+"' and '"+Valid.SetTgl(DTPCari2.getSelectedItem()+"")+"' ");
       kas.setSize(this.getWidth(), this.getHeight());        
       kas.setLocationRelativeTo(this);
       kas.setVisible(true);
}//GEN-LAST:event_ppGrafikPerAgamaActionPerformed

private void ppGrafikPerTahunActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppGrafikPerTahunActionPerformed
       tampil();
       grafikperiksapertahun kas=new grafikperiksapertahun("Grafik Periksa Per Tahun "+Valid.SetTgl(DTPCari1.getSelectedItem()+"")+" S.D. "+Valid.SetTgl(DTPCari2.getSelectedItem()+""),"where tgl_registrasi between '"+Valid.SetTgl(DTPCari1.getSelectedItem()+"")+"' and '"+Valid.SetTgl(DTPCari2.getSelectedItem()+"")+"' ");
       kas.setSize(this.getWidth(), this.getHeight());        
       kas.setLocationRelativeTo(this);
       kas.setVisible(true);
}//GEN-LAST:event_ppGrafikPerTahunActionPerformed

private void ppGrafikPerBulanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppGrafikPerBulanActionPerformed
       tampil();
       grafikperiksaperbulan kas=new grafikperiksaperbulan("Grafik Periksa Per Bulan "+Valid.SetTgl(DTPCari1.getSelectedItem()+"")+" S.D. "+Valid.SetTgl(DTPCari2.getSelectedItem()+""),"where tgl_registrasi between '"+Valid.SetTgl(DTPCari1.getSelectedItem()+"")+"' and '"+Valid.SetTgl(DTPCari2.getSelectedItem()+"")+"' ");
       kas.setSize(this.getWidth(), this.getHeight());        
       kas.setLocationRelativeTo(this);
       kas.setVisible(true);
}//GEN-LAST:event_ppGrafikPerBulanActionPerformed

private void ppGrafikPerTanggalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppGrafikPerTanggalActionPerformed
      tampil();
       grafikperiksaperhari kas=new grafikperiksaperhari("Grafik Periksa Per Hari "+Valid.SetTgl(DTPCari1.getSelectedItem()+"")+" S.D. "+Valid.SetTgl(DTPCari2.getSelectedItem()+""),"where tgl_registrasi between '"+Valid.SetTgl(DTPCari1.getSelectedItem()+"")+"' and '"+Valid.SetTgl(DTPCari2.getSelectedItem()+"")+"' ");
       kas.setSize(this.getWidth(), this.getHeight());        
       kas.setLocationRelativeTo(this);
       kas.setVisible(true);
}//GEN-LAST:event_ppGrafikPerTanggalActionPerformed

private void MnPeriksaLabActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnPeriksaLabActionPerformed
       if(tabMode.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Maaf, table masih kosong...!!!!");
            TNoReg.requestFocus();
        }else if(TPasien.getText().trim().equals("")){
            JOptionPane.showMessageDialog(null,"Maaf, Silahkan anda pilih dulu dengan menklik data pada table...!!!");
            tbPetugas.requestFocus();
        }else{
            if(Sequel.cariInteger("select count(no_rawat) from kamar_inap where no_rawat=?",TNoRw.getText())>0){
                        JOptionPane.showMessageDialog(null,"Maaf, Pasien sudah masuk Kamar Inap. Gunakan billing Ranap..!!!");
            }else {
                this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                DlgPeriksaLaboratorium dlgro=new DlgPeriksaLaboratorium(null,false);
                dlgro.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
                dlgro.setLocationRelativeTo(internalFrame1);
                dlgro.emptTeks();
                dlgro.isCek();
                dlgro.setNoRm(TNoRw.getText(),"Ralan"); 
                dlgro.setVisible(true);
                this.setCursor(Cursor.getDefaultCursor());
            }                
        }
}//GEN-LAST:event_MnPeriksaLabActionPerformed

private void MnCetakSuratSakitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnCetakSuratSakitActionPerformed
     pilihan=1;
     DlgSakit.setSize(550,121);
     DlgSakit.setLocationRelativeTo(internalFrame1);
     DlgSakit.setVisible(true);
}//GEN-LAST:event_MnCetakSuratSakitActionPerformed

private void BtnPrint2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrint2ActionPerformed
      if(TPasien.getText().trim().equals("")){
          JOptionPane.showMessageDialog(null,"Maaf, Silahkan anda pilih dulu pasien...!!!");                
      }else{
          if(pilihan==1){
                this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                Map<String, Object> param = new HashMap<>();
                  param.put("hari",lmsakit.getText());
                  param.put("TanggalAwal",TglSakit1.getSelectedItem().toString());
                  param.put("TanggalAkhir",TglSakit2.getSelectedItem().toString());
                  param.put("namars",var.getnamars());
                      param.put("alamatrs",var.getalamatrs());
                      param.put("kotars",var.getkabupatenrs());
                      param.put("propinsirs",var.getpropinsirs());
                      param.put("kontakrs",var.getkontakrs());
                      param.put("emailrs",var.getemailrs());   
                      param.put("logo",Sequel.cariGambar("select logo from setting")); 
                Valid.MyReport("rptSuratSakit.jrxml","report","::[ Surat Sakit ]::",
                              "select reg_periksa.no_rawat,dokter.nm_dokter,pasien.keluarga,pasien.namakeluarga,pasien.tgl_lahir,pasien.jk," +
                              " pasien.nm_pasien,pasien.jk,concat(reg_periksa.umurdaftar,' ',reg_periksa.sttsumur)as umur,pasien.pekerjaan,concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab) as alamat" +
                              " from reg_periksa inner join pasien inner join dokter inner join kelurahan inner join kecamatan inner join kabupaten" +
                              " on reg_periksa.no_rkm_medis=pasien.no_rkm_medis and reg_periksa.kd_dokter=dokter.kd_dokter and pasien.kd_kel=kelurahan.kd_kel "+
                         "and pasien.kd_kec=kecamatan.kd_kec and pasien.kd_kab=kabupaten.kd_kab "+
                              "where reg_periksa.no_rawat='"+TNoRw.getText()+"' ",param);
                this.setCursor(Cursor.getDefaultCursor());  
          }else if(pilihan==2){
                this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                Map<String, Object> param = new HashMap<>();
                  param.put("hari",lmsakit.getText());
                  param.put("TanggalAwal",TglSakit1.getSelectedItem().toString());
                  param.put("TanggalAkhir",TglSakit2.getSelectedItem().toString());
                  param.put("namars",var.getnamars());
                      param.put("alamatrs",var.getalamatrs());
                      param.put("kotars",var.getkabupatenrs());
                      param.put("propinsirs",var.getpropinsirs());
                      param.put("kontakrs",var.getkontakrs());
                      param.put("emailrs",var.getemailrs());   
                      param.put("logo",Sequel.cariGambar("select logo from setting")); 
                Valid.MyReport("rptSuratSakit3.jrxml","report","::[ Surat Sakit ]::",
                              "select reg_periksa.no_rawat,dokter.nm_dokter,pasien.keluarga,pasien.namakeluarga,pasien.tgl_lahir,pasien.jk," +
                              " pasien.nm_pasien,pasien.jk,concat(reg_periksa.umurdaftar,' ',reg_periksa.sttsumur)as umur,pasien.pekerjaan,concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab) as alamat" +
                              " from reg_periksa inner join pasien inner join dokter inner join kelurahan inner join kecamatan inner join kabupaten" +
                              " on reg_periksa.no_rkm_medis=pasien.no_rkm_medis and reg_periksa.kd_dokter=dokter.kd_dokter and pasien.kd_kel=kelurahan.kd_kel "+
                         "and pasien.kd_kec=kecamatan.kd_kec and pasien.kd_kab=kabupaten.kd_kab "+
                              "where reg_periksa.no_rawat='"+TNoRw.getText()+"' ",param);
                this.setCursor(Cursor.getDefaultCursor());  
              
          }else if(pilihan==3){
                this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                Map<String, Object> param = new HashMap<>();
                param.put("hari",lmsakit.getText());
                param.put("TanggalAwal",TglSakit1.getSelectedItem().toString());
                param.put("TanggalAkhir",TglSakit2.getSelectedItem().toString());
                param.put("namars",var.getnamars());
                param.put("alamatrs",var.getalamatrs());
                param.put("kotars",var.getkabupatenrs());
                param.put("propinsirs",var.getpropinsirs());
                param.put("kontakrs",var.getkontakrs());
                param.put("emailrs",var.getemailrs());  
                param.put("penyakit",Sequel.cariIsi("select concat(diagnosa_pasien.kd_penyakit,' ',penyakit.nm_penyakit) from diagnosa_pasien inner join reg_periksa inner join penyakit "+
                    "on diagnosa_pasien.no_rawat=reg_periksa.no_rawat and diagnosa_pasien.kd_penyakit=penyakit.kd_penyakit "+
                    "where diagnosa_pasien.no_rawat=? and diagnosa_pasien.prioritas='1'",TNoRw.getText()));
                param.put("logo",Sequel.cariGambar("select logo from setting")); 
                Valid.MyReport("rptSuratSakit4.jrxml","report","::[ Surat Sakit ]::",
                              "select reg_periksa.no_rawat,dokter.nm_dokter,pasien.keluarga,pasien.namakeluarga,pasien.tgl_lahir,pasien.jk," +
                              " pasien.nm_pasien,pasien.jk,concat(reg_periksa.umurdaftar,' ',reg_periksa.sttsumur)as umur,pasien.pekerjaan,concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab) as alamat" +
                              " from reg_periksa inner join pasien inner join dokter inner join kelurahan inner join kecamatan inner join kabupaten" +
                              " on reg_periksa.no_rkm_medis=pasien.no_rkm_medis and reg_periksa.kd_dokter=dokter.kd_dokter and pasien.kd_kel=kelurahan.kd_kel "+
                         "and pasien.kd_kec=kecamatan.kd_kec and pasien.kd_kab=kabupaten.kd_kab "+
                              "where reg_periksa.no_rawat='"+TNoRw.getText()+"' ",param);
                this.setCursor(Cursor.getDefaultCursor());  
              
          }
            
      }
}//GEN-LAST:event_BtnPrint2ActionPerformed

private void BtnKeluar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluar2ActionPerformed
   DlgSakit.dispose();
}//GEN-LAST:event_BtnKeluar2ActionPerformed

private void BtnPrint3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrint3ActionPerformed
                
            if(!Kelurahan2.getText().equals("")){
                DlgDemografi.dispose();   
                grafiksql kas=new grafiksql("::[ Data Demografi Per Area Kelurahan "+Kelurahan2.getText()+", Kecamatan "+Kecamatan2.getText()+", Kabupaten "+Kabupaten2.getText()+" ]::",
                        " reg_periksa inner join pasien inner join kabupaten inner join kecamatan inner join kelurahan on pasien.kd_kab=kabupaten.kd_kab and pasien.kd_kec=kecamatan.kd_kec and pasien.kd_kel=kelurahan.kd_kel "+
                        " and reg_periksa.no_rkm_medis=pasien.no_rkm_medis where kabupaten.nm_kab='"+Kabupaten2.getText()+"' and kecamatan.nm_kec='"+Kecamatan2.getText()+"' and kelurahan.nm_kel='"+Kelurahan2.getText()+"'  and reg_periksa.tgl_registrasi between '"+Valid.SetTgl(DTPCari1.getSelectedItem()+"")+"' and '"+Valid.SetTgl(DTPCari2.getSelectedItem()+"")+"'   ", 
                        "pasien.alamat","Area");
                kas.setSize(this.getWidth(), this.getHeight());        
                kas.setLocationRelativeTo(this);
                kas.setVisible(true);
            }else if(!Kecamatan2.getText().equals("")){
                DlgDemografi.dispose();   
                grafiksql kas=new grafiksql("::[ Data Demografi Per Kelurahan Kecamatan "+Kecamatan2.getText()+" Kabupaten "+Kabupaten2.getText()+" ]::",
                        " reg_periksa inner join pasien inner join kabupaten inner join kecamatan inner join kelurahan on pasien.kd_kab=kabupaten.kd_kab "+
                         "and pasien.kd_kec=kecamatan.kd_kec and pasien.kd_kel=kelurahan.kd_kel "+
                         "and reg_periksa.no_rkm_medis=pasien.no_rkm_medis where kabupaten.nm_kab='"+Kabupaten2.getText()+"' and kecamatan.nm_kec='"+Kecamatan2.getText()+"' and reg_periksa.tgl_registrasi between '"+Valid.SetTgl(DTPCari1.getSelectedItem()+"")+"' and '"+Valid.SetTgl(DTPCari2.getSelectedItem()+"")+"'  ", 
                         "kelurahan.nm_kel","Kelurahan");
                kas.setSize(this.getWidth(), this.getHeight());        
                kas.setLocationRelativeTo(this);
                kas.setVisible(true);
            }else if(!Kabupaten2.getText().equals("")){
                DlgDemografi.dispose();   
                grafiksql kas=new grafiksql("::[ Data Per Kecamatan Kabupaten "+Kabupaten2.getText()+" ]::",
                         " reg_periksa inner join pasien inner join kabupaten inner join kecamatan on pasien.kd_kab=kabupaten.kd_kab "+
                         "and pasien.kd_kec=kecamatan.kd_kec and reg_periksa.no_rkm_medis=pasien.no_rkm_medis where kabupaten.nm_kab='"+Kabupaten2.getText()+"'  and reg_periksa.tgl_registrasi between '"+Valid.SetTgl(DTPCari1.getSelectedItem()+"")+"' and '"+Valid.SetTgl(DTPCari2.getSelectedItem()+"")+"'  ", 
                         "kecamatan.nm_kec","Kecamatan");
                kas.setSize(this.getWidth(), this.getHeight());        
                kas.setLocationRelativeTo(this);
                kas.setVisible(true);
            }else if(Kabupaten2.getText().equals("")){
                DlgDemografi.dispose();   
                grafiksql kas=new grafiksql("::[ Data Demografi Per Kabupaten ]::",
                         " reg_periksa inner join pasien inner join kabupaten on pasien.kd_kab=kabupaten.kd_kab and reg_periksa.no_rkm_medis=pasien.no_rkm_medis where reg_periksa.tgl_registrasi between '"+Valid.SetTgl(DTPCari1.getSelectedItem()+"")+"' and '"+Valid.SetTgl(DTPCari2.getSelectedItem()+"")+"' ", 
                          "kabupaten.nm_kab","Kabupaten");
                kas.setSize(this.getWidth(), this.getHeight());        
                kas.setLocationRelativeTo(this);
                kas.setVisible(true);
            } 
}//GEN-LAST:event_BtnPrint3ActionPerformed

private void BtnKeluar3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluar3ActionPerformed
   DlgDemografi.dispose();
}//GEN-LAST:event_BtnKeluar3ActionPerformed

private void btnKelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKelActionPerformed
        var.setform("DlgReg");
        pasien.kel.emptTeks();
        pasien.kel.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
        pasien.kel.setLocationRelativeTo(internalFrame1);
        pasien.kel.setVisible(true);
     
}//GEN-LAST:event_btnKelActionPerformed

private void btnKecActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKecActionPerformed
   var.setform("DlgReg");
   pasien.kec.emptTeks();
        pasien.kec.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
        pasien.kec.setLocationRelativeTo(internalFrame1);
        pasien.kec.setVisible(true);      
}//GEN-LAST:event_btnKecActionPerformed

private void btnKabActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKabActionPerformed
        var.setform("DlgReg");
        pasien.kab.emptTeks();
        pasien.kab.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
        pasien.kab.setLocationRelativeTo(internalFrame1);
        pasien.kab.setVisible(true);   
}//GEN-LAST:event_btnKabActionPerformed

private void BtnPrint4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrint4ActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        if(tabMode.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Maaf, data sudah habis. Tidak ada data yang bisa anda print...!!!!");
            BtnBatal.requestFocus();
        }else if(tabMode.getRowCount()!=0){            
            if(!Kelurahan2.getText().equals("")){
                DlgDemografi.dispose();   
                Map<String, Object> data = new HashMap<>();
                data.put("judul","Data Demografi Per Area Kelurahan "+Kelurahan2.getText()+", Kecamatan "+Kecamatan2.getText()+", Kabupaten "+Kabupaten2.getText());
                data.put("area","Area");
                data.put("namars",var.getnamars());
                data.put("alamatrs",var.getalamatrs());
                data.put("kotars",var.getkabupatenrs());
                data.put("propinsirs",var.getpropinsirs());
                data.put("kontakrs",var.getkontakrs());
                data.put("emailrs",var.getemailrs());
                data.put("logo",Sequel.cariGambar("select logo from setting")); 
                Valid.MyReport("rptDemografi.jrxml","report","::[ Data Demografi Per Area Kelurahan "+Kelurahan2.getText()+", Kecamatan "+Kecamatan2.getText()+", Kabupaten "+Kabupaten2.getText()+" ]::",
                   "select  pasien.alamat as area,count(pasien.alamat) as jumlah from reg_periksa inner join pasien "+
                   "inner join kabupaten inner join kecamatan inner join kelurahan on pasien.kd_kab=kabupaten.kd_kab "+
                   "and pasien.kd_kec=kecamatan.kd_kec and pasien.kd_kel=kelurahan.kd_kel and reg_periksa.no_rkm_medis=pasien.no_rkm_medis "+
                   "where kabupaten.nm_kab='"+Kabupaten2.getText()+"' and kecamatan.nm_kec='"+Kecamatan2.getText()+"' "+
                   "and kelurahan.nm_kel='"+Kelurahan2.getText()+"' and reg_periksa.tgl_registrasi between '"+Valid.SetTgl(DTPCari1.getSelectedItem()+"")+"' and '"+Valid.SetTgl(DTPCari2.getSelectedItem()+"")+"' group by pasien.alamat order by pasien.alamat",data);
            }else if(!Kecamatan2.getText().equals("")){
                DlgDemografi.dispose();   
                Map<String, Object> data = new HashMap<>();
                data.put("judul","Data Demografi Per Kelurahan Kecamatan "+Kecamatan2.getText()+", Kabupaten "+Kabupaten2.getText());
                data.put("area","Kelurahan");
                data.put("namars",var.getnamars());
                data.put("alamatrs",var.getalamatrs());
                data.put("kotars",var.getkabupatenrs());
                data.put("propinsirs",var.getpropinsirs());
                data.put("kontakrs",var.getkontakrs());
                data.put("emailrs",var.getemailrs());
                data.put("logo",Sequel.cariGambar("select logo from setting")); 
                Valid.MyReport("rptDemografi.jrxml","report","::[ Data Demografi Per Kelurahan Kecamatan "+Kecamatan2.getText()+" Kabupaten "+Kabupaten2.getText()+" ]::",
                   "select kelurahan.nm_kel as area,count(kelurahan.nm_kel) as jumlah from reg_periksa inner join pasien "+
                   "inner join kabupaten inner join kecamatan inner join kelurahan on pasien.kd_kab=kabupaten.kd_kab "+
                   "and pasien.kd_kec=kecamatan.kd_kec and pasien.kd_kel=kelurahan.kd_kel and reg_periksa.no_rkm_medis=pasien.no_rkm_medis "+
                   "where kabupaten.nm_kab='"+Kabupaten2.getText()+"' and kecamatan.nm_kec='"+Kecamatan2.getText()+"' and reg_periksa.tgl_registrasi between '"+Valid.SetTgl(DTPCari1.getSelectedItem()+"")+"' and '"+Valid.SetTgl(DTPCari2.getSelectedItem()+"")+"' group by kelurahan.nm_kel order by kelurahan.nm_kel",data);
            }else if(!Kabupaten2.getText().equals("")){
                DlgDemografi.dispose();   
                Map<String, Object> data = new HashMap<>();
                data.put("judul","Data Demografi Per Kecamatan Kabupaten "+Kabupaten2.getText());
                data.put("area","Kecamatan");
                data.put("namars",var.getnamars());
                data.put("alamatrs",var.getalamatrs());
                data.put("kotars",var.getkabupatenrs());
                data.put("propinsirs",var.getpropinsirs());
                data.put("kontakrs",var.getkontakrs());
                data.put("emailrs",var.getemailrs());
                data.put("logo",Sequel.cariGambar("select logo from setting")); 
                Valid.MyReport("rptDemografi.jrxml","report","::[ Data Per Kecamatan Kabupaten "+Kabupaten2.getText()+" ]::",
                   "select kecamatan.nm_kec as area,count(kecamatan.nm_kec) as jumlah from reg_periksa inner join pasien "+
                   "inner join kabupaten inner join kecamatan on pasien.kd_kab=kabupaten.kd_kab and reg_periksa.no_rkm_medis=pasien.no_rkm_medis "+
                   "and pasien.kd_kec=kecamatan.kd_kec where kabupaten.nm_kab='"+Kabupaten2.getText()+"' and reg_periksa.tgl_registrasi between '"+Valid.SetTgl(DTPCari1.getSelectedItem()+"")+"' and '"+Valid.SetTgl(DTPCari2.getSelectedItem()+"")+"' group by kecamatan.nm_kec order by kecamatan.nm_kec",data);
            }else if(Kabupaten2.getText().equals("")){
                DlgDemografi.dispose();   
                Map<String, Object> data = new HashMap<>();
                data.put("judul","Data Demografi Per Kabupaten");
                data.put("area","Kabupaten");
                data.put("namars",var.getnamars());
                data.put("alamatrs",var.getalamatrs());
                data.put("kotars",var.getkabupatenrs());
                data.put("propinsirs",var.getpropinsirs());
                data.put("kontakrs",var.getkontakrs());
                data.put("emailrs",var.getemailrs());
                data.put("logo",Sequel.cariGambar("select logo from setting")); 
                Valid.MyReport("rptDemografi.jrxml","report","::[ Data Demografi Per Kabupaten ]::","select kabupaten.nm_kab as area,count(kabupaten.nm_kab) as jumlah from reg_periksa inner join pasien "+
                   "inner join kabupaten on pasien.kd_kab=kabupaten.kd_kab and reg_periksa.no_rkm_medis=pasien.no_rkm_medis where reg_periksa.tgl_registrasi between '"+Valid.SetTgl(DTPCari1.getSelectedItem()+"")+"' and '"+Valid.SetTgl(DTPCari2.getSelectedItem()+"")+"' group by kabupaten.nm_kab order by kabupaten.nm_kab",data);
            }                        
        }
        this.setCursor(Cursor.getDefaultCursor());
}//GEN-LAST:event_BtnPrint4ActionPerformed

private void ppGrafikDemografiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppGrafikDemografiActionPerformed
        DlgDemografi.setSize(550,180);
        DlgDemografi.setLocationRelativeTo(internalFrame1);
        DlgDemografi.setVisible(true);
}//GEN-LAST:event_ppGrafikDemografiActionPerformed

private void MnRujukMasukActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnRujukMasukActionPerformed
   if(TPasien.getText().trim().equals("")){
                JOptionPane.showMessageDialog(null,"Maaf, Silahkan anda pilih dulu pasien...!!!");
                tbPetugas.requestFocus();
      }else{    
                rujukmasuk.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
                rujukmasuk.setLocationRelativeTo(internalFrame1);
                rujukmasuk.emptTeks();
                rujukmasuk.isCek();
                rujukmasuk.setNoRm(TNoRw.getText(),DTPCari1.getDate(),DTPCari2.getDate()); 
                rujukmasuk.tampil();
                rujukmasuk.setVisible(true);
                //this.dispose();
        }
}//GEN-LAST:event_MnRujukMasukActionPerformed

private void kdpnjKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kdpnjKeyPressed
       if(evt.getKeyCode()==KeyEvent.VK_PAGE_DOWN){
            Sequel.cariIsi("select png_jawab from penjab where kd_pj=?",nmpnj,kdpnj.getText());      
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_UP){
            TAlmt.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            AsalRujukan.requestFocus();      
        }else if(evt.getKeyCode()==KeyEvent.VK_UP){
            btnPenjabActionPerformed(null);
        }
}//GEN-LAST:event_kdpnjKeyPressed

private void btnPenjabActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPenjabActionPerformed
        var.setform("DlgReg");
        pasien.penjab.onCari();
        pasien.penjab.isCek();
        pasien.penjab.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
        pasien.penjab.setLocationRelativeTo(internalFrame1);
        pasien.penjab.setVisible(true);
}//GEN-LAST:event_btnPenjabActionPerformed

private void MnLaporanRekapKunjunganPoliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnLaporanRekapKunjunganPoliActionPerformed
        if(tabMode.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Maaf, table masih kosong...!!!!");
            TNoReg.requestFocus();
        }else{
            Valid.panggilUrl("billing/LaporanKunjunganPoli.php?tanggal1="+Valid.SetTgl(DTPCari1.getSelectedItem()+"")+"&tanggal2="+Valid.SetTgl(DTPCari2.getSelectedItem()+""));                       
        } 
}//GEN-LAST:event_MnLaporanRekapKunjunganPoliActionPerformed

private void MnLaporanRekapKunjunganDokterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnLaporanRekapKunjunganDokterActionPerformed
       if(tabMode.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Maaf, table masih kosong...!!!!");
            TNoReg.requestFocus();
        }else{
            Valid.panggilUrl("billing/LaporanKunjunganDokter.php?tanggal1="+Valid.SetTgl(DTPCari1.getSelectedItem()+"")+"&tanggal2="+Valid.SetTgl(DTPCari2.getSelectedItem()+""));                       
        } 
}//GEN-LAST:event_MnLaporanRekapKunjunganDokterActionPerformed

private void MnLaporanRekapJenisBayarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnLaporanRekapJenisBayarActionPerformed
     if(tabMode.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Maaf, table masih kosong...!!!!");
            TNoReg.requestFocus();
        }else{
            Valid.panggilUrl("billing/LaporanKunjunganJenisBayar.php?tanggal1="+Valid.SetTgl(DTPCari1.getSelectedItem()+"")+"&tanggal2="+Valid.SetTgl(DTPCari2.getSelectedItem()+""));                       
        }
}//GEN-LAST:event_MnLaporanRekapJenisBayarActionPerformed

private void MnLaporanRekapRawatDaruratActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnLaporanRekapRawatDaruratActionPerformed
        if(tabMode.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Maaf, table masih kosong...!!!!");
            TNoReg.requestFocus();
        }else{
            Valid.panggilUrl("billing/LaporanRawatDarurat.php?tanggal1="+Valid.SetTgl(DTPCari1.getSelectedItem()+"")+"&tanggal2="+Valid.SetTgl(DTPCari2.getSelectedItem()+""));                       
        }
}//GEN-LAST:event_MnLaporanRekapRawatDaruratActionPerformed

private void MnLaporanRekapKunjunganBulananActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnLaporanRekapKunjunganBulananActionPerformed
        if(tabMode.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Maaf, table masih kosong...!!!!");
            TNoReg.requestFocus();
        }else{
            Valid.panggilUrl("billing/LaporanKunjunganBulanan.php?tanggal1="+Valid.SetTgl(DTPCari1.getSelectedItem()+"")+"&tanggal2="+Valid.SetTgl(DTPCari2.getSelectedItem()+""));                       
        }
}//GEN-LAST:event_MnLaporanRekapKunjunganBulananActionPerformed

private void MnLaporanRekapKunjunganBulananPoliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnLaporanRekapKunjunganBulananPoliActionPerformed
        if(tabMode.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Maaf, table masih kosong...!!!!");
            TNoReg.requestFocus();
        }else{
            Valid.panggilUrl("billing/LaporanKunjunganBulananPoli.php?tanggal1="+Valid.SetTgl(DTPCari1.getSelectedItem()+"")+"&tanggal2="+Valid.SetTgl(DTPCari2.getSelectedItem()+""));                       
        }
}//GEN-LAST:event_MnLaporanRekapKunjunganBulananPoliActionPerformed

    private void MnLaporanRekapPenyakitRalanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnLaporanRekapPenyakitRalanActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgFrekuensiPenyakitRalan ktginventaris=new DlgFrekuensiPenyakitRalan(null,false);
        ktginventaris.isCek();
        ktginventaris.setSize(this.getWidth()-20,this.getHeight()-20);
        ktginventaris.setLocationRelativeTo(this);
        ktginventaris.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnLaporanRekapPenyakitRalanActionPerformed

    private void MnNoResepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnNoResepActionPerformed
        if(TPasien.getText().trim().equals("")){
            JOptionPane.showMessageDialog(null,"Maaf, Silahkan anda pilih dulu pasien...!!!");
            tbPetugas.requestFocus();
        }else{
            if(Sequel.cariInteger("select count(no_rawat) from kamar_inap where no_rawat=?",TNoRw.getText())>0){
                        JOptionPane.showMessageDialog(null,"Maaf, Pasien sudah masuk Kamar Inap. Gunakan billing Ranap..!!!");
            }else {
                this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                DlgResepObat resep=new DlgResepObat(null,false);
                resep.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
                resep.setLocationRelativeTo(internalFrame1);
                resep.emptTeks();
                resep.isCek();
                resep.setNoRm(TNoRw.getText(),DTPCari1.getDate(),DTPCari2.getDate(),CmbJam.getSelectedItem().toString(),CmbMenit.getSelectedItem().toString(),CmbDetik.getSelectedItem().toString(),"ralan");
                resep.tampil();
                resep.setVisible(true);
                this.setCursor(Cursor.getDefaultCursor());
            }                
        }
    }//GEN-LAST:event_MnNoResepActionPerformed

    private void MnCheckListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnCheckListActionPerformed
            if(!TPasien.getText().equals("")){
                this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                Map<String, Object> param = new HashMap<>();
                param.put("poli",TPoli.getText());
                param.put("antrian",TNoReg.getText());
                param.put("nama",TPasien.getText());
                param.put("norm",TNoRM.getText());
                param.put("bayar",nmpnj.getText());
                param.put("namars",var.getnamars());
                param.put("alamatrs",var.getalamatrs());
                param.put("kotars",var.getkabupatenrs());
                param.put("propinsirs",var.getpropinsirs());
                param.put("kontakrs",var.getkontakrs());
                param.put("emailrs",var.getemailrs());
                param.put("logo",Sequel.cariGambar("select logo from setting"));
                Valid.MyReport("rptCheckList.jrxml","report","::[ Check List ]::",
                        "select current_date() as sekarang",param); 
                this.setCursor(Cursor.getDefaultCursor());
            }  
    }//GEN-LAST:event_MnCheckListActionPerformed

    private void Kabupaten2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Kabupaten2KeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_UP){
            btnKabActionPerformed(null);
        }else{Valid.pindah(evt,BtnKeluar3,Kecamatan2);}
    }//GEN-LAST:event_Kabupaten2KeyPressed

    private void Kecamatan2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Kecamatan2KeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_UP){
            btnKecActionPerformed(null);
        }else{Valid.pindah(evt,Kabupaten2,Kelurahan2);}
    }//GEN-LAST:event_Kecamatan2KeyPressed

    private void Kelurahan2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Kelurahan2KeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_UP){
            btnKelActionPerformed(null);
        }else{Valid.pindah(evt,Kecamatan2,BtnPrint4);}
    }//GEN-LAST:event_Kelurahan2KeyPressed

    private void BtnPrint4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnPrint4KeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnPrint4ActionPerformed(null);
        }else{Valid.pindah(evt,Kelurahan2,BtnPrint3);}
    }//GEN-LAST:event_BtnPrint4KeyPressed

    private void BtnPrint3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnPrint3KeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnPrint3ActionPerformed(null);
        }else{Valid.pindah(evt,BtnPrint4,BtnKeluar3);}
    }//GEN-LAST:event_BtnPrint3KeyPressed

    private void BtnKeluar3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluar3KeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnKeluar3ActionPerformed(null);
        }else{Valid.pindah(evt,BtnPrint3,Kabupaten2);}
    }//GEN-LAST:event_BtnKeluar3KeyPressed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        tampil();
    }//GEN-LAST:event_formWindowOpened

    private void MnPeriksaRadiologiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnPeriksaRadiologiActionPerformed
        if(tbPetugas.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Maaf, table masih kosong...!!!!");
            TCari.requestFocus();
        }else if(TPasien.getText().trim().equals("")){
            JOptionPane.showMessageDialog(null,"Maaf, Silahkan anda pilih dulu dengan menklik data pada table...!!!");
            tbPetugas.requestFocus();
        }else{
            if(Sequel.cariInteger("select count(no_rawat) from kamar_inap where no_rawat=?",TNoRw.getText())>0){
                JOptionPane.showMessageDialog(null,"Maaf, Pasien sudah masuk Kamar Inap. Gunakan billing Ranap..!!!");
            }else {
                this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                DlgPeriksaRadiologi dlgro=new DlgPeriksaRadiologi(null,false);
                dlgro.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
                dlgro.setLocationRelativeTo(internalFrame1);
                dlgro.emptTeks();
                dlgro.isCek();
                dlgro.setNoRm(TNoRw.getText(),"Ralan");  
                dlgro.tampil();
                dlgro.setVisible(true);
                this.setCursor(Cursor.getDefaultCursor());
            }
        }
    }//GEN-LAST:event_MnPeriksaRadiologiActionPerformed

    private void MnBarcodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnBarcodeActionPerformed
            if(!TPasien.getText().equals("")){
                this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                Map<String, Object> param = new HashMap<>();
                param.put("nama",TPasien.getText());
                param.put("alamat",Sequel.cariIsi("select date_format(tgl_lahir,'%d/%m/%Y') from pasien where no_rkm_medis=?",TNoRM.getText()));
                param.put("norm",TNoRM.getText());
                param.put("parameter","%"+TCari.getText().trim()+"%");     
                param.put("namars",var.getnamars());
                param.put("alamatrs",var.getalamatrs());
                param.put("kotars",var.getkabupatenrs());
                param.put("propinsirs",var.getpropinsirs());
                param.put("kontakrs",var.getkontakrs());
                param.put("emailrs",var.getemailrs());   
                Valid.MyReport("rptBarcodeRawat.jrxml","report","::[ Barcode No.Rawat ]::",
                        "select reg_periksa.no_rawat from reg_periksa where no_rawat='"+TNoRw.getText()+"'",param); 
                this.setCursor(Cursor.getDefaultCursor());
            }  
    }//GEN-LAST:event_MnBarcodeActionPerformed

    private void MnCheckList1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnCheckList1ActionPerformed
        if(!TPasien.getText().equals("")){
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Map<String, Object> param = new HashMap<>();
            param.put("poli",TPoli.getText());
            param.put("antrian",TNoReg.getText());
            param.put("nama",TPasien.getText());
            param.put("norm",TNoRM.getText());
            param.put("bayar",nmpnj.getText());
            param.put("namars",var.getnamars());
            param.put("alamatrs",var.getalamatrs());
            param.put("kotars",var.getkabupatenrs());
            param.put("propinsirs",var.getpropinsirs());
            param.put("kontakrs",var.getkontakrs());
            param.put("emailrs",var.getemailrs());
            param.put("logo",Sequel.cariGambar("select logo from setting"));
            Valid.MyReport("rptCheckList2.jrxml","report","::[ Check List ]::",
                "select current_date() as sekarang",param);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnCheckList1ActionPerformed

    private void MnDiagnosaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnDiagnosaActionPerformed
        if(TPasien.getText().trim().equals("")){
            JOptionPane.showMessageDialog(null,"Maaf, Silahkan anda pilih dulu pasien...!!!");
            tbPetugas.requestFocus();
        }else{
            if(Sequel.cariInteger("select count(no_rawat) from kamar_inap where no_rawat=?",TNoRw.getText())>0){
                    JOptionPane.showMessageDialog(null,"Maaf, Pasien sudah masuk Kamar Inap. Masukkan diagnosa lewat kamar inap..!!!");
            }else {
                this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                DlgDiagnosaPenyakit resep=new DlgDiagnosaPenyakit(null,false);
                resep.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
                resep.setLocationRelativeTo(internalFrame1);
                resep.isCek();
                resep.setNoRm(TNoRw.getText(),DTPCari1.getDate(),DTPCari2.getDate(),"Ralan");
                resep.panelDiagnosa1.tampil();
                resep.setVisible(true);
                this.setCursor(Cursor.getDefaultCursor());
            }                
        }
    }//GEN-LAST:event_MnDiagnosaActionPerformed

    private void ppGrafikPerpoli1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppGrafikPerpoli1ActionPerformed
        tampil();
        DefaultPieDataset dpd = new DefaultPieDataset();
        try {                
            rs = koneksi.prepareStatement("select poliklinik.nm_poli,count(poliklinik.nm_poli) as jumlah "+
                "from reg_periksa inner join poliklinik on reg_periksa.kd_poli=poliklinik.kd_poli "+
                "where tgl_registrasi between '"+Valid.SetTgl(DTPCari1.getSelectedItem()+"")+"' and '"+Valid.SetTgl(DTPCari2.getSelectedItem()+"")+"' group by poliklinik.nm_poli").executeQuery();
            while(rs.next()) {
                dpd.setValue(rs.getString(1)+"("+rs.getString(2)+")",rs.getDouble(2));
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
        
        JFreeChart freeChart = ChartFactory.createPieChart("Grafik Periksa Per Unit/Poli Tanggal "+Valid.SetTgl(DTPCari1.getSelectedItem()+"")+" S.D. "+Valid.SetTgl(DTPCari2.getSelectedItem()+""),dpd,true,true, false); //String title,PieDatasheet datasheet,boolean legends,boolean tooltips,boolean url 
        ChartFrame cf = new ChartFrame("Grafik Periksa Per Unit/Poli",freeChart);
        cf.setSize(internalFrame1.getWidth()-20, internalFrame1.getHeight()-20);
        cf.setLocationRelativeTo(internalFrame1);
        cf.setAlwaysOnTop(false);
        cf.setIconImage(new ImageIcon(super.getClass().getResource("/picture/addressbook-edit24.png")).getImage());
        cf.setVisible(true);        
    }//GEN-LAST:event_ppGrafikPerpoli1ActionPerformed

    private void ppGrafikPerpoli2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppGrafikPerpoli2ActionPerformed
        tampil();
        DefaultCategoryDataset dcd = new DefaultCategoryDataset();
        try {                
            rs = koneksi.prepareStatement("select poliklinik.nm_poli,count(poliklinik.nm_poli) as jumlah "+
                "from reg_periksa inner join poliklinik on reg_periksa.kd_poli=poliklinik.kd_poli "+
                "where tgl_registrasi between '"+Valid.SetTgl(DTPCari1.getSelectedItem()+"")+"' and '"+Valid.SetTgl(DTPCari2.getSelectedItem()+"")+"' group by poliklinik.nm_poli").executeQuery();
            while(rs.next()) {
                dcd.setValue(rs.getDouble(2),rs.getString(1)+"("+rs.getString(2)+")",rs.getString(1));
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
        JFreeChart freeChart = ChartFactory.createBarChart("Grafik Periksa Per Unit/Poli Tanggal "+Valid.SetTgl(DTPCari1.getSelectedItem()+"")+" S.D. "+Valid.SetTgl(DTPCari2.getSelectedItem()+""),"Poliklinik/Unit","Jumlah Pasien", dcd, PlotOrientation.VERTICAL,true, true,true); 
        ChartFrame cf = new ChartFrame("Grafik Periksa Per Unit/Poli",freeChart);
        cf.setSize(internalFrame1.getWidth()-20, internalFrame1.getHeight()-20);
        cf.setLocationRelativeTo(internalFrame1);
        cf.setAlwaysOnTop(false);
        cf.setIconImage(new ImageIcon(super.getClass().getResource("/picture/addressbook-edit24.png")).getImage());
        cf.setVisible(true);  
    }//GEN-LAST:event_ppGrafikPerpoli2ActionPerformed

    private void ppGrafikPerdokter1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppGrafikPerdokter1ActionPerformed
        tampil();
        DefaultPieDataset dpd = new DefaultPieDataset();
        try {                
            rs = koneksi.prepareStatement("select dokter.nm_dokter,count(dokter.nm_dokter) as jumlah "+
                   "from reg_periksa inner join dokter on reg_periksa.kd_dokter=dokter.kd_dokter "+
                   "where tgl_registrasi between '"+Valid.SetTgl(DTPCari1.getSelectedItem()+"")+"' and '"+Valid.SetTgl(DTPCari2.getSelectedItem()+"")+"' group by dokter.nm_dokter").executeQuery();
            while(rs.next()) {
                dpd.setValue(rs.getString(1)+"("+rs.getString(2)+")",rs.getDouble(2));
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
        
        JFreeChart freeChart = ChartFactory.createPieChart("Grafik Periksa Per Dokter Tanggal "+Valid.SetTgl(DTPCari1.getSelectedItem()+"")+" S.D. "+Valid.SetTgl(DTPCari2.getSelectedItem()+""),dpd,true,true, false);
        ChartFrame cf = new ChartFrame("Grafik Periksa Per Dokter",freeChart);
        cf.setSize(internalFrame1.getWidth()-20, internalFrame1.getHeight()-20);
        cf.setLocationRelativeTo(internalFrame1);
        cf.setAlwaysOnTop(false);
        cf.setIconImage(new ImageIcon(super.getClass().getResource("/picture/addressbook-edit24.png")).getImage());
        cf.setVisible(true);        
    }//GEN-LAST:event_ppGrafikPerdokter1ActionPerformed

    private void ppGrafikPerdokter2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppGrafikPerdokter2ActionPerformed
        tampil();
        DefaultCategoryDataset dcd = new DefaultCategoryDataset();
        try {                
            rs = koneksi.prepareStatement("select dokter.nm_dokter,count(dokter.nm_dokter) as jumlah "+
                   "from reg_periksa inner join dokter on reg_periksa.kd_dokter=dokter.kd_dokter "+
                   "where tgl_registrasi between '"+Valid.SetTgl(DTPCari1.getSelectedItem()+"")+"' and '"+Valid.SetTgl(DTPCari2.getSelectedItem()+"")+"' group by dokter.nm_dokter").executeQuery();
            while(rs.next()) {
                dcd.setValue(rs.getDouble(2),rs.getString(1)+"("+rs.getString(2)+")",rs.getString(1));
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
        
       JFreeChart freeChart = ChartFactory.createBarChart("Grafik Periksa Per Dokter Tanggal "+Valid.SetTgl(DTPCari1.getSelectedItem()+"")+" S.D. "+Valid.SetTgl(DTPCari2.getSelectedItem()+""),"Dokter","Jumlah Pasien", dcd, PlotOrientation.VERTICAL,true, true,true); 
        ChartFrame cf = new ChartFrame("Grafik Periksa Per Dokter",freeChart);
        cf.setSize(internalFrame1.getWidth()-20, internalFrame1.getHeight()-20);
        cf.setLocationRelativeTo(internalFrame1);
        cf.setAlwaysOnTop(false);
        cf.setIconImage(new ImageIcon(super.getClass().getResource("/picture/addressbook-edit24.png")).getImage());
        cf.setVisible(true);        
    }//GEN-LAST:event_ppGrafikPerdokter2ActionPerformed

    private void MnHemodialisaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnHemodialisaActionPerformed
        if(tabMode.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Maaf, table masih kosong...!!!!");
            TNoReg.requestFocus();
        }else if(TPasien.getText().trim().equals("")){
            JOptionPane.showMessageDialog(null,"Maaf, Silahkan anda pilih dulu dengan menklik data pada table...!!!");
            tbPetugas.requestFocus();
        }else{
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            DlgHemodialisa resume=new DlgHemodialisa(null,false);
            resume.setNoRm(TNoRw.getText(),DTPCari1.getDate(),DTPCari2.getDate()); 
            resume.isCek();
            resume.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
            resume.setLocationRelativeTo(internalFrame1);
            resume.setVisible(true);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnHemodialisaActionPerformed

    private void MnLabelTrackerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnLabelTrackerActionPerformed
        if(TPasien.getText().trim().equals("")){
            JOptionPane.showMessageDialog(null,"Maaf, Silahkan anda pilih dulu pasien...!!!");
        }else{
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Map<String, Object> param = new HashMap<>();
            param.put("namars",var.getnamars());
            param.put("alamatrs",var.getalamatrs());
            param.put("kotars",var.getkabupatenrs());
            param.put("propinsirs",var.getpropinsirs());
            param.put("kontakrs",var.getkontakrs());
            param.put("emailrs",var.getemailrs());
            param.put("no_rawat",TNoRw.getText());
            param.put("logo",Sequel.cariGambar("select logo from setting"));
            Valid.MyReport("rptLabelTracker.jrxml",param,"::[ Label Tracker ]::");
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnLabelTrackerActionPerformed

    private void MnCetakSuratSakit1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnCetakSuratSakit1ActionPerformed
        DlgSakit2.setSize(550,151);
        DlgSakit2.setLocationRelativeTo(internalFrame1);
        DlgSakit2.setVisible(true);
    }//GEN-LAST:event_MnCetakSuratSakit1ActionPerformed

    private void MnCheckList2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnCheckList2ActionPerformed
            if(!TPasien.getText().equals("")){
                this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                Map<String, Object> param = new HashMap<>();
                param.put("poli",TPoli.getText());
                param.put("antrian",TNoReg.getText());
                param.put("nama",TPasien.getText());
                param.put("norm",TNoRM.getText());
                param.put("bayar",nmpnj.getText());
                param.put("dokter",TDokter.getText());
                param.put("namars",var.getnamars());
                param.put("alamatrs",var.getalamatrs());
                param.put("alamat",TAlmt.getText());
                param.put("kotars",var.getkabupatenrs());
                param.put("propinsirs",var.getpropinsirs());
                param.put("kontakrs",var.getkontakrs());
                param.put("emailrs",var.getemailrs());
                param.put("logo",Sequel.cariGambar("select logo from setting"));
                Valid.MyReport("rptCheckList4.jrxml","report","::[ Check List ]::",
                        "select current_date() as sekarang",param); 
                this.setCursor(Cursor.getDefaultCursor());
            }  
    }//GEN-LAST:event_MnCheckList2ActionPerformed

    private void MnCheckList3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnCheckList3ActionPerformed
        if(!TPasien.getText().equals("")){
                this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                Map<String, Object> param = new HashMap<>();
                param.put("poli",TPoli.getText());
                param.put("antrian",TNoReg.getText());
                param.put("nama",TPasien.getText());
                param.put("norm",TNoRM.getText());
                param.put("dokter",TDokter.getText());
                param.put("bayar",nmpnj.getText());
                param.put("namars",var.getnamars());
                param.put("alamatrs",var.getalamatrs());
                param.put("alamat",TAlmt.getText());
                param.put("kotars",var.getkabupatenrs());
                param.put("propinsirs",var.getpropinsirs());
                param.put("kontakrs",var.getkontakrs());
                param.put("emailrs",var.getemailrs());
                param.put("logo",Sequel.cariGambar("select logo from setting"));
                Valid.MyReport("rptCheckList3.jrxml","report","::[ Check List ]::",
                        "select current_date() as sekarang",param); 
                this.setCursor(Cursor.getDefaultCursor());
            }  
    }//GEN-LAST:event_MnCheckList3ActionPerformed

    private void BtnPrint5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrint5ActionPerformed
      if(TPasien.getText().trim().equals("")){
          JOptionPane.showMessageDialog(null,"Maaf, Silahkan anda pilih dulu pasien...!!!");                
      }else{
         this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
         Map<String, Object> param = new HashMap<>();
            param.put("hari",lmsakit.getText());
            param.put("TanggalAwal",TglSakit1.getSelectedItem().toString());
            param.put("TanggalAkhir",TglSakit2.getSelectedItem().toString());
            param.put("namars",var.getnamars());
                param.put("alamatrs",var.getalamatrs());
                param.put("kotars",var.getkabupatenrs());
                param.put("propinsirs",var.getpropinsirs());
                param.put("kontakrs",var.getkontakrs());
                param.put("nomersurat",NomorSurat.getText());
                param.put("dokterpj",CrDokter3.getText());
                param.put("emailrs",var.getemailrs());   
                param.put("logo",Sequel.cariGambar("select logo from setting")); 
          Valid.MyReport("rptSuratSakit2.jrxml","report","::[ Surat Sakit ]::",
                        "select reg_periksa.no_rkm_medis,dokter.nm_dokter,pasien.keluarga,pasien.namakeluarga,pasien.tgl_lahir,pasien.jk," +
                        " pasien.nm_pasien,pasien.jk,concat(reg_periksa.umurdaftar,' ',reg_periksa.sttsumur)as umur,pasien.pekerjaan,pasien.alamat" +
                        " from reg_periksa inner join pasien inner join dokter" +
                        " on reg_periksa.no_rkm_medis=pasien.no_rkm_medis and reg_periksa.kd_dokter=dokter.kd_dokter  "+
                        "where reg_periksa.no_rawat='"+TNoRw.getText()+"' ",param);
          this.setCursor(Cursor.getDefaultCursor());
      }
    }//GEN-LAST:event_BtnPrint5ActionPerformed

    private void BtnKeluar4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluar4ActionPerformed
        DlgSakit2.dispose();
    }//GEN-LAST:event_BtnKeluar4ActionPerformed

    private void btnPenjab1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPenjab1ActionPerformed
        var.setform("DlgReg");
        rujukmasuk.tampil2();
        rujukmasuk.TCariPerujuk.requestFocus();
        rujukmasuk.WindowPerujuk.setSize(this.getWidth()-20,this.getHeight()-20);
        rujukmasuk.WindowPerujuk.setLocationRelativeTo(internalFrame1);
        rujukmasuk.WindowPerujuk.setVisible(true);
    }//GEN-LAST:event_btnPenjab1ActionPerformed

    private void AsalRujukanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_AsalRujukanKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_PAGE_UP){
            kdpnj.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            BtnSimpan.requestFocus();      
        }else if(evt.getKeyCode()==KeyEvent.VK_UP){
            btnPenjabActionPerformed(null);
        }
    }//GEN-LAST:event_AsalRujukanKeyPressed

    private void MnLaporanRekapPerujukActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnLaporanRekapPerujukActionPerformed
        
        if(tabMode.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Maaf, data sudah habis. Tidak ada data yang bisa anda print...!!!!");
            BtnBatal.requestFocus();
        }else if(tabMode.getRowCount()!=0){
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Map<String, Object> param = new HashMap<>();                 
            param.put("namars",var.getnamars());
            param.put("alamatrs",var.getalamatrs());
            param.put("kotars",var.getkabupatenrs());
            param.put("propinsirs",var.getpropinsirs());
            param.put("kontakrs",var.getkontakrs());
            param.put("emailrs",var.getemailrs());   
            param.put("logo",Sequel.cariGambar("select logo from setting")); 
               Valid.MyReport("rptRegPerPerujuk.jrxml","report","::[ Data Registrasi Per Perujuk ]::",
                       "select reg_periksa.tgl_registrasi,rujuk_masuk.perujuk,count(reg_periksa.no_rawat) as jumlah," +
                       "sum(reg_periksa.biaya_reg) as jasarujuk " +
                       "from reg_periksa inner join rujuk_masuk on reg_periksa.no_rawat=rujuk_masuk.no_rawat " +
                       "where reg_periksa.tgl_registrasi between '"+Valid.SetTgl(DTPCari1.getSelectedItem()+"")+"' and '"+Valid.SetTgl(DTPCari2.getSelectedItem()+"")+"' "+
                       "group by rujuk_masuk.perujuk order by count(reg_periksa.no_rawat) desc ",param);   
            this.setCursor(Cursor.getDefaultCursor());   
        }
        
    }//GEN-LAST:event_MnLaporanRekapPerujukActionPerformed

    private void MnCheckList4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnCheckList4ActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        if(!TPasien.getText().equals("")){
                Map<String, Object> param = new HashMap<>();
                param.put("poli",TPoli.getText());
                param.put("antrian",TNoReg.getText());
                param.put("nama",TPasien.getText());
                param.put("norm",TNoRM.getText());                
                param.put("dokter",TDokter.getText());
                param.put("no_rawat",TNoRw.getText());
                param.put("bayar",nmpnj.getText());
                param.put("penjab",TPngJwb.getText());
                param.put("namars",var.getnamars());
                param.put("alamatrs",var.getalamatrs());
                param.put("kotars",var.getkabupatenrs());
                param.put("propinsirs",var.getpropinsirs());
                param.put("kontakrs",var.getkontakrs());
                param.put("emailrs",var.getemailrs());
                param.put("logo",Sequel.cariGambar("select logo from setting"));
                Valid.MyReport("rptLembarPeriksa.jrxml","report","::[ Lembar Periksa ]::",
                        "select date_format(current_date(),'%d/%m/%Y') as sekarang",param); 
            }
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnCheckList4ActionPerformed

    private void MnCheckList5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnCheckList5ActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        if(!TPasien.getText().equals("")){
                Map<String, Object> param = new HashMap<>();
                param.put("poli",TPoli.getText());
                param.put("antrian",TNoReg.getText());
                param.put("nama",TPasien.getText());
                param.put("norm",TNoRM.getText());
                param.put("dokter",TDokter.getText());
                param.put("no_rawat",TNoRw.getText());
                param.put("bayar",nmpnj.getText());
                param.put("penjab",TPngJwb.getText());
                param.put("namars",var.getnamars());
                param.put("alamatrs",var.getalamatrs());
                param.put("kotars",var.getkabupatenrs());
                param.put("propinsirs",var.getpropinsirs());
                param.put("kontakrs",var.getkontakrs());
                param.put("emailrs",var.getemailrs());
                param.put("logo",Sequel.cariGambar("select logo from setting"));
                Valid.MyReport("rptLembarPeriksa2.jrxml","report","::[ Lembar Periksa ]::",
                        "select date_format(current_date(),'%d/%m/%Y') as sekarang",param); 
            }
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnCheckList5ActionPerformed

    private void BtnSeek5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSeek5ActionPerformed
        var.setform("DlgReg");
        pilihan=3;
        dokter.isCek();
        dokter.TCari.requestFocus();
        dokter.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
        dokter.setLocationRelativeTo(internalFrame1);
        dokter.setVisible(true);
    }//GEN-LAST:event_BtnSeek5ActionPerformed

    private void MnLabelTracker1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnLabelTracker1ActionPerformed
        if(TPasien.getText().trim().equals("")){
            JOptionPane.showMessageDialog(null,"Maaf, Silahkan anda pilih dulu pasien...!!!");
        }else{
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Map<String, Object> param = new HashMap<>();
            param.put("namars",var.getnamars());
            param.put("alamatrs",var.getalamatrs());
            param.put("kotars",var.getkabupatenrs());
            param.put("propinsirs",var.getpropinsirs());
            param.put("kontakrs",var.getkontakrs());
            param.put("emailrs",var.getemailrs());
            param.put("no_rawat",TNoRw.getText());
            param.put("logo",Sequel.cariGambar("select logo from setting"));
            Valid.MyReport("rptLabelTracker2.jrxml",param,"::[ Label Tracker ]::");
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnLabelTracker1ActionPerformed

    private void MnCetakRegisterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnCetakRegisterActionPerformed
        if(TPasien.getText().trim().equals("")){
            JOptionPane.showMessageDialog(null,"Maaf, Silahkan anda pilih dulu pasien...!!!");
        }else{
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Map<String, Object> param = new HashMap<>();
            param.put("namars",var.getnamars());
            param.put("alamatrs",var.getalamatrs());
            param.put("kotars",var.getkabupatenrs());
            param.put("propinsirs",var.getpropinsirs());
            param.put("kontakrs",var.getkontakrs());
            param.put("emailrs",var.getemailrs());
            param.put("logo",Sequel.cariGambar("select logo from setting"));
            Valid.MyReport("rptBuktiRegister.jrxml","report","::[ Bukti Register ]::",
                   "select reg_periksa.no_reg,reg_periksa.no_rawat,reg_periksa.tgl_registrasi,reg_periksa.jam_reg,pasien.no_tlp,"+
                   "reg_periksa.kd_dokter,dokter.nm_dokter,reg_periksa.no_rkm_medis,pasien.nm_pasien,pasien.jk,pasien.umur as umur,poliklinik.nm_poli,"+
                   "reg_periksa.p_jawab,reg_periksa.almt_pj,reg_periksa.hubunganpj,reg_periksa.biaya_reg,reg_periksa.stts_daftar,penjab.png_jawab "+
                   "from reg_periksa inner join dokter inner join pasien inner join poliklinik inner join penjab "+
                   "on reg_periksa.kd_dokter=dokter.kd_dokter and reg_periksa.no_rkm_medis=pasien.no_rkm_medis "+
                   "and reg_periksa.kd_pj=penjab.kd_pj and reg_periksa.kd_poli=poliklinik.kd_poli where reg_periksa.no_rawat='"+TNoRw.getText()+"' ",param);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnCetakRegisterActionPerformed

    private void DTPRegItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_DTPRegItemStateChanged
        isNumber();
    }//GEN-LAST:event_DTPRegItemStateChanged

    private void MnPersetujuanMedisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnPersetujuanMedisActionPerformed
        if(TPasien.getText().trim().equals("")){
            JOptionPane.showMessageDialog(null,"Maaf, Silahkan anda pilih dulu pasien...!!!");
        }else{
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Map<String, Object> param = new HashMap<>();
            param.put("namars",var.getnamars());
            param.put("alamatrs",var.getalamatrs());
            param.put("kotars",var.getkabupatenrs());
            param.put("propinsirs",var.getpropinsirs());
            param.put("kontakrs",var.getkontakrs());
            param.put("emailrs",var.getemailrs());
            param.put("logo",Sequel.cariGambar("select logo from setting"));
            Valid.MyReport("persetujuantindakanmedis.jrxml","report","::[ Persetujuan Tindakan ]::","select pasien.no_rkm_medis, pasien.nm_pasien, pasien.no_ktp, pasien.jk, "+
                   "pasien.tmp_lahir, pasien.tgl_lahir,pasien.nm_ibu, concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab) as alamat, pasien.gol_darah, pasien.pekerjaan,"+
                   "pasien.stts_nikah,pasien.agama,pasien.tgl_daftar,pasien.no_tlp,pasien.umur,"+
                   "pasien.pnd, pasien.keluarga, pasien.namakeluarga,penjab.png_jawab,pasien.pekerjaanpj,"+
                   "concat(pasien.alamatpj,', ',pasien.kelurahanpj,', ',pasien.kecamatanpj,', ',pasien.kabupatenpj) as alamatpj from pasien "+
                   "inner join kelurahan inner join kecamatan inner join kabupaten "+
                   "inner join penjab on pasien.kd_pj=penjab.kd_pj and pasien.kd_kel=kelurahan.kd_kel "+
                   "and pasien.kd_kec=kecamatan.kd_kec and pasien.kd_kab=kabupaten.kd_kab  where pasien.no_rkm_medis='"+TNoRM.getText()+"' ",param);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnPersetujuanMedisActionPerformed

    private void MnOperasiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnOperasiActionPerformed
        if(tabMode.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Maaf, table masih kosong...!!!!");
            TCari.requestFocus();
        }else{
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            DlgTagihanOperasi dlgro=new DlgTagihanOperasi(null,false);
            dlgro.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
            dlgro.setLocationRelativeTo(internalFrame1);
            dlgro.setNoRm(TNoRw.getText(),TNoRM.getText()+", "+TPasien.getText(),"Ralan");
            dlgro.setVisible(true);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnOperasiActionPerformed

    private void MnGelang1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnGelang1ActionPerformed
        if(tabMode.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Maaf, data registrasi sudah habis...!!!!");
            TCari.requestFocus();
        }else if(TPasien.getText().trim().equals("")){
            JOptionPane.showMessageDialog(null,"Maaf, Silahkan anda pilih dulu data registrasi pada table...!!!");
            TCari.requestFocus();
        }else{
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Map<String, Object> param = new HashMap<>();
            param.put("namars",var.getnamars());
            param.put("alamatrs",var.getalamatrs());
            param.put("kotars",var.getkabupatenrs());
            param.put("propinsirs",var.getpropinsirs());
            param.put("kontakrs",var.getkontakrs());
            param.put("emailrs",var.getemailrs());
            param.put("tanggal",DTPReg.getSelectedItem().toString());
            param.put("logo",Sequel.cariGambar("select logo from setting"));
            Valid.MyReport("rptBarcodeRM6.jrxml","report","::[ Gelang Pasien ]::","select pasien.no_rkm_medis, pasien.nm_pasien, pasien.no_ktp, pasien.jk, "+
                "pasien.tmp_lahir, pasien.tgl_lahir,pasien.nm_ibu, concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab) as alamat, pasien.gol_darah, pasien.pekerjaan,"+
                "pasien.stts_nikah,pasien.agama,pasien.tgl_daftar,pasien.no_tlp,pasien.umur,"+
                "pasien.pnd, pasien.keluarga, pasien.namakeluarga,penjab.png_jawab,pasien.pekerjaanpj,"+
                "concat(pasien.alamatpj,', ',pasien.kelurahanpj,', ',pasien.kecamatanpj,', ',pasien.kabupatenpj) as alamatpj from pasien "+
                "inner join kelurahan inner join kecamatan inner join kabupaten "+
                "inner join penjab on pasien.kd_pj=penjab.kd_pj and pasien.kd_kel=kelurahan.kd_kel "+
                "and pasien.kd_kec=kecamatan.kd_kec and pasien.kd_kab=kabupaten.kd_kab  where pasien.no_rkm_medis='"+TNoRM.getText()+"' ",param);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnGelang1ActionPerformed

    private void MnGelang2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnGelang2ActionPerformed
        if(tabMode.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Maaf, data registrasi sudah habis...!!!!");
            TCari.requestFocus();
        }else if(TPasien.getText().trim().equals("")){
            JOptionPane.showMessageDialog(null,"Maaf, Silahkan anda pilih dulu data registrasi pada table...!!!");
            TCari.requestFocus();
        }else{
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Map<String, Object> param = new HashMap<>();
            param.put("namars",var.getnamars());
            param.put("alamatrs",var.getalamatrs());
            param.put("kotars",var.getkabupatenrs());
            param.put("propinsirs",var.getpropinsirs());
            param.put("kontakrs",var.getkontakrs());
            param.put("emailrs",var.getemailrs());
            param.put("tanggal",DTPReg.getSelectedItem().toString());
            param.put("logo",Sequel.cariGambar("select logo from setting"));
            Valid.MyReport("rptBarcodeRM7.jrxml","report","::[ Gelang Pasien ]::","select pasien.no_rkm_medis, pasien.nm_pasien, pasien.no_ktp, pasien.jk, "+
                "pasien.tmp_lahir, pasien.tgl_lahir,pasien.nm_ibu, concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab) as alamat, pasien.gol_darah, pasien.pekerjaan,"+
                "pasien.stts_nikah,pasien.agama,pasien.tgl_daftar,pasien.no_tlp,pasien.umur,"+
                "pasien.pnd, pasien.keluarga, pasien.namakeluarga,penjab.png_jawab,pasien.pekerjaanpj,"+
                "concat(pasien.alamatpj,', ',pasien.kelurahanpj,', ',pasien.kecamatanpj,', ',pasien.kabupatenpj) as alamatpj from pasien "+
                "inner join kelurahan inner join kecamatan inner join kabupaten "+
                "inner join penjab on pasien.kd_pj=penjab.kd_pj and pasien.kd_kel=kelurahan.kd_kel "+
                "and pasien.kd_kec=kecamatan.kd_kec and pasien.kd_kab=kabupaten.kd_kab  where pasien.no_rkm_medis='"+TNoRM.getText()+"' ",param);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnGelang2ActionPerformed

    private void MnCheckList6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnCheckList6ActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        if(!TPasien.getText().equals("")){
                Map<String, Object> param = new HashMap<>();
                param.put("poli",TPoli.getText());
                param.put("antrian",TNoReg.getText());
                param.put("nama",TPasien.getText());
                param.put("norm",TNoRM.getText());
                param.put("lahir",Sequel.cariIsi("select DATE_FORMAT(tgl_lahir,'%d-%m-%Y') from pasien where no_rkm_medis=? ",TNoRM.getText()));
                param.put("dokter",TDokter.getText());
                param.put("no_rawat",TNoRw.getText());
                param.put("bayar",nmpnj.getText());
                param.put("penjab",TPngJwb.getText());
                param.put("namars",var.getnamars());
                param.put("alamatrs",var.getalamatrs());
                param.put("kotars",var.getkabupatenrs());
                param.put("propinsirs",var.getpropinsirs());
                param.put("kontakrs",var.getkontakrs());
                param.put("emailrs",var.getemailrs());
                param.put("logo",Sequel.cariGambar("select logo from setting"));
                Valid.MyReport("rptLembarPeriksa3.jrxml","report","::[ Lembar Periksa]::",
                        "select date_format(current_date(),'%d/%m/%Y') as sekarang",param); 
            }
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnCheckList6ActionPerformed

    private void MnCheckList7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnCheckList7ActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        if(!TPasien.getText().equals("")){
                Map<String, Object> param = new HashMap<>();
                param.put("poli",TPoli.getText());
                param.put("antrian",TNoReg.getText());
                param.put("nama",TPasien.getText());
                param.put("norm",TNoRM.getText());
                param.put("lahir",Sequel.cariIsi("select DATE_FORMAT(tgl_lahir,'%d-%m-%Y') from pasien where no_rkm_medis=? ",TNoRM.getText()));
                param.put("dokter",TDokter.getText());
                param.put("no_rawat",TNoRw.getText());
                param.put("bayar",nmpnj.getText());
                param.put("penjab",TPngJwb.getText());
                param.put("namars",var.getnamars());
                param.put("alamatrs",var.getalamatrs());
                param.put("kotars",var.getkabupatenrs());
                param.put("propinsirs",var.getpropinsirs());
                param.put("kontakrs",var.getkontakrs());
                param.put("emailrs",var.getemailrs());
                param.put("logo",Sequel.cariGambar("select logo from setting"));
                Valid.MyReport("rptLembarPeriksa4.jrxml","report","::[ Lembar Periksa]::",
                        "select date_format(current_date(),'%d/%m/%Y') as sekarang",param); 
            }
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnCheckList7ActionPerformed

    private void MnHapusTagihanOperasiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnHapusTagihanOperasiActionPerformed
        if(TPasien.getText().trim().equals("")){
            JOptionPane.showMessageDialog(null,"Maaf, Silahkan anda pilih dulu pasien...!!!");
            TCari.requestFocus();
        }else{
            Sequel.queryu("delete from operasi where no_rawat='"+TNoRw.getText()+"'");
            Sequel.queryu("delete from beri_obat_operasi where no_rawat='"+TNoRw.getText()+"'");
        }
    }//GEN-LAST:event_MnHapusTagihanOperasiActionPerformed

    private void MnHapusObatOperasiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnHapusObatOperasiActionPerformed
        if(TPasien.getText().trim().equals("")){
            JOptionPane.showMessageDialog(null,"Maaf, Silahkan anda pilih dulu pasien...!!!");
            TCari.requestFocus();
        }else{
            Sequel.queryu("delete from beri_obat_operasi where no_rawat='"+TNoRw.getText()+"'");
        }
    }//GEN-LAST:event_MnHapusObatOperasiActionPerformed

    private void MnGelang3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnGelang3ActionPerformed
        if(tabMode.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Maaf, data registrasi sudah habis...!!!!");
            TCari.requestFocus();
        }else if(TPasien.getText().trim().equals("")){
            JOptionPane.showMessageDialog(null,"Maaf, Silahkan anda pilih dulu data registrasi pada table...!!!");
            TCari.requestFocus();
        }else{
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Map<String, Object> param = new HashMap<>();
            param.put("namars",var.getnamars());
            param.put("alamatrs",var.getalamatrs());
            param.put("kotars",var.getkabupatenrs());
            param.put("propinsirs",var.getpropinsirs());
            param.put("kontakrs",var.getkontakrs());
            param.put("emailrs",var.getemailrs());
            param.put("tanggal",DTPReg.getSelectedItem().toString());
            param.put("logo",Sequel.cariGambar("select logo from setting"));
            Valid.MyReport("rptBarcodeRM8.jrxml","report","::[ Gelang Pasien ]::","select pasien.no_rkm_medis, pasien.nm_pasien, pasien.no_ktp, pasien.jk, "+
                "pasien.tmp_lahir, pasien.tgl_lahir,pasien.nm_ibu, concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab) as alamat, pasien.gol_darah, pasien.pekerjaan,"+
                "pasien.stts_nikah,pasien.agama,pasien.tgl_daftar,pasien.no_tlp,pasien.umur,"+
                "pasien.pnd, pasien.keluarga, pasien.namakeluarga,penjab.png_jawab,pasien.pekerjaanpj,"+
                "concat(pasien.alamatpj,', ',pasien.kelurahanpj,', ',pasien.kecamatanpj,', ',pasien.kabupatenpj) as alamatpj from pasien "+
                "inner join kelurahan inner join kecamatan inner join kabupaten "+
                "inner join penjab on pasien.kd_pj=penjab.kd_pj and pasien.kd_kel=kelurahan.kd_kel "+
                "and pasien.kd_kec=kecamatan.kd_kec and pasien.kd_kab=kabupaten.kd_kab  where pasien.no_rkm_medis='"+TNoRM.getText()+"' ",param);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnGelang3ActionPerformed

    private void MnGelang4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnGelang4ActionPerformed
        if(tabMode.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Maaf, data registrasi sudah habis...!!!!");
            TCari.requestFocus();
        }else if(TPasien.getText().trim().equals("")){
            JOptionPane.showMessageDialog(null,"Maaf, Silahkan anda pilih dulu data registrasi pada table...!!!");
            TCari.requestFocus();
        }else{
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Map<String, Object> param = new HashMap<>();
            param.put("namars",var.getnamars());
            param.put("alamatrs",var.getalamatrs());
            param.put("kotars",var.getkabupatenrs());
            param.put("propinsirs",var.getpropinsirs());
            param.put("kontakrs",var.getkontakrs());
            param.put("emailrs",var.getemailrs());
            param.put("tanggal",DTPReg.getSelectedItem().toString());
            param.put("logo",Sequel.cariGambar("select logo from setting"));
            Valid.MyReport("rptBarcodeRM10.jrxml","report","::[ Gelang Pasien ]::","select pasien.no_rkm_medis, pasien.nm_pasien, pasien.no_ktp, pasien.jk, "+
                "pasien.tmp_lahir, DATE_FORMAT(pasien.tgl_lahir,'%d/%m/%Y') as tgl_lahir,pasien.nm_ibu, concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab) as alamat, pasien.gol_darah, pasien.pekerjaan,"+
                "pasien.stts_nikah,pasien.agama,pasien.tgl_daftar,pasien.no_tlp,pasien.umur,"+
                "pasien.pnd, pasien.keluarga, pasien.namakeluarga,penjab.png_jawab,pasien.pekerjaanpj,"+
                "concat(pasien.alamatpj,', ',pasien.kelurahanpj,', ',pasien.kecamatanpj,', ',pasien.kabupatenpj) as alamatpj from pasien "+
                "inner join kelurahan inner join kecamatan inner join kabupaten "+
                "inner join penjab on pasien.kd_pj=penjab.kd_pj and pasien.kd_kel=kelurahan.kd_kel "+
                "and pasien.kd_kec=kecamatan.kd_kec and pasien.kd_kab=kabupaten.kd_kab  where pasien.no_rkm_medis='"+TNoRM.getText()+"' ",param);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnGelang4ActionPerformed

    private void MnBuktiPelayananRalanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnBuktiPelayananRalanActionPerformed
        if(TPasien.getText().trim().equals("")){
            JOptionPane.showMessageDialog(null,"Maaf, Silahkan anda pilih dulu pasien...!!!");
        }else{
             this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Map<String, Object> param = new HashMap<>();
            param.put("namars",var.getnamars());
            param.put("alamatrs",var.getalamatrs());
            param.put("kotars",var.getkabupatenrs());
            param.put("propinsirs",var.getpropinsirs());
            param.put("kontakrs",var.getkontakrs());
            param.put("emailrs",var.getemailrs());
            param.put("logo",Sequel.cariGambar("select logo from setting"));
            Valid.MyReport("rptBuktiPelayananRalan.jrxml","report","::[ Surat Jaminan & Bukti Pelayanan Rawat Jalan ]::",
                   "select reg_periksa.no_reg,reg_periksa.no_rawat,reg_periksa.tgl_registrasi,"+
                   "reg_periksa.jam_reg, reg_periksa.kd_dokter,dokter.nm_dokter,reg_periksa.no_rkm_medis,pasien.nm_pasien,pasien.jk,concat(reg_periksa.umurdaftar,' ',reg_periksa.sttsumur)as umur,poliklinik.nm_poli," +
                   "reg_periksa.p_jawab,reg_periksa.almt_pj,reg_periksa.hubunganpj,reg_periksa.biaya_reg," +
                   "reg_periksa.stts_daftar,penjab.png_jawab,pasien.no_peserta,pasien.tgl_lahir " +
                   "from reg_periksa inner join dokter inner join pasien inner join poliklinik inner join penjab " +
                   "on reg_periksa.kd_dokter=dokter.kd_dokter and reg_periksa.no_rkm_medis=pasien.no_rkm_medis and reg_periksa.kd_pj=penjab.kd_pj and reg_periksa.kd_poli=poliklinik.kd_poli "+
                   "where reg_periksa.no_rawat='"+TNoRw.getText()+"' ",param);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnBuktiPelayananRalanActionPerformed

    private void MnSEPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnSEPActionPerformed
        if(tabMode.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Maaf, table masih kosong...!!!!");
            TNoReg.requestFocus();
        }else if(TPasien.getText().trim().equals("")){
            JOptionPane.showMessageDialog(null,"Maaf, Silahkan anda pilih dulu dengan menklik data pada table...!!!");
            tbPetugas.requestFocus();
        }else{
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            var.setform("DlgReg");
            BPJSDataSEP dlgki=new BPJSDataSEP(null,false);
            dlgki.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
            dlgki.setLocationRelativeTo(internalFrame1);
            dlgki.isCek();
            dlgki.setNoRm(TNoRw.getText(),DTPReg.getDate(),"2. Ralan",kdpoli.getText(),TPoli.getText());   
            dlgki.setVisible(true);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnSEPActionPerformed

    private void MnLabelTracker2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnLabelTracker2ActionPerformed
        if(TPasien.getText().trim().equals("")){
            JOptionPane.showMessageDialog(null,"Maaf, Silahkan anda pilih dulu pasien...!!!");
        }else{
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Map<String, Object> param = new HashMap<>();
            param.put("namars",var.getnamars());
            param.put("alamatrs",var.getalamatrs());
            param.put("kotars",var.getkabupatenrs());
            param.put("propinsirs",var.getpropinsirs());
            param.put("kontakrs",var.getkontakrs());
            param.put("emailrs",var.getemailrs());
            param.put("logo",Sequel.cariGambar("select logo from setting"));
            Valid.MyReport("rptLabelTracker3.jrxml","report","::[ Label Tracker ]::",
                   "select reg_periksa.no_reg,reg_periksa.no_rawat,reg_periksa.tgl_registrasi,reg_periksa.jam_reg,"+
                   "reg_periksa.kd_dokter,dokter.nm_dokter,reg_periksa.no_rkm_medis,pasien.nm_pasien,pasien.jk,concat(reg_periksa.umurdaftar,' ',reg_periksa.sttsumur)as umur,poliklinik.nm_poli,"+
                   "reg_periksa.p_jawab,reg_periksa.almt_pj,reg_periksa.hubunganpj,reg_periksa.biaya_reg,reg_periksa.stts_daftar,penjab.png_jawab "+
                   "from reg_periksa inner join dokter inner join pasien inner join poliklinik inner join penjab "+
                   "on reg_periksa.kd_dokter=dokter.kd_dokter and reg_periksa.no_rkm_medis=pasien.no_rkm_medis "+
                   "and reg_periksa.kd_pj=penjab.kd_pj and reg_periksa.kd_poli=poliklinik.kd_poli where reg_periksa.no_rawat='"+TNoRw.getText()+"' ",param);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnLabelTracker2ActionPerformed

    private void MnLabelTracker3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnLabelTracker3ActionPerformed
        if(TPasien.getText().trim().equals("")){
            JOptionPane.showMessageDialog(null,"Maaf, Silahkan anda pilih dulu pasien...!!!");
        }else{
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Map<String, Object> param = new HashMap<>();
            param.put("namars",var.getnamars());
            param.put("alamatrs",var.getalamatrs());
            param.put("kotars",var.getkabupatenrs());
            param.put("propinsirs",var.getpropinsirs());
            param.put("kontakrs",var.getkontakrs());
            param.put("emailrs",var.getemailrs());
            param.put("logo",Sequel.cariGambar("select logo from setting"));
            Valid.MyReport("rptLabelTracker4.jrxml","report","::[ Label Tracker ]::",
                   "select reg_periksa.no_reg,reg_periksa.no_rawat,reg_periksa.tgl_registrasi,reg_periksa.jam_reg,"+
                   "reg_periksa.kd_dokter,dokter.nm_dokter,reg_periksa.no_rkm_medis,pasien.nm_pasien,pasien.jk,concat(reg_periksa.umurdaftar,' ',reg_periksa.sttsumur)as umur,poliklinik.nm_poli,"+
                   "reg_periksa.p_jawab,reg_periksa.almt_pj,reg_periksa.hubunganpj,reg_periksa.biaya_reg,reg_periksa.stts_daftar,penjab.png_jawab "+
                   "from reg_periksa inner join dokter inner join pasien inner join poliklinik inner join penjab "+
                   "on reg_periksa.kd_dokter=dokter.kd_dokter and reg_periksa.no_rkm_medis=pasien.no_rkm_medis "+
                   "and reg_periksa.kd_pj=penjab.kd_pj and reg_periksa.kd_poli=poliklinik.kd_poli where reg_periksa.no_rawat='"+TNoRw.getText()+"' ",param);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnLabelTracker3ActionPerformed

    private void MnGelang5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnGelang5ActionPerformed
        if(tabMode.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Maaf, data registrasi sudah habis...!!!!");
            TCari.requestFocus();
        }else if(TPasien.getText().trim().equals("")){
            JOptionPane.showMessageDialog(null,"Maaf, Silahkan anda pilih dulu data registrasi pada table...!!!");
            TCari.requestFocus();
        }else{
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Map<String, Object> param = new HashMap<>();
            param.put("namars",var.getnamars());
            param.put("alamatrs",var.getalamatrs());
            param.put("kotars",var.getkabupatenrs());
            param.put("propinsirs",var.getpropinsirs());
            param.put("kontakrs",var.getkontakrs());
            param.put("emailrs",var.getemailrs());
            param.put("tanggal",DTPReg.getSelectedItem().toString());
            param.put("logo",Sequel.cariGambar("select logo from setting"));
            Valid.MyReport("rptBarcodeRM14.jrxml","report","::[ Gelang Pasien ]::","select pasien.no_rkm_medis, pasien.nm_pasien, pasien.no_ktp, pasien.jk, "+
                "pasien.tmp_lahir, DATE_FORMAT(pasien.tgl_lahir,'%d/%m/%Y') as tgl_lahir,pasien.nm_ibu, concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab) as alamat, pasien.gol_darah, pasien.pekerjaan,"+
                "pasien.stts_nikah,pasien.agama,pasien.tgl_daftar,pasien.no_tlp,pasien.umur,"+
                "pasien.pnd, pasien.keluarga, pasien.namakeluarga,penjab.png_jawab,pasien.pekerjaanpj,"+
                "concat(pasien.alamatpj,', ',pasien.kelurahanpj,', ',pasien.kecamatanpj,', ',pasien.kabupatenpj) as alamatpj from pasien "+
                "inner join kelurahan inner join kecamatan inner join kabupaten "+
                "inner join penjab on pasien.kd_pj=penjab.kd_pj and pasien.kd_kel=kelurahan.kd_kel "+
                "and pasien.kd_kec=kecamatan.kd_kec and pasien.kd_kab=kabupaten.kd_kab  where pasien.no_rkm_medis='"+TNoRM.getText()+"' ",param);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnGelang5ActionPerformed

    private void MnCetakSuratSakit2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnCetakSuratSakit2ActionPerformed
        pilihan=2;
        DlgSakit.setSize(550,121);
        DlgSakit.setLocationRelativeTo(internalFrame1);
        DlgSakit.setVisible(true);
    }//GEN-LAST:event_MnCetakSuratSakit2ActionPerformed

    private void ppRiwayatBtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppRiwayatBtnPrintActionPerformed
        if(tabMode.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Maaf, data pasien sudah habis...!!!!");
            TNoRw.requestFocus();
        }else if(TPasien.getText().trim().equals("")){
            JOptionPane.showMessageDialog(null,"Maaf, Silahkan anda pilih dulu data registrasi pada table...!!!");
            TCari.requestFocus();
        }else{
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            DlgResumePerawatan resume=new DlgResumePerawatan(null,true);
            resume.setNoRm(TNoRM.getText(),TPasien.getText());
            resume.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
            resume.setLocationRelativeTo(internalFrame1);
            resume.setVisible(true);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_ppRiwayatBtnPrintActionPerformed

    private void MnCetakSuratSehatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnCetakSuratSehatActionPerformed
        if(TPasien.getText().trim().equals("")){
            JOptionPane.showMessageDialog(null,"Maaf, Silahkan anda pilih dulu pasien...!!!");
        }else{
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Map<String, Object> param = new HashMap<>();
            param.put("namars",var.getnamars());
            param.put("alamatrs",var.getalamatrs());
            param.put("kotars",var.getkabupatenrs());
            param.put("propinsirs",var.getpropinsirs());
            param.put("kontakrs",var.getkontakrs());
            param.put("emailrs",var.getemailrs());
            param.put("logo",Sequel.cariGambar("select logo from setting"));
            Valid.MyReport("rptSuratSehat.jrxml","report","::[ Surat Keterangan Sehat ]::",
                    "select reg_periksa.no_rawat,dokter.nm_dokter,pasien.tgl_lahir,pasien.jk,"+
                    " pasien.nm_pasien,pasien.jk,concat(reg_periksa.umurdaftar,' ',reg_periksa.sttsumur)as umur,pasien.pekerjaan,pasien.alamat "+
                    " from reg_periksa inner join pasien inner join dokter "+
                    " on reg_periksa.no_rkm_medis=pasien.no_rkm_medis and reg_periksa.kd_dokter=dokter.kd_dokter  "+
                    "where pasien.no_rkm_medis='"+TNoRM.getText()+"' ",param);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnCetakSuratSehatActionPerformed

    private void MnCetakBebasNarkobaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnCetakBebasNarkobaActionPerformed
        if(TPasien.getText().trim().equals("")){
            JOptionPane.showMessageDialog(null,"Maaf, Silahkan anda pilih dulu pasien...!!!");
        }else{
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Map<String, Object> param = new HashMap<>();
            param.put("namars",var.getnamars());
            param.put("alamatrs",var.getalamatrs());
            param.put("kotars",var.getkabupatenrs());
            param.put("propinsirs",var.getpropinsirs());
            param.put("kontakrs",var.getkontakrs());
            param.put("emailrs",var.getemailrs());
            param.put("logo",Sequel.cariGambar("select logo from setting"));
            Valid.MyReport("rptBebasNarkoba.jrxml","report","::[ Surat Keterangan Bebas Narkoba ]::",
                    "select reg_periksa.no_rawat,dokter.nm_dokter,pasien.tgl_lahir,pasien.jk,"+
                    " pasien.nm_pasien,pasien.jk,concat(reg_periksa.umurdaftar,' ',reg_periksa.sttsumur)as umur,pasien.pekerjaan,pasien.alamat "+
                    " from reg_periksa inner join pasien inner join dokter "+
                    " on reg_periksa.no_rkm_medis=pasien.no_rkm_medis and reg_periksa.kd_dokter=dokter.kd_dokter  "+
                    "where reg_periksa.no_rawat='"+TNoRw.getText()+"' ",param);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnCetakBebasNarkobaActionPerformed

    private void MnSJPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnSJPActionPerformed
        if(tabMode.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Maaf, table masih kosong...!!!!");
            TNoReg.requestFocus();
        }else if(TPasien.getText().trim().equals("")){
            JOptionPane.showMessageDialog(null,"Maaf, Silahkan anda pilih dulu dengan menklik data pada table...!!!");
            tbPetugas.requestFocus();
        }else{
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            var.setform("DlgReg");
            InhealthDataSJP dlgki=new InhealthDataSJP(null,false);
            dlgki.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
            dlgki.setLocationRelativeTo(internalFrame1);
            dlgki.isCek();
            dlgki.setNoRm(TNoRw.getText(),DTPReg.getDate(),"1 RJTP RAWAT JALAN TINGKAT PERTAMA",kdpoli.getText(),TPoli.getText());   
            dlgki.setVisible(true);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnSJPActionPerformed

    private void MnGelang6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnGelang6ActionPerformed
        if(tabMode.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Maaf, data registrasi sudah habis...!!!!");
            TCari.requestFocus();
        }else if(TPasien.getText().trim().equals("")){
            JOptionPane.showMessageDialog(null,"Maaf, Silahkan anda pilih dulu data registrasi pada table...!!!");
            TCari.requestFocus();
        }else{
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Map<String, Object> param = new HashMap<>();
            param.put("namars",var.getnamars());
            param.put("alamatrs",var.getalamatrs());
            param.put("kotars",var.getkabupatenrs());
            param.put("propinsirs",var.getpropinsirs());
            param.put("kontakrs",var.getkontakrs());
            param.put("emailrs",var.getemailrs());
            param.put("tanggal",DTPReg.getSelectedItem().toString());
            param.put("logo",Sequel.cariGambar("select logo from setting"));
            Valid.MyReport("rptBarcodeRM16.jrxml","report","::[ Gelang Pasien ]::","select pasien.no_rkm_medis, pasien.nm_pasien, pasien.no_ktp, pasien.jk, "+
                "pasien.tmp_lahir, pasien.tgl_lahir,pasien.nm_ibu, concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab) as alamat, pasien.gol_darah, pasien.pekerjaan,"+
                "pasien.stts_nikah,pasien.agama,pasien.tgl_daftar,pasien.no_tlp,pasien.umur,"+
                "pasien.pnd, pasien.keluarga, pasien.namakeluarga,penjab.png_jawab,pasien.pekerjaanpj,"+
                "concat(pasien.alamatpj,', ',pasien.kelurahanpj,', ',pasien.kecamatanpj,', ',pasien.kabupatenpj) as alamatpj from pasien "+
                "inner join kelurahan inner join kecamatan inner join kabupaten "+
                "inner join penjab on pasien.kd_pj=penjab.kd_pj and pasien.kd_kel=kelurahan.kd_kel "+
                "and pasien.kd_kec=kecamatan.kd_kec and pasien.kd_kab=kabupaten.kd_kab  where pasien.no_rkm_medis='"+TNoRM.getText()+"' ",param);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnGelang6ActionPerformed

    private void MnLembarCasemixActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnLembarCasemixActionPerformed
        if(TPasien.getText().trim().equals("")){
            JOptionPane.showMessageDialog(null,"Maaf, Silahkan anda pilih dulu pasien...!!!");
        }else{
             this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Map<String, Object> param = new HashMap<>();
            param.put("namars",var.getnamars());
            param.put("alamatrs",var.getalamatrs());
            param.put("kotars",var.getkabupatenrs());
            param.put("propinsirs",var.getpropinsirs());
            param.put("kontakrs",var.getkontakrs());
            param.put("emailrs",var.getemailrs());
            param.put("logo",Sequel.cariGambar("select logo from setting"));
            Valid.MyReport("rptLembarCasemix.jrxml","report","::[ Surat Jaminan & Bukti Pelayanan Rawat Jalan ]::",
                   "select reg_periksa.no_reg,reg_periksa.no_rawat,reg_periksa.tgl_registrasi,"+
                   "reg_periksa.jam_reg, reg_periksa.kd_dokter,dokter.nm_dokter,reg_periksa.no_rkm_medis,pasien.nm_pasien,pasien.jk,concat(reg_periksa.umurdaftar,' ',reg_periksa.sttsumur)as umur,poliklinik.nm_poli," +
                   "reg_periksa.p_jawab,reg_periksa.almt_pj,reg_periksa.hubunganpj,reg_periksa.biaya_reg," +
                   "reg_periksa.stts_daftar,penjab.png_jawab,pasien.no_peserta,pasien.tgl_lahir " +
                   "from reg_periksa inner join dokter inner join pasien inner join poliklinik inner join penjab " +
                   "on reg_periksa.kd_dokter=dokter.kd_dokter and reg_periksa.no_rkm_medis=pasien.no_rkm_medis and reg_periksa.kd_pj=penjab.kd_pj and reg_periksa.kd_poli=poliklinik.kd_poli "+
                   "where reg_periksa.no_rawat='"+TNoRw.getText()+"' ",param);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnLembarCasemixActionPerformed

    private void ppCatatanPasienBtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppCatatanPasienBtnPrintActionPerformed
        if(tabMode.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Maaf, data pasien sudah habis...!!!!");
            TNoRw.requestFocus();
        }else if(TPasien.getText().trim().equals("")){
            JOptionPane.showMessageDialog(null,"Maaf, Silahkan anda pilih dulu data registrasi pada table...!!!");
            TCari.requestFocus();
        }else{
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            catatan.setNoRm(TNoRM.getText());
            catatan.setSize(720,330);
            catatan.setLocationRelativeTo(internalFrame1);
            catatan.setVisible(true);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_ppCatatanPasienBtnPrintActionPerformed

    private void MnSPBKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnSPBKActionPerformed
        if(TPasien.getText().trim().equals("")){
            JOptionPane.showMessageDialog(null,"Maaf, Silahkan anda pilih dulu pasien...!!!");
        }else{
             this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Map<String, Object> param = new HashMap<>();
            param.put("namars",var.getnamars());
            param.put("alamatrs",var.getalamatrs());
            param.put("kotars",var.getkabupatenrs());
            param.put("propinsirs",var.getpropinsirs());
            param.put("kontakrs",var.getkontakrs());
            param.put("emailrs",var.getemailrs());
            param.put("logo",Sequel.cariGambar("select logo from setting"));
            Valid.MyReport("rptSBPK.jrxml","report","::[ Surat Bukti Pelayanan Kesehatan ]::",
                   "select reg_periksa.no_reg,reg_periksa.no_rawat,reg_periksa.tgl_registrasi,"+
                   "reg_periksa.jam_reg, reg_periksa.kd_dokter,dokter.nm_dokter,reg_periksa.no_rkm_medis,pasien.nm_pasien,pasien.jk,concat(reg_periksa.umurdaftar,' ',reg_periksa.sttsumur)as umur,poliklinik.nm_poli," +
                   "reg_periksa.p_jawab,reg_periksa.almt_pj,reg_periksa.hubunganpj,reg_periksa.biaya_reg," +
                   "reg_periksa.stts_daftar,penjab.png_jawab,pasien.no_peserta,pasien.tgl_lahir " +
                   "from reg_periksa inner join dokter inner join pasien inner join poliklinik inner join penjab " +
                   "on reg_periksa.kd_dokter=dokter.kd_dokter and reg_periksa.no_rkm_medis=pasien.no_rkm_medis and reg_periksa.kd_pj=penjab.kd_pj and reg_periksa.kd_poli=poliklinik.kd_poli "+
                   "where reg_periksa.no_rawat='"+TNoRw.getText()+"' ",param);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnSPBKActionPerformed

    private void MnResepDOkterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnResepDOkterActionPerformed
        if(TNoRw.getText().trim().equals("")){
            JOptionPane.showMessageDialog(null,"Maaf, Silahkan anda pilih dulu pasien...!!!");
            tbPetugas.requestFocus();
        }else{
            if(Sequel.cariInteger("select count(no_rawat) from kamar_inap where no_rawat=?",TNoRw.getText())>0){
                JOptionPane.showMessageDialog(null,"Maaf, Pasien sudah masuk Kamar Inap. Gunakan billing Ranap..!!!");
            }else {
                DlgPeresepanDokter resep=new DlgPeresepanDokter(null,false);
                resep.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
                resep.setLocationRelativeTo(internalFrame1);
                resep.setNoRm(TNoRw.getText(),DTPCari1.getDate(),CmbJam.getSelectedItem().toString(),CmbMenit.getSelectedItem().toString(),CmbDetik.getSelectedItem().toString(),
                    kddokter.getText(),TDokter.getText(),"ralan");
                resep.isCek();
                resep.tampilobat();
                resep.setVisible(true);
            }
        }
    }//GEN-LAST:event_MnResepDOkterActionPerformed

    private void ppBerkasBtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppBerkasBtnPrintActionPerformed
        if(tabMode.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Maaf, table masih kosong...!!!!");
            //TNoReg.requestFocus();
        }else if(TNoRw.getText().trim().equals("")){
            JOptionPane.showMessageDialog(null,"Maaf, Silahkan anda pilih dulu dengan menklik data pada table...!!!");
            tbPetugas.requestFocus();
        }else{
            Sequel.menyimpan("mutasi_berkas","'"+TNoRw.getText()+"','Sudah Diterima',now(),now(),'0000-00-00 00:00:00','0000-00-00 00:00:00'","status='Sudah Diterima',diterima=now()","no_rawat='"+TNoRw.getText()+"'");
            Valid.editTable(tabMode,"reg_periksa","no_rawat",TNoRw,"stts='Berkas Diterima'");
            if(tabMode.getRowCount()!=0){tampil();}
        }
    }//GEN-LAST:event_ppBerkasBtnPrintActionPerformed

    private void MnSudahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnSudahActionPerformed
        if(TNoRw.getText().trim().equals("")){
            Valid.textKosong(TNoRw,"No.Rawat");
        }else{
            if(Sequel.cariInteger("select count(no_rawat) from kamar_inap where no_rawat=?",TNoRw.getText())>0){
                JOptionPane.showMessageDialog(null,"Maaf, Pasien sudah masuk Kamar Inap. Gunakan billing Ranap..!!!");
            }else {
                Valid.editTable(tabMode,"reg_periksa","no_rawat",TNoRw,"stts='Sudah'");
                if(tabMode.getRowCount()!=0){tampil();}
            }

        }
    }//GEN-LAST:event_MnSudahActionPerformed

    private void MnBelumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnBelumActionPerformed
        if(TNoRw.getText().trim().equals("")){
            Valid.textKosong(TNoRw,"No.Rawat");
        }else{
            if(Sequel.cariInteger("select count(no_rawat) from kamar_inap where no_rawat=?",TNoRw.getText())>0){
                JOptionPane.showMessageDialog(null,"Maaf, Pasien sudah masuk Kamar Inap. Gunakan billing Ranap..!!!");
            }else {
                Valid.editTable(tabMode,"reg_periksa","no_rawat",TNoRw,"stts='Belum'");
                if(tabMode.getRowCount()!=0){tampil();}
            }

        }
    }//GEN-LAST:event_MnBelumActionPerformed

    private void MnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnBatalActionPerformed
        if(TNoRw.getText().trim().equals("")){
            Valid.textKosong(TNoRw,"No.Rawat");
        }else{
            if(Sequel.cariInteger("select count(no_rawat) from kamar_inap where no_rawat=?",TNoRw.getText())>0){
                JOptionPane.showMessageDialog(null,"Maaf, Pasien sudah masuk Kamar Inap. Gunakan billing Ranap..!!!");
            }else {
                Valid.editTable(tabMode,"reg_periksa","no_rawat",TNoRw,"stts='Batal',biaya_reg='0'");
                if(tabMode.getRowCount()!=0){tampil();}
            }
        }
    }//GEN-LAST:event_MnBatalActionPerformed

    private void MnDirujukActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnDirujukActionPerformed
        if(TNoRw.getText().trim().equals("")){
            Valid.textKosong(TNoRw,"No.Rawat");
        }else{
            if(Sequel.cariInteger("select count(no_rawat) from kamar_inap where no_rawat=?",TNoRw.getText())>0){
                JOptionPane.showMessageDialog(null,"Maaf, Pasien sudah masuk Kamar Inap. Gunakan billing Ranap..!!!");
            }else {
                Valid.editTable(tabMode,"reg_periksa","no_rawat",TNoRw,"stts='Dirujuk'");
                MnRujukActionPerformed(evt);
                if(tabMode.getRowCount()!=0){tampil();}
            }

        }
    }//GEN-LAST:event_MnDirujukActionPerformed

    private void MnDIrawatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnDIrawatActionPerformed
        if(TNoRw.getText().trim().equals("")){
            Valid.textKosong(TNoRw,"No.Rawat");
        }else{
            if(Sequel.cariInteger("select count(no_rawat) from kamar_inap where no_rawat=?",TNoRw.getText())>0){
                JOptionPane.showMessageDialog(null,"Maaf, Pasien sudah masuk Kamar Inap. Gunakan billing Ranap..!!!");
            }else {
                Valid.editTable(tabMode,"reg_periksa","no_rawat",TNoRw,"stts='Dirawat'");
                MnKamarInapActionPerformed(evt);
                if(tabMode.getRowCount()!=0){tampil();}
            }

        }
    }//GEN-LAST:event_MnDIrawatActionPerformed

    private void MnMeninggalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnMeninggalActionPerformed
        if(TNoRw.getText().trim().equals("")){
            Valid.textKosong(TNoRw,"No.Rawat");
        }else{
            if(Sequel.cariInteger("select count(no_rawat) from kamar_inap where no_rawat=?",TNoRw.getText())>0){
                JOptionPane.showMessageDialog(null,"Maaf, Pasien sudah masuk Kamar Inap. Gunakan billing Ranap..!!!");
            }else {
                Valid.editTable(tabMode,"reg_periksa","no_rawat",TNoRw,"stts='Meninggal'");
                DlgPasienMati dlgPasienMati=new DlgPasienMati(null,false);
                dlgPasienMati.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
                dlgPasienMati.setLocationRelativeTo(internalFrame1);
                dlgPasienMati.emptTeks();
                dlgPasienMati.setNoRm(TNoRM.getText());
                dlgPasienMati.isCek();
                dlgPasienMati.setVisible(true);
                if(tabMode.getRowCount()!=0){tampil();}
            }

        }
    }//GEN-LAST:event_MnMeninggalActionPerformed

    private void MnUrutNoRawatDescActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnUrutNoRawatDescActionPerformed
        order="reg_periksa.no_rawat desc";
        tampil();
    }//GEN-LAST:event_MnUrutNoRawatDescActionPerformed

    private void MnUrutNoRawatAscActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnUrutNoRawatAscActionPerformed
        order="reg_periksa.no_rawat asc";
        tampil();
    }//GEN-LAST:event_MnUrutNoRawatAscActionPerformed

    private void MnUrutTanggalDescActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnUrutTanggalDescActionPerformed
        order="reg_periksa.tgl_registrasi,reg_periksa.jam_reg desc";
        tampil();
    }//GEN-LAST:event_MnUrutTanggalDescActionPerformed

    private void MnUrutTanggalAscActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnUrutTanggalAscActionPerformed
        order="reg_periksa.tgl_registrasi,reg_periksa.jam_reg asc";
        tampil();
    }//GEN-LAST:event_MnUrutTanggalAscActionPerformed

    private void MnUrutDokterDescActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnUrutDokterDescActionPerformed
        order="dokter.nm_dokter desc";
        tampil();
    }//GEN-LAST:event_MnUrutDokterDescActionPerformed

    private void MnUrutDokterAscActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnUrutDokterAscActionPerformed
        order="dokter.nm_dokter asc";
        tampil();
    }//GEN-LAST:event_MnUrutDokterAscActionPerformed

    private void MnUrutPoliklinikDescActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnUrutPoliklinikDescActionPerformed
        order="poliklinik.nm_poli desc";
        tampil();
    }//GEN-LAST:event_MnUrutPoliklinikDescActionPerformed

    private void MnUrutPoliklinikAscActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnUrutPoliklinikAscActionPerformed
        order="poliklinik.nm_poli asc";
        tampil();
    }//GEN-LAST:event_MnUrutPoliklinikAscActionPerformed

    private void MnUrutPenjabDescActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnUrutPenjabDescActionPerformed
        order="penjab.png_jawab desc";
        tampil();
    }//GEN-LAST:event_MnUrutPenjabDescActionPerformed

    private void MnUrutPenjabAscActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnUrutPenjabAscActionPerformed
        order="penjab.png_jawab asc";
        tampil();
    }//GEN-LAST:event_MnUrutPenjabAscActionPerformed

    private void MnUrutStatusDescActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnUrutStatusDescActionPerformed
        order="reg_periksa.stts desc";
        tampil();
    }//GEN-LAST:event_MnUrutStatusDescActionPerformed

    private void MnUrutStatusAscActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnUrutStatusAscActionPerformed
        order="reg_periksa.stts asc";
        tampil();
    }//GEN-LAST:event_MnUrutStatusAscActionPerformed

    private void MnCetakSuratSakit3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnCetakSuratSakit3ActionPerformed
        pilihan=3;
        DlgSakit.setSize(550,121);
        DlgSakit.setLocationRelativeTo(internalFrame1);
        DlgSakit.setVisible(true);
    }//GEN-LAST:event_MnCetakSuratSakit3ActionPerformed

    private void MnJKRAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnJKRAActionPerformed
        if(TPasien.getText().trim().equals("")){
            JOptionPane.showMessageDialog(null,"Maaf, Silahkan anda pilih dulu pasien...!!!");
        }else{
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Map<String, Object> param = new HashMap<>();
            param.put("namars",var.getnamars());
            param.put("alamatrs",var.getalamatrs());
            param.put("kotars",var.getkabupatenrs());
            param.put("propinsirs",var.getpropinsirs());
            param.put("kontakrs",var.getkontakrs());
            param.put("emailrs",var.getemailrs());
            param.put("perujuk",Sequel.cariIsi("select perujuk from rujuk_masuk where no_rawat=?",TNoRw.getText()));
            param.put("no_rawat",TNoRw.getText());
            param.put("petugas",Sequel.cariIsi("select nama from petugas where nip=?",var.getkode()));
            param.put("logo",Sequel.cariGambar("select logo from setting"));
            Valid.MyReport("rptJkra.jrxml",param,"::[ SURAT JAMINAN KESEHATAN NASIONAL (JKRA) RAWAT JALAN ]::");
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnJKRAActionPerformed

    private void MnCetakRegister1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnCetakRegister1ActionPerformed
        if(TPasien.getText().trim().equals("")){
            JOptionPane.showMessageDialog(null,"Maaf, Silahkan anda pilih dulu pasien...!!!");
        }else{
             this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Map<String, Object> param = new HashMap<>();
            param.put("namars",var.getnamars());
            param.put("alamatrs",var.getalamatrs());
            param.put("kotars",var.getkabupatenrs());
            param.put("propinsirs",var.getpropinsirs());
            param.put("kontakrs",var.getkontakrs());
            param.put("emailrs",var.getemailrs());
            param.put("norawat",TNoRw.getText());
            param.put("logo",Sequel.cariGambar("select logo from setting"));
            Valid.MyReport("rptBuktiRegister3.jrxml",param,"::[ Bukti Register ]::");
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnCetakRegister1ActionPerformed

    private void MnPoliInternalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnPoliInternalActionPerformed
        if(tabMode.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Maaf, table masih kosong...!!!!");
            TNoReg.requestFocus();
        }else if(TPasien.getText().trim().equals("")){
            JOptionPane.showMessageDialog(null,"Maaf, Silahkan anda pilih dulu dengan menklik data pada table...!!!");
            tbPetugas.requestFocus();
        }else{            
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            dlgrjk.setLocationRelativeTo(internalFrame1);
            dlgrjk.isCek();
            dlgrjk.setNoRm(TNoRw.getText(),TNoRM.getText(),TPasien.getText(),internalFrame1.getWidth(),internalFrame1.getHeight()); 
            //dlgrjk.setNoRm2(TNoRw.getText(),TNoRM.getText(),TPasien.getText()); 
            dlgrjk.setVisible(true);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnPoliInternalActionPerformed

    private void TabRawatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabRawatMouseClicked
        if(TabRawat.getSelectedIndex()==0){
            tampil();
        }else if(TabRawat.getSelectedIndex()==1){
            tampil2();
        }else if(TabRawat.getSelectedIndex()==2){
            tampil3();
        }
        
        
    }//GEN-LAST:event_TabRawatMouseClicked

    private void tbPetugas2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbPetugas2MouseClicked
        TPasien.setText(tbPetugas2.getValueAt(tbPetugas2.getSelectedRow(),7).toString());
        if(tabMode2.getRowCount()!=0){
            if(evt.getClickCount()==1){
                i=tbPetugas2.getSelectedColumn();
                if(i==7){
                    if(validasicatatan.equals("Yes")){
                        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                        LabelCatatan.setText(Sequel.cariIsi("select catatan from catatan_pasien where no_rkm_medis=?",tbPetugas2.getValueAt(tbPetugas2.getSelectedRow(),6).toString()));
                        if(!LabelCatatan.getText().equals("")){
                            DlgCatatan.setLocationRelativeTo(TabRawat);
                            DlgCatatan.setVisible(true);
                        }else{
                            DlgCatatan.setVisible(false);
                        }                            
                        this.setCursor(Cursor.getDefaultCursor());
                    }  
                }                
            }else if(evt.getClickCount()==2){
                i=tbPetugas2.getSelectedColumn();
                if(i==1){
                    if(MnKamarInap1.isEnabled()==true){
                        MnKamarInap1ActionPerformed(null);
                    }                    
                }else if(i==2){
                    if(var.gettindakan_ralan()==true){
                        MnRawatJalan1ActionPerformed(null);
                    }                    
                }else if(i==3){
                    if(var.getberi_obat()==true){
                        MnPemberianObat1ActionPerformed(null);
                    }                   
                }else if(i==4){
                    if(var.getperiksa_lab()==true){
                        MnPeriksaLab1ActionPerformed(null);
                    }                    
                }
                
            }
            
        }
    }//GEN-LAST:event_tbPetugas2MouseClicked

    private void tbPetugas2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbPetugas2KeyPressed
        if(tabMode2.getRowCount()!=0){
            if(evt.getKeyCode()==KeyEvent.VK_SPACE){
                i=tbPetugas2.getSelectedColumn();
                if(i==1){
                    if(MnKamarInap1.isEnabled()==true){
                        MnKamarInap1ActionPerformed(null);
                    }                    
                }else if(i==2){
                    if(var.gettindakan_ralan()==true){
                        MnRawatJalan1ActionPerformed(null);
                    }                    
                }else if(i==3){
                    if(var.getberi_obat()==true){
                        MnPemberianObat1ActionPerformed(null);
                    }                   
                }else if(i==4){
                    if(var.getperiksa_lab()==true){
                        MnPeriksaLab1ActionPerformed(null);
                    }                    
                }
            }else if(evt.getKeyCode()==KeyEvent.VK_A){                
                for(i=0;i<tbPetugas2.getRowCount();i++){ 
                    tbPetugas2.setValueAt(true,i,0);
                }
            }
        }
    }//GEN-LAST:event_tbPetugas2KeyPressed

    private void MnKamarInap1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnKamarInap1ActionPerformed
        if(tabMode2.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Maaf, table masih kosong...!!!!");
            TNoReg.requestFocus();
        }else{
            if(tbPetugas2.getSelectedRow()!= -1){
                if(Sequel.cariRegistrasi(tbPetugas2.getValueAt(tbPetugas2.getSelectedRow(),1).toString())>0){
                    JOptionPane.showMessageDialog(rootPane,"Data billing sudah terverifikasi..!!");
                }else{ 
                    this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                    var.setstatus(true);
                    DlgKamarInap dlgki=new DlgKamarInap(null,false);
                    dlgki.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
                    dlgki.setLocationRelativeTo(internalFrame1);
                    dlgki.emptTeks();
                    dlgki.isCek();
                    dlgki.setNoRm(tbPetugas2.getValueAt(tbPetugas2.getSelectedRow(),1).toString());   
                    dlgki.setVisible(true);
                    this.setCursor(Cursor.getDefaultCursor());
                }                   
            }                
        } 
    }//GEN-LAST:event_MnKamarInap1ActionPerformed

    private void MnHapusRujukanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnHapusRujukanActionPerformed
        for(i=0;i<tbPetugas2.getRowCount();i++){ 
            if(tbPetugas2.getValueAt(i,0).toString().equals("true")){
                Sequel.queryu2("delete from rujukan_internal_poli where no_rawat=? and kd_dokter=?",2,new String[]{
                    tbPetugas2.getValueAt(i,1).toString(),tbPetugas2.getValueAt(i,4).toString()
                });
                //hapus ditabel detail interal poli
                Sequel.queryu2("delete from rujukan_internal_poli_detail where no_rawat=? ",1,new String[]{
                    tbPetugas2.getValueAt(i,1).toString()
                });
            }
        } 
        tampil2();
    }//GEN-LAST:event_MnHapusRujukanActionPerformed

    private void MnRawatJalan1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnRawatJalan1ActionPerformed
        if(tabMode2.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Maaf, table masih kosong...!!!!");
            TNoReg.requestFocus();
        }else{
            if(tbPetugas2.getSelectedRow()!= -1){
                if(Sequel.cariInteger("select count(no_rawat) from kamar_inap where no_rawat=?",tbPetugas2.getValueAt(tbPetugas2.getSelectedRow(),1).toString())>0){
                            JOptionPane.showMessageDialog(null,"Maaf, Pasien sudah masuk Kamar Inap. Gunakan billing Ranap..!!!");
                }else {
                    this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                    DlgRawatJalan dlgrwjl=new DlgRawatJalan(null,false);
                    dlgrwjl.isCek();
                    dlgrwjl.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
                    dlgrwjl.setLocationRelativeTo(internalFrame1);
                    dlgrwjl.SetPoli(tbPetugas2.getValueAt(tbPetugas2.getSelectedRow(),18).toString());
                    dlgrwjl.SetPj(tbPetugas2.getValueAt(tbPetugas2.getSelectedRow(),19).toString());
                    dlgrwjl.setNoRm(tbPetugas2.getValueAt(tbPetugas2.getSelectedRow(),1).toString(),
                        DTPCari1.getDate(),DTPCari2.getDate(),
                        tbPetugas2.getValueAt(tbPetugas2.getSelectedRow(),4).toString(),
                        tbPetugas2.getValueAt(tbPetugas2.getSelectedRow(),5).toString()
                    );   
                    dlgrwjl.setVisible(true);
                    this.setCursor(Cursor.getDefaultCursor());
                }  
            }
        }
    }//GEN-LAST:event_MnRawatJalan1ActionPerformed

    private void MnPeriksaLab1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnPeriksaLab1ActionPerformed
        if(tabMode2.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Maaf, table masih kosong...!!!!");
            TNoReg.requestFocus();
        }else{
            if(tbPetugas2.getSelectedRow()!= -1){
                if(Sequel.cariInteger("select count(no_rawat) from kamar_inap where no_rawat=?",tbPetugas2.getValueAt(tbPetugas2.getSelectedRow(),1).toString())>0){
                    JOptionPane.showMessageDialog(null,"Maaf, Pasien sudah masuk Kamar Inap. Gunakan billing Ranap..!!!");
                }else {
                    this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                    DlgPeriksaLaboratorium dlgro=new DlgPeriksaLaboratorium(null,false);
                    dlgro.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
                    dlgro.setLocationRelativeTo(internalFrame1);
                    dlgro.emptTeks();
                    dlgro.isCek();
                    dlgro.setNoRm(tbPetugas2.getValueAt(tbPetugas2.getSelectedRow(),1).toString(),"Ralan");  
                    dlgro.setDokterPerujuk(
                        tbPetugas2.getValueAt(tbPetugas2.getSelectedRow(),4).toString(),
                        tbPetugas2.getValueAt(tbPetugas2.getSelectedRow(),5).toString()
                    );
                    dlgro.setVisible(true);
                    this.setCursor(Cursor.getDefaultCursor());
                }
            }
        }
    }//GEN-LAST:event_MnPeriksaLab1ActionPerformed

    private void MnPeriksaRadiologi1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnPeriksaRadiologi1ActionPerformed
        if(tabMode2.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Maaf, table masih kosong...!!!!");
            TNoReg.requestFocus();
        }else{
            if(tbPetugas2.getSelectedRow()!= -1){
                if(Sequel.cariInteger("select count(no_rawat) from kamar_inap where no_rawat=?",tbPetugas2.getValueAt(tbPetugas2.getSelectedRow(),1).toString())>0){
                    JOptionPane.showMessageDialog(null,"Maaf, Pasien sudah masuk Kamar Inap. Gunakan billing Ranap..!!!");
                }else {
                    this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                    DlgPeriksaRadiologi dlgro=new DlgPeriksaRadiologi(null,false);
                    dlgro.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
                    dlgro.setLocationRelativeTo(internalFrame1);
                    dlgro.emptTeks();
                    dlgro.isCek();
                    dlgro.setNoRm(tbPetugas2.getValueAt(tbPetugas2.getSelectedRow(),1).toString(),"Ralan");  
                    dlgro.setDokterPerujuk(
                        tbPetugas2.getValueAt(tbPetugas2.getSelectedRow(),4).toString(),
                        tbPetugas2.getValueAt(tbPetugas2.getSelectedRow(),5).toString()
                    );
                    dlgro.tampil();
                    dlgro.setVisible(true);
                    this.setCursor(Cursor.getDefaultCursor());
                }
            }
        }
    }//GEN-LAST:event_MnPeriksaRadiologi1ActionPerformed

    private void MnOperasi1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnOperasi1ActionPerformed
        if(tabMode2.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Maaf, table masih kosong...!!!!");
            TNoReg.requestFocus();
        }else{
            if(tbPetugas2.getSelectedRow()!= -1){
                if(Sequel.cariInteger("select count(no_rawat) from kamar_inap where no_rawat=?",tbPetugas2.getValueAt(tbPetugas2.getSelectedRow(),1).toString())>0){
                    JOptionPane.showMessageDialog(null,"Maaf, Pasien sudah masuk Kamar Inap. Gunakan billing Ranap..!!!");
                }else {
                    this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                    DlgTagihanOperasi dlgro=new DlgTagihanOperasi(null,false);
                    dlgro.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
                    dlgro.setLocationRelativeTo(internalFrame1);
                    dlgro.setNoRm(tbPetugas2.getValueAt(tbPetugas2.getSelectedRow(),1).toString(),tbPetugas2.getValueAt(tbPetugas2.getSelectedRow(),6).toString()+", "+tbPetugas2.getValueAt(tbPetugas2.getSelectedRow(),7).toString(),"Ralan");
                    dlgro.setVisible(true);
                    this.setCursor(Cursor.getDefaultCursor());                    
                }
            }
        }
    }//GEN-LAST:event_MnOperasi1ActionPerformed

    private void MnPemberianObat1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnPemberianObat1ActionPerformed
        if(tabMode2.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Maaf, table masih kosong...!!!!");
            TNoReg.requestFocus();
        }else{
            if(tbPetugas2.getSelectedRow()!= -1){
                if(Sequel.cariInteger("select count(no_rawat) from kamar_inap where no_rawat=?",tbPetugas2.getValueAt(tbPetugas2.getSelectedRow(),1).toString())>0){
                    JOptionPane.showMessageDialog(null,"Maaf, Pasien sudah masuk Kamar Inap. Gunakan billing Ranap..!!!");
                }else {
                    this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                    DlgPemberianObat dlgrwinap=new DlgPemberianObat(null,false);
                    dlgrwinap.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
                    dlgrwinap.setLocationRelativeTo(internalFrame1);
                    dlgrwinap.isCek();
                    dlgrwinap.setNoRm(tbPetugas2.getValueAt(tbPetugas2.getSelectedRow(),1).toString(),DTPCari1.getDate(),DTPCari2.getDate(),"ralan");
                    dlgrwinap.setDokter(
                        tbPetugas2.getValueAt(tbPetugas2.getSelectedRow(),4).toString(),
                        tbPetugas2.getValueAt(tbPetugas2.getSelectedRow(),5).toString()
                    );
                    dlgrwinap.tampilPO();
                    dlgrwinap.setVisible(true);
                    this.setCursor(Cursor.getDefaultCursor());
                }
            }
        }
    }//GEN-LAST:event_MnPemberianObat1ActionPerformed

    private void MnResepDOkter1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnResepDOkter1ActionPerformed
        if(tabMode2.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Maaf, table masih kosong...!!!!");
            TNoReg.requestFocus();
        }else{
            if(tbPetugas2.getSelectedRow()!= -1){
                if(Sequel.cariInteger("select count(no_rawat) from kamar_inap where no_rawat=?",tbPetugas2.getValueAt(tbPetugas2.getSelectedRow(),1).toString())>0){
                    JOptionPane.showMessageDialog(null,"Maaf, Pasien sudah masuk Kamar Inap. Gunakan billing Ranap..!!!");
                }else {
                    this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                    DlgPeresepanDokter resep=new DlgPeresepanDokter(null,false);
                    resep.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
                    resep.setLocationRelativeTo(internalFrame1);
                    resep.setNoRm(tbPetugas2.getValueAt(tbPetugas2.getSelectedRow(),1).toString(),
                        DTPCari1.getDate(),CmbJam.getSelectedItem().toString(),CmbMenit.getSelectedItem().toString(),CmbDetik.getSelectedItem().toString(),
                        tbPetugas2.getValueAt(tbPetugas2.getSelectedRow(),4).toString(),
                        tbPetugas2.getValueAt(tbPetugas2.getSelectedRow(),5).toString(),"ralan"
                    );
                    resep.isCek();
                    resep.tampilobat();
                    resep.setVisible(true);
                    this.setCursor(Cursor.getDefaultCursor());
                }
            }
        }
    }//GEN-LAST:event_MnResepDOkter1ActionPerformed

    private void MnNoResep1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnNoResep1ActionPerformed
        if(tabMode2.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Maaf, table masih kosong...!!!!");
            TNoReg.requestFocus();
        }else{
            if(tbPetugas2.getSelectedRow()!= -1){
                if(Sequel.cariInteger("select count(no_rawat) from kamar_inap where no_rawat=?",tbPetugas2.getValueAt(tbPetugas2.getSelectedRow(),1).toString())>0){
                    JOptionPane.showMessageDialog(null,"Maaf, Pasien sudah masuk Kamar Inap. Gunakan billing Ranap..!!!");
                }else {
                    this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                    DlgResepObat resep=new DlgResepObat(null,false);
                    resep.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
                    resep.setLocationRelativeTo(internalFrame1);
                    resep.emptTeks();
                    resep.isCek();
                    resep.setNoRm(tbPetugas2.getValueAt(tbPetugas2.getSelectedRow(),1).toString(),
                        DTPCari1.getDate(),DTPCari2.getDate(),CmbJam.getSelectedItem().toString(),
                        CmbMenit.getSelectedItem().toString(),CmbDetik.getSelectedItem().toString(),
                        tbPetugas2.getValueAt(tbPetugas2.getSelectedRow(),4).toString(),
                        tbPetugas2.getValueAt(tbPetugas2.getSelectedRow(),5).toString(),"ralan"
                    );
                    resep.tampil();
                    resep.setVisible(true);
                    this.setCursor(Cursor.getDefaultCursor());
                }
            }
        }
    }//GEN-LAST:event_MnNoResep1ActionPerformed

    private void MnBilling1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnBilling1ActionPerformed
        if(tabMode2.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Maaf, table masih kosong...!!!!");
            TNoReg.requestFocus();
        }else{
            if(tbPetugas2.getSelectedRow()!= -1){
                if(Sequel.cariInteger("select count(no_rawat) from kamar_inap where no_rawat=?",tbPetugas2.getValueAt(tbPetugas2.getSelectedRow(),1).toString())>0){
                    JOptionPane.showMessageDialog(null,"Maaf, Pasien sudah masuk Kamar Inap. Gunakan billing Ranap..!!!");
                }else {
                    try {
                        pscaripiutang=koneksi.prepareStatement("select tgl_piutang from piutang_pasien where no_rkm_medis=? and status='Belum Lunas' order by tgl_piutang asc limit 1");
                        try {
                            pscaripiutang.setString(1,tbPetugas2.getValueAt(tbPetugas2.getSelectedRow(),6).toString());
                            rs=pscaripiutang.executeQuery();
                            if(rs.next()){
                                i=JOptionPane.showConfirmDialog(null, "Masih ada tunggakan pembayaran, apa mau bayar sekarang ?","Konfirmasi",JOptionPane.YES_NO_OPTION);
                                if(i==JOptionPane.YES_OPTION){
                                    this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                                     DlgLhtPiutang piutang=new DlgLhtPiutang(null,false);
                                     piutang.setNoRm(tbPetugas2.getValueAt(tbPetugas2.getSelectedRow(),6).toString(),rs.getDate(1));
                                     piutang.tampil();
                                     piutang.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
                                     piutang.setLocationRelativeTo(internalFrame1);
                                     piutang.setVisible(true);
                                     this.setCursor(Cursor.getDefaultCursor());
                                }else{
                                    this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                                    DlgBilingRalan dlgbil=new DlgBilingRalan(null,false);
                                    dlgbil.TNoRw.setText(tbPetugas2.getValueAt(tbPetugas2.getSelectedRow(),1).toString());
                                    dlgbil.isCek();
                                    dlgbil.isRawat(); 
                                    dlgbil.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
                                    dlgbil.setLocationRelativeTo(internalFrame1);
                                    dlgbil.setVisible(true);
                                    this.setCursor(Cursor.getDefaultCursor());
                                }
                            }else{ 
                                this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                                DlgBilingRalan dlgbil=new DlgBilingRalan(null,false);
                                dlgbil.TNoRw.setText(tbPetugas2.getValueAt(tbPetugas2.getSelectedRow(),1).toString()); 
                                dlgbil.isCek();
                                dlgbil.isRawat(); 
                                dlgbil.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
                                dlgbil.setLocationRelativeTo(internalFrame1);
                                dlgbil.setVisible(true);
                                this.setCursor(Cursor.getDefaultCursor());
                            }
                        } catch (Exception e) {
                            System.out.println(e);
                        }finally{
                            if( rs != null){
                                rs.close();
                            }

                            if( pscaripiutang != null){
                                pscaripiutang.close();
                            }
                        }
                    } catch (Exception e) {
                        System.out.println(e);
                    }     
                }
            }
        }
    }//GEN-LAST:event_MnBilling1ActionPerformed

    private void MnDiagnosa1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnDiagnosa1ActionPerformed
        if(tabMode2.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Maaf, table masih kosong...!!!!");
            TNoReg.requestFocus();
        }else{
            if(tbPetugas2.getSelectedRow()!= -1){
                if(Sequel.cariInteger("select count(no_rawat) from kamar_inap where no_rawat=?",tbPetugas2.getValueAt(tbPetugas2.getSelectedRow(),1).toString())>0){
                    JOptionPane.showMessageDialog(null,"Maaf, Pasien sudah masuk Kamar Inap. Gunakan billing Ranap..!!!");
                }else {
                    this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                    DlgDiagnosaPenyakit resep=new DlgDiagnosaPenyakit(null,false);
                    resep.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
                    resep.setLocationRelativeTo(internalFrame1);
                    resep.isCek();
                    resep.setNoRm(tbPetugas2.getValueAt(tbPetugas2.getSelectedRow(),1).toString(),
                        DTPCari1.getDate(),DTPCari2.getDate(),"Ralan"
                    );
                    resep.panelDiagnosa1.tampil();
                    resep.setVisible(true);
                    this.setCursor(Cursor.getDefaultCursor());
                }
            }
        }
    }//GEN-LAST:event_MnDiagnosa1ActionPerformed

    private void ppRiwayat1BtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppRiwayat1BtnPrintActionPerformed
        if(tabMode2.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Maaf, table masih kosong...!!!!");
            TNoReg.requestFocus();
        }else{
            if(tbPetugas2.getSelectedRow()!= -1){               
                this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                DlgResumePerawatan resume=new DlgResumePerawatan(null,true);
                resume.setNoRm(tbPetugas2.getValueAt(tbPetugas2.getSelectedRow(),6).toString(),
                    tbPetugas2.getValueAt(tbPetugas2.getSelectedRow(),7).toString()
                );
                resume.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
                resume.setLocationRelativeTo(internalFrame1);
                resume.setVisible(true);
                this.setCursor(Cursor.getDefaultCursor());                
            }
        }
    }//GEN-LAST:event_ppRiwayat1BtnPrintActionPerformed

    private void MnUrutNoRawatDesc1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnUrutNoRawatDesc1ActionPerformed
        order="reg_periksa.no_rawat desc";
        tampil2();
    }//GEN-LAST:event_MnUrutNoRawatDesc1ActionPerformed

    private void MnUrutNoRawatAsc1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnUrutNoRawatAsc1ActionPerformed
        order="reg_periksa.no_rawat asc";
        tampil2();
    }//GEN-LAST:event_MnUrutNoRawatAsc1ActionPerformed

    private void MnUrutTanggalDesc1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnUrutTanggalDesc1ActionPerformed
        order="reg_periksa.tgl_registrasi,reg_periksa.jam_reg desc";
        tampil2();
    }//GEN-LAST:event_MnUrutTanggalDesc1ActionPerformed

    private void MnUrutTanggalAsc1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnUrutTanggalAsc1ActionPerformed
        order="reg_periksa.tgl_registrasi,reg_periksa.jam_reg asc";
        tampil2();
    }//GEN-LAST:event_MnUrutTanggalAsc1ActionPerformed

    private void MnUrutDokterDesc1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnUrutDokterDesc1ActionPerformed
        order="dokter.nm_dokter desc";
        tampil2();
    }//GEN-LAST:event_MnUrutDokterDesc1ActionPerformed

    private void MnUrutDokterAsc1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnUrutDokterAsc1ActionPerformed
        order="dokter.nm_dokter asc";
        tampil2();
    }//GEN-LAST:event_MnUrutDokterAsc1ActionPerformed

    private void MnUrutPoliklinikDesc1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnUrutPoliklinikDesc1ActionPerformed
        order="poliklinik.nm_poli desc";
        tampil2();
    }//GEN-LAST:event_MnUrutPoliklinikDesc1ActionPerformed

    private void MnUrutPoliklinikAsc1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnUrutPoliklinikAsc1ActionPerformed
        order="poliklinik.nm_poli asc";
        tampil2();
    }//GEN-LAST:event_MnUrutPoliklinikAsc1ActionPerformed

    private void MnUrutPenjabDesc1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnUrutPenjabDesc1ActionPerformed
        order="penjab.png_jawab desc";
        tampil2();
    }//GEN-LAST:event_MnUrutPenjabDesc1ActionPerformed

    private void MnUrutPenjabAsc1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnUrutPenjabAsc1ActionPerformed
        order="penjab.png_jawab asc";
        tampil2();
    }//GEN-LAST:event_MnUrutPenjabAsc1ActionPerformed

    private void MnUrutStatusDesc1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnUrutStatusDesc1ActionPerformed
        order="reg_periksa.stts desc";
        tampil2();
    }//GEN-LAST:event_MnUrutStatusDesc1ActionPerformed

    private void MnUrutStatusAsc1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnUrutStatusAsc1ActionPerformed
        order="reg_periksa.stts asc";
        tampil2();
    }//GEN-LAST:event_MnUrutStatusAsc1ActionPerformed

    private void MnCetakSuratSehat1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnCetakSuratSehat1ActionPerformed
        if(TPasien.getText().trim().equals("")){
            JOptionPane.showMessageDialog(null,"Maaf, Silahkan anda pilih dulu pasien...!!!");
        }else{
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Map<String, Object> param = new HashMap<>();
            param.put("namars",var.getnamars());
            param.put("alamatrs",var.getalamatrs());
            param.put("kotars",var.getkabupatenrs());
            param.put("propinsirs",var.getpropinsirs());
            param.put("kontakrs",var.getkontakrs());
            param.put("emailrs",var.getemailrs());
            param.put("norawat",TNoRw.getText());
            param.put("bb",Sequel.cariIsi("select berat from pemeriksaan_ralan where no_rawat=?",TNoRw.getText()));
            param.put("td",Sequel.cariIsi("select tensi from pemeriksaan_ralan where no_rawat=?",TNoRw.getText()));
            param.put("tb",Sequel.cariIsi("select tinggi from pemeriksaan_ralan where no_rawat=?",TNoRw.getText()));
            param.put("logo",Sequel.cariGambar("select logo from setting"));
            Valid.MyReport("rptSuratSehat2.jrxml",param,"::[ Surat Keterangan Sehat ]::");
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnCetakSuratSehat1ActionPerformed

    private void MnUrutRegDesc1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnUrutRegDesc1ActionPerformed
        order="reg_periksa.no_reg desc";
        tampil();
    }//GEN-LAST:event_MnUrutRegDesc1ActionPerformed

    private void MnUrutRegAsc1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnUrutRegAsc1ActionPerformed
        order="reg_periksa.no_reg asc";
        tampil();
    }//GEN-LAST:event_MnUrutRegAsc1ActionPerformed

    private void LabelCatatanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LabelCatatanMouseClicked
        DlgCatatan.dispose();
    }//GEN-LAST:event_LabelCatatanMouseClicked

    private void internalFrame6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_internalFrame6MouseClicked
        DlgCatatan.dispose();
    }//GEN-LAST:event_internalFrame6MouseClicked

    private void MnLembarCasemix1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnLembarCasemix1ActionPerformed
        if(TPasien.getText().trim().equals("")){
            JOptionPane.showMessageDialog(null,"Maaf, Silahkan anda pilih dulu pasien...!!!");
        }else{
             this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Map<String, Object> param = new HashMap<>();
            param.put("namars",var.getnamars());
            param.put("alamatrs",var.getalamatrs());
            param.put("kotars",var.getkabupatenrs());
            param.put("propinsirs",var.getpropinsirs());
            param.put("kontakrs",var.getkontakrs());
            param.put("emailrs",var.getemailrs());
            param.put("logo",Sequel.cariGambar("select logo from setting"));
            Valid.MyReport("rptLembarCasemix2.jrxml","report","::[ Surat Jaminan & Bukti Pelayanan Rawat Jalan ]::",
                   "select reg_periksa.no_reg,reg_periksa.no_rawat,reg_periksa.tgl_registrasi,"+
                   "reg_periksa.jam_reg, reg_periksa.kd_dokter,dokter.nm_dokter,reg_periksa.no_rkm_medis,pasien.nm_pasien,pasien.jk,concat(reg_periksa.umurdaftar,' ',reg_periksa.sttsumur)as umur,poliklinik.nm_poli," +
                   "reg_periksa.p_jawab,reg_periksa.almt_pj,reg_periksa.hubunganpj,reg_periksa.biaya_reg," +
                   "reg_periksa.stts_daftar,penjab.png_jawab,pasien.no_peserta,pasien.tgl_lahir " +
                   "from reg_periksa inner join dokter inner join pasien inner join poliklinik inner join penjab " +
                   "on reg_periksa.kd_dokter=dokter.kd_dokter and reg_periksa.no_rkm_medis=pasien.no_rkm_medis and reg_periksa.kd_pj=penjab.kd_pj and reg_periksa.kd_poli=poliklinik.kd_poli "+
                   "where reg_periksa.no_rawat='"+TNoRw.getText()+"' ",param);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnLembarCasemix1ActionPerformed

    private void MnBarcode1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnBarcode1ActionPerformed
        if(!TPasien.getText().equals("")){
                this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                Map<String, Object> param = new HashMap<>();
                param.put("nama",TPasien.getText());
                param.put("alamat",Sequel.cariIsi("select date_format(tgl_lahir,'%d/%m/%Y') from pasien where no_rkm_medis=?",TNoRM.getText()));
                param.put("norm",TNoRM.getText());
                param.put("parameter","%"+TCari.getText().trim()+"%");     
                param.put("namars",var.getnamars());
                param.put("alamatrs",var.getalamatrs());
                param.put("kotars",var.getkabupatenrs());
                param.put("propinsirs",var.getpropinsirs());
                param.put("kontakrs",var.getkontakrs());
                param.put("emailrs",var.getemailrs());   
                Valid.MyReport("rptBarcodeRawat2.jrxml","report","::[ Barcode No.Rawat ]::",
                        "select reg_periksa.no_rawat from reg_periksa where no_rawat='"+TNoRw.getText()+"'",param); 
                this.setCursor(Cursor.getDefaultCursor());
            } 
    }//GEN-LAST:event_MnBarcode1ActionPerformed

    private void MnLembarCasemix2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnLembarCasemix2ActionPerformed
        if(TPasien.getText().trim().equals("")){
            JOptionPane.showMessageDialog(null,"Maaf, Silahkan anda pilih dulu pasien...!!!");
        }else{
             this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Map<String, Object> param = new HashMap<>();
            param.put("namars",var.getnamars());
            param.put("alamatrs",var.getalamatrs());
            param.put("kotars",var.getkabupatenrs());
            param.put("propinsirs",var.getpropinsirs());
            param.put("kontakrs",var.getkontakrs());
            param.put("emailrs",var.getemailrs());
            param.put("logo",Sequel.cariGambar("select logo from setting"));
            Valid.MyReport("rptLembarCasemix3.jrxml","report","::[ Surat Jaminan & Bukti Pelayanan Rawat Jalan ]::",
                   "select reg_periksa.no_reg,reg_periksa.no_rawat,reg_periksa.tgl_registrasi,"+
                   "reg_periksa.jam_reg, reg_periksa.kd_dokter,dokter.nm_dokter,reg_periksa.no_rkm_medis,pasien.nm_pasien,pasien.jk,concat(reg_periksa.umurdaftar,' ',reg_periksa.sttsumur)as umur,poliklinik.nm_poli," +
                   "reg_periksa.p_jawab,reg_periksa.almt_pj,reg_periksa.hubunganpj,reg_periksa.biaya_reg," +
                   "reg_periksa.stts_daftar,penjab.png_jawab,pasien.no_peserta,pasien.tgl_lahir " +
                   "from reg_periksa inner join dokter inner join pasien inner join poliklinik inner join penjab " +
                   "on reg_periksa.kd_dokter=dokter.kd_dokter and reg_periksa.no_rkm_medis=pasien.no_rkm_medis and reg_periksa.kd_pj=penjab.kd_pj and reg_periksa.kd_poli=poliklinik.kd_poli "+
                   "where reg_periksa.no_rawat='"+TNoRw.getText()+"' ",param);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnLembarCasemix2ActionPerformed

    private void MnBarcodeRM9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnBarcodeRM9ActionPerformed
        if(tabMode.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Maaf, data pasien sudah habis...!!!!");
            TNoRM.requestFocus();
        }else if(TPasien.getText().trim().equals("")){
            JOptionPane.showMessageDialog(null,"Maaf, Silahkan anda pilih dulu data pasien dengan menklik data pada table...!!!");
            tbPetugas.requestFocus();
        }else{
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Map<String, Object> param = new HashMap<>();
            param.put("namars",var.getnamars());
            param.put("alamatrs",var.getalamatrs());
            param.put("kotars",var.getkabupatenrs());
            param.put("propinsirs",var.getpropinsirs());
            param.put("kontakrs",var.getkontakrs());
            param.put("emailrs",var.getemailrs());
            param.put("logo",Sequel.cariGambar("select logo from setting"));
            Valid.MyReport("rptBarcodeRM18.jrxml","report","::[ Label Rekam Medis ]::","select pasien.no_rkm_medis, pasien.nm_pasien, pasien.no_ktp, pasien.jk, "+
                "pasien.tmp_lahir, pasien.tgl_lahir,pasien.nm_ibu, concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab) as alamat, pasien.gol_darah, pasien.pekerjaan,"+
                "pasien.stts_nikah,pasien.agama,pasien.tgl_daftar,pasien.no_tlp,pasien.umur,"+
                "pasien.pnd, pasien.keluarga, pasien.namakeluarga,penjab.png_jawab,pasien.pekerjaanpj,"+
                "concat(pasien.alamatpj,', ',pasien.kelurahanpj,', ',pasien.kecamatanpj,', ',pasien.kabupatenpj) as alamatpj from pasien "+
                "inner join kelurahan inner join kecamatan inner join kabupaten "+
                "inner join penjab on pasien.kd_pj=penjab.kd_pj and pasien.kd_kel=kelurahan.kd_kel "+
                "and pasien.kd_kec=kecamatan.kd_kec and pasien.kd_kab=kabupaten.kd_kab  where pasien.no_rkm_medis='"+TNoRM.getText()+"' ",param);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnBarcodeRM9ActionPerformed

    private void MnLembarRalanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnLembarRalanActionPerformed
        if(tabMode.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Maaf, data pasien sudah habis...!!!!");
            TNoRM.requestFocus();
        }else if(TPasien.getText().trim().equals("")){
            JOptionPane.showMessageDialog(null,"Maaf, Silahkan anda pilih dulu data pasien dengan menklik data pada table...!!!");
            tbPetugas.requestFocus();
        }else{
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Map<String, Object> param = new HashMap<>();                 
            param.put("namars",var.getnamars());
            param.put("alamatrs",var.getalamatrs());
            param.put("kotars",var.getkabupatenrs());
            param.put("propinsirs",var.getpropinsirs());
            param.put("kontakrs",var.getkontakrs());
            param.put("emailrs",var.getemailrs());   
            param.put("logo",Sequel.cariGambar("select logo from setting")); 
            Valid.MyReport("rptRM11.jrxml","report","::[ Lembar Rawat Jalan ]::",
                    "SELECT reg_periksa.tgl_registrasi, reg_periksa.jam_reg, "+
                    "poliklinik.nm_poli, pasien.no_rkm_medis, pasien.nm_pasien, "+
                    "pasien.no_ktp, pasien.jk, pasien.tmp_lahir, pasien.tgl_lahir,"+
                    "pasien.nm_ibu, concat(pasien.alamat,', ',kelurahan.nm_kel"+
                    ",', ',kecamatan.nm_kec,', ',kabupaten.nm_kab) AS alamat,"+
                    "pasien.gol_darah,pasien.pekerjaan,pasien.stts_nikah,"+
                    "pasien.agama,pasien.tgl_daftar,pasien.no_tlp,pasien.umur,"+
                    "pasien.pnd,pasien.keluarga,pasien.namakeluarga,"+
                    "penjab.png_jawab,pasien.pekerjaanpj,concat(pasien.alamatpj,"+
                    "', ',pasien.kelurahanpj,', ',pasien.kecamatanpj,', ',"+
                    "pasien.kabupatenpj) AS alamatpj FROM pasien INNER JOIN "+
                    "kelurahan INNER JOIN kecamatan INNER JOIN kabupaten "+
                    "INNER JOIN penjab ON pasien.kd_kel = kelurahan.kd_kel "+
                    "AND pasien.kd_kec = kecamatan.kd_kec AND pasien.kd_kab = kabupaten.kd_kab "+
                    "INNER JOIN reg_periksa ON reg_periksa.no_rkm_medis = pasien.no_rkm_medis "+
                    "AND reg_periksa.kd_pj = penjab.kd_pj INNER JOIN poliklinik "+
                    "ON poliklinik.kd_poli = reg_periksa.kd_poli WHERE "+
                    "reg_periksa.no_rawat ='"+TNoRw.getText()+"' ",param);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnLembarRalanActionPerformed

    private void MnSPBK1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnSPBK1ActionPerformed
        if(TPasien.getText().trim().equals("")){
            JOptionPane.showMessageDialog(null,"Maaf, Silahkan anda pilih dulu pasien...!!!");
        }else{
             this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Map<String, Object> param = new HashMap<>();
            param.put("namars",var.getnamars());
            param.put("alamatrs",var.getalamatrs());
            param.put("kotars",var.getkabupatenrs());
            param.put("propinsirs",var.getpropinsirs());
            param.put("kontakrs",var.getkontakrs());
            param.put("emailrs",var.getemailrs());
            param.put("logo",Sequel.cariGambar("select logo from setting"));
            param.put("logo2",Sequel.cariGambar("select logo from setting"));
            Valid.MyReport("rptSBPK2.jrxml","report","::[ Surat Bukti Pelayanan Kesehatan ]::",
                    "select reg_periksa.no_reg,reg_periksa.no_rawat,reg_periksa.tgl_registrasi,"+
                    "reg_periksa.jam_reg, reg_periksa.kd_dokter,dokter.nm_dokter,"+
                    "reg_periksa.no_rkm_medis,pasien.nm_pasien,pasien.jk,pasien.umur,"+
                    "pasien.no_tlp,poliklinik.nm_poli,reg_periksa.p_jawab,"+
                    "reg_periksa.almt_pj,reg_periksa.hubunganpj,reg_periksa.biaya_reg," +
                    "reg_periksa.stts_daftar,penjab.png_jawab,pasien.no_peserta,"+
                    "pasien.tgl_lahir from reg_periksa inner join dokter inner join "+
                    "pasien inner join poliklinik inner join penjab on "+
                    "reg_periksa.kd_dokter=dokter.kd_dokter and "+
                    "reg_periksa.no_rkm_medis=pasien.no_rkm_medis and "+
                    "reg_periksa.kd_pj=penjab.kd_pj and reg_periksa.kd_poli=poliklinik.kd_poli "+
                    "where reg_periksa.no_rawat='"+TNoRw.getText()+"' ",param);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnSPBK1ActionPerformed

    private void ppBerkasDigitalBtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppBerkasDigitalBtnPrintActionPerformed
        if(tabMode.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Maaf, data pasien sudah habis...!!!!");
            TNoRw.requestFocus();
        }else if(TPasien.getText().trim().equals("")){
            JOptionPane.showMessageDialog(null,"Maaf, Silahkan anda pilih dulu data registrasi pada table...!!!");
            TCari.requestFocus();
        }else{
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            DlgBerkasRawat berkas=new DlgBerkasRawat(null,true);
            berkas.setJudul("::[ Berkas Digital Perawatan ]::","berkasrawat/pages");
            try {
                berkas.loadURL("http://"+koneksiDB.HOST()+":"+prop.getProperty("PORTWEB")+"/"+prop.getProperty("HYBRIDWEB")+"/"+"berkasrawat/login2.php?act=login&usere=admin&passwordte=akusayangsamakamu&no_rawat="+TNoRw.getText());                    
            } catch (Exception ex) {
                System.out.println("Notifikasi : "+ex);
            }

            berkas.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
            berkas.setLocationRelativeTo(internalFrame1);        
            berkas.setVisible(true);        
            this.setCursor(Cursor.getDefaultCursor());
        }            
    }//GEN-LAST:event_ppBerkasDigitalBtnPrintActionPerformed

    private void ppBerkasDigital1BtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppBerkasDigital1BtnPrintActionPerformed
        if(tabMode2.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Maaf, table masih kosong...!!!!");
            TNoReg.requestFocus();
        }else{
            if(tbPetugas2.getSelectedRow()!= -1){ 
                this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                DlgBerkasRawat berkas=new DlgBerkasRawat(null,true);
                berkas.setJudul("::[ Berkas Digital Perawatan ]::","berkasrawat/pages");
                try {
                    berkas.loadURL("http://"+koneksiDB.HOST()+":"+prop.getProperty("PORTWEB")+"/"+prop.getProperty("HYBRIDWEB")+"/"+"berkasrawat/login2.php?act=login&usere=admin&passwordte=akusayangsamakamu&no_rawat="+tbPetugas2.getValueAt(tbPetugas2.getSelectedRow(),1).toString());                    
                } catch (Exception ex) {
                    System.out.println("Notifikasi : "+ex);
                }

                berkas.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
                berkas.setLocationRelativeTo(internalFrame1);        
                berkas.setVisible(true);        
                this.setCursor(Cursor.getDefaultCursor());
            }
        }                
    }//GEN-LAST:event_ppBerkasDigital1BtnPrintActionPerformed

    private void MnStatusBaruActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnStatusBaruActionPerformed
        if(TNoRw.getText().trim().equals("")){
            Valid.textKosong(TNoRw,"No.Rawat");
        }else{            
            Valid.editTable(tabMode,"reg_periksa","no_rawat",TNoRw,"status_poli='Baru'");
            if(tabMode.getRowCount()!=0){tampil();}
        }
    }//GEN-LAST:event_MnStatusBaruActionPerformed

    private void MnStatusLamaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnStatusLamaActionPerformed
        if(TNoRw.getText().trim().equals("")){
            Valid.textKosong(TNoRw,"No.Rawat");
        }else{            
            Valid.editTable(tabMode,"reg_periksa","no_rawat",TNoRw,"status_poli='Lama'");
            if(tabMode.getRowCount()!=0){tampil();}
        }
    }//GEN-LAST:event_MnStatusLamaActionPerformed

    private void ppIKPBtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppIKPBtnPrintActionPerformed
        if(tabMode.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Maaf, data pasien sudah habis...!!!!");
            TNoRw.requestFocus();
        }else if(TPasien.getText().trim().equals("")){
            JOptionPane.showMessageDialog(null,"Maaf, Silahkan anda pilih dulu data registrasi pada table...!!!");
            TCari.requestFocus();
        }else{
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            DlgDataInsidenKeselamatan aplikasi=new DlgDataInsidenKeselamatan(null,false);
            aplikasi.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
            aplikasi.setLocationRelativeTo(internalFrame1);
            aplikasi.isCek();
            aplikasi.setNoRm(TNoRw.getText(),DTPCari1.getDate(),DTPCari2.getDate(),TPoli.getText());
            aplikasi.tampil();
            aplikasi.setVisible(true);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_ppIKPBtnPrintActionPerformed

    private void ppIKP1BtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppIKP1BtnPrintActionPerformed
        if(tabMode2.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Maaf, table masih kosong...!!!!");
            TNoReg.requestFocus();
        }else{
            if(tbPetugas2.getSelectedRow()!= -1){ 
                this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                DlgDataInsidenKeselamatan aplikasi=new DlgDataInsidenKeselamatan(null,false);
                aplikasi.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
                aplikasi.setLocationRelativeTo(internalFrame1);
                aplikasi.isCek();
                aplikasi.setNoRm(tbPetugas2.getValueAt(tbPetugas2.getSelectedRow(),1).toString(),DTPCari1.getDate(),DTPCari2.getDate(),tbPetugas2.getValueAt(tbPetugas2.getSelectedRow(),10).toString());
                aplikasi.tampil();
                aplikasi.setVisible(true);
                this.setCursor(Cursor.getDefaultCursor());
            }
        }
    }//GEN-LAST:event_ppIKP1BtnPrintActionPerformed

    private void MnGelang7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnGelang7ActionPerformed
        if(tabMode.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Maaf, data registrasi sudah habis...!!!!");
            TCari.requestFocus();
        }else if(TPasien.getText().trim().equals("")){
            JOptionPane.showMessageDialog(null,"Maaf, Silahkan anda pilih dulu data registrasi pada table...!!!");
            TCari.requestFocus();
        }else{
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Map<String, Object> param = new HashMap<>();
            param.put("namars",var.getnamars());
            param.put("alamatrs",var.getalamatrs());
            param.put("kotars",var.getkabupatenrs());
            param.put("propinsirs",var.getpropinsirs());
            param.put("kontakrs",var.getkontakrs());
            param.put("emailrs",var.getemailrs());
            param.put("tanggal",DTPReg.getSelectedItem().toString());
            param.put("logo",Sequel.cariGambar("select logo from setting"));
            Valid.MyReport("rptBarcodeRM19.jrxml","report","::[ Gelang Pasien ]::","select pasien.no_rkm_medis, pasien.nm_pasien, pasien.no_ktp, pasien.jk, "+
                "pasien.tmp_lahir, pasien.tgl_lahir,pasien.nm_ibu, concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab) as alamat, pasien.gol_darah, pasien.pekerjaan,"+
                "pasien.stts_nikah,pasien.agama,pasien.tgl_daftar,pasien.no_tlp,pasien.umur,"+
                "pasien.pnd, pasien.keluarga, pasien.namakeluarga,penjab.png_jawab,pasien.pekerjaanpj,"+
                "concat(pasien.alamatpj,', ',pasien.kelurahanpj,', ',pasien.kecamatanpj,', ',pasien.kabupatenpj) as alamatpj from pasien "+
                "inner join kelurahan inner join kecamatan inner join kabupaten "+
                "inner join penjab on pasien.kd_pj=penjab.kd_pj and pasien.kd_kel=kelurahan.kd_kel "+
                "and pasien.kd_kec=kecamatan.kd_kec and pasien.kd_kab=kabupaten.kd_kab  where pasien.no_rkm_medis='"+TNoRM.getText()+"' ",param);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnGelang7ActionPerformed

    private void MnJadwalOperasiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnJadwalOperasiActionPerformed
        if(tabMode.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Maaf, table masih kosong...!!!!");
            TCari.requestFocus();
        }else{
            if(Sequel.cariInteger("select count(no_rawat) from kamar_inap where no_rawat=?",TNoRw.getText())>0){
                JOptionPane.showMessageDialog(null,"Maaf, Pasien sudah masuk Kamar Inap. Gunakan billing Ranap..!!!");
            }else {
                DlgBookingOperasi form=new DlgBookingOperasi(null,false);
                form.isCek();
                form.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
                form.setLocationRelativeTo(internalFrame1);
                form.setNoRm(TNoRw.getText(),TNoRM.getText(),TPasien.getText(),TPoli.getText(),"Ralan");
                form.setVisible(true);
            }
        }
    }//GEN-LAST:event_MnJadwalOperasiActionPerformed

    private void MnSKDPBPJSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnSKDPBPJSActionPerformed
        if(tabMode.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Maaf, table masih kosong...!!!!");
            TCari.requestFocus();
        }else{
            if(Sequel.cariInteger("select count(no_rawat) from kamar_inap where no_rawat=?",TNoRw.getText())>0){
                JOptionPane.showMessageDialog(null,"Maaf, Pasien sudah masuk Kamar Inap. Gunakan billing Ranap..!!!");
            }else {
                DlgSKDPBPJS form=new DlgSKDPBPJS(null,false);
                form.isCek();
                form.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
                form.setLocationRelativeTo(internalFrame1);  
                form.emptTeks();
                form.setNoRm(TNoRM.getText(),TPasien.getText(),kdpoli.getText(),TPoli.getText(),kddokter.getText(),TDokter.getText()); 
                form.setVisible(true);
            }                
        }
    }//GEN-LAST:event_MnSKDPBPJSActionPerformed

    private void MnPermintaanLabActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnPermintaanLabActionPerformed
        if(tabMode.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Maaf, table masih kosong...!!!!");
            TNoReg.requestFocus();
        }else if(TPasien.getText().trim().equals("")){
            JOptionPane.showMessageDialog(null,"Maaf, Silahkan anda pilih dulu dengan menklik data pada table...!!!");
            tbPetugas.requestFocus();
        }else{
            if(Sequel.cariInteger("select count(no_rawat) from kamar_inap where no_rawat=?",TNoRw.getText())>0){
                        JOptionPane.showMessageDialog(null,"Maaf, Pasien sudah masuk Kamar Inap. Gunakan billing Ranap..!!!");
            }else {
                this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                DlgPermintaanLaboratorium dlgro=new DlgPermintaanLaboratorium(null,false);
                dlgro.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
                dlgro.setLocationRelativeTo(internalFrame1);
                dlgro.emptTeks();
                dlgro.isCek();
                dlgro.setNoRm(TNoRw.getText(),"Ralan"); 
                dlgro.setVisible(true);
                this.setCursor(Cursor.getDefaultCursor());
            }                
        }
    }//GEN-LAST:event_MnPermintaanLabActionPerformed

    private void MnPermintaanRadiologiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnPermintaanRadiologiActionPerformed
        if(tabMode.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Maaf, table masih kosong...!!!!");
            TNoReg.requestFocus();
        }else if(TPasien.getText().trim().equals("")){
            JOptionPane.showMessageDialog(null,"Maaf, Silahkan anda pilih dulu dengan menklik data pada table...!!!");
            tbPetugas.requestFocus();
        }else{
            if(Sequel.cariInteger("select count(no_rawat) from kamar_inap where no_rawat=?",TNoRw.getText())>0){
                        JOptionPane.showMessageDialog(null,"Maaf, Pasien sudah masuk Kamar Inap. Gunakan billing Ranap..!!!");
            }else {
                this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                DlgPermintaanRadiologi dlgro=new DlgPermintaanRadiologi(null,false);
                dlgro.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
                dlgro.setLocationRelativeTo(internalFrame1);
                dlgro.emptTeks();
                dlgro.isCek();
                dlgro.setNoRm(TNoRw.getText(),"Ralan"); 
                dlgro.setVisible(true);
                this.setCursor(Cursor.getDefaultCursor());
            }                
        }
    }//GEN-LAST:event_MnPermintaanRadiologiActionPerformed

    private void MnPulangPaksaBtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnPulangPaksaBtnPrintActionPerformed
        if(TNoRw.getText().trim().equals("")){
            Valid.textKosong(TNoRw,"No.Rawat");
        }else{
            if(Sequel.cariInteger("select count(no_rawat) from kamar_inap where no_rawat=?",TNoRw.getText())>0){
                JOptionPane.showMessageDialog(null,"Maaf, Pasien sudah masuk Kamar Inap. Gunakan billing Ranap..!!!");
            }else {
                Valid.editTable(tabMode,"reg_periksa","no_rawat",TNoRw,"stts='Pulang Paksa'");
                if(tabMode.getRowCount()!=0){tampil();}
            }

        }
    }//GEN-LAST:event_MnPulangPaksaBtnPrintActionPerformed

    private void MnJadwalOperasi1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnJadwalOperasi1ActionPerformed
        if(tabMode2.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Maaf, table masih kosong...!!!!");
            TCari.requestFocus();
        }else{
            if(tbPetugas2.getSelectedRow()>-1){
                if(Sequel.cariInteger("select count(no_rawat) from kamar_inap where no_rawat=?",tbPetugas2.getValueAt(tbPetugas2.getSelectedRow(),1).toString())>0){
                    JOptionPane.showMessageDialog(null,"Maaf, Pasien sudah masuk Kamar Inap. Gunakan billing Ranap..!!!");
                }else {
                    this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                    DlgBookingOperasi form=new DlgBookingOperasi(null,false);
                    form.isCek();
                    form.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
                    form.setLocationRelativeTo(internalFrame1);
                    form.setNoRm(tbPetugas2.getValueAt(tbPetugas2.getSelectedRow(),1).toString(),tbPetugas2.getValueAt(tbPetugas2.getSelectedRow(),5).toString(),tbPetugas2.getValueAt(tbPetugas2.getSelectedRow(),6).toString(),tbPetugas2.getValueAt(tbPetugas2.getSelectedRow(),10).toString(),"Ralan");
                    form.setVisible(true);
                    this.setCursor(Cursor.getDefaultCursor());
                }
            }else{
                JOptionPane.showMessageDialog(null,"Maaf, Pilih pasien terlebih dahulu..!!!");
            }                
        }
    }//GEN-LAST:event_MnJadwalOperasi1ActionPerformed

    private void MnSKDPBPJS1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnSKDPBPJS1ActionPerformed
        if(tabMode2.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Maaf, table masih kosong...!!!!");
            TCari.requestFocus();
        }else{
            if(tbPetugas2.getSelectedRow()>-1){
                if(Sequel.cariInteger("select count(no_rawat) from kamar_inap where no_rawat=?",tbPetugas2.getValueAt(tbPetugas2.getSelectedRow(),1).toString())>0){
                    JOptionPane.showMessageDialog(null,"Maaf, Pasien sudah masuk Kamar Inap. Gunakan billing Ranap..!!!");
                }else {
                    this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                    DlgSKDPBPJS form=new DlgSKDPBPJS(null,false);
                    form.isCek();
                    form.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
                    form.setLocationRelativeTo(internalFrame1);  
                    form.emptTeks();
                    form.setNoRm(tbPetugas2.getValueAt(tbPetugas2.getSelectedRow(),6).toString(),tbPetugas2.getValueAt(tbPetugas2.getSelectedRow(),7).toString(),
                            tbPetugas2.getValueAt(tbPetugas2.getSelectedRow(),18).toString(),tbPetugas2.getValueAt(tbPetugas2.getSelectedRow(),10).toString(),
                            tbPetugas2.getValueAt(tbPetugas2.getSelectedRow(),4).toString(),tbPetugas2.getValueAt(tbPetugas2.getSelectedRow(),5).toString()); 
                    form.setVisible(true);
                    this.setCursor(Cursor.getDefaultCursor());
                }
            }else{
                JOptionPane.showMessageDialog(null,"Maaf, Pilih pasien terlebih dahulu..!!!");
            }
        }
    }//GEN-LAST:event_MnSKDPBPJS1ActionPerformed

    private void MnPermintaanLab1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnPermintaanLab1ActionPerformed
        if(tabMode2.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Maaf, table masih kosong...!!!!");
            TCari.requestFocus();
        }else{
            if(tbPetugas2.getSelectedRow()>-1){
                if(Sequel.cariInteger("select count(no_rawat) from kamar_inap where no_rawat=?",tbPetugas2.getValueAt(tbPetugas2.getSelectedRow(),1).toString())>0){
                    JOptionPane.showMessageDialog(null,"Maaf, Pasien sudah masuk Kamar Inap. Gunakan billing Ranap..!!!");
                }else {
                    this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                    DlgPermintaanLaboratorium dlgro=new DlgPermintaanLaboratorium(null,false);
                    dlgro.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
                    dlgro.setLocationRelativeTo(internalFrame1);
                    dlgro.emptTeks();
                    dlgro.isCek();
                    dlgro.setNoRm(tbPetugas2.getValueAt(tbPetugas2.getSelectedRow(),1).toString(),"Ralan",
                            tbPetugas2.getValueAt(tbPetugas2.getSelectedRow(),4).toString(),
                            tbPetugas2.getValueAt(tbPetugas2.getSelectedRow(),5).toString()); 
                    dlgro.setVisible(true);
                    this.setCursor(Cursor.getDefaultCursor());
                }
            }else{
                JOptionPane.showMessageDialog(null,"Maaf, Pilih pasien terlebih dahulu..!!!");
            }
        }
    }//GEN-LAST:event_MnPermintaanLab1ActionPerformed

    private void MnPermintaanRadiologi1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnPermintaanRadiologi1ActionPerformed
        if(tabMode2.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Maaf, table masih kosong...!!!!");
            TCari.requestFocus();
        }else{
            if(tbPetugas2.getSelectedRow()>-1){
                if(Sequel.cariInteger("select count(no_rawat) from kamar_inap where no_rawat=?",tbPetugas2.getValueAt(tbPetugas2.getSelectedRow(),1).toString())>0){
                    JOptionPane.showMessageDialog(null,"Maaf, Pasien sudah masuk Kamar Inap. Gunakan billing Ranap..!!!");
                }else {
                    this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                    DlgPermintaanRadiologi dlgro=new DlgPermintaanRadiologi(null,false);
                    dlgro.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
                    dlgro.setLocationRelativeTo(internalFrame1);
                    dlgro.emptTeks();
                    dlgro.isCek();
                    dlgro.setNoRm(tbPetugas2.getValueAt(tbPetugas2.getSelectedRow(),1).toString(),"Ralan",
                            tbPetugas2.getValueAt(tbPetugas2.getSelectedRow(),4).toString(),
                            tbPetugas2.getValueAt(tbPetugas2.getSelectedRow(),5).toString()); 
                    dlgro.setVisible(true);
                    this.setCursor(Cursor.getDefaultCursor());
                }
            }else{
                JOptionPane.showMessageDialog(null,"Maaf, Pilih pasien terlebih dahulu..!!!");
            }
        }
    }//GEN-LAST:event_MnPermintaanRadiologi1ActionPerformed

    private void MnBillingParsialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnBillingParsialActionPerformed
        if(tabMode.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Maaf, table masih kosong...!!!!");
            TNoReg.requestFocus();
        }else if(TPasien.getText().trim().equals("")){
            JOptionPane.showMessageDialog(null,"Maaf, Silahkan anda pilih dulu dengan menklik data pada table...!!!");
            tbPetugas.requestFocus();
        }else{
            if(Sequel.cariInteger("select count(no_rawat) from kamar_inap where no_rawat=?",TNoRw.getText())>0){
                JOptionPane.showMessageDialog(null,"Maaf, Pasien sudah masuk Kamar Inap. Gunakan billing Ranap..!!!");
            }else {                
                try {
                    pscaripiutang=koneksi.prepareStatement("select tgl_piutang from piutang_pasien where no_rkm_medis=? and status='Belum Lunas' order by tgl_piutang asc limit 1");
                    try {
                        pscaripiutang.setString(1,TNoRM.getText());
                        rs=pscaripiutang.executeQuery();
                        if(rs.next()){
                            i=JOptionPane.showConfirmDialog(null, "Masih ada tunggakan pembayaran, apa mau bayar sekarang ?","Konfirmasi",JOptionPane.YES_NO_OPTION);
                            if(i==JOptionPane.YES_OPTION){
                                this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                                 DlgLhtPiutang piutang=new DlgLhtPiutang(null,false);
                                 piutang.setNoRm(TNoRM.getText(),rs.getDate(1));
                                 piutang.tampil();
                                 piutang.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
                                 piutang.setLocationRelativeTo(internalFrame1);
                                 piutang.setVisible(true);
                                 this.setCursor(Cursor.getDefaultCursor());
                            }else{
                                billingprasial();
                            }
                        }else{ 
                            billingprasial();
                        }
                    } catch (Exception e) {
                        System.out.println(e);
                    }finally{
                        if( rs != null){
                            rs.close();
                        }

                        if( pscaripiutang != null){
                            pscaripiutang.close();
                        }
                    }
                } catch (Exception e) {
                    System.out.println(e);
                }                  
            }              
        }
        
    }//GEN-LAST:event_MnBillingParsialActionPerformed

    private void MnPCareActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnPCareActionPerformed
        if(tabMode.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Maaf, table masih kosong...!!!!");
            TNoReg.requestFocus();
        }else if(TPasien.getText().trim().equals("")){
            JOptionPane.showMessageDialog(null,"Maaf, Silahkan anda pilih dulu dengan menklik data pada table...!!!");
            tbPetugas.requestFocus();
        }else{
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            var.setform("DlgReg");
            PCareDataPendaftaran dlgki=new PCareDataPendaftaran(null,false);
            dlgki.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
            dlgki.setLocationRelativeTo(internalFrame1);
            dlgki.isCek();
            dlgki.setNoRm(TNoRw.getText(),DTPReg.getDate(),"2. Ralan",kdpoli.getText(),TPoli.getText());   
            dlgki.setVisible(true);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnPCareActionPerformed

    private void tbPetugasKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbPetugasKeyReleased
        if(tabMode.getRowCount()!=0){
            if((evt.getKeyCode()==KeyEvent.VK_ENTER)||(evt.getKeyCode()==KeyEvent.VK_UP)||(evt.getKeyCode()==KeyEvent.VK_DOWN)){
                try {
                    getData();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbPetugasKeyReleased

    private void MnBlangkoResepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnBlangkoResepActionPerformed
        if(TPasien.getText().trim().equals("")){
            JOptionPane.showMessageDialog(null,"Maaf, Silahkan anda pilih dulu pasien...!!!");
        }else{
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Map<String, Object> param = new HashMap<>();
            param.put("namars",var.getnamars());
            param.put("alamatrs",var.getalamatrs());
            param.put("kotars",var.getkabupatenrs());
            param.put("propinsirs",var.getpropinsirs());
            param.put("kontakrs",var.getkontakrs());
            param.put("emailrs",var.getemailrs());
            param.put("logo",Sequel.cariGambar("select logo from setting"));
            Valid.MyReport("rptBlankoResep.jrxml","report","::[ Bukti Register ]::",
                   "select pasien.no_ktp,pasien.no_peserta,reg_periksa.no_reg,reg_periksa.no_rawat,reg_periksa.tgl_registrasi,reg_periksa.jam_reg,pasien.no_tlp,"+
                   "reg_periksa.kd_dokter,dokter.nm_dokter,reg_periksa.no_rkm_medis,pasien.nm_pasien,pasien.jk,pasien.umur as umur,poliklinik.nm_poli,"+
                   "reg_periksa.p_jawab,reg_periksa.almt_pj,reg_periksa.hubunganpj,reg_periksa.biaya_reg,reg_periksa.stts_daftar,penjab.png_jawab "+
                   "from reg_periksa inner join dokter inner join pasien inner join poliklinik inner join penjab "+
                   "on reg_periksa.kd_dokter=dokter.kd_dokter and reg_periksa.no_rkm_medis=pasien.no_rkm_medis "+
                   "and reg_periksa.kd_pj=penjab.kd_pj and reg_periksa.kd_poli=poliklinik.kd_poli where reg_periksa.no_rawat='"+TNoRw.getText()+"' ",param);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnBlangkoResepActionPerformed

    private void MnRujukSisruteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnRujukSisruteActionPerformed
        if(tabMode.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Maaf, table masih kosong...!!!!");
            TNoReg.requestFocus();
        }else if(TPasien.getText().trim().equals("")){
            JOptionPane.showMessageDialog(null,"Maaf, Silahkan anda pilih dulu dengan menklik data pada table...!!!");
            tbPetugas.requestFocus();
        }else{
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            var.setform("DlgReg");
            SisruteRujukanKeluar dlgki=new SisruteRujukanKeluar(null,false);
            dlgki.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
            dlgki.setLocationRelativeTo(internalFrame1);
            dlgki.isCek();
            dlgki.setPasien(TNoRw.getText());  
            dlgki.setVisible(true);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnRujukSisruteActionPerformed

    private void MnCetakRegister2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnCetakRegister2ActionPerformed
        if(TPasien.getText().trim().equals("")){
            JOptionPane.showMessageDialog(null,"Maaf, Silahkan anda pilih dulu pasien...!!!");
        }else{
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Map<String, Object> param = new HashMap<>();
            param.put("namars",var.getnamars());
            param.put("alamatrs",var.getalamatrs());
            param.put("kotars",var.getkabupatenrs());
            param.put("propinsirs",var.getpropinsirs());
            param.put("kontakrs",var.getkontakrs());
            param.put("emailrs",var.getemailrs());
            param.put("logo",Sequel.cariGambar("select logo from setting"));
            Valid.MyReport("rptBuktiRegister8.jrxml","report","::[ Bukti Register ]::",
                "select reg_periksa.no_reg,reg_periksa.no_rawat,reg_periksa.tgl_registrasi,reg_periksa.jam_reg,pasien.no_tlp,"+
                "reg_periksa.kd_dokter,dokter.nm_dokter,reg_periksa.no_rkm_medis,pasien.nm_pasien,pasien.jk,concat(year(from_days(datediff(now(), pasien.tgl_lahir))),' Th ',month(from_days(datediff(now(),pasien.tgl_lahir))),' Bl ',day(from_days(datediff(now(),pasien.tgl_lahir))),' Hr')as umur,poliklinik.nm_poli,"+
                "reg_periksa.p_jawab,reg_periksa.almt_pj,reg_periksa.hubunganpj,reg_periksa.biaya_reg,reg_periksa.stts_daftar,penjab.png_jawab,pasien.no_peserta,pasien.no_ktp,pasien.tgl_lahir "+
                "from reg_periksa inner join dokter inner join pasien inner join poliklinik inner join penjab "+
                "on reg_periksa.kd_dokter=dokter.kd_dokter and reg_periksa.no_rkm_medis=pasien.no_rkm_medis "+
                "and reg_periksa.kd_pj=penjab.kd_pj and reg_periksa.kd_poli=poliklinik.kd_poli where reg_periksa.no_rawat='"+TNoRw.getText()+"' ",param);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnCetakRegister2ActionPerformed

    private void MnTeridentifikasiTBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnTeridentifikasiTBActionPerformed
        if(TPasien.getText().trim().equals("")){
            JOptionPane.showMessageDialog(null,"Maaf, Silahkan anda pilih dulu pasien...!!!");
            tbPetugas.requestFocus();
        }else{
            if(Sequel.cariInteger("select count(no_rawat) from kamar_inap where no_rawat=?",TNoRw.getText())>0){
                    JOptionPane.showMessageDialog(null,"Maaf, Pasien sudah masuk Kamar Inap. Masukkan diagnosa lewat kamar inap..!!!");
            }else {
                this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                DlgDataTB resep=new DlgDataTB(null,false);
                resep.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
                resep.setLocationRelativeTo(internalFrame1);
                resep.isCek();
                resep.emptTeks();
                resep.setNoRM(TNoRw.getText());
                resep.setVisible(true);
                this.setCursor(Cursor.getDefaultCursor());
            }                
        }
    }//GEN-LAST:event_MnTeridentifikasiTBActionPerformed

    private void MnTeridentifikasiTB1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnTeridentifikasiTB1ActionPerformed
        if(tabMode2.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Maaf, table masih kosong...!!!!");
            TNoReg.requestFocus();
        }else{
            if(tbPetugas2.getSelectedRow()!= -1){
                this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                DlgDataTB resep=new DlgDataTB(null,false);
                resep.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
                resep.setLocationRelativeTo(internalFrame1);
                resep.isCek();
                resep.emptTeks();
                resep.setNoRM(tbPetugas2.getValueAt(tbPetugas2.getSelectedRow(),1).toString());
                resep.setVisible(true);
                this.setCursor(Cursor.getDefaultCursor());
            }
        }
    }//GEN-LAST:event_MnTeridentifikasiTB1ActionPerformed

    private void MnRiwayatObatRacikanBtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnRiwayatObatRacikanBtnPrintActionPerformed
    if(tabMode.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Maaf, table masih kosong...!!!!");
            TNoReg.requestFocus();
        }else if(TPasien.getText().trim().equals("")){
            JOptionPane.showMessageDialog(null,"Maaf, Silahkan anda pilih dulu dengan menklik data pada table...!!!");
            tbPetugas.requestFocus();
        }else{
            //this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
           // DlgRawatJalan dlgrwjl=new DlgRawatJalan(null,false);
            //DlgRiwayatObatRacikan.isCek();
            DlgRiwayatObatRacikan dlgRwtRacikan=new DlgRiwayatObatRacikan(null,false);                                   
            dlgRwtRacikan.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
            dlgRwtRacikan.setLocationRelativeTo(internalFrame1);
            dlgRwtRacikan.setVisible(true);
            this.setCursor(Cursor.getDefaultCursor());
        }          
    }//GEN-LAST:event_MnRiwayatObatRacikanBtnPrintActionPerformed

    private void tbPetugas1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbPetugas1MouseClicked
    if(tabMode3.getRowCount()!=0){
            if(evt.getClickCount()==1){
                i=tbPetugas1.getSelectedColumn();
                if(i==7){
                    if(validasicatatan.equals("Yes")){
                        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                        LabelCatatan.setText(Sequel.cariIsi("select catatan from catatan_pasien where no_rkm_medis=?",tbPetugas1.getValueAt(tbPetugas1.getSelectedRow(),6).toString()));
                        if(!LabelCatatan.getText().equals("")){
                            DlgCatatan.setLocationRelativeTo(TabRawat);
                            DlgCatatan.setVisible(true);
                        }else{
                            DlgCatatan.setVisible(false);
                        }                            
                        this.setCursor(Cursor.getDefaultCursor());
                    }  
                }                
            }else if(evt.getClickCount()==2){
                i=tbPetugas1.getSelectedColumn();
                if(i==1){
                    if(MnKamarInap1.isEnabled()==true){
                        MnKamarInap1ActionPerformed(null);
                    }                    
                }else if(i==2){
                    if(var.gettindakan_ralan()==true){
                        MnRawatJalan1ActionPerformed(null);
                    }                    
                }else if(i==3){
                    if(var.getberi_obat()==true){
                        MnPemberianObat1ActionPerformed(null);
                    }                   
                }else if(i==4){
                    if(var.getperiksa_lab()==true){
                        MnPeriksaLab1ActionPerformed(null);
                    }                    
                }
                
            }
            
        }
    }//GEN-LAST:event_tbPetugas1MouseClicked

    private void tbPetugas1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbPetugas1KeyPressed
     if(tabMode.getRowCount()!=0){
            if(evt.getKeyCode()==KeyEvent.VK_SPACE){
                try {
                    getData();
                } catch (java.lang.NullPointerException e) {
                }
                i=tbPetugas1.getSelectedColumn();
                if(i==1){
                    if(MnKamarInap.isEnabled()==true){
                        MnKamarInapActionPerformed(null);
                    }                    
                }else if(i==2){
                    if(var.gettindakan_ralan()==true){
                        MnRawatJalanActionPerformed(null);
                    }                    
                }else if(i==3){
                    if(var.getberi_obat()==true){
                        MnPemberianObatActionPerformed(null);
                    }                   
                }else if(i==4){
                    if(var.getperiksa_lab()==true){
                        MnPeriksaLabActionPerformed(null);
                    }                    
                }else if(i==5){
                    if(var.getrujukan_masuk()==true){
                        MnRujukMasukActionPerformed(null);
                    }                    
                }else if(i==6){
                    MnCetakRegisterActionPerformed(null);
                }else if(i==7){
                    MnBuktiPelayananRalanActionPerformed(null);
                }
            }else if(evt.getKeyCode()==KeyEvent.VK_A){                
                for(i=0;i<tbPetugas.getRowCount();i++){ 
                    tbPetugas.setValueAt(true,i,0);
                }
            }else if(evt.getKeyCode()==KeyEvent.VK_L){
                MnBarcodeRM9ActionPerformed(null);
            }else if(evt.getKeyCode()==KeyEvent.VK_Z){
                MnSPBK1ActionPerformed(null);
            }else if(evt.getKeyCode()==KeyEvent.VK_W){
                MnLembarRalanActionPerformed(null);
            }
        }
    }//GEN-LAST:event_tbPetugas1KeyPressed

    private void tbPetugas1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbPetugas1KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tbPetugas1KeyReleased

    private void MnPaketMCUActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnPaketMCUActionPerformed
        if(TPasien.getText().trim().equals("")){
            JOptionPane.showMessageDialog(null,"Maaf, Silahkan anda pilih dulu dengan menklik data pada table...!!!");
            tbPetugas.requestFocus();
        }else{
        try {
           String lab,rad,polidalam,ekg;
           lab = "insert into periksa_lab (no_rawat,nip,kd_jenis_prw,tgl_periksa,jam,dokter_perujuk,biaya,kd_dokter,bagian_rs,bhp,tarif_perujuk,tarif_tindakan_dokter,tarif_tindakan_petugas,status) values ('"+TNoRw.getText()+"','lab1','J001000','"+Valid.SetTgl((String) DTPReg.getSelectedItem())+"','"+CmbJam.getSelectedItem()+":"+CmbMenit.getSelectedItem()+":"+CmbDetik.getSelectedItem()+"','DR00017','330500','DR00017','0','0','0','0','0','Ralan')";
           rad = "insert into periksa_radiologi (no_rawat,nip,kd_jenis_prw,tgl_periksa,jam,dokter_perujuk,biaya,kd_dokter,bagian_rs,bhp,tarif_perujuk,tarif_tindakan_dokter,tarif_tindakan_petugas) values ('"+TNoRw.getText()+"','rad1','J000010','"+Valid.SetTgl((String) DTPReg.getSelectedItem())+"','"+CmbJam.getSelectedItem()+":"+CmbMenit.getSelectedItem()+":"+CmbDetik.getSelectedItem()+"','DR00019','77000','DR00019','0','0','0','0','0')";
           polidalam ="insert into rawat_jl_pr (no_rawat,kd_jenis_prw,nip,tgl_perawatan,jam_rawat,material,bhp,tarif_tindakanpr,biaya_rawat) VALUES ('"+TNoRw.getText()+"','RJ100000','Surat Keterangan','"+Valid.SetTgl((String) DTPReg.getSelectedItem())+"','"+CmbJam.getSelectedItem()+":"+CmbMenit.getSelectedItem()+":"+CmbDetik.getSelectedItem()+"','0','0','30000','30000')";
           ekg ="insert into rawat_jl_pr (no_rawat,kd_jenis_prw,nip,tgl_perawatan,jam_rawat,material,bhp,tarif_tindakanpr,biaya_rawat) VALUES ('"+TNoRw.getText()+"','J000301','Surat Keterangan','"+Valid.SetTgl((String) DTPReg.getSelectedItem())+"','"+CmbJam.getSelectedItem()+":"+CmbMenit.getSelectedItem()+":"+CmbDetik.getSelectedItem()+"','0','0','40000','40000')";
           
           PreparedStatement pst_lab = koneksiDB.condb().prepareStatement(lab);          
           pst_lab.execute();
           PreparedStatement pst_rad = koneksiDB.condb().prepareStatement(rad);          
           pst_rad.execute();
           PreparedStatement pst_dalam = koneksiDB.condb().prepareStatement(polidalam);          
           pst_dalam.execute();
           PreparedStatement pst_ekg = koneksiDB.condb().prepareStatement(ekg);          
           pst_ekg.execute();
           JOptionPane.showMessageDialog(rootPane,"Pian telah berhasil menyimpan paket MCU..");
           
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }//GEN-LAST:event_MnPaketMCUActionPerformed

    private void MnPaketNapzaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnPaketNapzaActionPerformed
        if(TPasien.getText().trim().equals("")){
            JOptionPane.showMessageDialog(null,"Maaf, Silahkan anda pilih dulu dengan menklik data pada table...!!!");
            tbPetugas.requestFocus();
        }else{
        try {
        
        String lab, mop, amp, thc,suratnapza;
        mop = " insert into periksa_lab (no_rawat,nip,kd_jenis_prw,tgl_periksa,jam,dokter_perujuk,biaya,kd_dokter,bagian_rs,bhp,tarif_perujuk,tarif_tindakan_dokter,tarif_tindakan_petugas,status) values ('"+TNoRw.getText()+"','lab1','J000086','"+Valid.SetTgl((String) DTPReg.getSelectedItem())+"','"+CmbJam.getSelectedItem()+":"+CmbMenit.getSelectedItem()+":"+CmbDetik.getSelectedItem()+"','DR00017','35000','DR00017','0','0','0','0','0','Ralan')";
        amp = " insert into periksa_lab (no_rawat,nip,kd_jenis_prw,tgl_periksa,jam,dokter_perujuk,biaya,kd_dokter,bagian_rs,bhp,tarif_perujuk,tarif_tindakan_dokter,tarif_tindakan_petugas,status) values ('"+TNoRw.getText()+"','lab1','J000087','"+Valid.SetTgl((String) DTPReg.getSelectedItem())+"','"+CmbJam.getSelectedItem()+":"+CmbMenit.getSelectedItem()+":"+CmbDetik.getSelectedItem()+"','DR00017','35000','DR00017','0','0','0','0','0','Ralan')";
        thc = " insert into periksa_lab (no_rawat,nip,kd_jenis_prw,tgl_periksa,jam,dokter_perujuk,biaya,kd_dokter,bagian_rs,bhp,tarif_perujuk,tarif_tindakan_dokter,tarif_tindakan_petugas,status) values ('"+TNoRw.getText()+"','lab1','J000088','"+Valid.SetTgl((String) DTPReg.getSelectedItem())+"','"+CmbJam.getSelectedItem()+":"+CmbMenit.getSelectedItem()+":"+CmbDetik.getSelectedItem()+"','DR00017','35000','DR00017','0','0','0','0','0','Ralan')";
        suratnapza = "insert into rawat_jl_pr (no_rawat,kd_jenis_prw,nip,tgl_perawatan,jam_rawat,material,bhp,tarif_tindakanpr,biaya_rawat) VALUES ('"+TNoRw.getText()+"','J000469','Surat Keterangan','"+Valid.SetTgl((String) DTPReg.getSelectedItem())+"','"+CmbJam.getSelectedItem()+":"+CmbMenit.getSelectedItem()+":"+CmbDetik.getSelectedItem()+"','0','0','50000','50000')";
        PreparedStatement pst_mop = koneksiDB.condb().prepareStatement(mop);          
        pst_mop.execute();
        PreparedStatement pst_amp = koneksiDB.condb().prepareStatement(amp);          
        pst_amp.execute();
        PreparedStatement pst_thc = koneksiDB.condb().prepareStatement(thc);          
        pst_thc.execute();
        
        PreparedStatement pst_surat = koneksiDB.condb().prepareStatement(suratnapza);          
        pst_surat.execute();
        JOptionPane.showMessageDialog(rootPane,"Pian telah berhasil menyimpan paket NAPZA..");
        } catch (Exception e) {
          System.out.println(e);
        }
        }
    }//GEN-LAST:event_MnPaketNapzaActionPerformed

    private void MnPaketMMPIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnPaketMMPIActionPerformed
        if(TPasien.getText().trim().equals("")){
            JOptionPane.showMessageDialog(null,"Maaf, Silahkan anda pilih dulu dengan menklik data pada table...!!!");
            tbPetugas.requestFocus();
        }else{
            try {

                String suratmmpi,tesmmpi;
                suratmmpi = "insert into rawat_jl_pr (no_rawat,kd_jenis_prw,nip,tgl_perawatan,jam_rawat,material,bhp,tarif_tindakanpr,biaya_rawat) VALUES ('"+TNoRw.getText()+"','J000999998','Surat Keterangan','"+Valid.SetTgl((String) DTPReg.getSelectedItem())+"','"+CmbJam.getSelectedItem()+":"+CmbMenit.getSelectedItem()+":"+CmbDetik.getSelectedItem()+"','0','0','50000','50000')";
                tesmmpi = "insert into rawat_jl_dr (no_rawat,kd_jenis_prw,kd_dokter,tgl_perawatan,jam_rawat,material,bhp,tarif_tindakandr,biaya_rawat) VALUES ('"+TNoRw.getText()+"','J000597','D0000046','"+Valid.SetTgl((String) DTPReg.getSelectedItem())+"','"+CmbJam.getSelectedItem()+":"+CmbMenit.getSelectedItem()+":"+CmbDetik.getSelectedItem()+"','0','0','210000','210000')";

                PreparedStatement pst_surat = koneksiDB.condb().prepareStatement(suratmmpi);          
                pst_surat.execute();
                PreparedStatement pst_tes = koneksiDB.condb().prepareStatement(tesmmpi);          
                pst_tes.execute();
                JOptionPane.showMessageDialog(rootPane,"Pian telah berhasil menyimpan paket MMPI..");
            } catch (Exception e) {
              System.out.println(e);
            }
        }
    }//GEN-LAST:event_MnPaketMMPIActionPerformed

    private void MnDataSuratSehatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnDataSuratSehatActionPerformed
       if(tabMode.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Maaf, table masih kosong...!!!!");
            TNoReg.requestFocus();
        }else if(TPasien.getText().trim().equals("")){
            JOptionPane.showMessageDialog(null,"Maaf, Silahkan anda pilih dulu dengan menklik data pada table...!!!");
            tbPetugas.requestFocus();
        }else{
            if(Sequel.cariInteger("select count(no_rawat) from kamar_inap where no_rawat=?",TNoRw.getText())>0){
                        JOptionPane.showMessageDialog(null,"Maaf, Pasien sudah masuk Kamar Inap. Gunakan billing Ranap..!!!");
            }else {
                this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                
                DlgSuratSehat dlgro=new DlgSuratSehat(null,false);
                dlgro.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
                dlgro.setLocationRelativeTo(internalFrame1);
                //dlgro.emptTeks();
                //dlgro.isCek();
                //dlgro.setNoRm(TNoRw.getText(),"Ralan"); 
                dlgro.setVisible(true);
                this.setCursor(Cursor.getDefaultCursor());
            }                
        }
    }//GEN-LAST:event_MnDataSuratSehatActionPerformed

    private void MnCetakSuratSehat2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnCetakSuratSehat2ActionPerformed
        if(tabMode.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Maaf, table masih kosong...!!!!");
            TNoReg.requestFocus();
        }else if(TPasien.getText().trim().equals("")){
            JOptionPane.showMessageDialog(null,"Maaf, Silahkan anda pilih dulu dengan menklik data pada table...!!!");
            tbPetugas.requestFocus();
        }else{
            if(Sequel.cariInteger("select count(no_rawat) from kamar_inap where no_rawat=?",TNoRw.getText())>0){
                        JOptionPane.showMessageDialog(null,"Maaf, Pasien sudah masuk Kamar Inap. Gunakan billing Ranap..!!!");
            }else {
                this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                
                DlgSuratSehat dlgro=new DlgSuratSehat(null,false);
                dlgro.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
                dlgro.setLocationRelativeTo(internalFrame1);
                //dlgro.emptTeks();
                //dlgro.isCek();
                dlgro.setPasien(TNoRM.getText(),TNoRw.getText(), TPasien.getText(), TDokter.getText(), kddokter.getText());
                //dlgro.get
               // dlgro.
                dlgro.setVisible(true);
                this.setCursor(Cursor.getDefaultCursor());
            }                
        }
    }//GEN-LAST:event_MnCetakSuratSehat2ActionPerformed

    private void MnInputHPBtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnInputHPBtnPrintActionPerformed
        if(tabMode.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Maaf, table masih kosong...!!!!");
            TNoReg.requestFocus();
        }else if(TPasien.getText().trim().equals("")){
            JOptionPane.showMessageDialog(null,"Maaf, Silahkan anda pilih dulu dengan menklik data pada table...!!!");
            tbPetugas.requestFocus();
        }else{
          //  if(Sequel.cariRegistrasi(TNoRw.getText())>0){
           //     JOptionPane.showMessageDialog(rootPane,"Data billing sudah terverifikasi..!!");
           // }else{
                this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                var.setstatus(true);
                DlgNomorTelp dlgki=new DlgNomorTelp(null,false);
               // dlgki.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
                dlgki.setLocationRelativeTo(internalFrame1);
                //dlgki.emptTeks();
                //dlgki.isCek();
                dlgki.setPasien(TNoRM.getText(),TPasien.getText());   
                dlgki.setVisible(true);
                
                this.setCursor(Cursor.getDefaultCursor());
                
               // DlgInputNoHp.setLocationRelativeTo(internalFrame1);
                //DlgInputNoHp.
          //  } 
                
        }
    }//GEN-LAST:event_MnInputHPBtnPrintActionPerformed

    private void MnPaketHajiLakiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnPaketHajiLakiActionPerformed
      if(TPasien.getText().trim().equals("")){
            JOptionPane.showMessageDialog(null,"Maaf, Silahkan anda pilih dulu dengan menklik data pada table...!!!");
            tbPetugas.requestFocus();
        }else{
        try {
           String dl,gol_darah ;
           dl ="insert into periksa_lab (no_rawat,nip,kd_jenis_prw,tgl_periksa,jam,dokter_perujuk,biaya,kd_dokter,bagian_rs,bhp,tarif_perujuk,tarif_tindakan_dokter,tarif_tindakan_petugas,status) values ('"+TNoRw.getText()+"','lab1','J000033','"+Valid.SetTgl((String) DTPReg.getSelectedItem())+"','"+CmbJam.getSelectedItem()+":"+CmbMenit.getSelectedItem()+":"+CmbDetik.getSelectedItem()+"','DR00017','42000','DR00017','0','0','0','0','0','Ralan')";
           PreparedStatement pst_dl = koneksiDB.condb().prepareStatement(dl);          
           pst_dl.execute();
           
           gol_darah ="insert into periksa_lab (no_rawat,nip,kd_jenis_prw,tgl_periksa,jam,dokter_perujuk,biaya,kd_dokter,bagian_rs,bhp,tarif_perujuk,tarif_tindakan_dokter,tarif_tindakan_petugas,status) values ('"+TNoRw.getText()+"','lab1','J000029','"+Valid.SetTgl((String) DTPReg.getSelectedItem())+"','"+CmbJam.getSelectedItem()+":"+CmbMenit.getSelectedItem()+":"+CmbDetik.getSelectedItem()+"','DR00017','20000','DR00017','0','0','0','0','0','Ralan')";
           PreparedStatement pst_gd = koneksiDB.condb().prepareStatement(gol_darah);          
           pst_gd.execute();
           
           String led;
           led ="insert into periksa_lab (no_rawat,nip,kd_jenis_prw,tgl_periksa,jam,dokter_perujuk,biaya,kd_dokter,bagian_rs,bhp,tarif_perujuk,tarif_tindakan_dokter,tarif_tindakan_petugas,status) values ('"+TNoRw.getText()+"','lab1','J000022','"+Valid.SetTgl((String) DTPReg.getSelectedItem())+"','"+CmbJam.getSelectedItem()+":"+CmbMenit.getSelectedItem()+":"+CmbDetik.getSelectedItem()+"','DR00017','13000','DR00017','0','0','0','0','0','Ralan')";
           PreparedStatement pst_led = koneksiDB.condb().prepareStatement(led);          
           pst_led.execute();
           
           String urin_lengkap;
           urin_lengkap ="insert into periksa_lab (no_rawat,nip,kd_jenis_prw,tgl_periksa,jam,dokter_perujuk,biaya,kd_dokter,bagian_rs,bhp,tarif_perujuk,tarif_tindakan_dokter,tarif_tindakan_petugas,status) values ('"+TNoRw.getText()+"','lab1','J000001','"+Valid.SetTgl((String) DTPReg.getSelectedItem())+"','"+CmbJam.getSelectedItem()+":"+CmbMenit.getSelectedItem()+":"+CmbDetik.getSelectedItem()+"','DR00017','22500','DR00017','0','0','0','0','0','Ralan')";
           PreparedStatement pst_ul = koneksiDB.condb().prepareStatement(urin_lengkap);          
           pst_ul.execute();
           
           String asam_urat;
           asam_urat ="insert into periksa_lab (no_rawat,nip,kd_jenis_prw,tgl_periksa,jam,dokter_perujuk,biaya,kd_dokter,bagian_rs,bhp,tarif_perujuk,tarif_tindakan_dokter,tarif_tindakan_petugas,status) values ('"+TNoRw.getText()+"','lab1','J000061','"+Valid.SetTgl((String) DTPReg.getSelectedItem())+"','"+CmbJam.getSelectedItem()+":"+CmbMenit.getSelectedItem()+":"+CmbDetik.getSelectedItem()+"','DR00017','15500','DR00017','0','0','0','0','0','Ralan')";
           PreparedStatement pst_au = koneksiDB.condb().prepareStatement(asam_urat);          
           pst_au.execute();
           
           String sgot;
           sgot ="insert into periksa_lab (no_rawat,nip,kd_jenis_prw,tgl_periksa,jam,dokter_perujuk,biaya,kd_dokter,bagian_rs,bhp,tarif_perujuk,tarif_tindakan_dokter,tarif_tindakan_petugas,status) values ('"+TNoRw.getText()+"','lab1','J000052','"+Valid.SetTgl((String) DTPReg.getSelectedItem())+"','"+CmbJam.getSelectedItem()+":"+CmbMenit.getSelectedItem()+":"+CmbDetik.getSelectedItem()+"','DR00017','17500','DR00017','0','0','0','0','0','Ralan')";
           PreparedStatement pst_sgot = koneksiDB.condb().prepareStatement(sgot);          
           pst_sgot.execute();
           
           String sgpt;
           sgpt ="insert into periksa_lab (no_rawat,nip,kd_jenis_prw,tgl_periksa,jam,dokter_perujuk,biaya,kd_dokter,bagian_rs,bhp,tarif_perujuk,tarif_tindakan_dokter,tarif_tindakan_petugas,status) values ('"+TNoRw.getText()+"','lab1','J000053','"+Valid.SetTgl((String) DTPReg.getSelectedItem())+"','"+CmbJam.getSelectedItem()+":"+CmbMenit.getSelectedItem()+":"+CmbDetik.getSelectedItem()+"','DR00017','17500','DR00017','0','0','0','0','0','Ralan')";
           PreparedStatement pst_sgpt = koneksiDB.condb().prepareStatement(sgpt);          
           pst_sgpt.execute();
           
           String gp;
           gp ="insert into periksa_lab (no_rawat,nip,kd_jenis_prw,tgl_periksa,jam,dokter_perujuk,biaya,kd_dokter,bagian_rs,bhp,tarif_perujuk,tarif_tindakan_dokter,tarif_tindakan_petugas,status) values ('"+TNoRw.getText()+"','lab1','J000065','"+Valid.SetTgl((String) DTPReg.getSelectedItem())+"','"+CmbJam.getSelectedItem()+":"+CmbMenit.getSelectedItem()+":"+CmbDetik.getSelectedItem()+"','DR00017','14500','DR00017','0','0','0','0','0','Ralan')";
           PreparedStatement pst_gp = koneksiDB.condb().prepareStatement(gp);          
           pst_gp.execute();
           
           String lab_bnyk;
           lab_bnyk ="insert into periksa_lab values "
           /*gula 2 pp*/  + "('"+TNoRw.getText()+"','lab1','J000057','"+Valid.SetTgl((String) DTPReg.getSelectedItem())+"','"+CmbJam.getSelectedItem()+":"+CmbMenit.getSelectedItem()+":"+CmbDetik.getSelectedItem()+"','DR00017','0','0','0','0','0','0','0','17500','DR00017','Ralan'), "
           /*ureum*/      + "('"+TNoRw.getText()+"','lab1','J000059','"+Valid.SetTgl((String) DTPReg.getSelectedItem())+"','"+CmbJam.getSelectedItem()+":"+CmbMenit.getSelectedItem()+":"+CmbDetik.getSelectedItem()+"','DR00017','0','0','0','0','0','0','0','14500','DR00017','Ralan'), "
           /*kreatinin*/  + "('"+TNoRw.getText()+"','lab1','J000060','"+Valid.SetTgl((String) DTPReg.getSelectedItem())+"','"+CmbJam.getSelectedItem()+":"+CmbMenit.getSelectedItem()+":"+CmbDetik.getSelectedItem()+"','DR00017','0','0','0','0','0','0','0','20000','DR00017','Ralan'), "
           /*cholesterol*/+ "('"+TNoRw.getText()+"','lab1','J000049','"+Valid.SetTgl((String) DTPReg.getSelectedItem())+"','"+CmbJam.getSelectedItem()+":"+CmbMenit.getSelectedItem()+":"+CmbDetik.getSelectedItem()+"','DR00017','0','0','0','0','0','0','0','17500','DR00017','Ralan'), "
           /*hdl*/        + "('"+TNoRw.getText()+"','lab1','J000055','"+Valid.SetTgl((String) DTPReg.getSelectedItem())+"','"+CmbJam.getSelectedItem()+":"+CmbMenit.getSelectedItem()+":"+CmbDetik.getSelectedItem()+"','DR00017','0','0','0','0','0','0','0','31500','DR00017','Ralan'), "
           /*ldl*/         + "('"+TNoRw.getText()+"','lab1','J000056','"+Valid.SetTgl((String) DTPReg.getSelectedItem())+"','"+CmbJam.getSelectedItem()+":"+CmbMenit.getSelectedItem()+":"+CmbDetik.getSelectedItem()+"','DR00017','0','0','0','0','0','0','0','35000','DR00017','Ralan'), "
           /*triglyserida*/+ "('"+TNoRw.getText()+"','lab1','J000050','"+Valid.SetTgl((String) DTPReg.getSelectedItem())+"','"+CmbJam.getSelectedItem()+":"+CmbMenit.getSelectedItem()+":"+CmbDetik.getSelectedItem()+"','DR00017','0','0','0','0','0','0','0','25000','DR00017','Ralan'), "
           /*sampling dws*/+ "('"+TNoRw.getText()+"','lab1','J000372','"+Valid.SetTgl((String) DTPReg.getSelectedItem())+"','"+CmbJam.getSelectedItem()+":"+CmbMenit.getSelectedItem()+":"+CmbDetik.getSelectedItem()+"','DR00017','0','0','0','0','0','0','0','7000','DR00017','Ralan'), "
           /*hematologi*/ + "('"+TNoRw.getText()+"','lab1','J000030','"+Valid.SetTgl((String) DTPReg.getSelectedItem())+"','"+CmbJam.getSelectedItem()+":"+CmbMenit.getSelectedItem()+":"+CmbDetik.getSelectedItem()+"','DR00017','0','0','0','0','0','0','0','11000','DR00017','Ralan') "
        //   /*planotest*/  + "('"+TNoRw.getText()+"','lab1','J000002','"+Valid.SetTgl((String) DTPReg.getSelectedItem())+"','"+CmbJam.getSelectedItem()+":"+CmbMenit.getSelectedItem()+":"+CmbDetik.getSelectedItem()+"','DR00017','0','0','0','0','0','0','0','24500','DR00017','Ralan') "    
                          + "";
           PreparedStatement pst_lab_banyak = koneksiDB.condb().prepareStatement(lab_bnyk);          
           pst_lab_banyak.execute();
           
           String hari_jadwal, jadwal_dr_jantung;
           //cari hari ini
           hari_jadwal = Sequel.cariIsi("SELECT CASE  DAYNAME(tgl_registrasi) WHEN 'Sunday' THEN 'MINGGU' " +
                                        "WHEN 'Monday' THEN 'SENIN' WHEN 'Tuesday' THEN 'SELASA' " +
                                        "WHEN 'Wednesday' THEN 'RABU' WHEN 'Thursday' THEN 'KAMIS' " +
                                        "WHEN 'Friday' THEN 'JUMAT' WHEN 'Saturday' THEN 'SABTU' END as hari " +
                                        "FROM reg_periksa WHERE tgl_registrasi = ? ",Valid.SetTgl((String) DTPReg.getSelectedItem()));           
           
            //cari data jadwal dokter jantung berdasarkan hari
            jadwal_dr_jantung = Sequel.cariIsi("select kd_dokter from jadwal where hari_kerja ='"+hari_jadwal+"' and kd_poli ='U0012' ");
            //TCari.setText(jadwal_dr_jantung);
           String bc_ekg;
           bc_ekg = "insert into rawat_jl_dr (no_rawat,kd_jenis_prw,kd_dokter,tgl_perawatan,jam_rawat,material,bhp,tarif_tindakandr,biaya_rawat) VALUES ('"+TNoRw.getText()+"','J000189','"+jadwal_dr_jantung+"','"+Valid.SetTgl((String) DTPReg.getSelectedItem())+"','"+CmbJam.getSelectedItem()+":"+CmbMenit.getSelectedItem()+":"+CmbDetik.getSelectedItem()+"','0','0','21000','21000')";
           PreparedStatement pst_ek = koneksiDB.condb().prepareStatement(bc_ekg);          
           pst_ek.execute();
           
           //tindakan ekg sementara pakai paramedis VCT, harusnya paramedis MCU
           String ekg_mcu;
           ekg_mcu ="insert into rawat_jl_pr (no_rawat,kd_jenis_prw,nip,tgl_perawatan,jam_rawat,material,bhp,tarif_tindakanpr,biaya_rawat) VALUES ('"+TNoRw.getText()+"','J000301','unit42','"+Valid.SetTgl((String) DTPReg.getSelectedItem())+"','"+CmbJam.getSelectedItem()+":"+CmbMenit.getSelectedItem()+":"+CmbDetik.getSelectedItem()+"','0','0','40000','40000')";
           PreparedStatement pst_ekg_mcu = koneksiDB.condb().prepareStatement(ekg_mcu);          
           pst_ekg_mcu.execute();
           
           String thorax;
           thorax = "insert into periksa_radiologi (no_rawat,nip,kd_jenis_prw,tgl_periksa,jam,dokter_perujuk,biaya,kd_dokter,bagian_rs,bhp,tarif_perujuk,tarif_tindakan_dokter,tarif_tindakan_petugas) values ('"+TNoRw.getText()+"','rad1','J000010','"+Valid.SetTgl((String) DTPReg.getSelectedItem())+"','"+CmbJam.getSelectedItem()+":"+CmbMenit.getSelectedItem()+":"+CmbDetik.getSelectedItem()+"','DR00019','77000','DR00019','0','0','0','0','0')";
           PreparedStatement pst_thorax = koneksiDB.condb().prepareStatement(thorax);          
           pst_thorax.execute();
           
           String bc_thorax;
           bc_thorax = "insert into rawat_jl_dr (no_rawat,kd_jenis_prw,kd_dokter,tgl_perawatan,jam_rawat,material,bhp,tarif_tindakandr,biaya_rawat) VALUES ('"+TNoRw.getText()+"','J000180','DR00019','"+Valid.SetTgl((String) DTPReg.getSelectedItem())+"','"+CmbJam.getSelectedItem()+":"+CmbMenit.getSelectedItem()+":"+CmbDetik.getSelectedItem()+"','0','0','21000','21000')";
           PreparedStatement pst_bc_thorax = koneksiDB.condb().prepareStatement(bc_thorax);          
           pst_bc_thorax.execute();
           JOptionPane.showMessageDialog(rootPane,"Pian sukses menyimpan paket CJH Laki-laki..");
           
            } catch (Exception e) {
                System.out.println(e);
            }
        } 
           
    }//GEN-LAST:event_MnPaketHajiLakiActionPerformed

    private void MnPaketHajiPerempuanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnPaketHajiPerempuanActionPerformed
        if(TPasien.getText().trim().equals("")){
            JOptionPane.showMessageDialog(null,"Maaf, Silahkan anda pilih dulu dengan menklik data pada table...!!!");
            tbPetugas.requestFocus();
        }else{
        try {
           String dl,gol_darah ;
           dl ="insert into periksa_lab (no_rawat,nip,kd_jenis_prw,tgl_periksa,jam,dokter_perujuk,biaya,kd_dokter,bagian_rs,bhp,tarif_perujuk,tarif_tindakan_dokter,tarif_tindakan_petugas,status) values ('"+TNoRw.getText()+"','lab1','J000033','"+Valid.SetTgl((String) DTPReg.getSelectedItem())+"','"+CmbJam.getSelectedItem()+":"+CmbMenit.getSelectedItem()+":"+CmbDetik.getSelectedItem()+"','DR00017','42000','DR00017','0','0','0','0','0','Ralan')";
           PreparedStatement pst_dl = koneksiDB.condb().prepareStatement(dl);          
           pst_dl.execute();
           
           gol_darah ="insert into periksa_lab (no_rawat,nip,kd_jenis_prw,tgl_periksa,jam,dokter_perujuk,biaya,kd_dokter,bagian_rs,bhp,tarif_perujuk,tarif_tindakan_dokter,tarif_tindakan_petugas,status) values ('"+TNoRw.getText()+"','lab1','J000029','"+Valid.SetTgl((String) DTPReg.getSelectedItem())+"','"+CmbJam.getSelectedItem()+":"+CmbMenit.getSelectedItem()+":"+CmbDetik.getSelectedItem()+"','DR00017','20000','DR00017','0','0','0','0','0','Ralan')";
           PreparedStatement pst_gd = koneksiDB.condb().prepareStatement(gol_darah);          
           pst_gd.execute();
           
           String led;
           led ="insert into periksa_lab (no_rawat,nip,kd_jenis_prw,tgl_periksa,jam,dokter_perujuk,biaya,kd_dokter,bagian_rs,bhp,tarif_perujuk,tarif_tindakan_dokter,tarif_tindakan_petugas,status) values ('"+TNoRw.getText()+"','lab1','J000022','"+Valid.SetTgl((String) DTPReg.getSelectedItem())+"','"+CmbJam.getSelectedItem()+":"+CmbMenit.getSelectedItem()+":"+CmbDetik.getSelectedItem()+"','DR00017','13000','DR00017','0','0','0','0','0','Ralan')";
           PreparedStatement pst_led = koneksiDB.condb().prepareStatement(led);          
           pst_led.execute();
           
           String urin_lengkap;
           urin_lengkap ="insert into periksa_lab (no_rawat,nip,kd_jenis_prw,tgl_periksa,jam,dokter_perujuk,biaya,kd_dokter,bagian_rs,bhp,tarif_perujuk,tarif_tindakan_dokter,tarif_tindakan_petugas,status) values ('"+TNoRw.getText()+"','lab1','J000001','"+Valid.SetTgl((String) DTPReg.getSelectedItem())+"','"+CmbJam.getSelectedItem()+":"+CmbMenit.getSelectedItem()+":"+CmbDetik.getSelectedItem()+"','DR00017','22500','DR00017','0','0','0','0','0','Ralan')";
           PreparedStatement pst_ul = koneksiDB.condb().prepareStatement(urin_lengkap);          
           pst_ul.execute();
           
           String asam_urat;
           asam_urat ="insert into periksa_lab (no_rawat,nip,kd_jenis_prw,tgl_periksa,jam,dokter_perujuk,biaya,kd_dokter,bagian_rs,bhp,tarif_perujuk,tarif_tindakan_dokter,tarif_tindakan_petugas,status) values ('"+TNoRw.getText()+"','lab1','J000061','"+Valid.SetTgl((String) DTPReg.getSelectedItem())+"','"+CmbJam.getSelectedItem()+":"+CmbMenit.getSelectedItem()+":"+CmbDetik.getSelectedItem()+"','DR00017','15500','DR00017','0','0','0','0','0','Ralan')";
           PreparedStatement pst_au = koneksiDB.condb().prepareStatement(asam_urat);          
           pst_au.execute();
           
           String sgot;
           sgot ="insert into periksa_lab (no_rawat,nip,kd_jenis_prw,tgl_periksa,jam,dokter_perujuk,biaya,kd_dokter,bagian_rs,bhp,tarif_perujuk,tarif_tindakan_dokter,tarif_tindakan_petugas,status) values ('"+TNoRw.getText()+"','lab1','J000052','"+Valid.SetTgl((String) DTPReg.getSelectedItem())+"','"+CmbJam.getSelectedItem()+":"+CmbMenit.getSelectedItem()+":"+CmbDetik.getSelectedItem()+"','DR00017','17500','DR00017','0','0','0','0','0','Ralan')";
           PreparedStatement pst_sgot = koneksiDB.condb().prepareStatement(sgot);          
           pst_sgot.execute();
           
           String sgpt;
           sgpt ="insert into periksa_lab (no_rawat,nip,kd_jenis_prw,tgl_periksa,jam,dokter_perujuk,biaya,kd_dokter,bagian_rs,bhp,tarif_perujuk,tarif_tindakan_dokter,tarif_tindakan_petugas,status) values ('"+TNoRw.getText()+"','lab1','J000053','"+Valid.SetTgl((String) DTPReg.getSelectedItem())+"','"+CmbJam.getSelectedItem()+":"+CmbMenit.getSelectedItem()+":"+CmbDetik.getSelectedItem()+"','DR00017','17500','DR00017','0','0','0','0','0','Ralan')";
           PreparedStatement pst_sgpt = koneksiDB.condb().prepareStatement(sgpt);          
           pst_sgpt.execute();
           
           String gp;
           gp ="insert into periksa_lab (no_rawat,nip,kd_jenis_prw,tgl_periksa,jam,dokter_perujuk,biaya,kd_dokter,bagian_rs,bhp,tarif_perujuk,tarif_tindakan_dokter,tarif_tindakan_petugas,status) values ('"+TNoRw.getText()+"','lab1','J000065','"+Valid.SetTgl((String) DTPReg.getSelectedItem())+"','"+CmbJam.getSelectedItem()+":"+CmbMenit.getSelectedItem()+":"+CmbDetik.getSelectedItem()+"','DR00017','14500','DR00017','0','0','0','0','0','Ralan')";
           PreparedStatement pst_gp = koneksiDB.condb().prepareStatement(gp);          
           pst_gp.execute();
           
           String lab_bnyk;
           lab_bnyk ="insert into periksa_lab values "
           /*gula 2 pp*/  + "('"+TNoRw.getText()+"','lab1','J000057','"+Valid.SetTgl((String) DTPReg.getSelectedItem())+"','"+CmbJam.getSelectedItem()+":"+CmbMenit.getSelectedItem()+":"+CmbDetik.getSelectedItem()+"','DR00017','0','0','0','0','0','0','0','17500','DR00017','Ralan'), "
           /*ureum*/      + "('"+TNoRw.getText()+"','lab1','J000059','"+Valid.SetTgl((String) DTPReg.getSelectedItem())+"','"+CmbJam.getSelectedItem()+":"+CmbMenit.getSelectedItem()+":"+CmbDetik.getSelectedItem()+"','DR00017','0','0','0','0','0','0','0','14500','DR00017','Ralan'), "
           /*kreatinin*/  + "('"+TNoRw.getText()+"','lab1','J000060','"+Valid.SetTgl((String) DTPReg.getSelectedItem())+"','"+CmbJam.getSelectedItem()+":"+CmbMenit.getSelectedItem()+":"+CmbDetik.getSelectedItem()+"','DR00017','0','0','0','0','0','0','0','20000','DR00017','Ralan'), "
           /*cholesterol*/+ "('"+TNoRw.getText()+"','lab1','J000049','"+Valid.SetTgl((String) DTPReg.getSelectedItem())+"','"+CmbJam.getSelectedItem()+":"+CmbMenit.getSelectedItem()+":"+CmbDetik.getSelectedItem()+"','DR00017','0','0','0','0','0','0','0','17500','DR00017','Ralan'), "
           /*hdl*/        + "('"+TNoRw.getText()+"','lab1','J000055','"+Valid.SetTgl((String) DTPReg.getSelectedItem())+"','"+CmbJam.getSelectedItem()+":"+CmbMenit.getSelectedItem()+":"+CmbDetik.getSelectedItem()+"','DR00017','0','0','0','0','0','0','0','31500','DR00017','Ralan'), "
           /*ldl*/         + "('"+TNoRw.getText()+"','lab1','J000056','"+Valid.SetTgl((String) DTPReg.getSelectedItem())+"','"+CmbJam.getSelectedItem()+":"+CmbMenit.getSelectedItem()+":"+CmbDetik.getSelectedItem()+"','DR00017','0','0','0','0','0','0','0','35000','DR00017','Ralan'), "
           /*triglyserida*/+ "('"+TNoRw.getText()+"','lab1','J000050','"+Valid.SetTgl((String) DTPReg.getSelectedItem())+"','"+CmbJam.getSelectedItem()+":"+CmbMenit.getSelectedItem()+":"+CmbDetik.getSelectedItem()+"','DR00017','0','0','0','0','0','0','0','25000','DR00017','Ralan'), "
           /*sampling dws*/+ "('"+TNoRw.getText()+"','lab1','J000372','"+Valid.SetTgl((String) DTPReg.getSelectedItem())+"','"+CmbJam.getSelectedItem()+":"+CmbMenit.getSelectedItem()+":"+CmbDetik.getSelectedItem()+"','DR00017','0','0','0','0','0','0','0','7000','DR00017','Ralan'), "
           /*hematologi*/ + "('"+TNoRw.getText()+"','lab1','J000030','"+Valid.SetTgl((String) DTPReg.getSelectedItem())+"','"+CmbJam.getSelectedItem()+":"+CmbMenit.getSelectedItem()+":"+CmbDetik.getSelectedItem()+"','DR00017','0','0','0','0','0','0','0','11000','DR00017','Ralan'), "
           /*planotest*/  + "('"+TNoRw.getText()+"','lab1','J000002','"+Valid.SetTgl((String) DTPReg.getSelectedItem())+"','"+CmbJam.getSelectedItem()+":"+CmbMenit.getSelectedItem()+":"+CmbDetik.getSelectedItem()+"','DR00017','0','0','0','0','0','0','0','24500','DR00017','Ralan') "    
                          + "";
           PreparedStatement pst_lab_banyak = koneksiDB.condb().prepareStatement(lab_bnyk);          
           pst_lab_banyak.execute();
           
           String hari_jadwal, jadwal_dr_jantung;
           //cari hari ini
           hari_jadwal = Sequel.cariIsi("SELECT CASE  DAYNAME(tgl_registrasi) WHEN 'Sunday' THEN 'MINGGU' " +
                                        "WHEN 'Monday' THEN 'SENIN' WHEN 'Tuesday' THEN 'SELASA' " +
                                        "WHEN 'Wednesday' THEN 'RABU' WHEN 'Thursday' THEN 'KAMIS' " +
                                        "WHEN 'Friday' THEN 'JUMAT' WHEN 'Saturday' THEN 'SABTU' END as hari " +
                                        "FROM reg_periksa WHERE tgl_registrasi = ? ",Valid.SetTgl((String) DTPReg.getSelectedItem()));           
           
            //cari data jadwal dokter jantung berdasarkan hari
            jadwal_dr_jantung = Sequel.cariIsi("select kd_dokter from jadwal where hari_kerja ='"+hari_jadwal+"' and kd_poli ='U0012' ");
            //TCari.setText(jadwal_dr_jantung);
           String bc_ekg;
           bc_ekg = "insert into rawat_jl_dr (no_rawat,kd_jenis_prw,kd_dokter,tgl_perawatan,jam_rawat,material,bhp,tarif_tindakandr,biaya_rawat) VALUES ('"+TNoRw.getText()+"','J000189','"+jadwal_dr_jantung+"','"+Valid.SetTgl((String) DTPReg.getSelectedItem())+"','"+CmbJam.getSelectedItem()+":"+CmbMenit.getSelectedItem()+":"+CmbDetik.getSelectedItem()+"','0','0','21000','21000')";
           PreparedStatement pst_ek = koneksiDB.condb().prepareStatement(bc_ekg);          
           pst_ek.execute();
           
           //tindakan ekg sementara pakai paramedis VCT, harusnya paramedis MCU
           String ekg_mcu;
           ekg_mcu ="insert into rawat_jl_pr (no_rawat,kd_jenis_prw,nip,tgl_perawatan,jam_rawat,material,bhp,tarif_tindakanpr,biaya_rawat) VALUES ('"+TNoRw.getText()+"','J000301','unit42','"+Valid.SetTgl((String) DTPReg.getSelectedItem())+"','"+CmbJam.getSelectedItem()+":"+CmbMenit.getSelectedItem()+":"+CmbDetik.getSelectedItem()+"','0','0','40000','40000')";
           PreparedStatement pst_ekg_mcu = koneksiDB.condb().prepareStatement(ekg_mcu);          
           pst_ekg_mcu.execute();
           
           String thorax;
           thorax = "insert into periksa_radiologi (no_rawat,nip,kd_jenis_prw,tgl_periksa,jam,dokter_perujuk,biaya,kd_dokter,bagian_rs,bhp,tarif_perujuk,tarif_tindakan_dokter,tarif_tindakan_petugas) values ('"+TNoRw.getText()+"','rad1','J000010','"+Valid.SetTgl((String) DTPReg.getSelectedItem())+"','"+CmbJam.getSelectedItem()+":"+CmbMenit.getSelectedItem()+":"+CmbDetik.getSelectedItem()+"','DR00019','77000','DR00019','0','0','0','0','0')";
           PreparedStatement pst_thorax = koneksiDB.condb().prepareStatement(thorax);          
           pst_thorax.execute();
           
           String bc_thorax;
           bc_thorax = "insert into rawat_jl_dr (no_rawat,kd_jenis_prw,kd_dokter,tgl_perawatan,jam_rawat,material,bhp,tarif_tindakandr,biaya_rawat) VALUES ('"+TNoRw.getText()+"','J000180','DR00019','"+Valid.SetTgl((String) DTPReg.getSelectedItem())+"','"+CmbJam.getSelectedItem()+":"+CmbMenit.getSelectedItem()+":"+CmbDetik.getSelectedItem()+"','0','0','21000','21000')";
           PreparedStatement pst_bc_thorax = koneksiDB.condb().prepareStatement(bc_thorax);          
           pst_bc_thorax.execute();
           JOptionPane.showMessageDialog(rootPane,"Pian sukses menyimpan paket CJH Perempuan..");
           
            } catch (Exception e) {
                System.out.println(e);
            }
        }  
    }//GEN-LAST:event_MnPaketHajiPerempuanActionPerformed

    private void BtnSimpan2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpan2ActionPerformed
      String a;
      a=Sequel.cariIsi("select interpretasi from interpretasi_ekg where no_rawat="+TNoRwint.getText().toString());
      if(TNoRwint.getText().trim().equals("")){
            JOptionPane.showMessageDialog(null,"Maaf, Silahkan anda pilih dulu pasien...!!!");
        
      }else if(Tind.getText().trim().equals("")){
           
           try {
           String interpretasi;
           interpretasi = "insert into interpretasi_ekg values ('"+TNoRwint.getText().toString()+"','"+TKesan.getText().toString()+"')"; 
           PreparedStatement pst_int = koneksiDB.condb().prepareStatement(interpretasi);          
           pst_int.execute();
           Tind.setText(Sequel.cariIsi("select interpretasi from interpretasi_ekg where no_rawat= '"+TNoRwint.getText()+"'"));
           JOptionPane.showMessageDialog(rootPane,"Pian sukses menyimpan data..");
            } catch (Exception e) {
                System.out.println(e);
            }
        }else{
        try {
           String interpretasi;
           interpretasi = "update interpretasi_ekg set interpretasi = '"+TKesan.getText()+"' where no_rawat = '"+TNoRwint.getText()+"'"; 
           PreparedStatement pst_int = koneksiDB.condb().prepareStatement(interpretasi);          
           pst_int.execute(); 
           JOptionPane.showMessageDialog(rootPane,"Pian sukses mengubah data..");
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }//GEN-LAST:event_BtnSimpan2ActionPerformed

    private void BtnSimpan2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSimpan2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnSimpan2KeyPressed

    private void BtnKeluar5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluar5ActionPerformed
        //TKesan.setText("");
        //dispose();
        DlgInterpretasiEKG.dispose();
        
    }//GEN-LAST:event_BtnKeluar5ActionPerformed

    private void BtnKeluar5KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluar5KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnKeluar5KeyPressed

    private void TNoRMEkgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TNoRMEkgActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TNoRMEkgActionPerformed

    private void TKesanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TKesanKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TKesanKeyPressed

    private void BtnPrint1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrint1ActionPerformed
        // gasan cetak interpretasi
        Map<String, Object> param = new HashMap<>();
                    param.put("norm",Sequel.cariIsi("select no_rkm_medis from reg_periksa where no_rawat=?",TNoRwint.getText()));
                    param.put("nama",Sequel.cariIsi("select nm_pasien from reg_periksa,pasien where reg_periksa.no_rkm_medis=pasien.no_rkm_medis and reg_periksa.no_rawat=?",TNoRwint.getText()));
                    param.put("umur",Sequel.cariIsi("select umur from pasien where no_rkm_medis=?",TNoRMint.getText()) + " / "+Sequel.cariIsi("select tmp_lahir from reg_periksa, pasien where reg_periksa.no_rkm_medis = pasien.no_rkm_medis and reg_periksa.no_rawat =  ?",TNoRwint.getText())+", "+Sequel.cariIsi("select  DATE_FORMAT(pasien.tgl_lahir, '%d-%m-%Y') from reg_periksa, pasien where reg_periksa.no_rkm_medis = pasien.no_rkm_medis and reg_periksa.no_rawat =  ?",TNoRwint.getText()));
                    param.put("alamat",Sequel.cariIsi("SELECT concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab) as alamat FROM reg_periksa INNER JOIN pasien INNER JOIN kelurahan INNER JOIN kecamatan INNER JOIN kabupaten ON " +
                                                      "reg_periksa.no_rkm_medis = pasien.no_rkm_medis and pasien.kd_kel=kelurahan.kd_kel and " +
                                                      "kecamatan.kd_kec=pasien.kd_kec AND pasien.kd_kab=kabupaten.kd_kab WHERE " +
                                                      "	reg_periksa.no_rawat=?",TNoRwint.getText()));
                    param.put("interpretasi",TKesan.getText());
                    
                    param.put("namars",var.getnamars());
                    param.put("alamatrs",var.getalamatrs());
                    param.put("kotars",var.getkabupatenrs());
                    param.put("propinsirs",var.getpropinsirs());
                    param.put("kontakrs",var.getkontakrs());
                    param.put("emailrs",var.getemailrs());   
                    param.put("logo",Sequel.cariGambar("select logo from setting")); 
                    
                    Valid.MyReport("rptInterpretasiEKG1.jrxml","report","::[ Interpretasi EKG ]::","select * from interpretasi_ekg limit 1",param);
    }//GEN-LAST:event_BtnPrint1ActionPerformed

    private void BtnPrint1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnPrint1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnPrint1KeyPressed

    private void MnInterpretasiEkgBtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnInterpretasiEkgBtnPrintActionPerformed
       if(tabMode.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Maaf, table masih kosong...!!!!");
            TNoReg.requestFocus();
        }else if(TPasien.getText().trim().equals("")){
            JOptionPane.showMessageDialog(null,"Maaf, Silahkan anda pilih dulu dengan menklik data pada table...!!!");
            tbPetugas.requestFocus();
        }else{
                DlgInterpretasiEKG.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
                DlgInterpretasiEKG.setLocationRelativeTo(internalFrame1);
                DlgInterpretasiEKG.setVisible(true);
                TNoRwint.setText(TNoRw.getText());
                TNoRMint.setText(TNoRM.getText());
                TPasienint.setText(TPasien.getText());
                TKesan.setText(Sequel.cariIsi("select interpretasi from interpretasi_ekg where no_rawat= '"+TNoRwint.getText()+"'"));  
                // ini gasan pengkondisian di form interpretasi, jika isian kosong maka simpan, jika tidak ubah
                Tind.setVisible(false);
                Tind.setText(Sequel.cariIsi("select interpretasi from interpretasi_ekg where no_rawat= '"+TNoRwint.getText()+"'"));
                // ini gasan pengkondisian di form interpretasi, jika isian kosong maka simpan, jika tidak ubah
                
        }
    }//GEN-LAST:event_MnInterpretasiEkgBtnPrintActionPerformed

    private void MnInterpretasiEkg2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnInterpretasiEkg2ActionPerformed
        if(tabMode.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Maaf, table masih kosong...!!!!");
            TNoReg.requestFocus();
        }else if(TPasien.getText().trim().equals("")){
            JOptionPane.showMessageDialog(null,"Maaf, Silahkan anda pilih dulu dengan menklik data pada table...!!!");
            tbPetugas.requestFocus();
        }else{
                DlgInterpretasiEKG.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
                DlgInterpretasiEKG.setLocationRelativeTo(internalFrame1);
                DlgInterpretasiEKG.setVisible(true);
                TNoRwint.setText(tbPetugas2.getValueAt(tbPetugas2.getSelectedRow(),1).toString());
                TNoRMint.setText(tbPetugas2.getValueAt(tbPetugas2.getSelectedRow(),6).toString());
                TPasienint.setText(tbPetugas2.getValueAt(tbPetugas2.getSelectedRow(),7).toString());
                TKesan.setText(Sequel.cariIsi("select interpretasi from interpretasi_ekg where no_rawat= '"+TNoRwint.getText()+"'"));  
                // ini gasan pengkondisian di form interpretasi, jika isian kosong maka simpan, jika tidak ubah
                Tind.setVisible(false);
                Tind.setText(Sequel.cariIsi("select interpretasi from interpretasi_ekg where no_rawat= '"+TNoRwint.getText()+"'"));
                // ini gasan pengkondisian di form interpretasi, jika isian kosong maka simpan, jika tidak ubah
                
    }//GEN-LAST:event_MnInterpretasiEkg2ActionPerformed
  }
    private void MnJawabKonsulActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnJawabKonsulActionPerformed
                DlgJawabKonsul dlgjk=new DlgJawabKonsul(null,false);
                dlgjk.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
                dlgjk.setLocationRelativeTo(internalFrame1);
                dlgjk.setDataPasien(tbPetugas2.getValueAt(tbPetugas2.getSelectedRow(),1).toString(), tbPetugas2.getValueAt(tbPetugas2.getSelectedRow(),6).toString(), tbPetugas2.getValueAt(tbPetugas2.getSelectedRow(),7).toString(), tbPetugas2.getValueAt(tbPetugas2.getSelectedRow(),4).toString(),Sequel.cariIsi("select nm_dokter from reg_periksa, dokter WHERE reg_periksa.kd_dokter = dokter.kd_dokter AND reg_periksa.no_rawat='"+tbPetugas2.getValueAt(tbPetugas2.getSelectedRow(),1).toString()+"'"),tbPetugas2.getValueAt(tbPetugas2.getSelectedRow(),11).toString(),Sequel.cariIsi("select konsul from rujukan_internal_poli_detail where no_rawat= '"+tbPetugas2.getValueAt(tbPetugas2.getSelectedRow(),1).toString()+"'"));
                dlgjk.setVisible(true);
                
    }//GEN-LAST:event_MnJawabKonsulActionPerformed

    private void MnJawabanKonsulActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnJawabanKonsulActionPerformed
        DlgJawabanKonsul.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
        DlgJawabanKonsul.setLocationRelativeTo(internalFrame1);
        //DlgJawabanKonsul.setDataPasien(tbPetugas2.getValueAt(tbPetugas2.getSelectedRow(),1).toString(), tbPetugas2.getValueAt(tbPetugas2.getSelectedRow(),6).toString(), tbPetugas2.getValueAt(tbPetugas2.getSelectedRow(),7).toString(), tbPetugas2.getValueAt(tbPetugas2.getSelectedRow(),4).toString(),Sequel.cariIsi("select nm_dokter from reg_periksa, dokter WHERE reg_periksa.kd_dokter = dokter.kd_dokter AND reg_periksa.no_rawat='"+tbPetugas2.getValueAt(tbPetugas2.getSelectedRow(),1).toString()+"'"),tbPetugas2.getValueAt(tbPetugas2.getSelectedRow(),11).toString(),Sequel.cariIsi("select konsul from rujukan_internal_poli_detail where no_rawat= '"+tbPetugas2.getValueAt(tbPetugas2.getSelectedRow(),1).toString()+"'"));
        LabelNama.setText(tbPetugas.getValueAt(tbPetugas.getSelectedRow(),8).toString());
        LabelNoRM.setText(TNoRM.getText());
        LabelNoRW.setText(TNoRw.getText());
        LabelDokter.setText(TDokter.getText());
        LabelPoliPerujuk.setText(TPoli.getText());
        LabelDokterTujuan.setText(Sequel.cariIsi("select dokter.nm_dokter from rujukan_internal_poli, dokter where rujukan_internal_poli.kd_dokter = dokter.kd_dokter AND no_rawat = '"+TNoRw.getText()+"'"));
        LabelPoliTujuan.setText(Sequel.cariIsi("select poliklinik.nm_poli from rujukan_internal_poli, poliklinik where rujukan_internal_poli.kd_poli = poliklinik.kd_poli AND no_rawat = '"+TNoRw.getText()+"'"));
        TKonsulJawab.setText(Sequel.cariIsi("select konsul from rujukan_internal_poli_detail where no_rawat= '"+TNoRw.getText()+"'"));
        TPemeriksaanJawab.setText(Sequel.cariIsi("select pemeriksaan from rujukan_internal_poli_detail where no_rawat= '"+TNoRw.getText()+"'"));
        LabelDiagnosa.setText(Sequel.cariIsi("select diagnosa from rujukan_internal_poli_detail where no_rawat= '"+TNoRw.getText()+"'"));
        TSaranJawab.setText(Sequel.cariIsi("select saran from rujukan_internal_poli_detail where no_rawat= '"+TNoRw.getText()+"'"));
        DlgJawabanKonsul.setVisible(true);
    }//GEN-LAST:event_MnJawabanKonsulActionPerformed

    private void BtnKeluar1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluar1KeyPressed

    }//GEN-LAST:event_BtnKeluar1KeyPressed

    private void BtnKeluar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluar1ActionPerformed
        dispose();
    }//GEN-LAST:event_BtnKeluar1ActionPerformed

    private void BtnSimpan1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSimpan1KeyPressed

    }//GEN-LAST:event_BtnSimpan1KeyPressed

    private void BtnSimpan1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpan1ActionPerformed
        try {
            String no_tlp=TNoHp.getText();
            String no_rm=TNoRM.getText();

            String str;
            str = "update pasien set no_tlp = '"+no_tlp+"' where no_rkm_medis ='"+no_rm+"'";
            PreparedStatement pst = koneksiDB.condb().prepareStatement(str);
            pst.execute();
        } catch (Exception e) {
            System.out.println(e);
        }
        //JOptionPane.showMessageDialog(rootPane,"Nomor handphone pasien sudah disimpan..!!");
        // data_refresh.BtnCari.setActionCommand(true);
        //data_refresh.tampil();
        //DlgReg.
        TNoRM.setText("");
        TPasien1.setText("");
        TNoHp.setText("");

        //data_refresh.tampil_ambil();

        dispose();
    }//GEN-LAST:event_BtnSimpan1ActionPerformed

    private void TKonsulJawabKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TKonsulJawabKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TKonsulJawabKeyPressed

    private void TSaranJawabKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TSaranJawabKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TSaranJawabKeyPressed

    private void TPemeriksaanJawabKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TPemeriksaanJawabKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TPemeriksaanJawabKeyPressed

    private void BtnKeluar6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluar6ActionPerformed
          DlgJawabanKonsul.dispose();
    }//GEN-LAST:event_BtnKeluar6ActionPerformed

    private void BtnKeluar6KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluar6KeyPressed
       
    }//GEN-LAST:event_BtnKeluar6KeyPressed
        
    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            DlgReg dialog = new DlgReg(new javax.swing.JFrame(), true);
            dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosing(java.awt.event.WindowEvent e) {
                    System.exit(0);
                }
            });
            dialog.setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private widget.TextBox AsalRujukan;
    private widget.Button BtnAll;
    private widget.Button BtnBatal;
    public widget.Button BtnCari;
    private widget.Button BtnDokter;
    private widget.Button BtnEdit;
    private widget.Button BtnHapus;
    private widget.Button BtnKeluar;
    private widget.Button BtnKeluar1;
    private widget.Button BtnKeluar2;
    private widget.Button BtnKeluar3;
    private widget.Button BtnKeluar4;
    private widget.Button BtnKeluar5;
    private widget.Button BtnKeluar6;
    private widget.Button BtnPasien;
    private widget.Button BtnPrint;
    private widget.Button BtnPrint1;
    private widget.Button BtnPrint2;
    private widget.Button BtnPrint3;
    private widget.Button BtnPrint4;
    private widget.Button BtnPrint5;
    private widget.Button BtnSeek3;
    private widget.Button BtnSeek4;
    private widget.Button BtnSeek5;
    private widget.Button BtnSimpan;
    private widget.Button BtnSimpan1;
    private widget.Button BtnSimpan2;
    private widget.Button BtnUnit;
    private widget.CekBox ChkInput;
    private widget.CekBox ChkJln;
    private widget.CekBox ChkTracker;
    private widget.ComboBox CmbDetik;
    private widget.ComboBox CmbJam;
    private widget.ComboBox CmbMenit;
    private widget.TextBox CrDokter;
    private widget.TextBox CrDokter3;
    private widget.TextBox CrPoli;
    private widget.Tanggal DTPCari1;
    private widget.Tanggal DTPCari2;
    private widget.Tanggal DTPReg;
    private javax.swing.JDialog DlgCatatan;
    private javax.swing.JDialog DlgDemografi;
    private javax.swing.JDialog DlgInputNoHp;
    private javax.swing.JDialog DlgInterpretasiEKG;
    private javax.swing.JDialog DlgJawabanKonsul;
    private javax.swing.JDialog DlgSakit;
    private javax.swing.JDialog DlgSakit2;
    private widget.PanelBiasa FormInput;
    private widget.TextBox Kabupaten2;
    private widget.TextBox Kd2;
    private widget.TextBox Kecamatan2;
    private widget.TextBox Kelurahan2;
    private widget.Label LCount;
    private widget.Label LabelCatatan;
    private widget.Label LabelDiagnosa;
    private widget.Label LabelDokter;
    private widget.Label LabelDokterTujuan;
    public widget.Label LabelNama;
    private widget.Label LabelNoRM;
    private widget.Label LabelNoRW;
    private widget.Label LabelPoliPerujuk;
    private widget.Label LabelPoliTujuan;
    private javax.swing.JMenu MenuInputData;
    private javax.swing.JMenu MenuInputData1;
    private javax.swing.JMenuItem MnBarcode;
    private javax.swing.JMenuItem MnBarcode1;
    private javax.swing.JMenuItem MnBarcodeRM9;
    private javax.swing.JMenuItem MnBatal;
    private javax.swing.JMenuItem MnBelum;
    private javax.swing.JMenuItem MnBilling;
    private javax.swing.JMenuItem MnBilling1;
    private javax.swing.JMenuItem MnBillingParsial;
    private javax.swing.JMenuItem MnBlangkoResep;
    private javax.swing.JMenu MnBridging;
    private javax.swing.JMenuItem MnBuktiPelayananRalan;
    private javax.swing.JMenuItem MnCetakBebasNarkoba;
    private javax.swing.JMenuItem MnCetakRegister;
    private javax.swing.JMenuItem MnCetakRegister1;
    private javax.swing.JMenuItem MnCetakRegister2;
    private javax.swing.JMenuItem MnCetakSuratSakit;
    private javax.swing.JMenuItem MnCetakSuratSakit1;
    private javax.swing.JMenuItem MnCetakSuratSakit2;
    private javax.swing.JMenuItem MnCetakSuratSakit3;
    private javax.swing.JMenuItem MnCetakSuratSehat;
    private javax.swing.JMenuItem MnCetakSuratSehat1;
    private javax.swing.JMenuItem MnCetakSuratSehat2;
    private javax.swing.JMenuItem MnCheckList;
    private javax.swing.JMenuItem MnCheckList1;
    private javax.swing.JMenuItem MnCheckList2;
    private javax.swing.JMenuItem MnCheckList3;
    private javax.swing.JMenuItem MnCheckList4;
    private javax.swing.JMenuItem MnCheckList5;
    private javax.swing.JMenuItem MnCheckList6;
    private javax.swing.JMenuItem MnCheckList7;
    private javax.swing.JMenuItem MnDIrawat;
    private javax.swing.JMenuItem MnDataSuratSehat;
    private javax.swing.JMenuItem MnDiagnosa;
    private javax.swing.JMenuItem MnDiagnosa1;
    private javax.swing.JMenuItem MnDirujuk;
    private javax.swing.JMenuItem MnGelang1;
    private javax.swing.JMenuItem MnGelang2;
    private javax.swing.JMenuItem MnGelang3;
    private javax.swing.JMenuItem MnGelang4;
    private javax.swing.JMenuItem MnGelang5;
    private javax.swing.JMenuItem MnGelang6;
    private javax.swing.JMenuItem MnGelang7;
    private javax.swing.JMenu MnHapusData;
    private javax.swing.JMenuItem MnHapusObatOperasi;
    private javax.swing.JMenuItem MnHapusRujukan;
    private javax.swing.JMenuItem MnHapusTagihanOperasi;
    private javax.swing.JMenuItem MnHemodialisa;
    private javax.swing.JMenuItem MnInputHP;
    private javax.swing.JMenuItem MnInterpretasiEkg;
    private javax.swing.JMenuItem MnInterpretasiEkg2;
    private javax.swing.JMenuItem MnJKRA;
    private javax.swing.JMenuItem MnJadwalOperasi;
    private javax.swing.JMenuItem MnJadwalOperasi1;
    private javax.swing.JMenuItem MnJawabKonsul;
    private javax.swing.JMenuItem MnJawabanKonsul;
    private javax.swing.JMenuItem MnKamarInap;
    private javax.swing.JMenuItem MnKamarInap1;
    private javax.swing.JMenuItem MnLabelTracker;
    private javax.swing.JMenuItem MnLabelTracker1;
    private javax.swing.JMenuItem MnLabelTracker2;
    private javax.swing.JMenuItem MnLabelTracker3;
    private javax.swing.JMenuItem MnLaporanRekapJenisBayar;
    private javax.swing.JMenuItem MnLaporanRekapKunjunganBulanan;
    private javax.swing.JMenuItem MnLaporanRekapKunjunganBulananPoli;
    private javax.swing.JMenuItem MnLaporanRekapKunjunganDokter;
    private javax.swing.JMenuItem MnLaporanRekapKunjunganPoli;
    private javax.swing.JMenuItem MnLaporanRekapPenyakitRalan;
    private javax.swing.JMenuItem MnLaporanRekapPerujuk;
    private javax.swing.JMenuItem MnLaporanRekapRawatDarurat;
    private javax.swing.JMenuItem MnLembarCasemix;
    private javax.swing.JMenuItem MnLembarCasemix1;
    private javax.swing.JMenuItem MnLembarCasemix2;
    private javax.swing.JMenuItem MnLembarRalan;
    private javax.swing.JMenu MnMCU;
    private javax.swing.JMenuItem MnMeninggal;
    private javax.swing.JMenuItem MnNoResep;
    private javax.swing.JMenuItem MnNoResep1;
    private javax.swing.JMenu MnObat;
    private javax.swing.JMenu MnObat1;
    private javax.swing.JMenuItem MnOperasi;
    private javax.swing.JMenuItem MnOperasi1;
    private javax.swing.JMenuItem MnPCare;
    private javax.swing.JMenuItem MnPaketHajiLaki;
    private javax.swing.JMenuItem MnPaketHajiPerempuan;
    private javax.swing.JMenuItem MnPaketMCU;
    private javax.swing.JMenuItem MnPaketMMPI;
    private javax.swing.JMenuItem MnPaketNapza;
    private javax.swing.JMenuItem MnPemberianObat;
    private javax.swing.JMenuItem MnPemberianObat1;
    private javax.swing.JMenuItem MnPeriksaLab;
    private javax.swing.JMenuItem MnPeriksaLab1;
    private javax.swing.JMenuItem MnPeriksaRadiologi;
    private javax.swing.JMenuItem MnPeriksaRadiologi1;
    private javax.swing.JMenu MnPermintaan;
    private javax.swing.JMenu MnPermintaan1;
    private javax.swing.JMenuItem MnPermintaanLab;
    private javax.swing.JMenuItem MnPermintaanLab1;
    private javax.swing.JMenuItem MnPermintaanRadiologi;
    private javax.swing.JMenuItem MnPermintaanRadiologi1;
    private javax.swing.JMenuItem MnPersetujuanMedis;
    private javax.swing.JMenu MnPilihBilling;
    private javax.swing.JMenuItem MnPoliInternal;
    private javax.swing.JMenuItem MnPulangPaksa;
    private javax.swing.JMenuItem MnRawatJalan;
    private javax.swing.JMenuItem MnRawatJalan1;
    private javax.swing.JMenuItem MnResepDOkter;
    private javax.swing.JMenuItem MnResepDOkter1;
    private javax.swing.JMenuItem MnRiwayatObatRacikan;
    private javax.swing.JMenuItem MnRujuk;
    private javax.swing.JMenuItem MnRujukMasuk;
    private javax.swing.JMenuItem MnRujukSisrute;
    private javax.swing.JMenu MnRujukan;
    private javax.swing.JMenuItem MnSEP;
    private javax.swing.JMenuItem MnSJP;
    private javax.swing.JMenuItem MnSKDPBPJS;
    private javax.swing.JMenuItem MnSKDPBPJS1;
    private javax.swing.JMenuItem MnSPBK;
    private javax.swing.JMenuItem MnSPBK1;
    private javax.swing.JMenu MnStatus;
    private javax.swing.JMenuItem MnStatusBaru;
    private javax.swing.JMenuItem MnStatusLama;
    private javax.swing.JMenuItem MnSudah;
    private javax.swing.JMenuItem MnTeridentifikasiTB;
    private javax.swing.JMenuItem MnTeridentifikasiTB1;
    private javax.swing.JMenu MnTindakan;
    private javax.swing.JMenu MnTindakan1;
    private javax.swing.JMenu MnUrut;
    private javax.swing.JMenu MnUrut1;
    private javax.swing.JMenuItem MnUrutDokterAsc;
    private javax.swing.JMenuItem MnUrutDokterAsc1;
    private javax.swing.JMenuItem MnUrutDokterDesc;
    private javax.swing.JMenuItem MnUrutDokterDesc1;
    private javax.swing.JMenuItem MnUrutNoRawatAsc;
    private javax.swing.JMenuItem MnUrutNoRawatAsc1;
    private javax.swing.JMenuItem MnUrutNoRawatDesc;
    private javax.swing.JMenuItem MnUrutNoRawatDesc1;
    private javax.swing.JMenuItem MnUrutPenjabAsc;
    private javax.swing.JMenuItem MnUrutPenjabAsc1;
    private javax.swing.JMenuItem MnUrutPenjabDesc;
    private javax.swing.JMenuItem MnUrutPenjabDesc1;
    private javax.swing.JMenuItem MnUrutPoliklinikAsc;
    private javax.swing.JMenuItem MnUrutPoliklinikAsc1;
    private javax.swing.JMenuItem MnUrutPoliklinikDesc;
    private javax.swing.JMenuItem MnUrutPoliklinikDesc1;
    private javax.swing.JMenuItem MnUrutRegAsc1;
    private javax.swing.JMenuItem MnUrutRegDesc1;
    private javax.swing.JMenuItem MnUrutStatusAsc;
    private javax.swing.JMenuItem MnUrutStatusAsc1;
    private javax.swing.JMenuItem MnUrutStatusDesc;
    private javax.swing.JMenuItem MnUrutStatusDesc1;
    private javax.swing.JMenuItem MnUrutTanggalAsc;
    private javax.swing.JMenuItem MnUrutTanggalAsc1;
    private javax.swing.JMenuItem MnUrutTanggalDesc;
    private javax.swing.JMenuItem MnUrutTanggalDesc1;
    private widget.TextBox NoBalasan;
    private widget.TextBox NomorSurat;
    private javax.swing.JPanel PanelInput;
    private widget.ScrollPane Scroll;
    private widget.ScrollPane Scroll1;
    private widget.ScrollPane Scroll2;
    private widget.TextBox TAlmt;
    private widget.TextBox TBiaya;
    private widget.TextBox TCari;
    private widget.TextBox TDokter;
    private widget.TextBox THbngn;
    private widget.TextArea TKesan;
    private widget.TextArea TKonsulJawab;
    private widget.TextBox TNoHp;
    private widget.TextBox TNoRM;
    public widget.TextBox TNoRM1;
    public widget.TextBox TNoRMint;
    private widget.TextBox TNoReg;
    private widget.TextBox TNoRw;
    private widget.TextBox TNoRwint;
    private widget.TextBox TPasien;
    private widget.TextBox TPasien1;
    private widget.TextBox TPasienint;
    private widget.TextArea TPemeriksaanJawab;
    private widget.TextBox TPngJwb;
    private widget.TextBox TPoli;
    private widget.TextArea TSaranJawab;
    private widget.TextBox TStatus;
    private javax.swing.JTabbedPane TabRawat;
    private widget.Tanggal TglSakit1;
    private widget.Tanggal TglSakit2;
    public widget.TextBox Tind;
    private widget.Button btnKab;
    private widget.Button btnKec;
    private widget.Button btnKel;
    private widget.Button btnPenjab;
    private widget.Button btnPenjab1;
    private javax.swing.ButtonGroup buttonGroup1;
    private widget.InternalFrame internalFrame1;
    private widget.InternalFrame internalFrame2;
    private widget.InternalFrame internalFrame3;
    private widget.InternalFrame internalFrame4;
    private widget.InternalFrame internalFrame5;
    private widget.InternalFrame internalFrame6;
    private widget.InternalFrame internalFrame7;
    private widget.InternalFrame internalFrame8;
    private widget.Label jLabel10;
    private widget.Label jLabel11;
    private widget.Label jLabel12;
    private widget.Label jLabel13;
    private widget.Label jLabel14;
    private widget.Label jLabel15;
    private widget.Label jLabel16;
    private widget.Label jLabel17;
    private widget.Label jLabel18;
    private widget.Label jLabel19;
    private widget.Label jLabel20;
    private widget.Label jLabel21;
    private widget.Label jLabel22;
    private widget.Label jLabel23;
    private widget.Label jLabel24;
    private widget.Label jLabel25;
    private widget.Label jLabel26;
    private widget.Label jLabel27;
    private widget.Label jLabel28;
    private widget.Label jLabel29;
    private widget.Label jLabel3;
    private widget.Label jLabel30;
    private widget.Label jLabel31;
    private widget.Label jLabel32;
    private widget.Label jLabel33;
    private widget.Label jLabel34;
    private widget.Label jLabel35;
    private widget.Label jLabel36;
    private widget.Label jLabel37;
    private widget.Label jLabel38;
    private widget.Label jLabel39;
    private widget.Label jLabel4;
    private widget.Label jLabel40;
    private widget.Label jLabel41;
    private widget.Label jLabel42;
    private widget.Label jLabel43;
    private widget.Label jLabel44;
    private widget.Label jLabel45;
    private widget.Label jLabel46;
    private widget.Label jLabel5;
    private widget.Label jLabel6;
    private widget.Label jLabel7;
    private widget.Label jLabel8;
    private widget.Label jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenu jMenu7;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JPopupMenu jPopupMenu2;
    private javax.swing.JPopupMenu.Separator jSeparator10;
    private javax.swing.JPopupMenu.Separator jSeparator11;
    private javax.swing.JPopupMenu.Separator jSeparator12;
    private widget.TextBox kddokter;
    private widget.TextBox kdpnj;
    private widget.TextBox kdpoli;
    private widget.TextBox lmsakit;
    private widget.TextBox nmpnj;
    private widget.PanelBiasa panelBiasa2;
    private widget.PanelBiasa panelBiasa3;
    private widget.PanelBiasa panelBiasa4;
    private widget.panelisi panelGlass6;
    private widget.panelisi panelGlass7;
    private widget.panelisi panelGlass8;
    private javax.swing.JMenuItem ppBerkas;
    private javax.swing.JMenuItem ppBerkasDigital;
    private javax.swing.JMenuItem ppBerkasDigital1;
    private javax.swing.JMenuItem ppCatatanPasien;
    private javax.swing.JMenuItem ppGrafikDemografi;
    private javax.swing.JMenuItem ppGrafikPerAgama;
    private javax.swing.JMenuItem ppGrafikPerBulan;
    private javax.swing.JMenuItem ppGrafikPerJK;
    private javax.swing.JMenuItem ppGrafikPerPekerjaan;
    private javax.swing.JMenuItem ppGrafikPerTahun;
    private javax.swing.JMenuItem ppGrafikPerTanggal;
    private javax.swing.JMenuItem ppGrafikPerdokter;
    private javax.swing.JMenuItem ppGrafikPerdokter1;
    private javax.swing.JMenuItem ppGrafikPerdokter2;
    private javax.swing.JMenuItem ppGrafikPerpoli;
    private javax.swing.JMenuItem ppGrafikPerpoli1;
    private javax.swing.JMenuItem ppGrafikPerpoli2;
    private javax.swing.JMenuItem ppIKP;
    private javax.swing.JMenuItem ppIKP1;
    private javax.swing.JMenuItem ppRiwayat;
    private javax.swing.JMenuItem ppRiwayat1;
    private widget.ScrollPane scrollPane1;
    private widget.Table tbPetugas;
    private widget.Table tbPetugas1;
    private widget.Table tbPetugas2;
    // End of variables declaration//GEN-END:variables
    public void tampil_ambil() {
        Valid.tabelKosong(tabMode);   
        try {
            ps=koneksi.prepareStatement("select reg_periksa.no_reg,reg_periksa.no_rawat,reg_periksa.tgl_registrasi,reg_periksa.jam_reg,"+
                "reg_periksa.kd_dokter,dokter.nm_dokter,reg_periksa.no_rkm_medis,pasien.nm_pasien,pasien.jk,concat(reg_periksa.umurdaftar,' ',reg_periksa.sttsumur)as umur,poliklinik.nm_poli,"+
                "reg_periksa.p_jawab,reg_periksa.almt_pj,reg_periksa.hubunganpj,reg_periksa.biaya_reg,reg_periksa.stts_daftar,penjab.png_jawab,pasien.no_tlp,reg_periksa.stts,reg_periksa.status_poli, "+
                "reg_periksa.kd_poli,reg_periksa.kd_pj from reg_periksa inner join dokter inner join pasien inner join poliklinik inner join penjab "+
                "on reg_periksa.kd_dokter=dokter.kd_dokter and reg_periksa.no_rkm_medis=pasien.no_rkm_medis "+
                "and reg_periksa.kd_pj=penjab.kd_pj and reg_periksa.kd_poli=poliklinik.kd_poli  where  "+
                " poliklinik.kd_poli<>'IGDK' and poliklinik.nm_poli like ? and  dokter.nm_dokter like ? and tgl_registrasi between ? and ? and  reg_periksa.no_reg like ? or "+
                " poliklinik.kd_poli<>'IGDK' and poliklinik.nm_poli like ? and  dokter.nm_dokter like ? and tgl_registrasi between ? and ? and  reg_periksa.no_rawat like ? or "+
                " poliklinik.kd_poli<>'IGDK' and poliklinik.nm_poli like ? and  dokter.nm_dokter like ? and tgl_registrasi between ? and ? and  reg_periksa.tgl_registrasi like ? or "+
                " poliklinik.kd_poli<>'IGDK' and poliklinik.nm_poli like ? and  dokter.nm_dokter like ? and tgl_registrasi between ? and ? and  reg_periksa.kd_dokter like ? or "+
                " poliklinik.kd_poli<>'IGDK' and poliklinik.nm_poli like ? and  dokter.nm_dokter like ? and tgl_registrasi between ? and ? and  dokter.nm_dokter like ? or "+
                " poliklinik.kd_poli<>'IGDK' and poliklinik.nm_poli like ? and  dokter.nm_dokter like ? and tgl_registrasi between ? and ? and  reg_periksa.no_rkm_medis like ? or "+
                " poliklinik.kd_poli<>'IGDK' and poliklinik.nm_poli like ? and  dokter.nm_dokter like ? and tgl_registrasi between ? and ? and  reg_periksa.stts_daftar like ? or "+
                " poliklinik.kd_poli<>'IGDK' and poliklinik.nm_poli like ? and  dokter.nm_dokter like ? and tgl_registrasi between ? and ? and  pasien.nm_pasien like ? or "+
                " poliklinik.kd_poli<>'IGDK' and poliklinik.nm_poli like ? and  dokter.nm_dokter like ? and tgl_registrasi between ? and ? and  poliklinik.nm_poli like ? or "+
                " poliklinik.kd_poli<>'IGDK' and poliklinik.nm_poli like ? and  dokter.nm_dokter like ? and tgl_registrasi between ? and ? and  reg_periksa.p_jawab like ? or "+
                " poliklinik.kd_poli<>'IGDK' and poliklinik.nm_poli like ? and  dokter.nm_dokter like ? and tgl_registrasi between ? and ? and  reg_periksa.almt_pj like ? or "+
                " poliklinik.kd_poli<>'IGDK' and poliklinik.nm_poli like ? and  dokter.nm_dokter like ? and tgl_registrasi between ? and ? and  reg_periksa.hubunganpj like ? or "+
                " poliklinik.kd_poli<>'IGDK' and poliklinik.nm_poli like ? and  dokter.nm_dokter like ? and tgl_registrasi between ? and ? and  penjab.png_jawab like ? order by "+order); 
            try{  
                ps.setString(1,"%"+CrPoli.getText()+"%");
                ps.setString(2,"%"+CrDokter.getText()+"%");
                ps.setString(3,Valid.SetTgl(DTPCari1.getSelectedItem()+""));
                ps.setString(4,Valid.SetTgl(DTPCari2.getSelectedItem()+""));
                ps.setString(5,"%"+TCari.getText().trim()+"%");
                ps.setString(6,"%"+CrPoli.getText()+"%");
                ps.setString(7,"%"+CrDokter.getText()+"%");
                ps.setString(8,Valid.SetTgl(DTPCari1.getSelectedItem()+""));
                ps.setString(9,Valid.SetTgl(DTPCari2.getSelectedItem()+""));
                ps.setString(10,"%"+TCari.getText().trim()+"%");
                ps.setString(11,"%"+CrPoli.getText()+"%");
                ps.setString(12,"%"+CrDokter.getText()+"%");
                ps.setString(13,Valid.SetTgl(DTPCari1.getSelectedItem()+""));
                ps.setString(14,Valid.SetTgl(DTPCari2.getSelectedItem()+""));
                ps.setString(15,"%"+TCari.getText().trim()+"%");
                ps.setString(16,"%"+CrPoli.getText()+"%");
                ps.setString(17,"%"+CrDokter.getText()+"%");
                ps.setString(18,Valid.SetTgl(DTPCari1.getSelectedItem()+""));
                ps.setString(19,Valid.SetTgl(DTPCari2.getSelectedItem()+""));
                ps.setString(20,"%"+TCari.getText().trim()+"%");
                ps.setString(21,"%"+CrPoli.getText()+"%");
                ps.setString(22,"%"+CrDokter.getText()+"%");
                ps.setString(23,Valid.SetTgl(DTPCari1.getSelectedItem()+""));
                ps.setString(24,Valid.SetTgl(DTPCari2.getSelectedItem()+""));
                ps.setString(25,"%"+TCari.getText().trim()+"%");
                ps.setString(26,"%"+CrPoli.getText()+"%");
                ps.setString(27,"%"+CrDokter.getText()+"%");
                ps.setString(28,Valid.SetTgl(DTPCari1.getSelectedItem()+""));
                ps.setString(29,Valid.SetTgl(DTPCari2.getSelectedItem()+""));
                ps.setString(30,"%"+TCari.getText().trim()+"%");
                ps.setString(31,"%"+CrPoli.getText()+"%");
                ps.setString(32,"%"+CrDokter.getText()+"%");
                ps.setString(33,Valid.SetTgl(DTPCari1.getSelectedItem()+""));
                ps.setString(34,Valid.SetTgl(DTPCari2.getSelectedItem()+""));
                ps.setString(35,"%"+TCari.getText().trim()+"%");
                ps.setString(36,"%"+CrPoli.getText()+"%");
                ps.setString(37,"%"+CrDokter.getText()+"%");
                ps.setString(38,Valid.SetTgl(DTPCari1.getSelectedItem()+""));
                ps.setString(39,Valid.SetTgl(DTPCari2.getSelectedItem()+""));
                ps.setString(40,"%"+TCari.getText().trim()+"%");
                ps.setString(41,"%"+CrPoli.getText()+"%");
                ps.setString(42,"%"+CrDokter.getText()+"%");
                ps.setString(43,Valid.SetTgl(DTPCari1.getSelectedItem()+""));
                ps.setString(44,Valid.SetTgl(DTPCari2.getSelectedItem()+""));
                ps.setString(45,"%"+TCari.getText().trim()+"%");
                ps.setString(46,"%"+CrPoli.getText()+"%");
                ps.setString(47,"%"+CrDokter.getText()+"%");
                ps.setString(48,Valid.SetTgl(DTPCari1.getSelectedItem()+""));
                ps.setString(49,Valid.SetTgl(DTPCari2.getSelectedItem()+""));
                ps.setString(50,"%"+TCari.getText().trim()+"%");
                ps.setString(51,"%"+CrPoli.getText()+"%");
                ps.setString(52,"%"+CrDokter.getText()+"%");
                ps.setString(53,Valid.SetTgl(DTPCari1.getSelectedItem()+""));
                ps.setString(54,Valid.SetTgl(DTPCari2.getSelectedItem()+""));
                ps.setString(55,"%"+TCari.getText().trim()+"%");
                ps.setString(56,"%"+CrPoli.getText()+"%");
                ps.setString(57,"%"+CrDokter.getText()+"%");
                ps.setString(58,Valid.SetTgl(DTPCari1.getSelectedItem()+""));
                ps.setString(59,Valid.SetTgl(DTPCari2.getSelectedItem()+""));
                ps.setString(60,"%"+TCari.getText().trim()+"%");
                ps.setString(61,"%"+CrPoli.getText()+"%");
                ps.setString(62,"%"+CrDokter.getText()+"%");
                ps.setString(63,Valid.SetTgl(DTPCari1.getSelectedItem()+""));
                ps.setString(64,Valid.SetTgl(DTPCari2.getSelectedItem()+""));
                ps.setString(65,"%"+TCari.getText().trim()+"%");
                rs=ps.executeQuery();
                while(rs.next()){
                    tabMode.addRow(new Object[] {false,rs.getString(1),
                                   rs.getString(2),
                                   rs.getString(3),
                                   rs.getString(4),
                                   rs.getString(5),
                                   rs.getString(6),
                                   rs.getString(7),
                                   rs.getString(8),
                                   rs.getString(9),
                                   rs.getString(10),
                                   rs.getString(11),
                                   rs.getString(17),
                                   rs.getString(12),
                                   rs.getString(13),
                                   rs.getString(14),
                                   Valid.SetAngka(rs.getDouble(15)),
                                   rs.getString(16),
                                   rs.getString("no_tlp"),
                                   rs.getString("stts"),rs.getString("status_poli"),
                                   rs.getString("kd_poli"),rs.getString("kd_pj")
                    });
                }                    
            }catch(Exception e){
                System.out.println("Notifikasi : "+e);
            }finally{
                if(rs != null){
                    rs.close();
                }
                
                if(ps != null){
                    ps.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : "+e);
        }
    
        LCount.setText(""+tabMode.getRowCount());
    }
    public void tampil() {
        Valid.tabelKosong(tabMode);   
        try {
            ps=koneksi.prepareStatement("select reg_periksa.no_reg,reg_periksa.no_rawat,reg_periksa.tgl_registrasi,reg_periksa.jam_reg,"+
                "reg_periksa.kd_dokter,dokter.nm_dokter,reg_periksa.no_rkm_medis,pasien.nm_pasien,pasien.jk,concat(reg_periksa.umurdaftar,' ',reg_periksa.sttsumur)as umur,poliklinik.nm_poli,"+
                "reg_periksa.p_jawab,reg_periksa.almt_pj,reg_periksa.hubunganpj,reg_periksa.biaya_reg,reg_periksa.stts_daftar,penjab.png_jawab,pasien.no_tlp,reg_periksa.stts,reg_periksa.status_poli, "+
                "reg_periksa.kd_poli,reg_periksa.kd_pj from reg_periksa inner join dokter inner join pasien inner join poliklinik inner join penjab "+
                "on reg_periksa.kd_dokter=dokter.kd_dokter and reg_periksa.no_rkm_medis=pasien.no_rkm_medis "+
                "and reg_periksa.kd_pj=penjab.kd_pj and reg_periksa.kd_poli=poliklinik.kd_poli  where  "+
                " poliklinik.kd_poli<>'IGDK' and poliklinik.nm_poli like ? and  dokter.nm_dokter like ? and tgl_registrasi between ? and ? and "+ 
                " (reg_periksa.no_reg like ? or reg_periksa.no_rawat like ? or reg_periksa.tgl_registrasi like ? or reg_periksa.kd_dokter like ? or dokter.nm_dokter like ? or "+
                " reg_periksa.no_rkm_medis like ? or reg_periksa.stts_daftar like ? or pasien.nm_pasien like ? or poliklinik.nm_poli like ? or "+
                " penjab.png_jawab like ?) order by "+order);
            try{  
                ps.setString(1,"%"+CrPoli.getText()+"%");
                ps.setString(2,"%"+CrDokter.getText()+"%");
                ps.setString(3,Valid.SetTgl(DTPCari1.getSelectedItem()+""));
                ps.setString(4,Valid.SetTgl(DTPCari2.getSelectedItem()+""));
                ps.setString(5,"%"+TCari.getText().trim()+"%");
                ps.setString(6,"%"+TCari.getText().trim()+"%");
                ps.setString(7,"%"+TCari.getText().trim()+"%");
                ps.setString(8,"%"+TCari.getText().trim()+"%");
                ps.setString(9,"%"+TCari.getText().trim()+"%");
                ps.setString(10,"%"+TCari.getText().trim()+"%");
                ps.setString(11,"%"+TCari.getText().trim()+"%");
                ps.setString(12,"%"+TCari.getText().trim()+"%");
                ps.setString(13,"%"+TCari.getText().trim()+"%");
                ps.setString(14,"%"+TCari.getText().trim()+"%");
                rs=ps.executeQuery();
                while(rs.next()){
                    tabMode.addRow(new Object[] {false,rs.getString(1),
                                   rs.getString(2),
                                   rs.getString(3),
                                   rs.getString(4),
                                   rs.getString(5),
                                   rs.getString(6),
                                   rs.getString(7),
                                   rs.getString(8),
                                   rs.getString(9),
                                   rs.getString(10),
                                   rs.getString(11),
                                   rs.getString(17),
                                   rs.getString(12),
                                   rs.getString(13),
                                   rs.getString(14),
                                   Valid.SetAngka(rs.getDouble(15)),
                                   rs.getString(16),
                                   rs.getString("no_tlp"),
                                   rs.getString("stts"),rs.getString("status_poli"),
                                   rs.getString("kd_poli"),rs.getString("kd_pj")
                    });
                }                    
            }catch(Exception e){
                System.out.println("Notifikasi : "+e);
            }finally{
                if(rs != null){
                    rs.close();
                }
                
                if(ps != null){
                    ps.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : "+e);
        }
    
        LCount.setText(""+tabMode.getRowCount());
    }
    private void tampil3() {
      // DefaultTableModel tabMode3;
       Valid.tabelKosong(tabMode3);   
        try {
            ps=koneksi.prepareStatement("select reg_periksa.no_reg,reg_periksa.no_rawat,reg_periksa.tgl_registrasi,reg_periksa.jam_reg,"+
                "reg_periksa.kd_dokter,dokter.nm_dokter,reg_periksa.no_rkm_medis,pasien.nm_pasien,pasien.jk,concat(reg_periksa.umurdaftar,' ',reg_periksa.sttsumur)as umur,poliklinik.nm_poli,"+
                "reg_periksa.p_jawab,reg_periksa.almt_pj,reg_periksa.hubunganpj,reg_periksa.biaya_reg,reg_periksa.stts_daftar,penjab.png_jawab,pasien.no_tlp,reg_periksa.stts,reg_periksa.status_poli, "+
                "reg_periksa.kd_poli,reg_periksa.kd_pj from reg_periksa inner join dokter inner join pasien inner join poliklinik inner join penjab "+
                "on reg_periksa.kd_dokter=dokter.kd_dokter and reg_periksa.no_rkm_medis=pasien.no_rkm_medis "+
                "and reg_periksa.kd_pj=penjab.kd_pj and reg_periksa.kd_poli=poliklinik.kd_poli  where   " +
                " reg_periksa.stts='Belum' and reg_periksa.tgl_registrasi =  date_sub(CURDATE(),INTERVAL 1 DAY) and poliklinik.kd_poli<>'IGDK' and poliklinik.nm_poli like ? and  dokter.nm_dokter like ? and  reg_periksa.no_reg like ? or "+
                " reg_periksa.stts='Belum' and reg_periksa.tgl_registrasi =  date_sub(CURDATE(),INTERVAL 1 DAY) and poliklinik.kd_poli<>'IGDK' and poliklinik.nm_poli like ? and  dokter.nm_dokter like ? and  reg_periksa.no_rawat like ? or "+
                " reg_periksa.stts='Belum' and reg_periksa.tgl_registrasi =  date_sub(CURDATE(),INTERVAL 1 DAY) and poliklinik.kd_poli<>'IGDK' and poliklinik.nm_poli like ? and  dokter.nm_dokter like ? and  reg_periksa.tgl_registrasi like ? or "+
                " reg_periksa.stts='Belum' and reg_periksa.tgl_registrasi =  date_sub(CURDATE(),INTERVAL 1 DAY) and poliklinik.kd_poli<>'IGDK' and poliklinik.nm_poli like ? and  dokter.nm_dokter like ? and  reg_periksa.kd_dokter like ? or "+
                " reg_periksa.stts='Belum' and reg_periksa.tgl_registrasi =  date_sub(CURDATE(),INTERVAL 1 DAY) and poliklinik.kd_poli<>'IGDK' and poliklinik.nm_poli like ? and  dokter.nm_dokter like ? and  dokter.nm_dokter like ? or "+
                " reg_periksa.stts='Belum' and reg_periksa.tgl_registrasi =  date_sub(CURDATE(),INTERVAL 1 DAY) and poliklinik.kd_poli<>'IGDK' and poliklinik.nm_poli like ? and  dokter.nm_dokter like ? and  reg_periksa.no_rkm_medis like ? or "+
                " reg_periksa.stts='Belum' and reg_periksa.tgl_registrasi =  date_sub(CURDATE(),INTERVAL 1 DAY) and poliklinik.kd_poli<>'IGDK' and poliklinik.nm_poli like ? and  dokter.nm_dokter like ? and  reg_periksa.stts_daftar like ? or "+
                " reg_periksa.stts='Belum' and reg_periksa.tgl_registrasi =  date_sub(CURDATE(),INTERVAL 1 DAY) and poliklinik.kd_poli<>'IGDK' and poliklinik.nm_poli like ? and  dokter.nm_dokter like ? and  pasien.nm_pasien like ? or "+
                " reg_periksa.stts='Belum' and reg_periksa.tgl_registrasi =  date_sub(CURDATE(),INTERVAL 1 DAY) and poliklinik.kd_poli<>'IGDK' and poliklinik.nm_poli like ? and  dokter.nm_dokter like ? and  poliklinik.nm_poli like ? or "+
                " reg_periksa.stts='Belum' and reg_periksa.tgl_registrasi =  date_sub(CURDATE(),INTERVAL 1 DAY) and poliklinik.kd_poli<>'IGDK' and poliklinik.nm_poli like ? and  dokter.nm_dokter like ? and  reg_periksa.p_jawab like ? or "+
                " reg_periksa.stts='Belum' and reg_periksa.tgl_registrasi =  date_sub(CURDATE(),INTERVAL 1 DAY) and poliklinik.kd_poli<>'IGDK' and poliklinik.nm_poli like ? and  dokter.nm_dokter like ? and  reg_periksa.almt_pj like ? or "+
                " reg_periksa.stts='Belum' and reg_periksa.tgl_registrasi =  date_sub(CURDATE(),INTERVAL 1 DAY) and poliklinik.kd_poli<>'IGDK' and poliklinik.nm_poli like ? and  dokter.nm_dokter like ? and  reg_periksa.hubunganpj like ? or "+
                " reg_periksa.stts='Belum' and reg_periksa.tgl_registrasi =  date_sub(CURDATE(),INTERVAL 1 DAY) and poliklinik.kd_poli<>'IGDK' and poliklinik.nm_poli like ? and  dokter.nm_dokter like ? and  penjab.png_jawab like ? order by "+order); 
            try{  
                ps.setString(1,"%"+CrPoli.getText()+"%");
                ps.setString(2,"%"+CrDokter.getText()+"%");
               // ps.setString(3,Valid.SetTgl(DTPCari1.getSelectedItem()+""));
               // ps.setString(4,Valid.SetTgl(DTPCari2.getSelectedItem()+""));
                ps.setString(3,"%"+TCari.getText().trim()+"%");
                ps.setString(4,"%"+CrPoli.getText()+"%");
                ps.setString(5,"%"+CrDokter.getText()+"%");
               // ps.setString(8,Valid.SetTgl(DTPCari1.getSelectedItem()+""));
               // ps.setString(9,Valid.SetTgl(DTPCari2.getSelectedItem()+""));
                ps.setString(6,"%"+TCari.getText().trim()+"%");
                ps.setString(7,"%"+CrPoli.getText()+"%");
                ps.setString(8,"%"+CrDokter.getText()+"%");
              //  ps.setString(13,Valid.SetTgl(DTPCari1.getSelectedItem()+""));
               // ps.setString(14,Valid.SetTgl(DTPCari2.getSelectedItem()+""));
                ps.setString(9,"%"+TCari.getText().trim()+"%");
                ps.setString(10,"%"+CrPoli.getText()+"%");
                ps.setString(11,"%"+CrDokter.getText()+"%");
              //  ps.setString(18,Valid.SetTgl(DTPCari1.getSelectedItem()+""));
              //  ps.setString(19,Valid.SetTgl(DTPCari2.getSelectedItem()+""));
                ps.setString(12,"%"+TCari.getText().trim()+"%");
                ps.setString(13,"%"+CrPoli.getText()+"%");
                ps.setString(14,"%"+CrDokter.getText()+"%");
              //  ps.setString(23,Valid.SetTgl(DTPCari1.getSelectedItem()+""));
              //  ps.setString(24,Valid.SetTgl(DTPCari2.getSelectedItem()+""));
                ps.setString(15,"%"+TCari.getText().trim()+"%");
                ps.setString(16,"%"+CrPoli.getText()+"%");
                ps.setString(17,"%"+CrDokter.getText()+"%");
             //   ps.setString(28,Valid.SetTgl(DTPCari1.getSelectedItem()+""));
              //  ps.setString(29,Valid.SetTgl(DTPCari2.getSelectedItem()+""));
                ps.setString(18,"%"+TCari.getText().trim()+"%");
                ps.setString(19,"%"+CrPoli.getText()+"%");
                ps.setString(20,"%"+CrDokter.getText()+"%");
              //  ps.setString(33,Valid.SetTgl(DTPCari1.getSelectedItem()+""));
              //  ps.setString(34,Valid.SetTgl(DTPCari2.getSelectedItem()+""));
                ps.setString(21,"%"+TCari.getText().trim()+"%");
                ps.setString(22,"%"+CrPoli.getText()+"%");
                ps.setString(23,"%"+CrDokter.getText()+"%");
              //  ps.setString(38,Valid.SetTgl(DTPCari1.getSelectedItem()+""));
              //  ps.setString(39,Valid.SetTgl(DTPCari2.getSelectedItem()+""));
                ps.setString(24,"%"+TCari.getText().trim()+"%");
                ps.setString(25,"%"+CrPoli.getText()+"%");
                ps.setString(26,"%"+CrDokter.getText()+"%");
              //  ps.setString(43,Valid.SetTgl(DTPCari1.getSelectedItem()+""));
              //  ps.setString(44,Valid.SetTgl(DTPCari2.getSelectedItem()+""));
                ps.setString(27,"%"+TCari.getText().trim()+"%");
                ps.setString(28,"%"+CrPoli.getText()+"%");
                ps.setString(29,"%"+CrDokter.getText()+"%");
              //  ps.setString(48,Valid.SetTgl(DTPCari1.getSelectedItem()+""));
              //  ps.setString(49,Valid.SetTgl(DTPCari2.getSelectedItem()+""));
                ps.setString(30,"%"+TCari.getText().trim()+"%");
                ps.setString(31,"%"+CrPoli.getText()+"%");
                ps.setString(32,"%"+CrDokter.getText()+"%");
              //  ps.setString(53,Valid.SetTgl(DTPCari1.getSelectedItem()+""));
              //  ps.setString(54,Valid.SetTgl(DTPCari2.getSelectedItem()+""));
                ps.setString(33,"%"+TCari.getText().trim()+"%");
                ps.setString(34,"%"+CrPoli.getText()+"%");
                ps.setString(35,"%"+CrDokter.getText()+"%");
              //  ps.setString(58,Valid.SetTgl(DTPCari1.getSelectedItem()+""));
              //  ps.setString(59,Valid.SetTgl(DTPCari2.getSelectedItem()+""));
                ps.setString(36,"%"+TCari.getText().trim()+"%");
                ps.setString(37,"%"+CrPoli.getText()+"%");
                ps.setString(38,"%"+CrDokter.getText()+"%");
              //  ps.setString(63,Valid.SetTgl(DTPCari1.getSelectedItem()+""));
              //  ps.setString(64,Valid.SetTgl(DTPCari2.getSelectedItem()+""));
                ps.setString(39,"%"+TCari.getText().trim()+"%");
                rs=ps.executeQuery();
                while(rs.next()){
                    tabMode3.addRow(new Object[] {false,rs.getString(1),
                                   rs.getString(2),
                                   rs.getString(3),
                                   rs.getString(4),
                                   rs.getString(5),
                                   rs.getString(6),
                                   rs.getString(7),
                                   rs.getString(8),
                                   rs.getString(9),
                                   rs.getString(10),
                                   rs.getString(11),
                                   rs.getString(17),
                                   rs.getString(12),
                                   rs.getString(13),
                                   rs.getString(14),
                                   Valid.SetAngka(rs.getDouble(15)),
                                   rs.getString(16),
                                   rs.getString("no_tlp"),
                                   rs.getString("stts"),rs.getString("status_poli"),
                                   rs.getString("kd_poli"),rs.getString("kd_pj")
                    });
                }                    
            }catch(Exception e){
                System.out.println("Notifikasi : "+e);
            }finally{
                if(rs != null){
                    rs.close();
                }
                
                if(ps != null){
                    ps.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : "+e);
        }
    
        LCount.setText(""+tabMode3.getRowCount()); 
    } 
    private void tampil2() {
        Valid.tabelKosong(tabMode2);   
        try {
            ps=koneksi.prepareStatement("select reg_periksa.no_rawat,reg_periksa.tgl_registrasi,reg_periksa.jam_reg,a.nm_poli as poli_awal, "+
                       "rujukan_internal_poli.kd_dokter,dokter.nm_dokter,reg_periksa.no_rkm_medis,pasien.nm_pasien,pasien.jk,concat(reg_periksa.umurdaftar,' ',reg_periksa.sttsumur)as umur,poliklinik.nm_poli, "+
                       "reg_periksa.p_jawab,reg_periksa.almt_pj,reg_periksa.hubunganpj,reg_periksa.stts_daftar,penjab.png_jawab,pasien.no_tlp,reg_periksa.stts,rujukan_internal_poli.kd_poli,reg_periksa.kd_pj "+
                       "from reg_periksa inner join dokter inner join pasien inner join poliklinik inner join poliklinik as a inner join penjab inner join rujukan_internal_poli "+
                       "on rujukan_internal_poli.kd_dokter=dokter.kd_dokter and reg_periksa.no_rkm_medis=pasien.no_rkm_medis "+
                       "and rujukan_internal_poli.no_rawat=reg_periksa.no_rawat "+
                       "and reg_periksa.kd_pj=penjab.kd_pj and rujukan_internal_poli.kd_poli=poliklinik.kd_poli and reg_periksa.kd_poli=a.kd_poli where  "+
                    " poliklinik.nm_poli like ? and  dokter.nm_dokter like ? and tgl_registrasi between ? and ? and  reg_periksa.no_reg like ? or "+
                    " poliklinik.nm_poli like ? and  dokter.nm_dokter like ? and tgl_registrasi between ? and ? and  reg_periksa.no_rawat like ? or "+
                    " poliklinik.nm_poli like ? and  dokter.nm_dokter like ? and tgl_registrasi between ? and ? and  reg_periksa.tgl_registrasi like ? or "+
                    " poliklinik.nm_poli like ? and  dokter.nm_dokter like ? and tgl_registrasi between ? and ? and  rujukan_internal_poli.kd_dokter like ? or "+
                    " poliklinik.nm_poli like ? and  dokter.nm_dokter like ? and tgl_registrasi between ? and ? and  dokter.nm_dokter like ? or "+
                    " poliklinik.nm_poli like ? and  dokter.nm_dokter like ? and tgl_registrasi between ? and ? and  reg_periksa.no_rkm_medis like ? or "+
                    " poliklinik.nm_poli like ? and  dokter.nm_dokter like ? and tgl_registrasi between ? and ? and  reg_periksa.stts_daftar like ? or "+
                    " poliklinik.nm_poli like ? and  dokter.nm_dokter like ? and tgl_registrasi between ? and ? and  pasien.nm_pasien like ? or "+
                    " poliklinik.nm_poli like ? and  dokter.nm_dokter like ? and tgl_registrasi between ? and ? and  poliklinik.nm_poli like ? or "+
                    " poliklinik.nm_poli like ? and  dokter.nm_dokter like ? and tgl_registrasi between ? and ? and  reg_periksa.p_jawab like ? or "+
                    " poliklinik.nm_poli like ? and  dokter.nm_dokter like ? and tgl_registrasi between ? and ? and  reg_periksa.almt_pj like ? or "+
                    " poliklinik.nm_poli like ? and  dokter.nm_dokter like ? and tgl_registrasi between ? and ? and  reg_periksa.hubunganpj like ? or "+
                    " poliklinik.nm_poli like ? and  dokter.nm_dokter like ? and tgl_registrasi between ? and ? and  penjab.png_jawab like ? order by "+order); 
            try{  
                ps.setString(1,"%"+CrPoli.getText()+"%");
                ps.setString(2,"%"+CrDokter.getText()+"%");
                ps.setString(3,Valid.SetTgl(DTPCari1.getSelectedItem()+""));
                ps.setString(4,Valid.SetTgl(DTPCari2.getSelectedItem()+""));
                ps.setString(5,"%"+TCari.getText().trim()+"%");
                ps.setString(6,"%"+CrPoli.getText()+"%");
                ps.setString(7,"%"+CrDokter.getText()+"%");
                ps.setString(8,Valid.SetTgl(DTPCari1.getSelectedItem()+""));
                ps.setString(9,Valid.SetTgl(DTPCari2.getSelectedItem()+""));
                ps.setString(10,"%"+TCari.getText().trim()+"%");
                ps.setString(11,"%"+CrPoli.getText()+"%");
                ps.setString(12,"%"+CrDokter.getText()+"%");
                ps.setString(13,Valid.SetTgl(DTPCari1.getSelectedItem()+""));
                ps.setString(14,Valid.SetTgl(DTPCari2.getSelectedItem()+""));
                ps.setString(15,"%"+TCari.getText().trim()+"%");
                ps.setString(16,"%"+CrPoli.getText()+"%");
                ps.setString(17,"%"+CrDokter.getText()+"%");
                ps.setString(18,Valid.SetTgl(DTPCari1.getSelectedItem()+""));
                ps.setString(19,Valid.SetTgl(DTPCari2.getSelectedItem()+""));
                ps.setString(20,"%"+TCari.getText().trim()+"%");
                ps.setString(21,"%"+CrPoli.getText()+"%");
                ps.setString(22,"%"+CrDokter.getText()+"%");
                ps.setString(23,Valid.SetTgl(DTPCari1.getSelectedItem()+""));
                ps.setString(24,Valid.SetTgl(DTPCari2.getSelectedItem()+""));
                ps.setString(25,"%"+TCari.getText().trim()+"%");
                ps.setString(26,"%"+CrPoli.getText()+"%");
                ps.setString(27,"%"+CrDokter.getText()+"%");
                ps.setString(28,Valid.SetTgl(DTPCari1.getSelectedItem()+""));
                ps.setString(29,Valid.SetTgl(DTPCari2.getSelectedItem()+""));
                ps.setString(30,"%"+TCari.getText().trim()+"%");
                ps.setString(31,"%"+CrPoli.getText()+"%");
                ps.setString(32,"%"+CrDokter.getText()+"%");
                ps.setString(33,Valid.SetTgl(DTPCari1.getSelectedItem()+""));
                ps.setString(34,Valid.SetTgl(DTPCari2.getSelectedItem()+""));
                ps.setString(35,"%"+TCari.getText().trim()+"%");
                ps.setString(36,"%"+CrPoli.getText()+"%");
                ps.setString(37,"%"+CrDokter.getText()+"%");
                ps.setString(38,Valid.SetTgl(DTPCari1.getSelectedItem()+""));
                ps.setString(39,Valid.SetTgl(DTPCari2.getSelectedItem()+""));
                ps.setString(40,"%"+TCari.getText().trim()+"%");
                ps.setString(41,"%"+CrPoli.getText()+"%");
                ps.setString(42,"%"+CrDokter.getText()+"%");
                ps.setString(43,Valid.SetTgl(DTPCari1.getSelectedItem()+""));
                ps.setString(44,Valid.SetTgl(DTPCari2.getSelectedItem()+""));
                ps.setString(45,"%"+TCari.getText().trim()+"%");
                ps.setString(46,"%"+CrPoli.getText()+"%");
                ps.setString(47,"%"+CrDokter.getText()+"%");
                ps.setString(48,Valid.SetTgl(DTPCari1.getSelectedItem()+""));
                ps.setString(49,Valid.SetTgl(DTPCari2.getSelectedItem()+""));
                ps.setString(50,"%"+TCari.getText().trim()+"%");
                ps.setString(51,"%"+CrPoli.getText()+"%");
                ps.setString(52,"%"+CrDokter.getText()+"%");
                ps.setString(53,Valid.SetTgl(DTPCari1.getSelectedItem()+""));
                ps.setString(54,Valid.SetTgl(DTPCari2.getSelectedItem()+""));
                ps.setString(55,"%"+TCari.getText().trim()+"%");
                ps.setString(56,"%"+CrPoli.getText()+"%");
                ps.setString(57,"%"+CrDokter.getText()+"%");
                ps.setString(58,Valid.SetTgl(DTPCari1.getSelectedItem()+""));
                ps.setString(59,Valid.SetTgl(DTPCari2.getSelectedItem()+""));
                ps.setString(60,"%"+TCari.getText().trim()+"%");
                ps.setString(61,"%"+CrPoli.getText()+"%");
                ps.setString(62,"%"+CrDokter.getText()+"%");
                ps.setString(63,Valid.SetTgl(DTPCari1.getSelectedItem()+""));
                ps.setString(64,Valid.SetTgl(DTPCari2.getSelectedItem()+""));
                ps.setString(65,"%"+TCari.getText().trim()+"%");
                rs=ps.executeQuery();
                while(rs.next()){
                    tabMode2.addRow(new Object[] {false,
                        rs.getString("no_rawat"),rs.getString("tgl_registrasi"),
                        rs.getString("jam_reg"),rs.getString("kd_dokter"),
                        rs.getString("nm_dokter"),rs.getString("no_rkm_medis"),
                        rs.getString("nm_pasien"),rs.getString("jk"),
                        rs.getString("umur"),rs.getString("nm_poli"),rs.getString("poli_awal"),
                        rs.getString("png_jawab"),rs.getString("p_jawab"),
                        rs.getString("almt_pj"),rs.getString("hubunganpj"),
                        rs.getString("stts_daftar"),rs.getString("no_tlp"),
                        rs.getString("stts"),rs.getString("kd_poli"),rs.getString("kd_pj")
                    });
                }                    
            }catch(Exception e){
                System.out.println("Notifikasi : "+e);
            }finally{
                if(rs != null){
                    rs.close();
                }
                
                if(ps != null){
                    ps.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : "+e);
        }
    
        LCount.setText(""+tabMode2.getRowCount());
    }
    
    public void emptTeks() {
        TNoReg.setText("");
        TNoRw.setText("");
        Kd2.setText("");
        //DTPReg.setDate(new Date());
        TNoRM.setText("");
        TPasien.setText("");
        TPngJwb.setText("");
        THbngn.setText("");
        TAlmt.setText("");
        TStatus.setText("");
        AsalRujukan.setText("");        
        alamatperujuk="";
        isNumber();       
        TNoRM.requestFocus();
        kdpnj.setText("");
        nmpnj.setText("");
    }

    private void getData() {
        if(tbPetugas.getSelectedRow()!= -1){
            Kd2.setText(tbPetugas.getValueAt(tbPetugas.getSelectedRow(),2).toString());
            Valid.SetTgl(DTPReg,tbPetugas.getValueAt(tbPetugas.getSelectedRow(),3).toString());
            CmbJam.setSelectedItem(tbPetugas.getValueAt(tbPetugas.getSelectedRow(),4).toString().substring(0,2));
            CmbMenit.setSelectedItem(tbPetugas.getValueAt(tbPetugas.getSelectedRow(),4).toString().substring(3,5));
            CmbDetik.setSelectedItem(tbPetugas.getValueAt(tbPetugas.getSelectedRow(),4).toString().substring(6,8));
            kddokter.setText(tbPetugas.getModel().getValueAt(tbPetugas.getSelectedRow(),5).toString());
            TDokter.setText(tbPetugas.getModel().getValueAt(tbPetugas.getSelectedRow(),6).toString());
            TNoRM.setText(tbPetugas.getModel().getValueAt(tbPetugas.getSelectedRow(),7).toString());            
            isCekPasien();
            TPoli.setText(tbPetugas.getModel().getValueAt(tbPetugas.getSelectedRow(),11).toString());          
            nmpnj.setText(tbPetugas.getModel().getValueAt(tbPetugas.getSelectedRow(),12).toString());
            TPngJwb.setText(tbPetugas.getModel().getValueAt(tbPetugas.getSelectedRow(),13).toString());
            TAlmt.setText(tbPetugas.getModel().getValueAt(tbPetugas.getSelectedRow(),14).toString());
            THbngn.setText(tbPetugas.getModel().getValueAt(tbPetugas.getSelectedRow(),15).toString());
            TBiaya.setText(tbPetugas.getModel().getValueAt(tbPetugas.getSelectedRow(),16).toString());
            TStatus.setText(tbPetugas.getModel().getValueAt(tbPetugas.getSelectedRow(),17).toString());  
            kdpoli.setText(tbPetugas.getModel().getValueAt(tbPetugas.getSelectedRow(),21).toString()); 
            kdpnj.setText(tbPetugas.getModel().getValueAt(tbPetugas.getSelectedRow(),22).toString()); 
            Sequel.cariIsi("select perujuk from rujuk_masuk where no_rawat=?", AsalRujukan,tbPetugas.getModel().getValueAt(tbPetugas.getSelectedRow(),2).toString());
            TNoRw.setText(tbPetugas.getModel().getValueAt(tbPetugas.getSelectedRow(),2).toString());
            TNoReg.setText(tbPetugas.getModel().getValueAt(tbPetugas.getSelectedRow(),1).toString());                       
            
        }
    }


    private void jam(){
        ActionListener taskPerformer = new ActionListener(){
            private int nilai_jam;
            private int nilai_menit;
            private int nilai_detik;
            public void actionPerformed(ActionEvent e) {
                String nol_jam = "";
                String nol_menit = "";
                String nol_detik = "";
                
                Date now = Calendar.getInstance().getTime();

                // Mengambil nilaj JAM, MENIT, dan DETIK Sekarang
                if(ChkJln.isSelected()==true){
                    nilai_jam = now.getHours();
                    nilai_menit = now.getMinutes();
                    nilai_detik = now.getSeconds();
                }else if(ChkJln.isSelected()==false){
                    nilai_jam =CmbJam.getSelectedIndex();
                    nilai_menit =CmbMenit.getSelectedIndex();
                    nilai_detik =CmbDetik.getSelectedIndex();
                }

                // Jika nilai JAM lebih kecil dari 10 (hanya 1 digit)
                if (nilai_jam <= 9) {
                    // Tambahkan "0" didepannya
                    nol_jam = "0";
                }
                // Jika nilai MENIT lebih kecil dari 10 (hanya 1 digit)
                if (nilai_menit <= 9) {
                    // Tambahkan "0" didepannya
                    nol_menit = "0";
                }
                // Jika nilai DETIK lebih kecil dari 10 (hanya 1 digit)
                if (nilai_detik <= 9) {
                    // Tambahkan "0" didepannya
                    nol_detik = "0";
                }
                // Membuat String JAM, MENIT, DETIK
                String jam = nol_jam + Integer.toString(nilai_jam);
                String menit = nol_menit + Integer.toString(nilai_menit);
                String detik = nol_detik + Integer.toString(nilai_detik);
                // Menampilkan pada Layar
                //tampil_jam.setText("  " + jam + " : " + menit + " : " + detik + "  ");
                CmbJam.setSelectedItem(jam);
                CmbMenit.setSelectedItem(menit);
                CmbDetik.setSelectedItem(detik);
            }
        };
        // Timer
        new Timer(1000, taskPerformer).start();
    }

    private void isPas(){
        if(validasiregistrasi.equals("Yes")){
            if(Sequel.cariInteger("select count(no_rkm_medis) from reg_periksa where no_rkm_medis=? and status_bayar='Belum Bayar' and stts<>'Batal'",TNoRM.getText())>0){
                JOptionPane.showMessageDialog(rootPane,"Maaf, pasien pada kunjungan sebelumnya memiliki tagihan yang belum di closing.\nSilahkan konfirmasi dengan pihak kasir.. !!");
            }else{
                if(validasicatatan.equals("Yes")){
                    if(Sequel.cariInteger("select count(no_rkm_medis) from catatan_pasien where no_rkm_medis=?",TNoRM.getText())>0){
                        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                        catatan.setNoRm(TNoRM.getText());
                        catatan.setSize(720,330);
                        catatan.setLocationRelativeTo(internalFrame1);
                        catatan.setVisible(true);
                        this.setCursor(Cursor.getDefaultCursor());
                    }
                }                    
                isCekPasien();
            }
        }else{
            if(validasicatatan.equals("Yes")){
                if(Sequel.cariInteger("select count(no_rkm_medis) from catatan_pasien where no_rkm_medis=?",TNoRM.getText())>0){
                    this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                    catatan.setNoRm(TNoRM.getText());
                    catatan.setSize(720,330);
                    catatan.setLocationRelativeTo(internalFrame1);
                    catatan.setVisible(true);
                    this.setCursor(Cursor.getDefaultCursor());
                }
            }
            isCekPasien();
        }        
    }
    
    private void isCekPasien(){
        try {
            ps3=koneksi.prepareStatement("select nm_pasien,concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab) asal,"+
                        "namakeluarga,keluarga,pasien.kd_pj,penjab.png_jawab,if(tgl_daftar=?,'Baru','Lama') as daftar, "+
                        "TIMESTAMPDIFF(YEAR, tgl_lahir, CURDATE()) as tahun, "+
                        "(TIMESTAMPDIFF(MONTH, tgl_lahir, CURDATE()) - ((TIMESTAMPDIFF(MONTH, tgl_lahir, CURDATE()) div 12) * 12)) as bulan, "+
                        "TIMESTAMPDIFF(DAY, DATE_ADD(DATE_ADD(tgl_lahir,INTERVAL TIMESTAMPDIFF(YEAR, tgl_lahir, CURDATE()) YEAR), INTERVAL TIMESTAMPDIFF(MONTH, tgl_lahir, CURDATE()) - ((TIMESTAMPDIFF(MONTH, tgl_lahir, CURDATE()) div 12) * 12) MONTH), CURDATE()) as hari from pasien "+
                        "inner join kelurahan inner join kecamatan inner join kabupaten inner join penjab "+
                        "on pasien.kd_kel=kelurahan.kd_kel and pasien.kd_pj=penjab.kd_pj "+
                        "and pasien.kd_kec=kecamatan.kd_kec and pasien.kd_kab=kabupaten.kd_kab "+
                        "where pasien.no_rkm_medis=?");
            try {            
                ps3.setString(1,Valid.SetTgl(DTPReg.getSelectedItem()+""));
                ps3.setString(2,TNoRM.getText());
                rs=ps3.executeQuery();
                while(rs.next()){
                    TAlmt.setText(rs.getString("asal"));
                    TPngJwb.setText(rs.getString("namakeluarga"));
                    THbngn.setText(rs.getString("keluarga"));
                    kdpnj.setText(rs.getString("kd_pj"));
                    nmpnj.setText(rs.getString("png_jawab"));
                    TStatus.setText(rs.getString("daftar")); 
                    umur="0";
                    sttsumur="Th";
                    if(rs.getInt("tahun")>0){
                        umur=rs.getString("tahun");
                        sttsumur="Th";
                    }else if(rs.getInt("tahun")==0){
                        if(rs.getInt("bulan")>0){
                            umur=rs.getString("bulan");
                            sttsumur="Bl";
                        }else if(rs.getInt("bulan")==0){
                            umur=rs.getString("hari");
                            sttsumur="Hr";
                        }
                    }
                    
                    TPasien.setText(rs.getString("nm_pasien")+" ("+umur+" "+sttsumur+")");
                    switch (rs.getString("daftar")) {
                        case "Baru":
                            Sequel.cariIsi("select registrasi from poliklinik where kd_poli=?",TBiaya,kdpoli.getText());
                            break;
                        case "Lama":
                            Sequel.cariIsi("select registrasilama from poliklinik where kd_poli=?",TBiaya,kdpoli.getText());
                            break;
                        default:
                            Sequel.cariIsi("select registrasi from poliklinik where kd_poli=?",TBiaya,kdpoli.getText());
                            break;
                    }
                }
            } catch (Exception ex) {
                System.out.println(ex);
            }finally{
                if(rs != null ){
                    rs.close();
                }
                
                if(ps3 != null ){
                    ps3.close();
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }


    public JTextField getTextField(){
        return TNoRw;
    }

    public JButton getButton(){
        return BtnKeluar;
    }
    
    private void isForm(){
        if(ChkInput.isSelected()==true){
            ChkInput.setVisible(false);
            PanelInput.setPreferredSize(new Dimension(WIDTH,188));
            FormInput.setVisible(true);      
            ChkInput.setVisible(true);
        }else if(ChkInput.isSelected()==false){           
            ChkInput.setVisible(false);            
            PanelInput.setPreferredSize(new Dimension(WIDTH,20));
            FormInput.setVisible(false);      
            ChkInput.setVisible(true);
        }
    }
    
    public void isCek(){
        DTPReg.setDate(new Date());
        DTPCari1.setDate(new Date());
        DTPCari2.setDate(new Date());
        BtnSimpan.setEnabled(var.getregistrasi());
        BtnHapus.setEnabled(var.getregistrasi());
        BtnEdit.setEnabled(var.getregistrasi());
        BtnPrint.setEnabled(var.getregistrasi());
        MnOperasi.setEnabled(var.getoperasi());
        MnOperasi1.setEnabled(var.getoperasi());
        MnKamarInap.setEnabled(var.getkamar_inap());
        MnKamarInap1.setEnabled(var.getkamar_inap());
        MnRawatJalan.setEnabled(var.gettindakan_ralan());
        MnRawatJalan1.setEnabled(var.gettindakan_ralan());
        MnPemberianObat.setEnabled(var.getberi_obat());
        MnPemberianObat1.setEnabled(var.getberi_obat());
        MnBilling.setEnabled(var.getbilling_ralan());
        MnPeriksaLab.setEnabled(var.getperiksa_lab());
        MnPeriksaLab1.setEnabled(var.getperiksa_lab());
        MnPeriksaRadiologi.setEnabled(var.getperiksa_radiologi());
        MnPeriksaRadiologi1.setEnabled(var.getperiksa_radiologi());
        MnNoResep.setEnabled(var.getresep_obat());
        MnRujuk.setEnabled(var.getrujukan_keluar());
        MnRujukMasuk.setEnabled(var.getrujukan_masuk());
        MnDiagnosa.setEnabled(var.getdiagnosa_pasien());
        MnHapusTagihanOperasi.setEnabled(var.getoperasi());
        MnHapusObatOperasi.setEnabled(var.getoperasi());  
        MnSEP.setEnabled(var.getbpjs_sep());  
        MnPCare.setEnabled(var.getbridging_pcare_daftar());  
        MnSJP.setEnabled(var.getinhealth_sjp());  
        ppRiwayat.setEnabled(var.getresume_pasien());
        ppCatatanPasien.setEnabled(var.getcatatan_pasien());
        MnPoliInternal.setEnabled(var.getrujukan_poli_internal());
        MnHapusRujukan.setEnabled(var.getrujukan_poli_internal());        
        ppIKP.setEnabled(var.getinsiden_keselamatan_pasien());
        ppIKP1.setEnabled(var.getinsiden_keselamatan_pasien());   
        ppBerkasDigital.setEnabled(var.getberkas_digital_perawatan());        
        ppBerkasDigital1.setEnabled(var.getberkas_digital_perawatan());  
        MnJadwalOperasi.setEnabled(var.getbooking_operasi());  
        MnBillingParsial.setEnabled(var.getbilling_parsial()); 
        MnSKDPBPJS.setEnabled(var.getskdp_bpjs());  
        MnPermintaanLab.setEnabled(var.getpermintaan_lab());
        MnPermintaanRadiologi.setEnabled(var.getpermintaan_radiologi());
        MnJadwalOperasi1.setEnabled(var.getbooking_operasi());   
        MnSKDPBPJS1.setEnabled(var.getskdp_bpjs());  
        MnPermintaanLab1.setEnabled(var.getpermintaan_lab());
        MnPermintaanRadiologi1.setEnabled(var.getpermintaan_radiologi());
        MnTeridentifikasiTB.setEnabled(var.getkemenkes_sitt());
    }
    
    private void isNumber(){         
        if(BASENOREG.equals("booking")){
            switch (URUTNOREG) {
                case "poli":
                    if(Sequel.cariInteger("select ifnull(MAX(CONVERT(no_reg,signed)),0) from booking_registrasi where kd_poli='"+kdpoli.getText()+"' and tanggal_periksa='"+Valid.SetTgl(DTPReg.getSelectedItem()+"")+"'")>=
                            Sequel.cariInteger("select ifnull(MAX(CONVERT(no_reg,signed)),0) from reg_periksa where kd_poli='"+kdpoli.getText()+"' and tgl_registrasi='"+Valid.SetTgl(DTPReg.getSelectedItem()+"")+"'")){
                        Valid.autoNomer3("select ifnull(MAX(CONVERT(no_reg,signed)),0) from booking_registrasi where kd_poli='"+kdpoli.getText()+"' and tanggal_periksa='"+Valid.SetTgl(DTPReg.getSelectedItem()+"")+"'","",3,TNoReg);
                    }else{
                        Valid.autoNomer3("select ifnull(MAX(CONVERT(no_reg,signed)),0) from reg_periksa where kd_poli='"+kdpoli.getText()+"' and tgl_registrasi='"+Valid.SetTgl(DTPReg.getSelectedItem()+"")+"'","",3,TNoReg);
                    }
                    break;
                case "dokter":
                    if(Sequel.cariInteger("select ifnull(MAX(CONVERT(no_reg,signed)),0) from booking_registrasi where kd_dokter='"+kddokter.getText()+"' and tanggal_periksa='"+Valid.SetTgl(DTPReg.getSelectedItem()+"")+"'")>=
                            Sequel.cariInteger("select ifnull(MAX(CONVERT(no_reg,signed)),0) from reg_periksa where kd_dokter='"+kddokter.getText()+"' and tgl_registrasi='"+Valid.SetTgl(DTPReg.getSelectedItem()+"")+"'")){
                        Valid.autoNomer3("select ifnull(MAX(CONVERT(no_reg,signed)),0) from booking_registrasi where kd_dokter='"+kddokter.getText()+"' and tanggal_periksa='"+Valid.SetTgl(DTPReg.getSelectedItem()+"")+"'","",3,TNoReg);
                    }else{
                        Valid.autoNomer3("select ifnull(MAX(CONVERT(no_reg,signed)),0) from reg_periksa where kd_dokter='"+kddokter.getText()+"' and tgl_registrasi='"+Valid.SetTgl(DTPReg.getSelectedItem()+"")+"'","",3,TNoReg);
                    }
                    break;
                case "dokter + poli":  
                    if(Sequel.cariInteger("select ifnull(MAX(CONVERT(no_reg,signed)),0) from booking_registrasi where kd_dokter='"+kddokter.getText()+"' and kd_poli='"+kdpoli.getText()+"' and tanggal_periksa='"+Valid.SetTgl(DTPReg.getSelectedItem()+"")+"'")>=
                            Sequel.cariInteger("select ifnull(MAX(CONVERT(no_reg,signed)),0) from reg_periksa where kd_dokter='"+kddokter.getText()+"' and kd_poli='"+kdpoli.getText()+"' and tgl_registrasi='"+Valid.SetTgl(DTPReg.getSelectedItem()+"")+"'")){
                        Valid.autoNomer3("select ifnull(MAX(CONVERT(no_reg,signed)),0) from booking_registrasi where kd_dokter='"+kddokter.getText()+"' and kd_poli='"+kdpoli.getText()+"' and tanggal_periksa='"+Valid.SetTgl(DTPReg.getSelectedItem()+"")+"'","",3,TNoReg);
                    }else{
                        Valid.autoNomer3("select ifnull(MAX(CONVERT(no_reg,signed)),0) from reg_periksa where kd_dokter='"+kddokter.getText()+"' and kd_poli='"+kdpoli.getText()+"' and tgl_registrasi='"+Valid.SetTgl(DTPReg.getSelectedItem()+"")+"'","",3,TNoReg);
                    }                    
                    break;
                default:
                    if(Sequel.cariInteger("select ifnull(MAX(CONVERT(no_reg,signed)),0) from booking_registrasi where kd_poli='"+kdpoli.getText()+"' and tanggal_periksa='"+Valid.SetTgl(DTPReg.getSelectedItem()+"")+"'")>=
                            Sequel.cariInteger("select ifnull(MAX(CONVERT(no_reg,signed)),0) from reg_periksa where kd_poli='"+kdpoli.getText()+"' and tgl_registrasi='"+Valid.SetTgl(DTPReg.getSelectedItem()+"")+"'")){
                        Valid.autoNomer3("select ifnull(MAX(CONVERT(no_reg,signed)),0) from booking_registrasi where kd_poli='"+kdpoli.getText()+"' and tanggal_periksa='"+Valid.SetTgl(DTPReg.getSelectedItem()+"")+"'","",3,TNoReg);
                    }else{
                        Valid.autoNomer3("select ifnull(MAX(CONVERT(no_reg,signed)),0) from reg_periksa where kd_poli='"+kdpoli.getText()+"' and tgl_registrasi='"+Valid.SetTgl(DTPReg.getSelectedItem()+"")+"'","",3,TNoReg);
                    }
                    break;
            }
        }else{
            switch (URUTNOREG) {
                case "poli":
                    Valid.autoNomer3("select ifnull(MAX(CONVERT(no_reg,signed)),0) from reg_periksa where kd_poli='"+kdpoli.getText()+"' and tgl_registrasi='"+Valid.SetTgl(DTPReg.getSelectedItem()+"")+"'","",3,TNoReg);
                    break;
                case "dokter":
                    Valid.autoNomer3("select ifnull(MAX(CONVERT(no_reg,signed)),0) from reg_periksa where kd_dokter='"+kddokter.getText()+"' and tgl_registrasi='"+Valid.SetTgl(DTPReg.getSelectedItem()+"")+"'","",3,TNoReg);
                    break;
                case "dokter + poli":             
                    Valid.autoNomer3("select ifnull(MAX(CONVERT(no_reg,signed)),0) from reg_periksa where kd_dokter='"+kddokter.getText()+"' and kd_poli='"+kdpoli.getText()+"' and tgl_registrasi='"+Valid.SetTgl(DTPReg.getSelectedItem()+"")+"'","",3,TNoReg);
                    break;
                default:
                    Valid.autoNomer3("select ifnull(MAX(CONVERT(no_reg,signed)),0) from reg_periksa where kd_dokter='"+kddokter.getText()+"' and tgl_registrasi='"+Valid.SetTgl(DTPReg.getSelectedItem()+"")+"'","",3,TNoReg);
                    break;
            }
        }    
        
        if(Kd2.getText().equals("")){
            Valid.autoNomer3("select ifnull(MAX(CONVERT(RIGHT(no_rawat,6),signed)),0) from reg_periksa where tgl_registrasi='"+Valid.SetTgl(DTPReg.getSelectedItem()+"")+"' ",dateformat.format(DTPReg.getDate())+"/",6,TNoRw);           
        }            
    }
    
    public  void ctk(){
        try {
            String os = System.getProperty("os.name").toLowerCase();
            Runtime rt = Runtime.getRuntime();
            FileWriter writer = null;
            if(os.contains("win")) {
                writer = new FileWriter("//"+IPPRINTERTRACER);
            }else if (os.contains("mac")) {
                writer = new FileWriter("smb://"+IPPRINTERTRACER);
            }else if (os.contains("nix") || os.contains("nux")) {
                writer = new FileWriter("smb://"+IPPRINTERTRACER);
            }
            writer.write(".: TRACER :.");
            cetakStruk("Draft Sans Serif Condensed", writer,
                    MODE_DRAFT,
                    FONT_SANS_SERIF,
                    CONDENSED_ON,
                    SIZE_12_CPI,
                    SPACING_12_72);
            sendCommand(RESET, writer);
            writer.close();
        } catch (Exception ex) {
             System.out.println("Notif Writer 3 : "+ex);
        }
    }
    
    private  void cetakStruk(String title, FileWriter writer, char[]... mode) throws IOException {
        sendCommand(RESET, writer);
        for (int i = 0; i < mode.length; i++) {
            char[] cmd = mode[i];
            sendCommand(cmd, writer);
        }

        cetakStruk2(title,writer);
	sendCommand(VERTICAL_PRINT_POSITION, writer);
    }
    
    public void sendCommand(char[] command, Writer writer) throws IOException {
        writer.write(command);
    }
    
    private void cetakStruk2(  String title, FileWriter writer){
        String strukFile = "tracerRm.txt";
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(strukFile));        
            String tgll= Sequel.cariIsi("select tgl_registrasi from reg_periksa where no_rawat='"+TNoRw.getText()+"'");
            String[] tglref= tgll.split("-");
            boltText(writer);
            writer.write(".: TRACER :.");
            boltTextOff(writer);
            gantiBaris(writer);
            writer.write("No. RM      : ");
            boltText(writer);
            writer.write(TNoRM.getText());
            boltTextOff(writer);
            gantiBaris(writer);
            writer.write("Nama Pasien : ");
            boltText(writer);
            writer.write(TPasien.getText());
            boltTextOff(writer);
            gantiBaris(writer);
            writer.write("Tgl. Daftar : ");
            boltText(writer);
            writer.write(tglref[2]+"-"+tglref[1]+"-"+tglref[0]+"/"+CmbJam.getSelectedItem()+":"+CmbMenit.getSelectedItem()+":"+CmbDetik.getSelectedItem());
            gantiBaris(writer);
            boltTextOff(writer);
            writer.write("Ruangan     : ");
            boltText(writer);
            writer.write(Sequel.cariIsi("select nm_poli from poliklinik where kd_poli='"+kdpoli.getText()+"'"));
            boltTextOff(writer);

            gantiBaris(writer);
            gantiBaris(writer);
            gantiBaris(writer);
            reader.close();
        } catch (Exception ex) {
            System.out.println("Notif : "+ex);
        }
    }
    
    private void boltText(Writer writer){
        try {
            writer.write(ESC);
            writer.write((char)14);
            writer.write(ESC);
            writer.write('E');
        } catch (Exception e) {
            System.out.println("Notif : "+e);
        }            
    }
    
    private void boltTextOff(Writer writer) {
        try {
            writer.write(ESC);
            writer.write('F');
        } catch (Exception e) {
            System.out.println("Notif : "+e);
        }
    }
    
    private void gantiBaris(Writer writer) {
        try {
            writer.write("\n");
        } catch (Exception e) {
            System.out.println("Notif : "+e);
        }            
    }
    
    private void UpdateUmur(){
        Sequel.mengedit("pasien","no_rkm_medis=?","umur=CONCAT(CONCAT(CONCAT(TIMESTAMPDIFF(YEAR, tgl_lahir, CURDATE()), ' Th '),CONCAT(TIMESTAMPDIFF(MONTH, tgl_lahir, CURDATE()) - ((TIMESTAMPDIFF(MONTH, tgl_lahir, CURDATE()) div 12) * 12), ' Bl ')),CONCAT(TIMESTAMPDIFF(DAY, DATE_ADD(DATE_ADD(tgl_lahir,INTERVAL TIMESTAMPDIFF(YEAR, tgl_lahir, CURDATE()) YEAR), INTERVAL TIMESTAMPDIFF(MONTH, tgl_lahir, CURDATE()) - ((TIMESTAMPDIFF(MONTH, tgl_lahir, CURDATE()) div 12) * 12) MONTH), CURDATE()), ' Hr'))",1,new String[]{TNoRM.getText()});
    }

    private void isRegistrasi() {
        ceksukses=false;
        status="Baru";
        if(Sequel.cariInteger("select count(no_rkm_medis) from reg_periksa where no_rkm_medis=? and kd_poli=?",TNoRM.getText(),kdpoli.getText())>0){
            status="Lama";
        }
        
        if(Sequel.menyimpantf2("reg_periksa","?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?","No.Rawat",19,
                new String[]{TNoReg.getText(),TNoRw.getText(),Valid.SetTgl(DTPReg.getSelectedItem()+""),CmbJam.getSelectedItem()+":"+CmbMenit.getSelectedItem()+":"+CmbDetik.getSelectedItem(),
                kddokter.getText(),TNoRM.getText(),kdpoli.getText(),TPngJwb.getText(),TAlmt.getText(),THbngn.getText(),TBiaya.getText(),"Belum",
                TStatus.getText(),"Ralan",kdpnj.getText(),umur,sttsumur,"Belum Bayar",status})==true){
            ceksukses=true;
        }else{
            isNumber();
            if(Sequel.menyimpantf2("reg_periksa","?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?","No.Rawat",19,
                    new String[]{TNoReg.getText(),TNoRw.getText(),Valid.SetTgl(DTPReg.getSelectedItem()+""),CmbJam.getSelectedItem()+":"+CmbMenit.getSelectedItem()+":"+CmbDetik.getSelectedItem(),
                    kddokter.getText(),TNoRM.getText(),kdpoli.getText(),TPngJwb.getText(),TAlmt.getText(),THbngn.getText(),TBiaya.getText(),"Belum",
                    TStatus.getText(),"Ralan",kdpnj.getText(),umur,sttsumur,"Belum Bayar",status})==true){
                ceksukses=true;            
            }else{
                isNumber();
                if(Sequel.menyimpantf2("reg_periksa","?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?","No.Rawat",19,
                        new String[]{TNoReg.getText(),TNoRw.getText(),Valid.SetTgl(DTPReg.getSelectedItem()+""),CmbJam.getSelectedItem()+":"+CmbMenit.getSelectedItem()+":"+CmbDetik.getSelectedItem(),
                        kddokter.getText(),TNoRM.getText(),kdpoli.getText(),TPngJwb.getText(),TAlmt.getText(),THbngn.getText(),TBiaya.getText(),"Belum",
                        TStatus.getText(),"Ralan",kdpnj.getText(),umur,sttsumur,"Belum Bayar",status})==true){
                    ceksukses=true;
                }else{
                    isNumber();
                    if(Sequel.menyimpantf2("reg_periksa","?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?","No.Rawat",19,
                            new String[]{TNoReg.getText(),TNoRw.getText(),Valid.SetTgl(DTPReg.getSelectedItem()+""),CmbJam.getSelectedItem()+":"+CmbMenit.getSelectedItem()+":"+CmbDetik.getSelectedItem(),
                            kddokter.getText(),TNoRM.getText(),kdpoli.getText(),TPngJwb.getText(),TAlmt.getText(),THbngn.getText(),TBiaya.getText(),"Belum",
                            TStatus.getText(),"Ralan",kdpnj.getText(),umur,sttsumur,"Belum Bayar",status})==true){
                        ceksukses=true;
                    }else{
                        isNumber();
                        if(Sequel.menyimpantf("reg_periksa","?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?","No.Rawat",19,
                                new String[]{TNoReg.getText(),TNoRw.getText(),Valid.SetTgl(DTPReg.getSelectedItem()+""),CmbJam.getSelectedItem()+":"+CmbMenit.getSelectedItem()+":"+CmbDetik.getSelectedItem(),
                                kddokter.getText(),TNoRM.getText(),kdpoli.getText(),TPngJwb.getText(),TAlmt.getText(),THbngn.getText(),TBiaya.getText(),"Belum",
                                TStatus.getText(),"Ralan",kdpnj.getText(),umur,sttsumur,"Belum Bayar",status})==true){
                            ceksukses=true;            
                        }else{
                            TNoRM.requestFocus();
                            isNumber();
                        } 
                    } 
                } 
            }                
        } 
        
        if(ceksukses==true){
            UpdateUmur(); 
            if(!AsalRujukan.getText().equals("")){
                Valid.autoNomer3("select ifnull(MAX(CONVERT(RIGHT(rujuk_masuk.no_rawat,4),signed)),0) from reg_periksa inner join rujuk_masuk on reg_periksa.no_rawat=rujuk_masuk.no_rawat where reg_periksa.tgl_registrasi='"+Valid.SetTgl(DTPReg.getSelectedItem()+"")+"' ","BR/"+dateformat.format(DTPReg.getDate())+"/",4,NoBalasan);
                
                if(nosisrute.equals("")){
                    Sequel.menyimpan2("rujuk_masuk","'"+TNoRw.getText()+"','"+AsalRujukan.getText()+"','"+alamatperujuk+"','-','0','"+AsalRujukan.getText()+"','-','-','-','"+NoBalasan.getText()+"'","No.Rujuk");
                }else{
                    Sequel.menyimpan2("rujuk_masuk","'"+TNoRw.getText()+"','"+AsalRujukan.getText()+"','"+alamatperujuk+"','"+nosisrute+"','0','"+AsalRujukan.getText()+"','-','-','-','"+NoBalasan.getText()+"'","No.Rujuk"); 
                    nosisrute="";
                }                    
            }
            if(ChkTracker.isSelected()==true){
                ctk();
            }
            emptTeks(); 
            if(TabRawat.getSelectedIndex()==0){
                tampil();
            }                
        }  
    }

    private void billingprasial() {
        if(tbPetugas.getSelectedRow()!= -1){
            jmlparsial=0;
            if(aktifkanparsial.equals("yes")){
                jmlparsial=Sequel.cariInteger("select count(kd_pj) from set_input_parsial where kd_pj=?",tbPetugas.getValueAt(tbPetugas.getSelectedRow(),22).toString());
            }
            if(jmlparsial>0){
                DlgBilingParsialRalan parsialralan=new DlgBilingParsialRalan(null,false);
                parsialralan.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
                parsialralan.setLocationRelativeTo(internalFrame1);
                //parsialralan.emptTeks();
                parsialralan.isCek();
                parsialralan.setNoRm(TNoRw.getText(),kddokter.getText(),TDokter.getText(),kdpoli.getText());
                parsialralan.setVisible(true);
            }else{
                JOptionPane.showMessageDialog(null,"Maaf, Cara bayar "+tbPetugas.getValueAt(tbPetugas.getSelectedRow(),12).toString()+" tidak diijinkan menggunakan Billing Parsial...!!!");
            }
        }else{
            JOptionPane.showMessageDialog(null,"Maaf, Silahkan anda pilih dulu dengan menklik data pada table...!!!");
            tbPetugas.requestFocus();
        } 
    }
    
    public void SetPasien(String norm,String nosisrute,String FaskesAsal){
        ChkInput.setSelected(true);
        isForm(); 
        TNoRM.setText(norm);
        this.nosisrute=nosisrute;
        AsalRujukan.setText(FaskesAsal);
        isPas();
    }
    
    public void setPasien(String NamaPasien,String Kontak,String Alamat,String TempatLahir,String TglLahir,
            String JK,String NoKartuJKN,String NIK,String nosisrute,String FaskesAsal){
        var.setform("DlgReg");
        ChkInput.setSelected(true);
        isForm(); 
        pasien.emptTeks();
        pasien.isCek();
        this.nosisrute=nosisrute;
        AsalRujukan.setText(FaskesAsal);
        pasien.setPasien(NamaPasien, Kontak, Alamat, TempatLahir, TglLahir, JK, NoKartuJKN, NIK);
        pasien.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
        pasien.setLocationRelativeTo(internalFrame1);
        pasien.setVisible(true);
    }
}