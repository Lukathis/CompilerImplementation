package com.xc.chap4.Absyn;
import com.xc.chap4.Symbol.Symbol;
public class DecList {
   public Dec head;
   public DecList tail;
   public DecList(Dec h, DecList t) {head=h; tail=t;}
}
