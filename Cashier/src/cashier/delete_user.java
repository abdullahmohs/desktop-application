package cashier;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

public class delete_user extends Application implements EventHandler<ActionEvent> {

    Pane root = new Pane();
    Scene scene = new Scene(root, 500, 300);
    Rectangle2D bounds = Screen.getPrimary().getVisualBounds();
    TextField user_name = new TextField();
    PasswordField password = new PasswordField();
    TextField num = new TextField();
    Button delete = new Button("حذف");
    database base = new database();
    Boolean user = false;

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("حذف مستخدم");
        stage.setX(bounds.getWidth() / 3);
        stage.setY(bounds.getHeight() / 3);
        Platform.runLater(() -> root.requestFocus());
        try {
             stage.getIcons().add(new Image("cashier\\download.png"));
            String css = this.getClass().getResource("index.css").toExternalForm();
            scene.getStylesheets().add(css);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        scene.addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent e) -> {
            try {
                if (e.getCode() == KeyCode.ENTER) {
                    if (num.getText().length() == 0 || num.getText().toString() == null) {
                        JOptionPane.showMessageDialog(null, "من فضلك ادخل رقم المستخدم");
                    } else if (base.check(num.getText()) == false) {
                        JOptionPane.showMessageDialog(null, "عفوا خطأ فى رقم المستخدم");
                        num.setText("");
                    } else if (base.check(num.getText()) == true && user == false) {
                        Platform.runLater(() -> password.requestFocus());
                        user = true;
                    } else if (user == true) {
                        if (password.getText().length() == 0 || password.getText().toString() == null) {
                            JOptionPane.showMessageDialog(null, "من فضلك ادخل كلمة المرور");
                            Platform.runLater(() -> password.requestFocus());
                        } else if (base.get_password(num.getText()).equalsIgnoreCase(password.getText())) {
                            base.delete_user(num.getText());
                            num.setText("");
                            user_name.setText("");
                            password.setText("");
                            Platform.runLater(() -> num.requestFocus());
                        } else if (base.get_password(num.getText()) != password.getText() && password.getText().length() != 0) {
                            JOptionPane.showMessageDialog(null, "خطأ فى كلمة المرور");
                            password.setText("");
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
        num.setLayoutX(170);
        num.setLayoutY(40);
        user_name.setLayoutX(170);
        user_name.setLayoutY(70);
        password.setLayoutX(170);
        password.setLayoutY(100);
        delete.setLayoutX(230);
        delete.setLayoutY(140);
        user_name.setPromptText("اسم المستخدم");
        num.setPromptText("رقم المستخدم");
        password.setPromptText("كلمة المرور");
        user_name.setAlignment(Pos.CENTER_RIGHT);
        password.setAlignment(Pos.CENTER_RIGHT);
        num.setAlignment(Pos.CENTER_RIGHT);
        root.setStyle("-fx-background-color:#eee");
        root.getChildren().addAll(user_name, num, password, delete);

        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void handle(ActionEvent event) {
        if (event.getSource() == delete) {
            try {
                if (num.getText().length() == 0 || num.getText().toString() == null) {
                    JOptionPane.showMessageDialog(null, "من فضلك ادخل رقم المستخدم");
                } else if (base.check(num.getText()) == false) {
                    JOptionPane.showMessageDialog(null, "عفوا خطأ فى رقم المستخدم");
                    num.setText("");
                } else if (base.check(num.getText()) == true && user == false) {
                    Platform.runLater(() -> password.requestFocus());
                    user = true;
                } else if (user == true) {
                    if (password.getText().length() == 0 || password.getText().toString() == null) {
                        JOptionPane.showMessageDialog(null, "من فضلك ادخل كلمة المرور");
                        Platform.runLater(() -> password.requestFocus());
                    } else if (base.get_password(num.getText()).equalsIgnoreCase(password.getText())) {
                        base.delete_user(num.getText());
                        num.setText("");
                        user_name.setText("");
                        password.setText("");
                        
                    } else if (base.get_password(num.getText()) != password.getText() && password.getText().length() != 0) {
                        JOptionPane.showMessageDialog(null, "خطأ فى كلمة المرور");
                        password.setText("");
                    }
                }

            } catch (Exception ex) {
            }
        }
    }
}
