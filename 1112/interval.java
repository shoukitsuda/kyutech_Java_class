import java.util.*;

class Interval implements Iterator<Integer>,
                        Iterable<Integer>,
                        Comparable<Interval> {
    int start, end, cur;
    Interval(int s, int e) {
        start = s;
        end = e;
        cur = s;
    }

      public boolean hasNext() {
        return cur < end+1;
      }
      public Integer next() {
          Integer ret = Integer.valueOf(cur);
          cur++;
          return ret;
      }
      public Iterator<Integer> iterator() {

          return this;
      }
      public int compareTo(Interval v){
        if(this.start > v.start) return 1;
        if(this.start < v.start) return -1;

        if(this.end > v.end) return 1;
        if(this.end < v.end) return -1;
        return 0;
      }

     public String toString() {
        return "[" + start + "," + end +"]";
    }
}

class IntervalCheck {
    public static void main(String [] args) {
        for(Integer i : new Interval(3,6)) {
            System.out.println("i = " + i);
        }
        Interval [] is = new Interval [] {
            new Interval(2,3),
            new Interval(1,5),
            new Interval(2,6),
            new Interval(3,2),
            new Interval(2,3),
        };
        System.out.print("original:");
        for(Interval i : is) {
            System.out.print(" " + i);
        }
        System.out.println();
        Arrays.sort(is);
        System.out.print("sorted:");
        for(Interval i : is) {
            System.out.print(" " + i);
        }
        System.out.println();
    }
}
