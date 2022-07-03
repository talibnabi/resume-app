package dao.inter;

import entity.User;

import java.util.List;

public interface UserDaoInter {
    public List<User> getAll();

    public User getById(int userId);

    abstract boolean updateUser(User user);

    public boolean removeUser(int userId);

    public boolean addUser(User user);

}
