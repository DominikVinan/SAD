package gui

import mainpackage._
import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.scene._
import javafx.scene.control._
import javafx.scene.canvas._
import javafx.application.Application
import javafx.scene.Scene
import javafx.scene.layout._
import javafx.scene.control._
import javafx.stage.Stage
import javafx.event._ 
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import java.io.File
import javafx.event.{EventHandler, ActionEvent}  
import javafx.fxml.{FXML, FXMLLoader}  
import javafx.scene.{Parent, Scene, Group}  
import javafx.stage.Stage  
import javafx.scene.control.{Button, Label}  
import javafx.application.Application  
 
 
import java.nio.file.{Paths, Files}
import java.nio.charset.StandardCharsets

class GuiCtrl extends Application {
  
  @FXML  
  protected var myButton : Button = _
  @FXML  
  protected var photoPane : Pane = _
  @FXML  
  protected var myTextField : TextField = _

  override def start(primaryStage: Stage): Unit = { 
    primaryStage.setTitle("Scala Automaton Drawer")
    val loader = new FXMLLoader(getClass().getResource("GraphGui.fxml"))
    loader.setController(this)
    val rootLayout : Parent =  loader.load()
		val scene = new Scene(rootLayout)
    
    makePicture()
    val image = new Image((new File("tmp.gv.jpg")).toURI().toString())
    val iv1 = new ImageView();
    iv1.setImage(image);
    
    photoPane.getChildren().add(iv1)
    
    
		primaryStage.setScene(scene)
		primaryStage.show()
  }
  
  def getDotCode():String = {
    val drawer = new NFADrawer()
    drawer.makeStates
  }
  
  def makePicture(): Unit = {
    
    val fileTemp = new File("tmp.gv.jpg")
    if (fileTemp.exists) {
       fileTemp.delete()
    }
    val code = getDotCode()
    val tempFile = new File("tmp.gv")
    tempFile.createNewFile()
    tempFile.deleteOnExit()
    
    Files.write(Paths.get(tempFile.getAbsolutePath), code.getBytes(StandardCharsets.UTF_8))
    val rt = Runtime.getRuntime()
    
    rt.exec("dot -Tjpg tmp.gv -O")
    while(!Files.exists(Paths.get("tmp.gv.jpg"))){
      Thread.sleep(1000)
      
    }
   
  }
  
  
}


object GuiCtrl {
  def main(args: Array[String]) ={
    Application.launch(classOf[GuiCtrl], args: _*)
  }
}