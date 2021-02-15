package com.mycompany.invoice.customer.repository;

import com.company.invoice.core.entity.customer.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepositoryInterface extends CrudRepository<Customer,Long> {
    /**Invoice create(Invoice invoice);
    List<Invoice> list();
    Invoice getById(String number);*/
}
