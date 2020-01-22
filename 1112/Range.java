import java.util.*;

// �C���^�[�t�F�[�X�̎�����2
class Range implements Iterator<Integer>, Iterable<Integer> {
    int end;
    int cur;
    Range(int e) {
        this.end = e;
        this.cur = 0;
    }
    // Iterator<Integer> �̃��\�b�h
    public boolean hasNext() {
        return cur < end;
    }
    // Iterator<Integer> �̃��\�b�h
    public Integer next() {
        Integer ret = Integer.valueOf(cur);
        cur++;
        return ret;
    }
    // Iterable<Integr> �̃��\�b�h
    public Iterator<Integer> iterator() {
        return this;
    }
}

// Range �̓���`�F�b�N�p
class RangeCheck {
    public static void main(String [] args) {
        // Range �� Iterable<Integer> ���p���i�����j����̂ŁA
        // �g��for�����g�����Ƃ��ł���
        for(Integer i : new Range(5)) {
            System.out.println("i = " + i);
        }
    }

}