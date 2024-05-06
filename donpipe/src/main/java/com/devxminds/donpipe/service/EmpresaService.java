package com.devxminds.donpipe.service;

import com.devxminds.donpipe.dto.EmpresaDto;
import com.devxminds.donpipe.entidade.Empresa;
import com.devxminds.donpipe.repositorios.EmpresaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmpresaService {
    @Autowired
    private EmpresaRepository empresaRepository;
    @Autowired
    private ModelMapper modelMapper;

    public EmpresaDto criarEmpresa(EmpresaDto empresaDto) {
        Empresa empresa = modelMapper.map(empresaDto, Empresa.class);
        empresa = empresaRepository.save(empresa);

        return modelMapper.map(empresa, EmpresaDto.class);
    }

    public EmpresaDto findById(Long id) {
        Optional<Empresa> empresaOptional = empresaRepository.findById(id);
        if (empresaOptional.isPresent()) {
            return modelMapper.map(empresaOptional.get(), EmpresaDto.class);
        } else {
            throw new NoSuchElementException("Empresa não encontrada com o id: " + id);
        }
    }

    public List<EmpresaDto> getAll() {
        List<Empresa> empresas = empresaRepository.findAll();
        return empresas.stream()
                .map(empresa -> modelMapper.map(empresa, EmpresaDto.class))
                .collect(Collectors.toList());
    }
}
