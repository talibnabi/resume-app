package entity;

public class Country {
    private Integer id;
    private String birthplace;
    private String nationality;

    public Country() {

    }

    public Country(Integer id, String birthplace, String nationality) {
        this.id = id;
        this.birthplace = birthplace;
        this.nationality = nationality;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBirthplace() {
        return birthplace;
    }

    public void setBirthplace(String birthplace) {
        this.birthplace = birthplace;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    @Override
    public String toString() {
        return "Country{" +
                "id=" + id +
                ", birthplace='" + birthplace + '\'' +
                ", nationality='" + nationality + '\'' +
                '}';
    }
}
