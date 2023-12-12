package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import model.Usuario;

public class InformacionUsuario implements Initializable {
	
	static final String USER = "pri_easyliving";
    static final String PASS = "MixiMixi";
	
    @FXML
    private Button boton_editar;

    @FXML
    private Label infoNombre;

    @FXML
    private Label infoApellido;

    @FXML
    private Label infoTelefono;

    @FXML
    private Label infoMail;

    @FXML
    private PasswordField infoContrasennia;

    @FXML
    private Button buttonSubMenu;

    @FXML
    private Label nombreUser;
    
    @FXML
    private Label rolUser;
    
    @FXML
    private Button botonMenu;
    
    private Usuario userLogged;
   
    public void setUserLogged(Usuario userLogged) {
        this.userLogged = userLogged;
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
     	Connection conn = null;
        Statement stmt = null;
        String sql;
        
    	if(userLogged.rol_tipo.equals("Conductor") || userLogged.rol_tipo.equals("conductor") || userLogged.rol_tipo.equals("Conductora") || userLogged.rol_tipo.equals("conductora")) {
	    	
    		nombreUser.setText(userLogged.nombre);
        	rolUser.setText(userLogged.rol_tipo);
        	
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
    				int id = rs.getInt("id");
    				String  nombre = rs.getString("nombre");
    				String  apellidos = rs.getString("apellidos");
    				String  mail = rs.getString("mail");
    				String  password = rs.getString("password");
    				String  phone = rs.getString("phone");
    				
    				if(id == userLogged.id) {
    					infoNombre.setText(nombre);
    					infoApellido.setText(apellidos);
    					infoMail.setText(mail);
    					infoContrasennia.setText(password);
    					infoTelefono.setText(phone);
    					
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
        	
    	} else if (userLogged.rol_tipo.equals("Tecnico") || userLogged.rol_tipo.equals("tecnico") || userLogged.rol_tipo.equals("Tecnica") || userLogged.rol_tipo.equals("tecnica")) {
    		nombreUser.setText(userLogged.nombre);
        	rolUser.setText(userLogged.rol_tipo);
    		
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
				int id = rs.getInt("id");
				String  nombre = rs.getString("nombre");
				String  apellidos = rs.getString("apellidos");
				String  mail = rs.getString("mail");
				String  password = rs.getString("password");
				String  phone = rs.getString("phone");
				
				if(id == userLogged.id) {
					infoNombre.setText(nombre);
					infoApellido.setText(apellidos);
					infoMail.setText(mail);
					infoContrasennia.setText(password);
					infoTelefono.setText(phone);
					
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
    else {
    	nombreUser.setText(userLogged.nombre);
    	rolUser.setText(userLogged.rol_tipo);
		
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
			int id = rs.getInt("id");
			String  nombre = rs.getString("nombre");
			String  apellidos = rs.getString("apellidos");
			String  mail = rs.getString("mail");
			String  password = rs.getString("password");
			String  phone = rs.getString("phone");
			
			if(id == userLogged.id) {
				infoNombre.setText(nombre);
				infoApellido.setText(apellidos);
				infoMail.setText(mail);
				infoContrasennia.setText(password);
				infoTelefono.setText(phone);
				
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
    void funcion_editar(ActionEvent event) {
    	if(userLogged.rol_tipo.equals("Conductor") || userLogged.rol_tipo.equals("conductor") || userLogged.rol_tipo.equals("Conductora") || userLogged.rol_tipo.equals("conductora")) {
    		try {
    			FXMLLoader Loader_AjustesUsuario = new FXMLLoader(getClass().getResource("/view/ajustesUsuario_View.fxml"));
    			
    			AjustesUsuario controlAjustesUsuario = new AjustesUsuario();
    			
    			Loader_AjustesUsuario.setController(controlAjustesUsuario);
    			
    			controlAjustesUsuario.setUserLogged(userLogged);
    			
    			Parent rootLogin = Loader_AjustesUsuario.load();
    			
    			Stage stage = new Stage();
    			
    			stage.setScene(new Scene(rootLogin));
    			stage.show();
    			Stage stage1 = (Stage) boton_editar.getScene().getWindow();
    			stage1.close();
    		} catch(Exception e) {
    			e.printStackTrace();
    		}
    	}
    }
    
    @FXML
    void llevaSubMenu(ActionEvent event) {
    	if(userLogged.rol_tipo.equals("Conductor") || userLogged.rol_tipo.equals("conductor") || userLogged.rol_tipo.equals("Conductora") || userLogged.rol_tipo.equals("conductora")) {
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
