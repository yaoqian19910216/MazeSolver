/* Qian Yao
   RedID 815260362
   cssc0016
*/
package data_structures;
import java.util.Iterator;
public class Queue<E> implements Iterable<E>{
    //push element x to the back of the queue
    LinearList<E> queue;
    int idx;

    public Queue(){
       queue = new LinearList<E>();
       idx = 0;
    }

    public void enqueue(E obj){
       if(obj == ((E) null)){
           return;
       }
       queue.addLast(obj);
       idx++;
    }

    //remove the element from in front
    public E dequeue(){
       if(queue.isEmpty()){
          System.out.println("Empty Queue!");
          return null;
       }
       E result = queue.peekFirst();
       queue.removeFirst();
       idx--;
       return result;
    }

    public int size(){
       return idx;
    }

    public boolean isEmpty(){
       return (idx == 0);
    }

    //get the front element
    public E peek(){
       return queue.peekFirst();
    }

    //return true if the object is in the queue;
    public boolean contains(E obj){
       return queue.contains(obj);
    }

    //remove the queue
    public void makeEmpty(){
       queue.clear();
    }

    public boolean remove(E obj){
       if(obj ==((E)null)){
          return false;
       }else{
          if(((Comparable<E>)(queue.remove(obj))).compareTo(obj) == 0){
             idx--;
             return true;
          }
       }
       return false;
    }

    public Iterator<E> iterator(){
       return queue.iterator();
    }
}
