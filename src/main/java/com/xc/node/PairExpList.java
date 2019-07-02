package com.xc.node;

public class PairExpList extends ExpList {

    public Exp exp;
    public ExpList expList;

    public PairExpList(Exp exp, ExpList expList) {
        this.exp = exp;
        this.expList = expList;
    }
}
