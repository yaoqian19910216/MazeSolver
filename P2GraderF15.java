package graders;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

import data_structures.LinearList;
import data_structures.LinearListADT;
import data_structures.Queue;
import data_structures.Stack;

public class P2GraderF15 {

  LinearListADT<Integer> lut;

  static final int SmallTestSize = 10;
  static final int MediumTestSize = 50;
  static final int LargeTestSize = 50000;

  private void buildFront(int number) {
    for (int i = 1; i < number + 1; i++) {
      lut.addFirst(i);
    }
  }

  private void buildLast(int number) {
    for (int i = 1; i < number + 1; i++) {
      lut.addLast(i);
    }
  }

  private void iterateListContents(int limit) {
    int counter = 0;
    StringBuffer buffer = new StringBuffer();
    buffer.append('\n');
    for (Integer entry : lut) {
      buffer.append(entry + " ");
      if (counter > limit) {
        displayError("Iterator continued past limit");
        break;
      }
      counter++;
    }
    System.out.println(buffer.toString());
    displayNotice("Iterated through " + counter + " items");
  }

  private boolean testAddFirst(int size) {

    lut.clear();

    try {
      displayTestStart("addFirst: Expect 10-1");
      buildFront(size);
      if (size != lut.size()) {
        displayError(
            "Size mismatch - Expected: " + size + " Actual: " + lut.size());
      }
    } catch (Exception e) {
      displayError("addFirst exception");
    }
    try {
      iterateListContents(size);
    } catch (Exception e) {
      displayError("addFirst iteration exception");
    }
    try {
      if (lut.peekLast() != 1) {
        displayError("addFirst peekLast Expected: 1 Actual: " + lut.peekLast());
      }
    } catch (Exception e) {
      displayError("addFirst peekLast exception");
    }
    displayComplete();
    return true;
  }

  private boolean testAddLast(int size) {

    lut.clear();

    try {
      displayTestStart("addLast: Expect 1-10");
      buildLast(size);
      if (size != lut.size()) {
        displayError(
            "Size mismatch - Expected: " + (size) + " Actual: " + lut.size());
      }
    } catch (Exception e) {
      displayError("addLast exception");
    }
    try {
      iterateListContents(size);
    } catch (Exception e) {
      displayError("addLast iteration exception");
    }
    try {
      if (lut.peekFirst() != 1) {
        displayError("addLast peekFirst Expected: 1 Actual: " + lut.peekFirst());
      }
    } catch (Exception e) {
      displayError("addLast peekFirst exception");
    }
    displayComplete();
    return true;
  }

  private boolean testRemoveFirst(int size) {

    lut.clear();
    buildLast(size);

    displayTestStart("removeFirst: expect 0 item iteration");
    try {

      int count = size;
      while (count > 0) {
        Integer entry = lut.removeLast();
        if (entry != count) {
          displayError("removeFirst: did not match. Expected: " + count
              + " Actual: " + entry);
        } else if (lut.size() != count - 1) {
          displayError("removeFirst: size incorrect. Expected: " + (count - 1)
              + " Actual: " + lut.size());
        } else {
          System.out.print(". ");
        }
        count--;
      }
    } catch (Exception e) {
      displayError("removeFirst: exception");
    }
    try {
      iterateListContents(size);
    } catch (Exception e) {
      displayError("removeFirst: iteration exception");
    }
    try {
      if (lut.peekFirst() != null || lut.peekLast() != null) {
        displayError("removeFirst: peekFirst/peekLast incorrect");
      }
    } catch (Exception e) {
      displayError("removeFirst: peekFirst/peekLast produced exception");
    }
    try {
      if (lut.removeFirst() != null || lut.removeLast() != null) {
        displayError("removeFirst: removing from empty not null");
      }
    } catch (Exception e) {
      displayError("removeFirst: removing from empty exception");
    }

    displayComplete();
    return true;
  }

