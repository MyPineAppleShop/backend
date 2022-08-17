package com.sparta.pineapple.controller;

import com.sparta.pineapple.dto.request.BasketRequestDto;
import com.sparta.pineapple.dto.response.GetBasketResponseDto;
import com.sparta.pineapple.dto.response.ResponseDto;
import com.sparta.pineapple.service.BasketService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@RestController
public class BasketController {

    private final BasketService basketService;

    @RequestMapping(value = "/basket/{id}", method = RequestMethod.POST)
    public ResponseDto<?> createBasket(@PathVariable Long id,
                                       HttpServletRequest request) {
        return basketService.createBasket(id, request);
    }

    @RequestMapping(value = "/basket", method = RequestMethod.GET)
    public GetBasketResponseDto<?> getBasket(HttpServletRequest request) {
        return basketService.getBasket(request);
    }

    @RequestMapping(value = "/basket/{id}", method = RequestMethod.PATCH)
    public ResponseDto<?> updateBasket(@PathVariable Long id,
                                       @RequestBody BasketRequestDto basketRequestDto,
                                       HttpServletRequest request) {
        return basketService.updateBasket(id, basketRequestDto, request);
    }

    @RequestMapping(value = "/basket/{id}", method = RequestMethod.DELETE)
    public ResponseDto<?> deleteBasket(@PathVariable Long id,
                                       HttpServletRequest request) {
        return basketService.deleteBasket(id, request);
    }

}