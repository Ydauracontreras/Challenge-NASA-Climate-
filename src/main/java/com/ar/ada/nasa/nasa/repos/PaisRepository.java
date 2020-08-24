package com.ar.ada.nasa.nasa.repos;

import com.ar.ada.nasa.nasa.entity.Pais;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaisRepository extends JpaRepository<Pais, Integer> {

}