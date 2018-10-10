package pers.landriesnidis.macrpg.element.map;

import pers.landriesnidis.macrpg.PlayerScene;
import pers.landriesnidis.macrpg.element.ElementMenu;
import pers.landriesnidis.macrpg.player.Player;
import pers.landriesnidis.ptm4j.menu.events.StartEvent;

public class BaseMap extends ElementMenu{
	
	private MapType mapType;
	public BaseMap(MapType mapType) {
		this.mapType = mapType;
	}

	public MapType getMapType() {
		return mapType;
	}

	public void setMapType(MapType mapType) {
		this.mapType = mapType;
	}
	
	@Override
	public void onStart(StartEvent e) {
		super.onStart(e);
		Player player = ((PlayerScene)e.getSceneContext()).getPlayer();
		getOption(MY_STATE).setTextContent(String.format("[玩家状态]\n攻击：%d\n防御：%d\n速度：%d\n体质：%d\n活力：%d/100\n气血：%d/%d\n气运：%d", player.getAtk(),player.getDef(),player.getSpeed(),player.getCon(),player.getVit(),player.getHp(),player.getMaxHP(),player.getLuck()));
	}
}
