import java.util.*;
import java.math.*;
import java.io.*;


class BIwithMem {

    private BigInteger val;
    private HashMap<String, BigInteger> mem;

    BIwithMem(){
        val = BigInteger.ZERO;
        mem = new HashMap<String, BigInteger>();
    }

    BigInteger getValue(){
        return val;
    }

    void setValue(BigInteger bi){
        val = bi;
    }

    void store(String x, BigInteger a){
        mem.put(x,a);
    }

    BigInteger load(String x) {
        BigInteger bi = mem.get(x);
        if(bi == null) {
            throw new RuntimeException("Unknown variable: " + x);
        }
        return bi;
    }

    public String toString() {
        return val.toString();
    }

    public String dumpVars() {
        StringBuffer sb = new StringBuffer();
        for(Map.Entry<String, BigInteger> e : mem.entrySet()) {
            String var = e.getKey();
            BigInteger bi = e.getValue();
            sb.append(" " + var + " = " + bi + "\n");
        }
        return sb.toString();
    }
}


abstract class MonoOpBIWM implements Command<BIwithMem> {
    abstract String opeStr();
    abstract BigInteger op(BigInteger a);

    public boolean accept(String [] tokens) {
        return tokens.length == 1 && tokens[0].equals(opeStr());
    }

    public BIwithMem exec(BIwithMem res) {
        res.setValue(op(res.getValue()));
        return res;
    }
}


abstract class BIorVarWM implements Command<BIwithMem> {

    BigInteger parseOrLoad(BIwithMem res, String arg) {
        try {
            return new BigInteger(arg);
        } catch(NumberFormatException e){
        }
        BigInteger bi = res.load(arg);
        return bi;
    }
}


abstract class BinOpBIWM extends BIorVarWM {

    abstract String opeStr();

    abstract BigInteger op(BigInteger a, BigInteger b);

    String x;

    public boolean accept(String [] tokens) {
        if(tokens.length != 2) return false;
        if(!tokens[0].equals(opeStr())) return false;
        x = tokens[1];
        return true;
    }

    public BIwithMem exec(BIwithMem res) {
        BigInteger bi = parseOrLoad(res, x);
        res.setValue(op(res.getValue(), bi));
        return res;
    }
}

class NegBIWM extends MonoOpBIWM {
    String opeStr() { return "neg"; }
    BigInteger op(BigInteger a) {
        return a.negate();
    }
}
class AddBIWM extends BinOpBIWM {
    String opeStr() { return "+"; }
    BigInteger op(BigInteger a, BigInteger b) {
        return a.add(b);
    }
}
class SubBIWM extends BinOpBIWM {
    String opeStr() { return "-"; }
    BigInteger op(BigInteger a, BigInteger b) {
        return a.subtract(b);
    }
}
class MulBIWM extends BinOpBIWM {
    String opeStr() { return "*"; }
    BigInteger op(BigInteger a, BigInteger b) {
        return a.multiply(b);
    }
}
class DivBIWM extends BinOpBIWM {
    String opeStr() { return "/"; }
    BigInteger op(BigInteger a, BigInteger b) {
        return a.divide(b);
    }
}


class FactBIWM extends MonoOpBIWM{
    String opeStr() { return "fact"; }
    BigInteger op( BigInteger b) {
        BigInteger f = BigInteger.ONE;
        int n = b.intValue();
        for (int i = n ; i > 1 ;i--) {
            BigInteger j = BigInteger.valueOf(i);
            f = f.multiply(j);
        }
        b = f;
        return b;
    }
}

class ECDSABIWM extends BinOpBIWM {
    String opeStr() { return "ecdsa"; }

    BigInteger op(BigInteger a, BigInteger b) {
        //パラメータグループの設定
        BigInteger s = new BigInteger("0000000000000000000000000000000000000000000000000000000000000000");
        BigInteger t = new BigInteger("0000000000000000000000000000000000000000000000000000000000000007");
        BigInteger p = new BigInteger("115792089237316195423570985008687907853269984665640564039457584007908834671663");
        BigInteger x = new BigInteger("55066263022277343669578718895168534326250603453777594175500187360389116729240");
        BigInteger y = new BigInteger("32670510020758816978083085130507043184471273380659243275938904335757337482424");


        //パラメータの値を整形
        BigInteger x3 = x.pow(3);
        BigInteger y2 = y.pow(2);
        //左辺と右辺を計算しておく
        BigInteger left = y2;
        BigInteger right = (x3.add(s.multiply(x)).add(t)).mod(p);
        //secp256k1の計算
        BigInteger ans = right.sqrt();
        //公開鍵を生成
        return a.multiply(ans);
    }
}

class StoreBIWM implements Command<BIwithMem> {

    String x;

    public boolean accept(String [] tokens) {
        if(tokens.length != 2) return false;       // ���������邩�H
        if(!tokens[0].equals("store")) return false; // �ŏ������Z�q���H
        x = tokens[1];            // �ϐ������L�^
        return true;   // �����܂ŗ����� OK
    }

    public BIwithMem exec(BIwithMem res) {
        res.store(x, res.getValue());
        return res;
    }
}


class LoadBIWM implements Command<BIwithMem> {

    String x;

    public boolean accept(String [] tokens) {
        if(tokens.length != 2) return false;       // ���������邩�H
        if(!tokens[0].equals("load")) return false; // �ŏ������Z�q���H
        x = tokens[1];            // �ϐ������L�^
        return true;   // �����܂ŗ����� OK
    }

    public BIwithMem exec(BIwithMem res) {
        BigInteger bi = res.load(x);
        res.setValue(bi);
        return res;
    }
}

class ShowVarsBIWM implements Command<BIwithMem> {

    public boolean accept(String [] tokens) {
        return tokens.length == 1 && tokens[0].equals("show");
    }

    public BIwithMem exec(BIwithMem res) {
        System.out.println(res.dumpVars());
        return res;
    }
}

class LoadImmBIWM extends BIorVarWM {

    String x;

    public boolean accept(String [] tokens) {
        if(tokens.length != 1) return false;       // �g�[�N����H
        x = tokens[0];       // ���� or �ϐ����o���Ă���
        return true;   // �����܂ŗ����� OK
    }

    public BIwithMem exec(BIwithMem res) {
        BigInteger bi = parseOrLoad(res, x);
        res.setValue(bi);
        return res;
    }
}



class BICalculatorWithVar {
    public static void main(String [] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        List<Command<BIwithMem>> cmds = new ArrayList<Command<BIwithMem>>();
        cmds.add(new AddBIWM());
        cmds.add(new SubBIWM());
        cmds.add(new MulBIWM());
        cmds.add(new DivBIWM());
        cmds.add(new NegBIWM());
        cmds.add(new FactBIWM());
        cmds.add(new ECDSABIWM());

        cmds.add(new LoadBIWM());
        cmds.add(new StoreBIWM());
        cmds.add(new ShowVarsBIWM());
        cmds.add(new LoadImmBIWM());

        Calculator<BIwithMem> calc = new Calculator<BIwithMem>(cmds);

        BIwithMem biwm = new BIwithMem();
        calc.run(biwm, br);
    }


}