import java.io.BufferedReader;
import java.io.InputStreamReader;


public class ExecuteShellComand {

    public static void main(String[] args) throws InterruptedException {
        ExecuteShellComand obj = new ExecuteShellComand();
        String command = "docker ps ";
        String output = obj.executeCommand(command);

        while (!output.contains("CONTAINER")) {
            Thread.sleep(3000);
            System.out.println("Docker is starting...");
        }
        if (output.contains("CONTAINER")) {
            System.out.println("Docker is running");
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
