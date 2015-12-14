package gameApp;

import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import gameApp.beans.Game;
import gameApp.beans.Player;
import gameApp.beans.PlayerAndGame;
import gameApp.DBConfig;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class GameController {

	@FXML
	private Label infoLabel;
	@FXML
	private Button goButton;
	@FXML
	private ComboBox<String> operationComboBox;
	
	
	@FXML
	private TextField gameTitle_InsertGame_TextField;
	@FXML
	private Button submit_InsertGame_Button;
	@FXML
	private Button cancel_InsertGame_Button;

	@FXML
	private Label status_InsertGame_Label;

	@FXML
	private Button submit_InsertPlayer_Button;
	@FXML
	private Button cancel_InsertPlayer_Button;
	@FXML
	private TextField firstName_InsertPlayer_TextField, lastName_InsertPlayer_TextField, address_InsertPlayer_TextField,
			postalCode_InsertPlayer_TextField, province_InsertPlayer_TextField, phone_InsertPlayer_TextField;
	@FXML
	private Label status_InsertPlayer_Label;

	@FXML
	private Button submit_InsertPlayerGame_Button;
	@FXML
	private Button cancel_InsertPlayerAndGame_Button;
	@FXML
	private TextField score_InsertPlayerAndGame_TextField, date_InsertPlayerAndGame_TextField;
	@FXML 
	private Label status_InsertPlayerAndGame_Label;
	@FXML
	private Tab insertToPlayerAndGame_Tab;
	@FXML
	private ComboBox<Integer> gameId_InsertPlayerAndGame_Combobox, playerId_InsertPlayerAndGame_Combobox;

	@FXML private ComboBox<Integer> playerId_Display_Combobox;
	@FXML private Label status_display_label;
	@FXML private Pane display_pane;
	@FXML private GridPane pngInfo_gridpane_display;

	private boolean _mouseEntered_display = false;
	
	Stage stage;
	Scene scene;
	Parent root;

	private final int INSERT_GAME = 1;
	private final int INSERT_PLAYER = 2;
	private final int INSERT_PLAYERANDGAME = 3;

	//SUBMIT
	public void ClickSubmitButton(ActionEvent event) throws Exception {

		int operation = 0;

		if (event.getSource() == submit_InsertGame_Button) {

			operation = INSERT_GAME;

		} else if (event.getSource() == submit_InsertPlayer_Button) {

			operation = INSERT_PLAYER;

		} else if (event.getSource() == submit_InsertPlayerGame_Button) {

			operation = INSERT_PLAYERANDGAME;
		}

		if (operation != 0) {
			executeOperation(operation);
		}
	}

	// EVENTHANDLE
	public void ClickInsertToPlayerAndGame_Tab(Event event) throws Exception {

		if (((Tab) (event.getSource())).isSelected()) {

			ArrayList<Integer> gameIdArray = new ArrayList<Integer>();			
			ArrayList<Integer> playerIdArray = new ArrayList<Integer>();
			
			gameIdArray = getFromOneColumnOfTable("game_id", "game");
			playerIdArray = getFromOneColumnOfTable("player_id", "player");

			gameId_InsertPlayerAndGame_Combobox.getItems().clear();
			playerId_InsertPlayerAndGame_Combobox.getItems().clear();

			gameId_InsertPlayerAndGame_Combobox.getItems().addAll(gameIdArray);
			playerId_InsertPlayerAndGame_Combobox.getItems().addAll(playerIdArray);
		}
	}

	public void ClickCancelButton(ActionEvent event) throws Exception {

		stage = (Stage) ((Button) event.getSource()).getScene().getWindow();

		root = FXMLLoader.load(getClass().getResource("Game.fxml"));
		scene = new Scene(root);
		stage.setScene(scene);
	}
	
	@FXML private ComboBox<Integer> playerId_Update_Combobox;

	public void selectComboBox(ActionEvent event) throws Exception {

		if(event.getSource() == operationComboBox && operationComboBox.getValue() != null){
			goButton.setDisable(false);
			switch (operationComboBox.getValue()) {

			case "Display":
				infoLabel.setText("Status: Display operation selected");
				break;
			case "Insert":
				infoLabel.setText("Status: Insert operation selected");
				break;
			case "Update":
				infoLabel.setText("Status: Update operation selected");
				break;
			default:
				infoLabel.setText("Status: No operation selected.");
				break;
			}
		} else if(event.getSource() == playerId_Display_Combobox ){
			
			this._displayPlayerAndGameInfo(playerId_Display_Combobox.getValue());
			
		} else if(event.getSource() == playerId_Update_Combobox){

			this._displayPlayerToUpdatePane(playerId_Update_Combobox.getValue());
		}
		
	}
	
	@FXML Label firstName_upadte_label, 
				lastName_update_label,
				address_update_label, 
				postalCode_update_label,
				province_update_label,
				phoneNumber_update_label;
	
	@FXML TextField firstName_upadte_textField,
					lastName_update_textField,
					address_update_textField,
					postalCode_update_textField, 
					province_update_textField, 
					phoneNumber_update_textField;
	
	public void Clicksubmit_update_button(ActionEvent event) throws Exception {
		System.out.println("start update");
		
		String firstName, lastName, address, postalCode, province, phoneNumber;
		
		firstName = firstName_upadte_textField.getText();	
		lastName = lastName_update_textField.getText();
		address = address_update_textField.getText();
		postalCode = postalCode_update_textField.getText();
		province = province_update_textField.getText();
		phoneNumber = phoneNumber_update_textField.getText();
		
		//VALIDATE
		if (firstName == null || firstName == "" || firstName.trim().isEmpty()) {
			throw new Exception("invalid First Name.");
		}
		if (lastName == null || lastName == "" || lastName.trim().isEmpty()) {
			throw new Exception("invalid Last Name.");
		}
		if (address == null || address == "" || address.trim().isEmpty()) {
			throw new Exception("invalid Address.");
		}
		if (postalCode == null || postalCode == "" || postalCode.trim().isEmpty()) {
			throw new Exception("invalid Postal Code.");
		}
		if (province == null || province == "" || province.trim().isEmpty()) {
			throw new Exception("invalid Province.");
		}
		if (phoneNumber == null || phoneNumber == "" || phoneNumber.trim().isEmpty()) {
			throw new Exception("invalid Phone Number.");
		}
		
		//INSERT DATA 
		Player player = new Player();
		player.setPlayerId(playerId_Update_Combobox.getValue());
		player.setFirstName(firstName);		
		player.setLastName(lastName);
		player.setAddress(address);
		player.setPostalCode(postalCode);
		player.setProvince(province);
		player.setPhoneNumber(phoneNumber);
		
		String query = "update player set " + 
						"first_name = ?, last_name = ?, address = ?, postal_code = ?, province = ?, phone_number = ?"
				+ "where player_id = ?";
		
		try(		
				Connection conn = DBConfig.getConnection();
				PreparedStatement psm = conn.prepareStatement(query);				
		){
			psm.setString(1, player.getFirstName());
			psm.setString(2, player.getLastName());
			psm.setString(3, player.getAddress());
			psm.setString(4, player.getPostalCode());
			psm.setString(5, player.getProvince());
			psm.setString(6, player.getPhoneNumber());
			psm.setInt(7, player.getPlayerId());
			
			int affected = psm.executeUpdate();
			if(affected == 1) {
				status_display_label.setText("Status: information for playerId: " + player.getPlayerId() + " was updated successfully!");
			} else {
				status_display_label.setText("Status: No row was updated.");
			}
			
		}catch(SQLException e){
			DBConfig.displayException(e);
			status_display_label.setText("Status: update failed due to: " + e.getMessage());
		}
		
	}
	
	private void _displayPlayerToUpdatePane(Integer id)throws SQLException{
		
		Player player = _getRowByIdFromPlayerTabel(id);		
		firstName_upadte_label.setText(player.getFirstName());
		lastName_update_label.setText(player.getLastName());
		address_update_label.setText(player.getAddress());
		postalCode_update_label.setText(player.getPostalCode());
		province_update_label.setText(player.getProvince());
		phoneNumber_update_label.setText(player.getPhoneNumber());
		
		//UPDATE		
		firstName_upadte_textField.setText(player.getFirstName());
		lastName_update_textField.setText(player.getLastName());
		address_update_textField.setText(player.getAddress());
		postalCode_update_textField.setText(player.getPostalCode());
		province_update_textField.setText(player.getProvince());
		phoneNumber_update_textField.setText(player.getPhoneNumber());

		
	}
	
	@FXML private Label firstName_display_label, lastName_display_label,
	                    address_display_label,postalCode_display_label, 
	                    province_display_label,phoneNumber_display_label;

	@FXML private GridPane gameInfo_gridpane_display;

	private void _displayPlayerAndGameInfo(Integer id) throws SQLException{
		gameInfo_gridpane_display.getChildren().clear();
			
		Player player = _getRowByIdFromPlayerTabel(id);		
		firstName_display_label.setText(player.getFirstName());
		lastName_display_label.setText(player.getLastName());
		address_display_label.setText(player.getAddress());
		postalCode_display_label.setText(player.getPostalCode());
		province_display_label.setText(player.getProvince());
		phoneNumber_display_label.setText(player.getPhoneNumber());
		
		ArrayList<PlayerAndGame> pngArray = _getRowsByPlayerIdFromPlayerAndGame(id);
		
		if(pngArray.size() > 0){
			ArrayList<Game> gameArray = new ArrayList<Game>();
			
			for(int i = 0; i < pngArray.size(); i++){
				gameArray.add(this._getRowByGameIdFromGameTabel(pngArray.get(i).getGameId()));
			}
			
			for(int i = 0; i < pngArray.size(); i++){

				Label title_label = new Label();
				Label date_label = new Label();
				Label score_label = new Label();
				
				title_label.setText(gameArray.get(i).getGameTitle());				
				date_label.setText(pngArray.get(i).getPlayingDate().toString());				
				score_label.setText(pngArray.get(i).getScore() + "");

				gameInfo_gridpane_display.getRowConstraints().add(new RowConstraints(40));
				gameInfo_gridpane_display.add(title_label, 0, i);
				gameInfo_gridpane_display.add(date_label, 1, i);
				gameInfo_gridpane_display.add(score_label, 2, i);							
			}
			
			status_display_label.setText("Status: "+ gameArray.size() + " games played by " + player.getFirstName() + " " + player.getLastName());
		
		}else{
			status_display_label.setText("Status: no game played by " + player.getFirstName() + " " + player.getLastName() + ", must be so busy on programming...");
		}
	}
	
	private Game _getRowByGameIdFromGameTabel(int gameId) throws SQLException{
				
		String SQLQuery = "select * from game where game_id = ?";
		ResultSet resultSet = null;
		
		try(		
				Connection conn = DBConfig.getConnection();
				PreparedStatement psm = conn.prepareStatement(SQLQuery);				
		){
			psm.setInt(1, gameId);
			resultSet = psm.executeQuery();

			if(resultSet.next()){
				Game game = new Game();
				game.setGameId(gameId);
				game.setGameTitle(resultSet.getString("game_title"));
								
				return game;
			}else{
				return null;
			}
		}catch(SQLException exception){
			DBConfig.displayException(exception);
			return null;
		}finally{
			if(resultSet != null){
				resultSet.close();
			}
		}		
}
	
	private ArrayList<PlayerAndGame> _getRowsByPlayerIdFromPlayerAndGame(int playerId)throws SQLException{
		
		ArrayList<PlayerAndGame> pngArray = new ArrayList<PlayerAndGame>();
		
		String SQLQuery = "select * from playerandgame where player_id = ?";
		ResultSet resultSet = null;
		
		try(		
				Connection conn = DBConfig.getConnection();
				PreparedStatement psm = conn.prepareStatement(SQLQuery);				
		){
			psm.setInt(1, playerId);
			resultSet = psm.executeQuery();
			
			while(resultSet.next()){
				PlayerAndGame png = new PlayerAndGame();
				png.setGameId(resultSet.getInt("game_id"));
				png.setPlayingDate(resultSet.getDate("playing_date"));
				png.setScore(resultSet.getInt("score"));			
				
				pngArray.add(png);
			}
			return pngArray;
		}catch(SQLException exception){
			DBConfig.displayException(exception);
			return null;
		}finally{
			if(resultSet != null){
				resultSet.close();
			}
		}
	}
	
	private Player _getRowByIdFromPlayerTabel(int playerId) throws SQLException{
		String SQLQuery = "select * from player where player_id = ?";
		ResultSet resultSet = null;
		
		try(		
				Connection conn = DBConfig.getConnection();
				PreparedStatement psm = conn.prepareStatement(SQLQuery);				
		){
			psm.setInt(1, playerId);
			resultSet = psm.executeQuery();
			
			if(resultSet.next()){
				Player player = new Player();
				player.setPlayerId(playerId);
				player.setFirstName(resultSet.getString("first_name"));
				player.setLastName(resultSet.getString("last_name"));
				player.setAddress(resultSet.getString("address"));
				player.setPostalCode(resultSet.getString("postal_code"));
				player.setProvince(resultSet.getString("province"));
				player.setPhoneNumber(resultSet.getString("phone_number"));
				
				return player;
			}else{
				return null;
			}
		}catch(SQLException ex){
			DBConfig.displayException(ex);
			return null;
		}finally{
			if(resultSet != null){
				resultSet.close();
			}
		}
	}
	@FXML private Pane update_pane;
	
	public void mouseenter_pane(MouseEvent event)throws Exception{

		if(!this._mouseEntered_display && event.getSource() == display_pane){
			this._mouseEntered_display = true;
			
			playerId_Display_Combobox.getItems().clear();
			playerId_Display_Combobox.getItems().addAll(getFromOneColumnOfTable("player_id", "player"));
			
		}else if (!this._mouseEntered_display && event.getSource() == update_pane ){
			
			this._mouseEntered_display = true;
			playerId_Update_Combobox.getItems().clear();
			playerId_Update_Combobox.getItems().addAll(getFromOneColumnOfTable("player_id", "player"));
		}
		
	}

	public void ClickGoButton(ActionEvent event) throws Exception {
				
		if (operationComboBox.getValue() != null) {
			
			stage = (Stage) operationComboBox.getScene().getWindow();
			
			switch (operationComboBox.getValue()) {

				case "Display":
					
					root = FXMLLoader.load(getClass().getResource("Display.fxml"));
					scene = new Scene(root);
					stage.setScene(scene);				
					
					break;
	
				case "Insert":
					root = FXMLLoader.load(getClass().getResource("Insert.fxml"));					
					scene = new Scene(root);
					stage.setScene(scene);
					
					break;
	
				case "Update":
					root = FXMLLoader.load(getClass().getResource("Update.fxml"));
					scene = new Scene(root);
					stage.setScene(scene);
					break;
	
				default:
					break;

			}
		}
	}

	public void InputToActivateSubmit(KeyEvent event) {

		if (event.getSource() == gameTitle_InsertGame_TextField && submit_InsertGame_Button.isDisabled()) {
			submit_InsertGame_Button.setDisable(false);

		} else if (event.getSource() == phone_InsertPlayer_TextField && submit_InsertPlayer_Button.isDisable()) {
			submit_InsertPlayer_Button.setDisable(false);

		} else if (event.getSource() == score_InsertPlayerAndGame_TextField
				&& submit_InsertPlayerGame_Button.isDisable()) {
			submit_InsertPlayerGame_Button.setDisable(false);
		}
	}

	private void executeOperation(int operation) throws SQLException {

		switch (operation) {

			case INSERT_GAME:
				insertGame();
				break;
			case INSERT_PLAYER:
				insertPlayer();
				break;
			case INSERT_PLAYERANDGAME:
				insertPlayerAndGame();
				break;
		}
	}

	private void insertPlayerAndGame() throws SQLException {

		String query = "insert into playerandgame " + "(game_id, player_id, playing_date, score)" + "values(?,?,?,?)";
		ResultSet keys = null;

		try (Connection conn = DBConfig.getConnection();
				PreparedStatement psm = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);) {

			String gameId_s, playerId_s, score_s, playingDate_s;

			gameId_s = gameId_InsertPlayerAndGame_Combobox.getValue().toString();
			playerId_s = playerId_InsertPlayerAndGame_Combobox.getValue().toString();
			score_s = score_InsertPlayerAndGame_TextField.getText();
			playingDate_s = date_InsertPlayerAndGame_TextField.getText();

			if (gameId_s == null || gameId_s == "" || gameId_s.trim().isEmpty()) {
				throw new Exception("invalid Game Id.");
			}
			if (playerId_s == null || playerId_s == "" || playerId_s.trim().isEmpty()) {
				throw new Exception("invalid Player Id.");
			}
			if (score_s == null || score_s == "" || score_s.trim().isEmpty()) {
				throw new Exception("invalid Score.");
			}
			if (playingDate_s == null || playingDate_s == "" || playingDate_s.trim().isEmpty()) {
				throw new Exception("invalid Playing Date.");
			}
		
			int gameId, playerId, score;
			java.sql.Date playingDate;   
			gameId = Integer.parseInt(gameId_s);
			playerId = Integer.parseInt(playerId_s);
			score = Integer.parseInt(score_s);						
			playingDate = InputHelper.getDateInput(playingDate_s);
						
			PlayerAndGame playandgame = new PlayerAndGame();
			
			playandgame.setGameId(gameId);
			playandgame.setPlayerId(playerId);
			playandgame.setPlayingDate(playingDate);
			playandgame.setScore(score);
		
			psm.setInt(1, playandgame.getGameId());
			psm.setInt(2, playandgame.getPlayerId());
			psm.setDate(3, playandgame.getPlayingDate());
			psm.setInt(4, playandgame.getScore());

			int affected = psm.executeUpdate();

			if (affected == 1) {
				keys = psm.getGeneratedKeys();
				keys.next();
				int newKey = keys.getInt(1);
				playandgame.setPlayerGameId(newKey);

				score_InsertPlayerAndGame_TextField.setText(null);
				date_InsertPlayerAndGame_TextField.setText(null);
				status_InsertPlayerAndGame_Label.setText("Status: a record for InsertPlayerAndGame inserted >> (" + playandgame.getPlayerGameId()+ ", playerId: " + playandgame.getPlayerId() + ", gameId: " + playandgame.getGameId() + ")");
			}

		} catch (Exception e) {
			status_InsertPlayerAndGame_Label.setText("Status: operation failed due to: " + e.getMessage());
		} finally {
			if (keys != null) {
				keys.close();
			}
		}
	}

	private void insertPlayer() throws SQLException {

		String query = "insert into player " + "(first_name, last_name, address, postal_code, province, phone_number)"
				+ "values(?,?,?,?,?,?)";
		ResultSet keys = null;

		try (Connection conn = DBConfig.getConnection();
				PreparedStatement psm = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);) {

			String firstName, lastName, address, postalCode, province, phoneNumber;
			firstName = firstName_InsertPlayer_TextField.getText();
			lastName = lastName_InsertPlayer_TextField.getText();
			address = address_InsertPlayer_TextField.getText();
			postalCode = postalCode_InsertPlayer_TextField.getText();
			province = province_InsertPlayer_TextField.getText();
			phoneNumber = phone_InsertPlayer_TextField.getText();

			if (firstName == null || firstName == "" || firstName.trim().isEmpty()) {
				throw new Exception("invalid first name.");
			}
			if (lastName == null || lastName == "" || lastName.trim().isEmpty()) {
				throw new Exception("invalid last name.");
			}
			if (address == null || address == "" || address.trim().isEmpty()) {
				throw new Exception("invalid address.");
			}
			if (postalCode == null || postalCode == "" || postalCode.trim().isEmpty()) {
				throw new Exception("invalid postal code.");
			}
			if (province == null || province == "" || province.trim().isEmpty()) {
				throw new Exception("invalid province.");
			}
			if (phoneNumber == null || phoneNumber == "" || phoneNumber.trim().isEmpty()) {
				throw new Exception("invalid phone number.");
			}

			Player player = new Player();
			player.setFirstName(firstName);
			player.setLastName(lastName);
			player.setAddress(address);
			player.setPostalCode(postalCode);
			player.setProvince(province);
			player.setPhoneNumber(phoneNumber);

			psm.setString(1, player.getFirstName());
			psm.setString(2, player.getLastName());
			psm.setString(3, player.getAddress());
			psm.setString(4, player.getPostalCode());
			psm.setString(5, player.getProvince());
			psm.setString(6, player.getPhoneNumber());

			int affected = psm.executeUpdate();

			if (affected == 1) {
				keys = psm.getGeneratedKeys();
				keys.next();
				int newKey = keys.getInt(1);
				player.setPlayerId(newKey);

				firstName_InsertPlayer_TextField.setText(null);
				lastName_InsertPlayer_TextField.setText(null);
				address_InsertPlayer_TextField.setText(null);
				postalCode_InsertPlayer_TextField.setText(null);
				province_InsertPlayer_TextField.setText(null);
				phone_InsertPlayer_TextField.setText(null);

				status_InsertPlayer_Label.setText("Status: a player inserted >> (" + player.getPlayerId() + ", "
						+ player.getFirstName() + " " + player.getLastName() + ")");
			}

		} catch (Exception exception) {
			status_InsertPlayer_Label.setText("Status: operation failed due to: " + exception.getMessage());
		} finally {
			if (keys != null) {
				keys.close();
			}
		}
	}

	private void insertGame() throws SQLException {

		String query = "insert into game " + "(game_title)" + "values(?)";
		ResultSet keys = null;

		try (Connection conn = DBConfig.getConnection();
				PreparedStatement psm = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);) {

			String title = gameTitle_InsertGame_TextField.getText();

			if (title == "" || title == null || title.trim().isEmpty()) {
				throw new Exception("title empty"); 
			}

			Game game = new Game();
			game.setGameTitle(title);

			psm.setString(1, game.getGameTitle());

			int affected = psm.executeUpdate();

			if (affected == 1) {
				keys = psm.getGeneratedKeys();
				keys.next();
				int newKey = keys.getInt(1);
				game.setGameId(newKey);

				gameTitle_InsertGame_TextField.setText(null);
				status_InsertGame_Label.setText("Status: a game inserted >> (" + game.getGameId() + ", " + game.getGameTitle() + ")");

			}

		} catch (Exception exception) {
			status_InsertGame_Label.setText("Status: operation failed due to: " + exception.getMessage());
		} finally {
			if (keys != null) {
				keys.close();
			}
		}
	}

	public ArrayList<Integer> getFromOneColumnOfTable(String columnName, String tableName) {
		String query = "SELECT " + columnName + " FROM " + tableName;
		ArrayList<Integer> array = new ArrayList<Integer>();

		try (Connection conn = DBConfig.getConnection();
				Statement sm = conn.createStatement();
				ResultSet rs = sm.executeQuery(query);) {
			while (rs.next()) {
				array.add(rs.getInt(columnName));
			}

			return array;
		} catch (SQLException ex) {
			DBConfig.displayException(ex);
			return null;
		}
	}
}
