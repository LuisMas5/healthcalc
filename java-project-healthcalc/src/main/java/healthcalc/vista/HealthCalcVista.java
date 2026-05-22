package healthcalc.vista;

import healthcalc.vista.HealthCalcView;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

public class HealthCalcVista extends JFrame implements HealthCalcView {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	private JTextField textFieldIMC;
	private JTextField textFieldTrigli;
	private JTextField textFieldHDL;
	private JTextField textFieldCircunf;
	private JComboBox<String> comboBoxVAISexo;
	private JButton btnCalcularVAI;
	private JLabel lblResultadoVAI;
	private JLabel lblIMC;
	private JLabel lblTrigliceridos;
	private JLabel lblNewCircunf;
	private JLabel lblHDL;

	private JPanel panel_6;
	private JLabel lblTituloVAI;

	private JTextField textField_pesoBMI;
	private JTextField textField_AlturaBMI;
	private JButton btnButton_CalcularBMI;
	private JLabel lblNewLabel_ResultadoBMI;
	private JLabel lblNewLabel_clasificacionBMI;

	private JPanel panel_2;
	private JPanel panel_3;
	private JPanel panel_4;
	private JLabel lblResultIBW;
	private JLabel lblAlturaIBW;
	private JLabel lblNewLabel_1;
	private JTextField textFieldAlturaIBW;
	private JComboBox<String> comboBoxIBWSexo;
	private JPanel panel_5;
	private JLabel lblNewLabel;
	private JButton btnCalcularIBW;



