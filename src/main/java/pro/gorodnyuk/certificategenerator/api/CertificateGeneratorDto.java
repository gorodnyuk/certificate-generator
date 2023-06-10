package pro.gorodnyuk.certificategenerator.api;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
public class CertificateGeneratorDto {

    @Valid
    @NotNull
    private BookingPerson bookingPerson;

    @NotNull
    private LocalDate bookingDate;

    @Data
    public static class BookingPerson {

        @NotBlank
        private String lastName;

        @NotBlank
        private String firstName;

        @NotBlank
        private String middleName;
    }
}
