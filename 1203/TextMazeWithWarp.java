import java.util.*;
import java.io.*;
/**
 * �e�L�X�g�t�@�C������ǂݍ��ޒ����`�̖��H�N���X�i���[�v����j�D
 * �ʒu�� int �̑g (i, j)�D<br>
 * ���̓t�@�C���̌`���͎��̒ʂ�F<br>
 * �@�擪�s�ɍ��� h �ƕ� w ���󔒋�؂�ł���D<br>
 * �@�ȍ~�� h �s�́C���ꂼ�� w �����̕�����ł�����H�̉���s����\���D <br>
 * �@�@* : ��Q���i�ǁj�D���H�̎���͏�Q���ŕ����Ă���Ɖ��肵�ėǂ��D<br>
 * �@�@S : �X�^�[�g�D�K�����傤�ǈ���݂���D<br>
 * �@�@G : �S�[���D�K�����傤�ǈ���݂���D<br>
 *    [A-E] : ���[�v�����D�e�������X��D�Ή����郏�[�v�o���i�������j�ɔ�ԁD�o���͕K�����݂���D<br>
 *    [a-e] : ���[�v�o���D�e�������X��D<br>
 */
class TextMazeWithWarp extends TextMaze {
    /**
     * ���[�v�o���̏���ێ�����A�z�z��D
     */
    private HashMap<Character,IntPair> warpOuts;
    /**
     * �t�@�C�������󂯎��C���H��ǂݍ��ރR���X�g���N�^�D
     * @param file �t�@�C�����i�p�X�j�̕�����D
     */
    TextMazeWithWarp(String file) throws Exception {
        // TextMaze �̃R���X�g���N�^�𓮂����A�t�@�C�����e�� field �ɓǂݍ���
        super(file);
        // ���[�v�o���̏��������A�z�z��̃C���X�^���X�𐶐�
        warpOuts = new HashMap<Character,IntPair>();

        // TODO: ������ւ�����߂�
        // ���H�Ֆʏ�𑖍����A���[�v�o���̏��� warpOuts �ɏW�߂�
        for(...) {
            for(...) {
                if(...) {
                    warpOuts.put(..., ...);
                }
            }
        }
    }
    /**
     * �w�肳�ꂽ�ʒu{@code p}����ړ��\�Ȉʒu�̃��X�g��Ԃ��D
     * @param p �w��ʒu�D
     * @return �w��ʒu{@code p}����ړ��\�Ȉʒu�̃��X�g�D
     */
    public List<IntPair> getNeighbors(IntPair p) {
        // TODO: ������ւ�𖄂߂�
        // �up �����[�v�����ł���Ȃ�΁Ap �����[�v�o���̍��W�ɕύX���Ă����v������
        if(...) {
            p = ...
        }

        // ���Ƃ̏����͐e�N���X�̏����Ɠ���
        return super.getNeighbors(p);
    }
}
