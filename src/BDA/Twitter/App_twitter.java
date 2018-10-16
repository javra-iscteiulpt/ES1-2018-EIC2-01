package BDA.Twitter;
import java.util.List;

import BDA.FuncoesGerais;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;


public class App_twitter {
	private ObservableList<String> tweets = FXCollections.observableArrayList();
	
	@FXML
	private ListView<String> tweetsList;
	
	public static void main(String[] args) {
		
	}
//	@FXML
//    private void twitterLogin_clicked(ActionEvent event)
//    {
//		FuncoesGerais.mudarVistaFXML(event, getClass().getResource("twitter.fxml"));
//    }
	
	@FXML
    private void refresh_timeline_Clicked(MouseEvent event)
    {
		getTimeline();
		//FuncoesGerais.mudarVistaFXML(event, getClass().getResource("./Twitter/loginTwitter.fxml"));
    }
	
	public void getTimeline(){
		try {  
			ConfigurationBuilder cb = new ConfigurationBuilder();
	    	cb.setDebugEnabled(true)
	    	  .setOAuthConsumerKey("yPv2NQ8ozCWIQ1jZeXjWLGUce")
	    	  .setOAuthConsumerSecret("w7lfg9hNlQ8qFAfb5k7fMtzdiYhqhBFe5S6PNu0PfTy0FL6Vo8")
	    	  .setOAuthAccessToken("1051761005406154752-yRmIyBEYTX21kensmMUAvpNVRfC15Q")
	    	  .setOAuthAccessTokenSecret("F7mHLVxLhBOG3OHELLvYG5etmlIFtnXnNStgnlpHCShLX");
	    	TwitterFactory tf = new TwitterFactory(cb.build());
			Twitter twitter = tf.getInstance();  		
            List<Status> statuses = twitter.getHomeTimeline();
            System.out.println("------------------------\n Showing home timeline \n------------------------");
    		int counter=0;
    		int counterTotal = 0;
            for (Status status : statuses) {
            	String s = status.getUser().getName() + ":" + status.getText();
				
				tweets.add(counter, s);
				counter++;
				counterTotal++;
            }
            tweetsList.setItems(tweets);
    		System.out.println("-------------\nN� of Results: " + counter+"/"+counterTotal);
        } catch (Exception e) { System.out.println(e.getMessage()); }
	}

}