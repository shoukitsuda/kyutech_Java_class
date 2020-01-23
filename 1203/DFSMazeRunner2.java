/**
 * �R�}���h���C�������Ɏw�肵���e�L�X�g�t�@�C��������H��ǂݍ��݁A�E�o�o�H��\������{@code main} �����D�T���͐[���D��T���ŁD
 */
class DFSMazeRunner2 {
    public static void main(String [] args) throws Exception {
        if(args.length < 1) {
            System.out.println("java DFSMazeRunner2 mazefile [-g | -w]");
            System.out.println("  -w : the mazefile is of text-mazes with warps");
            System.out.println("  -g : the mazefile is of graph-mazes");
            return;
        }
        boolean graph = false;
        boolean warp = false;
        if(args.length >= 2) {
            if(args[1].equals("-g")) graph = true;
            if(args[1].equals("-w")) warp = true;
        }
        // TextMaze �� TextMazeWithWarp �� GraphMaze �� Maze<Pos> ����������̂ŁA
        // �S�Ăɑ΂��ē��� DFSMazeSolver.solve ���g�����Ƃ��ł���B
        if(!graph) {
            if(!warp) {
                DFSMazeSolver.solve(new TextMaze(args[0]));
            } else {
                DFSMazeSolver.solve(new TextMazeWithWarp(args[0]));
            }
        } else {
            DFSMazeSolver.solve(new GraphMaze(args[0]));
        }
    }
}