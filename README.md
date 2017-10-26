# jmeter_extra_tool

@(Jmeter)[工具类|自定义函数|软件测试]

**jmeter_extra_tool**是一个专为Jmeter服务的常用工具类和自定义函数集。为日常的测试工作带来方便。
 
- **com.tomoya.jmeter** 
	- **Tool类**：包含获取MD5值、获取中文名、JDBC结果写入文件等方法、数组写入Excel等方法
	- **RSA类**：包含公钥加密、私钥解密；私钥加密、公钥解密等方法
	- **Excel类**：包含覆盖写入和单行追加写入Excel文件的方法
- **com.tomoya.functions**
	- **...** 

-------------------

[TOC]

## 项目结构
### com.tomoya.jmeter
- com.tomoya.jmeter
	- com.tomoya.jmeter.Tool
		- getChineseName()
		<br>获取2~4个字的中文名，常用字版（名字为五个笔画以内）
		- getMD5(String)
		<br>返回32位md5值
		- getHashCode(String)
		<br>返回HashCode值
		- writeFile(String, String)
		<br>字符串写入文件
		- JDBCResultWriteFile(ArrayList, String, String)
		<br>Jmeter的JDBC Result中指定列，写入文件
		- JDBCResultWriteFile(ArrayList, String[], String)
		<br>Jmeter的JDBC Result中指定多列，写入文件
	- com.tomoya.jmeter.RSA
		- pubKeyEncrypt(String, String)
		<br>使用公钥进行加密，返回公钥密文
        - priKeyDecrypt(String, String)
		<br>传入公钥密文，使用私钥进行解密，返回明文
        - priKeyEncrypt(String, String)
		<br>使用私钥进行加密，返回私钥密文
        - pubKeyDecrypt(String, String)
		<br>传入私钥密文，使用公钥进行解密，返回明文
	- com.tomoya.jmeter.Excel
        - writeToExcel(String[], String[], String)
		<br>传入标题、单行数据源、文件地址，追加写入Excel文件
        - writeToExcel2(String[], List<Map> String)
		<br>传入标题、数据集、文件地址，复写整个Excel文件

### com.tomoya.functions
- com.tomoya.functions
	- com.tomoya.functions.MD5
		- execute(SampleResult, Sampler)
		<br>return 函数运行结果
		- getArgumentDesc()
		<br>return 参数描述
		- getReferenceKey()
		<br>return 函数名
		- setParameters(Collection<>)
		<br>传入Jmeter中用户输入的参数
    - HashCode
        - ...
    - ChineseName
        - ...

##发布版下载
###jmeter_extra_tool-v1.0.jar
[点击进入下载页面](https://pan.baidu.com/s/1miR4Fry "jmeter_extra_tool-v1.0.jar")

###jmeter_extra_tool-v1.1.jar
[点击进入下载页面](https://pan.baidu.com/s/1dFixDNb "jmeter_extra_tool-v1.1.jar")

[Apache POI](http://poi.apache.org/download.html "Apache POI") (下载后把所有jar包copy到Jmeter安装路径的/lib/ext目录下)

##使用方法
### Tool类
> 将编译出来的jar包放入Jmeter的/lib/ext路径下，重新启动Jmeter。在BeanShell类型组件中 **import com.tomoya.jmeter.*;** 就可以调用Tool类和RSA类中的方法了。

![jmeter_extra_tool](http://img.blog.csdn.net/20171023092151650?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvdG9tb3lhX2NoZW4=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

### Excel类
> 将编译出来的jar包和下载的Apache POI 所有jar包放入Jmeter的/lib/ext路径下，重新启动Jmeter。在BeanShell类型组件中 **import com.tomoya.jmeter.*;** 就可以调用Excel类中的方法了。

![这里写图片描述](http://img.blog.csdn.net/20171026123922665?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvdG9tb3lhX2NoZW4=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

### 自定义函数
> 将编译出来的jar包放入Jmeter的/lib/ext路径下，重新启动Jmeter。你将会在你的**函数助手对话框**中找到它们。本人定义的是以一个下划线**"_"**为前缀。

![jmeter_extra_tool](http://img.blog.csdn.net/20171023092219747?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvdG9tb3lhX2NoZW4=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)


## 代码块
### 自定义函数示例
> **注意：**编写Jmeter自定义函数时，包名必须包含"**functions**"。导入**ApacheJMeter_core.jar**文件到你的项目中。最后导出的jar包放在/lib/ext目录下。


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
 * FileName: MD5.java Jmeter自定义函数，获取MD5值
 * 包名必须包含"functions"，导出的jar包放在/lib/ext目录下
 * 
 * @author tomoya
 * @version v1.0
 */
public class MD5 extends AbstractFunction {
	/** 函数描述 */
	private static final List<String> desc = new LinkedList<String>();
	static {
		// desc.add("Get a MD5 String within specified parameter value.");
		desc.add("明文");
	}

	/** 函数名称 */
	private static final String FUNCTION_NAME = "_MD5";
	
	/** 传入参数的最小数量 */
	private static final int MIN_PARA_COUNT = 1;
	/** 传入参数的最大数量 */
	private static final int MAX_PARA_COUNT = 1;

	/** 函数接收值 */
	private Object[] values;
	
	/**
	 * 函数算法
	 * @return 函数运算结果
	 */
	private String run() {
		String str = new String(((CompoundVariable) values[0]).execute().trim());
		String MD5 = Tool.getMD5(str);
		return String.valueOf(MD5);
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
```



## 更新历史
|版本     |日期      |备注      |
|:--------|:---------|:---------|
|v1.0     |2017-10-07|建立|
|v1.1     |2017-10-25|新增Excel类（依赖Apache POI）|
|         |         |          |


## 开源地址
- coding.net：[https://coding.net/u/tomoya_chen/p/jmeter_extra_tool/git](https://coding.net/u/tomoya_chen/p/jmeter_extra_tool/git "jmeter_extra_tool")


## 反馈与建议
- 博客：[@tomoya_chen](http://blog.csdn.net/tomoya_chen "个人博客")
- 邮箱：<tomoya_chen@163.com>


---------
感谢阅读这份帮助文档。本人还是刚入门的初学者，如果有反馈和建议请不吝赐教！

  [1]: http://blog.csdn.net/tomoya_chen


