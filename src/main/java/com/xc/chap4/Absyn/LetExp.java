package com.xc.chap4.Absyn;
import com.xc.chap4.Symbol.Symbol;
public class LetExp extends Exp {
   public DecList decs;
   public Exp body;
   public LetExp(int p, DecList d, Exp b) {pos=p; decs=d; body=b;}
}
