package dao.impl;

import dao.inter.AbstractDAO;
import dao.inter.UserDaoInter;
import entity.User;
import entity.Country;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserDaoImpl extends AbstractDAO implements UserDaoInter {
    private static User getUser(ResultSet rs) {
        try {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            String surname = rs.getString("surname");
            String email = rs.getString("email");
            String phone = rs.getString("phone");
            String profileDescription = rs.getString("profile_description");
            String address = rs.getString("address");
            Date birthdate = rs.getDate("birthdate");
            int birthplaceId = rs.getInt("birthplace_id");
            int nationalityId = rs.getInt("nationality_id");
            String birthplace = rs.getString("birthplace");
            String nationality = rs.getString("nationality");
            Country userBirthplace = new Country(birthplaceId, birthplace, null);
            Country userNationality = new Country(nationalityId, null, nationality);
            return new User(id, name, surname, email, phone, profileDescription, address, birthdate, userBirthplace, userNationality);
        } catch (SQLException ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<User> getAll() {
        try {
            List<User> result = new ArrayList<>();
            Connection connection = connect();
            PreparedStatement stmt = connection.prepareStatement("select u.*,c.birthplace,cc.nationality from user u \n" +
                    "left join country c on u.birthplace_id=c.id " +
                    "left join country cc on u.nationality_id=cc.id");
            stmt.execute();
            ResultSet rs = stmt.getResultSet();
            while (rs.next()) {
                result.add(getUser(rs));
            }
            connection.close();
            return result;
        } catch (SQLException ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public User getById(int userId) {
        try {
            User user = null;
            Connection connection = connect();
            PreparedStatement stmt = connection.prepareStatement("select u.*,c.birthplace,cc.nationality from user u " +
                    " left join country c on u.birthplace_id=c.id" +
                    " left join country cc on u.nationality_id=cc.id where u.id=" + userId);
            stmt.execute();
            ResultSet rs = stmt.getResultSet();
            while (rs.next()) {
                user = getUser(rs);
            }
            connection.close();
            return user;
        } catch (SQLException ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public boolean updateUser(User user) {
        try {
            Connection connection = connect();
            PreparedStatement stmt = connection.prepareStatement("update user set name=?,surname=?,email=?,phone=?,profile_description=?,address=?,birthdate=?,birthplace_id=?,nationality_id=? where id=?");
            stmt.setString(1, user.getName());
            stmt.setString(2, user.getSurname());
            stmt.setString(3, user.getEmail());
            stmt.setString(4, user.getPhone());
            stmt.setString(5, user.getProfileDescription());
            stmt.setString(6, user.getAddress());
            stmt.setDate(7, user.getBirthdate());
            stmt.setInt(8, user.getBirthplace().getId());
            stmt.setInt(9, user.getNationality().getId());
            stmt.setInt(10, user.getId());
            return stmt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean removeUser(int userId) {
        try {
            Connection connection = connect();
            PreparedStatement stmt = connection.prepareStatement("delete from user where id=" + userId);
            return stmt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    
    @Override
    public boolean addUser(User user) {
        try {
            Connection connection = connect();
            PreparedStatement stmt = connection.prepareStatement("insert into user(name,surname,email,phone,profile_description,address,birthdate,birthplace_id,nationality_id) values(?,?,?,?,?,?,?,?,?)");
            stmt.setString(1, user.getName());
            stmt.setString(2, user.getSurname());
            stmt.setString(3, user.getEmail());
            stmt.setString(4, user.getPhone());
            stmt.setString(5, user.getProfileDescription());
            stmt.setString(6, user.getAddress());
            stmt.setDate(7, user.getBirthdate());
            stmt.setInt(8, user.getBirthplace().getId());
            stmt.setInt(9, user.getNationality().getId());
            return stmt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }


}
