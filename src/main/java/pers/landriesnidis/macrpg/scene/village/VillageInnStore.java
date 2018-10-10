package pers.landriesnidis.macrpg.scene.village;

import pers.landriesnidis.macrpg.PlayerScene;
import pers.landriesnidis.macrpg.element.store.BaseStore;
import pers.landriesnidis.macrpg.player.Player;
import pers.landriesnidis.ptm4j.option.Option;
import pers.landriesnidis.ptm4j.option.base.OptionHandler;
import pers.landriesnidis.ptm4j.scene.base.ISceneContext;

public class VillageInnStore extends BaseStore{
	@Override
	public void onCreate() {
		super.onCreate();
		setTitle("渔村客栈");
		setTextContent("客栈老板：住在这里可以帮助你快速恢复气血和活力，住宿一次只需$600~");
		
		addTextOption("住宿", null);
		getLastOption().setPreparatoryExecuteHandler(new OptionHandler() {
			public boolean preparatoryExecuteHandle(String text, ISceneContext sceneContext, Object dataTag,
					Option optionContext) {
				Player player = ((PlayerScene)sceneContext).getPlayer();
				// 扣除住宿费
				if(player.coinChange(-600)){
					// 体力回满
					player.setVit(100);
					// 恢复30%的气血
					player.hpChange((int) (player.getMaxHP()*0.3));
					// 保存数据
					PlayerScene.updatePlayerDataInDB(player);
					showMessage("好好休息了一番，神清气爽！(活力恢复满，气血恢复30%，钱币-600)", sceneContext, dataTag);
				}else{
					showMessage(String.format("投宿失败，钱币不足。(当前钱币：%d)", player.getCoin()), sceneContext, dataTag);
				}
				return false;
			}
		});
		addBackOption("离开");
		
	}
}
