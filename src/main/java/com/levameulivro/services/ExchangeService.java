package com.levameulivro.services;

import java.util.List;
import java.util.Optional;

import com.levameulivro.controllers.dto.ExchangeRequestDTO;
import com.levameulivro.models.Exchange;

public interface ExchangeService {
    List<Exchange> findAllExchange();
    Optional<Exchange> findExchangeById(Long exchangeId);
    Exchange createExchange(ExchangeRequestDTO exchangeDTO);
    Exchange updateExchange(Long exchangeId, ExchangeRequestDTO exchangeDTO);
    void deleteExchangeById(Long exchangeId);
}
