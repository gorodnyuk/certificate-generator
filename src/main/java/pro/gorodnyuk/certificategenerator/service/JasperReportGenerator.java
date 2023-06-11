package pro.gorodnyuk.certificategenerator.service;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@Component
public class JasperReportGenerator implements ReportGenerator {

    private static final DateTimeFormatter LOCAL_DATE_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    @Override
    public byte[] generate(String lastName, String firstName, String middleName, LocalDate bookingDate)
            throws FileNotFoundException, JRException {
        String reportPath = ResourceUtils.getFile("classpath:jasper/booking-certificate.jrxml")
                .getAbsolutePath();

        JasperReport jasperReport = JasperCompileManager.compileReport(reportPath);
        JasperPrint jasperPrint = JasperFillManager
                .fillReport(
                        jasperReport,
                        putReportParams(lastName, firstName, middleName, bookingDate),
                        new JREmptyDataSource()
                );

        return JasperExportManager.exportReportToPdf(jasperPrint);
    }

    private Map<String, Object> putReportParams(String lastName, String firstName, String middleName, LocalDate bookingDate)
            throws FileNotFoundException {
        String leftImagePath = ResourceUtils.getFile("classpath:jasper/cert-background.png")
                .getAbsolutePath();
        String stampImagePath = ResourceUtils.getFile("classpath:jasper/stamp.png")
                .getAbsolutePath();

        Map<String, Object> params = new HashMap<>();
        params.put("fullName", generateFullName(lastName, firstName, middleName));
        params.put("bookingDate", LOCAL_DATE_FORMATTER.format(bookingDate));
        params.put("leftImage", leftImagePath);
        params.put("stampImage", stampImagePath);

        return params;
    }

    private String generateFullName(String lastName, String firstName, String middleName) {
        return String.format("%s %s %s", lastName, firstName, middleName);
    }
}
