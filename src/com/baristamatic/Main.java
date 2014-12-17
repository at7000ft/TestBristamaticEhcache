package com.baristamatic;

import static java.lang.System.out;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

import com.baristamatic.domain.Drink;
import com.baristamatic.domain.InventoryEntry;
import com.baristamatic.service.DrinkService;

/**
 * <p>
 * Title: Main.java
 * </p>
 * <p>
 * Description:
 * 
 * </p>
 * <p>
 * Nov 1, 2012
 * </p>
 * 
 * @author RGH
 * 
 * 
 */
public class Main {
   private DrinkService drinkService;
   private List<String> validDrinkNumbers;
   private static final String RESTOCK_COMMAND_STRING = "r";
   private static final String QUIT_COMMAND_STRING = "q";
   private static final List<String> validCommands = Arrays.asList(RESTOCK_COMMAND_STRING, QUIT_COMMAND_STRING);

   /**
    * The main method.
    * 
    * @param args the arguments
    */
   public static void main(String[] args) {
      Main main = new Main();
      main.init();
      main.mainLoop();
   }

   /**
    * Init
    */
   private void init() {
  
      drinkService = new DrinkService();
      validDrinkNumbers = drinkService.getValidDrinkNumbers();
   }

   /**
    * Main loop.
    */
   private void mainLoop() {
      String commandString = "";
      // ... Initialize Scanner to read from console.
      Scanner input = new Scanner(System.in);
      while (!commandString.equals(QUIT_COMMAND_STRING)) {
         showInventory();
         showMenu();
         out.println("\n");
         try {
            // Get input
            commandString = input.nextLine().trim();
            // Test for valid entry
            if (!isValidEntry(commandString)) {
               out.println("Invalid selection: " + commandString + "\n");
               continue;
            }
            // Test for quit command
            if (commandString.equals(QUIT_COMMAND_STRING)) {
               continue;
            }
            // Test for restock command
            if (commandString.equals(RESTOCK_COMMAND_STRING)) {
               drinkService.reStock();
               continue;
            }
            // If a valid drink number was entered attempt to dispense a drink
            Drink requestedDrink = drinkService.getDrink(new Integer(commandString));
            if (drinkService.createDrink(new Integer(commandString))) {
               out.println("Dispensing: " + requestedDrink.getDisplayName() + "\n");
            } else {
               out.println("Out of stock: " + requestedDrink.getDisplayName() + "\n");
            }
         } catch (StringIndexOutOfBoundsException e) {
            out.println("Invalid selection: " + commandString + "\n");
         } catch (NoSuchElementException e) {
            out.println("Invalid selection: " + commandString + "\n");
         }
      }
      System.exit(0);
   }

   /**
    * Show inventory.
    */
   private void showInventory() {
      out.println("Inventory:");
      List<InventoryEntry> list = drinkService.getInventory();
      for (InventoryEntry entry : list) {
         out.println(entry.getDisplayName() + "," + entry.getCount());
      }
   }

   /**
    * Show menu.
    */
   private void showMenu() {
      out.println("\nMenu:");
      for (Drink drink : drinkService.getAllDrinks()) {
         out.println(drink.getNumber() + "," + drink.getDisplayName() + ",$" + drink.getDisplayCost() + "," + drink.isInStock());
      }
   }

   /**
    * Checks if is valid entry.
    * 
    * @param entry the entry
    * @return true, if is valid entry
    */
   private boolean isValidEntry(String entry) {
      if (entry.length() == 1 && validCommands.contains(entry)) {
         return true;
      } else if (testForDigits(entry) && validDrinkNumbers.contains(entry)) {
         return true;
      } else {
         return false;
      }
   }

   /**
    * Test for digits.
    * 
    * @param testStr the test str
    * @return true, if successful
    */
   private boolean testForDigits(String testStr) {
      boolean stat = true;
      for (int i = 0; i < testStr.length(); i++) {
         if (!Character.isDigit(testStr.charAt(i))) {
            stat = false;
            break;
         }
      }
      return stat;
   }
}
