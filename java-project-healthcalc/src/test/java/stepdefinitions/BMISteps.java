package stepdefinitions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import healthcalc.HealthCalc;
import healthcalc.HealthCalcImpl;
import healthcalc.exceptions.InvalidHealthDataException;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;

public class BMISteps {

    private HealthCalc healthCalc = HealthCalcImpl.getInstance();
    private double peso;
    private double altura;
    private double bmiInput;
    private double bmiResult;
    private String clasificacionResult;
    private Exception exception;

    @Dado("que el paciente tiene un peso de {double} kg")
    public void que_el_paciente_tiene_un_peso_de_kg(Double peso) {
        this.peso = peso;
    }

    @Dado("el paciente tiene una altura de {double} m")
    public void el_paciente_tiene_una_altura_de_m(Double altura) {
        this.altura = altura;
    }

    @Dado("que el paciente tiene un valor de BMI de {double}")
    public void que_el_paciente_tiene_un_valor_de_bmi_de(Double bmiInput) {
        this.bmiInput = bmiInput;
    }

    @Cuando("calculo el BMI del paciente")
    public void calculo_el_bmi_del_paciente() {
        exception = null;
        try {
            bmiResult = healthCalc.bmi(peso, altura);
        } catch (Exception e) {
            exception = e;
        }
    }

    @Cuando("clasifico el BMI del paciente")
    public void clasifico_el_bmi_del_paciente() {
        exception = null;
        try {
            clasificacionResult = healthCalc.bmiClassification(bmiInput);
        } catch (Exception e) {
            exception = e;
        }
    }

    @Entonces("el resultado del BMI deberia ser aproximadamente {double}")
    public void el_resultado_del_bmi_deberia_ser_aproximadamente(Double expectedBmi) {
        assertEquals(expectedBmi, bmiResult, 0.01);
    }

    @Entonces("el resultado de la clasificacion deberia ser {string}")
    public void el_resultado_de_la_clasificacion_deberia_ser(String expectedCategoria) {
        assertEquals(expectedCategoria, clasificacionResult);
    }

    @Entonces("el sistema debe lanzar una excepcion de datos invalidos")
    public void el_sistema_debe_lanzar_una_excepcion_de_datos_invalidos() {
        assertNotNull(exception, "Se esperaba una excepción pero no se lanzó");
        assertEquals(InvalidHealthDataException.class, exception.getClass());
    }
}