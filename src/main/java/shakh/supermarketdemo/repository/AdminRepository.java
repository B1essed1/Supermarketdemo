package shakh.supermarketdemo.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import shakh.supermarketdemo.data.ProductOrder;
import shakh.supermarketdemo.data.securitymodel.Admins;
import shakh.supermarketdemo.dto.AdminRegDto;
import shakh.supermarketdemo.dto.PersonVisualisationDto;


import java.util.List;

@Repository
public interface AdminRepository extends CrudRepository<Admins, Long>
{


    Admins findByUsername(String username);

    Admins findByEmail(String email);

    Admins findAdminsByOrders(ProductOrder order);

    @Query("select new shakh.supermarketdemo.dto.PersonVisualisationDto(a.id, a.firstName,a.lastName,a.username,a.email,a.phoneNumber, a.isActive) from Admins a")
    List<PersonVisualisationDto> getAllAdmins();

}

