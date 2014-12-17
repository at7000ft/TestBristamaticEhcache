package com.baristamatic.domain;

import java.io.Serializable;


/**
 * <p>
 * Title: InventoryEntry
 * </p>
 * <p>
 * Description: An Active Record Domain Model encapsulating DAO/EntityManager functionality
 * and population/maintaining the InventoryEntry instances in memory.
 * 
 * </p>
 * <p>
 * Oct 17, 2012
 * </p>
 * .
 * 
 * @author RGH
 */
 
public class InventoryEntry implements Serializable {
 
   private String displayName;
   private String name;
   private long cost;  //In cents
   private int count;

   /**
    * Instantiates a new inventory entry.
    */
   public InventoryEntry() {
      super();
   }
   
   /**
    * Instantiates a new inventory entry.
    * 
    * @param displayName the display name
    * @param name the name
    * @param cost the cost
    * @param count the count
    */
   public InventoryEntry(String displayName, String name, long cost, int count) {
      super();
      this.displayName = displayName;
      this.name = name;
      this.cost = cost;
      this.count = count;
   }

 

   /**
    * Gets the display name.
    * 
    * @return the displayName
    */
   public String getDisplayName() {
      return displayName;
   }

   /**
    * Sets the display name.
    * 
    * @param displayName the new display name
    */
   public void setDisplayName(String displayName) {
      this.displayName = displayName;
   }

   /**
    * Gets the name.
    * 
    * @return the name
    */
   public String getName() {
      return name;
   }

   /**
    * Sets the name.
    * 
    * @param name the new name
    */
   public void setName(String name) {
      this.name = name;
   }

 

   /**
    * @return the cost
    */
   public long getCost() {
      return cost;
   }

   /**
    * @param Set cost  
    */
   public void setCost(long cost) {
      this.cost = cost;
   }

   /**
    * Gets the count.
    * 
    * @return the count
    */
   public int getCount() {
      return count;
   }

   /**
    * Sets the count.
    * 
    * @param count the new count
    */
   public void setCount(int count) {
      this.count = count;
   }

   /*
    * @see java.lang.Object#toString()
    */
   public String toString() {
      return "InventoryEntry [displayName=" + displayName + ", name=" + name + ", cost=" + cost + ", count=" + count + "]";
   }
}
