package com.levameulivro.controllers;

import com.levameulivro.dto.ExchangeRequestDTO;
import com.levameulivro.dto.ExchangeResponseDTO;
import com.levameulivro.models.Exchange;
import com.levameulivro.services.impl.ExchangeServiceImp;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping(value = "/exchanges")
public class ExchangeController {

    @Autowired
    private ExchangeServiceImp exchangeServiceImp;

    @GetMapping
    public List<ExchangeResponseDTO> getAllExchanges(){
        List<Exchange> exchanges = exchangeServiceImp.findAllExchange();
        return exchanges.stream().map(ExchangeResponseDTO::new).collect(Collectors.toList());
    }

    @GetMapping("/{exchangeId}")
    public ResponseEntity<ExchangeResponseDTO> getExchangeById(@PathVariable Long exchangeId){
        Optional<Exchange> optional = exchangeServiceImp.findExchangeById(exchangeId);
        if(optional.isPresent()){
            return ResponseEntity.ok(new ExchangeResponseDTO(optional.get()));
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<ExchangeResponseDTO> addExchange(@RequestBody @Valid ExchangeRequestDTO exchangeDTO, UriComponentsBuilder uriBuilder){
        Exchange exchange = exchangeServiceImp.createExchange(exchangeDTO);

        URI uri = uriBuilder.path("/books/{bookId}").buildAndExpand(exchange.getId()).toUri();
        return ResponseEntity.created(uri).body(new ExchangeResponseDTO(exchange));
    }

    @PutMapping("/{exchangeId}")
    @Transactional
    public ResponseEntity<ExchangeResponseDTO> updateExchange(@PathVariable Long exchangeId, @RequestBody @Valid ExchangeRequestDTO exchangeDTO){
        Optional<Exchange> optional = exchangeServiceImp.findExchangeById(exchangeId);
        if(optional.isPresent()){
            Exchange exchange = exchangeServiceImp.updateExchange(exchangeId, exchangeDTO);
            return ResponseEntity.ok(new ExchangeResponseDTO(exchange));
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{exchangeId}")
    public ResponseEntity<ExchangeResponseDTO> destroyExchangeById(@PathVariable Long exchangeId){
        Optional<Exchange> optional = exchangeServiceImp.findExchangeById(exchangeId);
        if(optional.isPresent()){
            exchangeServiceImp.deleteExchangeById(exchangeId);
            return ResponseEntity.ok().build();
        }
        
        return ResponseEntity.notFound().build();
    }
}
