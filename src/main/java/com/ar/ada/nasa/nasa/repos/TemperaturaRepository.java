package com.ar.ada.nasa.nasa.repos;

import com.ar.ada.nasa.nasa.entity.Temperatura;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TemperaturaRepository extends JpaRepository<Temperatura, Integer> {

}