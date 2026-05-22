package healthcalc.hospital;

public class ResultadoIMC {

    private final double imc;
    private final String clasificacion;

    public ResultadoIMC(float imc, String clasificacion) {
        this.imc = imc;
        this.clasificacion = clasificacion;
    }

    public double getImc() {
        return imc;
    }

    public String getClasificacion() {
        return clasificacion;
    }

    @Override
    public String toString() {
        return "IMC: " + imc + " | Clasificación: " + clasificacion;
    }
}
