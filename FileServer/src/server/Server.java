package server;

import java.io.*;
import java.net.Socket;
import java.net.ServerSocket;
import java.util.HashMap;
import java.util.Objects;

public class Server {
    private static final int PORT = 1234;

    public static void main(String[] args) {
        String dataFilePath = "C:\\Users\\Глеб\\IdeaProjects\\FileServer\\src\\server\\data";
        File data = new File(dataFilePath);
        if (!data.exists() || !data.isDirectory()) data.mkdirs();
        System.out.println("Server started!");
        try {
            ServerSocket server = new ServerSocket(PORT);
            server.setReuseAddress(true);
            while (true) {
                Socket socket = server.accept();
                Thread t = new Thread(new ClientHandler(socket));
                t.start();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


static class ClientHandler implements Runnable {
    Socket socket;
    public ClientHandler(Socket socket) {
        this.socket = socket;
    }
    BufferedReader in = null;
    BufferedWriter out = null;

    @Override
    public void run() {
        try {
//            HashMap<Integer, String> ids = new HashMap<>();
//            File idFile = new File("C:\\Users\\Глеб\\IdeaProjects\\FileServer\\src\\server\\data\\ids.txt");
//            if (idFile.exists()) {
//                FileInputStream fileInputStream = new FileInputStream(idFile);
//                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
//                ids = (HashMap<Integer, String>) objectInputStream.readObject();
//                objectInputStream.close();
//                fileInputStream.close();
//            }
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            String str = null;
            while (!Objects.equals(str, "exit")) {
                String path = "C:\\Users\\Глеб\\IdeaProjects\\FileServer\\src\\server\\data\\";
                String byIdOrName;
                String filename;
                String idOrName;
                File file;
//                int id;
                str = in.readLine();
                if (Objects.equals(str, "1")) {
                    byIdOrName = in.readLine();
                    idOrName = in.readLine();
                    if (byIdOrName.equals("1")) {
                        filename = idOrName;
                        path += filename;
                    } else {
//                        id = Integer.parseInt(idOrName);
//                        path += ids.get(id);
                    }
                    file = new File(path);
                    if (file.exists()) {
                        out.write("200\n");
                        out.flush();
                        FileInputStream fileInputStream = new FileInputStream(file);
                        byte[] message = fileInputStream.readAllBytes();
                        int messageLength = message.length;
                        out.write(messageLength);
                        for (byte b : message) {
                            out.write(b);
                        }
                        fileInputStream.close();
                        out.flush();
                    } else {
                        out.write("404");
                    }
                }
                if (Objects.equals(str, "2")) {
                    filename = in.readLine();
                    String OnServerFilename = in.readLine();
                    if (OnServerFilename.equals("")) {
                        OnServerFilename = filename;
                    }
                    path += OnServerFilename;
                    file = new File(path);
                    FileOutputStream savingFileStream = new FileOutputStream(file);
                    int messageLength = in.read();
                    for (int i = 0; i < messageLength; i++) {
                        savingFileStream.write(in.read());
                    }
                    savingFileStream.close();
//                    id = generateNewId(ids);
//                    ids.put(id, OnServerFilename);
                    out.write("200\n");
                }
                if (Objects.equals(str, "3")) {
                    byIdOrName = in.readLine();
                    idOrName = in.readLine();
                    if (byIdOrName.equals("1")) {
                        filename = idOrName;
                        path += filename;
                    } else {
//                        id = Integer.parseInt(idOrName);
//                        path += ids.get(id);
                    }
                    file = new File(path);
                    out.write(file.delete() ? "200\n" : "404\n");
                }
                else if (Objects.equals(str, "exit")) {
                    System.exit(0);
                    socket.close();
                    socket.shutdownInput();
                    socket.shutdownOutput();
                }
            out.newLine();
            out.flush();
        }
            in.close();
            out.close();
            socket.close();
            socket.shutdownInput();
            socket.shutdownOutput();
        } catch (IOException e) {
            System.exit(0);
            throw new RuntimeException(e);
        }
    }
}

//    public static int generateNewId (HashMap <Integer, String> map){
//        return Integer.parseInt(String.valueOf(Math.abs(map.hashCode())));
//    }
}




