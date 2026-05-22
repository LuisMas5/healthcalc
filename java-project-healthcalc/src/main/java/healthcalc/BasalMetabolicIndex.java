package healthcalc;

import healthcalc.exceptions.InvalidHealthDataException;

public interface BasalMetabolicIndex {

    double bmi(Person person)
            throws InvalidHealthDataException;

    BMICategory category(Person person)
            throws InvalidHealthDataException;
}