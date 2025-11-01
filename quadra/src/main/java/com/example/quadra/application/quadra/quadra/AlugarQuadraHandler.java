package com.example.quadra.application.quadra.quadra;

import org.springframework.stereotype.Service;

import com.example.quadra.domain.quadra.Quadra;
import com.example.quadra.domain.quadra.QuadraRepository;
import com.example.quadra.domain.quadra.vo.CategoryType;
import com.example.quadra.domain.quadra.vo.Price;
import com.example.quadra.interfaces.rest.dto.quadra.QuadraResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AlugarQuadraHandler {
    private final QuadraRepository quadraRepository;

    public QuadraResponse handle(String name, boolean alugar) {
        Quadra quadra = quadraRepository.findByName(name)
            .orElseThrow(() -> new RuntimeException("Quadra não encontrada"));

        if (quadra.isAlugado()) {
            throw new RuntimeException("Quadra já está alugada");
        }

        quadra.setAlugado(alugar);
        Quadra savedQuadra = quadraRepository.save(quadra);

        return new QuadraResponse(
                savedQuadra.getId(),
                savedQuadra.getName(),
                savedQuadra.getLocal(),
                savedQuadra.isAlugado(),
                savedQuadra.getPrice().getValue(),
                savedQuadra.getCategory().getValue()
        );
    }
}
