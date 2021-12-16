package shakh.supermarketdemo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import shakh.supermarketdemo.data.securitymodel.Admins;

@Repository
public interface AdminRepository extends CrudRepository<Admins, Long>
{
    Admins findByUsername(String username);
}
