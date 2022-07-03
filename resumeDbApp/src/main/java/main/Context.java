package main;

import dao.impl.*;
import dao.inter.*;

public class Context {
    public static UserDaoInter instanceUserDao() {
        return new UserDaoImpl();
    }

    public static CountryDaoInter instanceCountryDao() {
        return new CountryDaoImpl();
    }

    public static EmploymentHistoryDaoInter instanceEmploymentHistoryDao() {
        return new EmploymentHistoryDaoImpl();
    }

    public static SkillDaoInter instanceSkillDao() {
        return new SkillDaoImpl();
    }

    public static UserSkillDaoInter instanceUserSkillDao() {
        return new UserSkillDaoImpl();
    }

    public static UniversityDaoInter instanceUniversityDao() {
        return new UniversityDaoImpl();
    }

    public static UserUniversityDaoInter instanceUserUniversityDao() {
        return new UserUniversityDaoImpl();
    }


}
