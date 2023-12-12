package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import model.Usuario;

public class MenuTecnico implements Initializable{

    @FXML
    private Label holaVariable;

    @FXML
    private Label variableNombre;

    @FXML
    private Button botonITV;

    @FXML
    private Button botonRevision;

    @FXML
    private Button botonCoche;
    
    @FXML
    private Button MensajeriaBtton;
    
    private Usuario userLogged;
    
    public void setUserLogged(Usuario userLogged) {
        this.userLogged = userLogged;
    }
    
    public void initialize(URL location, ResourceBundle resources) {
    	variableNombre.setText(userLogged.nombre);;
	}

    @FXML
    void clickCoche(ActionEvent event) {
     	try {
			FXMLLoader loaderInfoCoche = new FXMLLoader(getClass().getResource("/view/InfoCoche_View.fxml"));
			
			InfoCoche controlerInfoCoche = new InfoCoche();
			
			loaderInfoCoche.setController(controlerInfoCoche);

			controlerInfoCoche.setUserLogged(userLogged);
			
			Parent rootInfoCoche = loaderInfoCoche.load();
			
			Stage stage = new Stage();
			
			stage.setScene(new Scene(rootInfoCoche));
			stage.show();
			Stage stage1 = (Stage) botonCoche.getScene().getWindow();
			stage1.close();
			stage.setTitle("Información del Coche");
			} catch(Exception e) {
			e.printStackTrace();
		}

    }

    @FXML
    void clickITV(ActionEvent event) {
    	try {
			FXMLLoader loaderFechaITV = new FXMLLoader(getClass().getResource("/view/FechaITVTecnico_View.fxml"));
			
			FechaITVTecnico controlerFechaITV = new FechaITVTecnico();
			
			loaderFechaITV.setController(controlerFechaITV);
			
			controlerFechaITV.setUserLogged(userLogged);

			Parent rootFechaITV = loaderFechaITV.load();
			
			Stage stage = new Stage();
			
			stage.setScene(new Scene(rootFechaITV));
			stage.show();
			Stage stage1 = (Stage) botonITV.getScene().getWindow();
			stage1.close();
			stage.setTitle("Fecha ITV");
		} catch(Exception e) {
			e.printStackTrace();
		}


    }

    @FXML
    void clickRevision(ActionEvent event) { //revision del coche
    	try {
			FXMLLoader loaderFechaITV = new FXMLLoader(getClass().getResource("/view/Revision_View.fxml"));
			
			Revision controlerFechaITV = new Revision();
			
			loaderFechaITV.setController(controlerFechaITV);
			
			controlerFechaITV.setUserLogged(userLogged);

			Parent rootFechaITV = loaderFechaITV.load();
			
			Stage stage = new Stage();
			
			stage.setScene(new Scene(rootFechaITV));
			stage.show();
			Stage stage1 = (Stage) botonRevision.getScene().getWindow();
			stage1.close();
			stage.setTitle("Revison Coche");
		} catch(Exception e) {
			e.printStackTrace();
		}
    }
    
    @FXML
    void llevar_a_mensajeria(ActionEvent event) {
    	try {
			FXMLLoader loader_mensajeria = new FXMLLoader(getClass().getResource("/view/Mensajeria_View.fxml"));
			
			EnviarMsg controler_mensajeria = new EnviarMsg();
			
			loader_mensajeria.setController(controler_mensajeria);
			
			controler_mensajeria.setUserLogged(userLogged);

			Parent root_mensajeria = loader_mensajeria.load();
			
			Stage stage = new Stage();
			
			stage.setScene(new Scene(root_mensajeria));
			stage.show();
			Stage stage1 = (Stage) MensajeriaBtton.getScene().getWindow();
			stage1.close();
			stage.setTitle("Mensajería");
		} catch(Exception e) {
			e.printStackTrace();
		}

    }

}
