package storage;

import application.model.Destillering;
import application.model.Fad;
import application.model.Maltbatch;

import java.util.ArrayList;
import java.util.List;

public class Storage implements StorageInterface {

    private ArrayList<Fad> fade = new ArrayList<>();
    private ArrayList<Maltbatch> maltbatches = new ArrayList<>();
    private ArrayList<Destillering> destilleringer = new ArrayList<>();


    @Override
    public void addFad(Fad fad) {
        fade.add(fad);
    }

    public void addMaltbatch(Maltbatch maltbatch) {
        maltbatches.add(maltbatch);
    }

    @Override
    public void addDestillering(Destillering destillering) {
        destilleringer.add(destillering);
    }



    //----------------------------------------------------------------------------
    //overvej om vi vil returnere den samme liste eller en kopi?
    @Override
    public List<Fad> getFade() {
        return fade;
    }

    public List<Maltbatch> getMaltbatches() {
        return maltbatches;
    }

    @Override
    public List<Destillering> getDestilleringer() {
        return destilleringer;
    }
}
