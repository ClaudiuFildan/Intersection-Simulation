package intersection2;


import java.util.ArrayList;

public class Controller{
    ArrayList<TrafficLight> semaphores = new ArrayList <TrafficLight>();
    ArrayList <Lane> lanes = new ArrayList<Lane>();
    public void buildSemaphores() {
        for(int i=0;i<=3;i++) {

            TrafficLight semaphoresaux =  new TrafficLight();
            semaphores.add(semaphoresaux);

        }
    }
    public void showAllSemaphoresState() {
        System.out.println("___________________");
        for(int i=0;i<=3;i++) {
            semaphores.get(i).showSemaphore(i);
            //this.tCounterShow.append.semaphores.get(i).showSemaphore(i);
        }
        System.out.println("___________________");
    }
    public void buildLanes() {
        for(int i=0;i<=3;i++) {

            Lane lanesaux =  new Lane();
            lanes.add(lanesaux);

        }
    }
}