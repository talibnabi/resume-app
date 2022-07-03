package dao.impl;

import dao.inter.AbstractDAO;
import dao.inter.SkillDaoInter;
import entity.Skill;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SkillDaoImpl extends AbstractDAO implements SkillDaoInter {

    private static Skill getSkill(ResultSet rs) {
        try {
            int id = rs.getInt("id");
            String skillName = rs.getString("skill_name");
            return new Skill(id, skillName);
        } catch (SQLException ex) {
            Logger.getLogger(SkillDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }


    @Override
    public List<Skill> getAll() {
        try {
            List<Skill> result = new ArrayList<>();
            Connection connection = connect();
            PreparedStatement stmt = connection.prepareStatement("select * from skill");
            stmt.execute();
            ResultSet rs = stmt.getResultSet();
            while (rs.next()) {
                result.add(getSkill(rs));
            }
            connection.close();
            return result;
        } catch (SQLException ex) {
            Logger.getLogger(SkillDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }


    @Override
    public Skill getById(int skillId) {
        try {
            Skill skill = null;
            Connection connection = connect();
            PreparedStatement stmt = connection.prepareStatement("select * from skill where id=" + skillId);
            stmt.execute();
            ResultSet rs = stmt.getResultSet();
            while (rs.next()) {
                skill = getSkill(rs);
            }
            connection.close();
            return skill;
        } catch (SQLException ex) {
            Logger.getLogger(SkillDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }


    @Override
    public boolean updateSkill(Skill skill) {
        try {
            Connection connection = connect();
            PreparedStatement stmt = connection.prepareStatement("update skill set skill_name=? where id=?");
            stmt.setString(1, skill.getSkillName());
            stmt.setInt(2, skill.getId());
            return stmt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(SkillDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }


    @Override
    public boolean addSkill(Skill skill) {
        try {
            Connection connection = connect();
            PreparedStatement stmt = connection.prepareStatement("insert into skill(skill_name) values(?)");
            stmt.setString(1, skill.getSkillName());
            return stmt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(SkillDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
