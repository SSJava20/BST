package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import bst.ConsoleTreeVisitor;
import bst.IBST;
import bst.IntBST;

public class BSTTest {

	Map<Integer, Integer> map;
	List<Integer> notUsedKeys;
	List<Integer> toRemoveKeys;
	IBST bst;

	@Before
	public void before() {
		map = new LinkedHashMap<Integer, Integer>();
		notUsedKeys = new ArrayList<Integer>();
		toRemoveKeys = new ArrayList<Integer>();
		bst = new IntBST();
	}

	public void init() {
		// fill test map
		map.put(45, 45);
		map.put(18, 18);
		map.put(2, 2);
		map.put(48, 48);
		map.put(49, 49);
		map.put(40, 40);
		map.put(7845643, 7845643);

		// fill not used keys
		notUsedKeys.add(-564);
		notUsedKeys.add(-1321);
		notUsedKeys.add(9893);
		notUsedKeys.add(0);
		notUsedKeys.add(888);

		// fill keys to be deleted in test
		toRemoveKeys.add(45);
		toRemoveKeys.add(18);
		toRemoveKeys.add(48);

		// fill BST object by map values
		for (Integer key : map.keySet())
		{
			bst.add(key, map.get(key));
		}
	}

	@Test
	public void addGetTest() {

		init();
		compare(bst, map);
	}

	@Test
	public void containsKeyTest() {
		init();
		for (Integer key : map.keySet())
		{
			assertTrue(bst.containsKey(key));
		}
		for (Integer key : notUsedKeys)
		{
			assertFalse(bst.containsKey(key));
		}
	}

	@Test
	public void removeTest() {
		init();
		for (Integer key : toRemoveKeys)
		{
			bst.remove(key);
			map.remove(key);
		}
		compare(bst, map);
	}

	@Test
	public void bigDataTest() {
		for (int i = 0; i < 1000; i++)
		{
			bst.add(i, i);
			map.put(i, i);
		}
		compare(bst, map);
	}

	@Test
	public void dublicateKeyTest() {

		for (int i = 0; i < 1000; i++)
		{
			bst.add(i, i);
			map.put(i, i);
		}
		compare(bst, map);
		for (int i = 0; i < 1000; i++)
		{
			bst.add(i, i * 2);
			map.put(i, i * 2);
		}
	}

	@Test
	public void nullTest() {
		for (int i = 0; i < 10; i++)
		{
			bst.add(i, null);
			map.put(i, null);
		}
		compare(bst, map);
	}
	
	@Test
	public void visitorTest() {
		init();
		ConsoleTreeVisitor treeVisitor = new ConsoleTreeVisitor();
		bst.traverseAll(treeVisitor);
		compare(bst, map);
	}

	private void compare(IBST bst, Map<Integer, Integer> map) {
		for (Integer key : map.keySet())
		{
			assertEquals(map.get(key), bst.get(key));
			assertTrue(false);
		}

	}
}
