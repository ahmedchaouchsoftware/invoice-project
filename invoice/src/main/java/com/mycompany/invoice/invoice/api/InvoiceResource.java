package com.mycompany.invoice.invoice.api;

import com.company.invoice.core.entity.customer.Address;
import com.company.invoice.core.entity.customer.Customer;
import com.company.invoice.core.entity.invoice.Invoice;
import com.mycompany.invoice.invoice.services.InvoiceServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.ParallelFlux;
import reactor.core.scheduler.Schedulers;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/invoice")
public class InvoiceResource {

    @Autowired
    private InvoiceServiceInterface invoiceService;

    @Autowired
    private WebClient.Builder webClientBuilder;

    /*@PostMapping
    public Invoice create(@RequestBody Invoice invoice) {
        return invoiceService.createInvoice(invoice);
    }*/

    @GetMapping
    public ParallelFlux<Invoice> list() {
        System.out.println("La méthode display Home a été invoquée");
        List<Mono<Invoice>> invoiceMonos = new ArrayList<>();
        final WebClient webClient = webClientBuilder.baseUrl("http://customer-service/").build();
        Iterable<Invoice> invoices = invoiceService.getInvoiceList();
        invoices.forEach(invoice -> {
            invoiceMonos.add(webClient.get().uri("/customer/"+invoice.getIdCustomer())
                    .retrieve()
                    .bodyToMono(Customer.class)
                    .map(customer -> {
                        invoice.setCustomer(customer);
                        return invoice;
                    }));
            /*invoice.setCustomer(restTemplate.getForObject("customer/"+invoice.getIdCustomer(), Customer.class));*/
        });

        final Flux<Invoice> invoiceFlux = Flux.concat(invoiceMonos);
        return invoiceFlux.parallel().runOn(Schedulers.boundedElastic());
    }

   @GetMapping("/{id}")
    public Invoice get(@PathVariable("id") String id) {
        System.out.println("La méthode display Invoice a été invoquée");
        Invoice invoice = invoiceService.getInvoiceByNumber(id);
       final WebClient webClient = webClientBuilder.baseUrl("http://customer-service/").build();
        final Customer customer = webClient.get()
                .uri("http://customer-service/customer/"+invoice.getIdCustomer(), Customer.class)
                .retrieve()
                .bodyToMono(Customer.class)
                .block();
        assert customer != null;
        final Address address = webClient.get()
                .uri("http://customer-service/address/"+customer.getAddress().getId(), Address.class)
                .retrieve()
                .bodyToMono(Address.class)
                .block();
        customer.setAddress(address);
        invoice.setCustomer(customer);
        return invoice;
    }

    /*@GetMapping("/create-form")
   public String displayInvoiceCreateForm(@ModelAttribute InvoiceForm invoiceForm){
       return "invoice-create-form";
   }*/
    public InvoiceServiceInterface getInvoiceService() {
        return invoiceService;
    }

    public void setInvoiceService(InvoiceServiceInterface invoiceService) {
        this.invoiceService = invoiceService;
    }
}
