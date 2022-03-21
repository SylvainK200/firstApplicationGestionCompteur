package controller;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.control.LabeledMatchers.hasText;

import Gui.Main;
import controller.ModelTabs.InvitationClientTable;
import controller.ModelTabs.PointDeFournitureTable;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.control.TableView;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.runners.MethodSorters;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.matcher.base.NodeMatchers;
import org.testfx.util.WaitForAsyncUtils;

import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

import java.util.logging.Level;
import java.util.logging.Logger;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PrincipalTest  extends  ApplicationTest{
    public FxRobot robot = new FxRobot();
    final String TABINVITATION = "#tabInvitation";
    final String TABPORTEFEUILLE = "#tabPortefeuille";
    final String TABPOINTDEFOURNITURE = "#tabPointDeFourniture";
    final String TABCONSOMMATION = "#tabConsommation";

    final int SLEEPINGTIME = 2000;
   String userIdentifier = "abdel0";
    String userPassword = "abdel0";

    String userInvited = "Abdel2";
    String userInvitedPassword = "Abdel2";



    @BeforeClass
    public static void config() throws Exception{
        System.getProperties().put("test.fx.robot","glass");
    }
    @Override public void start(Stage stage) throws Exception{
        Main.startForTests(stage);
    }

    public void connect( String userIdentifier,String userPassword) throws Exception{
        clickOn("#identifiant").write(userIdentifier);
        clickOn("#mot_de_passe").write(userPassword);

        clickOn("#connect_button");
        WaitForAsyncUtils.waitForFxEvents();
    }
     @Test
    public void t1_connection_user() throws Exception{
        connect(this.userIdentifier,this.userPassword);
        
        clickOn("#tabPointDeFourniture");
        clickOn("#menuPointFourniture")
                .type(KeyCode.DOWN);

        System.out.println("DOWN");
        type(KeyCode.DOWN);
        sleep(SLEEPINGTIME);
        type(KeyCode.UP);
        sleep(SLEEPINGTIME);
        clickOn("#rechercher").write("lll");
        clickOn("#rechercher");

        //eraseText(4);
        clickOn("#rechercher").write("portefeuille");
        clickOn("#rechercher");
        //eraseText(15);
        clickOn("#rechercher").write("rercher");
        clickOn("#rechercher");
        //eraseText(8);
        clickOn(TABPORTEFEUILLE);
        clickOn("#tabConsommation");

        clickOn(TABINVITATION);
        sleep(2000);

    }


    @Test
    public void t2_connect_deconnect() throws Exception {
        connect(this.userIdentifier,this.userPassword);
        clickOn("#buttonDeconnect");
        verifyThat("#connect_button", NodeMatchers.isVisible());
        sleep(SLEEPINGTIME);

    }
    @Test
    public void t3_creation_compte() {
        //Loading page
        clickOn("#creer_compte");
        WaitForAsyncUtils.waitForFxEvents();

        clickOn("#nom").write("Abdel");
        clickOn("#number").write("690909090");
        clickOn("#identifiant").write(userIdentifier);
        clickOn("#adresse_mail").write("mail@example.com");
        clickOn("#mot_de_passe").write(userPassword);
        clickOn("#confirmation_mot_de_passe").write(userPassword);
        clickOn("#question_secrete").write("Who");
        clickOn("#reponse_question_secrete").write("is");
        clickOn("#creer_compte");
        WaitForAsyncUtils.waitForFxEvents();
        sleep(2000);
        verifyThat("#connect_button", hasText("Se connecter"));
    }

   

    

    @Test
    public void t4_ajoutPortefeuille() throws Exception{
        connect(this.userIdentifier,this.userPassword);
        clickOn(TABPORTEFEUILLE);
        clickOn("#ButtonAjouterPortefeuille");
        WaitForAsyncUtils.waitForFxEvents();
        clickOn("#nomPortefeuille").write("testPortefeuille");
        clickOn("#typePortefeuille").write("testtype");
        clickOn("#montantPlafond").write("montantPlafond");
        clickOn("#rue").write("rue-1");
        clickOn("#numero").write("345");
        clickOn("#ville").write("Paris");
        clickOn("#codePostal").write("2345");

        clickOn("#buttonEnregistrer");
        Node n = lookup("OK").query();
        clickOn(n);
        n = lookup("#buttonEnregistrer").query();
        final Stage stage = (Stage) n.getScene().getWindow();
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                stage.close();
            }
        });
        sleep(2000);
        Platform.exit();

    }


    @Test
    public void t5_modifierPortefeuille() throws Exception{
        connect(this.userIdentifier,this.userPassword);
        clickOn(TABPORTEFEUILLE);
        TableView n = (TableView) lookup("#TablesAffichagesPortefeuille").query();
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                n.getSelectionModel().select(0);
                sleep(SLEEPINGTIME/2);
                System.out.println("ouverture");
                clickOn("#ButtonModifier");
                WaitForAsyncUtils.waitForFxEvents();
                clickOn("#nom").eraseText(20);
                sleep(SLEEPINGTIME);
                clickOn("#nom").write("nouveaunom");
                clickOn("#button_valider");
                Node n2 = lookup("OK").query();
                clickOn(n2);
                System.out.println("....");
                Node n3 = lookup("valider").query();
                Stage s = (Stage)n3.getScene().getWindow();
                s.close();
            }
        });

        sleep(SLEEPINGTIME);
        Platform.exit();
    }
    public void clickOnAlertInformation(){
        Node n2 = lookup("OK").query();
        clickOn(n2);
    }
    @Test
    public void t6_ajoutPointDeFourniture() throws Exception{
        connect(this.userIdentifier,this.userPassword);
        clickOn(TABPORTEFEUILLE);
        clickOn("#ean_point_fourniture");
        type(KeyCode.DOWN);
        type(KeyCode.ENTER);
        clickOn("#boutonAjouter_pointFourniture");
        try{
            clickOnAlertInformation();
        }catch (Exception e){
            Logger.getLogger(this.getClass().getName()).log(Level.WARNING,"No OK found");
        }
        sleep(SLEEPINGTIME);
    }

    @Test
    public void t7_creationInvitation() throws Exception{
        connect(this.userIdentifier,this.userPassword);
        clickOn("#tabInvitation");
        clickOn("#ButtonAjouterInvitation");
        WaitForAsyncUtils.waitForFxEvents();
        clickOn("#ComboboxPortefeuille");
        type(KeyCode.DOWN);
        type(KeyCode.ENTER);
        clickOn("#identifiant").write("Abdel2");
        clickOn("#roleEcriture");
        clickOn("#roleLecture");
        clickOn("#ButtonValider");
        clickOnAlertInformation();
        clickOn("#ButtonRetour");
        TableView t = (TableView) lookup("TableInvitation").query();
        assert(t.getItems().size()>0);

        sleep(SLEEPINGTIME);

    }


    @Test
    public void t8_supprimerInvitation() throws Exception{
        connect(this.userIdentifier,this.userPassword);
        clickOn(TABINVITATION);
        TableView n = (TableView) lookup("#TableInvitation").query();
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                n.getSelectionModel().select(0);
                clickOn("#ButtonSupprimerInvitation");
                WaitForAsyncUtils.waitForFxEvents();
                sleep(2000);
            }
        });

    }

    @Test
    public void t9_creationInvitation2() throws Exception{
        connect(this.userIdentifier,this.userPassword);
        clickOn("#tabInvitation");
        clickOn("#ButtonAjouterInvitation");
        WaitForAsyncUtils.waitForFxEvents();
        clickOn("#ComboboxPortefeuille");
        type(KeyCode.DOWN);
        type(KeyCode.ENTER);
        clickOn("#identifiant").write("Abdel2");
        clickOn("#roleEcriture");
        clickOn("#roleLecture");
        clickOn("#ButtonValider");
        clickOnAlertInformation();
        clickOn("#ButtonRetour");
        TableView t = (TableView) lookup("TableInvitation").query();
        assert(t.getItems().size()>0);

        sleep(SLEEPINGTIME);

    }

    @Test
    public void tt1_reception_invitation() throws Exception{
        connect(userInvited,userInvitedPassword);
        TableView t = (TableView) lookup("#tableInvitation").query();
        Platform.runLater(
                new Runnable() {
                    @Override
                    public void run() {
                        t.getSelectionModel().select(0);
                        InvitationClientTable inv = (InvitationClientTable) t.getSelectionModel().getSelectedItem();
                        inv.select.setSelected(true);

                    }
                }
        );
        clickOn("#buttonAccepter");
        clickOn("#buttonPageSuivante");
        WaitForAsyncUtils.waitForFxEvents();
        sleep(SLEEPINGTIME);
        Platform.exit();
    }

    @Test
    public void tt2_supprimerPointFourniture() throws Exception{
        connect(userIdentifier,userPassword);
        TableView t = (TableView) lookup("#menuPointFourniture").query();
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                t.getSelectionModel().select(0);

            }
        });
        clickOn("#buttonSupprimerPointFourniture");
        clickOnAlertInformation();
        sleep(SLEEPINGTIME);
        Platform.exit();
    }
    @Test
    public void tt3_supprimerPortefeuille() throws Exception{

        connect(
                userIdentifier,
                userPassword
        );
        clickOn(TABPORTEFEUILLE);
        TableView t = (TableView) lookup("#TablesAffichagesPortefeuille").query();
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                t.getSelectionModel().select(0);

            }
        });
        clickOn("ButtonSupprimer");
        clickOnAlertInformation();
        sleep(SLEEPINGTIME);
        Platform.exit();
    }

}
