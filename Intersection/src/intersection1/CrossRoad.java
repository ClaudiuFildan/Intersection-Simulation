package intersection1;

public class CrossRoad {
    public float maxCycle=100;
    public void startSimulation(){
        Simulator firstsimulator=new Simulator();
        firstsimulator.setup();
        firstsimulator.loop();
    }
    public static void main(String[] args) throws InterruptedException{
        CrossRoad firstcrossroad =new CrossRoad();
        firstcrossroad.startSimulation();

    }

}