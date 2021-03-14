package com.mendes.domain;

import com.mendes.utils.GridPrinter;

public class ConwayGame{

    // attributes
    private Grid gameGrid;

    // constructor
    public ConwayGame(Grid grid) {
        this.gameGrid = grid;
    }

    // business method
    public void print() {
        GridPrinter printer = new GridPrinter(this.gameGrid);
        printer.print();
    }


}