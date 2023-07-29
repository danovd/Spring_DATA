package com.example.football.repository;


import com.example.football.models.entity.Town;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TownRepository  extends JpaRepository<Town, Long> {
}
