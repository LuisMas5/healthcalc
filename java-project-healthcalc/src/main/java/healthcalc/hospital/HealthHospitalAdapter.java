package healthcalc.hospital;

import healthcalc.HealthCalc;
import healthcalc.HealthCalcImpl;
import healthcalc.exceptions.InvalidHealthDataException;

public class HealthHospitalAdapter implements HealthHospital {

    private final HealthCalc calculadora;

    public HealthHospitalAdapter() {
        this.calculadora = HealthCalcImpl.getInstance();
    }

    @Override
    public ResultadoIMC indiceMasaCorporal(float altura, int peso) throws InvalidHealthDataException {
        double pesoKg = peso / 1000.0;

        double imc = calculadora.bmi(pesoKg, altura);
        String clasificacion = calculadora.bmiClassification(imc);

        return new ResultadoIMC((float) imc, clasificacion);
    }

    @Override
    public int pesoCorporalIdeal(char genero, float altura) throws InvalidHealthDataException {
        String sex = convertirGenero(genero);
        double alturaCm = altura * 100.0;

        double pesoIdeal = calculadora.idealWeight(alturaCm, sex);

        return Math.round((float) pesoIdeal);
    }

    private String convertirGenero(char genero) throws InvalidHealthDataException {
        char generoChar = Character.toLowerCase(genero);

        if (generoChar == 'm') {
            return "m";
        }

        if (generoChar == 'f') {
            return "f";
        }

        throw new InvalidHealthDataException("Gender must be 'm' or 'f'.");
    }
}
