import javafx.scene.image.Image;
import javafx.scene.canvas.GraphicsContext;
import javafx.geometry.Rectangle2D;

public class IconClass
{
    private Image image;
    private double positionX;
    private double positionY;    
    private double width;
    private double height;
    private double velocityX;
    private double velocityY;

    public IconClass()
    {
        positionX = 0;
        positionY = 0;    
        velocityX = 0;
        velocityY = 0;
    }

    public IconClass(String imageFilenameIn, double positionXIn, double positionYIn)
    {
        positionX = positionXIn;
        positionY = positionYIn;    
        velocityX = 0;
        velocityY = 0;
        this.setImage(imageFilenameIn);
    }


    public void setImage(Image i)
    {
        image = i;
        width = i.getWidth();
        height = i.getHeight();
    }

    public void setImage(String filename)
    {
        Image i = new Image(filename);
        setImage(i);
    }

    public void setPosition(double x, double y)
    {
        positionX = x;
        positionY = y;
    }

    public void setVelocity(double x, double y)
    {
        velocityX = x;
        velocityY = y;
    }

    public void addVelocity(double x, double y)
    {
        velocityX += x;
        velocityY += y;
    }

    public void update(double time)
    {
        positionX += velocityX * time;
        positionY += velocityY * time;
    }

    public void render(GraphicsContext gc)
    {
        gc.drawImage( image, positionX, positionY );
    }

    public Rectangle2D getBoundary()
    {
        System.out.println("this: positionX,positionY,width,height: " + this  + ": " + positionX + "," + positionY + "," + width + "," + height);
        return new Rectangle2D(positionX,positionY,width,height);
    }

    public boolean intersects(IconClass s)
    {
        return s.getBoundary().intersects( this.getBoundary() );
    }
    
    public String toString()
    {
        return " Position: [" + positionX + "," + positionY + "]" 
        + " Velocity: [" + velocityX + "," + velocityY + "]";
    }
}