package pl.kartven.portfoliopage.category;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDto> getCategoryById(@PathVariable Long id){
        return ResponseEntity.ok(categoryService.getCategoryById(id));
    }

    @GetMapping
    List<CategoryDto> getAllCategories() {
        return categoryService.getCategories();
    }

    @PostMapping
    CategoryDto createCategory(@RequestBody CategorySaveDto categorySaveDto) {
        return categoryService.createCategory(categorySaveDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryDto> updateCategory(@PathVariable Long id, @RequestBody CategoryDto categoryDto) {
        categoryDto.setId(id);
        return ResponseEntity.ok(categoryService.updateCategory(categoryDto));
    }

}
