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
import las.common_classes.PatternChecker;
import las.controller.ClientController;
import las.controller.GramaNiladariDivisionController;
import las.controller.GrantController;
import las.controller.LandController;
import las.controller.LotController;
import las.controller.NominatedSuccessorController;
import las.controller.PermitController;
import las.models.Client;
import las.models.GramaNiladariDivision;
import las.models.Land;
import las.models.Lot;
import las.models.NominatedSuccessor;
import las.models.Permit;

/**
 *
 * @author H.P. Asela
 */
public class PermitForm extends javax.swing.JInternalFrame {

    LandController LandController;
    LotController LotController;
    GrantController GrantController;
    ClientController ClientController;
    PermitController PermitController;
    GramaNiladariDivisionController GramaNiladariDivisionController;
    NominatedSuccessorController NominatedSuccessorController;

    /**
     * Creates new form PermitForm
     */
    public PermitForm() {
        initComponents();

        try {
            Connector sConnector = Connector.getSConnector();
            ClientController = sConnector.getClientController();
            GrantController = sConnector.getGrantController();
            PermitController = sConnector.getPermitController();
            GramaNiladariDivisionController = sConnector.getGramaNiladariDivisionController();
            NominatedSuccessorController = sConnector.getnomiNominatedSuccessorController();
            LotController=sConnector.getlotController();
            LandController = sConnector.getLandController();

        } catch (RemoteException | SQLException | NotBoundException | MalformedURLException | ClassNotFoundException ex) {
            Logger.getLogger(ApplicantForm.class.getName()).log(Level.SEVERE, null, ex);
        }

        nicInvalidLabel.setVisible(false);
        nameinvalidlabel.setVisible(false);
        generatePemitNumberButtun.setEnabled(false);
        addpermit_add_permit_button.setEnabled(false);
        add_permit_division_number_combo.requestFocus();

        add_permit_division_name_test.setEditable(false);
        add_permit_landName_test.setEditable(false);
        add_permit_perches_test.setEditable(false);
        add_permit_roods_test.setEditable(false);
        add_permit_acres_test.setEditable(false);
        add_permit_nameText.setEditable(false);
        add_permit_telephoneText.setEditable(false);
        add_permit_addressText.setEditable(false);
        add_permit_DOB_test.setEditable(false);
        add_permit_annualIncome.setEditable(false);
        add_permit_no_of_children_test.setEditable(false);
        addpermit_permit_numberTest.setEditable(false);
        cancelPermit_issueDate.setEditable(false);
        cancelPermit_DivisionumberTest.setEditable(false);
        cancelpermit_division_name_test.setEditable(false);
        cancelPermitPlanNumberTest.setEditable(false);
        cancelpermit_landName_test.setEditable(false);
        cancelPermitLotNumberTest.setEditable(false);
        cancelpermit_acres_test.setEditable(false);
        cancelpermit_perches_test.setEditable(false);
        cancelpermit_roods_test.setEditable(false);
        cancelPermit_clientNic.setEditable(false);
        cancelpermit_nameText.setEditable(false);
        cancelpermit_telephoneText.setEditable(false);
        cancelpermit_DOB_test.setEditable(false);
        cancelpermit_addressText.setEditable(false);
        cancelpermit_annualIncome.setEditable(false);
        cancelpermit_no_of_children_test.setEditable(false);
        cancelpermit_NOS_name_test.setEditable(false);
        cancelpermit_NOS_nic_test.setEditable(false);
        cancelpermit_NOS_address_test.setEditable(false);
        cancelpermit_cancel_permit_button.setEnabled(false);
        add_permit_division_number_combo.setEnabled(true);
        addressinvalidlabel.setVisible(false);
        
        view_permit_combo.setEditable(true);
        JTextComponent editorViewPemitPNCombo = (JTextComponent) view_permit_combo.getEditor().getEditorComponent();
        editorViewPemitPNCombo.addKeyListener(new KeyAdapter() {

            @Override
            public void keyReleased(KeyEvent e) {
                String item = (String) view_permit_combo.getEditor().getItem();
                ArrayList<Object> list = new ArrayList();
                try {
                    ArrayList<Permit> granthaventPermit = PermitController.getGrantHaventPermit(item);
                    for (int i = 0; i < granthaventPermit.size(); i++) {
                        list.add(granthaventPermit.get(i).getPermitNumber());
                    }
                    GUIitemsValidator.addItemToCombo(list, view_permit_combo);

                } catch (ClassNotFoundException | SQLException | RemoteException ex) {
                    Logger.getLogger(LandForm.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

        });

        

        //cancel permit
        
        cancelpermit_permit_number_combo.setEditable(true);
        JTextComponent editorCancelPemitPNCombo = (JTextComponent) cancelpermit_permit_number_combo.getEditor().getEditorComponent();
        editorCancelPemitPNCombo.addKeyListener(new KeyAdapter() {

            @Override
            public void keyReleased(KeyEvent e) {
                String item = (String) cancelpermit_permit_number_combo.getEditor().getItem();
                ArrayList<Object> list = new ArrayList();
                try {
                    ArrayList<Permit> granthaventPermit = PermitController.getGrantHaventPermit(item);
                    for (int i = 0; i < granthaventPermit.size(); i++) {
                        list.add(granthaventPermit.get(i).getPermitNumber());
                    }
                    GUIitemsValidator.addItemToCombo(list, cancelpermit_permit_number_combo);

                } catch (ClassNotFoundException | SQLException | RemoteException ex) {
                    Logger.getLogger(LandForm.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

        });

        //seach permit 
        add_permit_nic_combo.setEditable(true);
        JTextComponent editorAddPemitNICCombo = (JTextComponent) add_permit_nic_combo.getEditor().getEditorComponent();
        editorAddPemitNICCombo.addKeyListener(new KeyAdapter() {

            @Override
            public void keyReleased(KeyEvent e) {
                String item = (String) add_permit_nic_combo.getEditor().getItem();
                ArrayList<Object> list = new ArrayList();
                try {

                    ArrayList<Client> simmilarNICs = ClientController.getNoPermitOwners(item);
                    for (int i = 0; i < simmilarNICs.size(); i++) {
                        list.add(simmilarNICs.get(i).getNIC());
                    }
                    GUIitemsValidator.addItemToCombo(list, add_permit_nic_combo);

                } catch (ClassNotFoundException | SQLException | RemoteException ex) {
                    Logger.getLogger(LandForm.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

        });

        add_permit_division_number_combo.setEditable(true);
        JTextComponent editorDivisionalNumberCombo = (JTextComponent) add_permit_division_number_combo.getEditor().getEditorComponent();

        editorDivisionalNumberCombo.addKeyListener(new KeyAdapter() {

            @Override
            public void keyReleased(KeyEvent e) {

                add_permit_division_number_combo.setPopupVisible(true);
                String test = String.valueOf(add_permit_division_number_combo.getEditor().getItem());
                add_permit_division_number_combo.removeAllItems();
                try {
                    ArrayList<GramaNiladariDivision> simmilarGndDivisionNumbers = GramaNiladariDivisionController.getSimmilarGndDivisionNumbers(test);
                    for (GramaNiladariDivision simmilarGndDivisionNumber : simmilarGndDivisionNumbers) {
                        add_permit_division_number_combo.addItem(simmilarGndDivisionNumber.getDivisionNumber());
                    }
                    add_permit_division_number_combo.getEditor().setItem(test);
                } catch (ClassNotFoundException | SQLException | RemoteException ex) {
                    Logger.getLogger(GramaNiladhariForm.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

        });

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
        jTabbedPane4 = new javax.swing.JTabbedPane();
        jPanel5 = new javax.swing.JPanel();
        jPanel18 = new javax.swing.JPanel();
        jPanel19 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        AddGND2 = new javax.swing.JTextField();
        jLabel44 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        AddGNDNo2 = new javax.swing.JTextField();
        jLabel54 = new javax.swing.JLabel();
        AddNotSurveyedNorth2 = new javax.swing.JTextField();
        jLabel55 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        AddNotSurveyedEast2 = new javax.swing.JTextField();
        AddNotSurveyedSouth2 = new javax.swing.JTextField();
        AddNotSurveyedWest2 = new javax.swing.JTextField();
        jLabel58 = new javax.swing.JLabel();
        cancelpermit_permit_number_combo = new javax.swing.JComboBox();
        cancelPermit_issueDate = new javax.swing.JTextField();
        jPanel21 = new javax.swing.JPanel();
        jLabel65 = new javax.swing.JLabel();
        jLabel66 = new javax.swing.JLabel();
        jLabel67 = new javax.swing.JLabel();
        cancelpermit_NOS_name_test = new javax.swing.JTextField();
        cancelpermit_NOS_nic_test = new javax.swing.JTextField();
        jScrollPane6 = new javax.swing.JScrollPane();
        cancelpermit_NOS_address_test = new javax.swing.JTextArea();
        jPanel22 = new javax.swing.JPanel();
        cancelpermit_nameText = new javax.swing.JTextField();
        cancelpermit_telephoneText = new javax.swing.JTextField();
        jScrollPane7 = new javax.swing.JScrollPane();
        cancelpermit_addressText = new javax.swing.JTextArea();
        cancelpermit_no_of_children_test = new javax.swing.JTextField();
        cancelpermit_marriedStatusRButton = new javax.swing.JRadioButton();
        cancelpermit_singleStatusRButton = new javax.swing.JRadioButton();
        jLabel68 = new javax.swing.JLabel();
        jLabel69 = new javax.swing.JLabel();
        jLabel70 = new javax.swing.JLabel();
        jLabel71 = new javax.swing.JLabel();
        jLabel72 = new javax.swing.JLabel();
        jLabel73 = new javax.swing.JLabel();
        jLabel74 = new javax.swing.JLabel();
        cancelpermit_annualIncome = new javax.swing.JTextField();
        cancelpermit_DOB_test = new javax.swing.JTextField();
        jLabel75 = new javax.swing.JLabel();
        cancelPermit_clientNic = new javax.swing.JTextField();
        jPanel20 = new javax.swing.JPanel();
        jLabel59 = new javax.swing.JLabel();
        jLabel60 = new javax.swing.JLabel();
        cancelpermit_landName_test = new javax.swing.JTextField();
        jLabel61 = new javax.swing.JLabel();
        jLabel62 = new javax.swing.JLabel();
        cancelpermit_division_name_test = new javax.swing.JTextField();
        jLabel63 = new javax.swing.JLabel();
        jLabel64 = new javax.swing.JLabel();
        jLabel109 = new javax.swing.JLabel();
        cancelpermit_acres_test = new javax.swing.JTextField();
        cancelpermit_perches_test = new javax.swing.JTextField();
        cancelpermit_roods_test = new javax.swing.JTextField();
        jLabel110 = new javax.swing.JLabel();
        jLabel111 = new javax.swing.JLabel();
        jLabel112 = new javax.swing.JLabel();
        jLabel113 = new javax.swing.JLabel();
        cancelPermit_DivisionumberTest = new javax.swing.JTextField();
        cancelPermitPlanNumberTest = new javax.swing.JTextField();
        cancelPermitLotNumberTest = new javax.swing.JTextField();
        jLabel77 = new javax.swing.JLabel();
        cancelpermit_cancel_permit_button = new javax.swing.JButton();
        jPanel24 = new javax.swing.JPanel();
        jPanel25 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        AddGND3 = new javax.swing.JTextField();
        jLabel45 = new javax.swing.JLabel();
        jLabel78 = new javax.swing.JLabel();
        AddGNDNo3 = new javax.swing.JTextField();
        jLabel79 = new javax.swing.JLabel();
        AddNotSurveyedNorth3 = new javax.swing.JTextField();
        jLabel80 = new javax.swing.JLabel();
        jLabel81 = new javax.swing.JLabel();
        jLabel82 = new javax.swing.JLabel();
        AddNotSurveyedEast3 = new javax.swing.JTextField();
        AddNotSurveyedSouth3 = new javax.swing.JTextField();
        AddNotSurveyedWest3 = new javax.swing.JTextField();
        jLabel83 = new javax.swing.JLabel();
        view_permit_combo = new javax.swing.JComboBox();
        viewPermit_issueDate1 = new javax.swing.JTextField();
        jPanel26 = new javax.swing.JPanel();
        jLabel84 = new javax.swing.JLabel();
        jLabel85 = new javax.swing.JLabel();
        jLabel86 = new javax.swing.JLabel();
        viewpermit_NOS_name_test1 = new javax.swing.JTextField();
        viewpermit_NOS_nic_test1 = new javax.swing.JTextField();
        jScrollPane8 = new javax.swing.JScrollPane();
        viewpermit_NOS_address_test1 = new javax.swing.JTextArea();
        jPanel28 = new javax.swing.JPanel();
        viewpermit_nameText1 = new javax.swing.JTextField();
        viewpermit_telephoneText1 = new javax.swing.JTextField();
        jScrollPane9 = new javax.swing.JScrollPane();
        viewpermit_addressText1 = new javax.swing.JTextArea();
        viewpermit_no_of_children_test1 = new javax.swing.JTextField();
        viewpermit_marriedStatusRButton1 = new javax.swing.JRadioButton();
        viewpermit_singleStatusRButton1 = new javax.swing.JRadioButton();
        jLabel87 = new javax.swing.JLabel();
        jLabel88 = new javax.swing.JLabel();
        jLabel89 = new javax.swing.JLabel();
        jLabel90 = new javax.swing.JLabel();
        jLabel91 = new javax.swing.JLabel();
        jLabel92 = new javax.swing.JLabel();
        jLabel93 = new javax.swing.JLabel();
        viewpermit_annualIncome1 = new javax.swing.JTextField();
        viewpermit_DOB_test1 = new javax.swing.JTextField();
        jLabel94 = new javax.swing.JLabel();
        viewPermit_clientNic1 = new javax.swing.JTextField();
        jPanel29 = new javax.swing.JPanel();
        jLabel95 = new javax.swing.JLabel();
        jLabel96 = new javax.swing.JLabel();
        viewpermit_landName_test1 = new javax.swing.JTextField();
        jLabel97 = new javax.swing.JLabel();
        jLabel98 = new javax.swing.JLabel();
        viewpermit_division_name_test1 = new javax.swing.JTextField();
        jLabel104 = new javax.swing.JLabel();
        jLabel105 = new javax.swing.JLabel();
        jLabel114 = new javax.swing.JLabel();
        viewpermit_acres_test1 = new javax.swing.JTextField();
        viewpermit_perches_test1 = new javax.swing.JTextField();
        viewpermit_roods_test1 = new javax.swing.JTextField();
        jLabel115 = new javax.swing.JLabel();
        jLabel116 = new javax.swing.JLabel();
        jLabel117 = new javax.swing.JLabel();
        jLabel118 = new javax.swing.JLabel();
        viewPermit_DivisionumberTest1 = new javax.swing.JTextField();
        viewPermitPlanNumberTest1 = new javax.swing.JTextField();
        viewPermitLotNumberTest1 = new javax.swing.JTextField();
        jLabel106 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        AddGND = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        AddGNDNo = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        AddNotSurveyedNorth = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        AddNotSurveyedEast = new javax.swing.JTextField();
        AddNotSurveyedSouth = new javax.swing.JTextField();
        AddNotSurveyedWest = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        addpermit_permit_numberTest = new javax.swing.JTextField();
        addpermit_permit_issue_dateChooser = new org.freixas.jcalendar.JCalendarCombo();
        generatePemitNumberButtun = new javax.swing.JButton();
        jPanel11 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        add_permit_landName_test = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        add_permit_division_name_test = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        add_permit_lot_number_Combo = new javax.swing.JComboBox();
        jLabel100 = new javax.swing.JLabel();
        add_permit_acres_test = new javax.swing.JTextField();
        add_permit_perches_test = new javax.swing.JTextField();
        add_permit_roods_test = new javax.swing.JTextField();
        jLabel103 = new javax.swing.JLabel();
        jLabel102 = new javax.swing.JLabel();
        jLabel101 = new javax.swing.JLabel();
        jLabel99 = new javax.swing.JLabel();
        add_permit_division_number_combo = new javax.swing.JComboBox();
        add_permit_planNumber_combo = new javax.swing.JComboBox();
        jLabel76 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        add_permit_nic_combo = new javax.swing.JComboBox();
        add_permit_nameText = new javax.swing.JTextField();
        add_permit_telephoneText = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        add_permit_addressText = new javax.swing.JTextArea();
        add_permit_no_of_children_test = new javax.swing.JTextField();
        add_permit_marriedStatusRButton = new javax.swing.JRadioButton();
        add_permit_singleStatusRButton = new javax.swing.JRadioButton();
        jLabel32 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        add_permit_annualIncome = new javax.swing.JTextField();
        add_permit_DOB_test = new javax.swing.JTextField();
        jLabel37 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        addpermit_NOS_name_test = new javax.swing.JTextField();
        addpermit_NOS_nic_test = new javax.swing.JTextField();
        addpermit_address = new javax.swing.JScrollPane();
        addpermit_NOS_address_test = new javax.swing.JTextArea();
        nicInvalidLabel = new javax.swing.JLabel();
        nameinvalidlabel = new javax.swing.JLabel();
        addressinvalidlabel = new javax.swing.JLabel();
        addpermit_add_permit_button = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();

        setResizable(true);
        setTitle("Permit");
        setPreferredSize(new java.awt.Dimension(960, 600));

        jTabbedPane4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTabbedPane4.setPreferredSize(new java.awt.Dimension(581, 581));

        jPanel5.setPreferredSize(new java.awt.Dimension(581, 581));

        jPanel18.setPreferredSize(new java.awt.Dimension(950, 600));

        jPanel19.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel19.setPreferredSize(new java.awt.Dimension(581, 581));

        jLabel13.setText("Grama Niladari Division:");

        AddGND2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddGND2ActionPerformed(evt);
            }
        });

        jLabel44.setText("Permit No:");

        jLabel53.setText("Grama Niladari Division No:");

        AddGNDNo2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddGNDNo2ActionPerformed(evt);
            }
        });

        jLabel54.setText("On The North By:");

        AddNotSurveyedNorth2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddNotSurveyedNorth2ActionPerformed(evt);
            }
        });

        jLabel55.setText("On The East  By:");

        jLabel56.setText("On The South By:");

        jLabel57.setText("On The West By:");

        AddNotSurveyedEast2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddNotSurveyedEast2ActionPerformed(evt);
            }
        });

        AddNotSurveyedSouth2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddNotSurveyedSouth2ActionPerformed(evt);
            }
        });

        AddNotSurveyedWest2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddNotSurveyedWest2ActionPerformed(evt);
            }
        });

        jLabel58.setText("Issue Date:");

        cancelpermit_permit_number_combo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cancelpermit_permit_number_comboItemStateChanged(evt);
            }
        });
        cancelpermit_permit_number_combo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelpermit_permit_number_comboActionPerformed(evt);
            }
        });

        cancelPermit_issueDate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelPermit_issueDateActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addGap(77, 77, 77)
                        .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel19Layout.createSequentialGroup()
                                .addComponent(jLabel57)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(AddNotSurveyedWest2))
                            .addGroup(jPanel19Layout.createSequentialGroup()
                                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel56)
                                    .addComponent(jLabel55)
                                    .addComponent(jLabel54))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(AddNotSurveyedEast2)
                                    .addComponent(AddNotSurveyedSouth2)
                                    .addComponent(AddNotSurveyedNorth2)))))
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel19Layout.createSequentialGroup()
                                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel53, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(21, 21, 21)
                                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(AddGNDNo2, javax.swing.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)
                                    .addComponent(AddGND2)))
                            .addGroup(jPanel19Layout.createSequentialGroup()
                                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel58, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(cancelpermit_permit_number_combo, 0, 121, Short.MAX_VALUE)
                                    .addComponent(cancelPermit_issueDate))))
                        .addGap(2, 2, 2)))
                .addGap(18, 18, 18))
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cancelpermit_permit_number_combo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel58, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cancelPermit_issueDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(488, 488, 488)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel54, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(AddNotSurveyedNorth2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel55, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(AddNotSurveyedEast2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel56, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(AddNotSurveyedSouth2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel57, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(AddNotSurveyedWest2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(AddGND2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(AddGNDNo2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel53))
                .addGap(13, 13, 13))
        );

        jPanel21.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel65.setText("Name:");

        jLabel66.setText("NIC:");

        jLabel67.setText("Address:");

        cancelpermit_NOS_name_test.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelpermit_NOS_name_testActionPerformed(evt);
            }
        });

        cancelpermit_NOS_nic_test.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelpermit_NOS_nic_testActionPerformed(evt);
            }
        });

        cancelpermit_NOS_address_test.setColumns(20);
        cancelpermit_NOS_address_test.setRows(5);
        jScrollPane6.setViewportView(cancelpermit_NOS_address_test);

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel65)
                    .addComponent(jLabel66)
                    .addComponent(jLabel67))
                .addGap(37, 37, 37)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cancelpermit_NOS_name_test, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cancelpermit_NOS_nic_test, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel65)
                    .addComponent(cancelpermit_NOS_name_test, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel66)
                    .addComponent(cancelpermit_NOS_nic_test, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel21Layout.createSequentialGroup()
                        .addComponent(jLabel67)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(20, 20, 20))
        );

        jPanel22.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        cancelpermit_nameText.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cancelpermit_nameTextKeyReleased(evt);
            }
        });

        cancelpermit_addressText.setColumns(20);
        cancelpermit_addressText.setRows(5);
        jScrollPane7.setViewportView(cancelpermit_addressText);

        cancelpermit_no_of_children_test.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelpermit_no_of_children_testActionPerformed(evt);
            }
        });

        buttonGroup1.add(cancelpermit_marriedStatusRButton);
        cancelpermit_marriedStatusRButton.setText("Married");
        cancelpermit_marriedStatusRButton.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                cancelpermit_marriedStatusRButtonStateChanged(evt);
            }
        });

        buttonGroup1.add(cancelpermit_singleStatusRButton);
        cancelpermit_singleStatusRButton.setText("Single");
        cancelpermit_singleStatusRButton.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                cancelpermit_singleStatusRButtonStateChanged(evt);
            }
        });

        jLabel68.setText("NIC :");

        jLabel69.setText("Name:");

        jLabel70.setText("Phone Number:");

        jLabel71.setText("Address:");

        jLabel72.setText("Birthday:");

        jLabel73.setText("Status:");

        jLabel74.setText("Annual Income:");

        cancelpermit_DOB_test.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelpermit_DOB_testActionPerformed(evt);
            }
        });

        jLabel75.setText("No. of children:");

        cancelPermit_clientNic.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cancelPermit_clientNicKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel22Layout.createSequentialGroup()
                        .addComponent(jLabel71, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(49, 49, 49)
                        .addComponent(jScrollPane7))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel22Layout.createSequentialGroup()
                                .addComponent(jLabel70, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(32, 32, 32)
                                .addComponent(cancelpermit_telephoneText))
                            .addGroup(jPanel22Layout.createSequentialGroup()
                                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel74)
                                    .addComponent(jLabel75))
                                .addGap(48, 48, 48)
                                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cancelpermit_annualIncome)
                                    .addComponent(cancelpermit_no_of_children_test)))
                            .addGroup(jPanel22Layout.createSequentialGroup()
                                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel22Layout.createSequentialGroup()
                                        .addComponent(jLabel69, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(76, 76, 76))
                                    .addGroup(jPanel22Layout.createSequentialGroup()
                                        .addComponent(jLabel68, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGap(31, 31, 31)))
                                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cancelPermit_clientNic, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cancelpermit_nameText))))
                        .addComponent(jLabel73)
                        .addGroup(jPanel22Layout.createSequentialGroup()
                            .addGap(123, 123, 123)
                            .addComponent(cancelpermit_marriedStatusRButton)
                            .addGap(18, 18, 18)
                            .addComponent(cancelpermit_singleStatusRButton)))
                    .addGroup(jPanel22Layout.createSequentialGroup()
                        .addComponent(jLabel72, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(66, 66, 66)
                        .addComponent(cancelpermit_DOB_test)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel68, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cancelPermit_clientNic, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel69)
                    .addComponent(cancelpermit_nameText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cancelpermit_telephoneText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel70))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel71)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel22Layout.createSequentialGroup()
                        .addComponent(jLabel72)
                        .addGap(13, 13, 13))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel22Layout.createSequentialGroup()
                        .addComponent(cancelpermit_DOB_test, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cancelpermit_marriedStatusRButton)
                    .addComponent(cancelpermit_singleStatusRButton)
                    .addComponent(jLabel73))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel74)
                    .addComponent(cancelpermit_annualIncome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel75)
                    .addComponent(cancelpermit_no_of_children_test, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel20.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel59.setText("Plan Number");

        jLabel60.setText("Land Name");

        cancelpermit_landName_test.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelpermit_landName_testActionPerformed(evt);
            }
        });

        jLabel61.setText("Division Number:");

        jLabel62.setText("Name:");

        cancelpermit_division_name_test.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelpermit_division_name_testActionPerformed(evt);
            }
        });

        jLabel63.setText("Included Land");

        jLabel64.setText("Included Division");

        jLabel109.setText("Expected Extent");

        cancelpermit_perches_test.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelpermit_perches_testActionPerformed(evt);
            }
        });

        jLabel110.setText("Roods");

        jLabel111.setText("Perches");

        jLabel112.setText("Acre / Hectare");

        jLabel113.setText("Lot No");

        cancelPermitLotNumberTest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelPermitLotNumberTestActionPerformed(evt);
            }
        });

        jLabel77.setText("Lot Detail");

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel77, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jLabel63, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jLabel64)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(cancelPermitPlanNumberTest, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel20Layout.createSequentialGroup()
                            .addComponent(jLabel61, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(28, 28, 28)
                            .addComponent(cancelPermit_DivisionumberTest, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel59, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel62, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel60))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cancelpermit_division_name_test, javax.swing.GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE)
                    .addComponent(cancelpermit_landName_test)))
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(cancelpermit_roods_test, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel20Layout.createSequentialGroup()
                            .addComponent(jLabel113)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cancelPermitLotNumberTest, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel20Layout.createSequentialGroup()
                            .addComponent(jLabel109)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(cancelpermit_acres_test, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel112)
                    .addComponent(jLabel110)))
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addGap(90, 90, 90)
                .addComponent(cancelpermit_perches_test, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel111))
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel64)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel61, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cancelpermit_division_name_test, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel62, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cancelPermit_DivisionumberTest, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel63)
                .addGap(12, 12, 12)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel59, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel60, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cancelpermit_landName_test, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cancelPermitPlanNumberTest, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel77)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel113)
                    .addComponent(cancelPermitLotNumberTest, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel109)
                    .addComponent(cancelpermit_acres_test, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel112))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cancelpermit_perches_test, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel111))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cancelpermit_roods_test, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel110))
                .addContainerGap())
        );

        cancelpermit_cancel_permit_button.setText("Cancel Permit");
        cancelpermit_cancel_permit_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelpermit_cancel_permit_buttonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(cancelpermit_cancel_permit_button)
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jPanel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel19, javax.swing.GroupLayout.DEFAULT_SIZE, 405, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(72, 72, 72)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel18Layout.createSequentialGroup()
                        .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cancelpermit_cancel_permit_button)
                .addContainerGap(31, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, 779, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, 442, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jTabbedPane4.addTab("Cancel Permit", jPanel5);

        jPanel24.setPreferredSize(new java.awt.Dimension(950, 600));

        jPanel25.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel25.setPreferredSize(new java.awt.Dimension(581, 581));

        jLabel19.setText("Grama Niladari Division:");

        AddGND3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddGND3ActionPerformed(evt);
            }
        });

        jLabel45.setText("Permit No:");

        jLabel78.setText("Grama Niladari Division No:");

        AddGNDNo3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddGNDNo3ActionPerformed(evt);
            }
        });

        jLabel79.setText("On The North By:");

        AddNotSurveyedNorth3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddNotSurveyedNorth3ActionPerformed(evt);
            }
        });

        jLabel80.setText("On The East  By:");

        jLabel81.setText("On The South By:");

        jLabel82.setText("On The West By:");

        AddNotSurveyedEast3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddNotSurveyedEast3ActionPerformed(evt);
            }
        });

        AddNotSurveyedSouth3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddNotSurveyedSouth3ActionPerformed(evt);
            }
        });

        AddNotSurveyedWest3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddNotSurveyedWest3ActionPerformed(evt);
            }
        });

        jLabel83.setText("Issue Date:");

        view_permit_combo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                view_permit_comboItemStateChanged(evt);
            }
        });
        view_permit_combo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                view_permit_comboActionPerformed(evt);
            }
        });

        viewPermit_issueDate1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewPermit_issueDate1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel25Layout = new javax.swing.GroupLayout(jPanel25);
        jPanel25.setLayout(jPanel25Layout);
        jPanel25Layout.setHorizontalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel25Layout.createSequentialGroup()
                        .addGap(77, 77, 77)
                        .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel25Layout.createSequentialGroup()
                                .addComponent(jLabel82)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(AddNotSurveyedWest3))
                            .addGroup(jPanel25Layout.createSequentialGroup()
                                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel81)
                                    .addComponent(jLabel80)
                                    .addComponent(jLabel79))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(AddNotSurveyedEast3)
                                    .addComponent(AddNotSurveyedSouth3)
                                    .addComponent(AddNotSurveyedNorth3)))))
                    .addGroup(jPanel25Layout.createSequentialGroup()
                        .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel25Layout.createSequentialGroup()
                                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel78, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(21, 21, 21)
                                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(AddGNDNo3, javax.swing.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)
                                    .addComponent(AddGND3)))
                            .addGroup(jPanel25Layout.createSequentialGroup()
                                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel83, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(view_permit_combo, 0, 121, Short.MAX_VALUE)
                                    .addComponent(viewPermit_issueDate1))))
                        .addGap(2, 2, 2)))
                .addGap(18, 18, 18))
        );
        jPanel25Layout.setVerticalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(view_permit_combo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel83, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(viewPermit_issueDate1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(488, 488, 488)
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel79, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(AddNotSurveyedNorth3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel80, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(AddNotSurveyedEast3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel81, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(AddNotSurveyedSouth3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel82, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(AddNotSurveyedWest3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(AddGND3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(AddGNDNo3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel78))
                .addGap(13, 13, 13))
        );

        jPanel26.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel84.setText("Name:");

        jLabel85.setText("NIC:");

        jLabel86.setText("Address:");

        viewpermit_NOS_name_test1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewpermit_NOS_name_test1ActionPerformed(evt);
            }
        });

        viewpermit_NOS_nic_test1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewpermit_NOS_nic_test1ActionPerformed(evt);
            }
        });

        viewpermit_NOS_address_test1.setColumns(20);
        viewpermit_NOS_address_test1.setRows(5);
        jScrollPane8.setViewportView(viewpermit_NOS_address_test1);

        javax.swing.GroupLayout jPanel26Layout = new javax.swing.GroupLayout(jPanel26);
        jPanel26.setLayout(jPanel26Layout);
        jPanel26Layout.setHorizontalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel84)
                    .addComponent(jLabel85)
                    .addComponent(jLabel86))
                .addGap(37, 37, 37)
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(viewpermit_NOS_name_test1, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(viewpermit_NOS_nic_test1, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0))
        );
        jPanel26Layout.setVerticalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel84)
                    .addComponent(viewpermit_NOS_name_test1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel85)
                    .addComponent(viewpermit_NOS_nic_test1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel86)
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel28.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        viewpermit_nameText1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                viewpermit_nameText1KeyReleased(evt);
            }
        });

        viewpermit_addressText1.setColumns(20);
        viewpermit_addressText1.setRows(5);
        jScrollPane9.setViewportView(viewpermit_addressText1);

        viewpermit_no_of_children_test1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewpermit_no_of_children_test1ActionPerformed(evt);
            }
        });

        buttonGroup1.add(viewpermit_marriedStatusRButton1);
        viewpermit_marriedStatusRButton1.setText("Married");
        viewpermit_marriedStatusRButton1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                viewpermit_marriedStatusRButton1StateChanged(evt);
            }
        });

        buttonGroup1.add(viewpermit_singleStatusRButton1);
        viewpermit_singleStatusRButton1.setText("Single");
        viewpermit_singleStatusRButton1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                viewpermit_singleStatusRButton1StateChanged(evt);
            }
        });

        jLabel87.setText("NIC :");

        jLabel88.setText("Name:");

        jLabel89.setText("Phone Number:");

        jLabel90.setText("Address:");

        jLabel91.setText("Birthday:");

        jLabel92.setText("Status:");

        jLabel93.setText("Annual Income:");

        jLabel94.setText("No. of children:");

        viewPermit_clientNic1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                viewPermit_clientNic1KeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel28Layout = new javax.swing.GroupLayout(jPanel28);
        jPanel28.setLayout(jPanel28Layout);
        jPanel28Layout.setHorizontalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel28Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel28Layout.createSequentialGroup()
                        .addComponent(jLabel90, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(49, 49, 49)
                        .addComponent(jScrollPane9))
                    .addGroup(jPanel28Layout.createSequentialGroup()
                        .addComponent(jLabel91, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(66, 66, 66)
                        .addComponent(viewpermit_DOB_test1))
                    .addGroup(jPanel28Layout.createSequentialGroup()
                        .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel28Layout.createSequentialGroup()
                                    .addComponent(jLabel89, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(32, 32, 32)
                                    .addComponent(viewpermit_telephoneText1))
                                .addGroup(jPanel28Layout.createSequentialGroup()
                                    .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel93)
                                        .addComponent(jLabel94))
                                    .addGap(48, 48, 48)
                                    .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(viewpermit_annualIncome1)
                                        .addComponent(viewpermit_no_of_children_test1)))
                                .addGroup(jPanel28Layout.createSequentialGroup()
                                    .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel28Layout.createSequentialGroup()
                                            .addComponent(jLabel88, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(76, 76, 76))
                                        .addGroup(jPanel28Layout.createSequentialGroup()
                                            .addComponent(jLabel87, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addGap(31, 31, 31)))
                                    .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(viewPermit_clientNic1, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(viewpermit_nameText1))))
                            .addComponent(jLabel92)
                            .addGroup(jPanel28Layout.createSequentialGroup()
                                .addGap(123, 123, 123)
                                .addComponent(viewpermit_marriedStatusRButton1)
                                .addGap(18, 18, 18)
                                .addComponent(viewpermit_singleStatusRButton1)))
                        .addGap(0, 33, Short.MAX_VALUE)))
                .addGap(0, 4, Short.MAX_VALUE))
        );
        jPanel28Layout.setVerticalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel28Layout.createSequentialGroup()
                .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel87, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(viewPermit_clientNic1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel88)
                    .addComponent(viewpermit_nameText1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(viewpermit_telephoneText1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel89))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel90)
                    .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel91)
                    .addComponent(viewpermit_DOB_test1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(viewpermit_marriedStatusRButton1)
                    .addComponent(viewpermit_singleStatusRButton1)
                    .addComponent(jLabel92))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel93)
                    .addComponent(viewpermit_annualIncome1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel94)
                    .addComponent(viewpermit_no_of_children_test1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        jPanel29.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel95.setText("Plan Number");

        jLabel96.setText("Land Name");

        viewpermit_landName_test1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewpermit_landName_test1ActionPerformed(evt);
            }
        });

        jLabel97.setText("Division Number:");

        jLabel98.setText("Name:");

        viewpermit_division_name_test1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewpermit_division_name_test1ActionPerformed(evt);
            }
        });

        jLabel104.setText("Included Land");

        jLabel105.setText("Included Division");

        jLabel114.setText("Expected Extent");

        viewpermit_perches_test1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewpermit_perches_test1ActionPerformed(evt);
            }
        });

        jLabel115.setText("Roods");

        jLabel116.setText("Perches");

        jLabel117.setText("Acre / Hectare");

        jLabel118.setText("Lot No");

        viewPermit_DivisionumberTest1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewPermit_DivisionumberTest1ActionPerformed(evt);
            }
        });

        viewPermitLotNumberTest1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewPermitLotNumberTest1ActionPerformed(evt);
            }
        });

        jLabel106.setText("Lot Detail");

        javax.swing.GroupLayout jPanel29Layout = new javax.swing.GroupLayout(jPanel29);
        jPanel29.setLayout(jPanel29Layout);
        jPanel29Layout.setHorizontalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel29Layout.createSequentialGroup()
                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel106, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel104, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel105)
                    .addGroup(jPanel29Layout.createSequentialGroup()
                        .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(viewPermitPlanNumberTest1, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel29Layout.createSequentialGroup()
                                    .addComponent(jLabel97, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(viewPermit_DivisionumberTest1, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jLabel95, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel98, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel96))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(viewpermit_division_name_test1, javax.swing.GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE)
                            .addComponent(viewpermit_landName_test1)))
                    .addGroup(jPanel29Layout.createSequentialGroup()
                        .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(viewpermit_roods_test1, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel29Layout.createSequentialGroup()
                                .addComponent(jLabel114)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(viewpermit_acres_test1, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel117)
                            .addComponent(jLabel115)))
                    .addGroup(jPanel29Layout.createSequentialGroup()
                        .addGap(90, 90, 90)
                        .addComponent(viewpermit_perches_test1, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel116))
                    .addGroup(jPanel29Layout.createSequentialGroup()
                        .addComponent(jLabel118)
                        .addGap(59, 59, 59)
                        .addComponent(viewPermitLotNumberTest1, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(15, Short.MAX_VALUE))
        );
        jPanel29Layout.setVerticalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel29Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel105)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel97, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(viewpermit_division_name_test1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel98, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(viewPermit_DivisionumberTest1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel104)
                .addGap(12, 12, 12)
                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel95, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel96, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(viewpermit_landName_test1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(viewPermitPlanNumberTest1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel106)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel118)
                    .addComponent(viewPermitLotNumberTest1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel114)
                    .addComponent(viewpermit_acres_test1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel117))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(viewpermit_perches_test1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel116))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(viewpermit_roods_test1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel115))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel24Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel25, javax.swing.GroupLayout.DEFAULT_SIZE, 392, Short.MAX_VALUE)
                    .addComponent(jPanel29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 6, Short.MAX_VALUE))
        );
        jPanel24Layout.setVerticalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel24Layout.createSequentialGroup()
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel24Layout.createSequentialGroup()
                        .addComponent(jPanel28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel24Layout.createSequentialGroup()
                        .addComponent(jPanel25, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        jTabbedPane4.addTab("View Permit", jPanel24);

        jPanel2.setPreferredSize(new java.awt.Dimension(950, 600));

        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel6.setPreferredSize(new java.awt.Dimension(581, 581));

        jLabel4.setText("Grama Niladari Division:");

        AddGND.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddGNDActionPerformed(evt);
            }
        });

        jLabel5.setText("Permit No:");

        jLabel7.setText("Grama Niladari Division No:");

        AddGNDNo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddGNDNoActionPerformed(evt);
            }
        });

        jLabel27.setText("On The North By:");

        AddNotSurveyedNorth.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddNotSurveyedNorthActionPerformed(evt);
            }
        });

        jLabel28.setText("On The East  By:");

        jLabel29.setText("On The South By:");

        jLabel30.setText("On The West By:");

        AddNotSurveyedEast.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddNotSurveyedEastActionPerformed(evt);
            }
        });

        AddNotSurveyedSouth.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddNotSurveyedSouthActionPerformed(evt);
            }
        });

        AddNotSurveyedWest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddNotSurveyedWestActionPerformed(evt);
            }
        });

        jLabel3.setText("Issue Date:");

        addpermit_permit_numberTest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addpermit_permit_numberTestActionPerformed(evt);
            }
        });

        addpermit_permit_issue_dateChooser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addpermit_permit_issue_dateChooserActionPerformed(evt);
            }
        });

        generatePemitNumberButtun.setText("Generate");
        generatePemitNumberButtun.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                generatePemitNumberButtunMouseClicked(evt);
            }
        });
        generatePemitNumberButtun.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generatePemitNumberButtunActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(77, 77, 77)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel30)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(AddNotSurveyedWest))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel29)
                                    .addComponent(jLabel28)
                                    .addComponent(jLabel27))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(AddNotSurveyedEast)
                                    .addComponent(AddNotSurveyedSouth)
                                    .addComponent(AddNotSurveyedNorth)))))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(21, 21, 21)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(AddGNDNo, javax.swing.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)
                                    .addComponent(AddGND)))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addComponent(addpermit_permit_numberTest, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(generatePemitNumberButtun))
                                    .addComponent(addpermit_permit_issue_dateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addpermit_permit_numberTest, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(generatePemitNumberButtun))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addpermit_permit_issue_dateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(488, 488, 488)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(AddNotSurveyedNorth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(AddNotSurveyedEast, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(AddNotSurveyedSouth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(AddNotSurveyedWest, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(AddGND, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(AddGNDNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(13, 13, 13))
        );

        jPanel11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel14.setText("Plan Number");

        jLabel15.setText("Land Name");

        add_permit_landName_test.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                add_permit_landName_testActionPerformed(evt);
            }
        });
        add_permit_landName_test.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                add_permit_landName_testKeyReleased(evt);
            }
        });

        jLabel16.setText("Division Number:");

        jLabel17.setText("Name:");

        add_permit_division_name_test.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                add_permit_division_name_testActionPerformed(evt);
            }
        });
        add_permit_division_name_test.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                add_permit_division_name_testKeyReleased(evt);
            }
        });

        jLabel1.setText("Included Land");

        jLabel2.setText("Included Division");

        add_permit_lot_number_Combo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                add_permit_lot_number_ComboItemStateChanged(evt);
            }
        });
        add_permit_lot_number_Combo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                add_permit_lot_number_ComboActionPerformed(evt);
            }
        });

        jLabel100.setText("Expected Extent");

        add_permit_acres_test.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                add_permit_acres_testKeyReleased(evt);
            }
        });

        add_permit_perches_test.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                add_permit_perches_testActionPerformed(evt);
            }
        });

        jLabel103.setText("Roods");

        jLabel102.setText("Perches");

        jLabel101.setText("Acre / Hectare");

        jLabel99.setText("Lot No");

        add_permit_division_number_combo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                add_permit_division_number_comboItemStateChanged(evt);
            }
        });
        add_permit_division_number_combo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                add_permit_division_number_comboActionPerformed(evt);
            }
        });

        add_permit_planNumber_combo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                add_permit_planNumber_comboItemStateChanged(evt);
            }
        });

        jLabel76.setText("Lot Detail");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addComponent(jLabel99)
                .addGap(79, 79, 79)
                .addComponent(add_permit_lot_number_Combo, 0, 18, Short.MAX_VALUE)
                .addGap(247, 247, 247))
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel11Layout.createSequentialGroup()
                                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(add_permit_division_number_combo, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(add_permit_planNumber_combo, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, 56, Short.MAX_VALUE)
                            .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(add_permit_division_name_test, javax.swing.GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE)
                            .addComponent(add_permit_landName_test)))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(90, 90, 90)
                        .addComponent(add_permit_roods_test, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel103, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addComponent(jLabel100)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(add_permit_acres_test, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(add_permit_perches_test, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel102)
                            .addComponent(jLabel101)))
                    .addComponent(jLabel76, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(add_permit_division_name_test, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(add_permit_division_number_combo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1)
                .addGap(12, 12, 12)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(add_permit_landName_test, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(add_permit_planNumber_combo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel76)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel99)
                            .addComponent(add_permit_lot_number_Combo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel100)
                            .addComponent(add_permit_acres_test, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(add_permit_perches_test, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(jLabel101)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel102)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(add_permit_roods_test, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel103, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jPanel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        add_permit_nic_combo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                add_permit_nic_comboItemStateChanged(evt);
            }
        });

        add_permit_nameText.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                add_permit_nameTextKeyReleased(evt);
            }
        });

        add_permit_addressText.setColumns(20);
        add_permit_addressText.setRows(5);
        jScrollPane3.setViewportView(add_permit_addressText);

        add_permit_no_of_children_test.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                add_permit_no_of_children_testActionPerformed(evt);
            }
        });

        buttonGroup1.add(add_permit_marriedStatusRButton);
        add_permit_marriedStatusRButton.setText("Married");

        buttonGroup1.add(add_permit_singleStatusRButton);
        add_permit_singleStatusRButton.setText("Single");

        jLabel32.setText("NIC :");

        jLabel18.setText("Name:");

        jLabel33.setText("Phone Number:");

        jLabel34.setText("Address:");

        jLabel35.setText("Birthday:");

        jLabel36.setText("Status:");

        jLabel39.setText("Annual Income:");

        jLabel37.setText("No. of children:");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(49, 49, 49)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(add_permit_nameText)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 241, Short.MAX_VALUE)
                            .addComponent(add_permit_DOB_test)))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel39)
                            .addComponent(jLabel37))
                        .addGap(48, 48, 48)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(add_permit_annualIncome)
                            .addComponent(add_permit_no_of_children_test)))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addComponent(add_permit_telephoneText))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(32, 32, 32)
                                .addComponent(add_permit_nic_combo, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(jLabel36)
                                .addGap(88, 88, 88)
                                .addComponent(add_permit_marriedStatusRButton)
                                .addGap(18, 18, 18)
                                .addComponent(add_permit_singleStatusRButton)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(0, 5, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(add_permit_nic_combo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(add_permit_nameText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel33)
                    .addComponent(add_permit_telephoneText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel34)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel35)
                    .addComponent(add_permit_DOB_test, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(add_permit_marriedStatusRButton)
                    .addComponent(jLabel36)
                    .addComponent(add_permit_singleStatusRButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel39)
                    .addComponent(add_permit_annualIncome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel37)
                    .addComponent(add_permit_no_of_children_test, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel10.setText("Name:");

        jLabel11.setText("NIC:");

        jLabel12.setText("Address:");

        addpermit_NOS_name_test.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                addpermit_NOS_name_testKeyReleased(evt);
            }
        });

        addpermit_NOS_nic_test.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addpermit_NOS_nic_testActionPerformed(evt);
            }
        });
        addpermit_NOS_nic_test.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                addpermit_NOS_nic_testKeyReleased(evt);
            }
        });

        addpermit_address.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                addpermit_addressKeyReleased(evt);
            }
        });

        addpermit_NOS_address_test.setColumns(20);
        addpermit_NOS_address_test.setRows(5);
        addpermit_NOS_address_test.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                addpermit_NOS_address_testKeyReleased(evt);
            }
        });
        addpermit_address.setViewportView(addpermit_NOS_address_test);

        nicInvalidLabel.setForeground(new java.awt.Color(255, 0, 0));
        nicInvalidLabel.setText("NIC is invalid");

        nameinvalidlabel.setForeground(new java.awt.Color(255, 0, 0));
        nameinvalidlabel.setText("Name is invalid");

        addressinvalidlabel.setForeground(new java.awt.Color(255, 0, 0));
        addressinvalidlabel.setText("Invalid");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(jLabel11))
                        .addGap(49, 49, 49)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(addpermit_NOS_name_test, javax.swing.GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)
                            .addComponent(addpermit_NOS_nic_test))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(nicInvalidLabel, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(nameinvalidlabel, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addGap(37, 37, 37)
                        .addComponent(addpermit_address, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(addressinvalidlabel)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(addpermit_NOS_name_test, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nameinvalidlabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(addpermit_NOS_nic_test, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nicInvalidLabel))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12)
                            .addComponent(addpermit_address, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(addressinvalidlabel)))
                .addContainerGap(43, Short.MAX_VALUE))
        );

        addpermit_add_permit_button.setText("Add Permit");
        addpermit_add_permit_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addpermit_add_permit_buttonActionPerformed(evt);
            }
        });
        addpermit_add_permit_button.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                addpermit_add_permit_buttonKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(addpermit_add_permit_button)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, 377, Short.MAX_VALUE)
                            .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(addpermit_add_permit_button)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane4.addTab("Add Permit", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 781, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 346, Short.MAX_VALUE)
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 345, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 451, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 15, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 183, Short.MAX_VALUE)
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 183, Short.MAX_VALUE)))
        );

        jTabbedPane4.getAccessibleContext().setAccessibleName("Add Permit");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void viewPermitLotNumberTest1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewPermitLotNumberTest1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_viewPermitLotNumberTest1ActionPerformed

    private void viewpermit_perches_test1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewpermit_perches_test1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_viewpermit_perches_test1ActionPerformed

    private void viewpermit_division_name_test1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewpermit_division_name_test1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_viewpermit_division_name_test1ActionPerformed

    private void viewpermit_landName_test1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewpermit_landName_test1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_viewpermit_landName_test1ActionPerformed

    private void viewPermit_clientNic1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_viewPermit_clientNic1KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_viewPermit_clientNic1KeyReleased

    private void viewpermit_singleStatusRButton1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_viewpermit_singleStatusRButton1StateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_viewpermit_singleStatusRButton1StateChanged

    private void viewpermit_marriedStatusRButton1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_viewpermit_marriedStatusRButton1StateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_viewpermit_marriedStatusRButton1StateChanged

    private void viewpermit_no_of_children_test1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewpermit_no_of_children_test1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_viewpermit_no_of_children_test1ActionPerformed

    private void viewpermit_nameText1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_viewpermit_nameText1KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_viewpermit_nameText1KeyReleased

    private void viewpermit_NOS_nic_test1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewpermit_NOS_nic_test1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_viewpermit_NOS_nic_test1ActionPerformed

    private void viewpermit_NOS_name_test1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewpermit_NOS_name_test1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_viewpermit_NOS_name_test1ActionPerformed

    private void viewPermit_issueDate1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewPermit_issueDate1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_viewPermit_issueDate1ActionPerformed

    private void view_permit_comboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_view_permit_comboActionPerformed
        SetViewPermit(String.valueOf(view_permit_combo.getSelectedItem()));
    }//GEN-LAST:event_view_permit_comboActionPerformed

    private void view_permit_comboItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_view_permit_comboItemStateChanged
        SetViewPermit(String.valueOf(view_permit_combo.getSelectedItem()));
    }//GEN-LAST:event_view_permit_comboItemStateChanged

    private void AddNotSurveyedWest3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddNotSurveyedWest3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AddNotSurveyedWest3ActionPerformed

    private void AddNotSurveyedSouth3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddNotSurveyedSouth3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AddNotSurveyedSouth3ActionPerformed

    private void AddNotSurveyedEast3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddNotSurveyedEast3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AddNotSurveyedEast3ActionPerformed

    private void AddNotSurveyedNorth3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddNotSurveyedNorth3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AddNotSurveyedNorth3ActionPerformed

    private void AddGNDNo3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddGNDNo3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AddGNDNo3ActionPerformed

    private void AddGND3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddGND3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AddGND3ActionPerformed

    private void addpermit_add_permit_buttonKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_addpermit_add_permit_buttonKeyReleased
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_UP) {
            addpermit_NOS_address_test.requestFocus();
        }
    }//GEN-LAST:event_addpermit_add_permit_buttonKeyReleased

    private void addpermit_add_permit_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addpermit_add_permit_buttonActionPerformed
        if (!PatternChecker.checkStringdirect(addpermit_NOS_name_test.getText())) {
            nameinvalidlabel.setVisible(true);
        } else if (!PatternChecker.checkNICdirect(addpermit_NOS_nic_test.getText())) {
            nicInvalidLabel.setVisible(true);
        }
        else if(addpermit_NOS_address_test.getText().trim().length()==0){
            addressinvalidlabel.setVisible(true);
        }
        else {
            try {
                NominatedSuccessor nominatedSuccessor = new NominatedSuccessor(addpermit_NOS_nic_test.getText(), addpermit_NOS_name_test.getText(), addpermit_NOS_address_test.getText());
                Client searchClient = ClientController.searchClient(String.valueOf(add_permit_nic_combo.getSelectedItem()));
                Lot searchLot = LotController.searchLot(String.valueOf(add_permit_lot_number_Combo.getSelectedItem()));
                Date date = addpermit_permit_issue_dateChooser.getDate();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-dd");
                String issueDate = simpleDateFormat.format(date);
                Permit permit = new Permit(addpermit_permit_numberTest.getText(), issueDate, searchLot, searchClient, nominatedSuccessor);
                boolean addNewPermit = PermitController.addNewPermit(permit);
                if (addNewPermit) {
                    JOptionPane.showMessageDialog(this, "permit Added successfully");
                    add_permit_division_name_test.setText(null);
                    add_permit_landName_test.setText(null);
                    add_permit_perches_test.setText(null);
                    add_permit_roods_test.setText(null);
                    add_permit_acres_test.setText(null);
                    add_permit_nameText.setText(null);
                    add_permit_telephoneText.setText(null);
                    add_permit_addressText.setText(null);
                    add_permit_DOB_test.setText(null);
                    add_permit_annualIncome.setText(null);
                    add_permit_no_of_children_test.setText(null);
                    addpermit_permit_numberTest.setText(null);
                    addpermit_NOS_name_test.setText(null);
                    addpermit_NOS_nic_test.setText(null);
                    add_permit_division_number_combo.setSelectedItem(null);
                    add_permit_planNumber_combo.setSelectedItem(null);
                    add_permit_lot_number_Combo.setSelectedItem(null);
                    add_permit_marriedStatusRButton.setSelected(false);
                    add_permit_singleStatusRButton.setSelected(false);
                    add_permit_nic_combo.removeAll();

                } else {
                    JOptionPane.showMessageDialog(this, "permit does not added successfully");
                }
            } catch (ClassNotFoundException | SQLException | RemoteException ex) {
                Logger.getLogger(PermitForm.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_addpermit_add_permit_buttonActionPerformed

    private void addpermit_addressKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_addpermit_addressKeyReleased
        // TODO add your handling code here:
        EnableGererateButton();
    }//GEN-LAST:event_addpermit_addressKeyReleased

    private void addpermit_NOS_address_testKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_addpermit_NOS_address_testKeyReleased
        // TODO add your handling code here:
        EnableGererateButton();
        addressinvalidlabel.setVisible(false);
        if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
            addpermit_add_permit_button.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_UP) {
            addpermit_NOS_nic_test.requestFocus();
        }
    }//GEN-LAST:event_addpermit_NOS_address_testKeyReleased

    private void addpermit_NOS_nic_testKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_addpermit_NOS_nic_testKeyReleased
        // TODO add your handling code here:
        EnableGererateButton();
        nicInvalidLabel.setVisible(false);
        String newtext = PatternChecker.checkNIC(addpermit_NOS_nic_test.getText());
        addpermit_NOS_nic_test.setText(newtext);
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (PatternChecker.checkNICdirect(newtext)) {
                addpermit_NOS_address_test.requestFocus();
            } else {
                nicInvalidLabel.setVisible(true);
            }
        } else if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
            addpermit_NOS_address_test.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_UP) {
            addpermit_NOS_name_test.requestFocus();
        }
    }//GEN-LAST:event_addpermit_NOS_nic_testKeyReleased

    private void addpermit_NOS_nic_testActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addpermit_NOS_nic_testActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_addpermit_NOS_nic_testActionPerformed

    private void addpermit_NOS_name_testKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_addpermit_NOS_name_testKeyReleased
        // TODO add your handling code here:
        EnableGererateButton();
        nameinvalidlabel.setVisible(false);
        String newtext = PatternChecker.checkstring(addpermit_NOS_name_test.getText());
        addpermit_NOS_name_test.setText(newtext);
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (PatternChecker.checkStringdirect(newtext)) {
                addpermit_NOS_nic_test.requestFocus();
            } else {
                nameinvalidlabel.setVisible(true);
            }
        } else if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
            addpermit_NOS_nic_test.requestFocus();
        }
    }//GEN-LAST:event_addpermit_NOS_name_testKeyReleased

    private void add_permit_no_of_children_testActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_add_permit_no_of_children_testActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_add_permit_no_of_children_testActionPerformed

    private void add_permit_nameTextKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_add_permit_nameTextKeyReleased
        // TODO add your handling code here:
        EnableGererateButton();
    }//GEN-LAST:event_add_permit_nameTextKeyReleased

    private void add_permit_nic_comboItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_add_permit_nic_comboItemStateChanged
        try {
            Client searchClient = ClientController.searchClient((String) add_permit_nic_combo.getSelectedItem());
            if (searchClient != null) {
                add_permit_nameText.setText(searchClient.getClientName());
                add_permit_DOB_test.setText(searchClient.getBirthday());
                add_permit_no_of_children_test.setText(String.valueOf(searchClient.getNumberOfMarriedSons() + searchClient.getNumberOfUnmarriedSons()));
                add_permit_telephoneText.setText(searchClient.getTelephone());
                if (searchClient.isMarried() == 0) {
                    add_permit_singleStatusRButton.setSelected(true);
                } else {
                    add_permit_marriedStatusRButton.setSelected(true);
                }
                add_permit_annualIncome.setText(String.valueOf(searchClient.getAnnualIncome()));
                add_permit_addressText.setText(searchClient.getAddress());
            }
        } catch (ClassNotFoundException | SQLException | RemoteException ex) {
            Logger.getLogger(ApplicantForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_add_permit_nic_comboItemStateChanged

    private void add_permit_planNumber_comboItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_add_permit_planNumber_comboItemStateChanged
        try {
            Land searchLand = LandController.getAvailableLotOfLand(String.valueOf(add_permit_planNumber_combo.getSelectedItem()));
            if (searchLand != null) {
                add_permit_landName_test.setText(searchLand.getLandName());
                ArrayList<Lot> lotList = searchLand.getLotList();
                ArrayList<Object> list = new ArrayList();
                for (Lot lot : lotList) {
                    list.add(lot.getLotNumber());
                }
                GUIitemsValidator.addItemToCombo(list, add_permit_lot_number_Combo);

            }
        } catch (ClassNotFoundException | SQLException | RemoteException ex) {
            Logger.getLogger(GramaNiladhariForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_add_permit_planNumber_comboItemStateChanged

    private void add_permit_division_number_comboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_add_permit_division_number_comboActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_add_permit_division_number_comboActionPerformed

    private void add_permit_division_number_comboItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_add_permit_division_number_comboItemStateChanged
        try {
            GramaNiladariDivision searchGND = GramaNiladariDivisionController.searchGND((String) add_permit_division_number_combo.getSelectedItem());
            if (searchGND != null) {
                add_permit_division_name_test.setText(searchGND.getDivisionName());
                ArrayList<Land> landsOfDivision = LandController.getLandsOfDivision(searchGND.getDivisionNumber());
                ArrayList<Object> list = new ArrayList();
                for (Land land : landsOfDivision) {
                    list.add(land.getPlanNumber());
                }
                GUIitemsValidator.addItemToCombo(list, add_permit_planNumber_combo);

            }
        } catch (ClassNotFoundException | SQLException | RemoteException ex) {
            Logger.getLogger(GramaNiladhariForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_add_permit_division_number_comboItemStateChanged

    private void add_permit_perches_testActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_add_permit_perches_testActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_add_permit_perches_testActionPerformed

    private void add_permit_acres_testKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_add_permit_acres_testKeyReleased
        // TODO add your handling code here:
        EnableGererateButton();
    }//GEN-LAST:event_add_permit_acres_testKeyReleased

    private void add_permit_lot_number_ComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_add_permit_lot_number_ComboActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_add_permit_lot_number_ComboActionPerformed

    private void add_permit_lot_number_ComboItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_add_permit_lot_number_ComboItemStateChanged
        try {
            Lot searchLot = LotController.searchLot(String.valueOf(add_permit_lot_number_Combo.getSelectedItem()));
            if (searchLot != null) {
                add_permit_acres_test.setText(String.valueOf(searchLot.getNumberOfAcres()));
                add_permit_perches_test.setText(String.valueOf(searchLot.getNumberOfPerches()));
                add_permit_roods_test.setText(String.valueOf(searchLot.getNumberofRoods()));
            }

        } catch (ClassNotFoundException | SQLException | RemoteException ex) {
            Logger.getLogger(PermitForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_add_permit_lot_number_ComboItemStateChanged

    private void add_permit_division_name_testKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_add_permit_division_name_testKeyReleased
        // TODO add your handling code here:
        EnableGererateButton();
    }//GEN-LAST:event_add_permit_division_name_testKeyReleased

    private void add_permit_division_name_testActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_add_permit_division_name_testActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_add_permit_division_name_testActionPerformed

    private void add_permit_landName_testKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_add_permit_landName_testKeyReleased
        // TODO add your handling code here:
        EnableGererateButton();
    }//GEN-LAST:event_add_permit_landName_testKeyReleased

    private void add_permit_landName_testActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_add_permit_landName_testActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_add_permit_landName_testActionPerformed

    private void generatePemitNumberButtunActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generatePemitNumberButtunActionPerformed
        try {
            String permitNumber = String.valueOf(add_permit_division_number_combo.getSelectedItem()) + "/ENC/";
            int permitCountOfDivision = PermitController.getPermitCountOfDivision(String.valueOf(add_permit_division_number_combo.getSelectedItem()));
            if (permitCountOfDivision < 10) {
                permitNumber += "0" + String.valueOf(permitCountOfDivision + 1);
            } else {
                permitNumber += String.valueOf(permitCountOfDivision + 1);
            }
            addpermit_permit_numberTest.setText(permitNumber);
        } catch (ClassNotFoundException | SQLException | RemoteException ex) {
            Logger.getLogger(PermitForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_generatePemitNumberButtunActionPerformed

    private void generatePemitNumberButtunMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_generatePemitNumberButtunMouseClicked
        // TODO add your handling code here:
        addpermit_add_permit_button.setEnabled(true);
    }//GEN-LAST:event_generatePemitNumberButtunMouseClicked

    private void addpermit_permit_issue_dateChooserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addpermit_permit_issue_dateChooserActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_addpermit_permit_issue_dateChooserActionPerformed

    private void addpermit_permit_numberTestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addpermit_permit_numberTestActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_addpermit_permit_numberTestActionPerformed

    private void AddNotSurveyedWestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddNotSurveyedWestActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AddNotSurveyedWestActionPerformed

    private void AddNotSurveyedSouthActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddNotSurveyedSouthActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AddNotSurveyedSouthActionPerformed

    private void AddNotSurveyedEastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddNotSurveyedEastActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AddNotSurveyedEastActionPerformed

    private void AddNotSurveyedNorthActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddNotSurveyedNorthActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AddNotSurveyedNorthActionPerformed

    private void AddGNDNoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddGNDNoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AddGNDNoActionPerformed

    private void AddGNDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddGNDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AddGNDActionPerformed

    private void cancelPermitLotNumberTestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelPermitLotNumberTestActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cancelPermitLotNumberTestActionPerformed

    private void cancelpermit_perches_testActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelpermit_perches_testActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cancelpermit_perches_testActionPerformed

    private void cancelpermit_division_name_testActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelpermit_division_name_testActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cancelpermit_division_name_testActionPerformed

    private void cancelpermit_landName_testActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelpermit_landName_testActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cancelpermit_landName_testActionPerformed

    private void cancelPermit_clientNicKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cancelPermit_clientNicKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_cancelPermit_clientNicKeyReleased

    private void cancelpermit_singleStatusRButtonStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_cancelpermit_singleStatusRButtonStateChanged
        // TODO add your handling code here:
        cancelpermit_cancel_permit_button.setEnabled(true);
    }//GEN-LAST:event_cancelpermit_singleStatusRButtonStateChanged

    private void cancelpermit_marriedStatusRButtonStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_cancelpermit_marriedStatusRButtonStateChanged
        // TODO add your handling code here:
        cancelpermit_cancel_permit_button.setEnabled(true);
    }//GEN-LAST:event_cancelpermit_marriedStatusRButtonStateChanged

    private void cancelpermit_no_of_children_testActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelpermit_no_of_children_testActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cancelpermit_no_of_children_testActionPerformed

    private void cancelpermit_nameTextKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cancelpermit_nameTextKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_cancelpermit_nameTextKeyReleased

    private void cancelpermit_cancel_permit_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelpermit_cancel_permit_buttonActionPerformed
        int showConfirmDialog = JOptionPane.showConfirmDialog(this, "Do you want to cancel permit");
        if (showConfirmDialog == 0) {
            try {
                NominatedSuccessor nominatedSuccessor = new NominatedSuccessor(cancelpermit_NOS_nic_test.getText(), cancelpermit_NOS_name_test.getText(), cancelpermit_NOS_address_test.getText());
                Client searchClient = ClientController.searchClient(cancelPermit_clientNic.getText());
                Lot searchLot = LotController.searchLot(cancelPermitLotNumberTest.getText());
                Permit permit = new Permit(String.valueOf(cancelpermit_permit_number_combo.getSelectedItem()), cancelPermit_issueDate.getText(), searchLot, searchClient, nominatedSuccessor);
                boolean cancelPermit = PermitController.cancelPermit(permit);
                if (cancelPermit) {
                    JOptionPane.showMessageDialog(this, "permit cancel successfully");
                    cancelPermit_issueDate.setText(null);
                    cancelPermit_DivisionumberTest.setText(null);
                    cancelpermit_division_name_test.setText(null);
                    cancelPermitPlanNumberTest.setText(null);
                    cancelpermit_landName_test.setText(null);
                    cancelPermitLotNumberTest.setText(null);
                    cancelpermit_acres_test.setText(null);
                    cancelpermit_perches_test.setText(null);
                    cancelpermit_roods_test.setText(null);
                    cancelPermit_clientNic.setText(null);
                    cancelpermit_nameText.setText(null);
                    cancelpermit_telephoneText.setText(null);
                    cancelpermit_DOB_test.setText(null);
                    cancelpermit_addressText.setText(null);
                    cancelpermit_annualIncome.setText(null);
                    cancelpermit_no_of_children_test.setText(null);
                    cancelpermit_NOS_name_test.setText(null);
                    cancelpermit_NOS_nic_test.setText(null);
                    cancelpermit_NOS_address_test.setText(null);
                    cancelpermit_permit_number_combo.setSelectedItem(null);
                    cancelpermit_marriedStatusRButton.setSelected(false);
                    cancelpermit_singleStatusRButton.setSelected(false);
                } else {
                    JOptionPane.showMessageDialog(this, "permit does not cancel successfully");
                }
            } catch (ClassNotFoundException | SQLException | RemoteException ex) {
                Logger.getLogger(PermitForm.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_cancelpermit_cancel_permit_buttonActionPerformed

    private void cancelpermit_NOS_nic_testActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelpermit_NOS_nic_testActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cancelpermit_NOS_nic_testActionPerformed

    private void cancelpermit_NOS_name_testActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelpermit_NOS_name_testActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cancelpermit_NOS_name_testActionPerformed

    private void cancelPermit_issueDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelPermit_issueDateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cancelPermit_issueDateActionPerformed

    private void cancelpermit_permit_number_comboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelpermit_permit_number_comboActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cancelpermit_permit_number_comboActionPerformed

    private void cancelpermit_permit_number_comboItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cancelpermit_permit_number_comboItemStateChanged
        try {
            Permit searchPermit = PermitController.searchPermit(String.valueOf(cancelpermit_permit_number_combo.getSelectedItem()));
            if (searchPermit != null) {
                Client client = searchPermit.getClient();
                NominatedSuccessor nominatedSuccessor = searchPermit.getNominatedSuccessor();
                Lot lot = searchPermit.getLot();
                String permitIssueDate = searchPermit.getPermitIssueDate();
                cancelpermit_nameText.setText(client.getClientName());
                cancelpermit_telephoneText.setText(client.getTelephone());
                cancelPermit_clientNic.setText(client.getNIC());
                cancelpermit_DOB_test.setText(client.getBirthday());
                cancelpermit_addressText.setText(client.getAddress());
                cancelpermit_annualIncome.setText(String.valueOf(client.getAnnualIncome()));
                cancelpermit_no_of_children_test.setText(String.valueOf(client.getNumberOfMarriedSons() + client.getNumberOfUnmarriedSons()));
                if (client.isMarried() == 1) {
                    cancelpermit_marriedStatusRButton.setSelected(true);
                } else {
                    cancelpermit_singleStatusRButton.setSelected(true);
                }
                int year = Integer.parseInt(permitIssueDate.substring(0, 3));
                int month = Integer.parseInt(permitIssueDate.substring(5, 6));
                int date = Integer.parseInt(permitIssueDate.substring(8, 9));
                Date issueDate = new Date(year, month, date);

                cancelPermit_issueDate.setText(permitIssueDate);
                cancelpermit_NOS_address_test.setText(nominatedSuccessor.getAddress());
                cancelpermit_NOS_name_test.setText(nominatedSuccessor.getAddress());
                cancelpermit_NOS_nic_test.setText(nominatedSuccessor.getNIC_S());

                cancelPermitLotNumberTest.setText(lot.getLotNumber());
                cancelpermit_acres_test.setText(String.valueOf(lot.getNumberOfAcres()));
                cancelpermit_perches_test.setText(String.valueOf(lot.getNumberOfPerches()));
                cancelpermit_roods_test.setText(String.valueOf(lot.getNumberofRoods()));

                Land land = lot.getLand();
                cancelPermitPlanNumberTest.setText(land.getPlanNumber());
                cancelpermit_landName_test.setText(land.getLandName());
                cancelPermit_DivisionumberTest.setText(land.getDivisionNumber());
                GramaNiladariDivision searchGND = GramaNiladariDivisionController.searchGND(land.getDivisionNumber());
                cancelpermit_division_name_test.setText(searchGND.getDivisionName());

            }
        } catch (ClassNotFoundException | SQLException | RemoteException ex) {
            Logger.getLogger(PermitForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_cancelpermit_permit_number_comboItemStateChanged

    public void SetViewPermit(String permitno){
        try {
            Permit searchPermit = PermitController.searchPermit(permitno);
            if (searchPermit != null) {
                view_permit_combo.setSelectedItem(permitno);
                Client client = searchPermit.getClient();
                NominatedSuccessor nominatedSuccessor = searchPermit.getNominatedSuccessor();
                Lot lot = searchPermit.getLot();
                String permitIssueDate = searchPermit.getPermitIssueDate();
                viewpermit_nameText1.setText(client.getClientName());
                viewpermit_telephoneText1.setText(client.getTelephone());
                viewPermit_clientNic1.setText(client.getNIC());
                viewpermit_DOB_test1.setText(client.getBirthday());
                viewpermit_addressText1.setText(client.getAddress());
                viewpermit_annualIncome1.setText(String.valueOf(client.getAnnualIncome()));
                viewpermit_no_of_children_test1.setText(String.valueOf(client.getNumberOfMarriedSons() + client.getNumberOfUnmarriedSons()));
                if (client.isMarried() == 1) {
                    viewpermit_marriedStatusRButton1.setSelected(true);
                } else {
                    viewpermit_singleStatusRButton1.setSelected(true);
                }
                int year = Integer.parseInt(permitIssueDate.substring(0, 3));
                int month = Integer.parseInt(permitIssueDate.substring(5, 6));
                int date = Integer.parseInt(permitIssueDate.substring(8, 9));
                Date issueDate = new Date(year, month, date);

                viewPermit_issueDate1.setText(permitIssueDate);
                viewpermit_NOS_address_test1.setText(nominatedSuccessor.getAddress());
                viewpermit_NOS_name_test1.setText(nominatedSuccessor.getAddress());
                viewpermit_NOS_nic_test1.setText(nominatedSuccessor.getNIC_S());

                viewPermitLotNumberTest1.setText(lot.getLotNumber());
                viewpermit_acres_test1.setText(String.valueOf(lot.getNumberOfAcres()));
                viewpermit_perches_test1.setText(String.valueOf(lot.getNumberOfPerches()));
                viewpermit_roods_test1.setText(String.valueOf(lot.getNumberofRoods()));

                Land land = lot.getLand();
                viewPermitPlanNumberTest1.setText(land.getPlanNumber());
                viewpermit_landName_test1.setText(land.getLandName());
                viewPermit_DivisionumberTest1.setText(land.getDivisionNumber());
                GramaNiladariDivision searchGND = GramaNiladariDivisionController.searchGND(land.getDivisionNumber());
                viewpermit_division_name_test1.setText(searchGND.getDivisionName());

            }
        } catch (ClassNotFoundException | SQLException | RemoteException ex) {
            Logger.getLogger(PermitForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void AddNotSurveyedWest2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddNotSurveyedWest2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AddNotSurveyedWest2ActionPerformed

    private void AddNotSurveyedSouth2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddNotSurveyedSouth2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AddNotSurveyedSouth2ActionPerformed

    private void AddNotSurveyedEast2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddNotSurveyedEast2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AddNotSurveyedEast2ActionPerformed

    private void AddNotSurveyedNorth2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddNotSurveyedNorth2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AddNotSurveyedNorth2ActionPerformed

    private void AddGNDNo2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddGNDNo2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AddGNDNo2ActionPerformed

    private void AddGND2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddGND2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AddGND2ActionPerformed

    private void viewPermit_DivisionumberTest1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewPermit_DivisionumberTest1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_viewPermit_DivisionumberTest1ActionPerformed

    private void cancelpermit_DOB_testActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelpermit_DOB_testActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cancelpermit_DOB_testActionPerformed

    public void EnableGererateButton() {
        if (add_permit_division_name_test.getText().trim().length() != 0 && add_permit_nameText.getText().trim().length() != 0 && add_permit_landName_test.getText().trim().length() != 0 && add_permit_nameText.getText().trim().length() != 0
                && add_permit_acres_test.getText().trim().length() != 0 && addpermit_NOS_name_test.getText().trim().length() != 0 && addpermit_NOS_nic_test.getText().trim().length() != 0 && addpermit_NOS_address_test.getText().trim().length() != 0) {
            generatePemitNumberButtun.setEnabled(true);
        }
        
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField AddGND;
    private javax.swing.JTextField AddGND2;
    private javax.swing.JTextField AddGND3;
    private javax.swing.JTextField AddGNDNo;
    private javax.swing.JTextField AddGNDNo2;
    private javax.swing.JTextField AddGNDNo3;
    private javax.swing.JTextField AddNotSurveyedEast;
    private javax.swing.JTextField AddNotSurveyedEast2;
    private javax.swing.JTextField AddNotSurveyedEast3;
    private javax.swing.JTextField AddNotSurveyedNorth;
    private javax.swing.JTextField AddNotSurveyedNorth2;
    private javax.swing.JTextField AddNotSurveyedNorth3;
    private javax.swing.JTextField AddNotSurveyedSouth;
    private javax.swing.JTextField AddNotSurveyedSouth2;
    private javax.swing.JTextField AddNotSurveyedSouth3;
    private javax.swing.JTextField AddNotSurveyedWest;
    private javax.swing.JTextField AddNotSurveyedWest2;
    private javax.swing.JTextField AddNotSurveyedWest3;
    private javax.swing.JTextField add_permit_DOB_test;
    private javax.swing.JTextField add_permit_acres_test;
    private javax.swing.JTextArea add_permit_addressText;
    private javax.swing.JTextField add_permit_annualIncome;
    private javax.swing.JTextField add_permit_division_name_test;
    private javax.swing.JComboBox add_permit_division_number_combo;
    private javax.swing.JTextField add_permit_landName_test;
    private javax.swing.JComboBox add_permit_lot_number_Combo;
    private javax.swing.JRadioButton add_permit_marriedStatusRButton;
    private javax.swing.JTextField add_permit_nameText;
    private javax.swing.JComboBox add_permit_nic_combo;
    private javax.swing.JTextField add_permit_no_of_children_test;
    private javax.swing.JTextField add_permit_perches_test;
    private javax.swing.JComboBox add_permit_planNumber_combo;
    private javax.swing.JTextField add_permit_roods_test;
    private javax.swing.JRadioButton add_permit_singleStatusRButton;
    private javax.swing.JTextField add_permit_telephoneText;
    private javax.swing.JTextArea addpermit_NOS_address_test;
    private javax.swing.JTextField addpermit_NOS_name_test;
    private javax.swing.JTextField addpermit_NOS_nic_test;
    private javax.swing.JButton addpermit_add_permit_button;
    private javax.swing.JScrollPane addpermit_address;
    private org.freixas.jcalendar.JCalendarCombo addpermit_permit_issue_dateChooser;
    private javax.swing.JTextField addpermit_permit_numberTest;
    private javax.swing.JLabel addressinvalidlabel;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JTextField cancelPermitLotNumberTest;
    private javax.swing.JTextField cancelPermitPlanNumberTest;
    private javax.swing.JTextField cancelPermit_DivisionumberTest;
    private javax.swing.JTextField cancelPermit_clientNic;
    private javax.swing.JTextField cancelPermit_issueDate;
    private javax.swing.JTextField cancelpermit_DOB_test;
    private javax.swing.JTextArea cancelpermit_NOS_address_test;
    private javax.swing.JTextField cancelpermit_NOS_name_test;
    private javax.swing.JTextField cancelpermit_NOS_nic_test;
    private javax.swing.JTextField cancelpermit_acres_test;
    private javax.swing.JTextArea cancelpermit_addressText;
    private javax.swing.JTextField cancelpermit_annualIncome;
    private javax.swing.JButton cancelpermit_cancel_permit_button;
    private javax.swing.JTextField cancelpermit_division_name_test;
    private javax.swing.JTextField cancelpermit_landName_test;
    private javax.swing.JRadioButton cancelpermit_marriedStatusRButton;
    private javax.swing.JTextField cancelpermit_nameText;
    private javax.swing.JTextField cancelpermit_no_of_children_test;
    private javax.swing.JTextField cancelpermit_perches_test;
    private javax.swing.JComboBox cancelpermit_permit_number_combo;
    private javax.swing.JTextField cancelpermit_roods_test;
    private javax.swing.JRadioButton cancelpermit_singleStatusRButton;
    private javax.swing.JTextField cancelpermit_telephoneText;
    private javax.swing.JButton generatePemitNumberButtun;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel100;
    private javax.swing.JLabel jLabel101;
    private javax.swing.JLabel jLabel102;
    private javax.swing.JLabel jLabel103;
    private javax.swing.JLabel jLabel104;
    private javax.swing.JLabel jLabel105;
    private javax.swing.JLabel jLabel106;
    private javax.swing.JLabel jLabel109;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel110;
    private javax.swing.JLabel jLabel111;
    private javax.swing.JLabel jLabel112;
    private javax.swing.JLabel jLabel113;
    private javax.swing.JLabel jLabel114;
    private javax.swing.JLabel jLabel115;
    private javax.swing.JLabel jLabel116;
    private javax.swing.JLabel jLabel117;
    private javax.swing.JLabel jLabel118;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel81;
    private javax.swing.JLabel jLabel82;
    private javax.swing.JLabel jLabel83;
    private javax.swing.JLabel jLabel84;
    private javax.swing.JLabel jLabel85;
    private javax.swing.JLabel jLabel86;
    private javax.swing.JLabel jLabel87;
    private javax.swing.JLabel jLabel88;
    private javax.swing.JLabel jLabel89;
    private javax.swing.JLabel jLabel90;
    private javax.swing.JLabel jLabel91;
    private javax.swing.JLabel jLabel92;
    private javax.swing.JLabel jLabel93;
    private javax.swing.JLabel jLabel94;
    private javax.swing.JLabel jLabel95;
    private javax.swing.JLabel jLabel96;
    private javax.swing.JLabel jLabel97;
    private javax.swing.JLabel jLabel98;
    private javax.swing.JLabel jLabel99;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane4;
    private javax.swing.JLabel nameinvalidlabel;
    private javax.swing.JLabel nicInvalidLabel;
    private javax.swing.JTextField viewPermitLotNumberTest1;
    private javax.swing.JTextField viewPermitPlanNumberTest1;
    private javax.swing.JTextField viewPermit_DivisionumberTest1;
    private javax.swing.JTextField viewPermit_clientNic1;
    private javax.swing.JTextField viewPermit_issueDate1;
    private javax.swing.JComboBox view_permit_combo;
    private javax.swing.JTextField viewpermit_DOB_test1;
    private javax.swing.JTextArea viewpermit_NOS_address_test1;
    private javax.swing.JTextField viewpermit_NOS_name_test1;
    private javax.swing.JTextField viewpermit_NOS_nic_test1;
    private javax.swing.JTextField viewpermit_acres_test1;
    private javax.swing.JTextArea viewpermit_addressText1;
    private javax.swing.JTextField viewpermit_annualIncome1;
    private javax.swing.JTextField viewpermit_division_name_test1;
    private javax.swing.JTextField viewpermit_landName_test1;
    private javax.swing.JRadioButton viewpermit_marriedStatusRButton1;
    private javax.swing.JTextField viewpermit_nameText1;
    private javax.swing.JTextField viewpermit_no_of_children_test1;
    private javax.swing.JTextField viewpermit_perches_test1;
    private javax.swing.JTextField viewpermit_roods_test1;
    private javax.swing.JRadioButton viewpermit_singleStatusRButton1;
    private javax.swing.JTextField viewpermit_telephoneText1;
    // End of variables declaration//GEN-END:variables
}
