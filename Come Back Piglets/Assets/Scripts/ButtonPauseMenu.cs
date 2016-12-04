using UnityEngine;
using System.Collections;

public class ButtonPauseMenu : MonoBehaviour {

	public void MENU(){
		PauseMenu.showGUI=false;
		Application.LoadLevel("MainMenu");
	}

	public void QUIT(){
		Application.Quit();
	}
}