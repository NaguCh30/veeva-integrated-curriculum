package model;

public class Shipment {
    private final long shipmentId;
    private final Order order;
    private final String trackingId;
    private final String courierName;

    public Shipment(long shipmentId, Order order, String trackingId, String courierName) {
        if (shipmentId <= 0) {
            throw new IllegalArgumentException("Shipment ID should be greater than zero!");
        }
        if (order == null) {
            throw new IllegalArgumentException("Provide valid order details for shipment!");
        }
        if (trackingId == null || trackingId.isBlank()) {
            throw new IllegalArgumentException("Provide valid Tracking ID!");
        }
        if (courierName == null || courierName.isBlank()) {
            throw new IllegalArgumentException("Provide valid courier name!");
        }

        this.shipmentId = shipmentId;
        this.order = order;
        this.trackingId = trackingId;
        this.courierName = courierName;
    }

    public long getShipmentId() {
        return shipmentId;
    }

    public Order getOrder() {
        return order;
    }

    public String getTrackingId() {
        return trackingId;
    }

    public String getCourierName() {
        return courierName;
    }
}
