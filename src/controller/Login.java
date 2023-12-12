package controller;

import model.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.fxml.Initializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Login {
	static final String USER = "pri_easyliving";
    static final String PASS = "MixiMixi";

    @FXML
    public PasswordField inputPassword;

    @FXML
    public TextField inputUsuario;

    @FXML
    private Button botonLogin;

    @FXML
    private Button botonRegistro;

    @FXML
    private Label wrongLogin;

    @FXML
    void userLogin(ActionEvent event) {
    	Connection conn = null;
        Statement stmt = null;
        String sql;
        
    	int encontrado = 0;
    	Usuario userLogged = null;
    	String passwordInput = inputPassword.getText();
    	String encryptedInputPassword = getMD5Hash(passwordInput);
    	
    	try {
        	Class.forName("org.mariadb.jdbc.Driver");

            System.out.println("Connecting to a selected database...");

            conn = DriverManager.getConnection(
                    "jdbc:mariadb://195.235.211.197\r\n"
                    + "/prieasyliving", USER, PASS);
            System.out.println("Connectado a la Base de Datos...");
            
            sql = "SELECT * FROM usuarios";
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery( sql );
            
            while (rs.next()) {
				int id = rs.getInt("id");
				String nombre = rs.getString("nombre");
				String apellidos = rs.getString("apellidos");
				String rol_tipo = rs.getString("rol_tipo");
				int supervisor_id = rs.getInt("supervisor_id");
				String mail = rs.getString("mail");
                String password = rs.getString("password");
                String phone = rs.getString("phone");
                
                if(inputUsuario.getText().equals(mail) && encryptedInputPassword.equals(password)) {
                	if(rol_tipo.equals("Conductor") || rol_tipo.equals("conductor") || rol_tipo.equals("Conductora") || rol_tipo.equals("conductora")) {
                		encontrado = 1;
            			Usuario user = new Usuario(id, nombre, apellidos, rol_tipo, supervisor_id, mail, password, phone);
            			userLogged = user;
                	} else if(rol_tipo.equals("Tecnico") || rol_tipo.equals("tecnico") || rol_tipo.equals("Tecnica") || rol_tipo.equals("tecnica")) {
                		encontrado = 2;
            			Usuario user = new Usuario(id, nombre, apellidos, rol_tipo, supervisor_id, mail, password, phone);
            			userLogged = user;
                	} else if(rol_tipo.equals("Enfermero") || rol_tipo.equals("enfermero") || rol_tipo.equals("Enfermera") || rol_tipo.equals("enfermera")) {
                		encontrado = 3;
            			Usuario user = new Usuario(id, nombre, apellidos, rol_tipo, supervisor_id, mail, password, phone);
            			userLogged = user;
                	}
        			
        		}
			}
			
			if(encontrado == 1) {
	    		
	    		try {
	       			FXMLLoader loaderMenu = new FXMLLoader(getClass().getResource("/view/Menu_View.fxml"));
	       			
	       			Menu controlMenu = new Menu();
	        			
	       			loaderMenu.setController(controlMenu);
	       			
	       			controlMenu.setUserLogged(userLogged);

	        		Parent rootMenu = loaderMenu.load();
	        			
	        		Stage stage = new Stage();
	        			
	       			stage.setScene(new Scene(rootMenu));
	       			stage.show();
	       			Stage stage1 = (Stage) botonLogin.getScene().getWindow();
	    			stage1.close();
	       			stage.setTitle("Menú");
	       		} catch(Exception e) {
	       			e.printStackTrace();
	       		}
	    	} else if(encontrado == 2) {
	    		
	    		try {
	       			FXMLLoader loaderMenu = new FXMLLoader(getClass().getResource("/view/MenuTecnico_View.fxml"));
	       			
	       			MenuTecnico controlMenu = new MenuTecnico();
	        			
	       			loaderMenu.setController(controlMenu);
	       			
	       			controlMenu.setUserLogged(userLogged);

	        		Parent rootMenu = loaderMenu.load();
	        			
	        		Stage stage = new Stage();
	        			
	       			stage.setScene(new Scene(rootMenu));
	       			stage.show();
	       			Stage stage1 = (Stage) botonLogin.getScene().getWindow();
	    			stage1.close();
	       			stage.setTitle("Menú");
	       		} catch(Exception e) {
	       			e.printStackTrace();
	       		}
	    	} else if(encontrado == 3) {
	    		try {
	       			FXMLLoader loaderMenu = new FXMLLoader(getClass().getResource("/view/MenuMedico_View.fxml"));
	       			
	       			MenuMedico controlMenu = new MenuMedico();
	        			
	       			loaderMenu.setController(controlMenu);
	       			
	       			controlMenu.setUserLogged(userLogged);

	        		Parent rootMenu = loaderMenu.load();
	        			
	        		Stage stage = new Stage();
	        			
	       			stage.setScene(new Scene(rootMenu));
	       			stage.show();
	       			Stage stage1 = (Stage) botonLogin.getScene().getWindow();
	    			stage1.close();
	       			stage.setTitle("Registro Constantes");
	       		} catch(Exception e) {
	       			e.printStackTrace();
	       		}
	    	} else if(inputUsuario.getText().isEmpty() || inputPassword.getText().isEmpty()) {
	       		wrongLogin.setText("Por favor introduzca sus datos");
	       	}
	       	else {
	       		wrongLogin.setText("Usuario o contraseña incorrecta");
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
    
    @FXML
    void userRegister(ActionEvent event) {
    	try {
			FXMLLoader loaderRegister = new FXMLLoader(getClass().getResource("/view/Register_View.fxml"));
			
			Register controlRegister = new Register();
			
			loaderRegister.setController(controlRegister);

			Parent rootRegister = loaderRegister.load();
			
			Stage stage = new Stage();
			
			stage.setScene(new Scene(rootRegister));
			stage.show();
			Stage stage1 = (Stage) botonLogin.getScene().getWindow();
			stage1.close();
			stage.setTitle("Registro");
		} catch(Exception e) {
			e.printStackTrace();
		}
    }
    
    public static String getMD5Hash(String input) {
    	try {
    		MessageDigest md = MessageDigest.getInstance("MD5");
    		md.update(input.getBytes());
    		byte[] digest = md.digest();
    		StringBuilder sb = new StringBuilder();
    		
    		for(byte b : digest) {
    			sb.append(String.format("%02x", b & 0xff));
    		}
    		return sb.toString();
    	} catch (NoSuchAlgorithmException e) {
    		e.printStackTrace();
    		return null;
    	}
    }
}
