package shakh.supermarketdemo.service.ServicesImpls;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import shakh.supermarketdemo.data.Category;
import shakh.supermarketdemo.repository.CategoryRepository;
import shakh.supermarketdemo.service.CategoryService;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    @Override
    public Category save(Category category) {
        return categoryRepository.save(category);
    }
}
