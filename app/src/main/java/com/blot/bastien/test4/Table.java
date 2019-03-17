package com.blot.bastien.test4;

import java.util.ArrayList;

public class Table {
    protected ArrayList<Smile> smiles;

    public Table() {
        smiles = new ArrayList<>();
    }

    public void addSmile(Smile smile) {
        smiles.add(smile);
    }

    @Override
    public String toString() {
        return "Table{" +
                "smiles=" + smiles +
                '}';
    }

    public ArrayList<Smile> getSmiles() {
        return smiles;
    }

    public int getSmileIndex (Smile smiley) {
        for (int i = 0; i < smiles.size(); i++) {
            if (smiles.get(i).getImages() == smiley.getImages()) return i;

        }
        return 0;
    }
}
