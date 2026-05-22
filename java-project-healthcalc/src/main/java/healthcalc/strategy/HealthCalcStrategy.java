package healthcalc.strategy;
import healthcalc.exceptions.InvalidHealthDataException;
public interface HealthCalcStrategy {
    void calcularYMostrarIMC(double height, double weight, String lang) throws InvalidHealthDataException;
}