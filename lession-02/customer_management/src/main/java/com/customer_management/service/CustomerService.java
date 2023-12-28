package com.customer_management.service;

import com.customer_management.model.Customer;

import java.util.ArrayList;
import java.util.List;

public class CustomerService implements ICustomerService {
    private final List<Customer> customers;

    public CustomerService() {
        customers = new ArrayList<>();
        customers.add(new Customer(1, "Vũ Hoàng Quốc Việt", "viet@co.jp", "Sài Gòn"));
        customers.add(new Customer(2, "Hiếu Hiền", "hieu.dev@dxc.com", "Đồng Nai"));
        customers.add(new Customer(3, "Nguyễn Thị Thanh Thảo", "hieu.dev@dxc.com", "Bình Dương"));
    }

    @Override
    public List<Customer> findAll() {
        return customers;
    }

    @Override
    public Customer findById(long id) {
        for (Customer a : customers) {
            if (a.getId() == id) {
                return a;
            }
        }
        return null;
    }

    @Override
    public void save(Customer customer) {
        Customer a = findById(customer.getId());
        a.setName(customer.getName());
        a.setEmail(customer.getEmail());
        a.setAddress(customer.getAddress());
    }
}
