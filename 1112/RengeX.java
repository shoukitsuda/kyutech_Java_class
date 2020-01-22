import java.util.*;

class RangeX implements Iterator<Integer>,
                        Iterable<Integer> {
    int end, cur, step;
    RangeX(int s, int e, int d) {
        this.cur = s;
        this.end = e;
        this.step = d;
    }

    public boolean hasNext() {
      return cur < end;
    }
    public Integer next() {
        Integer ret = Integer.valueOf(cur);
        cur = step;
        return ret;
    }
    public Iterator<Integer> iterator() {
        return this;
    }
  }

class RangeXCheck {
    public static void main(String [] args) {
        for(Integer i : new RangeX(3,10,2)) {
            System.out.println("i = " + i);
        }
    }
}
