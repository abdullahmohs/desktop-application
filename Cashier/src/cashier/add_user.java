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
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class add_user extends Application implements EventHandler<ActionEvent> {

    Stage stage = new Stage();
    Rectangle2D bounds = Screen.getPrimary().getVisualBounds();
    TextField user_name = new TextField();
    PasswordField password = new PasswordField();
    PasswordField password1 = new PasswordField();
    TextField num = new TextField();
    Button add = new Button("إضافة");
    CheckBox box = new CheckBox("ادمن");
    CheckBox box1 = new CheckBox("مستخدم ");
    CheckBox box3 = new CheckBox("الوردية الصباحية");
    CheckBox box4 = new CheckBox("الوردية المسائية ");
    database base = new database();
    Boolean user = false;
    String kind = null;
    String per = null;
    Pane root;
    Alert a = new Alert(AlertType.INFORMATION);

    @Override
    public void start(Stage Stage) throws Exception {
        stage.setTitle("إضافة مستخدم جديد");
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
                if (e.getCode() == KeyCode.ENTER) {
                    if (num.getText().length() == 0 || num.getText().toString() == null) {
                        a.setHeaderText("من فضلك ادخل رقم المستخدم");
                        a.show();
                    } else if (base.check(num.getText()) == false) {
                        Platform.runLater(() -> user_name.requestFocus());
                        if (box.isSelected() == true) {
                            kind = "admin";
                        } else if (box1.isSelected() == true) {
                            kind = "user";
                        }
                        if (box3.isSelected() == true) {
                            per = "الوردية الصباحية";
                        } else if (box4.isSelected() == true) {
                            per = "الوردية المسائية";
                        }
                        if (num.getText().length() != 0 && user_name.getText().length() != 0) {
                            Platform.runLater(() -> password.requestFocus());
                        }
                        if (num.getText().length() != 0 && user_name.getText().length() != 0 && password.getText().length() != 0) {
                            Platform.runLater(() -> password1.requestFocus());
                        }
                        if (num.getText().length() != 0 && user_name.getText().length() != 0 && password.getText().length() != 0 && password1.getText().length() != 0) {
                            if (password.getText().equalsIgnoreCase(password1.getText())) {
                                Platform.runLater(() -> box.requestFocus());
                            } else {
                                a.setHeaderText("كلمة المرور غير متطابقة");
                                a.show();
                                password1.setText("");
                            }
                        }
                        if (num.getText().length() != 0 && user_name.getText().length() != 0 && password.getText().length() != 0 && kind != null && per != null && password1.getText().length() != 0) {
                            base.add_user(user_name.getText(), password.getText(), Integer.parseInt(num.getText()), kind, per);
                            num.setText("");
                            user_name.setText("");
                            password.setText("");
                            password1.setText("");
                            box1.setSelected(false);
                            box3.setSelected(false);
                            box4.setSelected(false);
                            box.setSelected(false);
                            kind = null;
                            per = null;
                            Platform.runLater(() -> num.requestFocus());
                        }
                    } else if (base.check(num.getText()) == true && user == false) {
                        a.setHeaderText("رقم المستخدم موجود بالفعل");
                        a.show();
                        num.setText("");
                    } else if (user == true) {
                        if (password.getText().length() == 0 || password.getText().toString() == null) {
                            a.setHeaderText("من فضلك ادخل كلمة المرور");
                            a.show();
                            Platform.runLater(() -> password.requestFocus());
                        }
                    }
                }

            } catch (Exception ex) {
            }
        });
        box.setOnAction(e -> {
            if (box.isSelected()) {
                box1.setSelected(false);
            }
        });
        box1.setOnAction(e -> {
            if (box1.isSelected()) {
                box.setSelected(false);
            }
        });
        box3.setOnAction(e -> {
            if (box3.isSelected()) {
                box4.setSelected(false);
            }
        });
        box4.setOnAction(e -> {
            if (box4.isSelected()) {
                box3.setSelected(false);
            }
        });

        a.getDialogPane().setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
        a.getDialogPane().setStyle("-fx-font-size: 12px; -fx-font-weight:bold; -fx-alignment: CENTER;");
        add.setOnAction(this);
        user_name.setPromptText("اسم المستخدم");
        num.setPromptText("رقم المستخدم");
        password.setPromptText("كلمة المرور");
        password1.setPromptText("تأكيد كلمة المرور");
        user_name.setAlignment(Pos.CENTER_RIGHT);
        password.setAlignment(Pos.CENTER_RIGHT);
        password1.setAlignment(Pos.CENTER_RIGHT);
        num.setAlignment(Pos.CENTER_RIGHT);
        root.setStyle("-fx-background-color:#eee");
        stage.setScene(scene);
        num.setLayoutX(170);
        num.setLayoutY(40);
        user_name.setLayoutX(170);
        user_name.setLayoutY(70);
        password.setLayoutX(170);
        password.setLayoutY(100);
        password1.setLayoutX(170);
        password1.setLayoutY(130);
        box.setLayoutX(170);
        box.setLayoutY(160);
        box1.setLayoutX(260);
        box1.setLayoutY(160);
        box4.setLayoutX(140);
        box4.setLayoutY(190);
        box3.setLayoutX(280);
        box3.setLayoutY(190);
        add.setLayoutX(230);
        add.setLayoutY(230);
        root.getChildren().addAll(user_name, password, num, add, box, box1);
        root.getChildren().addAll(box3, box4, password1);
        a.initOwner(stage);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void handle(ActionEvent event) {
        if (event.getSource() == add) {
            try {
                if (num.getText().length() == 0 || num.getText().toString() == null) {
                    a.setHeaderText("من فضلك ادخل رقم المستخدم");
                    a.show();
                } else if (base.check(num.getText()) == false) {
                    Platform.runLater(() -> user_name.requestFocus());
                    if (box.isSelected() == true) {
                        kind = "admin";
                    } else if (box1.isSelected() == true) {
                        kind = "user";
                    }
                    if (box3.isSelected() == true) {
                        per = "الوردية الصباحية";
                    } else if (box4.isSelected() == true) {
                        per = "الوردية المسائية";
                    }
                    if (num.getText().length() != 0 && user_name.getText().length() != 0) {
                        Platform.runLater(() -> password.requestFocus());
                    }
                    if (num.getText().length() != 0 && user_name.getText().length() != 0 && password.getText().length() != 0) {
                        Platform.runLater(() -> password1.requestFocus());
                    }
                    if (num.getText().length() != 0 && user_name.getText().length() != 0 && password.getText().length() != 0 && password1.getText().length() != 0) {
                        if (password.getText().equalsIgnoreCase(password1.getText())) {
                            Platform.runLater(() -> box.requestFocus());
                        } else {
                            a.setHeaderText("كلمة المرور غير متطابقة");
                            a.show();
                            password1.setText("");
                        }
                    }
                    if (num.getText().length() != 0 && user_name.getText().length() != 0 && password.getText().length() != 0 && kind != null && per != null && password1.getText().length() != 0) {
                        base.add_user(user_name.getText(), password.getText(), Integer.parseInt(num.getText()), kind, per);
                        num.setText("");
                        user_name.setText("");
                        password.setText("");
                        password1.setText("");
                        box1.setSelected(false);
                        box.setSelected(false);
                        box3.setSelected(false);
                        box4.setSelected(false);
                        kind = null;
                        per = null;
                        Platform.runLater(() -> num.requestFocus());
                    }
                } else if (base.check(num.getText()) == true && user == false) {
                    a.setHeaderText("رقم المستخدم موجود بالفعل");
                    a.show();
                    num.setText("");
                } else if (user == true) {
                    if (password.getText().length() == 0 || password.getText().toString() == null) {
                        a.setHeaderText("من فضلك ادخل كلمة المرور");
                        a.show();
                        Platform.runLater(() -> password.requestFocus());
                    }
                }
            } catch (Exception ex) {

            }
        }
    }
}
