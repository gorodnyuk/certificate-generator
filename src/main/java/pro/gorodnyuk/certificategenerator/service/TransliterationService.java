package pro.gorodnyuk.certificategenerator.service;

import com.ibm.icu.text.Transliterator;
import org.springframework.stereotype.Component;

@Component
public class TransliterationService {

    private final Transliterator transliterator;

    public TransliterationService() {
        this.transliterator = Transliterator.getInstance("Russian-Latin/BGN");
    }

    public String transliterate(String fullName) {
        return transliterator.transliterate(fullName);
    }
}
