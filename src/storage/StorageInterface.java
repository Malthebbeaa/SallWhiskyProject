package storage;

import application.model.Destillering;
import application.model.Maltbatch;

import java.util.List;

public interface StorageInterface {

    public void addMaltbatch(Maltbatch maltbatch);
    public void addDestillering(Destillering destillering);

    public List<Maltbatch> getMaltbatches();
    public List<Destillering> getDestilleringer();
}
