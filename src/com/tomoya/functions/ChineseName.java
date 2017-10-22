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
 * FileName: GetCnName.java Jmeter�Զ��庯������ȡ2~4��������
 * �����������"functions"��������jar������/lib/extĿ¼��
 * 
 * @author tomoya
 * @version v1.0
 */
public class ChineseName extends AbstractFunction {
	/** �������� */
	private static final List<String> desc = new LinkedList<String>();

	/** �������� */
	private static final String FUNCTION_NAME = "_ChineseName";
	
	/** �����������С���� */
	private static final int MIN_PARA_COUNT = 0;
	/** ���������������� */
	private static final int MAX_PARA_COUNT = 0;
	
	/**
	 * �����㷨
	 * @return ����������
	 */
	private String run() {
		String ChineseName = Tool.getChineseName();
		return String.valueOf(ChineseName);
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
	}

}