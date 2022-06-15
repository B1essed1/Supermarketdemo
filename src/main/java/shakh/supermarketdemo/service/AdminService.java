package shakh.supermarketdemo.service;

import javassist.NotFoundException;
import shakh.supermarketdemo.data.ProductOrder;
import shakh.supermarketdemo.data.securitymodel.AdminReserve;
import shakh.supermarketdemo.data.securitymodel.Admins;
import shakh.supermarketdemo.dto.AdminRegDto;
import shakh.supermarketdemo.dto.PersonVisualisationDto;

import java.util.List;

public interface AdminService
{
    Admins save(Admins admins);
    Admins getAdminsByMail(String mail);
    Admins getAdminByUsername(String name);
    Admins getAdminById(Long id) ;
    List<PersonVisualisationDto> getAllAdmins();
    Admins cast(AdminReserve adminReserve);
}
