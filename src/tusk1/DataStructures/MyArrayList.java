

package tusk1.DataStructures;
import java.util.Comparator;
//Здесь описываем класс, который описывает структуру данных для множества самолетов 
public class MyArrayList implements MyList{
    private Object[] AL;
    private int real_size;
    private final int min_cap=10;
    
    public MyArrayList(){
        AL=new Object[min_cap];
    }

    public MyArrayList(int init_cap){
        AL=new Object[init_cap];
    }

    private void imp_size(int minCapacity){
      Object[] help=new Object[minCapacity];
      System.arraycopy(AL,0,help,0,AL.length);
      AL=help;
    }
    
    private void dec_size(){
      Object[] help=new Object[AL.length-10];
      System.arraycopy(AL,0,help,0,help.length);
      AL=help;
    }
    
    private void pull_back(int index,int step){
       for(int i=index;i<real_size-step;i++){
           AL[i]=AL[i+step];
       }
       for(int i=real_size-step;i<real_size;i++)
           AL[i]=null;
       real_size-=step;
    }
    
    private void push_front(int index,int step){
        real_size+=step;
        ensureCapacity(real_size);
        for(int i=real_size-1;i>index+step-1;i--)
          AL[i]=AL[i-step];
    }
    
    @Override
    public void add(Object element){
      ensureCapacity(real_size+1);
      AL[real_size]=element;
      real_size++;
      }
    
    private void ensureCapacity(int minCapacity){
      if(minCapacity>AL.length)
            imp_size(minCapacity);
    }
    
    @Override
    public Object get(int index) throws ArrayIndexOutOfBoundsException{
       return AL[index];
   }
    
    @Override
    public Object remove(int index){ 
       Object current=AL[index];
       pull_back(index,1);
       while(AL.length-real_size>=15 && AL.length>min_cap)
           dec_size();
       return current;
    }
  
    @Override
    public void set(int index,Object element){
       AL[index]=element;
   }
    
    @Override
    public int size(){
        return real_size;
    }
    
    @Override
    public Object[] toArray(){
       Object[ ] data=new Object[real_size];
       System.arraycopy(AL, 0, data, 0, data.length);
       return data;
   }

  @Override
   public int indexOf(Object o, Comparator c){
      for(int i=0;i<AL.length;i++){
         if(c.compare(AL[i], o)==0)
             return i;
      } 
    return 0;
   }
 };
