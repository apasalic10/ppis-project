package ba.unsa.etf.nwt.service;

import ba.unsa.etf.nwt.DTO.CategoryPostDTO;
import ba.unsa.etf.nwt.entity.Category;
import ba.unsa.etf.nwt.repository.CategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository, ModelMapper modelMapper) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category with ID " + id + " not found"));
    }

    public Category createCategory(CategoryPostDTO dto) {
        Category category = new Category();

        category.setName(dto.getName());
        category.setDescription(dto.getDescription());
        category.setIconUrl(dto.getIconUrl());
        category.setSortOrder(dto.getSortOrder());

        if (dto.getParentCategory() != null) {
            Optional<Category> parent = categoryRepository.findById(dto.getParentCategory().getId());
            parent.ifPresent(category::setParentCategory);
        }

        Category saved = categoryRepository.save(category);

        if (dto.getSubcategory() != null && !dto.getSubcategory().isEmpty()) {
            List<Category> subcategories = dto.getSubcategory().stream().map(subDto -> {
                Category sub = new Category();
                sub.setName(subDto.getName());
                sub.setDescription(subDto.getDescription());
                sub.setIconUrl(subDto.getIconUrl());
                sub.setSortOrder(subDto.getSortOrder());
                sub.setParentCategory(saved);
                return sub;
            }).collect(Collectors.toList());

            categoryRepository.saveAll(subcategories);
            saved.setSubcategories(subcategories);
        }

        return saved;
    }

    public Category updateCategoryField(Long id, Map<String, Object> updates) {
        // Find the Category by ID
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category with ID " + id + " not found"));

        // Loop through the provided updates and set the fields dynamically
        updates.forEach((key, value) -> {
            switch (key) {
                case "name" -> category.setName(value.toString());
                case "description" -> category.setDescription(value.toString());
                case "iconUrl" -> category.setIconUrl(value.toString());
                case "sortOrder" -> category.setSortOrder(Integer.parseInt(value.toString()));
                case "parentCategory" -> {
                    // Assume the parent category is passed as an ID
                    Category parentCategory = categoryRepository.findById(Long.parseLong(value.toString()))
                            .orElseThrow(() -> new RuntimeException("Parent Category with ID " + value + " not found"));
                    category.setParentCategory(parentCategory);
                }
                default -> throw new IllegalArgumentException("Invalid field: " + key);
            }
        });

        return  categoryRepository.save(category);
    }

}
