/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package las.views;

import SeverConnector.Connector;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import las.common_classes.DateChecker;
import las.common_classes.GUIitemsValidator;
import las.common_classes.PatternChecker;
import las.controller.BackUP;
import las.controller.GramaNiladariDivisionController;
import las.controller.PermitController;
import las.controller.UserController;
import las.models.GramaNiladariDivision;
import las.models.Permit;
import las.models.User;
import las.views.reportdialogs.ApplicantByDivisionDialog;
import las.views.reportdialogs.LandsReportDialog;
import las.views.reportdialogs.PermitGrantByYearDialog;
import las.views.searchset.SearchClientForm;
import las.views.searchset.SearchForm;
import las.views.searchset.SearchGrantForm;
import las.views.searchset.SearchPermitForm;
import las.views.user_account_guis.LoginForm;
import las.views.user_account_guis.NewUserCreator;
import las.views.user_account_guis.PasswordManager;
import las.views.user_account_guis.ViewAllUsers;
import las.views.view_utilities.Calculator;
import las.views.view_utilities.Calender;

/**
 *
 * @author Gimhani
 */
public class FrontPage extends javax.swing.JFrame {

    UserController UserController;
    PermitController  PermitController;
    GramaNiladariDivisionController GramaNiladariDivisionController;
    BackUP backUP;
    private String curruser;
    private GramaNiladariDivision gnd;
    private PermitReminder permitReminderPanel;
    private ArrayList<Permit> reminderPermits= new ArrayList<Permit>();
    private JDialog reminderDialog = new JDialog (this,"Reminders",false);

    private static FrontPage frontpage=new FrontPage();

