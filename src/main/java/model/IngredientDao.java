package model;

import java.util.List;

public interface IngredientDao {
    public List<Ingredient> getIngredients();     
    public Ingredient getIngredientByID(int id);    
    public int save(Ingredient ingredient);
    public int update(int id);
    public int delete(int id);
}