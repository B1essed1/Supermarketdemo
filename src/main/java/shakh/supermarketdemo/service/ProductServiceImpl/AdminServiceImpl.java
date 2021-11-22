package shakh.supermarketdemo.service.ProductServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shakh.supermarketdemo.data.Admins;
import shakh.supermarketdemo.repository.AdminRepository;
import shakh.supermarketdemo.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminRepository adminRepository;

    @Override
    public Admins save(Admins admins)
    {
        return adminRepository.save(admins);
    }

    @Override
    public void delete(Long id) {
         adminRepository.deleteById(id);
    }
}
