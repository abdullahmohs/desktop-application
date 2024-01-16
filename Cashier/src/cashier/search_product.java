package cashier;

import static cashier.database.add;
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

public class search_product extends Application {

    Stage stage = new Stage();
    get_data get = new get_data();
    TextField search = new TextField();
    TableView<get_data> table = new TableView();
    TableColumn<get_data, String> name = new TableColumn("اسم الصنف");
    TableColumn<get_data, String> price = new TableColumn("السعر");
    TableColumn<get_data, String> code = new TableColumn("الكود");
    database base = new database();
    ObservableList data = FXCollections.observableArrayList();
    FilteredList<get_data> filter = new FilteredList<>(data, e -> true);
    money m = new money();
    ObservableList<String> options = FXCollections.observableArrayList("اسم الصنف", "السعر", "الكود");
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
        connect = (Connection) DriverManager.getConnection(add, user, passwordDB);
        database.s = connect.prepareStatement("select * from products");
        database.r = database.s.executeQuery();
        while (database.r.next()) {
            code.setCellValueFactory(new PropertyValueFactory<>("code"));
            price.setCellValueFactory(new PropertyValueFactory<>("price"));
            name.setCellValueFactory(new PropertyValueFactory<>("name"));
            data.add(new get_data(database.r.getString("code"), database.r.getString("price"), database.r.getString("products")));
            table.setItems(data);
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
                    screan.add_column(table.getColumns().get(2).getCellObservableValue(row).getValue().toString());
                    stage.close();
                }
            }
        });

        comboBox.setOnAction(m -> {
            check = comboBox.getValue().toString();
        });

        search.setPromptText("بحث");
        search.setAlignment(Pos.CENTER_RIGHT);
        price.setId("column");
        code.setId("column");
        name.setId("column");
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
        table.getColumns().addAll(price, name, code);
        search.setLayoutX(240);
        search.setLayoutY(20);
        comboBox.setLayoutX(80);
        comboBox.setLayoutY(20);
        l_comboBox.setLayoutX(40);
        l_comboBox.setLayoutY(20);
        table.setLayoutX(00);
        table.setLayoutY(60);
        price.setMinWidth(100);
        code.setMinWidth(100);
        name.setMinWidth(300);
        table.setEditable(
                false);
        price.setEditable(
                false);
        name.setEditable(
                false);
        stage.setResizable(
                false);
        price.setResizable(
                false);
        name.setResizable(
                false);
        stage.setScene(scene);

        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public static class get_data {

        private String code;
        private String price;
        private String name;

        public get_data(String code, String price, String name) {
            this.code = code;
            this.price = price;
            this.name = name;
        }

        public get_data() {

        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
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
                    if (get.getPrice().startsWith(text) && check == "السعر") {
                        return true;
                    } else if (get.getName().contains(text) && check == "اسم الصنف") {
                        return true;
                    } else if (get.getCode().startsWith(text) && check == "الكود") {
                        return true;
                    } else {
                        return false;
                    }
                });
                SortedList<get_data> sort = new SortedList<>(filter);
                sort.comparatorProperty().bind(table.comparatorProperty());
                table.setItems(sort);
            } catch (Exception ex) {

            }
        });
    }
}
