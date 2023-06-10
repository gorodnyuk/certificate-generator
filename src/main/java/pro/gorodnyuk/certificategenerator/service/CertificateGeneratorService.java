package pro.gorodnyuk.certificategenerator.service;

import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;
import pro.gorodnyuk.certificategenerator.api.CertificateGeneratorDto;

@Service
public class CertificateGeneratorService {

    public InputStreamResource generate(CertificateGeneratorDto certificateGeneratorDto) {
        throw new RuntimeException("Not implemented yet");
    }
}
