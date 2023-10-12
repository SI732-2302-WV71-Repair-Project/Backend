package com.api.repairapi.services;

import com.api.repairapi.models.PaymentsModel;
import com.api.repairapi.repositories.IPaymentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class PaymentsService {

    @Autowired
    private IPaymentsRepository paymentsRepository;

    public ArrayList<PaymentsModel> getPayments(){
        return (ArrayList<PaymentsModel>) this.paymentsRepository.findAll();
    }

    public Optional<PaymentsModel> getPaymentsById(Long id){
        return this.paymentsRepository.findById(id);
    }

    public PaymentsModel savePayment(PaymentsModel payment){
        return this.paymentsRepository.save(payment);
    }

    public PaymentsModel updatePayment(PaymentsModel request, Long id){
        PaymentsModel paymentsModel = this.paymentsRepository.findById(id).get();

        paymentsModel.setAmount(request.getAmount());
        paymentsModel.setPaymentMethod(request.getPaymentMethod());
        paymentsModel.setPaymentStatus(request.getPaymentStatus());
        paymentsModel.setTypeService(request.getTypeService());
        paymentsModel.setUser(request.getUser());
        paymentsModel.setDeviceModel(request.getDeviceModel());
        return this.paymentsRepository.save(paymentsModel);
    }

    public void deletePayment(Long id){
        try{
            this.paymentsRepository.deleteById(id);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
