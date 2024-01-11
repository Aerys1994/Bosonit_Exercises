package Components;
import javax.annotation.PostConstruct;


public class PostConstructor {
    @PostConstruct
    public void postConstruct() {
        System.out.println("Hola desde clase inicial");
    }
}
