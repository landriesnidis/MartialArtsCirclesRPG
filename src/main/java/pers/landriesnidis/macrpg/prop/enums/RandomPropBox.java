package pers.landriesnidis.macrpg.prop.enums;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Random;

public class RandomPropBox {
	/**
	 * 随机获取道具
	 * @param rewardMap 数据类型说明：HashMap<物品名称, 稀有程度(<=1)>。HashMap中数据put的顺序为由稀有到普通。
	 * @param max 物品获取的最大数量 num>=1
	 * @param autoComplete 当随机获取的物品数量未达到最高上限max，则用最后一项物品自动补全数量。
	 * @param luck 幸运值
	 * @return 数据类型说明：HashMap<物品名称, 获得数量>。有可能什么也没得到。
	 */
	public static HashMap<String, Integer> getPropsAtRandom(HashMap<String, Double> rewardMap, int max,boolean autoComplete, int luck){
		// 获得的物品列表
		HashMap<String, Integer> props = new HashMap<String, Integer>();
		// 随机数
		double r = new Random().nextDouble() + (luck/100.0);
		// 遍历
		Entry<String, Double> lastEntry = null;
		for(Entry<String, Double> entry:rewardMap.entrySet()){
			lastEntry = entry;
			int i = 0;
			if(r>entry.getValue()){
				double rt = r;
				// 判断是否能连续获得物品
				while(max>++i){
					rt*=0.8;
					if(rt<entry.getValue())
						break;
				}
			}
			max-=i;
			if(i>0)props.put(entry.getKey(), i);
			if(max<=0) break;
		}
		
		// 未达到可获取物品的最大数量则用档次最低的物品补全
		if(autoComplete && max>0){
			int i = 0;
			if(props.containsKey(lastEntry.getKey())){
				i = props.get(lastEntry.getKey());
			}
			props.put(lastEntry.getKey(), i+max);
		}
		
		return props;
	}
	
	public static String buildPropsList(HashMap<String, Integer> props){
		StringBuilder sb = new StringBuilder();
		for(Entry<String, Integer> entry:props.entrySet()){
			sb.append(String.format("%s*%d ", entry.getKey(),entry.getValue()));
		}
		return sb.toString();
	}
}
