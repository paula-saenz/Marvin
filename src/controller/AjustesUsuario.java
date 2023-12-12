package controller;

import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
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
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AjustesUsuario implements Initializable {

	static final String USER = "pri_easyliving";
    static final String PASS = "MixiMixi";

	@FXML
	private TextField TextNombre;

	@FXML
	private TextField TextApellido;

	@FXML
	private TextField TextTelefono;

	@FXML
	private TextField TextEmail;

	@FXML
	private TextField TextContrasennia;

	@FXML
	private Button guardarCambios;

	@FXML
	private Label rolUser;

	@FXML
	private Button buttonSubMenu;
	@FXML
	private Label errorLabel;

	@FXML
	private Label nombreUser;
	
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
		
		Connection conn = null;
        Statement stmt = null;
        String sql;
		
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
			while ( rs.next() ) {
				int idbbdd = rs.getInt("id");
				String nombrebbdd = rs.getString("nombre");
				String apellidosbbdd = rs.getString("apellidos");
				String  mailbbdd = rs.getString("mail");
				String  phonebbdd = rs.getString("phone");
				
				if(idbbdd == userLogged.id) {
					TextNombre.setText(nombrebbdd);
					TextApellido.setText(apellidosbbdd);
					TextEmail.setText(mailbbdd);
					TextTelefono.setText(phonebbdd);
					
					TextNombre.setEditable(true);
				    TextApellido.setEditable(true);
				    TextEmail.setEditable(true);
				    TextContrasennia.setEditable(true);
				    TextTelefono.setEditable(true);
				    
			
				}
				  
			}
			rs.close(); 
			stmt = conn.createStatement();
            stmt.executeUpdate(sql);  
            stmt.close();
    		conn.close();
        
    	}catch (SQLException se) {
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
	void funcion_guardarcambios(ActionEvent event) {
		Connection conn = null;
        Statement stmt = null;
        String sql;
     
        if ((TextNombre.getText().isEmpty()|| TextApellido.getText().isEmpty() 
        		|| TextTelefono.getText().isEmpty() || TextEmail.getText().isEmpty()
        		|| TextContrasennia.getText().isEmpty())) { 
        		errorLabel.setText("Por favor rellene todos los campos");
        }else {
    	try {
        Class.forName("org.mariadb.jdbc.Driver");

        System.out.println("Connecting to a selected database...");

        conn = DriverManager.getConnection(
                "jdbc:mariadb://195.235.211.197\r\n"
                + "/prieasyliving", USER, PASS);
        
        System.out.println("Connectado a la Base de Datos...");
       
        
		int id = userLogged.id;
		String nombre = TextNombre.getText().toString();
        String apellidos = TextApellido.getText().toString();
        String mail = TextEmail.getText().toString();
        String password = TextContrasennia.getText().toString();
        String encryptedPassword = getMD5Hash(password);
        String phone = TextTelefono.getText().toString();
        
        sql = "UPDATE usuarios  Set nombre = '"+nombre+"', apellidos ='"+apellidos+"' , mail ='"+mail+"' , "
        		+ "password ='"+encryptedPassword+"', phone = '"+phone+"'  WHERE id = '"+id+"' ";
              
            stmt = conn.createStatement();
            stmt.executeUpdate(sql);  
            stmt.close();
    		conn.close();
        
    	}catch (SQLException se) {
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
				FXMLLoader LoaderInformacionUsuario = new FXMLLoader(getClass().getResource("/view/InformacionUsuario_View.fxml"));			
				
				InformacionUsuario controlInformacionUsuario = new InformacionUsuario();
		        
				LoaderInformacionUsuario.setController(controlInformacionUsuario);
				
				controlInformacionUsuario.setUserLogged(userLogged);
				
		        Parent rootLogin = LoaderInformacionUsuario.load();
				
				Stage stage = new Stage();			
				
				stage.setScene(new Scene(rootLogin));
				stage.show();
				Stage stage1 = (Stage) guardarCambios.getScene().getWindow();
				stage1.close();
	   			stage.setTitle("Informacion Usuario");
			} catch(Exception e) {
				e.printStackTrace();
			}
        }
	}
	

	// cambio de pantalla exclusivamente
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
		} catch (Exception e) {
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
