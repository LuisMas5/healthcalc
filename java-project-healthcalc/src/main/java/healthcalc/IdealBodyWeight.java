package healthcalc;

import healthcalc.exceptions.InvalidHealthDataException;

public interface IdealBodyWeight {

    double idealWeight(Person person) throws InvalidHealthDataException;
}