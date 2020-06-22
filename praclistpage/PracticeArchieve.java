package praclistpage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class PracticeArchieve extends LoadPractice {
  // Constants
  private final int MULTIPLIER = 4;
  private static final String DEST = "C:\\Users\\Brandon\\.eclipse\\Practice_Builder\\";
  // Panes
  private Pane mainP = new Pane();
  private Pane pracSelector = new Pane();
  private Pane pracInfo = new Pane();
  private Pane graphPane = new Pane();
  private Pane graphInfoPane = new Pane();
  private ScrollPane mainScene;
  private ScrollPane pracSelectorScroll;
  private Pane graph1 = new Pane();
  private Pane graph2 = new Pane();
  private Pane graph3 = new Pane();
  // Layouts
  private VBox overalLayout = new VBox();
  private HBox LayoutA = new HBox();
  private VBox LayoutAA = new VBox();
  private VBox LayoutAB = new VBox();
  private HBox LayoutB = new HBox();
  // Borders
  private Border redBorder = new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID,
      CornerRadii.EMPTY, BorderWidths.DEFAULT));
  private Border blackBorder = new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID,
      CornerRadii.EMPTY, BorderWidths.DEFAULT));
  private Border blueBorder = new Border(new BorderStroke(Color.BLUE, BorderStrokeStyle.SOLID,
      CornerRadii.EMPTY, BorderWidths.DEFAULT));

  public PracticeArchieve() {
    int height = 2400;
    int width = 1525;

    mainP.setPrefSize(1800, height);
    mainP.setOnScroll(new EventHandler<ScrollEvent>() {
      @Override
      public void handle(ScrollEvent event) {
        double deltaY = event.getDeltaY() * MULTIPLIER; // *6 to make the scrolling a bit faster
        double width = mainScene.getContent().getBoundsInLocal().getWidth();
        double vvalue = mainScene.getVvalue();
        mainScene.setVvalue(vvalue + -deltaY / width); // deltaY/width to make the scrolling equally
                                                       // fast regardless of the actual width of the
                                                       // component
      }
    });
    mainScene = new ScrollPane(mainP);
    mainScene.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
    mainScene.setHbarPolicy(ScrollBarPolicy.AS_NEEDED);
    mainScene.setFitToWidth(true);

    // Setup practice archieve List
    pracSelector.setPrefSize(500, 800);
    pracSelector.setOnScroll(new EventHandler<ScrollEvent>() {
      @Override
      public void handle(ScrollEvent event) {
        double deltaY = event.getDeltaY() * MULTIPLIER; // *6 to make the scrolling a bit faster
        double width = pracSelectorScroll.getContent().getBoundsInLocal().getWidth();
        double vvalue = pracSelectorScroll.getVvalue();
        pracSelectorScroll.setVvalue(vvalue + -deltaY / width); // deltaY/width to make the
                                                                // scrolling equally
        // fast regardless of the actual width of the
        // component
      }
    });
    pracSelectorScroll = new ScrollPane(pracSelector);
    pracSelectorScroll.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
    pracSelectorScroll.setHbarPolicy(ScrollBarPolicy.NEVER);
    pracSelectorScroll.setBorder(blackBorder);
    pracSelectorScroll.setPrefSize(500, 350);

    pracInfo.setPrefSize(500, 250);
    pracInfo.setBorder(redBorder);
    try {
      createList(pracSelector);
    } catch (IOException e) {
      e.printStackTrace();
    }


    Label pracSelectorHeader = new Label("Past Practice List");
    pracSelectorHeader.setFont(Font.font("Arial", 20));
    pracSelectorHeader.setPrefSize(500, 50);
    pracSelectorHeader.setBorder(redBorder);
    LayoutAA = new VBox();
    LayoutAA.getChildren().addAll(pracSelectorHeader, pracSelectorScroll, pracInfo);

    // Graph Set up
    graphPane.setPrefSize(1000, 600);
    graphPane.setBorder(blackBorder);
    graphInfoPane.setPrefSize(1000, 50);
    graphInfoPane.setBorder(redBorder);
    LayoutAB = new VBox();
    LayoutAB.getChildren().addAll(graphPane, graphInfoPane);

    Region spacer1 = new Region();
    spacer1.setPrefSize(2, 650);

    Region spacer2 = new Region();
    spacer2.setPrefSize(10, 650);

    LayoutA.getChildren().addAll(spacer1, LayoutAA, spacer2, LayoutAB);
    LayoutA.setMinWidth(width);
    LayoutA.setMinHeight(650);
    LayoutA.setBorder(blueBorder);

    graph1.setPrefSize(500, 500);
    graph1.setBorder(blackBorder);
    Region spacerG1 = new Region();
    spacerG1.setPrefSize(10, 500);
    graph2.setPrefSize(500, 500);
    graph2.setBorder(blackBorder);
    Region spacerG2 = new Region();
    spacerG2.setPrefSize(10, 500);
    graph3.setPrefSize(500, 500);
    graph3.setBorder(blackBorder);

    LayoutB.getChildren().addAll(graph1, spacerG1, graph2, spacerG2, graph3);
    LayoutB.setMinWidth(width);
    LayoutB.setMinHeight(500);
    LayoutB.setBorder(blueBorder);
    overalLayout.getChildren().addAll(LayoutA, LayoutB);
    mainP.getChildren().add(overalLayout);
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
      buttons.add(new LoadPractice(date));
    }
    fileInput.close();
    System.out.println(Arrays.toString(buttons.toArray()));
    for (int i = 0; i < buttons.size(); i++) {
      layout.getChildren().add(buttons.get(i).getLayout());
    }
    pane.getChildren().add(layout);
  }

  public ScrollPane getPane() {
    return mainScene;
  }
}
