package dao.inter;

import entity.University;

import java.util.List;

public interface UniversityDaoInter {
    public List<University> getAll();

    public University getById(int id);

    public boolean updateUniversity(University university);

    public boolean addUniversity(University university);
}
