package org.magm.backend.integration.cli2.model;


//La interfaz IFacturaCli2SlimView define la estructura para una vista "delgada" de la entidad FacturaCli2. En este caso, también incluye detalles de factura (DetallesFactura).
//Puntos clave:
//
//IFacturaCli2SlimView Interfaz principal:
//
//getId(): Método para obtener el ID de la factura.
//getDetallesFactura(): Método para obtener una lista de detalles de factura.
//DetallesFactura Interfaz interna:
//
//getId(): Método para obtener el ID del detalle de la factura.
//getPrice(): Método para obtener el precio del detalle de la factura.
//getProduct(): Método para obtener el producto asociado al detalle de la factura (ProductCli2).
//Esta interfaz se utiliza para proporcionar una vista "delgada" de la entidad FacturaCli2, especialmente útil cuando solo se necesitan ciertos detalles de la factura y del producto. Si tienes alguna pregunta adicional o necesitas más información, no dudes en preguntar.
import java.util.List;

public interface IFacturaCli2SlimView {
    Long getId();

    List<DetallesFactura> getDetallesFactura();

    interface DetallesFactura{
        long getId();
//        String getCod_item();
        double getPrice();
//        long getCantidad();
        ProductCli2 getProduct();


    }
}