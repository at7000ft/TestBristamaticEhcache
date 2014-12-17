package com.baristamatic.dao;

import java.util.List;

import com.baristamatic.domain.Drink;

/**
 * <p> Title: IDrinkDao.java </p>
 * <p> Description:   
 *
 * </p>
 * <p> Nov 1, 2012</p>
 * @author RGH
 *
 *
 */
public interface IDrinkDao {
   public void save(Drink drink);
   public Drink get(Integer num);
   public List<Drink> findAll();
}
