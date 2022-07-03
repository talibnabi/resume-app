package dao.impl;

import dao.inter.AbstractDAO;
import dao.inter.SkillDaoInter;
import dao.inter.UserDaoInter;
import dao.inter.UserSkillDaoInter;
import entity.Skill;
import entity.User;
import entity.UserSkill;
import main.Context;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserSkillDaoImpl extends AbstractDAO implements UserSkillDaoInter {
    private static UserSkill getUserSkill(ResultSet rs) {
        try {
            int id = rs.getInt("id");
            int userId = rs.getInt("user_id");
            int skillId = rs.getInt("skill_id");
            
            int power = rs.getInt("power");
            UserDaoInter userDaoInter = Context.instanceUserDao();
            User user = userDaoInter.getById(userId);
            
            SkillDaoInter skillDaoInter = Context.instanceSkillDao();
            Skill skill = skillDaoInter.getById(skillId);
            return new UserSkill(id, user, skill, power);
        } catch (SQLException ex) {
            Logger.getLogger(UserSkillDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<UserSkill> getAll() {
        try {
            List<UserSkill> result = new ArrayList<>();
            Connection connection = connect();
            PreparedStatement stmt = connection.prepareStatement("select * from user_skill");
            stmt.execute();
            ResultSet rs = stmt.getResultSet();
            while (rs.next()) {
                result.add(getUserSkill(rs));
            }
            connection.close();
            return result;
        } catch (SQLException ex) {
            Logger.getLogger(UserSkillDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public UserSkill getById(int userSkillId) {
        try {
            UserSkill userSkill = null;
            Connection connection = connect();
            PreparedStatement stmt = connection.prepareStatement("select * from user_skill where id=" + userSkillId);
            stmt.execute();
            ResultSet rs = stmt.getResultSet();
            while (rs.next()) {
                userSkill = getUserSkill(rs);
            }
            connection.close();
            return userSkill;
        } catch (SQLException ex) {
            Logger.getLogger(UserSkillDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public boolean updateUserSkill(UserSkill userSkill) {
        try {
            Connection connection = connect();
            PreparedStatement stmt = connection.prepareStatement("update user_skill set user_id=?,skill_id=?,power=? where id=?");
            stmt.setInt(1, userSkill.getUser().getId());
            stmt.setInt(2, userSkill.getSkill().getId());
            stmt.setInt(3, userSkill.getPower());
            stmt.setInt(4, userSkill.getId());
            return stmt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(UserSkillDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean removeUserSkill(int userSkillId) {
        try {
            Connection connection = connect();
            PreparedStatement stmt = connection.prepareStatement("delete from user_skill where id=" + userSkillId);
            return stmt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(UserSkillDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean addUserSkill(UserSkill userSkill) {
        try {
            Connection connection = connect();
            PreparedStatement stmt = connection.prepareStatement("insert into user_skill(user_id,skill_id,power) values(?,?,?)");
            stmt.setInt(1, userSkill.getUser().getId());
            stmt.setInt(2, userSkill.getSkill().getId());
            stmt.setInt(3, userSkill.getPower());
            return stmt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(UserSkillDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
