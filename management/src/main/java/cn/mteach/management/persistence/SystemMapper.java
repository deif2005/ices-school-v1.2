package cn.mteach.management.persistence;

import cn.mteach.common.util.MenuItem;

import java.util.List;


public interface SystemMapper {

	public List<MenuItem> getMenuItemsByAuthority(String authority);
}
