package com.baristamatic.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;

import com.baristamatic.domain.Drink;
import com.baristamatic.service.DrinkService;

/**
 * <p> Title: TestDrinkService.java </p>
 * <p> Description:   
 *
 * </p>
 * <p> Nov 9, 2012</p>
 * @author RGH
 *
 *
 */
public class TestDrinkService {
   
   @Test
   public void testDrinkDao() {
      DrinkService drinkService = new DrinkService();
      List<Drink> drinks = drinkService.getAllDrinks();
      assertNotNull(drinks);
      assertEquals(6,drinks.size());
      //
      Drink drink = drinkService.getDrink(1);
      assertNotNull(drink);
      //
      drinkService.reStock();
      List<String>   validDrinkNumbers = drinkService.getValidDrinkNumbers();
      assertNotNull(validDrinkNumbers);
      assertEquals(6,validDrinkNumbers.size());
      //
      
      
   }
}
