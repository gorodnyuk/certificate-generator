package pro.gorodnyuk.certificategenerator.service;

import lombok.RequiredArgsConstructor;
import net.sf.jasperreports.engine.JRException;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;
import pro.gorodnyuk.certificategenerator.api.CertificateGeneratorDto;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;

@Service
@RequiredArgsConstructor
public class CertificateGeneratorService {

    private final ReportGenerator reportGenerator;

    public InputStreamResource generate(CertificateGeneratorDto certificateGeneratorDto)
            throws FileNotFoundException, JRException {
        return new InputStreamResource(new ByteArrayInputStream(reportGenerator.generate(
                certificateGeneratorDto.getBookingPerson().getLastName(),
                certificateGeneratorDto.getBookingPerson().getFirstName(),
                certificateGeneratorDto.getBookingPerson().getMiddleName(),
                certificateGeneratorDto.getBookingDate())));
    }
}
