package org.com.cn.xu.util.ibatis.plugin.service;

import org.hibernate.validator.HibernateValidator;
import org.hibernate.validator.HibernateValidatorConfiguration;
import org.hibernate.validator.cfg.ConstraintDef;
import org.hibernate.validator.cfg.ConstraintMapping;
import org.hibernate.validator.cfg.defs.*;
import org.hibernate.validator.constraints.*;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.*;
import java.util.Set;

/**
 * Validator工具类
 * @Title: ValidtorConvert.java
 * @Package com.framework.servlet.validtor
 * @Description:
 * @author maxwell
 * @version V1.0
 * @date
 */
public class ValidtorConvert {

	public static boolean isValidatorAnnotaion(Object obj) {
		return obj != null && (obj instanceof AssertFalse || obj instanceof AssertTrue || obj instanceof DecimalMax || obj instanceof DecimalMin || obj instanceof Digits || obj instanceof Future || obj instanceof Max || obj instanceof Min || obj instanceof NotNull || obj instanceof Null || obj instanceof Past || obj instanceof Pattern || obj instanceof Size || obj instanceof Email || obj instanceof Length || obj instanceof NotBlank || obj instanceof NotEmpty || obj instanceof Range || obj instanceof ScriptAssert || obj instanceof URL);
	}
	
	public static boolean isNotNull(Object obj){
		if(obj != null) return obj instanceof NotNull;
		return false;
	}
	
	public static String getNotNullMsg(Object obj){
		if(obj != null && obj instanceof NotNull){
			return ((NotNull)obj).message();
		}
		return "";
	}

	public static String validObjectValue(ConstraintMapping mapping, Object value) {
		if(mapping != null){
			HibernateValidatorConfiguration config = Validation.byProvider(HibernateValidator.class).configure();
			config.addMapping(mapping);
			ValidatorFactory factory = config.buildValidatorFactory();
			Validator validator = factory.getValidator();
			Set violations = validator.validate(value);
			if(violations.size()>0){
				StringBuffer buf = new StringBuffer();
//				for (Object violation : violations) {
				buf.append(((ConstraintViolation) violations.iterator().next()).getPropertyPath().toString());
				buf.append(((ConstraintViolation) violations.iterator().next()).getMessage() + "\r\n");
//				}
				return buf.toString();
			}
		}
		return null;
	}

	public static ConstraintDef getValidatorDef(Object obj) {
		if (obj != null) {
			if (obj instanceof AssertFalse) {
				return getAssertFalseDef((AssertFalse) obj);
			} else if (obj instanceof AssertTrue) {
				return getAssertTrueDef((AssertTrue) obj);
			} else if (obj instanceof DecimalMax) {
				return getDecimalMaxDef((DecimalMax) obj);
			} else if (obj instanceof DecimalMin) {
				return getDecimalMinDef((DecimalMin) obj);
			} else if (obj instanceof Digits) {
				return getDigitsDef((Digits) obj);
			} else if (obj instanceof Future) {
				return getFutureDef((Future) obj);
			} else if (obj instanceof Max) {
				return getMaxDef((Max) obj);
			} else if (obj instanceof Min) {
				return getMinDef((Min) obj);
			} else if (obj instanceof NotNull) {
				return getNotNullDef((NotNull) obj);
			} else if (obj instanceof Null) {
				return getNullDef((Null) obj);
			} else if (obj instanceof Past) {
				return getPastDef((Past) obj);
			} else if (obj instanceof Pattern) {
				return getPatternDef((Pattern) obj);
			} else if (obj instanceof Size) {
				return getSizeDef((Size) obj);
			} else if (obj instanceof Email) {
				return getEmailDef((Email) obj);
			} else if (obj instanceof Length) {
				return getLengthDef((Length) obj);
			} else if (obj instanceof NotBlank) {
				return getNotBlankDef((NotBlank) obj);
			} else if (obj instanceof NotEmpty) {
				return getNotEmptyDef((NotEmpty) obj);
			} else if (obj instanceof Range) {
				return getRangeDef((Range) obj);
			} else if (obj instanceof ScriptAssert) {
				return getScriptAssertDef((ScriptAssert) obj);
			} else if (obj instanceof URL) {
				return getURLDef((URL) obj);
			} else {
				return null;
			}
		}
		return null;
	}

	private static AssertFalseDef getAssertFalseDef(AssertFalse af) {
		if (af != null) {
			AssertFalseDef afd = new AssertFalseDef();
			afd.groups(af.groups());
			afd.message(af.message());
			afd.payload(af.payload());
			return afd;
		}
		return null;
	}

	private static AssertTrueDef getAssertTrueDef(AssertTrue at) {
		if (at != null) {
			AssertTrueDef atd = new AssertTrueDef();
			atd.groups(at.groups());
			atd.message(at.message());
			atd.payload(at.payload());
			return atd;
		}
		return null;
	}

	private static DecimalMaxDef getDecimalMaxDef(DecimalMax dm) {
		if (dm != null) {
			DecimalMaxDef dmd = new DecimalMaxDef();
			dmd.groups(dm.groups());
			dmd.message(dm.message());
			dmd.payload(dm.payload());
			dmd.value(dm.value());
			return dmd;
		}
		return null;
	}

