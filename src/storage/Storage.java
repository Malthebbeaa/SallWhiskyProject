package storage;

import application.model.Destillering;
import application.model.Korn;
import application.model.Maltbatch;
import application.model.Mark;

import java.util.ArrayList;
import java.util.List;

public class Storage implements StorageInterface{

    private ArrayList<Maltbatch> maltbatches = new ArrayList<>();
    private ArrayList<Destillering> destilleringer = new ArrayList<>();
    private ArrayList<Korn> korns = new ArrayList<>();
    private ArrayList<Mark> marker = new ArrayList<>();


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


    //----------------------------------------------------------------------------
    //overvej om vi vil returnere den samme liste eller en kopi?
    public List<Maltbatch> getMaltbatches(){
        return maltbatches;
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

}
