package com.sparta.pineapple.controller;

import com.sparta.pineapple.dto.request.BasketRequestDto;
import com.sparta.pineapple.dto.response.ResponseDto;
import com.sparta.pineapple.service.BasketService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@RestController
public class BasketController {

    private final BasketService basketService;

    @RequestMapping(value = "/basket", method = RequestMethod.POST)
    public ResponseDto<?> createBasket(@RequestBody BasketRequestDto requestDto,
                                        HttpServletRequest request) {
        return basketService.createBasket(requestDto, request);
    }

    @RequestMapping(value = "/basket", method = RequestMethod.GET)
    public ResponseDto<?> getBaskets() {
        return basketService.getBasket();
    }

    @RequestMapping(value = "/basket/{product_id}", method = RequestMethod.PATCH)
    public ResponseDto<?> updateBasket(@PathVariable Long product_id,
                                        @RequestBody BasketRequestDto basketRequestDto,
                                        HttpServletRequest request) {
        return basketService.updateBasket(product_id, basketRequestDto, request);
    }

    @RequestMapping(value = "/basket/{product_id}", method = RequestMethod.DELETE)
    public ResponseDto<?> deleteBasket(@PathVariable Long product_id,
                                        HttpServletRequest request) {
        return basketService.deleteBasket(product_id, request);
    }

}
