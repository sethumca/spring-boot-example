package org.javacampaign.springbootapp.controller;

import java.util.List;

import org.javacampaign.springbootapp.repo.City;
import org.javacampaign.springbootapp.repo.CityDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Transactional
public class CityController {

    private CityDao dao;

    @Autowired
    public CityController(CityDao dao) {
        this.dao = dao;
    }

    @GetMapping("/")
    public List<City> selectAll() {
        return dao.findAll();
    }

    @GetMapping("/update")
    public List<City> updateAndSelectAll(
            @RequestParam(value = "id", defaultValue = "1") int id,
            @RequestParam("name") String name) {
        City city = dao.findOne(id);
        city.setName(name);
        dao.save(city);
        return dao.findAll();
    }

}
