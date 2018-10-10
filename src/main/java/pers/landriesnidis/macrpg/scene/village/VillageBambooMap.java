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

public class VillageBambooMap extends BaseMap{

	public VillageBambooMap() {
		super(MapType.RESOURCE_FOREST);
	}
	
	public static HashMap<String, Double> PROPS;
	static{
		PROPS = new LinkedHashMap<String, Double>();
		PROPS.put("鹿茸", 0.95);
		PROPS.put("熊掌", 0.8);
		PROPS.put("蛇胆", 0.4);
		PROPS.put("兔肉", 0.2);
	}
	
	@Override
	public void onCreate() {
		super.onCreate();
		
		setTitle("竹林");
		setTextContent("一片葱郁茂密的小竹林，不少动物生活在这里。");
		
		addTextOption("狩猎(每次狩猎消耗体力8点)", null);
		getLastOption().setPreparatoryExecuteHandler(new OptionHandler() {
			public boolean preparatoryExecuteHandle(String text, ISceneContext sceneContext, Object dataTag,
					Option optionContext) {
				Player player = ((PlayerScene)sceneContext).getPlayer();
				// 扣除活力值
				if(player.deductVIT(10)){
					HashMap<String, Integer> map = RandomPropBox.getPropsAtRandom(VillageBambooMap.PROPS, 1, false, player.getLuck());
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
		addMenuOption("小河边", VillageRiverMap.class);
		addBackOption("离开");
	}
}


