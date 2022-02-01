/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sk.vendingmachine;

import com.sk.vendingmachine.controller.VendingMachineController;
import com.sk.vendingmachine.dao.VendingMachineAuditDao;
import com.sk.vendingmachine.dao.VendingMachineAuditDaoFileImpl;
import com.sk.vendingmachine.dao.VendingMachineDao;
import com.sk.vendingmachine.dao.VendingMachineDaoFileImpl;
import com.sk.vendingmachine.service.VendingMachineServiceLayer;
import com.sk.vendingmachine.service.VendingMachineServiceLayerImpl;
import com.sk.vendingmachine.ui.UserIO;
import com.sk.vendingmachine.ui.UserIOConsoleImpl;
import com.sk.vendingmachine.ui.VendingMachineView;

/**
 *
 * @author User
 */
public class App {
    public static void main(String[] args) {
        UserIO io = new UserIOConsoleImpl();
        VendingMachineView view = new VendingMachineView(io);
        VendingMachineAuditDao auditDao = new VendingMachineAuditDaoFileImpl();
        VendingMachineDao dao = new VendingMachineDaoFileImpl();
        VendingMachineServiceLayer service = new VendingMachineServiceLayerImpl(auditDao, dao);
        
        VendingMachineController controller = new VendingMachineController(view, service);
        
        controller.run();
    }
}
