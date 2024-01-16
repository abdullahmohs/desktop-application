package cashier;

import static cashier.database.add_4;
import static cashier.database.connect;
import static cashier.database.passwordDB;
import static cashier.database.user;
import java.sql.Connection;
import java.sql.DriverManager;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class search_pone_replace extends Application {

    Stage stage = new Stage();
    get_data_replace get = new get_data_replace();
    TextField search = new TextField();
    TableView<get_data_replace> table = new TableView();
    TableColumn<get_data_replace, String> num_pone = new TableColumn("رقم الحركة");
    TableColumn<get_data_replace, String> total = new TableColumn("الإجمالى");
    TableColumn<get_data_replace, String> history = new TableColumn("التاريخ");
    database base = new database();
    ObservableList data = FXCollections.observableArrayList();
    FilteredList<get_data_replace> filter = new FilteredList<>(data, e -> true);
    money m = new money();
    ObservableList<String> options = FXCollections.observableArrayList("رقم الحركة", "الإجمالى", "التاريخ");
    ComboBox comboBox = new ComboBox(options);
    Label l_comboBox = new Label("فلتر");
    String check = null;

    @Override
    public void start(Stage stage) throws Exception {
        Pane root = new Pane();
        Scene scene = new Scene(root, 500, 400);
        stage.getIcons().add(new Image("cashier\\download.png"));
        String css = this.getClass().getResource("index.css").toExternalForm();
        scene.getStylesheets().add(css);
        connect = (Connection) DriverManager.getConnection(add_4, user, passwordDB);
        database.s = connect.prepareStatement("select * from pone");
        database.r = database.s.executeQuery();
        while (database.r.next()) {
            if (database.r.getString("total") != null) {
                num_pone.setCellValueFactory(new PropertyValueFactory<>("num_pone"));
                total.setCellValueFactory(new PropertyValueFactory<>("total"));
                history.setCellValueFactory(new PropertyValueFactory<>("history"));
                data.add(new get_data_replace(database.r.getString("num_pone"), database.r.getString("total"), database.r.getString("history")));
                table.setItems(data);
            }
        }
        database.close_database();

        //keyboard
        search.addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent e) -> {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    if (e.getCode() == KeyCode.NUMPAD0 || e.getCode() == KeyCode.NUMPAD1 || e.getCode() == KeyCode.NUMPAD2
                            || e.getCode() == KeyCode.NUMPAD3 || e.getCode() == KeyCode.NUMPAD4 || e.getCode() == KeyCode.NUMPAD5
                            || e.getCode() == KeyCode.NUMPAD6 || e.getCode() == KeyCode.NUMPAD7 || e.getCode() == KeyCode.NUMPAD8
                            || e.getCode() == KeyCode.NUMPAD9 || e.getCode() == KeyCode.DIGIT0 || e.getCode() == KeyCode.DIGIT1
                            || e.getCode() == KeyCode.DIGIT2 || e.getCode() == KeyCode.DIGIT3 || e.getCode() == KeyCode.DIGIT4
                            || e.getCode() == KeyCode.DIGIT5 || e.getCode() == KeyCode.DIGIT6 || e.getCode() == KeyCode.DIGIT7
                            || e.getCode() == KeyCode.DIGIT8 || e.getCode() == KeyCode.DIGIT9) {
                        search_table_replace();
                    } else if (e.getCode() == KeyCode.BACK_SPACE) {
                        search_table_replace();
                    }
                    search_table_replace();
                }
            });
        });

        //mouse
        scene.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                if (event.getClickCount() == 2) {
                    int row = table.getSelectionModel().selectedIndexProperty().get();
                    replace.show_table_pone(table.getColumns().get(1).getCellObservableValue(row).getValue().toString());
                    stage.close();
                }
            }
        });

        comboBox.setOnAction(m -> {
            check = comboBox.getValue().toString();
        });

        search.setPromptText("بحث");
        search.setAlignment(Pos.CENTER_RIGHT);
        num_pone.setId("column");
        total.setId("column");
        history.setId("column");
        comboBox.setStyle("-fx-font-size:15px; -fx-font-weight:bold; ");
        l_comboBox.setStyle("-fx-font-size:18px; -fx-font-weight:bold; ");
        search.setMaxSize(250, 30);
        search.setMinSize(250, 30);
        comboBox.setMinSize(130, 30);
        comboBox.setMaxSize(130, 30);
        table.autosize();
        table.setMaxHeight(350);
        table.setMinHeight(350);
        root.getChildren().addAll(search, table, comboBox, l_comboBox);
        table.getColumns().addAll(total, num_pone, history);
        search.setLayoutX(240);
        search.setLayoutY(20);
        comboBox.setLayoutX(80);
        comboBox.setLayoutY(20);
        l_comboBox.setLayoutX(40);
        l_comboBox.setLayoutY(20);
        table.setLayoutX(00);
        table.setLayoutY(60);
        num_pone.setMinWidth(150);
        total.setMinWidth(150);
        history.setMinWidth(200);
        table.setEditable(
                false);
        num_pone.setEditable(
                false);
        total.setEditable(
                false);
        stage.setResizable(
                false);
        num_pone.setResizable(
                false);
        total.setResizable(
                false);
        stage.setScene(scene);

        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public static class get_data_replace {

        private String num_pone;
        private String total;
        private String history;

        public get_data_replace(String num_pone, String total, String history) {
            this.num_pone = num_pone;
            this.total = total;
            this.history = history;
        }

        public get_data_replace() {

        }

        public String getNum_pone() {
            return num_pone;
        }

        public void setNum_pone(String num_pone) {
            this.num_pone = num_pone;
        }

        public String getTotal() {
            return total;
        }

        public void setTotal(String total) {
            this.total = total;
        }

        public String getHistory() {
            return history;
        }

        public void setHistory(String history) {
            this.history = history;
        }

    }

    public void search_table_replace() {
        search.textProperty().addListener((observale, oldValue, newValue) -> {
            try {
                filter.setPredicate(get -> {
                    if (newValue.isEmpty() || newValue == null) {
                        return true;
                    }
                    String text = newValue;
                    if (get.getNum_pone().startsWith(text) && check == "رقم الحركة") {
                        return true;
                    } else if (get.getHistory().contains(text) && check == "التاريخ") {
                        return true;
                    } else if (get.getTotal().startsWith(text) && check == "الإجمالى") {
                        return true;
                    } else {
                        return false;
                    }
                });
                SortedList<get_data_replace> sort = new SortedList<>(filter);
                sort.comparatorProperty().bind(table.comparatorProperty());
                table.setItems(sort);
            } catch (Exception ex) {

            }
        });
    }
}
