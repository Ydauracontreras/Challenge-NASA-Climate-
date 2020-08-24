package com.ar.ada.nasa.nasa.controller;

import com.ar.ada.nasa.nasa.entity.Pais;
import com.ar.ada.nasa.nasa.model.request.PaisRequest;
import com.ar.ada.nasa.nasa.model.request.PaisUpdate;
import com.ar.ada.nasa.nasa.model.response.GenericResponse;
import com.ar.ada.nasa.nasa.service.PaisService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaisController {

    @Autowired
    PaisService paisService;

    @PostMapping("/api/paises")
    public ResponseEntity<?> crearPais(@RequestBody PaisRequest paisRequest) {
        paisService.crearPais(paisRequest.paisId, paisRequest.nombre);
        GenericResponse r = new GenericResponse();
        r.isOk = true;
        r.message = "Pais Creado con exito";
        r.id = paisRequest.paisId;
        return ResponseEntity.ok(r);

    }

    @GetMapping("/api/paises")
    public ResponseEntity<?> listarPaises() {
        return ResponseEntity.ok(paisService.listarPaises());
    }

    @GetMapping("/api/paises/{id}")
    public ResponseEntity<Pais> listarPaisesSegunId(@PathVariable int id) {
        return ResponseEntity.ok(paisService.listarPaisesSegunId(id));

    }

    @PutMapping("/api/paises/{id}")
    public ResponseEntity<?> modificarPais(@PathVariable int id, @RequestBody Pais pa) {
        GenericResponse r = new GenericResponse();
        Pais pais = paisService.listarPaisesSegunId(id);
        if (pais == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        boolean resultado = paisService.actualizarNombrePais(pais, pa);
        if (resultado) {
            r.isOk = true;
            r.id = id;
            r.message = "Pais actualizado con éxito.";
            return ResponseEntity.ok(r);
        } else {
            r.isOk = false;
            r.message = "No se pudo actualizar el pais";
            return ResponseEntity.badRequest().body(r);
        }

    }

}
