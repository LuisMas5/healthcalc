package healthcalc.strategy;

import healthcalc.exceptions.InvalidHealthDataException;

public class HealthContext {
    private HealthCalcStrategy strategy;

    // Permite inyectar la estrategia inicial si se quiere
    public HealthContext(HealthCalcStrategy strategy) {
        this.strategy = strategy;
    }
    
    // Constructor vacío por defecto
    public HealthContext() {}

    // Cambiar la estrategia en tiempo de ejecución
    public void setStrategy(HealthCalcStrategy strategy) {
        this.strategy = strategy;
    }

    // Ejecutar la estrategia actual
    public void executeStrategy(double height, double weight, String lang) throws InvalidHealthDataException {
        if (this.strategy == null) {
            throw new IllegalStateException("No se ha definido ninguna estrategia de cálculo.");
        }
        this.strategy.calcularYMostrarIMC(height, weight, lang);
    }
}