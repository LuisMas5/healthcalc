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

@DisplayName("Tests para la calculadora de salud.")
public class VAITest {

    private HealthCalc healthCalc;

    @BeforeEach
    void setUp() {
		healthCalc = HealthCalcImpl.getInstance();
    }

    @Nested
    @DisplayName("Métrica del VAI")
    class VAIMetricTests {

        @Test
        @DisplayName("Cálculo de VAI para hombre con valores estándar válidos")
        void testVaiHombreValido() throws InvalidHealthDataException {

            // Arrange
            String sexo = "m";
            double bmi = 27.0;
            double cc = 95.0;
            double tg = 1.5;
            double hdl = 1.1;

            double expected = (cc / (39.68 + (1.88 * bmi))) *
                              (tg / 1.03) *
                              (1.31 / hdl);

            // Act
            double result = healthCalc.vai(sexo, bmi, cc, tg, hdl);

            // Assert
            assertEquals(expected, result, 0.001);
        }

        @Test
        @DisplayName("Cálculo de VAI para mujer con valores estándar válidos")
        void testVaiMujerValido() throws InvalidHealthDataException {

            // Arrange
            String sexo = "f";
            double bmi = 24.0;
            double cc = 80.0;
            double tg = 1.2;
            double hdl = 1.4;

            double expected = (cc / (36.58 + (1.89 * bmi))) *
                              (tg / 0.81) *
                              (1.52 / hdl);

            // Act
            double result = healthCalc.vai(sexo, bmi, cc, tg, hdl);

            // Assert
            assertEquals(expected, result, 0.001);
        }

        @Test
        @DisplayName("Lanzar excepción cuando el sexo no es 'm' ni 'f'")
        void testSexoInvalido() {
            assertAll(
                () -> assertThrows(InvalidHealthDataException.class,
                        () -> healthCalc.vai("x", 25.0, 90.0, 1.2, 1.3)),
                () -> assertThrows(InvalidHealthDataException.class,
                        () -> healthCalc.vai("M", 25.0, 90.0, 1.2, 1.3)),
                () -> assertThrows(InvalidHealthDataException.class,
                        () -> healthCalc.vai("", 25.0, 90.0, 1.2, 1.3))
            );
        }

        @Test
        @DisplayName("Lanzar excepción cuando los valores son negativos o cero")
        void testValoresNegativosOCero() {
            assertAll(
                () -> assertThrows(InvalidHealthDataException.class,
                        () -> healthCalc.vai("m", -1.0, 90.0, 1.2, 1.3)),
                () -> assertThrows(InvalidHealthDataException.class,
                        () -> healthCalc.vai("m", 25.0, -10.0, 1.2, 1.3)),
                () -> assertThrows(InvalidHealthDataException.class,
                        () -> healthCalc.vai("m", 25.0, 90.0, -1.0, 1.3)),
                () -> assertThrows(InvalidHealthDataException.class,
                        () -> healthCalc.vai("m", 25.0, 90.0, 1.2, -1.0)),
                () -> assertThrows(InvalidHealthDataException.class,
                        () -> healthCalc.vai("m", 0.0, 90.0, 1.2, 1.3)),
                () -> assertThrows(InvalidHealthDataException.class,
                        () -> healthCalc.vai("m", 25.0, 0.0, 1.2, 1.3)),
                () -> assertThrows(InvalidHealthDataException.class,
                        () -> healthCalc.vai("m", 25.0, 90.0, 0.0, 1.3)),
                () -> assertThrows(InvalidHealthDataException.class,
                        () -> healthCalc.vai("m", 25.0, 90.0, 1.2, 0.0))
            );
        }

@ParameterizedTest(name = "BMI extremo inválido: {0}")
@ValueSource(doubles = {150.1, 200.0, 500.0})
@DisplayName("Bloqueo de valores de BMI superiores al límite humano razonable (150)")
void testBmiMaximoImposible(double bmi) {
    assertThrows(InvalidHealthDataException.class,
        () -> healthCalc.vai("m", bmi, 90.0, 1.2, 1.3));
}

@ParameterizedTest(name = "CC extremo inválido: {0}")
@ValueSource(doubles = {300.1, 400.0, 1000.0})
@DisplayName("Bloqueo de valores de circunferencia de cintura superiores al límite humano razonable (300 cm)")
void testCcMaximoImposible(double cc) {
    assertThrows(InvalidHealthDataException.class,
        () -> healthCalc.vai("m", 25.0, cc, 1.2, 1.3));
}

@ParameterizedTest(name = "TG extremo inválido: {0}")
@ValueSource(doubles = {20.1, 50.0, 100.0})
@DisplayName("Bloqueo de valores de triglicéridos superiores al límite biológico razonable")
void testTgMaximoImposible(double tg) {
    assertThrows(InvalidHealthDataException.class,
        () -> healthCalc.vai("m", 25.0, 90.0, tg, 1.3));
}

@ParameterizedTest(name = "HDL extremo inválido: {0}")
@ValueSource(doubles = {5.1, 10.0, 20.0})
@DisplayName("Bloqueo de valores de HDL superiores al límite biológico razonable")
void testHdlMaximoImposible(double hdl) {
    assertThrows(InvalidHealthDataException.class,
        () -> healthCalc.vai("m", 25.0, 90.0, 1.2, hdl));
}
    }
}