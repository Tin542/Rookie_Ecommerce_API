package com.tinnt.AssigmentRookie.controller;

import com.tinnt.AssigmentRookie.constans.ErrorCode;
import com.tinnt.AssigmentRookie.constans.SuccessCode;
import com.tinnt.AssigmentRookie.converter.BookConverter;
import com.tinnt.AssigmentRookie.converter.CategoryConverter;
import com.tinnt.AssigmentRookie.converter.RatingConverter;
import com.tinnt.AssigmentRookie.dto.BookDTO;
import com.tinnt.AssigmentRookie.dto.CategoryDTO;
import com.tinnt.AssigmentRookie.dto.RatingDTO;
import com.tinnt.AssigmentRookie.dto.ResponseDTO;
import com.tinnt.AssigmentRookie.entity.Account;
import com.tinnt.AssigmentRookie.entity.Book;
import com.tinnt.AssigmentRookie.entity.Category;
import com.tinnt.AssigmentRookie.entity.Rating;
import com.tinnt.AssigmentRookie.exception.AddException;
import com.tinnt.AssigmentRookie.exception.DeleteException;
import com.tinnt.AssigmentRookie.exception.NotFoundException;
import com.tinnt.AssigmentRookie.exception.UpdateException;
import com.tinnt.AssigmentRookie.service.AccountService;
import com.tinnt.AssigmentRookie.service.BookService;
import com.tinnt.AssigmentRookie.service.CategoryService;
import com.tinnt.AssigmentRookie.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("/admin")
public class AdminController {

    private CategoryService cateService;
    private CategoryConverter cateConvert;
    private BookService bookService;
    private BookConverter bookConverter;
    private RatingConverter rateConvert;
    private RatingService rateService;
    private AccountService accService;

    @Autowired
    public AdminController(CategoryService cateService, CategoryConverter cateConvert, BookService bookService, BookConverter bookConverter, RatingConverter rateConvert, RatingService rateService, AccountService accService) {
        this.cateService = cateService;
        this.cateConvert = cateConvert;
        this.bookService = bookService;
        this.bookConverter = bookConverter;
        this.rateConvert = rateConvert;
        this.rateService = rateService;
        this.accService = accService;
    }

