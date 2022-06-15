package shakh.supermarketdemo.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import shakh.supermarketdemo.data.Debitors;
import shakh.supermarketdemo.dto.PersonVisualisationDto;

import java.util.List;

@Repository
public interface DebitorsRepository extends CrudRepository<Debitors , Long>
{
    @Query("select new shakh.supermarketdemo.dto.PersonVisualisationDto(d.id, d.firstName,d.lastName,d.phoneNumber,d.additionalDetails) from Debitors d where d.isActive = true ")
    List<PersonVisualisationDto> getAllByIsActiveIsTrue();

}
