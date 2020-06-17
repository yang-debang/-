package p1;

public class test {
    int i=0;

    public void go(String str){
        StringBuffer buffer2=new StringBuffer();
        if(str.length()==1){
            if(str.charAt(0) >= 48 && str.charAt(0) <= 57){
                buffer2.append(str.charAt(0));
                System.out.println(buffer2);
                return;
            }
        }
        while(i<str.length())   //输入为str字符串
        {


            if(str.charAt(i)>=48&&str.charAt(i)<=57) {
                buffer2.append(str.charAt(i));


                    while (str.charAt(i + 1) >= 48 && str.charAt(i + 1) <= 57) {


                        buffer2.append(str.charAt(i + 1));
                        i = i + 1;
                        if (i + 1 == str.length()) {
                            break;
                        }
                    }

                System.out.println(buffer2);
                buffer2 = new StringBuffer();

            }
            i++;






        }

    }

}
