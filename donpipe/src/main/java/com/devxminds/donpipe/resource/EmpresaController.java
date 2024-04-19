package com.devxminds.donpipe.resource;

import com.devxminds.donpipe.dto.EmpresaDto;
import com.devxminds.donpipe.entidade.Empresa;
import com.devxminds.donpipe.service.EmpresaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/empresa")
public class EmpresaController {
    @Autowired
    private EmpresaService empresaService;

    @GetMapping("/{id}")
    public ResponseEntity<EmpresaDto> findById(@PathVariable Long id) {
        EmpresaDto empresaDto = empresaService.findById(id);
        if (empresaDto != null) {
            return ResponseEntity.ok(empresaDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
