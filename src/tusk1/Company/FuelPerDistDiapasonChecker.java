package tusk1.Company;

import tusk1.Company.AviaCompany.FlightInformation;
/*
 Diapason checker for loose of fuel per distance point
 */

public class FuelPerDistDiapasonChecker implements DiapasonChecker {

    @Override
    public boolean ifIn(Object our, Object lb, Object rb) {
        if (((Double) lb).doubleValue() <= ((FlightInformation) our).currentPlane.getFuelPerDist()
                && ((FlightInformation) our).currentPlane.getFuelPerDist() <= ((Double) rb).doubleValue()) {
            return true;
        }
        return false;
    }
};
