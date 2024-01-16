package cashier;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class main_page extends Application {

    Stage stage = new Stage();

    /**
     *
     * @param stage
     * @throws Exception
     */
    String name;

    @Override

    public void start(Stage stage) throws Exception {
        stage.setTitle("Cashier");
        Pane root = new Pane();
        Rectangle2D bounds = Screen.getPrimary().getVisualBounds();
        Scene scene = new Scene(root, bounds.getWidth(), bounds.getHeight() - 20);
        TreeItem<String> rootitem = new TreeItem();
        rootitem.setExpanded(true);
        TreeItem<String> first = new TreeItem("الموظفين");
        TreeItem<String> f_add = new TreeItem("إضافة مستخدم");
        TreeItem<String> f_update = new TreeItem("تعديل مستخدم");
        TreeItem<String> f_delete = new TreeItem("حذف مستخدم");
        rootitem.getChildren().add(first);
        first.getChildren().addAll(f_add, f_update, f_delete);
        TreeView tree = new TreeView(rootitem);
        tree.setShowRoot(false);
        tree.setStyle("-fx-background-color:#eee; -fx-font-size:20px;");
        tree.setMaxSize(bounds.getWidth() / 3, bounds.getHeight());
        tree.setMinSize(bounds.getWidth() / 3, bounds.getHeight());
        try {
            stage.getIcons().add(new Image("cashier\\download.png"));
            String css = this.getClass().getResource("index.css").toExternalForm();
            scene.getStylesheets().add(css);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        scene.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getClickCount() == 2) {
                }
                if (name.equals("إضافة مستخدم")) {
                    try {
                        new add_user().start(stage);
                    } catch (Exception ex) {
                        Logger.getLogger(main_page.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }

        });
        tree.setId("tree");
        tree.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
        tree.setLayoutX(850);
        stage.setScene(scene);
        root.getChildren().addAll(tree);
        stage.setMaximized(true);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
