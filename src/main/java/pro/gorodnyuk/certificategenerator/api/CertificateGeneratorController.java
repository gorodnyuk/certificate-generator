package pro.gorodnyuk.certificategenerator.api;

import lombok.RequiredArgsConstructor;
import net.sf.jasperreports.engine.JRException;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pro.gorodnyuk.certificategenerator.service.CertificateGeneratorService;

import javax.validation.Valid;
import java.io.FileNotFoundException;

@RestController
@RequiredArgsConstructor
public class CertificateGeneratorController {

    private final CertificateGeneratorService service;

    @GetMapping
    public ResponseEntity<InputStreamResource> generate(@Valid @RequestBody CertificateGeneratorDto request)
            throws JRException, FileNotFoundException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentDisposition(ContentDisposition.builder("attachment")
                .filename("booking-certificate.pdf")
                .build());
        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(service.generate(request));
    }
}
