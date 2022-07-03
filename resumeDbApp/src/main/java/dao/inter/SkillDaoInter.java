package dao.inter;

import entity.Skill;

import java.util.List;

public interface SkillDaoInter {
    public List<Skill> getAll();

    public Skill getById(int id);

    public boolean updateSkill(Skill skill);

    public boolean addSkill(Skill skill);

}
