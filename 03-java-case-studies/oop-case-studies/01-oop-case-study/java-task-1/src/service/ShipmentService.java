package service;

import model.Order;
import model.OrderStatus;
import model.Shipment;
import repository.OrderRepository;
import repository.ShipmentRepository;

public class ShipmentService {
    private final OrderRepository orderRepository;
    private final ShipmentRepository shipmentRepository;

    public ShipmentService(OrderRepository orderRepository, ShipmentRepository shipmentRepository) {
        this.orderRepository = orderRepository;
        this.shipmentRepository = shipmentRepository;
    }

    public Shipment dispatchOrder(long orderId, long shipmentId, String trackingId, String courierName) {
        if (orderId <= 0) {
            throw new IllegalArgumentException("Order ID should be greater than zero!");
        }
        Order order = orderRepository.findById(orderId);
        if (order == null) {
            throw new IllegalArgumentException("Order with ID " + orderId + " does not exist!");
        }
        if (order.getStatus() != OrderStatus.PLACED) {
            throw new IllegalStateException("Invalid Order Status!");
        }
        if(shipmentRepository.findByOrderId(orderId) != null) {
            throw new IllegalArgumentException("Shipment with Order ID " + orderId + " already exists!");
        }
        if (shipmentId <= 0) {
            throw new IllegalArgumentException("Shipment ID should be greater than zero!");
        }
        if (courierName == null || courierName.isBlank()) {
            throw new IllegalArgumentException("Provide valid Courier Name!");
        }

        Shipment shipment = new Shipment(shipmentId, order, trackingId, courierName);
        shipmentRepository.addShipment(shipment);
        order.updateStatus(OrderStatus.SHIPPED);
        return shipment;
    }

    public Shipment trackShipment(String trackingId) {
        if (trackingId == null || trackingId.isBlank()) {
            throw new IllegalArgumentException("Provide a valid Tracking ID");
        }

        Shipment shipment = shipmentRepository.findByTrackingId(trackingId);
        if (shipment == null) {
            throw new IllegalArgumentException("Shipment with Tracking ID " + trackingId + " does not exist!");
        }

        return shipment;
    }

    public void markInTransit(long orderId) {
        if (orderId <= 0) {
            throw new IllegalArgumentException("Order ID should be greater than zero!");
        }
        Order order = orderRepository.findById(orderId);
        if (order == null) {
            throw new IllegalArgumentException("Order with ID " + orderId + " does not exist!");
        }
        if (shipmentRepository.findByOrderId(orderId) == null) {
            throw new IllegalArgumentException("Shipment with Order ID " + orderId + " does not exist!");
        }
        order.updateStatus(OrderStatus.IN_TRANSIT);
    }

    public void markDelivered(long orderId) {
        if (orderId <= 0) {
            throw new IllegalArgumentException("Order ID should be greater than zero!");
        }
        Order order = orderRepository.findById(orderId);
        if (order == null) {
            throw new IllegalArgumentException("Order with ID " + orderId + " does not exist!");
        }
        if (shipmentRepository.findByOrderId(orderId) == null) {
            throw new IllegalArgumentException("Shipment with Order ID " + orderId + " does not exist!");
        }
        order.updateStatus(OrderStatus.DELIVERED);
    }

    public void markReturned(long orderId) {
        if (orderId <= 0) {
            throw new IllegalArgumentException("Order ID should be greater than zero!");
        }
        Order order = orderRepository.findById(orderId);
        if (order == null) {
            throw new IllegalArgumentException("Order with ID " + orderId + " does not exist!");
        }
        if (shipmentRepository.findByOrderId(orderId) == null) {
            throw new IllegalArgumentException("Shipment with Order ID " + orderId + " does not exist!");
        }
        order.updateStatus(OrderStatus.RETURNED);
    }

    public void markCancelled(long orderId) {
        if (orderId <= 0) {
            throw new IllegalArgumentException("Order ID should be greater than zero!");
        }
        Order order = orderRepository.findById(orderId);
        if (order == null) {
            throw new IllegalArgumentException("Order with ID " + orderId + " does not exist!");
        }
        if (shipmentRepository.findByOrderId(orderId) == null) {
            throw new IllegalArgumentException("Shipment with Order ID " + orderId + " does not exist!");
        }
        order.updateStatus(OrderStatus.CANCELLED);
    }
}
