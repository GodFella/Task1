

package tusk1.MyExceptions;

//оисключение, которое возникает, когда самолету по расчету не будет хватать топлива на
//перелет
public class NotEnoughFuel extends RuntimeException{
    private String msg;
    public NotEnoughFuel(){
        msg="with this level of fuel the plane cannot end its flight";
    }
    @Override
    public String toString(){
        return msg;
    }
}
