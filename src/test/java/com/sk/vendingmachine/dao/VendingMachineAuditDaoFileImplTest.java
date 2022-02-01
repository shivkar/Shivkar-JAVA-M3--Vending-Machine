/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sk.vendingmachine.dao;

import com.sk.vendingmachine.dao.VendingMachinePersistenceException;
import com.sk.vendingmachine.dao.VendingMachineAuditDao;
import com.sk.vendingmachine.dao.VendingMachineAuditDaoFileImpl;
import java.io.File;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 *
 * @author User
 */
public class VendingMachineAuditDaoFileImplTest {  //Not sure how to use - do i need to write methods to test? to read from file
    String testAuditFile = "testAuditFile.txt";
    VendingMachineAuditDao testAuditDao = new VendingMachineAuditDaoFileImpl(testAuditFile);
    
    
    public VendingMachineAuditDaoFileImplTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    @Test
    public void testWriteAuditEntry() throws VendingMachinePersistenceException {
        //ARRANGE
        String entry = "One Snickers removed.";
        
        //ACT
        testAuditDao.writeAuditEntry(entry);
        
    }
    
}
