package pers.landriesnidis.macrpg.element;

import pers.landriesnidis.macrpg.PlayerScene;
import pers.landriesnidis.macrpg.pack.PackMenu;
import pers.landriesnidis.macrpg.player.Player;
import pers.landriesnidis.ptm4j.menu.TextMenu;
import pers.landriesnidis.ptm4j.menu.events.StartEvent;
import pers.landriesnidis.ptm4j.option.Option;
import pers.landriesnidis.ptm4j.scene.base.ISceneContext;

public class ElementMenu extends TextMenu{
	
	public static final String MY_PACK = "背包";
	public static final String MY_STATE = "状态";
	
	public ElementMenu() {
		setAllowShowSerialNumber(true);
	}
	
	@Override
	public void onCreate() {
		super.onCreate();
		// 在所有地图中输入“我的背包”即可跳转至个人背包TextMenu
		addMenuOption(MY_PACK, PackMenu.class);
		getLastOption().setVisibility(false);
		addTextOption(MY_STATE, null);
		getLastOption().setVisibility(false);
	}
	
	@Override
	public void onStart(StartEvent e) {
		super.onStart(e);
		Player player = ((PlayerScene)e.getSceneContext()).getPlayer();
		Option optionState = getOption(MY_STATE);
		if(optionState!=null){
			optionState.setTextContent(String.format("[玩家状态]\n攻击：%d\n防御：%d\n速度：%d\n体质：%d\n活力：%d/100\n气血：%d/%d\n气运：%d", player.getAtk(),player.getDef(),player.getSpeed(),player.getCon(),player.getVit(),player.getHp(),player.getMaxHP(),player.getLuck()));
		}
	}
	
	@Override
	public void showMessage(String msg, ISceneContext sceneContext, Object dataTag) {
		if(msg.contains("@")){
			Player player = ((PlayerScene)sceneContext).getPlayer();
			msg = msg.replace("@name", player.getName());
//			msg = msg.replace("@coin", String.valueOf(player.getCoin()));
		}
		while(msg.contains("\n\n")){
			msg = msg.replace("\n\n", "\n");
		}
		super.showMessage(msg, sceneContext, dataTag);
	}
}
