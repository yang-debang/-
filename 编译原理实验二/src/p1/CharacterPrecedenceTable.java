package p1;

public class CharacterPrecedenceTable {
    char[][] firstvt = new char[5][7];
    char[][] lastvt = new char[5][7];
    String[][] production=new String[4][3];
    int k=0;int j=1;
    char[][] table=new char[7][7];
    char[] c1={'S','E','T','F'};//可以在run函数里写一个分拣非终和终结符的程序，放到指定的字符数组中
    char[] c2={'#','+','*','(',')','i'};
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
        for (int i = 0; i < 5; i++) {
           for (int j = 1; j < 7; j++) {
               firstvt[i][j]=' ';
               lastvt[i][j]=' ';
            }
        }
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
               table[i][j]=' ';
            }
        }
        for (int i = 1; i < 7; i++) {
               table[i][0]=c2[i-1];
            table[0][i]=c2[i-1];
        }
    }
    public void reception(int[][] biogroup, int key) {
        if (key == 1) {//对输出的二维first和last整形数组映射到该类中的first，last字符数组中
            for (int i = 1; i < 5; i++) {//key指定处理的是first集或last集
               firstvt[i][0]=c1[i-1];
                for (int j = 1; j < 7; j++) {
                    firstvt[0][j]=c2[j-1];
                    if(biogroup[i-1][j-1]==1){
                        firstvt[i][j]='!';
                    }
                }
            }
        }
        if (key == 2) {
            for (int i = 1; i < 5; i++) {
                lastvt[i][0]=c1[i-1];
                for (int j = 1; j < 7; j++) {
                    lastvt[0][j]=c2[j-1];
                    if(biogroup[i-1][j-1]==1){
                        lastvt[i][j]='!';
                    }
                }
            }
        }
    }
    public void print() {
        System.out.print(" ");
        for(int i = 0; i<5;i++) {
            for (int j = 0; j < 7; j++) {
               System.out.print(firstvt[i][j]+" ");
            }
            System.out.println();
        }
        System.out.print(" ");
        for(int i = 0; i<5;i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(lastvt[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println("算符优先表为：");
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(table[i][j]+" ");
            }
            System.out.println();
        }


    }
    public void putIntable(char a,char b,int key){//a是非终，b是终

        if(key==1){//处理firstvt集，先找到table的行，再置列
            for(int i=1;i<5;i++){
                if(firstvt[i][0]==a){//在firstvt集里找到该非终符号的集合的行
                    for(int j=1;j<7;j++){
                        if(table[j][0]==b) {//在table集里找到终结符所在的行。
                          for(int k=0;k<7;k++){
                              if(firstvt[i][k]=='!'){
                                  table[j][k]='<';
                              }
                          }
                        }
                    }
                }
            }
        }
        if(key==2){
            for(int i=1;i<5;i++){
                if(lastvt[i][0]==a){//在lastvt集里找到该非终符号的集合的行
                    for(int j=1;j<7;j++){
                        if(table[0][j]==b) {//在table集里找到终结符所在的列，再置行。
                            for(int k=0;k<7;k++){
                                if(lastvt[i][k]=='!'){
                                    table[k][j]='>';
                                }
                            }
                        }
                    }
                }
            }
        }
        if(key==3){
            for(int i=0;i<7;i++){
                if(table[i][0]==a){
                    for(int j=0;j<7;j++){
                        if(table[0][j]==b){
                            table[i][j]='=';
                        }
                    }
                }
            }
        }
    }


    public void analyse(){
        for(int i1=0;i1<4;i1++){
            for(int i2=0;i2<production[i1][1].length();i2++){
             if(i2-1>=0){//在产生式中找形如。。。aA。。。字符串
                 if(production[i1][1].charAt(i2)>=65&&production[i1][1].charAt(i2-1)<65){
                     putIntable(production[i1][1].charAt(i2),production[i1][1].charAt(i2-1),1);
                     //System.out.println("出发firstvt:"+production[i1][1].charAt(i2)+"&"+production[i1][1].charAt(i2-1));
                 }
             }
             if(i2+1<=production[i1][1].length()-1){//找形如。。。Aa..的字符串
                 if(production[i1][1].charAt(i2)>=65&&production[i1][1].charAt(i2+1)<65){
                     putIntable(production[i1][1].charAt(i2),production[i1][1].charAt(i2+1),2);
                     //System.out.println("出发lastvt"+production[i1][1].charAt(i2)+"&"+production[i1][1].charAt(i2+1));
                 }
             }
             if(i2+1<=production[i1][1].length()-1&&i2-1>=0){//找形如。。。aAb的字符串
                 if(production[i1][1].charAt(i2+1)<65&&production[i1][1].charAt(i2-1)<65){
                     putIntable(production[i1][1].charAt(i2-1),production[i1][1].charAt(i2+1),3);
                 }
             }
            }
        }
    }
  public char[][] send(){
        return table;
  }
}
