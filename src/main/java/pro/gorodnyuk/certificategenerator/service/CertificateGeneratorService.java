package pro.gorodnyuk.certificategenerator.service;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import pro.gorodnyuk.certificategenerator.api.CertificateGeneratorDto;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@Service
public class CertificateGeneratorService {

    private static final DateTimeFormatter LOCAL_DATE_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public InputStreamResource generate(CertificateGeneratorDto certificateGeneratorDto)
            throws FileNotFoundException, JRException {
        String filePath = ResourceUtils.getFile("classpath:jasper/booking-certificate.jrxml")
                .getAbsolutePath();
        String leftImagePath = ResourceUtils.getFile("classpath:jasper/cert-background.png")
                .getAbsolutePath();
        String stampImagePath = ResourceUtils.getFile("classpath:jasper/stamp.png")
                .getAbsolutePath();

        JasperReport jasperReport = JasperCompileManager.compileReport(filePath);
        Map<String, Object> params = new HashMap<>();
        params.put("fullName", generateFullName(certificateGeneratorDto.getBookingPerson()));
        params.put("bookingDate", LOCAL_DATE_FORMATTER.format(certificateGeneratorDto.getBookingDate()));
        params.put("leftImage", leftImagePath);
        params.put("stampImage", stampImagePath);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, new JREmptyDataSource());

        return new InputStreamResource(new ByteArrayInputStream(JasperExportManager.exportReportToPdf(jasperPrint)));
    }

    private String generateFullName(CertificateGeneratorDto.BookingPerson bookingPerson) {
        return String.format("%s %s %s",
                bookingPerson.getLastName(),
                bookingPerson.getFirstName(),
                bookingPerson.getMiddleName()
        );
    }
}