    /**
     * Creates new form FrontPage
     */
    private FrontPage() {
        initComponents();
        
        ImageIcon icon1 = new ImageIcon(getClass().getResource("/las/icons/logo-LAS-s.jpg"));
        setIconImage(icon1.getImage());
        try {
            Connector sConnector = Connector.getSConnector();
            UserController=sConnector.getUserController();
            PermitController = sConnector.getPermitController();
            backUP=sConnector.getbBackUPController();
            GramaNiladariDivisionController=sConnector.getGramaNiladariDivisionController();
            
        } catch (NotBoundException | MalformedURLException | RemoteException | SQLException | ClassNotFoundException ex) {
            Logger.getLogger(FrontPage.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(FrontPage.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        setSize(Toolkit.getDefaultToolkit().getScreenSize());
        
        desktopPane.removeAll();
        ApplicantForm applicantForm = new ApplicantForm();
        applicantForm.setSize(desktopPane.getSize());
        desktopPane.removeAll();
        desktopPane.add(applicantForm);
        applicantForm.setVisible(true);
        applicantForm.requestFoucsForm();
        setRemainders();
        setDate();
        setPreferredSize(new Dimension(1366, 768));
    }
    
    private FrontPage(String user) {
        this();
        try {
            this.curruser = user;
            username.setText(user);
            User currentuser = UserController.searchUser(user);
            //for gramaniladari
            if (currentuser.getPower() == 3) {
                addNewApplicantButton.setEnabled(false);
                addnewpermitbutton.setEnabled(false);
                addnewgrantbutton.setEnabled(false);
                addnewlandbutton.setEnabled(false);
                changegrantownershipbutton.setEnabled(false);
                GramaNiladariDivisionInfoButton.setEnabled(false);
                
                this.gnd=GramaNiladariDivisionController.searchGNDByOfficer(user);

            } //for land branch staff
            else if (currentuser.getPower() == 2) {
                
                addcertificationbutton.setEnabled(false);
            }
        } catch (ClassNotFoundException | SQLException|RemoteException ex) {
            Logger.getLogger(FrontPage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void addGrantForReminder(Permit permit){
        GrantForm grantForm = new GrantForm(this,permit);
        grantForm.setSize(desktopPane.getSize());
        desktopPane.removeAll();
        desktopPane.add(grantForm);
        grantForm.setVisible(true);

    
    }
    
    public void setRemainders(){
            
        try {
        
            ArrayList<Permit> permits =PermitController.getAllPermitsReadytoGrant();
      
            for(Permit permit:permits){
                if (DateChecker.isValid(permit.getPermitIssueDate())){
                  
                    this.reminderPermits.add(permit);
                            
                
                }
                
            }
            if (reminderPermits.isEmpty()){
            ImageIcon icon1 = new ImageIcon(getClass().getResource("/las/icons/no_reminders_1.png"));
            this.REminderButton.setDisabledIcon(icon1);
            this.REminderButton.setEnabled(false);
     
            
            }
            else {
                ImageIcon icon1 = new ImageIcon(getClass().getResource("/las/icons/reminders_1.png"));
                this.REminderButton.setIcon(icon1);
                this.REminderButton.setEnabled(true);
             
            
            
            }
        
            
        } catch (ClassNotFoundException | SQLException |RemoteException ex) {
            Logger.getLogger(FrontPage.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
                  
                  

   
        
    }
    
    
  

   public static FrontPage getInstance() {
        return frontpage;
    }
    
    public static FrontPage getInstance(String str) {
        FrontPage fp=new FrontPage(str);
        frontpage=fp;
        return frontpage;
    }

    private void setDate() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-d");
        Date date = new Date();
        dateLabel.setText(dateFormat.format(date));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        importantButtonSet = new javax.swing.ButtonGroup();
        searchButtonSet = new javax.swing.ButtonGroup();
        applicantSearchSet = new javax.swing.ButtonGroup();
        permitSearchSet = new javax.swing.ButtonGroup();
        grantSearchSet = new javax.swing.ButtonGroup();
        landSearchSet = new javax.swing.ButtonGroup();
        searchPanel1 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jRadioButton6 = new javax.swing.JRadioButton();
        jRadioButton7 = new javax.swing.JRadioButton();
        jRadioButton8 = new javax.swing.JRadioButton();
        jRadioButton9 = new javax.swing.JRadioButton();
        jRadioButton10 = new javax.swing.JRadioButton();
        jCheckBox13 = new javax.swing.JCheckBox();
        jCheckBox14 = new javax.swing.JCheckBox();
        jCheckBox15 = new javax.swing.JCheckBox();
        jCheckBox16 = new javax.swing.JCheckBox();
        jCheckBox17 = new javax.swing.JCheckBox();
        jCheckBox18 = new javax.swing.JCheckBox();
        jCheckBox19 = new javax.swing.JCheckBox();
        jCheckBox20 = new javax.swing.JCheckBox();
        jCheckBox21 = new javax.swing.JCheckBox();
        jCheckBox22 = new javax.swing.JCheckBox();
        jCheckBox23 = new javax.swing.JCheckBox();
        jCheckBox24 = new javax.swing.JCheckBox();
        desktopJPanel = new javax.swing.JPanel();
        titlePanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        userLogPanel = new javax.swing.JPanel();
        userpanel = new javax.swing.JPanel();
        username = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        LogOutButton = new javax.swing.JButton();
        ExitButton = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        dateLabel = new javax.swing.JLabel();
        CalenderButton = new javax.swing.JButton();
        REminderButton = new javax.swing.JButton();
        jButton17 = new javax.swing.JButton();
        jButton18 = new javax.swing.JButton();
        searchPanel = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        searchSetCombo = new javax.swing.JComboBox();
        searchByWhatCombo = new javax.swing.JComboBox();
        goButton = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        internalFrameAreaPanel = new javax.swing.JPanel();
        desktopPane = new javax.swing.JDesktopPane();
        jPanel3 = new javax.swing.JPanel();
        shortcutAccessPanel = new javax.swing.JPanel();
        addNewApplicantButton = new javax.swing.JButton();
        addnewpermitbutton = new javax.swing.JButton();
        addnewgrantbutton = new javax.swing.JButton();
        addnewlandbutton = new javax.swing.JButton();
        addcertificationbutton = new javax.swing.JButton();
        GramaNiladariDivisionInfoButton = new javax.swing.JButton();
        changegrantownershipbutton = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        addApplicantMenu = new javax.swing.JMenuItem();
        editApplicantMenu = new javax.swing.JMenuItem();
        viewAllAppMenu = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        addPermitMenu = new javax.swing.JMenuItem();
        nomPerSuccessorMenu = new javax.swing.JMenuItem();
        changeOwnPerMenu = new javax.swing.JMenuItem();
        Grant = new javax.swing.JMenu();
        addGrantMenu = new javax.swing.JMenuItem();
        nomGraSuccessorMenu = new javax.swing.JMenuItem();
        changeOwnGraMenu = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        statDivisionMenu = new javax.swing.JMenuItem();
        statLandMenu = new javax.swing.JMenuItem();
        statYearMenu = new javax.swing.JMenuItem();
        systemMenu = new javax.swing.JMenu();
        changePasswordMenu = new javax.swing.JMenuItem();
        createNewUserMenu = new javax.swing.JMenuItem();
        viewAllUsersMenu = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        backUpMenu = new javax.swing.JMenuItem();
        restoreMenu = new javax.swing.JMenuItem();

        searchPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel8.setText("Search ");

        searchButtonSet.add(jRadioButton6);
        jRadioButton6.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jRadioButton6.setText("1.Applicant");

        searchButtonSet.add(jRadioButton7);
        jRadioButton7.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jRadioButton7.setText("2.Permit");

        searchButtonSet.add(jRadioButton8);
        jRadioButton8.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jRadioButton8.setText("3.Grant");

        searchButtonSet.add(jRadioButton9);
        jRadioButton9.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jRadioButton9.setText("4.Grama Niladari Division");

        searchButtonSet.add(jRadioButton10);
        jRadioButton10.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jRadioButton10.setText("5.Available lands");

        applicantSearchSet.add(jCheckBox13);
        jCheckBox13.setText("By name");

        applicantSearchSet.add(jCheckBox14);
        jCheckBox14.setText("By NIC");

        applicantSearchSet.add(jCheckBox15);
        jCheckBox15.setText("By GND");

        permitSearchSet.add(jCheckBox16);
        jCheckBox16.setText("By permit number");

        permitSearchSet.add(jCheckBox17);
        jCheckBox17.setText("By applicant name");

        permitSearchSet.add(jCheckBox18);
        jCheckBox18.setText("By NIC");

        grantSearchSet.add(jCheckBox19);
        jCheckBox19.setText("By permit number");

        grantSearchSet.add(jCheckBox20);
        jCheckBox20.setText("By grant number");

        grantSearchSet.add(jCheckBox21);
        jCheckBox21.setText("By applicant name");

        grantSearchSet.add(jCheckBox22);
        jCheckBox22.setText("By NIC");

        landSearchSet.add(jCheckBox23);
        jCheckBox23.setText("All lands");

        landSearchSet.add(jCheckBox24);
        jCheckBox24.setText("By A Division");

        javax.swing.GroupLayout searchPanel1Layout = new javax.swing.GroupLayout(searchPanel1);
        searchPanel1.setLayout(searchPanel1Layout);
        searchPanel1Layout.setHorizontalGroup(
            searchPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(searchPanel1Layout.createSequentialGroup()
                .addGroup(searchPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(searchPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(searchPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jRadioButton6)
                            .addComponent(jRadioButton7)
                            .addComponent(jRadioButton8)
                            .addComponent(jRadioButton9)
                            .addGroup(searchPanel1Layout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addGroup(searchPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jCheckBox14)
                                    .addComponent(jCheckBox13)
                                    .addComponent(jCheckBox15)
                                    .addComponent(jCheckBox18)
                                    .addComponent(jCheckBox16)
                                    .addComponent(jCheckBox17)
                                    .addComponent(jCheckBox19)
                                    .addComponent(jCheckBox20)
                                    .addComponent(jCheckBox21)
                                    .addComponent(jCheckBox22))))
                        .addGap(0, 13, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(searchPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(searchPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jRadioButton10)
                    .addGroup(searchPanel1Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(searchPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jCheckBox23)
                            .addComponent(jCheckBox24))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        searchPanel1Layout.setVerticalGroup(
            searchPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(searchPanel1Layout.createSequentialGroup()
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jRadioButton6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jCheckBox13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jCheckBox14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCheckBox15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jRadioButton7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCheckBox16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCheckBox17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jCheckBox18)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jRadioButton8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jCheckBox19)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCheckBox20)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCheckBox21)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCheckBox22)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jRadioButton9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jRadioButton10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCheckBox23)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCheckBox24)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                formKeyReleased(evt);
            }
        });

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/las/icons/logo-LAS - Copy.jpg"))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Stencil", 1, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 153, 51));
        jLabel2.setText("Land Administration Unit");

        jLabel3.setFont(new java.awt.Font("Vani", 1, 18)); // NOI18N
        jLabel3.setText("Divisional Secretariat Office.Weligama.");

        jLabel4.setFont(new java.awt.Font("Script MT Bold", 1, 14)); // NOI18N
        jLabel4.setText("We reserve your land");

        javax.swing.GroupLayout titlePanelLayout = new javax.swing.GroupLayout(titlePanel);
        titlePanel.setLayout(titlePanelLayout);
        titlePanelLayout.setHorizontalGroup(
            titlePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(titlePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(titlePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 567, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 567, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 567, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        titlePanelLayout.setVerticalGroup(
            titlePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, titlePanelLayout.createSequentialGroup()
                .addGroup(titlePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(titlePanelLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel3)))
                .addContainerGap())
        );

        userpanel.setBackground(new java.awt.Color(102, 102, 102));

        username.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        username.setForeground(new java.awt.Color(255, 255, 255));
        username.setText("User Name and Icon");

        jPanel2.setOpaque(false);
        jPanel2.setLayout(new java.awt.GridLayout(1, 2, 10, 0));

        LogOutButton.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        LogOutButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/las/icons/logoff1.png"))); // NOI18N
        LogOutButton.setText("Log off");
        LogOutButton.setToolTipText("Log-off");
        LogOutButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LogOutButtonActionPerformed(evt);
            }
        });
        jPanel2.add(LogOutButton);

