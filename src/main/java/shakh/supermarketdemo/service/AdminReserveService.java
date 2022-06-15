package shakh.supermarketdemo.service;


import shakh.supermarketdemo.data.securitymodel.AdminReserve;

public interface AdminReserveService {
    AdminReserve findAdminReserveByMail(String email);
    void save(AdminReserve adminReserve);
    void deleteByMail(String email);
}
