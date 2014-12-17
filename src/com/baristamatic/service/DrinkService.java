package com.baristamatic.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.baristamatic.Bootstrap;
import com.baristamatic.dao.DrinkDao;
import com.baristamatic.dao.IDrinkDao;
import com.baristamatic.dao.IInventoryEntryDao;
import com.baristamatic.dao.InventoryEntryDao;
import com.baristamatic.domain.Drink;
import com.baristamatic.domain.InventoryEntry;

/**
 * <p>
 * Title: DrinkService.java
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
public class DrinkService {
   private IDrinkDao ddao = new DrinkDao();
   private IInventoryEntryDao idao = new InventoryEntryDao();

   
   /**
    * 
    */
   public DrinkService() {
      Bootstrap.init();
   }

   /**
    * Inits the valid drink numbers. Abstracts this class from Drink instance changes.
    *
    * @return the valid drink numbers
    */
   public List<String> getValidDrinkNumbers() {
      List<String> validDrinkNumbers = new ArrayList<String>();
      for (Drink drink : ddao.findAll()) {
         String number = Integer.toString(drink.getNumber());
         validDrinkNumbers.add(number);
      }
      return validDrinkNumbers;
   }

   /**
    * Re stock.
    */
   public void reStock() {
      Bootstrap.init();
   }

   /**
    * Gets the drink.
    *
    * @param number the number
    * @return the drink
    */
   public Drink getDrink(Integer number) {
      return ddao.get(number);
   }

   /**
    * Checks for ingredient availablity and decrements inventory if available.
    * Would make synchronized if service would be accessed by multiple client threads (like a servlet),
    * not needed for this example.
    * 
    * @param number the number
    * @return true if ingredients available and consumes ingredients, else returns false
    */
   public boolean createDrink(Integer number) {
      Drink drink = ddao.get(number);
      boolean avail = hasIngredientCount(drink.getRecipe());
      if (!avail) {
         return false;
      } else {
         consumeIngredients(drink.getRecipe());
      }
      return true;
   }

   /**
    * Gets the all drinks. Update availability flag and cost on way out in case inventory cost has changed.
    * 
    * @return the all drinks
    */
   public List<Drink> getAllDrinks() {
      List<Drink> drinks = ddao.findAll();
      for (Drink drink : drinks) {
         drink.setInStock(hasIngredientCount(drink.getRecipe()));
         drink.setDisplayCost(getDrinkCost(drink.getRecipe()));
      }
      return drinks;
   }

   /**
    * Gets the drink cost. Method could be in the Drink class but would make Drink dependent on
    * the InventoryEntry class.
    * 
    * @param recipe the recipe
    * @return the drink cost
    */
   private BigDecimal getDrinkCost(Map<String, Integer> recipe) {
      double tcost = 0;
      for (Map.Entry<String, Integer> ent : recipe.entrySet()) {
         int count = ent.getValue();
         InventoryEntry invEntry = idao.getByName(ent.getKey());
         tcost = tcost + (invEntry.getCost() * count);
      }
      BigDecimal totalCost = new BigDecimal(tcost / 100).setScale(2, BigDecimal.ROUND_HALF_UP);
      return totalCost;
   }

   /**
    * Gets the inventory.
    * 
    * @return the inventory
    */
   public List<InventoryEntry> getInventory() {
      return idao.findAll();
   }

   /**
    * Checks for ingredient count.
    * 
    * @param recipe the recipe
    * @return true, if successful
    */
   public boolean hasIngredientCount(Map<String, Integer> recipe) {
      for (Map.Entry<String, Integer> ent : recipe.entrySet()) {
         InventoryEntry ie = idao.getByName(ent.getKey());
         int requiredCount = ent.getValue();
         if (ie.getCount() < requiredCount) {
            return false;
         }
      }
      return true;
   }

   /**
    * Consume ingredients.
    * 
    * @param recipe the recipe
    */
   public void consumeIngredients(Map<String, Integer> recipe) {
      for (Map.Entry<String, Integer> ent : recipe.entrySet()) {
         InventoryEntry ie = idao.getByName(ent.getKey());
         int decrementCount = ent.getValue();
         ie.setCount(ie.getCount() >= decrementCount ? ie.getCount() - decrementCount : 0);
         idao.save(ie);
      }
   }
}
