package healthcalc.hospital;

import healthcalc.exceptions.InvalidHealthDataException;

public class HealthHospitalStatsDecorator extends HealthHospitalDecorator implements HealthStats {

    private float sumaAlturas = 0;
    private float sumaPesos = 0;
    private float sumaImc = 0;

    private int totalPacientes = 0;
    private int totalHombres = 0;
    private int totalMujeres = 0;

    public HealthHospitalStatsDecorator(HealthHospital wrapped) {
        super(wrapped);
    }

    @Override
    public ResultadoIMC indiceMasaCorporal(float altura, int peso) throws InvalidHealthDataException {
        ResultadoIMC resultado = wrapped.indiceMasaCorporal(altura, peso);

        float pesoKg = peso / 1000.0f;

        sumaAlturas += altura;
        sumaPesos += pesoKg;
        sumaImc += (float) resultado.getImc();
        totalPacientes++;

        return resultado;
    }

    @Override
    public int pesoCorporalIdeal(char genero, float altura) throws InvalidHealthDataException {
        char generoNormalizado = Character.toLowerCase(genero);

        if (generoNormalizado == 'm') {
            totalHombres++;
        } else if (generoNormalizado == 'f') {
            totalMujeres++;
        }

        return wrapped.pesoCorporalIdeal(genero, altura);
    }

    @Override
    public float alturaMedia() {
        return totalPacientes == 0 ? 0 : sumaAlturas / totalPacientes;
    }

    @Override
    public float pesoMedio() {
        return totalPacientes == 0 ? 0 : sumaPesos / totalPacientes;
    }

    @Override
    public float imcMedio() {
        return totalPacientes == 0 ? 0 : sumaImc / totalPacientes;
    }

    @Override
    public int numSexoH() {
        return totalHombres;
    }

    @Override
    public int numSexoM() {
        return totalMujeres;
    }

    @Override
    public int numTotalPacientes() {
        return totalPacientes;
    }
}