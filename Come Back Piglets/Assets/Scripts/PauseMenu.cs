using UnityEngine;
using System.Collections;

public class PauseMenu : MonoBehaviour {

	public static bool showGUI = false;
	GameObject canvas;

	void Start(){
		canvas = GameObject.Find("Canvas");
	}

	void Update(){
		if(Input.GetKeyDown(KeyCode.Escape)){
			showGUI= !showGUI;
		}
		if(showGUI){
			canvas.SetActive(true);
			Time.timeScale = 0;
		}else{
			canvas.SetActive(false);
			Time.timeScale = 1;
		}
	}

}
