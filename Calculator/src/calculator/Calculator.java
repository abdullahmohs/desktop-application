/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package calculator;

import java.text.DecimalFormat;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 *
 * @author Administrator
 */
public class Calculator extends Application implements EventHandler<ActionEvent> {

    Button button0 = new Button("0");
    Button button1 = new Button("1");
    Button button2 = new Button("2");
    Button button3 = new Button("3");
    Button button4 = new Button("4");
    Button button5 = new Button("5");
    Button button6 = new Button("6");
    Button button7 = new Button("7");
    Button button8 = new Button("8");
    Button button9 = new Button("9");
    Button button_reverse = new Button("+/-");
    Button button_dot = new Button(".");
    Button button_equal = new Button("=");
    Button button_plus = new Button("+");
    Button button_mins = new Button("-");
    Button button_multy = new Button("×");
    Button button_one_div = new Button("1/x");
    Button button_sqr = new Button("x²");
    Button button_root = new Button("√");
    Button button_div = new Button("÷");
    Button button_rem = new Button("%");
    Button button_clear = new Button("←");
    Button button_clear_all = new Button("C");
    Button button_ans = new Button("Ans");
    TextField text = new TextField("");
    Label label = new Label("");
    String ans="", total;
    ArrayList<String> result = new ArrayList();
    ArrayList<String> operation = new ArrayList();
    ArrayList<String> sub = new ArrayList();
    DecimalFormat format = new DecimalFormat("0.0");

