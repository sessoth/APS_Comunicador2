package apsc.core;

import java.io.PrintStream;
import java.net.Socket;

/**
 * @author Renan Esposte/Tarcísio Sesso
 */
public class User {

    private String id;
    private String nome;
    private PrintStream saidaCliente;
    private Socket socketCliente;

    public User(String id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public PrintStream getSaidaCliente() {
        return saidaCliente;
    }

    public void setSaidaCliente(PrintStream saidaCliente) {
        this.saidaCliente = saidaCliente;
    }

    @Override
    public String toString() {
        return getNome();
    }

    public Socket getSocketCliente() {
        return socketCliente;
    }

    public void setSocketCliente(Socket socketCliente) {
        this.socketCliente = socketCliente;
    }
}
