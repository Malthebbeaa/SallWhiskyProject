package storage;

import application.model.Destillering;
import application.model.Fad;
import application.model.Maltbatch;

import java.util.List;

public interface StorageInterface {

    public void addFad(Fad fad);

    public void addMaltbatch(Maltbatch maltbatch);
    public void addDestillering(Destillering destillering);

    public List<Fad> getFade();
    public List<Maltbatch> getMaltbatches();
    public List<Destillering> getDestilleringer();
}
