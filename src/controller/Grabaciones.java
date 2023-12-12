package controller;

import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
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
import model.Usuario;
import model.SensorBinario;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Grabaciones implements Initializable{
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
    
    private int posicion = 0;
    
    private Usuario userLogged;
    
    public void setUserLogged(Usuario userLogged) {
        this.userLogged = userLogged;
    }
    
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    	nombreUser.setText(userLogged.nombre);
    	rolUser.setText(userLogged.rol_tipo);
    	
    	try {
			Class.forName("org.mariadb.jdbc.Driver");
	    } catch (ClassNotFoundException se) {
	        //Handle errors for JDBC
	        se.printStackTrace();
	    }//end try
    	
        Connection conn = null;
        Statement stmt = null;
        String sqlQuery;
		FileInputStream fis = null;
		PreparedStatement ps = null;
        
        try {
        	conn = DriverManager.getConnection("jdbc:mariadb://195.235.211.197/prieasyliving", USER, PASS);

    		sqlQuery = "insert into sensoresbinarios(voz) values ( ?)";
			conn.setAutoCommit(false);
			File archivo = new File("\\grabaciones\\1-welcome.wav");
			//cambiar ruta absooluta por relativa
			fis = new FileInputStream(archivo);
			ps = conn.prepareStatement(sqlQuery);
			ps.setBinaryStream(1, fis, (int) archivo.length());
			ps.executeUpdate();
			conn.commit();
			ps.close();
			fis.close();
        } catch (Exception se) {
            se.printStackTrace();
        }
	}
    
    @FXML
    void enseñarDatos(ActionEvent event) {
    	Connection conn = null;
        Statement stmt = null;
        String sql;
    	
    	if(userLogged.rol_tipo.equals("Conductor") || userLogged.rol_tipo.equals("conductor") || userLogged.rol_tipo.equals("Conductora") || userLogged.rol_tipo.equals("conductora")) {
        	Vector<SensorBinario> datosVector = new Vector<SensorBinario>();
        	
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
	                
	                sql = "SELECT SENSORBINARIO.id, SENSORBINARIO.usuarios_id, SENSORBINARIO.nivel, SENSORBINARIO.fecha, USUARIOS.nombre "
	                		+ "FROM SENSORBINARIO  INNER JOIN SENSORBINARIO ON SENSORBINARIO.usuarios = USUARIOS.id";
	                stmt = conn.createStatement();
	    			ResultSet rs = stmt.executeQuery( sql );
	    			while (rs.next()) {
	    				int id = rs.getInt("id");
	    				int usuarios_id = rs.getInt("usuarios_id");
	    				String voz = rs.getString("voz");
	    				Date  fecha = rs.getDate("fecha");
	    				String nombre = rs.getString("nombre");
	    				
	    				errorFecha.setText("");
		        		
		        		if(inputFecha.getText().equals(fecha) && nombreUser.getText().equals(nombre)) {
		        			errorFecha.setText("");
		        			
		        			SensorBinario user = new SensorBinario(id, nombre, usuarios_id, voz, fecha);
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
                outputDatos.setText("Grabación de " +datosVector.get(posicion).nombre+ ": " +datosVector.get(posicion).voz+ "\n");
        	}
        	
        	if(event.getSource() == botonSiguiente) {
        		posicion++;
        		if(posicion == datosVector.size()) {
        			errorFecha.setText("Ya no hay más datos");
        		} else {
        			outputDatos.setText("Grabación de " +datosVector.get(posicion).nombre+ ": " +datosVector.get(posicion).voz+ "\n");
        		}
        	}
        	
        	if(event.getSource() == botonAntes) {
        		posicion--;
        		if(posicion == -1) {
        			errorFecha.setText("Ya no hay más datos");
        		} else {
        			outputDatos.setText("Grabación de " +datosVector.get(posicion).nombre+ ": " +datosVector.get(posicion).voz+ "\n");
        		}
        	}
    	} else{
        	Vector<SensorBinario> datosVector = new Vector<SensorBinario>();
        	
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
	                
	                sql = "SELECT SENSORBINARIO.id, SENSORBINARIO.usuarios_id, SENSORBINARIO.nivel, SENSORBINARIO.fecha, USUARIOS.nombre "
	                		+ "FROM SENSORBINARIO  INNER JOIN SENSORBINARIO ON SENSORBINARIO.usuarios = USUARIOS.id";
	                stmt = conn.createStatement();
	    			ResultSet rs = stmt.executeQuery( sql );
	    			while (rs.next()) {
	    				int id = rs.getInt("id");
	    				int usuarios_id = rs.getInt("usuarios_id");
	    				String voz = rs.getString("voz");
	    				Date  fecha = rs.getDate("fecha");
	    				String nombre = rs.getString("nombre");
	    				
	    				errorFecha.setText("");
		        		
		        		if(inputFecha.getText().equals(fecha) && nombreUser.getText().equals(nombre)) {
		        			errorFecha.setText("");
		        			
		        			SensorBinario user = new SensorBinario(id, nombre, usuarios_id, voz, fecha);
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
                outputDatos.setText("Grabación de " +datosVector.get(posicion).nombre+ ": " +datosVector.get(posicion).voz+ "\n");
        	}
        	
        	if(event.getSource() == botonSiguiente) {
        		posicion++;
        		if(posicion == datosVector.size()) {
        			errorFecha.setText("Ya no hay más datos");
        		} else {
        			outputDatos.setText("Grabación de " +datosVector.get(posicion).nombre+ ": " +datosVector.get(posicion).voz+ "\n");
        		}
        	}
        	
        	if(event.getSource() == botonAntes) {
        		posicion--;
        		if(posicion == -1) {
        			errorFecha.setText("Ya no hay más datos");
        		} else {
        			outputDatos.setText("Grabación de " +datosVector.get(posicion).nombre+ ": " +datosVector.get(posicion).voz+ "\n");
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
