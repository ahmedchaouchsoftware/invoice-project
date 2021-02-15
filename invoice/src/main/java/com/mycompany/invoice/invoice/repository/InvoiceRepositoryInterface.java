package com.mycompany.invoice.invoice.repository;

import com.company.invoice.core.entity.invoice.Invoice;
import org.springframework.data.repository.CrudRepository;

public interface InvoiceRepositoryInterface extends CrudRepository<Invoice,String> {

}
