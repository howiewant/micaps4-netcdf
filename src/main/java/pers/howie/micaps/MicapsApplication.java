package pers.howie.micaps;

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import picocli.CommandLine;
import picocli.CommandLine.IFactory;

@SpringBootApplication
public class MicapsApplication implements CommandLineRunner, ExitCodeGenerator {

    private IFactory factory;        
    private MicapsCommand micapsCommand;
    private int exitCode;

    // constructor injection
    MicapsApplication(IFactory factory, MicapsCommand micapsCommand) {
        this.factory = factory;
        this.micapsCommand = micapsCommand;
    }

    @Override
    public void run(String... args) {
        // let picocli parse command line args and run the business logic
        exitCode = new CommandLine(micapsCommand, factory).execute(args);
    }

    @Override
    public int getExitCode() {
        return exitCode;
    }

    public static void main(String[] args) {
        // let Spring instantiate and inject dependencies
        System.exit(SpringApplication.exit(SpringApplication.run(MicapsApplication.class, args)));
    }
}