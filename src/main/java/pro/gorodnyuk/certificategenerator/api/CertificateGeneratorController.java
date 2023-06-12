package pro.gorodnyuk.certificategenerator.api;

import lombok.RequiredArgsConstructor;
import net.sf.jasperreports.engine.JRException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pro.gorodnyuk.certificategenerator.service.CertificateGeneratorService;

import javax.validation.Valid;
import java.io.FileNotFoundException;

@RestController
@RequestMapping("/api/v1/certificate")
@RequiredArgsConstructor
public class CertificateGeneratorController {

    private final CertificateGeneratorService service;

    @PostMapping("/generate")
    public ResponseEntity<byte[]> generate(@Valid @RequestBody CertificateGeneratorDto request)
            throws JRException, FileNotFoundException {
        return ResponseEntity.ok(service.generate(request));
    }
}