    @Override
    public void start(Stage stage) {
        stage.setTitle("Calculator");
        Pane root = new Pane();
        javafx.geometry.Rectangle2D bounds = Screen.getPrimary().getVisualBounds();
        Scene scene = new Scene(root, bounds.getHeight() / 3.3, bounds.getWidth() / 3);
        stage.setX(bounds.getWidth() / 3);
        stage.setY(bounds.getHeight() / 3);
        stage.setResizable(false);
        stage.setScene(scene);
        text.setAlignment(Pos.CENTER_RIGHT);
        label.setAlignment(Pos.CENTER_RIGHT);
        text.setEditable(false);
        try {
            stage.getIcons().add(new Image("calculator\\images.png"));
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        //location
        button_ans.setLayoutX(3);
        button_ans.setLayoutY(380);
        button0.setLayoutX(77);
        button0.setLayoutY(380);
        button_dot.setLayoutX(151);
        button_dot.setLayoutY(380);
        button_equal.setLayoutX(225);
        button_equal.setLayoutY(380);
        button1.setLayoutX(3);
        button1.setLayoutY(331);
        button2.setLayoutX(77);
        button2.setLayoutY(331);
        button3.setLayoutX(151);
        button3.setLayoutY(331);
        button_plus.setLayoutX(225);
        button_plus.setLayoutY(331);
        button4.setLayoutX(3);
        button4.setLayoutY(282);
        button5.setLayoutX(77);
        button5.setLayoutY(282);
        button6.setLayoutX(151);
        button6.setLayoutY(282);
        button_mins.setLayoutX(225);
        button_mins.setLayoutY(282);
        button7.setLayoutX(3);
        button7.setLayoutY(233);
        button8.setLayoutX(77);
        button8.setLayoutY(233);
        button9.setLayoutX(151);
        button9.setLayoutY(233);
        button_multy.setLayoutX(225);
        button_multy.setLayoutY(233);
        button_one_div.setLayoutX(3);
        button_one_div.setLayoutY(184);
        button_sqr.setLayoutX(77);
        button_sqr.setLayoutY(184);
        button_root.setLayoutX(151);
        button_root.setLayoutY(184);
        button_div.setLayoutX(225);
        button_div.setLayoutY(184);
        button_rem.setLayoutX(3);
        button_rem.setLayoutY(135);
        button_reverse.setLayoutX(77);
        button_reverse.setLayoutY(135);
        button_clear_all.setLayoutX(151);
        button_clear_all.setLayoutY(135);
        button_clear.setLayoutX(225);
        button_clear.setLayoutY(135);
        text.setLayoutX(-30);
        text.setLayoutY(60);
        label.setLayoutX(-5);
        label.setLayoutY(10);

        //size  
        button_ans.setMinSize(70, 45);
        button0.setMinSize(70, 45);
        button_dot.setMinSize(70, 45);
        button_equal.setMinSize(70, 45);
        button1.setMinSize(70, 45);
        button2.setMinSize(70, 45);
        button3.setMinSize(70, 45);
        button_plus.setMinSize(70, 45);
        button_mins.setMinSize(70, 45);
        button4.setMinSize(70, 45);
        button5.setMinSize(70, 45);
        button6.setMinSize(70, 45);
        button7.setMinSize(70, 45);
        button8.setMinSize(70, 45);
        button9.setMinSize(70, 45);
        button_multy.setMinSize(70, 45);
        button_div.setMinSize(70, 45);
        button_one_div.setMinSize(70, 45);
        button_root.setMinSize(70, 45);
        button_sqr.setMinSize(70, 45);
        button_rem.setMinSize(70, 45);
        button_reverse.setMinSize(70, 45);
        button_clear.setMinSize(70, 45);
        button_clear_all.setMinSize(70, 45);
        text.setMinSize(290, 70);
        label.setMinSize(290, 70);

        //style
        button0.setStyle("-fx-font-size:20px; -fx-font-weight:bold; -fx-text-fill:white; -fx-background-color:#273746;");
        button_dot.setStyle("-fx-font-size:20px; -fx-font-weight:bold; -fx-text-fill:black; ");
        button_ans.setStyle("-fx-font-size:20px; -fx-font-weight:bold;-fx-text-fill:black;");
        button_equal.setStyle("-fx-font-size:20px; -fx-font-weight:bold;-fx-text-fill:black;");
        button1.setStyle("-fx-font-size:20px; -fx-font-weight:bold;-fx-text-fill:white;-fx-background-color:#273746;");
        button2.setStyle("-fx-font-size:20px; -fx-font-weight:bold;-fx-text-fill:white;-fx-background-color:#273746;");
        button3.setStyle("-fx-font-size:20px; -fx-font-weight:bold;-fx-text-fill:white;-fx-background-color:#273746;");
        button_plus.setStyle("-fx-font-size:20px; -fx-font-weight:bold;-fx-text-fill:black;");
        button_mins.setStyle("-fx-font-size:20px; -fx-font-weight:bold;-fx-text-fill:black;");
        button4.setStyle("-fx-font-size:20px; -fx-font-weight:bold;-fx-text-fill:white;-fx-background-color:#273746;");
        button5.setStyle("-fx-font-size:20px; -fx-font-weight:bold;-fx-text-fill:white;-fx-background-color:#273746;");
        button6.setStyle("-fx-font-size:20px; -fx-font-weight:bold;-fx-text-fill:white;-fx-background-color:#273746;");
        button7.setStyle("-fx-font-size:20px; -fx-font-weight:bold;-fx-text-fill:white;-fx-background-color:#273746;");
        button8.setStyle("-fx-font-size:20px; -fx-font-weight:bold;-fx-text-fill:white;-fx-background-color:#273746;");
        button9.setStyle("-fx-font-size:20px; -fx-font-weight:bold;-fx-text-fill:white;-fx-background-color:#273746;");
        button_multy.setStyle("-fx-font-size:20px; -fx-font-weight:bold;-fx-text-fill:black;");
        button_div.setStyle("-fx-font-size:20px; -fx-font-weight:bold;-fx-text-fill:black;");
        button_one_div.setStyle("-fx-font-size:20px; -fx-font-weight:bold;-fx-text-fill:black;");
        button_root.setStyle("-fx-font-size:20px; -fx-font-weight:bold;-fx-text-fill:black;");
        button_sqr.setStyle("-fx-font-size:20px; -fx-font-weight:bold;-fx-text-fill:black;");
        button_rem.setStyle("-fx-font-size:20px; -fx-font-weight:bold;-fx-text-fill:black;");
        button_clear.setStyle("-fx-font-size:20px; -fx-font-weight:bold;-fx-text-fill:black;");
        button_clear_all.setStyle("-fx-font-size:20px; -fx-font-weight:bold;-fx-text-fill:black;");
        button_reverse.setStyle("-fx-font-size:20px; -fx-font-weight:bold;-fx-text-fill:black;");
        text.setStyle("-fx-font-size:25px; -fx-font-weight:bold; -fx-text-fill:black;");
        label.setStyle("-fx-font-size:25px; -fx-text-fill:white; -fx-font-weight:bold;");
        root.setStyle("-fx-background-color:black;");

        //key event
        scene.addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent e) -> {
            if (e.getCode() == KeyCode.NUMPAD0) {
                if (text.getText().equals("0")) {
                } else {
                    text.setText(text.getText() + "0");
                    label.setText(label.getText() + "0");
                }
            } else if (e.getCode() == KeyCode.NUMPAD1) {
                if (text.getText().equals("0")) {
                    text.setText("1");
                    label.setText(text.getText());
                } else {
                    text.setText(text.getText() + "1");
                    label.setText(label.getText() + "1");
                }
            } else if (e.getCode() == KeyCode.NUMPAD2) {
                if (text.getText().equals("0")) {
                    text.setText("2");
                    label.setText(text.getText());
                } else {
                    text.setText(text.getText() + "2");
                    label.setText(label.getText() + "2");
                }
            } else if (e.getCode() == KeyCode.NUMPAD3) {
                if (text.getText().equals("0")) {
                    text.setText("3");
                    label.setText(text.getText());
                } else {
                    text.setText(text.getText() + "3");
                    label.setText(label.getText() + "3");
                }
            } else if (e.getCode() == KeyCode.NUMPAD4) {
                if (text.getText().equals("0")) {
                    text.setText("4");
                    label.setText(text.getText());
                } else {
                    text.setText(text.getText() + "4");
                    label.setText(label.getText() + "4");
                }

            } else if (e.getCode() == KeyCode.NUMPAD5) {
                if (text.getText().equals("0")) {
                    text.setText("5");
                    label.setText(text.getText());
                } else {
                    text.setText(text.getText() + "5");
                    label.setText(label.getText() + "5");
                }
            } else if (e.getCode() == KeyCode.NUMPAD6) {
                if (text.getText().equals("0")) {
                    text.setText("6");
                    label.setText(text.getText());
                } else {
                    text.setText(text.getText() + "6");
                    label.setText(label.getText() + "6");
                }
            } else if (e.getCode() == KeyCode.NUMPAD7) {
                if (text.getText().equals("0")) {
                    text.setText("7");
                    label.setText(text.getText());
                } else {
                    text.setText(text.getText() + "7");
                    label.setText(label.getText() + "7");
                }
            } else if (e.getCode() == KeyCode.NUMPAD8) {
                if (text.getText().equals("0")) {
                    text.setText("8");
                    label.setText(text.getText());
                } else {
                    text.setText(text.getText() + "8");
                    label.setText(label.getText() + "8");
                }
            } else if (e.getCode() == KeyCode.NUMPAD9) {
                if (text.getText().equals("0")) {
                    text.setText("9");
                    label.setText(text.getText());
                } else {
                    text.setText(text.getText() + "9");
                    label.setText(label.getText() + "9");
                }
            } else if (e.getCode() == KeyCode.ESCAPE) {
                text.setText("");
                label.setText("");
                operation.clear();
                result.clear();
            } else if (e.getCode() == KeyCode.DECIMAL) {
                try {
                    if (text.getText().contains(".")) {
                    } else {
                        text.setText(text.getText() + ".");
                        label.setText(label.getText() + ".");
                    }
                } catch (Exception ex) {
                }
            } else if (e.getCode() == KeyCode.ADD) {
                if (label.getText().length() == 0 || label.getText().endsWith("+")) {
                } else if (label.getText().endsWith("-") || label.getText().endsWith("/")
                        || label.getText().endsWith("*") || label.getText().endsWith("%")) {

                    label.setText(label.getText().substring(0, label.getText().length() - 1));
                    operation.set(operation.size() - 1, "+");
                    label.setText(label.getText() + operation.get(operation.size() - 1));
                } else {
                    operation.add("+");
                    result.add(text.getText());
                    label.setText(label.getText() + operation.get(operation.size() - 1));
                    text.setText("");
                }
            } else if (e.getCode() == KeyCode.SUBTRACT) {
                try {
                    if (label.getText().length() == 0 || label.getText().endsWith("-")) {
                    } else if (label.getText().endsWith("+")) {
                        label.setText(label.getText().substring(0, label.getText().length() - 1));
                        operation.set(operation.size() - 1, "-");
                        label.setText(label.getText() + operation.get(operation.size() - 1));
                    } else if (label.getText().endsWith("/") || label.getText().endsWith("*") || label.getText().endsWith("%")) {
                        label.setText(label.getText() + "-");
                        sub.add(Integer.toString(result.size()));
                    } else {
                        operation.add("-");
                        result.add(text.getText());
                        label.setText(label.getText() + operation.get(operation.size() - 1));
                        text.setText("");
                    }
                } catch (Exception ex) {
                }
            } else if (e.getCode() == KeyCode.MULTIPLY) {
                if (label.getText().length() == 0 || label.getText().endsWith("*")) {
                } else if (label.getText().endsWith("+") || label.getText().endsWith("/")
                        || label.getText().endsWith("-") || label.getText().endsWith("%")) {
                    label.setText(label.getText().substring(0, label.getText().length() - 1));
                    operation.set(operation.size() - 1, "*");
                    label.setText(label.getText() + operation.get(operation.size() - 1));
                } else {
                    operation.add("*");
                    result.add(text.getText());
                    label.setText(label.getText() + operation.get(operation.size() - 1));
                    text.setText("");
                }

            } else if (e.getCode() == KeyCode.DIVIDE) {
                if (label.getText().length() == 0 || label.getText().endsWith("/")) {
                } else if (label.getText().endsWith("+") || label.getText().endsWith("-")
                        || label.getText().endsWith("*") || label.getText().endsWith("%")) {

                    label.setText(label.getText().substring(0, label.getText().length() - 1));
                    operation.set(operation.size() - 1, "/");
                    label.setText(label.getText() + operation.get(operation.size() - 1));
                } else {
                    operation.add("/");
                    result.add(text.getText());
                    label.setText(label.getText() + operation.get(operation.size() - 1));
                    text.setText("");
                }
            } else if (e.getCode() == KeyCode.ENTER) {
                try {
                    result.add(text.getText());
                    if (text.getText().length() == 0 || label.getText().length() == 0) {
                    } else {
                        result.add(text.getText());
                        if (sub.size() != 0) {
                            for (int j = 0; j < sub.size(); j++) {
                                result.set(Integer.parseInt(sub.get(j)), Double.toString(Double.parseDouble(result.get(Integer.parseInt(sub.get(j)))) * -1));
                            }
                        }
                        for (int i = 0; i < result.size() - 1; i++) {
                            if (operation.get(i).equals("+")) {
                                total = Double.toString(Double.parseDouble(result.get(i)) + Double.parseDouble(result.get(i + 1)));
                                result.set(i + 1, total);
                            } else if (operation.get(i).equals("-")) {
                                total = Double.toString(Double.parseDouble(result.get(i)) - Double.parseDouble(result.get(i + 1)));
                                result.set(i + 1, total);
                            } else if (operation.get(i).equals("*")) {
                                total = Double.toString(Double.parseDouble(result.get(i)) * Double.parseDouble(result.get(i + 1)));
                                result.set(i + 1, total);
                            } else if (operation.get(i).equals("/")) {
                                total = Double.toString(Double.parseDouble(result.get(i)) / Double.parseDouble(result.get(i + 1)));
                                result.set(i + 1, total);
                            } else if (operation.get(i).equals("%")) {
                                total = Double.toString(Double.parseDouble(result.get(i)) % Double.parseDouble(result.get(i + 1)));
                                result.set(i + 1, total);
                            }
                        }
                    }
                } catch (Exception ex) {
                }
                text.setText(format.format(Double.parseDouble(total)));
                ans=total;
                operation.clear();
                result.clear();
                sub.clear();
            } else if (e.getCode() == KeyCode.BACK_SPACE) {
                try {
                    text.setText(text.getText().substring(0, text.getText().length() - 1));
                    label.setText(label.getText().substring(0, label.getText().length() - 1));
                } catch (Exception ex) {
                }
            }
            e.consume();
        });

