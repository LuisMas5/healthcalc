package healthcalc;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import healthcalc.exceptions.InvalidHealthDataException;

@DisplayName("Tests para la calculadora de salud (VAI).")
public class VAITest {

    private VisceralAdiposityIndex vaiCalculator;

    @BeforeEach
    void setUp() {
        vaiCalculator = new VisceralAdiposityIndexImpl();
    }

    private Person createDummyPerson(Gender gender, double targetBmi) {
        return new Person() {
            @Override
            public double weight() { 
                return (targetBmi * (1.8 * 1.8)); 
            }
            @Override
            public double height() { return 1.8; } 
            @Override
            public Gender gender() { return gender; }
            @Override
            public int age() { return 30; }
        };
    }

    @Nested
    @DisplayName("Métrica del VAI")
    class VAIMetricTests {

        @Test
        @DisplayName("Cálculo de VAI para hombre con valores estándar válidos")
        void testVaiHombreValido() throws InvalidHealthDataException {
            Person hombre = createDummyPerson(Gender.MALE, 27.0);
            float cc = 95.0f;
            float tg = 1.5f;
            float hdl = 1.1f;
            
            VisceralAdiposityData data = new VisceralAdiposityData(hombre, cc, tg, hdl);
            double expected = (cc / (39.68 + (1.88 * 27.0))) * (tg / 1.03) * (1.31 / hdl);
            double result = vaiCalculator.vai(data);

            assertEquals(expected, result, 0.001);
        }

        @Test
        @DisplayName("Cálculo de VAI para mujer con valores estándar válidos")
        void testVaiMujerValido() throws InvalidHealthDataException {
            Person mujer = createDummyPerson(Gender.FEMALE, 24.0);
            float cc = 80.0f;
            float tg = 1.2f;
            float hdl = 1.4f;

            VisceralAdiposityData data = new VisceralAdiposityData(mujer, cc, tg, hdl);
            double expected = (cc / (36.58 + (1.89 * 24.0))) * (tg / 0.81) * (1.52 / hdl);
            double result = vaiCalculator.vai(data);

            assertEquals(expected, result, 0.001);
        }

        @Test
        @DisplayName("Lanzar excepción cuando los valores son negativos o cero")
        void testValoresNegativosOCero() {
            Person hombre = createDummyPerson(Gender.MALE, 25.0);
            
            assertAll(
                () -> assertThrows(InvalidHealthDataException.class,
                        () -> vaiCalculator.vai(new VisceralAdiposityData(hombre, -10.0f, 1.2f, 1.3f))),
                () -> assertThrows(InvalidHealthDataException.class,
                        () -> vaiCalculator.vai(new VisceralAdiposityData(hombre, 90.0f, -1.0f, 1.3f))),
                () -> assertThrows(InvalidHealthDataException.class,
                        () -> vaiCalculator.vai(new VisceralAdiposityData(hombre, 90.0f, 1.2f, -1.0f))),
                () -> assertThrows(InvalidHealthDataException.class,
                        () -> vaiCalculator.vai(new VisceralAdiposityData(hombre, 0.0f, 1.2f, 1.3f))),
                () -> assertThrows(InvalidHealthDataException.class,
                        () -> vaiCalculator.vai(new VisceralAdiposityData(hombre, 90.0f, 0.0f, 1.3f))),
                () -> assertThrows(InvalidHealthDataException.class,
                        () -> vaiCalculator.vai(new VisceralAdiposityData(hombre, 90.0f, 1.2f, 0.0f)))
            );
        }

        @ParameterizedTest(name = "BMI extremo: {0}")
        @ValueSource(doubles = {150.1, 200.0, 500.0})
        @DisplayName("Bloqueo de valores de BMI superiores al límite humano razonable")
        void testBmiMaximoImposible(double bmi) {
            Person hombreBmiExtremo = createDummyPerson(Gender.MALE, bmi);
            assertThrows(InvalidHealthDataException.class,
                () -> vaiCalculator.vai(new VisceralAdiposityData(hombreBmiExtremo, 90.0f, 1.2f, 1.3f)));
        }

        @ParameterizedTest(name = "CC extremo: {0}")
        @ValueSource(floats = {300.1f, 400.0f, 1000.0f})
        @DisplayName("Bloqueo de valores de CC superiores al límite")
        void testCcMaximoImposible(float cc) {
            Person hombre = createDummyPerson(Gender.MALE, 25.0);
            assertThrows(InvalidHealthDataException.class,
                () -> vaiCalculator.vai(new VisceralAdiposityData(hombre, cc, 1.2f, 1.3f)));
        }

        @ParameterizedTest(name = "TG extremo: {0}")
        @ValueSource(floats = {20.1f, 50.0f, 100.0f})
        @DisplayName("Bloqueo de valores de TG superiores al límite biológico")
        void testTgMaximoImposible(float tg) {
            Person hombre = createDummyPerson(Gender.MALE, 25.0);
            assertThrows(InvalidHealthDataException.class,
                () -> vaiCalculator.vai(new VisceralAdiposityData(hombre, 90.0f, tg, 1.3f)));
        }

        @ParameterizedTest(name = "HDL extremo: {0}")
        @ValueSource(floats = {5.1f, 10.0f, 20.0f})
        @DisplayName("Bloqueo de valores de HDL superiores al límite biológico")
        void testHdlMaximoImposible(float hdl) {
            Person hombre = createDummyPerson(Gender.MALE, 25.0);
            assertThrows(InvalidHealthDataException.class,
                () -> vaiCalculator.vai(new VisceralAdiposityData(hombre, 90.0f, 1.2f, hdl)));
        }
    }
}
