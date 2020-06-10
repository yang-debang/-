package p1;
import java.io.*;
public class identify
{   exeTest et=new exeTest();


   public void goahead(String txt)
   {
      et.goahead();
      String str=new String();
      if(txt==null){
         return;
      }
      //String txt = "int i=0;int j=9-k;int k=743*3*88*6793*43*54*23*1222*56*5443*563;";


      char[] charArray = txt.toCharArray();
      StringBuffer sb=new StringBuffer();
      for(int i=0;i<txt.length();i++)
      {
         if(charArray[i]==32)
         {  str=sb.toString();

            et.searchInkeychar(str);
            //analyse(str.trim());
            //System.out.println(str);
            sb=new StringBuffer();
            if(i+2==txt.length()){
               System.out.println("存在语法错误");
               return;
            }
         }
         else
            {
            if (charArray[i] == 59 || charArray[i] == 123 || charArray[i] == 125)
            {
               analyse(sb.toString().trim());
               et.searchInlimchar(Character.toString(charArray[i]));
               sb = new StringBuffer();
            }  else
               {
               sb.append(charArray[i]);
                 }
            }
         if(i==txt.length()-1)//无用
         {
             str=sb.toString();
             //System.out.println(str);
         }
      }
      //et.print();
   }
   public void analyse(String str)
   { //在goahead中调用et的方法所赋予于exeTest类中的值，在analyse中保持不变


      StringBuffer buffer=new StringBuffer(str);

      StringBuffer buffer2=new StringBuffer();
      int i=0;
      while(i<str.length())
      {

         /*if ((buffer.charAt(i) <= 57) && (buffer.charAt(i) >= 48)) {
            {
               //识别为常量(num)
               System.out.println("(" + 3 + "," + buffer.charAt(i) + ")");
               buffer2 = new StringBuffer();
               i++;
            }
         }*/
         if (i==str.length()-1) {

               //识别为常量(num)
               buffer2.append(str.charAt(i));
               

               if((str.charAt(i)<48)||(str.charAt(i)>57)){
                  et.searchInmarkchar(buffer2.toString());
               }
               else {
                  et.searchInnum(buffer2.toString());
               }
               buffer2=new StringBuffer();
               i++;

         }
         else{
            if(buffer.charAt(i)==61)
            {
               //将字符串识别为变量(markchar)
               if(buffer2!=null){

                  et.searchInmarkchar(buffer2.toString());
                  et.searchInopchar(Character.toString(buffer.charAt(i)));
                  buffer2 = new StringBuffer();
                  i++;
               }
            }
            else{
               if ((buffer.charAt(i) == 43) || (buffer.charAt(i) == 45) || (buffer.charAt(i) == 42) || (buffer.charAt(i) == 37))
               {
                  //识别为变量
                  if (buffer2.length() != 0)
                  {  if(check(buffer2.toString())==true){

                     et.searchInnum(buffer2.toString());
                  }else{

                     et.searchInmarkchar(buffer2.toString());
                  }
                     et.searchInopchar(Character.toString(buffer.charAt(i)));
                     buffer2 = new StringBuffer();
                     i++;
                  }
               }
               else{
                  buffer2.append(buffer.charAt(i));
                  i++;
               }
            }

         }
         //在整个if外
      }  //循环结束分割符
      //System.out.println("输出为"+buffer2);
      if(buffer2.length()!=0) {
         System.out.println("输出为"+buffer2);
         if(check(buffer2.toString())==true){ //为纯数字
            System.out.println("执行num");
            et.searchInnum(buffer2.toString());
         }
         if(check(buffer2.toString())==false) {
            System.out.println("执行markchar");
            et.searchInmarkchar(buffer2.toString());
         }
      }
   }
   public boolean check(String str){
      for(int i=0;i<str.length();i++){
         if(str.charAt(i)<48||str.charAt(i)>57){
            return false;
         }
      }
      return  true;
   }
}
