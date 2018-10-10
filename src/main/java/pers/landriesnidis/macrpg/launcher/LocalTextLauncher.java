package pers.landriesnidis.macrpg.launcher;

import java.util.Scanner;

import pers.landriesnidis.macrpg.PlayerScene;
import pers.landriesnidis.ptm4j.menu.context.IMenuContext;
import pers.landriesnidis.ptm4j.scene.base.ISceneContext;
import pers.landriesnidis.ptm4j.scene.io.SceneReader;

public class LocalTextLauncher {
	public static void main(String[] args) {
		
		DataLoader.loadResourceFile();
		
		PlayerScene scene = new PlayerScene("@wx_id001",new SceneReader() {
			public void output(String text, IMenuContext menuContext, ISceneContext context, Object dataTag) {
				System.out.println(text);
			}
		});
		
		Scanner scanner = new Scanner(System.in);
		while(true){
			try {
				String text = scanner.nextLine();
				scene.input(text,null);
			} catch (Exception e) {
				e.printStackTrace();
				break;
			}
		}
		scanner.close();
	}
}
