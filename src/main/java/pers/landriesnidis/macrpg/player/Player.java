package pers.landriesnidis.macrpg.player;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map.Entry;

import pers.landriesnidis.macrpg.manager.PropManager;
import pers.landriesnidis.macrpg.prop.DrugItem;
import pers.landriesnidis.macrpg.prop.EquipItem;
import pers.landriesnidis.macrpg.prop.ForceItem;
import pers.landriesnidis.macrpg.prop.PropItem;

public class Player implements IPlayer{
	
	/*************************************************
	 * 					账  号  信  息
	 *************************************************/
	
	// session id
	private String sessionId;
	
	// 用户名
	private String name;
	
	// 口令
	private String psw;
	
	/*************************************************
	 * 					财  物  积  分
	 *************************************************/
	
	// 钱币
	private int coin;
	
	// 用户背包（名称、数量）
	private HashMap<String, Integer> pack;
	
	/*************************************************
	 * 					玩  家  属  性
	 *************************************************/
	
	// 攻击力
	private int atk = 20;
	
	// 防御力
	private int def = 10;
	
	// 速度
	private int speed = 10;
	
	// 体质
	private int con = 10;
		
	// 血量
	private int hp = 100;
	
	// 活力值
	private int vit = 100;
	
	// 活力恢复时间
	private long vitTime = 0;
	
	// 福缘
	private int luck = 0;
	
	/*************************************************
	 * 					装  备  功  法
	 *************************************************/
	
	// 武器
	private String weapon;
	
	// 装甲
	private String armour;
	
	// 内功
	private String force;
	
	// 修炼内功冷却时间
	private long forceTime = 0;
	
	// 武技
	private ArrayList<String> martials;
	
	/*************************************************
	 * 				Setters and Getter
	 *************************************************/
	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPsw() {
		return psw;
	}

	public void setPsw(String psw) {
		this.psw = psw;
	}

	public int getCoin() {
		return coin;
	}

	public void setCoin(int coin) {
		this.coin = coin;
	}

	public HashMap<String, Integer> getPack() {
		return pack;
	}

	public void setPack(HashMap<String, Integer> pack) {
		this.pack = pack;
	}

	public int getAtk() {
		return atk;
	}

	public void setAtk(int atk) {
		this.atk = atk;
	}

	public int getDef() {
		return def;
	}

