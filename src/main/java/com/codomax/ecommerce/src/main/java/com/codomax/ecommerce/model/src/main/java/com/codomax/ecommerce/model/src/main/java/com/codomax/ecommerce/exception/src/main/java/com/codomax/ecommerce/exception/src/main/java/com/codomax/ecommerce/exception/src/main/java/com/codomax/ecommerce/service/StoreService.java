package com.codomax.ecommerce.service;

import com.codomax.ecommerce.exception.BusinessRuleException;
import com.codomax.ecommerce.exception.ResourceNotFoundException;
import com.codomax.ecommerce.model.Order;
import com.codomax.ecommerce.model.Product;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class StoreService {
    private final Map<Long, Product> products = new ConcurrentHashMap<>();
    private final Map<Long, Order> orders = new ConcurrentHashMap<>();
    private final AtomicLong productSeq = new AtomicLong(1);
    private final AtomicLong orderSeq = new AtomicLong(1);

    public Product addProduct(Product product) {
        product.setId(productSeq.getAndIncrement());
        products.put(product.getId(), product);
        return product;
    }

    public List<Product> getAllProducts() {
        return new ArrayList<>(products.values());
    }

    public Product getProduct(Long id) {
        Product p = products.get(id);
        if (p == null) {
            throw new ResourceNotFoundException("Product not found with id: " + id);
        }
        return p;
    }

    public Order placeOrder(Order order) {
        Product product = getProduct(order.getProductId());
        if (product.getStock() < order.getQuantity()) {
            throw new BusinessRuleException("Insufficient stock for product: " + product.getName());
        }
        product.setStock(product.getStock() - order.getQuantity());
        order.setId(orderSeq.getAndIncrement());
        order.setTotalAmount(product.getPrice() * order.getQuantity());
        order.setStatus("CONFIRMED");
        orders.put(order.getId(), order);
        return order;
    }

    public List<Order> getAllOrders() {
        return new ArrayList<>(orders.values());
    }
}
