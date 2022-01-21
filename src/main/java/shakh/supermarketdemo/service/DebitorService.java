package shakh.supermarketdemo.service;

import javassist.NotFoundException;
import shakh.supermarketdemo.data.Debitors;

import java.util.List;

public interface DebitorService
{
    Debitors save(Debitors debitors);
    void deleteById(Long id);
    Debitors getDebitorById(Long id) ;
    List<Debitors> getAllDebitors() ;
}
