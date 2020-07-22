package com.xc.chap1;

class interp {
    /**
     * This function that "interprets" a program in this language
     * It used two mutually recursive functions interpStm and interpExp
     * Represent a "table", mapping identifiers to the integer values assigned to them
     * @param s
     */
    static void interp(Stm s) {
        /* you write this part */
        interpStm(s, null);
    }

    static Table interpStm(Stm stm, Table t) {
        if (stm instanceof CompoundStm) {
            CompoundStm compoundStm = (CompoundStm)stm;
            Table cst1 = interpStm(compoundStm.stm1, t);
            Table cst2 = interpStm(compoundStm.stm2, cst1);
            return cst2;
        } else if (stm instanceof AssignStm) {
            AssignStm assignStm = (AssignStm)stm;
            IntAndTable ast1 = interpExp(assignStm.exp, t);
            return new Table(assignStm.id, ast1.i, ast1.t);
        } else if (stm instanceof PrintStm) {
            PrintStm printStm = (PrintStm)stm;
            return print(printStm.exps, t);
        }
        return null;
    }

    static Table print(ExpList expList, Table t) {
        if (expList instanceof PairExpList) {
            PairExpList pairExpList = (PairExpList)expList;
            IntAndTable pet1 = interpExp(pairExpList.head, t);
            System.out.print(pet1.i + " ");
            return print(pairExpList.tail, pet1.t);
        } else if (expList instanceof LastExpList) {
            LastExpList lastExpList = (LastExpList)expList;
            IntAndTable leTale = interpExp(lastExpList.head, t);
            System.out.print(leTale.i + " ");
            return leTale.t;
        }
        throw new UnsupportedOperationException("ExpList should either be Pair or Last");
    }

    static IntAndTable interpExpList(ExpList expList, Table t) {
        if (expList instanceof PairExpList) {
            PairExpList pairExpList = (PairExpList)expList;
            IntAndTable pet1 = interpExp(pairExpList.head, t);
            IntAndTable pet2 = interpExpList(pairExpList.tail, pet1.t);
            return pet2;
        } else if (expList instanceof LastExpList) {
            LastExpList lastExpList = (LastExpList)expList;
            IntAndTable let1 = interpExp(lastExpList.head, t);
            return let1;
        }
        throw new UnsupportedOperationException("ExpList should either be Pair or Last");
    }

    static IntAndTable interpExp(Exp exp, Table t) {
        if (exp instanceof IdExp) {
            return new IntAndTable(t.lookup(((IdExp)exp).id), t);
        } else if (exp instanceof NumExp) {
            return new IntAndTable(((NumExp)exp).num, t);
        } else if (exp instanceof OpExp) {
            OpExp opExp = (OpExp)exp;
            IntAndTable oet1 = interpExp(opExp.left, t);
            IntAndTable oet2 = interpExp(opExp.right, oet1.t);
            switch (opExp.oper) {
                case 1:
                    return new IntAndTable(oet1.i + oet2.i, oet2.t);
                case 2:
                    return new IntAndTable(oet1.i - oet2.i, oet2.t);
                case 3:
                    return new IntAndTable(oet1.i * oet2.i, oet2.t);
                case 4:
                    return new IntAndTable(oet1.i / oet2.i, oet2.t);
            }
        } else if (exp instanceof EseqExp) {
            EseqExp eseqExp = (EseqExp)exp;
            Table eseqTable1 = interpStm(eseqExp.stm, t);
            IntAndTable eseqTable2 = interpExp(eseqExp.exp, eseqTable1);
            return eseqTable2;
        }
        throw new UnsupportedOperationException("Exp should be IdExp/NumExp/OpExp/EseqExp");
    }


    /**
     * This function returns max number of arguments of any print statement
     * within any subexpression of a given statement, e.g. maxargs(prog) is 2
     * @param s
     * @return max number of arguments for print statements inside s
     */
    static int maxargs(Stm s) {
        /* you write this part */
        /**
         * There're 3 kinds of statements
         * 1. Compound statement
         * 2. Assign statement
         * 3. Print statement
         */
        if (s instanceof CompoundStm) {
            CompoundStm cs = (CompoundStm)s;
            int c1 = maxargs(cs.stm1);
            int c2 = maxargs(cs.stm2);
            return c1 > c2 ? c1 : c2;
        } else if (s instanceof  AssignStm) {
            AssignStm as = (AssignStm)s;
            return maxargs(as.exp);
        } else if (s instanceof  PrintStm) {
            PrintStm ps = (PrintStm)s;
            return maxargs(ps.exps, 0);
        }

        throw new UnsupportedOperationException("Unsupported statement");
    }

    static int maxargs(Exp e) {
        /**
         * There're 4 kinds of Exp
         * 1. IdExp
         * 2. NumExp
         * 3. OpExp
         * 4. EseqExp
         */
        if (e instanceof IdExp) {
            return 0;
        } else if (e instanceof NumExp) {
            return 0;
        } else if (e instanceof OpExp) {
            OpExp opExp = (OpExp)e;
            int c1 = maxargs(opExp.left);
            int c2 = maxargs(opExp.right);
            return Math.max(c1, c2);
        } else if (e instanceof EseqExp) {
            EseqExp eseqExp = (EseqExp)e;
            int c1 = maxargs(eseqExp.stm);
            int c2 = maxargs(eseqExp.exp);
            return Math.max(c1, c2);
        }
        throw new UnsupportedOperationException("Unsupported Expression");
    }

    static int maxargs(ExpList expList, int cnt) {
        /**
         * There're 2 kinds of ExpList
         * 1. PairExpList
         * 2. LastExpList
         */
        if (expList instanceof PairExpList) {
            PairExpList pairExpList = (PairExpList)expList;
            return maxargs(pairExpList.tail, cnt + 1);
        } else if (expList instanceof LastExpList) {
            return cnt + 1;
        }
        throw new UnsupportedOperationException("Unsupported ExpList");
    }

    public static void main(String args[]) throws java.io.IOException {
        System.out.println(maxargs(prog.prog)); // should be 2
        interp(prog.prog);
    }
}
