package com.devxminds.donpipe.resource;

import com.devxminds.donpipe.service.BronzeService;
import com.devxminds.donpipe.service.LogService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bronze")
public class BronzeController {
    @Autowired
    private BronzeService bronzeService;
    @Autowired
    private LogService logService;
    @Autowired
    private ModelMapper modelMapper;
}
