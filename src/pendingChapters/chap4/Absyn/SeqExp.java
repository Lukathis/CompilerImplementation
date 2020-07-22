package com.xc.chap4.Absyn;
import com.xc.chap4.Symbol.Symbol;
public class SeqExp extends Exp {
   public ExpList list;
   public SeqExp(int p, ExpList l) {pos=p; list=l;}
}
