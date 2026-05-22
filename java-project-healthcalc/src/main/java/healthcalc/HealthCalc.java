package healthcalc;

import healthcalc.exceptions.InvalidHealthDataException;

public interface HealthCalc {
	
	/**
	 * Calculate the BMI classification of a person.
	 * The BMI classification is based on the following table:
	 * - Severe thinness: BMI < 16
	 * - Moderate thinness: 16 <= BMI < 17
	 * - Mild thinness: 17 <= BMI < 18.5
	 * - Normal weight: 18.5 <= BMI < 25
	 * - Overweight: 25 <= BMI < 30
	 * - Obese Class I: 30 <= BMI < 35
	 * - Obese Class II: 35 <= BMI < 40
	 * - Obese Class III: BMI >= 40
	 *
	 * @param bmi	Body Mass Index of the person (kg/m2).
	 * @return	 	The BMI classification of the person.
	 * @throws Exception
	 */
	public String bmiClassification(double bmi) throws InvalidHealthDataException;
	public double bmi(double weight, double height) throws InvalidHealthDataException;

	/**
 * Calculate the Visceral Adiposity Index (VAI) of a person.
 * 
 * The VAI is calculated using different formulas depending on sex:
 * 
 * For men:
 * VAI = [CC / (39.68 + (1.88 * BMI))] × (TG / 1.03) × (1.31 / HDL)
 * 
 * For women:
 * VAI = [CC / (36.58 + (1.89 * BMI))] × (TG / 0.81) × (1.52 / HDL)
 * 
 * Where:
 * - sex must be "m" (male) or "f" (female)
 * - BMI is Body Mass Index (kg/m2)
 * - CC is Waist Circumference (cm)
 * - TG is Triglycerides (mmol/L)
 * - HDL is HDL cholesterol (mmol/L)
 * 
 * @param sex   Sex of the person ("m" or "f")
 * @param bmi   Body Mass Index (kg/m2)
 * @param cc    Waist circumference (cm)
 * @param tg    Triglycerides (mmol/L)
 * @param hdl   HDL cholesterol (mmol/L)
 * @return      The Visceral Adiposity Index (VAI)
 * @throws InvalidHealthDataException if parameters are invalid
 */
public double vai(String sex, double bmi, double cc, double tg, double hdl) throws InvalidHealthDataException;

    /**
     * Estimate the ideal body weight (IBW) for a person based on height and sex.
     *
     * Implementation uses the Lorentz formula which works directly with height in
     * centimetres and is straightforward for clinicians:
     *
     * <pre>
     *  Male   : IBW = (height_cm - 100) - (height_cm - 150)/4
     *  Female : IBW = (height_cm - 100) - (height_cm - 150)/2
     * </pre>
     *
     * @param heightCm Height in centimetres. Must be within sensible human limits.
     * @param sex      "m" or "f" (case‑sensitive) for male/female.
     * @return         The ideal weight in kilograms.
     * @throws InvalidHealthDataException when any argument is out of range.
     */
    public double idealWeight(double heightCm, String sex) throws InvalidHealthDataException;

    /**
     * Calculate the basal metabolic rate (BMR) using a common clinical formula.
     *
     * This implementation opts for the Mifflin‑St Jeor equation, which is widely
     * used today:
     *
     * <pre>
     *  BMR(male)   = (10 × weight_kg) + (6.25 × height_cm) - (5 × age) + 5
     *  BMR(female) = (10 × weight_kg) + (6.25 × height_cm) - (5 × age) - 161
     * </pre>
     *
     * @param weightKg  Current weight in kilograms (soft/hard limits enforced).
     * @param heightCm  Height in centimetres.
     * @param ageYears  Age in years (positive integer, reasonable upper bound).
     * @param sex       "m" or "f" for male/female.
     * @return          Estimated BMR in kilocalories/day.
     * @throws InvalidHealthDataException when parameters are invalid.
     */
    public double basalMetabolicRate(double weightKg, double heightCm, int ageYears, String sex) throws InvalidHealthDataException;

    public double bmi(Person person) throws InvalidHealthDataException;
    public BMICategory category(Person person) throws InvalidHealthDataException;
    public double idealWeight(Person person) throws InvalidHealthDataException;



}

