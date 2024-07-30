package com.namji.datacollection.repository;

import com.namji.datacollection.entity.Data;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DataJpaRepository extends JpaRepository<Data, Long> {
}
