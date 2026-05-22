package healthcalc;

public class HealthPerson implements Person {

    private final double weight;
    private final double height;
    private final Gender gender;
    private final int age;

    public HealthPerson(double weight, double height, Gender gender, int age) {
        this.weight = weight;
        this.height = height;
        this.gender = gender;
        this.age = age;
    }

    @Override
    public double weight() {
        return weight;
    }

    @Override
    public double height() {
        return height;
    }

    @Override
    public Gender gender() {
        return gender;
    }

    @Override
    public int age() {
        return age;
    }
}