package cashier;

import static cashier.database.add_3;
import static cashier.database.add_4;
import static cashier.database.connect;
import static cashier.database.passwordDB;
import static cashier.database.user;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Locale;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class replace extends Application implements EventHandler<ActionEvent> {

    database base = new database();
    int num_row;
    private static Double sum = 0.0;
    Double t = 0.0;
    Double s;
    int recept;
    Button b_pone;
    Stage stage;
    private static TableView<table_model> table;
    private static TableColumn<table_model, String> price;
    private static TableColumn<table_model, String> name_product;
    private static TableColumn<table_model, String> quntity;
    private static TableColumn<table_model, String> total;
    private static TableColumn<table_model, String> code;
    Button next;
    Button back;
    private static TextField num_pone;
    private static TextField total_table;
    private static ObservableList<table_model> list;
    int num;
    Alert a = new Alert(AlertType.INFORMATION);

    @Override

    public void start(Stage stage) throws Exception {
        stage.setTitle("المرتجع");
        Pane root = new Pane();
        Rectangle2D bounds = Screen.getPrimary().getVisualBounds();
        Scene scene = new Scene(root, bounds.getWidth() / 1.5, bounds.getHeight() / 1.5);
        table = new TableView();
        price = new TableColumn("السعر");
        name_product = new TableColumn("اســـم الصنـــف");
        quntity = new TableColumn("الكمية");
        total = new TableColumn("الإجمالى");
        code = new TableColumn("كود الصنف");
        TextField parcode = new TextField();
        num_pone = new TextField();
        total_table = new TextField();
        Label l_total = new Label("الإجمالى");
        Label l_parcode = new Label("الباركود");
        Label l_date = new Label("التاريخ");
        Label l_pone = new Label("رقم الحركة");
        Label l_next = new Label("التالي");
        Label l_back = new Label("السابق");
        b_pone = new Button();
        list = FXCollections.observableArrayList();
        DatePicker date = new DatePicker();
        date.setValue(LocalDate.now());
        Locale.setDefault(new Locale("ar"));
        next = new Button("");
        back = new Button("");
        recept = base.get_num_pone_replace();

        try {
            stage.getIcons().add(new Image("cashier\\download.png"));
            String css = this.getClass().getResource("index.css").toExternalForm();
            scene.getStylesheets().add(css);
            table.getColumns().addAll(total, price, quntity, name_product, code);
            num_pone.setText(Integer.toString(base.get_num_pone_replace()));
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        scene.addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent e) -> {
            try {
                sum = 0.0;
                if (e.getCode() == KeyCode.ENTER) {
                    if (parcode.getText().length() == 0 || parcode.getText() == null) {
                    } else if (base.check_parcode(parcode.getText()) == true || base.check_code(parcode.getText()) == true) {
                        show_table();
                        if (base.check_parcode(parcode.getText()) == true) {
                            list.addAll(
                                    new table_model(base.get_price(parcode.getText()), "1", base.get_price(parcode.getText()), base.get_name(parcode.getText()), base.get_code(parcode.getText())));
                        } else if (base.check_code(parcode.getText()) == true) {
                            list.addAll(new table_model(database.getPrice(parcode.getText()), "1", database.getPrice(parcode.getText()), database.getName(parcode.getText()), parcode.getText()));
                        }
                        table.setItems(list);
                        parcode.setText("");
                        sum_table();
                    } else {
                        a.setHeaderText("عفوا الصنف غير مسجل");
                        a.show();
                        parcode.setText("");
                    }

                } else if (e.getCode() == KeyCode.DELETE) {
                    Platform.runLater(() -> {
                        try {
                            table.getItems().remove(num_row);
                            if (table.getItems().size() == 0 && total_table.getText() != null) {
                                total_table.setText("");
                            }

                            for (int i = 0; i < table.getItems().size(); i++) {
                                if (table.getItems().size() == 0 && !total_table.getText().isEmpty()) {
                                    total_table.setText("");
                                } else {
                                    sum += Double.parseDouble(table.getColumns().get(0).getCellObservableValue(i).getValue().toString());
                                    total_table.setText(sum.toString());
                                }
                            }
                        } catch (NumberFormatException ex) {
                        }
                    });
                } else if (e.getCode() == KeyCode.F5) {
                    date.setValue(LocalDate.now());
                    if (table.getItems().isEmpty()) {
                    } else if (base.get_num_pone_replace() != Integer.parseInt(num_pone.getText())) {
                        a.setHeaderText("هذا الرقم موجود بالفعل");
                        a.show();
                        num_pone.setText(Integer.toString(base.get_num_pone_replace()));
                        table.getItems().clear();
                        total_table.setText("");
                        Platform.runLater(() -> parcode.requestFocus());
                    } else {
                        base.craete_table_replace(recept);
                        for (int i = 0; i < table.getItems().size(); i++) {
                            base.add_pone_replace(recept, table.getColumns().get(0).getCellObservableValue(i).getValue().toString(), table.getColumns().get(1).getCellObservableValue(i).getValue().toString(), table.getColumns().get(2).getCellObservableValue(i).getValue().toString(), table.getColumns().get(3).getCellObservableValue(i).getValue().toString(), table.getColumns().get(4).getCellObservableValue(i).getValue().toString());
                            base.plus_quntity(Integer.parseInt(table.getColumns().get(4).getCellObservableValue(i).getValue().toString()), table.getColumns().get(2).getCellObservableValue(i).getValue().toString());
                        }
                        base.update_details_replace(recept, total_table.getText(), date.getValue().toString());
                        num_pone.setText(Integer.toString(++recept));
                        base.detail_pone_replace(recept);
                        table.getItems().clear();
                        total_table.setText("");

                        Platform.runLater(() -> parcode.requestFocus());
                    }
                } else if (e.getCode() == KeyCode.F3) {
                    try {
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }
                }
            } catch (HeadlessException | NumberFormatException ex) {
                System.out.println(ex.getMessage());
            }
        });

        num_pone.addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent e) -> {
            if (e.getCode() == KeyCode.ENTER) {
                if (Integer.parseInt(num_pone.getText()) < base.get_num_pone_replace()) {
                    try {
                        table.getItems().clear();
                        connect = (Connection) DriverManager.getConnection(add_3, user, passwordDB);
                        database.s = connect.prepareStatement("select * from replace_?");
                        database.s.setInt(1, Integer.parseInt(num_pone.getText()));
                        database.r = database.s.executeQuery();
                        while (database.r.next()) {
                            show_table();
                            list.add(
                                    new table_model(database.r.getString("total"), database.r.getString("quntity"), database.r.getString("price"), base.r.getString("name_products"), base.r.getString("code")));
                            table.setItems(list);
                        }
                        database.close_database();
                        connect = (Connection) DriverManager.getConnection(add_4, user, passwordDB);
                        database.s = connect.prepareStatement("select * from pone where num_pone = ?");
                        database.s.setString(1, num_pone.getText());
                        database.r = database.s.executeQuery();
                        database.r.next();
                        total_table.setText(database.r.getString("total"));
                    } catch (SQLException ex) {
                        System.out.println(ex.getMessage());
                    }
                }
                if (Integer.parseInt(num_pone.getText()) == base.get_num_pone_replace()) {
                    Platform.runLater(() -> {
                        table.getItems().clear();
                        total_table.setText("");
                        parcode.requestFocus();
                    });
                }

            }
        });
        //button
        b_pone.setOnAction(this);
        back.setOnAction(this);
        next.setOnAction(this);
        quntity.setOnEditCommit(ev -> {
            s = Double.parseDouble(database.getPrice(table.getColumns().get(4).getCellObservableValue(num_row).getValue().toString())) * Double.parseDouble(ev.getNewValue());
            list.set(num_row, new table_model(s.toString(), ev.getNewValue(), database.getPrice(table.getColumns().get(4).getCellObservableValue(num_row).getValue().toString()), database.getName(table.getColumns().get(4).getCellObservableValue(num_row).getValue().toString()), table.getColumns().get(4).getCellObservableValue(num_row).getValue().toString()));
            table.setItems(list);
            sum_table();
        });

        //mouse 
        scene.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                try {
                    if (event.getClickCount() == 1) {
                        num_row = table.getSelectionModel().selectedIndexProperty().getValue();
                    }

                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }
        });

        stage.setOnCloseRequest(event -> {
            Alert a = new Alert(AlertType.CONFIRMATION);
            a.setHeaderText("هل تريد اغلاق البرنامج");
            a.initOwner(stage);
            a.setX(500);
            a.setY(300);
            a.show();
        });
        price.setId("column");
        name_product.setId("column");
        quntity.setId("column");
        total.setId("column");
        code.setId("column");
        l_total.setId(
                "L");
        b_pone.setId(
                "search");
        next.setId(
                "next");
        back.setId(
                "back");
        back.setRotate(180);
        parcode.setMaxSize(300, 30);
        parcode.setMinSize(300, 30);
        table.setMaxWidth(bounds.getWidth() / 1.55);
        table.setMinWidth(bounds.getWidth() / 1.55);
        table.setMaxHeight(bounds.getHeight() / 2.2);
        table.setMinHeight(bounds.getHeight() / 2.2);
        name_product.setMinWidth(320);
        price.setMinWidth(125);
        quntity.setMinWidth(125);
        total.setMinWidth(125);
        code.setMinWidth(125);
        l_parcode.setLayoutX(320);
        l_parcode.setLayoutY(10);
        parcode.setLayoutX(200);
        parcode.setLayoutY(50);
        table.setLayoutX(15);
        table.setLayoutY(100);
        l_back.setLayoutX(235);
        l_back.setLayoutY(580);
        back.setLayoutX(300);
        back.setLayoutY(580);
        l_next.setLayoutX(380);
        l_next.setLayoutY(580);
        next.setLayoutX(350);
        next.setLayoutY(580);
        l_total.setLayoutX(580);
        l_total.setLayoutY(560);
        total_table.setLayoutX(710);
        total_table.setLayoutY(555);

        total_table.setMinSize(
                130, 60);
        total_table.setMaxSize(
                130, 60);
        date.setLayoutX(520);
        date.setLayoutY(50);
        l_date.setLayoutX(550);
        l_date.setLayoutY(10);
        date.setMinSize(130, 30);
        date.setMaxSize(130, 30);
        b_pone.setMinSize(30, 30);
        b_pone.setMaxSize(30, 30);
        b_pone.setLayoutX(670);
        b_pone.setLayoutY(50);
        num_pone.setLayoutX(700);
        num_pone.setLayoutY(50);
        l_pone.setLayoutX(720);
        l_pone.setLayoutY(10);
        num_pone.setMinSize(
                150, 30);
        num_pone.setMaxSize(
                150, 30);

        table.setEditable(
                true);
        name_product.setEditable(
                true);
        name_product.setResizable(
                false);
        price.setEditable(
                false);
        price.setResizable(
                false);
        quntity.setCellFactory(TextFieldTableCell.forTableColumn());
        quntity.setResizable(
                false);
        total.setEditable(
                false);
        total.setResizable(
                false);
        total_table.setEditable(
                false);
        code.setEditable(false);
        code.setResizable(false);

        parcode.setAlignment(Pos.CENTER_RIGHT);

        num_pone.setAlignment(Pos.CENTER_RIGHT);

        total_table.setAlignment(Pos.CENTER_RIGHT);

        try {
            total_table.setStyle("-fx-font-size:30px; -fx-alignment: CENTER;");
            root.setStyle("-fx-background-color:#eee");
            l_parcode.setStyle("-fx-font-size:20px; -fx-font-weight:bold;");
            l_date.setStyle("-fx-font-size:20px; -fx-font-weight:bold;");
            l_pone.setStyle("-fx-font-size:20px; -fx-font-weight:bold; -fx-alignment: CENTER;");
            l_next.setStyle("-fx-font-size:20px; -fx-font-weight:bold;-fx-font-color:black;");
            l_back.setStyle("-fx-font-size:20px; -fx-font-weight:bold; -fx-font-color:black;");
            num_pone.setStyle("-fx-font-size:20px; -fx-font-weight:bold; -fx-alignment: CENTER;");
            a.getDialogPane().setStyle("-fx-font-size: 13px; -fx-font-weight:bold; -fx-alignment: CENTER;");

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        root.getChildren()
                .addAll(parcode, table, total_table, l_total, date, l_next);
        root.getChildren()
                .addAll(l_date, l_parcode, l_pone, num_pone, b_pone, next, back, l_back);
        stage.setScene(scene);
        a.initOwner(stage);
        a.getDialogPane().setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);

        stage.show();
    }

    public static void main(String[] args) {
        launch(args);

    }

    @Override
    public void handle(ActionEvent event) {
        if (event.getSource() == b_pone) {
            try {
                new search_pone_replace().start(new search_pone_replace().stage);
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        } else if (event.getSource() == back) {
            try {
                if (table.getItems().size() != 0 && base.get_num_pone_replace() == Integer.parseInt(num_pone.getText())) {

                } else {
                    num = Integer.parseInt(num_pone.getText());
                    num--;
                    if (num > 0) {
                        num_pone.setText(Integer.toString(num));
                        table.getItems().clear();
                        connect = (Connection) DriverManager.getConnection(add_3, user, passwordDB);
                        database.s = connect.prepareStatement("select * from replace_?");
                        database.s.setInt(1, Integer.parseInt(num_pone.getText()));
                        database.r = database.s.executeQuery();
                        while (database.r.next()) {
                            show_table();
                            list.add(
                                    new table_model(database.r.getString("total"), database.r.getString("quntity"), database.r.getString("price"), database.r.getString("name_products"), database.r.getString("code")));
                            table.setItems(list);
                        }
                        database.close_database();
                        connect = (Connection) DriverManager.getConnection(add_4, user, passwordDB);
                        database.s = connect.prepareStatement("select * from pone where num_pone = ?");
                        database.s.setString(1, num_pone.getText());
                        database.r = database.s.executeQuery();
                        database.r.next();
                        total_table.setText(database.r.getString("total"));
                    }
                }
            } catch (NumberFormatException | SQLException ex) {
                System.out.println(ex.getMessage());
            }
        } else if (event.getSource() == next) {
            try {
                num = Integer.parseInt(num_pone.getText());
                num++;
                if (num < base.get_num_pone_replace()) {

                    num_pone.setText(Integer.toString(num));
                    table.getItems().clear();
                    connect = (Connection) DriverManager.getConnection(add_3, user, passwordDB);
                    database.s = connect.prepareStatement("select * from replace_?");
                    database.s.setInt(1, Integer.parseInt(num_pone.getText()));
                    database.r = database.s.executeQuery();
                    while (database.r.next()) {
                        show_table();
                        list.add(
                                new table_model(database.r.getString("total"), database.r.getString("quntity"), database.r.getString("price"), database.r.getString("name_products"), database.r.getString("code")));
                        table.setItems(list);
                    }
                    database.close_database();
                    connect = (Connection) DriverManager.getConnection(add_4, user, passwordDB);
                    database.s = connect.prepareStatement("select * from pone where num_pone = ?");
                    database.s.setString(1, num_pone.getText());
                    database.r = database.s.executeQuery();
                    database.r.next();
                    total_table.setText(database.r.getString("total"));

                }
            } catch (NumberFormatException | SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    public static class table_model {

        String name_product, price, total, quntity, code;

        table_model(String total, String quntity, String price, String name_product, String code) {
            this.name_product = name_product;
            this.price = price;
            this.quntity = quntity;
            this.total = total;
            this.code = code;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        table_model() {

        }

        public String getName_product() {
            return name_product;
        }

        public void setName_product(String name_product) {
            this.name_product = name_product;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getTotal() {
            return total;
        }

        public void setTotal(String total) {
            this.total = total;
        }

        public String getQuntity() {
            return quntity;
        }

        public void setQuntity(String quntity) {
            this.quntity = quntity;
        }

    }

    public static void show_table() {
        name_product.setCellValueFactory(new PropertyValueFactory<>("name_product"));
        price.setCellValueFactory(new PropertyValueFactory<>("price"));
        total.setCellValueFactory(new PropertyValueFactory<>("total"));
        quntity.setCellValueFactory(new PropertyValueFactory<>("quntity"));
        code.setCellValueFactory(new PropertyValueFactory<>("code"));
    }

    public static void sum_table() {
        sum = 0.0;
        for (int i = 0; i <= table.getItems().size() - 1; i++) {
            if (table.getItems().size() == 0) {
                total_table.setText("");
            } else {
                sum += Double.parseDouble(table.getColumns().get(0).getCellObservableValue(i).getValue().toString());
                total_table.setText(sum.toString());
            }
        }
    }

    public static void show_table_pone(String code_pone) {
        try {
            num_pone.setText(code_pone);
            table.getItems().clear();
            connect = (Connection) DriverManager.getConnection(add_3, user, passwordDB);
            database.s = connect.prepareStatement("select * from replace_?");
            database.s.setInt(1, Integer.parseInt(code_pone));
            database.r = database.s.executeQuery();
            while (database.r.next()) {
                show_table();
                list.add(
                        new table_model(database.r.getString("total"), database.r.getString("quntity"), database.r.getString("price"), database.r.getString("name_products"), database.r.getString("code")));
                table.setItems(list);
            }
            database.close_database();
            connect = (Connection) DriverManager.getConnection(add_4, user, passwordDB);
            database.s = connect.prepareStatement("select * from pone where num_pone = ?");
            database.s.setString(1, code_pone);
            database.r = database.s.executeQuery();
            database.r.next();
            total_table.setText(database.r.getString("total"));
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }
}
