/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package las.views.searchset;

import SeverConnector.Connector;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import las.common_classes.PatternChecker;
import las.controller.ClientController;
import las.controller.GramaNiladariDivisionController;
import las.controller.GrantController;
import las.controller.LandController;
import las.controller.LotController;
import las.controller.NominatedSuccessorController;
import las.controller.PermitController;
import las.models.Client;
import las.models.Grant;
import las.models.Permit;
import las.views.ApplicantForm;
import las.views.FrontPage;

/**
 *
 * @author Gimhani
 */
public class SearchClientForm extends SearchForm {
    PopUpTable popUp;
    
    LandController LandController;
    LotController LotController;
    GrantController GrantController;
    ClientController ClientController;
    PermitController PermitController;
    GramaNiladariDivisionController GramaNiladariDivisionController;
    NominatedSuccessorController NominatedSuccessorController;        
    
    /**
     * Creates new form SearchClientForm
     */
    public SearchClientForm() {
        
        initComponents();
        
            try {
            Connector sConnector = Connector.getSConnector();
            ClientController=sConnector.getClientController();
            GrantController=sConnector.getGrantController();
            PermitController=sConnector.getPermitController();
            GramaNiladariDivisionController=sConnector.getGramaNiladariDivisionController();
            NominatedSuccessorController=sConnector.getnomiNominatedSuccessorController();
            LandController=sConnector.getLandController();
            
        } catch (RemoteException | SQLException | NotBoundException | MalformedURLException|ClassNotFoundException ex) {
            Logger.getLogger(ApplicantForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        typeText.requestFocus();
        model = (DefaultTableModel) jTable1.getModel();
    }
    
    public SearchClientForm(String search, String bywhat) {
        this();
        this.bywhat = bywhat;
        this.search = search;
        popUp=new PopUpTable(jTable1);
        
    }
    public void requestFocusForm(){
        typeText.requestFocus();
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        typeText = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        jLabel1.setText("Type text:");

        typeText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                typeTextActionPerformed(evt);
            }
        });
        typeText.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                typeTextKeyReleased(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "NIC", "Name", "Birthday", "Telephone", "Address", "Permit ", "Grant"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.getTableHeader().setReorderingAllowed(false);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setResizable(false);
            jTable1.getColumnModel().getColumn(1).setResizable(false);
            jTable1.getColumnModel().getColumn(2).setResizable(false);
            jTable1.getColumnModel().getColumn(3).setResizable(false);
            jTable1.getColumnModel().getColumn(4).setResizable(false);
            jTable1.getColumnModel().getColumn(5).setResizable(false);
            jTable1.getColumnModel().getColumn(6).setResizable(false);
        }

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 791, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(32, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(typeText, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(typeText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void typeTextKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_typeTextKeyReleased
        try {
            
            model.getDataVector().removeAllElements();
            revalidate();
            
            String text = typeText.getText();
            if (search == "Applicant" && bywhat == "By name") {
                typeText.setText(PatternChecker.checkstring(text));
                ArrayList<Client> clientlist = ClientController.getSimilarNames(text);
                if (!clientlist.isEmpty()) {
                    
                    for (Client client : clientlist) {
                        Permit permit = PermitController.searchPermitByClient(client.getNIC());
                        String permitNumber;
                        if (permit != null) {
                            permitNumber = permit.getPermitNumber();
                        } else {
                            permitNumber = "Not given";
                        }
                        
                        Grant grant = GrantController.searchGrantByClient(client.getNIC());
                        String grantNumber;
                        if (grant != null) {
                            grantNumber = grant.getGrantNumber();
                        } else {
                            grantNumber = "Not given";
                        }
                        Object[] rowdata = {client.getNIC(), client.getClientName(), client.getBirthday(), client.getTelephone(), client.getAddress(), permitNumber, grantNumber};
                        model.addRow(rowdata);
                    }
                } else {
                    jTable1.removeAll();
                }
            }
            if (search == "Applicant" && bywhat == "By NIC") {
                typeText.setText(PatternChecker.checkNIC(text));
                ArrayList<Client> clientlist = ClientController.getSimmilarNICs(text);
                if (!clientlist.isEmpty()) {
                    
                    for (Client client : clientlist) {
                        Permit permit = PermitController.searchPermitByClient(client.getNIC());
                        String permitNumber;
                        if (permit != null) {
                            permitNumber = permit.getPermitNumber();
                        } else {
                            permitNumber = "Not given";
                        }
                        
                        Grant grant = GrantController.searchGrantByClient(client.getNIC());
                        String grantNumber;
                        if (grant != null) {
                            grantNumber = grant.getGrantNumber();
                        } else {
                            grantNumber = "Not given";
                        }
                        Object[] rowdata = {client.getNIC(), client.getClientName(), client.getBirthday(), client.getTelephone(), client.getAddress(), permitNumber, grantNumber};
                        model.addRow(rowdata);
                    }
                } else {
                    jTable1.removeAll();
                }
            }
            
        } catch (ClassNotFoundException | SQLException | RemoteException ex) {
            Logger.getLogger(SearchClientForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_typeTextKeyReleased

    private void typeTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_typeTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_typeTextActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
         if(SwingUtilities.isRightMouseButton(evt)){
             popUp.show(evt.getComponent(),evt.getX(),evt.getY());
         }
    }//GEN-LAST:event_jTable1MouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField typeText;
    // End of variables declaration//GEN-END:variables
}

class PopUpTable extends JPopupMenu {
    
    public PopUpTable(final JTable table) {
        JMenuItem viewItem = new JMenuItem("View Client");
        JMenuItem editItem = new JMenuItem("Edit Client");
        viewItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selected = table.getSelectedRow();
                    String nic=String.valueOf(((DefaultTableModel)table.getModel()).getValueAt(selected, 0));
                 FrontPage fp=FrontPage.getInstance();
                ApplicantForm applicantForm = new ApplicantForm();
                fp.SetDesktopPaneForClient(applicantForm,1);
                 try {
                    applicantForm.searchClient(nic);
                } catch (ClassNotFoundException | SQLException | RemoteException ex) {
                    Logger.getLogger(PopUpTable.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        editItem.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                int selected = table.getSelectedRow();
                String nic=String.valueOf(((DefaultTableModel)table.getModel()).getValueAt(selected, 0));
                FrontPage fp=FrontPage.getInstance();
                ApplicantForm applicantForm = new ApplicantForm();
                fp.SetDesktopPaneForClient(applicantForm,2);
                applicantForm.editClient(nic);
                
                            }
        });
        
        
        
        
        add(viewItem);
        add(editItem);
    }
    
}
