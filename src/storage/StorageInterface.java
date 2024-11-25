package storage;

import application.model.Destillering;
import application.model.Korn;
import application.model.Maltbatch;
import application.model.Mark;

import java.util.List;

public interface StorageInterface {

    public void addMaltbatch(Maltbatch maltbatch);
    public void addDestillering(Destillering destillering);
    public void addMark(Mark mark);
    public void addKorn(Korn korn);

    public List<Maltbatch> getMaltbatches();
    public List<Destillering> getDestilleringer();
    public List<Mark> getMarker();
    public List<Korn> getKorn();
}
