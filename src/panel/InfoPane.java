package panel;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

public class InfoPane extends VBox {
    private Image page = new Image(ClassLoader.getSystemResourceAsStream("image/buttons/background.png"));
    public InfoPane() {
        super();
        this.setAlignment(Pos.CENTER); // Center align the content of the VBox
        this.setPrefSize(1000, 1000);

        // Create HBox for the "Close" button and align it to the top right
        HBox closeButtonBox = createCloseButton();

        // Create VBox for "Made By" label and align it to top center
        VBox madeByBox = createMadeBy();

        // Create labels for additional information
        Label infoLabel1 = createLable("6633256621 Sorravich Lakngoenchai");
        Label infoLabel2 = createLable("6633219421 Worawalun Setthawiwat");
        Label infoLabel3 = createLable("6633071821 Nattapong Rukngan");
        Label infoLabel4 = createLable("6633207921 Ravisara Maka");

        // Add labels to the VBox
        this.getChildren().addAll(closeButtonBox, madeByBox, infoLabel1, infoLabel2, infoLabel3, infoLabel4);

        BackgroundSize backgroundSize = new BackgroundSize(500, 300, false, false, false, false);

        // Set background image centered in the VBox
        this.setBackground(new Background(new BackgroundImage(
                page,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                backgroundSize
        )));

        // Set margin to center the VBox
        VBox.setMargin(this, new Insets(150, 0, 0, 0));
    }

    public HBox createCloseButton() {
        HBox closeButtonBox = new HBox();
        closeButtonBox.setAlignment(Pos.TOP_CENTER);
        closeButtonBox.setPadding(new Insets(0,0,0,430));

        // Create Close object
        Close close = new Close();

        // Add Close button to the HBox
        closeButtonBox.getChildren().add(close);
        return closeButtonBox;
    }

    public VBox createMadeBy() {
        // Create VBox for "Made By" label and align it to top center
        VBox madeByBox = new VBox();
        madeByBox.setAlignment(Pos.TOP_CENTER);
        madeByBox.setPadding(new Insets(0, 0, 0, 0)); // Add top padding to push it down

        // Create and configure the "Made By" label
        Label titleLabel = new Label("Made By");
        titleLabel.setStyle("-fx-font-size: 60px; -fx-font-weight: bold;"); // Example style

        // Add "Made By" label to the VBox
        madeByBox.getChildren().add(titleLabel);

        return madeByBox;
    }

    public Label createLable(String text) {
        // Create label for additional information
        Label infoLabel = new Label(text);
        infoLabel.setStyle("-fx-font-size: 18; -fx-font-weight: 600;");
        return infoLabel;
    }

}