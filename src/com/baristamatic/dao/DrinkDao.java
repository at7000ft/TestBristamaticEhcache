package com.baristamatic.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;

import com.baristamatic.domain.Drink;

/**
 * <p> Title: DrinkDao.java </p>
 * <p> Description:   
 *
 * </p>
 * <p> Nov 1, 2012</p>
 * @author RGH
 *
 *
 */
public class DrinkDao  implements IDrinkDao {
   private  Cache cache;

   /**
    * Instantiates a new drink dao.
    *
    */
   public DrinkDao() {
      cache = EhcacheManager.instance().getDrinkCache();
       
   }

   /* 
    * @see com.baristamatic.dao.IDrinkDao#saveDrink(com.baristamatic.domain.Drink)
    */
   public void save(Drink drink) {
      cache.put(new Element(drink.getName(), drink));
    
   }
   
   /* 
    * @see com.baristamatic.dao.IDrinkDao#findAll()
    */
   public List<Drink> findAll() {
      Map<Object,Element> map = cache.getAll(cache.getKeys());
      List<Drink> lst = new ArrayList<Drink>();
      for (Map.Entry<Object, Element> entry : map.entrySet()) {
         Element ele = entry.getValue();
         lst.add((Drink) ele.getObjectValue());
      }
      return lst;
   }
   
   /* 
    * @see com.baristamatic.dao.IDrinkDao#getDrink(java.lang.Integer)
    */
   public Drink get(Integer num) {
      List<Drink> drinks= findAll();
      for (Drink drink : drinks) {
         if (drink.getNumber() == num) {
            return drink;
         }
      }
      return null;
   }
}
