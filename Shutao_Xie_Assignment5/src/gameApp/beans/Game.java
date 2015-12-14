package gameApp.beans;

public class Game {

	private int _gameId;
	private String _gameTitle;
	
	public int getGameId(){
		return this._gameId; 
		}
	public void setGameId(int id){
		this._gameId = id;
		}	

	public String getGameTitle(){
		return this._gameTitle; 
		}
	
	public void setGameTitle(String title){
		this._gameTitle = title;
		}
}

