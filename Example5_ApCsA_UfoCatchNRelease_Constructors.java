import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;

// Collect the Money Bags!
public class Example5_ApCsA_UfoCatchNRelease_Constructors extends Application 
{
    public static void main(String[] args) 
    {
        launch(args);
    }

    @Override
    public void start(Stage theStage) 
    {
        theStage.setTitle( "UFO Catch n Release" );

        Group root = new Group();
        Scene theScene = new Scene( root );
        theStage.setScene( theScene );

        Canvas canvas = new Canvas( 512, 512 );
        root.getChildren().add( canvas );

        ArrayList<String> playerInputList = new ArrayList<String>();

        theScene.setOnKeyPressed(
            new EventHandler<KeyEvent>()
            {
                public void handle(KeyEvent e)
                {
                    String playerInputCode = e.getCode().toString();
                    if ( !playerInputList.contains(playerInputCode) )
                        playerInputList.add( playerInputCode );
                }
            });

        theScene.setOnKeyReleased(
            new EventHandler<KeyEvent>()
            {
                public void handle(KeyEvent e)
                {
                    String playerInputCode = e.getCode().toString();
                    playerInputList.remove( playerInputCode );
                }
            });

        GraphicsContext gc = canvas.getGraphicsContext2D();

        Font theFont = Font.font( "Helvetica", FontWeight.BOLD, 24 );
        gc.setFont( theFont );
        gc.setFill( Color.GREEN );
        gc.setStroke( Color.BLACK );
        gc.setLineWidth(1);
        
        // IconClass briefcaseObject = new IconClass();
        // briefcaseObject.setImage("briefcase.png");
        IconClass briefcaseObject = new IconClass("briefcase.png", 200, 0);
        // briefcaseObject.setImage("briefcase.png");
        // briefcaseObject.setPosition(200, 0);
        
        ArrayList<IconClass> ufoList = new ArrayList<IconClass>();
        
        for (int i = 0; i < 15; i++)
        {
            // IconClass ufoObject = new IconClass();
//[jwc]            moneybag.setImage("moneybag.png");
//            [jwc] whitebackground not great: moneybag.setImage("cookie-chocolatechip.png");
            // ufoObject.setImage("ufo.png");
            double px = 350 * Math.random() + 50;
            double py = 350 * Math.random() + 50;          
            // ufoObject.setPosition(px,py);
            IconClass ufoObject = new IconClass("ufo.png", px, py);
            ufoList.add( ufoObject );
        }
        
        LongValue lastNanoTime = new LongValue( System.nanoTime() );

        IntValue score = new IntValue(0);

        new AnimationTimer()
        {
            public void handle(long currentNanoTime)
            {
                // calculate time since last update.
                double elapsedTime = (currentNanoTime - lastNanoTime.value) / 1000000000.0;
                lastNanoTime.value = currentNanoTime;
                
                // game logic
                
                briefcaseObject.setVelocity(0,0);
                if (playerInputList.contains("LEFT"))
                    briefcaseObject.addVelocity(-50,0);
                if (playerInputList.contains("RIGHT"))
                    briefcaseObject.addVelocity(50,0);
                if (playerInputList.contains("UP"))
                    briefcaseObject.addVelocity(0,-50);
                if (playerInputList.contains("DOWN"))
                    briefcaseObject.addVelocity(0,50);
                    
                briefcaseObject.update(elapsedTime);
                
                // collision detection
                
                Iterator<IconClass> ufoIterator = ufoList.iterator();
                while ( ufoIterator.hasNext() )
                {
                    IconClass ufoObject = ufoIterator.next();
                    if ( briefcaseObject.intersects(ufoObject) )
                    {
                        ufoIterator.remove();
                        score.value++;
                    }
                }
                
                // render
                
                gc.clearRect(0, 0, 512,512);
                briefcaseObject.render( gc );
                
                for (IconClass ufoObject : ufoList )
                    ufoObject.render( gc );

// [jwc]                String pointsText = "Cash: $" + (100 * score.value);
                String pointsText = "UFOs: " + (1 * score.value);
//                gc.fillText( pointsText, 360, 36 );
                gc.fillText( pointsText, 370, 36 );
//                gc.strokeText( pointsText, 360, 36 );
                gc.strokeText( pointsText, 370, 36 );                
            }
        }.start();

        theStage.show();
    }
}