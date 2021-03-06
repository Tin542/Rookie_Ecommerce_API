package com.tinnt.AssigmentRookie.controller;

import com.tinnt.AssigmentRookie.constans.ErrorCode;
import com.tinnt.AssigmentRookie.constans.SuccessCode;
import com.tinnt.AssigmentRookie.converter.*;
import com.tinnt.AssigmentRookie.dto.*;
import com.tinnt.AssigmentRookie.dto.author.AuthorDTO;
import com.tinnt.AssigmentRookie.dto.book.BookDTO;
import com.tinnt.AssigmentRookie.dto.category.CategoryDTO;
import com.tinnt.AssigmentRookie.dto.publisher.PublisherDTO;
import com.tinnt.AssigmentRookie.dto.rate.RatingDTO;
import com.tinnt.AssigmentRookie.entity.*;
import com.tinnt.AssigmentRookie.exception.AddException;
import com.tinnt.AssigmentRookie.exception.DeleteException;
import com.tinnt.AssigmentRookie.exception.NotFoundException;
import com.tinnt.AssigmentRookie.exception.UpdateException;
import com.tinnt.AssigmentRookie.repository.AuthorRepository;
import com.tinnt.AssigmentRookie.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;
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
    private PublisherService publisherService;
    private PublisherConverter publisherConverter;
    private AuthorService authorService;
    private AuthorConverter authorConverter;
    private AuthorRepository authorRepository;

    @Autowired
    public AdminController(CategoryService cateService, CategoryConverter cateConvert, BookService bookService, BookConverter bookConverter, RatingConverter rateConvert, RatingService rateService, AccountService accService, PublisherService publisherService, PublisherConverter publisherConverter, AuthorService authorService, AuthorConverter authorConverter, AuthorRepository authorRepository) {
        this.cateService = cateService;
        this.cateConvert = cateConvert;
        this.bookService = bookService;
        this.bookConverter = bookConverter;
        this.rateConvert = rateConvert;
        this.rateService = rateService;
        this.accService = accService;
        this.publisherService = publisherService;
        this.publisherConverter = publisherConverter;
        this.authorService = authorService;
        this.authorConverter = authorConverter;
        this.authorRepository = authorRepository;
    }

    //Get Category
    @GetMapping(value = "/category")
    public ResponseEntity<ResponseDTO> getCategory(@RequestParam(defaultValue = "") String keyword,
                                                   @RequestParam(defaultValue = "0") int page,
                                                   @RequestParam(defaultValue = "3") int size){
        ResponseDTO response = new ResponseDTO();
        try {
            Pageable paging = PageRequest.of(page, size);
            Page<Category> pageCate = cateService.searchCategory(keyword, paging);

            //retrieve the List of items in the page.
            List<Category> listCateEntity = pageCate.getContent();
            List<CategoryDTO> listCateDTO = new ArrayList<>();
            for ( Category cateEntity: listCateEntity ) {
                CategoryDTO cateDTO = cateConvert.toDTO(cateEntity);
                listCateDTO.add(cateDTO);

            }

            HashMap<String, Object> map = new HashMap<>();
            map.put("Categories",listCateDTO);
            map.put("currentPage", pageCate.getNumber());//current Page.
            map.put("totalItems", pageCate.getTotalElements());//total items stored in database.
            map.put("totalPages", pageCate.getTotalPages());//number of total pages.

            response.setData(map);
            response.setSuccessCode(SuccessCode.BOOK_GET_SUCCESS);

        }catch(Exception e){
            throw new NotFoundException(e.getMessage());
        }
        return ResponseEntity.ok().body(response);
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

    //Delete category
    @PutMapping(value = "/delete-category/{id}")
    public ResponseEntity<ResponseDTO> deleteCategory(@PathVariable(name = "id")long id){
        ResponseDTO response = new ResponseDTO();
        try {
            if(cateService.deleteCategory(id) > 0){
                response.setSuccessCode(SuccessCode.CATEGORY_DELETE_SUCCESS);
            }else{
                response.setErrorCode((ErrorCode.CATEGORY_DELETE_ERROR));
            }
        }catch (Exception e){
            throw new DeleteException(e.getMessage());
        }
        return ResponseEntity.ok().body(response);
    }

    //Add Book
    @PostMapping(value = "/add-book")
    public ResponseEntity<ResponseDTO> addBook(@Valid @RequestBody BookDTO bookDTO){
        ResponseDTO response = new ResponseDTO();
        try {
            Set<String> strAuthor = bookDTO.getAuthorName();
            Set<Author> author = new HashSet<>();
            for (String authorName : strAuthor) {
                Author authorEntity = authorRepository.findByName(authorName)
                        .orElseThrow(() -> new NotFoundException("Cannot found author: "+authorName));
                author.add(authorEntity);
            }

            Book bookEntity = bookConverter.toEntity(bookDTO);
            bookEntity.setAuthor(author);
            bookEntity = bookService.saveBook(bookEntity);

            BookDTO bookDTO1 = bookConverter.toDTO(bookEntity);
            bookDTO1.setAuthorName(strAuthor);
            response.setData(bookDTO1);
            response.setSuccessCode(SuccessCode.BOOK_ADD_SUCCESS);

        }catch(Exception e){
            throw new AddException(e.getMessage());

        }
        return ResponseEntity.ok().body(response);
    }

    //Update Book
    @PutMapping(value = "/update-book/{id}")
    public ResponseEntity<ResponseDTO> updateBook(@Valid @RequestBody BookDTO bookDTO, @PathVariable(name = "id") long id){
        ResponseDTO response = new ResponseDTO();
        try {
            Set<String> strAuthor = bookDTO.getAuthorName();
            Set<Author> author = new HashSet<>();
            for (String authorName : strAuthor) {
                Author authorEntity = authorRepository.findByName(authorName)
                        .orElseThrow(() -> new NotFoundException("Cannot found author: "+authorName));
                author.add(authorEntity);
            }

            Book bookEntity = bookConverter.toEntity(bookDTO);
            bookEntity.setAuthor(author);

            bookEntity = bookService.updateBook(bookEntity, id);
            BookDTO bookDTO1 = bookConverter.toDTO(bookEntity);
            bookDTO1.setAuthorName(strAuthor);
            response.setData(bookDTO1);
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
            if(bookService.deleteBook(id) >0){
                response.setSuccessCode(SuccessCode.BOOK_DELETE_SUCCESS);
            }else{
                response.setErrorCode(ErrorCode.BOOK_DELETE_ERROR);
            }
        }catch (Exception e){
            throw new DeleteException(e.getMessage());
        }
        return ResponseEntity.ok().body(response);
    }

    //search book (by name )
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
                    BookDTO bookDTO = bookConverter.toDTO(bookEntity);
                    listBookDTO.add(bookDTO);

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
    //search book (by name )
    @GetMapping(value = "/search-book-by-cate")
    public ResponseEntity<ResponseDTO> searchBookByCate(@RequestParam long id,
                                                  @RequestParam(defaultValue = "0") int page,
                                                  @RequestParam(defaultValue = "3") int size){//number item of page
        ResponseDTO response = new ResponseDTO();
        try {
            Pageable paging = PageRequest.of(page, size);
            Page<Book> pageBook = bookService.getBookByCategory(id,paging);

            //retrieve the List of items in the page.
            List<Book> listBookEntity = pageBook.getContent();
            List<BookDTO> listBookDTO = new ArrayList<>();
            for ( Book bookEntity: listBookEntity ) {
                BookDTO bookDTO = bookConverter.toDTO(bookEntity);
                listBookDTO.add(bookDTO);

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
        }catch (Exception e){
            response.setErrorCode(e.getMessage());
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
    @PutMapping(value = "/update-rate/{id}")
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
    @DeleteMapping(value = "/delete-rate/{id}")
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

    //get all publisher
    @GetMapping(value = "/publisher")
    public ResponseEntity<ResponseDTO> getALlPublisher(@RequestParam(defaultValue = "") String keyword,
                                                       @RequestParam(defaultValue = "0") int page,
                                                       @RequestParam(defaultValue = "3") int size){
        ResponseDTO response = new ResponseDTO();
        try {
            Pageable paging = PageRequest.of(page, size);
            Page<Publisher> pagePublisher = publisherService.searchPublisher(keyword, paging);

            //retrieve the List of items in the page.
            List<Publisher> listPublisherEntity = pagePublisher.getContent();
            List<PublisherDTO> listPublisherDTO = listPublisherEntity.stream()
                    .map(publisherConverter::toDTO)
                    .collect(Collectors.toList());

            HashMap<String, Object> map = new HashMap<>();
            map.put("publishers",listPublisherDTO);
            map.put("currentPage", pagePublisher.getNumber());//current Page.
            map.put("totalItems", pagePublisher.getTotalElements());//total items stored in database.
            map.put("totalPages", pagePublisher.getTotalPages());//number of total pages.

            response.setData(map);
            response.setSuccessCode(SuccessCode.PUBLISHER_GET_SUCCESS);
        }catch(Exception e){
            throw new NotFoundException(e.getMessage());
        }
        return ResponseEntity.ok().body(response);
    }

    //add publisher
    @PostMapping(value = "/add-publisher")
    public ResponseEntity<ResponseDTO> addPublisher(@Valid @RequestBody PublisherDTO publisherDTO){
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            Publisher publisherEntity = publisherConverter.toEntity(publisherDTO);
            publisherEntity =publisherService.addPublisher(publisherEntity);
            responseDTO.setData(publisherConverter.toDTO(publisherEntity));
            responseDTO.setSuccessCode(SuccessCode.PUBLISHER_ADD_SUCCESS);
        } catch (Exception e) {
            throw new AddException(e.getMessage());
        }
        return ResponseEntity.ok().body(responseDTO);
    }

    //Update publisher
    @PutMapping(value = "/update-publisher/{id}")
    public ResponseEntity<ResponseDTO> updatePublisher(@Valid @RequestBody PublisherDTO publisherDTO,
                                                       @PathVariable(name = "id")Long id) {
        ResponseDTO respone = new ResponseDTO();
        try {
            Publisher publisherEntity = publisherService.updatePublisher(publisherConverter.toEntity(publisherDTO), id);
            respone.setData(publisherConverter.toDTO(publisherEntity));
            respone.setSuccessCode(SuccessCode.PUBLISHER_UPDATE_SUCCESS);

        } catch (Exception e) {
            throw new UpdateException(e.getMessage());
        }
        return ResponseEntity.ok().body(respone);
    }

    //Delete publisher
    @PutMapping(value = "/delete-publisher/{id}")
    public ResponseEntity<ResponseDTO> deletePublisher(@PathVariable(name = "id")long id){
        ResponseDTO response = new ResponseDTO();
        try {
            if(publisherService.deletePublisher(id) > 0){
                response.setSuccessCode(SuccessCode.PUBLISHER_DELETE_SUCCESS);
            }else{
                response.setErrorCode(ErrorCode.PUBLISHER_DELETE_ERROR);
            }
        }catch (Exception e){
            throw new DeleteException(e.getMessage());
        }
        return ResponseEntity.ok().body(response);
    }

    //get all author
    @GetMapping(value = "/author")
    public ResponseEntity<ResponseDTO> getALlAuthor(@RequestParam(defaultValue = "") String keyword,
                                                       @RequestParam(defaultValue = "0") int page,
                                                       @RequestParam(defaultValue = "3") int size){
        ResponseDTO response = new ResponseDTO();
        try {
            Pageable paging = PageRequest.of(page, size);
            Page<Author> pageAuthor = authorService.searchAuthor(keyword, paging);

            //retrieve the List of items in the page.
            List<Author> listAuthorEntity = pageAuthor.getContent();
            List<AuthorDTO> listAuthorDTO = listAuthorEntity.stream()
                    .map(authorConverter::toDTO)
                    .collect(Collectors.toList());

            HashMap<String, Object> map = new HashMap<>();
            map.put("authors",listAuthorDTO);
            map.put("currentPage", pageAuthor.getNumber());//current Page.
            map.put("totalItems", pageAuthor.getTotalElements());//total items stored in database.
            map.put("totalPages", pageAuthor.getTotalPages());//number of total pages.

            response.setData(map);
            response.setSuccessCode(SuccessCode.AUTHOR_GET_SUCCESS);
        }catch(Exception e){
            throw new NotFoundException(e.getMessage());
        }
        return ResponseEntity.ok().body(response);
    }

    //add author
    @PostMapping(value = "/add-author")
    public ResponseEntity<ResponseDTO> addPublisher(@Valid @RequestBody AuthorDTO authorDTO){
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            Author authorEntity = authorConverter.toEntity(authorDTO);
            authorEntity =authorService.addAuthor(authorEntity);
            responseDTO.setData(authorConverter.toDTO(authorEntity));
            responseDTO.setSuccessCode(SuccessCode.AUTHOR_ADD_SUCCESS);
        } catch (Exception e) {
            throw new AddException(e.getMessage());
        }
        return ResponseEntity.ok().body(responseDTO);
    }

    //Update publisher
    @PutMapping(value = "/update-author/{id}")
    public ResponseEntity<ResponseDTO> updateAuthor(@Valid @RequestBody AuthorDTO authorDTO,
                                                       @PathVariable(name = "id")Long id) {
        ResponseDTO respone = new ResponseDTO();
        try {
            Author authorEntity = authorService.updateAuthor(authorConverter.toEntity(authorDTO), id);
            respone.setData(authorConverter.toDTO(authorEntity));
            respone.setSuccessCode(SuccessCode.AUTHOR_UPDATE_SUCCESS);

        } catch (Exception e) {
            throw new UpdateException(e.getMessage());
        }
        return ResponseEntity.ok().body(respone);
    }

    //Delete author
    @PutMapping(value = "/delete-author/{id}")
    public ResponseEntity<ResponseDTO> deleteAuthor(@PathVariable(name = "id")long id){
        ResponseDTO response = new ResponseDTO();
        try {
            if(authorService.deleteAuthor(id) > 0){
                response.setSuccessCode(SuccessCode.AUTHOR_DELETE_SUCCESS);
            }else{
                response.setErrorCode(ErrorCode.AUTHOR_DELETE_ERROR);
            }
        }catch (Exception e){
            throw new DeleteException(e.getMessage());
        }
        return ResponseEntity.ok().body(response);
    }
}