	public HealthCalcVista() {
		setTitle("HealthCalc");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 575, 302);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane, BorderLayout.CENTER);

		JPanel panelBMI = new JPanel();
		tabbedPane.addTab("Calcular BMI", null, panelBMI, null);
		panelBMI.setLayout(new BorderLayout(10, 10));
		panelBMI.setBorder(new EmptyBorder(20, 20, 20, 20));

		JPanel panelFormularioBMI = new JPanel();
		panelBMI.add(panelFormularioBMI, BorderLayout.NORTH);

		GridBagLayout gbl_panelFormularioBMI = new GridBagLayout();
		gbl_panelFormularioBMI.columnWidths = new int[] {120, 220, 0};
		gbl_panelFormularioBMI.rowHeights = new int[] {35, 35, 45, 0};
		gbl_panelFormularioBMI.columnWeights = new double[] {0.0, 1.0, Double.MIN_VALUE};
		gbl_panelFormularioBMI.rowWeights = new double[] {0.0, 0.0, 0.0, Double.MIN_VALUE};
		panelFormularioBMI.setLayout(gbl_panelFormularioBMI);

		JLabel lblPesoBMI = new JLabel("Peso (kg):");
		GridBagConstraints gbc_lblPesoBMI = new GridBagConstraints();
		gbc_lblPesoBMI.anchor = GridBagConstraints.EAST;
		gbc_lblPesoBMI.insets = new Insets(0, 0, 10, 10);
		gbc_lblPesoBMI.gridx = 0;
		gbc_lblPesoBMI.gridy = 0;
		panelFormularioBMI.add(lblPesoBMI, gbc_lblPesoBMI);

		textField_pesoBMI = new JTextField();
		GridBagConstraints gbc_textFieldPesoBMI = new GridBagConstraints();
		gbc_textFieldPesoBMI.insets = new Insets(0, 0, 10, 0);
		gbc_textFieldPesoBMI.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldPesoBMI.gridx = 1;
		gbc_textFieldPesoBMI.gridy = 0;
		panelFormularioBMI.add(textField_pesoBMI, gbc_textFieldPesoBMI);
		textField_pesoBMI.setColumns(10);

		JLabel lblAlturaBMI = new JLabel("Altura (m):");
		GridBagConstraints gbc_lblAlturaBMI = new GridBagConstraints();
		gbc_lblAlturaBMI.anchor = GridBagConstraints.EAST;
		gbc_lblAlturaBMI.insets = new Insets(0, 0, 10, 10);
		gbc_lblAlturaBMI.gridx = 0;
		gbc_lblAlturaBMI.gridy = 1;
		panelFormularioBMI.add(lblAlturaBMI, gbc_lblAlturaBMI);

		textField_AlturaBMI = new JTextField();
		GridBagConstraints gbc_textFieldAlturaBMI = new GridBagConstraints();
		gbc_textFieldAlturaBMI.insets = new Insets(0, 0, 10, 0);
		gbc_textFieldAlturaBMI.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldAlturaBMI.gridx = 1;
		gbc_textFieldAlturaBMI.gridy = 1;
		panelFormularioBMI.add(textField_AlturaBMI, gbc_textFieldAlturaBMI);
		textField_AlturaBMI.setColumns(10);

		JPanel panelBotonBMI = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
		GridBagConstraints gbc_panelBotonBMI = new GridBagConstraints();
		gbc_panelBotonBMI.gridwidth = 2;
		gbc_panelBotonBMI.fill = GridBagConstraints.HORIZONTAL;
		gbc_panelBotonBMI.gridx = 0;
		gbc_panelBotonBMI.gridy = 2;
		panelFormularioBMI.add(panelBotonBMI, gbc_panelBotonBMI);

		btnButton_CalcularBMI = new JButton("Calcular BMI");
		btnButton_CalcularBMI.setPreferredSize(new java.awt.Dimension(160, 35));
		panelBotonBMI.add(btnButton_CalcularBMI);

		JPanel panelResultadoBMI = new JPanel();
		panelResultadoBMI.setLayout(new BoxLayout(panelResultadoBMI, BoxLayout.Y_AXIS));
		panelResultadoBMI.setBorder(new EmptyBorder(25, 0, 0, 0));
		panelBMI.add(panelResultadoBMI, BorderLayout.CENTER);

		lblNewLabel_ResultadoBMI = new JLabel("BMI: -");
		lblNewLabel_ResultadoBMI.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_ResultadoBMI.setAlignmentX(Component.CENTER_ALIGNMENT);

		lblNewLabel_clasificacionBMI = new JLabel("Clasificación: -");
		lblNewLabel_clasificacionBMI.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_clasificacionBMI.setAlignmentX(Component.CENTER_ALIGNMENT);
		panelResultadoBMI.add(lblNewLabel_ResultadoBMI);
		panelResultadoBMI.add(lblNewLabel_clasificacionBMI);

		JPanel panelIBW = new JPanel();
		tabbedPane.addTab("Calcular IBW", null, panelIBW, null);
		panelIBW.setLayout(new BorderLayout(0, 0));

		panel_2 = new JPanel();
		panelIBW.add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(new BorderLayout(0, 0));

		panel_5 = new JPanel();
		panel_2.add(panel_5, BorderLayout.NORTH);

		lblNewLabel = new JLabel("¡Bienvenido a la calculadora de IBW!");
		lblNewLabel.setFont(new Font("Rockwell Nova", Font.PLAIN, 18));
		panel_5.add(lblNewLabel);

		panel_3 = new JPanel();
		panel_2.add(panel_3, BorderLayout.CENTER);
		panel_3.setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,}));

		lblAlturaIBW = new JLabel("Altura (cm): ");
		lblAlturaIBW.setFont(new Font("Rockwell Nova", Font.PLAIN, 12));
		panel_3.add(lblAlturaIBW, "8, 6, right, default");

		textFieldAlturaIBW = new JTextField();
		panel_3.add(textFieldAlturaIBW, "10, 6, fill, default");
		textFieldAlturaIBW.setColumns(10);

		lblNewLabel_1 = new JLabel("Sexo: ");
		lblNewLabel_1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
		lblNewLabel_1.setFont(new Font("Rockwell Nova", Font.PLAIN, 12));
		panel_3.add(lblNewLabel_1, "8, 12, right, default");

		comboBoxIBWSexo = new JComboBox<>();
		comboBoxIBWSexo.setModel(new DefaultComboBoxModel<>(new String[] {"Masculino", "Femenino"}));
		panel_3.add(comboBoxIBWSexo, "10, 12, fill, default");

		btnCalcularIBW = new JButton("Calcular IBW");
		btnCalcularIBW.setFont(new Font("Rockwell Nova", Font.PLAIN, 11));
		panel_3.add(btnCalcularIBW, "10, 16");

		panel_4 = new JPanel();
		panel_2.add(panel_4, BorderLayout.SOUTH);

		lblResultIBW = new JLabel("IBW: -");
		lblResultIBW.setFont(new Font("Rockwell Nova", Font.PLAIN, 14));
		panel_4.add(lblResultIBW);

		JPanel panelVAI = new JPanel();
		panelVAI.setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,}));

		JLabel lblVAISexo = new JLabel("Sexo");
		lblVAISexo.setFont(new Font("Rockwell Nova", Font.PLAIN, 12));
		panelVAI.add(lblVAISexo, "4, 4, left, default");

		comboBoxVAISexo = new JComboBox<>();
		comboBoxVAISexo.setFont(new Font("Rockwell Nova", Font.PLAIN, 11));
		comboBoxVAISexo.setModel(new DefaultComboBoxModel<>(new String[] {"Masculino", "Femenino"}));
		panelVAI.add(comboBoxVAISexo, "6, 4, fill, default");

		lblIMC = new JLabel("IMC");
		lblIMC.setFont(new Font("Rockwell Nova", Font.PLAIN, 12));
		lblIMC.setBackground(new Color(192, 192, 192));
		panelVAI.add(lblIMC, "8, 4, left, default");

		textFieldIMC = new JTextField();
		panelVAI.add(textFieldIMC, "10, 4, fill, default");
		textFieldIMC.setColumns(10);

		lblTrigliceridos = new JLabel("Triglicéridos (mmol/l)");
		lblTrigliceridos.setFont(new Font("Rockwell Nova", Font.PLAIN, 12));
		panelVAI.add(lblTrigliceridos, "4, 8, left, default");

		textFieldTrigli = new JTextField();
		panelVAI.add(textFieldTrigli, "6, 8, fill, default");
		textFieldTrigli.setColumns(10);

		lblHDL = new JLabel("HDL (mmol/l)");
		lblHDL.setFont(new Font("Rockwell Nova", Font.PLAIN, 12));
		panelVAI.add(lblHDL, "8, 8, left, default");

		textFieldHDL = new JTextField();
		panelVAI.add(textFieldHDL, "10, 8, fill, default");
		textFieldHDL.setColumns(10);

		lblNewCircunf = new JLabel("Circunferencia \r\nde cintura(cm)");
		lblNewCircunf.setFont(new Font("Rockwell Nova", Font.PLAIN, 12));
		panelVAI.add(lblNewCircunf, "4, 12, center, default");

		textFieldCircunf = new JTextField();
		panelVAI.add(textFieldCircunf, "6, 12, fill, default");
		textFieldCircunf.setColumns(10);

		btnCalcularVAI = new JButton("Calcular VAI");
		btnCalcularVAI.setFont(new Font("Rockwell Nova", Font.PLAIN, 11));
		panelVAI.add(btnCalcularVAI, "10, 12");

		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout(0, 0));
		tabbedPane.addTab("Calcular VAI", null, panel, null);

		panel_6 = new JPanel();
		panel.add(panel_6, BorderLayout.NORTH);

		lblTituloVAI = new JLabel("¡Bienvenido a la calculadora de VAI!");
		lblTituloVAI.setFont(new Font("Rockwell Nova", Font.PLAIN, 18));
		panel_6.add(lblTituloVAI);

		panel.add(panelVAI);

		JPanel panel_1 = new JPanel();
		panel.add(panel_1, BorderLayout.SOUTH);

		lblResultadoVAI = new JLabel("VAI: -");
		lblResultadoVAI.setFont(new Font("Rockwell Nova", Font.PLAIN, 14));
		panel_1.add(lblResultadoVAI);
	}

	public JTextField getTextFieldIMC() {
		return textFieldIMC;
	}

	public JTextField getTextFieldTrigli() {
		return textFieldTrigli;
	}

	public JTextField getTextFieldHDL() {
		return textFieldHDL;
	}

	public JTextField getTextFieldCircunf() {
		return textFieldCircunf;
	}

	public JComboBox<String> getComboBoxVAISexo() {
		return comboBoxVAISexo;
	}

	public JButton getBtnCalcularVAI() {
		return btnCalcularVAI;
	}

	public JLabel getLblResultadoVAI() {
		return lblResultadoVAI;
	}

	public JTextField getTextFieldPesoBMI() {
		return textField_pesoBMI;
	}

	public JTextField getTextFieldAlturaBMI() {
		return textField_AlturaBMI;
	}

	public JButton getBtnCalcularBMI() {
		return btnButton_CalcularBMI;
	}

	public JLabel getLblResultadoBMI() {
		return lblNewLabel_ResultadoBMI;
	}

	public JLabel getLblClasificacionBMI() {
		return lblNewLabel_clasificacionBMI;
	}

	public JComboBox<String> getcomboBoxIBWSexo() {
		return comboBoxIBWSexo;
	}

	public JLabel getLblResultadoIBW() {
		return lblResultIBW;
	}

	public JButton getBtnCalcularIBW() {
	    return btnCalcularIBW;
	}

	public JTextField getTextFieldAlturaIBW() {
		return textFieldAlturaIBW;
	}
}