	private static DecimalMinDef getDecimalMinDef(DecimalMin dm) {
		if (dm != null) {
			DecimalMinDef dmd = new DecimalMinDef();
			dmd.groups(dm.groups());
			dmd.message(dm.message());
			dmd.payload(dm.payload());
			dmd.value(dm.value());
			return dmd;
		}
		return null;
	}

	private static DigitsDef getDigitsDef(Digits digit) {
		if (digit != null) {
			DigitsDef digitd = new DigitsDef();
			digitd.groups(digit.groups());
			digitd.message(digit.message());
			digitd.payload(digit.payload());
			digitd.integer(digit.integer());
			digitd.fraction(digit.fraction());
			return digitd;
		}
		return null;
	}

	private static FutureDef getFutureDef(Future f) {
		if (f != null) {
			FutureDef fd = new FutureDef();
			fd.groups(f.groups());
			fd.message(f.message());
			fd.payload(f.payload());
			return fd;
		}
		return null;
	}

	private static PastDef getPastDef(Past f) {
		if (f != null) {
			PastDef fd = new PastDef();
			fd.groups(f.groups());
			fd.message(f.message());
			fd.payload(f.payload());
			return fd;
		}
		return null;
	}

	private static MaxDef getMaxDef(Max max) {
		if (max != null) {
			MaxDef maxd = new MaxDef();
			maxd.groups(max.groups());
			maxd.message(max.message());
			maxd.payload(max.payload());
			maxd.value(max.value());
			return maxd;
		}
		return null;
	}

	private static MinDef getMinDef(Min min) {
		if (min != null) {
			MinDef mind = new MinDef();
			mind.groups(min.groups());
			mind.message(min.message());
			mind.payload(min.payload());
			mind.value(min.value());
			return mind;
		}
		return null;
	}

	private static NotNullDef getNotNullDef(NotNull nn) {
		if (nn != null) {
			NotNullDef nnd = new NotNullDef();
			nnd.groups(nn.groups());
			nnd.message(nn.message());
			nnd.payload(nn.payload());
			return nnd;
		}
		return null;
	}

	private static NullDef getNullDef(Null n) {
		if (n != null) {
			NullDef nd = new NullDef();
			nd.groups(n.groups());
			nd.message(n.message());
			nd.payload(n.payload());
			return nd;
		}
		return null;
	}

	private static PatternDef getPatternDef(Pattern p) {
		if (p != null) {
			PatternDef pd = new PatternDef();
			pd.groups(p.groups());
			pd.message(p.message());
			pd.payload(p.payload());
			pd.flags(p.flags());
			pd.regexp(p.regexp());
			return pd;
		}
		return null;
	}

	private static SizeDef getSizeDef(Size size) {
		if (size != null) {
			SizeDef sized = new SizeDef();
			sized.groups(size.groups());
			sized.message(size.message());
			sized.payload(size.payload());
			sized.max(size.max());
			sized.min(size.min());
			return sized;
		}
		return null;
	}

	private static EmailDef getEmailDef(Email e) {
		if (e != null) {
			EmailDef ed = new EmailDef();
			ed.groups(e.groups());
			ed.message(e.message());
			ed.payload(e.payload());
			ed.flags(e.flags());
			ed.regexp(e.regexp());
			return ed;
		}
		return null;
	}

	private static LengthDef getLengthDef(Length l) {
		if (l != null) {
			LengthDef ld = new LengthDef();
			ld.groups(l.groups());
			ld.message(l.message());
			ld.payload(l.payload());
			ld.max(l.max());
			ld.max(l.min());
			return ld;
		}
		return null;
	}

	private static NotBlankDef getNotBlankDef(NotBlank nm) {
		if (nm != null) {
			NotBlankDef ld = new NotBlankDef();
			ld.groups(nm.groups());
			ld.message(nm.message());
			ld.payload(nm.payload());
			return ld;
		}
		return null;
	}

	private static NotEmptyDef getNotEmptyDef(NotEmpty ne) {
		if (ne != null) {
			NotEmptyDef ld = new NotEmptyDef();
			ld.groups(ne.groups());
			ld.message(ne.message());
			ld.payload(ne.payload());
			return ld;
		}
		return null;
	}

	private static RangeDef getRangeDef(Range r) {
		if (r != null) {
			RangeDef ld = new RangeDef();
			ld.groups(r.groups());
			ld.message(r.message());
			ld.payload(r.payload());
			ld.max(r.max());
			ld.min(r.min());
			return ld;
		}
		return null;
	}

	private static ScriptAssertDef getScriptAssertDef(ScriptAssert sh) {
		if (sh != null) {
			ScriptAssertDef ld = new ScriptAssertDef();
			ld.groups(sh.groups());
			ld.message(sh.message());
			ld.payload(sh.payload());
			ld.alias(sh.alias());
			ld.lang(sh.lang());
			ld.script(sh.script());
			return ld;
		}
		return null;
	}

	private static URLDef getURLDef(URL sh) {
		if (sh != null) {
			URLDef ld = new URLDef();
			ld.groups(sh.groups());
			ld.message(sh.message());
			ld.payload(sh.payload());
			ld.flags(sh.flags());
			ld.port(sh.port());
			ld.protocol(sh.protocol());
			ld.regexp(sh.regexp());
			return ld;
		}
		return null;
	}

}
