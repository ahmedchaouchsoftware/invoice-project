package com.mycompany.invoice.invoice.controller.keyboard;

import com.company.invoice.core.entity.customer.Customer;
import com.company.invoice.core.entity.invoice.Invoice;
import com.mycompany.invoice.invoice.controller.InvoiceControllerInterface;
import com.mycompany.invoice.invoice.services.InvoiceServiceInterface;
import org.springframework.stereotype.Controller;

import java.util.Scanner;

@Controller
public class InvoiceControllerKeyboard implements InvoiceControllerInterface {

    private InvoiceServiceInterface invoiceService;

    @Override
    public String createInvoice(Invoice invoice) {
        System.out.println("What is the customer Name ? ");
        Scanner sc = new Scanner(System.in);
        String customerName = sc.nextLine();
        invoice = new Invoice();
        Customer customer = new Customer(customerName);
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
