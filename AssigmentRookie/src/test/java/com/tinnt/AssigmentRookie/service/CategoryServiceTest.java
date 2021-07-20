package com.tinnt.AssigmentRookie.service;

import com.tinnt.AssigmentRookie.entity.Category;
import com.tinnt.AssigmentRookie.repository.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@SpringBootTest
public class CategoryServiceTest {

    @Autowired
    private CategoryService cateService;

    @MockBean
    private CategoryRepository cateRepository;

    @MockBean
    private Category category;

    private List<Category> list;

    @BeforeEach
    public void setUp() {
        list = new ArrayList<>();
        Category cate1 = new Category(1L, "Comic 1");
        Category cate2 = new Category(2L, "Comic 2");
        Category cate3 = new Category(3L, "Comic 3");
        list.add(cate1);
        list.add(cate2);
        list.add(cate3);
    }

    //test add category
    @Test
    public void addCategory_thenReturnCategory() throws Exception{
        when(cateRepository.save(list.get(0))).thenReturn(list.get(0));
        assertEquals(cateService.addCategory(list.get(0)), list.get(0));
    }

    // test get all category
    @Test
    public void getAllCategory_thenReturnListCategory() throws Exception{
        when(cateRepository.findAll()).thenReturn(list);
        assertEquals(cateService.getAllCategory(), list);
    }

    // test get by id
    @Test
    public void whenValidID_thenCategoryShouldBeFound() throws Exception {
        Category cate = new Category();
        Long CateID = 1L;
        Optional<Category> optional = Optional.of(cate);
        assertNotNull(optional);
        when(cateRepository.findById(CateID)).thenReturn(optional);
        Category cate2 = cateService.getCategoryByID(CateID).get();
        assertEquals(cate2.getCategoryName(), cate.getCategoryName());
    }

    //test get by name
    @Test
    public void whenNameNotNull_thenCategoryShouldBeFound()throws Exception{
        Category cate = new Category();
        String name = "Comic 1";
        Optional<Category> optional = Optional.of(cate);
        assertNotNull(optional);
        when(cateRepository.findByName(name)).thenReturn(optional);
        Category cate2 = cateService.getCategoryByName(name).get();
        assertEquals(cate,cate2);
    }

    // test update category
    @Test
    public void updateCate_ThenReturnCategory() throws Exception {
        Category cate = new Category();
        Long CateID = 1L;
        Optional<Category> optional = Optional.of(cate);
        assertNotNull(optional);
        when(cateRepository.findById(CateID)).thenReturn(optional);
        when(cateRepository.save(optional.get())).thenReturn(cate);
        Category cate2 = cateService.updateCategory(cate,CateID);
        assertEquals(cate2.getCategoryName(), cate.getCategoryName());
    }

}
