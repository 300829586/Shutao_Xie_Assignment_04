
/*File Name: Shutao_Xie-Term_test
 * Author's Name: Shutao Xie
 * Date: Dec. 14, 2015
 * App Description: It is a Target-Heart-Rate Calculator App. When selecting your gender, 
 * and enter your age, you can calculate the maximum heart rate and target heart rate range.
 * */

// DRIVER CLASS ++++++++++++++++++++++++++++++++
public class ProgramHRate {

	// MAIN STATIC METHOD ++++++++++++++++++++++
	public static void main(String[] args) {
		
		try {
			HRate frame = new HRate();
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
