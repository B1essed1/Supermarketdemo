package shakh.supermarketdemo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import shakh.supermarketdemo.data.securitymodel.AdminReserve;
@Repository
public interface AdminReserveRepository extends CrudRepository<AdminReserve, Long> {
    AdminReserve findByEmail(String email);
    AdminReserve deleteByEmail(String email);
}