        //Action
        button0.setOnAction(this);
        button1.setOnAction(this);
        button2.setOnAction(this);
        button3.setOnAction(this);
        button4.setOnAction(this);
        button5.setOnAction(this);
        button6.setOnAction(this);
        button7.setOnAction(this);
        button8.setOnAction(this);
        button9.setOnAction(this);
        button_clear_all.setOnAction(this);
        button_clear.setOnAction(this);
        button_reverse.setOnAction(this);
        button_root.setOnAction(this);
        button_sqr.setOnAction(this);
        button_dot.setOnAction(this);
        button_one_div.setOnAction(this);
        button_ans.setOnAction(this);
        button_plus.setOnAction(this);
        button_mins.setOnAction(this);
        button_multy.setOnAction(this);
        button_div.setOnAction(this);
        button_rem.setOnAction(this);
        button_equal.setOnAction(this);

        //add button
        root.getChildren().addAll(button0, button1, button2, button3, button_plus, button_ans);
        root.getChildren().addAll(button4, button5, button6, button7, button_dot);
        root.getChildren().addAll(button8, button9, button_equal, button_reverse, button_mins);
        root.getChildren().addAll(button_multy, button_one_div, button_div, button_root, button_sqr);
        root.getChildren().addAll(button_rem, button_clear, button_clear_all, text, label);

