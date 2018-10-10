package pers.landriesnidis.macrpg.element.npc.common;

import pers.landriesnidis.macrpg.PlayerScene;
import pers.landriesnidis.macrpg.element.npc.BaseNPC;
import pers.landriesnidis.macrpg.element.npc.function.DetectionPlayerCompositionTextMenu;
import pers.landriesnidis.macrpg.player.Player;
import pers.landriesnidis.ptm4j.menu.events.StartEvent;
import pers.landriesnidis.ptm4j.option.Option;
import pers.landriesnidis.ptm4j.option.base.OptionHandler;
import pers.landriesnidis.ptm4j.scene.base.ISceneContext;

public class GodStickNPC extends BaseNPC{
	@Override
	public void onCreate() {
		super.onCreate();
		setAllowShowSerialNumber(true);
		setTitle("老神棍");
		setTextContent("老夫神算子，没什么太大本事，不过是上知天文，下知地理，中晓人和。明阴阳，懂八卦，晓奇门，知遁甲。运筹帷幄之中，决胜千里之外。抱膝委坐自比管仲、乐毅之贤，笑傲风月，未出茅庐便知三分天下。找老夫何事不妨直说……");
		
		addArgsMenuOption("测测你是由什么做成的($100)", DetectionPlayerCompositionTextMenu.class);
		getLastOption().setPreparatoryExecuteHandler(new OptionHandler() {
			public boolean preparatoryExecuteHandle(String text, ISceneContext sceneContext, Object dataTag,
					Option optionContext) {
				Player player = ((PlayerScene)sceneContext).getPlayer();
				// 扣除费用
				if(player.coinChange(-100)){
					return true;
				}else{
					showMessage(String.format("没钱赶紧走远点，别打扰本大仙儿做生意！(钱币不足，当前钱币：%d)", player.getCoin()), sceneContext, dataTag);
					return false;
				}
			}
		});
		addBackOption("返回");
	}
	
	@Override
	public void onStart(StartEvent e) {
		super.onStart(e);
	}
}
