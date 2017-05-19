package org.com.cn.xu.util.ibatis.plugin.service;

import com.alibaba.dubbo.common.utils.StringUtils;
import org.com.cn.xu.util.ibatis.plugin.JsonPage;
import org.com.cn.xu.util.ibatis.plugin.annotation.Param;
import org.com.cn.xu.util.ibatis.plugin.annotation.Return;
import org.com.cn.xu.util.ibatis.plugin.annotation.Scope;
import org.com.cn.xu.util.ibatis.plugin.util.Utils;
import org.hibernate.validator.cfg.ConstraintMapping;
import org.hibernate.validator.cfg.context.TypeConstraintMappingContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.*;

/**
 * 请求参数/返回值基础类定义
 * @Title: ParamUnit.java
 * @Package com.framework.servlet.service
 * @Description:
 * @author maxwell
 * @version V1.0
 * @date
 */
public class ParamUnit {
	private static final Logger LOG = LoggerFactory.getLogger(ParamUnit.class);
	
	private ThreadLocal<Object> _local = new ThreadLocal<Object>();
	public static final String PARAM_UNIT_TYPE_PARAM = "param";
	public static final String PARAM_UNIT_TYPE_RETURN = "return";
	/**
	 * 参数类型
	 */
	private Class type;
	/**
	 * 泛型
	 */
	private Class target;
	/**
	 * 参数名
	 */
	private String name;
	/**
	 * 参数所属分组
	 */
	private String group;
	// private Object value;
	/**
	 * 对应的父级属性
	 */
	private String attr;
	/**
	 * 对应的子参数
	 */
	private ParamUnit[] child;
	/**
	 * 参数的生命周期
	 */
	private Scope scope;
	/**
	 * 对应validate规则
	 */
	private ConstraintMapping validatorMapping;
	private boolean isNotNull;
	private String notNullMsg;
	private Set<Class> targetSet;

	public String getNotNullMsg() {
		return notNullMsg;
	}

	public void setNotNullMsg(String notNullMsg) {
		this.notNullMsg = notNullMsg;
	}

	public Class getType() {
		return type;
	}

