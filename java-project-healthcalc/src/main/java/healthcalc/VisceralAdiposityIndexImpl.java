package healthcalc;

import healthcalc.exceptions.InvalidHealthDataException;

public class VisceralAdiposityIndexImpl implements VisceralAdiposityIndex {

    @Override
    public float vai(VisceralAdiposityData data) throws InvalidHealthDataException {
        Person p = data.getPerson();
        float cc = data.getWaistCircumference();
        float tg = data.getTriglycerides();
        float hdl = data.getHdl();

        double weight = p.weight();
        double height = p.height(); 
        Gender gender = p.gender();

        // 1. Validaciones extraídas de los tests originales de BDD:
        // Límite cintura: 200 ; Límite triglicéridos: 15 ; Límite HDL: 5
        if (cc <= 0 || cc > 200 || tg <= 0 || tg > 15 || hdl <= 0 || hdl > 5) {
            throw new InvalidHealthDataException("Valores de datos médicos fuera de límites.");
        }
        
        if (weight <= 0 || height <= 0) {
            throw new InvalidHealthDataException("Peso o altura inválidos en la persona.");
        }

        // 2. Cálculo del BMI implícito:
        double bmi = weight / (height * height);

        if (bmi <= 0 || bmi > 150) {
            throw new InvalidHealthDataException("BMI fuera del límite humano razonable.");
        }

        // 3. Fórmulas de VAI:
        double result;
        if (gender == Gender.MALE) {
            result = (cc / (39.68 + (1.88 * bmi))) * (tg / 1.03) * (1.31 / hdl);
        } else if (gender == Gender.FEMALE) {
            result = (cc / (36.58 + (1.89 * bmi))) * (tg / 0.81) * (1.52 / hdl);
        } else {
            throw new InvalidHealthDataException("Género no válido.");
        }

        return (float) result;
    }
}