package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
 
import javax.sql.DataSource;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class IngredientDaoImpl implements IngredientDao {

    @Autowired
    @Qualifier("dbDataSource")
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;
 
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
         
    }
     
 
    public List<Ingredient> getIngredients() {
         
        List<Ingredient> ingredients = new ArrayList<Ingredient>();
        String query = "select id, name, price from ingredients"; 
        jdbcTemplate = new JdbcTemplate(dataSource);
        List<Map<String,Object>> ingredientRows = jdbcTemplate.queryForList(query);
         
        for(Map<String,Object> ingredientRow : ingredientRows){
            Ingredient ingredient = new Ingredient();
            ingredient.setId(Integer.parseInt(String.valueOf(ingredientRow.get("id"))));
            ingredient.setName(String.valueOf(ingredientRow.get("name")));
            ingredient.setPrice(Double.parseDouble(String.valueOf(ingredientRow.get("price"))));
            ingredients.add(ingredient);
        }
        return ingredients;
    }
     
    public Ingredient getIngredientByID (int id) {
        String sql = "SELECT * FROM ingredients WHERE id=?" ;
        jdbcTemplate = new JdbcTemplate(dataSource);
        return jdbcTemplate.queryForObject(sql,  new Object[]{id}, new BeanPropertyRowMapper<Ingredient>(Ingredient.class));
    }
 
    public int delete(int id) {
        String sql = "DELETE FROM ingredients WHERE id=?" ;
        jdbcTemplate = new JdbcTemplate(dataSource);
        return jdbcTemplate.update(sql);
    }
     
    public int save(Ingredient ingredient) {
        String sql = "" ;
        jdbcTemplate = new JdbcTemplate(dataSource);
        return jdbcTemplate.update(sql);
    }
     
    public int update(int id) {
        String sql = "" ;
        jdbcTemplate = new JdbcTemplate(dataSource);
        return jdbcTemplate.update(sql);
    }
}