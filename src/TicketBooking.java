
import java.awt.Color;
import static java.awt.Color.red;
import static java.awt.Color.yellow;
import java.awt.Image;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import java.sql.*;

import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import static java.awt.Color.red;
import static java.awt.Color.yellow;
import java.awt.Font;
import java.awt.Image;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public final class TicketBooking extends javax.swing.JFrame {

//    public TicketBooking() {
//        initComponents();
//        ScaleImage1();
//        ScaleImage2();
//        ScaleImage3();
//        Set_Table_Prop();
//        setResizable(false);
//
//        bus_name.removeAllItems();
//        bus_name.addItem("Select");
//        setTitle("Ticket Booking");
//    }
    public TicketBooking(String userid) {
        initComponents();
        USERID = userid;
        ScaleImage1();
        ScaleImage2();
        ScaleImage3();
        Set_Table_Prop();
        setResizable(false);

        bus_name.removeAllItems();
        bus_name.addItem("Select");
        setTitle("Ticket Booking");
    }

    //Variable declaration
    Integer Nseat = 0;
    String url = "jdbc:mysql://localhost:3306/busmania";
    String username = "root";
    String pass = "123456";
    String USERID, atime;
    String Price, busname, pname, pcontact, SelectedFrom, SelectedTo, SelectedDate, selectedTime, selectedSeat = "";
    boolean ok = false;

    class bus_info {

        public String BUS_NAME, ARRIVAL_TIME, DEPARTURE_TIME;
        public int FARE;

        public bus_info(String BUS_NAME, int FARE, String ARRIVAL_TIME, String DEPARTURE_TIME) {
            this.BUS_NAME = BUS_NAME;
            this.FARE = FARE;
            this.ARRIVAL_TIME = ARRIVAL_TIME;
            this.DEPARTURE_TIME = DEPARTURE_TIME;
        }
    }

    ArrayList<bus_info> ar = new ArrayList<>();
    ArrayList<String> ar2 = new ArrayList<>();

    public void showTable() {
        ar2.clear();

        bus_name.removeAllItems();
        bus_name.addItem("Select");

        DefaultTableModel mod = (DefaultTableModel) (jTable1.getModel());
        Object[] col = new Object[4];
        mod.setRowCount(0);
        for (int i = 0; i < ar.size(); i++) {
            col[0] = ar.get(i).BUS_NAME;
            col[1] = ar.get(i).FARE;
            col[2] = ar.get(i).ARRIVAL_TIME;
            col[3] = ar.get(i).DEPARTURE_TIME;
            mod.addRow(col);
            ar2.add(ar.get(i).BUS_NAME);
            bus_name.addItem(ar.get(i).BUS_NAME);
        }
    }

    public void ScaleImage1() {
        ImageIcon ic;
        ic = new ImageIcon("D:\\Netbeans Projects\\BusMania\\src\\img\\steer.png");
        Image im = ic.getImage();
        Image imScale = im.getScaledInstance(Steer.getWidth(), Steer.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon scaledicon = new ImageIcon(imScale);
        Steer.setIcon(scaledicon);
    }

    public void ScaleImage2() {
        ImageIcon ic;
        ic = new ImageIcon("D:\\Netbeans Projects\\BusMania\\src\\img\\SeatBack.jpg");
        Image im = ic.getImage();
        Image imScale = im.getScaledInstance(SeatB.getWidth(), SeatB.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon scaledicon = new ImageIcon(imScale);
        SeatB.setIcon(scaledicon);
    }

    public void ScaleImage3() {
        ImageIcon ic;
        ic = new ImageIcon("D:\\Netbeans Projects\\BusMania\\src\\img\\back.jpg");
        Image im = ic.getImage();
        Image imScale = im.getScaledInstance(Background.getWidth(), Background.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon scaledicon = new ImageIcon(imScale);
        Background.setIcon(scaledicon);
    }

    public void Set_Table_Prop() {
        JTableHeader tableHeader = jTable1.getTableHeader();
        tableHeader.setForeground(Color.black);

        Font headerFont = new Font("Verdana", Font.BOLD, 14);
        tableHeader.setFont(headerFont);
        //jTable1.getColumnModel().getColumn(4).setPreferredWidth(150);

        jTable1.setFont(new Font("Arial", Font.BOLD, 14));
        jTable1.setRowHeight(30);
    }

    public boolean is_valid_contact(String pcontact) {
        if (pcontact.length() != 11) {
            return false;
        }
        for (int i = 0; i < pcontact.length(); i++) {
            if (pcontact.charAt(i) >= '0' && pcontact.charAt(i) <= '9') {
                continue;
            }
            return false;
        }
        return true;
    }

    public void paysubmit_buttonActionPerformed_work() {
        if (!ok) {
            JOptionPane.showMessageDialog(rootPane, "You should Book first!!!");
            return;
        }
        SelectedDate = ((JTextField) jDateChooser1.getDateEditor().getUiComponent()).getText();
        pname = name_field.getText();
        pcontact = contact_field.getText();
        Price = total_price.getText();
        busname = (String) bus_name.getSelectedItem();

        if (is_valid_contact(pcontact)) {
            JOptionPane.showMessageDialog(rootPane, "Enter a valid number", "Error", 2);
        } else {
            int ind = bus_name.getSelectedIndex();
            selectedTime = ar.get(ind - 1).DEPARTURE_TIME;
            atime = ar.get(ind - 1).ARRIVAL_TIME;
            dispose();
            //new Payment().setVisible(true);
            //new Payment(pname,pcontact,SelectedFrom,SelectedTo,SelectedDate,selectedTime,selectedSeat,Nseat,price).setVisible(true);
            Payment p_ob = new Payment(pname, pcontact, SelectedFrom, SelectedTo, SelectedDate, selectedTime, selectedSeat, Nseat, Price, busname, USERID, atime);
            p_ob.setVisible(true);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        From = new javax.swing.JComboBox<>();
        To = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        Search = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        name_field = new javax.swing.JTextField();
        contact_field = new javax.swing.JTextField();
        A1 = new javax.swing.JButton();
        A2 = new javax.swing.JButton();
        A4 = new javax.swing.JButton();
        A3 = new javax.swing.JButton();
        B2 = new javax.swing.JButton();
        B4 = new javax.swing.JButton();
        B3 = new javax.swing.JButton();
        B1 = new javax.swing.JButton();
        C2 = new javax.swing.JButton();
        C4 = new javax.swing.JButton();
        C3 = new javax.swing.JButton();
        D2 = new javax.swing.JButton();
        D4 = new javax.swing.JButton();
        D3 = new javax.swing.JButton();
        D1 = new javax.swing.JButton();
        C1 = new javax.swing.JButton();
        E2 = new javax.swing.JButton();
        G1 = new javax.swing.JButton();
        E4 = new javax.swing.JButton();
        E3 = new javax.swing.JButton();
        F2 = new javax.swing.JButton();
        F4 = new javax.swing.JButton();
        F3 = new javax.swing.JButton();
        F1 = new javax.swing.JButton();
        G2 = new javax.swing.JButton();
        G4 = new javax.swing.JButton();
        G3 = new javax.swing.JButton();
        H2 = new javax.swing.JButton();
        H4 = new javax.swing.JButton();
        H3 = new javax.swing.JButton();
        E1 = new javax.swing.JButton();
        H1 = new javax.swing.JButton();
        I2 = new javax.swing.JButton();
        I4 = new javax.swing.JButton();
        I3 = new javax.swing.JButton();
        I1 = new javax.swing.JButton();
        J4 = new javax.swing.JButton();
        J3 = new javax.swing.JButton();
        J1 = new javax.swing.JButton();
        J2 = new javax.swing.JButton();
        Steer = new javax.swing.JLabel();
        jButton41 = new javax.swing.JButton();
        jButton42 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        SeatB = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        flevel = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        tlevel = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        total_price = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        nseat_field = new javax.swing.JTextField();
        dateee = new javax.swing.JTextField();
        Reset = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        ticket_price = new javax.swing.JLabel();
        Book = new javax.swing.JButton();
        paysubmit_button = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton45 = new javax.swing.JButton();
        jButton46 = new javax.swing.JButton();
        jButton47 = new javax.swing.JButton();
        bus_name = new javax.swing.JComboBox<>();
        jLabel16 = new javax.swing.JLabel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jLabel11 = new javax.swing.JLabel();
        Background = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        From.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        From.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select", "Noakhali", "Dhaka", "Chittagong", "CoxBazar", "Cumilla", "Feni", " " }));
        From.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FromActionPerformed(evt);
            }
        });
        getContentPane().add(From, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 45, 140, 30));

        To.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        To.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select", "Dhaka", "Cumilla", "Chittagong", "Feni", "CoxBazar", "Noakhali" }));
        To.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ToActionPerformed(evt);
            }
        });
        getContentPane().add(To, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 85, 140, 30));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setText("FROM ");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(17, 46, 40, 20));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setText("TO ");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(29, 86, 20, 20));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setText("DATE ");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(13, 126, 40, 20));

        Search.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        Search.setText("FIND");
        Search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SearchActionPerformed(evt);
            }
        });
        getContentPane().add(Search, new org.netbeans.lib.awtextra.AbsoluteConstraints(72, 172, 80, 30));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setText("NAME :");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 36, 50, 20));

        name_field.setFont(new java.awt.Font("SansSerif", 0, 15)); // NOI18N
        getContentPane().add(name_field, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 32, 160, 30));

        contact_field.setFont(new java.awt.Font("SansSerif", 0, 15)); // NOI18N
        getContentPane().add(contact_field, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 30, 140, 30));

        A1.setBackground(new java.awt.Color(0, 255, 0));
        A1.setFont(new java.awt.Font("Segoe UI Symbol", 0, 12)); // NOI18N
        A1.setText("A1");
        A1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                A1ActionPerformed(evt);
            }
        });
        getContentPane().add(A1, new org.netbeans.lib.awtextra.AbsoluteConstraints(715, 100, 50, 30));

        A2.setBackground(new java.awt.Color(0, 255, 0));
        A2.setText("A2");
        A2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                A2ActionPerformed(evt);
            }
        });
        getContentPane().add(A2, new org.netbeans.lib.awtextra.AbsoluteConstraints(765, 100, 50, 30));

        A4.setBackground(new java.awt.Color(0, 255, 0));
        A4.setText("A4");
        A4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                A4ActionPerformed(evt);
            }
        });
        getContentPane().add(A4, new org.netbeans.lib.awtextra.AbsoluteConstraints(885, 100, 50, 30));

        A3.setBackground(new java.awt.Color(0, 255, 0));
        A3.setFont(new java.awt.Font("Segoe UI Symbol", 0, 12)); // NOI18N
        A3.setText("A3");
        A3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                A3ActionPerformed(evt);
            }
        });
        getContentPane().add(A3, new org.netbeans.lib.awtextra.AbsoluteConstraints(835, 100, 50, 30));

        B2.setBackground(new java.awt.Color(0, 255, 0));
        B2.setText("B2");
        B2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                B2ActionPerformed(evt);
            }
        });
        getContentPane().add(B2, new org.netbeans.lib.awtextra.AbsoluteConstraints(765, 140, 50, 30));

        B4.setBackground(new java.awt.Color(0, 255, 0));
        B4.setText("B4");
        B4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                B4ActionPerformed(evt);
            }
        });
        getContentPane().add(B4, new org.netbeans.lib.awtextra.AbsoluteConstraints(885, 140, 50, 30));

        B3.setBackground(new java.awt.Color(0, 255, 0));
        B3.setFont(new java.awt.Font("Segoe UI Symbol", 0, 12)); // NOI18N
        B3.setText("B3");
        B3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                B3ActionPerformed(evt);
            }
        });
        getContentPane().add(B3, new org.netbeans.lib.awtextra.AbsoluteConstraints(835, 140, 50, 30));

        B1.setBackground(new java.awt.Color(0, 255, 0));
        B1.setFont(new java.awt.Font("Segoe UI Symbol", 0, 12)); // NOI18N
        B1.setText("B1");
        B1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                B1ActionPerformed(evt);
            }
        });
        getContentPane().add(B1, new org.netbeans.lib.awtextra.AbsoluteConstraints(715, 140, 50, 30));

        C2.setBackground(new java.awt.Color(0, 255, 0));
        C2.setText("C2");
        C2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C2ActionPerformed(evt);
            }
        });
        getContentPane().add(C2, new org.netbeans.lib.awtextra.AbsoluteConstraints(765, 180, 50, 30));

        C4.setBackground(new java.awt.Color(0, 255, 0));
        C4.setText("C4");
        C4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C4ActionPerformed(evt);
            }
        });
        getContentPane().add(C4, new org.netbeans.lib.awtextra.AbsoluteConstraints(885, 180, 50, 30));

        C3.setBackground(new java.awt.Color(0, 255, 0));
        C3.setFont(new java.awt.Font("Segoe UI Symbol", 0, 12)); // NOI18N
        C3.setText("C3");
        C3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C3ActionPerformed(evt);
            }
        });
        getContentPane().add(C3, new org.netbeans.lib.awtextra.AbsoluteConstraints(835, 180, 50, 30));

        D2.setBackground(new java.awt.Color(0, 255, 0));
        D2.setText("D2");
        D2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                D2ActionPerformed(evt);
            }
        });
        getContentPane().add(D2, new org.netbeans.lib.awtextra.AbsoluteConstraints(765, 220, 50, 30));

        D4.setBackground(new java.awt.Color(0, 255, 0));
        D4.setText("D4");
        D4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                D4ActionPerformed(evt);
            }
        });
        getContentPane().add(D4, new org.netbeans.lib.awtextra.AbsoluteConstraints(885, 220, 50, 30));

        D3.setBackground(new java.awt.Color(0, 255, 0));
        D3.setFont(new java.awt.Font("Segoe UI Symbol", 0, 12)); // NOI18N
        D3.setText("D3");
        D3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                D3ActionPerformed(evt);
            }
        });
        getContentPane().add(D3, new org.netbeans.lib.awtextra.AbsoluteConstraints(835, 220, 50, 30));

        D1.setBackground(new java.awt.Color(0, 255, 0));
        D1.setFont(new java.awt.Font("Segoe UI Symbol", 0, 12)); // NOI18N
        D1.setText("D1");
        D1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                D1ActionPerformed(evt);
            }
        });
        getContentPane().add(D1, new org.netbeans.lib.awtextra.AbsoluteConstraints(715, 220, 50, 30));

        C1.setBackground(new java.awt.Color(0, 255, 0));
        C1.setFont(new java.awt.Font("Segoe UI Symbol", 0, 12)); // NOI18N
        C1.setText("C1");
        C1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C1ActionPerformed(evt);
            }
        });
        getContentPane().add(C1, new org.netbeans.lib.awtextra.AbsoluteConstraints(715, 180, 50, 30));

        E2.setBackground(new java.awt.Color(0, 255, 0));
        E2.setText("E2");
        E2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                E2ActionPerformed(evt);
            }
        });
        getContentPane().add(E2, new org.netbeans.lib.awtextra.AbsoluteConstraints(765, 260, 50, 30));

        G1.setBackground(new java.awt.Color(0, 255, 0));
        G1.setFont(new java.awt.Font("Segoe UI Symbol", 0, 12)); // NOI18N
        G1.setText("G1");
        G1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                G1ActionPerformed(evt);
            }
        });
        getContentPane().add(G1, new org.netbeans.lib.awtextra.AbsoluteConstraints(715, 340, 50, 30));

        E4.setBackground(new java.awt.Color(0, 255, 0));
        E4.setText("E4");
        E4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                E4ActionPerformed(evt);
            }
        });
        getContentPane().add(E4, new org.netbeans.lib.awtextra.AbsoluteConstraints(885, 260, 50, 30));

        E3.setBackground(new java.awt.Color(0, 255, 0));
        E3.setFont(new java.awt.Font("Segoe UI Symbol", 0, 12)); // NOI18N
        E3.setText("E3");
        E3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                E3ActionPerformed(evt);
            }
        });
        getContentPane().add(E3, new org.netbeans.lib.awtextra.AbsoluteConstraints(835, 260, 50, 30));

        F2.setBackground(new java.awt.Color(0, 255, 0));
        F2.setText("F2");
        F2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                F2ActionPerformed(evt);
            }
        });
        getContentPane().add(F2, new org.netbeans.lib.awtextra.AbsoluteConstraints(765, 300, 50, 30));

        F4.setBackground(new java.awt.Color(0, 255, 0));
        F4.setText("F4");
        F4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                F4ActionPerformed(evt);
            }
        });
        getContentPane().add(F4, new org.netbeans.lib.awtextra.AbsoluteConstraints(885, 300, 50, 30));

        F3.setBackground(new java.awt.Color(0, 255, 0));
        F3.setFont(new java.awt.Font("Segoe UI Symbol", 0, 12)); // NOI18N
        F3.setText("F3");
        F3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                F3ActionPerformed(evt);
            }
        });
        getContentPane().add(F3, new org.netbeans.lib.awtextra.AbsoluteConstraints(835, 300, 50, 30));

        F1.setBackground(new java.awt.Color(0, 255, 0));
        F1.setFont(new java.awt.Font("Segoe UI Symbol", 0, 12)); // NOI18N
        F1.setText("F1");
        F1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                F1ActionPerformed(evt);
            }
        });
        getContentPane().add(F1, new org.netbeans.lib.awtextra.AbsoluteConstraints(715, 300, 50, 30));

        G2.setBackground(new java.awt.Color(0, 255, 0));
        G2.setText("G2");
        G2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                G2ActionPerformed(evt);
            }
        });
        getContentPane().add(G2, new org.netbeans.lib.awtextra.AbsoluteConstraints(765, 340, 50, 30));

        G4.setBackground(new java.awt.Color(0, 255, 0));
        G4.setText("G4");
        G4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                G4ActionPerformed(evt);
            }
        });
        getContentPane().add(G4, new org.netbeans.lib.awtextra.AbsoluteConstraints(885, 340, 50, 30));

        G3.setBackground(new java.awt.Color(0, 255, 0));
        G3.setFont(new java.awt.Font("Segoe UI Symbol", 0, 12)); // NOI18N
        G3.setText("G3");
        G3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                G3ActionPerformed(evt);
            }
        });
        getContentPane().add(G3, new org.netbeans.lib.awtextra.AbsoluteConstraints(835, 340, 50, 30));

        H2.setBackground(new java.awt.Color(0, 255, 0));
        H2.setText("H2");
        H2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                H2ActionPerformed(evt);
            }
        });
        getContentPane().add(H2, new org.netbeans.lib.awtextra.AbsoluteConstraints(765, 380, 50, 30));

        H4.setBackground(new java.awt.Color(0, 255, 0));
        H4.setText("H4");
        H4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                H4ActionPerformed(evt);
            }
        });
        getContentPane().add(H4, new org.netbeans.lib.awtextra.AbsoluteConstraints(885, 380, 50, 30));

        H3.setBackground(new java.awt.Color(0, 255, 0));
        H3.setFont(new java.awt.Font("Segoe UI Symbol", 0, 12)); // NOI18N
        H3.setText("H3");
        H3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                H3ActionPerformed(evt);
            }
        });
        getContentPane().add(H3, new org.netbeans.lib.awtextra.AbsoluteConstraints(835, 380, 50, 30));

        E1.setBackground(new java.awt.Color(0, 255, 0));
        E1.setFont(new java.awt.Font("Segoe UI Symbol", 0, 12)); // NOI18N
        E1.setText("E1");
        E1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                E1ActionPerformed(evt);
            }
        });
        getContentPane().add(E1, new org.netbeans.lib.awtextra.AbsoluteConstraints(715, 260, 50, 30));

        H1.setBackground(new java.awt.Color(0, 255, 0));
        H1.setFont(new java.awt.Font("Segoe UI Symbol", 0, 12)); // NOI18N
        H1.setText("H1");
        H1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                H1ActionPerformed(evt);
            }
        });
        getContentPane().add(H1, new org.netbeans.lib.awtextra.AbsoluteConstraints(715, 380, 50, 30));

        I2.setBackground(new java.awt.Color(0, 255, 0));
        I2.setText("I2");
        I2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                I2ActionPerformed(evt);
            }
        });
        getContentPane().add(I2, new org.netbeans.lib.awtextra.AbsoluteConstraints(765, 420, 50, 30));

        I4.setBackground(new java.awt.Color(0, 255, 0));
        I4.setText("I4");
        I4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                I4ActionPerformed(evt);
            }
        });
        getContentPane().add(I4, new org.netbeans.lib.awtextra.AbsoluteConstraints(885, 420, 50, 30));

        I3.setBackground(new java.awt.Color(0, 255, 0));
        I3.setFont(new java.awt.Font("Segoe UI Symbol", 0, 12)); // NOI18N
        I3.setText("I3");
        I3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                I3ActionPerformed(evt);
            }
        });
        getContentPane().add(I3, new org.netbeans.lib.awtextra.AbsoluteConstraints(835, 420, 50, 30));

        I1.setBackground(new java.awt.Color(0, 255, 0));
        I1.setFont(new java.awt.Font("Segoe UI Symbol", 0, 12)); // NOI18N
        I1.setText("I1");
        I1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                I1ActionPerformed(evt);
            }
        });
        getContentPane().add(I1, new org.netbeans.lib.awtextra.AbsoluteConstraints(715, 420, 50, 30));

        J4.setBackground(new java.awt.Color(0, 255, 0));
        J4.setText("J4");
        J4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                J4ActionPerformed(evt);
            }
        });
        getContentPane().add(J4, new org.netbeans.lib.awtextra.AbsoluteConstraints(885, 460, 50, 30));

        J3.setBackground(new java.awt.Color(0, 255, 0));
        J3.setFont(new java.awt.Font("Segoe UI Symbol", 0, 12)); // NOI18N
        J3.setText("J3");
        J3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                J3ActionPerformed(evt);
            }
        });
        getContentPane().add(J3, new org.netbeans.lib.awtextra.AbsoluteConstraints(835, 460, 50, 30));

        J1.setBackground(new java.awt.Color(0, 255, 0));
        J1.setFont(new java.awt.Font("Segoe UI Symbol", 0, 12)); // NOI18N
        J1.setText("J1");
        J1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                J1ActionPerformed(evt);
            }
        });
        getContentPane().add(J1, new org.netbeans.lib.awtextra.AbsoluteConstraints(715, 460, 50, 30));

        J2.setBackground(new java.awt.Color(0, 255, 0));
        J2.setText("J2");
        J2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                J2ActionPerformed(evt);
            }
        });
        getContentPane().add(J2, new org.netbeans.lib.awtextra.AbsoluteConstraints(765, 460, 50, 30));
        getContentPane().add(Steer, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 44, 40, 40));

        jButton41.setBackground(new java.awt.Color(0, 255, 0));
        getContentPane().add(jButton41, new org.netbeans.lib.awtextra.AbsoluteConstraints(718, 40, 30, 20));

        jButton42.setBackground(new java.awt.Color(255, 0, 0));
        getContentPane().add(jButton42, new org.netbeans.lib.awtextra.AbsoluteConstraints(718, 70, 30, 20));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setText(":    Available");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(752, 36, 100, 20));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setText(":    Booked");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(752, 66, 100, 20));

        SeatB.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        getContentPane().add(SeatB, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 20, 230, 480));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel7.setText("CONTACT :");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 30, 70, 20));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel8.setText("FROM :");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 70, 50, 20));

        flevel.setFont(new java.awt.Font("SansSerif", 1, 15)); // NOI18N
        getContentPane().add(flevel, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 70, 120, 20));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel10.setText("TO :");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(492, 66, 30, 20));

        tlevel.setFont(new java.awt.Font("SansSerif", 1, 15)); // NOI18N
        getContentPane().add(tlevel, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 70, 130, 20));

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel12.setText("BUSNAME :");
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 100, 70, 20));

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        getContentPane().add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 120, -1, -1));

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel15.setText("DATE :");
        getContentPane().add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(486, 96, 40, 20));

        total_price.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        getContentPane().add(total_price, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 160, 120, 20));

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel17.setText("NO OF SEAT :");
        getContentPane().add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(233, 130, 80, 20));

        jLabel19.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel19.setText("TOTAL PRICE :");
        getContentPane().add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(448, 160, 90, 20));
        getContentPane().add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 140, 60, 20));

        nseat_field.setEditable(false);
        nseat_field.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        getContentPane().add(nseat_field, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 130, 70, 30));

        dateee.setEditable(false);
        dateee.setFont(new java.awt.Font("SansSerif", 1, 15)); // NOI18N
        dateee.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dateeeActionPerformed(evt);
            }
        });
        getContentPane().add(dateee, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 98, 140, 30));

        Reset.setBackground(new java.awt.Color(0, 0, 0));
        Reset.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        Reset.setForeground(new java.awt.Color(255, 255, 255));
        Reset.setText("RESET");
        Reset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ResetActionPerformed(evt);
            }
        });
        getContentPane().add(Reset, new org.netbeans.lib.awtextra.AbsoluteConstraints(302, 203, 80, 30));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel9.setText("PRICE :");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 160, 40, 20));

        ticket_price.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        getContentPane().add(ticket_price, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 160, 80, 20));

        Book.setBackground(new java.awt.Color(0, 0, 0));
        Book.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        Book.setForeground(new java.awt.Color(255, 255, 255));
        Book.setText("BOOK");
        Book.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BookActionPerformed(evt);
            }
        });
        getContentPane().add(Book, new org.netbeans.lib.awtextra.AbsoluteConstraints(422, 203, 80, 30));

        paysubmit_button.setBackground(new java.awt.Color(0, 0, 0));
        paysubmit_button.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        paysubmit_button.setForeground(new java.awt.Color(255, 255, 255));
        paysubmit_button.setText("PAY");
        paysubmit_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                paysubmit_buttonActionPerformed(evt);
            }
        });
        getContentPane().add(paysubmit_button, new org.netbeans.lib.awtextra.AbsoluteConstraints(542, 203, 80, 30));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Bus Name", "Ticket Fare", "Departure Time", "Arrival Time"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 250, 710, 250));

        jButton45.setBackground(new java.awt.Color(0, 0, 0));
        jButton45.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        jButton45.setForeground(new java.awt.Color(255, 255, 255));
        jButton45.setText("LOG OUT");
        jButton45.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton45ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton45, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 510, 140, 30));

        jButton46.setBackground(new java.awt.Color(0, 0, 0));
        jButton46.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        jButton46.setForeground(new java.awt.Color(255, 255, 255));
        jButton46.setText("MyBooking");
        jButton46.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton46ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton46, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 510, 120, 30));

        jButton47.setBackground(new java.awt.Color(0, 0, 0));
        jButton47.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        jButton47.setForeground(new java.awt.Color(255, 255, 255));
        jButton47.setText("Complain");
        jButton47.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton47ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton47, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 510, 100, 30));

        bus_name.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        bus_name.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select" }));
        bus_name.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bus_nameActionPerformed(evt);
            }
        });
        getContentPane().add(bus_name, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 100, 130, -1));

        jLabel16.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        getContentPane().add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 20, 480, 230));

        jDateChooser1.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jDateChooser1PropertyChange(evt);
            }
        });
        getContentPane().add(jDateChooser1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 120, 140, -1));

        jLabel11.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 20, 230, 230));
        getContentPane().add(Background, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 0, 970, 580));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void FromActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FromActionPerformed
        // TODO add your handling code here:
        SelectedFrom = From.getSelectedItem().toString();
        if (SelectedFrom == "Select") {
            //JOptionPane.showMessageDialog(rootPane, "Invalid !!!");
            flevel.setText("");
        } else
            flevel.setText(SelectedFrom);
    }//GEN-LAST:event_FromActionPerformed

    private void ToActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ToActionPerformed
        // TODO add your handling code here:
        SelectedTo = To.getSelectedItem().toString();
        if (SelectedFrom == SelectedTo || SelectedTo == "Select") {
            //JOptionPane.showMessageDialog(rootPane, "Invalid !!!");
            To.setSelectedIndex(0);
            tlevel.setText("");
        } else
            tlevel.setText(SelectedTo);
    }//GEN-LAST:event_ToActionPerformed

    private void A1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_A1ActionPerformed
        if (flevel.getText().isEmpty())
            JOptionPane.showMessageDialog(rootPane, "Select your starting place");
        else if (tlevel.getText().isEmpty())
            JOptionPane.showMessageDialog(rootPane, "Select your destination place");
        else if (ticket_price.getText().isEmpty())
            JOptionPane.showMessageDialog(rootPane, "Select your journey Bus Plz!!");
        else if (name_field.getText().isEmpty() || contact_field.getText().isEmpty() || dateee.getText().isEmpty())
            JOptionPane.showMessageDialog(rootPane, "Fill up required fields");
        else if (A1.getBackground() == red)
            JOptionPane.showMessageDialog(rootPane, "Seat Booked !!");
        else if (A1.getBackground() == yellow) {
            A1.setBackground(Color.green);
            Nseat--;
            String seat = Nseat.toString();
            nseat_field.setText(seat);
            selectedSeat = " ";
        } else if (Nseat == 4)
            JOptionPane.showMessageDialog(rootPane, "You can't buy more than 4 Tickets");
        else {
            A1.setBackground(Color.yellow);
            Nseat++;
            String seat = Nseat.toString();
            nseat_field.setText(seat);
            selectedSeat += "A1 ";
        }
    }//GEN-LAST:event_A1ActionPerformed

    private void jDateChooser2PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jDateChooser2PropertyChange
        // TODO add your handling code here:
        if ("date".equals(evt.getPropertyName())) {
            Date date = jDateChooser1.getDateEditor().getDate();
            SimpleDateFormat sf = new SimpleDateFormat("dd/MM/yyyy");
            String mydate = sf.format(date);
            dateee.setText(mydate);
        } else {
            dateee.setText(null);
        }
    }//GEN-LAST:event_jDateChooser2PropertyChange

    private void A2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_A2ActionPerformed
        // TODO add your handling code here:
        if (flevel.getText().isEmpty())
            JOptionPane.showMessageDialog(rootPane, "Select your starting place");
        else if (tlevel.getText().isEmpty())
            JOptionPane.showMessageDialog(rootPane, "Select your destination place");
        else if (ticket_price.getText().isEmpty())
            JOptionPane.showMessageDialog(rootPane, "Select your journey Bus Plz!!");
        else if (name_field.getText().isEmpty() || contact_field.getText().isEmpty() || dateee.getText().isEmpty())
            JOptionPane.showMessageDialog(rootPane, "Fill up required fields");
        else if (A2.getBackground() == red)
            JOptionPane.showMessageDialog(rootPane, "Seat Booked");
        else if (A2.getBackground() == yellow) {
            A2.setBackground(Color.green);
            Nseat--;
            String seat = Nseat.toString();
            nseat_field.setText(seat);
            selectedSeat = " ";
        } else if (Nseat == 4)
            JOptionPane.showMessageDialog(rootPane, "You can't buy more than 4 Tickets");
        else {
            A2.setBackground(Color.yellow);
            Nseat++;
            String seat = Nseat.toString();
            nseat_field.setText(seat);
            selectedSeat += "A2 ";
        }
    }//GEN-LAST:event_A2ActionPerformed

    private void A3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_A3ActionPerformed
        // TODO add your handling code here:
        if (flevel.getText().isEmpty())
            JOptionPane.showMessageDialog(rootPane, "Select your starting place");
        else if (tlevel.getText().isEmpty())
            JOptionPane.showMessageDialog(rootPane, "Select your destination place");
        else if (ticket_price.getText().isEmpty())
            JOptionPane.showMessageDialog(rootPane, "Select your journey Bus Plz!!");
        else if (name_field.getText().isEmpty() || contact_field.getText().isEmpty() || dateee.getText().isEmpty())
            JOptionPane.showMessageDialog(rootPane, "Fill up required fields");
        else if (A3.getBackground() == red)
            JOptionPane.showMessageDialog(rootPane, "Seat Booked");
        else if (A3.getBackground() == yellow) {
            A3.setBackground(Color.green);
            Nseat--;
            String seat = Nseat.toString();
            nseat_field.setText(seat);
            selectedSeat = " ";
        } else if (Nseat == 4)
            JOptionPane.showMessageDialog(rootPane, "You can't buy more than 4 Tickets");
        else {
            A3.setBackground(Color.yellow);
            Nseat++;
            String seat = Nseat.toString();
            nseat_field.setText(seat);
            selectedSeat += "A3 ";
        }
    }//GEN-LAST:event_A3ActionPerformed

    private void A4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_A4ActionPerformed
        // TODO add your handling code here:
        if (flevel.getText().isEmpty())
            JOptionPane.showMessageDialog(rootPane, "Select your starting place");
        else if (tlevel.getText().isEmpty())
            JOptionPane.showMessageDialog(rootPane, "Select your destination place");
        else if (ticket_price.getText().isEmpty())
            JOptionPane.showMessageDialog(rootPane, "Select your journey Bus Plz!!");
        else if (name_field.getText().isEmpty() || contact_field.getText().isEmpty() || dateee.getText().isEmpty())
            JOptionPane.showMessageDialog(rootPane, "Fill up required fields");
        else if (A4.getBackground() == red)
            JOptionPane.showMessageDialog(rootPane, "Seat Booked");
        else if (A4.getBackground() == yellow) {
            A4.setBackground(Color.green);
            Nseat--;
            String seat = Nseat.toString();
            nseat_field.setText(seat);
            selectedSeat = " ";
        } else if (Nseat == 4)
            JOptionPane.showMessageDialog(rootPane, "You can't buy more than 4 Tickets");
        else {
            A4.setBackground(Color.yellow);
            Nseat++;
            String seat = Nseat.toString();
            nseat_field.setText(seat);
            selectedSeat += "A4 ";
        }
    }//GEN-LAST:event_A4ActionPerformed

    private void B1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B1ActionPerformed
        // TODO add your handling code here:
        if (flevel.getText().isEmpty())
            JOptionPane.showMessageDialog(rootPane, "Select your starting place");
        else if (tlevel.getText().isEmpty())
            JOptionPane.showMessageDialog(rootPane, "Select your destination place");
        else if (ticket_price.getText().isEmpty())
            JOptionPane.showMessageDialog(rootPane, "Select your journey Bus Plz!!");
        else if (name_field.getText().isEmpty() || contact_field.getText().isEmpty() || dateee.getText().isEmpty())
            JOptionPane.showMessageDialog(rootPane, "Fill up required fields");
        else if (B1.getBackground() == red)
            JOptionPane.showMessageDialog(rootPane, "Seat Booked");
        else if (B1.getBackground() == yellow) {
            B1.setBackground(Color.green);
            Nseat--;
            String seat = Nseat.toString();
            nseat_field.setText(seat);
            selectedSeat = " ";
        } else if (Nseat == 4)
            JOptionPane.showMessageDialog(rootPane, "You can't buy more than 4 Tickets");
        else {
            B1.setBackground(Color.yellow);
            Nseat++;
            String seat = Nseat.toString();
            nseat_field.setText(seat);
            selectedSeat += "B1 ";
        }
    }//GEN-LAST:event_B1ActionPerformed

    private void B2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B2ActionPerformed
        // TODO add your handling code here:
        if (flevel.getText().isEmpty())
            JOptionPane.showMessageDialog(rootPane, "Select your starting place");
        else if (tlevel.getText().isEmpty())
            JOptionPane.showMessageDialog(rootPane, "Select your destination place");
        else if (ticket_price.getText().isEmpty())
            JOptionPane.showMessageDialog(rootPane, "Select your journey Bus Plz!!");
        else if (name_field.getText().isEmpty() || contact_field.getText().isEmpty() || dateee.getText().isEmpty())
            JOptionPane.showMessageDialog(rootPane, "Fill up required fields");
        else if (B2.getBackground() == red)
            JOptionPane.showMessageDialog(rootPane, "Seat Booked");
        else if (B2.getBackground() == yellow) {
            B2.setBackground(Color.green);
            Nseat--;
            String seat = Nseat.toString();
            nseat_field.setText(seat);
            selectedSeat = " ";
        } else if (Nseat == 4)
            JOptionPane.showMessageDialog(rootPane, "You can't buy more than 4 Tickets");
        else {
            B2.setBackground(Color.yellow);
            Nseat++;
            String seat = Nseat.toString();
            nseat_field.setText(seat);
            selectedSeat += "B2 ";
        }
    }//GEN-LAST:event_B2ActionPerformed

    private void B3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B3ActionPerformed
        // TODO add your handling code here:
        if (flevel.getText().isEmpty())
            JOptionPane.showMessageDialog(rootPane, "Select your starting place");
        else if (tlevel.getText().isEmpty())
            JOptionPane.showMessageDialog(rootPane, "Select your destination place");
        else if (ticket_price.getText().isEmpty())
            JOptionPane.showMessageDialog(rootPane, "Select your journey Bus Plz!!");
        else if (name_field.getText().isEmpty() || contact_field.getText().isEmpty() || dateee.getText().isEmpty())
            JOptionPane.showMessageDialog(rootPane, "Fill up required fields");
        else if (B3.getBackground() == red)
            JOptionPane.showMessageDialog(rootPane, "Seat Booked");
        else if (B3.getBackground() == yellow) {
            B3.setBackground(Color.green);
            Nseat--;
            String seat = Nseat.toString();
            nseat_field.setText(seat);
            selectedSeat = " ";
        } else if (Nseat == 4)
            JOptionPane.showMessageDialog(rootPane, "You can't buy more than 4 Tickets");
        else {
            B3.setBackground(Color.yellow);
            Nseat++;
            String seat = Nseat.toString();
            nseat_field.setText(seat);
            selectedSeat += "B3 ";
        }
    }//GEN-LAST:event_B3ActionPerformed

    private void B4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B4ActionPerformed
        // TODO add your handling code here:
        if (flevel.getText().isEmpty())
            JOptionPane.showMessageDialog(rootPane, "Select your starting place");
        else if (tlevel.getText().isEmpty())
            JOptionPane.showMessageDialog(rootPane, "Select your destination place");
        else if (ticket_price.getText().isEmpty())
            JOptionPane.showMessageDialog(rootPane, "Select your journey Bus Plz!!");
        else if (name_field.getText().isEmpty() || contact_field.getText().isEmpty() || dateee.getText().isEmpty())
            JOptionPane.showMessageDialog(rootPane, "Fill up required fields");
        else if (B4.getBackground() == red)
            JOptionPane.showMessageDialog(rootPane, "Seat Booked");
        else if (B4.getBackground() == yellow) {
            B4.setBackground(Color.green);
            Nseat--;
            String seat = Nseat.toString();
            nseat_field.setText(seat);
            selectedSeat = " ";
        } else if (Nseat == 4)
            JOptionPane.showMessageDialog(rootPane, "You can't buy more than 4 Tickets");
        else {
            B4.setBackground(Color.yellow);
            Nseat++;
            String seat = Nseat.toString();
            nseat_field.setText(seat);
            selectedSeat += "B4 ";
        }
    }//GEN-LAST:event_B4ActionPerformed

    private void C1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C1ActionPerformed
        // TODO add your handling code here:
        if (flevel.getText().isEmpty())
            JOptionPane.showMessageDialog(rootPane, "Select your starting place");
        else if (tlevel.getText().isEmpty())
            JOptionPane.showMessageDialog(rootPane, "Select your destination place");
        else if (ticket_price.getText().isEmpty())
            JOptionPane.showMessageDialog(rootPane, "Select your journey Bus Plz!!");
        else if (name_field.getText().isEmpty() || contact_field.getText().isEmpty() || dateee.getText().isEmpty())
            JOptionPane.showMessageDialog(rootPane, "Fill up required fields");
        else if (C1.getBackground() == red)
            JOptionPane.showMessageDialog(rootPane, "Seat Booked");
        else if (C1.getBackground() == yellow) {
            C1.setBackground(Color.green);
            Nseat--;
            String seat = Nseat.toString();
            nseat_field.setText(seat);
            selectedSeat = " ";
        } else if (Nseat == 4)
            JOptionPane.showMessageDialog(rootPane, "You can't buy more than 4 Tickets");
        else {
            C1.setBackground(Color.yellow);
            Nseat++;
            String seat = Nseat.toString();
            nseat_field.setText(seat);
            selectedSeat += "C1 ";
        }
    }//GEN-LAST:event_C1ActionPerformed

    private void C2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C2ActionPerformed
        if (flevel.getText().isEmpty())
            JOptionPane.showMessageDialog(rootPane, "Select your starting place");
        else if (tlevel.getText().isEmpty())
            JOptionPane.showMessageDialog(rootPane, "Select your destination place");
        else if (ticket_price.getText().isEmpty())
            JOptionPane.showMessageDialog(rootPane, "Select your journey Bus Plz!!");
        else if (name_field.getText().isEmpty() || contact_field.getText().isEmpty() || dateee.getText().isEmpty())
            JOptionPane.showMessageDialog(rootPane, "Fill up required fields");
        else if (C2.getBackground() == red)
            JOptionPane.showMessageDialog(rootPane, "Seat Booked");
        else if (C2.getBackground() == yellow) {
            C2.setBackground(Color.green);
            Nseat--;
            String seat = Nseat.toString();
            nseat_field.setText(seat);
            selectedSeat = " ";
        } else if (Nseat == 4)
            JOptionPane.showMessageDialog(rootPane, "You can't buy more than 4 Tickets");
        else {
            C2.setBackground(Color.yellow);
            Nseat++;
            String seat = Nseat.toString();
            nseat_field.setText(seat);
            selectedSeat += "C2 ";
        }
    }//GEN-LAST:event_C2ActionPerformed

    private void C3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C3ActionPerformed
        // TODO add your handling code here:
        if (flevel.getText().isEmpty())
            JOptionPane.showMessageDialog(rootPane, "Select your starting place");
        else if (tlevel.getText().isEmpty())
            JOptionPane.showMessageDialog(rootPane, "Select your destination place");
        else if (ticket_price.getText().isEmpty())
            JOptionPane.showMessageDialog(rootPane, "Select your journey Bus Plz!!");
        else if (name_field.getText().isEmpty() || contact_field.getText().isEmpty() || dateee.getText().isEmpty())
            JOptionPane.showMessageDialog(rootPane, "Fill up required fields");
        else if (C3.getBackground() == red)
            JOptionPane.showMessageDialog(rootPane, "Seat Booked");
        else if (C3.getBackground() == yellow) {
            C3.setBackground(Color.green);
            Nseat--;
            String seat = Nseat.toString();
            nseat_field.setText(seat);
            selectedSeat = " ";
        } else if (Nseat == 4)
            JOptionPane.showMessageDialog(rootPane, "You can't buy more than 4 Tickets");
        else {
            C3.setBackground(Color.yellow);
            Nseat++;
            String seat = Nseat.toString();
            nseat_field.setText(seat);
            selectedSeat += "C3 ";
        }
    }//GEN-LAST:event_C3ActionPerformed

    private void C4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C4ActionPerformed
        // TODO add your handling code here:
        if (flevel.getText().isEmpty())
            JOptionPane.showMessageDialog(rootPane, "Select your starting place");
        else if (tlevel.getText().isEmpty())
            JOptionPane.showMessageDialog(rootPane, "Select your destination place");
        else if (ticket_price.getText().isEmpty())
            JOptionPane.showMessageDialog(rootPane, "Select your journey Bus Plz!!");
        else if (name_field.getText().isEmpty() || contact_field.getText().isEmpty() || dateee.getText().isEmpty())
            JOptionPane.showMessageDialog(rootPane, "Fill up required fields");
        else if (C4.getBackground() == red)
            JOptionPane.showMessageDialog(rootPane, "Seat Booked");
        else if (C4.getBackground() == yellow) {
            C4.setBackground(Color.green);
            Nseat--;
            String seat = Nseat.toString();
            nseat_field.setText(seat);
            selectedSeat = " ";
        } else if (Nseat == 4)
            JOptionPane.showMessageDialog(rootPane, "You can't buy more than 4 Tickets");
        else {
            C4.setBackground(Color.yellow);
            Nseat++;
            String seat = Nseat.toString();
            nseat_field.setText(seat);
            selectedSeat += "C4 ";
        }
    }//GEN-LAST:event_C4ActionPerformed

    private void jButton45ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton45ActionPerformed
        // TODO add your handling code here:
        User_Login lg = new User_Login();
        lg.setVisible(true);
        dispose();
    }//GEN-LAST:event_jButton45ActionPerformed

    private void D1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_D1ActionPerformed
        if (flevel.getText().isEmpty())
            JOptionPane.showMessageDialog(rootPane, "Select your starting place");
        else if (tlevel.getText().isEmpty())
            JOptionPane.showMessageDialog(rootPane, "Select your destination place");
        else if (ticket_price.getText().isEmpty())
            JOptionPane.showMessageDialog(rootPane, "Select your journey Bus Plz!!");
        else if (name_field.getText().isEmpty() || contact_field.getText().isEmpty() || dateee.getText().isEmpty())
            JOptionPane.showMessageDialog(rootPane, "Fill up required fields");
        else if (D1.getBackground() == red)
            JOptionPane.showMessageDialog(rootPane, "Seat Booked");
        else if (D1.getBackground() == yellow) {
            D1.setBackground(Color.green);
            Nseat--;
            String seat = Nseat.toString();
            nseat_field.setText(seat);
            selectedSeat = " ";
        } else if (Nseat == 4)
            JOptionPane.showMessageDialog(rootPane, "You can't buy more than 4 Tickets");
        else {
            D1.setBackground(Color.yellow);
            Nseat++;
            String seat = Nseat.toString();
            nseat_field.setText(seat);
            selectedSeat += "D1 ";
        }
    }//GEN-LAST:event_D1ActionPerformed

    private void D2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_D2ActionPerformed
        if (flevel.getText().isEmpty())
            JOptionPane.showMessageDialog(rootPane, "Select your starting place");
        else if (tlevel.getText().isEmpty())
            JOptionPane.showMessageDialog(rootPane, "Select your destination place");
        else if (ticket_price.getText().isEmpty())
            JOptionPane.showMessageDialog(rootPane, "Select your journey Bus Plz!!");
        else if (name_field.getText().isEmpty() || contact_field.getText().isEmpty() || dateee.getText().isEmpty())
            JOptionPane.showMessageDialog(rootPane, "Fill up required fields");
        else if (D2.getBackground() == red)
            JOptionPane.showMessageDialog(rootPane, "Seat Booked");
        else if (D2.getBackground() == yellow) {
            D2.setBackground(Color.green);
            Nseat--;
            String seat = Nseat.toString();
            nseat_field.setText(seat);
            selectedSeat = " ";
        } else if (Nseat == 4)
            JOptionPane.showMessageDialog(rootPane, "You can't buy more than 4 Tickets");
        else {
            D2.setBackground(Color.yellow);
            Nseat++;
            String seat = Nseat.toString();
            nseat_field.setText(seat);
            selectedSeat += "D2 ";
        }
    }//GEN-LAST:event_D2ActionPerformed

    private void D3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_D3ActionPerformed
        if (flevel.getText().isEmpty())
            JOptionPane.showMessageDialog(rootPane, "Select your starting place");
        else if (tlevel.getText().isEmpty())
            JOptionPane.showMessageDialog(rootPane, "Select your destination place");
        else if (ticket_price.getText().isEmpty())
            JOptionPane.showMessageDialog(rootPane, "Select your journey Bus Plz!!");
        else if (name_field.getText().isEmpty() || contact_field.getText().isEmpty() || dateee.getText().isEmpty())
            JOptionPane.showMessageDialog(rootPane, "Fill up required fields");
        else if (D3.getBackground() == red)
            JOptionPane.showMessageDialog(rootPane, "Seat Booked");
        else if (D3.getBackground() == yellow) {
            D3.setBackground(Color.green);
            Nseat--;
            String seat = Nseat.toString();
            nseat_field.setText(seat);
            selectedSeat = " ";
        } else if (Nseat == 4)
            JOptionPane.showMessageDialog(rootPane, "You can't buy more than 4 Tickets");
        else {
            D3.setBackground(Color.yellow);
            Nseat++;
            String seat = Nseat.toString();
            nseat_field.setText(seat);
            selectedSeat += "D3 ";
        }
    }//GEN-LAST:event_D3ActionPerformed

    private void D4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_D4ActionPerformed
        if (flevel.getText().isEmpty())
            JOptionPane.showMessageDialog(rootPane, "Select your starting place");
        else if (tlevel.getText().isEmpty())
            JOptionPane.showMessageDialog(rootPane, "Select your destination place");
        else if (ticket_price.getText().isEmpty())
            JOptionPane.showMessageDialog(rootPane, "Select your journey Bus Plz!!");
        else if (name_field.getText().isEmpty() || contact_field.getText().isEmpty() || dateee.getText().isEmpty())
            JOptionPane.showMessageDialog(rootPane, "Fill up required fields");
        else if (D4.getBackground() == red)
            JOptionPane.showMessageDialog(rootPane, "Seat Booked");
        else if (D4.getBackground() == yellow) {
            D4.setBackground(Color.green);
            Nseat--;
            String seat = Nseat.toString();
            nseat_field.setText(seat);
            selectedSeat = " ";
        } else if (Nseat == 4)
            JOptionPane.showMessageDialog(rootPane, "You can't buy more than 4 Tickets");
        else {
            D4.setBackground(Color.yellow);
            Nseat++;
            String seat = Nseat.toString();
            nseat_field.setText(seat);
            selectedSeat += "D4 ";
        }
    }//GEN-LAST:event_D4ActionPerformed

    private void E1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_E1ActionPerformed
        if (flevel.getText().isEmpty())
            JOptionPane.showMessageDialog(rootPane, "Select your starting place");
        else if (tlevel.getText().isEmpty())
            JOptionPane.showMessageDialog(rootPane, "Select your destination place");
        else if (ticket_price.getText().isEmpty())
            JOptionPane.showMessageDialog(rootPane, "Select your journey Bus Plz!!");
        else if (name_field.getText().isEmpty() || contact_field.getText().isEmpty() || dateee.getText().isEmpty())
            JOptionPane.showMessageDialog(rootPane, "Fill up required fields");
        else if (E1.getBackground() == red)
            JOptionPane.showMessageDialog(rootPane, "Seat Booked");
        else if (E1.getBackground() == yellow) {
            E1.setBackground(Color.green);
            Nseat--;
            String seat = Nseat.toString();
            nseat_field.setText(seat);
            selectedSeat = " ";
        } else if (Nseat == 4)
            JOptionPane.showMessageDialog(rootPane, "You can't buy more than 4 Tickets");
        else {
            E1.setBackground(Color.yellow);
            Nseat++;
            String seat = Nseat.toString();
            nseat_field.setText(seat);
            selectedSeat += "E1 ";
        }
    }//GEN-LAST:event_E1ActionPerformed

    private void E2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_E2ActionPerformed
        if (flevel.getText().isEmpty())
            JOptionPane.showMessageDialog(rootPane, "Select your starting place");
        else if (tlevel.getText().isEmpty())
            JOptionPane.showMessageDialog(rootPane, "Select your destination place");
        else if (ticket_price.getText().isEmpty())
            JOptionPane.showMessageDialog(rootPane, "Select your journey Bus Plz!!");
        else if (name_field.getText().isEmpty() || contact_field.getText().isEmpty() || dateee.getText().isEmpty())
            JOptionPane.showMessageDialog(rootPane, "Fill up required fields");
        else if (E2.getBackground() == red)
            JOptionPane.showMessageDialog(rootPane, "Seat Booked");
        else if (E2.getBackground() == yellow) {
            E2.setBackground(Color.green);
            Nseat--;
            String seat = Nseat.toString();
            nseat_field.setText(seat);
            selectedSeat = " ";
        } else if (Nseat == 4)
            JOptionPane.showMessageDialog(rootPane, "You can't buy more than 4 Tickets");
        else {
            E2.setBackground(Color.yellow);
            Nseat++;
            String seat = Nseat.toString();
            nseat_field.setText(seat);
            selectedSeat += "E2 ";
        }
    }//GEN-LAST:event_E2ActionPerformed

    private void E3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_E3ActionPerformed
        // TODO add your handling code here:
        if (flevel.getText().isEmpty())
            JOptionPane.showMessageDialog(rootPane, "Select your starting place");
        else if (tlevel.getText().isEmpty())
            JOptionPane.showMessageDialog(rootPane, "Select your destination place");
        else if (ticket_price.getText().isEmpty())
            JOptionPane.showMessageDialog(rootPane, "Select your journey Bus Plz!!");
        else if (name_field.getText().isEmpty() || contact_field.getText().isEmpty() || dateee.getText().isEmpty())
            JOptionPane.showMessageDialog(rootPane, "Fill up required fields");
        else if (E3.getBackground() == red)
            JOptionPane.showMessageDialog(rootPane, "Seat Booked");
        else if (E3.getBackground() == yellow) {
            E3.setBackground(Color.green);
            Nseat--;
            String seat = Nseat.toString();
            nseat_field.setText(seat);
            selectedSeat = " ";
        } else if (Nseat == 4)
            JOptionPane.showMessageDialog(rootPane, "You can't buy more than 4 Tickets");
        else {
            E3.setBackground(Color.yellow);
            Nseat++;
            String seat = Nseat.toString();
            nseat_field.setText(seat);
            selectedSeat += "E3 ";
        }
    }//GEN-LAST:event_E3ActionPerformed

    private void E4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_E4ActionPerformed
        // TODO add your handling code here:
        if (flevel.getText().isEmpty())
            JOptionPane.showMessageDialog(rootPane, "Select your starting place");
        else if (tlevel.getText().isEmpty())
            JOptionPane.showMessageDialog(rootPane, "Select your destination place");
        else if (ticket_price.getText().isEmpty())
            JOptionPane.showMessageDialog(rootPane, "Select your journey Bus Plz!!");
        else if (name_field.getText().isEmpty() || contact_field.getText().isEmpty() || dateee.getText().isEmpty())
            JOptionPane.showMessageDialog(rootPane, "Fill up required fields");
        else if (E4.getBackground() == red)
            JOptionPane.showMessageDialog(rootPane, "Seat Booked");
        else if (E4.getBackground() == yellow) {
            E4.setBackground(Color.green);
            Nseat--;
            String seat = Nseat.toString();
            nseat_field.setText(seat);
            selectedSeat = " ";
        } else if (Nseat == 4)
            JOptionPane.showMessageDialog(rootPane, "You can't buy more than 4 Tickets");
        else {
            E4.setBackground(Color.yellow);
            Nseat++;
            String seat = Nseat.toString();
            nseat_field.setText(seat);
            selectedSeat += "E4 ";
        }
    }//GEN-LAST:event_E4ActionPerformed

    private void F1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_F1ActionPerformed
        // TODO add your handling code here:
        if (flevel.getText().isEmpty())
            JOptionPane.showMessageDialog(rootPane, "Select your starting place");
        else if (tlevel.getText().isEmpty())
            JOptionPane.showMessageDialog(rootPane, "Select your destination place");
        else if (ticket_price.getText().isEmpty())
            JOptionPane.showMessageDialog(rootPane, "Select your journey Bus Plz!!");
        else if (name_field.getText().isEmpty() || contact_field.getText().isEmpty() || dateee.getText().isEmpty())
            JOptionPane.showMessageDialog(rootPane, "Fill up required fields");
        else if (F1.getBackground() == red)
            JOptionPane.showMessageDialog(rootPane, "Seat Booked");
        else if (F1.getBackground() == yellow) {
            F1.setBackground(Color.green);
            Nseat--;
            String seat = Nseat.toString();
            nseat_field.setText(seat);
            selectedSeat = " ";
        } else if (Nseat == 4)
            JOptionPane.showMessageDialog(rootPane, "You can't buy more than 4 Tickets");
        else {
            F1.setBackground(Color.yellow);
            Nseat++;
            String seat = Nseat.toString();
            nseat_field.setText(seat);
            selectedSeat += "F1 ";
        }
    }//GEN-LAST:event_F1ActionPerformed

    private void F2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_F2ActionPerformed
        // TODO add your handling code here:
        if (flevel.getText().isEmpty())
            JOptionPane.showMessageDialog(rootPane, "Select your starting place");
        else if (tlevel.getText().isEmpty())
            JOptionPane.showMessageDialog(rootPane, "Select your destination place");
        else if (ticket_price.getText().isEmpty())
            JOptionPane.showMessageDialog(rootPane, "Select your journey Bus Plz!!");
        else if (name_field.getText().isEmpty() || contact_field.getText().isEmpty() || dateee.getText().isEmpty())
            JOptionPane.showMessageDialog(rootPane, "Fill up required fields");
        else if (F2.getBackground() == red)
            JOptionPane.showMessageDialog(rootPane, "Seat Booked");
        else if (F2.getBackground() == yellow) {
            F2.setBackground(Color.green);
            Nseat--;
            String seat = Nseat.toString();
            nseat_field.setText(seat);
            selectedSeat = " ";
        } else if (Nseat == 4)
            JOptionPane.showMessageDialog(rootPane, "You can't buy more than 4 Tickets");
        else {
            F2.setBackground(Color.yellow);
            Nseat++;
            String seat = Nseat.toString();
            nseat_field.setText(seat);
            selectedSeat += "F2 ";
        }
    }//GEN-LAST:event_F2ActionPerformed

    private void F3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_F3ActionPerformed
        // TODO add your handling code here:
        if (flevel.getText().isEmpty())
            JOptionPane.showMessageDialog(rootPane, "Select your starting place");
        else if (tlevel.getText().isEmpty())
            JOptionPane.showMessageDialog(rootPane, "Select your destination place");
        else if (ticket_price.getText().isEmpty())
            JOptionPane.showMessageDialog(rootPane, "Select your journey Bus Plz!!");
        else if (name_field.getText().isEmpty() || contact_field.getText().isEmpty() || dateee.getText().isEmpty())
            JOptionPane.showMessageDialog(rootPane, "Fill up required fields");
        else if (F3.getBackground() == red)
            JOptionPane.showMessageDialog(rootPane, "Seat Booked");
        else if (F3.getBackground() == yellow) {
            F3.setBackground(Color.green);
            Nseat--;
            String seat = Nseat.toString();
            nseat_field.setText(seat);
            selectedSeat = " ";
        } else if (Nseat == 4)
            JOptionPane.showMessageDialog(rootPane, "You can't buy more than 4 Tickets");
        else {
            F3.setBackground(Color.yellow);
            Nseat++;
            String seat = Nseat.toString();
            nseat_field.setText(seat);
            selectedSeat += "F3 ";
        }
    }//GEN-LAST:event_F3ActionPerformed

    private void F4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_F4ActionPerformed
        // TODO add your handling code here:
        if (flevel.getText().isEmpty())
            JOptionPane.showMessageDialog(rootPane, "Select your starting place");
        else if (tlevel.getText().isEmpty())
            JOptionPane.showMessageDialog(rootPane, "Select your destination place");
        else if (ticket_price.getText().isEmpty())
            JOptionPane.showMessageDialog(rootPane, "Select your journey Bus Plz!!");
        else if (name_field.getText().isEmpty() || contact_field.getText().isEmpty() || dateee.getText().isEmpty())
            JOptionPane.showMessageDialog(rootPane, "Fill up required fields");
        else if (F4.getBackground() == red)
            JOptionPane.showMessageDialog(rootPane, "Seat Booked");
        else if (F4.getBackground() == yellow) {
            F4.setBackground(Color.green);
            Nseat--;
            String seat = Nseat.toString();
            nseat_field.setText(seat);
            selectedSeat = " ";
        } else if (Nseat == 4)
            JOptionPane.showMessageDialog(rootPane, "You can't buy more than 4 Tickets");
        else {
            F4.setBackground(Color.yellow);
            Nseat++;
            String seat = Nseat.toString();
            nseat_field.setText(seat);
            selectedSeat += "F4 ";
        }
    }//GEN-LAST:event_F4ActionPerformed

    private void G1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_G1ActionPerformed
        // TODO add your handling code here:
        if (flevel.getText().isEmpty())
            JOptionPane.showMessageDialog(rootPane, "Select your starting place");
        else if (tlevel.getText().isEmpty())
            JOptionPane.showMessageDialog(rootPane, "Select your destination place");
        else if (ticket_price.getText().isEmpty())
            JOptionPane.showMessageDialog(rootPane, "Select your journey Bus Plz!!");
        else if (name_field.getText().isEmpty() || contact_field.getText().isEmpty() || dateee.getText().isEmpty())
            JOptionPane.showMessageDialog(rootPane, "Fill up required fields");
        else if (G1.getBackground() == red)
            JOptionPane.showMessageDialog(rootPane, "Seat Booked");
        else if (G1.getBackground() == yellow) {
            G1.setBackground(Color.green);
            Nseat--;
            String seat = Nseat.toString();
            nseat_field.setText(seat);
            selectedSeat = " ";
        } else if (Nseat == 4)
            JOptionPane.showMessageDialog(rootPane, "You can't buy more than 4 Tickets");
        else {
            G1.setBackground(Color.yellow);
            Nseat++;
            String seat = Nseat.toString();
            nseat_field.setText(seat);
            selectedSeat += "G1 ";
        }
    }//GEN-LAST:event_G1ActionPerformed

    private void G2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_G2ActionPerformed
        // TODO add your handling code here:
        if (flevel.getText().isEmpty())
            JOptionPane.showMessageDialog(rootPane, "Select your starting place");
        else if (tlevel.getText().isEmpty())
            JOptionPane.showMessageDialog(rootPane, "Select your destination place");
        else if (ticket_price.getText().isEmpty())
            JOptionPane.showMessageDialog(rootPane, "Select your journey Bus Plz!!");
        else if (name_field.getText().isEmpty() || contact_field.getText().isEmpty() || dateee.getText().isEmpty())
            JOptionPane.showMessageDialog(rootPane, "Fill up required fields");
        else if (G2.getBackground() == red)
            JOptionPane.showMessageDialog(rootPane, "Seat Booked");
        else if (G2.getBackground() == yellow) {
            G2.setBackground(Color.green);
            Nseat--;
            String seat = Nseat.toString();
            nseat_field.setText(seat);
            selectedSeat = " ";
        } else if (Nseat == 4)
            JOptionPane.showMessageDialog(rootPane, "You can't buy more than 4 Tickets");
        else {
            G2.setBackground(Color.yellow);
            Nseat++;
            String seat = Nseat.toString();
            nseat_field.setText(seat);
            selectedSeat += "G2 ";
        }
    }//GEN-LAST:event_G2ActionPerformed

    private void G3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_G3ActionPerformed
        // TODO add your handling code here:
        if (flevel.getText().isEmpty())
            JOptionPane.showMessageDialog(rootPane, "Select your starting place");
        else if (tlevel.getText().isEmpty())
            JOptionPane.showMessageDialog(rootPane, "Select your destination place");
        else if (ticket_price.getText().isEmpty())
            JOptionPane.showMessageDialog(rootPane, "Select your journey Bus Plz!!");
        else if (name_field.getText().isEmpty() || contact_field.getText().isEmpty() || dateee.getText().isEmpty())
            JOptionPane.showMessageDialog(rootPane, "Fill up required fields");
        else if (G3.getBackground() == red)
            JOptionPane.showMessageDialog(rootPane, "Seat Booked");
        else if (G3.getBackground() == yellow) {
            G3.setBackground(Color.green);
            Nseat--;
            String seat = Nseat.toString();
            nseat_field.setText(seat);
            selectedSeat = " ";
        } else if (Nseat == 4)
            JOptionPane.showMessageDialog(rootPane, "You can't buy more than 4 Tickets");
        else {
            G3.setBackground(Color.yellow);
            Nseat++;
            String seat = Nseat.toString();
            nseat_field.setText(seat);
            selectedSeat += "G3 ";
        }
    }//GEN-LAST:event_G3ActionPerformed

    private void G4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_G4ActionPerformed
        // TODO add your handling code here:
        if (flevel.getText().isEmpty())
            JOptionPane.showMessageDialog(rootPane, "Select your starting place");
        else if (tlevel.getText().isEmpty())
            JOptionPane.showMessageDialog(rootPane, "Select your destination place");
        else if (ticket_price.getText().isEmpty())
            JOptionPane.showMessageDialog(rootPane, "Select your journey Bus Plz!!");
        else if (name_field.getText().isEmpty() || contact_field.getText().isEmpty() || dateee.getText().isEmpty())
            JOptionPane.showMessageDialog(rootPane, "Fill up required fields");
        else if (G4.getBackground() == red)
            JOptionPane.showMessageDialog(rootPane, "Seat Booked");
        else if (G4.getBackground() == yellow) {
            G4.setBackground(Color.green);
            Nseat--;
            String seat = Nseat.toString();
            nseat_field.setText(seat);
            selectedSeat = " ";
        } else if (Nseat == 4)
            JOptionPane.showMessageDialog(rootPane, "You can't buy more than 4 Tickets");
        else {
            G4.setBackground(Color.yellow);
            Nseat++;
            String seat = Nseat.toString();
            nseat_field.setText(seat);
            selectedSeat += "G4 ";
        }
    }//GEN-LAST:event_G4ActionPerformed

    private void H1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_H1ActionPerformed
        // TODO add your handling code here:
        if (flevel.getText().isEmpty())
            JOptionPane.showMessageDialog(rootPane, "Select your starting place");
        else if (tlevel.getText().isEmpty())
            JOptionPane.showMessageDialog(rootPane, "Select your destination place");
        else if (ticket_price.getText().isEmpty())
            JOptionPane.showMessageDialog(rootPane, "Select your journey Bus Plz!!");
        else if (name_field.getText().isEmpty() || contact_field.getText().isEmpty() || dateee.getText().isEmpty())
            JOptionPane.showMessageDialog(rootPane, "Fill up required fields");
        else if (H1.getBackground() == red)
            JOptionPane.showMessageDialog(rootPane, "Seat Booked");
        else if (H1.getBackground() == yellow) {
            H1.setBackground(Color.green);
            Nseat--;
            String seat = Nseat.toString();
            nseat_field.setText(seat);
            selectedSeat = " ";
        } else if (Nseat == 4)
            JOptionPane.showMessageDialog(rootPane, "You can't buy more than 4 Tickets");
        else {
            H1.setBackground(Color.yellow);
            Nseat++;
            String seat = Nseat.toString();
            nseat_field.setText(seat);
            selectedSeat += "H1 ";
        }
    }//GEN-LAST:event_H1ActionPerformed

    private void H2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_H2ActionPerformed
        // TODO add your handling code here:
        if (flevel.getText().isEmpty())
            JOptionPane.showMessageDialog(rootPane, "Select your starting place");
        else if (tlevel.getText().isEmpty())
            JOptionPane.showMessageDialog(rootPane, "Select your destination place");
        else if (ticket_price.getText().isEmpty())
            JOptionPane.showMessageDialog(rootPane, "Select your journey Bus Plz!!");
        else if (name_field.getText().isEmpty() || contact_field.getText().isEmpty() || dateee.getText().isEmpty())
            JOptionPane.showMessageDialog(rootPane, "Fill up required fields");
        else if (H2.getBackground() == red)
            JOptionPane.showMessageDialog(rootPane, "Seat Booked");
        else if (H2.getBackground() == yellow) {
            H2.setBackground(Color.green);
            Nseat--;
            String seat = Nseat.toString();
            nseat_field.setText(seat);
            selectedSeat = " ";
        } else if (Nseat == 4)
            JOptionPane.showMessageDialog(rootPane, "You can't buy more than 4 Tickets");
        else {
            H2.setBackground(Color.yellow);
            Nseat++;
            String seat = Nseat.toString();
            nseat_field.setText(seat);
            selectedSeat += "H2 ";
        }
    }//GEN-LAST:event_H2ActionPerformed

    private void H3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_H3ActionPerformed
        // TODO add your handling code here:
        if (flevel.getText().isEmpty())
            JOptionPane.showMessageDialog(rootPane, "Select your starting place");
        else if (tlevel.getText().isEmpty())
            JOptionPane.showMessageDialog(rootPane, "Select your destination place");
        else if (ticket_price.getText().isEmpty())
            JOptionPane.showMessageDialog(rootPane, "Select your journey Bus Plz!!");
        else if (name_field.getText().isEmpty() || contact_field.getText().isEmpty() || dateee.getText().isEmpty())
            JOptionPane.showMessageDialog(rootPane, "Fill up required fields");
        else if (H3.getBackground() == red)
            JOptionPane.showMessageDialog(rootPane, "Seat Booked");
        else if (H3.getBackground() == yellow) {
            H3.setBackground(Color.green);
            Nseat--;
            String seat = Nseat.toString();
            nseat_field.setText(seat);
            selectedSeat = " ";
        } else if (Nseat == 4)
            JOptionPane.showMessageDialog(rootPane, "You can't buy more than 4 Tickets");
        else {
            H3.setBackground(Color.yellow);
            Nseat++;
            String seat = Nseat.toString();
            nseat_field.setText(seat);
            selectedSeat += "H3 ";
        }
    }//GEN-LAST:event_H3ActionPerformed

    private void H4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_H4ActionPerformed
        if (flevel.getText().isEmpty())
            JOptionPane.showMessageDialog(rootPane, "Select your starting place");
        else if (tlevel.getText().isEmpty())
            JOptionPane.showMessageDialog(rootPane, "Select your destination place");
        else if (ticket_price.getText().isEmpty())
            JOptionPane.showMessageDialog(rootPane, "Select your journey Bus Plz!!");
        else if (name_field.getText().isEmpty() || contact_field.getText().isEmpty() || dateee.getText().isEmpty())
            JOptionPane.showMessageDialog(rootPane, "Fill up required fields");
        else if (H4.getBackground() == red)
            JOptionPane.showMessageDialog(rootPane, "Seat Booked");
        else if (H4.getBackground() == yellow) {
            H4.setBackground(Color.green);
            Nseat--;
            String seat = Nseat.toString();
            nseat_field.setText(seat);
            selectedSeat = " ";
        } else if (Nseat == 4)
            JOptionPane.showMessageDialog(rootPane, "You can't buy more than 4 Tickets");
        else {
            H4.setBackground(Color.yellow);
            Nseat++;
            String seat = Nseat.toString();
            nseat_field.setText(seat);
            selectedSeat += "H4 ";
        }
    }//GEN-LAST:event_H4ActionPerformed

    private void I1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_I1ActionPerformed
        // TODO add your handling code here:
        if (flevel.getText().isEmpty())
            JOptionPane.showMessageDialog(rootPane, "Select your starting place");
        else if (tlevel.getText().isEmpty())
            JOptionPane.showMessageDialog(rootPane, "Select your destination place");
        else if (ticket_price.getText().isEmpty())
            JOptionPane.showMessageDialog(rootPane, "Select your journey Bus Plz!!");
        else if (name_field.getText().isEmpty() || contact_field.getText().isEmpty() || dateee.getText().isEmpty())
            JOptionPane.showMessageDialog(rootPane, "Fill up required fields");
        else if (I1.getBackground() == red)
            JOptionPane.showMessageDialog(rootPane, "Seat Booked");
        else if (I1.getBackground() == yellow) {
            I1.setBackground(Color.green);
            Nseat--;
            String seat = Nseat.toString();
            nseat_field.setText(seat);
            selectedSeat = " ";
        } else if (Nseat == 4)
            JOptionPane.showMessageDialog(rootPane, "You can't buy more than 4 Tickets");
        else {
            I1.setBackground(Color.yellow);
            Nseat++;
            String seat = Nseat.toString();
            nseat_field.setText(seat);
            selectedSeat += "I1 ";
        }
    }//GEN-LAST:event_I1ActionPerformed

    private void I2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_I2ActionPerformed
        if (flevel.getText().isEmpty())
            JOptionPane.showMessageDialog(rootPane, "Select your starting place");
        else if (tlevel.getText().isEmpty())
            JOptionPane.showMessageDialog(rootPane, "Select your destination place");
        else if (ticket_price.getText().isEmpty())
            JOptionPane.showMessageDialog(rootPane, "Select your journey Bus Plz!!");
        else if (name_field.getText().isEmpty() || contact_field.getText().isEmpty() || dateee.getText().isEmpty())
            JOptionPane.showMessageDialog(rootPane, "Fill up required fields");
        else if (I2.getBackground() == red)
            JOptionPane.showMessageDialog(rootPane, "Seat Booked");
        else if (I2.getBackground() == yellow) {
            I2.setBackground(Color.green);
            Nseat--;
            String seat = Nseat.toString();
            nseat_field.setText(seat);
            selectedSeat = " ";
        } else if (Nseat == 4)
            JOptionPane.showMessageDialog(rootPane, "You can't buy more than 4 Tickets");
        else {
            I2.setBackground(Color.yellow);
            Nseat++;
            String seat = Nseat.toString();
            nseat_field.setText(seat);
            selectedSeat += "I2 ";
        }
    }//GEN-LAST:event_I2ActionPerformed

    private void I3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_I3ActionPerformed
        if (flevel.getText().isEmpty())
            JOptionPane.showMessageDialog(rootPane, "Select your starting place");
        else if (tlevel.getText().isEmpty())
            JOptionPane.showMessageDialog(rootPane, "Select your destination place");
        else if (ticket_price.getText().isEmpty())
            JOptionPane.showMessageDialog(rootPane, "Select your journey Bus Plz!!");
        else if (name_field.getText().isEmpty() || contact_field.getText().isEmpty() || dateee.getText().isEmpty())
            JOptionPane.showMessageDialog(rootPane, "Fill up required fields");
        else if (I3.getBackground() == red)
            JOptionPane.showMessageDialog(rootPane, "Seat Booked");
        else if (I3.getBackground() == yellow) {
            I3.setBackground(Color.green);
            Nseat--;
            String seat = Nseat.toString();
            nseat_field.setText(seat);
            selectedSeat = " ";
        } else if (Nseat == 4)
            JOptionPane.showMessageDialog(rootPane, "You can't buy more than 4 Tickets");
        else {
            I3.setBackground(Color.yellow);
            Nseat++;
            String seat = Nseat.toString();
            nseat_field.setText(seat);
            selectedSeat += "I3 ";
        }
    }//GEN-LAST:event_I3ActionPerformed

    private void I4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_I4ActionPerformed
        // TODO add your handling code here:
        if (flevel.getText().isEmpty())
            JOptionPane.showMessageDialog(rootPane, "Select your starting place");
        else if (tlevel.getText().isEmpty())
            JOptionPane.showMessageDialog(rootPane, "Select your destination place");
        else if (ticket_price.getText().isEmpty())
            JOptionPane.showMessageDialog(rootPane, "Select your journey Bus Plz!!");
        else if (name_field.getText().isEmpty() || contact_field.getText().isEmpty() || dateee.getText().isEmpty())
            JOptionPane.showMessageDialog(rootPane, "Fill up required fields");
        else if (I4.getBackground() == red)
            JOptionPane.showMessageDialog(rootPane, "Seat Booked");
        else if (I4.getBackground() == yellow) {
            I4.setBackground(Color.green);
            Nseat--;
            String seat = Nseat.toString();
            nseat_field.setText(seat);
            selectedSeat = " ";
        } else if (Nseat == 4)
            JOptionPane.showMessageDialog(rootPane, "You can't buy more than 4 Tickets");
        else {
            I4.setBackground(Color.yellow);
            Nseat++;
            String seat = Nseat.toString();
            nseat_field.setText(seat);
            selectedSeat += "I4 ";
        }
    }//GEN-LAST:event_I4ActionPerformed

    private void J1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_J1ActionPerformed
        // TODO add your handling code here:
        if (flevel.getText().isEmpty())
            JOptionPane.showMessageDialog(rootPane, "Select your starting place");
        else if (tlevel.getText().isEmpty())
            JOptionPane.showMessageDialog(rootPane, "Select your destination place");
        else if (ticket_price.getText().isEmpty())
            JOptionPane.showMessageDialog(rootPane, "Select your journey Bus Plz!!");
        else if (name_field.getText().isEmpty() || contact_field.getText().isEmpty() || dateee.getText().isEmpty())
            JOptionPane.showMessageDialog(rootPane, "Fill up required fields");
        else if (J1.getBackground() == red)
            JOptionPane.showMessageDialog(rootPane, "Seat Booked");
        else if (J1.getBackground() == yellow) {
            J1.setBackground(Color.green);
            Nseat--;
            String seat = Nseat.toString();
            nseat_field.setText(seat);
            selectedSeat = " ";
        } else if (Nseat == 4)
            JOptionPane.showMessageDialog(rootPane, "You can't buy more than 4 Tickets");
        else {
            J1.setBackground(Color.yellow);
            Nseat++;
            String seat = Nseat.toString();
            nseat_field.setText(seat);
            selectedSeat += "J1 ";
        }
    }//GEN-LAST:event_J1ActionPerformed

    private void J2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_J2ActionPerformed
        // TODO add your handling code here:
        if (flevel.getText().isEmpty())
            JOptionPane.showMessageDialog(rootPane, "Select your starting place");
        else if (tlevel.getText().isEmpty())
            JOptionPane.showMessageDialog(rootPane, "Select your destination place");
        else if (ticket_price.getText().isEmpty())
            JOptionPane.showMessageDialog(rootPane, "Select your journey Bus Plz!!");
        else if (name_field.getText().isEmpty() || contact_field.getText().isEmpty() || dateee.getText().isEmpty())
            JOptionPane.showMessageDialog(rootPane, "Fill up required fields");
        else if (J2.getBackground() == red)
            JOptionPane.showMessageDialog(rootPane, "Seat Booked");
        else if (J2.getBackground() == yellow) {
            J2.setBackground(Color.green);
            Nseat--;
            String seat = Nseat.toString();
            nseat_field.setText(seat);
            selectedSeat = " ";
        } else if (Nseat == 4)
            JOptionPane.showMessageDialog(rootPane, "You can't buy more than 4 Tickets");
        else {
            J2.setBackground(Color.yellow);
            Nseat++;
            String seat = Nseat.toString();
            nseat_field.setText(seat);
            selectedSeat += "J2 ";
        }
    }//GEN-LAST:event_J2ActionPerformed

    private void J3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_J3ActionPerformed
        // TODO add your handling code here:
        if (flevel.getText().isEmpty())
            JOptionPane.showMessageDialog(rootPane, "Select your starting place");
        else if (tlevel.getText().isEmpty())
            JOptionPane.showMessageDialog(rootPane, "Select your destination place");
        else if (ticket_price.getText().isEmpty())
            JOptionPane.showMessageDialog(rootPane, "Select your journey Bus Plz!!");
        else if (name_field.getText().isEmpty() || contact_field.getText().isEmpty() || dateee.getText().isEmpty())
            JOptionPane.showMessageDialog(rootPane, "Fill up required fields");
        else if (J3.getBackground() == red)
            JOptionPane.showMessageDialog(rootPane, "Seat Booked");
        else if (J3.getBackground() == yellow) {
            J3.setBackground(Color.green);
            Nseat--;
            String seat = Nseat.toString();
            nseat_field.setText(seat);
            selectedSeat = " ";
        } else if (Nseat == 4)
            JOptionPane.showMessageDialog(rootPane, "You can't buy more than 4 Tickets");
        else {
            J3.setBackground(Color.yellow);
            Nseat++;
            String seat = Nseat.toString();
            nseat_field.setText(seat);
            selectedSeat += "J3 ";
        }
    }//GEN-LAST:event_J3ActionPerformed

    private void J4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_J4ActionPerformed
        // TODO add your handling code here:
        if (flevel.getText().isEmpty())
            JOptionPane.showMessageDialog(rootPane, "Select your starting place");
        else if (tlevel.getText().isEmpty())
            JOptionPane.showMessageDialog(rootPane, "Select your destination place");
        else if (ticket_price.getText().isEmpty())
            JOptionPane.showMessageDialog(rootPane, "Select your journey Bus Plz!!");
        else if (name_field.getText().isEmpty() || contact_field.getText().isEmpty() || dateee.getText().isEmpty())
            JOptionPane.showMessageDialog(rootPane, "Fill up required fields");
        else if (J4.getBackground() == red)
            JOptionPane.showMessageDialog(rootPane, "Seat Booked");
        else if (J4.getBackground() == yellow) {
            J4.setBackground(Color.green);
            Nseat--;
            String seat = Nseat.toString();
            nseat_field.setText(seat);
            selectedSeat = " ";
        } else if (Nseat == 4)
            JOptionPane.showMessageDialog(rootPane, "You can't buy more than 4 Tickets");
        else {
            J4.setBackground(Color.yellow);
            Nseat++;
            String seat = Nseat.toString();
            nseat_field.setText(seat);
            selectedSeat += "J4 ";
        }
    }//GEN-LAST:event_J4ActionPerformed

    private void BookActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BookActionPerformed
        if (nseat_field.getText().isEmpty())
            JOptionPane.showMessageDialog(rootPane, "Select your seat first");
        else {
//            String selectd = (String) jComboBox2.getSelectedItem();
            int ind = bus_name.getSelectedIndex();
//            jLabel11.setText(String.valueOf(ar.get(ind-1).FARE));
            total_price.setText(String.valueOf(Nseat * ar.get(ind - 1).FARE));
            ok = true;
            if (A1.getBackground() == yellow) {
                A1.setBackground(Color.red);
            }
            if (A2.getBackground() == yellow) {
                A2.setBackground(Color.red);
            }
            if (A3.getBackground() == yellow) {
                A3.setBackground(Color.red);
            }
            if (A4.getBackground() == yellow) {
                A4.setBackground(Color.red);
            }
            if (B1.getBackground() == yellow) {
                B1.setBackground(Color.red);
            }
            if (B2.getBackground() == yellow) {
                B2.setBackground(Color.red);
            }
            if (B3.getBackground() == yellow) {
                B3.setBackground(Color.red);
            }
            if (B4.getBackground() == yellow) {
                B4.setBackground(Color.red);
            }
            if (C1.getBackground() == yellow) {
                C1.setBackground(Color.red);
            }
            if (C2.getBackground() == yellow) {
                C2.setBackground(Color.red);
            }
            if (C3.getBackground() == yellow) {
                C3.setBackground(Color.red);
            }
            if (C4.getBackground() == yellow) {
                C4.setBackground(Color.red);
            }
            if (D1.getBackground() == yellow) {
                D1.setBackground(Color.red);
            }
            if (D2.getBackground() == yellow) {
                D2.setBackground(Color.red);
            }
            if (D3.getBackground() == yellow) {
                D3.setBackground(Color.red);
            }
            if (D4.getBackground() == yellow) {
                D4.setBackground(Color.red);
            }
            if (E1.getBackground() == yellow) {
                E1.setBackground(Color.red);
            }
            if (E2.getBackground() == yellow) {
                E2.setBackground(Color.red);
            }
            if (E3.getBackground() == yellow) {
                E3.setBackground(Color.red);
            }
            if (E4.getBackground() == yellow) {
                E4.setBackground(Color.red);
            }
            if (F1.getBackground() == yellow) {
                F1.setBackground(Color.red);
            }
            if (F2.getBackground() == yellow) {
                F2.setBackground(Color.red);
            }
            if (F3.getBackground() == yellow) {
                F3.setBackground(Color.red);
            }
            if (F4.getBackground() == yellow) {
                F4.setBackground(Color.red);
            }
            if (G1.getBackground() == yellow) {
                G1.setBackground(Color.red);
            }
            if (G2.getBackground() == yellow) {
                G2.setBackground(Color.red);
            }
            if (G3.getBackground() == yellow) {
                G3.setBackground(Color.red);
            }
            if (G4.getBackground() == yellow) {
                G4.setBackground(Color.red);
            }
            if (H1.getBackground() == yellow) {
                H1.setBackground(Color.red);
            }
            if (H2.getBackground() == yellow) {
                H2.setBackground(Color.red);
            }
            if (H3.getBackground() == yellow) {
                H3.setBackground(Color.red);
            }
            if (H4.getBackground() == yellow) {
                H4.setBackground(Color.red);
            }
            if (I1.getBackground() == yellow) {
                I1.setBackground(Color.red);
            }
            if (I2.getBackground() == yellow) {
                I2.setBackground(Color.red);
            }
            if (I3.getBackground() == yellow) {
                I3.setBackground(Color.red);
            }
            if (I4.getBackground() == yellow) {
                I4.setBackground(Color.red);
            }
            if (J1.getBackground() == yellow) {
                J1.setBackground(Color.red);
            }
            if (J2.getBackground() == yellow) {
                J2.setBackground(Color.red);
            }
            if (J3.getBackground() == yellow) {
                J3.setBackground(Color.red);
            }
            if (J4.getBackground() == yellow) {
                J4.setBackground(Color.red);
            }
        }
    }//GEN-LAST:event_BookActionPerformed

    private void ResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ResetActionPerformed
        if (ok) {
            JOptionPane.showMessageDialog(rootPane, "Seats were booked,So you can't Reset");
            return;
        }
        Nseat = 0;
        From.setSelectedIndex(0);
        To.setSelectedIndex(0);
        flevel.setText("");
        tlevel.setText("");
        dateee.setText("");
        name_field.setText("");
        contact_field.setText("");
        nseat_field.setText("");
        ticket_price.setText("");
        total_price.setText("");
        selectedSeat = " ";
        //Clearing Table
        //DefaultTableModel dm = (DefaultTableModel) jTable1.getModel();
        //dm.getDataVector().removeAllElements();
        //dm.fireTableDataChanged();

        //Clearing Date
        try {
            jDateChooser1.setCalendar(null);
        } catch (Exception e) {
        }

        //clear seatcolor
        if (A1.getBackground() == yellow) {
            A1.setBackground(Color.green);
        }
        if (A2.getBackground() == yellow) {
            A2.setBackground(Color.green);
        }
        if (A3.getBackground() == yellow) {
            A3.setBackground(Color.green);
        }
        if (A4.getBackground() == yellow) {
            A4.setBackground(Color.green);
        }
        if (B1.getBackground() == yellow) {
            B1.setBackground(Color.green);
        }
        if (B2.getBackground() == yellow) {
            B2.setBackground(Color.green);
        }
        if (B3.getBackground() == yellow) {
            B3.setBackground(Color.green);
        }
        if (B4.getBackground() == yellow) {
            B4.setBackground(Color.green);
        }
        if (C1.getBackground() == yellow) {
            C1.setBackground(Color.green);
        }
        if (C2.getBackground() == yellow) {
            C2.setBackground(Color.green);
        }
        if (C3.getBackground() == yellow) {
            C3.setBackground(Color.green);
        }
        if (C4.getBackground() == yellow) {
            C4.setBackground(Color.green);
        }
        if (D1.getBackground() == yellow) {
            D1.setBackground(Color.green);
        }
        if (D2.getBackground() == yellow) {
            D2.setBackground(Color.green);
        }
        if (D3.getBackground() == yellow) {
            D3.setBackground(Color.green);
        }
        if (D4.getBackground() == yellow) {
            D4.setBackground(Color.green);
        }
        if (E1.getBackground() == yellow) {
            E1.setBackground(Color.green);
        }
        if (E2.getBackground() == yellow) {
            E2.setBackground(Color.green);
        }
        if (E3.getBackground() == yellow) {
            E3.setBackground(Color.green);
        }
        if (E4.getBackground() == yellow) {
            E4.setBackground(Color.green);
        }
        if (F1.getBackground() == yellow) {
            F1.setBackground(Color.green);
        }
        if (F2.getBackground() == yellow) {
            F2.setBackground(Color.green);
        }
        if (F3.getBackground() == yellow) {
            F3.setBackground(Color.green);
        }
        if (F4.getBackground() == yellow) {
            F4.setBackground(Color.green);
        }
        if (G1.getBackground() == yellow) {
            G1.setBackground(Color.green);
        }
        if (G2.getBackground() == yellow) {
            G2.setBackground(Color.green);
        }
        if (G3.getBackground() == yellow) {
            G3.setBackground(Color.green);
        }
        if (G4.getBackground() == yellow) {
            G4.setBackground(Color.green);
        }
        if (H1.getBackground() == yellow) {
            H1.setBackground(Color.green);
        }
        if (H2.getBackground() == yellow) {
            H2.setBackground(Color.green);
        }
        if (H3.getBackground() == yellow) {
            H3.setBackground(Color.green);
        }
        if (H4.getBackground() == yellow) {
            H4.setBackground(Color.green);
        }
        if (I1.getBackground() == yellow) {
            I1.setBackground(Color.green);
        }
        if (I2.getBackground() == yellow) {
            I2.setBackground(Color.green);
        }
        if (I3.getBackground() == yellow) {
            I3.setBackground(Color.green);
        }
        if (I4.getBackground() == yellow) {
            I4.setBackground(Color.green);
        }
        if (J1.getBackground() == yellow) {
            J1.setBackground(Color.green);
        }
        if (J2.getBackground() == yellow) {
            J2.setBackground(Color.green);
        }
        if (J3.getBackground() == yellow) {
            J3.setBackground(Color.green);
        }
        if (J4.getBackground() == yellow)
            J4.setBackground(Color.green);
    }//GEN-LAST:event_ResetActionPerformed

    private void paysubmit_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_paysubmit_buttonActionPerformed
        paysubmit_buttonActionPerformed_work();

    }//GEN-LAST:event_paysubmit_buttonActionPerformed

    private void dateeeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dateeeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dateeeActionPerformed

    private void jButton47ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton47ActionPerformed
        Complain_Box_User cbu = new Complain_Box_User(USERID);
        cbu.setVisible(true);
        dispose();
    }//GEN-LAST:event_jButton47ActionPerformed

    private void SearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SearchActionPerformed
        // TODO add your handling code here:
        if (!ok) {
            String from = (String) From.getSelectedItem();
            String to = (String) To.getSelectedItem();

            if (from == "Select") {
                JOptionPane.showMessageDialog(null, "Invalid 'from' location!", "Error", 2);
            } else if (to == "Select") {
                JOptionPane.showMessageDialog(null, "Invalid 'to' location!", "Error", 2);
            } else if (from == to) {
                JOptionPane.showMessageDialog(null, "Please select valid locations!", "Error", 2);
            } else {
                try {
                    String query = "SELECT * FROM " + from + "_" + to + ";";
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection con = (Connection) DriverManager.getConnection(url, username, pass); //2
                    Statement st = (Statement) con.createStatement();
                    ResultSet rs = st.executeQuery(query);

                    ar.clear();
                    while (rs.next()) {
                        bus_info obj = new bus_info(rs.getString("bus_name"), rs.getInt("ticket_fare"), rs.getString("arrival_time"), rs.getString("departure_time"));
                        ar.add(obj);
                    }
                    showTable();
                    st.close();
                    con.close();

                } catch (ClassNotFoundException | SQLException ex) {
                    JOptionPane.showMessageDialog(null, ex, "Error", 0);
                }
            }
        }
    }//GEN-LAST:event_SearchActionPerformed

    private void bus_nameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bus_nameActionPerformed
        // TODO add your handling code here:
        int ind = bus_name.getSelectedIndex();
        String selectd = (String) bus_name.getSelectedItem();

        if (ind > 0) {
            ticket_price.setText(String.valueOf(ar.get(ind - 1).FARE));
        }

    }//GEN-LAST:event_bus_nameActionPerformed

    private void jButton46ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton46ActionPerformed
        // TODO add your handling code here:
        Mybooking mb = new Mybooking(USERID);
        mb.setVisible(true);
        dispose();
    }//GEN-LAST:event_jButton46ActionPerformed

    private void jDateChooser1PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jDateChooser1PropertyChange
        // TODO add your handling code here:
        String selectdate = ((JTextField) jDateChooser1.getDateEditor().getUiComponent()).getText();
        dateee.setText(selectdate);
    }//GEN-LAST:event_jDateChooser1PropertyChange

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
            java.util.logging.Logger.getLogger(TicketBooking.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TicketBooking.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TicketBooking.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TicketBooking.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
//            new TicketBooking().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton A1;
    private javax.swing.JButton A2;
    private javax.swing.JButton A3;
    private javax.swing.JButton A4;
    private javax.swing.JButton B1;
    private javax.swing.JButton B2;
    private javax.swing.JButton B3;
    private javax.swing.JButton B4;
    private javax.swing.JLabel Background;
    private javax.swing.JButton Book;
    private javax.swing.JButton C1;
    private javax.swing.JButton C2;
    private javax.swing.JButton C3;
    private javax.swing.JButton C4;
    private javax.swing.JButton D1;
    private javax.swing.JButton D2;
    private javax.swing.JButton D3;
    private javax.swing.JButton D4;
    private javax.swing.JButton E1;
    private javax.swing.JButton E2;
    private javax.swing.JButton E3;
    private javax.swing.JButton E4;
    private javax.swing.JButton F1;
    private javax.swing.JButton F2;
    private javax.swing.JButton F3;
    private javax.swing.JButton F4;
    private javax.swing.JComboBox<String> From;
    private javax.swing.JButton G1;
    private javax.swing.JButton G2;
    private javax.swing.JButton G3;
    private javax.swing.JButton G4;
    private javax.swing.JButton H1;
    private javax.swing.JButton H2;
    private javax.swing.JButton H3;
    private javax.swing.JButton H4;
    private javax.swing.JButton I1;
    private javax.swing.JButton I2;
    private javax.swing.JButton I3;
    private javax.swing.JButton I4;
    private javax.swing.JButton J1;
    private javax.swing.JButton J2;
    private javax.swing.JButton J3;
    private javax.swing.JButton J4;
    private javax.swing.JButton Reset;
    private javax.swing.JButton Search;
    private javax.swing.JLabel SeatB;
    private javax.swing.JLabel Steer;
    private javax.swing.JComboBox<String> To;
    private javax.swing.JComboBox<String> bus_name;
    private javax.swing.JTextField contact_field;
    private javax.swing.JTextField dateee;
    private javax.swing.JLabel flevel;
    private javax.swing.JButton jButton41;
    private javax.swing.JButton jButton42;
    private javax.swing.JButton jButton45;
    private javax.swing.JButton jButton46;
    private javax.swing.JButton jButton47;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField name_field;
    private javax.swing.JTextField nseat_field;
    private javax.swing.JButton paysubmit_button;
    private javax.swing.JLabel ticket_price;
    private javax.swing.JLabel tlevel;
    private javax.swing.JLabel total_price;
    // End of variables declaration//GEN-END:variables
}
