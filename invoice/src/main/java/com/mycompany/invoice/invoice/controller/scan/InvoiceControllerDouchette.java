package com.mycompany.invoice.invoice.controller.scan;

import com.company.invoice.core.entity.customer.Customer;
import com.company.invoice.core.entity.invoice.Invoice;
import com.mycompany.invoice.invoice.controller.InvoiceControllerInterface;
import com.mycompany.invoice.invoice.services.InvoiceServiceInterface;

//@Controller
public class InvoiceControllerDouchette implements InvoiceControllerInterface {

    private InvoiceServiceInterface invoiceService;

    @Override
    public String createInvoice(Invoice invoice) {
        System.out.println("Usage of Scanner");
        invoice = new Invoice();
        Customer customer = new Customer("Virgin Galactic");
        invoice.setCustomer(customer);
        invoiceService.createInvoice(invoice);
        return null;
    }

    public InvoiceServiceInterface getInvoiceService() {
        return invoiceService;
    }

    public void setInvoiceService(InvoiceServiceInterface invoiceService) {
        this.invoiceService = invoiceService;
    }
}
