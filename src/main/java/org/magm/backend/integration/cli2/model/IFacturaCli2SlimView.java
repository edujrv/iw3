package org.magm.backend.integration.cli2.model;



import java.util.List;

public interface IFacturaCli2SlimView {
    Long getId();

    List<DetallesFactura> getDetallesFactura();

    interface DetallesFactura{
        long getId();
//        String getCodItem();
        double getPrice();
//        long getCantidad();
        ProductCli2 getProduct();


    }
}