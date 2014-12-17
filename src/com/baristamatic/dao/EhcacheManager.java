package com.baristamatic.dao;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;

/**
 * <p>
 * Title: EhcacheManager.java
 * </p>
 * <p>
 * Description:
 * 
 * </p>
 * <p>
 * Apr 27, 2012
 * </p>
 * 
 * @author tater
 *          
 * 
 * 
 */
public class EhcacheManager {
   private static final EhcacheManager  instance = new EhcacheManager();
   private  Cache drinkCache;
   private  Cache inventoryCache;


   public static final String DRINK_CACHE_NAME = "drinkCache";
   public static final String INVENTORY_CACHE_NAME = "inventoryCache";

   private EhcacheManager() {
      try {
         CacheManager.getInstance().addCache(DRINK_CACHE_NAME);
         CacheManager.getInstance().addCache(INVENTORY_CACHE_NAME);
         drinkCache = CacheManager.getInstance().getCache(DRINK_CACHE_NAME);
         inventoryCache = CacheManager.getInstance().getCache(INVENTORY_CACHE_NAME);
      } catch (Exception e) {
         e.printStackTrace();
      } 
   }

   public static EhcacheManager instance() {
      return instance;
   }

   public Cache getDrinkCache() {
      return drinkCache;
   }
   
   public Cache getInventoryCache() {
      return inventoryCache;
   }
}
