import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Main {
    static Scanner scan = new Scanner(System.in);
    static String path = "C:\\Users\\Глеб\\IdeaProjects\\Reminder\\src\\";

    public static void main(String[] args) throws UnsupportedAudioFileException, LineUnavailableException, IOException, InterruptedException {
        List<String> dates = new ArrayList<>();
        String message = "";
        String song = "";
        SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        System.out.println("Введите тип мелодии: 1, 2, 3: ");
        String songtype = scan.nextLine();
        System.out.println("Введите дату и время (yyyy-MM-dd HH:mm:ss) на которое вы хотите поставить напоминание");
        String firstMes = scan.nextLine();


        dates.add(firstMes);
        while (!message.equals("exit")){
            System.out.println("Если хотите поставить ещё напоминание, напишите дату и время. В противном случае, exit");
            message = scan.nextLine();
            dates.add(message);
        }
        dates.remove("exit");
        switch (songtype){
            case "1":
                song = "1.wav";
                break;
            case "2":
                song = "2.wav";
                break;
            case "3":
                song = "3.wav";
                break;
        }

        while(!dates.isEmpty()){
            Date d = new Date();
            for (String o : dates) {
                if (o.equals(formater.format(d))) {
                    System.out.println("Напоминание " + formater.format(d));
                    playMusic(song);
                    dates.remove(o);
                }
            }
        }
    }


    public static void playMusic(String filename) throws UnsupportedAudioFileException, IOException, LineUnavailableException, InterruptedException {
        path = path + filename;
        File file = new File(path);
        if (file.exists()){
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
            Thread.sleep(10000);
            clip.stop();
        }
    }
}