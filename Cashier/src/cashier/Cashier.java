package cashier;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class Cashier extends Application implements EventHandler<ActionEvent> {

    private TextField user_name = new TextField();
    private PasswordField password = new PasswordField();
    private TextField num = new TextField();
    private Button login = new Button("تسجيل الدخول");
    private Rectangle2D bounds = Screen.getPrimary().getVisualBounds();
    protected database base = new database();
    private Pane root = new Pane();
    private Boolean user = false;
    protected main_page page = new main_page();
    Stage stage = new Stage();
    Alert a = new Alert(AlertType.ERROR);

    @Override
    public void start(Stage stage) throws Exception {
        Scene scene = new Scene(root, 500, 300);
        stage.setX(bounds.getWidth() / 3);
        stage.setY(bounds.getHeight() / 3);
        Platform.runLater(() -> root.requestFocus());
        stage.setTitle("تسجيل الدخول");
        stage.setResizable(false);
        root.setId("root");
        stage.setScene(scene);
        try {
            stage.getIcons().add(new Image("cashier\\download.png"));
            String css = this.getClass().getResource("index.css").toExternalForm();
            scene.getStylesheets().add(css);
            a.getDialogPane().setStyle("-fx-font-size: 13px; -fx-font-weight:bold; ");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        Platform.runLater(() -> num.requestFocus());
        user_name.setPromptText("اسم المستخدم");
        num.setPromptText("رقم المستخدم");
        password.setPromptText("كلمة المرور");
        user_name.setAlignment(Pos.CENTER_RIGHT);
        password.setAlignment(Pos.CENTER_RIGHT);
        num.setAlignment(Pos.CENTER_RIGHT);

        // keyboard 
        login.setOnAction(this);
        scene.addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent e) -> {
            try {
                if (e.getCode() == KeyCode.ENTER) {
                    if (num.getText().length() == 0 || num.getText() == null) {
                        a.setHeaderText("من فضلك ادخل رقم المستخدم");
                        a.show();
                    } else if (base.check(num.getText()) == false) {
                        a.setHeaderText("عفوا خطأ فى رقم المستخدم");
                        a.show();
                        num.setText("");
                    } else if (base.check(num.getText()) == true && user == false) {
                        Platform.runLater(() -> password.requestFocus());
                        user = true;
                    } else if (user == true) {
                        if (password.getText().length() == 0 || password.getText() == null) {
                            a.setHeaderText("من فضلك ادخل كلمة المرور");
                            a.show();
                            Platform.runLater(() -> password.requestFocus());
                        } else if (base.get_password(num.getText()).equalsIgnoreCase(password.getText())) {
                            new main_page().start(page.stage);
                            stage.close();
                        } else if (base.get_password(num.getText()) != password.getText() && password.getText().length() != 0) {
                            a.setHeaderText("خطأ فى كلمة المرور");
                            a.show();
                            password.setText("");
                            Platform.runLater(() -> password.requestFocus());
                        }
                    }
                }

            } catch (Exception ex) {
            }
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
                        if (base.check(num.getText()) == true) {
                            user_name.setText(base.get_user(num.getText()));
                        } else {
                            user_name.setText("");
                        }
                    } else if (num.getText().length() == 0 || num.getText().toString() == null) {
                        user_name.setText("");
                    } else if (e.getCode() == KeyCode.BACK_SPACE) {
                        if (base.check(num.getText()) == true) {
                            user_name.setText(base.get_user(num.getText()));
                        } else {
                            user_name.setText("");
                        }
                    }
                }
            });
        }
        );
        login.setId("login");
        num.setLayoutX(
                300);
        num.setLayoutY(
                90);
        user_name.setLayoutX(
                300);
        user_name.setLayoutY(
                120);
        password.setLayoutX(
                300);
        password.setLayoutY(
                150);
        login.setLayoutX(
                335);
        login.setLayoutY(
                190);
        root.getChildren()
                .addAll(user_name, password, num, login);
        a.getDialogPane().setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);

        a.initOwner(stage);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void handle(ActionEvent event) {
        if (event.getSource() == login) {
            try {
                if (num.getText().length() == 0 || num.getText() == null) {
                    a.setHeaderText("من فضلك ادخل رقم المستخدم");
                    a.show();
                } else if (base.check(num.getText()) == false) {
                    a.setHeaderText("عفوا خطأ فى رقم المستخدم");
                    a.show();
                    num.setText("");
                } else if (base.check(num.getText()) == true && user == false) {
                    Platform.runLater(() -> password.requestFocus());
                    user = true;
                } else if (user == true) {
                    if (password.getText().length() == 0 || password.getText() == null) {
                        a.setHeaderText("من فضلك ادخل كلمة المرور");
                        a.show();

                        Platform.runLater(() -> password.requestFocus());

                    } else if (base.get_password(num.getText()).equalsIgnoreCase(password.getText())) {
                        new main_page().start(page.stage);
                        stage.close();
                    } else if (base.get_password(num.getText()) != password.getText() && password.getText().length() != 0) {
                        a.setHeaderText("خطأ فى كلمة المرور");
                        a.show();
                        password.setText("");
                        Platform.runLater(() -> password.requestFocus());
                    }
                }
            } catch (Exception ex) {
            }
        }
    }
}
