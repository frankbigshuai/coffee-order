package edu.iu.habahram.coffeeorder.controllers;

import edu.iu.habahram.coffeeorder.model.Customer;
import edu.iu.habahram.coffeeorder.model.OrderData;
import edu.iu.habahram.coffeeorder.model.Receipt;
import edu.iu.habahram.coffeeorder.repository.CustomerRepository;
import edu.iu.habahram.coffeeorder.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/orders")
public class OrderController {

    private OrderRepository orderRepository;
    private CustomerRepository customerRepository;
    @Autowired
    public OrderController(OrderRepository orderRepository, CustomerRepository customerRepository) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody OrderData order) {
        try {
            Receipt receipt = orderRepository.add(order);
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(receipt);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }
    }

    @PostMapping("/signup")
    public void signup(@RequestBody Customer customer){
        try{
            customerRepository.save(customer);
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
