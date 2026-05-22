package healthcalc;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import healthcalc.exceptions.InvalidHealthDataException;

/**
 * Tests for ideal weight calculation.
 * 
 * @author ISA
 */
@DisplayName("Tests for ideal weight (IW).")
public class IWTest {

    private final HealthCalcImpl calcu = HealthCalcImpl.getInstance();

    ///////////////////////////////////////////////////////////////////////////
    // idealWeight() test
    ///////////////////////////////////////////////////////////////////////////

    // 1
    @Test
    @DisplayName("Test de Altura Negativa IW")
    public void testAlturaNegativaIW() {
        assertThrows(InvalidHealthDataException.class,
                () -> calcu.idealWeight(-5, "m"),
                "La altura introducida es negativa");
    }

    // 2
    @Test
    @DisplayName("Test de Altura menor a la esperada IW")
    public void testAlturaMenorPosibleIW() {
        assertThrows(InvalidHealthDataException.class,
                () -> calcu.idealWeight(20, "m"),
                "La altura introducida es menor a la esperada la formula da errores con valores lejanos al promedio, recuerda que es altura en cm");
    }

    // 3
    @Test
    @DisplayName("Test de Altura mayor a la esperada IW")
    public void testAlturaMayorPosibleIW() {
        assertThrows(InvalidHealthDataException.class,
                () -> calcu.idealWeight(301, "m"),
                "La altura introducida es demasiado alta, revisa los valores introducidos");
    }

    // 4
    @Test
    @DisplayName("Test de altura problematica con la formula hombres IW")
    public void testAlturaProblemaHombresIW() {
        assertThrows(InvalidHealthDataException.class,
                () -> calcu.idealWeight(29, "m"),
                "La altura introducida es menor a la esperada la formula da errores con valores lejanos al promedio, recuerda que es altura en cm");
    }

    // 5
    @Test
    @DisplayName("Test de altura problematica con la formula mujeres IW")
    public void testAlturaProblemaMujeresIW() {
        assertThrows(InvalidHealthDataException.class,
                () -> calcu.idealWeight(29, "f"),
                "La altura introducida es menor a la esperada la formula da errores con valores lejanos al promedio, recuerda que es altura en cm");
    }

    // 6
    @Test
    @DisplayName("Test de sexo erroneo IW")
    public void testGenderProblematicIW() {
        assertThrows(InvalidHealthDataException.class,
                () -> calcu.idealWeight(168, "x"),
                "Solo se acepta entradas de 'f' for Female (mujer) y 'm' for Male (hombre)");
    }

    // 7
    @Test
    @DisplayName("Test entrada de sexo correcta IW")
    public void testSexCorrectIW() {
        assertDoesNotThrow(() -> calcu.idealWeight(168, "m"));
        assertDoesNotThrow(() -> calcu.idealWeight(168, "f"));
    }

    // 8
    @Test
    @DisplayName("Test hombre uso promedio IW")
    public void testHombrePromedioIW() throws InvalidHealthDataException {
        double iw = 110 - 100 - (110 - 150) / 4.0;
        assertEquals(iw, calcu.idealWeight(110, "m"), 0.001);

        iw = 168 - 100 - (168 - 150) / 4.0;
        assertEquals(iw, calcu.idealWeight(168, "m"), 0.001);

        iw = 250 - 100 - (250 - 150) / 4.0;
        assertEquals(iw, calcu.idealWeight(250, "m"), 0.001);
    }

    // 9
    @Test
    @DisplayName("Test mujer uso promedio IW")
    public void testMujerPromedioIW() throws InvalidHealthDataException {
        double iw = 100 - 100 - (100 - 150) / 2.0;
        assertEquals(iw, calcu.idealWeight(100, "f"), 0.001);

        iw = 168 - 100 - (168 - 150) / 2.0;
        assertEquals(iw, calcu.idealWeight(168, "f"), 0.001);

        iw = 250 - 100 - (250 - 150) / 2.0;
        assertEquals(iw, calcu.idealWeight(250, "f"), 0.001);
    }

    // 10
    @Test
    @DisplayName("Test hombre especifico IW")
    public void testHombreEspecificoIW() throws InvalidHealthDataException {
        assertEquals(63.5, calcu.idealWeight(168, "m"), 0.001);
    }

    // 11
    @Test
    @DisplayName("Test mujer especifica IW")
    public void testMujerEspecificaIW() throws InvalidHealthDataException {
        assertEquals(59, Math.round(calcu.idealWeight(168, "f")));
    }
        @Test
    @DisplayName("Test peso ideal usando Person hombre")
    public void testIdealWeightConPersonHombre() throws InvalidHealthDataException {
        Person person = new HealthPerson(70.0, 1.68, Gender.MALE, 25);

        double expected = 168 - 100 - (168 - 150) / 4.0;

        assertEquals(expected, calcu.idealWeight(person), 0.001);
    }

    @Test
    @DisplayName("Test peso ideal usando Person mujer")
    public void testIdealWeightConPersonMujer() throws InvalidHealthDataException {
        Person person = new HealthPerson(60.0, 1.68, Gender.FEMALE, 25);

        double expected = 168 - 100 - (168 - 150) / 2.0;

        assertEquals(expected, calcu.idealWeight(person), 0.001);
    }
    

}
