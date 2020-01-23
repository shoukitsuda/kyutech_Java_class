/**
 * �R�}���h���C�������Ɏw�肵���e�L�X�g�t�@�C��������H��ǂݍ��݁A�E�o�o�H��\������{@code main} �����D�T���͐[���D��T���ŁD
 */
class DFSTextMazeRunner {
    public static void main(String [] args) throws Exception {
        if(args.length < 1) {
            System.out.println("java DFSTextMazeRunner mazefile");
            return;
        }
        TextMaze tm = new TextMaze(args[0]);
        DFSMazeSolver.solve(tm);
    }
}