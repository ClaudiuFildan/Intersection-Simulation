package intersection2;


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