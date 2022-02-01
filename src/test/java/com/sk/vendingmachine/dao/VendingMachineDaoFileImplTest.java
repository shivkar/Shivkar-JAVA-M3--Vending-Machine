/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sk.vendingmachine.dao;

import com.sk.vendingmachine.dao.VendingMachineDao;
import com.sk.vendingmachine.dao.VendingMachinePersistenceException;
import com.sk.vendingmachine.dao.VendingMachineDaoFileImpl;
import com.sk.vendingmachine.dto.Item;
import java.math.BigDecimal;
import java.util.Map;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 *
 * @author User
 */
public class VendingMachineDaoFileImplTest {
    // Question - if we are only reading from and not writing to the DAO, it okay to just make a copy of it.
    
    VendingMachineDao testDao = new VendingMachineDaoFileImpl("VendingMachineTestFile.txt");
    
    public VendingMachineDaoFileImplTest() {
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
    public void testGetItem() throws VendingMachinePersistenceException {
        //ARRANGE
        Item snickersClone = new Item("Snickers");
        snickersClone.setCost(new BigDecimal("2.10"));
        snickersClone.setInventory(0);
        
        //ACT
        Item retrievedItem = testDao.getItem("Snickers");
        
        //ASSERT
        assertNotNull(retrievedItem, "Item should not be null");
        assertEquals(retrievedItem, snickersClone,"The item retrieved should be snickers");
        
    }
    @Test
    public void testRemoveOneItemFromInventory() throws VendingMachinePersistenceException {
        //ARRANGE
        String itemName = "Malteasers";
        
        //ACT
        //get the inventory before we remove one
        int inventoryBefore = testDao.getItemInventory(itemName);
        
        //remove one item
        testDao.removeOneItemFromInventory(itemName);
        
        //get the inventory after we have removed one
        int inventoryAfter = testDao.getItemInventory(itemName);
        
        assertTrue(inventoryAfter<inventoryBefore,"The inventory after should be less than before");
        assertEquals(inventoryAfter,inventoryBefore-1,"The inventory after should be one less than it was"
                + "before");
        
    }
    @Test
    public void testGetItemInventory() throws VendingMachinePersistenceException {
        //ARRANGE 
        String itemName = "Snickers";
        
        //ACT
        int retrievedInventory = testDao.getItemInventory(itemName);

        //ASSERT 
        assertEquals(retrievedInventory,0,"There are 0 items of snickers left.");  
    }
    
    @Test
    public void testGetMapOfItemNamesInStockWithCosts() throws VendingMachinePersistenceException {
        
        //ACT
        Map<String,BigDecimal> itemsInStock = testDao.getMapOfItemNamesInStockWithCosts();
        
        //ASSERT
        //there are 0 snickers left, so it should not be included.
        assertFalse(itemsInStock.containsKey("Snickers"));
        //There are 7 items in total, only snickers  is out of stock, so there should be 6 items
        assertEquals(itemsInStock.size(),6,"The menu list should contain 6 items.");
        assertTrue(itemsInStock.containsKey("Kitkat") &&
                itemsInStock.containsKey("McCoys") &&
                itemsInStock.containsKey("Haribo") &&
                itemsInStock.containsKey("Malteasers") &&
                itemsInStock.containsKey("Starburst") &&
                itemsInStock.containsKey("Cereal bar"));
    }

}
