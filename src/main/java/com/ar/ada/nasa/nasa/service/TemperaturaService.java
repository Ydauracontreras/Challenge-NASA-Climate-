package com.ar.ada.nasa.nasa.service;

import java.util.List;
import java.util.Optional;

import com.ar.ada.nasa.nasa.entity.Temperatura;
import com.ar.ada.nasa.nasa.repos.TemperaturaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TemperaturaService {

    @Autowired
    TemperaturaRepository temperaturaRepository;

    @Autowired
    PaisService paisService;

    public boolean grabar(Temperatura temperatura) {
        temperaturaRepository.save(temperatura);
        return true;
    }

    public Temperatura crearTemperatura(int codigoPais, int anioTemperatura, double grados) {
        Temperatura temperatura = new Temperatura();
        temperatura.setPais(paisService.listarPaisesSegunId(codigoPais));
        temperatura.setAnoTemperatura(anioTemperatura);
        temperatura.setGrados(grados);
        for (Temperatura temp : temperaturaRepository.findAll()) {
            if (temp.getAnoTemperatura() == anioTemperatura)
                return null;
        }
        grabar(temperatura);
        return temperatura;
    }

    public Temperatura listarTemperaturaPorId(int id) {
        Optional<Temperatura> temperatura = temperaturaRepository.findById(id);
        if (temperatura.isPresent())
            return temperatura.get();
        return null;
    }

    public List<Temperatura> listarTemperaturas() {
        return temperaturaRepository.findAll();
    }

}