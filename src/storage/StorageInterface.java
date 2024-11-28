package storage;

import application.model.*;
import javafx.collections.ObservableList;

import java.util.List;

public interface StorageInterface {

    public void addFad(Fad fad);

    public void addMaltbatch(Maltbatch maltbatch);

    public void addDestillering(Destillering destillering);

    public void addMark(Mark mark);

    public void addKorn(Korn korn);

    public void addLager(Lager lager);

    public void addWhiskyProdukt(WhiskyProdukt whiskyProdukt);


    public ObservableList<Fad> getFade();

    public ObservableList<Maltbatch> getMaltbatches();

    public ObservableList<Destillering> getDestilleringer();

    public List<Mark> getMarker();

    public List<Korn> getKorn();

    public ObservableList<Lager> getLager();

    public List<WhiskyProdukt> getWhiskyProdukt();

}
