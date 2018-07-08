package main;

import javafx.application.Application;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;


public class parseUI extends Application {
    static TableView<SerialData> table = new TableView<SerialData>();
    protected static ObservableList<SerialData> data =
            FXCollections.observableArrayList();

    public static void main(String[] args) {
        launch(args);
    }
    final Alert alert = new Alert(Alert.AlertType.ERROR);
    final Button button = new Button ("Browse");
    final Label notification = new Label ();
    final TextArea text = new TextArea ("");
    final Button Done = new Button("Parse");
    final Button Reset = new Button("Reset");

    String address = " ";


    @Override public void start(Stage stage) {
        stage.setTitle("Parse MODBUS");
        Scene scene = new Scene(new Group(), 810, 480);

        // Dialog box for wrong file select
        //
        alert.setTitle("Error!");
        alert.setHeaderText(null);

        //Select what mode to parse, the requests or the responses
        //
        final ComboBox mode = new ComboBox();
        mode.getItems().addAll(
                "Request",
                "Response"
        );

        /*      //Select filter mode
        final ComboBox filterComboBox = new ComboBox();
        filterComboBox.getItems().addAll(
                "Line",
                "Function",
                "Address",
                "None"
        );*/
        table.setEditable(true);

        //Create Columns and set the values they're going to display and as what data type
        //
        TableColumn<SerialData,Integer> lineCol = new TableColumn<SerialData,Integer>("Line");
        lineCol.setCellValueFactory(new PropertyValueFactory<SerialData,Integer>("Line"));
        TableColumn<SerialData,Integer> idCol = new TableColumn<SerialData,Integer>("ID");
        idCol.setCellValueFactory(new PropertyValueFactory<SerialData,Integer>("ID"));
        TableColumn<SerialData,Integer> fcnCol = new TableColumn<SerialData, Integer>("Code");
        fcnCol.setCellValueFactory(new PropertyValueFactory<SerialData,Integer>("Code"));
        TableColumn addCol = new TableColumn("Address");
        addCol.setCellValueFactory(new PropertyValueFactory<SerialData,String>("Address"));
        TableColumn amntCol = new TableColumn("Amounts");
        amntCol.setCellValueFactory(new PropertyValueFactory<SerialData,String>("Amounts"));
        TableColumn statCol = new TableColumn("Status");
        statCol.setCellValueFactory(new PropertyValueFactory<SerialData,String>("Status"));
        TableColumn valCol = new TableColumn("Value");
        valCol.setCellValueFactory(new PropertyValueFactory<SerialData, String>("Value"));
        TableColumn bytCol = new TableColumn("Bytes");
        bytCol.setCellValueFactory(new PropertyValueFactory<SerialData, String >("Bytes"));
        TableColumn bitCol = new TableColumn("Bits");
        bitCol.setCellValueFactory(new PropertyValueFactory<SerialData,String >("Bits"));
        TableColumn valsCol = new TableColumn("Values");
        valsCol.setCellValueFactory(new PropertyValueFactory<SerialData, String>("Values"));

        table.setItems(data);
        table.getColumns().addAll(lineCol,idCol,fcnCol,addCol,amntCol,statCol,valCol,bytCol,bitCol,valsCol);


//      filterComboBox.setValue("None");
        mode.setValue("Request");

        GridPane grid = new GridPane();
        grid.setVgap(4);
        grid.setHgap(50);
        grid.setPadding(new Insets(5, 5, 5, 5));
        grid.add(new Label("Mode: "), 0, 0);
        grid.add(mode, 1, 0);
        //to be added in new version
        //grid.add(new Label("Filter: "), 6, 0);
        //grid.add(filterComboBox, 7,0);
        grid.add(table, 0, 2, 8, 1);
        grid.add(button, 0, 3);
        grid.add (notification, 1, 3, 3, 1);


        //Opens file browser
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                final FileChooser fileChooser = new FileChooser();

                //Filter to make only txt files selectable
                FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TEXT Files (*.txt)", "*.txt");
                fileChooser.getExtensionFilters().add(extFilter);

                final File selectedFile = fileChooser.showOpenDialog(stage);
                if (selectedFile != null){
                    selectedFile.getAbsolutePath();
                    System.out.println(selectedFile);
                    ParseSelect.file = selectedFile;
                    grid.add(Done, 7,3);
                    GridPane.setHalignment(Done, HPos.RIGHT);
                }
            }

        });


        // Begins parse procedure and is dependent on the MODE selected
        //
        Done.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ParseSelect.mode = mode.getValue().toString();
                data.clear();
                table.getItems().clear();
                switch(ParseSelect.mode){
                    case "Request":
                        try {
                            SerialRequest.parse();
                        }catch(Exception e){
                            alert.setContentText("Error:\n" + e + "\n\n Please restart application and ensure correct text file was selected and the Log file was unchanged");
                            alert.showAndWait();
                        }
                        break;

                    case "Response":
                        try {
                            SerialResponse.parse();
                        }catch(Exception e){
                            alert.setContentText("Error:\n" + e + "\n\n Please restart application and ensure correct text file was selected and the Log file was unchanged");
                            alert.showAndWait();
                        }
                        break;

                    default:
                        break;
                }
            }
        });


        Group root = (Group)scene.getRoot();
        root.getChildren().add(grid);
        stage.setScene(scene);
        stage.show();


    }
}
