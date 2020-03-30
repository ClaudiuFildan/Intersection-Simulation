package intersection1;

import java.util.ArrayList;

public class Simulator{
    int[] queue=new int[4];
    int[] time =new int[4];
    int total=100;
    Controller firstControler = new Controller();
    public void setup() {
        firstControler.buildSemaphores();
        firstControler.buildLanes();
    }
    public void colectDataCarIn() {
        for(int i=0;i<=3;i++) {
            firstControler.lanes.get(i).trafficJamWorse(i);
        }
        for(int i=0;i<=3;i++) {
            this.queue[i]=firstControler.lanes.get(i).getLaneState();
        }
    }
    public void maximumCapacity() throws MaximumException{
        for(int i=0;i<=3;i++) {
            if(queue[i]>=80)
                throw new MaximumException(i);
        }

    }
    public void colectDataCarOut() {
        for(int i=0;i<=3;i++) {
            firstControler.lanes.get(i).trafficJamBetter(i);
        }
    }
    public void colectDataCarNow() {
        for(int i=0;i<=3;i++) {
            firstControler.lanes.get(i).showLaneState(i);
        }
    }
    public void showTimeVector() {
        System.out.println("The time allocation for each line is ["+time[0]+","+time[1]+","+time[2]+","+time[3]+"]");
        System.out.println("Total time allocation for each line is "+(time[0]+time[1]+time[2]+time[3])+" ");
    }
    public void AlgorithmError() {
        int i,maxCorectionIndex=0;
        for(i=0+1;i<=3;i++) {
            if(time[maxCorectionIndex]<time[i])
                maxCorectionIndex=i;
        }
        time[maxCorectionIndex]=time[maxCorectionIndex]+total;
        System.out.println("The Error is "+total+". New biggest time interval "+time[maxCorectionIndex]+".");
    }
    public void Algorithm() {
        int i;
        float timeslot=100;
        float factor;
        float totalNrOfCars=0;
        ArrayList<Integer> vector=new ArrayList<Integer>();



        System.out.println("_____Algorithm________");

        for(i=0;i<=3;i++) {
            totalNrOfCars=totalNrOfCars+this.queue[i];
            vector.add(queue[i]);
        }

        vector.sort(null);
        System.out.println("Sorted vector of queues ["+vector.get(0)+","+vector.get(1)+","+vector.get(2)+","+vector.get(3)+"]");
        System.out.println("__________");

        factor=timeslot/totalNrOfCars;

        System.out.println("Initial factor=timeslot/totalNrOfCars is "+factor+"="+timeslot+"/"+totalNrOfCars+" ");
        System.out.println("__________");
        for(i=0;i<=3;i++) {


            System.out.println("For factor*(nr of cars at line nr:"+i+") The time allocated is: "+(factor*vector.get(0))+"="+factor+"*"+vector.get(0)+" ");
            if((factor*vector.get(0))<5 ){

                timeslot=timeslot-5;
                totalNrOfCars=totalNrOfCars-vector.get(0);
                factor=timeslot/totalNrOfCars;

                System.out.println("Because time allocated<5, New factor "+factor+"="+timeslot+"/"+totalNrOfCars+" ");

                for(int j=0;j<=3;j++) {
                    if(vector.get(0)==queue[j]) {

                        time[j]=5;
                        //total=total-time[j];
                    }
                }
                total=total-5;

            }else{

                for(int j=0;j<=3;j++) {///j=i
                    if(vector.get(0)==queue[j]) {

                        time[j]=(int) Math.floor(factor*vector.get(0));

                    }
                }
                total=total-((int) Math.floor((factor*vector.get(0))));
            }

            vector.remove(0);
        }

        showTimeVector();
        System.out.println("__________");
        this.AlgorithmError();
        System.out.println("__________");
        showTimeVector();
        System.out.println("_____End Algorithm_____");
    }
    public void Intrerups() {
        try {


            for(int i=0;i<=3;i++) {
                for(int j=0;j<=3;j++) {

                    firstControler.semaphores.get(j).changeColorSemaphores(1,0,0);

                }
                firstControler.semaphores.get(i).changeColorSemaphores(0,0,1);
                firstControler.showAllSemaphoresState();
                Thread.sleep(1000*((int)time[0]));
                for(int j=0;j<=3;j++) {

                    firstControler.semaphores.get(j).changeColorSemaphores(0,0,0);

                }
                //firstControler.semaphores.get(i).changeColorSemaphores(0,0,1);
                firstControler.showAllSemaphoresState();
                //Thread.sleep(100*((int)time[0]));
            }
			  	/*
			  	for(int i=0;i<=3;i++) {
			  		firstControler.semaphores.get(i).changeColorSemaphores(0,1,0);

			  		firstControler.showAllSemaphoresState();
			  	}*/

            //firstControler.semaphores.get(0).changeColorSemaphores(0,1,0);
            //firstControler.showAllSemaphoresState();

        }
        catch(InterruptedException e)
        {
            Thread.currentThread().interrupt();
        }
    }
    public void loop() {

        System.out.println("__________");
        colectDataCarNow();
        System.out.println("__________");
        colectDataCarIn();
        System.out.println("__________");
        colectDataCarNow();
        System.out.println("__________");
        try {
            maximumCapacity();
        }catch(MaximumException e) {
            System.out.println("Exception:"+e.getMessage());
        }

        Algorithm();
        System.out.println("__________");
        colectDataCarNow();
        System.out.println("__________");
        colectDataCarOut();
        System.out.println("__________");
        colectDataCarNow();
        System.out.println("__________");

        //colectDataCarIn();
        //System.out.println("__________");
        //colectDataCarNow();
        //System.out.println("__________");
        //colectDataCarIn();
        //Intrerups();


        // Thread.sleep(1000*((int)time[0]));
        // System.out.println(" time1 unu "+time[0]+" doi "+time[1]+" trei "+time[2]+" patru "+time[3]);


        //  Thread.sleep(1000*((int)time[1]));
        // System.out.println(" time2");

        //  Thread.sleep(1000*((int)time[2]));
        // System.out.println(" time3");


        // Thread.sleep(1000*((int)time[3]));
        // System.out.println(" time4");

        //}
        //catch(InterruptedException ex)
        //{
        //  Thread.currentThread().interrupt();
        //}
    }

}