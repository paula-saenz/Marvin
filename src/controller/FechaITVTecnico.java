package controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.Vector;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.CocheInfo;
import model.Usuario;

public class FechaITVTecnico implements Initializable {

	static final String USER = "pri_easyliving";
	static final String PASS = "MixiMixi";

	@FXML
	private Button buttonSubMenu;

	@FXML
	private Label nombreUser;

	@FXML
	private Label rolUser;

	@FXML
	private Button botonMenu;

	@FXML
	private TextField inputFecha;

	@FXML
	private Button botonFecha;

	@FXML
	private Text outputDatos;

	@FXML
	private Button botonAntes;

	@FXML
	private Button botonSiguiente;

	@FXML
	private Label errorFecha;
	
	@FXML
    private Label TextTuProximaVisita;

	private Usuario userLogged;

	private int posicion = 0;

	public void setUserLogged(Usuario userLogged) {
		this.userLogged = userLogged;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		nombreUser.setText(userLogged.nombre);
		rolUser.setText(userLogged.rol_tipo);
	}

	@FXML
	void enseñarDatos(ActionEvent event) {
		Connection conn = null;
        Statement stmt = null;
        String sql;
        int usuario_activo= userLogged.id;


	    	Vector<CocheInfo> datosVector = new Vector<CocheInfo>();
	    	
	    	if(inputFecha.getText().isEmpty()) {
	    		errorFecha.setText("Por favor introduzca el dato");
	    	} else {
	    		try {
	            	Class.forName("org.mariadb.jdbc.Driver");

	                System.out.println("Connecting to a selected database...");

	                conn = DriverManager.getConnection(
	                        "jdbc:mariadb://195.235.211.197\r\n"
	                        + "/prieasyliving", USER, PASS);
	                
	                System.out.println("Connectado a la Base de Datos...");
	                
	                sql = "SELECT usuarios.id, usuarios.supervisor_id, usuarios.nombre, usuarios.apellidos, cocheinfo.matricula, cocheinfo.modelo, cocheinfo.anio "
	                		+ "FROM usuarios "
	                		+ "JOIN cocheinfo ON usuarios.id = cocheinfo.usuarios_id "
	                		+ "WHERE usuarios.supervisor_id =" + usuario_activo;
         
	                stmt = conn.createStatement();
	    			ResultSet rs = stmt.executeQuery( sql );
	    			while (rs.next()) {	    
	    				
	    				int id= rs.getInt("id");
	    				int supervisor_id=rs.getInt("supervisor_id");
	    				String nombre = rs.getString("nombre");
	    				String apellidos = rs.getString("apellidos");
	    				String matricula = rs.getString("matricula");
	    				String modelo = rs.getString("modelo");
	    				String annio_fab = rs.getString("anio");
	    				int annio_Coche=Integer.parseInt(annio_fab);
    			    	int annio_actual = 2023;

	    				errorFecha.setText("");
		        		if(inputFecha.getText().equals(nombre) && supervisor_id == userLogged.id) { 
		        			errorFecha.setText("");
		        					        			
		        			CocheInfo user = new CocheInfo(id, matricula, modelo, annio_fab);
		        			datosVector.add(user);
		        			
		        			if(event.getSource() == botonFecha) {
		        	            outputDatos.setText("El usuario: " +nombre+ " " + apellidos+" con coche: "+datosVector.get(posicion).modelo+ ", matricula: " +datosVector.get(posicion).matricula+ " y año: " +datosVector.get(posicion).anio);
		        	            if (annio_actual - annio_Coche < 4) {
	    		    		  		TextTuProximaVisita.setText( "Todavía no tiene que pasar la ITV, la primera es a los 4 años desde la fecha de la primera matriculación");
	    		    		  	  } else if (annio_actual - annio_Coche > 4 && annio_actual - annio_Coche < 10) {
	    		    		  		TextTuProximaVisita.setText("Tiene que pasar la ITV cada dos años después de su última revisión");
	    		    		  	  } else {
	    		    		  		TextTuProximaVisita.setText("Tiene que pasar la ITV anualmente después de su última revisión");
	    		    		  	  }
		        			}
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
	    	}
	    	
	    	
	    	
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
			stage.setTitle("Submenu");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	

	@FXML
	void volverMenu(ActionEvent event) {
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
	}

}
