package shakh.supermarketdemo.service.ServicesImpls;

import org.springframework.stereotype.Service;
import shakh.supermarketdemo.data.Debitors;
import shakh.supermarketdemo.repository.DebitorsRepository;
import shakh.supermarketdemo.service.DebitorService;

@Service
public class DebitorsServiceImpl implements DebitorService{

    private  final DebitorsRepository debitorsRepository;

    public DebitorsServiceImpl(DebitorsRepository debitorsRepository) {
        this.debitorsRepository = debitorsRepository;
    }

    @Override
    public Debitors save(Debitors debitors) {
        return debitorsRepository.save(debitors);
    }

    @Override
    public void deleteById(Long id) {
        debitorsRepository.deleteById(id);
    }

}
