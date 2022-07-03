package dao.inter;

import entity.Country;

import java.util.List;

public interface CountryDaoInter {
    public List<Country> getAll();

    public Country getById(int id);

    public boolean updateCountry(Country country);


    public boolean addCountry(Country country);
}
