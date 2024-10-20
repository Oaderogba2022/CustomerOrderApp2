package ie.atu.week5.customerapp;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CustomerOrderController {
    private final CustomerRepository customerRepository;
    private final OrderRepository orderRepository;

    public CustomerOrderController(CustomerRepository customerRepository, OrderRepository orderRepository) {
        this.customerRepository = customerRepository;
        this.orderRepository = orderRepository;
    }

    @PostMapping("/customer-with-orders")
    public ResponseEntity<String> createCustomerWithOrders(@Valid @RequestBody CustomerOrderRequest customerOrderRequest) {
        // Check if customer already exists before saving
        Customer savedCustomer = customerRepository.save(customerOrderRequest.getCustomer());

        // Link orders to the saved customer
        for (Order order : customerOrderRequest.getOrders()) {
            order.setCustomerId(savedCustomer.getId());  // Link order to the customer
            orderRepository.save(order);  // Save the order
        }

        return ResponseEntity.ok("Customer and orders created successfully");
    }
}