  private boolean testRemoveLast(int size) {

    lut.clear();
    buildFront(size);

    displayTestStart("removeLast: expect 0 item iteration");
    try {

      int count = 0;
      while (count < size) {
        Integer entry = lut.removeLast();
        if (entry != count + 1) {
          displayError("removeLast: did not match. Expected: " + (count + 1)
              + " Actual: " + entry);
        } else if (lut.size() != size - count - 1) {
          displayError("removeLast: size incorrect. Expected: "
              + (size - count - 1) + " Actual: " + lut.size());
        } else {
          System.out.print(". ");
        }
        count++;
      }
    } catch (Exception e) {
      displayError("removeLast: exception");
    }
    try {
      iterateListContents(size);
    } catch (Exception e) {
      displayError("removeLast: iteration exception");
    }
    try {
      if (lut.peekFirst() != null || lut.peekLast() != null) {
        displayError("removeFirst: peekFirst/peekLast incorrect");
      }
    } catch (Exception e) {
      displayError("removeFirst: peekFirst/peekLast produced exception");
    }
    try {
      if (lut.removeFirst() != null || lut.removeLast() != null) {
        displayError("removeLast: Removing from empty not null");
      }
    } catch (Exception e) {
      displayError("removeLast: removing from empty exception");
    }

    displayComplete();
    return true;
  }

  private boolean testRemove(int size) {

    int halfSize = size >> 1;

    lut.clear();
    buildFront(halfSize);
    buildLast(halfSize);

    try {
      displayTestStart(
          "remove( new Object ): removing first set of odd numbers");
      for (int i = 1; i <= halfSize; i += 2) {
        if (lut.remove(new Integer(i)) == null) {
          displayError("Remove returned null at " + i);
        }
      }
      iterateListContents(size);
      if (lut.peekFirst() != halfSize - 1) {
        displayError("Unexpected first element: " + lut.peekFirst() + '\n');
      }
      displayTestStart(
          "remove( new Object ): removing second set of odd numbers");
      try {
        for (int i = 1; i <= halfSize; i += 2) {
          if (lut.remove(new Integer(i)) == null) {
            displayError("Remove returned null at " + i);
          }
        }
        iterateListContents(size);
        if (lut.peekLast() != halfSize - 1) {
          displayError("Unexpected last element: " + lut.peekLast() + '\n');
        }
      } catch (Exception e) {
        displayError("remove(new Object) threw exception");
      }
      displayTestStart(
          "remove( new Object ): removing first set of even numbers");
      try {
        for (int i = 2; i < halfSize; i += 2) {
          if (lut.remove(new Integer(i)) == null) {
            displayError("Remove returned null at " + i);
          }
        }
      } catch (Exception e) {
        displayError("remove(new Object) threw exception");
      }
      iterateListContents(size);
      displayTestStart(
          "remove( new Object ): removing second set of even numbers");
      for (int i = 2; i < halfSize; i += 2) {
        if (lut.remove(new Integer(i)) == null) {
          displayError("Remove returned null at " + i);
        }
      }
      iterateListContents(size);

      try {
        if (lut.peekFirst() != null || lut.peekLast() != null) {
          displayError(
              "remove: peekFirst/peekLast invalid after final item removed");
        }
      } catch (Exception e) {
        displayError("remove: peekFirst/peekLast threw exception.");
      }
      displayComplete();
    } catch (Exception e) {
      displayError("remove( new Object ) threw exception" + '\n');
    }

    return true;
  }

  private boolean testFind(int size) {
    lut.clear();
    buildFront(size);
    displayTestStart("find( new Integer )");

    for (int i = 0; i < size; i++) {
      Integer target = new Integer(i + 1);
      try {
        if (lut.find(target) == null) {
          displayError("Did not find " + target + " in list" + '\n');
          displayNotice("Aborting find().");
          break;
        }
      } catch (Exception e) {
        displayError("find( " + target + " ) threw exception");
      }
    }

    displayComplete();
    return true;
  }

  private boolean testContains(int size) {

    lut.clear();
    buildFront(size);

    displayTestStart("contains( new Integer )");

    for (int i = 0; i < size; i++) {
      Integer target = i + 1;
      try {
        if (lut.contains(target) == false) {
          displayError("Did not contain object in list: " + target);
          displayNotice("Aborting contains.");
          break;
        }
      } catch (Exception e) {
        displayError("contains( " + target + " ) threw exception");
      }
    }
    displayComplete();

    return true;
  }

  private boolean testConcurrentIterator() {

    lut.clear();
    buildFront(SmallTestSize);

    displayTestStart(
        "ConcurrentModificationException with reported size: " + lut.size());
    try {
      for (Integer entry : lut) {
        lut.clear();
      }
      displayNotice("Clear did not throw concurrent exception");
    } catch (ConcurrentModificationException e) {

    } catch (Exception e) {
      displayNotice("Incorrect exception testing .clear(): " + e);
    }
    try {
      lut.clear();
      buildFront(SmallTestSize);
      for (Integer entry : lut) {
        lut.remove(entry);
      }
      displayNotice("Remove(entry) did not throw concurrent exception");

    } catch (ConcurrentModificationException e) {
      displayComplete();
      return true;
    } catch (Exception e) {
      displayNotice("Incorrect exception " + e);
    }
    displayError("Missing ConcurrentModificationException" + '\n');
    return false;
  }

