package controller;

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

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.SQLException;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Register {
	
	static final String USER = "pri_easyliving";
    static final String PASS = "MixiMixi";

	 	@FXML
	    private Label errorMessage;

	    @FXML
	    private Button botonAcabar;

	    @FXML
	    private PasswordField inputPasswordAgain;

	    @FXML
	    private PasswordField inputPassword;

	    @FXML
	    private TextField inputMail;

	    @FXML
	    private TextField inputNombre;

	    @FXML
	    private TextField inputApellido;

	    @FXML
	    private TextField inputPhone;

	    @FXML
	    private TextField inputRol;

    @FXML
    void finalRegister(ActionEvent event) {
    	Connection conn = null;
        Statement stmt = null;
        String sql;
    	
    	try {
    		if(inputNombre.getText().isEmpty()|| inputApellido.getText().isEmpty() || inputRol.getText().isEmpty() || inputMail.getText().isEmpty()
        			|| inputPassword.getText().isEmpty() || inputPasswordAgain.getText().isEmpty() || inputPhone.getText().isEmpty()) {
        		errorMessage.setText("Por favor introduzca sus datos");
        	} else {
        		Class.forName("org.mariadb.jdbc.Driver");

                System.out.println("Connecting to a selected database...");

                conn = DriverManager.getConnection(
                        "jdbc:mariadb://195.235.211.197\r\n"
                        + "/prieasyliving", USER, PASS);
                
                System.out.println("Connectado a la Base de Datos...");
                
                String nombre = inputNombre.getText().toString();
                String apellidos = inputApellido.getText().toString();
                String rol_tipo = inputRol.getText().toString();
                String mail = inputMail.getText().toString();
                String password = inputPassword.getText().toString();
                String encryptedPassword = getMD5Hash(password);
                String phone = inputPhone.getText().toString();
                
                sql = "Insert into usuarios(nombre, apellidos, rol_tipo, mail, password, phone)"
                		+ "values('"+nombre+"', '"+apellidos+"', '"+rol_tipo+"', '"+mail+"', '"+encryptedPassword+"', '"+phone+"');";
                System.out.println(sql);
                stmt = conn.createStatement();
                stmt.executeUpdate(sql);
                stmt.close();
        		
        		try {
           			FXMLLoader loaderLogin = new FXMLLoader(getClass().getResource("/view/Login_View.fxml"));
           			
           			Login controlLogin = new Login();
            			
           			loaderLogin.setController(controlLogin);

            		Parent rootMenu = loaderLogin.load();
            			
            		Stage stage = new Stage();
            			
           			stage.setScene(new Scene(rootMenu));
           			stage.show();
           			Stage stage1 = (Stage) botonAcabar.getScene().getWindow();
        			stage1.close();
           			stage.setTitle("Login");
           		} catch(Exception e) {
           			e.printStackTrace();
           		}
        	}
			
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