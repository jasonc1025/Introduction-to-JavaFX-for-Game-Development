import javafx.application.Application;
import javafx.stage.Stage;

// Required 'public' or will not run in DrJava nor VSCode 
public class Example1 extends Application 
{
    public static void main(String[] args) 
    {
        launch(args);
    }

    @Override
    public void start(Stage theStage) 
    {
        theStage.setTitle("Hello, World!");
        theStage.show();
    }
}
