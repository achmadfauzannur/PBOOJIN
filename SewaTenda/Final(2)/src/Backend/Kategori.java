/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Backend;


import Backend.DBHelper;    
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author oj
 */
public class Kategori implements ICrud{
    private int cat_id;
    private String cat_title;

    public Kategori() {
    }

    public Kategori(String nama) {
        this.cat_title = nama;
    }

    public void setIdkategori(int idkategori) {
        this.cat_id = idkategori;
    }

    public void setNama(String nama) {
        this.cat_title = nama;
    }

    public int getIdkategori() {
        return cat_id;
    }

    public String getNama() {
        return cat_title;
    }
    
    
    public Kategori getByCategoriesId(int id){
        Kategori kat = new Kategori();
        ResultSet rs = DBHelper.selectQuery("SELECT * FROM categories WHERE cat_id = '"+id+"'");
        try{
            while(rs.next()){
                kat = new Kategori();
                kat.setIdkategori(rs.getInt("cat_id"));
                kat.setNama(rs.getString("cat_title"));
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return kat;
    }
    
    public ArrayList<Kategori> getAllCategories(){
        ArrayList<Kategori> ListKategori = new ArrayList();
        ResultSet rs = DBHelper.selectQuery("SELECT * FROM categories");
        try{
             while(rs.next()){
                Kategori kat = new Kategori();
                kat.setIdkategori(rs.getInt("cat_id"));
                kat.setNama(rs.getString("cat_title"));
                ListKategori.add(kat);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return ListKategori;
    }
    
    public ArrayList<Kategori> searchCategories(String keyword){
        ArrayList<Kategori> ListKategori = new ArrayList();
        String sql = "SELECT * FROM categories WHERE "+
                " cat_title LIKE '%"+keyword+"%' ";
        ResultSet rs = DBHelper.selectQuery(sql);
        
        try{
             while(rs.next()){
                Kategori kat = new Kategori();
                kat.setIdkategori(rs.getInt("cat_id"));
                kat.setNama(rs.getString("cat_title"));
                
                ListKategori.add(kat);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return ListKategori;
    }
    
    public void save(){
        if(getByCategoriesId(cat_id).getIdkategori()==0){
            String SQL = "INSERT INTO categories (cat_title) VALUES ("+
                    " '"+this.cat_title+"'"+
                    " )";
            this.cat_id = DBHelper.insertQueryGetId(SQL);
           
        } else {
            String SQL = "UPDATE categories SET "+
                    "cat_title = '"+this.cat_title+"'" +
                    "WHERE cat_id = '"+this.cat_id+"'";
            DBHelper.executeQuery(SQL);
        }
    }
    public void delete(){
        String SQL = "DELETE FROM categories WHERE cat_id = '"+this.cat_id+"'";
        DBHelper.executeQuery(SQL);
    }
}
