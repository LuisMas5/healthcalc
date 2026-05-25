package stepdefinitions;

import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import io.cucumber.java.es.Cuando;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import healthcalc.Gender;
import healthcalc.Person;
import healthcalc.VisceralAdiposityData;
import healthcalc.VisceralAdiposityIndex;
import healthcalc.VisceralAdiposityIndexImpl;
import healthcalc.exceptions.InvalidHealthDataException;

public class VAISteps {

    private VisceralAdiposityIndex vaiCalculator;
    private float cc;
    private float tg;
    private float hdl;
    private double imc;
    private Gender sexo;
    private double vaiResult;
    private InvalidHealthDataException exception;

    @Dado("que la calculadora de salud esta operativa")
    public void que_la_calculadora_de_salud_esta_operativa() {
        vaiCalculator = new VisceralAdiposityIndexImpl();
        cc = 0;
        tg = 0;
        hdl = 0;
        imc = 0;
        sexo = null;
        vaiResult = 0;
        exception = null;
    }

    @Dado("que el paciente tiene una circunferencia de cintura de {double} cm")
    public void que_el_paciente_tiene_una_circunferencia_de_cintura_de_cm(Double double1) {
        cc = double1.floatValue();
    }

    @Dado("el paciente tiene un nivel de trigliceridos de {double} mmol\\/L")
    public void el_paciente_tiene_un_nivel_de_trigliceridos_de_mmol_l(Double double1) {
        tg = double1.floatValue();
    }

    @Dado("el paciente tiene un nivel de HDL de {double} mmol\\/L")
    public void el_paciente_tiene_un_nivel_de_hdl_de_mmol_l(Double double1) {
        hdl = double1.floatValue();
    }

    @Dado("el paciente tiene un IMC de {double}")
    public void el_paciente_tiene_un_imc_de(Double double1) {
        imc = double1;
    }

    @Dado("el paciente es de sexo femenino")
    public void el_paciente_es_de_sexo_femenino() {
        sexo = Gender.FEMALE;
    }

    @Dado("el paciente es de sexo masculino")
    public void el_paciente_es_de_sexo_masculino() {
        sexo = Gender.MALE;
    }

    @Dado("el paciente tiene un sexo no valido")
    public void el_paciente_tiene_un_sexo_no_valido() {
        sexo = null; 
    }

    @Cuando("calculo el VAI del paciente")
    public void calculo_el_vai_del_paciente() {
        try {
            Person dummyPerson = new Person() {
                @Override
                public double weight() {
                    return imc * (1.8 * 1.8);
                }
                @Override
                public double height() {
                    return 1.8;
                }
                @Override
                public Gender gender() {
                    return sexo;
                }
                @Override
                public int age() {
                    return 30;
                }
            };

            VisceralAdiposityData data = new VisceralAdiposityData(dummyPerson, cc, tg, hdl);
            vaiResult = vaiCalculator.vai(data);

        } catch (InvalidHealthDataException e) {
            exception = e;
        }
    }

    @Entonces("el resultado del VAI deberia ser aproximadamente {double}")
    public void el_resultado_del_vai_deberia_ser_aproximadamente(Double double1) {
       assertEquals(double1, vaiResult, 0.01);
    }

    @Entonces("el sistema debe lanzar una excepcion")
    public void el_sistema_debe_lanzar_una_excepcion() {
        assertNotNull(exception);
    }
}
