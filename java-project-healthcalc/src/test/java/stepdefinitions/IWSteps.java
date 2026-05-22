package stepdefinitions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import healthcalc.HealthCalcImpl;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;

public class IWSteps {

    private HealthCalcImpl calculadora = HealthCalcImpl.getInstance();

    public static double altura;
    public static String sexo;
    public static double resultado;
    public static Exception excepcion;

    @Dado("que el paciente tiene una altura de {double} cm")
    public void que_el_paciente_tiene_una_altura_de_cm(Double altura) {
        IWSteps.altura = altura;
    }

    @Dado("en el IW el paciente es de sexo {word}")
    public void en_el_iw_el_paciente_es_de_sexo(String sexoTexto) {
        if (sexoTexto.equalsIgnoreCase("masculino")) {
            IWSteps.sexo = "m";
        } else if (sexoTexto.equalsIgnoreCase("femenino")) {
            IWSteps.sexo = "f";
        } else {
            IWSteps.sexo = sexoTexto;
        }
    }

    @Dado("en el IW el paciente tiene un sexo no valido")
    public void en_el_iw_el_paciente_tiene_un_sexo_no_valido() {
        IWSteps.sexo = "x";
    }

    @Cuando("calculo el IW del paciente")
    public void calculo_el_iw_del_paciente() {
        excepcion = null;
        try {
            resultado = calculadora.idealWeight(altura, sexo);
        } catch (Exception e) {
            excepcion = e;
        }
    }

    @Entonces("el resultado del IW deberia ser aproximadamente {double}")
    public void el_resultado_del_iw_deberia_ser_aproximadamente(Double esperado) {
        assertEquals(esperado, resultado, 0.001);
    }

    @Entonces("en el IW el sistema debe lanzar una excepcion")
    public void en_el_iw_el_sistema_debe_lanzar_una_excepcion() {
        assertNotNull(excepcion);
    }
}