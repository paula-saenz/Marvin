package controller;

import javafx.scene.control.Label;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Usuario;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.SQLException;

public class EditarCoche implements Initializable{
	static final String USER = "pri_easyliving";
    static final String PASS = "MixiMixi";
	
    @FXML
    private TextField inputMatricula;

    @FXML
    private TextField inputModelo;

    @FXML
    private TextField inputAnio;
    
    @FXML
    private Button botonConfirmar;
    
    @FXML
    private Label errorInfo;
    
    @FXML
    private Label nombreUser;

    @FXML
    private Label rolUser;
    
    @FXML
    private Button buttonSubMenu;
    
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
    void confirmDatos(ActionEvent event) {
    	Connection conn = null;
        Statement stmt = null;
        String sql;
    	
    	if(inputMatricula.getText().isEmpty() || inputModelo.getText().isEmpty() || inputAnio.getText().isEmpty()) {
    		errorInfo.setText("Por favor introduzca sus datos");
    	} else {
    		try {
            	Class.forName("org.mariadb.jdbc.Driver");

                System.out.println("Connecting to a selected database...");

                conn = DriverManager.getConnection(
                        "jdbc:mariadb://195.235.211.197\r\n"
                        + "/prieasyliving", USER, PASS);
                
                System.out.println("Connectado a la Base de Datos...");
                
                int id = userLogged.id;
                String matricula = inputMatricula.getText().toString();
                String modelo = inputModelo.getText().toString();
                String anio = inputAnio.getText().toString();
                
                sql = "UPDATE cocheInfo "
                		+ "SET id = '"+id+"',  matricula = '"+matricula+"', modelo = '"+modelo+"', anio = '"+anio+"'"
                				+ "WHERE id = '"+id+"'";
                stmt = conn.createStatement();
                stmt.executeUpdate(sql);  
                stmt.close();
    			
    			conn.close();

            } catch (SQLException se) {
                se.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (stmt != null) {
                        conn.close();
                    }
                } catch (SQLException se) {
                }
                try {
                    if (conn != null) {
                        conn.close();
                    }
                } catch (SQLException se) {
                    se.printStackTrace();
                }
            }
    		
    		try {
       			FXMLLoader loaderCoche = new FXMLLoader(getClass().getResource("/view/InfoCoche_View.fxml"));
       			
       			InfoCoche controlCoche = new InfoCoche();
        			
       			loaderCoche.setController(controlCoche);
       			
       			controlCoche.setUserLogged(userLogged);

        		Parent rootCoche = loaderCoche.load();
        			
        		Stage stage = new Stage();
        			
       			stage.setScene(new Scene(rootCoche));
       			stage.show();
       			Stage stage1 = (Stage) botonConfirmar.getScene().getWindow();
    			stage1.close();
       			stage.setTitle("Información del Coche");
       		} catch(Exception e) {
       			e.printStackTrace();
       		}
    	}
    }
    
    @FXML
    void llevaSubMenu(ActionEvent event) {
    	try {
   			FXMLLoader loaderCoche = new FXMLLoader(getClass().getResource("/view/Submenu_View.fxml"));
   			
   			InfoCoche controlCoche = new InfoCoche();
    			
   			loaderCoche.setController(controlCoche);
   			
   			controlCoche.setUserLogged(userLogged);

    		Parent rootCoche = loaderCoche.load();
    			
    		Stage stage = new Stage();
    			
   			stage.setScene(new Scene(rootCoche));
   			stage.show();
   			Stage stage1 = (Stage) buttonSubMenu.getScene().getWindow();
			stage1.close();
   			stage.setTitle("Submenú");
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