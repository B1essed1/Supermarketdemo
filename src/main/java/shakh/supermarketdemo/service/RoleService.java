package shakh.supermarketdemo.service;

import shakh.supermarketdemo.data.securitymodel.Role;

public interface RoleService {
    Role getRoleByName(String name);
    void save(Role role);
}
