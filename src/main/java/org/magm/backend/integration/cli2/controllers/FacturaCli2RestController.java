package org.magm.backend.integration.cli2.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.magm.backend.controllers.BaseRestController;
import org.magm.backend.controllers.Constants;
import org.magm.backend.integration.cli2.model.FacturaCli2;
import org.magm.backend.integration.cli2.model.FacturaCli2SlimV1JsonSerializer;
import org.magm.backend.integration.cli2.model.FacturaCli2SlimV2JsonSerializer;
import org.magm.backend.integration.cli2.model.IFacturaCli2SlimView;
import org.magm.backend.integration.cli2.model.business.FacturaCli2Business;
import org.magm.backend.integration.cli2.model.business.IFacturaCli2Business;
import org.magm.backend.model.Factura;
import org.magm.backend.model.business.BusinessException;
import org.magm.backend.model.business.FoundException;
import org.magm.backend.model.business.IAuditoriaBusiness;
import org.magm.backend.model.business.NotFoundException;
import org.magm.backend.util.IStandartResponseBusiness;
import org.magm.backend.util.JsonUtiles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Profile({"cli2","mysqldev"})
@RestController
@RequestMapping(Constants.URL_FACTURAS)
public class FacturaCli2RestController extends BaseRestController {

    @Autowired
    private IFacturaCli2Business facturaCli2Business;

    @Autowired
    private IAuditoriaBusiness auditoriaBusiness;

    @Autowired
    private IStandartResponseBusiness response;

    @PostMapping(value = "/nueva-factura")
    public ResponseEntity<?> load(@RequestBody FacturaCli2 factura) {
        try {
            FacturaCli2 response = facturaCli2Business.add(factura);
            auditoriaBusiness.add(factura.getId(),getUserLogged().getUsername(),factura.getFechaEmision(),"ALTA");
            HttpHeaders responseHeaders=new HttpHeaders();
            responseHeaders.set("location",Constants.URL_PRODUCTS+"/"+response.getId());
            return new ResponseEntity<>( responseHeaders, HttpStatus.CREATED);
        } catch (FoundException e) {
            return new ResponseEntity<>(response.build(HttpStatus.NOT_FOUND, e, e.getMessage()), HttpStatus.NOT_FOUND);
        } catch (BusinessException e) {
            return new ResponseEntity<>(response.build(HttpStatus.INTERNAL_SERVER_ERROR, e, e.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/modificar-factura")
    public ResponseEntity<?> update(@RequestBody FacturaCli2 factura) {
        try {
            facturaCli2Business.update(factura);
//            facturaCli2Business.delete(facturaCli2Business.load(factura.getNumero()).getId());

            auditoriaBusiness.add(factura.getId(),getUserLogged().getUsername(),factura.getFechaEmision(),"MODIFICADA");
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (BusinessException e) {
            return new ResponseEntity<>(response.build(HttpStatus.INTERNAL_SERVER_ERROR, e, e.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(response.build(HttpStatus.NOT_FOUND, e, e.getMessage()), HttpStatus.NOT_FOUND);
        } catch (FoundException e) {
            throw new RuntimeException(e);
        }
    }

    @PutMapping(value = "/anular-factura/{id}")
    public ResponseEntity<?> anularFactura(@PathVariable("id") long id) {
        try {
            facturaCli2Business.anularFactura(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (BusinessException e) {
            return new ResponseEntity<>(response.build(HttpStatus.INTERNAL_SERVER_ERROR, e, e.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(response.build(HttpStatus.NOT_FOUND, e, e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(value = "/desanular-factura/{id}")
    public ResponseEntity<?> desanularFactura(@PathVariable("id") long id) {
        try {
            facturaCli2Business.desAnularFactura(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (BusinessException e) {
            return new ResponseEntity<>(response.build(HttpStatus.INTERNAL_SERVER_ERROR, e, e.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(response.build(HttpStatus.NOT_FOUND, e, e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value="/{numero}", produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> load(@PathVariable("numero") long numero,
                                  @RequestParam(name = "slim", required = false, defaultValue = "v0") String slimVersion) {
        try {
            if(slimVersion.equalsIgnoreCase("v1")){
                return new ResponseEntity<>(facturaCli2Business.load(numero), HttpStatus.OK);
            } else if(slimVersion.equalsIgnoreCase("v2")){
                return new ResponseEntity<>(facturaCli2Business.findOneByNumeroV2(numero),HttpStatus.OK);
            }else{
                return new ResponseEntity<>(facturaCli2Business.load(numero), HttpStatus.OK);
            }

        } catch (BusinessException e) {
            return new ResponseEntity<>(response.build(HttpStatus.INTERNAL_SERVER_ERROR, e, e.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(response.build(HttpStatus.NOT_FOUND, e, e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value="/lista", produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> loadAll(
            @RequestParam(name = "slim", required = false, defaultValue = "v0") String slimVersion) {
        try {
            StdSerializer<FacturaCli2> ser = null;

            if(slimVersion.equalsIgnoreCase("v1")){
                ser = new FacturaCli2SlimV1JsonSerializer(FacturaCli2.class, false);
            }
            else if(slimVersion.equalsIgnoreCase("v2")){
                ser = new FacturaCli2SlimV2JsonSerializer(FacturaCli2.class, false);
            }else {
                ser = new FacturaCli2SlimV1JsonSerializer(FacturaCli2.class, false);
            }

            String result = JsonUtiles.getObjectMapper(FacturaCli2.class, ser, null)
                    .writeValueAsString(facturaCli2Business.loadAll());

//            String result = JsonUtiles.getObjectMapper(FacturaCli2.class, ser, null)
//                    .writeValueAsString(facturaCli2List);

                return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (BusinessException e) {
            return new ResponseEntity<>(response.build(HttpStatus.INTERNAL_SERVER_ERROR, e, e.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(response.build(HttpStatus.NOT_FOUND, e, e.getMessage()), HttpStatus.NOT_FOUND);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping(value="/lista-no-anulada", produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> loadNoAnuladas() {
        try {
            return new ResponseEntity<>(facturaCli2Business.listaNoAnulada(), HttpStatus.OK);
        } catch (BusinessException e) {
            return new ResponseEntity<>(response.build(HttpStatus.INTERNAL_SERVER_ERROR, e, e.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value="/eliminar-factura/{numero}")
    public ResponseEntity<?> deleteByNumber(@PathVariable("numero") long numero){
        try {
            FacturaCli2 factura = facturaCli2Business.load(numero);
            facturaCli2Business.deleteByNumero(numero);
            auditoriaBusiness.add(factura.getId(),getUserLogged().getUsername(),factura.getFechaEmision(),"BAJA");
            return new ResponseEntity<>( HttpStatus.OK);
        } catch (BusinessException | NotFoundException e) {
            return new ResponseEntity<>(response.build(HttpStatus.INTERNAL_SERVER_ERROR, e, e.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (FoundException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping(value="/lista-id/{idProducto}", produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> listarID(@PathVariable(name="idProducto") long idProducto) {
        try {
            return new ResponseEntity<>(facturaCli2Business.idFacturaPorProducto(idProducto), HttpStatus.OK);
        } catch (BusinessException e) {
            return new ResponseEntity<>(response.build(HttpStatus.INTERNAL_SERVER_ERROR, e, e.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
