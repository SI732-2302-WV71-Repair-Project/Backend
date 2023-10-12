package com.api.repairapi.controllers;

import com.api.repairapi.models.PaymentsModel;
import com.api.repairapi.services.PaymentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/payments")
public class PaymentsController {

    @Autowired
    private PaymentsService paymentsService;

    @GetMapping
    public ArrayList<PaymentsModel> getPayments(){
        return this.paymentsService.getPayments();
    }

    @GetMapping(path = "/{id}")
    public Optional<PaymentsModel> getPaymentById(@PathVariable Long id){
        return this.paymentsService.getPaymentsById(id);
    }
    @PostMapping
    public PaymentsModel savePayment(@RequestBody PaymentsModel payment){
        return this.paymentsService.savePayment(payment);
    }

    @PutMapping(path = "/{id}")
    public PaymentsModel updatePayment(@RequestBody PaymentsModel request, @PathVariable Long id){
        return this.paymentsService.updatePayment(request, id);
    }

    @DeleteMapping(path = "/{id}")
    public void deletePayment(@PathVariable Long id){
        this.paymentsService.deletePayment(id);
    }
}
