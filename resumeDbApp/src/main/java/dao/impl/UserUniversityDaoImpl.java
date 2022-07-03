package dao.impl;

import dao.inter.AbstractDAO;
import dao.inter.UniversityDaoInter;
import dao.inter.UserDaoInter;
import dao.inter.UserUniversityDaoInter;
import entity.University;
import entity.User;
import entity.UserUniversity;
import main.Context;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserUniversityDaoImpl extends AbstractDAO implements UserUniversityDaoInter {
    
    private static UserUniversity getUserUniversity(ResultSet rs) {
        try {
            int id = rs.getInt("id");
            Date beginDate = rs.getDate("begin_date");
            Date endDate = rs.getDate("end_date");
            int userId = rs.getInt("user_id");
            int universityId = rs.getInt("university_id");
            int gpa = rs.getInt("gpa");
            UserDaoInter userDaoInter = Context.instanceUserDao();
            User user = userDaoInter.getById(userId);
            
            UniversityDaoInter universityDaoInter = Context.instanceUniversityDao();
            University university = universityDaoInter.getById(universityId);
            
            return new UserUniversity(id, beginDate, endDate, gpa, user, university);
        } catch (SQLException ex) {
            Logger.getLogger(UserUniversityDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<UserUniversity> getAll() {
        try {
            List<UserUniversity> result = new ArrayList<>();
            Connection connection = connect();
            PreparedStatement stmt = connection.prepareStatement("select * from user_university");
            stmt.execute();
            ResultSet rs = stmt.getResultSet();
            while (rs.next()) {
                result.add(getUserUniversity(rs));
            }
            connection.close();
            return result;
        } catch (SQLException ex) {
            Logger.getLogger(UserUniversityDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public UserUniversity getById(int userUniversityId) {
        try {
            UserUniversity university = null;
            Connection connection = connect();
            PreparedStatement stmt = connection.prepareStatement("select * from user_university where id=" + userUniversityId);
            stmt.execute();
            ResultSet rs = stmt.getResultSet();
            while (rs.next()) {
                university = getUserUniversity(rs);
            }
            return university;
        } catch (SQLException ex) {
            Logger.getLogger(UserUniversityDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public boolean updateUserUniversity(UserUniversity userUniversity) {
        try {
            Connection connection = connect();
            PreparedStatement stmt = connection.prepareStatement("update user_university set user_id=?,university_id=?,gpa=?,begin_date=?,end_date=? where id=?");
            stmt.setInt(1, userUniversity.getUser().getId());
            stmt.setInt(2, userUniversity.getUniversity().getId());
            stmt.setInt(3, userUniversity.getGpa());
            stmt.setDate(4, userUniversity.getBeginDate());
            stmt.setDate(5, userUniversity.getEndDate());
            stmt.setInt(6, userUniversity.getId());
            return stmt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(UserUniversityDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean removeUserUniversity(int userUniversityId) {
        try {
            Connection connection = connect();
            PreparedStatement stmt = connection.prepareStatement("delete from user_university where id=" + userUniversityId);
            return stmt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(UserUniversityDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean addUserUniversity(UserUniversity userUniversity) {
        try {
            Connection connection = connect();
            PreparedStatement stmt = connection.prepareStatement("insert into user_university(user_id,university_id,gpa,begin_date,end_date) values(?,?,?,?,?)");
            stmt.setInt(1, userUniversity.getUser().getId());
            stmt.setInt(2, userUniversity.getUniversity().getId());
            stmt.setInt(3, userUniversity.getGpa());
            stmt.setDate(4, userUniversity.getBeginDate());
            stmt.setDate(5, userUniversity.getEndDate());
            return stmt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(UserUniversityDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
