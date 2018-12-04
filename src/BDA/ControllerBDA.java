package BDA;

import java.io.IOException;

import org.w3c.dom.Node;
import javafx.scene.input.MouseEvent;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class ControllerBDA {
	/**
	 * ListView (biblioteca Javafx)
	 */

	@FXML
	private ListView<Mensagem> list;
	
	/**
	 * Checkbox para adicionar/remover emails
	 */
	@FXML
	private CheckBox email;
	
	/**
	 * Checkbox para adicionar/remover posts do facebook
	 */
	@FXML
	private CheckBox facebook;
	
	
	/**
	 * Checkbox para adicionar/remover tweets
	 */
	@FXML
	private CheckBox twitter;
	
	/**
	 * TextField correspondente � palavra ou frase a pesquisar
	 */
	@FXML
	private TextField pesquisa;
	
	/**
	 * Label correspondente ao username do login do email guardado nesse momento
	 */
	@FXML
	private Label userEmail;
	/**
	 * Label correspondente ao username do login do facebook guardado nesse momento
	 */
	@FXML
	private Label userFacebook;
	/**
	 * Label correspondente ao username do twitter do facebook guardado nesse momento
	 */
	@FXML
	private Label userTwitter;
	
	/**
	 *Instancia do tipo TimelineBDA
	 */
	TimelineBDA bda = new TimelineBDA();
	/**
	 * Procedimento respons�vel por iniciar a aplica��o com a timeline 
	 */
	public void initialize() throws Exception {
		
		if (XMLclass.existsLogin(XMLclass.configFile, XMLclass.emailService)) {
			Credential emailCred = new Credential(
					XMLclass.getLogin(XMLclass.configFile, XMLclass.emailService).getAttributes());
			userEmail.setText(emailCred.getUsername());
			
		}
		if (XMLclass.existsLogin(XMLclass.configFile, XMLclass.facebookService)) {
			Credential facebookCred = new Credential(
					XMLclass.getLogin(XMLclass.configFile, XMLclass.facebookService).getAttributes());
			userFacebook.setText(facebookCred.getUsername());
		}
		if (XMLclass.existsLogin(XMLclass.configFile, XMLclass.twitterService)) {
			Credential twitterCred = new Credential(
					XMLclass.getLogin(XMLclass.configFile, XMLclass.twitterService).getAttributes());
			userTwitter.setText(twitterCred.getUsername());
		}
		Task<Void> exampleTask = new Task<Void>() {
			
			@Override
			protected Void call() throws Exception {
				

				list.setItems(bda.getTimeLine());
				return null;
			}
		};
		
		new Thread(exampleTask).start();
		
	}
	
	/**
	 * Procedimento que devolve a lista de mensagens
	 * @param event MouseEvent
	 */
	@FXML
	private void getMensagens(MouseEvent event) {
			Task<Void> exampleTask = new Task<Void>() {
			
			@Override
			protected Void call() throws Exception {
				
				list.setItems(bda.getTimeLine());
				return null;
			}
		};
		
		new Thread(exampleTask).start();
		
	}
	
	/**
	 * Procedimento que filtra a lista de mensagens dada uma palavra ou frase
	 * @param event ActionEvent
	 * @throws Exception e 
	 */
	@FXML
	private void filter(ActionEvent event) throws Exception {
		System.out.println(pesquisa.getText());
		list.setItems(bda.setFilter(pesquisa.getText()));
	}
	
	/**
	 * Procedimento que filtra a lista de mensagens para remover/adicionar emails
	 * @param event ActionEvent
	 * @throws Exception e 
	 */
	@FXML
	private void email(ActionEvent event) throws Exception {
		if(email.isSelected()){
			bda.setShowEmail(true);
		}
		if(!email.isSelected()){
			bda.setShowEmail(false);
		}
		list.setItems(bda.getTimeLine());
	}
	/**
	 * Procedimento que filtra a lista de mensagens para remover/adicionar posts
	 * @param event ActionEvent
	 * @throws Exception e
	 */
	@FXML
	private void facebook(ActionEvent event) throws Exception {
		if(facebook.isSelected()){
			bda.setShowFacebook(true);
		}
		if(!facebook.isSelected()){
			bda.setShowFacebook(false);
		}
		list.setItems(bda.getTimeLine());
	}
	/**
	 * Procedimento que filtra a lista de mensagens para adicionar/remover tweets
	 * @param event ActionEvent
	 * @throws Exception e
	 */
	@FXML
	private void twitter(ActionEvent event) throws Exception {
		if(twitter.isSelected()){
			bda.setShowTwitter(true);
		}
		if(!twitter.isSelected()){
			bda.setShowTwitter(false);
		}
		list.setItems(bda.getTimeLine());
	}
	
	/**
	 * Procedimento que filtra a lista de mensagens para apresentar so emails
	 * @param event ActionEvent
	 * @throws Exception e
	 */
	@FXML
	private void onlyEmail(ActionEvent event) throws Exception {
		list.setItems(bda.getOnlyEmail());
	}
	
	/**
	 * Procedimento que filtra a lista de mensagens para apresentar so posts do facebook
	 * @param event ActionEvent
	 * @throws Exception e
	 */
	@FXML
	private void onlyFacebook(ActionEvent event) throws Exception {
		list.setItems(bda.getOnlyFacebook());
	}
	
	/**
	 * Procedimento que filtra a lista de mensagens para apresentar so tweets
	 * @param event ActionEvent
	 * @throws Exception e
	 */
	@FXML
	private void onlyTwitter(ActionEvent event) throws Exception {
		list.setItems(bda.getOnlyTwitter());
	}
	
	/**
	 * Procedimento que retorna � janela principal da aplica��o
	 * @param event MouseEvent
	 * @throws IOException 
	 */
	@FXML
	private void voltar(MouseEvent event) throws IOException {
		FuncoesGerais.mudarVistaFXML(event, getClass().getResource("mainWindow.fxml"));
	}
}
