package application.model;

public class Historie {
    private String historie;
    private WhiskyProdukt produkt;

    public Historie(String historie) {
        this.historie = historie;
    }

    public void setHistorie(String historie) {
        this.historie = historie;
    }

    public String getHistorie() {
        return historie;
    }
}
