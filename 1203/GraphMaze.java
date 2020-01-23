import java.util.*;
import java.io.*;
/**
 * ��ʂ̃O���t�ŗ^��������H�N���X�D
 * �ʒu�̓m�[�h���ł��� String�D<br>
 * ���̓t�@�C���̌`���͎��̒ʂ�F<br>
 * �@�擪�s�Ƀm�[�h�� n �ƃG�b�W�� m ���󔒋�؂�ł���D<br>
 * �@���� n �s�́C���ꂼ��󔒂̓����Ă��Ȃ�������ŁC�m�[�h�̖��O��\���D�d���͂Ȃ��D<br>
 * �@���ʂȃm�[�h���͎��̒ʂ�F<br>
 * �@�@�X�^�[�g�̃m�[�h���� "S"�D�K���܂܂��D<br>
 * �@�@�S�[���̃m�[�h���� "G"�D�K���܂܂��D<br>
 * �@�X�ɑ��� m �s�ɁC�G�b�W�̏�񂪗^������D<br>
 * �@�G�b�W�̏��́C���̕ӂ��Ȃ�2�̃m�[�h�̖��O���󔒋�؂�ɂ������́D
 */
class GraphMaze implements Maze<String> {
    /**
     * �m�[�h��
     */
    private int n;
    /**
     * �G�b�W��
     */

    private int m;
    /**
     * ���H���̂̏�� �� �e�m�[�h���Ȃ���m�[�h�̃��X�g�D
     */
    private HashMap<String, List<String>> neighbors;
    /**
     * getNeighbor �Ăяo���񐔁D
     */
    private int cnt;
    /**
     * �X�^�[�g�m�[�h���̒萔�D
     */
    private final static String startNode = "S";
    /**
     * �S�[���m�[�h���̒萔�D
     */
    private final static String goalNode = "G";
    /**
     * �t�@�C�������󂯎��C���H��ǂݍ��ރR���X�g���N�^�D
     * @param file �t�@�C�����i�p�X�j�̕�����D
     */
    GraphMaze(String file) throws Exception {
        // �t�@�C������ 1�s���ǂݍ��ޏ���
        BufferedReader br = new BufferedReader(new FileReader(file));
        // 1�s�ǂݍ��݁A�󔒋�؂�ɂ��鏀��������
        StringTokenizer st = new StringTokenizer(br.readLine());
        // �ŏ����m�[�h��, �����G�b�W��
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        // �m�[�h���̓o�^
        neighbors = new HashMap<String, List<String>>();
        for(int i = 0; i < n; i++) {
            String name = br.readLine(); // �m�[�h��
            neighbors.put(name, new ArrayList<String>()); // ��̗אڃ��X�g�ǉ�
        }
        // �G�b�W�̓o�^
        for(int i = 0; i < m; i++) {
            // 1�s�ǂݍ��݁A�󔒋�؂�ɂ��鏀��������
            st = new StringTokenizer(br.readLine());
            String n1 = st.nextToken(); // �Е��̃m�[�h
            String n2 = st.nextToken(); // �����̃m�[�h
            neighbors.get(n1).add(n2);  // n1 �̗אڃm�[�h���X�g�� n2 ��ǉ�
            neighbors.get(n2).add(n1);  // n2 �̗אڃm�[�h���X�g�� n1 ��ǉ�
        }
        cnt = 0;
    }

    /**
  * @param ps
  */
      public String getStart(){
         return startNode;
       }

    public  List<String> getNeighbors(String p){
        cnt++;
        return neighbors.get(p);
      }

    public boolean isGoal (String p){
             return goalNode.equals(p);
          }

    public void printAnswer(List<String> ps) {
        for(String s : ps) {
            System.out.println(s);
        }
        System.out.println("# of calling getNeighbors = " + cnt);
    }
}
