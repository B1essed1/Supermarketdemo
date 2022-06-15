package shakh.supermarketdemo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import shakh.supermarketdemo.data.Category;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Long> {

}
