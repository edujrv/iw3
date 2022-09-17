//
//package org.magm.backend.integration.cli2.model;
//
//import com.fasterxml.jackson.core.JacksonException;
//import com.fasterxml.jackson.core.JsonParser;
//import com.fasterxml.jackson.databind.DeserializationContext;
//import com.fasterxml.jackson.databind.JsonNode;
//import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
//import com.fasterxml.jackson.databind.ser.std.StdSerializer;
//import org.magm.backend.integration.cli2.model.business.IDetalleFacturaCli2Business;
//import org.magm.backend.model.business.BusinessException;
//import org.magm.backend.model.business.ICategoryBusiness;
//import org.magm.backend.model.business.NotFoundException;
//import org.magm.backend.util.JsonUtiles;
//
//import java.io.IOException;
//import java.util.Date;
//
//
//public class FacturaCli2JsonDeserializer extends StdDeserializer<FacturaCli2>  {
//    private static final long serialVersionUID = -3891285352118964728L;
//
//    protected FacturaCli2JsonDeserializer(Class<?> vc) {
//            super(vc);
//    }
//
//    private IDetalleFacturaCli2Business detalleFacturaBusiness;
//
//    public FacturaCli2JsonDeserializer(Class<?> vc, ICategoryBusiness categoryBusiness) {
//        super(vc);
//        this.detalleFacturaBusiness = detalleFacturaBusiness;
//    }
//
//    @Override
//    public FacturaCli2 deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JacksonException {
//        FacturaCli2 facturaCli2 = new FacturaCli2();
//        JsonNode node = jp.getCodec().readTree(jp);
//        //
//        String code = JsonUtiles.getString(node, "factura_code,code_factura,code".split(","),
//        System.currentTimeMillis() + "");
//        long numero = JsonUtiles.getLong(node,
//        "numero,factura_numero,numero_factura,num".split(","), 0);
//        boolean anulada = JsonUtiles.getBoolean(node, "anulada,is_anulada".split(","), false);
//        Date fechaemi = JsonUtiles.getObjectMapper(node, "emision,fechaemi,fe".split(","), null);
//
//        Date fechaemi = JsonUtiles.getObjectMapper(Date.class, new StdSerializer );
//
//
//
//        Date fechaven = JsonUtiles.getDate(node, "vencimiento,fechaven,fv".split(","), null);
//        double price = JsonUtiles.getDouble(node, "factura_price,price_factura,price".split(","), 0);
//
//
//        facturaCli2.setCodFacturaCli2(code);
//        facturaCli2.setNumero(numero);
//        facturaCli2.setAnulada(anulada);
//        facturaCli2.setFechaEmision(fechaemi);
//        facturaCli2.setFechaVencimiento(fechaven);
//        facturaCli2.setPrice(price);
//
//        long detallefactura = JsonUtiles.getLong(node, "detallefactura,detalle_factura,factura_detalle".split(","), 0);
//        if (detallefactura != 0) {
//            try {
//                facturaCli2.setDetallesFactura(detalleFacturaBusiness.load(detallefactura));
//            } catch (NotFoundException | BusinessException e) {
//
//            }
//        }
//        return facturaCli2;
//    }
//}
