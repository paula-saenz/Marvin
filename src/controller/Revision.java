package controller;

import java.net.URL;
import java.util.ResourceBundle;
import com.jfoenix.controls.JFXTextField;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Usuario;

public class Revision implements Initializable{

	@FXML
	private Button buttonSubMenu;

	@FXML
	private Label nombreUser;

	@FXML
	private Label rolUser;

	@FXML
	private JFXTextField citaRevision;

	@FXML
	private Text textoRevision;
	
	@FXML
    private Button botonMenu;

	private Usuario userLogged;
	 
	 public void setUserLogged(Usuario userLogged) {
		 this.userLogged = userLogged;
	 }
	
	public void initialize(URL location, ResourceBundle resources) {
		if(userLogged.rol_tipo.equals("Conductor") || userLogged.rol_tipo.equals("conductor") || userLogged.rol_tipo.equals("Conductora") || userLogged.rol_tipo.equals("conductora")
		    	|| userLogged.rol_tipo.equals("Enfermero") || userLogged.rol_tipo.equals("enfermero") || userLogged.rol_tipo.equals("Enfermera") || userLogged.rol_tipo.equals("enfermera")) {
			nombreUser.setText(userLogged.nombre);
	    	rolUser.setText(userLogged.rol_tipo);
	    	
	    	textoRevision.setText("Kilometraje actual de 10000 a 15000: 1ª revisión. "
	    			+ "De 30000 a 35000: 2ª revision. >60000: 3ª revisión. ");
		} else {
			nombreUser.setText(userLogged.nombre);
	    	rolUser.setText(userLogged.rol_tipo);
	    	
	    	textoRevision.setText("Si tu kilometraje actual es de 10000 a 15000, tienes que pedir tu primera revisión. "
	    			+ "Si es de 30000 a 35000 tienes que pedir tu segunda revision. Si es superior a 60000, tienes que pedir tu "
	    			+ "tercera revisión, en la cual además se realizará cambio de bujías y de aceite. ");
		}
	}
	

@FXML
void llevaSubMenu(ActionEvent event) {
	if(userLogged.rol_tipo.equals("Conductor") || userLogged.rol_tipo.equals("conductor") || userLogged.rol_tipo.equals("Conductora") || userLogged.rol_tipo.equals("conductora")
	    	|| userLogged.rol_tipo.equals("Enfermero") || userLogged.rol_tipo.equals("enfermero") || userLogged.rol_tipo.equals("Enfermera") || userLogged.rol_tipo.equals("enfermera")) {
		try {
			FXMLLoader LoaderSubmenu = new FXMLLoader(getClass().getResource("/view/Submenu_View.fxml"));
			
			Submenu controlerSubmenu = new Submenu();
			
			LoaderSubmenu.setController(controlerSubmenu);
			
			controlerSubmenu.setUserLogged(userLogged);

			Parent rootSubmenu = LoaderSubmenu.load();
			
			Stage stage = new Stage();
			
			stage.setScene(new Scene(rootSubmenu));
			stage.show();
			Stage stage1 = (Stage) buttonSubMenu.getScene().getWindow();
			stage1.close();
			stage.setTitle("Submenu");
		} catch(Exception e) {
			e.printStackTrace();
		}
	} else {
		try {
			FXMLLoader LoaderSubmenu = new FXMLLoader(getClass().getResource("/view/Submenu_View.fxml"));
			
			Submenu controlerSubmenu = new Submenu();
			
			LoaderSubmenu.setController(controlerSubmenu);
			
			controlerSubmenu.setUserLogged(userLogged);

			Parent rootSubmenu = LoaderSubmenu.load();
			
			Stage stage = new Stage();
			
			stage.setScene(new Scene(rootSubmenu));
			stage.show();
			Stage stage1 = (Stage) buttonSubMenu.getScene().getWindow();
			stage1.close();
			stage.setTitle("Submenu");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	}

@FXML
void volverMenu(ActionEvent event) {
	if(userLogged.rol_tipo.equals("Conductor") || userLogged.rol_tipo.equals("conductor") || userLogged.rol_tipo.equals("Conductora") || userLogged.rol_tipo.equals("conductora")) {
		try {
   			FXMLLoader loaderMenu = new FXMLLoader(getClass().getResource("/view/Menu_View.fxml"));
   			
   			Menu controlMenu = new Menu();
    			
   			loaderMenu.setController(controlMenu);
   			
   			controlMenu.setUserLogged(userLogged);

    		Parent rootMenu = loaderMenu.load();
    			
    		Stage stage = new Stage();
    			
   			stage.setScene(new Scene(rootMenu));
   			stage.show();
   			Stage stage1 = (Stage) botonMenu.getScene().getWindow();
			stage1.close();
   			stage.setTitle("Menú");
   		} catch(Exception e) {
   			e.printStackTrace();
   		}
	} else if(userLogged.rol_tipo.equals("Tecnico") || userLogged.rol_tipo.equals("tecnico") || userLogged.rol_tipo.equals("Tecnica") || userLogged.rol_tipo.equals("tecnica")) {
		try {
   			FXMLLoader loaderMenu = new FXMLLoader(getClass().getResource("/view/MenuTecnico_View.fxml"));
   			
   			MenuTecnico controlMenu = new MenuTecnico();
    			
   			loaderMenu.setController(controlMenu);
   			
   			controlMenu.setUserLogged(userLogged);

    		Parent rootMenu = loaderMenu.load();
    			
    		Stage stage = new Stage();
    			
   			stage.setScene(new Scene(rootMenu));
   			stage.show();
   			Stage stage1 = (Stage) botonMenu.getScene().getWindow();
			stage1.close();
   			stage.setTitle("Menú");
   		} catch(Exception e) {
   			e.printStackTrace();
   		}
	} else if(userLogged.rol_tipo.equals("Enfermero") || userLogged.rol_tipo.equals("enfermero") || userLogged.rol_tipo.equals("Enfermera") || userLogged.rol_tipo.equals("enfermera")) {
		try {
   			FXMLLoader loaderMenu = new FXMLLoader(getClass().getResource("/view/MenuMedico_View.fxml"));
   			
   			MenuMedico controlMenu = new MenuMedico();
    			
   			loaderMenu.setController(controlMenu);
   			
   			controlMenu.setUserLogged(userLogged);

    		Parent rootMenu = loaderMenu.load();
    			
    		Stage stage = new Stage();
    			
   			stage.setScene(new Scene(rootMenu));
   			stage.show();
   			Stage stage1 = (Stage) botonMenu.getScene().getWindow();
			stage1.close();
   			stage.setTitle("Registro Constantes");
   		} catch(Exception e) {
   			e.printStackTrace();
   		}
	}
}
}
