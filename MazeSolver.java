/*
 Qian Yao
 RedID 815260362
 Account cssc0016
 I am using Queue and Stack class in homework2, not Queue and Stack interface in blackboard mentioned. I have used the updated data_strcutures package, which is in my handin/prog2 files. Please download it first.
 */

import data_structures.*;

public class MazeSolver{
    int row;
    Queue<GridCell> q = new Queue<GridCell>();
    Stack<GridCell> s = new Stack<GridCell>(); 
    MazeGrid maz;
    public MazeSolver(int dimension){
          maz = new MazeGrid(this, dimension);              
          row = dimension;
    }

    public void mark(){
          if(row <= 0){
             return;
          }
          System.out.println(row);
          GridCell c  = maz.getCell(0,0);
          c.setDistance(0);
          q.enqueue(c);
          while(!q.isEmpty()){
            GridCell d;
            d = q.dequeue();
            if(d == null){
               return;
            }
            int x = d.getX();
            int y = d.getY();
            int dis = d.getDistance();

            GridCell e = maz.getCell(x,y-1);
            if(maz.isValidMove(e) && !e.wasVisited()){
                  e.setDistance(dis+1);
                  maz.markDistance(e);
                  q.enqueue(e);
            }
                  
            GridCell f = maz.getCell(x,y+1);
            if(maz.isValidMove(f) && !f.wasVisited()){
                  f.setDistance(dis+1);
                  maz.markDistance(f);
                  q.enqueue(f);
            }

            GridCell h = maz.getCell(x-1,y);
            if(maz.isValidMove(h) && !h.wasVisited()){
                  h.setDistance(dis+1);
                  maz.markDistance(h);
                  q.enqueue(h);
            }
            
            GridCell k = maz.getCell(x+1,y);      
            if(maz.isValidMove(k) && !k.wasVisited()){      
                  k.setDistance(dis+1);
                  maz.markDistance(k);
                  q.enqueue(k);
            } 
          }
    }

    public boolean move(){
          if(row <= 0){
             return false;
          }
          GridCell c = maz.getCell(row - 1,row - 1); 
          int dis = c.getDistance();
          if(dis == -1){
             return false;
          }  
          maz.markMove(c);
          s.push(c);
          while(dis != 0){
             int x = c.getX();
             int y = c.getY();
             GridCell d = maz.getCell(x, y - 1);
             GridCell f = maz.getCell(x, y + 1);
             GridCell h = maz.getCell(x - 1, y);
             GridCell k = maz.getCell(x + 1, y);
             if(maz.isValidMove(d) && c.wasVisited()){
                if(d.getDistance() <= dis){
                   dis = d.getDistance();
                   c = d;
                }
             }
             if(maz.isValidMove(f) && f.wasVisited()){
                if(f.getDistance() <= dis){
                   dis = f.getDistance();
                   c = f;
                }
             }
             if(maz.isValidMove(h) && h.wasVisited()){
                if(h.getDistance() <= dis){
                   dis = h.getDistance();
                   c = h;
                }
             }
             if(maz.isValidMove(k) && k.wasVisited()){
                if(k.getDistance() <= dis){
                   dis = k.getDistance();
                   c = k;
                }
             }
             maz.markMove(c);
             s.push(c); 
          }
          return true;
    }

    public void reset(){
           q.makeEmpty(); 
           s.makeEmpty();    
    }

    public static void main(String[] arg) {
        MazeSolver mySolver = new MazeSolver(50);
    }
}
