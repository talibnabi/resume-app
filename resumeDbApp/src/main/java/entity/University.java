package entity;

public class University {
    private Integer id;
    private String universityName;

    public University() {

    }

    public University(Integer id, String universityName) {
        this.id = id;
        this.universityName = universityName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUniversityName() {
        return universityName;
    }

    public void setUniversityName(String universityName) {
        this.universityName = universityName;
    }

    @Override
    public String toString() {
        return "University{" +
                "id=" + id +
                ", universityName='" + universityName + '\'' +
                '}';
    }
}
