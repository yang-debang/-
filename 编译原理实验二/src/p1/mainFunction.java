package p1;
import java.io.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class mainFunction {
    public static void main(String[] args) {
        firstVt firstvt=new firstVt();
        lastVt lastvt=new lastVt();
        CharacterPrecedenceTable cpt=new CharacterPrecedenceTable();
        equals eq=new equals();
        try {
            BufferedReader in = new BufferedReader(new FileReader("C:\\Users\\yangd\\Desktop\\text.txt"));
            String txt = new String();
            while ((txt = in.readLine()) != null) {
                 firstvt.run(txt.trim());
                 lastvt.run(txt.trim());
                 cpt.run(txt.trim());
                 eq.run(txt.trim());
                 System.out.println(txt.trim());
            }
               //vt.run(txt.trim());

        } catch (
                IOException e) {
        }
        cpt.init();
        System.out.println("开始构造FirstVt集：");
        firstvt.init();
        firstvt.analyse();
        firstvt.recursive();
        firstvt.printBiogroup();
        cpt.reception(firstvt.send(),1);
        System.out.println("开始构造LastVt集：");
        lastvt.init();
        lastvt.analyse();
        lastvt.recursive();
        lastvt.printBiogroup();
        cpt.reception(lastvt.send(),2);
        cpt.analyse();
        cpt.print();
        eq.init();
        eq.reception(cpt.send());
        //eq.begin();
        eq.mainAlgorithm();
        //eq.print();
    }
}
