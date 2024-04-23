package com.devxminds.donpipe.service;

import com.devxminds.donpipe.dto.LogDto;
import com.devxminds.donpipe.entidade.Log;
import com.devxminds.donpipe.repositorios.LogRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class LogService {
    @Autowired
    private LogRepository logRepository;
    @Autowired
    private ModelMapper modelMapper;

    public LogDto saveLog(LogDto logDto) {
        Log log = modelMapper.map(logDto, Log.class);
        log = logRepository.save(log);
        return modelMapper.map(log, LogDto.class);
    }

    public LogDto getLog(Long id) {
        Optional<Log> log = logRepository.findById(id);
        if (log.isPresent()) {
            return modelMapper.map(log.get(), LogDto.class);
        } else {
            throw new NoSuchElementException("Log no encontrado.");
        }
    }

}
