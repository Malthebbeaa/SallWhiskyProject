package application.model;

public class FadLeverandør {
    String navn;
    String land;

    public FadLeverandør(String navn, String land) {
        this.navn = navn;
        this.land = land;
    }

    @Override
    public String toString() {
        return navn + ", " + land;
    }

    public static String getLand() {
        return land;
    }

    public void setLand(String land) {
        this.land = land;
    }
}
