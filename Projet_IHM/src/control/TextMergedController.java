package control;

import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.FileChooser;
import view.TextMergedView;

import javafx.scene.control.TextArea;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import javafx.scene.control.ScrollPane;

/* j'importe la bibliotheque diff_match_patch depuis le package name.fraser.neil.plaintext  */
import name.fraser.neil.plaintext.diff_match_patch;
import name.fraser.neil.plaintext.diff_match_patch.Diff;





public class TextMergedController {
    private TextMergedView view;

    /*----------- je cree un constructeur qui prend en parametre la vue --------*/
    public TextMergedController(TextMergedView view) {
        this.view = view;
        view.setOriginalFileImportAction(this::importFile);
        view.setModifiedFileImportAction(this::importFile);

        /* je connecte le boutton comparer avec la methode compareText */
        view.getcompareButton().setOnAction(event -> compareText());
    }

     /* ---------------- je cree une methode pour importer les fichiers */
    private void importFile(TextArea textArea) {
        FileChooser fileChooser = new FileChooser();
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            try {
                String content = new String(Files.readAllBytes(Paths.get(selectedFile.getPath())));
                textArea.setText(content);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }


    /* je cree une methode pour comparer les deux textes: j'utilise la bibliotheque diff_match_patch */
   public void compareText() {
    String originalText = view.getOriginalTextArea().getText() + "\n";
    String modifiedText = view.getModifiedTextArea().getText() + "\n";

    diff_match_patch dmp = new diff_match_patch();
    LinkedList<Diff> diffs = dmp.diff_main(originalText, modifiedText);

    updateScrollPaneWithDiffs(view.getOriginalScrollPane(), diffs, false);
    updateScrollPaneWithDiffs(view.getModifiedScrollPane(), diffs, true);
}


    /* je cree une methode pour mettre a jour les textes avec les differences */
private void updateScrollPaneWithDiffs(ScrollPane scrollPane, LinkedList<Diff> diffs, boolean isModifiedText) {
    TextFlow textFlow = new TextFlow();

    for (Diff diff : diffs) {
        Text text = new Text(diff.text);

        switch (diff.operation) {
            case INSERT:
                if (isModifiedText) {
                    text.setFill(Color.GREEN);
                    textFlow.getChildren().add(text);
                }
                break;
            case DELETE:
                if (!isModifiedText) {
                    text.setFill(Color.RED);
                    textFlow.getChildren().add(text);
                }
                break;
            case EQUAL:
                text.setFill(Color.BLACK);
                textFlow.getChildren().add(text);
                break;
        }
    }

    scrollPane.setContent(textFlow);
}


}
