package com.zwjgsgoa.dao.impl;

import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;

import com.zwjgsgoa.exception.ZGException;
import com.zwjgsgoa.model.BaseModel;

@Component("baseDao")
public class BaseDao {
	private HibernateTemplate hibernateTemplate;
	//匹配的规则
	public final static String MATCH_NULL_4_ALL = "1";
	public final static String MATCH_NULL_4_SOME = "2";
	public final static String NO_MATCH = "-1";

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	@Resource
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
	
	public void findModel(BaseModel model, String matchType){
//		hibernateTemplate.find
	}
	
	public void saveModel(BaseModel model, String matchType) throws Exception {
		if (matchType.equals(MATCH_NULL_4_ALL)) {
			if (!matchNull(model)) {
				hibernateTemplate.save(model);
			} else {
				throw new ZGException(model.getClass() + ":"+ZGException.ATTRIBUTE_IS_NULL_OR_EMPTY);
			}
		}
	}

	public void delModel(BaseModel model, String matchType) throws Exception {
		if (matchType.equals(MATCH_NULL_4_ALL)) {
			if (matchNull(model)) {
				hibernateTemplate.delete(model);
			} else {
				throw new ZGException(model.getClass() + ":"+ZGException.ATTRIBUTE_IS_NULL_OR_EMPTY);
			}
		}

	}

	public void updateModel(BaseModel model, String matchType) throws Exception {
		if (matchType.equals(MATCH_NULL_4_ALL)) {
			if (matchNull(model)) {
				hibernateTemplate.update(model);
			} else {
				throw new ZGException(model.getClass() + ":"+ZGException.ATTRIBUTE_IS_NULL_OR_EMPTY);
			}
		}
	}

	public void saveOrUpdateModel(BaseModel model, String matchType)
			throws Exception {
		if (matchType.equals(MATCH_NULL_4_ALL)) {
			if (matchNull(model)) {
				hibernateTemplate.saveOrUpdate(model);
			} else {
				throw new ZGException(model.getClass() + ":"+ZGException.ATTRIBUTE_IS_NULL_OR_EMPTY);
			}
		}
	}

	public Object executeSql(final String sql, final Map<String, String> params) {
		return hibernateTemplate.execute(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				Query query = session.createQuery(sql);
				Set<String> paramskey = params.keySet();
				Iterator iterator = paramskey.iterator();
				while (iterator.hasNext()) {
					String key = iterator.next().toString();
					query.setString(key, params.get(key));
				}
				return query.list();
			}
		});
	}
	
	/** 
	 * @description 数据检测是否有NULL 或者空 或者长度为0的List
	 * @param  需要检测的对象
	 * @return boolean 是否有有NULL 或者空 或者长度为0的List
	 * @throws 
	 **/
	private boolean matchNull(Object param) {
		boolean isNull = true;
		try {
			if(param == null)
				throw new Exception();
			Class paramClass = param.getClass();
			//������еķ���
			Method[] paramMethod = paramClass.getMethods();
			for (int i = 0; i < paramMethod.length; i++) {
				Method paramMethodTemp = paramMethod[i];
				String methodName = paramMethodTemp.getName();
				if (methodName.length() >= 3) {
					//��ȡ���е�getXXX���� �鿴���ֵ
					if ("get".equals(methodName.substring(0, 3))) {
						Class returnType = paramMethodTemp.getReturnType();
						Object retrunValue = paramMethodTemp
								.invoke(param, null);
						if (retrunValue == null) {
							isNull = false;
							continue;
						}
						
						//�鿴���󷵻ص��ǲ���LIST �� MAP ����� ������ĳ����Ƿ�Ϊ0
						if (returnType.isInstance(new ArrayList())) {
							if (((List) retrunValue).isEmpty()) {
								isNull = false;
								continue;
							}
						}
						if (returnType.isInstance(new HashMap())) {
							if (((Map) retrunValue).isEmpty()) {
								isNull = false;
								continue;
							}
						}
						// returnObject = paramMethodTemp.invoke(param, null);

					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isNull;
	}
}
