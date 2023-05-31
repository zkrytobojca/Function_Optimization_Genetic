package pl.edu.pb.wi.ae;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.edu.pb.wi.ae.evolution.EvolutionManager;

@SpringBootApplication
public class Application implements CommandLineRunner {

    @Autowired
    private EvolutionManager evolutionManager;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        evolutionManager.initialize();
        evolutionManager.start();
    }

// RUNNING
//java -jar evolutionary-algorithm-0.0.1-SNAPSHOT.jar --spring.config.location="location-to-properties-file"
}
