package com.example.quadra.application.quadra.quadra;

import lombok.RequiredArgsConstructor;


import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.quadra.domain.quadra.Quadra;
import com.example.quadra.domain.quadra.QuadraRepository;
import com.example.quadra.domain.quadra.vo.Category;
import com.example.quadra.domain.quadra.vo.CategoryType;
import com.example.quadra.domain.quadra.vo.Price;
import com.example.quadra.interfaces.rest.dto.quadra.QuadraResponse;

@Service
@RequiredArgsConstructor
public class RegisterQuadraHandler {
    private final QuadraRepository quadraRepository;

    public QuadraResponse handle(String name, String local, double priceRaw, CategoryType category) {
        Price price = Price.of(priceRaw);

        // if (quadraRepository.existsByEmail(email.getValue())) {
        //     throw new ResponseStatusException(HttpStatus.CONFLICT, "Email já cadastrado");
        // }

        Quadra quadra = new Quadra(name, local, price, category);
        Quadra savedQuadra = quadraRepository.save(quadra);

        return new QuadraResponse(
                savedQuadra.getId(),
                savedQuadra.getName(),
                savedQuadra.getLocal(),
                savedQuadra.getPrice().getValue(),
                savedQuadra.getCategory().getValue()
        );
    }
}
