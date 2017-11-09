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
 * FileName: Date.java Jmeter�Զ��庯������ȡ����
 * �����������"functions"��������jar������/lib/extĿ¼��
 * 
 * @author tomoya
 * @version v1.1
 */
public class Date extends AbstractFunction {
	/** �������� */
	private static final List<String> desc = new LinkedList<String>();
	static {
		// desc.add("Get a MD5 String within specified parameter value.");
		desc.add("���� (����: -1, ����: 0, ����: 1)");
		desc.add("��ʽ (Ĭ��: yyyy-MM-dd)");
	}

	/** �������� */
	private static final String FUNCTION_NAME = "_Date";
	
	/** �����������С���� */
	private static final int MIN_PARA_COUNT = 0;
	/** ���������������� */
	private static final int MAX_PARA_COUNT = 2;

	/** ��������ֵ */
	private Object[] values;
	
	/**
	 * �����㷨
	 * @return ����������
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