/* Qian Yao
   RedID 815260362
   cssc0016
*/
package data_structures;
import java.util.Iterator;
public class Stack<E> implements Iterable<E> {
    LinearList<E> stack;
    int idx;

    public Stack(){
       stack = new LinearList<E>();
       idx = 0;
    }

    // Push element x onto stack.
        public void push(E x) {
            if(x == ((E) null)){
                return;
            }
            if(stack.addFirst(x)){
              idx++;
            }
        }

       // remove the element on the top of the stack 
        public E pop() {
            if(stack.isEmpty()){
               System.out.println("empty stack!");
               return null;
            }
            E result = stack.peekFirst();
            stack.removeFirst();
            idx--;
            return result;
        }

        // Get the top element.
        public E peek() {
            return stack.peekFirst();                    
        }

        public int size(){
            return idx;
        }

        // Return whether the stack is empty.
        public boolean isEmpty() {
             return (idx == 0);                  
        }

        public boolean contains(E obj){
             return stack.contains(obj);
        }

        public void makeEmpty(){
             stack.clear();
        }

        public boolean remove(E obj){
          if(obj ==((E)null)){
              return false;
          }else{
             if (((Comparable<E>)(stack.remove(obj))).compareTo(obj) == 0){
                idx--;
                return true;
             }
          } 
          return false;
        }

        public Iterator<E> iterator(){
           return stack.iterator();
        }
}


