package shakh.supermarketdemo.service;

import javassist.NotFoundException;
import shakh.supermarketdemo.data.ProductOrder;
import shakh.supermarketdemo.data.securitymodel.Admins;

import java.util.List;

public interface AdminService
{
    Admins save(Admins admins);
    void delete(Long id);
    Admins getAdminsByOrder(ProductOrder order);
    Admins getAdminById(Long id) ;
    List<Admins> getAllAdmins();
}
