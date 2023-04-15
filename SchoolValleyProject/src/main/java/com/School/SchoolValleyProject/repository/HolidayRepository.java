package com.School.SchoolValleyProject.repository;

import com.School.SchoolValleyProject.Model.Holiday;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class HolidayRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired

    public HolidayRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Holiday> findAllHoliday()
    {
        String sql="SELECT *FROM Holidays";

         BeanPropertyRowMapper<Holiday> rowmapper= BeanPropertyRowMapper.newInstance(Holiday.class);

     return (jdbcTemplate.query(sql,rowmapper));
    }
}
