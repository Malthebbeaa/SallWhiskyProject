package application.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Påfyldning {
    private LocalDate påfyldningsDato;
    private double literPåfyldt;
    private List<Fad> fade;

    //overvej forbindelsen i UML om den skal være tvunget til fad?
    public Påfyldning(LocalDate påfyldningsDato, double literPåfyldt) {
        this.påfyldningsDato = påfyldningsDato;
        this.literPåfyldt = literPåfyldt;
        this.fade = new ArrayList<>();
    }

    //flyt evt op i contructor
    //implementer observer pattern?
    public void fyldPåFad(Fad fad){
        fade.add(fad);
        fad.setFyldt(true);
    }

    public LocalDate getPåfyldningsDato() {
        return påfyldningsDato;
    }

    public double getLiterPåfyldt() {
        return literPåfyldt;
    }

    public List<Fad> getFade() {
        return fade;
    }
}
