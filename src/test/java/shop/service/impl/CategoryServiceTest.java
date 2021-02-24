package shop.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import shop.dao.impl.CategoryDao;
import shop.domain.Category;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class CategoryServiceTest {

    private CategoryService categoryService;

    @Mock
    CategoryDao categoryDao;

    Category category;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        categoryService = new CategoryService(categoryDao);
        category = new Category();
        category.setId(1L);
        category.setName("Cat");
        category.setVisible(true);
    }

    @Test
    void findOne() {
        when(categoryDao.findOne(1L)).thenReturn(java.util.Optional.ofNullable(category));
        Category cat = categoryService.findOne(1L);
        assertNotNull(cat);
        assertEquals(Long.valueOf(1), cat.getId());
    }

    @Test
    void findAll() {
    }

    @Test
    void create() {
    }

    @Test
    void delete() {
    }

    @Test
    void createFromDto() {
    }

    @Test
    void getAllProperties() {
    }

    @Test
    void findAllFiltered() {
    }

    @Test
    void findAllVisible() {
    }

    @Test
    void findByName() {
    }

    @Test
    void update() {
    }

    @Test
    void getAllPropertiesByCategoryId() {
    }

    @Test
    void getAllCategoryDto() {
    }

    @Test
    void getDtoById() {
    }

    @Test
    void testUpdate() {
    }
}