package p1;
import java.io.*;
public class exeTest {
    String[] markChar=new String[10];
    String[] keychar=new String[10];
    String[] num=new String[10];
    String[] opchar=new String[10];
    String[] limchar=new String[5];

    public void goahead ()
    {
        keychar[0]="int";keychar[1]="char";keychar[2]="printf";keychar[3]="if";keychar[4]="else";
        keychar[5]="while";keychar[6]="void";

        opchar[0]="+";opchar[1]="-";opchar[2]="*";opchar[3]="/";opchar[4]="=";opchar[5]="&";
        opchar[6]="\\";opchar[7]="%";
        limchar[0]=";";limchar[1]="{";limchar[2]="}";

    }
     public void searchInmarkchar(String str)
     { int key=0;
         for( int i=0;i<10;i++)
         {
             if (str.equals(markChar[i]))
             {
                 System.out.println("(" + "1" + "," + i + ")");
                 key=1;
                 break;
             }
         }

         if(key==0)
         {

                 for (int j = 0; j < 10; j++)
                 {
                     if (markChar[j] == null)
                     {
                         markChar[j] = str;
                         System.out.println(markChar[j]+"新增为"+"("+"1"+","+j+")");
                         break;
                     }
                     else{
                           //System.out.println("标识符表满了");
                         }
                 }

         }
     }
     public void print()
     {
        for(int i=0;i<markChar.length;i++)
        {
            System.out.println(markChar[i]);
        }
     }
     public void searchInnum(String str){
         int key=0;
         for( int i=0;i<10;i++)
         {
             if (str.equals(num[i]))
             {
                 System.out.println("常数(" + "3" + "," + num+ ")");
                 key=1;
                 break;
             }

         }

         if(key==0)
         {

             for (int j = 0; j < 10; j++)
             {
                 if (num[j] == null)
                 {
                     num[j] = str;
                     System.out.println(num[j]+"新增为"+"("+"3"+","+j+")");
                     return;
                 }

             }
             System.out.println("常数表满了");
             key=0;
         }
     }

    public void searchInkeychar(String str)
    {
        int key=0;
        for( int i=0;i<10;i++)
        {
            if (str.equals(keychar[i]))
            {
                System.out.println("(" + "2" + "," + i + ")");
                key=1;
                break;
            }

        }

        if(key==0)
        {

            for (int j = 0; j < 10; j++)
            {
                if (keychar[j] == null)
                {
                    keychar[j] = str;
                    return;
                }

            }
            System.out.println("关键字表满了");
            key=0;
        }
    }
    public void searchInopchar(String str)
    {
        int key=0;
        for( int i=0;i<10;i++)
        {
            if (str.equals(opchar[i]))
            {
                System.out.println("(" + "4" + "," + i + ")");
                key=1;
                break;
            }
        }

        if(key==0)
        {

            for (int j = 0; j < 10; j++)
            {
                if (opchar[j] == null)
                {
                    opchar[j] = str;
                   return;
                }

            }
            System.out.println("运算符表满了");
            key=0;
        }
    }
    public void searchInlimchar(String str)
    {
        int key=0;
        for( int i=0;i<5;i++)
        {
            if (str.equals(limchar[i]))
            {
                System.out.println("(" + "5" + "," + i + ")");
                key=1;
                break;
            }
        }

        if(key==0)
        {

            for (int j = 0; j < 5; j++)
            {
                if (limchar[j] == null)
                {
                    limchar[j] = str;
                    return;
                }

            }
            System.out.println("界符表满了");
        }
            key=0;

    }

}

