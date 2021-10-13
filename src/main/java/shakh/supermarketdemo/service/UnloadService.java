package shakh.supermarketdemo.service;

import shakh.supermarketdemo.data.Product;
import shakh.supermarketdemo.data.Unload;

import java.util.List;
import java.util.Set;

public interface UnloadService
{
    Unload save (Unload unload);

    Unload getById(Long id);
    Unload getUnloadsById(Long id);
    Set<Unload> getAll();
}
