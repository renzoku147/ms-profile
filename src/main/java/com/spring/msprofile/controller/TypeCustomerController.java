package com.spring.msprofile.controller;

import com.spring.msprofile.entity.TypeCustomer;
import com.spring.msprofile.service.TypeCustomerService;
import jdk.swing.interop.SwingInterOpUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RefreshScope
@RestController
@Slf4j
@RequestMapping("/typecustomer")
public class TypeCustomerController {

    @Autowired
    TypeCustomerService typeCustomerService;

    @GetMapping("/list")
    public Flux<TypeCustomer> list(){
        return typeCustomerService.findAll();
    }

    @GetMapping("/find/{id}")
    public Mono<TypeCustomer> findById(@PathVariable String id){
        return typeCustomerService.findById(id);
    }

    @PostMapping("/create")
    public Mono<ResponseEntity<TypeCustomer>> create(@RequestBody TypeCustomer typeCustomer){
        return typeCustomerService.checkSubType(typeCustomer.getSubType().getId())
                .flatMap(subType -> {
                    System.out.println("subType : " + subType.getValue());
                    typeCustomer.setSubType(subType);
                    System.out.println(typeCustomer);
                    return typeCustomerService.create(typeCustomer)
                            .map(tc -> new ResponseEntity<>(tc , HttpStatus.CREATED));
                })
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/update")
    public Mono<ResponseEntity<TypeCustomer>> update(@Valid @RequestBody TypeCustomer c) {
        return typeCustomerService.update(c)
                .map(savedCustomer -> new ResponseEntity<>(savedCustomer, HttpStatus.CREATED))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/delete/{id}")
    public Mono<ResponseEntity<String>> delete(@PathVariable String id) {
        return typeCustomerService.delete(id)
                .filter(deleteCustomer -> deleteCustomer)
                .map(deleteCustomer -> new ResponseEntity<>("Customer Deleted", HttpStatus.ACCEPTED))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
