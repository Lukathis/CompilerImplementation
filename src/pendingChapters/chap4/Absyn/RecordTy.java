package com.xc.chap4.Absyn;
import com.xc.chap4.Symbol.Symbol;
public class RecordTy extends Ty {
   public FieldList fields;
   public RecordTy(int p, FieldList f) {pos=p; fields=f;}
}   
