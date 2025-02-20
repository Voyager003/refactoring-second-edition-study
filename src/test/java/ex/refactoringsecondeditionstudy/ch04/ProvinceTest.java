package ex.refactoringsecondeditionstudy.ch04;


import com.fasterxml.jackson.databind.ObjectMapper;

public class ProvinceTest {
    private static final DataLoader DATA_LOADER = new DataLoader(
            ProvinceTest.class.getClassLoader(),
            new ObjectMapper()
    );
}
