package com.sveticov.st2020.repository;

import com.sveticov.st2020.model.SizeModelBox;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface RepositorySizeModelBox extends JpaRepository<SizeModelBox,Integer> {
}
