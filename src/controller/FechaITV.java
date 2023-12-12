package controller;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Usuario;
import model.CocheInfo;

import java.net.URL;
import java.util.ResourceBundle;


public class FechaITV  implements Initializable  {
	
	static final String USER = "pri_easyliving";
    static final String PASS = "MixiMixi";
    
	@FXML
    private Pane PanelFechaITV;
	@FXML
    private Label labelProximaFecha;
	@FXML
	private Label annioCoche;
	@FXML
    private Hyperlink LinkInfoITV;
	@FXML
	private Text TextTuProximaVisita;
    @FXML
    private Button buttonSubMenu;
	@FXML
    private CocheInfo coche;
	
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
	        
	        
	    	if (userLogged.rol_tipo.equals("Conductor") || userLogged.rol_tipo.equals("conductor") || userLogged.rol_tipo.equals("Conductora") || userLogged.rol_tipo.equals("conductora")) {
	    		nombreUser.setText(userLogged.nombre);
	        	rolUser.setText(userLogged.rol_tipo);
	    	
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
       				int usuarioId = rs.getInt("usuarios_id");
    				String matricula = rs.getString("matricula");
    				String modelo = rs.getString("modelo");
    				String  annio_fab = rs.getString("anio");
    			
    				int annio_Coche=Integer.parseInt(annio_fab);
    				if(usuarioId==userLogged.id) {
    			    	int annio_actual = 2023;

    	    		  	annioCoche.setText("El coche con matricula " + matricula + 
    	    		  			" y modelo "+ modelo + " se matriculo en el año: "+ annio_fab);

    			    	 if (annio_actual - annio_Coche < 4) {
    		    		  		TextTuProximaVisita.setText( "Todavía no tiene que pasar la ITV, la primera es a los 4 años desde la fecha de la primera matriculación");
    		    		  	  } else if (annio_actual - annio_Coche > 4 && annio_actual - annio_Coche < 10) {
    		    		  		TextTuProximaVisita.setText("Tiene que pasar la ITV cada dos años después de la última revisión");
    		    		  	  } else {
    		    		  		TextTuProximaVisita.setText("Tiene que pasar la ITV anualmente después de la última revisión");
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
    void InfoITVEscrito(ActionEvent event) {
    	try {
			FXMLLoader loaderInformacionITV = new FXMLLoader(getClass().getResource("/view/InformacionITV_View.fxml"));
			
			InformacionITV controlerInformacionITV = new InformacionITV();
			
			loaderInformacionITV.setController(controlerInformacionITV);
			
			Parent rootInformacionITV = loaderInformacionITV.load();
			
			Stage stage = new Stage();
			
			stage.setScene(new Scene(rootInformacionITV));
			stage.show();
			Stage stage1 = (Stage) LinkInfoITV.getScene().getWindow();
			stage1.close();
			stage.setTitle("Informacion ITV");
		} catch(Exception e) {
			e.printStackTrace();
		}

    }
 
    @FXML
    void llevaSubMenu(ActionEvent event) {
    	if(userLogged.rol_tipo.equals("Conductor") || userLogged.rol_tipo.equals("conductor") || userLogged.rol_tipo.equals("Conductora") || userLogged.rol_tipo.equals("conductora")
    	    	|| userLogged.rol_tipo.equals("Enfermero") || userLogged.rol_tipo.equals("enfermero") || userLogged.rol_tipo.equals("Enfermera") || userLogged.rol_tipo.equals("enfermera")) {
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
    	} else {
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
