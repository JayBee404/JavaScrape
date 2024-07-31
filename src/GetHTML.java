import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.geometry.Pos;

import java.util.Scanner;
import java.io.*;
import java.net.*;

public class GetHTML extends Application
{
    private TextArea outArea;
    private Button doItButton;
    private Label urlLabel;
    private TextField urlText;

    private String textLine = new String("");
    private String outString = new String();
    
    private final String initialString = ""; //enter URL to scrape here

    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage)
    {
        outArea = new TextArea();
        outArea.setPrefColumnCount(50);
        outArea.setPrefRowCount(40);
        outArea.setWrapText(false);
        outArea.setFont(Font.font("Courier New", FontWeight.BOLD, 20));

        urlLabel = new Label("URL: ");
        urlText = new TextField();
        urlText.setPrefWidth(400);
        urlText.setText(initialString);

        doItButton    = new Button("Get HTML Doc");
        doItButton.setOnAction(new ButtonClickHandler());

        HBox buttonBox = new HBox(15,urlLabel,urlText,doItButton);
        buttonBox.setAlignment(Pos.CENTER);
        VBox mainBox = new VBox(10,buttonBox,outArea);
        mainBox.setAlignment(Pos.CENTER);

        Scene scene = new Scene(mainBox, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("URL HTML Doc Getter");
        primaryStage.show();
    }

    class ButtonClickHandler implements EventHandler<ActionEvent>
    {
        @Override
        public void handle(ActionEvent event)
        {
            try
            {
                URL theURL = new URL(urlText.getText());

                BufferedReader inputStream = new BufferedReader(
                        new InputStreamReader(theURL.openStream()));

                String inputLine;
                String webpage = "";
                while ((inputLine = inputStream.readLine()) != null)
                {
                    webpage += inputLine;
                }
                inputStream.close();

                try {
                    FileWriter fileWriter = new FileWriter("");// output filename here
                    fileWriter.write(webpage);
                    fileWriter.close();
                    System.out.println("Successfully wrote to the file.");
                } catch (IOException e) {
                    System.out.println("An error occurred while writing to the file.");
                    e.printStackTrace();
                }

            }
            catch (IOException exception)
            {
                exception.printStackTrace();

            }
        }
    }
}
