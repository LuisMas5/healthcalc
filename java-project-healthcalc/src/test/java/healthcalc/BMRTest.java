package healthcalc;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import healthcalc.exceptions.InvalidHealthDataException;

/**
 * Tests for basal metabolic rate calculation.
 * 
 * @author ISA
 */
@DisplayName("Tests for basal metabolic rate (BMR).")
public class BMRTest {

    private final HealthCalcImpl calcu = HealthCalcImpl.getInstance();

    ///////////////////////////////////////////////////////////////////////////
    // basalMetabolicRate() test
    ///////////////////////////////////////////////////////////////////////////

    // 1
    @Test
    @DisplayName("Test entrada incorrecta peso BMR")
    public void testPesoIncorrectoBMR() {
        assertThrows(InvalidHealthDataException.class,
                () -> calcu.basalMetabolicRate(-1, 168, 22, "m"),
                "valor de peso incorrecto (solo se aceptaran valores dentro del umbral 1kg<X<700kg)");
    }

    // 2
    @Test
    @DisplayName("Test entrada incorrecta altura BMR")
    public void testAlturaIncorrectaBMR() {
        assertThrows(InvalidHealthDataException.class,
                () -> calcu.basalMetabolicRate(60, 0, 22, "m"),
                "valor de altura incorrecto (solo se aceptaran valores dentro del umbral 30cm<X<300cm)");
    }

    // 3
    @Test
    @DisplayName("Test entrada incorrecta sexo BMR")
    public void testSexoIncorrectoBMR() {
        assertThrows(InvalidHealthDataException.class,
                () -> calcu.basalMetabolicRate(60, 168, 22, "x"),
                "valor de sexo incorrecto (solo se aceptaran valores correspondientes a 'm' y 'f')");
    }

    // 4
    @Test
    @DisplayName("Test entrada incorrecta edad BMR")
    public void testEdadIncorrectaBMR() {
        assertThrows(InvalidHealthDataException.class,
                () -> calcu.basalMetabolicRate(60, 168, 200, "m"),
                "edad incorrecto (solo se aceptaran valores dentro del umbral 0<X<150 años)");
    }

    // 5
    @Test
    @DisplayName("Test peso fuera de rango BMR")
    public void testValoresRarosBMR() {
        assertThrows(InvalidHealthDataException.class,
                () -> calcu.basalMetabolicRate(800, 168, 22, "f"),
                "valores introducidos no son adecuados para el calculo");
    }

    // 6
    @Test
    @DisplayName("Test entrada de TODOS los sexos BMR")
    public void testSexCorrectoBMR() {
        assertDoesNotThrow(() -> calcu.basalMetabolicRate(60, 168, 21, "m"));
        assertDoesNotThrow(() -> calcu.basalMetabolicRate(60, 168, 21, "f"));
    }

    // 7
    @Test
    @DisplayName("Test mujer promedio BMR")
    public void testWomanBMR() throws InvalidHealthDataException {
        double bmr = (10 * 21 + 6.25 * 150 - 5 * 13 - 161);
        assertEquals(bmr, calcu.basalMetabolicRate(21, 150, 13, "f"), 0.001);

        bmr = (10 * 65 + 6.25 * 167 - 5 * 22 - 161);
        assertEquals(bmr, calcu.basalMetabolicRate(65, 167, 22, "f"), 0.001);

        bmr = (10 * 80 + 6.25 * 170 - 5 * 55 - 161);
        assertEquals(bmr, calcu.basalMetabolicRate(80, 170, 55, "f"), 0.001);
    }

    // 8
    @Test
    @DisplayName("Test hombre promedio BMR")
    public void testMenBMR() throws InvalidHealthDataException {
        double bmr = (10 * 21 + 6.25 * 150 - 5 * 13 + 5);
        assertEquals(bmr, calcu.basalMetabolicRate(21, 150, 13, "m"), 0.001);

        bmr = (10 * 65 + 6.25 * 167 - 5 * 22 + 5);
        assertEquals(bmr, calcu.basalMetabolicRate(65, 167, 22, "m"), 0.001);

        bmr = (10 * 80 + 6.25 * 170 - 5 * 55 + 5);
        assertEquals(bmr, calcu.basalMetabolicRate(80, 170, 55, "m"), 0.001);
    }

    // 9
    @Test
    @DisplayName("Test mujer Externa BMR")
    public void testWomanExternaBMR() throws InvalidHealthDataException {
        assertEquals(1390.25, calcu.basalMetabolicRate(60, 169, 21, "f"), 0.001);
    }

    // 10
    @Test
    @DisplayName("Test hombre Externo BMR")
    public void testMenExternoBMR() throws InvalidHealthDataException {
        assertEquals(1556.25, calcu.basalMetabolicRate(60, 169, 21, "m"), 0.001);
    }

}
