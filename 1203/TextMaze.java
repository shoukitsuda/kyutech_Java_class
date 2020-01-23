import java.util.*;
import java.io.*;

public class TextMaze implements Maze<IntPair> {

    int h;

    int w;

    char [][] field;

    IntPair start;

    IntPair goal;

    int cnt;

    TextMaze(String file) throws Exception {
        // �t�@�C������ 1�s���ǂݍ��ޏ���
        BufferedReader br = new BufferedReader(new FileReader(file));
        // 1�s�ǂݍ��݁A�󔒋�؂�ɂ���
        StringTokenizer st = new StringTokenizer(br.readLine());
        // �ŏ��������A������
        h = Integer.parseInt(st.nextToken());
        w = Integer.parseInt(st.nextToken());
        // ���H�Ֆʂ��L�^����z��쐬
        field = new char[h][w];
        // ���H�ՖʂɁA�t�@�C�����當����ǂݍ���
        for(int i = 0; i < h; i++) {
            String line = br.readLine(); // 1�s�ǂݍ���
            for(int j = 0; j < w; j++) {
                field[i][j] = line.charAt(j);
                if(field[i][j] == 'S') { // �X�^�[�g�ʒu�̋L�^
                    start = new IntPair(i, j);
                } else if(field[i][j] == 'G') { // �S�[���ʒu�̋L�^
                    goal = new IntPair(i, j);
                }
            }
        }
        cnt = 0; // �J�E���^������
    }

    public IntPair getStart() {
        return new IntPair(start); // �O�ׁ̈A�R�s�[���ĕԂ�
    }
    // 4���� (1,0), (0,1), (-1,0), (0,-1)
    private static final int [] di = new int[] {1, 0, -1, 0};
    private static final int [] dj = new int[] {0, 1, 0, -1};

    public List<IntPair> getNeighbors(IntPair p) {
        cnt++; // getNeightbors �̌Ăяo���񐔃J�E���g
        ArrayList<IntPair> ps = new ArrayList<IntPair>();
        if(field[p.i][p.j] == '*') { // �ǂ̒��ɂ��� -> �ړ��ł��Ȃ�
            return ps;
        }
        for(int k = 0; k < 4; k++) { // 4 ���������Ɋm�F
            int ii = p.i + di[k];
            int jj = p.j + dj[k];
            // �אڈʒu (ii, jj) ���ǂłȂ��Ȃ�ړ��\�ʒu�Ƃ��� ps �ɒǉ�
            if(ii >= 0 && jj >= 0 && ii < h && jj < w && field[ii][jj] != '*') {
                ps.add(new IntPair(ii, jj));
            }
        }
        return ps;
    }

    public boolean isGoal(IntPair p) {
        return goal.equals(p);
    }

    public void printAnswer(List<IntPair> ps) {
        char [][] lines = new char[h][w];  // �Ֆʂ��R�s�[����
        for(int i = 0; i < h; i++) {
            for(int j = 0; j < w; j++) {
                lines[i][j] = field[i][j];
            }
        }
        for(IntPair p : ps) {  // �ړ��o�H�� + ��u���Ă���
            // �󔒂̂Ƃ���ȊO�ɂ͌o�H�\�������Ȃ�
            if(lines[p.i][p.j] != ' ') continue;
            lines[p.i][p.j] = '.';
        }
        for(int i = 0; i < h; i++) { // �ړ��o�H����ꂽ�Ֆʂ��o��
            System.out.println(new String(lines[i]));
        }
        System.out.println("# of calling getNeighbors = " + cnt);
    }
}
