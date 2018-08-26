package boguszGroup.Blog;


import javafx.scene.image.Image;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class PostTest {

  public void testLoadImg() throws FileNotFoundException {
    String imgPath = "D://Foto//Birthday '18//20180731_194815.jpg";

    Image img = new Image(new FileInputStream(imgPath));


  }
}
