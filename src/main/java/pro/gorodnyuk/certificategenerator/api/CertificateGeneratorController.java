package pro.gorodnyuk.certificategenerator.api;

import lombok.RequiredArgsConstructor;
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

@RestController
@RequiredArgsConstructor
public class CertificateGeneratorController {

    private final CertificateGeneratorService service;

    @GetMapping
    public ResponseEntity<InputStreamResource> generate(@Valid @RequestBody CertificateGeneratorDto request) {
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
