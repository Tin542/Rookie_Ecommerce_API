package com.tinnt.AssigmentRookie.controller;

import com.tinnt.AssigmentRookie.constans.ErrorCode;
import com.tinnt.AssigmentRookie.constans.SuccessCode;
import com.tinnt.AssigmentRookie.converter.RatingConverter;
import com.tinnt.AssigmentRookie.dto.RatingDTO;
import com.tinnt.AssigmentRookie.dto.ResponseDTO;
import com.tinnt.AssigmentRookie.entity.Account;
import com.tinnt.AssigmentRookie.entity.Rating;
import com.tinnt.AssigmentRookie.exception.AddException;
import com.tinnt.AssigmentRookie.exception.DeleteException;
import com.tinnt.AssigmentRookie.exception.NotFoundException;
import com.tinnt.AssigmentRookie.exception.UpdateException;
import com.tinnt.AssigmentRookie.service.AccountService;
import com.tinnt.AssigmentRookie.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/BookStore/rate")
public class RatingController {

    @Autowired
    private RatingConverter rateConvert;

    @Autowired
    private RatingService rateService;

    @Autowired
    private AccountService accService;

    @PostMapping
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
                response.setData(rateConvert.toDTO(rateEntity));
                response.setSuccessCode(SuccessCode.RATING_ADD_SUCCESS);
            }

        }catch (Exception e){
            throw new AddException(e.getMessage());
        }

        return ResponseEntity.ok().body(response);
    }

    @PutMapping(value = "/{id}")
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

    @DeleteMapping(value = "/{id}")
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

    @GetMapping(value = "/{bookID}")
    public ResponseEntity<ResponseDTO> getRatingByBookID(@PathVariable(name = "bookID")Long id){
        ResponseDTO response = new ResponseDTO();
        try {
            List<Rating> listRatingEntity = rateService.getRatingByBookID(id);
            if(listRatingEntity.isEmpty()){
                response.setErrorCode(ErrorCode.RATING_FIND_ERROR);
            }else{
                List<RatingDTO> listRatingDTO = new ArrayList<>();
                for ( Rating rateEntity : listRatingEntity ) {
                    RatingDTO rateDTO = rateConvert.toDTO(rateEntity);
                    listRatingDTO.add(rateDTO);
                }
                response.setData(listRatingDTO);
                response.setSuccessCode(SuccessCode.RATING_FIND_SUCCESS);
            }

        }catch (Exception e){
            throw new NotFoundException(e.getMessage());
        }
        return ResponseEntity.ok().body(response);
    }
}
