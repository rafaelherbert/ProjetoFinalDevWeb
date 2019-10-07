/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;
import java.util.Date;

/**
 *
 * @author ramir
 */
public class Sell extends Model{
    
    int id;
    int user_id;
    int product_id;
    int quantify;
    java.util.Date creation_date = new java.util.Date();
    java.sql.Date sqlDate = new java.sql.Date(creation_date.getTime());

    public int getId() {
        return id;
    }

    public int getUser_id() {
        return user_id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public int getQuantify() {
        return quantify;
    }

    public Date getCreation_date() {
        return creation_date;
    }

    public java.sql.Date getSqlDate() {
        return sqlDate;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public void setQuantify(int quantify) {
        this.quantify = quantify;
    }

    public void setCreation_date(Date creation_date) {
        this.creation_date = creation_date;
    }

    public void setSqlDate(java.sql.Date sqlDate) {
        this.sqlDate = sqlDate;
    }

    @Override
    public String toString() {
        return "Sell{" + "id=" + id + ", user_id=" + user_id + ", product_id=" + product_id + ", quantify=" + quantify + ", creation_date=" + creation_date + ", sqlDate=" + sqlDate + '}';
    }
    
   
}
