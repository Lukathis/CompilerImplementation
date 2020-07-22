package com.xc.chap4.Absyn;
import com.xc.chap4.Symbol.Symbol;
public class ArrayTy extends Ty {
   public Symbol typ;
   public ArrayTy(int p, Symbol t) {pos=p; typ=t;}
}
