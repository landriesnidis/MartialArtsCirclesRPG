package pers.landriesnidis.macrpg.player;

import java.util.HashMap;

public interface IPlayer {
	/**
	 * 钱币变化
	 * @param value 金额
	 * @return 扣除钱币是否成功
	 */
	boolean coinChange(int value);
	
	/**
	 * 扣除活力值
	 * @param value 活力值
	 * @return 扣除活力值是否成功
	 */
	boolean deductVIT(int value);
	
	/**
	 * 计算活力值
	 * @return 实际活力值
	 */
	int calculateVIT();
	
	/**
	 * 计算攻击力（包含装备）
	 * @return 实际攻击力
	 */
	double calculateAtk();
	
	/**
	 * 计算防御力（包含装备）
	 * @return 实际防御力
	 */
	double calculateDef();
	
	/**
	 * 计算速度（包含装备）
	 * @return 实际速度值
	 */
	double calculateSpeed();
	
	/**
	 * 扣除血量
	 * @param value 损失血量
	 * @return 扣除血量是否成功
	 */
	boolean hpChange(int value);
	
	/**
	 * 学习新功法
	 * @param forceName 功法名称
	 * @return 修炼功法是否成功
	 */
	boolean learnNewForce(String forceName);
	
	/**
	 * 学习新技能
	 * @param martialName 技能名称
	 * @return 学习技能是否成功
	 */
	boolean learnNewMartial(String martialName);
	
	/**
	 * 向背包中添加道具
	 * @param propName 道具名称
	 * @param num 数量
	 * @return 添加道具是否成功
	 */
	boolean addPropToPack(String propName, int num);
	
	/**
	 * 从背包中取出道具
	 * @param propName 道具名称
	 * @param num 数量
	 * @return 取出道具是否成功
	 */
	boolean removePropFromPack(String propName, int num);
	
	/**
	 * 批量更改背包内物品数量
	 * @param changeMap 数据类型说明：HashMap<物品名称, 增减量（可取负数）>
	 * @return 当存在扣除物品的情况，则有可能因数量不够而扣除失败 返回false
	 */
	boolean changePackProps(HashMap<String, Integer> changeMap);
	
	/**
	 * 判断内功修炼时间是否已冷却
	 * @return 是否已冷却
	 */
	boolean isCanExerciseForce();
	
	/**
	 * 修炼内功
	 * @return
	 */
	boolean exerciseForce();
	
	/**
	 * 使用背包中的道具
	 * @param propName 道具名称
	 * @return
	 */
	boolean useProp(String propName);
	
	/**
	 * 服用药物
	 * @param drugName
	 * @return
	 */
	boolean eatDrug(String drugName);
	
	/**
	 * 佩带装备
	 * @param equipName
	 * @return
	 */
	boolean wearEquip(String equipName);
}
