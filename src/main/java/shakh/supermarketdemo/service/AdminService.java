package shakh.supermarketdemo.service;

import shakh.supermarketdemo.data.securitymodel.Admins;

public interface AdminService
{
    Admins save(Admins admins);
    void delete(Long id);
}
