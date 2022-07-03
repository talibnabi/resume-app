package dao.inter;

import entity.EmploymentHistory;

import java.util.List;

public interface EmploymentHistoryDaoInter {
    public List<EmploymentHistory> getAll();

    public EmploymentHistory getById(int id);

    public boolean updateEmploymentHistory(EmploymentHistory employmentHistory);

    public boolean removeEmploymentHistory(int id);

    public boolean addEmploymentHistory(EmploymentHistory employmentHistory);
}
