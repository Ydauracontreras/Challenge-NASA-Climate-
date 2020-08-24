package com.ar.ada.nasa.nasa.entity;

import javax.persistence.*;

@Entity
@Table(name = "temperatura")
public class Temperatura {
    @Id
    @Column(name = "id_temperatura")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer temperaturaId;
    @Column(name = "ano_temperatura")
    private int anoTemperatura;
    private double grados;
    @ManyToOne
    @JoinColumn(name = "id_pais", referencedColumnName = "id_pais")
    private Pais pais;

    /**
     * @return the temperaturaId
     */
    public Integer getTemperaturaId() {
        return temperaturaId;
    }

    /**
     * @param temperaturaId the temperaturaId to set
     */
    public void setTemperaturaId(Integer temperaturaId) {
        this.temperaturaId = temperaturaId;
    }

    /**
     * @return the anoTemperatura
     */
    public int getAnoTemperatura() {
        return anoTemperatura;
    }

    /**
     * @param anoTemperatura the anoTemperatura to set
     */
    public void setAnoTemperatura(int anoTemperatura) {
        this.anoTemperatura = anoTemperatura;
    }

    /**
     * @return the grados
     */
    public double getGrados() {
        return grados;
    }

    /**
     * @param grados the grados to set
     */
    public void setGrados(double grados) {
        this.grados = grados;
    }

    /**
     * @return the pais
     */
    public Pais getPais() {
        return pais;
    }

    /**
     * @param pais the pais to set
     */
    public void setPais(Pais pais) {
        this.pais = pais;
        this.pais.getTemperaturas().add(this);
    }

}