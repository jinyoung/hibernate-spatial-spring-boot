package com.demo.petstore.domain;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.vividsolutions.jts.geom.Geometry;

public interface CatRepository extends JpaRepository<Cat, Integer>{
   
    @Query("SELECT p FROM Pet p WHERE within(p.point, :filter) = true")
    List<Cat> findCatWithin(@Param("filter") Geometry filter);
}
