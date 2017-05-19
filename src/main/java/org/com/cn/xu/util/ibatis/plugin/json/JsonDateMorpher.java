package org.com.cn.xu.util.ibatis.plugin.json;

import net.sf.ezmorph.object.AbstractObjectMorpher;
import net.sf.ezmorph.object.DateMorpher;

/**
 * josn日期转化器
 * @Title: JsonDateMorpher.java
 * @Package com.framework.servlet.util.json
 * @Description:
 * @author maxwell
 * @version V1.0
 * @date
 */
public class JsonDateMorpher extends AbstractObjectMorpher {

	private DateMorpher dph;
	
	public JsonDateMorpher(DateMorpher dph){
		super();
		this.dph = dph;
	}
	
	@Override
	public Object morph(Object arg0) {
		if(arg0==null || (arg0 instanceof String && ((String)arg0).length()==0)) return null;
		return dph.morph(arg0);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Class morphsTo() {
		return dph.morphsTo();
	}
	
}
