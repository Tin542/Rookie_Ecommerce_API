package com.tinnt.AssigmentRookie.controller;

import com.tinnt.AssigmentRookie.constans.ErrorCode;
import com.tinnt.AssigmentRookie.constans.SuccessCode;
import com.tinnt.AssigmentRookie.converter.*;
import com.tinnt.AssigmentRookie.dto.*;
import com.tinnt.AssigmentRookie.entity.*;
import com.tinnt.AssigmentRookie.exception.NotFoundException;
import com.tinnt.AssigmentRookie.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("/home")
public class HomeController {

    private CategoryService cateService;
    private CategoryConverter cateConvert;
    private BookService bookService;
    private BookConverter bookConverter;
    private RatingConverter rateConvert;
    private RatingService rateService;
    private PublisherConverter publisherConverter;
    private PublisherService publisherService;
    private AuthorService authorService;
    private AuthorConverter authorConverter;

    @Autowired
    public HomeController(CategoryService cateService, CategoryConverter cateConvert, BookService bookService, BookConverter bookConverter, RatingConverter rateConvert, RatingService rateService, PublisherConverter publisherConverter, PublisherService publisherService, AuthorService authorService, AuthorConverter authorConverter) {
        this.cateService = cateService;
        this.cateConvert = cateConvert;
        this.bookService = bookService;
        this.bookConverter = bookConverter;
        this.rateConvert = rateConvert;
        this.rateService = rateService;
        this.publisherConverter = publisherConverter;
        this.publisherService = publisherService;
        this.authorService = authorService;
        this.authorConverter = authorConverter;
    }

    //Get category by name
    @GetMapping(value = "/get-category-by-name/{name}")
    public ResponseEntity<ResponseDTO> getCateByName(@PathVariable(name = "name") String name){
        ResponseDTO response = new ResponseDTO();
        try {
            Category cateEntity = cateService.getCategoryByName(name).get();
            response.setData(cateConvert.toDTO(cateEntity));
            response.setSuccessCode(SuccessCode.CATEGORY_FIND_SUCCESS);
        } catch (Exception e) {
            throw new NotFoundException(e.getMessage());
        }
        return ResponseEntity.ok().body(response);
    }

    //Get category by Id
    @GetMapping(value = "/get-category-by-id/{id}")
    public ResponseEntity<ResponseDTO> getCateById(@PathVariable(name = "id")Long id) throws NotFoundException{
        ResponseDTO response = new ResponseDTO();
        try {
            Category cateEntity = cateService.getCategoryByID(id).get();
            response.setData(cateConvert.toDTO(cateEntity));
            response.setSuccessCode(SuccessCode.CATEGORY_FIND_SUCCESS);

        } catch (Exception e) {
            throw new NotFoundException(e.getMessage());
        }
        return ResponseEntity.ok().body(response);
    }

    //Get all category
    @GetMapping(value = "/category")
    public ResponseEntity<ResponseDTO> getAllCategory(){

        ResponseDTO response = new ResponseDTO();
        try {
            List<Category> listCateEntity = cateService.getAllCategory();
            List<CategoryDTO> listCateDTO = new ArrayList<>();
            for ( Category cateEntity : listCateEntity) {
                if(cateEntity.isDelete() == false){
                    CategoryDTO cateDTO = cateConvert.toDTO(cateEntity);
                    listCateDTO.add(cateDTO);
                }
            }

            response.setData(listCateDTO);
            response.setSuccessCode(SuccessCode.CATEGORY_GET_SUCCESS);
        } catch (Exception e) {
            throw new NotFoundException(ErrorCode.CATEGORY_FIND_ERROR);
        }
        return ResponseEntity.ok().body(response);
    }

    //Get all book
    @GetMapping(value = "/book")
    public ResponseEntity<ResponseDTO> getAllBook(@RequestParam(defaultValue = "0") int page,
                                                  @RequestParam(defaultValue = "3") int size){
        ResponseDTO response = new ResponseDTO();
        try {
            List<Book> listBookEntity = new ArrayList<>();
            Pageable paging = PageRequest.of(page, size);
            Page<Book> pageBook = bookService.getAllBook(paging);

            //retrieve the List of items in the page.
            listBookEntity = pageBook.getContent();

            List<BookDTO> listBookDTO = new ArrayList<>();

            for ( Book bookEntity: listBookEntity ) {
                if(bookEntity.isDelete()==false && bookEntity.getQuantity() > 0 && bookEntity.getCategory().isDelete() == false){
                    BookDTO bookDTO = bookConverter.toDTO(bookEntity);
                    listBookDTO.add(bookDTO);
                }
            }
            HashMap<String, Object> map = new HashMap<>();
            map.put("Books",listBookDTO);
            map.put("currentPage", pageBook.getNumber());//current Page.
            map.put("totalItems", pageBook.getTotalElements());//total items stored in database.
            map.put("totalPages", pageBook.getTotalPages());//number of total pages.

            response.setData(map);
            response.setSuccessCode(SuccessCode.BOOK_GET_SUCCESS);
        }catch (Exception e){
            response.setErrorCode(e.getMessage());
        }
        return ResponseEntity.ok().body(response);
    }

