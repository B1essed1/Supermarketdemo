package shakh.supermarketdemo.service.ServicesImpls;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shakh.supermarketdemo.data.Product;
import shakh.supermarketdemo.data.Unload;
import shakh.supermarketdemo.dto.UnloadDto;
import shakh.supermarketdemo.exceptions.UnloadNotFoundException;
import shakh.supermarketdemo.repository.UnloadRepository;
import shakh.supermarketdemo.service.ProductService;
import shakh.supermarketdemo.service.UnloadService;

import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class UnlaodServiceImpl implements UnloadService
{

    private final UnloadRepository unloadRepository;
    private final ProductService productService;

    @Override
    public Unload save(Unload unload)
    {
        return unloadRepository.save(unload);
    }

    @Override
    public Unload getById(Long id) {

        Optional<Unload> unload = unloadRepository.findById(id);
        return unload.get();
    }

    @Override
    public Unload getUnloadsById(Long id)
    {
        Unload unload = unloadRepository.findUnloadById(id);

        if (unload != null)
        return unload;
        else throw new UnloadNotFoundException("#### Get unloads by id Method is not working ");
    }


    @Override
    public Set<Unload> getAll()
    {

        Set<Unload> allUnloads = new HashSet<>();
        unloadRepository.findAll().forEach(allUnloads::add);

        if (allUnloads.isEmpty()) throw new UnloadNotFoundException("############## that shit unloads not found ");
        return allUnloads;
    }

    @Override
    public Unload unloadProduct(UnloadDto unloadDto) {
        Product product = productService.getProductById(unloadDto.getProductId());
        Unload unload = new Unload();
        unload.setAmount(unloadDto.getAmount());
        unload.setCreatedTime(new Date());
        unload.setPriceOfBuy(unloadDto.getPriceOfBuy());
        unload.setPriceOfSell(unloadDto.getPriceOfSell());
        unload.setIsActive(unloadDto.getIsActive());
        unloadRepository.save(unload);
        product.setLastUpdatedTime(new Date());
        product.setPriceOfSell(unload.getPriceOfSell());
        product.setPriceOfBuy(unloadDto.getPriceOfBuy());
        product.setAmount(product.getAmount()+ unloadDto.getAmount());
        Set<Unload> unloads = new HashSet<>();
        unloads.add(unload);
        product.setUnload(unloads);
        productService.save(product);
        return unload;
    }


}
