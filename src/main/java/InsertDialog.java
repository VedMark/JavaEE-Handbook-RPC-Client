import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;
import thrift.JavaEETechnology;
import thrift.JavaEETechnologyVersions;

class InsertDialog extends Stage {

    public InsertDialog(Stage owner, Controller holder) {
        super();
        initOwner(owner);
        setTitle("Add an entry");
        initModality(Modality.WINDOW_MODAL);
        Group root = new Group();
        Scene scene = new Scene(root, 700, 450, Color.WHITE);
        setScene(scene);
        setResizable(false);

        GridPane gridpane = new GridPane();
        gridpane.setPadding(new Insets(5));
        gridpane.setHgap(4);
        gridpane.setVgap(10);

        Label name = new Label("Name: ");
        gridpane.add(name, 0, 1);
        final TextField nameFld = new TextField();
        gridpane.add(nameFld, 0, 2);

        Label versions = new Label("Versions: ");
        versions.setFont(Font.font(versions.getFont().getFamily(), FontWeight.BOLD, versions.getFont().getSize()));
        gridpane.add(versions, 0, 3);

        Label versionForJava4 = new Label("Java 4: ");
        gridpane.add(versionForJava4, 0, 4);
        final TextField vForJava4Fld = new TextField();
        gridpane.add(vForJava4Fld, 0, 5);

        Label versionForJava5 = new Label("Java 5: ");
        gridpane.add(versionForJava5, 0, 6);
        final TextField vForJava5Fld = new TextField();
        gridpane.add(vForJava5Fld, 0, 7);

        Label versionForJava6 = new Label("Java 6: ");
        gridpane.add(versionForJava6, 0, 8);
        final TextField vForJava6Fld = new TextField();
        gridpane.add(vForJava6Fld, 0, 9);

        Label versionForJava7 = new Label("Java 7: ");
        gridpane.add(versionForJava7, 0, 10);
        final TextField vForJava7Fld = new TextField();
        gridpane.add(vForJava7Fld, 0, 11);

        Label versionForJava8 = new Label("Java 8: ");
        gridpane.add(versionForJava8, 0, 12);
        final TextField vForJava8Fld = new TextField();
        gridpane.add(vForJava8Fld, 0, 13);

        Label description = new Label("Description: ");
        gridpane.add(description, 1, 1);
        final TextArea descriptionArea = new TextArea();
        gridpane.add(descriptionArea, 1, 2, 10, 12);


        Button add = new Button("Ok");
        add.resize(80,30);
        add.setOnAction(actionEvent -> {
            if(nameFld.getText().isEmpty()) {
                holder.showErrorAlert(
                        "Data error",
                        "Field \"Name\" could not be empty");
            } else {
                buildJavaTechnologyObject(holder, nameFld, vForJava4Fld,
                        vForJava5Fld, vForJava6Fld, vForJava7Fld,
                        vForJava8Fld, descriptionArea);
                close();
            }
        });
        gridpane.add(add, 4, 15);

        Button close = new Button("Close");
        close.resize(80, 30);
        close.setOnAction(event -> close());
        gridpane.add(close, 8, 15);

        GridPane.setHalignment(close, HPos.RIGHT);
        root.getChildren().add(gridpane);
    }

    private void buildJavaTechnologyObject(Controller holder, TextField nameFld, TextField vForJava4Fld,
                                           TextField vForJava5Fld, TextField vForJava6Fld, TextField vForJava7Fld,
                                           TextField vForJava8Fld, TextArea descriptionArea) {
        final JavaEETechnology technology = new JavaEETechnology();
        technology.setName(nameFld.getText());
        technology.setVersions(new JavaEETechnologyVersions());
        technology.getVersions().setVersionForJava4(vForJava4Fld.getText());
        technology.getVersions().setVersionForJava5(vForJava5Fld.getText());
        technology.getVersions().setVersionForJava6(vForJava6Fld.getText());
        technology.getVersions().setVersionForJava7(vForJava7Fld.getText());
        technology.getVersions().setVersionForJava8(vForJava8Fld.getText());
        technology.setDescription(descriptionArea.getText());
        holder.addRow(technology);
    }
}