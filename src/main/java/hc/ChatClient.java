package hc;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.impl.clientside.HazelcastClientInstanceImpl;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.topic.ITopic;

import javafx.application.Application; 
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray; 
import javafx.collections.ObservableList; 
import javafx.geometry.Insets;
import javafx.scene.Scene; 
import javafx.scene.control .*; 
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox; 
import javafx.stage.Stage;

import java.util.ArrayList; 
import java.util.List;

public class ChatClient extends Application {
	public static void main(String[] args) {
		Application.launch(ChatClient.class);
	}
	private HazelcastInstance hazelcastInstance;
	@Override
	public void start(Stage primaryStage ) throws Exception {
		hazelcastInstance = HazelcastClient.newHazelcastClient();
		BorderPane borderPane = new BorderPane();
		Label labelName = new Label("Name:");
		TextField textFieldName = new TextField();
		Button buttonSubscribe = new Button("Subscribe");
		Label labelTo = new Label("To:");
		TextField textFieldTo = new TextField();
		Button buttonSend = new Button("Send");
		Label labelMessage = new Label("Message");
		TextField textFieldMessage = new TextField();
		List<String> messages= new ArrayList<>();
		ObservableList <String> observableList = FXCollections.observableList (messages);
		ListView <String> listView = new ListView <>(observableList);
		HBox hBoxTop = new HBox(10);
		hBoxTop.setPadding(new Insets(10));
		hBoxTop.getChildren().addAll(labelName,textFieldName,buttonSubscribe);
		HBox hBoxBottom = new HBox(10);
		hBoxBottom.setPadding(new Insets(10));
		hBoxBottom.getChildren().addAll(labelTo,textFieldTo,labelMessage,textFieldMessage,buttonSend);
		borderPane.setTop(hBoxTop);
		borderPane.setBottom(hBoxBottom);
		borderPane.setCenter(listView);
		Scene scene = new Scene(borderPane, 600, 400);
		primaryStage.setScene(scene);
		primaryStage.show();
		buttonSubscribe.setOnAction(evt-> {
			ITopic<String> topic = hazelcastInstance.getTopic(textFieldName.getText());
			topic.addMessageListener (message -> {
				System.out.println("Réception du message message.getMessageObject");
				observableList.add(message.getMessageObject());
			});
			buttonSubscribe.setDisable(true);
		});
		buttonSend.setOnAction(evt-> {
			ITopic topicTo = hazelcastInstance.getTopic(textFieldTo.getText());
			topicTo.publish(textFieldMessage.getText());
		});
	}
}
