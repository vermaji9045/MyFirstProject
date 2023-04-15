package com.School.SchoolValleyProject.repository;

import com.School.SchoolValleyProject.ContactRowMapper.ContactRowMapper;
import com.School.SchoolValleyProject.Model.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class ContactRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public ContactRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int saveContactMsg(Contact contact){
        String sql = "INSERT INTO contact_msg (NAME,MOBILE_NUM,EMAIL,SUBJECT,MESSAGE,STATUS," +
                "CREATED_AT,CREATED_BY) VALUES (?,?,?,?,?,?,?,?)";
        return jdbcTemplate.update(sql,contact.getName(),contact.getMobileNum(),
                contact.getEmail(),contact.getSubject(),contact.getMessage(),
                contact.getStatus(),contact.getCreatedAt(),contact.getCreatedBy());
    }

    public  List<Contact> findMsgsWithStatus(String status)
    {
        String sql="SELECT * FROM contact_msg WHERE STATUS = ?";

        return jdbcTemplate.query(sql, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
             ps.setString(1,status);
            }
        },new ContactRowMapper());


    }

    public int updatemsgStatus(int contact_Id,String status,String Updated_By)
    {
        String sql="UPDATE contact_msg SET STATUS=?,UPDATED_BY=?,UPDATED_AT=? WHERE CONTACT_ID=? ";

        return jdbcTemplate.update(sql,new PreparedStatementSetter(){

            @Override
            public void setValues(PreparedStatement ps) throws SQLException {

                ps.setString(1,status);
                ps.setString(2,Updated_By);
                ps.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
                ps.setInt(4,contact_Id);
            }


        });

    }
}
