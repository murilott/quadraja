package com.example.quadra.application.quadra.quadra;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.quadra.domain.quadra.Quadra;
import com.example.quadra.domain.quadra.QuadraRepository;
import com.example.quadra.interfaces.rest.dto.quadra.QuadraResponse;

@Service
public class ListQuadrasHandler {
    private final QuadraRepository quadraRepository;

    public ListQuadrasHandler(QuadraRepository quadraRepository) {
        this.quadraRepository = quadraRepository;
    }

    public Page<QuadraResponse> handle(Pageable pageable) {
        Page<Quadra> page = quadraRepository.findAll(pageable);

        return page.map(quadra -> new QuadraResponse(
                quadra.getId(),
                quadra.getName(),
                quadra.getLocal(),
                quadra.isAlugado(),
                quadra.getPrice().getValue(),
                quadra.getCategory().getValue()
        ));
    }
}
