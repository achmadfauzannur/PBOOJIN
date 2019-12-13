/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Backend;

import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author oj
 */
public class Produk extends Kategori implements ICrud{
    private int product_id, product_price, product_cat;
    private Kategori kategori;
    private String product_brand, product_title, product_desc, product_keyword, cat_title; 

    public Kategori getKategori() {
        return kategori;
    }

    public void setKategori(Kategori kategori) {
        this.kategori = kategori;
    }
    
    public String getCat_title() {
        return cat_title;
    }

    public void setCat_title(String cat_title) {
        this.cat_title = cat_title;
    }

    public Produk(int product_id, int product_price, int product_cat, String product_brand, String product_title, String product_desc, String product_keyword) {
        this.product_id = product_id;
        this.product_price = product_price;
        this.product_cat = product_cat;
        this.product_brand = product_brand;
        this.product_title = product_title;
        this.product_desc = product_desc;
        this.product_keyword = product_keyword;
    }

    public Produk() {
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public int getProduct_price() {
        return product_price;
    }

    public void setProduct_price(int product_price) {
        this.product_price = product_price;
    }

    public int getProduct_cat() {
        return product_cat;
    }

    public void setProduct_cat(int product_cat) {
        this.product_cat = product_cat;
    }

    public String getProduct_brand() {
        return product_brand;
    }

    public void setProduct_brand(String product_brand) {
        this.product_brand = product_brand;
    }

    public String getProduct_title() {
        return product_title;
    }

    public void setProduct_title(String product_title) {
        this.product_title = product_title;
    }

    public String getProduct_desc() {
        return product_desc;
    }

    public void setProduct_desc(String product_desc) {
        this.product_desc = product_desc;
    }

    public String getProduct_keyword() {
        return product_keyword;
    }

    public void setProduct_keyword(String product_keyword) {
        this.product_keyword = product_keyword;
    }
    
    public Produk getByProdukId(int id){
        Produk prd = new Produk();
        ResultSet rs = DBHelper.selectQuery("SELECT * FROM products WHERE product_id = '"+id+"'");
        try{
            while(rs.next()){
                prd = new Produk();
                prd.setProduct_brand(rs.getString("product_brand"));
                prd.setProduct_cat(rs.getInt("product_cat"));
                prd.setProduct_desc(rs.getString("product_desc"));
                prd.setProduct_id(rs.getInt("product_id"));
                prd.setProduct_keyword(rs.getString("product_keywords"));
                prd.setProduct_price(rs.getInt("product_price"));
                prd.setProduct_title(rs.getString("product_title"));
                
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return prd;
    }
    
    public ArrayList<Produk> getByCategories(int id){
        ArrayList<Produk> ListProduk = new ArrayList();
        this.kategori = super.getByCategoriesId(id);
        ResultSet rs = DBHelper.selectQuery("SELECT * FROM products WHERE product_cat = '"+id+"'");
        try{
            while(rs.next()){
                Produk prd = new Produk();
                prd.setProduct_brand(rs.getString("product_brand"));
                prd.setProduct_id(rs.getInt("product_id"));
                prd.setProduct_desc(rs.getString("product_desc"));
                prd.setProduct_id(rs.getInt("product_id"));
                prd.setCat_title(kategori.getNama());
                prd.setProduct_keyword(rs.getString("product_keywords"));
                prd.setProduct_price(rs.getInt("product_price"));
                prd.setProduct_title(rs.getString("product_title"));
                ListProduk.add(prd);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return ListProduk;
    }
    
    public ArrayList<Produk> getAllProduk(){
        ArrayList<Produk> ListProduk = new ArrayList();
        ResultSet rs = DBHelper.selectQuery("SELECT * FROM products");
        try{
             while(rs.next()){
                Produk prd = new Produk();
                prd.setProduct_brand(rs.getString("product_brand"));
                prd.setProduct_cat(rs.getInt("product_cat"));
                prd.setProduct_desc(rs.getString("product_desc"));
                prd.setProduct_id(rs.getInt("product_id"));
                prd.setProduct_keyword(rs.getString("product_keywords"));
                prd.setProduct_price(rs.getInt("product_price"));
                prd.setProduct_title(rs.getString("product_title"));
                ListProduk.add(prd);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return ListProduk;
    }
    
    public ArrayList<Produk> searchProduk(String keyword){
        ArrayList<Produk> ListProduk = new ArrayList();
        String sql = "SELECT * FROM products WHERE "+
                " products_title LIKE '%"+keyword+"%' "+
                " OR product_desc LIKE '%"+keyword+"'";
        ResultSet rs = DBHelper.selectQuery(sql);
        
        try{
             while(rs.next()){
                Produk prd = new Produk();
                prd.setProduct_brand(rs.getString("product_brand"));
                prd.setProduct_cat(rs.getInt("product_cat"));
                prd.setProduct_desc(rs.getString("product_desc"));
                prd.setProduct_id(rs.getInt("product_id"));
                prd.setProduct_keyword(rs.getString("product_keywords"));
                prd.setProduct_price(rs.getInt("product_price"));
                prd.setProduct_title(rs.getString("product_title"));
                ListProduk.add(prd);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return ListProduk;
    }
    
    public void save(){
        if(getByProdukId(product_id).getProduct_id()==0){
            String SQL = "INSERT INTO products (product_price, product_cat, product_brand, product_title, product_desc, product_keyword) VALUES ("+
                    " '"+this.product_price+"' "+
                    " '"+this.product_cat+"' "+
                    " '"+this.product_brand+"' "+
                    " '"+this.product_title+"' "+
                    " '"+this.product_desc+"' "+
                    " '"+this.product_keyword+"' "+
                    " )";
            this.product_id = DBHelper.insertQueryGetId(SQL);
           
        } else {
            String SQL = "UPDATE products SET "+
                    "product_price = '"+this.product_price+"', " +
                    "product_cat = '"+this.product_cat+"', " +
                    "product_brand = '"+this.product_brand+"', " +
                    "product_title = '"+this.product_title+"', " +
                    "product_desc = '"+this.product_desc+"', " +
                    "product_keywords = '"+this.product_keyword+"' "+
                    "WHERE product_id = '"+this.product_id+"'";
            DBHelper.executeQuery(SQL);
        }
    }
    
    public void delete(){
        String SQL = "DELETE FROM products WHERE product_id = '"+this.product_id+"'";
        DBHelper.executeQuery(SQL);
    }
        
        
}
