package com.baristamatic.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;

import com.baristamatic.domain.InventoryEntry;

/**
 * <p> Title: InventoryEntryDao.java </p>
 * <p> Description:   
 *
 * </p>
 * <p> Nov 1, 2012</p>
 * @author RGH
 *
 *
 */
public class InventoryEntryDao   implements IInventoryEntryDao {
   private  Cache cache;
 
   /**
    * Instantiates a new inventory entry dao.
    *
    */
   public InventoryEntryDao() {
      cache = EhcacheManager.instance().getInventoryCache();
   }

   /* 
    * @see com.baristamatic.dao.IInventoryEntryDao#saveInventoryEntry(com.baristamatic.domain.InventoryEntry)
    */
   public void save(InventoryEntry entry) {
      cache.put(new Element(entry.getName(), entry));
   }
   
   /* 
    * @see com.baristamatic.dao.IInventoryEntryDao#getByName(java.lang.String)
    */
   public InventoryEntry getByName(String name) {
      //return createQuery().field("name").equal(name).asList().get(0);
      return (InventoryEntry) cache.get(name).getValue();
   }

   /* 
    * @see com.baristamatic.dao.IInventoryEntryDao#findAll()
    */
   public List<InventoryEntry> findAll() {
      Map<Object,Element> map = cache.getAll(cache.getKeys());
      List<InventoryEntry> lst = new ArrayList<InventoryEntry>();
      for (Map.Entry<Object, Element> entry : map.entrySet()) {
         Element ele = entry.getValue();
         lst.add((InventoryEntry) ele.getObjectValue());
      }
      return lst;
   }
}
