package healthcalc.vista;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

public interface HealthCalcView {

    JTextField getTextFieldPesoBMI();

    JTextField getTextFieldAlturaBMI();

    JButton getBtnCalcularBMI();

    JLabel getLblResultadoBMI();

    JLabel getLblClasificacionBMI();

    JTextField getTextFieldIMC();

    JTextField getTextFieldTrigli();

    JTextField getTextFieldHDL();

    JTextField getTextFieldCircunf();

    JComboBox<String> getComboBoxVAISexo();

    JButton getBtnCalcularVAI();

    JLabel getLblResultadoVAI();

    JComboBox<String> getcomboBoxIBWSexo();

    JLabel getLblResultadoIBW();

    JButton getBtnCalcularIBW();

    JTextField getTextFieldAlturaIBW();
}