  private void testStack(int size) {

    Stack<Integer> sut = new Stack<Integer>();

    displayTestStart("Stack Implementation");
    try {
      for (int i = 0; i < size; i++) {
        sut.push((Integer) i);
      }
    } catch (Exception e) {
      displayError("Exception during push() on stack");
    }

    try {
      int counter = size - 1;
      Iterator<Integer> it = sut.iterator();
      while (it.hasNext()) {
        Integer entry = it.next();
        if (entry != counter) {
          displayError("Iterator expected: " + counter + " Actual: " + entry);
          displayNotice("Aborting iteration test");
          break;
        }
        counter--;
      }

    } catch (Exception e) {
      displayError("Exception during iteration");
    }

    try {
      for (int i = size - 1; i >= 0; i--) {
        if (sut.pop() != i) {
          displayError("Invalid pop element. Expected: " + i);
          displayNotice("Aborting pop test");
          break;
        }
      }
    } catch (Exception e) {
      displayError("Exception during pop" + '\n');
    }

  }

  private void testQueue(int size) {

    Queue<Integer> sut = new Queue<Integer>();

    displayTestStart("Queue Implementation");
    try {
      for (int i = 0; i < size; i++) {
        sut.enqueue((Integer) i);
      }
    } catch (Exception e) {
      displayError("Exception during enqueue() on queue");
    }

    try {
      int counter = 0;
      Iterator<Integer> it = sut.iterator();
      while (it.hasNext()) {
        Integer entry = it.next();

        if (entry != counter) {
          displayError("Iterator expected: " + counter + " Actual: " + entry);
          displayNotice("Aborting iteration test");
          break;
        }
        counter++;
      }
    } catch (Exception e) {
      displayError("Exception during iteration");
    }

    try {
      for (int i = 0; i < size; i++) {
        if (sut.dequeue() != i) {
          displayError("Invalid dequeue element. Expected: " + i);
          displayNotice("Aborting dequeue test");
          break;
        }
      }
    } catch (Exception e) {
      displayError("Exception during dequeue" + '\n');
    }

  }

  private void initialize() {
    lut = new LinearList<Integer>();
  }

  private void runTests() {
    long startTime;

    startTime = System.nanoTime();
    testAddFirst(SmallTestSize);
    displayElapsedTime(startTime);

    startTime = System.nanoTime();
    testAddLast(SmallTestSize);
    displayElapsedTime(startTime);

    startTime = System.nanoTime();
    testFind(LargeTestSize);
    displayElapsedTime(startTime);

    startTime = System.nanoTime();
    testContains(LargeTestSize);
    displayElapsedTime(startTime);

    startTime = System.nanoTime();
    testRemoveFirst(SmallTestSize);
    displayElapsedTime(startTime);

    startTime = System.nanoTime();
    testRemoveLast(SmallTestSize);
    displayElapsedTime(startTime);

    startTime = System.nanoTime();
    testRemove(MediumTestSize);
    displayElapsedTime(startTime);

    startTime = System.nanoTime();
    testConcurrentIterator();
    displayElapsedTime(startTime);

    startTime = System.nanoTime();
    testStack(LargeTestSize);
    displayElapsedTime(startTime);

    startTime = System.nanoTime();
    testQueue(LargeTestSize);
    displayElapsedTime(startTime);

  }

  private void displayComplete() {
    System.out.print("Complete" + '\n');
  }

  private void displayElapsedTime(long startingTime) {
    System.out.println("Elapsed Time: "
        + Double.toString(((System.nanoTime() - startingTime)) / 1000000.0)
        + " mS" + '\n');
  }

  private void displayTestStart(String testName) {
    System.out.print("-- Now Testing " + testName + " . . . ");
  }

  private void displayNotice(String message) {
    System.out.println('\n' + "Note: " + message);
  }

  private void displayError(String message) {
    System.out.print('\n' + "**Error: " + message);
  }

  public P2GraderF15() {
    initialize();
    try {
      runTests();
    } catch (Exception e) {
      displayError("General Error");
    }

  }

  public static void main(String[] args) {
    new P2GraderF15();
    System.out.println("Testing complete. Farewell.");
  }

}
