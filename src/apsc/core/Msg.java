package apsc.core;

/**
 * @author Renan Esposte/Tarcísio Sesso
 */
public class Msg {

    private String msg;
    private String from;
    private String to;
    private boolean interna;
    private String op;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public boolean isInterna() {
        return interna;
    }

    public void setInterna(boolean interna) {
        this.interna = interna;
    }

    public String getOp() {
        return op;
    }

    public void setOp(String op) {
        this.op = op;
    }

    @Override
    public String toString() {
        return from + "," + to + "," + msg + "," + op + "," + interna;
    }
}
