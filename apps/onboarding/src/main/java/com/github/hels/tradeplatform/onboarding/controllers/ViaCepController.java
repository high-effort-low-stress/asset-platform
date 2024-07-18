package com.github.hels.tradeplatform.onboarding.controllers;

import com.github.hels.tradeplatform.onboarding.infrastructure.ViaCep;
import com.github.hels.tradeplatform.onboarding.service.ViaCepService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/address")
@RequiredArgsConstructor
@Tag(name = "viaCep")
public class ViaCepController {

    private final ViaCepService service;

    @Operation(description = "busca endereço utilizando API viaCep", method = "GET")
    @Parameter(name = "zipCode", in = ParameterIn.PATH, description = "cep a ser consultado", required = true)
    @GetMapping("/{zipCode}")
    public ResponseEntity<ViaCep> test(@PathVariable(value = "zipCode") String zipCode) {
        return ResponseEntity.ok(service.execute(zipCode));
    }
}
