using System;
using UnityEngine;
using UnityEngine.SceneManagement;

namespace UnityStandardAssets._2D
{
	public class Restarter : MonoBehaviour
	{
		private void OnTriggerEnter2D(Collider2D other)
		{
			if (other.tag == "Player")
			{
				SceneManager.LoadScene(Application.loadedLevelName);

				Player player = (Player)GameObject.FindGameObjectWithTag("Player").GetComponent<Player>();
				player.ReinitialisationPlayer();
				
				LittlePig littlePig = (LittlePig)GameObject.FindGameObjectWithTag("LittlePig").GetComponent<LittlePig>();
				littlePig.ReinitialisationLittlePig();
			}
		}
	}
}