package Controller;

import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import Model.*;
import Business.*;



public class SerieResearch {

    @FXML
    private Button buttonSerie;

    @FXML
    private TableView<ResultResearch> resultTable;

    @FXML
    private TableColumn<ResultResearch, String> resultColumn;

    @FXML
    private ComboBox<String> serieCB;

    @FXML
    private TableView<ResultResearch> tableName;

    @FXML
    private TableColumn<ResultResearch, String> nameColumn;

    private SerieManager serieManager;
    private ArrayList<ResultResearch> resultResearch;
    private ArrayList<ResultResearch> tableNames;

    @FXML
    public void initialize(){
        serieManager = new SerieManager();
        initSeries();
    }

    @FXML
    public void initSeries() {
        try {
            ArrayList<Serie> series = serieManager.getAllSeries();
            for(Serie serie : series){
                serieCB.getItems().add(serie.getName());
            }
        } catch (Exception exception) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error while loading series");
            alert.showAndWait();
        }
    }

    @FXML
    public void onSearchSerieClicked(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        try {
            if (serieCB.getValue() != null) {
                resultTable.getItems().clear();
                tableName.getItems().clear();

                tableNames = new ArrayList<>();
                tableNames.add(new ResultResearch("Book"));
                tableNames.add(new ResultResearch("Person"));
                tableNames.add(new ResultResearch("Person"));
                tableNames.add(new ResultResearch("Edition"));
                nameColumn.setCellValueFactory(new PropertyValueFactory<>("result"));
                tableName.getItems().addAll(tableNames);

                resultResearch = serieManager.getSearchSerie(serieCB.getValue());
                resultColumn.setCellValueFactory(new PropertyValueFactory<>("result"));
                resultTable.getItems().addAll(resultResearch);
            } else {
                alert.setHeaderText("Please select a serie");
                alert.showAndWait();
            }
        } catch (Exception exception) {
            alert.setHeaderText("Error while searching serie");
            alert.showAndWait();
        }
    }

}
