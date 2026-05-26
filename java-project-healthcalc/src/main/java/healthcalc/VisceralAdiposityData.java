package healthcalc;

public class VisceralAdiposityData {
    private Person person;
    private float waistCircumference;
    private float triglycerides;
    private float hdl;

    public VisceralAdiposityData(Person person, float waistCircumference, float triglycerides, float hdl) {
        this.person = person;
        this.waistCircumference = waistCircumference;
        this.triglycerides = triglycerides;
        this.hdl = hdl;
    }

    // Getters
    public Person getPerson() { return person; }
    public float getWaistCircumference() { return waistCircumference; }
    public float getTriglycerides() { return triglycerides; }
    public float getHdl() { return hdl; }
}
