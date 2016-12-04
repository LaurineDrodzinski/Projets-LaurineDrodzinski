using UnityEngine;
using System.Collections;

public class MainMenu : MonoBehaviour {

	public void START(){
		Application.LoadLevel("scene2");
	}

	public void QUIT(){
		Application.Quit();
	}
}