        ExitButton.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        ExitButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/las/icons/exit1.png"))); // NOI18N
        ExitButton.setText("Exit");
        ExitButton.setToolTipText("Exit");
        ExitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExitButtonActionPerformed(evt);
            }
        });
        jPanel2.add(ExitButton);

        javax.swing.GroupLayout userpanelLayout = new javax.swing.GroupLayout(userpanel);
        userpanel.setLayout(userpanelLayout);
        userpanelLayout.setHorizontalGroup(
            userpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(userpanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(userpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(username, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(userpanelLayout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 261, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        userpanelLayout.setVerticalGroup(
            userpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, userpanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(username, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.setBackground(new java.awt.Color(102, 102, 102));

        jLabel7.setBackground(new java.awt.Color(204, 204, 255));
        jLabel7.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Today:");

        dateLabel.setForeground(new java.awt.Color(255, 255, 255));

        CalenderButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/las/icons/Calendar68.png"))); // NOI18N
        CalenderButton.setToolTipText("Calender");
        CalenderButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CalenderButtonActionPerformed(evt);
            }
        });

        REminderButton.setToolTipText("Reminders");
        REminderButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                REminderButtonActionPerformed(evt);
            }
        });

        jButton17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/las/icons/Calc6868.png"))); // NOI18N
        jButton17.setToolTipText("Calculator");
        jButton17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton17ActionPerformed(evt);
            }
        });

        jButton18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/las/icons/Calc6868.png"))); // NOI18N
        jButton18.setToolTipText("Calculator");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(REminderButton, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton17, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton18, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(18, 18, 18)
                        .addComponent(dateLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
                        .addComponent(CalenderButton, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(dateLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(CalenderButton, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(REminderButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton18, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout userLogPanelLayout = new javax.swing.GroupLayout(userLogPanel);
        userLogPanel.setLayout(userLogPanelLayout);
        userLogPanelLayout.setHorizontalGroup(
            userLogPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, userLogPanelLayout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(userpanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(2, 2, 2))
        );
        userLogPanelLayout.setVerticalGroup(
            userLogPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, userLogPanelLayout.createSequentialGroup()
                .addGroup(userLogPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(userpanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        searchPanel.setBackground(new java.awt.Color(102, 102, 102));
        searchPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        searchPanel.setForeground(new java.awt.Color(255, 255, 255));

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Search ");

        searchSetCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Applicant", "Grant", "Permit", "GramaNiladariDivision", "Lands" }));
        searchSetCombo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                searchSetComboItemStateChanged(evt);
            }
        });
        searchSetCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchSetComboActionPerformed(evt);
            }
        });

        searchByWhatCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "By name", "By NIC" }));
        searchByWhatCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchByWhatComboActionPerformed(evt);
            }
        });

        goButton.setText("Go ");
        goButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                goButtonActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/las/icons/ourlogo.png"))); // NOI18N

        jLabel9.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel9.setText("-A Product By GuideSpark-");

        javax.swing.GroupLayout searchPanelLayout = new javax.swing.GroupLayout(searchPanel);
        searchPanel.setLayout(searchPanelLayout);
        searchPanelLayout.setHorizontalGroup(
            searchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(searchPanelLayout.createSequentialGroup()
                .addGroup(searchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(searchPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(searchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(searchPanelLayout.createSequentialGroup()
                                .addGroup(searchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(searchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(searchSetCombo, 0, 162, Short.MAX_VALUE)
                                        .addComponent(searchByWhatCombo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(goButton, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(searchPanelLayout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addGap(17, 17, 17)))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE))))
                .addContainerGap())
        );
        searchPanelLayout.setVerticalGroup(
            searchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(searchPanelLayout.createSequentialGroup()
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(searchSetCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(searchByWhatCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(goButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        desktopPane.setOpaque(false);
        desktopPane.setPreferredSize(new java.awt.Dimension(581, 581));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 66, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 29, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout desktopPaneLayout = new javax.swing.GroupLayout(desktopPane);
        desktopPane.setLayout(desktopPaneLayout);
        desktopPaneLayout.setHorizontalGroup(
            desktopPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, desktopPaneLayout.createSequentialGroup()
                .addContainerGap(700, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(130, 130, 130))
        );
        desktopPaneLayout.setVerticalGroup(
            desktopPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(desktopPaneLayout.createSequentialGroup()
                .addGap(207, 207, 207)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(276, Short.MAX_VALUE))
        );
        desktopPane.setLayer(jPanel3, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout internalFrameAreaPanelLayout = new javax.swing.GroupLayout(internalFrameAreaPanel);
        internalFrameAreaPanel.setLayout(internalFrameAreaPanelLayout);
        internalFrameAreaPanelLayout.setHorizontalGroup(
            internalFrameAreaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(internalFrameAreaPanelLayout.createSequentialGroup()
                .addComponent(desktopPane, javax.swing.GroupLayout.PREFERRED_SIZE, 896, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        internalFrameAreaPanelLayout.setVerticalGroup(
            internalFrameAreaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(internalFrameAreaPanelLayout.createSequentialGroup()
                .addComponent(desktopPane, javax.swing.GroupLayout.PREFERRED_SIZE, 512, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        shortcutAccessPanel.setBackground(new java.awt.Color(102, 102, 102));
        shortcutAccessPanel.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        shortcutAccessPanel.setLayout(new java.awt.GridLayout(7, 1, 50, 10));

        addNewApplicantButton.setFont(new java.awt.Font("Microsoft Sans Serif", 1, 14)); // NOI18N
        addNewApplicantButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/las/icons/applicant.png"))); // NOI18N
        addNewApplicantButton.setText("1-Applicant Details");
        importantButtonSet.add(addNewApplicantButton);
        addNewApplicantButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addNewApplicantButtonActionPerformed(evt);
            }
        });
        shortcutAccessPanel.add(addNewApplicantButton);

        addnewpermitbutton.setFont(new java.awt.Font("Microsoft Sans Serif", 1, 14)); // NOI18N
        addnewpermitbutton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/las/icons/permit.png"))); // NOI18N
        addnewpermitbutton.setText("2-Permit Details");
        importantButtonSet.add(addnewpermitbutton);
        addnewpermitbutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addnewpermitbuttonActionPerformed(evt);
            }
        });
        shortcutAccessPanel.add(addnewpermitbutton);

        addnewgrantbutton.setFont(new java.awt.Font("Microsoft Sans Serif", 1, 14)); // NOI18N
        addnewgrantbutton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/las/icons/grant.png"))); // NOI18N
        addnewgrantbutton.setText("3-Grant Details");
        importantButtonSet.add(addnewgrantbutton);
        addnewgrantbutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addnewgrantbuttonActionPerformed(evt);
            }
        });
        shortcutAccessPanel.add(addnewgrantbutton);

        addnewlandbutton.setFont(new java.awt.Font("Microsoft Sans Serif", 1, 14)); // NOI18N
        addnewlandbutton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/las/icons/land.png"))); // NOI18N
        addnewlandbutton.setText("4-Land Details");
        importantButtonSet.add(addnewlandbutton);
        addnewlandbutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addnewlandbuttonActionPerformed(evt);
            }
        });
        shortcutAccessPanel.add(addnewlandbutton);

        addcertificationbutton.setFont(new java.awt.Font("Microsoft Sans Serif", 1, 14)); // NOI18N
        addcertificationbutton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/las/icons/certify.png"))); // NOI18N
        addcertificationbutton.setText("5-Permit Certification");
        importantButtonSet.add(addcertificationbutton);
        addcertificationbutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addcertificationbuttonActionPerformed(evt);
            }
        });
        shortcutAccessPanel.add(addcertificationbutton);

        GramaNiladariDivisionInfoButton.setFont(new java.awt.Font("Microsoft Sans Serif", 1, 14)); // NOI18N
        GramaNiladariDivisionInfoButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/las/icons/villages.png"))); // NOI18N
        GramaNiladariDivisionInfoButton.setText("6-Village Division Info");
        importantButtonSet.add(GramaNiladariDivisionInfoButton);
        GramaNiladariDivisionInfoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GramaNiladariDivisionInfoButtonActionPerformed(evt);
            }
        });
        shortcutAccessPanel.add(GramaNiladariDivisionInfoButton);

        changegrantownershipbutton.setFont(new java.awt.Font("Microsoft Sans Serif", 1, 14)); // NOI18N
        changegrantownershipbutton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/las/icons/change_owner.png"))); // NOI18N
        changegrantownershipbutton.setText("7-Change Ownership");
        importantButtonSet.add(changegrantownershipbutton);
        shortcutAccessPanel.add(changegrantownershipbutton);

        javax.swing.GroupLayout desktopJPanelLayout = new javax.swing.GroupLayout(desktopJPanel);
        desktopJPanel.setLayout(desktopJPanelLayout);
        desktopJPanelLayout.setHorizontalGroup(
            desktopJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(desktopJPanelLayout.createSequentialGroup()
                .addComponent(titlePanel, javax.swing.GroupLayout.PREFERRED_SIZE, 720, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(userLogPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(desktopJPanelLayout.createSequentialGroup()
                .addComponent(searchPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(internalFrameAreaPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(shortcutAccessPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        desktopJPanelLayout.setVerticalGroup(
            desktopJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(desktopJPanelLayout.createSequentialGroup()
                .addGroup(desktopJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(userLogPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(titlePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(desktopJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(desktopJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(internalFrameAreaPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(searchPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(shortcutAccessPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 494, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jMenuBar1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jMenu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/las/icons/applicant - s.png"))); // NOI18N
        jMenu1.setText("Applicant");

        addApplicantMenu.setText("Add new applicant");
        addApplicantMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addApplicantMenuActionPerformed(evt);
            }
        });
        jMenu1.add(addApplicantMenu);

        editApplicantMenu.setText("Edit applicant");
        editApplicantMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editApplicantMenuActionPerformed(evt);
            }
        });
        jMenu1.add(editApplicantMenu);

        viewAllAppMenu.setText("Search applicant");
        viewAllAppMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewAllAppMenuActionPerformed(evt);
            }
        });
        jMenu1.add(viewAllAppMenu);

        jMenuBar1.add(jMenu1);

        jMenu2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/las/icons/permit - s.png"))); // NOI18N
        jMenu2.setText("Permit");

        addPermitMenu.setText("Add new permit");
        addPermitMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addPermitMenuActionPerformed(evt);
            }
        });
        jMenu2.add(addPermitMenu);

        nomPerSuccessorMenu.setText("Nominate a successor");
        nomPerSuccessorMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nomPerSuccessorMenuActionPerformed(evt);
            }
        });
        jMenu2.add(nomPerSuccessorMenu);

        changeOwnPerMenu.setText("Change ownership");
        changeOwnPerMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                changeOwnPerMenuActionPerformed(evt);
            }
        });
        jMenu2.add(changeOwnPerMenu);

        jMenuBar1.add(jMenu2);

        Grant.setIcon(new javax.swing.ImageIcon(getClass().getResource("/las/icons/grant - s.png"))); // NOI18N
        Grant.setText("Grant");

        addGrantMenu.setText("Add new Grant");
        addGrantMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addGrantMenuActionPerformed(evt);
            }
        });
        Grant.add(addGrantMenu);

        nomGraSuccessorMenu.setText("Nominate a successor");
        nomGraSuccessorMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nomGraSuccessorMenuActionPerformed(evt);
            }
        });
        Grant.add(nomGraSuccessorMenu);

        changeOwnGraMenu.setText("Change ownership");
        changeOwnGraMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                changeOwnGraMenuActionPerformed(evt);
            }
        });
        Grant.add(changeOwnGraMenu);

        jMenuBar1.add(Grant);

        jMenu3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/las/icons/chart.png"))); // NOI18N
        jMenu3.setText("Reports");

        statDivisionMenu.setText("Statistics by Division");
        statDivisionMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                statDivisionMenuActionPerformed(evt);
            }
        });
        jMenu3.add(statDivisionMenu);

        statLandMenu.setText("Statistics of Lands");
        statLandMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                statLandMenuActionPerformed(evt);
            }
        });
        jMenu3.add(statLandMenu);

        statYearMenu.setText("Statistics by Year");
        statYearMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                statYearMenuActionPerformed(evt);
            }
        });
        jMenu3.add(statYearMenu);

        jMenuBar1.add(jMenu3);

        systemMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/las/icons/System.png"))); // NOI18N
        systemMenu.setText("System");

        changePasswordMenu.setText("Change password");
        changePasswordMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                changePasswordMenuActionPerformed(evt);
            }
        });
        systemMenu.add(changePasswordMenu);

        createNewUserMenu.setText("Create new user");
        createNewUserMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createNewUserMenuActionPerformed(evt);
            }
        });
        systemMenu.add(createNewUserMenu);

        viewAllUsersMenu.setText("View all users");
        viewAllUsersMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewAllUsersMenuActionPerformed(evt);
            }
        });
        systemMenu.add(viewAllUsersMenu);
        systemMenu.add(jSeparator1);

        backUpMenu.setText("Create a backup");
        backUpMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backUpMenuActionPerformed(evt);
            }
        });
        systemMenu.add(backUpMenu);

        restoreMenu.setText("Restore backup");
        restoreMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                restoreMenuActionPerformed(evt);
            }
        });
        systemMenu.add(restoreMenu);

        jMenuBar1.add(systemMenu);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(desktopJPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(desktopJPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void REminderButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_REminderButtonActionPerformed
        permitReminderPanel = new PermitReminder(this.reminderPermits,this);
        JDialog.setDefaultLookAndFeelDecorated(true);
        reminderDialog.setContentPane(this.permitReminderPanel);
        reminderDialog.pack();
        reminderDialog.setVisible(true);
        
      
    }//GEN-LAST:event_REminderButtonActionPerformed

    private void addNewApplicantButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addNewApplicantButtonActionPerformed
        ApplicantForm applicantForm = new ApplicantForm();
        applicantForm.setSize(desktopPane.getSize());
        desktopPane.removeAll();
        desktopPane.add(applicantForm);
        applicantForm.setVisible(true);
        applicantForm.requestFoucsForm();
    }//GEN-LAST:event_addNewApplicantButtonActionPerformed
    
    public void SetDesktopPaneForClient(ApplicantForm form,int num){
                form.setSize(desktopPane.getSize());
                desktopPane.removeAll();
                desktopPane.add(form);
                form.setVisible(true);
                form.focustabbedpane(num);
                form.requestFoucsForm();
    }
    
    public void SetDesktopPaneForGrant(GrantForm form,int num){
                form.setSize(desktopPane.getSize());
                desktopPane.removeAll();
                desktopPane.add(form);
                form.setVisible(true);
                form.focustabbedpane(num);
                //form.requestFoucsForm();
    }
    
    public void addApplicantForm(String nic){
        ApplicantForm applicantForm = new ApplicantForm(nic);
        applicantForm.setSize(desktopPane.getSize());
        desktopPane.removeAll();
        desktopPane.add(applicantForm);
        applicantForm.setVisible(true);
    }
    
    private void GramaNiladariDivisionInfoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GramaNiladariDivisionInfoButtonActionPerformed
        GramaNiladhariForm gramaNiladhariForm = new GramaNiladhariForm();
        gramaNiladhariForm.setSize(desktopPane.getSize());
        desktopPane.removeAll();
        desktopPane.add(gramaNiladhariForm);
        gramaNiladhariForm.setVisible(true);
    }//GEN-LAST:event_GramaNiladariDivisionInfoButtonActionPerformed

    private void addnewlandbuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addnewlandbuttonActionPerformed
        LandForm landForm = new LandForm();
        landForm.setSize(desktopPane.getSize());
        desktopPane.removeAll();
        desktopPane.add(landForm);
        landForm.setVisible(true);

    }//GEN-LAST:event_addnewlandbuttonActionPerformed

    private void addnewpermitbuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addnewpermitbuttonActionPerformed
        PermitForm permitForm = new PermitForm();
        permitForm.setSize(desktopPane.getSize());
        desktopPane.removeAll();
        desktopPane.add(permitForm);
        permitForm.setVisible(true);
    }//GEN-LAST:event_addnewpermitbuttonActionPerformed

    private void addnewgrantbuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addnewgrantbuttonActionPerformed
        GrantForm grantForm = new GrantForm(this);
        grantForm.setSize(desktopPane.getSize());
        desktopPane.removeAll();
        desktopPane.add(grantForm);
        grantForm.setVisible(true);
    }//GEN-LAST:event_addnewgrantbuttonActionPerformed

    private void goButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_goButtonActionPerformed
        SearchForm searchForm = null;
        switch (String.valueOf(searchSetCombo.getSelectedItem())) {
            case "Applicant":
                switch ((String) searchByWhatCombo.getSelectedItem()) {
                    case "By name":
                        searchForm = new SearchClientForm("Applicant", "By name");
                        
                        break;
                    case "By NIC":
                        searchForm = new SearchClientForm("Applicant", "By NIC");
                        break;

                }
                break;
            case "Permit":
                switch ((String) searchByWhatCombo.getSelectedItem()) {
                    case "By permit number":
                        searchForm = new SearchPermitForm("Permit", "By permit number");
                        break;
                    case "By applicant name":
                        searchForm = new SearchPermitForm("Permit", "By applicant name");
                        break;
                    case "By NIC":
                        searchForm = new SearchPermitForm("Permit", "By NIC");
                        break;
                }
                break;
            case "Grant":
                switch ((String) searchByWhatCombo.getSelectedItem()) {
                    case "By permit number":
                        searchForm = new SearchGrantForm("Grant", "By permit number");
                        break;
                    case "By applicant name":
                        searchForm = new SearchGrantForm("Grant", "By applicant name");
                        break;
                    case "By NIC":
                        searchForm = new SearchGrantForm("Grant", "By NIC");
                        break;
                    case "By grant number":
                        searchForm = new SearchGrantForm("Grant", "By grant number");
                        break;
                }
                break;
        }
        
        searchForm.setSize(desktopPane.getSize());
        desktopPane.removeAll();
        desktopPane.add(searchForm);
        searchForm.setVisible(true);
        searchForm.requestFocusForm();
    }//GEN-LAST:event_goButtonActionPerformed

    private void searchSetComboItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_searchSetComboItemStateChanged
        String selected = String.valueOf(searchSetCombo.getSelectedItem());

        ArrayList<String> applicantlist = new ArrayList<>(Arrays.asList("By name", "By NIC"));
        ArrayList<String> permitlist = new ArrayList<>(Arrays.asList("By permit number", "By applicant name", "By NIC"));
        ArrayList<String> grantlist = new ArrayList<>(Arrays.asList("By grant number", "By permit number", "By applicant name", "By NIC"));

        if (selected == "Applicant") {
            GUIitemsValidator.addItemToCombo(applicantlist, searchByWhatCombo);
        } else if (selected == "Permit") {
            GUIitemsValidator.addItemToCombo(permitlist, searchByWhatCombo);
        } else if (selected == "Grant") {
            GUIitemsValidator.addItemToCombo(grantlist, searchByWhatCombo);
        }
    }//GEN-LAST:event_searchSetComboItemStateChanged

    private void searchByWhatComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchByWhatComboActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchByWhatComboActionPerformed

    private void addcertificationbuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addcertificationbuttonActionPerformed
        new GramaNiladariCertificationForm(this, true,gnd).setVisible(true);
    }//GEN-LAST:event_addcertificationbuttonActionPerformed

    private void searchSetComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchSetComboActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchSetComboActionPerformed

    private void nomPerSuccessorMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nomPerSuccessorMenuActionPerformed
        new ChangeNominateSuccessoOfPermitrForm(this, true).setVisible(true);
    }//GEN-LAST:event_nomPerSuccessorMenuActionPerformed

    private void changeOwnPerMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_changeOwnPerMenuActionPerformed
        new ChangePermitOwnershipForm().setVisible(true);
    }//GEN-LAST:event_changeOwnPerMenuActionPerformed

    private void changeOwnGraMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_changeOwnGraMenuActionPerformed
