package healthcalc.hospital;

import healthcalc.exceptions.InvalidHealthDataException;

public abstract class HealthHospitalDecorator implements HealthHospital {

    protected final HealthHospital wrapped;

    public HealthHospitalDecorator(HealthHospital wrapped) {
        this.wrapped = wrapped;
    }

    @Override
    public ResultadoIMC indiceMasaCorporal(float altura, int peso) throws InvalidHealthDataException {
        return wrapped.indiceMasaCorporal(altura, peso);
    }

    @Override
    public int pesoCorporalIdeal(char genero, float altura) throws InvalidHealthDataException {
        return wrapped.pesoCorporalIdeal(genero, altura);
    }
}
