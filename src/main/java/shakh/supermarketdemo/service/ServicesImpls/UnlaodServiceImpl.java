package shakh.supermarketdemo.service.ServicesImpls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shakh.supermarketdemo.data.Unload;
import shakh.supermarketdemo.exceptions.UnloadNotFoundException;
import shakh.supermarketdemo.repository.UnloadRepository;
import shakh.supermarketdemo.service.UnloadService;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class UnlaodServiceImpl implements UnloadService
{
    @Autowired
    private UnloadRepository unloadRepository;

    @Override
    public Unload save(Unload unload)
    {
        LocalDateTime createdTime = LocalDateTime.now();
        unload.setCreatedTime(createdTime);
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


}
