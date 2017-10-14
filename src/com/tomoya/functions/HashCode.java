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
 * FileName: GetHashCode.java Jmeter�Զ��庯������ȡMD5ֵ
 * �����������"functions"��������jar������/lib/extĿ¼��
 * 
 * @author tomoya
 * @version v1.0
 */
public class HashCode extends AbstractFunction {
	/** �������� */
	private static final List<String> desc = new LinkedList<String>();
	static {
		desc.add("����");
	}

	/** �������� */
	private static final String FUNCTION_NAME = "_hashCode";
	
	/** �����������С���� */
	private static final int MIN_PARA_COUNT = 1;
	/** ���������������� */
	private static final int MAX_PARA_COUNT = 1;

	/** ��������ֵ */
	private Object[] values;
	
	/** ���ղ���1 */
	private String str;
	/** ����������� */
	private int hashCode;
	
	/**
	 * �����㷨
	 * @return ����������
	 */
	private String run() {
		str = new String(((CompoundVariable) values[0]).execute().trim());
		hashCode = Tool.getHashCode(str);
		return String.valueOf(hashCode);
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