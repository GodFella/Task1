

package tusk1.DataStructures;
import java.util.Comparator;

public interface MyList {
    void add(Object e);
    //void add(int index,Object element);
    //void addAll(Object[] c);
    //void addAll(int index, Object[] c);
    Object get(int index) throws NullPointerException;
    Object remove(int index);
    void set(int index,Object element);
    int indexOf(Object o, Comparator c);
    int size();
    Object[] toArray();
}
