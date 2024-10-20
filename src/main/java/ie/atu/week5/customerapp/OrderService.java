package ie.atu.week5.customerapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class OrderService {
    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public List<Order> getOrdersByCustomerId(String customerId) {
        return orderRepository.findByCustomerId(customerId);
    }

    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }

    public void createOrders(List<Order> orders) {
        orderRepository.saveAll(orders); // Save all orders at once
    }

    public boolean existsById(String id) {
        return orderRepository.existsById(id);
    }

    public void deleteOrderById(String id) {
        orderRepository.deleteById(id);
    }
}
