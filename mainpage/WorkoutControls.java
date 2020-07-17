package mainpage;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import table.Table;
import java.util.ArrayList;
import java.util.Optional;
import background.Group;
import background.IOFiles;
import background.SwimWorkout;
import graphs.Graphs;

public class WorkoutControls {
  private ArrayList<Button> buttons;
  private String[] buttonNames;

  public WorkoutControls() {
    buttons = new ArrayList<Button>();
    buttonNames = new String[] {"Add Group", "Remove Group", "Game Day", "Reset", "Save"};
  }

  public WorkoutControls(SwimWorkout workout, ArrayList<Table> tables, ArrayList<Graphs> graphs,
      VBox tableLayout, ArrayList<TableModifiers> tableControls, ArrayList<HBox> tableControlLayout,
      ArrayList<RadioButton> graphSelector, ToggleGroup RBLayout, FlowPane graphPane) {
    this();
    for (int i = 0; i < 5; i++) {
      buttons.add(new Button(buttonNames[i]));
      if (i != 1) {
        buttons.get(i).setPrefSize(80, 40);
      } else {
        buttons.get(i).setPrefSize(100, 40);
      }
      buttons.get(i).setAlignment(Pos.CENTER);
      buttons.get(i).setFont(Font.font("Arial", 12));
      switch (i) {
        case 0:
          buttons.get(i).setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
              workout.addGroup(new Group(SwimWorkout.getNextID()));
              int index = workout.size() - 1;
              tables.add(new Table(workout.getGroup(index), graphs));
              tableLayout.getChildren().addAll(tables.get(index).getHeader(),
                  tables.get(index).getTableView());
              tableControls.add(new TableModifiers(workout.getGroup(index), tables.get(index)));
              tableControlLayout.add(new HBox());
              tableControlLayout.get(index).setPrefSize(250, 40);
              tableControlLayout.get(index).setMaxWidth(250);
              tableControlLayout.get(index).setMaxHeight(50);
              tableControlLayout.get(index).getChildren().addAll(
                  tableControls.get(index).getAddRow(), tableControls.get(index).getRemRow(),
                  tableControls.get(index).getCopyRow(), tableControls.get(index).getPasteRow(),
                  tableControls.get(index).getClearRow());
              MainPage.getControl().getRowLayout().getChildren().add(tableControlLayout.get(index));
              for (int i = 0; i < tables.size(); i++) {
                tables.get(i).getTableView().refresh();
              }
              for (int i = 0; i < SidePane.getNodeSize(); i++) {
                if (SidePane.getNode(i).getData(workout.getGroup(index).getID()) == null) {
                  SidePane.getNode(i).addData(0);
                } else {
                  SidePane.getNode(i).setData(workout.getGroup(index).getID(), 0);
                }
              }
              graphSelector.add(new RadioButton("Graph " + (workout.getGroup(index).getID() + 1)));
              graphSelector.get(index).setToggleGroup(RBLayout);
              if (workout.size() == 1) {
                graphSelector.get(index).setSelected(true);
              }
              graphPane.getChildren().add(graphSelector.get(index));
              graphs.add(new Graphs(workout.getGroup(index)));
            }
          });
          break;
        case 1:
          buttons.get(i).setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
              if (workout.size() != 1) {
                tableLayout.getChildren().remove(((2 * workout.size()) - 2) + 1);
                tableLayout.getChildren().remove((2 * workout.size()) - 2);
                tables.remove(workout.size() - 1);
                MainPage.getControl().getRowLayout().getChildren().remove(workout.size() - 1);
                tableControlLayout.remove(workout.size() - 1);
                tableControls.remove(workout.size() - 1);
                for (int i = 0; i < tables.size(); i++) {
                  tables.get(i).getTableView().refresh();
                }
                for (int i = 0; i < SidePane.getNodeSize(); i++) {
                  if (SidePane.getNode(i)
                      .getData(workout.getGroup(workout.size() - 1).getID()) != null) {
                    SidePane.getNode(i).remData(workout.size() - 1);
                  }
                }
                graphs.remove(workout.size() - 1);
                if (graphSelector.get(workout.size() - 1).isSelected()) {
                  graphSelector.get(workout.size() - 1).setSelected(false);
                  graphSelector.remove(workout.size() - 1);
                  graphPane.getChildren().remove(workout.size() - 1);
                  graphSelector.get(workout.size() - 2).setSelected(true);
                } else {
                  graphSelector.remove(workout.size() - 1);
                  graphPane.getChildren().remove(workout.size() - 1);
                }
                RadioButton radioButton = (RadioButton) RBLayout.getSelectedToggle();
                for (int i = 0; i < graphSelector.size(); i++) {
                  if (radioButton.getText().contentEquals(graphSelector.get(i).getText())) {
                    MainPage.getGraphPane().getPane().getChildren().clear();
                    MainPage.getGraphPane().getLayout().getChildren().clear();
                    MainPage.getGraphPane().setLayout(graphs.get(i));
                  }
                }
                workout.remGroup(workout.size() - 1);
              }
            }
          });
          break;
        case 2:
          buttons.get(i).setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
              Alert confirmation = new Alert(AlertType.CONFIRMATION);
              confirmation.setTitle("Game Day");
              confirmation
                  .setContentText("Are you sure you want to switch to " + "game day layout?");
              Optional<ButtonType> result = confirmation.showAndWait();
              if (result.get() == ButtonType.OK) {
                System.out.println("Ok"); // TODO
              } else {
                System.out.println("Cancled"); // TODO
              }
            }
          });
          break;
        case 3:
          buttons.get(i).setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
              int remIndex = workout.size();
              for (int i = remIndex; i > 0; i--) {
                tableLayout.getChildren().remove(((2 * i) - 2) + 1);
                tableLayout.getChildren().remove((2 * i) - 2);
                tables.remove(i - 1);
                workout.remGroup(i - 1);
                MainPage.getControl().getRowLayout().getChildren().remove(i - 1);
                tableControlLayout.remove(i - 1);
                tableControls.remove(i - 1);
              }
              workout.addGroup(new Group(SwimWorkout.getNextID()));
              tables.add(new Table(workout.getGroup(0), graphs));
              tableLayout.getChildren().addAll(tables.get(0).getHeader(),
                  tables.get(0).getTableView());
              tableControls.add(new TableModifiers(workout.getGroup(0), tables.get(0)));
              tableControlLayout.add(new HBox());
              tableControlLayout.get(0).setPrefSize(250, 40);
              tableControlLayout.get(0).setMaxWidth(250);
              tableControlLayout.get(0).setMaxHeight(50);
              tableControlLayout.get(0).getChildren().addAll(tableControls.get(0).getAddRow(),
                  tableControls.get(0).getRemRow(), tableControls.get(0).getCopyRow(),
                  tableControls.get(0).getPasteRow(), tableControls.get(0).getClearRow());
              for (int i = 0; i < SidePane.getNodeSize(); i++) {
                for (int j = SidePane.getNode(i).size() - 1; j >= 0; j--) {
                  SidePane.getNode(i).remData(j);
                }
              }
              for (int i = 0; i < SidePane.getNodeSize(); i++) {
                SidePane.getNode(i).addData(0);
              }
              for (int i = graphSelector.size() - 1; i >= 0; i--) {
                graphSelector.remove(workout.size() - 1);
                graphPane.getChildren().remove(workout.size() - 1);
              }
              graphSelector.add(new RadioButton("Graph " + (workout.getGroup(0).getID() + 1)));
              graphSelector.get(0).setToggleGroup(RBLayout);
              graphSelector.get(0).setSelected(true);
              graphPane.getChildren().add(graphSelector.get(0));
              for (int i = 0; i < graphs.size(); i++) {
                graphs.remove(i);
              }
              graphs.add(new Graphs(workout.getGroup(0)));
              MainPage.getGraphPane().getPane().getChildren().clear();
              MainPage.getGraphPane().getLayout().getChildren().clear();
              MainPage.getGraphPane().setLayout(graphs.get(0));
              MainPage.getControl().getRowLayout().getChildren().add(tableControlLayout.get(0));
            }
          });
          break;
        case 4:
          buttons.get(i).setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
              Alert alert = new Alert(AlertType.CONFIRMATION);
              alert.setTitle("Save to file");
              alert.setContentText("Are you sure you want to save the workout?");
              Optional<ButtonType> result = alert.showAndWait();
              if (result.get() == ButtonType.OK) {
                IOFiles.saveToFiles(workout); // Saves the date, and dumps data
                // Creates a readable txt File of the workout and a pdf file
                EmailFile email = new EmailFile(IOFiles.readableWorkout(workout));
                if (workout.getName().contentEquals("")) { 
                  email.setSubjectLine("Swim Workout");
                } else {
                  email.setSubjectLine(workout.getName());
                }
                email.setBody(workout.getDescription());
                if (email.sendEmail()) {
                  Alert inform = new Alert(AlertType.INFORMATION);
                  inform.setTitle("Save to file");
                  inform.setHeaderText("SAVED!");
                  Label alertText = new Label("File Saved as " + email.getSubjectLine()
                      + " and emailed to " + email.getRecipient() + ".");
                  alertText.setWrapText(true);
                  inform.getDialogPane().setContent(alertText);
                  inform.showAndWait();
                } else {
                  Alert inform = new Alert(AlertType.ERROR);
                  inform.setTitle("Save to file");
                  inform.setHeaderText("ERROR!");
                  Label alertText =
                      new Label("Error: something went wrong when trying to save the file.");
                  alertText.setWrapText(true);
                  inform.getDialogPane().setContent(alertText);
                  inform.showAndWait();
                }

              } else {
                System.out.println("Not Saved");
              }
            }
          });
          break;
      }
    }
  }

  public int size() {
    return buttons.size();
  }

  public Button getButton(int i) {
    return buttons.get(i);
  }

}
