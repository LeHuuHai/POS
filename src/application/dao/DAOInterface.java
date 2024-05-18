
package application.dao; 

import java.util.ArrayList;

public interface DAOInterface<T>{
    
    public boolean insert(T t);
    
    public boolean delete(T t);
    
    public boolean update(T t);
    
    public ArrayList<T> selectAll();
    
    public T selectById(T t);
}
