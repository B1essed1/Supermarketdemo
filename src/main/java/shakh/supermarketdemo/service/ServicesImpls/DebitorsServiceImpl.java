package shakh.supermarketdemo.service.ServicesImpls;

import javassist.NotFoundException;
import org.springframework.stereotype.Service;
import shakh.supermarketdemo.data.Debitors;
import shakh.supermarketdemo.data.OrderItem;
import shakh.supermarketdemo.dto.PersonVisualisationDto;
import shakh.supermarketdemo.dto.ProductOrderDto;
import shakh.supermarketdemo.repository.DebitorsRepository;
import shakh.supermarketdemo.service.DebitorService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class DebitorsServiceImpl implements DebitorService {

    private final DebitorsRepository debitorsRepository;

    public DebitorsServiceImpl(DebitorsRepository debitorsRepository) {
        this.debitorsRepository = debitorsRepository;
    }

    @Override
    public Debitors save(PersonVisualisationDto debitors) {
        Debitors debitor = new Debitors();

        debitor.setIsActive(true);
        debitor.setFirstName(debitors.getFirstName());
        debitor.setLastName(debitors.getLastName());
        debitor.setAdditionalDetails(debitors.getAdditionalDetails());
        debitor.setPhoneNumber(debitors.getPhoneNumber());
        debitor.setCreatedDate(new Date());
         return debitorsRepository.save(debitor);
    }

    @Override
    public void deleteById(Long id) {
        debitorsRepository.deleteById(id);
    }

    @Override
    public Debitors getDebitorById(Long id) {

        Optional<Debitors> debitors = debitorsRepository.findById(id);
        if (debitors.isEmpty()) throw new RuntimeException("bi id boyicha debitor topilmadi!");
        else return debitors.get();
    }

    @Override
    public List<PersonVisualisationDto> getAllDebitors() {

        List<PersonVisualisationDto> debitors = debitorsRepository.getAllByIsActiveIsTrue();
        if (debitors.isEmpty()) throw new RuntimeException("hali birorta ham debitor qoshilmagan");
        else return debitors;
    }

}
