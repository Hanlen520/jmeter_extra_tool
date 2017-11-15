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
import com.tomoya.jmeter.IdCard;

/**
 * FileName: IdCard.java Jmeter�Զ��庯������ȡ������֤
 * �����������"functions"��������jar������/lib/extĿ¼��
 * 
 * @author tomoya
 * @version v1.0
 */
public class IdCardNo extends AbstractFunction {
	/** �������� */
	private static final List<String> desc = new LinkedList<String>();
	static {
		desc.add("���� (��: �Ϻ���)");
	}

	/** �������� */
	private static final String FUNCTION_NAME = "_IdCard";
	
	/** �����������С���� */
	private static final int MIN_PARA_COUNT = 0;
	/** ���������������� */
	private static final int MAX_PARA_COUNT = 1;
	
	/** ��������ֵ */
	private Object[] values;
	
	/**
	 * �����㷨
	 * @return ����������
	 */
	private String run() {
		if (values.length == 1) {
			String val1 = new String(((CompoundVariable) values[0]).execute().trim());
			if (!(val1 == null || val1.length() <= 0)) {
				return IdCard.getIdCard(val1);
			}else {
				return IdCard.getIdCard();
			}
		}else {
			return IdCard.getIdCard();
		}				
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