package healthcalc;

import java.awt.EventQueue;

import healthcalc.controller.HealthCalcController;
import healthcalc.vista.HealthCalcView;
import healthcalc.vista.HealthCalcVista;

public class MainGUI {

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    HealthCalcVista view = new HealthCalcVista();
                    new HealthCalcController(view);
                    view.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
