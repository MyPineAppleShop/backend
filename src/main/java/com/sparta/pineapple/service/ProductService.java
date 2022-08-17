package com.sparta.pineapple.service;

import com.sparta.pineapple.dto.request.ProductRequestDto;
import com.sparta.pineapple.dto.response.ProductResponseDto;
import com.sparta.pineapple.dto.response.ResponseDto;
import com.sparta.pineapple.jwt.TokenProvider;
import com.sparta.pineapple.model.Product;
import com.sparta.pineapple.model.Member;
import com.sparta.pineapple.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final TokenProvider tokenProvider;

    @Transactional
    public ResponseDto<?> createProduct(ProductRequestDto requestDto,
                                       HttpServletRequest request) {

        if (null == request.getHeader("Refresh-Token")) {
            return ResponseDto.fail("MEMBER_NOT_FOUND", "로그인이 필요합니다.");
        }

        if (null == request.getHeader("Authorization")) {
            return ResponseDto.fail("MEMBER_NOT_FOUND", "로그인이 필요합니다.");
        }

        Member member = validateMember(request);
        if (null == member) {
            return ResponseDto.fail("INVALID_TOKEN", "Token이 유효하지 않습니다.");
        }

        Product product = Product.builder()
                .distinction(requestDto.getDistinction())
                .productName(requestDto.getProductName())
                .cost(requestDto.getCost())
                .image(requestDto.getImage())
                .count(requestDto.getCount())
                .build();

        productRepository.save(product);

        return ResponseDto.success(
                ProductResponseDto.builder()
                        .id(product.getId())
                        .distinction(product.getDistinction())
                        .productName(product.getProductName())
                        .cost(product.getCost())
                        .image(product.getImage())
                        .count(product.getCount())
                        .createdAt(product.getCreatedAt())
                        .modifiedAt(product.getModifiedAt())
                        .build()
        );

    }

    @Transactional(readOnly = true)
    public ResponseDto<?> getProduct() {

        List<Product> productList = productRepository.findAll();
        List<ProductResponseDto> productResponseDtoList = new ArrayList<>();

        for (Product product : productList) {
            productResponseDtoList.add(
                    ProductResponseDto.builder()
                            .id(product.getId())
                            .distinction(product.getDistinction())
                            .productName(product.getProductName())
                            .cost(product.getCost())
                            .image(product.getImage())
                            .count(product.getCount())
                            .createdAt(product.getCreatedAt())
                            .modifiedAt(product.getModifiedAt())
                            .build()
            );
        }

        return ResponseDto.success(productResponseDtoList);

    }

    @Transactional
    public ResponseDto<?> updateProduct(Long id, ProductRequestDto requestDto, HttpServletRequest request) {
        if (null == request.getHeader("Refresh-Token")) {
            return ResponseDto.fail("MEMBER_NOT_FOUND", "로그인이 필요합니다.");
        }

        if (null == request.getHeader("Authorization")) {
            return ResponseDto.fail("MEMBER_NOT_FOUND", "로그인이 필요합니다.");
        }

        Member member = validateMember(request);
        if (null == member) {
            return ResponseDto.fail("INVALID_TOKEN", "Token이 유효하지 않습니다.");
        }

        Product product = getPresentProduct(id);
        if (null == product) {
            return ResponseDto.fail("NOT_FOUND", "존재하지 않는 상품입니다.");
        }

        product.update(requestDto);
        return ResponseDto.success(
                ProductResponseDto.builder()
                        .id(product.getId())
                        .distinction(product.getDistinction())
                        .productName(product.getProductName())
                        .cost(product.getCost())
                        .image(product.getImage())
                        .count(product.getCount())
                        .createdAt(product.getCreatedAt())
                        .modifiedAt(product.getModifiedAt())
                        .build()
        );
    }

    @Transactional
    public ResponseDto<?> deleteProduct(Long id, HttpServletRequest request) {
        if (null == request.getHeader("Refresh-Token")) {
            return ResponseDto.fail("MEMBER_NOT_FOUND", "로그인이 필요합니다.");
        }

        if (null == request.getHeader("Authorization")) {
            return ResponseDto.fail("MEMBER_NOT_FOUND", "로그인이 필요합니다.");
        }

        Member member = validateMember(request);
        if (null == member) {
            return ResponseDto.fail("INVALID_TOKEN", "Token이 유효하지 않습니다.");
        }

        Product product = getPresentProduct(id);
        if (null == product) {
            return ResponseDto.fail("NOT_FOUND", "존재하지 않는 상품입니다.");
        }

        productRepository.delete(product);
        return ResponseDto.success(
                ProductResponseDto.builder()
                        .id(product.getId())
                        .distinction(product.getDistinction())
                        .productName(product.getProductName())
                        .cost(product.getCost())
                        .image(product.getImage())
                        .count(product.getCount())
                        .createdAt(product.getCreatedAt())
                        .modifiedAt(product.getModifiedAt())
                        .build()
        );
    }

    @Transactional(readOnly = true)
    public Product getPresentProduct(Long id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        return optionalProduct.orElse(null);
    }

    @Transactional
    public Member validateMember(HttpServletRequest request) {
        if (!tokenProvider.validateToken(request.getHeader("Refresh-Token"))) {
            return null;
        }
        return tokenProvider.getMemberFromAuthentication();
    }

}