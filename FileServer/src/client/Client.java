package client;

import java.io.*;
import java.net.Socket;
import java.util.Objects;
import java.util.Scanner;

public class Client {

    private static final String HOST = "localhost";
    private static final int PORT = 1234;

    public static void main(String[] args) {
        String dataFilePath = "C:\\Users\\Глеб\\IdeaProjects\\FileServer\\src\\client\\data";
        try (Socket socket = new Socket(HOST, PORT)) {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            Scanner scanner = new Scanner(System.in);
            String str = null;
            String response;
            while (!Objects.equals(str, "exit")) {
            System.out.println("Enter action (1 - get a file, 2 - create a file, 3 - delete a file):");
                str = scanner.nextLine();
                switch (str) {
                    case "exit" -> {
                        out.write(str + "\n");
                        System.out.println("The request was sent.");
                        socket.close();
                        in.close();
                        out.close();
                    }
                    case "1" -> {
                        out.write(str + "\n");
                        System.out.println("Do you want to get the file by name or by id (1 — name, 2 — id):");
                        String byIdOrName = scanner.nextLine();
                        out.write(byIdOrName + "\n");
                        if (byIdOrName.equals("1")) {
                            System.out.println("Enter filename:");
                        } else {
                            System.out.println("Enter id:");
                        }
                        String idOrName = scanner.nextLine();
                        out.write(idOrName + "\n");
                        out.flush();
                        System.out.println("The request was sent.");
                        response = in.readLine();
                        System.out.println(response);
                        if (response.equals("200")) {
                            System.out.println("The file was downloaded! Specify a name for it:");
                            String savedFilesName = scanner.nextLine();
                            File file = new File(dataFilePath + "\\" + savedFilesName);
                            FileOutputStream savingFileStream = new FileOutputStream(file);
                            int messageLength = in.read();
                            for (int i = 0; i < messageLength; i++) {
                                savingFileStream.write(in.read());
                            }
                            savingFileStream.close();
                            System.out.println("File saved on the hard drive!");
                        } else {
                            System.out.println("The response says that this file is not found!");
                        }
                    }
                    case "2" -> {
                        out.write(str + "\n");
                        System.out.println("Enter name of the file: ");
                        String newFilename = scanner.nextLine();
                        out.write(newFilename + "\n");
                        System.out.println("Enter name of the file to be saved on server: ");
                        String serverFilename = scanner.nextLine();
                        out.write(serverFilename + "\n");
                        out.flush();
                        File file = new File(dataFilePath + "\\" + newFilename);
                        FileInputStream fileInputStream = new FileInputStream(file);
                        byte[] message = fileInputStream.readAllBytes();
                        int messageLength = message.length;
                        out.write(messageLength);
                        for (byte b : message) {
                            out.write(b);
                        }
                        fileInputStream.close();
                        out.flush();
                        System.out.println("The request was sent.");
                        response = in.readLine();
                        System.out.println(response);
                        if (response.equals("200")) {
                            System.out.println("Response says that file is saved! ID = 0");
                        } else {
                            System.out.println("err");
                        }
                    }
                    case "3" -> {
                        out.write(str + "\n");
                        System.out.println("Do you want to delete the file by name or by id (1 — name, 2 — id):");
                        String byIdOrName = scanner.nextLine();
                        out.write(byIdOrName + "\n");
                        if (byIdOrName.equals("1")) {
                            System.out.println("Enter filename:");
                        } else {
                            System.out.println("Enter id:");
                        }
                        String idOrMessage = scanner.nextLine();
                        out.write(idOrMessage + "\n");
                        out.flush();
                        System.out.println("The request was sent.");
                        response = in.readLine();
                        if (response.equals("200")) {
                            System.out.println("The response says that this file was deleted successfully!");
                        } else {
                            System.out.println("not found");
                        }
                    }
                }
            }
            socket.close();
            in.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
