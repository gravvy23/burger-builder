package model;

//import javax.xml.bind.annotation.XmlRootElement;
//@XmlRootElement
public class Ingredient {

    private String name;
    private Double price;
    private Integer id;

    public Ingredient() {
    }

    public Ingredient(String name, Double price, Integer id) {
        this.name = name;
        this.price = price;
        this.id = id;
    }

    public void setName(String newValue) {
        name = newValue;
    }

    public String getName() {
        return name;
    }

    public void setPrice(Double newValue) {
        price = newValue;
    }

    public Double getPrice() {
        return price;
    }

    public void setId(Integer newValue) {
        id = newValue;
    }

    public Integer getId() {
        return id;
    }

}
