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
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import model.Usuario;

public class Menu implements Initializable{
	static final String USER = "pri_easyliving";
    static final String PASS = "MixiMixi";
	

	@FXML
    private Label holaVariable;

    @FXML
    private Label variableNombre;

    @FXML
    private Button botonCoche;

    @FXML
    private Button botonRevision;

    @FXML
    private Button botonITV;

    @FXML
    private Button botonRutas;

    @FXML
    private Button botonHistorial;

    @FXML
    private Button botonDatos;
    
    @FXML
    private Button MensajeriaBtton;

    @FXML
    private ImageView imagenEuropeaParcial;

	private Usuario userLogged;
    
    public void setUserLogged(Usuario userLogged) {
        this.userLogged = userLogged;
    }
    
    public void initialize(URL location, ResourceBundle resources) {
    	variableNombre.setText(userLogged.nombre);
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
			FXMLLoader loaderFechaITV = new FXMLLoader(getClass().getClass().getResource("/view/RegistroConstantes_View.fxml"));
			
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
    void clickITV(ActionEvent event) {
    	try {
			FXMLLoader loaderFechaITV = new FXMLLoader(getClass().getResource("/view/FechaITV_View.fxml"));
			
			FechaITV controlerFechaITV = new FechaITV();
			
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
			stage.setTitle("Revision");
		} catch(Exception e) {
			e.printStackTrace();
		}
    }

    @FXML
    void clickRutas(ActionEvent event) {
    	try {
			FXMLLoader loaderRutas_Guardadas = new FXMLLoader(getClass().getResource("/view/RutasGuardadas_View.fxml"));
			
			Rutas_Guardadas controlerRutas_Guardadas = new Rutas_Guardadas();
			
			loaderRutas_Guardadas.setController(controlerRutas_Guardadas);
			
			controlerRutas_Guardadas.setUserLogged(userLogged);

			Parent rootRutas_Guardadas = loaderRutas_Guardadas.load();
			
			Stage stage = new Stage();
			
			stage.setScene(new Scene(rootRutas_Guardadas));
			stage.show();
			Stage stage1 = (Stage) botonRutas.getScene().getWindow();
			stage1.close();
			stage.setTitle("Rutas Guardadas");
		} catch(Exception e) {
			e.printStackTrace();
		}

    }

      
    @FXML
    void llevar_a_mensajeria(ActionEvent event) {
    	
    		try {
    			FXMLLoader loader_Vermensajeria = new FXMLLoader(getClass().getResource("/view/VerMensajeria_View.fxml"));
    			
    			VerMensajes controler_VerMensajeria = new VerMensajes();
    			
    			loader_Vermensajeria.setController(controler_VerMensajeria);
    			
    			controler_VerMensajeria.setUserLogged(userLogged);
    			
    			Parent root_Vermensajeria = loader_Vermensajeria.load();
    			
    			Stage stage = new Stage();
    			
    			stage.setScene(new Scene(root_Vermensajeria));
    			stage.show();
    			Stage stage1 = (Stage) MensajeriaBtton.getScene().getWindow();
    			stage1.close();
    			stage.setTitle("Ver Mensajería");
    		} catch(Exception e) {
    			e.printStackTrace();
    		}

}
}