package shakh.supermarketdemo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import shakh.supermarketdemo.data.Debitors;

@Repository
public interface DebitorsRepository extends CrudRepository<Debitors , Long>
{

}
