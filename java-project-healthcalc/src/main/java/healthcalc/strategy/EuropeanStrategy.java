package healthcalc.strategy;

import healthcalc.HealthCalcImpl;
import healthcalc.exceptions.InvalidHealthDataException;

public class EuropeanStrategy implements HealthCalcStrategy {

    @Override
    public void calcularYMostrarIMC(double heightMeters, double weightGrams, String lang) throws InvalidHealthDataException {
        // 1. Convertir gramos a kilos
        double weightKg = weightGrams / 1000.0;
        
        // 2. Obtener la instancia de la calculadora 
        HealthCalcImpl calculator = HealthCalcImpl.getInstance();
        
        // 3. Calcular IMC
        double imc = calculator.bmi(weightKg, heightMeters);
        
        // 4. Mostrar mensaje en el idioma seleccionado
        if (lang.equalsIgnoreCase("en")) {
            System.out.printf("The person with height %.2f meters and %.2f Kg has a BMI of %.2f.\n", 
                              heightMeters, weightKg, imc);
        } else {
            System.out.printf("La persona con altura %.2f metros y %.2f Kg tiene un IMC de %.2f.\n", 
                              heightMeters, weightKg, imc);
        }
    }
}