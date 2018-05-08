package grup2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.Background;
import javafx.scene.text.Font;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

import static grup2.Main.OYS;
import static grup2.Main.yarismaEkraniPencere;

/**
 * Created by Yavuz on 26.04.2018.
 */
public class YarismaEkraniController implements Initializable {
    int soruSayaci = 1;
    Soru simdikiSoru ;

    @FXML
    public Label LabelUlkeAdi;

    @FXML
    public Label LabelSoruSayisi;

    @FXML
    public Label LabelSoru;

    @FXML
    public Button ButtonSecenekA;

    @FXML
    public Button ButtonSecenekB;

    @FXML
    public Button ButtonSecenekC;

    @FXML
    public Button ButtonSecenekD;





    /**
     * Called to initialize a controller after its root element has been
     * completely processed.
     *
     * @param location  The location used to resolve relative paths for the root object, or
     *                  <tt>null</tt> if the location is not known.
     * @param resources The resources used to localize the root object, or <tt>null</tt> if
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        OYS.soruPaketiOlustur(5);
        System.out.println("ss");
        soruSayaci = 1;
        LabelUlkeAdi.setText(OYS.simdikiUlke.getUlkeAdi());
        simdikiSoru = getNextSoru();
        sorularıGoster(simdikiSoru);
    }


    public boolean checkIfTrue(int prediction, int actual){
        if (prediction == actual){
            return true;
        }
        return false;
    }

    public Soru getNextSoru(){
        return OYS.sorular.poll();
    }

    public void sorularıGoster(Soru s){
        LabelSoruSayisi.setText(soruSayaci + "/5");
        LabelSoru.setText(s.soruMetni);
        if( LabelSoru.getText().length()>60){
            LabelSoru.setFont(Font.font(18));
        }else {
            LabelSoru.setFont(Font.font(23));
        }

        ButtonSecenekA.setText(s.secenekA);
        ButtonSecenekB.setText(s.secenekB);
        ButtonSecenekC.setText(s.secenekC);
        ButtonSecenekD.setText(s.secenekD);
        soruSayaci++;
    }

    public void buttonAction(ActionEvent event){
        if (!OYS.sorular.isEmpty()){
            int prediction = Integer.parseInt(((Button)event.getSource()).getId().replace("btn",""));

            switch (prediction) {
                case 1: break;
                case 2: break;
                case 3: break;
                case 4: break;
            }

            if(checkIfTrue(prediction,simdikiSoru.dogruCevap)){
                ButtonSecenekA.setStyle("-fx-background-color: green");
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }else {
                ((Button)event.getSource()).setStyle("-fx-background-color: red");
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            simdikiSoru = getNextSoru();
//            sorularıGoster(simdikiSoru);
        } else {
            OYS.writeUsersIntoJson();
            yarismaEkraniPencere.close();
        }
    }
}
