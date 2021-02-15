package com.mycompany.invoice.invoice.services.number;

import com.company.invoice.core.entity.invoice.Invoice;
import com.mycompany.invoice.invoice.repository.InvoiceRepositoryInterface;
import com.mycompany.invoice.invoice.services.InvoiceServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class InvoiceServiceNumber implements InvoiceServiceInterface {

    @Autowired
    private InvoiceRepositoryInterface invoiceRepository;

    /*@Autowired
    private CustomerRepositoryInterface customerRepository;*/

    @Override
    @Transactional
    public Invoice createInvoice(Invoice invoice) {
        //customerRepository.save(invoice.getCustomer());
        return invoiceRepository.save(invoice);
    }

    @Override
    public Iterable<Invoice> getInvoiceList() {
        return invoiceRepository.findAll();
    }

    @Override
    public Invoice getInvoiceByNumber(String number) {
        return invoiceRepository.findById(number).orElseThrow();
    }

    public InvoiceRepositoryInterface getInvoiceRepository() {
        return invoiceRepository;
    }

    public void setInvoiceRepository(InvoiceRepositoryInterface invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }
}
