package shakh.supermarketdemo.service;

import shakh.supermarketdemo.data.Admins;

public interface AdminService
{
    Admins save(Admins admins);
    void delete(Long id);
}
