/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Backend;

/**
 *
 * @author oj
 */
public final class SQL {
    public static final String ALL_CATEGORIES = "SELECT * FROM categories";
    public static final String ALL_PRODUCT = "SELECT * FROM products";
    public static final String INSERT_PRODUCT = "INSERT INTO products (product_price, product_cat, product_brand, product_title, product_desc, product_keyword) VALUES (";
}
