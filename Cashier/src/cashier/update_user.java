package cashier;

import java.io.File;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

public class update_user extends Application implements EventHandler<ActionEvent> {

    Stage stage = new Stage();
    Rectangle2D bounds = Screen.getPrimary().getVisualBounds();
    TextField user_name = new TextField();
    TextField password = new TextField();
    TextField num = new TextField();
    Button update = new Button("تعديل");
    CheckBox box = new CheckBox("ادمن");
    CheckBox box1 = new CheckBox("مستخدم ");
    database base = new database();
    Pane root;
    CheckBox box3 = new CheckBox("الوردية الصباحية");
    CheckBox box4 = new CheckBox("الوردية المسائية ");
    String kind = null;
    String per = null;

    @Override
    public void start(Stage Stage) throws Exception {

        stage.setTitle("تعديل مستخدم ");
        Pane root = new Pane();
        Scene scene = new Scene(root, 500, 300);
        stage.setX(bounds.getWidth() / 3);
        stage.setY(bounds.getHeight() / 3);
        stage.setResizable(false);
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
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        if (e.getCode() == KeyCode.BACK_SPACE) {
                            if (num.getText().length() == 0 || num.getText().toString() == null) {
                                user_name.setText("");
                                password.setText("");
                                box1.setSelected(false);
                                box.setSelected(false);
                                box3.setSelected(false);
                                box4.setSelected(false);
                            }
                        }
                    }
                });
                if (e.getCode() == KeyCode.ENTER) {
                    if (num.getText().length() == 0 || num.getText().toString() == null) {
                        JOptionPane.showMessageDialog(null, "من فضلك ادخل اسم المستخدم");
                    } else if (base.check(num.getText()).equals(false)) {
                        JOptionPane.showMessageDialog(null, "خطأ فى رقم المستخدم");
                        num.setText("");
                    } else if (base.check(num.getText()) == true) {
                        if (num.getText().length() != 0 && user_name.getText().length() == 0) {
                            user_name.setText(base.get_user(num.getText()));
                            password.setText(base.get_password(num.getText()));
                            if (base.get_condition(num.getText()).equals("admin")) {
                                box.setSelected(true);
                                kind = "admin";
                            } else if (base.get_condition(num.getText()).equals("user")) {
                                box1.setSelected(true);
                                kind = "user";
                            }
                            if (base.get_perid(num.getText()).equals("AM")) {
                                box3.setSelected(true);
                                per = "AM";
                            } else if (base.get_perid(num.getText()).equals("PM")) {
                                box4.setSelected(true);
                                per = "PM";
                            }
                        } else if (num.getText().length() != 0 && user_name.getText().length() != 0 && password.getText().length() != 0 && kind != null && per != null) {
                            if (box.isSelected() == true) {
                                kind = "admin";
                            } else if (box1.isSelected() == true) {
                                kind = "user";
                            }
                            if (box3.isSelected() == true) {
                                per = "AM";
                            } else if (box4.isSelected() == true) {
                                per = "PM";
                            }
                            base.update_user(user_name.getText(), password.getText(), Integer.parseInt(num.getText()), kind, per);
                            num.setText("");
                            user_name.setText("");
                            password.setText("");
                            box.setSelected(false);
                            box1.setSelected(false);
                            box3.setSelected(false);
                            box4.setSelected(false);
                            per = null;
                            kind = null;
                        }
                    }
                }

            } catch (Exception ex) {
            }
        });
        update.setOnAction(this);
        user_name.setPromptText("اسم المستخدم");
        num.setPromptText("رقم المستخدم");
        password.setPromptText("كلمة المرور");
        user_name.setAlignment(Pos.CENTER_RIGHT);
        password.setAlignment(Pos.CENTER_RIGHT);
        num.setAlignment(Pos.CENTER_RIGHT);
        root.setStyle("-fx-background-color:#eee");
        stage.setScene(scene);
        num.setLayoutX(170);
        num.setLayoutY(40);
        user_name.setLayoutX(170);
        user_name.setLayoutY(70);
        password.setLayoutX(170);
        password.setLayoutY(100);
        box.setLayoutX(170);
        box.setLayoutY(130);
        box1.setLayoutX(260);
        box1.setLayoutY(130);
        box4.setLayoutX(140);
        box4.setLayoutY(160);
        box3.setLayoutX(280);
        box3.setLayoutY(160);
        update.setLayoutX(225);
        update.setLayoutY(220);
        root.getChildren().addAll(user_name, password, num, update, box, box1, box3, box4);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void handle(ActionEvent event) {
        if (event.getSource() == update) {
            try {
                if (num.getText().length() == 0 || num.getText().toString() == null) {
                    JOptionPane.showMessageDialog(null, "من فضلك ادخل اسم المستخدم");
                } else if (base.check(num.getText()).equals(false)) {
                    JOptionPane.showMessageDialog(null, "خطأ فى رقم المستخدم");
                    num.setText("");
                } else if (base.check(num.getText()) == true) {
                    if (num.getText().length() != 0 && user_name.getText().length() == 0) {
                        user_name.setText(base.get_user(num.getText()));
                        password.setText(base.get_password(num.getText()));
                        if (base.get_condition(num.getText()).equals("admin")) {
                            box.setSelected(true);
                            kind = "admin";
                        } else if (base.get_condition(num.getText()).equals("user")) {
                            box1.setSelected(true);
                            kind = "user";
                        }
                        if (base.get_perid(num.getText()).equals("AM")) {
                            box3.setSelected(true);
                            per = "AM";
                        } else if (base.get_perid(num.getText()).equals("PM")) {
                            box4.setSelected(true);
                            per = "PM";
                        }
                    } else if (num.getText().length() != 0 && user_name.getText().length() != 0 && password.getText().length() != 0 && kind != null && per != null) {
                        if (box.isSelected() == true) {
                            kind = "admin";
                        } else if (box1.isSelected() == true) {
                            kind = "user";
                        }
                        if (box3.isSelected() == true) {
                            per = "AM";
                        } else if (box4.isSelected() == true) {
                            per = "PM";
                        }
                        base.update_user(user_name.getText(), password.getText(), Integer.parseInt(num.getText()), kind, per);
                        num.setText("");
                        user_name.setText("");
                        password.setText("");
                        box.setSelected(false);
                        box1.setSelected(false);
                        box3.setSelected(false);
                        box4.setSelected(false);
                        per = null;
                        kind = null;
                    }
                }
            } catch (Exception ex) {
            }
        }
    }
}
