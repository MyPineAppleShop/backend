package com.sparta.pineapple.service;

import com.sparta.pineapple.dto.request.BasketRequestDto;
import com.sparta.pineapple.dto.response.BasketResponseDto;
import com.sparta.pineapple.dto.response.GetBasketResponseDto;
import com.sparta.pineapple.dto.response.ResponseDto;
import com.sparta.pineapple.jwt.TokenProvider;
import com.sparta.pineapple.model.Basket;
import com.sparta.pineapple.model.Member;
import com.sparta.pineapple.model.Product;
import com.sparta.pineapple.repository.BasketRepository;
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
public class BasketService {

    private final BasketRepository basketRepository;
    private final ProductRepository productRepository;
    private final TokenProvider tokenProvider;

    @Transactional
    public ResponseDto<?> createBasket(Long id,
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

        Product product = getPresentProduct(id);
        if (null == product) {
            return ResponseDto.fail("NOT_FOUND", "존재하지 않는 상품입니다.");
        }

        Basket basket = getDuplicationCheck(product);
        if (null == basket) {
            basket = Basket.builder()
                    .member(member)
                    .product(product)
                    .count(1)
                    .build();

            basketRepository.save(basket);

            return ResponseDto.success(
                    BasketResponseDto.builder()
                            .id(basket.getId())
                            .productId(product.getId())
                            .distinction(product.getDistinction())
                            .productName(product.getProductName())
                            .cost(product.getCost())
                            .image(product.getImage())
                            .count(basket.getCount())
                            .totalCost((long) product.getCost() * product.getCount())
                            .createdAt(basket.getCreatedAt())
                            .modifiedAt(basket.getModifiedAt())
                            .build()
            );
        }

        return ResponseDto.fail("DUPLICATED_PRODUCT", "이미 장바구니에 있습니다.");

    }

    @Transactional(readOnly = true)
    public GetBasketResponseDto<?> getBasket(HttpServletRequest request) {

        if (null == request.getHeader("Refresh-Token")) {
            return GetBasketResponseDto.fail("MEMBER_NOT_FOUND", "로그인이 필요합니다.");
        }

        if (null == request.getHeader("Authorization")) {
            return GetBasketResponseDto.fail("MEMBER_NOT_FOUND", "로그인이 필요합니다.");
        }

        Member member = validateMember(request);
        if (null == member) {
            return GetBasketResponseDto.fail("INVALID_TOKEN", "Token이 유효하지 않습니다.");
        }

        List<Basket> basketList = basketRepository.findByMember(member);
        List<BasketResponseDto> basketResponseDtoList = new ArrayList<>();

        long basketTotalCost = 0;

        for (Basket basket : basketList) {
            basketTotalCost += (long) basket.getProduct().getCost() * basket.getCount();
        }

        for (Basket basket : basketList) {
            basketResponseDtoList.add(
                    BasketResponseDto.builder()
                            .id(basket.getId())
                            .productId(basket.getProduct().getId())
                            .distinction(basket.getProduct().getDistinction())
                            .productName(basket.getProduct().getProductName())
                            .cost(basket.getProduct().getCost())
                            .image(basket.getProduct().getImage())
                            .count(basket.getCount())
                            .totalCost((long) basket.getProduct().getCost() * basket.getCount())
                            .basketTotalCost(basketTotalCost)
                            .createdAt(basket.getCreatedAt())
                            .modifiedAt(basket.getModifiedAt())
                            .build()
            );
        }

        return GetBasketResponseDto.success(basketResponseDtoList, basketTotalCost);

    }

    @Transactional
    public ResponseDto<?> updateBasket(Long id, BasketRequestDto requestDto, HttpServletRequest request) {
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

        Basket basket = getPresentBasket(id);
        if (null == basket) {
            return ResponseDto.fail("NOT_FOUND", "존재하지 않는 상품입니다.");
        }

        basket.update(requestDto);

        long basketTotalCost = 0;

        List<Basket> basketList = basketRepository.findByMember(member);
        for (Basket basket2 : basketList) {
            basketTotalCost += (long) basket2.getProduct().getCost() * basket2.getCount();
        }


        return ResponseDto.success(
                BasketResponseDto.builder()
                        .id(basket.getId())
                        .productId(basket.getProduct().getId())
                        .distinction(basket.getProduct().getDistinction())
                        .productName(basket.getProduct().getProductName())
                        .cost(basket.getProduct().getCost())
                        .image(basket.getProduct().getImage())
                        .count(basket.getCount())
                        .totalCost((long) basket.getProduct().getCost() * basket.getCount())
                        .basketTotalCost(basketTotalCost)
                        .createdAt(basket.getCreatedAt())
                        .modifiedAt(basket.getModifiedAt())
                        .build()
        );
    }

    @Transactional
    public ResponseDto<?> deleteBasket(Long id, HttpServletRequest request) {
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

        Basket basket = getPresentBasket(id);
        if (null == basket) {
            return ResponseDto.fail("NOT_FOUND", "존재하지 않는 상품입니다.");
        }

        basketRepository.delete(basket);
        return ResponseDto.success(
                BasketResponseDto.builder()
                        .id(basket.getId())
                        .productId(basket.getProduct().getId())
                        .distinction(basket.getProduct().getDistinction())
                        .productName(basket.getProduct().getProductName())
                        .cost(basket.getProduct().getCost())
                        .image(basket.getProduct().getImage())
                        .count(basket.getCount())
                        .totalCost((long) basket.getProduct().getCost() * basket.getCount())
                        .createdAt(basket.getCreatedAt())
                        .modifiedAt(basket.getModifiedAt())
                        .build()
        );
    }

    @Transactional(readOnly = true)
    public Basket getPresentBasket(Long id) {
        Optional<Basket> optionalBasket = basketRepository.findById(id);
        return optionalBasket.orElse(null);
    }

    @Transactional(readOnly = true)
    public Basket getDuplicationCheck(Product product) {
        Optional<Basket> optionalBasket = basketRepository.findByProduct(product);
        return optionalBasket.orElse(null);
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