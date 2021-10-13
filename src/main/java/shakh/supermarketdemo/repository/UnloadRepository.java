package shakh.supermarketdemo.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import shakh.supermarketdemo.data.Product;
import shakh.supermarketdemo.data.Unload;

import java.util.List;

@Repository
public interface UnloadRepository  extends CrudRepository<Unload,Long>
{
    List<Product> findByProductId(@Param("id") Long id);
    Unload findUnloadById(@Param("id") Long id);

}
