package com.codegym.repository;

import com.codegym.model.Picture;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface IPictureRepository extends PagingAndSortingRepository<Picture, Integer> {
    @Transactional
    @Modifying
    @Query("update Picture set likes = likes + 1 where id = :id")
    void like(@Param("id") int id);
}
