/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;
/**
 *
 * @author ramir
 */
public class Product{
    
    int id;
    int quantity;
    int temp_quantity; // Used to know how much of each product a client will buy.
    Double price;
    String img_url;
    String brand;
    String category;
    String name;
    String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public int getQuantity() {
        return quantity;
    }

    public int getTemp_quantity() {
        return temp_quantity;
    }

    public void setTemp_quantity(int temp_quantity) {
        this.temp_quantity = temp_quantity;
    }
    
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Product{" + "id=" + id + ", quantity=" + quantity + ", temp_quantity=" + temp_quantity + ", price=" + price + ", img_url=" + img_url + ", brand=" + brand + ", category=" + category + ", name=" + name + ", description=" + description + '}';
    }
          
}