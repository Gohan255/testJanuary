package task1.model;

import task1.interfaces.IShape;


public abstract class Shape implements IShape {

    public Shape() {
    }

    @Override
    public String toString() {
        return "Shape{}";
    }
}
