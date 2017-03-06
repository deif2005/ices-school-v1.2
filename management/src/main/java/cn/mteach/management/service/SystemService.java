package cn.mteach.management.service;

import cn.mteach.common.util.MenuItem;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;

@Service
public interface SystemService {

	public LinkedHashMap<String,MenuItem> getMenuItemsByAuthority(String authority);
}