    //Get book by id
    @GetMapping(value = "/get-book-by-id/{id}")
    public ResponseEntity<ResponseDTO> getBookById(@PathVariable(name = "id")long id){
        ResponseDTO response = new ResponseDTO();
        try {
            Book bookEntity = bookService.getBookByID(id).get();
            response.setData(bookConverter.toDTO(bookEntity));
            response.setSuccessCode(SuccessCode.BOOK_FIND_SUCCESS);
        }catch (Exception e){
            throw new NotFoundException(e.getMessage());
        }
        return ResponseEntity.ok().body(response);
    }

    //search book (by name or category id)
    @GetMapping(value = "/search-book")
    public ResponseEntity<ResponseDTO> searchBook(@RequestParam String keyword,
                                                  @RequestParam(defaultValue = "0") int page,
                                                  @RequestParam(defaultValue = "3") int size){//number item of page
        ResponseDTO response = new ResponseDTO();
        try {
            Pageable paging = PageRequest.of(page, size);
            Page<Book> pageBook = bookService.searchBook(keyword, paging);

            //retrieve the List of items in the page.
            List<Book> listBookEntity = pageBook.getContent();
            List<BookDTO> listBookDTO = new ArrayList<>();
            for ( Book bookEntity: listBookEntity ) {
                if(bookEntity.isDelete()==false && bookEntity.getQuantity() > 0 && bookEntity.getCategory().isDelete() == false){
                    BookDTO bookDTO = bookConverter.toDTO(bookEntity);
                    listBookDTO.add(bookDTO);
                }
            }

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

    //get all rating by book id
    @GetMapping(value = "/{bookID}")
    public ResponseEntity<ResponseDTO> getRatingByBookID(@PathVariable(name = "bookID")Long id){
        ResponseDTO response = new ResponseDTO();
        try {
            List<Rating> listRatingEntity = rateService.getRatingByBookID(id);
            if(listRatingEntity.isEmpty()){
                response.setErrorCode(ErrorCode.RATING_FIND_ERROR);
            }else{
                List<RatingDTO> listRatingDTO = listRatingEntity.stream()
                        .map(rateConvert::toDTO)
                        .collect(Collectors.toList());

                response.setData(listRatingDTO);
                response.setSuccessCode(SuccessCode.RATING_FIND_SUCCESS);
            }

        }catch (Exception e){
            throw new NotFoundException(e.getMessage());
        }
        return ResponseEntity.ok().body(response);
    }

    //get all publisher
    @GetMapping(value = "/publisher")
    public ResponseEntity<ResponseDTO> getALlPublisher(){
        ResponseDTO response = new ResponseDTO();
        try {
            List<Publisher> listPublisherEntity = publisherService.getAllPublisher();
            List<PublisherDTO> listPublisherDTO = new ArrayList<>();
            for ( Publisher publisherEntity : listPublisherEntity) {
                if(publisherEntity.isDelete() == false){
                    PublisherDTO publisherDTO = publisherConverter.toDTO(publisherEntity);
                    listPublisherDTO.add(publisherDTO);
                }
            }

            response.setData(listPublisherDTO);
            response.setSuccessCode(SuccessCode.PUBLISHER_GET_SUCCESS);
        }catch(Exception e){
            throw new NotFoundException(e.getMessage());
        }
        return ResponseEntity.ok().body(response);
    }

    //get publisher by id
    @GetMapping(value = "publisher/{id}")
    public ResponseEntity<ResponseDTO> getPublisherByID(@PathVariable(name = "id")Long id){
        ResponseDTO response = new ResponseDTO();
        try {
            Publisher publisherEntity = publisherService.getPublisherByID(id).get();
            response.setData(publisherConverter.toDTO(publisherEntity));
            response.setSuccessCode(SuccessCode.PUBLISHER_GET_SUCCESS);

        }catch (Exception e){
            throw new NotFoundException(e.getMessage());
        }
        return ResponseEntity.ok().body(response);
    }

    //get all author
    @GetMapping(value = "/author")
    public ResponseEntity<ResponseDTO> getALlAuthor(){
        ResponseDTO response = new ResponseDTO();
        try {
            List<Author> listAuthorEntity = authorService.getAllAuthor();
            List<AuthorDTO> listAuthorDTO = new ArrayList<>();
            for ( Author authorEntity : listAuthorEntity) {
                if(authorEntity.isDelete() == false){
                    AuthorDTO authorDTO = authorConverter.toDTO(authorEntity);
                    listAuthorDTO.add(authorDTO);
                }
            }

            response.setData(listAuthorDTO);
            response.setSuccessCode(SuccessCode.AUTHOR_GET_SUCCESS);
        }catch(Exception e){
            throw new NotFoundException(e.getMessage());
        }
        return ResponseEntity.ok().body(response);
    }
    //get author by id
    @GetMapping(value = "author/{id}")
    public ResponseEntity<ResponseDTO> getAuthorByID(@PathVariable(name = "id")Long id){
        ResponseDTO response = new ResponseDTO();
        try {
            Author authorEntity = authorService.getAuthorByID(id).get();
            response.setData(authorConverter.toDTO(authorEntity));
            response.setSuccessCode(SuccessCode.AUTHOR_GET_SUCCESS);

        }catch (Exception e){
            throw new NotFoundException(e.getMessage());
        }
        return ResponseEntity.ok().body(response);
    }


}
