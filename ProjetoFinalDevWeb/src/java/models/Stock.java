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
public class Stock {
    
    int id;
    int product_id;
    int quantify;

    public int getId() {
        return id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public int getQuantify() {
        return quantify;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public void setQuantify(int quantify) {
        this.quantify = quantify;
    }

    @Override
    public String toString() {
        return "Stock{" + "id=" + id + ", product_id=" + product_id + ", quantify=" + quantify + '}';
    }  
    
}