new ChangeGrantOwnershipForm().setVisible(true);
    }//GEN-LAST:event_changeOwnGraMenuActionPerformed

    private void nomGraSuccessorMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nomGraSuccessorMenuActionPerformed
        new ChangeNominateSuccessoGrantrForm(this, true).setVisible(true);
    }//GEN-LAST:event_nomGraSuccessorMenuActionPerformed

    private void backUpMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backUpMenuActionPerformed
        /*try {
            int writeBackup = BackUp.writeBackup();
            if(writeBackup==0){
                JOptionPane.showMessageDialog(this,"Backup  successfully");
            }else{
                JOptionPane.showMessageDialog(this,"Backup is not successfully");
            
            }
        } catch (IOException ex) {
                   Logger.getLogger(FrontPage.class.getName()).log(Level.SEVERE, null, ex);
        }*/
    }//GEN-LAST:event_backUpMenuActionPerformed

    private void restoreMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_restoreMenuActionPerformed
        try {
            int restoreBackup = backUP.restoreBackup();
            if(restoreBackup==0){
                JOptionPane.showMessageDialog(this,"Backup restored successfully");
            }else{
                JOptionPane.showMessageDialog(this,"Backup is not restored successfully");
            
            }
            
        } catch (IOException | InterruptedException ex) {
           ex.printStackTrace();
        }
    }//GEN-LAST:event_restoreMenuActionPerformed

    private void LogOutButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LogOutButtonActionPerformed
        
        new LoginForm().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_LogOutButtonActionPerformed

    private void ExitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExitButtonActionPerformed
        int result=JOptionPane.showConfirmDialog(this, "Are you sure you want to exit?");
        if(result==JOptionPane.YES_OPTION){
            this.dispose();
        }
    }//GEN-LAST:event_ExitButtonActionPerformed

    private void changePasswordMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_changePasswordMenuActionPerformed
        new PasswordManager(this, true, curruser).setVisible(true);
    }//GEN-LAST:event_changePasswordMenuActionPerformed

    private void createNewUserMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createNewUserMenuActionPerformed
        new NewUserCreator(this, true).setVisible(true);
    }//GEN-LAST:event_createNewUserMenuActionPerformed

    private void viewAllUsersMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewAllUsersMenuActionPerformed
        new ViewAllUsers(this, true).setVisible(true);
    }//GEN-LAST:event_viewAllUsersMenuActionPerformed

    private void addGrantMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addGrantMenuActionPerformed
        GrantForm grantForm = new GrantForm(this);
        grantForm.setSize(desktopPane.getSize());
        desktopPane.removeAll();
        desktopPane.add(grantForm);
        grantForm.setVisible(true);
    }//GEN-LAST:event_addGrantMenuActionPerformed

    private void statDivisionMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_statDivisionMenuActionPerformed
         new ApplicantByDivisionDialog(this,true).setVisible(true);
    }//GEN-LAST:event_statDivisionMenuActionPerformed

    private void statYearMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_statYearMenuActionPerformed
        new PermitGrantByYearDialog(this, true).setVisible(true);
    }//GEN-LAST:event_statYearMenuActionPerformed

    private void addApplicantMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addApplicantMenuActionPerformed
        ApplicantForm applicantForm = new ApplicantForm();
        applicantForm.setSize(desktopPane.getSize());
        desktopPane.removeAll();
        desktopPane.add(applicantForm);
        applicantForm.setVisible(true);
        applicantForm.requestFoucsForm();
    }//GEN-LAST:event_addApplicantMenuActionPerformed

    private void editApplicantMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editApplicantMenuActionPerformed
        ApplicantForm applicantForm = new ApplicantForm(2);
        applicantForm.setSize(desktopPane.getSize());
        desktopPane.removeAll();
        desktopPane.add(applicantForm);
        applicantForm.setVisible(true);
        applicantForm.requestFoucsForm();
    }//GEN-LAST:event_editApplicantMenuActionPerformed

    private void viewAllAppMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewAllAppMenuActionPerformed
        ApplicantForm applicantForm = new ApplicantForm(1);
        applicantForm.setSize(desktopPane.getSize());
        desktopPane.removeAll();
        desktopPane.add(applicantForm);
        applicantForm.setVisible(true);
        applicantForm.requestFoucsForm();
    }//GEN-LAST:event_viewAllAppMenuActionPerformed

    private void addPermitMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addPermitMenuActionPerformed
        PermitForm permitForm = new PermitForm();
        permitForm.setSize(desktopPane.getSize());
        desktopPane.removeAll();
        desktopPane.add(permitForm);
        permitForm.setVisible(true);
    }//GEN-LAST:event_addPermitMenuActionPerformed

    private void statLandMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_statLandMenuActionPerformed
        new LandsReportDialog(this, true).setVisible(true);
    }//GEN-LAST:event_statLandMenuActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE); //as if cancel option clicked before,
        int result=JOptionPane.showConfirmDialog(this, "Are you sure you want to exit?");
        if(result==JOptionPane.YES_OPTION){
            this.dispose();
        }else{
            this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        }
    }//GEN-LAST:event_formWindowClosing

    private void formKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_1) {
            addNewApplicantButton.doClick();
        } else if (evt.getKeyCode() == KeyEvent.VK_2) {
            addnewpermitbutton.doClick();
        } else if (evt.getKeyCode() == KeyEvent.VK_3) {
            addnewgrantbutton.doClick();
        } else if (evt.getKeyCode() == KeyEvent.VK_4) {
            addnewlandbutton.doClick();
        } else if (evt.getKeyCode() == KeyEvent.VK_5) {
            addcertificationbutton.doClick();
        } else if (evt.getKeyCode() == KeyEvent.VK_6) {
            GramaNiladariDivisionInfoButton.doClick();
        } else if (evt.getKeyCode() == KeyEvent.VK_7) {
            changegrantownershipbutton.doClick();
        }
    }//GEN-LAST:event_formKeyReleased

    private void jButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton17ActionPerformed
        new Calculator().setVisible(true);
    }//GEN-LAST:event_jButton17ActionPerformed

    private void CalenderButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CalenderButtonActionPerformed
        new Calender(this, true).setVisible(true);
    }//GEN-LAST:event_CalenderButtonActionPerformed

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
            java.util.logging.Logger.getLogger(FrontPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrontPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrontPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrontPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                FrontPage fp = new FrontPage();
                fp.setVisible(true);

            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton CalenderButton;
    private javax.swing.JButton ExitButton;
    private javax.swing.JButton GramaNiladariDivisionInfoButton;
    private javax.swing.JMenu Grant;
    private javax.swing.JButton LogOutButton;
    private javax.swing.JButton REminderButton;
    private javax.swing.JMenuItem addApplicantMenu;
    private javax.swing.JMenuItem addGrantMenu;
    private javax.swing.JButton addNewApplicantButton;
    private javax.swing.JMenuItem addPermitMenu;
    private javax.swing.JButton addcertificationbutton;
    private javax.swing.JButton addnewgrantbutton;
    private javax.swing.JButton addnewlandbutton;
    private javax.swing.JButton addnewpermitbutton;
    private javax.swing.ButtonGroup applicantSearchSet;
    private javax.swing.JMenuItem backUpMenu;
    private javax.swing.JMenuItem changeOwnGraMenu;
    private javax.swing.JMenuItem changeOwnPerMenu;
    private javax.swing.JMenuItem changePasswordMenu;
    private javax.swing.JButton changegrantownershipbutton;
    private javax.swing.JMenuItem createNewUserMenu;
    private javax.swing.JLabel dateLabel;
    private javax.swing.JPanel desktopJPanel;
    private javax.swing.JDesktopPane desktopPane;
    private javax.swing.JMenuItem editApplicantMenu;
    private javax.swing.JButton goButton;
    private javax.swing.ButtonGroup grantSearchSet;
    private javax.swing.ButtonGroup importantButtonSet;
    private javax.swing.JPanel internalFrameAreaPanel;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton18;
    private javax.swing.JCheckBox jCheckBox13;
    private javax.swing.JCheckBox jCheckBox14;
    private javax.swing.JCheckBox jCheckBox15;
    private javax.swing.JCheckBox jCheckBox16;
    private javax.swing.JCheckBox jCheckBox17;
    private javax.swing.JCheckBox jCheckBox18;
    private javax.swing.JCheckBox jCheckBox19;
    private javax.swing.JCheckBox jCheckBox20;
    private javax.swing.JCheckBox jCheckBox21;
    private javax.swing.JCheckBox jCheckBox22;
    private javax.swing.JCheckBox jCheckBox23;
    private javax.swing.JCheckBox jCheckBox24;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JRadioButton jRadioButton10;
    private javax.swing.JRadioButton jRadioButton6;
    private javax.swing.JRadioButton jRadioButton7;
    private javax.swing.JRadioButton jRadioButton8;
    private javax.swing.JRadioButton jRadioButton9;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.ButtonGroup landSearchSet;
    private javax.swing.JMenuItem nomGraSuccessorMenu;
    private javax.swing.JMenuItem nomPerSuccessorMenu;
    private javax.swing.ButtonGroup permitSearchSet;
    private javax.swing.JMenuItem restoreMenu;
    private javax.swing.ButtonGroup searchButtonSet;
    private javax.swing.JComboBox searchByWhatCombo;
    private javax.swing.JPanel searchPanel;
    private javax.swing.JPanel searchPanel1;
    private javax.swing.JComboBox searchSetCombo;
    private javax.swing.JPanel shortcutAccessPanel;
    private javax.swing.JMenuItem statDivisionMenu;
    private javax.swing.JMenuItem statLandMenu;
    private javax.swing.JMenuItem statYearMenu;
    private javax.swing.JMenu systemMenu;
    private javax.swing.JPanel titlePanel;
    private javax.swing.JPanel userLogPanel;
    private javax.swing.JLabel username;
    private javax.swing.JPanel userpanel;
    private javax.swing.JMenuItem viewAllAppMenu;
    private javax.swing.JMenuItem viewAllUsersMenu;
    // End of variables declaration//GEN-END:variables
}
