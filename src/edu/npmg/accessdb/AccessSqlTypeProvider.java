package edu.npmg.accessdb;

import java.util.HashMap;
import java.util.Map;

public class AccessSqlTypeProvider implements SqlTypeProvider
{
	private Map<String, String> types = new HashMap<String, String>() {{
		put("Integer", "Number");
		put("String", "TEXT");
		put("Date", "Date");
		put("Boolean", "Boolean");
	}};
	@Override
	public String getSqlType(String javaType)
	{
		return types.get(javaType);
	}

}
