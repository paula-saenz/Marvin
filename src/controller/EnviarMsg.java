package controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Usuario;

	public class EnviarMsg implements Initializable {
		
		static final String USER = "pri_easyliving";
	    static final String PASS = "MixiMixi";

	    @FXML
	    private Label nombreUser;

	    @FXML
	    private Label rolUser;

	    @FXML
	    private Button buttonSubMenu;

	    @FXML
	    private Button botonMenu;

	    @FXML
	    private TextField inputEmail;

	    @FXML
	    private TextField inputMensaje;

	    @FXML
	    private Button botonEnviar;
	    
	    private Usuario userLogged;
	    
	    public void setUserLogged(Usuario userLogged) {
	        this.userLogged = userLogged;
	    }

	    public void initialize(URL location, ResourceBundle resources) {
	     nombreUser.setText(userLogged.nombre);
	     rolUser.setText(userLogged.rol_tipo);
	     }

	    @FXML
	    void enviarMensaje(ActionEvent event) {

	    	Connection conn = null;
	        Statement stmt = null;
	        String sql, sql2;
	        
	        try{
	        	Class.forName("org.mariadb.jdbc.Driver");

	            System.out.println("Connecting to a selected database...");

	            conn = DriverManager.getConnection(
	                    "jdbc:mariadb://195.235.211.197\r\n"
	                    + "/prieasyliving", USER, PASS);
	            
	            System.out.println("Connectado a la Base de Datos...");
	            
	            sql = "SELECT * FROM Usuarios";
	            stmt = conn.createStatement();
	            ResultSet rs = stmt.executeQuery( sql );
	            
	            while (rs.next()) {
	            	int id = rs.getInt("id");
					String mail = rs.getString("mail");
	                
	                if(inputEmail.getText().equals(mail)) {
	                	String mensaje = inputMensaje.getText().toString();
	                	
	                	sql2 = "INSERT INTO Mensajes(id_emitido, id_recibido, mensaje)"
	                			+ "VALUES('"+userLogged.id+"', '"+id+"', '"+mensaje+"');";
	                	stmt = conn.createStatement();
	                	stmt.executeUpdate(sql2);
	                    stmt.close();
	                } else {
	                	System.out.println("No puedes");
	                }
	            }
	            
	            rs.close();
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
				FXMLLoader loader_Vermensajeria = new FXMLLoader(getClass().getResource("/view/VerMensajeria_View.fxml"));
				
				VerMensajes controler_VerMensajeria = new VerMensajes();
				
				loader_Vermensajeria.setController(controler_VerMensajeria);
				
				controler_VerMensajeria.setUserLogged(userLogged);
				
				Parent root_Vermensajeria = loader_Vermensajeria.load();
				
				Stage stage = new Stage();
				
				stage.setScene(new Scene(root_Vermensajeria));
				stage.show();
				Stage stage1 = (Stage) botonEnviar.getScene().getWindow();
				stage1.close();
				stage.setTitle("Ver Mensajería");
			} catch(Exception e) {
				e.printStackTrace();
			}
	    }

	    @FXML
	    void llevaSubMenu(ActionEvent event) {
	    	try {
	   			FXMLLoader loaderCoche = new FXMLLoader(getClass().getResource("/view/Submenu_View.fxml"));
	   			
	   			Submenu controlCoche = new Submenu();
	    			
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



