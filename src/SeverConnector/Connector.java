/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SeverConnector;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import las.controller.*;

/**
 *
 * @author DinsuG
 */
public class Connector {
    private final RemoteFactory remoteFactory;
    private static Connector serverConnector = null;
    private BackUP backUP;
    private ClientController clientController;
    private GramaNiladariDivisionController gramaNiladaryController;
    private GrantController grantController;
    private LandController landController;
    private LotController lotController;
    private NominatedSuccessorController nominatedSuccessorController;
    private PermitController permitController;
    private UserController userController;
    public Connector() throws NotBoundException, MalformedURLException, RemoteException {
        String lookUpString = "rmi://127.0.0.1:344/LandBranchServer";
        remoteFactory = (RemoteFactory) Naming.lookup(lookUpString);
    }

    public RemoteFactory getRemoteFactory() {
        return remoteFactory;
    }

    public static Connector getSConnector() throws NotBoundException, MalformedURLException, RemoteException {
        if (serverConnector == null) {
            serverConnector = new Connector();
        }
        return serverConnector;
    }

    public ClientController getClientController() throws RemoteException, SQLException, ClassNotFoundException {
        if (clientController == null) {
            clientController = remoteFactory.getClientController();
        }
        return clientController;
    }

    public GramaNiladariDivisionController getGramaNiladariDivisionController() throws RemoteException, SQLException, ClassNotFoundException {
        if (gramaNiladaryController == null) {
            gramaNiladaryController = remoteFactory.getGramaNiladaryDivisionController();
        }
        return gramaNiladaryController;
    }

    public GrantController getGrantController() throws RemoteException, SQLException, ClassNotFoundException {
        if (grantController == null) {
            grantController = remoteFactory.getGrantController();
        }
        return grantController;
    }
    
    public LandController getLandController() throws RemoteException, SQLException, ClassNotFoundException {
        if (landController == null) {
            landController = remoteFactory.getLandController();
        }
        return landController;
    }
    
    public LotController getlotController() throws RemoteException, SQLException, ClassNotFoundException {
        if (lotController == null) {
            lotController = remoteFactory.getLotController();
        }
        return lotController;
    }
    
    public NominatedSuccessorController getnomiNominatedSuccessorController() throws RemoteException, SQLException, ClassNotFoundException {
        if (nominatedSuccessorController == null) {
            nominatedSuccessorController = remoteFactory.getNominatedSuccessorController();
        }
        return nominatedSuccessorController;
    }
    
    public PermitController getPermitController() throws RemoteException, SQLException, ClassNotFoundException {
        if (permitController == null) {
            permitController = remoteFactory.getPermitController();
        }
        return permitController;
    }
    
     public UserController getUserController() throws RemoteException, SQLException, ClassNotFoundException {
        if (userController == null) {
            userController = remoteFactory.getUeController();
        }
        return userController;
    }
    
       public BackUP getbBackUPController() throws RemoteException, SQLException, ClassNotFoundException, InterruptedException {
        if (backUP == null) {
            backUP=remoteFactory.getBackUP();
        }
        return backUP;
    }
    
    
}
