package com.xc.node;

public class OpExp extends Exp {

    public Exp left;
    public Exp right;
    public Op op;

    public OpExp(Exp left, Op op, Exp right) {
        this.left = left;
        this.op = op;
        this.right = right;
    }

}
