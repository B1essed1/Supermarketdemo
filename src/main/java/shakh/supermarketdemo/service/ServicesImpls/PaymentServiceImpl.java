package shakh.supermarketdemo.service.ServicesImpls;

import org.springframework.stereotype.Service;
import shakh.supermarketdemo.data.Payment;
import shakh.supermarketdemo.repository.PaymentRepository;
import shakh.supermarketdemo.service.PaymentService;

import java.util.Optional;
@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;

    public PaymentServiceImpl(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }


    @Override
    public Payment getPaymentById(Long id) {
        Optional<Payment> payment =  paymentRepository.findById(id);
        return payment.get();
    }

    @Override
    public Payment save(Payment payment) {
        return paymentRepository.save(payment);
    }
}
