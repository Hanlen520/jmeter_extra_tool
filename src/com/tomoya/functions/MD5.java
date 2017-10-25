package com.tomoya.functions;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import org.apache.jmeter.engine.util.CompoundVariable;
import org.apache.jmeter.functions.AbstractFunction;
import org.apache.jmeter.functions.InvalidVariableException;
import org.apache.jmeter.samplers.SampleResult;
import org.apache.jmeter.samplers.Sampler;

import com.tomoya.jmeter.Tool;

/**
 * FileName: MD5.java Jmeter�Զ��庯������ȡMD5ֵ
 * �����������"functions"��������jar������/lib/extĿ¼��
 * 
 * @author tomoya
 * @version v1.0
 */
public class MD5 extends AbstractFunction {
	/** �������� */
	private static final List<String> desc = new LinkedList<String>();
	static {
		// desc.add("Get a MD5 String within specified parameter value.");
		desc.add("����");
	}

	/** �������� */
	private static final String FUNCTION_NAME = "_MD5";
	
	/** �����������С���� */
	private static final int MIN_PARA_COUNT = 1;
	/** ���������������� */
	private static final int MAX_PARA_COUNT = 1;

	/** ��������ֵ */
	private Object[] values;
	
	/**
	 * �����㷨
	 * @return ����������
	 */
	private String run() {
		String str = new String(((CompoundVariable) values[0]).execute().trim());
		String MD5 = Tool.getMD5(str);
		return String.valueOf(MD5);
	} 
	

	/**
	 * ���ز�������
	 */
	@Override
	public List<String> getArgumentDesc() {
		return desc;
	}
	
	/**
	 * ���غ������н��
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
	 * ���غ�����
	 */
	@Override
	public String getReferenceKey() {
		return FUNCTION_NAME;
	}
	
	/**
	 * �����������
	 */
	@Override
	public void setParameters(Collection<CompoundVariable> parameters) throws InvalidVariableException {
		checkParameterCount(parameters, MIN_PARA_COUNT, MAX_PARA_COUNT); // �������ĸ����Ƿ���ȷ
		values = parameters.toArray(); // ��ֵ�����������
	}

}