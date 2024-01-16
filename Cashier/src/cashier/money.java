package cashier;

import java.time.LocalDate;
import java.util.Locale;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class money extends Application implements EventHandler<ActionEvent> {

    protected Stage stage = new Stage();
    protected Rectangle2D bounds = Screen.getPrimary().getVisualBounds();
    protected TextField user_name = new TextField();
    protected TextField mon = new TextField();
    protected TextField money = new TextField();
    protected TextField mins = new TextField();
    protected Button search = new Button("");
    protected database base = new database();
    protected Pane root;
    Label l_name = new Label("اسم المستخدم");
    Label l_mon = new Label("المرتب الشهري");
    Label l_money = new Label("السلف");
    Label l_mins = new Label("الباقى");

    @Override
    public void start(Stage Stage) throws Exception {
        DatePicker picker = new DatePicker();
        picker.setValue(LocalDate.now());
        LocalDate date = picker.getValue();
        Locale.setDefault(new Locale("ar"));
        stage.setTitle("المرتبات");
        Label l_date = new Label("التاريخ");
        Pane root = new Pane();
        Scene scene = new Scene(root, 500, 300);
        stage.setX(bounds.getWidth() / 3);
        stage.setY(bounds.getHeight() / 3);
        stage.setResizable(false);
        try {
            stage.getIcons().add(new Image("cashier\\download.png"));
            String css = this.getClass().getResource("index.css").toExternalForm();
            scene.getStylesheets().add(css);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        picker.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getClickCount() == 2) {
                }
            }
        });
        Platform.runLater(() -> {
            picker.setOnAction(e -> {
                System.out.println(date.toString());
            });

        });

        search.setOnAction(this);
        search.setId("search");
        search.setMinSize(30, 30);
        search.setMaxSize(30, 30);
        user_name.setAlignment(Pos.CENTER_RIGHT);
        mon.setAlignment(Pos.CENTER_RIGHT);
        money.setAlignment(Pos.CENTER_RIGHT);
        mins.setAlignment(Pos.CENTER_RIGHT);
        root.setStyle("-fx-background-color:#eee");
        l_name.setStyle("-fx-font-size:15px; -fx-font-weight:bold; -fx-font-color:balck;");
        l_mon.setStyle("-fx-font-size:15px; -fx-font-weight:bold; -fx-font-color:balck;");
        l_money.setStyle("-fx-font-size:15px; -fx-font-weight:bold; -fx-font-color:balck;");
        l_mins.setStyle("-fx-font-size:15px; -fx-font-weight:bold; -fx-font-color:balck;");
        picker.setMaxSize(130, 30);
        picker.setMinSize(130, 30);
        stage.setScene(scene);
        user_name.setLayoutX(130);
        user_name.setLayoutY(70);
        l_name.setLayoutX(320);
        l_name.setLayoutY(70);
        mon.setLayoutX(130);
        mon.setLayoutY(100);
        l_mon.setLayoutX(320);
        l_mon.setLayoutY(100);
        money.setLayoutX(130);
        money.setLayoutY(130);
        l_money.setLayoutX(320);
        l_money.setLayoutY(130);
        mins.setLayoutX(130);
        mins.setLayoutY(160);
        l_mins.setLayoutX(320);
        l_mins.setLayoutY(160);
        search.setLayoutX(90);
        search.setLayoutY(70);
        picker.setLayoutX(150);
        picker.setLayoutY(10);
        l_date.setLayoutX(300);
        l_date.setLayoutY(10);
        l_date.setStyle("-fx-font-size:18px; -fx-font-weight:bold; ");
        root.getChildren().addAll(user_name, mins, mon, money, search, l_name, l_mon, l_money, l_mins, picker, l_date);

        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void handle(ActionEvent event) {
        if (event.getSource() == search) {
            try {

            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
}
