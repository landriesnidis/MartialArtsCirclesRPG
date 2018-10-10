package pers.landriesnidis.macrpg;

import java.util.HashMap;

import pers.landriesnidis.macrpg.manager.TextMenuManager;
import pers.landriesnidis.macrpg.player.Player;
import pers.landriesnidis.macrpg.scene.village.VillageMap;
import pers.landriesnidis.ptm4j.menu.TextMenu;
import pers.landriesnidis.ptm4j.menu.events.StartEvent;
import pers.landriesnidis.ptm4j.scene.base.ISceneContext;

public class RegisterMenu extends TextMenu{

	private int step = 0;
	
	@Override
	public void onCreate() {
		super.onCreate();
		setAllowReveiceText(true);
		setTitle("新玩家注册");
		setTextContent("请根据提示信息完成新玩家注册：");
	}
	
	@Override
	public void onStart(StartEvent e) {
		super.onStart(e);
		showMessage("请为您的游戏角色起一个响亮的名字吧（6个字以内）：", e.getSceneContext(), null);
	}
	
	@Override
	public boolean onTextReveived(String text, ISceneContext sceneContext, Object dataTag) {
		Player player = ((PlayerScene)sceneContext).getPlayer();
		switch(step){
		case 0:
			if(text.length()<=6){
				// 将数据写入数据库
				if(PlayerScene.checkPlayerNameOnlyOne(text)){
					++step;
					player.setName(text);
					PlayerScene.insertPlayerDataToDB(player);
					showMessage("为了能多端登录，请为您的这个角色设置一个登录密码吧（10个字以内）：", sceneContext, null);
				}else{
					showMessage("这个名字已经被占用啦，再重新想一个吧~ （6个字以内）：", sceneContext, null);
				}
			}else{
				showMessage("您的名字起的太长啦，稍微简略一点吧（6个字以内）：", sceneContext, null);
			}
			break;
		case 1:
			if(text.length()<=10){
				++step;
				player.setPsw(text);
				player.setPack(new HashMap<String, Integer>());
				player.addPropToPack("新手宝箱", 1);
				showMessage("注册已完成，正在跳转...", sceneContext, null);
				PlayerScene.updatePlayerDataInDB(player);
				((PlayerScene)sceneContext).setRootMenu(TextMenuManager.getMenuByClass(VillageMap.class));
			}else{
				showMessage("密码不符合规范呐，稍微简短一点吧（10个字以内）：", sceneContext, null);
			}
			break;
		}
		return true;
	}
}
