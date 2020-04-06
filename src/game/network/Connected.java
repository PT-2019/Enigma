package game.network;

import com.badlogic.gdx.net.Socket;

import java.io.*;

public class Connected extends Thread {
    private BufferedReader reader;
    private BufferedWriter writer;

    public Connected(Socket s){
        this.reader = new BufferedReader(new InputStreamReader(s.getInputStream()));
        this.writer = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
    }

    @Override
    public void run(){
        while(true){

        }
    }

    public void close() {
        try {
            this.reader.close();
            this.writer.close();
        }catch(IOException e){
            //Ignore
        }
    }
}
