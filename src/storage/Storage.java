package storage;

import application.model.Maltbatch;

import java.util.ArrayList;

public class Storage {

    private ArrayList<Maltbatch> maltbatches = new ArrayList<>();

    public void addMaltbatch(Maltbatch maltbatch){
        maltbatches.add(maltbatch);
    }
}
