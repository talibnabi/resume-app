package dao.inter;

import entity.UserUniversity;

import java.util.List;

public interface UserUniversityDaoInter {
    public List<UserUniversity> getAll();

    public UserUniversity getById(int id);

    public boolean updateUserUniversity(UserUniversity userUniversity);

    public boolean removeUserUniversity(int userUniversityId);

    public boolean addUserUniversity(UserUniversity userUniversity);
}
