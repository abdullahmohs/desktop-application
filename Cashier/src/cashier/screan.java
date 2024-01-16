package cashier;

import static cashier.database.add;
import static cashier.database.add_1;
import static cashier.database.connect;
import static cashier.database.passwordDB;
import static cashier.database.user;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
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
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.text.TextAlignment;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class screan extends Application implements EventHandler<ActionEvent> {

    database base = new database();
    int num_row;
    private static Double sum = 0.0;
    Double t = 0.0;
    private static Double s;
    int recept;
    Button b_pone, b_replace, details;
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
    private static TextField offer;
    private static TextField cash;
    private static TextField total_table;
    private static TextField mins;
    private static TextField parcode;
    private static ObservableList<table_model> list;
    int num;
    Alert a = new Alert(AlertType.INFORMATION);
    static Label qun;
    String par;
    private static String check = "false";

    @Override

    public void start(Stage stage) throws Exception {
        stage.setTitle("شاشة البيع");
        Pane root = new Pane();
        Rectangle2D bounds = Screen.getPrimary().getVisualBounds();
        Scene scene = new Scene(root);
        table = new TableView();
        price = new TableColumn("السعر");
        name_product = new TableColumn("اســـم الصنـــف");
        quntity = new TableColumn("الكمية");
        total = new TableColumn("الإجمالى");
        code = new TableColumn("كود الصنف");
        parcode = new TextField();
        num_pone = new TextField();
        offer = new TextField();
        cash = new TextField();
        total_table = new TextField();
        mins = new TextField();
        Label l_total = new Label("الإجمالى");
        Label l_cash = new Label("المدفوع");
        Label l_mins = new Label("الباقى");
        Label l_parcode = new Label("الباركود");
        Label l_date = new Label("التاريخ");
        Label l_pone = new Label("رقم الحركة");
        Label l_offer = new Label("العدد");
        Label l_next = new Label("التالي");
        Label l_back = new Label("السابق");
        Label l_details = new Label("التعليمات");
        Label l_replace = new Label("المرتجع");
        qun = new Label("");
        details = new Button();
        b_pone = new Button();
        b_replace = new Button();
        list = FXCollections.observableArrayList();
        DatePicker date = new DatePicker();
        date.setValue(LocalDate.now());
        Locale.setDefault(new Locale("ar"));
        next = new Button("");
        back = new Button("");
        recept = base.get_num_pone();
        Tooltip tip = new Tooltip("");
        details.setTooltip(tip);

        try {

            details.setContentDisplay(ContentDisplay.RIGHT);
            stage.getIcons().add(new Image("cashier\\download.png"));
            String css = this.getClass().getResource("index.css").toExternalForm();
            scene.getStylesheets().add(css);
            table.getColumns().addAll(total, price, quntity, name_product, code);
            num_pone.setText(Integer.toString(recept));
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        details.setId("de");

        parcode.addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent e) -> {
            try {
                if (e.getCode() == KeyCode.ENTER) {
                    if (parcode.getText().length() == 0 || parcode.getText() == null) {
                    } else if (database.check_parcode(parcode.getText()) == true || database.check_code(parcode.getText()) == true) {
                        par = parcode.getText();
                        show_table();
                        if (database.check_parcode(parcode.getText()) == true && Integer.parseInt(database.get_quntity(parcode.getText())) > 0) {
                            plus_column(par);
                            if (check == "false") {
                                list.addAll(
                                        new table_model(database.get_price(parcode.getText()), "1", database.get_price(parcode.getText()), base.get_name(parcode.getText()), database.get_code(parcode.getText())));
                                table.setItems(list);
                                parcode.setText("");
                                sum_table();
                                show_quntity(par);
                            }
                        } else if (database.check_code(parcode.getText()) == true && Integer.parseInt(database.getQuntity(parcode.getText())) > 0) {
                            plus_column(par);
                            if (check == "false") {
                                list.addAll(new table_model(database.getPrice(parcode.getText()), "1", database.getPrice(parcode.getText()), database.getName(parcode.getText()), parcode.getText()));
                                table.setItems(list);
                                parcode.setText("");
                                sum_table();
                                show_quntity(par);
                            }
                        } else {
                            a.setHeaderText("عفوا لا توجد كمية كافية");
                            a.show();
                            parcode.setText("");
                        }
                    } else {
                        a.setHeaderText("عفوا الصنف غير مسجل");
                        a.show();
                        parcode.setText("");
                    }

                }
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        });

        num_pone.addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent e) -> {
            if (e.getCode() == KeyCode.ENTER) {
                if (Integer.parseInt(num_pone.getText()) < base.get_num_pone()) {
                    try {
                        table.getItems().clear();
                        connect = (Connection) DriverManager.getConnection(add_1, user, passwordDB);
                        database.s = connect.prepareStatement("select * from pone_?");
                        database.s.setInt(1, Integer.parseInt(num_pone.getText()));
                        database.r = database.s.executeQuery();
                        while (database.r.next()) {
                            show_table();
                            list.add(
                                    new table_model(database.r.getString("total"), database.r.getString("quntity"), database.r.getString("price"), base.r.getString("name_products"), base.r.getString("code")));
                            table.setItems(list);
                        }
                        database.close_database();
                        connect = (Connection) DriverManager.getConnection(add, user, passwordDB);
                        database.s = connect.prepareStatement("select * from pone where num_pone = ?");
                        database.s.setString(1, num_pone.getText());
                        database.r = database.s.executeQuery();
                        database.r.next();
                        total_table.setText(database.r.getString("total"));
                        cash.setText(database.r.getString("cash"));
                        mins.setText(database.r.getString("mins"));
                        offer.setText(database.r.getString("number"));
                    } catch (SQLException ex) {
                        System.out.println(ex.getMessage());
                    }
                }
                if (Integer.parseInt(num_pone.getText()) == base.get_num_pone()) {
                    Platform.runLater(() -> {
                        table.getItems().clear();
                        total_table.setText("");
                        mins.setText("");
                        cash.setText("");
                        offer.setText("");
                        parcode.requestFocus();
                    });
                }

            }
        });

        scene.addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent e) -> {
            try {
                if (e.getCode() == KeyCode.DELETE) {
                    Platform.runLater(() -> {
                        try {
                            if (!table.getItems().isEmpty()) {
                                table.getItems().remove(num_row);
                                qun.setVisible(false);
                                if (table.getItems().size() == 0 && total_table.getText() != null) {
                                    total_table.setText("");
                                    offer.setText("");
                                }
                                for (int i = 0; i < table.getItems().size(); i++) {
                                    if (table.getItems().size() == 0 && !total_table.getText().isEmpty()) {
                                        total_table.setText("");
                                        offer.setText("");
                                    } else {
                                        sum_table();
                                    }
                                }
                            }
                        } catch (NumberFormatException ex) {
                        }
                    });
                } else if (e.getCode() == KeyCode.BACK_SPACE) {
                    Platform.runLater(() -> {
                        if (cash.getText().length() == 0 || cash.getText() == null) {
                            if (total_table.getText().length() != 0 && total_table.getText() != null) {
                                mins.setText("");
                            }
                        } else {
                            t = Double.parseDouble(cash.getText()) - Double.parseDouble(total_table.getText());
                            mins.setText(t.toString());
                            t = 0.0;
                        }
                    });
                } else if (e.getCode() == KeyCode.F5) {
                    qun.setVisible(false);
                    date.setValue(LocalDate.now());
                    if (table.getItems().isEmpty()) {
                    } else if (base.get_num_pone() != Integer.parseInt(num_pone.getText())) {
                        a.setHeaderText("هذا الرقم موجود بالفعل");
                        a.show();
                        num_pone.setText(Integer.toString(base.get_num_pone()));
                        table.getItems().clear();
                        total_table.setText("");
                        cash.setText("");
                        mins.setText("");
                        offer.setText("");
                        Platform.runLater(() -> parcode.requestFocus());
                    } else {
                        base.craete_table(recept);
                        for (int i = 0; i < table.getItems().size(); i++) {
                            base.add_pone(recept, table.getColumns().get(0).getCellObservableValue(i).getValue().toString(), table.getColumns().get(1).getCellObservableValue(i).getValue().toString(), table.getColumns().get(2).getCellObservableValue(i).getValue().toString(), table.getColumns().get(3).getCellObservableValue(i).getValue().toString(), table.getColumns().get(4).getCellObservableValue(i).getValue().toString());
                            base.mins_quntity(Integer.parseInt(table.getColumns().get(4).getCellObservableValue(i).getValue().toString()), table.getColumns().get(2).getCellObservableValue(i).getValue().toString());
                        }
                        base.update_details(recept, total_table.getText(), cash.getText(), mins.getText(), offer.getText(), date.getValue().toString());
                        num_pone.setText(Integer.toString(++recept));
                        base.detail_pone(recept);
                        table.getItems().clear();
                        total_table.setText("");
                        cash.setText("");
                        mins.setText("");
                        offer.setText("");
                        Platform.runLater(() -> parcode.requestFocus());
                    }
                } else if (e.getCode() == KeyCode.F3) {

                    try {
                        qun.setVisible(false);
                        new search_product().start(new search_product().stage);
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }
                }
                Platform.runLater(() -> {
                    if (cash.getText().length() == 0 || cash.getText() == null) {
                        if (cash.getText().length() != 0 && total_table.getText() != null) {
                            mins.setText(Double.toString(Double.parseDouble(total_table.getText()) * -1));
                        }
                    } else {
                        t = Double.parseDouble(cash.getText()) - Double.parseDouble(total_table.getText());
                        mins.setText(t.toString());
                        t = 0.0;
                    }
                });
            } catch (HeadlessException | NumberFormatException ex) {
                System.out.println(ex.getMessage());
            }
        });

        //button
        b_pone.setOnAction(this);
        b_replace.setOnAction(this);
        back.setOnAction(this);
        next.setOnAction(this);
        details.setOnAction(this);

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
        details.setShape(new Circle(5));
        b_replace.setShape(new Circle(5));
        stage.setMaximized(true);
        price.setId("column");
        name_product.setId("column");
        quntity.setId("column");
        total.setId("column");
        code.setId("column");
        l_total.setId(
                "L");
        l_cash.setId(
                "L");
        l_mins.setId(
                "L");
        l_offer.setId(
                "L");
        b_pone.setId(
                "search");
        b_replace.setId("replace");
        next.setId(
                "next");
        back.setId(
                "back");
        back.setRotate(180);
        parcode.setMaxSize(300, 30);
        parcode.setMinSize(300, 30);
        table.autosize();
        table.setMaxHeight(800);
        table.setMinHeight(800);
        name_product.setMinWidth(435);
        price.setMinWidth(180);
        quntity.setMinWidth(180);
        total.setMinWidth(180);
        code.setMaxWidth(120);
        code.setMinWidth(120);
        l_parcode.setLayoutX(470);
        l_parcode.setLayoutY(10);
        details.setMaxSize(40, 40);
        details.setMinSize(40, 40);
        b_replace.setMaxSize(45, 45);
        b_replace.setMinSize(45, 45);
        parcode.setLayoutX(350);
        parcode.setLayoutY(50);
        table.setLayoutX(145);
        table.setLayoutY(100);
        l_back.setLayoutX(530);
        l_back.setLayoutY(910);
        back.setLayoutX(600);
        back.setLayoutY(910);
        l_next.setLayoutX(690);
        l_next.setLayoutY(910);
        next.setLayoutX(650);
        next.setLayoutY(910);
        l_total.setLayoutX(15);
        l_total.setLayoutY(130);
        total_table.setLayoutX(5);
        total_table.setLayoutY(180);
        cash.setLayoutX(5);
        cash.setLayoutY(380);
        l_cash.setLayoutX(15);
        l_cash.setLayoutY(330);
        mins.setLayoutX(5);
        mins.setLayoutY(580);
        l_mins.setLayoutX(25);
        l_mins.setLayoutY(530);
        l_offer.setLayoutX(30);
        l_offer.setLayoutY(730);
        offer.setLayoutX(5);
        offer.setLayoutY(780);
        total_table.setMinSize(
                130, 60);
        total_table.setMaxSize(
                130, 60);
        cash.setMinSize(
                130, 60);
        cash.setMaxSize(
                130, 60);
        mins.setMinSize(
                130, 60);
        mins.setMaxSize(
                130, 60);
        offer.setMaxSize(
                130, 60);
        offer.setMinSize(
                130, 60);
        qun.setLayoutX(60);
        qun.setLayoutY(50);
        date.setLayoutX(690);
        date.setLayoutY(50);
        l_date.setLayoutX(720);
        l_date.setLayoutY(10);
        date.setMinSize(130, 30);
        date.setMaxSize(130, 30);
        b_pone.setMinSize(30, 30);
        b_pone.setMaxSize(30, 30);
        b_pone.setLayoutX(850);
        b_pone.setLayoutY(50);
        num_pone.setLayoutX(880);
        num_pone.setLayoutY(50);
        l_pone.setLayoutX(900);
        l_pone.setLayoutY(10);
        tip.setTextAlignment(TextAlignment.CENTER);
        tip.setText(tip.getText() + "\n" + "الحفظ" + " F5");
        tip.setText(tip.getText() + "\n" + "البحث" + " F3");
        num_pone.setMinSize(
                150, 30);
        num_pone.setMaxSize(
                150, 30);
        details.setLayoutX(1050);
        details.setLayoutY(40);
        b_replace.setLayoutX(1160);
        b_replace.setLayoutY(40);
        l_replace.setLayoutX(1155);
        l_replace.setLayoutY(10);
        l_details.setLayoutX(1035);
        l_details.setLayoutY(10);
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
        mins.setEditable(
                false);
        offer.setEditable(
                false);
        parcode.setAlignment(Pos.CENTER_RIGHT);

        num_pone.setAlignment(Pos.CENTER_RIGHT);

        total_table.setAlignment(Pos.CENTER_RIGHT);

        cash.setAlignment(Pos.CENTER_RIGHT);

        mins.setAlignment(Pos.CENTER_RIGHT);

        try {
            total_table.setStyle("-fx-font-size:30px; -fx-alignment: CENTER;");
            cash.setStyle("-fx-font-size:30px; -fx-alignment: CENTER;");
            mins.setStyle("-fx-font-size:30px; -fx-alignment: CENTER;");
            offer.setStyle("-fx-font-size:30px; -fx-alignment: CENTER;");
            root.setStyle("-fx-background-color:#eee");
            l_parcode.setStyle("-fx-font-size:20px; -fx-font-weight:bold;");
            tip.setStyle("-fx-font-size:20px; -fx-font-weight:bold;");
            l_date.setStyle("-fx-font-size:20px; -fx-font-weight:bold;");
            l_pone.setStyle("-fx-font-size:20px; -fx-font-weight:bold; -fx-alignment: CENTER;");
            l_details.setStyle("-fx-font-size:20px; -fx-font-weight:bold; -fx-alignment: CENTER;");
            l_replace.setStyle("-fx-font-size:20px; -fx-font-weight:bold; -fx-alignment: CENTER;");
            l_next.setStyle("-fx-font-size:20px; -fx-font-weight:bold;-fx-font-color:black;");
            l_back.setStyle("-fx-font-size:20px; -fx-font-weight:bold; -fx-font-color:black;");
            qun.setStyle("-fx-font-size:20px; -fx-font-weight:bold; -fx-font-color:black;");
            num_pone.setStyle("-fx-font-size:20px; -fx-font-weight:bold; -fx-alignment: CENTER;");
            details.setStyle("-fx-color:black");
            a.getDialogPane().setStyle("-fx-font-size: 13px; -fx-font-weight:bold; -fx-alignment: CENTER;");

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        root.getChildren()
                .addAll(parcode, table, cash, mins, total_table, l_total, l_cash, l_mins, date, l_next, qun);
        root.getChildren()
                .addAll(l_date, l_parcode, l_pone, num_pone, b_pone, offer, l_offer, next, back, l_back, details, l_details, b_replace, l_replace);
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
                qun.setVisible(false);
                new search_pone().start(new search_pone().stage);
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        } else if (event.getSource() == back) {
            try {
                qun.setVisible(false);
                if (table.getItems().size() != 0 && base.get_num_pone() == Integer.parseInt(num_pone.getText())) {
                } else {
                    num = Integer.parseInt(num_pone.getText());
                    num--;
                    if (num > 0) {
                        num_pone.setText(Integer.toString(num));
                        table.getItems().clear();
                        connect = (Connection) DriverManager.getConnection(add_1, user, passwordDB);
                        database.s = connect.prepareStatement("select * from pone_?");
                        database.s.setInt(1, Integer.parseInt(num_pone.getText()));
                        database.r = database.s.executeQuery();
                        while (database.r.next()) {
                            show_table();
                            list.add(
                                    new table_model(database.r.getString("total"), database.r.getString("quntity"), database.r.getString("price"), database.r.getString("name_products"), database.r.getString("code")));
                            table.setItems(list);
                        }
                        database.close_database();
                        connect = (Connection) DriverManager.getConnection(add, user, passwordDB);
                        database.s = connect.prepareStatement("select * from pone where num_pone = ?");
                        database.s.setString(1, num_pone.getText());
                        database.r = database.s.executeQuery();
                        database.r.next();
                        total_table.setText(database.r.getString("total"));
                        cash.setText(database.r.getString("cash"));
                        mins.setText(database.r.getString("mins"));
                        offer.setText(database.r.getString("number"));
                    }
                }
            } catch (NumberFormatException | SQLException ex) {
                System.out.println(ex.getMessage());
            }
        } else if (event.getSource() == next) {
            try {
                qun.setVisible(false);
                num = Integer.parseInt(num_pone.getText());
                num++;
                if (num < base.get_num_pone()) {

                    num_pone.setText(Integer.toString(num));
                    table.getItems().clear();
                    connect = (Connection) DriverManager.getConnection(add_1, user, passwordDB);
                    database.s = connect.prepareStatement("select * from pone_?");
                    database.s.setInt(1, Integer.parseInt(num_pone.getText()));
                    database.r = database.s.executeQuery();
                    while (database.r.next()) {
                        show_table();
                        list.add(
                                new table_model(database.r.getString("total"), database.r.getString("quntity"), database.r.getString("price"), database.r.getString("name_products"), database.r.getString("code")));
                        table.setItems(list);
                    }
                    database.close_database();
                    connect = (Connection) DriverManager.getConnection(add, user, passwordDB);
                    database.s = connect.prepareStatement("select * from pone where num_pone = ?");
                    database.s.setString(1, num_pone.getText());
                    database.r = database.s.executeQuery();
                    database.r.next();
                    total_table.setText(database.r.getString("total"));
                    cash.setText(database.r.getString("cash"));
                    mins.setText(database.r.getString("mins"));
                    offer.setText(database.r.getString("number"));
                }
            } catch (NumberFormatException | SQLException ex) {
                System.out.println(ex.getMessage());
            }
        } else if (event.getSource() == details) {
            Platform.runLater(() -> {
                parcode.requestFocus();
            });
        } else if (event.getSource() == b_replace) {
            try {
                new replace().start(new replace().stage);
            } catch (Exception ex) {
                Logger.getLogger(screan.class.getName()).log(Level.SEVERE, null, ex);
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

    public static void add_column(String code_product) {
        try {
            show_table();
            plus_column(code_product);
            if (check == "false") {
                list.addAll(new table_model(database.getPrice(code_product), "1", database.getPrice(code_product), database.getName(code_product), code_product));
                table.setItems(list);
            }
            sum_table();
            show_quntity(code_product);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void show_table_pone(String code_pone) {
        try {
            qun.setVisible(false);
            num_pone.setText(code_pone);
            table.getItems().clear();
            connect = (Connection) DriverManager.getConnection(add_1, user, passwordDB);
            database.s = connect.prepareStatement("select * from pone_?");
            database.s.setInt(1, Integer.parseInt(code_pone));
            database.r = database.s.executeQuery();
            while (database.r.next()) {
                show_table();
                list.add(
                        new table_model(database.r.getString("total"), database.r.getString("quntity"), database.r.getString("price"), database.r.getString("name_products"), database.r.getString("code")));
                table.setItems(list);
            }
            database.close_database();
            connect = (Connection) DriverManager.getConnection(add, user, passwordDB);
            database.s = connect.prepareStatement("select * from pone where num_pone = ?");
            database.s.setString(1, code_pone);
            database.r = database.s.executeQuery();
            database.r.next();
            total_table.setText(database.r.getString("total"));
            cash.setText(database.r.getString("cash"));
            mins.setText(database.r.getString("mins"));
            offer.setText(database.r.getString("number"));
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public static void sum_table() {
        sum = 0.0;
        for (int i = 0; i <= table.getItems().size() - 1; i++) {
            if (table.getItems().size() == 0) {
                total_table.setText("");
                offer.setText("");
            } else {
                sum += Double.parseDouble(table.getColumns().get(0).getCellObservableValue(i).getValue().toString());
                total_table.setText(sum.toString());
                offer.setText(Integer.toString(table.getItems().size()));
            }
        }
    }

    public static void show_quntity(String par) {
        qun.setVisible(true);
        if (database.check_parcode(par) == true) {
            if (Integer.parseInt(database.get_quntity(par)) >= 100) {
                qun.setStyle("-fx-font-size:20px; -fx-font-weight:bold; -fx-font-color:black; -fx-background-color : green;");
                qun.setText("الكمية : " + database.get_quntity(par));
            } else if (Integer.parseInt(database.get_quntity(par)) < 100 && Integer.parseInt(database.get_quntity(par)) >= 50) {
                qun.setStyle("-fx-font-size:20px; -fx-font-weight:bold; -fx-font-color:black; -fx-background-color : orange;");
                qun.setText("الكمية : " + database.get_quntity(par));
            } else if (Integer.parseInt(database.get_quntity(par)) < 50 && Integer.parseInt(database.get_quntity(par)) > 0) {
                qun.setStyle("-fx-font-size:20px; -fx-font-weight:bold; -fx-font-color:black; -fx-background-color : yellow;");
                qun.setText("الكمية : " + database.get_quntity(par));
            } else if (Integer.parseInt(database.get_quntity(par)) == 0) {
                qun.setStyle("-fx-font-size:20px; -fx-font-weight:bold; -fx-font-color:black; -fx-background-color : red;");
                qun.setText("الكمية : " + database.get_quntity(par));
            }
        } else if (database.check_code(par) == true) {
            if (Integer.parseInt(database.getQuntity(par)) >= 100) {
                qun.setStyle("-fx-font-size:20px; -fx-font-weight:bold; -fx-font-color:black; -fx-background-color : green;");
                qun.setText("الكمية : " + database.getQuntity(par));
            } else if (Integer.parseInt(database.getQuntity(par)) < 100 && Integer.parseInt(database.getQuntity(par)) >= 50) {
                qun.setStyle("-fx-font-size:20px; -fx-font-weight:bold; -fx-font-color:black; -fx-background-color : orange;");
                qun.setText("الكمية : " + database.getQuntity(par));
            } else if (Integer.parseInt(database.getQuntity(par)) < 50 && Integer.parseInt(database.getQuntity(par)) >= 10) {
                qun.setStyle("-fx-font-size:20px; -fx-font-weight:bold; -fx-font-color:black; -fx-background-color : yellow;");
                qun.setText("الكمية : " + database.getQuntity(par));
            } else if (Integer.parseInt(database.getQuntity(par)) < 10 && Integer.parseInt(database.getQuntity(par)) > 0) {
                qun.setStyle("-fx-font-size:20px; -fx-font-weight:bold; -fx-font-color:black; -fx-background-color : yellow;");
                qun.setText("الكمية : " + database.getQuntity(par));
            } else if (Integer.parseInt(database.getQuntity(par)) == 0) {
                qun.setStyle("-fx-font-size:20px; -fx-font-weight:bold; -fx-font-color:black; -fx-background-color : red;");
                qun.setText("الكمية : " + database.getQuntity(par));
            }
        }
    }

    public static String plus_column(String p) {
        if (database.check_parcode(p) == true) {
            if (!table.getItems().isEmpty()) {
                for (int i = 0; i < table.getItems().size(); i++) {
                    if (database.get_code(parcode.getText()).equals(table.getColumns().get(4).getCellObservableValue(i).getValue().toString())) {
                        list.set(i, new table_model(database.get_price(parcode.getText()), Integer.toString(Integer.parseInt(table.getColumns().get(2).getCellObservableValue(i).getValue().toString()) + 1), database.getPrice(table.getColumns().get(4).getCellObservableValue(i).getValue().toString()), database.getName(table.getColumns().get(4).getCellObservableValue(i).getValue().toString()), table.getColumns().get(4).getCellObservableValue(i).getValue().toString()));
                        s = Double.parseDouble(database.getPrice(table.getColumns().get(4).getCellObservableValue(i).getValue().toString())) * Double.parseDouble(table.getColumns().get(2).getCellObservableValue(i).getValue().toString());
                        list.set(i, new table_model(s.toString(), table.getColumns().get(2).getCellObservableValue(i).getValue().toString(), database.getPrice(table.getColumns().get(4).getCellObservableValue(i).getValue().toString()), database.getName(table.getColumns().get(4).getCellObservableValue(i).getValue().toString()), table.getColumns().get(4).getCellObservableValue(i).getValue().toString()));
                        table.setItems(list);
                        parcode.setText("");
                        sum_table();
                        check = "true";
                        break;
                    } else {
                        check = "false";
                    }
                }
            }
        } else if (database.check_code(p) == true) {
            if (!table.getItems().isEmpty()) {
                for (int i = 0; i < table.getItems().size(); i++) {
                    if (p.equals(table.getColumns().get(4).getCellObservableValue(i).getValue().toString())) {
                        list.set(i, new table_model(database.get_price(parcode.getText()), Integer.toString(Integer.parseInt(table.getColumns().get(2).getCellObservableValue(i).getValue().toString()) + 1), database.getPrice(table.getColumns().get(4).getCellObservableValue(i).getValue().toString()), database.getName(table.getColumns().get(4).getCellObservableValue(i).getValue().toString()), table.getColumns().get(4).getCellObservableValue(i).getValue().toString()));
                        s = Double.parseDouble(database.getPrice(table.getColumns().get(4).getCellObservableValue(i).getValue().toString())) * Double.parseDouble(table.getColumns().get(2).getCellObservableValue(i).getValue().toString());
                        list.set(i, new table_model(s.toString(), table.getColumns().get(2).getCellObservableValue(i).getValue().toString(), database.getPrice(table.getColumns().get(4).getCellObservableValue(i).getValue().toString()), database.getName(table.getColumns().get(4).getCellObservableValue(i).getValue().toString()), table.getColumns().get(4).getCellObservableValue(i).getValue().toString()));
                        table.setItems(list);
                        parcode.setText("");
                        sum_table();
                        check = "true";
                        break;
                    } else {
                        check = "false";
                    }
                }
            }
        }
        return check;
    }

}
