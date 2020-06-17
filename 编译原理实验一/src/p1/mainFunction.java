package p1;
import java.io.*;
public class mainFunction
{   //exeTest et=new exeTest();


    public static void main(String[] args)
    {
        identify id=new identify();
        String txt=new String();
        try {
        BufferedReader in = new BufferedReader(new FileReader("C:\\Users\\yangd\\Desktop\\text.txt"));

        while ((txt = in.readLine()) != null) {

            id.goahead(txt);
        }
        
            id.goahead(txt);
    } catch (IOException e) {
    }
   }
}
