package shakh.supermarketdemo.service;

import javassist.NotFoundException;
import shakh.supermarketdemo.data.Debitors;
import shakh.supermarketdemo.dto.PersonVisualisationDto;

import java.util.List;

public interface DebitorService
{
    Debitors save(PersonVisualisationDto debitors);
    void deleteById(Long id);
    Debitors getDebitorById(Long id) ;
    List<PersonVisualisationDto> getAllDebitors() ;
}
