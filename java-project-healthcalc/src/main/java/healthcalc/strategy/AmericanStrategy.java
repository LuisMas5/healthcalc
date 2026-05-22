package healthcalc.strategy;

import healthcalc.HealthCalcImpl;
import healthcalc.exceptions.InvalidHealthDataException;

public class AmericanStrategy implements HealthCalcStrategy {

    @Override
    public void calcularYMostrarIMC(double heightFeet, double weightLbs, String lang) throws InvalidHealthDataException {
        // 1. Conversiones (1 kg = 2.20462 libras, 1 m = 39.37 pulgadas, 1 pie = 12 pulgadas)
        double weightKg = weightLbs / 2.20462;
        double heightInches = heightFeet * 12;
        double heightMeters = heightInches / 39.37;
        
        // 2. Obtener instancia Singleton
        HealthCalcImpl calculator = HealthCalcImpl.getInstance();
        
        // 3. Calcular IMC
        double imc = calculator.bmi(weightKg, heightMeters);
        
        // 4. Mostrar mensaje
        if (lang.equalsIgnoreCase("en")) {
            System.out.printf("The person with height %.2f feet and %.2f lbs has a BMI of %.2f.\n", 
                              heightFeet, weightLbs, imc);
        } else {
            System.out.printf("La persona con altura %.2f pies y %.2f libras tiene un IMC de %.2f.\n", 
                              heightFeet, weightLbs, imc);
        }
    }
}