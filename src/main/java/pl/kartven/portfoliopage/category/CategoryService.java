package pl.kartven.portfoliopage.category;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<CategoryDto> getCategory() {
        return categoryRepository.findAll()
                .stream()
                .map(CategoryDtoMapper::map)
                .toList();
    }

    Optional<CategoryDto> getCategoryById(Long id){
        return categoryRepository.findById(id)
                .map(CategoryDtoMapper::map);
    }

}
