package controller;

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
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Usuario;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InfoCoche implements Initializable{
	static final String USER = "pri_easyliving";
    static final String PASS = "MixiMixi";
	
	@FXML
    private Button botonEditar;

    @FXML
    private Label labelMatricula;

    @FXML
    private Label labelModelo;

    @FXML
    private Label labelAnio;

    @FXML
    private Label nombreUser;

    @FXML
    private Label rolUser;
    
    @FXML
    private Button buttonSubMenu;
    
    @FXML
    private TextField inputConductor;

    @FXML
    private Button botonVerConductor;
    
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
        nombreUser.setText(userLogged.nombre);
    	rolUser.setText(userLogged.rol_tipo);
        
    	if(userLogged.rol_tipo.equals("Conductor") || userLogged.rol_tipo.equals("conductor") || userLogged.rol_tipo.equals("Conductora") || userLogged.rol_tipo.equals("conductora")) {
        	try {
            	Class.forName("org.mariadb.jdbc.Driver");

                System.out.println("Connecting to a selected database...");

                conn = DriverManager.getConnection(
                        "jdbc:mariadb://195.235.211.197\r\n"
                        + "/prieasyliving", USER, PASS);
                
                System.out.println("Connectado a la Base de Datos...");
                
                sql = "SELECT * FROM cocheinfo";
                stmt = conn.createStatement();
    			ResultSet rs = stmt.executeQuery( sql );
    			while ( rs.next() ) {
    				int  usuarios_id = rs.getInt("usuarios_id");
    				String  matricula = rs.getString("matricula");
    				String  modelo = rs.getString("modelo");
    				String  anio = rs.getString("anio");
    				
    				if(usuarios_id == userLogged.id) {
    					labelMatricula.setText(matricula);
                		labelModelo.setText(modelo);
                		labelAnio.setText(anio);
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
        	
    	} else {
    		nombreUser.setText(userLogged.nombre);
        	rolUser.setText(userLogged.rol_tipo);
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
			stage.setTitle("Submenú");
		} catch(Exception e) {
			e.printStackTrace();
		}
    }
    
    @FXML
    void pressEditar(ActionEvent event) {
    	if(userLogged.rol_tipo.equals("Conductor") || userLogged.rol_tipo.equals("conductor") || userLogged.rol_tipo.equals("Conductora") || userLogged.rol_tipo.equals("conductora")) {
    		try {
    			FXMLLoader loaderEditar = new FXMLLoader(getClass().getResource("/view/EditarCoche_View.fxml"));
    			
    			EditarCoche controlEditar = new EditarCoche();
    			
    			loaderEditar.setController(controlEditar);

    			controlEditar.setUserLogged(userLogged);
    			
    			Parent rootEditar = loaderEditar.load();
    			
    			Stage stage = new Stage();
    			
    			stage.setScene(new Scene(rootEditar));
    			stage.show();
    			Stage stage1 = (Stage) botonEditar.getScene().getWindow();
    			stage1.close();
    			stage.setTitle("Editar Coche");
    		} catch(Exception e) {
    			e.printStackTrace();
    		}
    	}
    }
    
    @FXML
    void verConductor(ActionEvent event) {
    	Connection conn = null;
        Statement stmt = null;
        String sql;
    	
    	if(userLogged.rol_tipo.equals("Tecnico") || userLogged.rol_tipo.equals("tecnico") || userLogged.rol_tipo.equals("Tecnica") || userLogged.rol_tipo.equals("tecnica")) {
        	
        	try {
            	Class.forName("org.mariadb.jdbc.Driver");

                System.out.println("Connecting to a selected database...");

                conn = DriverManager.getConnection(
                        "jdbc:mariadb://195.235.211.197\r\n"
                        + "/prieasyliving", USER, PASS);
                
                System.out.println("Connectado a la Base de Datos...");
                
                sql = "SELECT cocheinfo.id, cocheinfo.usuarios_id, cocheinfo.matricula, cocheinfo.modelo, cocheinfo.anio, usuarios.nombre "
                		+ "FROM cocheinfo INNER JOIN usuarios ON cocheinfo.usuarios_id = usuarios.id";
                stmt = conn.createStatement();
    			ResultSet rs = stmt.executeQuery( sql );
    			while ( rs.next() ) {
    				String  matricula = rs.getString("matricula");
    				String  modelo = rs.getString("modelo");
    				String  anio = rs.getString("anio");
    				String nombre = rs.getString("nombre");
    				
    				if(inputConductor.getText().equals(nombre)) {
    					labelMatricula.setText(matricula);
                		labelModelo.setText(modelo);
                		labelAnio.setText(anio);
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