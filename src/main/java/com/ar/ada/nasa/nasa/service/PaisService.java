package com.ar.ada.nasa.nasa.service;

import java.util.List;
import java.util.Optional;

import com.ar.ada.nasa.nasa.entity.Pais;
import com.ar.ada.nasa.nasa.model.request.PaisUpdate;
import com.ar.ada.nasa.nasa.repos.PaisRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaisService {

    @Autowired
    PaisRepository paisRepository;

    public boolean grabar(Pais pais) {
        paisRepository.save(pais);
        return true;
    }

    public Pais crearPais(int paisId, String nombre) {
        Pais pais = new Pais();
        pais.setPaisId(paisId);
        pais.setNombre(nombre);
        grabar(pais);
        return pais;

    }

    public List<Pais> listarPaises() {
        return paisRepository.findAll();
    }

    public Pais listarPaisesSegunId(int id) {
        Optional<Pais> pais = paisRepository.findById(id);
        if (pais.isPresent())
            return pais.get();
        return null;

    }

    public boolean actualizarNombrePais(Pais paisOriginal, Pais paisModificado) {
        paisOriginal.setNombre(paisModificado.getNombre());
        grabar(paisOriginal);
        return true;

    }

}
