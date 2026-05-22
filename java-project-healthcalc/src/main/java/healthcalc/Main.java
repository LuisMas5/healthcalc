package healthcalc;

import healthcalc.exceptions.InvalidHealthDataException;
import java.util.Scanner;
import java.util.Locale;
import healthcalc.hospital.HealthHospital;
import healthcalc.hospital.HealthHospitalAdapter;
import healthcalc.hospital.HealthHospitalStatsDecorator;
import healthcalc.hospital.ResultadoIMC;
import healthcalc.strategy.*;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in).useLocale(Locale.getDefault());
        HealthCalc healthCalc = HealthCalcImpl.getInstance();

        try {
            System.out.println("==========================================");
            System.out.println("   === ¡Bienvenido a HealthCalc! ===   ");
            System.out.println("==========================================");
            System.out.print("> Introduce tu sexo ('m' para hombre, 'f' para mujer): ");
            String sex = scanner.next().toLowerCase();

            System.out.print("> Introduce tu edad (ej. 25): ");
            int age = scanner.nextInt();

            System.out.print("> Introduce el peso en kilogramos (ej. 70,5): ");
            double weight = scanner.nextDouble();

            System.out.print("> Introduce la altura en metros (ej. 1,75): ");
            double heightMeters = scanner.nextDouble();
            double heightCm = heightMeters * 100;

            System.out.println("\n--- Datos adicionales para el Índice de Adiposidad Visceral (VAI) ---");
            System.out.print("> Introduce la circunferencia de la cintura en cm: ");
            double cc = scanner.nextDouble();

            System.out.print("> Introduce los triglicéridos (mmol/L): ");
            double tg = scanner.nextDouble();

            System.out.print("> Introduce el colesterol HDL (mmol/L): ");
            double hdl = scanner.nextDouble();

            System.out.println("\nCalculando métricas...");
            System.out.println("------------------------------------------");

            double bmi = healthCalc.bmi(weight, heightMeters);
            String classification = healthCalc.bmiClassification(bmi);
            double ibw = healthCalc.idealWeight(heightCm, sex);
            double vai = healthCalc.vai(sex, bmi, cc, tg, hdl);
            double bmr = healthCalc.basalMetabolicRate(weight, heightCm, age, sex);

            System.out.printf("1. IMC (BMI): %.2f kg/m2\n", bmi);
            System.out.println("   Clasificación: " + classification);
            System.out.printf("2. Peso Ideal (IBW - Lorentz): %.2f kg\n", ibw);
            System.out.printf("3. Índice de Adiposidad Visceral (VAI): %.2f\n", vai);
            System.out.printf("4. Tasa Metabólica Basal (BMR): %.2f kcal/día\n", bmr);
            System.out.println("------------------------------------------");

            System.out.println("\n=== Prueba Adapter Hospital ===");

            HealthHospital hospitalCalc = new HealthHospitalAdapter();

            ResultadoIMC resultadoIMC = hospitalCalc.indiceMasaCorporal((float) heightMeters, (int) (weight * 1000));
            int pesoIdealHospital = hospitalCalc.pesoCorporalIdeal(sex.charAt(0), (float) heightMeters);

            System.out.println(resultadoIMC);
            System.out.println("Peso corporal ideal hospital: " + pesoIdealHospital + " kg");

            System.out.println("================================");

            System.out.println("\n=== Prueba Decorator HealthStats ===");

            HealthHospital hospitalBase = new HealthHospitalAdapter();
            HealthHospitalStatsDecorator hospitalConStats = new HealthHospitalStatsDecorator(hospitalBase);

            ResultadoIMC resultadoStats = hospitalConStats.indiceMasaCorporal(
                    (float) heightMeters,
                    (int) (weight * 1000)
            );

            hospitalConStats.pesoCorporalIdeal(
                    sex.charAt(0),
                    (float) heightMeters
            );

            System.out.println(resultadoStats);

            System.out.printf("Altura media: %.2f m\n", hospitalConStats.alturaMedia());
            System.out.printf("Peso medio: %.2f kg\n", hospitalConStats.pesoMedio());
            System.out.printf("IMC medio: %.2f\n", hospitalConStats.imcMedio());
            System.out.println("Número de hombres: " + hospitalConStats.numSexoH());
            System.out.println("Número de mujeres: " + hospitalConStats.numSexoM());
            System.out.println("Número total de pacientes: " + hospitalConStats.numTotalPacientes());

            System.out.println("================================");
            System.out.println("\n=== Prueba patrón Strategy ===");
            healthcalc.strategy.HealthContext context = new healthcalc.strategy.HealthContext();

            // 1. Versión Europea en Español (Metros y gramos)
            System.out.println("[Configurando Estrategia Europea - Idioma: ES]");
            context.setStrategy(new healthcalc.strategy.EuropeanStrategy());
            // Usamos gramos (weight * 1000)
            context.executeStrategy(heightMeters, weight * 1000, "es");

            // 2. Versión Americana en inglés (Pies y libras)
            // Ejemplo: 1.80m son ~5.91 pies | 75kg son ~165.35 lbs
            System.out.println("\n[Configurando Estrategia Americana - Idioma: EN]");
            context.setStrategy(new healthcalc.strategy.AmericanStrategy());
            double heightFeet = heightMeters * 3.28084;
            double weightLbs = weight * 2.20462;
            context.executeStrategy(heightFeet, weightLbs, "en");

            System.out.println("=============================================================");

        } catch (InvalidHealthDataException e) {
            System.err.println("\n[ERROR DE DATOS]: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("\n[ERROR]: Entrada inválida. Asegúrate de usar el formato numérico correcto.");
        } finally {
            scanner.close();
        }
    }
}
