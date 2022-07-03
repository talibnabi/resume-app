package dao.impl;

import dao.inter.AbstractDAO;
import dao.inter.EmploymentHistoryDaoInter;
import dao.inter.UserDaoInter;
import entity.EmploymentHistory;
import entity.User;
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

public class EmploymentHistoryDaoImpl extends AbstractDAO implements EmploymentHistoryDaoInter {

    private static EmploymentHistory getEmploymentHistory(ResultSet rs) {
        try {
            int id = rs.getInt("id");
            String header = rs.getString("header");
            Date beginDate = rs.getDate("begin_date");
            Date endDate = rs.getDate("end_date");
            String jobDescription = rs.getString("job_description");
            int userId = rs.getInt("user_id");
            UserDaoInter userDaoInter = Context.instanceUserDao();
            User user = userDaoInter.getById(userId);
            return new EmploymentHistory(id, header, beginDate, endDate, jobDescription, user);
        } catch (SQLException ex) {
            Logger.getLogger(EmploymentHistoryDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<EmploymentHistory> getAll() {
        try {
            List<EmploymentHistory> result = new ArrayList<EmploymentHistory>();
            Connection connection = connect();
            PreparedStatement stmt = connection.prepareStatement("select * from employment_history");
            stmt.execute();
            ResultSet rs = stmt.getResultSet();
            while (rs.next()) {
                result.add(getEmploymentHistory(rs));
            }
            connection.close();
            return result;
        } catch (SQLException ex) {
            Logger.getLogger(EmploymentHistoryDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

   
    @Override
    public EmploymentHistory getById(int employeeId) {
        try {
            EmploymentHistory employmentHistory = null;
            Connection connection = connect();
            PreparedStatement stmt = connection.prepareStatement("select * from employment_history where id=" + employeeId);
            stmt.execute();
            ResultSet rs = stmt.getResultSet();
            while (rs.next()) {
                employmentHistory = getEmploymentHistory(rs);
            }
            connection.close();
            return employmentHistory;
        } catch (SQLException ex) {
            Logger.getLogger(EmploymentHistoryDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

   
    @Override
    public boolean updateEmploymentHistory(EmploymentHistory employmentHistory) {
        try {
            Connection connection = connect();
            PreparedStatement stmt = connection.prepareStatement("update employment_history set header=?,begin_date=?,end_date=?,job_description=?,user_id=? where id=?");
            stmt.setString(1, employmentHistory.getHeader());
            stmt.setDate(2, employmentHistory.getBeginDate());
            stmt.setDate(3, employmentHistory.getEndDate());
            stmt.setString(4, employmentHistory.getJobDescription());
            stmt.setInt(5, employmentHistory.getUser().getId());
            stmt.setInt(6, employmentHistory.getId());
            return stmt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(EmploymentHistoryDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    
    @Override
    public boolean removeEmploymentHistory(int userId) {
        try {
            Connection connection = connect();
            PreparedStatement stmt = connection.prepareStatement("delete from employment_history where id=" + userId);
            return stmt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(EmploymentHistoryDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

   
    @Override
    public boolean addEmploymentHistory(EmploymentHistory employmentHistory) {
        try {
            Connection connection = connect();
            PreparedStatement stmt = connection.prepareStatement("insert into employment_history(header,begin_date,end_date,job_description,user_id) values(?,?,?,?,?)");
            stmt.setString(1, employmentHistory.getHeader());
            stmt.setDate(2, employmentHistory.getBeginDate());
            stmt.setDate(3, employmentHistory.getEndDate());
            stmt.setString(4, employmentHistory.getJobDescription());
            stmt.setInt(5, employmentHistory.getUser().getId());
            return stmt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(EmploymentHistoryDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
