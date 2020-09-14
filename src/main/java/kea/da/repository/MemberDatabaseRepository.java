package kea.da.repository;

import kea.da.model.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class MemberDatabaseRepository implements IMember{

    //connect to MySQL database
    @Autowired
    JdbcTemplate jdbcTemplate;
    SqlRowSet sqlRowSet;


    @Override
    public boolean create(Member memberToBeAdded) {
        return false;
    }

    @Override
    public Member read(String email) {
        // create a MySQL query and execute
        String sql = "SELECT * FROM loginDemo.members WHERE members.email = " + email;
        sqlRowSet = jdbcTemplate.queryForRowSet(sql);

        while(sqlRowSet.next()){
            return new Member(sqlRowSet.getString("name"),sqlRowSet.getString("email"), sqlRowSet.getString("password"));
        }

        return null;
    }

    @Override
    public List<Member> readAll() {
        List<Member> members = new ArrayList<>();

        // create a MySQL query and execute
        String sql = "SELECT * FROM loginDemo.members";
        sqlRowSet = jdbcTemplate.queryForRowSet(sql);

        while(sqlRowSet.next()){
            members.add(new Member (sqlRowSet.getString("name"),sqlRowSet.getString("email"), sqlRowSet.getString("password")));
        }

        return members;
    }
}
