

package tusk1.MyExceptions;


//исключение, возникающее когда мы переполняем вместительность самолета(
//людьми или грузом).
public class CapacityProblemException extends RuntimeException{
   private String msg;
   public CapacityProblemException(String s){
       switch(s){
           case "People":
               msg="Cannot settle more people than the number of chairs in the plane";
               break;
           case "Cargo":
               msg="The plane cannot carry that amount of cargo";
               break;
           default:
               break;
       }
   }

   @Override
   public String toString(){
       return msg;
   }
};
