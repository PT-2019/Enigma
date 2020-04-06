package game.network;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.net.Socket;
import com.badlogic.gdx.net.SocketHints;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Client {
    private BufferedReader reader;
    private BufferedWriter writer;

    public Client(String host){
        Socket socket = Gdx.net.newClientSocket(Net.Protocol.TCP,host,Server.DEFAULT_PORT,new SocketHints());
        this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
    }
}
