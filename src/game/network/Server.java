package game.network;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.net.ServerSocket;
import com.badlogic.gdx.net.ServerSocketHints;
import com.badlogic.gdx.net.SocketHints;

public class Server extends Thread {
    private ServerSocket socket;
    public final static int DEFAULT_PORT = 65432;

    public Server() {
        this.socket = Gdx.net.newServerSocket(Net.Protocol.TCP,DEFAULT_PORT,new ServerSocketHints());
    }

    @Override
    public void run() {
        while(true){
            new Connected(this.socket.accept(new SocketHints()));
        }
    }
}
