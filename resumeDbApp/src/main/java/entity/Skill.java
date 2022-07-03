package entity;

public class Skill {
    private Integer id;
    private String skillName;

    public Skill() {

    }

    public Skill(Integer id, String skillName) {
        this.id = id;
        this.skillName = skillName;
    }

    public Skill(int skillId) {
        this.id = skillId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSkillName() {
        return skillName;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }

    @Override
    public String toString() {
        return "Skill{" +
                "id=" + id +
                ", skillName='" + skillName + '\'' +
                '}';
    }
}
