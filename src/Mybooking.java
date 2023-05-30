
import java.sql.*;
import java.awt.Color;
import java.awt.Font;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;

public final class Mybooking extends javax.swing.JFrame {

    String url = "jdbc:mysql://localhost:3306/busmania";
    String username = "root";
    String pass = "123456";
    String USERID;
    
    public Mybooking() {
        setResizable(false);
        initComponents();
        Set_Frame();
        Set_Table_Prop();
        SearchBooking();
    }
    public Mybooking(String userid) {
        setResizable(false);
        initComponents();
        USERID=userid;
        Set_Frame();
        Set_Table_Prop();
        SearchBooking();
    }
    public void Set_Frame() {
        getContentPane().setBackground(Color.black);
    }
    public void Set_Table_Prop() {
        JTableHeader tableHeader = jTable1.getTableHeader();
        tableHeader.setForeground(Color.black);

        Font headerFont = new Font("Verdana", Font.BOLD, 14);
        tableHeader.setFont(headerFont);
        TableCellRenderer rendererFromHeader = jTable1.getTableHeader().getDefaultRenderer();
        JLabel headerLabel = (JLabel) rendererFromHeader;
        headerLabel.setHorizontalAlignment(JLabel.CENTER);
        jTable1.setRowHeight(35);
        jTable1.getColumnModel().getColumn(1).setPreferredWidth(100);
       // jTable1.getColumnModel().getColumn(0).setPreferredWidth(120);

    }
    class my_booking {

        public String bus, seatBooked, date , arrival,departure,from,destination;

        public my_booking(String bus, String seatBooked, String date,String from,String destination,String departure, String arrival) {
            this.bus = bus;
            this.seatBooked=seatBooked;
            this.date = date;
            this.from = from;
            this.destination = destination;
            this.departure = departure;
            this.arrival = arrival;
        }
    }
    ArrayList<my_booking> ar = new ArrayList<>();
    public void showTable() {
        DefaultTableModel mod = (DefaultTableModel) (jTable1.getModel());
        Object[] col = new Object[8];
        mod.setRowCount(0);
        System.out.println(ar.size());
        for (int i = 0; i < ar.size(); i++) {
            col[0] = ar.get(i).bus;
            col[1] = ar.get(i).seatBooked;
            col[2] = ar.get(i).date;
            col[3] = ar.get(i).from;
            col[4] = ar.get(i).destination;
            col[5] = ar.get(i).departure;
            col[6] = ar.get(i).arrival;
            mod.addRow(col);
        }
    }
    public void SearchBooking() {
        try {
            String query = "SELECT bus,seatBooked,journeyDate, startPlace,destination,departure,arrival FROM My_Booking WHERE user='"+USERID+"';";
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = (Connection) DriverManager.getConnection(url, username, pass); //2
            Statement st = (Statement) con.createStatement();
            ResultSet rs = st.executeQuery(query);

            ar.clear();
            while (rs.next()) {
                my_booking obj = new my_booking(rs.getString("bus"),rs.getString("seatBooked"), rs.getString("journeyDate"), rs.getString("startPlace"),rs.getString("destination"),rs.getString("departure"),rs.getString("arrival"));
                ar.add(obj);
            }
            showTable();
            st.close();
            con.close();

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(TicketBooking.class.getName()).log(Level.SEVERE, null, ex);
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

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTable1.setBackground(new java.awt.Color(0, 204, 204));
        jTable1.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Bus", "Seat Booked", "Date", "From", "Destination", "Departure", "Arrival"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setGridColor(new java.awt.Color(255, 0, 0));
        jTable1.setShowHorizontalLines(true);
        jTable1.setShowVerticalLines(true);
        jScrollPane1.setViewportView(jTable1);

        jButton1.setBackground(new java.awt.Color(0, 255, 255));
        jButton1.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        jButton1.setText("Previous Page");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(layout.createSequentialGroup()
                .addGap(325, 325, 325)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(340, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 399, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(8, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        TicketBooking tk = new TicketBooking(USERID);
        tk.setVisible(true);
        dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(Mybooking.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Mybooking.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Mybooking.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Mybooking.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Mybooking().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
