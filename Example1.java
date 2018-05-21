import javafx.application.Application;
import javafx.stage.Stage;

//Remove 'public' to match 'Edhesive:ApCsA' public class Example1 extends Application 
class Example1 extends Application 
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
