import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;


public class LaunchingExternalApps {

    public static void main(String[] args) throws InterruptedException, IOException {
        LaunchingExternalApps obj = new LaunchingExternalApps();
        if (!obj.executeCommand("docker ps").contains("CONTAINER")) {
            File file = new File("C:\\Program Files\\Docker\\Docker\\Docker for Windows.exe");
            Desktop.getDesktop().open(file);
            while (!obj.executeCommand("docker ps").contains("CONTAINER")) {
                Thread.sleep(5000);
                System.out.println("Docker is starting...");
                if (obj.executeCommand("docker ps").contains("CONTAINER")) {
                    System.out.println("Docker is running");
                }
            }
        }
    }

    private String executeCommand(String command) {
        StringBuffer output = new StringBuffer();
        Process p;
        try {
            p = Runtime.getRuntime().exec(command);
            p.waitFor();
            BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line = "";
            while ((line = reader.readLine()) != null) {
                output.append(line + "\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return output.toString();
    }

}


