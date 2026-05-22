package healthcalc.controller;

import java.awt.Color;

import healthcalc.HealthCalcImpl;
import healthcalc.exceptions.InvalidHealthDataException;
import healthcalc.vista.HealthCalcView;

import healthcalc.BMICategory;
import healthcalc.Gender;
import healthcalc.HealthPerson;
import healthcalc.Person;

public class HealthCalcController {

    private final HealthCalcView view;
    private final HealthCalcImpl model;

    public HealthCalcController(HealthCalcView view) {
        this.view = view;
        this.model = HealthCalcImpl.getInstance();
        initController();
    }

    private void initController() {
        view.getBtnCalcularBMI().addActionListener(e -> calcularBMI());
        view.getBtnCalcularVAI().addActionListener(e -> calcularVAI());
        view.getBtnCalcularIBW().addActionListener(e -> calcularIBW());
    }

        private void calcularBMI() {
        try {
            double peso = Double.parseDouble(view.getTextFieldPesoBMI().getText());
            double altura = Double.parseDouble(view.getTextFieldAlturaBMI().getText());

            Person person = new HealthPerson(peso, altura, Gender.MALE, 1);

            double resultado = model.bmi(person);
            BMICategory categoria = model.category(person);

            view.getLblResultadoBMI().setForeground(new Color(0, 128, 0));
            view.getLblClasificacionBMI().setForeground(new Color(0, 128, 0));

            view.getLblResultadoBMI().setText(String.format("BMI: %.2f", resultado));
            view.getLblClasificacionBMI().setText("Clasificación: " + formatBmiCategory(categoria));

        } catch (InvalidHealthDataException e) {
            view.getLblResultadoBMI().setForeground(Color.RED);
            view.getLblClasificacionBMI().setForeground(Color.RED);

            view.getLblResultadoBMI().setText("BMI: -");
            view.getLblClasificacionBMI().setText(e.getMessage());

        } catch (NumberFormatException e) {
            view.getLblResultadoBMI().setForeground(Color.RED);
            view.getLblClasificacionBMI().setForeground(Color.RED);

            view.getLblResultadoBMI().setText("BMI: -");
            view.getLblClasificacionBMI().setText("Error: Invalid values");
        }
    }

    private void calcularVAI() {
        try {
            String sexoSeleccionado = view.getComboBoxVAISexo().getSelectedItem().toString();
            String sex;

            if (sexoSeleccionado.equals("Masculino")) {
                sex = "m";
            } else {
                sex = "f";
            }

            double bmi = Double.parseDouble(view.getTextFieldIMC().getText());
            double trigl = Double.parseDouble(view.getTextFieldTrigli().getText());
            double hdl = Double.parseDouble(view.getTextFieldHDL().getText());
            double cc = Double.parseDouble(view.getTextFieldCircunf().getText());

            double resultado = model.vai(sex, bmi, cc, trigl, hdl);
            view.getLblResultadoVAI().setForeground(new Color(0, 128, 0));
            view.getLblResultadoVAI().setText(String.format("VAI: %.2f", resultado));

        } catch (InvalidHealthDataException e) {
            view.getLblResultadoVAI().setForeground(Color.RED);
            view.getLblResultadoVAI().setText(e.getMessage());

        } catch (NumberFormatException e) {
            view.getLblResultadoVAI().setForeground(Color.RED);
            view.getLblResultadoVAI().setText("Error: Invalid values");
        }
    }

    private void calcularIBW() {
        try {
            String sexoSeleccionado = view.getcomboBoxIBWSexo().getSelectedItem().toString();
            String sex;

            if (sexoSeleccionado.equals("Masculino")) {
                sex = "m";
            } else {
                sex = "f";
            }

            double altura = Double.parseDouble(view.getTextFieldAlturaIBW().getText());

            double resultado = model.idealWeight(altura, sex);
            view.getLblResultadoIBW().setForeground(new Color(0, 128, 0));
            view.getLblResultadoIBW().setText(String.format("IBW: %.2f", resultado));

        } catch (InvalidHealthDataException e) {
            view.getLblResultadoIBW().setForeground(Color.RED);
            view.getLblResultadoIBW().setText(e.getMessage());

        } catch (NumberFormatException e) {
            view.getLblResultadoIBW().setForeground(Color.RED);
            view.getLblResultadoIBW().setText("Error: Invalid values");
        }
    }
    private String formatBmiCategory(BMICategory category) {
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
                return category.toString();
        }
    }
}
