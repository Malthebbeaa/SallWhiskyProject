package storage;

import application.model.Destillering;
import application.model.Maltbatch;

import java.util.ArrayList;
import java.util.List;

public class Storage implements StorageInterface{

    private ArrayList<Maltbatch> maltbatches = new ArrayList<>();
    private ArrayList<Destillering> destilleringer = new ArrayList<>();


    public void addMaltbatch(Maltbatch maltbatch){
        maltbatches.add(maltbatch);
    }

    @Override
    public void addDestillering(Destillering destillering) {
        destilleringer.add(destillering);
    }


    //----------------------------------------------------------------------------
    //overvej om vi vil returnere den samme liste eller en kopi?
    public List<Maltbatch> getMaltbatches(){
        return maltbatches;
    }
    @Override
    public List<Destillering> getDestilleringer() {
        return destilleringer;
    }
}
