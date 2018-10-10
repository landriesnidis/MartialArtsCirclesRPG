package pers.landriesnidis.macrpg.utils;

import java.util.Date;

public class TimeRecorder {
	// 间隔时间
	private int interval = 0;
	// 上次更新时间
	private long updateTime = 0;
	
	public TimeRecorder(int interval) {
		this.interval = interval;
	}
	
	/**
	 * 判断预设的时间是否已经到了（自动重置）
	 * @return
	 */
	public boolean isTimeUp() {
		long nowTime = new Date().getTime();
		if(nowTime>=updateTime){
			updateTime = nowTime + interval;
			return true;
		}
		return false;
	}
	
	/**
	 * 设置间隔时间（毫秒）
	 * @param interval
	 */
	public void setInterval(int interval) {
		this.interval = interval;
	}
	/**
	 * 获得间隔时间（毫秒）
	 * @return
	 */
	public int getInterval() {
		return interval;
	}
	
	/**
	 * 重置更新时间
	 */
	public void reset(){
		long nowTime = new Date().getTime();
		updateTime = nowTime + interval;
	}
}
