package kea.da.repository;

import kea.da.model.Member;

import java.util.List;

public interface IMember {

    boolean create(Member memberToBeAdded);

    Member read(String email);

    List<Member> readAll();
}
