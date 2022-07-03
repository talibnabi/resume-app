package dao.impl;

import dao.inter.AbstractDAO;
import dao.inter.UniversityDaoInter;
import entity.University;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UniversityDaoImpl extends AbstractDAO implements UniversityDaoInter {
    
    private static University getUniversity(ResultSet rs) {
        try {
            int id = rs.getInt("id");
            String universityName = rs.getString("university_name");
            return new University(id, universityName);
        } catch (SQLException ex) {
            Logger.getLogger(UniversityDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    
    @Override
    public List<University> getAll() {
        try {
            List<University> result = new ArrayList<>();
            Connection connection = connect();
            PreparedStatement stmt = connection.prepareStatement("select * from university");
            stmt.execute();
            ResultSet rs = stmt.getResultSet();
            while (rs.next()) {
                result.add(getUniversity(rs));
            }
            connection.close();
            return result;
        } catch (SQLException ex) {
            Logger.getLogger(UniversityDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public University getById(int universityId) {
        try {
            University university = null;
            Connection connection = connect();
            PreparedStatement stmt = connection.prepareStatement("select * from university where id=" + universityId);
            stmt.execute();
            ResultSet rs = stmt.getResultSet();
            while (rs.next()) {
                university = getUniversity(rs);
            }
            connection.close();
            return university;
        } catch (SQLException ex) {
            Logger.getLogger(UniversityDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public boolean updateUniversity(University university) {
        try {
            Connection connection = connect();
            PreparedStatement stmt = connection.prepareStatement("update university set university_name=? where id=?");
            stmt.setString(1, university.getUniversityName());
            stmt.setInt(2, university.getId());
            return stmt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(UniversityDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean addUniversity(University university) {
        try {
            Connection connection = connect();
            PreparedStatement stmt = connection.prepareStatement("insert into university(university_name) values(?)");
            stmt.setString(1, university.getUniversityName());
            return stmt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(UniversityDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
