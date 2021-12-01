package edu.npmg.accessdb;

import java.util.HashMap;
import java.util.Map;

public class AccessSqlTypeProvider implements SqlTypeProvider
{
	private Map<String, String> types = new HashMap<>() {{
		put("Integer", "INTEGER");
		put("String", "TEXT");
		put("Date", "Date");
		put("Boolean", "Boolean");
		put("int", "INTEGER");
		put("boolean", "Boolean");
	}};
	@Override
	public String getSqlType(String javaType)
	{
		return types.get(javaType);
	}

}
