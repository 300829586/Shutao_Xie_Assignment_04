package gameApp.beans;

public class Player {

	private int _playerId;
	private String _firstName;
	private String _lastName;
	private String _address;
	private String _postalCode;
	private String _province;
	private String _phoneNumber;
	
	public int getPlayerId() {
		return _playerId;
	}
	public void setPlayerId(int playerId) {
		this._playerId = playerId;
	}
	public String getFirstName() {
		return _firstName;
	}
	public void setFirstName(String firstName) {
		this._firstName = firstName;
	}
	public String getLastName() {
		return _lastName;
	}
	public void setLastName(String lastName) {
		this._lastName = lastName;
	}
	public String getAddress() {
		return _address;
	}
	public void setAddress(String address) {
		this._address = address;
	}
	public String getPostalCode() {
		return _postalCode;
	}
	public void setPostalCode(String postalCode) {
		this._postalCode = postalCode;
	}
	public String getProvince() {
		return _province;
	}
	public void setProvince(String province) {
		this._province = province;
	}
	public String getPhoneNumber() {
		return _phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this._phoneNumber = phoneNumber;
	}
}

