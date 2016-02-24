/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package las.views;

import SeverConnector.Connector;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.JTextComponent;
import las.common_classes.GUIitemsValidator;
import las.controller.ClientController;
import las.controller.GramaNiladariDivisionController;
import las.controller.GrantController;
import las.controller.LotController;
import las.controller.PermitController;
import las.models.Client;
import las.models.GramaNiladariDivision;
import las.models.Grant;
import las.models.Land;
import las.models.Lot;
import las.models.NominatedSuccessor;
import las.models.Permit;

/**
 *
 * @author Gimhani Uthpala
 */
public class GrantForm extends javax.swing.JInternalFrame {
    private Permit choosenPermit;
    private Grant choosenGrant;
    LotController LotController;
    GrantController GrantController;
    ClientController ClientController;
    PermitController PermitController;
    GramaNiladariDivisionController GramaNiladariDivisionController;
    private FrontPage frontpage;
    private boolean isReminderPermit = false;
    /**
     * Creates new form GrantForm1
     */
    public GrantForm() {
        
        initComponents();
    }

    public GrantForm(FrontPage frontpage) {
        initComponents();
        this.frontpage=frontpage;
        
        try {
            Connector sConnector = Connector.getSConnector();
            ClientController=sConnector.getClientController();
            GrantController=sConnector.getGrantController();
            PermitController=sConnector.getPermitController();
            GramaNiladariDivisionController=sConnector.getGramaNiladariDivisionController();
            LotController=sConnector.getlotController();
            
        } catch (RemoteException | SQLException | NotBoundException | MalformedURLException|ClassNotFoundException ex) {
            Logger.getLogger(ApplicantForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        add_grant_ownernameText.setEditable(false);
        add_grant_owner_nic_text.setEditable(false);
        add_grantowner_telephoneText.setEditable(false);
        add_grantowner_addressText.setEditable(false);
        add_grantowner_DOB_test.setEditable(false);
        add_grant_owner_annualIncome.setEditable(false);
        add_grant_owner_no_of_children_test.setEditable(false);
        addgrant_S_name_test.setEditable(false);
        addgrant_S_nic_test.setEditable(false);
        addgrant_S_address_test.setEditable(false);
        addgrant_permit_issueDate.setEditable(false);
        add_grant_division_no_text.setEditable(false);
        add_grant_division_name_text.setEditable(false);
        add_grant_plan_no_text.setEditable(false);
        add_grant_landName_text.setEditable(false);
        add_grant_lotno_text.setEditable(false);
        add_grant_acres_text.setEditable(false);
       add_grant_perches_text.setEditable(false);
        add_grant_roods_text.setEditable(false);
        add_grant_button.setEnabled(false);
        //add grant
        
        this.Add_Grant_Grant_No.setEditable(true);
        this.add_grant_permit_no_combo.setEditable(true);
        JTextComponent editorSearchPemitPNCombo = (JTextComponent) add_grant_permit_no_combo.getEditor().getEditorComponent();
        editorSearchPemitPNCombo.addKeyListener(new KeyAdapter() {

            @Override
            public void keyReleased(KeyEvent e) {
                String item = (String) add_grant_permit_no_combo.getEditor().getItem();
                ArrayList<Object> list = new ArrayList();
                try {
                    ArrayList<Permit> simmilarPlanNumbers = PermitController.getSimilarPermitNumbers(item);
                    for (int i = 0; i < simmilarPlanNumbers.size(); i++) {
                        list.add(simmilarPlanNumbers.get(i).getPermitNumber());
                    }
                    GUIitemsValidator.addItemToCombo(list, add_grant_permit_no_combo);

                } catch (ClassNotFoundException | SQLException | RemoteException ex) {
                    Logger.getLogger(LandForm.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

        });
        
        //search grant
        
        this.search_grant_grantnoCombo.setEditable(true);
        JTextComponent editorSearchGrantPNCombo = (JTextComponent) this.search_grant_grantnoCombo.getEditor().getEditorComponent();
        editorSearchGrantPNCombo.addKeyListener(new KeyAdapter() {

            @Override
            public void keyReleased(KeyEvent e) {
                String item = (String) search_grant_grantnoCombo.getEditor().getItem();
                ArrayList<Object> list = new ArrayList();
                try {
                    ArrayList<Grant> simmilarPlanNumbers = GrantController.getSimilarGrantNumbers(item);
                    for (int i = 0; i < simmilarPlanNumbers.size(); i++) {
                        list.add(simmilarPlanNumbers.get(i).getGrantNumber());
                    }
                    GUIitemsValidator.addItemToCombo(list,search_grant_grantnoCombo );

                } catch (ClassNotFoundException | SQLException|RemoteException ex) {
                    Logger.getLogger(LandForm.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

        });
                
        
        
        
        
        
    }
    public GrantForm(FrontPage frontpage,Permit permitTobeGrant){
        this(frontpage);
        this.choosenPermit=permitTobeGrant;
        this.isReminderPermit=true;
        this.UpdateOwner();
        
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel4 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        grantnolabel = new javax.swing.JLabel();
        issuedatelabel = new javax.swing.JLabel();
        Add_Grant_Grant_No = new javax.swing.JTextField();
        addgrant_grant_issue_dateChooser = new org.freixas.jcalendar.JCalendarCombo();
        jPanel7 = new javax.swing.JPanel();
        permitnolabel = new javax.swing.JLabel();
        add_grant_issuedate_label = new javax.swing.JLabel();
        add_grant_permit_no_combo = new javax.swing.JComboBox();
        addgrant_permit_issueDate = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        add_grant_landName_text = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        add_grant_division_name_text = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel100 = new javax.swing.JLabel();
        add_grant_acres_text = new javax.swing.JTextField();
        add_grant_perches_text = new javax.swing.JTextField();
        add_grant_roods_text = new javax.swing.JTextField();
        jLabel103 = new javax.swing.JLabel();
        jLabel102 = new javax.swing.JLabel();
        jLabel101 = new javax.swing.JLabel();
        jLabel99 = new javax.swing.JLabel();
        add_grant_division_no_text = new javax.swing.JTextField();
        add_grant_plan_no_text = new javax.swing.JTextField();
        add_grant_lotno_text = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        add_grant_ownernameText = new javax.swing.JTextField();
        add_grantowner_telephoneText = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        add_grantowner_addressText = new javax.swing.JTextArea();
        add_grant_owner_no_of_children_test = new javax.swing.JTextField();
        add_grant_owner_marriedStatusRButton = new javax.swing.JRadioButton();
        add_grant_owner_singleStatusRButton = new javax.swing.JRadioButton();
        jLabel35 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        add_grant_owner_annualIncome = new javax.swing.JTextField();
        add_grantowner_DOB_test = new javax.swing.JTextField();
        jLabel41 = new javax.swing.JLabel();
        add_grant_changeOwner = new javax.swing.JButton();
        add_grant_owner_nic_text = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel26 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        addgrant_S_name_test = new javax.swing.JTextField();
        addgrant_S_nic_test = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        addgrant_S_address_test = new javax.swing.JTextArea();
        nominateSuccessorButton = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        add_grant_button = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane14 = new javax.swing.JScrollPane();
        jPanel13 = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        viewAll_table = new javax.swing.JTable();
        jPanel16 = new javax.swing.JPanel();
        viewAll_load_buttun = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jPanel5 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        search_grantnolabel = new javax.swing.JLabel();
        search_grant_issuedatelabel = new javax.swing.JLabel();
        search_grant_issuedateText = new javax.swing.JTextField();
        search_grant_grantnoCombo = new javax.swing.JComboBox();
        jPanel12 = new javax.swing.JPanel();
        search_grant_ownernameText = new javax.swing.JTextField();
        search_grantowner_telephoneText = new javax.swing.JTextField();
        jScrollPane5 = new javax.swing.JScrollPane();
        search_grantowner_addressText = new javax.swing.JTextArea();
        search_grant_owner_no_of_children_text = new javax.swing.JTextField();
        search_grant_owner_marriedStatusRButton = new javax.swing.JRadioButton();
        search_grant_owner_singleStatusRButton = new javax.swing.JRadioButton();
        jLabel45 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        search_grant_owner_annualIncome = new javax.swing.JTextField();
        search_grantowner_DOB_text = new javax.swing.JTextField();
        jLabel51 = new javax.swing.JLabel();
        search_grant_owner_nic_text = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        jLabel27 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        searchgrant_S_name_text = new javax.swing.JTextField();
        searchgrant_S_nic_text = new javax.swing.JTextField();
        jScrollPane6 = new javax.swing.JScrollPane();
        searchgrant_S_address_text = new javax.swing.JTextArea();
        jLabel8 = new javax.swing.JLabel();
        jPanel15 = new javax.swing.JPanel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        search_grant_landName_text = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        search_grant_division_name_text = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel104 = new javax.swing.JLabel();
        search_grant_acres_text = new javax.swing.JTextField();
        search_grant_perches_text = new javax.swing.JTextField();
        search_grant_roods_text = new javax.swing.JTextField();
        jLabel105 = new javax.swing.JLabel();
        jLabel107 = new javax.swing.JLabel();
        jLabel108 = new javax.swing.JLabel();
        search_grant_division_no_text = new javax.swing.JTextField();
        search_grant_plan_no_text = new javax.swing.JTextField();
        search_grant_lotno_text = new javax.swing.JTextField();
        jLabel106 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();

        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel6.setPreferredSize(new java.awt.Dimension(581, 581));

        grantnolabel.setText("Grant No:");

        issuedatelabel.setText("Issue Date:");

        Add_Grant_Grant_No.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                Add_Grant_Grant_NoKeyReleased(evt);
            }
        });

        addgrant_grant_issue_dateChooser.setEditable(true);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(issuedatelabel, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(grantnolabel, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Add_Grant_Grant_No, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addgrant_grant_issue_dateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(grantnolabel, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Add_Grant_Grant_No, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(issuedatelabel, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(addgrant_grant_issue_dateChooser, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel7.setPreferredSize(new java.awt.Dimension(581, 581));

        permitnolabel.setText("Permit No:");

        add_grant_issuedate_label.setText("Issue Date:");

        add_grant_permit_no_combo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                add_grant_permit_no_comboItemStateChanged(evt);
            }
        });
        add_grant_permit_no_combo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                add_grant_permit_no_comboActionPerformed(evt);
            }
        });

        addgrant_permit_issueDate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addgrant_permit_issueDateActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("Permit Information");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(permitnolabel, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                        .addComponent(add_grant_issuedate_label, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)))
                .addGap(29, 29, 29)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(add_grant_permit_no_combo, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addgrant_permit_issueDate, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addGap(379, 379, 379))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addGap(8, 8, 8)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(add_grant_permit_no_combo, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(permitnolabel, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(add_grant_issuedate_label, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addgrant_permit_issueDate, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        jPanel11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel22.setText("Plan Number:");

        jLabel23.setText("Land Name:");

        jLabel24.setText("Division Number:");

        jLabel25.setText("Division Name:");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("Included Division");

        jLabel100.setText("Expected Extent:");

        add_grant_perches_text.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                add_grant_perches_textActionPerformed(evt);
            }
        });

        jLabel103.setText("Roods");

        jLabel102.setText("Perches");

        jLabel101.setText("Acre / Hectare");

        jLabel99.setText("Lot No:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setText("Included Land");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setText("Lot Details");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel99))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(add_grant_division_no_text, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(add_grant_division_name_text, javax.swing.GroupLayout.PREFERRED_SIZE, 357, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(add_grant_plan_no_text, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(add_grant_landName_text, javax.swing.GroupLayout.PREFERRED_SIZE, 474, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(add_grant_lotno_text, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(jLabel100)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(add_grant_roods_text, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(add_grant_acres_text, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addComponent(add_grant_perches_text, javax.swing.GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel102)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel103)
                            .addComponent(jLabel101))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(383, 383, 383))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(391, 391, 391))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(403, 403, 403))))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(add_grant_division_no_text, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(add_grant_division_name_text, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4)
                .addGap(13, 13, 13)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(add_grant_plan_no_text, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(add_grant_landName_text, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(add_grant_lotno_text, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel99))
                .addGap(18, 18, 18)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel100)
                    .addComponent(add_grant_acres_text, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel101))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(add_grant_roods_text, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel103))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(add_grant_perches_text, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel102))
                .addGap(34, 34, 34))
        );

        jPanel9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        add_grant_ownernameText.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                add_grant_ownernameTextKeyReleased(evt);
            }
        });

        add_grantowner_addressText.setColumns(20);
        add_grantowner_addressText.setRows(5);
        jScrollPane3.setViewportView(add_grantowner_addressText);

        add_grant_owner_no_of_children_test.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                add_grant_owner_no_of_children_testActionPerformed(evt);
            }
        });

        add_grant_owner_marriedStatusRButton.setText("Married");
        add_grant_owner_marriedStatusRButton.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                add_grant_owner_marriedStatusRButtonStateChanged(evt);
            }
        });

        add_grant_owner_singleStatusRButton.setText("Single");
        add_grant_owner_singleStatusRButton.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                add_grant_owner_singleStatusRButtonStateChanged(evt);
            }
        });
        add_grant_owner_singleStatusRButton.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                add_grant_owner_singleStatusRButtonKeyReleased(evt);
            }
        });

        jLabel35.setText("NIC :");

        jLabel20.setText("Name:");

        jLabel36.setText("Phone Number:");

        jLabel37.setText("Address:");

        jLabel38.setText("Birthday:");

        jLabel39.setText("Status:");

        jLabel40.setText("Annual Income:");

        jLabel41.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel41.setText("No. of married children:");

        add_grant_changeOwner.setText("Change Ownership");
        add_grant_changeOwner.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                add_grant_changeOwnerActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel6.setText("Change Ownership ");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(332, 332, 332))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                                .addComponent(add_grant_changeOwner)
                                .addGap(331, 331, 331))))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jLabel20, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE)
                                        .addComponent(jLabel35, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel39)
                                    .addComponent(jLabel40))
                                .addGap(39, 39, 39)
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane3)
                                    .addGroup(jPanel9Layout.createSequentialGroup()
                                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(add_grant_owner_annualIncome, javax.swing.GroupLayout.PREFERRED_SIZE, 321, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(add_grantowner_DOB_test, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(add_grant_owner_nic_text, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(add_grant_ownernameText, javax.swing.GroupLayout.PREFERRED_SIZE, 626, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(add_grantowner_telephoneText, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(jPanel9Layout.createSequentialGroup()
                                                .addComponent(add_grant_owner_marriedStatusRButton)
                                                .addGap(18, 18, 18)
                                                .addComponent(add_grant_owner_singleStatusRButton)))
                                        .addGap(0, 0, Short.MAX_VALUE))))
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(add_grant_owner_no_of_children_test, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addGap(11, 11, 11)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(add_grant_owner_nic_text, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(add_grant_ownernameText, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel36)
                    .addComponent(add_grantowner_telephoneText, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel38)
                    .addComponent(add_grantowner_DOB_test, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel39)
                    .addComponent(add_grant_owner_marriedStatusRButton)
                    .addComponent(add_grant_owner_singleStatusRButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(add_grant_owner_annualIncome, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(add_grant_owner_no_of_children_test, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(add_grant_changeOwner)
                .addContainerGap())
        );

        jPanel8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel26.setText("Name:");

        jLabel42.setText("NIC:");

        jLabel43.setText("Address:");

        addgrant_S_nic_test.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addgrant_S_nic_testActionPerformed(evt);
            }
        });

        addgrant_S_address_test.setColumns(20);
        addgrant_S_address_test.setRows(5);
        jScrollPane2.setViewportView(addgrant_S_address_test);

        nominateSuccessorButton.setText("Nominate New Successor");
        nominateSuccessorButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nominateSuccessorButtonActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setText("Successor Details");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(355, 355, 355))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                                .addComponent(nominateSuccessorButton)
                                .addGap(310, 310, 310))))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel26)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(addgrant_S_name_test, javax.swing.GroupLayout.PREFERRED_SIZE, 825, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(19, 19, 19))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel42)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(addgrant_S_nic_test, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(620, 620, 620))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel43)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 84, Short.MAX_VALUE)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 761, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(83, 83, 83))))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addGap(20, 20, 20)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addgrant_S_name_test, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel42)
                    .addComponent(addgrant_S_nic_test, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel43)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(nominateSuccessorButton)
                        .addGap(18, 18, 18))))
        );

        add_grant_button.setText("Add Grant");
        add_grant_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                add_grant_buttonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, 983, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, 983, Short.MAX_VALUE)
                    .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(add_grant_button, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(326, 326, 326))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(add_grant_button, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jScrollPane1.setViewportView(jPanel4);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 868, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1454, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Add Grant", jPanel1);

        viewAll_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "GrantNumber", "IssueDate", "Clientname", "ClientNic", "DivisionalNumber", "PlanNumber", "LandName", "LotNumber", "NominateSuccesor", "OwnarshipPosition"
            }
        ));
        jScrollPane7.setViewportView(viewAll_table);

        viewAll_load_buttun.setText("Load");
        viewAll_load_buttun.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewAll_load_buttunActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(viewAll_load_buttun, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(viewAll_load_buttun)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 983, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 480, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(889, Short.MAX_VALUE))
        );

        jScrollPane14.setViewportView(jPanel13);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane14, javax.swing.GroupLayout.DEFAULT_SIZE, 868, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane14)
                .addContainerGap())
        );

        jTabbedPane1.addTab("View all grants", jPanel2);

        jPanel10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel10.setPreferredSize(new java.awt.Dimension(581, 581));

        search_grantnolabel.setText("Grant No:");

        search_grant_issuedatelabel.setText("Issue Date:");

        search_grant_issuedateText.setEditable(false);

        search_grant_grantnoCombo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                search_grant_grantnoComboItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(search_grantnolabel, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                        .addComponent(search_grant_issuedatelabel, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)))
                .addGap(29, 29, 29)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(search_grant_issuedateText, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(search_grant_grantnoCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(search_grantnolabel, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(search_grant_grantnoCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(search_grant_issuedatelabel, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(search_grant_issuedateText, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        search_grant_ownernameText.setEditable(false);
        search_grant_ownernameText.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                search_grant_ownernameTextKeyReleased(evt);
            }
        });

        search_grantowner_telephoneText.setEditable(false);

        search_grantowner_addressText.setEditable(false);
        search_grantowner_addressText.setColumns(20);
        search_grantowner_addressText.setRows(5);
        jScrollPane5.setViewportView(search_grantowner_addressText);

        search_grant_owner_no_of_children_text.setEditable(false);
        search_grant_owner_no_of_children_text.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                search_grant_owner_no_of_children_textActionPerformed(evt);
            }
        });

        search_grant_owner_marriedStatusRButton.setText("Married");

        search_grant_owner_singleStatusRButton.setText("Single");

        jLabel45.setText("NIC :");

        jLabel21.setText("Name:");

        jLabel46.setText("Phone Number:");

        jLabel47.setText("Address:");

        jLabel48.setText("Birthday:");

        jLabel49.setText("Status:");

        jLabel50.setText("Annual Income:");

        search_grant_owner_annualIncome.setEditable(false);

        search_grantowner_DOB_text.setEditable(false);

        jLabel51.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel51.setText("No. of married children:");

        search_grant_owner_nic_text.setEditable(false);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setText("Owner Details");

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel51)
                    .addComponent(jLabel50)
                    .addComponent(jLabel49)
                    .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel48, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(search_grant_ownernameText)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(search_grant_owner_nic_text, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(search_grantowner_telephoneText, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel12Layout.createSequentialGroup()
                                .addComponent(search_grant_owner_marriedStatusRButton)
                                .addGap(18, 18, 18)
                                .addComponent(search_grant_owner_singleStatusRButton))
                            .addComponent(search_grant_owner_annualIncome, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(search_grant_owner_no_of_children_text, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(search_grantowner_DOB_text, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 691, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(331, 331, 331)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(20, 20, 20)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(search_grant_owner_nic_text, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE))
                .addGap(12, 12, 12)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel21)
                    .addComponent(search_grant_ownernameText, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(search_grantowner_telephoneText, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel46))
                .addGap(18, 18, 18)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel47)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel48)
                    .addComponent(search_grantowner_DOB_text, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel49)
                    .addComponent(search_grant_owner_marriedStatusRButton)
                    .addComponent(search_grant_owner_singleStatusRButton))
                .addGap(18, 18, 18)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel50, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(search_grant_owner_annualIncome, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel51, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(search_grant_owner_no_of_children_text, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel14.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel27.setText("Name:");

        jLabel52.setText("NIC:");

        jLabel53.setText("Address:");

        searchgrant_S_name_text.setEditable(false);

        searchgrant_S_nic_text.setEditable(false);

        searchgrant_S_address_text.setEditable(false);
        searchgrant_S_address_text.setColumns(20);
        searchgrant_S_address_text.setRows(5);
        jScrollPane6.setViewportView(searchgrant_S_address_text);

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel8.setText("Successor Details");

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel52)
                            .addComponent(jLabel53)
                            .addComponent(jLabel27))
                        .addGap(79, 79, 79)
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(searchgrant_S_nic_text, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(searchgrant_S_name_text, javax.swing.GroupLayout.PREFERRED_SIZE, 629, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 695, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGap(334, 334, 334)
                        .addComponent(jLabel8)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchgrant_S_name_text, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addComponent(jScrollPane6))
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel52)
                            .addComponent(searchgrant_S_nic_text, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel53)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(11, 11, 11))
        );

        jPanel15.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel28.setText("Plan Number:");

        jLabel29.setText("Land Name:");

        search_grant_landName_text.setEditable(false);

        jLabel30.setText("Division Number:");

        jLabel31.setText("Division Name:");

        search_grant_division_name_text.setEditable(false);

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel9.setText("Included Land");

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel10.setText("Included Division");

        jLabel104.setText("Expected Extent:");

        search_grant_acres_text.setEditable(false);

        search_grant_perches_text.setEditable(false);
        search_grant_perches_text.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                search_grant_perches_textActionPerformed(evt);
            }
        });

        search_grant_roods_text.setEditable(false);

        jLabel105.setText("Roods");

        jLabel107.setText("Acre / Hectare");

        jLabel108.setText("Lot No:");

        search_grant_division_no_text.setEditable(false);

        search_grant_plan_no_text.setEditable(false);

        search_grant_lotno_text.setEditable(false);

        jLabel106.setText("Perches");

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel11.setText("Lot details");

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10))
                        .addGap(410, 410, 410))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addGap(423, 423, 423))))
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addComponent(jLabel104)
                        .addGap(40, 40, 40)
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(search_grant_acres_text, javax.swing.GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE)
                            .addComponent(search_grant_roods_text, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(search_grant_perches_text, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel106)
                            .addComponent(jLabel105)
                            .addComponent(jLabel107)))
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(24, 24, 24)
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(search_grant_lotno_text, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(search_grant_division_no_text, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(search_grant_plan_no_text, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(search_grant_division_name_text, javax.swing.GroupLayout.DEFAULT_SIZE, 608, Short.MAX_VALUE)
                            .addComponent(search_grant_landName_text)))
                    .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel108))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(search_grant_division_no_text, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(search_grant_division_name_text, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addComponent(jLabel9)
                .addGap(15, 15, 15)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(search_grant_plan_no_text, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(search_grant_landName_text, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel11)
                .addGap(12, 12, 12)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel108)
                    .addComponent(search_grant_lotno_text, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel104, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(search_grant_acres_text, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel107))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(search_grant_roods_text, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel105))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(search_grant_perches_text, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel106))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel14, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jPanel12, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 833, Short.MAX_VALUE)
                    .addComponent(jPanel10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 833, Short.MAX_VALUE)
                    .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(318, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(262, Short.MAX_VALUE))
        );

        jScrollPane4.setViewportView(jPanel5);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 868, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 1465, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Search grants", jPanel3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Add_Grant_Grant_NoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Add_Grant_Grant_NoKeyReleased
        // TODO add your handling code here:
        EnableAdd();
    }//GEN-LAST:event_Add_Grant_Grant_NoKeyReleased

    private void add_grant_permit_no_comboItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_add_grant_permit_no_comboItemStateChanged
        this.UpdateOwner();

        /*   try {
            this.choosenPermit = PermitController.searchPermit(String.valueOf(add_grant_permit_no_combo.getSelectedItem()));
            if (choosenPermit != null) {
                Client client = choosenPermit.getClient();
                NominatedSuccessor nominatedSuccessor = choosenPermit.getNominatedSuccessor();
                Lot lot = choosenPermit.getLot();
                Land land = lot.getLand();
                String permitIssueDate = choosenPermit.getPermitIssueDate();
                this.add_grant_issuedate_label.setText(permitIssueDate);//Permit issue date need to be added

                this.add_grant_division_no_text.setText(land.getDivisionNumber());
                GramaNiladariDivision searchGND = GramaNiladariDivisionController.searchGND(land.getDivisionNumber());
                add_grant_division_name_text.setText(searchGND.getDivisionName());

                this.add_grant_landName_text.setText(land.getLandName());
                this.add_grant_plan_no_text.setText(land.getPlanNumber());

                this.add_grant_lotno_text.setText(lot.getLotNumber());
                this.add_grant_acres_text.setText(String.valueOf(lot.getNumberOfAcres()));
                this.add_grant_perches_text.setText(String.valueOf(lot.getNumberOfPerches()));
                this.add_grant_roods_text.setText(String.valueOf(lot.getNumberofRoods()));

                this.add_grant_owner_nic_text.setText(client.getNIC());
                this.add_grant_ownernameText.setText(client.getClientName());
                this.add_grantowner_telephoneText.setText(client.getTelephone());
                this.add_grantowner_DOB_test.setText(client.getBirthday());
                this.add_grantowner_addressText.setText(client.getAddress());
                this.add_grant_owner_annualIncome.setText(String.valueOf(client.getAnnualIncome()));
                this.add_grant_owner_no_of_children_test.setText(String.valueOf(client.getNumberOfMarriedSons() + client.getNumberOfUnmarriedSons()));
                if (client.isMarried() == 1) {
                    this.add_grant_owner_marriedStatusRButton.setSelected(true);
                } else {
                    this.add_grant_owner_singleStatusRButton.setSelected(true);
                }

                this.addgrant_S_nic_test.setText(nominatedSuccessor.getNIC_S());
                this.addgrant_S_name_test.setText(nominatedSuccessor.getName());
                this.addgrant_S_address_test.setText(nominatedSuccessor.getAddress());

            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(PermitForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        */
    }//GEN-LAST:event_add_grant_permit_no_comboItemStateChanged

    private void add_grant_permit_no_comboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_add_grant_permit_no_comboActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_add_grant_permit_no_comboActionPerformed

    private void addgrant_permit_issueDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addgrant_permit_issueDateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_addgrant_permit_issueDateActionPerformed

    private void add_grant_perches_textActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_add_grant_perches_textActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_add_grant_perches_textActionPerformed

    private void add_grant_ownernameTextKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_add_grant_ownernameTextKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_add_grant_ownernameTextKeyReleased

    private void add_grant_owner_no_of_children_testActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_add_grant_owner_no_of_children_testActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_add_grant_owner_no_of_children_testActionPerformed

    private void add_grant_owner_marriedStatusRButtonStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_add_grant_owner_marriedStatusRButtonStateChanged
        // TODO add your handling code here:
        EnableAdd();
    }//GEN-LAST:event_add_grant_owner_marriedStatusRButtonStateChanged

    private void add_grant_owner_singleStatusRButtonStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_add_grant_owner_singleStatusRButtonStateChanged
        // TODO add your handling code here:
        EnableAdd();
    }//GEN-LAST:event_add_grant_owner_singleStatusRButtonStateChanged

    private void add_grant_owner_singleStatusRButtonKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_add_grant_owner_singleStatusRButtonKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_add_grant_owner_singleStatusRButtonKeyReleased

    private void add_grant_changeOwnerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_add_grant_changeOwnerActionPerformed
        ChangePermitOwnershipForm changeOwnForm = new ChangePermitOwnershipForm(this);
        changeOwnForm.setVisible(true);

    }//GEN-LAST:event_add_grant_changeOwnerActionPerformed

    private void nominateSuccessorButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nominateSuccessorButtonActionPerformed
        ChangeNominateSuccessoGrantrForm newGrantSuccessor = new ChangeNominateSuccessoGrantrForm(this,this.choosenPermit);
        newGrantSuccessor.setVisible(true);
    }//GEN-LAST:event_nominateSuccessorButtonActionPerformed

    private void add_grant_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_add_grant_buttonActionPerformed
        try {
            this.choosenPermit = PermitController.searchPermit(String.valueOf(add_grant_permit_no_combo.getSelectedItem()));
            if (choosenPermit!=null){
                NominatedSuccessor nominatedSuccessor = new NominatedSuccessor(this.addgrant_S_nic_test.getText(), this.addgrant_S_name_test.getText(), this.addgrant_S_address_test.getText());
                Client searchClient = ClientController.searchClient(String.valueOf(this.add_grant_owner_nic_text.getText()));
                Lot searchLot = LotController.searchLot(this.add_grant_lotno_text.getText());
                Date date = this.addgrant_grant_issue_dateChooser.getDate();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-dd");
                String issueDate = simpleDateFormat.format(date);

                Grant grant = new Grant(this.Add_Grant_Grant_No.getText(), issueDate,choosenPermit, searchLot, searchClient, nominatedSuccessor);
                boolean addNewGrant = GrantController.addNewGrant(grant);
                if (addNewGrant) {
                    this.frontpage.setRemainders();
                    JOptionPane.showMessageDialog(this, "Grant Added successfully");
                    add_grant_ownernameText.setText(null);
                    add_grant_owner_nic_text.setText(null);
                    add_grantowner_telephoneText.setText(null);
                    add_grantowner_addressText.setText(null);
                    add_grantowner_DOB_test.setText(null);
                    add_grant_owner_annualIncome.setText(null);
                    add_grant_owner_no_of_children_test.setText(null);
                    addgrant_S_name_test.setText(null);

                    addgrant_S_nic_test.setText(null);
                    addgrant_S_address_test.setText(null);
                    addgrant_permit_issueDate.setText(null);
                    add_grant_division_no_text.setText(null);
                    add_grant_division_name_text.setText(null);
                    add_grant_plan_no_text.setText(null);
                    add_grant_landName_text.setText(null);
                    add_grant_lotno_text.setText(null);
                    add_grant_acres_text.setText(null);
                    add_grant_perches_text.setText(null);
                    add_grant_roods_text.setText(null);
                    add_grant_owner_marriedStatusRButton.setSelected(false);
                    add_grant_owner_singleStatusRButton.setSelected(false);
                    Add_Grant_Grant_No.setText(null);
                    add_grant_permit_no_combo.setSelectedItem(null);
                } else {
                    JOptionPane.showMessageDialog(this, "Grant does not added successfully");
                }

            }
        }

        catch (ClassNotFoundException | SQLException|RemoteException ex) {
            Logger.getLogger(PermitForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_add_grant_buttonActionPerformed

    private void addgrant_S_nic_testActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addgrant_S_nic_testActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_addgrant_S_nic_testActionPerformed

    private void search_grant_grantnoComboItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_search_grant_grantnoComboItemStateChanged

        try {
            this.choosenGrant = GrantController.searchGrant(String.valueOf(this.search_grant_grantnoCombo.getSelectedItem()));
            if (choosenGrant != null) {
                Client client = choosenGrant.getClient();
                NominatedSuccessor nominatedSuccessor = choosenGrant.getNominatedSuccessor();
                Lot lot = choosenGrant.getLot();
                Land land = lot.getLand();
                String grantIssueDate = choosenGrant.getGrantIssueDate();
                this.search_grant_issuedateText.setText(grantIssueDate);//Permit issue date need to be added

                this.search_grant_division_no_text.setText(land.getDivisionNumber());
                GramaNiladariDivision searchGND = GramaNiladariDivisionController.searchGND(land.getDivisionNumber());
                search_grant_division_name_text.setText(searchGND.getDivisionName());

                this.search_grant_landName_text.setText(land.getLandName());
                this.search_grant_plan_no_text.setText(land.getPlanNumber());

                this.search_grant_lotno_text.setText(lot.getLotNumber());
                this.search_grant_acres_text.setText(String.valueOf(lot.getNumberOfAcres()));
                this.search_grant_perches_text.setText(String.valueOf(lot.getNumberOfPerches()));
                this.search_grant_roods_text.setText(String.valueOf(lot.getNumberofRoods()));

                this.search_grant_owner_nic_text.setText(client.getNIC());
                this.search_grant_ownernameText.setText(client.getClientName());
                this.search_grantowner_telephoneText.setText(client.getTelephone());
                this.search_grantowner_DOB_text.setText(client.getBirthday());
                this.search_grantowner_addressText.setText(client.getAddress());
                this.search_grant_owner_annualIncome.setText(String.valueOf(client.getAnnualIncome()));
                this.search_grant_owner_no_of_children_text.setText(String.valueOf(client.getNumberOfMarriedSons() + client.getNumberOfUnmarriedSons()));
                if (client.isMarried() == 1) {
                    this.search_grant_owner_marriedStatusRButton.setSelected(true);
                } else {
                    this.search_grant_owner_singleStatusRButton.setSelected(true);
                }

                this.searchgrant_S_nic_text.setText(nominatedSuccessor.getNIC_S());
                this.searchgrant_S_name_text.setText(nominatedSuccessor.getName());
                this.searchgrant_S_address_text.setText(nominatedSuccessor.getAddress());

            }
        } catch (ClassNotFoundException | SQLException |RemoteException ex) {
            Logger.getLogger(PermitForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_search_grant_grantnoComboItemStateChanged

    private void search_grant_ownernameTextKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_search_grant_ownernameTextKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_search_grant_ownernameTextKeyReleased

    private void search_grant_owner_no_of_children_textActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_search_grant_owner_no_of_children_textActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_search_grant_owner_no_of_children_textActionPerformed

    private void search_grant_perches_textActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_search_grant_perches_textActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_search_grant_perches_textActionPerformed

    private void viewAll_load_buttunActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewAll_load_buttunActionPerformed
        DefaultTableModel model = (DefaultTableModel) viewAll_table.getModel();
        model.getDataVector().removeAllElements();
        revalidate();
        try {
            ArrayList<Grant> allGrant = GrantController.getAllGrant();

            for (Grant grant : allGrant) {
                Object[] rowdata = {grant.getGrantNumber(), grant.getGrantIssueDate(), grant.getClient().getClientName(), grant.getClient().getNIC(), grant.getLot().getLand().getDivisionNumber(), grant.getLot().getLand().getPlanNumber(), grant.getLot().getLand().getLandName(), grant.getLot().getLotNumber(), grant.getNominatedSuccessor().getName(), grant.getClient().getGrantOwnershipPosition()};
                model.addRow(rowdata);
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(GramaNiladhariForm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RemoteException ex) {
            Logger.getLogger(GrantForm2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_viewAll_load_buttunActionPerformed

    
    public void EnableAdd(){
    if(addgrant_permit_issueDate.getText().trim().length()!=0 && Add_Grant_Grant_No.getText().trim().length()!=0){
        add_grant_button.setEnabled(true);
    }
}
    public void UpdateOwner(){
        try {
            if (!this.isReminderPermit){
                this.choosenPermit = PermitController.searchPermit(String.valueOf(add_grant_permit_no_combo.getSelectedItem()));
            }
            else {
                this.add_grant_permit_no_combo.setSelectedItem(this.choosenPermit.getPermitNumber());
            }
            if (choosenPermit != null) {
                Client client = choosenPermit.getClient();
                NominatedSuccessor nominatedSuccessor = choosenPermit.getNominatedSuccessor();
                Lot lot = choosenPermit.getLot();
                Land land = lot.getLand();
                String permitIssueDate = choosenPermit.getPermitIssueDate();
                this.addgrant_permit_issueDate.setText(permitIssueDate);//Permit issue date need to be added

                this.add_grant_division_no_text.setText(land.getDivisionNumber());
                GramaNiladariDivision searchGND = GramaNiladariDivisionController.searchGND(land.getDivisionNumber());
                add_grant_division_name_text.setText(searchGND.getDivisionName());

                this.add_grant_landName_text.setText(land.getLandName());
                this.add_grant_plan_no_text.setText(land.getPlanNumber());

                this.add_grant_lotno_text.setText(lot.getLotNumber());
                this.add_grant_acres_text.setText(String.valueOf(lot.getNumberOfAcres()));
                this.add_grant_perches_text.setText(String.valueOf(lot.getNumberOfPerches()));
                this.add_grant_roods_text.setText(String.valueOf(lot.getNumberofRoods()));

                this.add_grant_owner_nic_text.setText(client.getNIC());
                this.add_grant_ownernameText.setText(client.getClientName());
                this.add_grantowner_telephoneText.setText(client.getTelephone());
                this.add_grantowner_DOB_test.setText(client.getBirthday());
                this.add_grantowner_addressText.setText(client.getAddress());
                this.add_grant_owner_annualIncome.setText(String.valueOf(client.getAnnualIncome()));
                this.add_grant_owner_no_of_children_test.setText(String.valueOf(client.getNumberOfMarriedSons() + client.getNumberOfUnmarriedSons()));
                if (client.isMarried() == 1) {
                    this.add_grant_owner_marriedStatusRButton.setSelected(true);
                } else {
                    this.add_grant_owner_singleStatusRButton.setSelected(true);
                }
                
                this.addgrant_S_nic_test.setText(nominatedSuccessor.getNIC_S());
                this.addgrant_S_name_test.setText(nominatedSuccessor.getName());
                this.addgrant_S_address_test.setText(nominatedSuccessor.getAddress());

               
            }
        } catch (ClassNotFoundException | SQLException|RemoteException ex) {
            Logger.getLogger(PermitForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    
    }
    
    public void UpdateSuccessor(NominatedSuccessor newSuccessor){
            this.addgrant_S_nic_test.setText(newSuccessor.getNIC_S());
            this.addgrant_S_name_test.setText(newSuccessor.getName());
            this.addgrant_S_address_test.setText(newSuccessor.getAddress());
    
    }
    
    public Permit getPermit(){
        return this.choosenPermit;
    }
    
    public void SearchGrantByNo(String num){
         try {
            this.choosenGrant = GrantController.searchGrant(num);
            if (choosenGrant != null) {
                search_grant_grantnoCombo.setSelectedItem(num);
                Client client = choosenGrant.getClient();
                NominatedSuccessor nominatedSuccessor = choosenGrant.getNominatedSuccessor();
                Lot lot = choosenGrant.getLot();
                Land land = lot.getLand();
                String grantIssueDate = choosenGrant.getGrantIssueDate();
                this.search_grant_issuedateText.setText(grantIssueDate);//Permit issue date need to be added

                this.search_grant_division_no_text.setText(land.getDivisionNumber());
                GramaNiladariDivision searchGND = GramaNiladariDivisionController.searchGND(land.getDivisionNumber());
                search_grant_division_name_text.setText(searchGND.getDivisionName());

                this.search_grant_landName_text.setText(land.getLandName());
                this.search_grant_plan_no_text.setText(land.getPlanNumber());

                this.search_grant_lotno_text.setText(lot.getLotNumber());
                this.search_grant_acres_text.setText(String.valueOf(lot.getNumberOfAcres()));
                this.search_grant_perches_text.setText(String.valueOf(lot.getNumberOfPerches()));
                this.search_grant_roods_text.setText(String.valueOf(lot.getNumberofRoods()));

                this.search_grant_owner_nic_text.setText(client.getNIC());
                this.search_grant_ownernameText.setText(client.getClientName());
                this.search_grantowner_telephoneText.setText(client.getTelephone());
                this.search_grantowner_DOB_text.setText(client.getBirthday());
                this.search_grantowner_addressText.setText(client.getAddress());
                this.search_grant_owner_annualIncome.setText(String.valueOf(client.getAnnualIncome()));
                this.search_grant_owner_no_of_children_text.setText(String.valueOf(client.getNumberOfMarriedSons() + client.getNumberOfUnmarriedSons()));
                if (client.isMarried() == 1) {
                    this.search_grant_owner_marriedStatusRButton.setSelected(true);
                } else {
                    this.search_grant_owner_singleStatusRButton.setSelected(true);
                }
                
                this.searchgrant_S_nic_text.setText(nominatedSuccessor.getNIC_S());
                this.searchgrant_S_name_text.setText(nominatedSuccessor.getName());
                this.searchgrant_S_address_text.setText(nominatedSuccessor.getAddress());

               
            }
        } catch (ClassNotFoundException | SQLException |RemoteException ex) {
            Logger.getLogger(PermitForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
                                                      

                                                 

    public void focustabbedpane(int num){
        jTabbedPane1.setSelectedIndex(num);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Add_Grant_Grant_No;
    private javax.swing.JTextField add_grant_acres_text;
    private javax.swing.JButton add_grant_button;
    private javax.swing.JButton add_grant_changeOwner;
    private javax.swing.JTextField add_grant_division_name_text;
    private javax.swing.JTextField add_grant_division_no_text;
    private javax.swing.JLabel add_grant_issuedate_label;
    private javax.swing.JTextField add_grant_landName_text;
    private javax.swing.JTextField add_grant_lotno_text;
    private javax.swing.JTextField add_grant_owner_annualIncome;
    private javax.swing.JRadioButton add_grant_owner_marriedStatusRButton;
    private javax.swing.JTextField add_grant_owner_nic_text;
    private javax.swing.JTextField add_grant_owner_no_of_children_test;
    private javax.swing.JRadioButton add_grant_owner_singleStatusRButton;
    private javax.swing.JTextField add_grant_ownernameText;
    private javax.swing.JTextField add_grant_perches_text;
    private javax.swing.JComboBox add_grant_permit_no_combo;
    private javax.swing.JTextField add_grant_plan_no_text;
    private javax.swing.JTextField add_grant_roods_text;
    private javax.swing.JTextField add_grantowner_DOB_test;
    private javax.swing.JTextArea add_grantowner_addressText;
    private javax.swing.JTextField add_grantowner_telephoneText;
    private javax.swing.JTextArea addgrant_S_address_test;
    private javax.swing.JTextField addgrant_S_name_test;
    private javax.swing.JTextField addgrant_S_nic_test;
    private org.freixas.jcalendar.JCalendarCombo addgrant_grant_issue_dateChooser;
    private javax.swing.JTextField addgrant_permit_issueDate;
    private javax.swing.JLabel grantnolabel;
    private javax.swing.JLabel issuedatelabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel100;
    private javax.swing.JLabel jLabel101;
    private javax.swing.JLabel jLabel102;
    private javax.swing.JLabel jLabel103;
    private javax.swing.JLabel jLabel104;
    private javax.swing.JLabel jLabel105;
    private javax.swing.JLabel jLabel106;
    private javax.swing.JLabel jLabel107;
    private javax.swing.JLabel jLabel108;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel99;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JScrollPane jScrollPane15;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JButton nominateSuccessorButton;
    private javax.swing.JLabel permitnolabel;
    private javax.swing.JTextField search_grant_acres_text;
    private javax.swing.JTextField search_grant_division_name_text;
    private javax.swing.JTextField search_grant_division_no_text;
    private javax.swing.JComboBox search_grant_grantnoCombo;
    private javax.swing.JTextField search_grant_issuedateText;
    private javax.swing.JLabel search_grant_issuedatelabel;
    private javax.swing.JTextField search_grant_landName_text;
    private javax.swing.JTextField search_grant_lotno_text;
    private javax.swing.JTextField search_grant_owner_annualIncome;
    private javax.swing.JRadioButton search_grant_owner_marriedStatusRButton;
    private javax.swing.JTextField search_grant_owner_nic_text;
    private javax.swing.JTextField search_grant_owner_no_of_children_text;
    private javax.swing.JRadioButton search_grant_owner_singleStatusRButton;
    private javax.swing.JTextField search_grant_ownernameText;
    private javax.swing.JTextField search_grant_perches_text;
    private javax.swing.JTextField search_grant_plan_no_text;
    private javax.swing.JTextField search_grant_roods_text;
    private javax.swing.JLabel search_grantnolabel;
    private javax.swing.JTextField search_grantowner_DOB_text;
    private javax.swing.JTextArea search_grantowner_addressText;
    private javax.swing.JTextField search_grantowner_telephoneText;
    private javax.swing.JTextArea searchgrant_S_address_text;
    private javax.swing.JTextField searchgrant_S_name_text;
    private javax.swing.JTextField searchgrant_S_nic_text;
    private javax.swing.JButton viewAll_load_buttun;
    private javax.swing.JTable viewAll_table;
    // End of variables declaration//GEN-END:variables
}
