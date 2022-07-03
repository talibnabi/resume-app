package dao.impl;

import dao.inter.AbstractDAO;
import dao.inter.CountryDaoInter;
import entity.Country;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CountryDaoImpl extends AbstractDAO implements CountryDaoInter {
    
    private static Country getCountry(ResultSet rs) {
        try {
            int id = rs.getInt("id");
            String birthplace = rs.getString("birthplace");
            String nationality = rs.getString("nationality");
            return new Country(id, birthplace, nationality);
        } catch (SQLException ex) {
            Logger.getLogger(CountryDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

 
    @Override
    public List<Country> getAll() {
        try {
            List<Country> result = new ArrayList<Country>();
            Connection connection = connect();
            PreparedStatement stmt = connection.prepareStatement("select * from country");
            stmt.execute();
            ResultSet rs = stmt.getResultSet();
            while (rs.next()) {
                result.add(getCountry(rs));
            }
            connection.close();
            return result;
        } catch (SQLException ex) {
            Logger.getLogger(CountryDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

   
    @Override
    public Country getById(int userId) {
        try {
            Country country = null;
            Connection connection = connect();
            PreparedStatement stmt = connection.prepareStatement("select * from country where id=" + userId);
            stmt.execute();
            ResultSet rs = stmt.getResultSet();
            while (rs.next()) {
                country = getCountry(rs);
            }
            connection.close();
            return country;
        } catch (SQLException ex) {
            Logger.getLogger(CountryDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

 
    @Override
    public boolean updateCountry(Country country) {
        try {
            Connection connection = connect();
            PreparedStatement stmt = connection.prepareStatement("update country set birthplace=?,nationality=? where id=?");
            stmt.setString(1, country.getBirthplace());
            stmt.setString(2, country.getNationality());
            stmt.setInt(3, country.getId());
            return stmt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(CountryDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }


  
    @Override
    public boolean addCountry(Country country) {
        try {
            Connection connection = connect();
            PreparedStatement stmt = connection.prepareStatement("insert into country(birthplace,nationality) values (?,?)");
            stmt.setString(1, country.getBirthplace());
            stmt.setString(2, country.getNationality());
            return stmt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(CountryDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
