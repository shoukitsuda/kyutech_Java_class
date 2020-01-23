/**
 * int �̑g�̃N���X�D
 */
public class IntPair {
    /**
     * ���v�f
     */
    int i;
    /**
     * ���v�f
     */
    int j;
    /**
     * �e�v�f���󂯎��R���X�g���N�^�D
     * @param i ���v�f�D
     * @param j ���v�f�D
     */
    IntPair(int i, int j) {
        this.i = i;
        this.j = j;
    }
    /**
     * �R�s�[�R���X�g���N�^�D
     * @param p �R�s�[���� {@code IntPair}�D
     */
    IntPair(IntPair p) {
        this.i = p.i;
        this.j = p.j;
    }
    /**
     * �w�肳�ꂽ�I�u�W�F�N�g�Ƃ̓��l������D
     * �w�肳�ꂽ�I�u�W�F�N�g�� {@code IntPair} �ł���C���C���v�f�Ƒ��v�f���Ƃ��ɓ�������Γ����ł���D
     * @param o �w�肳�ꂽ�I�u�W�F�N�g�D
     * @return �w�肳�ꂽ�I�u�W�F�N�g�Ɠ����Ȃ� {@code true}�C�����łȂ���� {@code false}�D
     */
    public boolean equals(Object o) {
        if(o == null) return false;  // ���肪 null �Ȃ� false
        if(o instanceof IntPair) {   // o �� IntPair �̃C���X�^���X�Ȃ�A
            IntPair ip = (IntPair)o; // o �� IntPair �̃C���X�^���X�Ƃ݂Ȃ���
            return ip.i == i && ip.j == j; // ��r
        }
        return false;
    }
    /**
     * ���̃I�u�W�F�N�g�̃n�b�V���l��Ԃ��D
     * {@code this.equals(o) == true} �̂Ƃ��C{@code o.hashCode() == this.hashCode()} �ł���D
     * �Ƃ肠���� Fibonacci hashing (The Art of Computer Programming, Vol. 3, Sect. 6.4) �ő��v�f���΂��D
     * @return ���̃I�u�W�F�N�g�̃n�b�V���l�D
     */
    public int hashCode() {
        return i + j * 0x9e3779b9; // 2654435769;
    }
    /**
     * ���̑g�̕�����\����Ԃ��D
     * @return ���̑g�̕�����\���D
     */
    public String toString() {
        return "(" + i + "," + j + ")";
    }
}