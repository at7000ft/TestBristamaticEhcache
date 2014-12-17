package com.baristamatic.dao;

import java.util.List;

import com.baristamatic.domain.InventoryEntry;

/**
 * <p> Title: IInventoryEntryDao.java </p>
 * <p> Description:   
 *
 * </p>
 * <p> Nov 1, 2012</p>
 * @author RGH
 *
 *
 */
public interface IInventoryEntryDao {
   public void save(InventoryEntry entry);
   public InventoryEntry getByName(String name);
   public List<InventoryEntry> findAll();
}
