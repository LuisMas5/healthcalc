package healthcalc.hospital;

import healthcalc.exceptions.InvalidHealthDataException;

public interface HealthHospital {

    ResultadoIMC indiceMasaCorporal(float altura, int peso) throws InvalidHealthDataException;

    int pesoCorporalIdeal(char genero, float altura) throws InvalidHealthDataException;
}
