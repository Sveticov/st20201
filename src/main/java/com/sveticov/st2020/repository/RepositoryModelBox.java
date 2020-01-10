package com.sveticov.st2020.repository;

import com.sveticov.st2020.model.ModelBox;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RepositoryModelBox extends JpaRepository<ModelBox,Integer> {
@Query(value = "SELECT * FROM MODEL as m inner join SIZEBOX as s on m.id_size=s.id_size",nativeQuery = true)
   List<ModelBox> findAll();

}
