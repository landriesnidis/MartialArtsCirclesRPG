package pers.landriesnidis.macrpg.prop;

import java.util.HashMap;

import pers.landriesnidis.macrpg.prop.enums.ItemType;

public class PropItem extends BaseItem{

	public PropItem() {
		super(ItemType.PROP);
	}
	private String add_porps;
	private String remove_props;
	private int inc_coin;
	private String script;
	
	private HashMap<String, Integer> propsChangeMap;
	
	public HashMap<String, Integer> getPropsChangeMap() {
		if(propsChangeMap == null){
			propsChangeMap = new HashMap<String, Integer>();
			String[] arr = null; 
			if(add_porps!=null){
				arr = add_porps.split("\\|");
				for(String s:arr){
					if(s.indexOf('*')!=-1){
						int num = Integer.parseInt(s.split("\\*")[1]);
						propsChangeMap.put(s.split("\\*")[0], num);
					}else{
						propsChangeMap.put(s, 1);
					}
				}
			}
			if(remove_props!=null){
				arr = remove_props.split("\\|");
				for(String s:arr){
					if(s.indexOf('*')!=-1){
						int num = Integer.parseInt(s.split("\\*")[1]);
						propsChangeMap.put(s.split("\\*")[0], 0-num);
					}else{
						propsChangeMap.put(s, -1);
					}
				}
			}
		}
		return propsChangeMap;
	}
	public void setPropsChangeMap(HashMap<String, Integer> propsChangeMap) {
		this.propsChangeMap = propsChangeMap;
	}
	
	public String getAdd_porps() {
		return add_porps;
	}
	public void setAdd_porps(String add_porps) {
		this.add_porps = add_porps;
	}
	public String getRemove_props() {
		return remove_props;
	}
	public void setRemove_props(String remove_props) {
		this.remove_props = remove_props;
	}
	public int getInc_coin() {
		return inc_coin;
	}
	public void setInc_coin(int inc_coin) {
		this.inc_coin = inc_coin;
	}
	public String getScript() {
		return script;
	}
	public void setScript(String script) {
		this.script = script;
	}
}
