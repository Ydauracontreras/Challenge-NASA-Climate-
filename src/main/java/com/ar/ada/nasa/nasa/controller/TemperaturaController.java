package com.ar.ada.nasa.nasa.controller;

import java.util.ArrayList;
import java.util.List;

import com.ar.ada.nasa.nasa.entity.Temperatura;
import com.ar.ada.nasa.nasa.model.request.TemperaturaRequest;
import com.ar.ada.nasa.nasa.model.response.GenericResponse;
import com.ar.ada.nasa.nasa.model.response.MaximaTemperatura;
import com.ar.ada.nasa.nasa.model.response.TemperaturaResponse;
import com.ar.ada.nasa.nasa.service.PaisService;
import com.ar.ada.nasa.nasa.service.TemperaturaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TemperaturaController {
    @Autowired
    TemperaturaService temperaturaService;

    @Autowired
    PaisService paisService;

    @PostMapping("/api/temperaturas")
    public ResponseEntity<?> crearTemperatura(@RequestBody TemperaturaRequest temperaturaRequest) {
        GenericResponse r = new GenericResponse();
        Temperatura temperatura = temperaturaService.crearTemperatura(temperaturaRequest.paisId,
                temperaturaRequest.anoTemperatura, temperaturaRequest.grados);
        if (temperatura == null) {
            r.isOk = false;
            r.message = "Existe una temperatura para ese año";
            return ResponseEntity.badRequest().body(r);
        }

        r.isOk = true;
        r.message = "Temperatura creada con exito";
        r.id = temperaturaRequest.paisId;
        return ResponseEntity.ok(r);

    }

    @GetMapping("/api/temperaturas/paises/{id}")
    public ResponseEntity<?> listarTemperaturasPorPais(@PathVariable int id) {
        List<Temperatura> temperaturas = paisService.listarPaisesSegunId(id).getTemperaturas();
        return ResponseEntity.ok(temperaturas);
    }

    @GetMapping("/api/temperaturas/anios/{anio}")
    public ResponseEntity<?> listarTemperaturaPorAño(@PathVariable int anio) {
        List<TemperaturaResponse> temperaturasF = new ArrayList<>();
        for (Temperatura temp : temperaturaService.listarTemperaturas()) {
            TemperaturaResponse temperatura = new TemperaturaResponse();
            temperatura.nombrePais = temp.getPais().getNombre();
            temperatura.grados = temp.getGrados();
            temperaturasF.add(temperatura);
        }

        return ResponseEntity.ok(temperaturasF);
    }

    @GetMapping("/api/temperaturas/maximas/{paidId}")
    public ResponseEntity<?> mostarTemperaturaMaxima(@PathVariable int paidId) {
        MaximaTemperatura max = new MaximaTemperatura();
        Temperatura temperatura = new Temperatura();
        double maximo = 0;
        for (Temperatura temp : temperaturaService.listarTemperaturas()) {
            if (temp.getGrados() > maximo)
                temperatura = temp;
            maximo = temp.getGrados();
        }
        max.nombrePais = temperatura.getPais().getNombre();
        max.temperaturaMaxima = temperatura.getGrados();
        max.anio = temperatura.getAnoTemperatura();

        return ResponseEntity.ok(max);
    }

    @GetMapping("/api/temperaturas")
    public ResponseEntity<?> listarTemperaturas() {
        return ResponseEntity.ok(temperaturaService.listarTemperaturas());
    }

    @DeleteMapping("/api/temperaturas/{id}")
    public ResponseEntity<?> deleteTemperatura(@PathVariable int id) {
        GenericResponse r = new GenericResponse();
        Temperatura temperaturaDeBaja = temperaturaService.listarTemperaturaPorId(id);
        if (temperaturaDeBaja == null) {
            return ResponseEntity.notFound().build();
        }
        temperaturaDeBaja.setAnoTemperatura(0);

        boolean resultado = temperaturaService.grabar(temperaturaDeBaja);
        if (resultado) {
            r.isOk = true;
            r.id = temperaturaDeBaja.getTemperaturaId();
            r.message = "Eliminaste la temperatura con éxito.";
            return ResponseEntity.ok(r);
        } else {
            r.isOk = false;
            r.message = "No pudiste eliminar la tempetaura";
            return ResponseEntity.badRequest().body(r);
        }
    }

}
