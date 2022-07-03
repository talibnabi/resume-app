package dao.inter;

import entity.UserSkill;

import java.util.List;

public interface UserSkillDaoInter {
    public List<UserSkill> getAll();

    public UserSkill getById(int id);

    public boolean updateUserSkill(UserSkill userSkill);

    public boolean removeUserSkill(int id);

    public boolean addUserSkill(UserSkill userSkill);
}
