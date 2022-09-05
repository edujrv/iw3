package org.magm.backend.controllers;

import org.magm.backend.model.Factura;
import org.magm.backend.model.Product;
import org.magm.backend.model.business.BusinessException;
import org.magm.backend.model.business.FoundException;
import org.magm.backend.model.business.IFacturaBusiness;
import org.magm.backend.model.business.NotFoundException;
import org.magm.backend.util.IStandartResponseBusiness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(Constants.URL_PRODUCTS)
public class FacturaRestController extends BaseRestController{

    @Autowired
    private IFacturaBusiness facturaBusiness;

    @Autowired
    private IStandartResponseBusiness response;

    @PostMapping(value = "/nueva-factura")
    public ResponseEntity<?> load(@RequestBody Factura factura) {
        try {
            Factura response=facturaBusiness.add(factura);
            HttpHeaders responseHeaders=new HttpHeaders();
            responseHeaders.set("location",Constants.URL_PRODUCTS+"/"+response.getId());
            return new ResponseEntity<>( responseHeaders,HttpStatus.CREATED);
        } catch (FoundException e) {
            return new ResponseEntity<>(response.build(HttpStatus.NOT_FOUND, e, e.getMessage()), HttpStatus.NOT_FOUND);
        } catch (BusinessException e) {
            return new ResponseEntity<>(response.build(HttpStatus.INTERNAL_SERVER_ERROR, e, e.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/modificar-factura")
    public ResponseEntity<?> update(@RequestBody Factura factura) {
        try {
            facturaBusiness.update(factura);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (BusinessException e) {
            return new ResponseEntity<>(response.build(HttpStatus.INTERNAL_SERVER_ERROR, e, e.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(response.build(HttpStatus.NOT_FOUND, e, e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(value = "/anular-factura/{id}")
    public ResponseEntity<?> anularFactura(@PathVariable("id") long id) {
        try {
            facturaBusiness.anularFactura(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (BusinessException e) {
            return new ResponseEntity<>(response.build(HttpStatus.INTERNAL_SERVER_ERROR, e, e.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(response.build(HttpStatus.NOT_FOUND, e, e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value="/{id}", produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> load(@PathVariable("id") long id) {
        try {
            return new ResponseEntity<>(facturaBusiness.load(id), HttpStatus.OK);
        } catch (BusinessException e) {
            return new ResponseEntity<>(response.build(HttpStatus.INTERNAL_SERVER_ERROR, e, e.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(response.build(HttpStatus.NOT_FOUND, e, e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }
}

/*
Crear los siguientes servicios:
- Dar de alta una factura completa con sus items
- Modificar cualquier parte de la factura
- Anular una factura por numero
- Obtener una factura
- Listar todas las facturas no anuladas
* */