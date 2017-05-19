package org.com.cn.xu.util.ibatis.plugin.validate;

import com.alibaba.dubbo.common.utils.StringUtils;
import org.com.cn.xu.exception.ErrorCode;
import org.com.cn.xu.exception.SystemException;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

//import com.framework.core.exception.SystemException;
//import com.framework.service.test.model.ValidParam;
//import com.framework.utils.ErrorCode;

/**
 * 内置的验证服务bean
 * @Title: DefaultValidatorImpl.java
 * @Package com.framework.service.validate
 * @Description:基于JPA的validator标准，验证bean的正确性
 * @author maxwell
 * @version V1.0
 * @date
 */
@Service
public class DefaultValidatorImpl implements IValidator {
	
	private Validator validator;

	public DefaultValidatorImpl() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}
	/*
	 * 查看接口的注释描述
	 * @see com.framework.service.validate.IValidator#validate(java.lang.Object, java.lang.Class<?>[])
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public void validate(Object obj, Class<?>... classes) {
		if (obj == null)
			throw new SystemException("object is null,pls chk.", ErrorCode.ERROR_CODE_VALIDJPA_ERROR);
		Set violations = validator.validate(obj, classes);
		if (violations.size() > 0) {
			StringBuffer buf = new StringBuffer();
			for (Object violation : violations) {
				buf.append(((ConstraintViolation) violation).getMessage() + "\r\n");
			}
			String error = buf.toString();
			if (StringUtils.isNotEmpty(error)) {
				try {
					throw new SystemException(error, ErrorCode.ERROR_CODE_VALIDJPA_ERROR);
				} catch (SystemException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	/*
	 * 查看接口的注释描述
	 * @see com.framework.service.validate.IValidator#validate(java.lang.Class, java.lang.String, java.lang.Object, java.lang.Class<?>[])
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public void validate(Class<?> target,String property,Object obj, Class<?>... classes) {
		Set violations = validator.validateValue(target, property, obj, classes);
		if (violations.size() > 0) {
			StringBuffer buf = new StringBuffer();
			for (Object violation : violations) {
				buf.append(((ConstraintViolation) violation).getMessage() + "\r\n");
			}
			String error = buf.toString();
			if (StringUtils.isNotEmpty(error)) {
				throw new SystemException(error, ErrorCode.ERROR_CODE_VALIDJPA_ERROR);
			}
		}
	}
}
