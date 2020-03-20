package intersection2;

import  java.util. * ;
import java.util.concurrent.TimeUnit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



class TrafficLight{
	int red=0;
	int yellow=0;
	int green=0;
	public void changeColorSemaphores(int r,int y,int g){
		red=r;
		yellow=y;
		green=g;
	}	
	public void showSemaphore(int n) {
		//System.out.println("___________________");
		System.out.print("Semaphore nr "+n+": ");
		if(this.red==1)
			System.out.println("Red");
		else if(this.yellow==1){
			System.out.println("Yellow");}
		else if(this.green==1){
			System.out.println("Green");}
		else {
			System.out.println("Not Working");}
		}
	public String showSemaphoreString(int n) {
		//System.out.println("___________________");
		//System.out.print("Semaphore nr "+n+": ");
		if(this.red==1)
			return "Red";
		else if(this.yellow==1){
			return "Yellow";}
		else if(this.green==1){
			return "Green";}
		else {
			return "Not Working";}
		}
}

class Lane{
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

class Controller{
	ArrayList<TrafficLight> semaphores = new ArrayList<TrafficLight>();
	ArrayList<Lane> lanes = new ArrayList<Lane>();
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
class Car{
	
}
class MaximumException extends Exception{
	public MaximumException(int i) {
		super(" !!!OverCapacity Alert!!! At Lane nr:"+i+".");
	}
}
class Simulator{
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


public class CrossRoad extends JFrame implements ActionListener{
	public float maxCycle=100;
	Simulator firstsimulator=new Simulator();
	
	int number=1;
	JButton bEnters;
	JTextArea tCounterShow;
	
	CrossRoad(){
		setTitle("This is a CrossRoad Application");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		init();
		setSize(500,300);
		setVisible(true);
		
	}
	 public void init(){
		 this.setLayout(null);
         int width=100;int height = 20;
         
         bEnters = new JButton("Counter: ");
         bEnters.setBounds(50, 50, width, height);
         
         tCounterShow = new JTextArea();
         tCounterShow.setBounds(10,100,450,180);
         tCounterShow.setEditable(false);
        
         bEnters.addActionListener(this);
         
         JScrollPane scroll = new JScrollPane(tCounterShow);     
       
         scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
         scroll.setBounds(10, 100, 450, 180);
         
         add(scroll);
         add(bEnters);
         //add(tCounterShow);
	 }
	 public void actionPerformed(ActionEvent e) {
		 	String aux;
		 	int a;
		 	startSimulation();
		 	
		 	try {
		 		tCounterShow.setText("Start Simulation"+"\n");
		 		
	 			for(int i=0;i<=3;i++) {
	 				tCounterShow.append("----------------"+"\n");
	 				a=firstsimulator.time[i];
	 				tCounterShow.append("Time:"+a+"\n");
	 				for(int j=0;j<=3;j++)
	 				{
	 					firstsimulator.firstControler.semaphores.get(j).changeColorSemaphores(1,0,0);
	 					if(i==j) {
	 						firstsimulator.firstControler.semaphores.get(j).changeColorSemaphores(0,0,1);
	    			
	 					}
	 					aux=firstsimulator.firstControler.semaphores.get(j).showSemaphoreString(j);
	 					tCounterShow.append("Semaphore nr"+j+"."+"\n");
	 					tCounterShow.append(aux+"\n");
	 					//tCounterShow.append("----------------"+"\n");
	 					

	 				}
	 					tCounterShow.append("----------------"+"\n");
	 			
	 					
	 					  		
	 				}
	 
		 	
		 		
		 		
		 		
		 		for(int i=0;i<=3;i++) {
			  		for(int j=0;j<=3;j++) {
			  			
			  			firstsimulator.firstControler.semaphores.get(j).changeColorSemaphores(1,0,0);
			  			
			  		}
			  		firstsimulator.firstControler.semaphores.get(i).changeColorSemaphores(0,0,1);
			  		firstsimulator.firstControler.showAllSemaphoresState();
			  		
			  		for(int j=0;j<=3;j++) {
			  			
			  			firstsimulator.firstControler.semaphores.get(j).changeColorSemaphores(0,0,0);
			  			
			  		}
			  		//firstControler.semaphores.get(i).changeColorSemaphores(0,0,1);
			  		firstsimulator.firstControler.showAllSemaphoresState();
		 		}
		 		
		 		
		 		Thread.sleep(100*5);
		 		
		 		
		 		
			  
		 	}
		 	catch(InterruptedException ea)
		 	{
		    Thread.currentThread().interrupt();
		 	}
		}
	public void startSimulation(){
		
		firstsimulator.setup();
		firstsimulator.loop();
	}
	 
	
	 
	public static void main(String[] args) throws InterruptedException{
		CrossRoad firstcrossroad =new CrossRoad();
		//firstcrossroad.startSimulation();
		
	}

}


