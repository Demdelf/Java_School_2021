package shop.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import shop.dao.impl.PropertyDao;
import shop.domain.Property;
import shop.dto.PropertyDto;

class PropertyServiceTest {
    private PropertyService propertyService;

    @Mock
    PropertyDao dao;

    @Mock
    CategoryService categoryService;

    Property property;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        propertyService = new PropertyService(dao, categoryService);
        property = new Property();
        property.setId(1L);
        property.setName("Length");
        property.setType("cm");
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void findOneTest() {
        when(dao.findOne(1L)).thenReturn(Optional.ofNullable(property));
        Property property1 = propertyService.findOne(1L);
        assertNotNull(property1);
        assertEquals(Long.valueOf(1), property1.getId());
    }

    @Test
    void findAll() {

    }

    @Test
    void createFromDtoTest() {
        when(dao.create(any(Property.class))).thenReturn(property);
        PropertyDto testPropertyDto = new PropertyDto();
        testPropertyDto.setId(1L);
        testPropertyDto.setName("Length");
        testPropertyDto.setType("cm");

        Property testProperty = propertyService.create(testPropertyDto);
        assertNotNull(testProperty);
        assertEquals("Length", testProperty.getName());
        verify(dao, times(1)).create(any(Property.class));
    }

    @Test
    void delete() {
    }

    @Test
    void testCreate() {
    }

    @Test
    void convertPropertyDtoToProperty() {
        when(dao.create(any(Property.class))).thenReturn(property);
        PropertyDto testPropertyDto = new PropertyDto();
        testPropertyDto.setId(1L);
        testPropertyDto.setName("Length");
        testPropertyDto.setType("cm");

        Property testProperty = propertyService.convertPropertyDtoToProperty(testPropertyDto);
        assertNotNull(testProperty);
        assertEquals("Length", testProperty.getName());
    }

    @Test
    void findByName() {
    }

    @Test
    void update() {
    }

    @Test
    void findAllUniq() {
    }

    @Test
    void getNewPropertyDto() {
    }
}
