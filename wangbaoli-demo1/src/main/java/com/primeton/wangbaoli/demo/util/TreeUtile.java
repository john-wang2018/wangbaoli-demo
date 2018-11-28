package com.primeton.wangbaoli.demo.util;

import java.util.ArrayList;
import java.util.List;

import com.primeton.wangbaoli.demo.entity.Org;
/**
 * 生成树状数据结构
 * @author root
 *
 */
public class TreeUtile {
	public static List<Org> RecursiveOrg(List<Org> nods) {
		List<Org> trees = new ArrayList<Org>();
		for (Org tree : nods) {
			if (null == tree.getSuperOrgId()) {
				trees.add(findChild(tree, nods));
			}
		}
		return trees;

	}

	private static Org findChild(Org tree, List<Org> nods) {
		for (Org org : nods) {
			if (tree.getId().equals(org.getSuperOrgId())) {
				if (tree.getChildrens() == null) {
					tree.setChildrens(new ArrayList<Org>());
				}
				tree.getChildrens().add(findChild(org, nods));
			}

		}
		return tree;
	}

}
