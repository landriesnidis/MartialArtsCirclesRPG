package pers.landriesnidis.macrpg;

import pers.landriesnidis.macrpg.manager.TextMenuManager;
import pers.landriesnidis.macrpg.player.Player;
import pers.landriesnidis.macrpg.scene.village.VillageMap;
import pers.landriesnidis.ptm4j.menu.TextMenu;
import pers.landriesnidis.ptm4j.menu.events.StartEvent;
import pers.landriesnidis.ptm4j.option.Option;
import pers.landriesnidis.ptm4j.option.base.OptionHandler;
import pers.landriesnidis.ptm4j.scene.base.ISceneContext;

public class LoginMenu extends TextMenu{
	private int step = 0;
	
	@Override
	public void onCreate() {
		super.onCreate();
		setAllowShowSerialNumber(true);
		setTitle("登录");
		setTextContent("欢迎来到武侠文字RPG，请根据提示进入游戏：");
		
		addTextOption("登录", null);
		getLastOption().setPreparatoryExecuteHandler(new OptionHandler() {
			public boolean preparatoryExecuteHandle(String text, ISceneContext sceneContext, Object dataTag,
					Option optionContext) {
				// 允许接受字符串
				setAllowReveiceText(true);
				showMessage("输入您的游戏角色名：", sceneContext, dataTag);
				return false;
			}
		});
		
		addMenuOption("注册", new RegisterMenu());
	}
	
	@Override
	public void onStart(StartEvent e) {
		super.onStart(e);
	}
	
	@Override
	public boolean onTextReveived(String text, ISceneContext sceneContext, Object dataTag) {
		Player player = ((PlayerScene)sceneContext).getPlayer();
		String sessionId = player.getSessionId();
		switch(step){
		case 0:
			if(text.length()<=6){
				// 判断玩家名是否存在
				if(!PlayerScene.checkPlayerNameOnlyOne(text)){
					++step;
					// 根据玩家名称读取玩家数据
					player = PlayerScene.readPlayerDataByPlayerName(text);
					player.setSessionId(sessionId);
					((PlayerScene)sceneContext).setPlayer(player);
					showMessage("请输入登录密码：", sceneContext, dataTag);
				}else{
					showMessage("您输入的玩家名称还未被注册，请确认后重新输入：", sceneContext, dataTag);
				}
			}else{
				showMessage("您输入的名字太长啦，不符合规范哦（6个字以内）：", sceneContext, dataTag);
			}
			break;
		case 1:
			if(text.length()<=10){
				// 验证密码
				if(player.getPsw().contentEquals(text)){
					showMessage("登录验证成功，正在跳转...", sceneContext, null);
					((PlayerScene)sceneContext).setPlayer(player);
					PlayerScene.updatePlayerDataInDB(player);
					// 重置玩家的根TextMenu并跳转
					((PlayerScene)sceneContext).setRootMenu(TextMenuManager.getMenuByClass(VillageMap.class));
				}else{
					showMessage("密码输入有误，请确认后重试（10个字以内）：", sceneContext, null);
				}
			}else{
				showMessage("密码输入有误，请确认后重试（10个字以内）", sceneContext, null);
			}
			break;
		} 
		return true;
	}
}
