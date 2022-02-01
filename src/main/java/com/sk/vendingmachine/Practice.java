/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sk.vendingmachine;

import com.sk.vendingmachine.dto.Item;
import com.sk.vendingmachine.ui.UserIO;
import com.sk.vendingmachine.ui.UserIOConsoleImpl;

/**
 *
 * @author User
 */
public class Practice {
    public static void main(String[] args) {
        UserIO io = new UserIOConsoleImpl();

        Item kitKat = new Item("kitKat", "1.50", 10);
        Item snickers = new Item("snickers", "2.10", 10);
        Item mcCoysCrisps = new Item("mcCoysCrisps", "1.30", 10);
        Item haribo = new Item("haribo", "1.60", 10);
        Item cerealBar = new Item("cerealBar", "2.50", 10);
        Item taytoCrisps = new Item("taytoCrisps", "1.20", 10);
        
        
        boolean keepGoing = true;
        int itemSelection = 0;
        while(keepGoing) {
            io.print("Vending Machine Selection: ");
            io.print("1. Kitkat, $1.50");
            io.print("2. Snickers, $2.10");
            io.print("3. McCoys crisps, $1.30");
            io.print("4. Haribo, $1.60");
            io.print("5. Cereal bar, $2.50");
            //io.print("6. Tayto crisps, $1.20");
            io.print("6. Exit");
            
            itemSelection = io.readInt("Please select an item from the above list", 1,7);
            switch (itemSelection) {
                case 1:
                    //kitkat();
                    //System.out.println("1");
                    break;
                case 2:
                    //item2();
                    System.out.println("2");
                    break;
                case 3:
                    //item3();
                    System.out.println("3");
                    break;
                case 4:
                    //item4();
                    System.out.println("4");
                    break;
                case 5:
                    //item5();
                    System.out.println("5");
                    break;    
                case 6:
                    System.out.println("exit");
                    keepGoing = false;
                    break;
                default:
                    io.print("unknown command");
            }
        }
        io.print("Good bye");
    }
    
}






