# jmeter_extra_tool

@(Jmeter)[������|�Զ��庯��|�������]

**jmeter_extra_tool**��һ��רΪJmeter����ĳ��ù�������Զ��庯������Ϊ�ճ��Ĳ��Թ����������㡣
 
- **com.tomoya.jmeter** 
	- **Tool��**��������ȡMD5ֵ����ȡ��������JDBC���д���ļ��ȷ���������д��Excel�ȷ���
	- **RSA��**��������Կ���ܡ�˽Կ���ܣ�˽Կ���ܡ���Կ���ܵȷ���
	- **Excel��**����������д��͵���׷��д��Excel�ļ��ķ���
- **com.tomoya.functions**
	- **...** 

-------------------

[TOC]

## ��Ŀ�ṹ
### com.tomoya.jmeter
- com.tomoya.jmeter
	- com.tomoya.jmeter.Tool
		- getChineseName()
		<br>��ȡ2~4���ֵ��������������ְ棨����Ϊ����ʻ����ڣ�
		- getMD5(String)
		<br>����32λmd5ֵ
		- getHashCode(String)
		<br>����HashCodeֵ
		- writeFile(String, String)
		<br>�ַ���д���ļ�
		- JDBCResultWriteFile(ArrayList, String, String)
		<br>Jmeter��JDBC Result��ָ���У�д���ļ�
		- JDBCResultWriteFile(ArrayList, String[], String)
		<br>Jmeter��JDBC Result��ָ�����У�д���ļ�
	- com.tomoya.jmeter.RSA
		- pubKeyEncrypt(String, String)
		<br>ʹ�ù�Կ���м��ܣ����ع�Կ����
        - priKeyDecrypt(String, String)
		<br>���빫Կ���ģ�ʹ��˽Կ���н��ܣ���������
        - priKeyEncrypt(String, String)
		<br>ʹ��˽Կ���м��ܣ�����˽Կ����
        - pubKeyDecrypt(String, String)
		<br>����˽Կ���ģ�ʹ�ù�Կ���н��ܣ���������
	- com.tomoya.jmeter.Excel
        - writeToExcel(String[], String[], String)
		<br>������⡢��������Դ���ļ���ַ��׷��д��Excel�ļ�
        - writeToExcel2(String[], List<Map> String)
		<br>������⡢���ݼ����ļ���ַ����д����Excel�ļ�

### com.tomoya.functions
- com.tomoya.functions
	- com.tomoya.functions.MD5
		- execute(SampleResult, Sampler)
		<br>return �������н��
		- getArgumentDesc()
		<br>return ��������
		- getReferenceKey()
		<br>return ������
		- setParameters(Collection<>)
		<br>����Jmeter���û�����Ĳ���
    - HashCode
        - ...
    - ChineseName
        - ...

##����������
###jmeter_extra_tool-v1.0.jar
[�����������ҳ��](https://pan.baidu.com/s/1miR4Fry "jmeter_extra_tool-v1.0.jar")

###jmeter_extra_tool-v1.1.jar
[�����������ҳ��](https://pan.baidu.com/s/1dFixDNb "jmeter_extra_tool-v1.1.jar")

[Apache POI](http://poi.apache.org/download.html "Apache POI") (���غ������jar��copy��Jmeter��װ·����/lib/extĿ¼��)

##ʹ�÷���
### Tool��
> �����������jar������Jmeter��/lib/ext·���£���������Jmeter����BeanShell��������� **import com.tomoya.jmeter.*;** �Ϳ��Ե���Tool���RSA���еķ����ˡ�

![jmeter_extra_tool](http://img.blog.csdn.net/20171023092151650?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvdG9tb3lhX2NoZW4=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

### Excel��
> �����������jar�������ص�Apache POI ����jar������Jmeter��/lib/ext·���£���������Jmeter����BeanShell��������� **import com.tomoya.jmeter.*;** �Ϳ��Ե���Excel���еķ����ˡ�

![����дͼƬ����](http://img.blog.csdn.net/20171026123922665?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvdG9tb3lhX2NoZW4=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

### �Զ��庯��
> �����������jar������Jmeter��/lib/ext·���£���������Jmeter���㽫�������**�������ֶԻ���**���ҵ����ǡ����˶��������һ���»���**"_"**Ϊǰ׺��

![jmeter_extra_tool](http://img.blog.csdn.net/20171023092219747?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvdG9tb3lhX2NoZW4=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)


## �����
### �Զ��庯��ʾ��
> **ע�⣺**��дJmeter�Զ��庯��ʱ�������������"**functions**"������**ApacheJMeter_core.jar**�ļ��������Ŀ�С���󵼳���jar������/lib/extĿ¼�¡�


``` java
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
```



## ������ʷ
|�汾     |����      |��ע      |
|:--------|:---------|:---------|
|v1.0     |2017-10-07|����|
|v1.1     |2017-10-25|����Excel�ࣨ����Apache POI��|
|         |         |          |


## ��Դ��ַ
- coding.net��[https://coding.net/u/tomoya_chen/p/jmeter_extra_tool/git](https://coding.net/u/tomoya_chen/p/jmeter_extra_tool/git "jmeter_extra_tool")


## �����뽨��
- ���ͣ�[@tomoya_chen](http://blog.csdn.net/tomoya_chen "���˲���")
- ���䣺<tomoya_chen@163.com>


---------
��л�Ķ���ݰ����ĵ������˻��Ǹ����ŵĳ�ѧ�ߣ�����з����ͽ����벻�ߴͽ̣�

  [1]: http://blog.csdn.net/tomoya_chen


