package application.model;

public class Mark {

    private String markNavn;
    private boolean økologisk;

    public Mark(String markNavn, boolean økologisk) {
        this.markNavn = markNavn;
        this.økologisk = økologisk;
    }

    @Override
    public String toString() {
        return markNavn + (økologisk? " økologisk mark" : " ikke økologisk mark");
    }
}
