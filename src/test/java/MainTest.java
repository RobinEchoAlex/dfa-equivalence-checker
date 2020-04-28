package test.java;

import main.entity.Dfa;
import main.algorithm.DfaAlgorithm;
import main.algorithm.Main;

public class MainTest {
    public static boolean equivalence(String path1,String path2 ) {
        Dfa dfa1 = Main.readDfa(path1);
        Dfa dfa2 = Main.readDfa(path2);
        DfaAlgorithm dfaAlgorithm = new DfaAlgorithm();
        return dfaAlgorithm.equivalence(dfa1,dfa2);
    }


}
