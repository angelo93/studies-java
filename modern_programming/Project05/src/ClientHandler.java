import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.ArrayList;

public class ClientHandler implements Runnable{
    public static ArrayList<ClientHandler> clientHandlers = new ArrayList<>();
    private Socket socket;
    private BufferedReader buffReader;
    private BufferedWriter buffWriter;
    private String clientName;

    public ClientHandler(Socket socket) {
        this.socket = socket;
        try {
            this.buffWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.buffReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.clientName = buffReader.readLine();

            clientHandlers.add(this);
            broadcastMsg("<SERVER> New Client: " + clientName);
        } catch (IOException e) {
            cleanup(socket, buffReader, buffWriter);
        }
    }

    @Override
    public void run() {
        String msgFromClient;
        boolean status_ok = true;
        while (socket.isConnected() && status_ok == true) {
            try {
                msgFromClient = buffReader.readLine();
                broadcastMsg(msgFromClient);
            } catch (IOException e) {
                status_ok = false;
                cleanup(socket, buffReader, buffWriter);
            }
        }
    }

    public void broadcastMsg(String msgToSend) {
        for (ClientHandler clientHandler : clientHandlers) {
            if (!clientHandler.clientName.equals(clientName)) {
                try {
                    clientHandler.buffWriter.write(msgToSend);
                    clientHandler.buffWriter.newLine();
                    clientHandler.buffWriter.flush();
                } catch (IOException e) {
                    cleanup(socket, buffReader, buffWriter);
                }
            }
        }
    }

    public void delClientHandler() {
        clientHandlers.remove(this);
        broadcastMsg("<SERVER> Client Disconnected: " + clientName);
    }

    public void cleanup(Socket socket, BufferedReader buffReader, BufferedWriter buffWriter) {
        delClientHandler();
        try {
            if (buffReader != null) buffReader.close();
            if (buffWriter != null) buffWriter.close();
            if (socket != null) socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
