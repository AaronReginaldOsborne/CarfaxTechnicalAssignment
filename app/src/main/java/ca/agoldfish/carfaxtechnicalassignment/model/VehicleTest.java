package ca.agoldfish.carfaxtechnicalassignment.model;

import java.util.List;

public class VehicleTest {

    private String badge ="";
    private Dealer dealer;

    public Dealer getDealer() {
        return dealer;
    }

    public void setDealer(Dealer dealer) {
        this.dealer = dealer;
    }

    public String getBadge() {
        return badge;
    }

    public void setBadge(String badge) {
        this.badge = badge;
    }

    public VehicleTest() {
    }

    public VehicleTest(String badge) {
        this.badge = badge;
    }
}
