package repository;

import model.Shipment;
import java.util.HashMap;
import java.util.Map;

public class ShipmentRepository {
    private final Map<Long, Shipment> shipments = new HashMap<>();

    public void addShipment(Shipment shipment) {
        if (shipment == null) {
            throw new IllegalArgumentException("Provide valid shipment details!");
        }
        if (shipments.containsKey(shipment.getShipmentId())) {
            throw new IllegalArgumentException("Shipment with ID " + shipment.getShipmentId() + " already exists!");
        }
        for (Shipment currentShipment : shipments.values()) {
            if (currentShipment.getTrackingId().equalsIgnoreCase(shipment.getTrackingId()) ||
                currentShipment.getOrder().getOrderId() == shipment.getOrder().getOrderId()) {
                throw new IllegalArgumentException("Shipment with Tracking ID " + shipment.getTrackingId() + " Order ID "+ shipment.getOrder().getOrderId() + " already exists!");
            }
        }
        shipments.put(shipment.getShipmentId(), shipment);
    }

    public Shipment findByShipmentId(long shipmentId) {
        if (shipmentId <= 0) {
            throw new IllegalArgumentException("Shipmen ID should be greater than zero!");
        }
        return shipments.get(shipmentId);
    }

    public Shipment findByTrackingId(String trackingId) {
        if (trackingId == null || trackingId.isBlank()) {
            throw new IllegalArgumentException("Provide a valid Tracking ID!");
        }
        for (Shipment shipment : shipments.values()) {
            if (trackingId.equalsIgnoreCase(shipment.getTrackingId())) {
                return shipment;
            }
        }
        return null;
    }

    public Shipment findByOrderId(long orderId) {
        if (orderId <= 0) {
            throw new IllegalArgumentException("Order ID must be grater than zero!");
        }
        for (Shipment shipment : shipments.values()) {
            if (orderId == shipment.getOrder().getOrderId()) {
                return  shipment;
            }
        }
        return null;
    }
}
