package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.ScrollPane;

public class TextMergedView extends BorderPane {

    /* je cree deux attribut textarea representant les zones de saisie de mes deux textes*/
        private TextArea originalTextArea;
        private TextArea modifiedTextArea;
   /* deux scrollpane pour contenir mes text avec leur diference */
    private ScrollPane originalScrollPane;
    private ScrollPane modifiedScrollPane;

/* boutton d'import */
    private Button importoriginaltextButton;
    private Button importedittextButton;

    /* boutton de comparaison */
    private Button compareButton;



        /* ------------------------------je cree un constructeur qui initialise les deux zones de texte -----------------------------*/
        public TextMergedView() {
            originalTextArea = new TextArea();
            modifiedTextArea = new TextArea();

            originalScrollPane = new ScrollPane();
            originalScrollPane.setPrefSize(600, 600);
            modifiedScrollPane = new ScrollPane();
            modifiedScrollPane.setPrefSize(600, 600);


            originalTextArea.setPromptText("Importer votre fichier original...");
            originalTextArea.setPrefHeight(400);
            originalTextArea.setPrefWidth(600);
            originalTextArea.setEditable(false); // Désactive la modification du texte ORIGINAL
            originalTextArea.setWrapText(true); // Active le retour à la ligne automatique

            modifiedTextArea.setPromptText("Importer votre fichier modifié...");
            modifiedTextArea.setPrefHeight(400);
            modifiedTextArea.setPrefWidth(600);
            modifiedTextArea.setWrapText(true); // Active le retour à la ligne automatique

            this.setLeft(originalTextArea);
            this.setRight(modifiedTextArea);

            /* -------------------------------Je cree la barre de menu -------------------------------*/
            MenuBar menuBar = new MenuBar();
            // element du menu fichier
            Menu Menufichier = new Menu("Fichier");
            MenuItem saveItem = new MenuItem("Sauvegarder");
            MenuItem exportItem = new MenuItem("Exporter en Format TXT");
            MenuItem deleteItem = new MenuItem("Supprimer");

            // element du menu editer
            Menu Menuedit = new Menu("Editer");
            MenuItem acceptitem = new MenuItem("Accepter");
            MenuItem refuseritem = new MenuItem("Refuser");

            // j'ajoute les elements du menu fichier et editer
            Menufichier.getItems().addAll(exportItem, saveItem, deleteItem);
            Menuedit.getItems().addAll(acceptitem, refuseritem);
            menuBar.getMenus().addAll(Menufichier,Menuedit);

            this.setTop(menuBar);

            /* bouton de comparaison et d'import*/
            //  bouton de comparaison
             compareButton = new Button("Comparer");

            // bouton d'import
             importoriginaltextButton = new Button("Import original file");

             importedittextButton = new Button("Import modified file");

            /*------ je mets les elements dans un Hbox-------*/
            // les deux textarea dnas un Hbox
            HBox textAreasBox = new HBox(5, originalTextArea, modifiedTextArea);
            textAreasBox.setPadding(new Insets(10));
            textAreasBox.setAlignment(Pos.CENTER);

            // les boutons dans un Hbox
            HBox importButtonsBox = new HBox(500, importoriginaltextButton, importedittextButton);
            importButtonsBox.setPadding(new Insets(10));
            importButtonsBox.setAlignment(Pos.CENTER);

            // les deux scrollpane dans un Hbox
            HBox scrollPaneBox = new HBox(5, originalScrollPane, modifiedScrollPane);
            scrollPaneBox.setPadding(new Insets(10));
            scrollPaneBox.setAlignment(Pos.CENTER);

            /* je met les deux Hbox et le boutton comparer dans un Vbox*/
            VBox vbox = new VBox(5, importButtonsBox,textAreasBox, scrollPaneBox, compareButton);
            vbox.setPadding(new Insets(5));
            vbox.setAlignment(Pos.CENTER);

            this.setTop(menuBar);
            this.setCenter(vbox);

        }

        /*-------------------------- getters and seters--------------------------------*/
        public TextArea getOriginalTextArea() {

            return originalTextArea;
        }

        public void setOriginalTextArea(TextArea originalTextArea) {
            this.originalTextArea = originalTextArea;
        }

        public TextArea getModifiedTextArea() {
            return modifiedTextArea;
        }

        public void setModifiedTextArea(TextArea modifiedTextArea) {

            this.modifiedTextArea = modifiedTextArea;
        }

        public ScrollPane getOriginalScrollPane() {
            return originalScrollPane;
        }

        public void setOriginalScrollPane(ScrollPane originalScrollPane) {
            this.originalScrollPane = originalScrollPane;
        }

        public ScrollPane getModifiedScrollPane() {
            return modifiedScrollPane;
        }

        public void setModifiedScrollPane(ScrollPane modifiedScrollPane) {
            this.modifiedScrollPane = modifiedScrollPane;
        }



        // Méthode pour récupérer le contenu de la zone de texte original
    public void setOriginalFileImportAction(FileImportAction action) {
        importoriginaltextButton.setOnAction(e -> action.importFile(originalTextArea));
    }

    // Méthode pour récupérer le contenu de la zone de texte modifié
    public void setModifiedFileImportAction(FileImportAction action) {
        importedittextButton.setOnAction(e -> action.importFile(modifiedTextArea));
    }

    // Méthode pour récupérer le bouton de comparaison
    public Button getcompareButton() {
        return compareButton;
    }


}