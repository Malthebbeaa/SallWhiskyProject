package storage;

import application.model.*;

import java.util.List;

public interface StorageInterface {

    public void addFad(Fad fad);

    public void addMaltbatch(Maltbatch maltbatch);

    public void addDestillering(Destillering destillering);

    public void addMark(Mark mark);

    public void addKorn(Korn korn);

    public List<Fad> getFade();

    public List<Maltbatch> getMaltbatches();

    public List<Destillering> getDestilleringer();

    public List<Mark> getMarker();

    public List<Korn> getKorn();
}