	public void setType(Class type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Object getValue() {
		// return value;
		return _local.get();
	}

	public void setValue(Object value) {
		// this.value = value;
		_local.set(value);
	}

	public void remove() {
		_local.remove();
	}

	public ParamUnit[] getChild() {
		return child;
	}

	public void setChild(ParamUnit[] child) {
		this.child = child;
	}

	public Class getTarget() {
		return target;
	}

	public void setTarget(Class target, String paramunitType,Set<Class> targetSet) {
		this.target = target;
		this.targetSet = targetSet;
		// if(Scope.request.equals(scope)){
		if (!Utils.isPrimitive(target) && !Utils.isJCOTable(target) && !Utils.isStream(target)) {
			if (PARAM_UNIT_TYPE_RETURN.equals(paramunitType)) {
				if(type.equals(JsonPage.class)){
					getReturnJsonPage();
				}else{
					getReturnList();
				}
			} else {
				if (Scope.request.equals(scope)) {
					getParamList();
				}
			}
		}
		// }
	}

	private void getParamList() {
		List<ParamUnit> clist = new ArrayList<ParamUnit>();
		if(targetSet.contains(target)){
			LOG.warn("key-value request do not support nested");
			return;
		}
		this.targetSet.add(target);
		Field[] fields = target.getDeclaredFields();
		for (Field item : fields) {
			if (item.isAnnotationPresent(Param.class)) {
				Param p = item.getAnnotation(Param.class);
				String groupTmp = p.group();
				if (ParamUnitGroupMgr.isNeededGroup(this.group, groupTmp)) {
					Class typeTmp = item.getType();
					String nameTmp = p.name();
					if (StringUtils.isEmpty(nameTmp)) {
						nameTmp = item.getName();
					}
					nameTmp = this.name + "." + nameTmp;
					Class ctar = p.target();
					ParamUnit cpu = new ParamUnit();
					cpu.setAttr(item.getName());
					cpu.setType(typeTmp);
					cpu.setName(nameTmp);
					cpu.setGroup(this.group);
					if (ctar.equals(Object.class)) {
						ctar = typeTmp;
					}
					// 加入Validtor校验anntation扫描
					if (Utils.isPrimitive(ctar)) {
						Annotation[] annotations = item.getDeclaredAnnotations();
						if (annotations != null && annotations.length > 0) {
							ConstraintMapping vm = new ConstraintMapping();
							TypeConstraintMappingContext<?> context = vm.type(ctar);
							for (Annotation at : annotations) {
								if (ValidtorConvert.isValidatorAnnotaion(at)) {
									if (ValidtorConvert.isNotNull(at)) {
										cpu.setNotNull(true);
										cpu.setNotNullMsg(ValidtorConvert.getNotNullMsg(at));
									}
									context.constraint(ValidtorConvert.getValidatorDef(at));
								}
							}
							cpu.setValidatorMapping(vm);
						}
					}
					// end
					Set<Class> childset = new HashSet<Class>();
					childset.addAll(this.targetSet);
					cpu.setTarget(ctar, PARAM_UNIT_TYPE_PARAM,childset);
					childset.clear();
					childset = null;
					clist.add(cpu);
				}
			}
		}
		child = clist.toArray(new ParamUnit[clist.size()]);
	}

	private void getReturnList() {
		if (!Map.class.isAssignableFrom(target) && !Map.class.equals(target)) {
			List<ParamUnit> clist = new ArrayList<ParamUnit>();
			if(targetSet.contains(target)){
				LOG.warn("xml response do not support nested");
				return;
			}
			this.targetSet.add(target);
			Field[] fields = target.getDeclaredFields();
			for (Field item : fields) {
				if (item.isAnnotationPresent(Return.class)) {
					Return r = item.getAnnotation(Return.class);
					String groupTmp = r.group();
					if (ParamUnitGroupMgr.isNeededGroup(this.group, groupTmp)) {
						Class typeTmp = item.getType();
						String nameTmp = r.name();
						if (StringUtils.isEmpty(nameTmp)) {
							nameTmp = item.getName();
						}
						Class ctar = r.target();
						ParamUnit cpu = new ParamUnit();
						cpu.setAttr(item.getName());
						cpu.setType(typeTmp);
						cpu.setName(nameTmp);
						cpu.setGroup(this.group);
						if (ctar.equals(Object.class)) {
							ctar = typeTmp;
						}
						Set<Class> childset = new HashSet<Class>();
						childset.addAll(this.targetSet);
						cpu.setTarget(ctar, PARAM_UNIT_TYPE_RETURN,childset);
						childset.clear();
						childset = null;
						clist.add(cpu);
					}
				}
			}
			child = clist.toArray(new ParamUnit[clist.size()]);
		}
	}
	
	
	private void getReturnJsonPage() {
		Class orgtarget = target;
		this.target = this.type;
		List<ParamUnit> clist = new ArrayList<ParamUnit>();
		Field[] fields = type.getDeclaredFields();
		for (Field item : fields) {
			if (item.isAnnotationPresent(Return.class)) {
				Return r = item.getAnnotation(Return.class);
				String groupTmp = r.group();
				if (ParamUnitGroupMgr.isNeededGroup(this.group, groupTmp)) {
					Class typeTmp = item.getType();
					String nameTmp = r.name();
					if (StringUtils.isEmpty(nameTmp)) {
						nameTmp = item.getName();
					}
					Class ctar = r.target();
					ParamUnit cpu = new ParamUnit();
					cpu.setAttr(item.getName());
					cpu.setType(typeTmp);
					cpu.setName(nameTmp);
					cpu.setGroup(this.group);
					if (ctar.equals(Object.class)) {
						ctar = typeTmp;
						if(List.class.isAssignableFrom(typeTmp)){
							ctar = orgtarget;
						}
					}
					Set<Class> childset = new HashSet<Class>();
					childset.addAll(this.targetSet);
					cpu.setTarget(ctar, PARAM_UNIT_TYPE_RETURN,childset);
					childset.clear();
					childset = null;
					clist.add(cpu);
				}
			}
		}
		child = clist.toArray(new ParamUnit[clist.size()]);
	}
	

	public String getAttr() {
		return attr;
	}

	public void setAttr(String attr) {
		this.attr = attr;
	}

	public Scope getScope() {
		return scope;
	}

	public void setScope(Scope scope) {
		this.scope = scope;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public ConstraintMapping getValidatorMapping() {
		return validatorMapping;
	}

	public void setValidatorMapping(ConstraintMapping validatorMapping) {
		this.validatorMapping = validatorMapping;
	}

	public boolean isNotNull() {
		return isNotNull;
	}

	public void setNotNull(boolean isNotNull) {
		this.isNotNull = isNotNull;
	}

	public Set<Class> getTargetSet() {
		return targetSet;
	}

	public void setTargetSet(Set<Class> targetSet) {
		this.targetSet = targetSet;
	}
}
