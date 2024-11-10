import java.util.LinkedList;

import static java.lang.Math.abs;
import static java.lang.Math.max;

public class LapCsere {
    static Keret A = new Keret('A');
    static Keret B = new Keret('B');
    static Keret C = new Keret('C');
    static  int laphiba = 0;
    static int steps = 1;
    static LinkedList<Keret> frames = new LinkedList<>();
    static LinkedList<Keret> freezedFrames = new LinkedList<>();
    static LinkedList<Character> eredmeny = new LinkedList<>();

    LapCsere(){
        frames.add(A);
        frames.add(B);
        frames.add(C);
        step();
    }
    public void step(){
        for(int page : Main.input) {
            if (talalat(abs(page)) || freezenTalalat(abs(page))) {
                    /*
                    if (page < 0) {
                        frame.dirty = true;
                    }
                     */
                pageFinder(abs(page)).used = steps;
                eredmeny.add('-');

            } else if (frames.isEmpty()) {
                eredmeny.add('*');
                laphiba++;
            } else {
                Keret frame;
                if (areEmpty()) {
                    frame = frames.getFirst();
                } else frame = lru();
                eredmeny.add(frame.name);
                //lap beirasa
                frame.getPages().add(abs(page));
                //mar nem ures
                frame.empty = false;
                //egy use
                frame.used = steps;
                //freeze
                //frame.freeze = true;
                freezedFrames.add(frames.remove(frames.indexOf(frame)));
                //sorvege
                //frames.add(2, frames.remove(frames.indexOf(frame)));
                //laphiba
                laphiba++;
            }

            for (Keret k : freezedFrames) {
                k.freezeTime++;
            }
            unFreeze();
            steps++;
        }
    }

    public boolean talalat(int currentPage){
        int counter = 0;
        for(Keret k : frames){
            if(!k.getPages().isEmpty() && k.getPages().getLast().equals(currentPage) ){
                counter++;
            }
        }
        if(counter > 0)
            return true;
        return false;
    }
    public boolean freezenTalalat(int currentPage){
        int counter = 0;
        for(Keret k : freezedFrames){
            if(!k.getPages().isEmpty() && k.getPages().getLast().equals(currentPage) ){
                counter++;
            }
        }
        if(counter > 0)
            return true;
        return false;
    }
    public void unFreeze(){
        LinkedList<Keret> temp = new LinkedList<>();
        for(Keret k : freezedFrames) {
            if(k.freezeTime > 3) {
                k.freezeTime = 0;
                temp.add(k);
            }
        }
       if(temp != null) {
           freezedFrames.removeAll(temp);
           frames.addAll(temp);
       }
    }
    public boolean jegkorszak(){
      return freezedFrames.size() == 3;
    }
    public boolean areEmpty(){
        int counter = 0;
        for(Keret k : frames){
            if(k.empty ){
                counter++;
            }
        }
        if(counter > 0)
            return true;
        return false;
    }
    public Keret pageFinder(int currentPage){
        Keret frame = null;
        for(Keret k : frames){
            if(!k.getPages().isEmpty() && k.getPages().contains(currentPage)){
                frame = k;
            }
        }
        if(frame == null){
            for(Keret k : freezedFrames){
                if(!k.getPages().isEmpty() && k.getPages().contains(currentPage)){
                    frame = k;
                }
            }
        }
        return frame;
    }

    public Keret lru() {
        Keret min = frames.getFirst();
        for (Keret k : frames) {
            if (k.used <= min.used ) {
                min = k;
            }
        }
        return min;
    }

    public void kiir(){
        for(Character c : eredmeny){
            System.out.print(c);
        }
        System.out.println();
        System.out.println(laphiba);
    }
}
