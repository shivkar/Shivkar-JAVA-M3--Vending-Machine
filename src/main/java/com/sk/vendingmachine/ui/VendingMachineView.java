/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sk.vendingmachine.ui;

import com.sk.vendingmachine.dto.Change;
import com.sk.vendingmachine.dto.Item;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 *
 * @author User
 */
public class VendingMachineView {
    
    private UserIO io;
    
    public VendingMachineView (UserIO io){
        this.io = io;
    }

    public BigDecimal getMoney() {
        return io.readBigDecimal("Please input the amount money in dollars before making selection");
    }
    
    public void displayMenuBanner() {
        io.print("=== Menu ===");
    }
    
    public void displayMenu(Map<String, BigDecimal> itemsInStockWithCosts){
        itemsInStockWithCosts.entrySet().forEach(entry ->{
        System.out.println(entry.getKey() + ": $" +entry.getValue());
        });
    }
    
    public String getItemSelection(){
        return io.readString("Please select an item from the menu above or 'exit' to quit");
    }

    public void displayEnjoyBanner(String name) {
        io.print("Here is your change.");
        io.print("Enjoy your " + name + "!");
    }
    
    public void displayInsufficientFundsMsg(BigDecimal money){
        io.print("Insufficent funds, you only have input $"+ money);
    }
    
    public void displayItemOutOfStockMsg(String name){
        io.print("Error, " + name + " is out of stock.");
    }   

    public void displayChangeDuePerCoin(Map<BigDecimal, BigDecimal> changeDuePerCoin) {
        changeDuePerCoin.entrySet().forEach(entry ->{
                 System.out.println(entry.getKey() + "c : " +entry.getValue());
         });
    }

    public void displayExitBanner() {
        io.print("Good Bye!!!");
    }
    
    public void displayUnknownCommandBanner() {
        io.print("Unknown Command!!!");
    }
    
    public void displayErrorMessage (String errorMsg) {
        io.print("=== Error ===");
        io.print(errorMsg);
    }
    
    public void displayPleaseTryAgainMsg() {
        io.print("Please select something else.");
    }


}
