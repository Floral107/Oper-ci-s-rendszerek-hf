import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    static LinkedList<Task> srtf = new LinkedList<>();
    static LinkedList<Task> rr = new LinkedList<>();
    static LinkedList<Task> srtf_kesz = new LinkedList<>();
    static LinkedList<Task> rr_kesz = new LinkedList<>();
    static LinkedList<String> eredmeny = new LinkedList<>();
    static  LinkedList<Task> erkezesi_sorrend = new LinkedList<>();
    static int utem  = 0;
    static boolean volt_srtf = false;

    public static void rendez(){
        //srtf
        srtf.sort(new BetuComp());
        srtf.sort(new IndIdoComp());
        //srtf.sort(new LokIdoComp());

        //rr
        rr.sort(new IndIdoComp());
       // rr.sort(new BetuComp());
    }
    public static void keszt_rendez() {
        //srtf
        //srtf_kesz.sort(new IndIdoComp()); mivel a keszen vannak ez mar nem kell, hisz ez alapjan kerultek ide, legalabbis remelem
        srtf_kesz.sort(new LokIdoComp());
        // srtf_kesz.sort(new BetuComp());

    }
    public static void rrkeszt_rendez(){
        rr_kesz.sort(new BetuComp());

    }

    public static void main(String[] args) {
        boolean flag = false;
        //String pattern = "[A-Z],[0-1],[0-30],[0-30]\n";
       // Pattern p = Pattern.compile(pattern);

       //File f = new File(args[0]); //
        Scanner sc = new Scanner(System.in);  //System.in
        boolean meg_mehet = true;
        while(meg_mehet && sc.hasNextLine()){
            String line = sc.nextLine();
            if(line.contains("0")||line.contains("1")) {
                String[] task = line.split(",");
                erkezesi_sorrend.add(new Task(task));

                if (Integer.parseInt(task[1]) == 0) {
                    rr.add(new Task(task));
                }
                if (Integer.parseInt(task[1]) == 1) {
                    srtf.add(new Task(task));
                }
            }else meg_mehet = false;
        }
        sc.close();



//elso rendezes
        rendez();
        erkezesi_sorrend.sort(new IndIdoComp()); // kiakaszt komolyan

        // maga az utemezes
        while(!srtf.isEmpty() || !rr.isEmpty() || !srtf_kesz.isEmpty() || !rr_kesz.isEmpty()) {
            while (rr.peekFirst() != null && rr.peekFirst().ii == utem) {
                 rr_kesz.add(rr.removeFirst());
            }


            while(srtf.peekFirst() != null && srtf.peekFirst().ii == utem){
                srtf_kesz.add(srtf.removeFirst());
                }

            //srtf resz, kb kesz sztem :)

            if(!srtf_kesz.isEmpty()){
                volt_srtf = true;
                keszt_rendez();
                if(!rr_kesz.isEmpty() && rr_kesz.getFirst().ic == 1){
                    rr_kesz.getFirst().volt = true;
                    rr_kesz.getFirst().ic = 0;
                }
                Task temp = srtf_kesz.peekFirst();

                temp.li--;
                for(Task t : erkezesi_sorrend){if(t.betu.equals(temp.betu)){t.li--;}}
                if(temp.li == 0){
                    if (!eredmeny.isEmpty() && !eredmeny.getLast().equals(temp.betu)) {
                        eredmeny.add(srtf_kesz.removeFirst().betu);
                    }else {
                        srtf_kesz.removeFirst();
                    }
                    for (Task v : erkezesi_sorrend) {
                        if( v.ii <= utem && v.li > 0 && !v.betu.equals(temp.betu)) {
                            v.var++;
                        }
                    }
                }else if(temp.li > 0){
                    if(!eredmeny.contains(temp.betu)) {
                        eredmeny.add(srtf_kesz.getFirst().betu);
                    }
                    //kint van mert akkor is kell nyilvan ha az eredmenybe nem irunk
                    for (Task v : erkezesi_sorrend) {
                        if( v.ii <= utem && v.li > 0 && !v.betu.equals(temp.betu)) {
                            v.var++;
                        }
                    }
                }
            }

            //rr resz, sor vegere elv

            if(!rr_kesz.isEmpty() && !volt_srtf){
                int idoszelet = 2;
                int i =0;
                //for(Task t : rr_kesz){if(t.ic == 0)i++;}
                //if(i == rr_kesz.size()) {rrkeszt_rendez();}
                if(rr_kesz.size() > 1 && rr_kesz.peekFirst().volt ){
                    rr_kesz.peekFirst().volt = false;
                    rr_kesz.add(1,rr_kesz.removeFirst());
                }
                Task temp = rr_kesz.peekFirst();

                 //temp.volt = false;
                temp.li--;
                for(Task t : erkezesi_sorrend){if(t.betu.equals(temp.betu)){t.li--;}}
                temp.ic++;
                if(temp.li == 0) {
                    if(eredmeny.isEmpty()){eredmeny.add(rr_kesz.getFirst().betu);}
                    if (!eredmeny.getLast().equals(temp.betu)) {
                        eredmeny.add(rr_kesz.removeFirst().betu);
                    } else {
                        rr_kesz.removeFirst();
                    }
                    for (Task v : erkezesi_sorrend) {
                        if (v.ii <= utem && v.li > 0 && !v.betu.equals(temp.betu)) {
                            v.var++;
                        }
                    }
                }
                if(temp.li > 0 && temp.ic < idoszelet){
                    if(eredmeny.isEmpty()){eredmeny.add(rr_kesz.getFirst().betu);}
                    if(!eredmeny.getLast().equals(temp.betu)) {
                        eredmeny.add(rr_kesz.getFirst().betu);
                    }
                    //if(temp.ic == 1){temp.volt = true;}
                    //kint van mert akkor is kell nyilvan ha az eredmenybe nem irunk
                    for (Task v : erkezesi_sorrend) {
                        if( v.ii <= utem && v.li > 0 && !v.betu.equals(temp.betu)) {
                            v.var++;
                        }
                    }
                }
                //majom, viz

                if(temp.li != 0 && temp.ic == idoszelet){
                    if(!eredmeny.getLast().equals(temp.betu)) {
                        eredmeny.add(rr_kesz.getFirst().betu);
                    }
                    temp.ic = 0;
                    flag = true;
                    //sor vege

                    if(rr_kesz.size() > 1) {
                        for (Task v : erkezesi_sorrend) {
                            if( v.ii <= utem && v.li > 0 && !v.betu.equals(temp.betu)) {
                                v.var++;
                            }
                        }
                        //sorvege kollega
                        rr_kesz.add(rr_kesz.removeFirst());
                    }

                }
            }

            utem++;
            volt_srtf = false;
        }

//elso sor, kimenet

        for(String str : eredmeny){
            System.out.print(str);
        }
        System.out.println();

//masodik sor, kimenet

        for(Task t : erkezesi_sorrend){
            if(t != erkezesi_sorrend.getLast()) {
                t.toString_b();
                System.out.print(",");
            }
        }
        erkezesi_sorrend.getLast().toString_b();
    }
}

