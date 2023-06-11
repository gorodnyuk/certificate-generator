package pro.gorodnyuk.certificategenerator.service;

import net.sf.jasperreports.engine.JRException;

import java.io.FileNotFoundException;
import java.time.LocalDate;

public interface ReportGenerator {

    byte[] generate(String lastName, String firstName, String middleName, LocalDate bookingDate)
            throws FileNotFoundException, JRException;
}
