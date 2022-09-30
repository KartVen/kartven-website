package pl.kartven.portfoliopage.category;

import org.springframework.stereotype.Service;
import pl.kartven.portfoliopage.exception.DataNotFoundException;

import java.util.List;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<CategoryDto> getCategories() {
        return categoryRepository.findAll()
                .stream()
                .map(CategoryDtoMapper::map)
                .toList();
    }

    public CategoryDto createCategory(CategorySaveDto categorySaveDto) {
        Category category = new Category();
        category.setName(categorySaveDto.getName());
        return CategoryDtoMapper.map(categoryRepository.save(category));
    }

    public CategoryDto getCategoryById(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Category not exist with id: " + id));
        return CategoryDtoMapper.map(category);
    }

    public CategoryDto updateCategory(CategoryDto categoryDto) {
        Category category = categoryRepository.findById(categoryDto.getId())
                .orElseThrow(() -> new DataNotFoundException("Category not exist with id: " + categoryDto.getId()));

        category.setName(categoryDto.getName());
        return CategoryDtoMapper.map(categoryRepository.save(category));
    }
}
