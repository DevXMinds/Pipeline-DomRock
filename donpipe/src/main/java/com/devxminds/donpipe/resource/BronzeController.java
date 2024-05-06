package com.devxminds.donpipe.resource;

import com.devxminds.donpipe.dto.BronzeDto;
import com.devxminds.donpipe.dto.LogDto;
import com.devxminds.donpipe.entidade.Bronze;
import com.devxminds.donpipe.service.ArquivoService;
import com.devxminds.donpipe.service.BronzeService;
import com.devxminds.donpipe.service.LogService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/bronze")
public class BronzeController {
    @Autowired
    private BronzeService bronzeService;
    @Autowired
    private LogService logService;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ArquivoService arquivoService;

    @PostMapping("/load")
    public ResponseEntity<Bronze> load(@RequestBody BronzeDto bronzeDto) {
        Bronze bronzeCriado = bronzeService.store(bronzeDto);
        logService.saveLog(new LogDto(null,bronzeCriado.getIdUser(), null, bronzeService.getMostRecentBronze().get().getIdArquivo()));
        return ResponseEntity.status(HttpStatusCode.valueOf(201)).body(bronzeCriado);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BronzeDto> findById(@PathVariable Long id) {
        BronzeDto bronzeDto =bronzeService.findById(id);
        logService.saveLog(new LogDto(null,bronzeDto.getIdArquivo().getIdUser(), null, modelMapper.map(bronzeDto, Bronze.class).getIdArquivo()));
        if (bronzeDto != null) {
            return ResponseEntity.ok(bronzeDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/latestBronze")
    public ResponseEntity<Bronze> findLatestBronze() {
        Optional<Bronze> bronze = bronzeService.getMostRecentBronze();
        if (bronze.isPresent()) {
            logService.saveLog(new LogDto(null,bronze.get().getIdUser(),null,modelMapper.map(bronze, Bronze.class).getIdArquivo()));
            return ResponseEntity.ok(bronze.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
