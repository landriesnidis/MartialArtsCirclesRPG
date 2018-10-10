package pers.landriesnidis.macrpg.scene.village;

import java.util.HashMap;
import java.util.LinkedHashMap;

import pers.landriesnidis.macrpg.PlayerScene;
import pers.landriesnidis.macrpg.element.map.BaseMap;
import pers.landriesnidis.macrpg.element.map.MapType;
import pers.landriesnidis.macrpg.player.Player;
import pers.landriesnidis.macrpg.prop.enums.RandomPropBox;
import pers.landriesnidis.ptm4j.option.Option;
import pers.landriesnidis.ptm4j.option.base.OptionHandler;
import pers.landriesnidis.ptm4j.scene.base.ISceneContext;

public class VillageMineMap extends BaseMap{
	
	public static HashMap<String, Double> MINES;
	static{
		MINES = new LinkedHashMap<String, Double>();
		MINES.put("金矿", 0.95);
		MINES.put("银矿", 0.8);
		MINES.put("铁矿", 0.5);
		MINES.put("石料", 0.2);
	}
	
	public VillageMineMap() {
		super(MapType.RESOURCE_ORE);
	}

	@Override
	public void onCreate() {
		super.onCreate();
		
		setTitle("后山矿洞");
		setTextContent("一个满地碎石的昏暗矿坑，也许能挖到些有价值的东西...(每次挖矿会消耗10点活力)");
		
		addTextOption("挖矿", null);
		getLastOption().setPreparatoryExecuteHandler(new OptionHandler() {
			public boolean preparatoryExecuteHandle(String text, ISceneContext sceneContext, Object dataTag,
					Option optionContext) {
				Player player = ((PlayerScene)sceneContext).getPlayer();
				// 扣除活力值
				if(player.deductVIT(10)){
					HashMap<String, Integer> map = RandomPropBox.getPropsAtRandom(VillageMineMap.MINES, 1, false, player.getLuck());
					player.changePackProps(map);
					if(map.isEmpty()){
						showMessage("非常遗憾，这次什么也没有挖到。", sceneContext, dataTag);
					}else{
						showInfo(getTitle(), String.format("本次挖矿获得了：%s", RandomPropBox.buildPropsList(map)), getMenuFormatString(), sceneContext, dataTag);
					}
				}else{
					showMessage("你已经很疲惫了，休息一下再来吧。", sceneContext, dataTag);
				}
				return false;
			}
		});
		
		addBackOption("离开");
	}
}