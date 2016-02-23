/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package las.views;

import SeverConnector.Connector;
import java.awt.GridLayout;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import las.controller.GramaNiladariDivisionController;
import las.controller.PermitController;
import las.models.GramaNiladariDivision;
import las.models.Permit;

/**
 *
 * @author DinsuG
 */
public class GramaNiladariCertificationForm extends javax.swing.JInternalFrame {

    GramaNiladariDivisionController GramaNiladariDivisionController;
    PermitController PermitController;
    GramaNiladariDivision gnd;

    /**
     * Creates new form NewJInternalFrame
     */
    public GramaNiladariCertificationForm() {
        initComponents();
        try {
            Connector sConnector = Connector.getSConnector();
            GramaNiladariDivisionController = sConnector.getGramaNiladariDivisionController();
            PermitController = sConnector.getPermitController();
        } catch (NotBoundException | MalformedURLException | RemoteException | SQLException | ClassNotFoundException ex) {
            Logger.getLogger(GramaNiladariCertificationForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        no_applicant_label.setVisible(false);
        //setLocationRelativeTo(null);
        setTitle("Certifications to add");
        //////////////
        try {
            this.gnd = GramaNiladariDivisionController.searchGND("406");
            ArrayList<Permit> permitListToCertify = GramaNiladariDivisionController.getPermitsToCertify(gnd.getDivisionNumber());
            if (permitListToCertify.size() > 10) {
                ((GridLayout) buttonPanel.getLayout()).setRows(permitListToCertify.size());
            }
            for (Permit p : permitListToCertify) {
                buttonPanel.add(new JButton(p.getClient().getClientName()));
            }

            buttonPanel.setVisible(true);
        } catch (SQLException | ClassNotFoundException | RemoteException ex) {
            Logger.getLogger(GramaNiladariCertificationForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    public GramaNiladariCertificationForm(java.awt.Frame parent, boolean modal, GramaNiladariDivision gnd) {
        this();
        try {
            this.gnd = gnd;
            
            ArrayList<Permit> permitListToCertify = GramaNiladariDivisionController.getPermitsToCertify(gnd.getDivisionNumber());
            if (permitListToCertify.size() > 10) {
                ((GridLayout) buttonPanel.getLayout()).setRows(permitListToCertify.size());
            }
            for (Permit p : permitListToCertify) {
                buttonPanel.add(new JButton(p.getClient().getClientName()));
            }
            buttonPanel.setVisible(true);
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(GramaNiladariCertificationForm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RemoteException ex) {
            Logger.getLogger(GramaNiladariCertificationForm.class.getName()).log(Level.SEVERE, null, ex);
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        buttonGroup4 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        dateText = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        gramaNiladaryTest = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        gramaNiladaryDivisionTest = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        divisionNumberTest = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        permitNumberTest = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        no_applicant_label = new javax.swing.JLabel();
        buttonPanel = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        q1Yes = new javax.swing.JRadioButton();
        q1No = new javax.swing.JRadioButton();
        q2No = new javax.swing.JRadioButton();
        q2Yes = new javax.swing.JRadioButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        q3Yes = new javax.swing.JRadioButton();
        q3No = new javax.swing.JRadioButton();
        jLabel9 = new javax.swing.JLabel();
        q4Yes = new javax.swing.JRadioButton();
        q4No = new javax.swing.JRadioButton();
        saveButton = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel1.setText("Today:");

        jLabel2.setText("Grama Niladari:");

        jLabel3.setText("My Grama Niladari Division:");

        jLabel4.setText("Division Number:");

        jLabel10.setText("Permit Number");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(dateText, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(gramaNiladaryTest, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(gramaNiladaryDivisionTest, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(permitNumberTest, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(divisionNumberTest, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(115, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(dateText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(gramaNiladaryTest, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(gramaNiladaryDivisionTest, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(permitNumberTest, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(divisionNumberTest, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel5.setText("Permit holders to certify:");

        no_applicant_label.setText("No applicants");

        buttonPanel.setLayout(new java.awt.GridLayout(10, 1));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(buttonPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(40, 40, 40)
                .addComponent(no_applicant_label, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(no_applicant_label))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel6.setText(" Has The permit Holder Completed One Year: ");

        buttonGroup1.add(q1Yes);
        q1Yes.setText("Yes");
        q1Yes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                q1YesActionPerformed(evt);
            }
        });

        buttonGroup1.add(q1No);
        q1No.setText("No");

        buttonGroup2.add(q2No);
        q2No.setText("No");

        buttonGroup2.add(q2Yes);
        q2Yes.setText("Yes");

        jLabel7.setText(" Has Any Complaints About The Owner:");

        jLabel8.setText("Has Any Issues Regarding The Ownership Of The Land:");

        buttonGroup3.add(q3Yes);
        q3Yes.setText("Yes");

        buttonGroup3.add(q3No);
        q3No.setText("No");

        jLabel9.setText(" Has Any Complaints About Illegal Cultivations:");

        buttonGroup4.add(q4Yes);
        q4Yes.setText("Yes");

        buttonGroup4.add(q4No);
        q4No.setText("No");

        saveButton.setText("Save");
        saveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButtonActionPerformed(evt);
            }
        });

        jButton2.setText("Cancel");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(18, 18, 18)
                        .addComponent(q1Yes)
                        .addGap(18, 18, 18)
                        .addComponent(q1No)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(q3Yes)
                                .addGap(18, 18, 18)
                                .addComponent(q3No))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                                        .addComponent(jLabel7)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                                        .addComponent(q2Yes)))
                                .addGap(18, 18, 18)
                                .addComponent(q2No)))
                        .addGap(31, 31, 31))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(saveButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton2))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addGap(18, 18, 18)
                                .addComponent(q4Yes)
                                .addGap(18, 18, 18)
                                .addComponent(q4No)))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(q1Yes)
                    .addComponent(q1No))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel7)
                        .addComponent(q2Yes))
                    .addComponent(q2No))
                .addGap(18, 18, 18)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(q3No)
                    .addComponent(q3Yes))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(q4No)
                    .addComponent(q4Yes))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 66, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(saveButton)
                    .addComponent(jButton2))
                .addGap(21, 21, 21))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void q1YesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_q1YesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_q1YesActionPerformed

    private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButtonActionPerformed
        String permitNumber = permitNumberTest.getText();
        try {
            Permit searchPermit = PermitController.searchPermit(permitNumber);
            if (q1Yes.isSelected() && q2Yes.isSelected() && q3Yes.isSelected() && q4Yes.isSelected()) {
                searchPermit.setCertified(1);
            } else {
                searchPermit.setCertified(0);
            }
            boolean addGramaNiladaryCertificateToPermit = PermitController.addGramaNiladaryCertificateToPermit(searchPermit);
            if (addGramaNiladaryCertificateToPermit) {
                JOptionPane.showMessageDialog(this, "Add Grama Niladary Certification Well");
            } else {
                JOptionPane.showMessageDialog(this, "Grama Niladary Certification does not added");
            

}

        } catch (RemoteException | SQLException | ClassNotFoundException ex) {
            Logger.getLogger(GramaNiladariCertificationForm.class  

.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_saveButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.ButtonGroup buttonGroup4;
    private javax.swing.JPanel buttonPanel;
    private javax.swing.JTextField dateText;
    private javax.swing.JTextField divisionNumberTest;
    private javax.swing.JTextField gramaNiladaryDivisionTest;
    private javax.swing.JTextField gramaNiladaryTest;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JLabel no_applicant_label;
    private javax.swing.JTextField permitNumberTest;
    private javax.swing.JRadioButton q1No;
    private javax.swing.JRadioButton q1Yes;
    private javax.swing.JRadioButton q2No;
    private javax.swing.JRadioButton q2Yes;
    private javax.swing.JRadioButton q3No;
    private javax.swing.JRadioButton q3Yes;
    private javax.swing.JRadioButton q4No;
    private javax.swing.JRadioButton q4Yes;
    private javax.swing.JButton saveButton;
    // End of variables declaration//GEN-END:variables
}
