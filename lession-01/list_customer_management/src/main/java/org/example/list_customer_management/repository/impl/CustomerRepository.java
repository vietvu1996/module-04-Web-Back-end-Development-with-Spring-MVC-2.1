package org.example.list_customer_management.repository.impl;

import org.example.list_customer_management.bean.Customer;
import org.example.list_customer_management.repository.ICustomerRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomerRepository implements ICustomerRepository {
    Map<Integer, Customer> customers;

    public CustomerRepository() {
        customers = new HashMap<>();
        customers.put(1, new Customer(1, "Nguyễn Khắc Nhật", "nhat@codegym.vn", "Hà Nội"));
        customers.put(2, new Customer(2, "Đặng Đức Hoà", "hoa.dang@codegym.vn", "Đà Nẵng"));
        customers.put(3, new Customer(3, "Lê Thị Châu", "chau.le@codegym.vn", "Hà Nội"));
        customers.put(4, new Customer(4, "Nguyễn Thuỳ Dương", "duong.nguyen@codegym.vn", "Sài Gòn"));
        customers.put(5, new Customer(5, "Nguyễn Bá Minh Đạo", "dao.nguyen@codegym.vn", "Sài Gòn"));
    }

    @Override
    public List<Customer> findAll() {
        return new ArrayList<>(customers.values());
    }

    @Override
    public Customer list(int id) {
        return customers.get(id);
    }
}
