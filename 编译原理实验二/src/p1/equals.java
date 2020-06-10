package p1;

public class equals {
    int k=0;int j=1;
    StringBuffer testString=new StringBuffer("#(i+i)*i#");
    int length=testString.length();
    char[][] table=new char[7][7];
    StringBuffer buffer=new StringBuffer();
    String[][] production=new String[4][3];
    public void run(String txt){ //将产生式分割，放入二维字符串中
        StringBuffer buffer=new StringBuffer();
        for(int i=0;i<txt.length();i++){
            if(txt.charAt(i)==61){
                production[k][0]=buffer.toString().trim();
                buffer=new StringBuffer();

            }
            else if(txt.charAt(i)==124){
                production[k][j]=buffer.toString().trim();
                buffer=new StringBuffer();
                j++;
            }
            else if(i==txt.length()-1){
                buffer.append(txt.charAt(i));
                production[k][j]=buffer.toString().trim();

            }
            else{buffer.append(txt.charAt(i));}
        }
        k++;
        j=1;
    }
    public void init(){
        putInstack(outstack(1));
    }//将测试字符串的#扣进缓存区中
    public void reception(char[][] table){
        for(int i=0;i<7;i++){//接受处理好的优先表，放到这个类的优先表中
            for(int j=0;j<7;j++){
                this.table[i][j]=table[i][j];
            }
        }
    }
   public char outstack(int a){//出栈，用a指定出栈的是缓存区还是测试字符串

        if(a==1){
            char c=testString.charAt(0);
            testString.deleteCharAt(0);
            return c;
        }
        if(a==2){
            char c=buffer.charAt(buffer.length()-1);
            buffer.deleteCharAt(buffer.length()-1);
            return 0;
        }
        return 0;
   }
   public void putInstack(char b){
        buffer.append(b);
   }//入栈，缓冲区放入字符
   public char compare(char a,char b){//返回终结符的优先关系
        for(int i=1;i<7;i++){
            if(table[i][0]==a){//先在table中找到行
                for(int j=1;j<7;j++){
                    if(table[0][j]==b){//再在table中找到列
                        return table[i][j];
                    }
                }
            }
        }
        return 0;
   }
   public char check(char c){//将输入的符号找到对应的规约符号
       if(c>=65){//若要规约的是非终
           for (int i = 1; i < 4; i++) {//跳过第一行产生式
               if(production[i][2].charAt(0)==c){//右部第二个产生式是单独的字符，故直接找该列
                   return production[i][0].charAt(0);//返回左部的字符
               }
           }
       }else {//规约的是终结符
           for (int i = 1; i < 4; i++) {
               for (int j = 1; j < 3; j++) {//无视非终符号，只要在产生式中出现了该终结符，则规约
                   if (production[i][j].charAt(0) == c) {
                       return production[i][0].charAt(0);
                   }
               }
           }
       }
        return '!';//无法规约
   }
   //check输入的是栈顶符号，checkvt输入的是次栈顶符号，若用到checkvt，则次栈顶符号一定是终结符
   public char checkvt(char c){
       for(int i=0;i<4;i++){
           for(int j=0;j<3;j++){//在右部第一个产生式中找终结符，只要出现就规约
               if(production[i][1].charAt(j)==c){
                   return production[i][0].charAt(0);
               }
           }
       }
       return '!';
   }
   public char search(String str){
        for(int g=0;g<str.length();g++){
            if(str.charAt(g)<65||str.charAt(g)>=97){
                //本例中，对比0或1位的终结符，即可判断要规约到的字符
                if(g==0){
                    for (int i = 1; i < 4; i++) {
                        for (int j = 1; j < 3; j++) {//无视非终符号，只要在产生式中出现了该终结符，则规约
                            if (production[i][j].charAt(0) == str.charAt(g)) {
                                return production[i][0].charAt(0);
                            }
                        }
                    }
                }
                if(g==1){//若终结符在1位，则可直接放弃对比右部第二产生式
                    for (int i = 1; i < 3; i++) {
                        for(int j=0;j<production[i][1].length();j++){
                            if(str.charAt(g)==production[i][1].charAt(j)){
                                return production[i][0].charAt(0);
                            }
                        }
                    }
                }
            }
        }
        return 0;
   }
   public void mainAlgorithm(){
        int k;
        while(testString.length()!=0) {
            if (buffer.charAt(buffer.length() - 1) < 65 || buffer.charAt(buffer.length() - 1) >= 97) {
                k = buffer.length() - 1;//以k来标记离栈顶最近的终结符
            } else {
                k = buffer.length() - 2;
            }
            if(compare(buffer.charAt(k), testString.charAt(0)) == ' '){
                System.out.println("该句子不可由产生式推导出");
                break;
            }
            //如果栈顶和待输入字符的优先性为<或=，则进栈
            if (compare(buffer.charAt(k), testString.charAt(0)) == '<' || compare(buffer.charAt(k), testString.charAt(0)) == '=') {
                char b = testString.charAt(0);
                putInstack(b);
                outstack(1);
                System.out.println(buffer + "    " + testString + " 将" + b + "进栈");
            }
            //否则规约
            else {//规则：循环比较相邻的终结符优先性，若。。aAb关系为<，则规约Ab。。后面的字符串，只比较终结符位置
                char q = buffer.charAt(k);
               // System.out.println(q);
                if (buffer.charAt(k - 1) >= 65 && buffer.charAt(k - 1) <= 97) {
                    k = k - 2;
                } else {
                    k = k - 1;
                }//k始终指向终结符的位置
                if (compare(buffer.charAt(k), q) != '<') {
                    //循环对比相邻终结符的优先关系，直到出现<
                    while (compare(buffer.charAt(k), q) != '<') {
                        q = buffer.charAt(k);
                        if (buffer.charAt(k) < 65 || buffer.charAt(k) >= 97) {
                            k = k - 1;
                        } else {
                            k = k - 2;
                        }
                    }
                    String str = buffer.substring(k + 1, buffer.length());
                    //check str
                    for (int i = 0; i < str.length(); i++) {
                        outstack(2);
                    }
                    putInstack(search(str));
                    System.out.println(buffer + "    " + testString + " 将" + str + "规约为" + search(str));
                }
                else {
                    String str = buffer.substring(k + 1, buffer.length());
                  // System.out.println(str);
                    for (int i = 0; i < str.length(); i++) {
                        outstack(2);
                    }
                    putInstack(search(str));
                    System.out.println(buffer + "    " + testString + " 将" + str + "规约为" + search(str));
                }
            }
        }
       System.out.println("将"+buffer+"规约为S  规约完成");
   }
   public void begin(){//主要优先分析算法
        int i;
        while(testString.length()!=0) {
            if (buffer.charAt(buffer.length() - 1) < 65||buffer.charAt(buffer.length() - 1) >=97) {
                i=buffer.length()-1;//缓冲区栈顶是终结符，处理栈顶元素
                if(compare(buffer.charAt(i),testString.charAt(0))=='<'||compare(buffer.charAt(i),testString.charAt(0))=='='){
                    char b=testString.charAt(0);//若栈顶元素和待输入符号关系是<或=，则进栈
                    putInstack(outstack(1));
                    System.out.println(buffer+"    "+testString+" 进栈"+b);
                }
               else if(compare(buffer.charAt(i),testString.charAt(0))=='>'){
                    //关系是>,则规约
                    if(check(buffer.charAt(i))=='!'){
                        char b=buffer.charAt(i);//如果无法直接规约，则规约后三位（应该找最左素短语，规约）
                        outstack(2);outstack(2);outstack(2);
                        putInstack(checkvt(b));
                        System.out.println(buffer+"   "+testString+" 后三位规约为"+checkvt(b));
                    }
                    else {//可以直接规约
                        char b=buffer.charAt(i);
                        outstack(2);
                        putInstack(check(b));
                        System.out.println(buffer+"   "+testString+" 将"+b+"规约为"+check(b));
                    }
                }
            }
            else {//栈顶是非终
                 i=buffer.length()-2;
                 if(compare(buffer.charAt(i),testString.charAt(0))=='<'||compare(buffer.charAt(i),testString.charAt(0))=='='){
                    //需要移进
                     char b=testString.charAt(0);
                     putInstack(outstack(1));
                     System.out.println(buffer+"    "+testString+" 进栈"+b);
                 }
                 if(testString.length()==0){}//设定的终止循环条件是测试字符串为空时。但在检测循环条件前，已经为空了，若处理测试字符串的首字符，则会报错
                 else if(compare(buffer.charAt(i),testString.charAt(0))=='>'){
                     //需要规约
                         if(check(buffer.charAt(i+1))!='!'){//若栈顶可以规约，则规约
                            char b=check(buffer.charAt(i+1));
                            outstack(2);
                            putInstack(b);
                            System.out.println(buffer+"   "+testString+" 将"+b+"规约为"+b);
                         }
                         else {//栈顶无法规约
                             char b = buffer.charAt(i);
                             outstack(2);//出栈三次（应该找最左素短语，规约）
                             outstack(2);
                             outstack(2);
                             putInstack(checkvt(b));
                             System.out.println("栈顶符号已无法继续规约，现规约次栈顶符号");
                             System.out.println(buffer + "   " + testString + " 将" + b + "规约为" + checkvt(b));
                         }
                 }
            }
        }
       System.out.println("将"+buffer+"规约为S  规约完成");
   }
}
