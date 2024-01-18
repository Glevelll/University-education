import javax.sound.sampled.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class Reminder {
    static SimpleDateFormat form = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    static Date d = new Date();

    public static void main(String[] args) throws UnsupportedAudioFileException, LineUnavailableException, IOException, InterruptedException {
        File dates = new File("src\\events");
        String song = "";
        FileWriter writer = new FileWriter(dates, false);
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите enter");
        String command = sc.nextLine();

        while (!Objects.equals(command, "exit")) {
                System.out.println("1 - Предстоящие события, 2 - Добавить события, 3 - Закрыть");
                command = sc.nextLine();
                if (Objects.equals(command, "1")) {
                    String fileName = "src\\events";
                    try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
                        String line;
                        while ((line = reader.readLine()) != null) {
                            System.out.println(line);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                if (Objects.equals(command, "2")) {
                    System.out.println("Введите add ");
                    String message = sc.nextLine();

                    while (!message.equals("exit")) {
                        System.out.println("1 - Добавить, 2 - Выйти");
                        String instruction = sc.nextLine();
                        if(Objects.equals(instruction, "1")) {
                            System.out.println("Номер мелодии (1-4) ");
                            String num = sc.nextLine();
                            System.out.println("Укажите дату, время и название события");
                            message = sc.nextLine();
                            writer.write(num + " " + message + "\n");
                        }
                        if (instruction.equals("2")) {
                            message = "exit";
                        }
                    }
                    writer.close();
                }
                if(Objects.equals(command, "3")){
                    break;
                }
        }

            try (BufferedReader br = new BufferedReader(new FileReader(dates))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String partDate = line.substring(2, 21);
                    waitMusic(partDate);
                    String partSong = line.substring(0,1);
                    switch (partSong) {
                        case "1" -> song = "1.wav";
                        case "2" -> song = "2.wav";
                        case "3" -> song = "3.wav";
                        case "4" -> song = "4.wav";
                    }
                    System.out.println(line);
                    playMusic(song);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    public static void playMusic(String filename) throws UnsupportedAudioFileException, IOException, LineUnavailableException, InterruptedException {
        String path = "C:\\Users\\Глеб\\IdeaProjects\\Reminder\\src\\";
        path = path + filename;
        File file = new File(path);
        if (file.exists()) {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
            Thread.sleep(10000);
            clip.stop();
        }
    }

    public static void waitMusic(String line) {
        while (!(line.equals(form.format(d)))) {
            Date currentDate = new Date();
            if (form.format(currentDate).equals(line)) {
                return;
            }
        }
    }
}