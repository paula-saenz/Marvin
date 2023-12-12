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
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import model.Usuario;

public class Rutas_Guardadas implements Initializable{
	
	 @FXML
	    private Button buttonSubMenu;

	    @FXML
	    private Label nombreUser;

	    @FXML
	    private Label rolUser;

	    @FXML
	    private Button ButtonMasRutas;
	    
	    @FXML
	    private Button botonMenu;
	    
	    private Usuario userLogged;
	    
	    public void setUserLogged(Usuario userLogged) {
	        this.userLogged = userLogged;
	    }
	    
	    @Override
	    public void initialize(URL location, ResourceBundle resources) {
	    	nombreUser.setText(userLogged.nombre);
	    	rolUser.setText(userLogged.rol_tipo);
	    }

	    @FXML
	    void llevaSubMenu(ActionEvent event) {
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
			} catch(Exception e) {
				e.printStackTrace();
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



