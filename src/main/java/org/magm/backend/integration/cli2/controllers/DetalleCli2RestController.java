package org.magm.backend.integration.cli2.controllers;


import org.magm.backend.controllers.BaseRestController;
import org.magm.backend.controllers.Constants;
import org.magm.backend.integration.cli2.model.DetalleFacturaCli2;
import org.magm.backend.integration.cli2.model.FacturaCli2;
import org.magm.backend.integration.cli2.model.business.IDetalleFacturaCli2Business;
import org.magm.backend.integration.cli2.model.business.IFacturaCli2Business;
import org.magm.backend.model.business.BusinessException;
import org.magm.backend.model.business.FoundException;
import org.magm.backend.model.business.NotFoundException;
import org.magm.backend.util.IStandartResponseBusiness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
//El controlador DetalleCli2RestController maneja las operaciones relacionadas con los detalles de las facturas en el contexto de la integración cli2. Aquí hay un resumen de los métodos definidos en este controlador:
/*
* Nuevo Detalle (/nuevo-detalle):

Ruta: POST /detalles/nuevo-detalle
Descripción: Agrega un nuevo detalle de factura.
Parámetros de entrada: Detalle de factura (DetalleFacturaCli2).
Respuestas:
201 CREATED: Detalle de factura creado exitosamente.
404 NOT FOUND: Si se encuentra un problema (por ejemplo, ya existe el detalle de factura).
500 INTERNAL SERVER ERROR: Si hay un error general en la operación.
Cargar Detalle (/{id}):

Ruta: GET /detalles/{id}
Descripción: Recupera un detalle de factura por su identificador.
Parámetros de entrada: Identificador del detalle de factura.
Respuestas:
200 OK: Detalle de factura recuperado exitosamente.
404 NOT FOUND: Si no se encuentra el detalle de factura.
500 INTERNAL SERVER ERROR: Si hay un error general en la operación
* */
//Estos métodos proporcionan una interfaz para interactuar con la lógica de negocio relacionada con los detalles de facturas en el contexto de la integración cli2.
@Profile({"cli2","mysqldev"})
@RestController
@RequestMapping(Constants.URL_DETALLES)
public class DetalleCli2RestController extends BaseRestController {

    @Autowired
    private IDetalleFacturaCli2Business detalleFacturaCli2Business;

    @Autowired
    private IStandartResponseBusiness response;

    @PostMapping(value = "/nuevo-detalle")
    public ResponseEntity<?> load(@RequestBody DetalleFacturaCli2 detallefactura) {
        try {
            DetalleFacturaCli2 response=detalleFacturaCli2Business.add(detallefactura);
            HttpHeaders responseHeaders=new HttpHeaders();
            responseHeaders.set("location",Constants.URL_DETALLES+"/"+response.getId());
            return new ResponseEntity<>( responseHeaders, HttpStatus.CREATED);
        } catch (FoundException e) {
            return new ResponseEntity<>(response.build(HttpStatus.NOT_FOUND, e, e.getMessage()), HttpStatus.NOT_FOUND);
        } catch (BusinessException e) {
            return new ResponseEntity<>(response.build(HttpStatus.INTERNAL_SERVER_ERROR, e, e.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value="/{id}", produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> load(@PathVariable("id") long id) {
        try {
            return new ResponseEntity<>(detalleFacturaCli2Business.load(id), HttpStatus.OK);
        } catch (BusinessException e) {
            return new ResponseEntity<>(response.build(HttpStatus.INTERNAL_SERVER_ERROR, e, e.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(response.build(HttpStatus.NOT_FOUND, e, e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }
}
