package pro.gorodnyuk.certificategenerator.service;

import lombok.RequiredArgsConstructor;
import net.sf.jasperreports.engine.JRException;
import org.springframework.stereotype.Service;
import pro.gorodnyuk.certificategenerator.api.CertificateGeneratorDto;

import java.io.FileNotFoundException;

@Service
@RequiredArgsConstructor
public class CertificateGeneratorService {

    private final ReportGenerator reportGenerator;

    public byte[] generate(CertificateGeneratorDto certificateGeneratorDto)
            throws FileNotFoundException, JRException {
        return reportGenerator.generate(
                certificateGeneratorDto.getBookingPerson().getLastName(),
                certificateGeneratorDto.getBookingPerson().getFirstName(),
                certificateGeneratorDto.getBookingPerson().getMiddleName(),
                certificateGeneratorDto.getBookingDate());
    }
}
