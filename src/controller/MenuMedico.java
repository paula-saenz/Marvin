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

public class MenuMedico implements Initializable{

    @FXML
    private Label holaVariable;

    @FXML
    private Label variableNombre;

    @FXML
    private Button botonDatos;

    @FXML
    private Button botonHistorial;
    
    @FXML
    private Button MensajeriaBtton;

    @FXML
    private Button botonCoche;
    
    private Usuario userLogged;
    
    public void setUserLogged(Usuario userLogged) {
        this.userLogged = userLogged;
    }
    
    public void initialize(URL location, ResourceBundle resources) {
    	variableNombre.setText(userLogged.nombre);
	}

    @FXML
    void clickDatos(ActionEvent event) { //info guante 
    	try {
			FXMLLoader loaderFechaITV = new FXMLLoader(getClass().getResource("/view/InfoGuante_View.fxml"));
			
			InfoGuante controlerFechaITV = new InfoGuante();
			
			loaderFechaITV.setController(controlerFechaITV);
			
			controlerFechaITV.setUserLogged(userLogged);

			Parent rootFechaITV = loaderFechaITV.load();
			
			Stage stage = new Stage();
			
			stage.setScene(new Scene(rootFechaITV));
			stage.show();
			Stage stage1 = (Stage) botonDatos.getScene().getWindow();
			stage1.close();
			stage.setTitle("Información del Guante");
		} catch(Exception e) {
			e.printStackTrace();
		}
    	

    }

    @FXML
    void clickHistorial(ActionEvent event) { //datos de las constantes
    	try {
			FXMLLoader loaderFechaITV = new FXMLLoader(getClass().getResource("/view/RegistroConstantes_View.fxml"));
			
			RegistroConstantes controlerFechaITV = new RegistroConstantes();
			
			loaderFechaITV.setController(controlerFechaITV);
			
			controlerFechaITV.setUserLogged(userLogged);

			Parent rootFechaITV = loaderFechaITV.load();
			
			Stage stage = new Stage();
			
			stage.setScene(new Scene(rootFechaITV));
			stage.show();
			Stage stage1 = (Stage) botonHistorial.getScene().getWindow();
			stage1.close();
			stage.setTitle("Registro de Constantes");
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
