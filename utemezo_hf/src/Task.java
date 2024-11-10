public class Task {
    String betu; //task betujele
    int pr; //task prioritasa
    int ii; //task inditasi ideje
    int li; //task loketideje
    int var; //task varakozasi ideje
    int njohetsz; //rr-nel kell remelem
    int ic; //ido counter az rr segitesere
    boolean volt;

    Task(String b, int p, int i, int l, int v){
        betu = b;
        pr = p;
        ii = i;
        li = l;
        var = v;
    }
    Task(String[] sor){
        betu = sor[0];
        pr = Integer.parseInt(sor[1]);
        ii = Integer.parseInt(sor[2]);
        li = Integer.parseInt(sor[3]);
        var = 0;
        njohetsz = ii;
        ic = 0;
        volt = false;
    }
    Task(){}
    void toString_a(){System.out.println(this.betu);}
    void toString_b(){System.out.print(this.betu + ":"+ this.var);}

}
