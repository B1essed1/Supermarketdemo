package shakh.supermarketdemo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import shakh.supermarketdemo.data.Admins;

import java.util.Optional;

@Repository
public interface AdminRepository extends CrudRepository<Admins, Long>
{
    Optional<Admins> findAdminsByUsername(String name);

}
