package com.xc.chap4.Absyn;
import com.xc.chap4.Symbol.Symbol;
public class ExpList {
   public Exp head;
   public ExpList tail;
   public ExpList(Exp h, ExpList t) {head=h; tail=t;}
}
