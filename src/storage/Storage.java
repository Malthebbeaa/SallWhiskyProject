package storage;

import application.model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

public class Storage implements StorageInterface{

    private ArrayList<Fad> fade = new ArrayList<>();
    private ArrayList<FadLeverandør> fadLeverandører = new ArrayList<>();
    private ObservableList<Maltbatch> maltbatches = FXCollections.observableArrayList();
    private ArrayList<Destillering> destilleringer = new ArrayList<>();
    private ArrayList<Korn> korns = new ArrayList<>();
    private ArrayList<Mark> marker = new ArrayList<>();
    private ObservableList<Lager> lagre = FXCollections.observableArrayList();
    private ArrayList<WhiskyProdukt> whiskyProdukter = new ArrayList<>();


    @Override
    public void addFad(Fad fad) {
        fade.add(fad);
    }

    public void addMaltbatch(Maltbatch maltbatch){
        maltbatches.add(maltbatch);
    }

    @Override
    public void addDestillering(Destillering destillering) {
        destilleringer.add(destillering);
    }

    public void addMark(Mark mark){
        marker.add(mark);
    }

    public void addKorn(Korn korn){
        korns.add(korn);
    }

    @Override
    public void addLager(Lager lager) {
        lagre.add(lager);
    }

    @Override
    public void addWhiskyProdukt(WhiskyProdukt whiskyProdukt) {
        whiskyProdukter.add(whiskyProdukt);
    }

    @Override
    public void addFadleverandør(FadLeverandør fadLeverandør) {
        fadLeverandører.add(fadLeverandør);
    }

    //----------------------------------------------------------------------------
    //overvej om vi vil returnere den samme liste eller en kopi?
    @Override
    public List<Fad> getFade() {
        return fade;
    }

    @Override
    public List<FadLeverandør> getFadleverandør() {
        return fadLeverandører;
    }

    public ObservableList<Maltbatch> getMaltbatches(){
        return FXCollections.unmodifiableObservableList(maltbatches);
    }
    @Override
    public List<Destillering> getDestilleringer() {
        return destilleringer;
    }

    @Override
    public List<Mark> getMarker() {
        return marker;
    }

    @Override
    public List<Korn> getKorn() {
        return korns;
    }

    @Override
    public ObservableList<Lager> getLager() {
        return FXCollections.unmodifiableObservableList(lagre);
    }

    @Override
    public List<WhiskyProdukt> getWhiskyProdukt() {
        return whiskyProdukter;
    }

}
