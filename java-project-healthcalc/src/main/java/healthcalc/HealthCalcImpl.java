package healthcalc;

import healthcalc.exceptions.InvalidHealthDataException;

public class HealthCalcImpl
        implements HealthCalc, BasalMetabolicIndex, IdealBodyWeight {

    private static HealthCalcImpl instance;

    private HealthCalcImpl() {

    }

    public static HealthCalcImpl getInstance() {
        if (instance == null) {
            instance = new HealthCalcImpl();
        }
        return instance;
    }

    @Override
    public String bmiClassification(double bmi) throws InvalidHealthDataException {
        return categoryToText(categoryFromBmi(bmi));
    }

    @Override
    public double bmi(double weight, double height) throws InvalidHealthDataException {
        if (weight <= 0) {
            throw new InvalidHealthDataException("Weight must be positive.");
        }
        if (height <= 0) {
            throw new InvalidHealthDataException("Height must be positive.");
        }
        if (weight < 1 || weight > 700) {
            throw new InvalidHealthDataException("Weight must be within a possible biological range [1-700] kg.");
        }
        if (height < 0.30 || height > 3.00) {
            throw new InvalidHealthDataException("Height must be within a possible biological range [0.30-3.00] m.");
        }
        return weight / Math.pow(height, 2);
    }

    @Override
    public double vai(String sex, double bmi, double cc, double tg, double hdl)
            throws InvalidHealthDataException {

        // Validar parámetros
        if (sex == null || (!sex.equals("m") && !sex.equals("f"))) {
            throw new InvalidHealthDataException("Sex must be 'm' (male) or 'f' (female).");
        }
        if (bmi <= 0 || bmi > 150) {
            throw new InvalidHealthDataException("BMI must be within valid range [1-150].");
        }
        if (cc <= 0 || cc > 200) {
            throw new InvalidHealthDataException("Waist circumference must be within valid range (0-200] cm.");
        }
        if (tg <= 0 || tg > 15) {
            throw new InvalidHealthDataException("Triglycerides must be within valid range (0-15] mmol/L.");
        }
        if (hdl <= 0 || hdl > 5) {
            throw new InvalidHealthDataException("HDL cholesterol must be within valid range (0-5] mmol/L.");
        }

        // Calcular VAI según el sexo
        if (sex.equals("m")) {
            // Para hombres: VAI = [CC / (39.68 + (1.88 * BMI))] × (TG / 1.03) × (1.31 /
            // HDL)
            return (cc / (39.68 + (1.88 * bmi))) * (tg / 1.03) * (1.31 / hdl);
        } else {
            // Para mujeres: VAI = [CC / (36.58 + (1.89 * BMI))] × (TG / 0.81) × (1.52 /
            // HDL)
            return (cc / (36.58 + (1.89 * bmi))) * (tg / 0.81) * (1.52 / hdl);
        }
    }

    @Override
    public double idealWeight(double heightCm, String sex) throws InvalidHealthDataException {
        if (heightCm <= 0) {
            throw new InvalidHealthDataException("Height must be positive.");
        }
        if (heightCm < 30 || heightCm > 300) {
            throw new InvalidHealthDataException("Height must be within realistic human limits [30-300] cm.");
        }
        if (sex == null || (!sex.equals("m") && !sex.equals("f"))) {
            throw new InvalidHealthDataException("Sex must be 'm' or 'f'.");
        }

        // Lorentz formula
        if (sex.equals("m")) {
            return (heightCm - 100) - ((heightCm - 150) / 4.0);
        } else {
            return (heightCm - 100) - ((heightCm - 150) / 2.0);
        }
    }

    @Override
    public double basalMetabolicRate(double weightKg, double heightCm, int ageYears, String sex)
            throws InvalidHealthDataException {
        if (weightKg <= 0) {
            throw new InvalidHealthDataException("Weight must be positive.");
        }
        if (heightCm <= 0) {
            throw new InvalidHealthDataException("Height must be positive.");
        }
        if (ageYears <= 0 || ageYears > 150) {
            throw new InvalidHealthDataException("Age must be a positive value less than or equal to 150.");
        }
        if (weightKg < 1 || weightKg > 700) {
            throw new InvalidHealthDataException("Weight must be within a possible biological range [1-700] kg.");
        }
        if (heightCm < 30 || heightCm > 300) {
            throw new InvalidHealthDataException("Height must be within realistic human limits [30-300] cm.");
        }
        if (sex == null || (!sex.equals("m") && !sex.equals("f"))) {
            throw new InvalidHealthDataException("Sex must be 'm' or 'f'.");
        }

        // Mifflin-St Jeor equation
        if (sex.equals("m")) {
            return (10 * weightKg) + (6.25 * heightCm) - (5 * ageYears) + 5;
        } else {
            return (10 * weightKg) + (6.25 * heightCm) - (5 * ageYears) - 161;
        }
    }

    @Override
    public double bmi(Person person) throws InvalidHealthDataException {
        return bmi(person.weight(), person.height());
    }

    @Override
    public BMICategory category(Person person) throws InvalidHealthDataException {
        return categoryFromBmi(bmi(person));
    }

    @Override
    public double idealWeight(Person person) throws InvalidHealthDataException {
        return idealWeight(person.height() * 100, person.gender() == Gender.MALE ? "m" : "f");
    }

    private BMICategory categoryFromBmi(double bmi) throws InvalidHealthDataException {
    if (bmi <= 0) {
        throw new InvalidHealthDataException("BMI must be greater than 0.");
    }
    if (bmi > 150) {
        throw new InvalidHealthDataException("BMI must be within a possible biological range [0-150].");
    }

    if (bmi < 16) {
        return BMICategory.SEVERE_THINNESS;
    } else if (bmi < 17) {
        return BMICategory.MODERATE_THINNESS;
    } else if (bmi < 18.5) {
        return BMICategory.MILD_THINNESS;
    } else if (bmi < 25) {
        return BMICategory.NORMAL_WEIGHT;
    } else if (bmi < 30) {
        return BMICategory.OVERWEIGHT;
    } else if (bmi < 35) {
        return BMICategory.OBESE_CLASS_I;
    } else if (bmi < 40) {
        return BMICategory.OBESE_CLASS_II;
    } else {
        return BMICategory.OBESE_CLASS_III;
    }
}

private String categoryToText(BMICategory category) {
    switch (category) {
        case SEVERE_THINNESS:
            return "Severe thinness";
        case MODERATE_THINNESS:
            return "Moderate thinness";
        case MILD_THINNESS:
            return "Mild thinness";
        case NORMAL_WEIGHT:
            return "Normal weight";
        case OVERWEIGHT:
            return "Overweight";
        case OBESE_CLASS_I:
            return "Obese Class I";
        case OBESE_CLASS_II:
            return "Obese Class II";
        case OBESE_CLASS_III:
            return "Obese Class III";
        default:
            throw new IllegalArgumentException("Unknown BMI category.");
    }
}

}