    //Add Category
    @PostMapping(value = "/add-category")
    public ResponseEntity<ResponseDTO> addCategory(@Valid @RequestBody CategoryDTO categoryDTO){
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            Category cateEntity = cateConvert.toEntity(categoryDTO);
            cateEntity = cateService.addCategory(cateEntity);
            responseDTO.setData(cateConvert.toDTO(cateEntity));
            responseDTO.setSuccessCode(SuccessCode.CATEGORY_ADD_SUCCESS);
        } catch (Exception e) {
            throw new AddException(e.getMessage());
        }
        return ResponseEntity.ok().body(responseDTO);
    }

    //Update Category
    @PutMapping(value = "/update-category/{id}")
    public ResponseEntity<ResponseDTO> updateCategory(@Valid @RequestBody CategoryDTO cateDTO, @PathVariable(name = "id")Long id) {
        ResponseDTO respone = new ResponseDTO();
        try {
            Category cateEntity = cateService.updateCategory(cateConvert.toEntity(cateDTO), id);
            respone.setData(cateConvert.toDTO(cateEntity));
            respone.setSuccessCode(SuccessCode.CATEGORY_UPDATE_SUCCESS);

        } catch (Exception e) {
            throw new UpdateException(e.getMessage());
        }
        return ResponseEntity.ok().body(respone);
    }

    //Add Book
    @PostMapping(value = "/add-book")
    public ResponseEntity<ResponseDTO> addBook(@Valid @RequestBody BookDTO bookDTO){
        ResponseDTO response = new ResponseDTO();
        try {
            Optional<Category> optional = cateService.getCategoryByName(bookDTO.getCategoryName());
            if(optional.isEmpty()){
                response.setErrorCode(ErrorCode.CATEGORY_FIND_ERROR);
            }else{
                Category cateEntity = optional.get();
                Book bookEntity = bookConverter.toEntity(bookDTO);
                bookEntity.setCategory(cateEntity);
                bookEntity = bookService.saveBook(bookEntity);
                response.setData(bookConverter.toDTO(bookEntity));
                response.setSuccessCode(SuccessCode.BOOK_ADD_SUCCESS);
            }

        }catch(Exception e){
            response.setErrorCode(ErrorCode.BOOK_ADD_ERROR);
            throw new AddException(e.getMessage());

        }
        return ResponseEntity.ok().body(response);
    }

    //Update Book
    @PutMapping(value = "/update-book/{id}")
    public ResponseEntity<ResponseDTO> updateBook(@Valid @RequestBody BookDTO bookDTO, @PathVariable(name = "id") long id){
        ResponseDTO response = new ResponseDTO();
        try {
            Book bookEntity = bookConverter.toEntity(bookDTO);
            bookEntity = bookService.updateBook(bookEntity, id);
            response.setData(bookConverter.toDTO(bookEntity));
            response.setSuccessCode(SuccessCode.BOOK_UPDATE_SUCCESS);
        }catch(Exception e){
            throw new UpdateException(e.getMessage());
        }
        return ResponseEntity.ok().body(response);
    }

    //Delete book
    @PutMapping(value = "/delete-book/{id}")
    public ResponseEntity<ResponseDTO> deleteBook(@PathVariable(name = "id")long id){
        ResponseDTO response = new ResponseDTO();
        try {
            Optional<Book> optional = bookService.getBookByID(id);
            if(optional.isEmpty()){
                response.setErrorCode(ErrorCode.BOOK_FIND_ERROR);
            }else{
                Book bookEntity = optional.get();
                bookEntity.setDelete(true);
                bookService.saveBook(bookEntity);

                response.setData(bookConverter.toDTO(bookEntity));
                response.setSuccessCode(SuccessCode.BOOK_DELETE_SUCCESS);
            }
        }catch (Exception e){
            throw new DeleteException(e.getMessage());
        }
        return ResponseEntity.ok().body(response);
    }

    //search book (by name or category id)
    @GetMapping(value = "/search-book/{keyword}")
    public ResponseEntity<ResponseDTO> searchBook(@PathVariable(name = "keyword")String keyword,
                                                  @RequestParam(defaultValue = "0") int page,
                                                  @RequestParam(defaultValue = "3") int size){//number item of page
        ResponseDTO response = new ResponseDTO();
        try {
            Pageable paging = PageRequest.of(page, size);
            Page<Book> pageBook = bookService.searchBook(keyword, paging);

            //retrieve the List of items in the page.
            List<Book> listBookEntity = pageBook.getContent();
            List<BookDTO> listBookDTO = listBookEntity.stream()
                    .map(bookConverter::toDTO)
                    .collect(Collectors.toList());

            HashMap<String, Object> map = new HashMap<>();
            map.put("Books",listBookDTO);
            map.put("currentPage", pageBook.getNumber());//current Page.
            map.put("totalItems", pageBook.getTotalElements());//total items stored in database.
            map.put("totalPages", pageBook.getTotalPages());//number of total pages.

            response.setData(map);
            response.setSuccessCode(SuccessCode.BOOK_GET_SUCCESS);

        }catch(Exception e){
            throw new NotFoundException(e.getMessage());
        }
        return ResponseEntity.ok().body(response);
    }

    //add rating
    @PostMapping(value = "/rating")
    public ResponseEntity<ResponseDTO> addRating (@RequestBody RatingDTO rateDTO){
        ResponseDTO response = new ResponseDTO();
        try {
            Optional<Account> optional = accService.findByUsername(rateDTO.getUsername());
            if(optional.isEmpty()){
                response.setErrorCode(ErrorCode.ACCOUNT_NOT_FOUND);
            }else{
                Account account = optional.get();

                Rating rateEntity = rateConvert.toEntity(rateDTO);
                rateEntity.setAccountRate(account);
                rateEntity = rateService.addRating(rateEntity);

                bookService.updateBookRating(rateDTO.getBookID());

                response.setData(rateConvert.toDTO(rateEntity));
                response.setSuccessCode(SuccessCode.RATING_ADD_SUCCESS);
            }

        }catch (Exception e){
            throw new AddException(e.getMessage());
        }

        return ResponseEntity.ok().body(response);
    }

    //Edit rate
    @PutMapping(value = "/all/{id}")
    public ResponseEntity<ResponseDTO> editRating(@PathVariable(name = "id")Long id, @RequestBody RatingDTO rateDTO){
        ResponseDTO response = new ResponseDTO();
        try {
            Optional<Rating> optionalRating = rateService.getRatingById(id);
            if(optionalRating.isEmpty()){
                response.setErrorCode(ErrorCode.RATING_FIND_ERROR);
            }else{
                Rating rateEntity = optionalRating.get();
                Optional<Account> optionalAcc = accService.findByUsername(rateDTO.getUsername());
                if(optionalAcc.isEmpty()){
                    response.setErrorCode(ErrorCode.ACCOUNT_NOT_FOUND);
                }else{
                    Account acc = optionalAcc.get();
                    rateEntity = rateConvert.toEntity(rateDTO);
                    rateEntity.setAccountRate(acc);
                    rateEntity.setRateID(id);
                    rateEntity = rateService.editRating(rateEntity);
                    response.setSuccessCode(SuccessCode.RATING_UPDATE_SUCCESS);
                    response.setData(rateConvert.toDTO(rateEntity));
                }
            }
        }catch (Exception e){
            throw new UpdateException(e.getMessage());
        }

        return ResponseEntity.ok().body(response);
    }

    //delete rate
    @DeleteMapping(value = "/all/{id}")
    public ResponseEntity<ResponseDTO> deleteRating(@PathVariable(name = "id")Long id){
        ResponseDTO response = new ResponseDTO();
        try {
            Optional<Rating> optional = rateService.getRatingById(id);
            if(optional.isEmpty()){
                response.setErrorCode(ErrorCode.RATING_FIND_ERROR);
            }else{
                rateService.deleteRating(id);
                response.setSuccessCode(SuccessCode.RATING_DELETE_SUCCESS);
                response.setData(true);
            }
        }catch (Exception e){
            response.setData(false);
            throw new DeleteException(e.getMessage());
        }
        return ResponseEntity.ok().body(response);
    }
}
