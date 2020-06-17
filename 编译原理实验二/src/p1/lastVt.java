package p1;

public class lastVt {
    int k=0;int j=1;
    int stackLength;
    CharacterPrecedenceTable cpt=new CharacterPrecedenceTable();
    String[][] production=new String[4][3];
    int[][] biogroup=new int[4][6];
    String[] stack=new String[10];
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
    public void putinstack(char a,char b){
        StringBuffer sb=new StringBuffer();
        for(int i=0;i<10;i++){
            if(stack[i]==null){
                sb.append(a);sb.append(b);
                stack[i]=sb.toString();
                stackLength=i+1;
                System.out.println(stack[i]+" 栈长度为："+stackLength);
                break;
            }
        }
        if(a=='S'){
            if(b=='#'){biogroup[0][0]=1;}
            if(b=='+'){biogroup[0][1]=1;}
            if(b=='*'){biogroup[0][2]=1;}
            if(b=='('){biogroup[0][3]=1;}
            if(b==')'){biogroup[0][4]=1;}
            if(b=='i'){biogroup[0][5]=1;}

        }
        if(a=='E'){
            if(b=='#'){biogroup[1][0]=1;}
            if(b=='+'){biogroup[1][1]=1;}
            if(b=='*'){biogroup[1][2]=1;}
            if(b=='('){biogroup[1][3]=1;}
            if(b==')'){biogroup[1][4]=1;}
            if(b=='i'){biogroup[1][5]=1;}
        }
        if(a=='T'){
            if(b=='#'){biogroup[2][0]=1;}
            if(b=='+'){biogroup[2][1]=1;}
            if(b=='*'){biogroup[2][2]=1;}
            if(b=='('){biogroup[2][3]=1;}
            if(b==')'){biogroup[2][4]=1;}
            if(b=='i'){biogroup[2][5]=1;}
        }
        if(a=='F'){
            if(b=='#'){biogroup[3][0]=1;}
            if(b=='+'){biogroup[3][1]=1;}
            if(b=='*'){biogroup[3][2]=1;}
            if(b=='('){biogroup[3][3]=1;}
            if(b==')'){biogroup[3][4]=1;}
            if(b=='i'){biogroup[3][5]=1;}
        }
    }
    public String outstack(){

        String buffer=new String();
        if(stack[0]==null){
            System.out.println("栈空");
            stackLength=0;
            return null;
        }
        for(int i=0;i<10;i++){
            if(stack[i]==null){
                buffer=stack[i-1];
                stack[i-1]=null;
                stackLength=i-1;
                return buffer;
            }
        }
        return null;
    }
    public void analyse(){

        for(int i1=0;i1<4;i1++){
            if(production[i1][2]!=null){
                if(production[i1][2].charAt(0)<65||production[i1][2].charAt(0)>=97) {

                    putinstack(production[i1][0].charAt(0),production[i1][2].charAt(0));
                }
            }
            for(int i=1;i<3;i++) {

                if (production[i1][1].charAt(production[i1][1].length()-i)<65) {

                    putinstack(production[i1][0].charAt(0),production[i1][1].charAt(production[i1][1].length()-i));

                }
            }
        }

    }
    /*
    S→#E#
    E→E+T | T
    T→T*F |F
    F→(E)|i
    */
    public void recursive (){
        if(stackLength==0){
            System.out.println("栈空了" );
            return;
        }
        String stirngstack=outstack().trim();
        char a=stirngstack.charAt(0);
        char b=stirngstack.charAt(1);

        for(int i1=0;i1<4;i1++) {
            if(production[i1][2]==null){
                if(a==production[i1][0].charAt(0)){}
                else if(a==production[i1][1].charAt(production[i1][1].length()-1)){

                    putinstack(production[i1][0].charAt(0),b);
                }
            }
            else if (a==production[i1][1].charAt(production[i1][1].length()-1)||a==production[i1][2].charAt(production[i1][2].length()-1)){
                if(a==production[i1][0].charAt(0)){}
                else {
                     putinstack(production[i1][0].charAt(0), b);
                }
            }
        }
        recursive ();
    }
    public void init(){
        for(int i1=0;i1<4;i1++){
            for(int i2=0;i2<6;i2++){
                biogroup[i1][i2]=0;
            }

        }
    }
    public void printBiogroup(){
        System.out.println("LastVt集:");
        System.out.println("  # + * ( ) i");
        for(int i1=0;i1<4;i1++){
            System.out.print(production[i1][0]+" ");
            for(int i2=0;i2<6;i2++){
                System.out.print(biogroup[i1][i2]+" ");
            }
            System.out.println();
        }

    }
    public int[][] send(){
        return biogroup;
    }
}
