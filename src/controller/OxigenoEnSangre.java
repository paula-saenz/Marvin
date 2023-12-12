package controller;

import javafx.scene.control.Button;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Vector;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Usuario;
import model.SensorNoBinario;

import java.sql.Date;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OxigenoEnSangre implements Initializable{
	static final String USER = "pri_easyliving";
    static final String PASS = "MixiMixi";
    
    @FXML
    private Button buttonSubMenu;
	
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
    private Label nombreUser;

    @FXML
    private Label rolUser;

    @FXML
    private Button botonMenu;
    
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
		
		if(userLogged.rol_tipo.equals("Conductor")  || userLogged.rol_tipo.equals("conductor") || userLogged.rol_tipo.equals("Conductora") || userLogged.rol_tipo.equals("conductora")) {
	    	Vector<SensorNoBinario> datosVector = new Vector<SensorNoBinario>();
	    	
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
	                
	                sql = "SELECT sen.id, sen.usuarios_id, sen.nivelhb, sen.nivelsp02, sen.fecha, u.nombre "
	                		+ "FROM sensoresnobinarios AS sen "
	                		+ "INNER JOIN usuarios AS u ON sen.usuarios_id = u.id ";
	                stmt = conn.createStatement();
	    			ResultSet rs = stmt.executeQuery( sql );
	    			while (rs.next()) {
	    				int id = rs.getInt("id");
	    				int usuarios_id = rs.getInt("usuarios_id");
	    				float nivelhb = rs.getInt("nivelhb");
	    				float nivelsp02 = rs.getInt("nivelsp02");
	    				Date  fecha = rs.getDate("fecha");
	    				String fechaString = fecha.toString();
	    				String nombre = rs.getString("nombre");
	    				
	    				errorFecha.setText("");
		        		
		        		if(inputFecha.getText().equals(fechaString) && usuarios_id == userLogged.id) {
		        			errorFecha.setText("");
		        			
		        			SensorNoBinario user = new SensorNoBinario(id, nombre, usuarios_id, nivelhb, nivelsp02, fecha);
		        			datosVector.add(user);
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
	    	
	    	
	    	if(event.getSource() == botonFecha) {
	            outputDatos.setText("Datos de " +datosVector.get(posicion).nombre+ ": " +datosVector.get(posicion).nivelsp02+ "\n");
	    	}
	    	
	    	if(event.getSource() == botonSiguiente) {
	    		posicion++;
	    		if(posicion == datosVector.size()) {
	    			errorFecha.setText("Ya no hay más datos");
	    		} else {
	    			outputDatos.setText("Datos de " +datosVector.get(posicion).nombre+ ": " +datosVector.get(posicion).nivelsp02+ "\n");
	    		}
	    	}
	    	
	    	if(event.getSource() == botonAntes) {
	    		posicion--;
	    		if(posicion == -1) {
	    			errorFecha.setText("Ya no hay más datos");
	    		} else {
	    			outputDatos.setText("Datos de " +datosVector.get(posicion).nombre+ ": " +datosVector.get(posicion).nivelsp02+ "\n");
	    		}
	    	}
		} else {
	    	Vector<SensorNoBinario> datosVector = new Vector<SensorNoBinario>();
	    	
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
	                
	                sql = "SELECT sen.id, sen.usuarios_id, sen.nivelhb, sen.nivelsp02, sen.fecha, u.nombre "
	                		+ "FROM sensoresnobinarios AS sen "
	                		+ "INNER JOIN usuarios AS u ON sen.usuarios_id = u.id ";
	                stmt = conn.createStatement();
	    			ResultSet rs = stmt.executeQuery( sql );
	    			while (rs.next()) {
	    				int id = rs.getInt("id");
	    				int usuarios_id = rs.getInt("usuarios_id");
	    				float nivelhb = rs.getInt("nivelhb");
	    				float nivelsp02 = rs.getInt("nivelsp02");
	    				Date  fecha = rs.getDate("fecha");
	    				String fechaString = fecha.toString();
	    				String nombre = rs.getString("nombre");
	    				
	    				errorFecha.setText("");
		        		
		        		if(inputFecha.getText().equals(fechaString)) {
		        			SensorNoBinario user2 = new SensorNoBinario(id, nombre, usuarios_id, nivelhb, nivelsp02, fecha);
		        			datosVector.add(user2);
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
	    	
	    	if(event.getSource() == botonFecha) {
	            outputDatos.setText("Datos de " +datosVector.get(posicion).nombre+ ": " +datosVector.get(posicion).nivelsp02+ "\n");
	    	}
	    	
	    	if(event.getSource() == botonSiguiente) {
	    		posicion++;
	    		if(posicion == datosVector.size()) {
	    			errorFecha.setText("Ya no hay más datos");
	    		} else {
	    			outputDatos.setText("Datos de " +datosVector.get(posicion).nombre+ ": " +datosVector.get(posicion).nivelsp02+ "\n");
	    		}
	    	}
	    	
	    	if(event.getSource() == botonAntes) {
	    		posicion--;
	    		if(posicion == -1) {
	    			errorFecha.setText("Ya no hay más datos");
	    		} else {
	    			outputDatos.setText("Datos de " +datosVector.get(posicion).nombre+ ": " +datosVector.get(posicion).nivelsp02+ "\n");
	    		}
	    	}
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
    	try {
			FXMLLoader loaderFechaITV = new FXMLLoader(getClass().getClass().getResource("/view/RegistroConstantes_View.fxml"));
			
			RegistroConstantes controlerFechaITV = new RegistroConstantes();
			
			loaderFechaITV.setController(controlerFechaITV);
			
			controlerFechaITV.setUserLogged(userLogged);

			Parent rootFechaITV = loaderFechaITV.load();
			
			Stage stage = new Stage();
			
			stage.setScene(new Scene(rootFechaITV));
			stage.show();
			Stage stage1 = (Stage) botonMenu.getScene().getWindow();
			stage1.close();
			stage.setTitle("Registro de Constantes");
		} catch(Exception e) {
			e.printStackTrace();
		}
    }
	
}
