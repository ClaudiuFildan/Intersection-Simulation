package intersection1;

import java.util.ArrayList;
import java.util.Random;

public class Lane{
    int numberOfCars;
    Random rand = new Random();
    ArrayList<Car> carsqueue = new ArrayList<Car>();

    public void trafficJamWorse(int nr) {

        int c=rand.nextInt(100);

        System.out.println("In lane nr:"+nr+" arrive "+c+" cars.");

        for(int i=0;i<c;i++) {
            Car carsqueueaux= new Car();
            carsqueue.add(carsqueueaux);
        }
    }
    public void trafficJamBetter(int nr) {

        int c=rand.nextInt(50);
        int a=carsqueue.size();

        //System.out.println("In lane nr:"+nr+" left "+c+" cars.[RANDOM]");
        if(c>a) {
            c=a;
        }
        System.out.println("In lane nr:"+nr+" left "+c+" cars.[TRUE]");
        for(int i=0;i<c;i++) {
            carsqueue.remove(0);
        }

    }
    public void showLaneState(int nr) {
        int a=carsqueue.size();
        System.out.println("In lane nr:"+nr+" are now "+a+" cars.");
    }
    public void showLaneStep(int nr) {
        this.trafficJamWorse(nr);
        this.showLaneState(nr);
        this.trafficJamBetter(nr);
        this.showLaneState(nr);
    }
    public int getLaneState() {
        return carsqueue.size();
    }
}