        stage.show();

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void handle(ActionEvent e) {
        if (e.getSource() == button0) {
            if (text.getText().equals("0")) {
            } else {
                text.setText(text.getText() + "0");
                label.setText(label.getText() + "0");
            }
        } else if (e.getSource() == button1) {
            if (text.getText().equals("0")) {
                text.setText("1");
                label.setText(text.getText());
            } else {
                text.setText(text.getText() + "1");
                label.setText(label.getText() + "1");
            }
        } else if (e.getSource() == button2) {
            if (text.getText().equals("0")) {
                text.setText("2");
                label.setText(text.getText());
            } else {
                text.setText(text.getText() + "2");
                label.setText(label.getText() + "2");
            }
        } else if (e.getSource() == button3) {
            if (text.getText().equals("0")) {
                text.setText("3");
                label.setText(text.getText());
            } else {
                text.setText(text.getText() + "3");
                label.setText(label.getText() + "3");
            }
        } else if (e.getSource() == button4) {
            if (text.getText().equals("0")) {
                text.setText("4");
                label.setText(text.getText());
            } else {
                text.setText(text.getText() + "4");
                label.setText(label.getText() + "4");
            }

        } else if (e.getSource() == button5) {
            if (text.getText().equals("0")) {
                text.setText("5");
                label.setText(text.getText());
            } else {
                text.setText(text.getText() + "5");
                label.setText(label.getText() + "5");
            }

        } else if (e.getSource() == button6) {
            if (text.getText().equals("0")) {
                text.setText("6");
                label.setText(text.getText());
            } else {
                text.setText(text.getText() + "6");
                label.setText(label.getText() + "6");
            }

        } else if (e.getSource() == button7) {
            if (text.getText().equals("0")) {
                text.setText("7");
                label.setText(text.getText());
            } else {
                text.setText(text.getText() + "7");
                label.setText(label.getText() + "7");
            }

        } else if (e.getSource() == button8) {
            if (text.getText().equals("0")) {
                text.setText("8");
                label.setText(text.getText());
            } else {
                text.setText(text.getText() + "8");
                label.setText(label.getText() + "8");
            }

        } else if (e.getSource() == button9) {
            if (text.getText().equals("0")) {
                text.setText("9");
                label.setText(text.getText());
            } else {
                text.setText(text.getText() + "9");
                label.setText(label.getText() + "9");
            }

        } else if (e.getSource() == button_clear_all) {
            text.setText("");
            label.setText("");
            operation.clear();
            result.clear();
        } else if (e.getSource() == button_clear) {
            try {
                text.setText(text.getText().substring(0, text.getText().length() - 1));
                label.setText(label.getText().substring(0, label.getText().length() - 1));
            } catch (Exception ex) {
            }
        } else if (e.getSource() == button_reverse) {
            try {
                text.setText(Double.toString(Double.parseDouble(text.getText()) * -1));
                label.setText(text.getText());
                ans = text.getText();
            } catch (Exception ex) {
            }
        } else if (e.getSource() == button_root) {
            try {
                if (text.getText().length() == 0) {
                } else {
                    label.setText("√" + text.getText());
                    text.setText(Double.toString(Math.sqrt(Double.parseDouble(text.getText()))));
                    ans = text.getText();
                }
            } catch (Exception ex) {
            }
        } else if (e.getSource() == button_sqr) {
            try {
                if (text.getText().length() == 0) {
                } else {
                    label.setText(text.getText() + "²");
                    text.setText(Double.toString(Math.pow(Double.parseDouble(text.getText()), 2)));
                    ans = text.getText();
                }
            } catch (Exception ex) {
            }
        } else if (e.getSource() == button_dot) {
            try {
                if (text.getText().contains(".")) {
                } else {
                    text.setText(text.getText() + ".");
                    label.setText(label.getText() + ".");
                }
            } catch (Exception ex) {
            }
        } else if (e.getSource() == button_one_div) {
            if (text.getText().length() == 0) {
            } else {
                label.setText("1/" + text.getText());
                text.setText(Double.toString(1 / Double.parseDouble(text.getText())));
                ans = text.getText();
            }
        } else if (e.getSource() == button_ans) {
            try {
                if (ans.equals("")) {
                } else {
                    text.setText(ans);
                    label.setText(ans);
                }
            } catch (Exception ex) {
            }
        } else if (e.getSource() == button_plus) {
            if (label.getText().length() == 0 || label.getText().endsWith("+")) {
            } else if (label.getText().endsWith("-") || label.getText().endsWith("/")
                    || label.getText().endsWith("*") || label.getText().endsWith("%")) {

                label.setText(label.getText().substring(0, label.getText().length() - 1));
                operation.set(operation.size() - 1, "+");
                label.setText(label.getText() + operation.get(operation.size() - 1));
            } else {
                operation.add("+");
                result.add(text.getText());
                label.setText(label.getText() + operation.get(operation.size() - 1));
                text.setText("");
            }

        } else if (e.getSource() == button_mins) {
            try {
                if (label.getText().length() == 0 || label.getText().endsWith("-")) {
                } else if (label.getText().endsWith("+")) {
                    label.setText(label.getText().substring(0, label.getText().length() - 1));
                    operation.set(operation.size() - 1, "-");
                    label.setText(label.getText() + operation.get(operation.size() - 1));
                } else if (label.getText().endsWith("/") || label.getText().endsWith("*") || label.getText().endsWith("%")) {
                    label.setText(label.getText() + "-");
                    sub.add(Integer.toString(result.size()));
                } else {
                    operation.add("-");
                    result.add(text.getText());
                    label.setText(label.getText() + operation.get(operation.size() - 1));
                    text.setText("");
                }
            } catch (Exception ex) {
            }
        } else if (e.getSource() == button_multy) {
            if (label.getText().length() == 0 || label.getText().endsWith("*")) {
            } else if (label.getText().endsWith("+") || label.getText().endsWith("/")
                    || label.getText().endsWith("-") || label.getText().endsWith("%")) {
                label.setText(label.getText().substring(0, label.getText().length() - 1));
                operation.set(operation.size() - 1, "*");
                label.setText(label.getText() + operation.get(operation.size() - 1));
            } else {
                operation.add("*");
                result.add(text.getText());
                label.setText(label.getText() + operation.get(operation.size() - 1));
                text.setText("");
            }
        } else if (e.getSource() == button_div) {
            if (label.getText().length() == 0 || label.getText().endsWith("/")) {
            } else if (label.getText().endsWith("+") || label.getText().endsWith("-")
                    || label.getText().endsWith("*") || label.getText().endsWith("%")) {

                label.setText(label.getText().substring(0, label.getText().length() - 1));
                operation.set(operation.size() - 1, "/");
                label.setText(label.getText() + operation.get(operation.size() - 1));
            } else {
                operation.add("/");
                result.add(text.getText());
                label.setText(label.getText() + operation.get(operation.size() - 1));
                text.setText("");
            }
        } else if (e.getSource() == button_rem) {
            if (label.getText().length() == 0 || label.getText().endsWith("%")) {
            } else if (label.getText().endsWith("+") || label.getText().endsWith("/")
                    || label.getText().endsWith("*") || label.getText().endsWith("-")) {
                label.setText(label.getText().substring(0, label.getText().length() - 1));
                operation.set(operation.size() - 1, "%");
                label.setText(label.getText() + operation.get(operation.size() - 1));
            } else {
                operation.add("%");
                result.add(text.getText());
                label.setText(label.getText() + operation.get(operation.size() - 1));
                text.setText("");
            }
        } else if (e.getSource() == button_equal) {
            try {
                result.add(text.getText());
                if (text.getText().length() == 0 || label.getText().length() == 0) {
                } else {
                    result.add(text.getText());
                    if (sub.size() != 0) {
                        for (int j = 0; j < sub.size(); j++) {
                            result.set(Integer.parseInt(sub.get(j)), Double.toString(Double.parseDouble(result.get(Integer.parseInt(sub.get(j)))) * -1));
                        }
                    }
                    for (int i = 0; i < result.size() - 1; i++) {
                        if (operation.get(i).equals("+")) {
                            total = Double.toString(Double.parseDouble(result.get(i)) + Double.parseDouble(result.get(i + 1)));
                            result.set(i + 1, total);
                        } else if (operation.get(i).equals("-")) {
                            total = Double.toString(Double.parseDouble(result.get(i)) - Double.parseDouble(result.get(i + 1)));
                            result.set(i + 1, total);
                        } else if (operation.get(i).equals("*")) {
                            total = Double.toString(Double.parseDouble(result.get(i)) * Double.parseDouble(result.get(i + 1)));
                            result.set(i + 1, total);
                        } else if (operation.get(i).equals("/")) {
                            total = Double.toString(Double.parseDouble(result.get(i)) / Double.parseDouble(result.get(i + 1)));
                            result.set(i + 1, total);
                        } else if (operation.get(i).equals("%")) {
                            total = Double.toString(Double.parseDouble(result.get(i)) % Double.parseDouble(result.get(i + 1)));
                            result.set(i + 1, total);
                        }
                    }
                }
            } catch (Exception ex) {
            }
            text.setText(format.format(Double.parseDouble(total)));
            ans=total;
            operation.clear();
            result.clear();
            sub.clear();
        }
    }

}
