package shakh.supermarketdemo.service;

import shakh.supermarketdemo.data.Debitors;

public interface DebitorService
{
    Debitors save(Debitors debitors);
    void deleteById(Long id);
}
