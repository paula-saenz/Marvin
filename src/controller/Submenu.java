package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import model.Usuario;


public class Submenu {
	@FXML
	 private Button logOut;
	 
	 @FXML
	 private Button volverinicio;
	 
	 @FXML
	 private Button configuracion;

	 private Usuario userLogged;
	 
	 public void setUserLogged(Usuario userLogged) {
		 this.userLogged = userLogged;
	 }
	 
	 @FXML
	 void  funcion_configuracion(ActionEvent event) {
	    	//si damos al boton configuracion, irnos a la interfaz de Ajustes de usuario

	    	try {
				FXMLLoader LoaderInformacionUsuario = new FXMLLoader(getClass().getResource("/view/InformacionUsuario_View.fxml"));			
				
				InformacionUsuario controlInformacionUsuario = new InformacionUsuario();
		        
				LoaderInformacionUsuario.setController(controlInformacionUsuario);
				
				controlInformacionUsuario.setUserLogged(userLogged);
				
		        Parent rootLogin = LoaderInformacionUsuario.load();
				
				Stage stage = new Stage();			
				
				stage.setScene(new Scene(rootLogin));
				stage.show();
				Stage stage1 = (Stage) configuracion.getScene().getWindow();
				stage1.close();
	   			stage.setTitle("Informacion Usuario");
			} catch(Exception e) {
				e.printStackTrace();
			}

	 	}
	 
	 @FXML
	 void funcion_logOut(ActionEvent event) {
	 	   	try {
				FXMLLoader loaderLogin = new FXMLLoader(getClass().getResource("/view/Login_View.fxml"));
				
				Login controlLogin = new Login();
				
				loaderLogin.setController(controlLogin);

				Parent rootLogin = loaderLogin.load();
				
				Stage stage = new Stage();
				
				stage.setScene(new Scene(rootLogin));
				stage.show();
				Stage stage1 = (Stage) logOut.getScene().getWindow();
				stage1.close();
				stage.setTitle("Login");
			} catch(Exception e) {
				e.printStackTrace();
			}
	 
	    }

	    @FXML
	    void funcionVolverInicio(ActionEvent event) {	    	
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
		   			Stage stage1 = (Stage) volverinicio.getScene().getWindow();
					stage1.close();
		   		} catch(Exception e) {
		   			e.printStackTrace();
		   		}
	    	} else if(userLogged.rol_tipo.equals("Tecnico") || userLogged.rol_tipo.equals("tecnico") || userLogged.rol_tipo.equals("Tecnica") || userLogged.rol_tipo.equals("tecnica")){
	    		try {
		   			FXMLLoader loaderMenu = new FXMLLoader(getClass().getResource("/view/MenuTecnico_View.fxml"));
		   			
		   			MenuTecnico controlMenu = new MenuTecnico();
		    			
		   			loaderMenu.setController(controlMenu);
		   			
		   			controlMenu.setUserLogged(userLogged);

		    		Parent rootMenu = loaderMenu.load();
		    			
		    		Stage stage = new Stage();
		    			
		   			stage.setScene(new Scene(rootMenu));
		   			stage.show();
		   			Stage stage1 = (Stage) volverinicio.getScene().getWindow();
					stage1.close();
		   		} catch(Exception e) {
		   			e.printStackTrace();
		   		}
	    	} else if(userLogged.rol_tipo.equals("Enfermero") || userLogged.rol_tipo.equals("enfermero") || userLogged.rol_tipo.equals("Enfermera") || userLogged.rol_tipo.equals("enfermera")){
	    		try {
		   			FXMLLoader loaderMenu = new FXMLLoader(getClass().getResource("/view/MenuMedico_View.fxml"));
		   			
		   			MenuMedico controlMenu = new MenuMedico();
		    			
		   			loaderMenu.setController(controlMenu);
		   			
		   			controlMenu.setUserLogged(userLogged);

		    		Parent rootMenu = loaderMenu.load();
		    			
		    		Stage stage = new Stage();
		    			
		   			stage.setScene(new Scene(rootMenu));
		   			stage.show();
		   			Stage stage1 = (Stage) volverinicio.getScene().getWindow();
					stage1.close();
		   		} catch(Exception e) {
		   			e.printStackTrace();
		   		}
	    	}
	    }
	}
