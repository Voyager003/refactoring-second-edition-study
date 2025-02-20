package ex.refactoringsecondeditionstudy.ch04;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ProvinceTest {
    private static final DataLoader DATA_LOADER = new DataLoader(
            ProvinceTest.class.getClassLoader(),
            new ObjectMapper()
    );

    @Test
    void shortfall() {
        final Province asia = new Province(DATA_LOADER.sampleProvinceData());

        final int shortfall = asia.getShortfall();

        assertThat(shortfall).isEqualTo(5);
    }
}
