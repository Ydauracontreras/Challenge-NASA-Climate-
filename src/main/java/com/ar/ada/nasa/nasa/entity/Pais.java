package com.ar.ada.nasa.nasa.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name = "pais")
public class Pais {
    @Id
    @Column(name = "id_pais")

    private Integer paisId;
    private String nombre;
    @OneToMany(mappedBy = "pais", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Temperatura> temperaturas = new ArrayList<>();

    /**
     * @return the paisId
     */
    public Integer getPaisId() {
        return paisId;
    }

    /**
     * @param paisId the paisId to set
     */
    public void setPaisId(Integer paisId) {
        this.paisId = paisId;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the temperatura
     */
    public List<Temperatura> getTemperaturas() {
        return temperaturas;
    }

    /**
     * @param temperatura the temperatura to set
     */
    public void setTemperaturas(List<Temperatura> temperaturas) {
        this.temperaturas = temperaturas;
    }

}