package practiceArchieve;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import mainpage.SidePane;

public class SelectionList {
  private final double MULTIPLIER = 1.5;
  private final String DEST = "C:\\Users\\Brandon\\.eclipse\\Practice_Builder\\";
  private final Border RED_BORDER = new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID,
      CornerRadii.EMPTY, BorderWidths.DEFAULT));
  private final Border BLACK_BORDER = new Border(new BorderStroke(Color.BLACK,
      BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT));
  private final int WIDTH = 500;
  private final Font TEST_FONT = new Font("Arial", 18);

  private Label pracSelectorHeader; // Header for the whole pane
  private Pane selectorPane; // Pane that holds all of the avaiable practices to select from
  private ScrollPane selectorScrollPane; // Scroll pane for the selector pane
  private Pane infoPane; // Pane to hold the information about the selected practice
  private VBox infoPaneLayout; // Layout for the infoPane
  private Label description; // First label in info pane displaying the description
  private String descriptionText; // The text for the description to allow it to be changed
  private Label infoHeader; // The header for the selected information
  private static ArrayList<Label> info; // All of the information selected in the settings
  private Pane labelPane; // Pane to hold the of the info labels
  private static VBox labelLayout; // Layout for all of the labels with in the labelPane
  private ScrollPane labelScrollPane; // Scroll pane for the label pane if there are enough labels
  private VBox layout; // the overall layout for the selection panel

  public SelectionList() {
    selectorPane = new Pane();
    selectorScrollPane = new ScrollPane(selectorPane);
    description = new Label();
    descriptionText = "Description: \n";
    infoHeader = new Label();
    info = new ArrayList<Label>();
    labelLayout = new VBox();
    infoPane = new Pane();
    infoPaneLayout = new VBox();
    labelPane = new Pane();
    labelScrollPane = new ScrollPane(labelPane);
    layout = new VBox();
    pracSelectorHeader = new Label("Past Practice List");

    selectorPane.setPrefSize(WIDTH, 800);
    selectorPane.setOnScroll(new EventHandler<ScrollEvent>() {
      @Override
      public void handle(ScrollEvent event) {
        double deltaY = event.getDeltaY() * MULTIPLIER; // *6 to make the scrolling a bit faster
        double width = selectorScrollPane.getContent().getBoundsInLocal().getWidth();
        double vvalue = selectorScrollPane.getVvalue();
        selectorScrollPane.setVvalue(vvalue + -deltaY / width); // deltaY/width to make the
                                                                // scrolling equally
        // fast regardless of the actual width of the
        // component
      }
    });
    selectorScrollPane.vvalueProperty().addListener(new ChangeListener<Number>() {
      public void changed(ObservableValue<? extends Number> ov, Number old_val, Number new_val) {
        double testNum = 0.001;
        if ((double) new_val >= 1.0) {
          selectorScrollPane.setVvalue((double) new_val - testNum);
        } else if ((double) new_val <= 0) {
          selectorScrollPane.setVvalue((double) new_val + testNum);
        }
      }
    });
    try {
      createList(selectorPane);
    } catch (IOException e) {
      e.printStackTrace();
    }
    selectorScrollPane.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
    selectorScrollPane.setHbarPolicy(ScrollBarPolicy.NEVER);
    selectorScrollPane.setBorder(BLACK_BORDER);
    selectorScrollPane.setPrefSize(WIDTH, 150);

    infoPane.setPrefSize(WIDTH, 300);
    infoPane.setBorder(BLACK_BORDER);

    description.setText(descriptionText);
    description.setWrapText(true);
    description.setPrefSize(WIDTH, 125);
    description.setFont(TEST_FONT);
    description.setAlignment(Pos.TOP_LEFT);
    description.setBorder(BLACK_BORDER);

    infoHeader.setText("Information for selected workout:");
    infoHeader.setPrefSize(WIDTH, 25);
    infoHeader.setFont(TEST_FONT);
    infoHeader.setBorder(BLACK_BORDER);

    labelPane.setPrefSize(WIDTH, 400);
    labelPane.setBorder(BLACK_BORDER);

    for (int i = 0; i < SidePane.getNodeSize(); i++) {
      info.add(new Label("Blank Label: "));
      info.get(i).setFont(TEST_FONT);
      info.get(i).setBorder(RED_BORDER);
      info.get(i).setPrefSize(WIDTH, 40);
      labelLayout.getChildren().add(info.get(i));
    }

    labelPane.getChildren().add(labelLayout);
    labelPane.setOnScroll(new EventHandler<ScrollEvent>() {
      @Override
      public void handle(ScrollEvent event) {
        double deltaY = event.getDeltaY() * MULTIPLIER; // *6 to make the scrolling a bit faster
        double width = labelScrollPane.getContent().getBoundsInLocal().getWidth();
        double vvalue = labelScrollPane.getVvalue();
        labelScrollPane.setVvalue(vvalue + -deltaY / width); // deltaY/width to make the
                                                                // scrolling equally
        // fast regardless of the actual width of the
        // component
      }
    });
    labelScrollPane.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
    labelScrollPane.setHbarPolicy(ScrollBarPolicy.NEVER);
    labelScrollPane.setBorder(BLACK_BORDER);
    labelScrollPane.setPrefSize(WIDTH, 325);
    labelScrollPane.vvalueProperty().addListener(new ChangeListener<Number>() {
      public void changed(ObservableValue<? extends Number> ov, Number old_val, Number new_val) {
        double testNum = 0.99;
        if ((double) new_val >= 1.0) {
          new_val = 0.99;
          selectorScrollPane.setVvalue((double)new_val);
          System.out.println("After Max: " + selectorScrollPane.getVvalue());
        } else if ((double) new_val <= 0) {
          selectorScrollPane.setVvalue(0.01);
        }
        System.out.println(new_val);
      }
    });

    infoPaneLayout.getChildren().addAll(description, infoHeader, labelScrollPane);
    infoPane.getChildren().add(infoPaneLayout);

    layout.getChildren().addAll(pracSelectorHeader, selectorScrollPane, infoPane);
  }

  private void createList(Pane pane) throws IOException {
    ArrayList<LoadPractice> buttons = new ArrayList<LoadPractice>();
    String date = null;
    Scanner fileInput = null;
    VBox layout = new VBox();
    File dates = new File(DEST + "dates.txt");
    fileInput = new Scanner(dates);
    while (fileInput.hasNextLine()) {
      date = fileInput.nextLine();
      buttons.add(new LoadPractice());
      buttons.get(buttons.size() - 1).setDate(date);
    }
    fileInput.close();
    // System.out.println(Arrays.toString(buttons.toArray()));
    for (int i = 0; i < buttons.size(); i++) {
      layout.getChildren().add(buttons.get(i).getLayout());
    }
    pane.getChildren().add(layout);
  }

  public static void setInfo(ArrayList<Label> newInfo) {
    info.clear();
    labelLayout.getChildren().clear();
    for (int i = 0; i < newInfo.size(); i++) {
      info.add(newInfo.get(i));
      labelLayout.getChildren().add(info.get(i));
    }
  }


  public VBox getLayout() {
    return layout;
  }

}