	public void setDef(int def) {
		this.def = def;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
	public int getCon() {
		return con;
	}
	
	public void setCon(int con) {
		this.con = con;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}
	
	public int getMaxHP() {
		return (int) (con * 10);
	}
	
	public int getVit() {
		return vit;
	}

	public void setVit(int vit) {
		this.vit = vit;
	}

	public long getVitTime() {
		return vitTime;
	}

	public void setVitTime(long vitTime) {
		this.vitTime = vitTime;
	}

	public String getWeapon() {
		return weapon;
	}

	public void setWeapon(String weapon) {
		this.weapon = weapon;
	}

	public String getArmour() {
		return armour;
	}

	public void setArmour(String armour) {
		this.armour = armour;
	}

	public String getForce() {
		return force;
	}

	public void setForce(String force) {
		this.force = force;
	}

	public ArrayList<String> getMartials() {
		return martials;
	}
	
	public void setMartials(ArrayList<String> martials) {
		this.martials = martials;
	}
	
	public int getLuck() {
		return luck;
	}
	
	public void setLuck(int luck) {
		this.luck = luck;
	}

	public long getForceTime() {
		return forceTime;
	}
	
	public void setForceTime(long forceTime) {
		this.forceTime = forceTime;
	}
	
	/*************************************************
	 * 					属  性  计  算
	 *************************************************/
	
	public boolean coinChange(int value) {
		if(value<0 && coin+value<0){
			return false;
		}
		coin+=value;
		return true;
	}

	public boolean deductVIT(int value) {
		int v = calculateVIT();
		if(value > v){
			return false;
		}
		vit -= value;
		return true;
	}
	
	public int calculateVIT() {
		// 活力值上限 100
		if(vit == 100){
			vitTime = new Date().getTime();
			return 100;
		}
		// 根据时间恢复体力值
		long lastTime = vitTime;
		vitTime = new Date().getTime();
		long m = (vitTime - lastTime)/60/1000 + vit;
		if(m>100){
			vit = 100;
		}else{
			vit = (int) m;
		}
		return vit;
	}

	public double calculateAtk() {
		return atk;
	}

	public double calculateDef() {
		return def;
	}

	public double calculateSpeed() {
		return speed;
	}

	public boolean hpChange(int value) {
		if(hp<0 && hp+value<0){
			return false;
		}else{
			hp+=value;
			int max = getMaxHP();
			if(hp>max) hp = max;
			return true;
		}
	}
	
	public boolean learnNewForce(String forceName) {
		ForceItem newForce = (ForceItem) PropManager.getItemByName(forceName);
		// 当前已修炼了功法
		if(this.force != null){
			ForceItem oldForce = (ForceItem) PropManager.getItemByName(this.force);
			
			// 如果已经修炼了相同的功法
			if(forceName.contentEquals(this.force)){
				return false;
			}
			
			// 判断重修的是不是相同门派的功法
			// - 重修本门派的功法会消散20%的功力
			// - 改修其他门派的功法会消散50%的功力
			if(newForce.getFactions().contentEquals(oldForce.getFactions())){
				this.atk *=0.8;
				this.def *=0.8;
				this.speed *=0.8;
				this.con *=0.8;
			}else{
				this.atk *=0.5;
				this.def *=0.5;
				this.speed *=0.5;
				this.con *=0.5;
			}
			// 扣除道具
			removePropFromPack(forceName, 1);
			return true;
		}else{
			this.force = newForce.getName();
			// 扣除道具
			removePropFromPack(forceName, 1);
			return true;
		}
	}

	public boolean learnNewMartial(String martialName) {
		if(martials==null){
			martials = new ArrayList<String>();
		}
		if(martials.contains(martialName)) 
			return false;
		martials.add(martialName);
		return true;
	}

	public boolean addPropToPack(String propName, int num) {
		if(num<0) return false;
		int i = 0;
		if(pack.containsKey(propName)){
			i = pack.get(propName);
		}
		pack.put(propName, i+num);
		return true;
	}

	public boolean removePropFromPack(String propName, int num) {
		if(num<0) return false;
		int i = 0;
		if(!pack.containsKey(propName)){
			return false;
		}else{
			i = pack.get(propName);
			if(num>i){						// 拥有物品的数量少于要扣除的物品数量 返回false
				return false;
			}else if(num==i){				// 拥有物品的数量与要扣除的物品数量相等 则 直接移除该项 返回true
				pack.remove(propName);
				return true;
			}else{							// 拥有物品的数量大于要扣除的物品数量 则 扣除相应数量 返回true
				pack.put(propName, i-num);
				return true;
			}
		}
	}
	
	public boolean changePackProps(HashMap<String, Integer> changeMap){
		if(changeMap!=null){
			// 检查需要扣除的物品数量是否足够
			for (Entry<String, Integer> entry : changeMap.entrySet()) {
				if(entry.getValue()<0){
					// 若背包中没有该物品 或 有此类物品但数量不足 返回 false
					if(!pack.containsKey(entry.getKey()) || pack.get(entry.getKey())+entry.getValue()<0) {
						return false;
					}
				}
			}
			// 执行物品的增减
			for (Entry<String, Integer> entry : changeMap.entrySet()) {
				int value = entry.getValue();
				if(value<0){
					removePropFromPack(entry.getKey(), 0-value);
				}else{
					addPropToPack(entry.getKey(), value);
				}
			}
		}
		return true;
	}

	public boolean isCanExerciseForce() {
		long nowTime = new Date().getTime();
		if(nowTime>forceTime){
			return true;
		}
		return false;
	}
	
	/**
	 * 修炼内功
	 * 当未修炼内功或修炼CD未到则返回false
	 */
	public boolean exerciseForce(){
		// 如果没有功法或功法冷却时间没到则返回false
		if(this.force==null || !isCanExerciseForce()){
			return false;
		}
		ForceItem force = (ForceItem) PropManager.getItemByName(this.force);
		this.atk += force.getInc_atk();
		this.def += force.getInc_def();
		this.speed += force.getInc_speed();
		this.con += force.getInc_con();
		this.forceTime = new Date().getTime() + (60 * 1000 * force.getCd_min());
		return true;
	}
	
	public boolean useProp(String propName) {
		PropItem prop = (PropItem) PropManager.getItemByName(propName);
		if(prop!=null && pack.containsKey(propName)){
			this.coin += prop.getInc_coin();
			HashMap<String, Integer> changeMap = prop.getPropsChangeMap();
			return changePackProps(changeMap);
		}
		return false;
	}

	public boolean eatDrug(String drugName) {
		DrugItem drug = (DrugItem) PropManager.getItemByName(drugName);
		if(drug!=null){
			if(!removePropFromPack(drugName, 1)) return false;
			this.atk += drug.getInc_atk();
			this.def += drug.getInc_def();
			this.speed += drug.getInc_speed();
			this.con += drug.getInc_con();
			this.vit += drug.getInc_vit();
			this.luck += drug.getInc_luck();
			return true;
		}
		return false;
	}

	public boolean wearEquip(String equipName) {
		EquipItem equip = (EquipItem) PropManager.getItemByName(equipName);
		if(equip!=null && pack.containsKey(equipName)){
			if(equip.getEquip_part().contentEquals("武器")){
				// 如果已持有武器，则把武器返回背包
				if(this.weapon!=null && !this.weapon.isEmpty()){
					addPropToPack(this.weapon, 1);
				}
				this.weapon = equipName;
			}else if(equip.getEquip_part().contentEquals("装甲")){
				// 如果已身着装甲，则把装甲返回背包
				if(this.armour!=null && !this.armour.isEmpty()){
					addPropToPack(this.armour, 1);
				}
				this.armour = equipName;
			}else{
				// 其他类型的装备尚未开发
			}
			return removePropFromPack(equipName, 1);
		}
		return false;
	}
} 
