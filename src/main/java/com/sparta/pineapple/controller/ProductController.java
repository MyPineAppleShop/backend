package com.sparta.pineapple.controller;

import com.sparta.pineapple.dto.request.ProductRequestDto;
import com.sparta.pineapple.dto.response.ResponseDto;
import com.sparta.pineapple.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@RestController
public class ProductController {

    private final ProductService productService;

    @RequestMapping(value = "/product", method = RequestMethod.POST)
    public ResponseDto<?> createProduct(@RequestBody ProductRequestDto requestDto,
                                        HttpServletRequest request) {
        return productService.createProduct(requestDto, request);
    }

    @RequestMapping(value = "/product", method = RequestMethod.GET)
    public ResponseDto<?> getProducts() {
        return productService.getProduct();
    }

    @RequestMapping(value = "/product/{product_id}", method = RequestMethod.PATCH)
    public ResponseDto<?> updateProduct(@PathVariable Long product_id,
                                        @RequestBody ProductRequestDto productRequestDto,
                                        HttpServletRequest request) {
        return productService.updateProduct(product_id, productRequestDto, request);
    }

    @RequestMapping(value = "/product/{product_id}", method = RequestMethod.DELETE)
    public ResponseDto<?> deleteProduct(@PathVariable Long product_id,
                                        HttpServletRequest request) {
        return productService.deleteProduct(product_id, request);
    }

}
