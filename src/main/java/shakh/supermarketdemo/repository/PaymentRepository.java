package shakh.supermarketdemo.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import shakh.supermarketdemo.data.Payment;

@Repository
public interface PaymentRepository  extends CrudRepository<Payment , Long> {
}
