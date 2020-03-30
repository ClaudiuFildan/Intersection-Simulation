package intersection2;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CrossRoad extends JFrame implements ActionListener {
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