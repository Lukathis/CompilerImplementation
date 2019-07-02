package com.xc.chap4.Absyn;
import com.xc.chap4.Symbol.Symbol;
public class VarExp extends Exp {
   public Var var;
   public VarExp(int p, Var v) {pos=p; var=v;}
}   
