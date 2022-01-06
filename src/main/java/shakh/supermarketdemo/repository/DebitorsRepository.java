package shakh.supermarketdemo.repository;

import org.springframework.data.repository.CrudRepository;
import shakh.supermarketdemo.data.Debitors;

public interface DebitorsRepository extends CrudRepository<Debitors , Long>
{

}
