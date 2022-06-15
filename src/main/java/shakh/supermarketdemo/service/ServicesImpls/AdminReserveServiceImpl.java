package shakh.supermarketdemo.service.ServicesImpls;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import shakh.supermarketdemo.data.securitymodel.AdminReserve;
import shakh.supermarketdemo.repository.AdminReserveRepository;
import shakh.supermarketdemo.service.AdminReserveService;

@RequiredArgsConstructor
@Slf4j
@Service
public class AdminReserveServiceImpl implements AdminReserveService {

    private final AdminReserveRepository reserveRepository;

    @Override
    public AdminReserve findAdminReserveByMail(String email) {
        return reserveRepository.findByEmail(email);
    }

    @Override
    public void save(AdminReserve adminReserve) {
      AdminReserve adminReserve1 =   reserveRepository.save(adminReserve);
      if (adminReserve1 !=null) log.info("admin reserve saved {}" , adminReserve1.getEmail());
      else log.error("admin reserved does not saved");

    }

    @Override
    public void deleteByMail(String email) {
        reserveRepository.deleteByEmail(email);
    }


}
