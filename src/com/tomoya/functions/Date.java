package com.tomoya.functions;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.apache.jmeter.engine.util.CompoundVariable;
import org.apache.jmeter.functions.AbstractFunction;
import org.apache.jmeter.functions.InvalidVariableException;
import org.apache.jmeter.samplers.SampleResult;
import org.apache.jmeter.samplers.Sampler;

import com.tomoya.jmeter.Tool;

/**
 * FileName: Date.java Jmeter自定义函数，获取日期
 * 包名必须包含"functions"，导出的jar包放在/lib/ext目录下
 * 
 * @author tomoya
 * @version v1.1
 */
public class Date extends AbstractFunction {
	/** 函数描述 */
	private static final List<String> desc = new LinkedList<String>();
	static {
		// desc.add("Get a MD5 String within specified parameter value.");
		desc.add("类型 (昨日: -1, 今日: 0, 明日: 1)");
		desc.add("格式 (默认: yyyy-MM-dd)");
	}

	/** 函数名称 */
	private static final String FUNCTION_NAME = "_Date";
	
	/** 传入参数的最小数量 */
	private static final int MIN_PARA_COUNT = 0;
	/** 传入参数的最大数量 */
	private static final int MAX_PARA_COUNT = 2;

	/** 函数接收值 */
	private Object[] values;
	
	/**
	 * 函数算法
	 * @return 函数运算结果
	 */
	private String run() {
		int type = 0;
		String format = "yyyy-MM-dd";
		
		if (values.length == 1) {
			String val1 = new String(((CompoundVariable) values[0]).execute().trim());
			if (!(val1 == null || val1.length() <= 0)) {
				type = Integer.parseInt(val1);
			}
		}else if(values.length == 2) {
			String val1 = new String(((CompoundVariable) values[0]).execute().trim());
			String val2 = new String(((CompoundVariable) values[1]).execute().trim());
			if (!(val1 == null || val1.length() <= 0)) {
				type = Integer.parseInt(val1);
			}
			if (!(val2 == null || val2.length() <= 0)) {
				format = val2;
			}
		}
				
		return Tool.date(type, format);
	} 
	

	/**
	 * 返回参数描述
	 */
	@Override
	public List<String> getArgumentDesc() {
		return desc;
	}
	
	/**
	 * 返回函数运行结果
	 */
	@Override
	public String execute(SampleResult previousResult, Sampler currentSampler) throws InvalidVariableException {
		try {
			return run();
		} catch (Exception ex) {
			throw new InvalidVariableException(ex);
		}
	}
	
	/**
	 * 返回函数名
	 */
	@Override
	public String getReferenceKey() {
		return FUNCTION_NAME;
	}
	
	/**
	 * 返回输入参数
	 */
	@Override
	public void setParameters(Collection<CompoundVariable> parameters) throws InvalidVariableException {
		checkParameterCount(parameters, MIN_PARA_COUNT, MAX_PARA_COUNT); // 检查参数的个数是否正确
		values = parameters.toArray(); // 将值存入类变量中
	}

}