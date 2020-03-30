package intersection1;

public class MaximumException extends Exception{
    public MaximumException(int i) {
        super(" !!!OverCapacity Alert!!! At Lane nr:"+i+".");
    }
}