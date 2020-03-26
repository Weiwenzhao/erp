package com.hk.wwz.dao;

import com.hk.wwz.pojo.Depart;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartMapper {
    List<Depart> getAllDepart();

    Depart getDepart(String id);

    int deleteDepart(List<String> deleteDepartList);

    int addDepart(Depart depart);

    int addDepartList(List<Depart> departList);

}
