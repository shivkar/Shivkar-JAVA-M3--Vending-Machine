/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sk.vendingmachine.dao;

import com.sk.vendingmachine.dto.Item;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

/**
 *
 * @author User
 */
public class VendingMachineDaoFileImpl implements VendingMachineDao {
    private Map <String, Item> items = new HashMap<>();
    public static final String DELIMITER = "::";
    private final String VENDING_MACHINE_FILE;
    
    public VendingMachineDaoFileImpl() {
        VENDING_MACHINE_FILE = "VendingMachine.txt";
    }
    public VendingMachineDaoFileImpl(String testFile) {
        VENDING_MACHINE_FILE = testFile;
    }

    @Override
    public int getItemInventory(String name) throws VendingMachinePersistenceException {
        loadMachine();
        return items.get(name).getInventory();
    }

    @Override
    public void removeOneItemFromInventory(String name) throws VendingMachinePersistenceException {
        loadMachine();
        int prevInventory = items.get(name).getInventory();
        items.get(name).setInventory(prevInventory-1);
        writeMachine();
    }
    
    //Returns item or null if there is no item associated with the given item name
    @Override
    public Item getItem(String name) throws VendingMachinePersistenceException {
        loadMachine();
        return items.get(name);
//        Set<String> names = items.keySet();
//        
//        for(String n : names){
//            
//            if(name.equalsIgnoreCase(name))
//            {
//                return items.get(n);
//            }
//        }
//        
//        return null;
    }

    //Dont think i need the method below, ignore for now.
//    @Override
//    public List<String> getListOfItemNamesInStock()throws VendingMachinePersistenceException {
//        loadMachine();
//        //Return a list of the items names where the item inventory
//        //is greater than 0, i.e. get the keys where the inventory>0
//        
//        //Filter the map to begin with
//        Map<String, Item> filteredMap = items.entrySet()
//                .stream()
//                .filter(map -> map.getValue().getInventory() >=0)
//                .collect(Collectors.toMap(map -> map.getKey(), map -> map.getValue()));
//        
//        //Once map is filtered, retrieve the names i.e. the keys
//        List<String> itemsInStock = (List<String>) filteredMap.keySet();
//
//        return itemsInStock;
//    }
        
     
    @Override
    public Map<String,BigDecimal> getMapOfItemNamesInStockWithCosts() throws VendingMachinePersistenceException{
        loadMachine();
        //Return a list of the items names where the item inventory
        //is greater than 0, i.e. get the keys where the inventory>0
        
        Map<String, BigDecimal> itemsInStockWithCosts = items.entrySet()
                .stream()
                .filter(map -> map.getValue().getInventory() > 0)
                .collect(Collectors.toMap(map -> map.getKey(), map -> map.getValue().getCost()));
        
        return itemsInStockWithCosts;       
   
    }
    
    
    //Marshall: process of transforming memory represenetation of an object to a data format
    //suit for permanent storage
    private String marshallItem (Item anItem) {
        String itemAsText = anItem.getName() + DELIMITER;
        itemAsText += anItem.getCost() + DELIMITER;
        itemAsText += anItem.getInventory();
        return itemAsText;
    }
    
    
    //Unmarshall: process of transforming the memory representation of an object 
    private Item unmarshallItem (String itemAsText){
        //split the string into an array of strings at the delimiter
        String [] itemTokens = itemAsText.split("::");
        String name = itemTokens[0];
        Item itemFromFile = new Item(name);
        BigDecimal bigDecimal = new BigDecimal(itemTokens[1]);
        itemFromFile.setCost(bigDecimal);
        itemFromFile.setInventory(Integer.parseInt(itemTokens[2]));
        return itemFromFile;
    }
    
    
    private void loadMachine() throws VendingMachinePersistenceException {
        Scanner scanner;

        try {
            // Create Scanner for reading the file
            scanner = new Scanner(
                    new BufferedReader(
                            new FileReader(VENDING_MACHINE_FILE)));
        } catch (FileNotFoundException e) {
            throw new VendingMachinePersistenceException(
                    "-_- Could not load item data into memory.", e);
        }
        String currentLine;
        Item currentItem;
        
        while (scanner.hasNextLine()) {
            currentLine = scanner.nextLine();
            currentItem = unmarshallItem(currentLine);
            items.put(currentItem.getName(), currentItem);
        }
        scanner.close();
        }
    
    
    @Override
    public  List<Item> getAllItems() throws VendingMachinePersistenceException {
        loadMachine();
        return new ArrayList(items.values());
    }
    
    
    private void writeMachine() throws VendingMachinePersistenceException {
        PrintWriter out;
        
        try {
            out = new PrintWriter(new FileWriter(VENDING_MACHINE_FILE));
        } catch (IOException e) {
            throw new VendingMachinePersistenceException("Could not save student data.", e);
        }
        String itemAsText;
        List <Item> itemList = this.getAllItems();
        for (Item currentItem : itemList) {
            itemAsText = marshallItem(currentItem);
            out.println(itemAsText);
            out.flush();
        }
        out.close();
        }

    
    }
    
    
    
