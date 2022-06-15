package shakh.supermarketdemo.service.ServicesImpls;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import shakh.supermarketdemo.data.securitymodel.Role;
import shakh.supermarketdemo.repository.RoleRepository;
import shakh.supermarketdemo.service.RoleService;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    @Override
    public Role getRoleByName(String name) {
      return   roleRepository.findByName(name);
    }

    @Override
    public void save(Role role) {
        roleRepository.save(role);
    }
}
