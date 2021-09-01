package com.tinnt.AssigmentRookie.repository;

import com.tinnt.AssigmentRookie.entity.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class CategoryRepositoryTest {

    private List<Category> list;

    @MockBean
    private Category category;

    @Autowired
    CategoryRepository cateRepository;

    @Test
    public void testFindByNameMethod_ReturnShouldBeCategory()throws Exception{
        Category cate = new Category();
        cate.setCategoryID(1);
        cate.setCategoryName("Comic");
        assertNotNull(cateRepository.save(cate));

        Category cate2 = cateRepository.findByName(cate.getCategoryName()).get();
        assertEquals(cate.getCategoryID(), cate2.getCategoryID());
    }
}
