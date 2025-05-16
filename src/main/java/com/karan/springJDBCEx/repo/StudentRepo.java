package com.karan.springJDBCEx.repo;

import com.karan.springJDBCEx.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class StudentRepo {

    private JdbcTemplate jdbc;
    public JdbcTemplate  getJdbc() {
        return jdbc;
    }
    @Autowired
    public void setJdbc(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public void save(Student s) {
        String sql = "INSERT INTO STUDENT (rollno, name, marks) VALUES (?,?,?)";
        int rows = jdbc.update(sql, s.getRollno(), s.getName(), s.getMarks());
        System.out.println(rows + " rows affected");
    }

    public List<Student> findAll() {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM STUDENT";

        //using Mapper functional interface to map the result set to Student object
        students = jdbc.query(sql, (rs, rowNum) -> {
            Student s = new Student();
            s.setRollno(rs.getInt("rollno"));
            s.setName(rs.getString("name"));
            s.setMarks(rs.getInt("marks"));
            return s;
        });

        return students;
    }
}
