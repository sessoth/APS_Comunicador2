package apsc.server;

import apsc.core.User;
import java.io.InputStream;
import java.util.Scanner;

/**
 * @author Renan Esposte/Tarcísio Sesso
 */
public class TrataCliente implements Runnable {

    private InputStream cliente;
    private ComunicServ servidor;
    private User user;
    
    public TrataCliente(User user, InputStream cliente, ComunicServ servidor) {
        this.cliente = cliente;
        this.servidor = servidor;
        this.user = user;
    }

    
    public void run() {
        // quando chegar uma msg, distribui pra todos
        Scanner s = new Scanner(this.cliente);
        while (s.hasNextLine()) {
                 servidor.interpretaMensagem(s.nextLine());
        }
        s.close();
    }
}
