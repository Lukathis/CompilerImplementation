package com.xc.chap4.Absyn;
import com.xc.chap4.Symbol.Symbol;
public class WhileExp extends Exp {
   public Exp test, body;
   public WhileExp(int p, Exp t, Exp b) {pos=p; test=t; body=b;}
}
