

package tusk1.MyExceptions;
//Исключение, которое возникает, когда мы пытаемся влить топлива больше,
//чем может содержать бак
public class MoreFuelThanCanContain extends RuntimeException{
    private String msg;
    public MoreFuelThanCanContain(){
        msg="You cannot add more fuel than the back can contain!!";
    }
    @Override
    public String toString(){
        return msg;
    }
};
