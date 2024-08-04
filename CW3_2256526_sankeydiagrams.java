package com.CW3;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.text.Text;
import javafx.scene.shape.CubicCurve;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javafx.scene.transform.Scale;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
public class CW3_2256526_sankeydiagrams extends Application {
    //stroe the line text
    String line;
    //the every line text
    String[] strArray = new String[10];
    //the every line number
    String[] textnum = new String[10];
    //the line number
    int num_list;
    //the line number add
    double textnum_add=0;
    //help vartiry
    double Lastcurve_Width=0;
    //help vartiry
    int whether=0;
//the document into

    @Override
    public void start(Stage primaryStage)
    {
        Path filePath1 = Paths.get("Y:\\CW3test\\exa1.txt");
        Path filePath2 = Paths.get("Y:\\CW3test\\exa2.txt");
        Path filePath3 = Paths.get("Y:\\CW3test\\exa3.txt");
        Path filePath4 = Paths.get("Y:\\CW3test\\exa4.txt");
        //create the copmbox
        ComboBox<String> comboBox = new ComboBox<>();
        comboBox.getItems().addAll("example 1", "example 2", "example 3","example 4");
        comboBox.setLayoutX(100);
        comboBox.setLayoutY(50);
        //file input
        //create the text
        try (BufferedReader reader = Files.newBufferedReader(filePath2))
        {
            String line1;
            for (int i =0;(line1 = reader.readLine()) != null;i++)
            {
                strArray[i] = line1;
                line = line1;
                textnum[i]=strArray[i];
                num_list = i;
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        comboBox.setOnAction
                (event -> {
                    String selected = comboBox.getValue();
                    if (selected=="example 1")
                    {try (BufferedReader reader = Files.newBufferedReader(filePath1))
                    {
                        String line1;
                        for (int i =0;(line1 = reader.readLine()) != null;i++)
                        {
                            strArray[i] = line1;
                            line = line1;
                            textnum[i]=strArray[i];
                            num_list = i;
                        }
                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }}
                    if (selected=="example 2")
                    {try (BufferedReader reader = Files.newBufferedReader(filePath2))
                    {
                        String line1;
                        for (int i =0;(line1 = reader.readLine()) != null;i++)
                        {
                            strArray[i] = line1;
                            line = line1;
                            textnum[i]=strArray[i];
                            num_list = i;
                        }
                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }}
                    if (selected=="example 3")
                    {try (BufferedReader reader = Files.newBufferedReader(filePath3))
                    {
                        String line1;
                        for (int i =0;(line1 = reader.readLine()) != null;i++)
                        {
                            strArray[i] = line1;
                            line = line1;
                            textnum[i]=strArray[i];
                            num_list = i;
                        }
                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }}
                    if (selected=="example 4")
                    {try (BufferedReader reader = Files.newBufferedReader(filePath4))
                    {
                        String line1;
                        for (int i =0;(line1 = reader.readLine()) != null;i++)
                        {
                            strArray[i] = line1;
                            line = line1;
                            textnum[i]=strArray[i];
                            num_list = i;
                        }
                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }}
                });

        //text num get
        for (int i=2;i<num_list+1;i++)
        {
            textnum[i] = textnum[i].replaceAll("[a-zA-Z\\s]", "");
            String str = textnum[i];
            double doubleValue = Double.parseDouble(str);
            double doubleValue1 = doubleValue;
            textnum_add = textnum_add + doubleValue1;
        }
//the object create from the main screen

    // create the text
        Listblock[] listblocks = new Listblock[8];
    //create pane
        Pane pane_tital = new Pane();
    // the star square create
        Rectangle square_tital = new Rectangle(84, 160, 35, 150);
        square_tital.setFill(Color.rgb(0,255,255));
    // the word create
        Text text_tital = new Text(20,240,strArray[1]);
    //create the button
        Button create_Sanky = new Button("Restart");
        create_Sanky.setLayoutX(220);
        create_Sanky.setLayoutY(50);
        create_Sanky.setPrefSize(70, 20);
        create_Sanky.setOnAction(event -> {
            whether++;
            //show the Sanky digrame in pane
            for (int i = 0; i < num_list-1; i++)
            {

                listblocks[i] = new Listblock(strArray[i+2], i + 1, num_list-1, pane_tital,textnum_add,Lastcurve_Width);
                listblocks[i].display();
                Lastcurve_Width = Lastcurve_Width+listblocks[i].output_curve_width();
                if (whether/2==1)listblocks[i].clear();
            };
            pane_tital.getChildren().addAll(square_tital,text_tital);
        });
        //tital and screen show
        Scene scene = new Scene(pane_tital, 900, 500);
        pane_tital.getChildren().addAll(create_Sanky,comboBox);
        Scale scale = new Scale(1,1);
        pane_tital.getTransforms().add(scale);
        scene.widthProperty().addListener((obs, oldVal, newVal) -> updateScale(scale, scene));
        scene.heightProperty().addListener((obs, oldVal, newVal) -> updateScale(scale, scene));
        primaryStage.setScene(scene);
        primaryStage.setTitle("JavaFX Sanky diagram for 汖");
        primaryStage.show();
    }
    //control big and small
    private void updateScale(Scale scale, Scene scene)
    {
        double baseWidth = 900;
        double baseHeight = 500;
        double scaleX = scene.getWidth() / baseWidth;
        double scaleY = scene.getHeight() / baseHeight;
        scale.setX(scaleX);
        scale.setY(scaleY);
    }
    //Sanky digram create class
    class Listblock extends CW3_2256526_sankeydiagrams
    {
        //use to store the curve add width
        private  double Width_add = 0;
        //store the width of last curve
        private double Lastcurve_Width;
        //store the sum of data
        private  double textnum_add;
        //store the data text
        private String Listword;
        //store the number of recorded data
        private int totalnum;
        //store the number of recorded data
        private int Which_num;
        //put the curve pane
        private Pane pane;
        //data of control the color
        double colorbase=Math.random()*365;
        //squre color
        public Color squre_color=Color.hsb(colorbase,0.80,0.7);
        //curve color
        public Color curve_color=Color.hsb(colorbase,0.40,1);

        //srtructer method constructor
        public Listblock(String Listword,int Which_num,int totalnum,Pane pane,double textnum_add,double Lastcurve_Width)
        {
            this.Listword = Listword;
            this.totalnum = totalnum;
            this.Which_num = Which_num;
            this.pane = pane;
            this.textnum_add = textnum_add;
            this.Lastcurve_Width = Lastcurve_Width;
        }
        public void display()
        {
            //help variable
            double Width_add = 0;
            // width control
            Width_add  = Width_add + Lastcurve_Width;

            //get the text number
            String on_num_Listword= Listword.replaceAll("[a-zA-Z\\s]", "");
            double textnum = Double.parseDouble(on_num_Listword);

            //define the uint variable
            double Proportion_num = ((Which_num - 1.0) / totalnum);
            double Proportion_textnum = (textnum/textnum_add);

            //control the half down level
            int curveYcont1 = 3;
            int curveYcont2 = 0;

            //judge half value
            int halfi = 0;
            if (Proportion_num>0.5){halfi = 1;}
            //the constant
            double sx = 120;
            double ex = 585;

            //set the sit y of curve
            double sy = 160 +Width_add+0.5*150*Proportion_textnum;
            double ey = 50+ 380 * Proportion_num+75*Proportion_textnum;

            //set squre
            Rectangle square_list = new Rectangle(ex-10,50+(380* Proportion_num), 45.5, 150*Proportion_textnum );
            square_list.setFill(squre_color);

            //set text
            Text text = new Text(30, 55+(380* Proportion_num)+75*Proportion_textnum, Listword);
            double textwid = (text.getLayoutBounds().getWidth());
            text.setX(ex-15-textwid);

            //set curve
            CubicCurve curve = new CubicCurve();
            curve.setStartX(sx);
            curve.setStartY(sy);
            curve.setControlX1(450.0f);
            curve.setControlY1(sy+halfi*curveYcont1);
            curve.setControlX2(500.0f);
            curve.setControlY2(ey-halfi*curveYcont2);
            curve.setStrokeWidth(150*Proportion_textnum);
            curve.setEndX(ex);
            curve.setEndY(ey);
            curve.setFill(Color.WHITE);
            curve.setStroke(curve_color);
            pane.getChildren().addAll(curve,text,square_list);
        }
        public void clear()
        {
            pane.getChildren().clear();
        }
        public double output_curve_width()
        {
            String on_num_Listword= Listword.replaceAll("[a-zA-Z\\s]", "");
            double textnum = Double.parseDouble(on_num_Listword);
            double width = 150*(textnum/textnum_add);
            return width;
        }

    }
    public static void main(String[] args) {
        launch(args);
    }
}