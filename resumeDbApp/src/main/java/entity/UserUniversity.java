package entity;

import java.sql.Date;

public class UserUniversity {
    private Integer id;
    private Date beginDate;
    private Date endDate;
    private int gpa;
    private User user;
    private University university;

    public UserUniversity() {

    }

    public UserUniversity(Integer id, Date beginDate, Date endDate, int gpa, User user, University university) {
        this.id = id;
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.gpa = gpa;
        this.user = user;
        this.university = university;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getGpa() {
        return gpa;
    }

    public void setGpa(int gpa) {
        this.gpa = gpa;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public University getUniversity() {
        return university;
    }

    public void setUniversity(University university) {
        this.university = university;
    }

    @Override
    public String toString() {
        return "UserUniversity{" +
                "id=" + id +
                ", beginDate=" + beginDate +
                ", endDate=" + endDate +
                ", gpa=" + gpa +
                ", user=" + user +
                ", university=" + university +
                '}';
    }
}
