package pro.gorodnyuk.certificategenerator.api;

import lombok.RequiredArgsConstructor;
import net.sf.jasperreports.engine.JRException;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pro.gorodnyuk.certificategenerator.service.CertificateGeneratorService;
import pro.gorodnyuk.certificategenerator.service.TransliterationService;

import javax.validation.Valid;
import java.io.FileNotFoundException;

@RestController
@RequestMapping("/api/v1/certificate")
@RequiredArgsConstructor
public class CertificateGeneratorController {

    private final CertificateGeneratorService service;
    private final TransliterationService transliterationService;

    @PostMapping("/generate")
    public ResponseEntity<byte[]> generate(@Valid @RequestBody CertificateGeneratorDto request)
            throws JRException, FileNotFoundException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentDisposition(ContentDisposition.builder("attachment")
                .filename(transliterateName(request.getBookingPerson()) + "-booking-certificate.pdf")
                .build());
        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(service.generate(request));
    }

    private String transliterateName(CertificateGeneratorDto.BookingPerson bookingPerson) {
        return transliterationService
                .transliterate("%s-%s-%s".formatted(bookingPerson.getLastName(), bookingPerson.getFirstName(), bookingPerson.getMiddleName())).toLowerCase();
    }
}
