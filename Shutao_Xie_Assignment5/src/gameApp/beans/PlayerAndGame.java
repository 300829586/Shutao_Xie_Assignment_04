package gameApp.beans;

import java.sql.Date;

/**PlayerAndGame Model - Java Bean*/
public class PlayerAndGame {

	private int _playerGameId;
	private int _gameId;
	private int _playerId;
	private Date _playingDate;
	private int _score;
	
	// public properties +++++++++++++++++++++++++++++++
	public int getPlayerGameId(){ return this._playerGameId; }
	public int getGameId(){ return this._gameId; }
	public int getPlayerId(){ return this._playerId; }
	public Date getPlayingDate(){ return this._playingDate; }
	public int getScore(){ return this._score; }
	
	public void setPlayerGameId(int id){ this._playerGameId = id;}
	public void setPlayerId(int id){ this._playerId = id;}
	public void setGameId(int id){ this._gameId = id;}
	public void setPlayingDate(Date date){ this._playingDate = date;}
	public void setScore(int score){ this._score = score;}
	
	
}
